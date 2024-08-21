package com.google.firebase.firestore.util;

import android.os.Handler;
import android.os.Looper;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import javax.annotation.CheckReturnValue;

public class AsyncQueue {
    private final ArrayList<DelayedTask> delayedTasks = new ArrayList<>();
    /* access modifiers changed from: private */
    public final SynchronizedShutdownAwareExecutor executor = new SynchronizedShutdownAwareExecutor();
    private final ArrayList<TimerId> timerIdsToSkip = new ArrayList<>();

    public enum TimerId {
        ALL,
        LISTEN_STREAM_IDLE,
        LISTEN_STREAM_CONNECTION_BACKOFF,
        WRITE_STREAM_IDLE,
        WRITE_STREAM_CONNECTION_BACKOFF,
        ONLINE_STATE_TIMEOUT,
        GARBAGE_COLLECTION,
        RETRY_TRANSACTION,
        CONNECTIVITY_ATTEMPT_TIMER
    }

    public class DelayedTask {
        private ScheduledFuture scheduledFuture;
        /* access modifiers changed from: private */
        public final long targetTimeMs;
        private final Runnable task;
        /* access modifiers changed from: private */
        public final TimerId timerId;

        private DelayedTask(TimerId timerId2, long j, Runnable runnable) {
            this.timerId = timerId2;
            this.targetTimeMs = j;
            this.task = runnable;
        }

        /* access modifiers changed from: private */
        public void start(long j) {
            this.scheduledFuture = AsyncQueue.this.executor.schedule(AsyncQueue$DelayedTask$$Lambda$1.lambdaFactory$(this), j, TimeUnit.MILLISECONDS);
        }

        /* access modifiers changed from: package-private */
        public void skipDelay() {
            handleDelayElapsed();
        }

        public void cancel() {
            AsyncQueue.this.verifyIsCurrentThread();
            ScheduledFuture scheduledFuture2 = this.scheduledFuture;
            if (scheduledFuture2 != null) {
                scheduledFuture2.cancel(false);
                markDone();
            }
        }

        /* access modifiers changed from: private */
        public void handleDelayElapsed() {
            AsyncQueue.this.verifyIsCurrentThread();
            if (this.scheduledFuture != null) {
                markDone();
                this.task.run();
            }
        }

        private void markDone() {
            Assert.hardAssert(this.scheduledFuture != null, "Caller should have verified scheduledFuture is non-null.", new Object[0]);
            this.scheduledFuture = null;
            AsyncQueue.this.removeDelayedTask(this);
        }
    }

    public static <TResult> Task<TResult> callTask(Executor executor2, Callable<Task<TResult>> callable) {
        TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        executor2.execute(AsyncQueue$$Lambda$1.lambdaFactory$(callable, executor2, taskCompletionSource));
        return taskCompletionSource.getTask();
    }

    static /* synthetic */ void lambda$callTask$1(Callable callable, Executor executor2, TaskCompletionSource taskCompletionSource) {
        try {
            ((Task) callable.call()).continueWith(executor2, AsyncQueue$$Lambda$7.lambdaFactory$(taskCompletionSource));
        } catch (Exception e) {
            taskCompletionSource.setException(e);
        } catch (Throwable th) {
            taskCompletionSource.setException(new IllegalStateException("Unhandled throwable in callTask.", th));
        }
    }

    static /* synthetic */ Void lambda$callTask$0(TaskCompletionSource taskCompletionSource, Task task) throws Exception {
        if (task.isSuccessful()) {
            taskCompletionSource.setResult(task.getResult());
            return null;
        }
        taskCompletionSource.setException(task.getException());
        return null;
    }

    private class SynchronizedShutdownAwareExecutor implements Executor {
        private final ScheduledThreadPoolExecutor internalExecutor;
        private boolean isShuttingDown = false;
        /* access modifiers changed from: private */
        public final Thread thread;

        private class DelayedStartFactory implements Runnable, ThreadFactory {
            private Runnable delegate;
            private final CountDownLatch latch;

            private DelayedStartFactory() {
                this.latch = new CountDownLatch(1);
            }

            public void run() {
                try {
                    this.latch.await();
                } catch (InterruptedException unused) {
                    Thread.currentThread().interrupt();
                }
                this.delegate.run();
            }

            public Thread newThread(Runnable runnable) {
                Assert.hardAssert(this.delegate == null, "Only one thread may be created in an AsyncQueue.", new Object[0]);
                this.delegate = runnable;
                this.latch.countDown();
                return SynchronizedShutdownAwareExecutor.this.thread;
            }
        }

        SynchronizedShutdownAwareExecutor() {
            DelayedStartFactory delayedStartFactory = new DelayedStartFactory();
            Thread newThread = Executors.defaultThreadFactory().newThread(delayedStartFactory);
            this.thread = newThread;
            newThread.setName("FirestoreWorker");
            newThread.setDaemon(true);
            newThread.setUncaughtExceptionHandler(AsyncQueue$SynchronizedShutdownAwareExecutor$$Lambda$1.lambdaFactory$(this));
            C19191 r1 = new ScheduledThreadPoolExecutor(1, delayedStartFactory, AsyncQueue.this) {
                /* access modifiers changed from: protected */
                public void afterExecute(Runnable runnable, Throwable th) {
                    super.afterExecute(runnable, th);
                    if (th == null && (runnable instanceof Future)) {
                        Future future = (Future) runnable;
                        try {
                            if (future.isDone()) {
                                future.get();
                            }
                        } catch (CancellationException unused) {
                        } catch (ExecutionException e) {
                            th = e.getCause();
                        } catch (InterruptedException unused2) {
                            Thread.currentThread().interrupt();
                        }
                    }
                    if (th != null) {
                        AsyncQueue.this.panic(th);
                    }
                }
            };
            this.internalExecutor = r1;
            r1.setKeepAliveTime(3, TimeUnit.SECONDS);
        }

        /* access modifiers changed from: private */
        public synchronized boolean isShuttingDown() {
            return this.isShuttingDown;
        }

        public synchronized void execute(Runnable runnable) {
            if (!this.isShuttingDown) {
                this.internalExecutor.execute(runnable);
            }
        }

        public void executeEvenAfterShutdown(Runnable runnable) {
            try {
                this.internalExecutor.execute(runnable);
            } catch (RejectedExecutionException unused) {
                Logger.warn(AsyncQueue.class.getSimpleName(), "Refused to enqueue task after panic", new Object[0]);
            }
        }

        /* access modifiers changed from: private */
        public <T> Task<T> executeAndReportResult(Callable<T> callable) {
            TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
            try {
                execute(AsyncQueue$SynchronizedShutdownAwareExecutor$$Lambda$2.lambdaFactory$(taskCompletionSource, callable));
            } catch (RejectedExecutionException unused) {
                Logger.warn(AsyncQueue.class.getSimpleName(), "Refused to enqueue task after panic", new Object[0]);
            }
            return taskCompletionSource.getTask();
        }

        static /* synthetic */ void lambda$executeAndReportResult$1(TaskCompletionSource taskCompletionSource, Callable callable) {
            try {
                taskCompletionSource.setResult(callable.call());
            } catch (Exception e) {
                taskCompletionSource.setException(e);
                throw new RuntimeException(e);
            }
        }

        /* access modifiers changed from: private */
        public synchronized Task<Void> executeAndInitiateShutdown(Runnable runnable) {
            if (isShuttingDown()) {
                TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
                taskCompletionSource.setResult(null);
                return taskCompletionSource.getTask();
            }
            Task<Void> executeAndReportResult = executeAndReportResult(AsyncQueue$SynchronizedShutdownAwareExecutor$$Lambda$3.lambdaFactory$(runnable));
            this.isShuttingDown = true;
            return executeAndReportResult;
        }

        /* access modifiers changed from: private */
        public synchronized ScheduledFuture<?> schedule(Runnable runnable, long j, TimeUnit timeUnit) {
            if (this.isShuttingDown) {
                return null;
            }
            return this.internalExecutor.schedule(runnable, j, timeUnit);
        }

        /* access modifiers changed from: private */
        public void shutdownNow() {
            this.internalExecutor.shutdownNow();
        }

        /* access modifiers changed from: private */
        public void setCorePoolSize(int i) {
            this.internalExecutor.setCorePoolSize(i);
        }
    }

    public Executor getExecutor() {
        return this.executor;
    }

    public void verifyIsCurrentThread() {
        Thread currentThread = Thread.currentThread();
        if (this.executor.thread != currentThread) {
            throw Assert.fail("We are running on the wrong thread. Expected to be on the AsyncQueue thread %s/%d but was %s/%d", this.executor.thread.getName(), Long.valueOf(this.executor.thread.getId()), currentThread.getName(), Long.valueOf(currentThread.getId()));
        }
    }

    @CheckReturnValue
    public <T> Task<T> enqueue(Callable<T> callable) {
        return this.executor.executeAndReportResult(callable);
    }

    @CheckReturnValue
    public Task<Void> enqueue(Runnable runnable) {
        return enqueue(AsyncQueue$$Lambda$2.lambdaFactory$(runnable));
    }

    public Task<Void> enqueueAndInitiateShutdown(Runnable runnable) {
        return this.executor.executeAndInitiateShutdown(runnable);
    }

    public void enqueueAndForgetEvenAfterShutdown(Runnable runnable) {
        this.executor.executeEvenAfterShutdown(runnable);
    }

    public boolean isShuttingDown() {
        return this.executor.isShuttingDown();
    }

    public void enqueueAndForget(Runnable runnable) {
        enqueue(runnable);
    }

    public DelayedTask enqueueAfterDelay(TimerId timerId, long j, Runnable runnable) {
        if (this.timerIdsToSkip.contains(timerId)) {
            j = 0;
        }
        DelayedTask createAndScheduleDelayedTask = createAndScheduleDelayedTask(timerId, j, runnable);
        this.delayedTasks.add(createAndScheduleDelayedTask);
        return createAndScheduleDelayedTask;
    }

    public void skipDelaysForTimerId(TimerId timerId) {
        this.timerIdsToSkip.add(timerId);
    }

    public void panic(Throwable th) {
        this.executor.shutdownNow();
        new Handler(Looper.getMainLooper()).post(AsyncQueue$$Lambda$3.lambdaFactory$(th));
    }

    static /* synthetic */ void lambda$panic$3(Throwable th) {
        if (th instanceof OutOfMemoryError) {
            OutOfMemoryError outOfMemoryError = new OutOfMemoryError("Firestore (22.0.1) ran out of memory. Check your queries to make sure they are not loading an excessive amount of data.");
            outOfMemoryError.initCause(th);
            throw outOfMemoryError;
        }
        throw new RuntimeException("Internal error in Cloud Firestore (22.0.1).", th);
    }

    public void runSync(Runnable runnable) throws InterruptedException {
        Semaphore semaphore = new Semaphore(0);
        Throwable[] thArr = new Throwable[1];
        enqueueAndForget(AsyncQueue$$Lambda$4.lambdaFactory$(runnable, thArr, semaphore));
        semaphore.acquire(1);
        if (thArr[0] != null) {
            throw new RuntimeException("Synchronous task failed", thArr[0]);
        }
    }

    static /* synthetic */ void lambda$runSync$4(Runnable runnable, Throwable[] thArr, Semaphore semaphore) {
        try {
            runnable.run();
        } catch (Throwable th) {
            thArr[0] = th;
        }
        semaphore.release();
    }

    public boolean containsDelayedTask(TimerId timerId) {
        Iterator<DelayedTask> it = this.delayedTasks.iterator();
        while (it.hasNext()) {
            if (it.next().timerId == timerId) {
                return true;
            }
        }
        return false;
    }

    public void runDelayedTasksUntil(TimerId timerId) throws InterruptedException {
        runSync(AsyncQueue$$Lambda$5.lambdaFactory$(this, timerId));
    }

    static /* synthetic */ void lambda$runDelayedTasksUntil$6(AsyncQueue asyncQueue, TimerId timerId) {
        Assert.hardAssert(timerId == TimerId.ALL || asyncQueue.containsDelayedTask(timerId), "Attempted to run tasks until missing TimerId: %s", timerId);
        Collections.sort(asyncQueue.delayedTasks, AsyncQueue$$Lambda$6.lambdaFactory$());
        Iterator it = new ArrayList(asyncQueue.delayedTasks).iterator();
        while (it.hasNext()) {
            DelayedTask delayedTask = (DelayedTask) it.next();
            delayedTask.skipDelay();
            if (timerId != TimerId.ALL && delayedTask.timerId == timerId) {
                return;
            }
        }
    }

    public void shutdown() {
        this.executor.setCorePoolSize(0);
    }

    private DelayedTask createAndScheduleDelayedTask(TimerId timerId, long j, Runnable runnable) {
        DelayedTask delayedTask = new DelayedTask(timerId, System.currentTimeMillis() + j, runnable);
        delayedTask.start(j);
        return delayedTask;
    }

    /* access modifiers changed from: private */
    public void removeDelayedTask(DelayedTask delayedTask) {
        Assert.hardAssert(this.delayedTasks.remove(delayedTask), "Delayed task not found.", new Object[0]);
    }
}

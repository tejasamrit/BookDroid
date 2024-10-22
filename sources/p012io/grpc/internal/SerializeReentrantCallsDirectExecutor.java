package p012io.grpc.internal;

import java.util.ArrayDeque;
import java.util.concurrent.Executor;
import java.util.logging.Level;
import java.util.logging.Logger;

/* renamed from: io.grpc.internal.SerializeReentrantCallsDirectExecutor */
class SerializeReentrantCallsDirectExecutor implements Executor {
    private static final Logger log = Logger.getLogger(SerializeReentrantCallsDirectExecutor.class.getName());
    private boolean executing;
    private ArrayDeque<Runnable> taskQueue;

    SerializeReentrantCallsDirectExecutor() {
    }

    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0035, code lost:
        if (r6.taskQueue == null) goto L_0x0017;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:6:0x0012, code lost:
        if (r6.taskQueue != null) goto L_0x0014;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:7:0x0014, code lost:
        completeQueuedTasks();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0017, code lost:
        r6.executing = false;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void execute(java.lang.Runnable r7) {
        /*
            r6 = this;
            java.lang.String r0 = "'task' must not be null."
            com.google.common.base.Preconditions.checkNotNull(r7, r0)
            boolean r0 = r6.executing
            if (r0 != 0) goto L_0x0043
            r0 = 1
            r6.executing = r0
            r0 = 0
            r7.run()     // Catch:{ all -> 0x001a }
            java.util.ArrayDeque<java.lang.Runnable> r7 = r6.taskQueue
            if (r7 == 0) goto L_0x0017
        L_0x0014:
            r6.completeQueuedTasks()
        L_0x0017:
            r6.executing = r0
            goto L_0x0046
        L_0x001a:
            r1 = move-exception
            java.util.logging.Logger r2 = log     // Catch:{ all -> 0x0038 }
            java.util.logging.Level r3 = java.util.logging.Level.SEVERE     // Catch:{ all -> 0x0038 }
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ all -> 0x0038 }
            r4.<init>()     // Catch:{ all -> 0x0038 }
            java.lang.String r5 = "Exception while executing runnable "
            r4.append(r5)     // Catch:{ all -> 0x0038 }
            r4.append(r7)     // Catch:{ all -> 0x0038 }
            java.lang.String r7 = r4.toString()     // Catch:{ all -> 0x0038 }
            r2.log(r3, r7, r1)     // Catch:{ all -> 0x0038 }
            java.util.ArrayDeque<java.lang.Runnable> r7 = r6.taskQueue
            if (r7 == 0) goto L_0x0017
            goto L_0x0014
        L_0x0038:
            r7 = move-exception
            java.util.ArrayDeque<java.lang.Runnable> r1 = r6.taskQueue
            if (r1 == 0) goto L_0x0040
            r6.completeQueuedTasks()
        L_0x0040:
            r6.executing = r0
            throw r7
        L_0x0043:
            r6.enqueue(r7)
        L_0x0046:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: p012io.grpc.internal.SerializeReentrantCallsDirectExecutor.execute(java.lang.Runnable):void");
    }

    private void completeQueuedTasks() {
        while (true) {
            Runnable poll = this.taskQueue.poll();
            if (poll != null) {
                try {
                    poll.run();
                } catch (Throwable th) {
                    Logger logger = log;
                    Level level = Level.SEVERE;
                    logger.log(level, "Exception while executing runnable " + poll, th);
                }
            } else {
                return;
            }
        }
    }

    private void enqueue(Runnable runnable) {
        if (this.taskQueue == null) {
            this.taskQueue = new ArrayDeque<>(4);
        }
        this.taskQueue.add(runnable);
    }
}

package com.google.firebase.firestore.util;

import com.google.firebase.firestore.util.AsyncQueue;
import java.util.Date;

public class ExponentialBackoff {
    public static final double DEFAULT_BACKOFF_FACTOR = 1.5d;
    public static final long DEFAULT_BACKOFF_INITIAL_DELAY_MS = 1000;
    public static final long DEFAULT_BACKOFF_MAX_DELAY_MS = 60000;
    private final double backoffFactor;
    private long currentBaseMs;
    private final long initialDelayMs;
    private long lastAttemptTime;
    private final long maxDelayMs;
    private long nextMaxDelayMs;
    private final AsyncQueue queue;
    private final AsyncQueue.TimerId timerId;
    private AsyncQueue.DelayedTask timerTask;

    public ExponentialBackoff(AsyncQueue asyncQueue, AsyncQueue.TimerId timerId2, long j, double d, long j2) {
        this.queue = asyncQueue;
        this.timerId = timerId2;
        this.initialDelayMs = j;
        this.backoffFactor = d;
        this.maxDelayMs = j2;
        this.nextMaxDelayMs = j2;
        this.lastAttemptTime = new Date().getTime();
        reset();
    }

    public ExponentialBackoff(AsyncQueue asyncQueue, AsyncQueue.TimerId timerId2) {
        this(asyncQueue, timerId2, 1000, 1.5d, DEFAULT_BACKOFF_MAX_DELAY_MS);
    }

    public void reset() {
        this.currentBaseMs = 0;
    }

    public void resetToMax() {
        this.currentBaseMs = this.nextMaxDelayMs;
    }

    public void setTemporaryMaxDelay(long j) {
        this.nextMaxDelayMs = j;
    }

    public void backoffAndRun(Runnable runnable) {
        cancel();
        long jitterDelayMs = this.currentBaseMs + jitterDelayMs();
        long max = Math.max(0, new Date().getTime() - this.lastAttemptTime);
        long max2 = Math.max(0, jitterDelayMs - max);
        if (this.currentBaseMs > 0) {
            Logger.debug(getClass().getSimpleName(), "Backing off for %d ms (base delay: %d ms, delay with jitter: %d ms, last attempt: %d ms ago)", Long.valueOf(max2), Long.valueOf(this.currentBaseMs), Long.valueOf(jitterDelayMs), Long.valueOf(max));
        }
        this.timerTask = this.queue.enqueueAfterDelay(this.timerId, max2, ExponentialBackoff$$Lambda$1.lambdaFactory$(this, runnable));
        long j = (long) (((double) this.currentBaseMs) * this.backoffFactor);
        this.currentBaseMs = j;
        long j2 = this.initialDelayMs;
        if (j < j2) {
            this.currentBaseMs = j2;
        } else {
            long j3 = this.nextMaxDelayMs;
            if (j > j3) {
                this.currentBaseMs = j3;
            }
        }
        this.nextMaxDelayMs = this.maxDelayMs;
    }

    static /* synthetic */ void lambda$backoffAndRun$0(ExponentialBackoff exponentialBackoff, Runnable runnable) {
        exponentialBackoff.lastAttemptTime = new Date().getTime();
        runnable.run();
    }

    public void cancel() {
        AsyncQueue.DelayedTask delayedTask = this.timerTask;
        if (delayedTask != null) {
            delayedTask.cancel();
            this.timerTask = null;
        }
    }

    private long jitterDelayMs() {
        return (long) ((Math.random() - 0.5d) * ((double) this.currentBaseMs));
    }
}

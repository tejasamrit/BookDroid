package com.google.firebase.firestore.util;

import com.google.firebase.firestore.util.AsyncQueue;
import java.lang.Thread;

/* compiled from: AsyncQueue */
final /* synthetic */ class AsyncQueue$SynchronizedShutdownAwareExecutor$$Lambda$1 implements Thread.UncaughtExceptionHandler {
    private final AsyncQueue.SynchronizedShutdownAwareExecutor arg$1;

    private AsyncQueue$SynchronizedShutdownAwareExecutor$$Lambda$1(AsyncQueue.SynchronizedShutdownAwareExecutor synchronizedShutdownAwareExecutor) {
        this.arg$1 = synchronizedShutdownAwareExecutor;
    }

    public static Thread.UncaughtExceptionHandler lambdaFactory$(AsyncQueue.SynchronizedShutdownAwareExecutor synchronizedShutdownAwareExecutor) {
        return new AsyncQueue$SynchronizedShutdownAwareExecutor$$Lambda$1(synchronizedShutdownAwareExecutor);
    }

    public void uncaughtException(Thread thread, Throwable th) {
        AsyncQueue.this.panic(th);
    }
}

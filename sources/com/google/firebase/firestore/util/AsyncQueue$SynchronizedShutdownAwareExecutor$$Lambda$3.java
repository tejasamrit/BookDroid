package com.google.firebase.firestore.util;

import java.util.concurrent.Callable;

/* compiled from: AsyncQueue */
final /* synthetic */ class AsyncQueue$SynchronizedShutdownAwareExecutor$$Lambda$3 implements Callable {
    private final Runnable arg$1;

    private AsyncQueue$SynchronizedShutdownAwareExecutor$$Lambda$3(Runnable runnable) {
        this.arg$1 = runnable;
    }

    public static Callable lambdaFactory$(Runnable runnable) {
        return new AsyncQueue$SynchronizedShutdownAwareExecutor$$Lambda$3(runnable);
    }

    public Object call() {
        return this.arg$1.run();
    }
}

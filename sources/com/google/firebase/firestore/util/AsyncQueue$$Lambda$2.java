package com.google.firebase.firestore.util;

import java.util.concurrent.Callable;

/* compiled from: AsyncQueue */
final /* synthetic */ class AsyncQueue$$Lambda$2 implements Callable {
    private final Runnable arg$1;

    private AsyncQueue$$Lambda$2(Runnable runnable) {
        this.arg$1 = runnable;
    }

    public static Callable lambdaFactory$(Runnable runnable) {
        return new AsyncQueue$$Lambda$2(runnable);
    }

    public Object call() {
        return this.arg$1.run();
    }
}

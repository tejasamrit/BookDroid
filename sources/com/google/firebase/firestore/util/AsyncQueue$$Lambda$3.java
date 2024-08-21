package com.google.firebase.firestore.util;

/* compiled from: AsyncQueue */
final /* synthetic */ class AsyncQueue$$Lambda$3 implements Runnable {
    private final Throwable arg$1;

    private AsyncQueue$$Lambda$3(Throwable th) {
        this.arg$1 = th;
    }

    public static Runnable lambdaFactory$(Throwable th) {
        return new AsyncQueue$$Lambda$3(th);
    }

    public void run() {
        AsyncQueue.lambda$panic$3(this.arg$1);
    }
}

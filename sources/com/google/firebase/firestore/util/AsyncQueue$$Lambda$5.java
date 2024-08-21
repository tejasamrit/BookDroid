package com.google.firebase.firestore.util;

import com.google.firebase.firestore.util.AsyncQueue;

/* compiled from: AsyncQueue */
final /* synthetic */ class AsyncQueue$$Lambda$5 implements Runnable {
    private final AsyncQueue arg$1;
    private final AsyncQueue.TimerId arg$2;

    private AsyncQueue$$Lambda$5(AsyncQueue asyncQueue, AsyncQueue.TimerId timerId) {
        this.arg$1 = asyncQueue;
        this.arg$2 = timerId;
    }

    public static Runnable lambdaFactory$(AsyncQueue asyncQueue, AsyncQueue.TimerId timerId) {
        return new AsyncQueue$$Lambda$5(asyncQueue, timerId);
    }

    public void run() {
        AsyncQueue.lambda$runDelayedTasksUntil$6(this.arg$1, this.arg$2);
    }
}

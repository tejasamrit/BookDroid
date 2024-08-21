package com.google.firebase.firestore.util;

import com.google.firebase.firestore.util.AsyncQueue;
import java.util.Comparator;

/* compiled from: AsyncQueue */
final /* synthetic */ class AsyncQueue$$Lambda$6 implements Comparator {
    private static final AsyncQueue$$Lambda$6 instance = new AsyncQueue$$Lambda$6();

    private AsyncQueue$$Lambda$6() {
    }

    public static Comparator lambdaFactory$() {
        return instance;
    }

    public int compare(Object obj, Object obj2) {
        return Long.compare(((AsyncQueue.DelayedTask) obj).targetTimeMs, ((AsyncQueue.DelayedTask) obj2).targetTimeMs);
    }
}

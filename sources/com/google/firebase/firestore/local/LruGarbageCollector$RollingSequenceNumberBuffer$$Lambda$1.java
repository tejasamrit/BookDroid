package com.google.firebase.firestore.local;

import java.util.Comparator;

/* compiled from: LruGarbageCollector */
final /* synthetic */ class LruGarbageCollector$RollingSequenceNumberBuffer$$Lambda$1 implements Comparator {
    private static final LruGarbageCollector$RollingSequenceNumberBuffer$$Lambda$1 instance = new LruGarbageCollector$RollingSequenceNumberBuffer$$Lambda$1();

    private LruGarbageCollector$RollingSequenceNumberBuffer$$Lambda$1() {
    }

    public static Comparator lambdaFactory$() {
        return instance;
    }

    public int compare(Object obj, Object obj2) {
        return ((Long) obj2).compareTo((Long) obj);
    }
}

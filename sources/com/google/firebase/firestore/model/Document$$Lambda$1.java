package com.google.firebase.firestore.model;

import java.util.Comparator;

/* compiled from: Document */
final /* synthetic */ class Document$$Lambda$1 implements Comparator {
    private static final Document$$Lambda$1 instance = new Document$$Lambda$1();

    private Document$$Lambda$1() {
    }

    public static Comparator lambdaFactory$() {
        return instance;
    }

    public int compare(Object obj, Object obj2) {
        return ((Document) obj).getKey().compareTo(((Document) obj2).getKey());
    }
}

package com.google.firebase.firestore.model;

import java.util.Comparator;

/* compiled from: DocumentKey */
final /* synthetic */ class DocumentKey$$Lambda$1 implements Comparator {
    private static final DocumentKey$$Lambda$1 instance = new DocumentKey$$Lambda$1();

    private DocumentKey$$Lambda$1() {
    }

    public static Comparator lambdaFactory$() {
        return instance;
    }

    public int compare(Object obj, Object obj2) {
        return ((DocumentKey) obj).compareTo((DocumentKey) obj2);
    }
}

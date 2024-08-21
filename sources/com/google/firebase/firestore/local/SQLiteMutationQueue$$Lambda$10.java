package com.google.firebase.firestore.local;

import com.google.firebase.firestore.model.mutation.MutationBatch;
import com.google.firebase.firestore.util.Util;
import java.util.Comparator;

/* compiled from: SQLiteMutationQueue */
final /* synthetic */ class SQLiteMutationQueue$$Lambda$10 implements Comparator {
    private static final SQLiteMutationQueue$$Lambda$10 instance = new SQLiteMutationQueue$$Lambda$10();

    private SQLiteMutationQueue$$Lambda$10() {
    }

    public static Comparator lambdaFactory$() {
        return instance;
    }

    public int compare(Object obj, Object obj2) {
        return Util.compareIntegers(((MutationBatch) obj).getBatchId(), ((MutationBatch) obj2).getBatchId());
    }
}

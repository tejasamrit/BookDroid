package com.google.firebase.firestore.local;

import android.database.Cursor;
import com.google.firebase.firestore.util.Function;

/* compiled from: SQLiteMutationQueue */
final /* synthetic */ class SQLiteMutationQueue$$Lambda$5 implements Function {
    private final SQLiteMutationQueue arg$1;

    private SQLiteMutationQueue$$Lambda$5(SQLiteMutationQueue sQLiteMutationQueue) {
        this.arg$1 = sQLiteMutationQueue;
    }

    public static Function lambdaFactory$(SQLiteMutationQueue sQLiteMutationQueue) {
        return new SQLiteMutationQueue$$Lambda$5(sQLiteMutationQueue);
    }

    public Object apply(Object obj) {
        return this.arg$1.decodeInlineMutationBatch(((Cursor) obj).getInt(0), ((Cursor) obj).getBlob(1));
    }
}

package com.google.firebase.firestore.local;

import android.database.Cursor;
import com.google.firebase.firestore.util.Consumer;
import java.util.List;

/* compiled from: SQLiteMutationQueue */
final /* synthetic */ class SQLiteMutationQueue$$Lambda$11 implements Consumer {
    private final SQLiteMutationQueue arg$1;
    private final List arg$2;
    private final int arg$3;

    private SQLiteMutationQueue$$Lambda$11(SQLiteMutationQueue sQLiteMutationQueue, List list, int i) {
        this.arg$1 = sQLiteMutationQueue;
        this.arg$2 = list;
        this.arg$3 = i;
    }

    public static Consumer lambdaFactory$(SQLiteMutationQueue sQLiteMutationQueue, List list, int i) {
        return new SQLiteMutationQueue$$Lambda$11(sQLiteMutationQueue, list, i);
    }

    public void accept(Object obj) {
        SQLiteMutationQueue.lambda$getAllMutationBatchesAffectingQuery$10(this.arg$1, this.arg$2, this.arg$3, (Cursor) obj);
    }
}

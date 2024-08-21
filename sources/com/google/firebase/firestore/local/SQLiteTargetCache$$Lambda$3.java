package com.google.firebase.firestore.local;

import android.database.Cursor;
import android.util.SparseArray;
import com.google.firebase.firestore.util.Consumer;

/* compiled from: SQLiteTargetCache */
final /* synthetic */ class SQLiteTargetCache$$Lambda$3 implements Consumer {
    private final SQLiteTargetCache arg$1;
    private final SparseArray arg$2;
    private final int[] arg$3;

    private SQLiteTargetCache$$Lambda$3(SQLiteTargetCache sQLiteTargetCache, SparseArray sparseArray, int[] iArr) {
        this.arg$1 = sQLiteTargetCache;
        this.arg$2 = sparseArray;
        this.arg$3 = iArr;
    }

    public static Consumer lambdaFactory$(SQLiteTargetCache sQLiteTargetCache, SparseArray sparseArray, int[] iArr) {
        return new SQLiteTargetCache$$Lambda$3(sQLiteTargetCache, sparseArray, iArr);
    }

    public void accept(Object obj) {
        SQLiteTargetCache.lambda$removeQueries$2(this.arg$1, this.arg$2, this.arg$3, (Cursor) obj);
    }
}

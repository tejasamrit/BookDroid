package com.google.firebase.firestore.local;

import android.database.Cursor;
import com.google.firebase.firestore.util.Consumer;

/* compiled from: SQLiteTargetCache */
final /* synthetic */ class SQLiteTargetCache$$Lambda$1 implements Consumer {
    private final SQLiteTargetCache arg$1;

    private SQLiteTargetCache$$Lambda$1(SQLiteTargetCache sQLiteTargetCache) {
        this.arg$1 = sQLiteTargetCache;
    }

    public static Consumer lambdaFactory$(SQLiteTargetCache sQLiteTargetCache) {
        return new SQLiteTargetCache$$Lambda$1(sQLiteTargetCache);
    }

    public void accept(Object obj) {
        SQLiteTargetCache.lambda$start$0(this.arg$1, (Cursor) obj);
    }
}

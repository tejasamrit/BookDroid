package com.google.firebase.firestore.local;

import android.database.Cursor;
import com.google.firebase.firestore.util.Consumer;

/* compiled from: SQLiteTargetCache */
final /* synthetic */ class SQLiteTargetCache$$Lambda$2 implements Consumer {
    private final SQLiteTargetCache arg$1;
    private final Consumer arg$2;

    private SQLiteTargetCache$$Lambda$2(SQLiteTargetCache sQLiteTargetCache, Consumer consumer) {
        this.arg$1 = sQLiteTargetCache;
        this.arg$2 = consumer;
    }

    public static Consumer lambdaFactory$(SQLiteTargetCache sQLiteTargetCache, Consumer consumer) {
        return new SQLiteTargetCache$$Lambda$2(sQLiteTargetCache, consumer);
    }

    public void accept(Object obj) {
        this.arg$2.accept(this.arg$1.decodeTargetData(((Cursor) obj).getBlob(0)));
    }
}

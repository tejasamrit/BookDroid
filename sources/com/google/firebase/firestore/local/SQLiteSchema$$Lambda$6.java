package com.google.firebase.firestore.local;

import android.database.Cursor;
import com.google.firebase.firestore.util.Consumer;

/* compiled from: SQLiteSchema */
final /* synthetic */ class SQLiteSchema$$Lambda$6 implements Consumer {
    private final SQLiteSchema arg$1;

    private SQLiteSchema$$Lambda$6(SQLiteSchema sQLiteSchema) {
        this.arg$1 = sQLiteSchema;
    }

    public static Consumer lambdaFactory$(SQLiteSchema sQLiteSchema) {
        return new SQLiteSchema$$Lambda$6(sQLiteSchema);
    }

    public void accept(Object obj) {
        SQLiteSchema.lambda$dropLastLimboFreeSnapshotVersion$6(this.arg$1, (Cursor) obj);
    }
}

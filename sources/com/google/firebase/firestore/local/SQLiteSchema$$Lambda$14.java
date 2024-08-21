package com.google.firebase.firestore.local;

import android.database.Cursor;
import com.google.firebase.firestore.util.Consumer;

/* compiled from: SQLiteSchema */
final /* synthetic */ class SQLiteSchema$$Lambda$14 implements Consumer {
    private final SQLiteSchema arg$1;
    private final String arg$2;

    private SQLiteSchema$$Lambda$14(SQLiteSchema sQLiteSchema, String str) {
        this.arg$1 = sQLiteSchema;
        this.arg$2 = str;
    }

    public static Consumer lambdaFactory$(SQLiteSchema sQLiteSchema, String str) {
        return new SQLiteSchema$$Lambda$14(sQLiteSchema, str);
    }

    public void accept(Object obj) {
        this.arg$1.removeMutationBatch(this.arg$2, ((Cursor) obj).getInt(0));
    }
}

package com.google.firebase.firestore.local;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;
import com.google.firebase.firestore.util.Consumer;

/* compiled from: SQLiteSchema */
final /* synthetic */ class SQLiteSchema$$Lambda$8 implements Consumer {
    private final boolean[] arg$1;
    private final SQLiteStatement arg$2;
    private final long arg$3;

    private SQLiteSchema$$Lambda$8(boolean[] zArr, SQLiteStatement sQLiteStatement, long j) {
        this.arg$1 = zArr;
        this.arg$2 = sQLiteStatement;
        this.arg$3 = j;
    }

    public static Consumer lambdaFactory$(boolean[] zArr, SQLiteStatement sQLiteStatement, long j) {
        return new SQLiteSchema$$Lambda$8(zArr, sQLiteStatement, j);
    }

    public void accept(Object obj) {
        SQLiteSchema.lambda$ensureSequenceNumbers$8(this.arg$1, this.arg$2, this.arg$3, (Cursor) obj);
    }
}

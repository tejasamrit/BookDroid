package com.google.firebase.firestore.local;

import android.database.Cursor;
import com.google.firebase.firestore.util.Function;

/* compiled from: SQLiteRemoteDocumentCache */
final /* synthetic */ class SQLiteRemoteDocumentCache$$Lambda$1 implements Function {
    private final SQLiteRemoteDocumentCache arg$1;

    private SQLiteRemoteDocumentCache$$Lambda$1(SQLiteRemoteDocumentCache sQLiteRemoteDocumentCache) {
        this.arg$1 = sQLiteRemoteDocumentCache;
    }

    public static Function lambdaFactory$(SQLiteRemoteDocumentCache sQLiteRemoteDocumentCache) {
        return new SQLiteRemoteDocumentCache$$Lambda$1(sQLiteRemoteDocumentCache);
    }

    public Object apply(Object obj) {
        return this.arg$1.decodeMaybeDocument(((Cursor) obj).getBlob(0));
    }
}

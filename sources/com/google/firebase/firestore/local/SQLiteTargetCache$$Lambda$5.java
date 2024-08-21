package com.google.firebase.firestore.local;

import android.database.Cursor;
import com.google.firebase.firestore.local.SQLiteTargetCache;
import com.google.firebase.firestore.model.DocumentKey;
import com.google.firebase.firestore.util.Consumer;

/* compiled from: SQLiteTargetCache */
final /* synthetic */ class SQLiteTargetCache$$Lambda$5 implements Consumer {
    private final SQLiteTargetCache.DocumentKeysHolder arg$1;

    private SQLiteTargetCache$$Lambda$5(SQLiteTargetCache.DocumentKeysHolder documentKeysHolder) {
        this.arg$1 = documentKeysHolder;
    }

    public static Consumer lambdaFactory$(SQLiteTargetCache.DocumentKeysHolder documentKeysHolder) {
        return new SQLiteTargetCache$$Lambda$5(documentKeysHolder);
    }

    public void accept(Object obj) {
        this.arg$1.keys = this.arg$1.keys.insert(DocumentKey.fromPath(EncodedPath.decodeResourcePath(((Cursor) obj).getString(0))));
    }
}

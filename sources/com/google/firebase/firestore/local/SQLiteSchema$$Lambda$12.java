package com.google.firebase.firestore.local;

import android.database.Cursor;
import com.google.firebase.firestore.model.ResourcePath;
import com.google.firebase.firestore.util.Consumer;

/* compiled from: SQLiteSchema */
final /* synthetic */ class SQLiteSchema$$Lambda$12 implements Consumer {
    private final Consumer arg$1;

    private SQLiteSchema$$Lambda$12(Consumer consumer) {
        this.arg$1 = consumer;
    }

    public static Consumer lambdaFactory$(Consumer consumer) {
        return new SQLiteSchema$$Lambda$12(consumer);
    }

    public void accept(Object obj) {
        this.arg$1.accept((ResourcePath) EncodedPath.decodeResourcePath(((Cursor) obj).getString(0)).popLast());
    }
}

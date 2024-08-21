package com.google.firebase.firestore.local;

import android.database.Cursor;
import com.google.firebase.firestore.util.Function;

/* compiled from: SQLitePersistence */
final /* synthetic */ class SQLitePersistence$$Lambda$2 implements Function {
    private static final SQLitePersistence$$Lambda$2 instance = new SQLitePersistence$$Lambda$2();

    private SQLitePersistence$$Lambda$2() {
    }

    public static Function lambdaFactory$() {
        return instance;
    }

    public Object apply(Object obj) {
        return Long.valueOf(((Cursor) obj).getLong(0));
    }
}

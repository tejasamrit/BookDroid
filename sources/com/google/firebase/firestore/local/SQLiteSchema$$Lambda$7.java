package com.google.firebase.firestore.local;

import android.database.Cursor;
import com.google.firebase.firestore.util.Function;

/* compiled from: SQLiteSchema */
final /* synthetic */ class SQLiteSchema$$Lambda$7 implements Function {
    private static final SQLiteSchema$$Lambda$7 instance = new SQLiteSchema$$Lambda$7();

    private SQLiteSchema$$Lambda$7() {
    }

    public static Function lambdaFactory$() {
        return instance;
    }

    public Object apply(Object obj) {
        return Long.valueOf(((Cursor) obj).getLong(0));
    }
}

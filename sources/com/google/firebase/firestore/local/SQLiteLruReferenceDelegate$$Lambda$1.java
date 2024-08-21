package com.google.firebase.firestore.local;

import android.database.Cursor;
import com.google.firebase.firestore.util.Function;

/* compiled from: SQLiteLruReferenceDelegate */
final /* synthetic */ class SQLiteLruReferenceDelegate$$Lambda$1 implements Function {
    private static final SQLiteLruReferenceDelegate$$Lambda$1 instance = new SQLiteLruReferenceDelegate$$Lambda$1();

    private SQLiteLruReferenceDelegate$$Lambda$1() {
    }

    public static Function lambdaFactory$() {
        return instance;
    }

    public Object apply(Object obj) {
        return Long.valueOf(((Cursor) obj).getLong(0));
    }
}

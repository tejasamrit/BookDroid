package com.google.firebase.firestore.local;

import android.database.Cursor;
import com.google.firebase.firestore.util.Consumer;
import java.util.List;

/* compiled from: SQLiteMutationQueue */
final /* synthetic */ class SQLiteMutationQueue$$Lambda$12 implements Consumer {
    private final List arg$1;

    private SQLiteMutationQueue$$Lambda$12(List list) {
        this.arg$1 = list;
    }

    public static Consumer lambdaFactory$(List list) {
        return new SQLiteMutationQueue$$Lambda$12(list);
    }

    public void accept(Object obj) {
        this.arg$1.add(EncodedPath.decodeResourcePath(((Cursor) obj).getString(0)));
    }
}

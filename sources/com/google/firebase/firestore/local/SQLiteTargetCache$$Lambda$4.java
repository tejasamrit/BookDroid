package com.google.firebase.firestore.local;

import android.database.Cursor;
import com.google.firebase.firestore.core.Target;
import com.google.firebase.firestore.local.SQLiteTargetCache;
import com.google.firebase.firestore.util.Consumer;

/* compiled from: SQLiteTargetCache */
final /* synthetic */ class SQLiteTargetCache$$Lambda$4 implements Consumer {
    private final SQLiteTargetCache arg$1;
    private final Target arg$2;
    private final SQLiteTargetCache.TargetDataHolder arg$3;

    private SQLiteTargetCache$$Lambda$4(SQLiteTargetCache sQLiteTargetCache, Target target, SQLiteTargetCache.TargetDataHolder targetDataHolder) {
        this.arg$1 = sQLiteTargetCache;
        this.arg$2 = target;
        this.arg$3 = targetDataHolder;
    }

    public static Consumer lambdaFactory$(SQLiteTargetCache sQLiteTargetCache, Target target, SQLiteTargetCache.TargetDataHolder targetDataHolder) {
        return new SQLiteTargetCache$$Lambda$4(sQLiteTargetCache, target, targetDataHolder);
    }

    public void accept(Object obj) {
        SQLiteTargetCache.lambda$getTargetData$3(this.arg$1, this.arg$2, this.arg$3, (Cursor) obj);
    }
}

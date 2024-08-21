package com.google.firebase.firestore.local;

import com.google.firebase.firestore.auth.User;
import com.google.firebase.firestore.core.IndexRange;
import com.google.firebase.firestore.model.DocumentKey;
import com.google.firebase.firestore.model.FieldPath;
import com.google.firebase.firestore.model.ResourcePath;
import com.google.firestore.p009v1.Value;

public class SQLiteCollectionIndex {

    /* renamed from: db */
    private final SQLitePersistence f333db;
    private final String uid;

    SQLiteCollectionIndex(SQLitePersistence sQLitePersistence, User user) {
        this.f333db = sQLitePersistence;
        this.uid = user.isAuthenticated() ? user.getUid() : "";
    }

    public void addEntry(FieldPath fieldPath, Value value, DocumentKey documentKey) {
        throw new RuntimeException("Not yet implemented.");
    }

    public void removeEntry(FieldPath fieldPath, Value value, DocumentKey documentKey) {
        throw new RuntimeException("Not yet implemented.");
    }

    public IndexCursor getCursor(ResourcePath resourcePath, IndexRange indexRange) {
        throw new RuntimeException("Not yet implemented.");
    }
}

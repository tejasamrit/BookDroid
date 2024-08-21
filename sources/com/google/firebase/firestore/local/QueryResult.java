package com.google.firebase.firestore.local;

import com.google.firebase.database.collection.ImmutableSortedMap;
import com.google.firebase.database.collection.ImmutableSortedSet;
import com.google.firebase.firestore.model.Document;
import com.google.firebase.firestore.model.DocumentKey;

public class QueryResult {
    private final ImmutableSortedMap<DocumentKey, Document> documents;
    private final ImmutableSortedSet<DocumentKey> remoteKeys;

    public QueryResult(ImmutableSortedMap<DocumentKey, Document> immutableSortedMap, ImmutableSortedSet<DocumentKey> immutableSortedSet) {
        this.documents = immutableSortedMap;
        this.remoteKeys = immutableSortedSet;
    }

    public ImmutableSortedMap<DocumentKey, Document> getDocuments() {
        return this.documents;
    }

    public ImmutableSortedSet<DocumentKey> getRemoteKeys() {
        return this.remoteKeys;
    }
}

package com.google.firebase.firestore.local;

import com.google.firebase.database.collection.ImmutableSortedMap;
import com.google.firebase.firestore.core.Query;
import com.google.firebase.firestore.model.Document;
import com.google.firebase.firestore.model.DocumentKey;
import com.google.firebase.firestore.model.MaybeDocument;
import com.google.firebase.firestore.model.SnapshotVersion;
import java.util.Map;

interface RemoteDocumentCache {
    void add(MaybeDocument maybeDocument, SnapshotVersion snapshotVersion);

    MaybeDocument get(DocumentKey documentKey);

    Map<DocumentKey, MaybeDocument> getAll(Iterable<DocumentKey> iterable);

    ImmutableSortedMap<DocumentKey, Document> getAllDocumentsMatchingQuery(Query query, SnapshotVersion snapshotVersion);

    void remove(DocumentKey documentKey);
}

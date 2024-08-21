package com.google.firebase.firestore;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.core.ViewSnapshot;
import com.google.firebase.firestore.model.Document;
import com.google.firebase.firestore.util.Preconditions;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class QuerySnapshot implements Iterable<QueryDocumentSnapshot> {
    private List<DocumentChange> cachedChanges;
    private MetadataChanges cachedChangesMetadataState;
    private final FirebaseFirestore firestore;
    private final SnapshotMetadata metadata;
    private final Query originalQuery;
    private final ViewSnapshot snapshot;

    QuerySnapshot(Query query, ViewSnapshot viewSnapshot, FirebaseFirestore firebaseFirestore) {
        this.originalQuery = (Query) Preconditions.checkNotNull(query);
        this.snapshot = (ViewSnapshot) Preconditions.checkNotNull(viewSnapshot);
        this.firestore = (FirebaseFirestore) Preconditions.checkNotNull(firebaseFirestore);
        this.metadata = new SnapshotMetadata(viewSnapshot.hasPendingWrites(), viewSnapshot.isFromCache());
    }

    private class QuerySnapshotIterator implements Iterator<QueryDocumentSnapshot> {

        /* renamed from: it */
        private final Iterator<Document> f325it;

        QuerySnapshotIterator(Iterator<Document> it) {
            this.f325it = it;
        }

        public boolean hasNext() {
            return this.f325it.hasNext();
        }

        public QueryDocumentSnapshot next() {
            return QuerySnapshot.this.convertDocument(this.f325it.next());
        }

        public void remove() {
            throw new UnsupportedOperationException("QuerySnapshot does not support remove().");
        }
    }

    public Query getQuery() {
        return this.originalQuery;
    }

    public SnapshotMetadata getMetadata() {
        return this.metadata;
    }

    public List<DocumentChange> getDocumentChanges() {
        return getDocumentChanges(MetadataChanges.EXCLUDE);
    }

    public List<DocumentChange> getDocumentChanges(MetadataChanges metadataChanges) {
        if (!MetadataChanges.INCLUDE.equals(metadataChanges) || !this.snapshot.excludesMetadataChanges()) {
            if (this.cachedChanges == null || this.cachedChangesMetadataState != metadataChanges) {
                this.cachedChanges = Collections.unmodifiableList(DocumentChange.changesFromSnapshot(this.firestore, metadataChanges, this.snapshot));
                this.cachedChangesMetadataState = metadataChanges;
            }
            return this.cachedChanges;
        }
        throw new IllegalArgumentException("To include metadata changes with your document changes, you must also pass MetadataChanges.INCLUDE to addSnapshotListener().");
    }

    public List<DocumentSnapshot> getDocuments() {
        ArrayList arrayList = new ArrayList(this.snapshot.getDocuments().size());
        Iterator<Document> it = this.snapshot.getDocuments().iterator();
        while (it.hasNext()) {
            arrayList.add(convertDocument(it.next()));
        }
        return arrayList;
    }

    public boolean isEmpty() {
        return this.snapshot.getDocuments().isEmpty();
    }

    public int size() {
        return this.snapshot.getDocuments().size();
    }

    public Iterator<QueryDocumentSnapshot> iterator() {
        return new QuerySnapshotIterator(this.snapshot.getDocuments().iterator());
    }

    public <T> List<T> toObjects(Class<T> cls) {
        return toObjects(cls, DocumentSnapshot.ServerTimestampBehavior.DEFAULT);
    }

    public <T> List<T> toObjects(Class<T> cls, DocumentSnapshot.ServerTimestampBehavior serverTimestampBehavior) {
        Preconditions.checkNotNull(cls, "Provided POJO type must not be null.");
        ArrayList arrayList = new ArrayList();
        Iterator<QueryDocumentSnapshot> it = iterator();
        while (it.hasNext()) {
            arrayList.add(it.next().toObject(cls, serverTimestampBehavior));
        }
        return arrayList;
    }

    /* access modifiers changed from: private */
    public QueryDocumentSnapshot convertDocument(Document document) {
        return QueryDocumentSnapshot.fromDocument(this.firestore, document, this.snapshot.isFromCache(), this.snapshot.getMutatedKeys().contains(document.getKey()));
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof QuerySnapshot)) {
            return false;
        }
        QuerySnapshot querySnapshot = (QuerySnapshot) obj;
        if (!this.firestore.equals(querySnapshot.firestore) || !this.originalQuery.equals(querySnapshot.originalQuery) || !this.snapshot.equals(querySnapshot.snapshot) || !this.metadata.equals(querySnapshot.metadata)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return (((((this.firestore.hashCode() * 31) + this.originalQuery.hashCode()) * 31) + this.snapshot.hashCode()) * 31) + this.metadata.hashCode();
    }
}

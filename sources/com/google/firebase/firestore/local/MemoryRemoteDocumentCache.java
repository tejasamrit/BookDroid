package com.google.firebase.firestore.local;

import android.util.Pair;
import com.google.firebase.database.collection.ImmutableSortedMap;
import com.google.firebase.firestore.core.Query;
import com.google.firebase.firestore.model.Document;
import com.google.firebase.firestore.model.DocumentCollections;
import com.google.firebase.firestore.model.DocumentKey;
import com.google.firebase.firestore.model.MaybeDocument;
import com.google.firebase.firestore.model.ResourcePath;
import com.google.firebase.firestore.model.SnapshotVersion;
import com.google.firebase.firestore.util.Assert;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

final class MemoryRemoteDocumentCache implements RemoteDocumentCache {
    /* access modifiers changed from: private */
    public ImmutableSortedMap<DocumentKey, Pair<MaybeDocument, SnapshotVersion>> docs = ImmutableSortedMap.Builder.emptyMap(DocumentKey.comparator());
    private final MemoryPersistence persistence;

    MemoryRemoteDocumentCache(MemoryPersistence memoryPersistence) {
        this.persistence = memoryPersistence;
    }

    public void add(MaybeDocument maybeDocument, SnapshotVersion snapshotVersion) {
        Assert.hardAssert(!snapshotVersion.equals(SnapshotVersion.NONE), "Cannot add document to the RemoteDocumentCache with a read time of zero", new Object[0]);
        this.docs = this.docs.insert(maybeDocument.getKey(), new Pair(maybeDocument, snapshotVersion));
        this.persistence.getIndexManager().addToCollectionParentIndex((ResourcePath) maybeDocument.getKey().getPath().popLast());
    }

    public void remove(DocumentKey documentKey) {
        this.docs = this.docs.remove(documentKey);
    }

    public MaybeDocument get(DocumentKey documentKey) {
        Pair pair = this.docs.get(documentKey);
        if (pair != null) {
            return (MaybeDocument) pair.first;
        }
        return null;
    }

    public Map<DocumentKey, MaybeDocument> getAll(Iterable<DocumentKey> iterable) {
        HashMap hashMap = new HashMap();
        for (DocumentKey next : iterable) {
            hashMap.put(next, get(next));
        }
        return hashMap;
    }

    public ImmutableSortedMap<DocumentKey, Document> getAllDocumentsMatchingQuery(Query query, SnapshotVersion snapshotVersion) {
        Assert.hardAssert(!query.isCollectionGroupQuery(), "CollectionGroup queries should be handled in LocalDocumentsView", new Object[0]);
        ImmutableSortedMap<DocumentKey, Document> emptyDocumentMap = DocumentCollections.emptyDocumentMap();
        ResourcePath path = query.getPath();
        Iterator<Map.Entry<DocumentKey, Pair<MaybeDocument, SnapshotVersion>>> iteratorFrom = this.docs.iteratorFrom(DocumentKey.fromPath((ResourcePath) path.append("")));
        while (iteratorFrom.hasNext()) {
            Map.Entry next = iteratorFrom.next();
            if (!path.isPrefixOf(((DocumentKey) next.getKey()).getPath())) {
                break;
            }
            MaybeDocument maybeDocument = (MaybeDocument) ((Pair) next.getValue()).first;
            if ((maybeDocument instanceof Document) && ((SnapshotVersion) ((Pair) next.getValue()).second).compareTo(snapshotVersion) > 0) {
                Document document = (Document) maybeDocument;
                if (query.matches(document)) {
                    emptyDocumentMap = emptyDocumentMap.insert(document.getKey(), document);
                }
            }
        }
        return emptyDocumentMap;
    }

    /* access modifiers changed from: package-private */
    public Iterable<MaybeDocument> getDocuments() {
        return new DocumentIterable();
    }

    /* access modifiers changed from: package-private */
    public long getByteSize(LocalSerializer localSerializer) {
        Iterator<MaybeDocument> it = new DocumentIterable().iterator();
        long j = 0;
        while (it.hasNext()) {
            j += (long) localSerializer.encodeMaybeDocument(it.next()).getSerializedSize();
        }
        return j;
    }

    private class DocumentIterable implements Iterable<MaybeDocument> {
        private DocumentIterable() {
        }

        public Iterator<MaybeDocument> iterator() {
            final Iterator it = MemoryRemoteDocumentCache.this.docs.iterator();
            return new Iterator<MaybeDocument>() {
                public boolean hasNext() {
                    return it.hasNext();
                }

                public MaybeDocument next() {
                    return (MaybeDocument) ((Pair) ((Map.Entry) it.next()).getValue()).first;
                }
            };
        }
    }
}

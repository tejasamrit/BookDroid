package com.google.firebase.firestore.local;

import com.google.firebase.database.collection.ImmutableSortedMap;
import com.google.firebase.firestore.core.Query;
import com.google.firebase.firestore.model.Document;
import com.google.firebase.firestore.model.DocumentCollections;
import com.google.firebase.firestore.model.DocumentKey;
import com.google.firebase.firestore.model.MaybeDocument;
import com.google.firebase.firestore.model.NoDocument;
import com.google.firebase.firestore.model.ResourcePath;
import com.google.firebase.firestore.model.SnapshotVersion;
import com.google.firebase.firestore.model.mutation.Mutation;
import com.google.firebase.firestore.model.mutation.MutationBatch;
import com.google.firebase.firestore.model.mutation.PatchMutation;
import com.google.firebase.firestore.util.Assert;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

class LocalDocumentsView {
    private final IndexManager indexManager;
    private final MutationQueue mutationQueue;
    private final RemoteDocumentCache remoteDocumentCache;

    LocalDocumentsView(RemoteDocumentCache remoteDocumentCache2, MutationQueue mutationQueue2, IndexManager indexManager2) {
        this.remoteDocumentCache = remoteDocumentCache2;
        this.mutationQueue = mutationQueue2;
        this.indexManager = indexManager2;
    }

    /* access modifiers changed from: package-private */
    public RemoteDocumentCache getRemoteDocumentCache() {
        return this.remoteDocumentCache;
    }

    /* access modifiers changed from: package-private */
    public MutationQueue getMutationQueue() {
        return this.mutationQueue;
    }

    /* access modifiers changed from: package-private */
    public IndexManager getIndexManager() {
        return this.indexManager;
    }

    /* access modifiers changed from: package-private */
    public MaybeDocument getDocument(DocumentKey documentKey) {
        return getDocument(documentKey, this.mutationQueue.getAllMutationBatchesAffectingDocumentKey(documentKey));
    }

    private MaybeDocument getDocument(DocumentKey documentKey, List<MutationBatch> list) {
        MaybeDocument maybeDocument = this.remoteDocumentCache.get(documentKey);
        for (MutationBatch applyToLocalView : list) {
            maybeDocument = applyToLocalView.applyToLocalView(documentKey, maybeDocument);
        }
        return maybeDocument;
    }

    private Map<DocumentKey, MaybeDocument> applyLocalMutationsToDocuments(Map<DocumentKey, MaybeDocument> map, List<MutationBatch> list) {
        for (Map.Entry next : map.entrySet()) {
            MaybeDocument maybeDocument = (MaybeDocument) next.getValue();
            for (MutationBatch applyToLocalView : list) {
                maybeDocument = applyToLocalView.applyToLocalView((DocumentKey) next.getKey(), maybeDocument);
            }
            next.setValue(maybeDocument);
        }
        return map;
    }

    /* access modifiers changed from: package-private */
    public ImmutableSortedMap<DocumentKey, MaybeDocument> getDocuments(Iterable<DocumentKey> iterable) {
        return getLocalViewOfDocuments(this.remoteDocumentCache.getAll(iterable));
    }

    /* access modifiers changed from: package-private */
    public ImmutableSortedMap<DocumentKey, MaybeDocument> getLocalViewOfDocuments(Map<DocumentKey, MaybeDocument> map) {
        ImmutableSortedMap<DocumentKey, MaybeDocument> emptyMaybeDocumentMap = DocumentCollections.emptyMaybeDocumentMap();
        for (Map.Entry next : applyLocalMutationsToDocuments(map, this.mutationQueue.getAllMutationBatchesAffectingDocumentKeys(map.keySet())).entrySet()) {
            DocumentKey documentKey = (DocumentKey) next.getKey();
            Object obj = (MaybeDocument) next.getValue();
            if (obj == null) {
                obj = new NoDocument(documentKey, SnapshotVersion.NONE, false);
            }
            emptyMaybeDocumentMap = emptyMaybeDocumentMap.insert(documentKey, obj);
        }
        return emptyMaybeDocumentMap;
    }

    /* access modifiers changed from: package-private */
    public ImmutableSortedMap<DocumentKey, Document> getDocumentsMatchingQuery(Query query, SnapshotVersion snapshotVersion) {
        ResourcePath path = query.getPath();
        if (query.isDocumentQuery()) {
            return getDocumentsMatchingDocumentQuery(path);
        }
        if (query.isCollectionGroupQuery()) {
            return getDocumentsMatchingCollectionGroupQuery(query, snapshotVersion);
        }
        return getDocumentsMatchingCollectionQuery(query, snapshotVersion);
    }

    private ImmutableSortedMap<DocumentKey, Document> getDocumentsMatchingDocumentQuery(ResourcePath resourcePath) {
        ImmutableSortedMap<DocumentKey, Document> emptyDocumentMap = DocumentCollections.emptyDocumentMap();
        MaybeDocument document = getDocument(DocumentKey.fromPath(resourcePath));
        return document instanceof Document ? emptyDocumentMap.insert(document.getKey(), (Document) document) : emptyDocumentMap;
    }

    private ImmutableSortedMap<DocumentKey, Document> getDocumentsMatchingCollectionGroupQuery(Query query, SnapshotVersion snapshotVersion) {
        Assert.hardAssert(query.getPath().isEmpty(), "Currently we only support collection group queries at the root.", new Object[0]);
        String collectionGroup = query.getCollectionGroup();
        ImmutableSortedMap<DocumentKey, Document> emptyDocumentMap = DocumentCollections.emptyDocumentMap();
        for (ResourcePath append : this.indexManager.getCollectionParents(collectionGroup)) {
            Iterator<Map.Entry<DocumentKey, Document>> it = getDocumentsMatchingCollectionQuery(query.asCollectionQueryAtPath((ResourcePath) append.append(collectionGroup)), snapshotVersion).iterator();
            while (it.hasNext()) {
                Map.Entry next = it.next();
                emptyDocumentMap = emptyDocumentMap.insert((DocumentKey) next.getKey(), (Document) next.getValue());
            }
        }
        return emptyDocumentMap;
    }

    private ImmutableSortedMap<DocumentKey, Document> getDocumentsMatchingCollectionQuery(Query query, SnapshotVersion snapshotVersion) {
        ImmutableSortedMap<DocumentKey, Document> allDocumentsMatchingQuery = this.remoteDocumentCache.getAllDocumentsMatchingQuery(query, snapshotVersion);
        List<MutationBatch> allMutationBatchesAffectingQuery = this.mutationQueue.getAllMutationBatchesAffectingQuery(query);
        ImmutableSortedMap<DocumentKey, Document> addMissingBaseDocuments = addMissingBaseDocuments(allMutationBatchesAffectingQuery, allDocumentsMatchingQuery);
        for (MutationBatch next : allMutationBatchesAffectingQuery) {
            for (Mutation next2 : next.getMutations()) {
                if (query.getPath().isImmediateParentOf(next2.getKey().getPath())) {
                    DocumentKey key = next2.getKey();
                    MaybeDocument maybeDocument = addMissingBaseDocuments.get(key);
                    MaybeDocument applyToLocalView = next2.applyToLocalView(maybeDocument, maybeDocument, next.getLocalWriteTime());
                    if (applyToLocalView instanceof Document) {
                        addMissingBaseDocuments = addMissingBaseDocuments.insert(key, (Document) applyToLocalView);
                    } else {
                        addMissingBaseDocuments = addMissingBaseDocuments.remove(key);
                    }
                }
            }
        }
        Iterator<Map.Entry<DocumentKey, Document>> it = addMissingBaseDocuments.iterator();
        while (it.hasNext()) {
            Map.Entry next3 = it.next();
            if (!query.matches((Document) next3.getValue())) {
                addMissingBaseDocuments = addMissingBaseDocuments.remove((DocumentKey) next3.getKey());
            }
        }
        return addMissingBaseDocuments;
    }

    private ImmutableSortedMap<DocumentKey, Document> addMissingBaseDocuments(List<MutationBatch> list, ImmutableSortedMap<DocumentKey, Document> immutableSortedMap) {
        HashSet hashSet = new HashSet();
        for (MutationBatch mutations : list) {
            for (Mutation next : mutations.getMutations()) {
                if ((next instanceof PatchMutation) && !immutableSortedMap.containsKey(next.getKey())) {
                    hashSet.add(next.getKey());
                }
            }
        }
        for (Map.Entry next2 : this.remoteDocumentCache.getAll(hashSet).entrySet()) {
            if (next2.getValue() != null && (next2.getValue() instanceof Document)) {
                immutableSortedMap = immutableSortedMap.insert((DocumentKey) next2.getKey(), (Document) next2.getValue());
            }
        }
        return immutableSortedMap;
    }
}

package com.google.firebase.firestore.local;

import com.google.firebase.database.collection.ImmutableSortedMap;
import com.google.firebase.database.collection.ImmutableSortedSet;
import com.google.firebase.firestore.core.Query;
import com.google.firebase.firestore.model.Document;
import com.google.firebase.firestore.model.DocumentKey;
import com.google.firebase.firestore.model.MaybeDocument;
import com.google.firebase.firestore.model.SnapshotVersion;
import com.google.firebase.firestore.util.Assert;
import com.google.firebase.firestore.util.Logger;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;

public class DefaultQueryEngine implements QueryEngine {
    private static final String LOG_TAG = "DefaultQueryEngine";
    private LocalDocumentsView localDocumentsView;

    public void handleDocumentChange(MaybeDocument maybeDocument, MaybeDocument maybeDocument2) {
    }

    public void setLocalDocumentsView(LocalDocumentsView localDocumentsView2) {
        this.localDocumentsView = localDocumentsView2;
    }

    public ImmutableSortedMap<DocumentKey, Document> getDocumentsMatchingQuery(Query query, SnapshotVersion snapshotVersion, ImmutableSortedSet<DocumentKey> immutableSortedSet) {
        Assert.hardAssert(this.localDocumentsView != null, "setLocalDocumentsView() not called", new Object[0]);
        if (query.matchesAllDocuments()) {
            return executeFullCollectionScan(query);
        }
        if (snapshotVersion.equals(SnapshotVersion.NONE)) {
            return executeFullCollectionScan(query);
        }
        ImmutableSortedSet<Document> applyQuery = applyQuery(query, this.localDocumentsView.getDocuments(immutableSortedSet));
        if ((query.hasLimitToFirst() || query.hasLimitToLast()) && needsRefill(query.getLimitType(), applyQuery, immutableSortedSet, snapshotVersion)) {
            return executeFullCollectionScan(query);
        }
        if (Logger.isDebugEnabled()) {
            Logger.debug(LOG_TAG, "Re-using previous result from %s to execute query: %s", snapshotVersion.toString(), query.toString());
        }
        ImmutableSortedMap<DocumentKey, Document> documentsMatchingQuery = this.localDocumentsView.getDocumentsMatchingQuery(query, snapshotVersion);
        Iterator<Document> it = applyQuery.iterator();
        while (it.hasNext()) {
            Document next = it.next();
            documentsMatchingQuery = documentsMatchingQuery.insert(next.getKey(), next);
        }
        return documentsMatchingQuery;
    }

    private ImmutableSortedSet<Document> applyQuery(Query query, ImmutableSortedMap<DocumentKey, MaybeDocument> immutableSortedMap) {
        ImmutableSortedSet<Document> immutableSortedSet = new ImmutableSortedSet<>(Collections.emptyList(), query.comparator());
        Iterator<Map.Entry<DocumentKey, MaybeDocument>> it = immutableSortedMap.iterator();
        while (it.hasNext()) {
            MaybeDocument maybeDocument = (MaybeDocument) it.next().getValue();
            if (maybeDocument instanceof Document) {
                Document document = (Document) maybeDocument;
                if (query.matches(document)) {
                    immutableSortedSet = immutableSortedSet.insert(document);
                }
            }
        }
        return immutableSortedSet;
    }

    private boolean needsRefill(Query.LimitType limitType, ImmutableSortedSet<Document> immutableSortedSet, ImmutableSortedSet<DocumentKey> immutableSortedSet2, SnapshotVersion snapshotVersion) {
        Document document;
        if (immutableSortedSet2.size() != immutableSortedSet.size()) {
            return true;
        }
        if (limitType == Query.LimitType.LIMIT_TO_FIRST) {
            document = immutableSortedSet.getMaxEntry();
        } else {
            document = immutableSortedSet.getMinEntry();
        }
        if (document == null) {
            return false;
        }
        if (document.hasPendingWrites() || document.getVersion().compareTo(snapshotVersion) > 0) {
            return true;
        }
        return false;
    }

    private ImmutableSortedMap<DocumentKey, Document> executeFullCollectionScan(Query query) {
        if (Logger.isDebugEnabled()) {
            Logger.debug(LOG_TAG, "Using full collection scan to execute query: %s", query.toString());
        }
        return this.localDocumentsView.getDocumentsMatchingQuery(query, SnapshotVersion.NONE);
    }
}

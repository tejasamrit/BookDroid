package com.google.firebase.firestore.local;

import android.database.Cursor;
import com.google.firebase.Timestamp;
import com.google.firebase.database.collection.ImmutableSortedMap;
import com.google.firebase.firestore.core.Query;
import com.google.firebase.firestore.local.SQLitePersistence;
import com.google.firebase.firestore.model.Document;
import com.google.firebase.firestore.model.DocumentCollections;
import com.google.firebase.firestore.model.DocumentKey;
import com.google.firebase.firestore.model.MaybeDocument;
import com.google.firebase.firestore.model.ResourcePath;
import com.google.firebase.firestore.model.SnapshotVersion;
import com.google.firebase.firestore.util.Assert;
import com.google.firebase.firestore.util.BackgroundQueue;
import com.google.firebase.firestore.util.Executors;
import com.google.protobuf.InvalidProtocolBufferException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executor;

final class SQLiteRemoteDocumentCache implements RemoteDocumentCache {

    /* renamed from: db */
    private final SQLitePersistence f339db;
    private final LocalSerializer serializer;

    SQLiteRemoteDocumentCache(SQLitePersistence sQLitePersistence, LocalSerializer localSerializer) {
        this.f339db = sQLitePersistence;
        this.serializer = localSerializer;
    }

    public void add(MaybeDocument maybeDocument, SnapshotVersion snapshotVersion) {
        Assert.hardAssert(!snapshotVersion.equals(SnapshotVersion.NONE), "Cannot add document to the RemoteDocumentCache with a read time of zero", new Object[0]);
        String pathForKey = pathForKey(maybeDocument.getKey());
        Timestamp timestamp = snapshotVersion.getTimestamp();
        com.google.firebase.firestore.proto.MaybeDocument encodeMaybeDocument = this.serializer.encodeMaybeDocument(maybeDocument);
        this.f339db.execute("INSERT OR REPLACE INTO remote_documents (path, read_time_seconds, read_time_nanos, contents) VALUES (?, ?, ?, ?)", pathForKey, Long.valueOf(timestamp.getSeconds()), Integer.valueOf(timestamp.getNanoseconds()), encodeMaybeDocument.toByteArray());
        this.f339db.getIndexManager().addToCollectionParentIndex((ResourcePath) maybeDocument.getKey().getPath().popLast());
    }

    public void remove(DocumentKey documentKey) {
        String pathForKey = pathForKey(documentKey);
        this.f339db.execute("DELETE FROM remote_documents WHERE path = ?", pathForKey);
    }

    public MaybeDocument get(DocumentKey documentKey) {
        String pathForKey = pathForKey(documentKey);
        return (MaybeDocument) this.f339db.query("SELECT contents FROM remote_documents WHERE path = ?").binding(pathForKey).firstValue(SQLiteRemoteDocumentCache$$Lambda$1.lambdaFactory$(this));
    }

    public Map<DocumentKey, MaybeDocument> getAll(Iterable<DocumentKey> iterable) {
        ArrayList arrayList = new ArrayList();
        for (DocumentKey path : iterable) {
            arrayList.add(EncodedPath.encode(path.getPath()));
        }
        HashMap hashMap = new HashMap();
        for (DocumentKey put : iterable) {
            hashMap.put(put, (Object) null);
        }
        SQLitePersistence.LongQuery longQuery = new SQLitePersistence.LongQuery(this.f339db, "SELECT contents FROM remote_documents WHERE path IN (", arrayList, ") ORDER BY path");
        while (longQuery.hasMoreSubqueries()) {
            longQuery.performNextSubquery().forEach(SQLiteRemoteDocumentCache$$Lambda$2.lambdaFactory$(this, hashMap));
        }
        return hashMap;
    }

    static /* synthetic */ void lambda$getAll$1(SQLiteRemoteDocumentCache sQLiteRemoteDocumentCache, Map map, Cursor cursor) {
        MaybeDocument decodeMaybeDocument = sQLiteRemoteDocumentCache.decodeMaybeDocument(cursor.getBlob(0));
        map.put(decodeMaybeDocument.getKey(), decodeMaybeDocument);
    }

    public ImmutableSortedMap<DocumentKey, Document> getAllDocumentsMatchingQuery(Query query, SnapshotVersion snapshotVersion) {
        SQLitePersistence.Query query2;
        Assert.hardAssert(!query.isCollectionGroupQuery(), "CollectionGroup queries should be handled in LocalDocumentsView", new Object[0]);
        ResourcePath path = query.getPath();
        int length = path.length() + 1;
        String encode = EncodedPath.encode(path);
        String prefixSuccessor = EncodedPath.prefixSuccessor(encode);
        Timestamp timestamp = snapshotVersion.getTimestamp();
        BackgroundQueue backgroundQueue = new BackgroundQueue();
        ImmutableSortedMap<DocumentKey, Document>[] immutableSortedMapArr = {DocumentCollections.emptyDocumentMap()};
        if (snapshotVersion.equals(SnapshotVersion.NONE)) {
            query2 = this.f339db.query("SELECT path, contents FROM remote_documents WHERE path >= ? AND path < ?").binding(encode, prefixSuccessor);
        } else {
            query2 = this.f339db.query("SELECT path, contents FROM remote_documents WHERE path >= ? AND path < ?AND (read_time_seconds > ? OR (read_time_seconds = ? AND read_time_nanos > ?))").binding(encode, prefixSuccessor, Long.valueOf(timestamp.getSeconds()), Long.valueOf(timestamp.getSeconds()), Integer.valueOf(timestamp.getNanoseconds()));
        }
        query2.forEach(SQLiteRemoteDocumentCache$$Lambda$3.lambdaFactory$(this, length, backgroundQueue, query, immutableSortedMapArr));
        try {
            backgroundQueue.drain();
        } catch (InterruptedException e) {
            Assert.fail("Interrupted while deserializing documents", e);
        }
        return immutableSortedMapArr[0];
    }

    static /* synthetic */ void lambda$getAllDocumentsMatchingQuery$3(SQLiteRemoteDocumentCache sQLiteRemoteDocumentCache, int i, BackgroundQueue backgroundQueue, Query query, ImmutableSortedMap[] immutableSortedMapArr, Cursor cursor) {
        if (EncodedPath.decodeResourcePath(cursor.getString(0)).length() == i) {
            byte[] blob = cursor.getBlob(1);
            Executor executor = backgroundQueue;
            if (cursor.isLast()) {
                executor = Executors.DIRECT_EXECUTOR;
            }
            executor.execute(SQLiteRemoteDocumentCache$$Lambda$4.lambdaFactory$(sQLiteRemoteDocumentCache, blob, query, immutableSortedMapArr));
        }
    }

    static /* synthetic */ void lambda$getAllDocumentsMatchingQuery$2(SQLiteRemoteDocumentCache sQLiteRemoteDocumentCache, byte[] bArr, Query query, ImmutableSortedMap[] immutableSortedMapArr) {
        MaybeDocument decodeMaybeDocument = sQLiteRemoteDocumentCache.decodeMaybeDocument(bArr);
        if ((decodeMaybeDocument instanceof Document) && query.matches((Document) decodeMaybeDocument)) {
            synchronized (sQLiteRemoteDocumentCache) {
                immutableSortedMapArr[0] = immutableSortedMapArr[0].insert(decodeMaybeDocument.getKey(), (Document) decodeMaybeDocument);
            }
        }
    }

    private String pathForKey(DocumentKey documentKey) {
        return EncodedPath.encode(documentKey.getPath());
    }

    /* access modifiers changed from: private */
    public MaybeDocument decodeMaybeDocument(byte[] bArr) {
        try {
            return this.serializer.decodeMaybeDocument(com.google.firebase.firestore.proto.MaybeDocument.parseFrom(bArr));
        } catch (InvalidProtocolBufferException e) {
            throw Assert.fail("MaybeDocument failed to parse: %s", e);
        }
    }
}

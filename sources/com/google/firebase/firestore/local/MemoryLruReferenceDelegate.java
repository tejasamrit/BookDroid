package com.google.firebase.firestore.local;

import android.util.SparseArray;
import com.google.firebase.firestore.core.ListenSequence;
import com.google.firebase.firestore.local.LruGarbageCollector;
import com.google.firebase.firestore.model.DocumentKey;
import com.google.firebase.firestore.model.MaybeDocument;
import com.google.firebase.firestore.util.Assert;
import com.google.firebase.firestore.util.Consumer;
import java.util.HashMap;
import java.util.Map;

class MemoryLruReferenceDelegate implements ReferenceDelegate, LruDelegate {
    private long currentSequenceNumber;
    private final LruGarbageCollector garbageCollector;
    private ReferenceSet inMemoryPins;
    private final ListenSequence listenSequence;
    private final Map<DocumentKey, Long> orphanedSequenceNumbers = new HashMap();
    private final MemoryPersistence persistence;
    private final LocalSerializer serializer;

    MemoryLruReferenceDelegate(MemoryPersistence memoryPersistence, LruGarbageCollector.Params params, LocalSerializer localSerializer) {
        this.persistence = memoryPersistence;
        this.serializer = localSerializer;
        this.listenSequence = new ListenSequence(memoryPersistence.getTargetCache().getHighestListenSequenceNumber());
        this.currentSequenceNumber = -1;
        this.garbageCollector = new LruGarbageCollector(this, params);
    }

    public LruGarbageCollector getGarbageCollector() {
        return this.garbageCollector;
    }

    public void onTransactionStarted() {
        Assert.hardAssert(this.currentSequenceNumber == -1, "Starting a transaction without committing the previous one", new Object[0]);
        this.currentSequenceNumber = this.listenSequence.next();
    }

    public void onTransactionCommitted() {
        Assert.hardAssert(this.currentSequenceNumber != -1, "Committing a transaction without having started one", new Object[0]);
        this.currentSequenceNumber = -1;
    }

    public long getCurrentSequenceNumber() {
        Assert.hardAssert(this.currentSequenceNumber != -1, "Attempting to get a sequence number outside of a transaction", new Object[0]);
        return this.currentSequenceNumber;
    }

    public void forEachTarget(Consumer<TargetData> consumer) {
        this.persistence.getTargetCache().forEachTarget(consumer);
    }

    public long getSequenceNumberCount() {
        long targetCount = this.persistence.getTargetCache().getTargetCount();
        long[] jArr = new long[1];
        forEachOrphanedDocumentSequenceNumber(MemoryLruReferenceDelegate$$Lambda$1.lambdaFactory$(jArr));
        return targetCount + jArr[0];
    }

    static /* synthetic */ void lambda$getSequenceNumberCount$0(long[] jArr, Long l) {
        jArr[0] = jArr[0] + 1;
    }

    public void forEachOrphanedDocumentSequenceNumber(Consumer<Long> consumer) {
        for (Map.Entry next : this.orphanedSequenceNumbers.entrySet()) {
            if (!isPinned((DocumentKey) next.getKey(), ((Long) next.getValue()).longValue())) {
                consumer.accept((Long) next.getValue());
            }
        }
    }

    public void setInMemoryPins(ReferenceSet referenceSet) {
        this.inMemoryPins = referenceSet;
    }

    public int removeTargets(long j, SparseArray<?> sparseArray) {
        return this.persistence.getTargetCache().removeQueries(j, sparseArray);
    }

    public int removeOrphanedDocuments(long j) {
        MemoryRemoteDocumentCache remoteDocumentCache = this.persistence.getRemoteDocumentCache();
        int i = 0;
        for (MaybeDocument key : remoteDocumentCache.getDocuments()) {
            DocumentKey key2 = key.getKey();
            if (!isPinned(key2, j)) {
                remoteDocumentCache.remove(key2);
                this.orphanedSequenceNumbers.remove(key2);
                i++;
            }
        }
        return i;
    }

    public void removeMutationReference(DocumentKey documentKey) {
        this.orphanedSequenceNumbers.put(documentKey, Long.valueOf(getCurrentSequenceNumber()));
    }

    public void removeTarget(TargetData targetData) {
        this.persistence.getTargetCache().updateTargetData(targetData.withSequenceNumber(getCurrentSequenceNumber()));
    }

    public void addReference(DocumentKey documentKey) {
        this.orphanedSequenceNumbers.put(documentKey, Long.valueOf(getCurrentSequenceNumber()));
    }

    public void removeReference(DocumentKey documentKey) {
        this.orphanedSequenceNumbers.put(documentKey, Long.valueOf(getCurrentSequenceNumber()));
    }

    public void updateLimboDocument(DocumentKey documentKey) {
        this.orphanedSequenceNumbers.put(documentKey, Long.valueOf(getCurrentSequenceNumber()));
    }

    private boolean mutationQueuesContainsKey(DocumentKey documentKey) {
        for (MemoryMutationQueue containsKey : this.persistence.getMutationQueues()) {
            if (containsKey.containsKey(documentKey)) {
                return true;
            }
        }
        return false;
    }

    private boolean isPinned(DocumentKey documentKey, long j) {
        if (mutationQueuesContainsKey(documentKey) || this.inMemoryPins.containsKey(documentKey) || this.persistence.getTargetCache().containsKey(documentKey)) {
            return true;
        }
        Long l = this.orphanedSequenceNumbers.get(documentKey);
        if (l == null || l.longValue() <= j) {
            return false;
        }
        return true;
    }

    public long getByteSize() {
        long byteSize = this.persistence.getTargetCache().getByteSize(this.serializer) + 0 + this.persistence.getRemoteDocumentCache().getByteSize(this.serializer);
        for (MemoryMutationQueue byteSize2 : this.persistence.getMutationQueues()) {
            byteSize += byteSize2.getByteSize(this.serializer);
        }
        return byteSize;
    }
}

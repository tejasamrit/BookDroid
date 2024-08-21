package com.google.firebase.firestore.local;

import android.util.SparseArray;
import com.google.firebase.database.collection.ImmutableSortedSet;
import com.google.firebase.firestore.core.Target;
import com.google.firebase.firestore.model.DocumentKey;
import com.google.firebase.firestore.model.SnapshotVersion;
import com.google.firebase.firestore.util.Consumer;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

final class MemoryTargetCache implements TargetCache {
    private long highestSequenceNumber = 0;
    private int highestTargetId;
    private SnapshotVersion lastRemoteSnapshotVersion = SnapshotVersion.NONE;
    private final MemoryPersistence persistence;
    private final ReferenceSet references = new ReferenceSet();
    private final Map<Target, TargetData> targets = new HashMap();

    MemoryTargetCache(MemoryPersistence memoryPersistence) {
        this.persistence = memoryPersistence;
    }

    public int getHighestTargetId() {
        return this.highestTargetId;
    }

    public long getTargetCount() {
        return (long) this.targets.size();
    }

    public void forEachTarget(Consumer<TargetData> consumer) {
        for (TargetData accept : this.targets.values()) {
            consumer.accept(accept);
        }
    }

    public long getHighestListenSequenceNumber() {
        return this.highestSequenceNumber;
    }

    public SnapshotVersion getLastRemoteSnapshotVersion() {
        return this.lastRemoteSnapshotVersion;
    }

    public void setLastRemoteSnapshotVersion(SnapshotVersion snapshotVersion) {
        this.lastRemoteSnapshotVersion = snapshotVersion;
    }

    public void addTargetData(TargetData targetData) {
        this.targets.put(targetData.getTarget(), targetData);
        int targetId = targetData.getTargetId();
        if (targetId > this.highestTargetId) {
            this.highestTargetId = targetId;
        }
        if (targetData.getSequenceNumber() > this.highestSequenceNumber) {
            this.highestSequenceNumber = targetData.getSequenceNumber();
        }
    }

    public void updateTargetData(TargetData targetData) {
        addTargetData(targetData);
    }

    public void removeTargetData(TargetData targetData) {
        this.targets.remove(targetData.getTarget());
        this.references.removeReferencesForId(targetData.getTargetId());
    }

    /* access modifiers changed from: package-private */
    public int removeQueries(long j, SparseArray<?> sparseArray) {
        Iterator<Map.Entry<Target, TargetData>> it = this.targets.entrySet().iterator();
        int i = 0;
        while (it.hasNext()) {
            Map.Entry next = it.next();
            int targetId = ((TargetData) next.getValue()).getTargetId();
            if (((TargetData) next.getValue()).getSequenceNumber() <= j && sparseArray.get(targetId) == null) {
                it.remove();
                removeMatchingKeysForTargetId(targetId);
                i++;
            }
        }
        return i;
    }

    public TargetData getTargetData(Target target) {
        return this.targets.get(target);
    }

    public void addMatchingKeys(ImmutableSortedSet<DocumentKey> immutableSortedSet, int i) {
        this.references.addReferences(immutableSortedSet, i);
        ReferenceDelegate referenceDelegate = this.persistence.getReferenceDelegate();
        Iterator<DocumentKey> it = immutableSortedSet.iterator();
        while (it.hasNext()) {
            referenceDelegate.addReference(it.next());
        }
    }

    public void removeMatchingKeys(ImmutableSortedSet<DocumentKey> immutableSortedSet, int i) {
        this.references.removeReferences(immutableSortedSet, i);
        ReferenceDelegate referenceDelegate = this.persistence.getReferenceDelegate();
        Iterator<DocumentKey> it = immutableSortedSet.iterator();
        while (it.hasNext()) {
            referenceDelegate.removeReference(it.next());
        }
    }

    private void removeMatchingKeysForTargetId(int i) {
        this.references.removeReferencesForId(i);
    }

    public ImmutableSortedSet<DocumentKey> getMatchingKeysForTargetId(int i) {
        return this.references.referencesForId(i);
    }

    public boolean containsKey(DocumentKey documentKey) {
        return this.references.containsKey(documentKey);
    }

    /* access modifiers changed from: package-private */
    public long getByteSize(LocalSerializer localSerializer) {
        long j = 0;
        for (Map.Entry<Target, TargetData> value : this.targets.entrySet()) {
            j += (long) localSerializer.encodeTargetData((TargetData) value.getValue()).getSerializedSize();
        }
        return j;
    }
}

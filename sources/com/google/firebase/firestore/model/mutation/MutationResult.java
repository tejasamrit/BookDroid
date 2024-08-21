package com.google.firebase.firestore.model.mutation;

import com.google.firebase.firestore.model.SnapshotVersion;
import com.google.firebase.firestore.util.Preconditions;
import com.google.firestore.p009v1.Value;
import java.util.List;

public final class MutationResult {
    private final List<Value> transformResults;
    private final SnapshotVersion version;

    public MutationResult(SnapshotVersion snapshotVersion, List<Value> list) {
        this.version = (SnapshotVersion) Preconditions.checkNotNull(snapshotVersion);
        this.transformResults = list;
    }

    public SnapshotVersion getVersion() {
        return this.version;
    }

    public List<Value> getTransformResults() {
        return this.transformResults;
    }
}

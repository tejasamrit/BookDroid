package com.google.firebase.firestore.model;

public abstract class MaybeDocument {
    private final DocumentKey key;
    private final SnapshotVersion version;

    public abstract boolean hasPendingWrites();

    MaybeDocument(DocumentKey documentKey, SnapshotVersion snapshotVersion) {
        this.key = documentKey;
        this.version = snapshotVersion;
    }

    public DocumentKey getKey() {
        return this.key;
    }

    public SnapshotVersion getVersion() {
        return this.version;
    }
}

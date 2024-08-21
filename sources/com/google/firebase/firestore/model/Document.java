package com.google.firebase.firestore.model;

import com.google.firestore.p009v1.Value;
import java.util.Comparator;

public final class Document extends MaybeDocument {
    private static final Comparator<Document> KEY_COMPARATOR = Document$$Lambda$1.lambdaFactory$();
    private final DocumentState documentState;
    private ObjectValue objectValue;

    public enum DocumentState {
        LOCAL_MUTATIONS,
        COMMITTED_MUTATIONS,
        SYNCED
    }

    public static Comparator<Document> keyComparator() {
        return KEY_COMPARATOR;
    }

    public Document(DocumentKey documentKey, SnapshotVersion snapshotVersion, ObjectValue objectValue2, DocumentState documentState2) {
        super(documentKey, snapshotVersion);
        this.documentState = documentState2;
        this.objectValue = objectValue2;
    }

    public ObjectValue getData() {
        return this.objectValue;
    }

    public Value getField(FieldPath fieldPath) {
        return this.objectValue.get(fieldPath);
    }

    public boolean hasLocalMutations() {
        return this.documentState.equals(DocumentState.LOCAL_MUTATIONS);
    }

    public boolean hasCommittedMutations() {
        return this.documentState.equals(DocumentState.COMMITTED_MUTATIONS);
    }

    public boolean hasPendingWrites() {
        return hasLocalMutations() || hasCommittedMutations();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Document)) {
            return false;
        }
        Document document = (Document) obj;
        if (!getVersion().equals(document.getVersion()) || !getKey().equals(document.getKey()) || !this.documentState.equals(document.documentState) || !this.objectValue.equals(document.objectValue)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return (((((getKey().hashCode() * 31) + getVersion().hashCode()) * 31) + this.documentState.hashCode()) * 31) + this.objectValue.hashCode();
    }

    public String toString() {
        return "Document{key=" + getKey() + ", data=" + getData() + ", version=" + getVersion() + ", documentState=" + this.documentState.name() + '}';
    }
}

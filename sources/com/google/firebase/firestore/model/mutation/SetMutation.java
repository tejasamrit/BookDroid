package com.google.firebase.firestore.model.mutation;

import com.google.firebase.Timestamp;
import com.google.firebase.firestore.model.Document;
import com.google.firebase.firestore.model.DocumentKey;
import com.google.firebase.firestore.model.MaybeDocument;
import com.google.firebase.firestore.model.ObjectValue;
import com.google.firebase.firestore.util.Assert;

public final class SetMutation extends Mutation {
    private final ObjectValue value;

    public ObjectValue extractBaseValue(MaybeDocument maybeDocument) {
        return null;
    }

    public SetMutation(DocumentKey documentKey, ObjectValue objectValue, Precondition precondition) {
        super(documentKey, precondition);
        this.value = objectValue;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        SetMutation setMutation = (SetMutation) obj;
        if (!hasSameKeyAndPrecondition(setMutation) || !this.value.equals(setMutation.value)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return (keyAndPreconditionHashCode() * 31) + this.value.hashCode();
    }

    public String toString() {
        return "SetMutation{" + keyAndPreconditionToString() + ", value=" + this.value + "}";
    }

    public MaybeDocument applyToRemoteDocument(MaybeDocument maybeDocument, MutationResult mutationResult) {
        verifyKeyMatches(maybeDocument);
        Assert.hardAssert(mutationResult.getTransformResults() == null, "Transform results received by SetMutation.", new Object[0]);
        return new Document(getKey(), mutationResult.getVersion(), this.value, Document.DocumentState.COMMITTED_MUTATIONS);
    }

    public MaybeDocument applyToLocalView(MaybeDocument maybeDocument, MaybeDocument maybeDocument2, Timestamp timestamp) {
        verifyKeyMatches(maybeDocument);
        if (!getPrecondition().isValidFor(maybeDocument)) {
            return maybeDocument;
        }
        return new Document(getKey(), getPostMutationVersion(maybeDocument), this.value, Document.DocumentState.LOCAL_MUTATIONS);
    }

    public ObjectValue getValue() {
        return this.value;
    }
}

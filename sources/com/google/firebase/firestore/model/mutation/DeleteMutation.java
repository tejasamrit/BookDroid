package com.google.firebase.firestore.model.mutation;

import com.google.firebase.Timestamp;
import com.google.firebase.firestore.model.DocumentKey;
import com.google.firebase.firestore.model.MaybeDocument;
import com.google.firebase.firestore.model.NoDocument;
import com.google.firebase.firestore.model.ObjectValue;
import com.google.firebase.firestore.model.SnapshotVersion;
import com.google.firebase.firestore.util.Assert;

public final class DeleteMutation extends Mutation {
    public ObjectValue extractBaseValue(MaybeDocument maybeDocument) {
        return null;
    }

    public DeleteMutation(DocumentKey documentKey, Precondition precondition) {
        super(documentKey, precondition);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return hasSameKeyAndPrecondition((DeleteMutation) obj);
    }

    public int hashCode() {
        return keyAndPreconditionHashCode();
    }

    public String toString() {
        return "DeleteMutation{" + keyAndPreconditionToString() + "}";
    }

    public MaybeDocument applyToRemoteDocument(MaybeDocument maybeDocument, MutationResult mutationResult) {
        verifyKeyMatches(maybeDocument);
        Assert.hardAssert(mutationResult.getTransformResults() == null, "Transform results received by DeleteMutation.", new Object[0]);
        return new NoDocument(getKey(), mutationResult.getVersion(), true);
    }

    public MaybeDocument applyToLocalView(MaybeDocument maybeDocument, MaybeDocument maybeDocument2, Timestamp timestamp) {
        verifyKeyMatches(maybeDocument);
        if (!getPrecondition().isValidFor(maybeDocument)) {
            return maybeDocument;
        }
        return new NoDocument(getKey(), SnapshotVersion.NONE, false);
    }
}

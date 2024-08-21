package com.google.firebase.firestore.model.mutation;

import com.google.firebase.Timestamp;
import com.google.firebase.firestore.model.DocumentKey;
import com.google.firebase.firestore.model.MaybeDocument;
import com.google.firebase.firestore.model.ObjectValue;
import com.google.firebase.firestore.util.Assert;

public final class VerifyMutation extends Mutation {
    public VerifyMutation(DocumentKey documentKey, Precondition precondition) {
        super(documentKey, precondition);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return hasSameKeyAndPrecondition((VerifyMutation) obj);
    }

    public int hashCode() {
        return keyAndPreconditionHashCode();
    }

    public String toString() {
        return "VerifyMutation{" + keyAndPreconditionToString() + "}";
    }

    public MaybeDocument applyToRemoteDocument(MaybeDocument maybeDocument, MutationResult mutationResult) {
        throw Assert.fail("VerifyMutation should only be used in Transactions.", new Object[0]);
    }

    public MaybeDocument applyToLocalView(MaybeDocument maybeDocument, MaybeDocument maybeDocument2, Timestamp timestamp) {
        throw Assert.fail("VerifyMutation should only be used in Transactions.", new Object[0]);
    }

    public ObjectValue extractBaseValue(MaybeDocument maybeDocument) {
        throw Assert.fail("VerifyMutation should only be used in Transactions.", new Object[0]);
    }
}

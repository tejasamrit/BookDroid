package com.google.firebase.firestore.model.mutation;

import com.google.firebase.Timestamp;
import com.google.firebase.firestore.model.Document;
import com.google.firebase.firestore.model.DocumentKey;
import com.google.firebase.firestore.model.MaybeDocument;
import com.google.firebase.firestore.model.ObjectValue;
import com.google.firebase.firestore.model.UnknownDocument;
import com.google.firebase.firestore.util.Assert;
import com.google.firestore.p009v1.Value;
import java.util.ArrayList;
import java.util.List;

public final class TransformMutation extends Mutation {
    private final List<FieldTransform> fieldTransforms;

    public TransformMutation(DocumentKey documentKey, List<FieldTransform> list) {
        super(documentKey, Precondition.exists(true));
        this.fieldTransforms = list;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        TransformMutation transformMutation = (TransformMutation) obj;
        if (!hasSameKeyAndPrecondition(transformMutation) || !this.fieldTransforms.equals(transformMutation.fieldTransforms)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return (keyAndPreconditionHashCode() * 31) + this.fieldTransforms.hashCode();
    }

    public String toString() {
        return "TransformMutation{" + keyAndPreconditionToString() + ", fieldTransforms=" + this.fieldTransforms + "}";
    }

    public List<FieldTransform> getFieldTransforms() {
        return this.fieldTransforms;
    }

    public MaybeDocument applyToRemoteDocument(MaybeDocument maybeDocument, MutationResult mutationResult) {
        verifyKeyMatches(maybeDocument);
        Assert.hardAssert(mutationResult.getTransformResults() != null, "Transform results missing for TransformMutation.", new Object[0]);
        if (!getPrecondition().isValidFor(maybeDocument)) {
            return new UnknownDocument(getKey(), mutationResult.getVersion());
        }
        Document requireDocument = requireDocument(maybeDocument);
        return new Document(getKey(), mutationResult.getVersion(), transformObject(requireDocument.getData(), serverTransformResults(requireDocument, mutationResult.getTransformResults())), Document.DocumentState.COMMITTED_MUTATIONS);
    }

    public MaybeDocument applyToLocalView(MaybeDocument maybeDocument, MaybeDocument maybeDocument2, Timestamp timestamp) {
        verifyKeyMatches(maybeDocument);
        if (!getPrecondition().isValidFor(maybeDocument)) {
            return maybeDocument;
        }
        Document requireDocument = requireDocument(maybeDocument);
        return new Document(getKey(), requireDocument.getVersion(), transformObject(requireDocument.getData(), localTransformResults(timestamp, maybeDocument, maybeDocument2)), Document.DocumentState.LOCAL_MUTATIONS);
    }

    public ObjectValue extractBaseValue(MaybeDocument maybeDocument) {
        ObjectValue.Builder builder = null;
        for (FieldTransform next : this.fieldTransforms) {
            Value computeBaseValue = next.getOperation().computeBaseValue(maybeDocument instanceof Document ? ((Document) maybeDocument).getField(next.getFieldPath()) : null);
            if (computeBaseValue != null) {
                if (builder == null) {
                    builder = ObjectValue.newBuilder();
                }
                builder.set(next.getFieldPath(), computeBaseValue);
            }
        }
        if (builder != null) {
            return builder.build();
        }
        return null;
    }

    private Document requireDocument(MaybeDocument maybeDocument) {
        Assert.hardAssert(maybeDocument instanceof Document, "Unknown MaybeDocument type %s", maybeDocument);
        Document document = (Document) maybeDocument;
        Assert.hardAssert(document.getKey().equals(getKey()), "Can only transform a document with the same key", new Object[0]);
        return document;
    }

    private List<Value> serverTransformResults(MaybeDocument maybeDocument, List<Value> list) {
        ArrayList arrayList = new ArrayList(this.fieldTransforms.size());
        Assert.hardAssert(this.fieldTransforms.size() == list.size(), "server transform count (%d) should match field transform count (%d)", Integer.valueOf(list.size()), Integer.valueOf(this.fieldTransforms.size()));
        for (int i = 0; i < list.size(); i++) {
            FieldTransform fieldTransform = this.fieldTransforms.get(i);
            TransformOperation operation = fieldTransform.getOperation();
            Value value = null;
            if (maybeDocument instanceof Document) {
                value = ((Document) maybeDocument).getField(fieldTransform.getFieldPath());
            }
            arrayList.add(operation.applyToRemoteDocument(value, list.get(i)));
        }
        return arrayList;
    }

    private List<Value> localTransformResults(Timestamp timestamp, MaybeDocument maybeDocument, MaybeDocument maybeDocument2) {
        ArrayList arrayList = new ArrayList(this.fieldTransforms.size());
        for (FieldTransform next : this.fieldTransforms) {
            TransformOperation operation = next.getOperation();
            Value value = null;
            if (maybeDocument instanceof Document) {
                value = ((Document) maybeDocument).getField(next.getFieldPath());
            }
            if (value == null && (maybeDocument2 instanceof Document)) {
                value = ((Document) maybeDocument2).getField(next.getFieldPath());
            }
            arrayList.add(operation.applyToLocalView(value, timestamp));
        }
        return arrayList;
    }

    private ObjectValue transformObject(ObjectValue objectValue, List<Value> list) {
        Assert.hardAssert(list.size() == this.fieldTransforms.size(), "Transform results length mismatch.", new Object[0]);
        ObjectValue.Builder builder = objectValue.toBuilder();
        for (int i = 0; i < this.fieldTransforms.size(); i++) {
            builder.set(this.fieldTransforms.get(i).getFieldPath(), list.get(i));
        }
        return builder.build();
    }
}

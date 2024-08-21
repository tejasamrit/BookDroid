package com.google.firebase.firestore.core;

import com.google.firebase.firestore.model.FieldPath;
import com.google.firebase.firestore.util.Assert;
import com.google.firestore.p009v1.Value;

public class IndexRange {
    private final Value end;
    private final FieldPath fieldPath;
    private final Value start;

    private IndexRange(Builder builder) {
        this.fieldPath = builder.fieldPath;
        this.start = builder.start;
        this.end = builder.end;
    }

    public FieldPath getFieldPath() {
        return this.fieldPath;
    }

    public Value getStart() {
        return this.start;
    }

    public Value getEnd() {
        return this.end;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        /* access modifiers changed from: private */
        public Value end;
        /* access modifiers changed from: private */
        public FieldPath fieldPath;
        /* access modifiers changed from: private */
        public Value start;

        public Builder setFieldPath(FieldPath fieldPath2) {
            this.fieldPath = fieldPath2;
            return this;
        }

        public Builder setStart(Value value) {
            this.start = value;
            return this;
        }

        public Builder setEnd(Value value) {
            this.end = value;
            return this;
        }

        public IndexRange build() {
            Assert.hardAssert(this.fieldPath != null, "Field path must be specified", new Object[0]);
            return new IndexRange(this);
        }
    }
}

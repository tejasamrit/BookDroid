package com.google.firebase.firestore.model.mutation;

import com.google.firebase.Timestamp;
import com.google.firebase.firestore.model.Values;
import com.google.firestore.p009v1.ArrayValue;
import com.google.firestore.p009v1.Value;
import java.util.Collections;
import java.util.List;

public abstract class ArrayTransformOperation implements TransformOperation {
    private final List<Value> elements;

    /* access modifiers changed from: protected */
    public abstract Value apply(Value value);

    public Value computeBaseValue(Value value) {
        return null;
    }

    ArrayTransformOperation(List<Value> list) {
        this.elements = Collections.unmodifiableList(list);
    }

    public List<Value> getElements() {
        return this.elements;
    }

    public Value applyToLocalView(Value value, Timestamp timestamp) {
        return apply(value);
    }

    public Value applyToRemoteDocument(Value value, Value value2) {
        return apply(value);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return this.elements.equals(((ArrayTransformOperation) obj).elements);
    }

    public int hashCode() {
        return (getClass().hashCode() * 31) + this.elements.hashCode();
    }

    static ArrayValue.Builder coercedFieldValuesArray(Value value) {
        if (Values.isArray(value)) {
            return (ArrayValue.Builder) value.getArrayValue().toBuilder();
        }
        return ArrayValue.newBuilder();
    }

    public static class Union extends ArrayTransformOperation {
        public Union(List<Value> list) {
            super(list);
        }

        /* access modifiers changed from: protected */
        public Value apply(Value value) {
            ArrayValue.Builder coercedFieldValuesArray = coercedFieldValuesArray(value);
            for (Value next : getElements()) {
                if (!Values.contains(coercedFieldValuesArray, next)) {
                    coercedFieldValuesArray.addValues(next);
                }
            }
            return (Value) Value.newBuilder().setArrayValue(coercedFieldValuesArray).build();
        }
    }

    public static class Remove extends ArrayTransformOperation {
        public Remove(List<Value> list) {
            super(list);
        }

        /* access modifiers changed from: protected */
        public Value apply(Value value) {
            ArrayValue.Builder coercedFieldValuesArray = coercedFieldValuesArray(value);
            for (Value next : getElements()) {
                int i = 0;
                while (i < coercedFieldValuesArray.getValuesCount()) {
                    if (Values.equals(coercedFieldValuesArray.getValues(i), next)) {
                        coercedFieldValuesArray.removeValues(i);
                    } else {
                        i++;
                    }
                }
            }
            return (Value) Value.newBuilder().setArrayValue(coercedFieldValuesArray).build();
        }
    }
}

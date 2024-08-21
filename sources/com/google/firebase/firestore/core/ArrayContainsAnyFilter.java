package com.google.firebase.firestore.core;

import com.google.firebase.firestore.core.Filter;
import com.google.firebase.firestore.model.Document;
import com.google.firebase.firestore.model.FieldPath;
import com.google.firebase.firestore.model.Values;
import com.google.firebase.firestore.util.Assert;
import com.google.firestore.p009v1.Value;

public class ArrayContainsAnyFilter extends FieldFilter {
    ArrayContainsAnyFilter(FieldPath fieldPath, Value value) {
        super(fieldPath, Filter.Operator.ARRAY_CONTAINS_ANY, value);
        Assert.hardAssert(Values.isArray(value), "ArrayContainsAnyFilter expects an ArrayValue", new Object[0]);
    }

    public boolean matches(Document document) {
        Value field = document.getField(getField());
        if (!Values.isArray(field)) {
            return false;
        }
        for (Value contains : field.getArrayValue().getValuesList()) {
            if (Values.contains(getValue().getArrayValue(), contains)) {
                return true;
            }
        }
        return false;
    }
}

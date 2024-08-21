package com.google.firebase.firestore.core;

import com.google.firebase.firestore.core.Filter;
import com.google.firebase.firestore.model.Document;
import com.google.firebase.firestore.model.FieldPath;
import com.google.firebase.firestore.model.Values;
import com.google.firebase.firestore.util.Assert;
import com.google.firestore.p009v1.Value;

public class NotInFilter extends FieldFilter {
    NotInFilter(FieldPath fieldPath, Value value) {
        super(fieldPath, Filter.Operator.NOT_IN, value);
        Assert.hardAssert(Values.isArray(value), "NotInFilter expects an ArrayValue", new Object[0]);
    }

    public boolean matches(Document document) {
        Value field;
        if (!Values.contains(getValue().getArrayValue(), Values.NULL_VALUE) && (field = document.getField(getField())) != null && !Values.contains(getValue().getArrayValue(), field)) {
            return true;
        }
        return false;
    }
}

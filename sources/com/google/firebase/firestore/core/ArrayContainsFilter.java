package com.google.firebase.firestore.core;

import com.google.firebase.firestore.core.Filter;
import com.google.firebase.firestore.model.Document;
import com.google.firebase.firestore.model.FieldPath;
import com.google.firebase.firestore.model.Values;
import com.google.firestore.p009v1.Value;

public class ArrayContainsFilter extends FieldFilter {
    ArrayContainsFilter(FieldPath fieldPath, Value value) {
        super(fieldPath, Filter.Operator.ARRAY_CONTAINS, value);
    }

    public boolean matches(Document document) {
        Value field = document.getField(getField());
        return Values.isArray(field) && Values.contains(field.getArrayValue(), getValue());
    }
}

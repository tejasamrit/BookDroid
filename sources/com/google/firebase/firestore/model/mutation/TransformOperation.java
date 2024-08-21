package com.google.firebase.firestore.model.mutation;

import com.google.firebase.Timestamp;
import com.google.firestore.p009v1.Value;

public interface TransformOperation {
    Value applyToLocalView(Value value, Timestamp timestamp);

    Value applyToRemoteDocument(Value value, Value value2);

    Value computeBaseValue(Value value);
}

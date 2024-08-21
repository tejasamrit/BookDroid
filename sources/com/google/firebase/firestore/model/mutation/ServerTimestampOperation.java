package com.google.firebase.firestore.model.mutation;

import com.google.firebase.Timestamp;
import com.google.firebase.firestore.model.ServerTimestamps;
import com.google.firestore.p009v1.Value;

public class ServerTimestampOperation implements TransformOperation {
    private static final ServerTimestampOperation SHARED_INSTANCE = new ServerTimestampOperation();

    public Value applyToRemoteDocument(Value value, Value value2) {
        return value2;
    }

    public Value computeBaseValue(Value value) {
        return null;
    }

    private ServerTimestampOperation() {
    }

    public static ServerTimestampOperation getInstance() {
        return SHARED_INSTANCE;
    }

    public Value applyToLocalView(Value value, Timestamp timestamp) {
        return ServerTimestamps.valueOf(timestamp, value);
    }
}

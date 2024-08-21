package com.google.firestore.p009v1;

import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Timestamp;
import java.util.List;

/* renamed from: com.google.firestore.v1.WriteResultOrBuilder */
public interface WriteResultOrBuilder extends MessageLiteOrBuilder {
    Value getTransformResults(int i);

    int getTransformResultsCount();

    List<Value> getTransformResultsList();

    Timestamp getUpdateTime();

    boolean hasUpdateTime();
}

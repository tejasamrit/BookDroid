package com.google.firestore.p009v1;

import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Timestamp;
import java.util.List;

/* renamed from: com.google.firestore.v1.CommitResponseOrBuilder */
public interface CommitResponseOrBuilder extends MessageLiteOrBuilder {
    Timestamp getCommitTime();

    WriteResult getWriteResults(int i);

    int getWriteResultsCount();

    List<WriteResult> getWriteResultsList();

    boolean hasCommitTime();
}

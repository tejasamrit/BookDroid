package com.google.firestore.p009v1;

import com.google.firestore.p009v1.Write;
import com.google.protobuf.ByteString;
import com.google.protobuf.MessageLiteOrBuilder;

/* renamed from: com.google.firestore.v1.WriteOrBuilder */
public interface WriteOrBuilder extends MessageLiteOrBuilder {
    Precondition getCurrentDocument();

    String getDelete();

    ByteString getDeleteBytes();

    Write.OperationCase getOperationCase();

    DocumentTransform getTransform();

    Document getUpdate();

    DocumentMask getUpdateMask();

    String getVerify();

    ByteString getVerifyBytes();

    boolean hasCurrentDocument();

    boolean hasTransform();

    boolean hasUpdate();

    boolean hasUpdateMask();
}

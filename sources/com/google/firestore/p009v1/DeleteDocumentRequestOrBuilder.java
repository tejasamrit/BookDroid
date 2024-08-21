package com.google.firestore.p009v1;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageLiteOrBuilder;

/* renamed from: com.google.firestore.v1.DeleteDocumentRequestOrBuilder */
public interface DeleteDocumentRequestOrBuilder extends MessageLiteOrBuilder {
    Precondition getCurrentDocument();

    String getName();

    ByteString getNameBytes();

    boolean hasCurrentDocument();
}

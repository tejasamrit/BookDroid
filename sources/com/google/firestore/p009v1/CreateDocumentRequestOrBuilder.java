package com.google.firestore.p009v1;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageLiteOrBuilder;

/* renamed from: com.google.firestore.v1.CreateDocumentRequestOrBuilder */
public interface CreateDocumentRequestOrBuilder extends MessageLiteOrBuilder {
    String getCollectionId();

    ByteString getCollectionIdBytes();

    Document getDocument();

    String getDocumentId();

    ByteString getDocumentIdBytes();

    DocumentMask getMask();

    String getParent();

    ByteString getParentBytes();

    boolean hasDocument();

    boolean hasMask();
}

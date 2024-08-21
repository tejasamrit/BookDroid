package com.google.firebase.firestore.local;

import com.google.firebase.firestore.model.DocumentKey;
import java.io.Closeable;

public class IndexCursor implements Closeable {
    public boolean next() {
        throw new RuntimeException("Not yet implemented.");
    }

    public DocumentKey getDocumentKey() {
        throw new RuntimeException("Not yet implemented.");
    }

    public void close() {
        throw new RuntimeException("Not yet implemented.");
    }
}

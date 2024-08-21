package com.google.firebase.firestore.local;

public interface GarbageCollectionScheduler {
    void start();

    void stop();
}

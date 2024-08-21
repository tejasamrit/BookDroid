package com.google.firebase.firestore.core;

import com.google.firebase.firestore.EventListener;

/* compiled from: FirestoreClient */
final /* synthetic */ class FirestoreClient$$Lambda$14 implements Runnable {
    private final FirestoreClient arg$1;
    private final EventListener arg$2;

    private FirestoreClient$$Lambda$14(FirestoreClient firestoreClient, EventListener eventListener) {
        this.arg$1 = firestoreClient;
        this.arg$2 = eventListener;
    }

    public static Runnable lambdaFactory$(FirestoreClient firestoreClient, EventListener eventListener) {
        return new FirestoreClient$$Lambda$14(firestoreClient, eventListener);
    }

    public void run() {
        this.arg$1.eventManager.addSnapshotsInSyncListener(this.arg$2);
    }
}

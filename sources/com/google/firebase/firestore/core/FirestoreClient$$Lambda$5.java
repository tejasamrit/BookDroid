package com.google.firebase.firestore.core;

/* compiled from: FirestoreClient */
final /* synthetic */ class FirestoreClient$$Lambda$5 implements Runnable {
    private final FirestoreClient arg$1;

    private FirestoreClient$$Lambda$5(FirestoreClient firestoreClient) {
        this.arg$1 = firestoreClient;
    }

    public static Runnable lambdaFactory$(FirestoreClient firestoreClient) {
        return new FirestoreClient$$Lambda$5(firestoreClient);
    }

    public void run() {
        FirestoreClient.lambda$terminate$5(this.arg$1);
    }
}

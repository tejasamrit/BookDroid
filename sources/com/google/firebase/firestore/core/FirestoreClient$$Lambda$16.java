package com.google.firebase.firestore.core;

import com.google.firebase.firestore.auth.User;

/* compiled from: FirestoreClient */
final /* synthetic */ class FirestoreClient$$Lambda$16 implements Runnable {
    private final FirestoreClient arg$1;
    private final User arg$2;

    private FirestoreClient$$Lambda$16(FirestoreClient firestoreClient, User user) {
        this.arg$1 = firestoreClient;
        this.arg$2 = user;
    }

    public static Runnable lambdaFactory$(FirestoreClient firestoreClient, User user) {
        return new FirestoreClient$$Lambda$16(firestoreClient, user);
    }

    public void run() {
        FirestoreClient.lambda$new$1(this.arg$1, this.arg$2);
    }
}

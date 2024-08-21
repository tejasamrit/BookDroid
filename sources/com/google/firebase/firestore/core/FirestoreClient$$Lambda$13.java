package com.google.firebase.firestore.core;

import com.google.android.gms.tasks.TaskCompletionSource;

/* compiled from: FirestoreClient */
final /* synthetic */ class FirestoreClient$$Lambda$13 implements Runnable {
    private final FirestoreClient arg$1;
    private final TaskCompletionSource arg$2;

    private FirestoreClient$$Lambda$13(FirestoreClient firestoreClient, TaskCompletionSource taskCompletionSource) {
        this.arg$1 = firestoreClient;
        this.arg$2 = taskCompletionSource;
    }

    public static Runnable lambdaFactory$(FirestoreClient firestoreClient, TaskCompletionSource taskCompletionSource) {
        return new FirestoreClient$$Lambda$13(firestoreClient, taskCompletionSource);
    }

    public void run() {
        this.arg$1.syncEngine.registerPendingWritesTask(this.arg$2);
    }
}

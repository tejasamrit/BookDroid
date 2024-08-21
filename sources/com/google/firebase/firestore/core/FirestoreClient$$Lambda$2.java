package com.google.firebase.firestore.core;

import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.firebase.firestore.auth.User;
import com.google.firebase.firestore.util.AsyncQueue;
import com.google.firebase.firestore.util.C1920Listener;
import java.util.concurrent.atomic.AtomicBoolean;

/* compiled from: FirestoreClient */
final /* synthetic */ class FirestoreClient$$Lambda$2 implements C1920Listener {
    private final FirestoreClient arg$1;
    private final AtomicBoolean arg$2;
    private final TaskCompletionSource arg$3;
    private final AsyncQueue arg$4;

    private FirestoreClient$$Lambda$2(FirestoreClient firestoreClient, AtomicBoolean atomicBoolean, TaskCompletionSource taskCompletionSource, AsyncQueue asyncQueue) {
        this.arg$1 = firestoreClient;
        this.arg$2 = atomicBoolean;
        this.arg$3 = taskCompletionSource;
        this.arg$4 = asyncQueue;
    }

    public static C1920Listener lambdaFactory$(FirestoreClient firestoreClient, AtomicBoolean atomicBoolean, TaskCompletionSource taskCompletionSource, AsyncQueue asyncQueue) {
        return new FirestoreClient$$Lambda$2(firestoreClient, atomicBoolean, taskCompletionSource, asyncQueue);
    }

    public void onValue(Object obj) {
        FirestoreClient.lambda$new$2(this.arg$1, this.arg$2, this.arg$3, this.arg$4, (User) obj);
    }
}

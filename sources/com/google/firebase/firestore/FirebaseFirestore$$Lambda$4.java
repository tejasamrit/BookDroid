package com.google.firebase.firestore;

import com.google.firebase.firestore.core.AsyncEventListener;

/* compiled from: FirebaseFirestore */
final /* synthetic */ class FirebaseFirestore$$Lambda$4 implements ListenerRegistration {
    private final FirebaseFirestore arg$1;
    private final AsyncEventListener arg$2;

    private FirebaseFirestore$$Lambda$4(FirebaseFirestore firebaseFirestore, AsyncEventListener asyncEventListener) {
        this.arg$1 = firebaseFirestore;
        this.arg$2 = asyncEventListener;
    }

    public static ListenerRegistration lambdaFactory$(FirebaseFirestore firebaseFirestore, AsyncEventListener asyncEventListener) {
        return new FirebaseFirestore$$Lambda$4(firebaseFirestore, asyncEventListener);
    }

    public void remove() {
        FirebaseFirestore.lambda$addSnapshotsInSyncListener$4(this.arg$1, this.arg$2);
    }
}

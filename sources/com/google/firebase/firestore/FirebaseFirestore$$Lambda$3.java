package com.google.firebase.firestore;

/* compiled from: FirebaseFirestore */
final /* synthetic */ class FirebaseFirestore$$Lambda$3 implements EventListener {
    private final Runnable arg$1;

    private FirebaseFirestore$$Lambda$3(Runnable runnable) {
        this.arg$1 = runnable;
    }

    public static EventListener lambdaFactory$(Runnable runnable) {
        return new FirebaseFirestore$$Lambda$3(runnable);
    }

    public void onEvent(Object obj, FirebaseFirestoreException firebaseFirestoreException) {
        FirebaseFirestore.lambda$addSnapshotsInSyncListener$3(this.arg$1, (Void) obj, firebaseFirestoreException);
    }
}

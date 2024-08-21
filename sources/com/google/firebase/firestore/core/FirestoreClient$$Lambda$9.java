package com.google.firebase.firestore.core;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.Task;

/* compiled from: FirestoreClient */
final /* synthetic */ class FirestoreClient$$Lambda$9 implements Continuation {
    private static final FirestoreClient$$Lambda$9 instance = new FirestoreClient$$Lambda$9();

    private FirestoreClient$$Lambda$9() {
    }

    public static Continuation lambdaFactory$() {
        return instance;
    }

    public Object then(Task task) {
        return FirestoreClient.lambda$getDocumentFromLocalCache$9(task);
    }
}

package com.google.firebase.firestore.remote;

import com.google.android.gms.tasks.OnSuccessListener;
import p012io.grpc.ClientCall;

/* compiled from: FirestoreChannel */
final /* synthetic */ class FirestoreChannel$2$$Lambda$1 implements OnSuccessListener {
    private static final FirestoreChannel$2$$Lambda$1 instance = new FirestoreChannel$2$$Lambda$1();

    private FirestoreChannel$2$$Lambda$1() {
    }

    public static OnSuccessListener lambdaFactory$() {
        return instance;
    }

    public void onSuccess(Object obj) {
        ((ClientCall) obj).halfClose();
    }
}

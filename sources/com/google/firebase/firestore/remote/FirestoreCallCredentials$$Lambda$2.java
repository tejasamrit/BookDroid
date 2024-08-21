package com.google.firebase.firestore.remote;

import com.google.android.gms.tasks.OnFailureListener;
import p012io.grpc.CallCredentials;

/* compiled from: FirestoreCallCredentials */
final /* synthetic */ class FirestoreCallCredentials$$Lambda$2 implements OnFailureListener {
    private final CallCredentials.MetadataApplier arg$1;

    private FirestoreCallCredentials$$Lambda$2(CallCredentials.MetadataApplier metadataApplier) {
        this.arg$1 = metadataApplier;
    }

    public static OnFailureListener lambdaFactory$(CallCredentials.MetadataApplier metadataApplier) {
        return new FirestoreCallCredentials$$Lambda$2(metadataApplier);
    }

    public void onFailure(Exception exc) {
        FirestoreCallCredentials.lambda$applyRequestMetadata$1(this.arg$1, exc);
    }
}

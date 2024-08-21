package com.google.firebase.firestore.local;

import com.google.firebase.firestore.model.SnapshotVersion;
import com.google.firebase.firestore.remote.RemoteEvent;
import com.google.firebase.firestore.util.Supplier;

/* compiled from: LocalStore */
final /* synthetic */ class LocalStore$$Lambda$6 implements Supplier {
    private final LocalStore arg$1;
    private final RemoteEvent arg$2;
    private final SnapshotVersion arg$3;

    private LocalStore$$Lambda$6(LocalStore localStore, RemoteEvent remoteEvent, SnapshotVersion snapshotVersion) {
        this.arg$1 = localStore;
        this.arg$2 = remoteEvent;
        this.arg$3 = snapshotVersion;
    }

    public static Supplier lambdaFactory$(LocalStore localStore, RemoteEvent remoteEvent, SnapshotVersion snapshotVersion) {
        return new LocalStore$$Lambda$6(localStore, remoteEvent, snapshotVersion);
    }

    public Object get() {
        return LocalStore.lambda$applyRemoteEvent$5(this.arg$1, this.arg$2, this.arg$3);
    }
}

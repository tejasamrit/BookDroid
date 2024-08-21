package com.google.firebase.firestore.local;

import com.google.firebase.firestore.core.Target;
import com.google.firebase.firestore.local.LocalStore;

/* compiled from: LocalStore */
final /* synthetic */ class LocalStore$$Lambda$8 implements Runnable {
    private final LocalStore arg$1;
    private final LocalStore.AllocateQueryHolder arg$2;
    private final Target arg$3;

    private LocalStore$$Lambda$8(LocalStore localStore, LocalStore.AllocateQueryHolder allocateQueryHolder, Target target) {
        this.arg$1 = localStore;
        this.arg$2 = allocateQueryHolder;
        this.arg$3 = target;
    }

    public static Runnable lambdaFactory$(LocalStore localStore, LocalStore.AllocateQueryHolder allocateQueryHolder, Target target) {
        return new LocalStore$$Lambda$8(localStore, allocateQueryHolder, target);
    }

    public void run() {
        LocalStore.lambda$allocateTarget$7(this.arg$1, this.arg$2, this.arg$3);
    }
}

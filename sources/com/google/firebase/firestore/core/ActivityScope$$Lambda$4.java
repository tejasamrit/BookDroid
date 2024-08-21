package com.google.firebase.firestore.core;

import com.google.firebase.firestore.ListenerRegistration;

/* compiled from: ActivityScope */
final /* synthetic */ class ActivityScope$$Lambda$4 implements Runnable {
    private final ListenerRegistration arg$1;

    private ActivityScope$$Lambda$4(ListenerRegistration listenerRegistration) {
        this.arg$1 = listenerRegistration;
    }

    public static Runnable lambdaFactory$(ListenerRegistration listenerRegistration) {
        return new ActivityScope$$Lambda$4(listenerRegistration);
    }

    public void run() {
        this.arg$1.remove();
    }
}

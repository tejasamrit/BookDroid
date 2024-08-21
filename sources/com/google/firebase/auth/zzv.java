package com.google.firebase.auth;

import com.google.firebase.FirebaseApp;
import com.google.firebase.components.ComponentContainer;
import com.google.firebase.components.ComponentFactory;

/* compiled from: com.google.firebase:firebase-auth@@20.0.1 */
final /* synthetic */ class zzv implements ComponentFactory {
    static final ComponentFactory zza = new zzv();

    private zzv() {
    }

    public final Object create(ComponentContainer componentContainer) {
        int i = FirebaseAuthRegistrar.zza;
        return new com.google.firebase.auth.internal.zzv((FirebaseApp) componentContainer.get(FirebaseApp.class));
    }
}

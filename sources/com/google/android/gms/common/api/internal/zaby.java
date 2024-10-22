package com.google.android.gms.common.api.internal;

import android.os.RemoteException;
import com.google.android.gms.common.Feature;
import com.google.android.gms.common.api.internal.RegistrationMethods;
import com.google.android.gms.tasks.TaskCompletionSource;

/* compiled from: com.google.android.gms:play-services-base@@17.3.0 */
final class zaby extends RegisterListenerMethod<A, L> {
    private final /* synthetic */ RegistrationMethods.Builder zaa;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zaby(RegistrationMethods.Builder builder, ListenerHolder listenerHolder, Feature[] featureArr, boolean z) {
        super(listenerHolder, featureArr, z);
        this.zaa = builder;
    }

    /* access modifiers changed from: protected */
    public final void registerListener(A a, TaskCompletionSource<Void> taskCompletionSource) throws RemoteException {
        this.zaa.zaa.accept(a, taskCompletionSource);
    }
}

package com.google.android.gms.internal.p007firebaseauthapi;

import com.google.android.gms.common.api.internal.RemoteCall;
import com.google.android.gms.tasks.TaskCompletionSource;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzrs */
/* compiled from: com.google.firebase:firebase-auth@@20.0.1 */
final /* synthetic */ class zzrs implements RemoteCall {
    private final zzrt zza;

    zzrs(zzrt zzrt) {
        this.zza = zzrt;
    }

    public final void accept(Object obj, Object obj2) {
        this.zza.zzd((zztc) obj, (TaskCompletionSource) obj2);
    }
}

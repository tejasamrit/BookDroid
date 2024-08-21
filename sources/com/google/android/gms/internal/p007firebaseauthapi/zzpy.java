package com.google.android.gms.internal.p007firebaseauthapi;

import com.google.android.gms.common.api.internal.RemoteCall;
import com.google.android.gms.tasks.TaskCompletionSource;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzpy */
/* compiled from: com.google.firebase:firebase-auth@@20.0.1 */
final /* synthetic */ class zzpy implements RemoteCall {
    private final zzpz zza;

    zzpy(zzpz zzpz) {
        this.zza = zzpz;
    }

    public final void accept(Object obj, Object obj2) {
        zzpz zzpz = this.zza;
        zzpz.zzv = new zzum(zzpz, (TaskCompletionSource) obj2);
        ((zztc) obj).zzo().zzq(new zzlo(zzpz.zze.zzg()), zzpz.zzc);
    }
}

package com.google.android.gms.internal.p007firebaseauthapi;

import com.google.android.gms.common.api.internal.RemoteCall;
import com.google.android.gms.tasks.TaskCompletionSource;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzrg */
/* compiled from: com.google.firebase:firebase-auth@@20.0.1 */
final /* synthetic */ class zzrg implements RemoteCall {
    private final zzrh zza;

    zzrg(zzrh zzrh) {
        this.zza = zzrh;
    }

    public final void accept(Object obj, Object obj2) {
        zzrh zzrh = this.zza;
        zzrh.zzv = new zzum(zzrh, (TaskCompletionSource) obj2);
        ((zztc) obj).zzo().zzo(new zzme(zzrh.zze.zzg()), zzrh.zzc);
    }
}

package com.google.android.gms.internal.p007firebaseauthapi;

import com.google.android.gms.common.api.internal.RemoteCall;
import com.google.android.gms.tasks.TaskCompletionSource;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzsg */
/* compiled from: com.google.firebase:firebase-auth@@20.0.1 */
final /* synthetic */ class zzsg implements RemoteCall {
    private final zzsh zza;

    zzsg(zzsh zzsh) {
        this.zza = zzsh;
    }

    public final void accept(Object obj, Object obj2) {
        zzsh zzsh = this.zza;
        zzsh.zzv = new zzum(zzsh, (TaskCompletionSource) obj2);
        ((zztc) obj).zzo().zzm(new zzng(zzsh.zze.zzg()), zzsh.zzc);
    }
}

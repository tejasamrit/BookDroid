package com.google.android.gms.internal.p007firebaseauthapi;

import com.google.android.gms.common.api.internal.RemoteCall;
import com.google.android.gms.tasks.TaskCompletionSource;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzpw */
/* compiled from: com.google.firebase:firebase-auth@@20.0.1 */
final /* synthetic */ class zzpw implements RemoteCall {
    private final zzpx zza;

    zzpw(zzpx zzpx) {
        this.zza = zzpx;
    }

    public final void accept(Object obj, Object obj2) {
        zzpx zzpx = this.zza;
        zzpx.zzv = new zzum(zzpx, (TaskCompletionSource) obj2);
        ((zztc) obj).zzo().zzh(zzpx.zza, zzpx.zzc);
    }
}

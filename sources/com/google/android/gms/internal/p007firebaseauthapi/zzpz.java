package com.google.android.gms.internal.p007firebaseauthapi;

import com.google.android.gms.common.api.internal.TaskApiCall;
import com.google.firebase.auth.internal.zzan;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzpz */
/* compiled from: com.google.firebase:firebase-auth@@20.0.1 */
final class zzpz extends zzun<Void, zzan> {
    public zzpz() {
        super(5);
    }

    public final String zza() {
        return "delete";
    }

    public final TaskApiCall<zztc, Void> zzb() {
        return TaskApiCall.builder().run(new zzpy(this)).build();
    }

    public final void zzc() {
        ((zzan) this.zzf).zza();
        zzj(null);
    }
}

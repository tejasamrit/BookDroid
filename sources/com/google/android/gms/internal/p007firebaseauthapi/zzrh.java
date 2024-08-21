package com.google.android.gms.internal.p007firebaseauthapi;

import com.google.android.gms.common.api.internal.TaskApiCall;
import com.google.firebase.auth.internal.zzg;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzrh */
/* compiled from: com.google.firebase:firebase-auth@@20.0.1 */
final class zzrh extends zzun<Void, zzg> {
    public zzrh() {
        super(2);
    }

    public final String zza() {
        return "reload";
    }

    public final TaskApiCall<zztc, Void> zzb() {
        return TaskApiCall.builder().run(new zzrg(this)).build();
    }

    public final void zzc() {
        ((zzg) this.zzf).zza(this.zzj, zzsy.zzS(this.zzd, this.zzk));
        zzj(null);
    }
}

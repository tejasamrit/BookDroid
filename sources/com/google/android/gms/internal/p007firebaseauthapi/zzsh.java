package com.google.android.gms.internal.p007firebaseauthapi;

import com.google.android.gms.common.api.internal.TaskApiCall;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.internal.zzg;
import com.google.firebase.auth.internal.zzr;
import com.google.firebase.auth.internal.zzx;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzsh */
/* compiled from: com.google.firebase:firebase-auth@@20.0.1 */
final class zzsh extends zzun<AuthResult, zzg> {
    public zzsh() {
        super(2);
    }

    public final String zza() {
        return "unlinkEmailCredential";
    }

    public final TaskApiCall<zztc, AuthResult> zzb() {
        return TaskApiCall.builder().run(new zzsg(this)).build();
    }

    public final void zzc() {
        zzx zzS = zzsy.zzS(this.zzd, this.zzk);
        ((zzg) this.zzf).zza(this.zzj, zzS);
        zzj(new zzr(zzS));
    }
}

package com.google.android.gms.internal.p007firebaseauthapi;

import android.os.RemoteException;
import com.google.android.gms.common.api.internal.TaskApiCall;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.internal.zzg;
import com.google.firebase.auth.internal.zzr;
import com.google.firebase.auth.internal.zzx;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzrp */
/* compiled from: com.google.firebase:firebase-auth@@20.0.1 */
final class zzrp extends zzun<AuthResult, zzg> {
    private final zzmo zza;

    public zzrp(String str) {
        super(2);
        this.zza = new zzmo(str);
    }

    public final String zza() {
        return "signInAnonymously";
    }

    public final TaskApiCall<zztc, AuthResult> zzb() {
        return TaskApiCall.builder().run(new zzro(this)).build();
    }

    public final void zzc() {
        zzx zzS = zzsy.zzS(this.zzd, this.zzk);
        ((zzg) this.zzf).zza(this.zzj, zzS);
        zzj(new zzr(zzS));
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zzd(zztc zztc, TaskCompletionSource taskCompletionSource) throws RemoteException {
        this.zzv = new zzum(this, taskCompletionSource);
        zztc.zzo().zzp(this.zza, this.zzc);
    }
}

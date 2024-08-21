package com.google.android.gms.internal.p007firebaseauthapi;

import android.os.RemoteException;
import com.google.android.gms.common.api.internal.TaskApiCall;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.internal.zzg;
import com.google.firebase.auth.internal.zzr;
import com.google.firebase.auth.internal.zzx;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzsj */
/* compiled from: com.google.firebase:firebase-auth@@20.0.1 */
final class zzsj extends zzun<AuthResult, zzg> {
    private final String zza;

    public zzsj(String str) {
        super(2);
        this.zza = Preconditions.checkNotEmpty(str, "provider cannot be null or empty");
    }

    public final String zza() {
        return "unlinkFederatedCredential";
    }

    public final TaskApiCall<zztc, AuthResult> zzb() {
        return TaskApiCall.builder().run(new zzsi(this)).build();
    }

    public final void zzc() {
        zzx zzS = zzsy.zzS(this.zzd, this.zzk);
        ((zzg) this.zzf).zza(this.zzj, zzS);
        zzj(new zzr(zzS));
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zzd(zztc zztc, TaskCompletionSource taskCompletionSource) throws RemoteException {
        this.zzv = new zzum(this, taskCompletionSource);
        zztc.zzo().zzn(new zzni(this.zza, this.zze.zzg()), this.zzc);
    }
}

package com.google.android.gms.internal.p007firebaseauthapi;

import android.os.RemoteException;
import com.google.android.gms.common.api.internal.TaskApiCall;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.firebase.auth.internal.zzg;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzsf */
/* compiled from: com.google.firebase:firebase-auth@@20.0.1 */
final class zzsf extends zzun<Void, zzg> {
    private final zzne zza;

    public zzsf(String str, String str2) {
        super(2);
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotNull(str2);
        this.zza = new zzne(str, str2);
    }

    public final String zza() {
        return "unenrollMfa";
    }

    public final TaskApiCall<zztc, Void> zzb() {
        return TaskApiCall.builder().run(new zzse(this)).build();
    }

    public final void zzc() {
        ((zzg) this.zzf).zza(this.zzj, zzsy.zzS(this.zzd, this.zzk));
        zzj(null);
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zzd(zztc zztc, TaskCompletionSource taskCompletionSource) throws RemoteException {
        this.zzv = new zzum(this, taskCompletionSource);
        zztc.zzo().zzD(this.zza, this.zzc);
    }
}

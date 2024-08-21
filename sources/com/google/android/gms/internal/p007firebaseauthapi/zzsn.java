package com.google.android.gms.internal.p007firebaseauthapi;

import android.os.RemoteException;
import com.google.android.gms.common.api.internal.TaskApiCall;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.firebase.auth.internal.zzg;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzsn */
/* compiled from: com.google.firebase:firebase-auth@@20.0.1 */
final class zzsn extends zzun<Void, zzg> {
    private final String zza;

    public zzsn(String str) {
        super(2);
        this.zza = Preconditions.checkNotEmpty(str, "password cannot be null or empty");
    }

    public final String zza() {
        return "updatePassword";
    }

    public final TaskApiCall<zztc, Void> zzb() {
        return TaskApiCall.builder().run(new zzsm(this)).build();
    }

    public final void zzc() {
        ((zzg) this.zzf).zza(this.zzj, zzsy.zzS(this.zzd, this.zzk));
        zzj(null);
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zzd(zztc zztc, TaskCompletionSource taskCompletionSource) throws RemoteException {
        this.zzv = new zzum(this, taskCompletionSource);
        zztc.zzo().zzg(new zzlg(this.zze.zzg(), this.zza), this.zzc);
    }
}

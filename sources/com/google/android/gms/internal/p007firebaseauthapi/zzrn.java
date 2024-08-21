package com.google.android.gms.internal.p007firebaseauthapi;

import android.os.RemoteException;
import com.google.android.gms.common.api.internal.TaskApiCall;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.firebase.auth.internal.zzg;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzrn */
/* compiled from: com.google.firebase:firebase-auth@@20.0.1 */
final class zzrn extends zzun<Void, zzg> {
    private final zzmm zza;

    public zzrn(String str) {
        super(9);
        this.zza = new zzmm(str);
    }

    public final String zza() {
        return "setFirebaseUIVersion";
    }

    public final TaskApiCall<zztc, Void> zzb() {
        return TaskApiCall.builder().run(new zzrm(this)).build();
    }

    public final void zzc() {
        zzj(null);
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zzd(zztc zztc, TaskCompletionSource taskCompletionSource) throws RemoteException {
        this.zzv = new zzum(this, taskCompletionSource);
        zztc.zzo().zzy(this.zza, this.zzc);
    }
}

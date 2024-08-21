package com.google.android.gms.internal.p007firebaseauthapi;

import android.os.RemoteException;
import com.google.android.gms.common.api.internal.TaskApiCall;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.firebase.auth.ActionCodeSettings;
import com.google.firebase.auth.internal.zzg;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzrj */
/* compiled from: com.google.firebase:firebase-auth@@20.0.1 */
final class zzrj extends zzun<Void, zzg> {
    private final zzmg zza;

    public zzrj(String str, ActionCodeSettings actionCodeSettings) {
        super(6);
        Preconditions.checkNotEmpty(str, "token cannot be null or empty");
        this.zza = new zzmg(str, actionCodeSettings);
    }

    public final String zza() {
        return "sendEmailVerification";
    }

    public final TaskApiCall<zztc, Void> zzb() {
        return TaskApiCall.builder().run(new zzri(this)).build();
    }

    public final void zzc() {
        zzj(null);
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zzd(zztc zztc, TaskCompletionSource taskCompletionSource) throws RemoteException {
        this.zzv = new zzum(this, taskCompletionSource);
        zztc.zzo().zzx(this.zza, this.zzc);
    }
}

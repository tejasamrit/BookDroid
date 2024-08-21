package com.google.android.gms.internal.p007firebaseauthapi;

import android.os.RemoteException;
import com.google.android.gms.common.api.internal.TaskApiCall;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.firebase.auth.ActionCodeSettings;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzst */
/* compiled from: com.google.firebase:firebase-auth@@20.0.1 */
final class zzst extends zzun<Void, Void> {
    private final zznm zza;

    public zzst(String str, String str2, ActionCodeSettings actionCodeSettings) {
        super(6);
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotEmpty(str2);
        Preconditions.checkNotNull(actionCodeSettings);
        this.zza = new zznm(str, str2, actionCodeSettings);
    }

    public final String zza() {
        return "verifyBeforeUpdateEmail";
    }

    public final TaskApiCall<zztc, Void> zzb() {
        return TaskApiCall.builder().run(new zzss(this)).build();
    }

    public final void zzc() {
        zzj(null);
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zzd(zztc zztc, TaskCompletionSource taskCompletionSource) throws RemoteException {
        this.zzv = new zzum(this, taskCompletionSource);
        zztc.zzo().zzG(this.zza, this.zzc);
    }
}

package com.google.android.gms.internal.p007firebaseauthapi;

import android.os.RemoteException;
import com.google.android.gms.common.api.internal.TaskApiCall;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.firebase.auth.ActionCodeResult;
import com.google.firebase.auth.internal.zzg;
import com.google.firebase.auth.internal.zzo;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzpt */
/* compiled from: com.google.firebase:firebase-auth@@20.0.1 */
final class zzpt extends zzun<ActionCodeResult, zzg> {
    private final zzli zza;

    public zzpt(String str, String str2) {
        super(4);
        Preconditions.checkNotEmpty(str, "code cannot be null or empty");
        this.zza = new zzli(str, str2);
    }

    public final String zza() {
        return "checkActionCode";
    }

    public final TaskApiCall<zztc, ActionCodeResult> zzb() {
        return TaskApiCall.builder().run(new zzps(this)).build();
    }

    public final void zzc() {
        zzj(new zzo(this.zzm));
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zzd(zztc zztc, TaskCompletionSource taskCompletionSource) throws RemoteException {
        this.zzv = new zzum(this, taskCompletionSource);
        zztc.zzo().zzr(this.zza, this.zzc);
    }
}

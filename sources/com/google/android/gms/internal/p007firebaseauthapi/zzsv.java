package com.google.android.gms.internal.p007firebaseauthapi;

import android.os.RemoteException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.TaskApiCall;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.firebase.FirebaseError;
import com.google.firebase.auth.internal.zzg;
import com.google.firebase.auth.internal.zzo;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzsv */
/* compiled from: com.google.firebase:firebase-auth@@20.0.1 */
final class zzsv extends zzun<String, zzg> {
    private final zzli zza;

    public zzsv(String str, String str2) {
        super(4);
        Preconditions.checkNotEmpty(str, "code cannot be null or empty");
        this.zza = new zzli(str, str2);
    }

    public final String zza() {
        return "verifyPasswordResetCode";
    }

    public final TaskApiCall<zztc, String> zzb() {
        return TaskApiCall.builder().run(new zzsu(this)).build();
    }

    public final void zzc() {
        if (new zzo(this.zzm).getOperation() != 0) {
            zzk(new Status(FirebaseError.ERROR_INTERNAL_ERROR));
        } else {
            zzj(this.zzm.zzb());
        }
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zzd(zztc zztc, TaskCompletionSource taskCompletionSource) throws RemoteException {
        this.zzv = new zzum(this, taskCompletionSource);
        zztc.zzo().zzr(this.zza, this.zzc);
    }
}

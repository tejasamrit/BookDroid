package com.google.android.gms.internal.p007firebaseauthapi;

import android.os.RemoteException;
import com.google.android.gms.common.api.internal.TaskApiCall;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.firebase.auth.SignInMethodQueryResult;
import com.google.firebase.auth.internal.zzaj;
import com.google.firebase.auth.internal.zzg;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzqb */
/* compiled from: com.google.firebase:firebase-auth@@20.0.1 */
final class zzqb extends zzun<SignInMethodQueryResult, zzg> {
    private final zzlw zza;

    public zzqb(String str, String str2) {
        super(3);
        Preconditions.checkNotEmpty(str, "email cannot be null or empty");
        this.zza = new zzlw(str, str2);
    }

    public final String zza() {
        return "fetchSignInMethodsForEmail";
    }

    public final TaskApiCall<zztc, SignInMethodQueryResult> zzb() {
        return TaskApiCall.builder().run(new zzqa(this)).build();
    }

    public final void zzc() {
        zzj(new zzaj(this.zzl.zzb()));
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zzd(zztc zztc, TaskCompletionSource taskCompletionSource) throws RemoteException {
        this.zzv = new zzum(this, taskCompletionSource);
        zztc.zzo().zzj(this.zza, this.zzc);
    }
}

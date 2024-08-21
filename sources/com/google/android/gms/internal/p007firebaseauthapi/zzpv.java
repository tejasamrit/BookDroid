package com.google.android.gms.internal.p007firebaseauthapi;

import android.os.RemoteException;
import com.google.android.gms.common.api.internal.TaskApiCall;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.firebase.auth.internal.zzg;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzpv */
/* compiled from: com.google.firebase:firebase-auth@@20.0.1 */
final class zzpv extends zzun<Void, zzg> {
    private final zzlk zza;

    public zzpv(String str, String str2, String str3) {
        super(4);
        Preconditions.checkNotEmpty(str, "code cannot be null or empty");
        Preconditions.checkNotEmpty(str2, "new password cannot be null or empty");
        this.zza = new zzlk(str, str2, str3);
    }

    public final String zza() {
        return "confirmPasswordReset";
    }

    public final TaskApiCall<zztc, Void> zzb() {
        return TaskApiCall.builder().run(new zzpu(this)).build();
    }

    public final void zzc() {
        zzj(null);
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zzd(zztc zztc, TaskCompletionSource taskCompletionSource) throws RemoteException {
        this.zzv = new zzum(this, taskCompletionSource);
        zztc.zzo().zzt(this.zza, this.zzc);
    }
}

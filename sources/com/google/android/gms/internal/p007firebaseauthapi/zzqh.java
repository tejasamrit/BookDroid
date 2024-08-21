package com.google.android.gms.internal.p007firebaseauthapi;

import android.os.RemoteException;
import android.text.TextUtils;
import com.google.android.gms.common.api.internal.TaskApiCall;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.firebase.auth.GetTokenResult;
import com.google.firebase.auth.internal.zzay;
import com.google.firebase.auth.internal.zzg;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzqh */
/* compiled from: com.google.firebase:firebase-auth@@20.0.1 */
final class zzqh extends zzun<GetTokenResult, zzg> {
    private final zzlu zza;

    public zzqh(String str) {
        super(1);
        Preconditions.checkNotEmpty(str, "refresh token cannot be null");
        this.zza = new zzlu(str);
    }

    public final String zza() {
        return "getAccessToken";
    }

    public final TaskApiCall<zztc, GetTokenResult> zzb() {
        return TaskApiCall.builder().run(new zzqg(this)).build();
    }

    public final void zzc() {
        if (TextUtils.isEmpty(this.zzj.zzd())) {
            this.zzj.zzc(this.zza.zza());
        }
        ((zzg) this.zzf).zza(this.zzj, this.zze);
        zzj(zzay.zza(this.zzj.zze()));
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zzd(zztc zztc, TaskCompletionSource taskCompletionSource) throws RemoteException {
        this.zzv = new zzum(this, taskCompletionSource);
        zztc.zzo().zzb(this.zza, this.zzc);
    }
}

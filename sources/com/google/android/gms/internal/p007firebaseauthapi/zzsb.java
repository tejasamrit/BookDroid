package com.google.android.gms.internal.p007firebaseauthapi;

import android.os.RemoteException;
import com.google.android.gms.common.api.internal.TaskApiCall;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.firebase.auth.internal.zzag;
import com.google.firebase.auth.internal.zzg;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzsb */
/* compiled from: com.google.firebase:firebase-auth@@20.0.1 */
final class zzsb extends zzun<Void, zzg> {
    private final zzna zza;

    public zzsb(zzag zzag, String str, String str2, long j, boolean z, boolean z2, String str3, String str4, boolean z3) {
        super(8);
        Preconditions.checkNotNull(zzag);
        Preconditions.checkNotEmpty(str);
        this.zza = new zzna(zzag.zzc(), str, str2, j, z, z2, str3, str4, z3);
    }

    public final String zza() {
        return "startMfaEnrollmentWithPhoneNumber";
    }

    public final TaskApiCall<zztc, Void> zzb() {
        return TaskApiCall.builder().run(new zzsa(this)).build();
    }

    public final void zzc() {
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zzd(zztc zztc, TaskCompletionSource taskCompletionSource) throws RemoteException {
        this.zzv = new zzum(this, taskCompletionSource);
        zztc.zzo().zzB(this.zza, this.zzc);
    }
}

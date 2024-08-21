package com.google.android.gms.internal.p007firebaseauthapi;

import android.os.RemoteException;
import com.google.android.gms.common.api.internal.TaskApiCall;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.firebase.auth.PhoneAuthProvider;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzsx */
/* compiled from: com.google.firebase:firebase-auth@@20.0.1 */
final class zzsx extends zzun<Void, PhoneAuthProvider.OnVerificationStateChangedCallbacks> {
    private final zzmk zza;

    public zzsx(zzwt zzwt) {
        super(8);
        Preconditions.checkNotNull(zzwt);
        this.zza = new zzmk(zzwt);
    }

    public final String zza() {
        return "verifyPhoneNumber";
    }

    public final TaskApiCall<zztc, Void> zzb() {
        return TaskApiCall.builder().run(new zzsw(this)).build();
    }

    public final void zzc() {
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zzd(zztc zztc, TaskCompletionSource taskCompletionSource) throws RemoteException {
        this.zzv = new zzum(this, taskCompletionSource);
        zztc.zzo().zzu(this.zza, this.zzc);
    }
}

package com.google.android.gms.internal.p007firebaseauthapi;

import android.os.RemoteException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.TaskApiCall;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.firebase.FirebaseError;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.internal.zzg;
import com.google.firebase.auth.internal.zzx;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzrd */
/* compiled from: com.google.firebase:firebase-auth@@20.0.1 */
final class zzrd extends zzun<Void, zzg> {
    private final zzmy zza;

    public zzrd(PhoneAuthCredential phoneAuthCredential, String str) {
        super(2);
        Preconditions.checkNotNull(phoneAuthCredential, "credential cannot be null");
        phoneAuthCredential.zzf(false);
        this.zza = new zzmy(phoneAuthCredential, str);
    }

    public final String zza() {
        return "reauthenticateWithPhoneCredential";
    }

    public final TaskApiCall<zztc, Void> zzb() {
        return TaskApiCall.builder().run(new zzrc(this)).build();
    }

    public final void zzc() {
        zzx zzS = zzsy.zzS(this.zzd, this.zzk);
        if (this.zze.getUid().equalsIgnoreCase(zzS.getUid())) {
            ((zzg) this.zzf).zza(this.zzj, zzS);
            zzj(null);
            return;
        }
        zzk(new Status(FirebaseError.ERROR_USER_MISMATCH));
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zzd(zztc zztc, TaskCompletionSource taskCompletionSource) throws RemoteException {
        this.zzv = new zzum(this, taskCompletionSource);
        zztc.zzo().zzv(this.zza, this.zzc);
    }
}

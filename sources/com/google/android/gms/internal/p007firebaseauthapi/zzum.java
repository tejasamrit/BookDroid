package com.google.android.gms.internal.p007firebaseauthapi;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.FirebaseAuth;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzum */
/* compiled from: com.google.firebase:firebase-auth@@20.0.1 */
public final class zzum<ResultT, CallbackT> {
    private final zzun<ResultT, CallbackT> zza;
    private final TaskCompletionSource<ResultT> zzb;

    public zzum(zzun<ResultT, CallbackT> zzun, TaskCompletionSource<ResultT> taskCompletionSource) {
        this.zza = zzun;
        this.zzb = taskCompletionSource;
    }

    public final void zza(ResultT resultt, Status status) {
        Preconditions.checkNotNull(this.zzb, "completion source cannot be null");
        if (status != null) {
            zzun<ResultT, CallbackT> zzun = this.zza;
            if (zzun.zzs != null) {
                TaskCompletionSource<ResultT> taskCompletionSource = this.zzb;
                FirebaseAuth instance = FirebaseAuth.getInstance(zzun.zzd);
                zzun<ResultT, CallbackT> zzun2 = this.zza;
                taskCompletionSource.setException(zzte.zzc(instance, zzun2.zzs, ("reauthenticateWithCredential".equals(zzun2.zza()) || "reauthenticateWithCredentialWithData".equals(this.zza.zza())) ? this.zza.zze : null));
                return;
            }
            AuthCredential authCredential = zzun.zzp;
            if (authCredential != null) {
                this.zzb.setException(zzte.zzb(status, authCredential, zzun.zzq, zzun.zzr));
            } else {
                this.zzb.setException(zzte.zza(status));
            }
        } else {
            this.zzb.setResult(resultt);
        }
    }
}

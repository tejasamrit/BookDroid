package com.google.android.gms.internal.p007firebaseauthapi;

import android.text.TextUtils;
import com.google.android.gms.common.api.Status;
import com.google.firebase.FirebaseError;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.zze;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzoh */
/* compiled from: com.google.firebase:firebase-auth@@20.0.1 */
final class zzoh implements zzup<zzxp> {
    final /* synthetic */ zzup zza;
    final /* synthetic */ zzoi zzb;

    zzoh(zzoi zzoi, zzup zzup) {
        this.zzb = zzoi;
        this.zza = zzup;
    }

    public final void zza(String str) {
        this.zza.zza(str);
    }

    public final /* bridge */ /* synthetic */ void zzb(Object obj) {
        zzxp zzxp = (zzxp) obj;
        if (!TextUtils.isEmpty(zzxp.zzf())) {
            this.zzb.zzb.zzl(new Status(FirebaseError.ERROR_CREDENTIAL_ALREADY_IN_USE), PhoneAuthCredential.zzc(zzxp.zzg(), zzxp.zzf()));
            return;
        }
        this.zzb.zzc.zzO(new zzwg(zzxp.zzc(), zzxp.zzb(), Long.valueOf(zzxp.zzd()), "Bearer"), (String) null, "phone", Boolean.valueOf(zzxp.zze()), (zze) null, this.zzb.zzb, this.zza);
    }
}

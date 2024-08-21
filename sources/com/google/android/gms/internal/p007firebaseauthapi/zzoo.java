package com.google.android.gms.internal.p007firebaseauthapi;

import android.text.TextUtils;
import com.google.firebase.auth.internal.zzai;
import com.google.firebase.auth.zze;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzoo */
/* compiled from: com.google.firebase:firebase-auth@@20.0.1 */
final class zzoo implements zzup<zzxr> {
    final /* synthetic */ zzop zza;

    zzoo(zzop zzop) {
        this.zza = zzop;
    }

    public final void zza(String str) {
        this.zza.zzb.zzk(zzai.zza(str));
    }

    public final /* bridge */ /* synthetic */ void zzb(Object obj) {
        zzxr zzxr = (zzxr) obj;
        if (TextUtils.isEmpty(zzxr.zzb()) || TextUtils.isEmpty(zzxr.zzc())) {
            this.zza.zzb.zzk(zzai.zza("INTERNAL_SUCCESS_SIGN_OUT"));
            return;
        }
        this.zza.zzc.zzO(new zzwg(zzxr.zzc(), zzxr.zzb(), Long.valueOf(zzwi.zza(zzxr.zzb())), "Bearer"), (String) null, (String) null, false, (zze) null, this.zza.zzb, this);
    }
}

package com.google.android.gms.internal.p007firebaseauthapi;

import com.google.firebase.auth.internal.zzai;
import com.google.firebase.auth.zze;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzof */
/* compiled from: com.google.firebase:firebase-auth@@20.0.1 */
final class zzof implements zzup<zzxp> {
    final /* synthetic */ zztb zza;
    final /* synthetic */ zzpj zzb;

    zzof(zzpj zzpj, zztb zztb) {
        this.zzb = zzpj;
        this.zza = zztb;
    }

    public final void zza(String str) {
        this.zza.zzk(zzai.zza(str));
    }

    public final /* bridge */ /* synthetic */ void zzb(Object obj) {
        zzxp zzxp = (zzxp) obj;
        this.zzb.zzO(new zzwg(zzxp.zzc(), zzxp.zzb(), Long.valueOf(zzxp.zzd()), "Bearer"), (String) null, (String) null, Boolean.valueOf(zzxp.zze()), (zze) null, this.zza, this);
    }
}

package com.google.android.gms.internal.p007firebaseauthapi;

import com.google.firebase.auth.internal.zzai;
import com.google.firebase.auth.zze;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzou */
/* compiled from: com.google.firebase:firebase-auth@@20.0.1 */
final class zzou implements zzup<zzvt> {
    final /* synthetic */ zztb zza;
    final /* synthetic */ zzpj zzb;

    zzou(zzpj zzpj, zztb zztb) {
        this.zzb = zzpj;
        this.zza = zztb;
    }

    public final void zza(String str) {
        this.zza.zzk(zzai.zza(str));
    }

    public final /* bridge */ /* synthetic */ void zzb(Object obj) {
        zzvt zzvt = (zzvt) obj;
        this.zzb.zzO(new zzwg(zzvt.zzc(), zzvt.zzb(), Long.valueOf(zzwi.zza(zzvt.zzb())), "Bearer"), (String) null, (String) null, false, (zze) null, this.zza, this);
    }
}

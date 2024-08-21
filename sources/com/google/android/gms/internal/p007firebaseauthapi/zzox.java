package com.google.android.gms.internal.p007firebaseauthapi;

import com.google.firebase.auth.internal.zzai;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzox */
/* compiled from: com.google.firebase:firebase-auth@@20.0.1 */
final class zzox implements zzup<zzwg> {
    final /* synthetic */ zztb zza;
    final /* synthetic */ zzpj zzb;

    zzox(zzpj zzpj, zztb zztb) {
        this.zzb = zzpj;
        this.zza = zztb;
    }

    public final void zza(String str) {
        this.zza.zzk(zzai.zza(str));
    }

    public final /* bridge */ /* synthetic */ void zzb(Object obj) {
        zzwg zzwg = (zzwg) obj;
        this.zzb.zza.zzh(new zzvw(zzwg.zze()), new zzow(this, this, zzwg));
    }
}

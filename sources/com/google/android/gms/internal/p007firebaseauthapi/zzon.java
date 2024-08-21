package com.google.android.gms.internal.p007firebaseauthapi;

import com.google.firebase.auth.internal.zzai;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzon */
/* compiled from: com.google.firebase:firebase-auth@@20.0.1 */
final class zzon implements zzup<zzwg> {
    final /* synthetic */ String zza;
    final /* synthetic */ zztb zzb;
    final /* synthetic */ zzpj zzc;

    zzon(zzpj zzpj, String str, zztb zztb) {
        this.zzc = zzpj;
        this.zza = str;
        this.zzb = zztb;
    }

    public final void zza(String str) {
        this.zzb.zzk(zzai.zza(str));
    }

    public final /* bridge */ /* synthetic */ void zzb(Object obj) {
        zzwg zzwg = (zzwg) obj;
        this.zzc.zza.zzh(new zzvw(zzwg.zze()), new zzom(this, this, zzwg));
    }
}

package com.google.android.gms.internal.p007firebaseauthapi;

import com.google.firebase.auth.internal.zzai;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzog */
/* compiled from: com.google.firebase:firebase-auth@@20.0.1 */
final class zzog implements zzup<zzwg> {
    final /* synthetic */ String zza;
    final /* synthetic */ String zzb;
    final /* synthetic */ zztb zzc;
    final /* synthetic */ zzpj zzd;

    zzog(zzpj zzpj, String str, String str2, zztb zztb) {
        this.zzd = zzpj;
        this.zza = str;
        this.zzb = str2;
        this.zzc = zztb;
    }

    public final void zza(String str) {
        this.zzc.zzk(zzai.zza(str));
    }

    public final /* bridge */ /* synthetic */ void zzb(Object obj) {
        zzwg zzwg = (zzwg) obj;
        zzww zzww = new zzww();
        zzww.zzg(zzwg.zze());
        zzww.zzh(this.zza);
        zzww.zzi(this.zzb);
        zzpj.zzI(this.zzd, this.zzc, zzwg, zzww, this);
    }
}

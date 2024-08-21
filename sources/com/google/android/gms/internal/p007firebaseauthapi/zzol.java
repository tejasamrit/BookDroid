package com.google.android.gms.internal.p007firebaseauthapi;

import com.google.firebase.auth.internal.zzai;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzol */
/* compiled from: com.google.firebase:firebase-auth@@20.0.1 */
final class zzol implements zzup<zzwg> {
    final /* synthetic */ zztb zza;
    final /* synthetic */ zzpj zzb;

    zzol(zzpj zzpj, zztb zztb) {
        this.zzb = zzpj;
        this.zza = zztb;
    }

    public final void zza(String str) {
        this.zza.zzk(zzai.zza(str));
    }

    public final /* bridge */ /* synthetic */ void zzb(Object obj) {
        zzwg zzwg = (zzwg) obj;
        zzww zzww = new zzww();
        zzww.zzg(zzwg.zze());
        zzww.zzh((String) null);
        zzww.zzi((String) null);
        zzpj.zzI(this.zzb, this.zza, zzwg, zzww, this);
    }
}

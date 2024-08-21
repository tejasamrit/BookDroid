package com.google.android.gms.internal.p007firebaseauthapi;

import java.util.List;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zznw */
/* compiled from: com.google.firebase:firebase-auth@@20.0.1 */
final class zznw implements zzup<zzvx> {
    final /* synthetic */ zzuo zza;
    final /* synthetic */ zztb zzb;
    final /* synthetic */ zzwg zzc;
    final /* synthetic */ zzww zzd;
    final /* synthetic */ zzpj zze;

    zznw(zzpj zzpj, zzuo zzuo, zztb zztb, zzwg zzwg, zzww zzww) {
        this.zze = zzpj;
        this.zza = zzuo;
        this.zzb = zztb;
        this.zzc = zzwg;
        this.zzd = zzww;
    }

    public final void zza(String str) {
        this.zza.zza(str);
    }

    public final /* bridge */ /* synthetic */ void zzb(Object obj) {
        List<zzvz> zzb2 = ((zzvx) obj).zzb();
        if (zzb2 == null || zzb2.isEmpty()) {
            this.zza.zza("No users");
        } else {
            zzpj.zzK(this.zze, this.zzb, this.zzc, zzb2.get(0), this.zzd, this.zza);
        }
    }
}

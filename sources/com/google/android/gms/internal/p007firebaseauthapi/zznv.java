package com.google.android.gms.internal.p007firebaseauthapi;

import com.google.firebase.auth.internal.zzai;
import com.google.firebase.auth.zze;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zznv */
/* compiled from: com.google.firebase:firebase-auth@@20.0.1 */
final class zznv implements zzup<zzvp> {
    final /* synthetic */ zztb zza;
    final /* synthetic */ zzpj zzb;

    zznv(zzpj zzpj, zztb zztb) {
        this.zzb = zzpj;
        this.zza = zztb;
    }

    public final void zza(String str) {
        this.zza.zzk(zzai.zza(str));
    }

    public final /* bridge */ /* synthetic */ void zzb(Object obj) {
        zzvp zzvp = (zzvp) obj;
        if (zzvp.zzh()) {
            this.zza.zzo(new zznq(zzvp.zzg(), zzvp.zzf(), (zze) null));
            return;
        }
        this.zzb.zzO(new zzwg(zzvp.zzc(), zzvp.zzb(), Long.valueOf(zzvp.zze()), "Bearer"), (String) null, (String) null, Boolean.valueOf(zzvp.zzd()), (zze) null, this.zza, this);
    }
}

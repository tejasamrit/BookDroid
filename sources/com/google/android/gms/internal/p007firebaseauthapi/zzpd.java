package com.google.android.gms.internal.p007firebaseauthapi;

import com.google.firebase.auth.internal.zzai;
import com.google.firebase.auth.zze;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzpd */
/* compiled from: com.google.firebase:firebase-auth@@20.0.1 */
final class zzpd implements zzup<zzwz> {
    final /* synthetic */ zztb zza;
    final /* synthetic */ zzpj zzb;

    zzpd(zzpj zzpj, zztb zztb) {
        this.zzb = zzpj;
        this.zza = zztb;
    }

    public final void zza(String str) {
        this.zza.zzk(zzai.zza(str));
    }

    public final /* bridge */ /* synthetic */ void zzb(Object obj) {
        zzwz zzwz = (zzwz) obj;
        this.zzb.zzO(new zzwg(zzwz.zzc(), zzwz.zzb(), Long.valueOf(zzwz.zzd()), "Bearer"), (String) null, (String) null, true, (zze) null, this.zza, this);
    }
}

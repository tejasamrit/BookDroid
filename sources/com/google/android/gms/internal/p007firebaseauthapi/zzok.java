package com.google.android.gms.internal.p007firebaseauthapi;

import android.content.Context;
import com.google.firebase.auth.internal.zzai;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzok */
/* compiled from: com.google.firebase:firebase-auth@@20.0.1 */
final class zzok implements zzup<zzwg> {
    final /* synthetic */ zzxg zza;
    final /* synthetic */ zztb zzb;
    final /* synthetic */ zzpj zzc;

    zzok(zzpj zzpj, zzxg zzxg, zztb zztb) {
        this.zzc = zzpj;
        this.zza = zzxg;
        this.zzb = zztb;
    }

    public final void zza(String str) {
        this.zzb.zzk(zzai.zza(str));
    }

    public final /* bridge */ /* synthetic */ void zzb(Object obj) {
        this.zza.zzf(true);
        this.zza.zzb(((zzwg) obj).zze());
        this.zzc.zza.zzd((Context) null, this.zza, new zzoj(this, this));
    }
}

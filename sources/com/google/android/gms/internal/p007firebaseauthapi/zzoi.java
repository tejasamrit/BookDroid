package com.google.android.gms.internal.p007firebaseauthapi;

import android.content.Context;
import com.google.firebase.auth.internal.zzai;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzoi */
/* compiled from: com.google.firebase:firebase-auth@@20.0.1 */
final class zzoi implements zzup<zzwg> {
    final /* synthetic */ zzxo zza;
    final /* synthetic */ zztb zzb;
    final /* synthetic */ zzpj zzc;

    zzoi(zzpj zzpj, zzxo zzxo, Context context, zztb zztb) {
        this.zzc = zzpj;
        this.zza = zzxo;
        this.zzb = zztb;
    }

    public final void zza(String str) {
        this.zzb.zzk(zzai.zza(str));
    }

    public final /* bridge */ /* synthetic */ void zzb(Object obj) {
        this.zza.zzd(((zzwg) obj).zze());
        this.zzc.zza.zzm((Context) null, this.zza, new zzoh(this, this));
    }
}

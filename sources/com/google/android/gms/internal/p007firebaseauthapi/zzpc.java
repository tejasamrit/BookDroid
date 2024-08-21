package com.google.android.gms.internal.p007firebaseauthapi;

import com.google.firebase.auth.internal.zzai;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzpc */
/* compiled from: com.google.firebase:firebase-auth@@20.0.1 */
final class zzpc implements zzup<zzxi> {
    final /* synthetic */ zztb zza;
    final /* synthetic */ zzpj zzb;

    zzpc(zzpj zzpj, zztb zztb) {
        this.zzb = zzpj;
        this.zza = zztb;
    }

    public final void zza(String str) {
        this.zza.zzk(zzai.zza(str));
    }

    public final /* bridge */ /* synthetic */ void zzb(Object obj) {
        zzxi zzxi = (zzxi) obj;
        if (!zzxi.zzo()) {
            zzpj.zzH(this.zzb, zzxi, this.zza, this);
        } else {
            this.zza.zzo(new zznq(zzxi.zzn(), zzxi.zzm(), zzxi.zzp()));
        }
    }
}

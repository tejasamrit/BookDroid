package com.google.android.gms.internal.p007firebaseauthapi;

import com.google.firebase.auth.internal.zzai;
import com.google.firebase.auth.zze;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzor */
/* compiled from: com.google.firebase:firebase-auth@@20.0.1 */
final class zzor implements zzup<zzxk> {
    final /* synthetic */ zztb zza;
    final /* synthetic */ zzpj zzb;

    zzor(zzpj zzpj, zztb zztb) {
        this.zzb = zzpj;
        this.zza = zztb;
    }

    public final void zza(String str) {
        this.zza.zzk(zzai.zza(str));
    }

    public final /* bridge */ /* synthetic */ void zzb(Object obj) {
        zzxk zzxk = (zzxk) obj;
        this.zzb.zzO(new zzwg(zzxk.zzc(), zzxk.zzb(), Long.valueOf(zzxk.zzd()), "Bearer"), (String) null, (String) null, Boolean.valueOf(zzxk.zze()), (zze) null, this.zza, this);
    }
}

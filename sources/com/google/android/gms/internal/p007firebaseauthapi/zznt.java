package com.google.android.gms.internal.p007firebaseauthapi;

import com.google.firebase.auth.internal.zzai;
import com.google.firebase.auth.zze;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zznt */
/* compiled from: com.google.firebase:firebase-auth@@20.0.1 */
final class zznt implements zzup<zzxn> {
    final /* synthetic */ zztb zza;
    final /* synthetic */ zzpj zzb;

    zznt(zzpj zzpj, zztb zztb) {
        this.zzb = zzpj;
        this.zza = zztb;
    }

    public final void zza(String str) {
        this.zza.zzk(zzai.zza(str));
    }

    public final /* bridge */ /* synthetic */ void zzb(Object obj) {
        zzxn zzxn = (zzxn) obj;
        if (zzxn.zzg()) {
            this.zza.zzo(new zznq(zzxn.zzf(), zzxn.zze(), (zze) null));
            return;
        }
        this.zzb.zzO(new zzwg(zzxn.zzc(), zzxn.zzb(), Long.valueOf(zzxn.zzd()), "Bearer"), (String) null, (String) null, false, (zze) null, this.zza, this);
    }
}

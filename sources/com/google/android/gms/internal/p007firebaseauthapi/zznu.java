package com.google.android.gms.internal.p007firebaseauthapi;

import com.google.firebase.auth.EmailAuthCredential;
import com.google.firebase.auth.internal.zzai;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zznu */
/* compiled from: com.google.firebase:firebase-auth@@20.0.1 */
final class zznu implements zzup<zzwg> {
    final /* synthetic */ EmailAuthCredential zza;
    final /* synthetic */ zztb zzb;
    final /* synthetic */ zzpj zzc;

    zznu(zzpj zzpj, EmailAuthCredential emailAuthCredential, zztb zztb) {
        this.zzc = zzpj;
        this.zza = emailAuthCredential;
        this.zzb = zztb;
    }

    public final void zza(String str) {
        this.zzb.zzk(zzai.zza(str));
    }

    public final /* bridge */ /* synthetic */ void zzb(Object obj) {
        this.zzc.zzN(new zzvo(this.zza, ((zzwg) obj).zze()), this.zzb);
    }
}

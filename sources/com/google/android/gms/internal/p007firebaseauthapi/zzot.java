package com.google.android.gms.internal.p007firebaseauthapi;

import android.content.Context;
import com.google.firebase.auth.internal.zzai;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzot */
/* compiled from: com.google.firebase:firebase-auth@@20.0.1 */
final class zzot implements zzup<zzwg> {
    final /* synthetic */ zzvq zza;
    final /* synthetic */ zztb zzb;
    final /* synthetic */ zzpj zzc;

    zzot(zzpj zzpj, zzvq zzvq, Context context, zztb zztb) {
        this.zzc = zzpj;
        this.zza = zzvq;
        this.zzb = zztb;
    }

    public final void zza(String str) {
        this.zzb.zzk(zzai.zza(str));
    }

    public final /* bridge */ /* synthetic */ void zzb(Object obj) {
        this.zza.zzc(((zzwg) obj).zze());
        this.zzc.zza.zzr((Context) null, this.zza, new zzos(this));
    }
}

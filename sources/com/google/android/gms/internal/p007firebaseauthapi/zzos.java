package com.google.android.gms.internal.p007firebaseauthapi;

import com.google.firebase.auth.internal.zzai;
import com.google.firebase.auth.zze;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzos */
/* compiled from: com.google.firebase:firebase-auth@@20.0.1 */
final class zzos implements zzup<zzvr> {
    final /* synthetic */ zzot zza;

    zzos(zzot zzot) {
        this.zza = zzot;
    }

    public final void zza(String str) {
        this.zza.zzb.zzk(zzai.zza(str));
    }

    public final /* bridge */ /* synthetic */ void zzb(Object obj) {
        zzvr zzvr = (zzvr) obj;
        this.zza.zzc.zzO(new zzwg(zzvr.zzc(), zzvr.zzb(), Long.valueOf(zzwi.zza(zzvr.zzb())), "Bearer"), (String) null, (String) null, false, (zze) null, this.zza.zzb, this);
    }
}

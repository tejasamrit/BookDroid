package com.google.android.gms.internal.p007firebaseauthapi;

import java.util.List;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzow */
/* compiled from: com.google.firebase:firebase-auth@@20.0.1 */
final class zzow implements zzup<zzvx> {
    final /* synthetic */ zzup zza;
    final /* synthetic */ zzwg zzb;
    final /* synthetic */ zzox zzc;

    zzow(zzox zzox, zzup zzup, zzwg zzwg) {
        this.zzc = zzox;
        this.zza = zzup;
        this.zzb = zzwg;
    }

    public final void zza(String str) {
        this.zza.zza(str);
    }

    public final /* bridge */ /* synthetic */ void zzb(Object obj) {
        List<zzvz> zzb2 = ((zzvx) obj).zzb();
        if (zzb2 == null || zzb2.isEmpty()) {
            this.zza.zza("No users");
        } else {
            this.zzc.zza.zzb(this.zzb, zzb2.get(0));
        }
    }
}

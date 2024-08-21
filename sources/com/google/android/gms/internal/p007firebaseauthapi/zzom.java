package com.google.android.gms.internal.p007firebaseauthapi;

import com.google.firebase.auth.internal.zzai;
import java.util.List;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzom */
/* compiled from: com.google.firebase:firebase-auth@@20.0.1 */
final class zzom implements zzup<zzvx> {
    final /* synthetic */ zzup zza;
    final /* synthetic */ zzwg zzb;
    final /* synthetic */ zzon zzc;

    zzom(zzon zzon, zzup zzup, zzwg zzwg) {
        this.zzc = zzon;
        this.zza = zzup;
        this.zzb = zzwg;
    }

    public final void zza(String str) {
        this.zzc.zzb.zzk(zzai.zza(str));
    }

    public final /* bridge */ /* synthetic */ void zzb(Object obj) {
        List<zzvz> zzb2 = ((zzvx) obj).zzb();
        if (zzb2 == null || zzb2.isEmpty()) {
            this.zza.zza("No users.");
            return;
        }
        zzww zzww = new zzww();
        zzww.zzg(this.zzb.zze());
        zzww.zzl(this.zzc.zza);
        zzon zzon = this.zzc;
        zzpj.zzK(zzon.zzc, zzon.zzb, this.zzb, zzb2.get(0), zzww, this.zza);
    }
}

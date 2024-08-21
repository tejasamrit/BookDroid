package com.google.android.gms.internal.p007firebaseauthapi;

import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.auth.internal.zzai;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzpe */
/* compiled from: com.google.firebase:firebase-auth@@20.0.1 */
final class zzpe implements zzup<zzwg> {
    final /* synthetic */ UserProfileChangeRequest zza;
    final /* synthetic */ zztb zzb;
    final /* synthetic */ zzpj zzc;

    zzpe(zzpj zzpj, UserProfileChangeRequest userProfileChangeRequest, zztb zztb) {
        this.zzc = zzpj;
        this.zza = userProfileChangeRequest;
        this.zzb = zztb;
    }

    public final void zza(String str) {
        this.zzb.zzk(zzai.zza(str));
    }

    public final /* bridge */ /* synthetic */ void zzb(Object obj) {
        zzwg zzwg = (zzwg) obj;
        zzww zzww = new zzww();
        zzww.zzg(zzwg.zze());
        if (this.zza.zzb() || this.zza.getDisplayName() != null) {
            zzww.zzj(this.zza.getDisplayName());
        }
        if (this.zza.zzc() || this.zza.getPhotoUri() != null) {
            zzww.zzk(this.zza.zza());
        }
        zzpj.zzI(this.zzc, this.zzb, zzwg, zzww, this);
    }
}

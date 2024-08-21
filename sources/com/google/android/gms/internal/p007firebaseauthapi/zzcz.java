package com.google.android.gms.internal.p007firebaseauthapi;

import java.security.GeneralSecurityException;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzcz */
/* compiled from: com.google.firebase:firebase-auth@@20.0.1 */
final class zzcz extends zzas<zzgq> {
    public zzcz() {
        super(zzgq.class, new zzcy(zzal.class));
    }

    public final String zzb() {
        return "type.googleapis.com/google.crypto.tink.EciesAeadHkdfPublicKey";
    }

    public final zzhn zzc() {
        return zzhn.ASYMMETRIC_PUBLIC;
    }

    public final /* bridge */ /* synthetic */ zzaar zzd(zzym zzym) throws zzzw {
        return zzgq.zze(zzym, zzzb.zza());
    }

    public final /* bridge */ /* synthetic */ void zze(zzaar zzaar) throws GeneralSecurityException {
        zzgq zzgq = (zzgq) zzaar;
        zzkr.zzb(zzgq.zza(), 0);
        zzdg.zza(zzgq.zzb());
    }
}

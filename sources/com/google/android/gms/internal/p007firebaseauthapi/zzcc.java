package com.google.android.gms.internal.p007firebaseauthapi;

import java.security.GeneralSecurityException;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzcc */
/* compiled from: com.google.firebase:firebase-auth@@20.0.1 */
public final class zzcc extends zzas<zzfk> {
    zzcc() {
        super(zzfk.class, new zzca(zzag.class));
    }

    public final String zzb() {
        return "type.googleapis.com/google.crypto.tink.AesGcmKey";
    }

    public final zzhn zzc() {
        return zzhn.SYMMETRIC;
    }

    public final /* bridge */ /* synthetic */ zzaar zzd(zzym zzym) throws zzzw {
        return zzfk.zzc(zzym, zzzb.zza());
    }

    public final /* bridge */ /* synthetic */ void zze(zzaar zzaar) throws GeneralSecurityException {
        zzfk zzfk = (zzfk) zzaar;
        zzkr.zzb(zzfk.zza(), 0);
        zzkr.zza(zzfk.zzb().zzc());
    }

    public final zzaq<zzfn, zzfk> zzi() {
        return new zzcb(this, zzfn.class);
    }
}

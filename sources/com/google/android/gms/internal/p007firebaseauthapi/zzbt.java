package com.google.android.gms.internal.p007firebaseauthapi;

import java.security.GeneralSecurityException;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzbt */
/* compiled from: com.google.firebase:firebase-auth@@20.0.1 */
public final class zzbt extends zzas<zzem> {
    zzbt() {
        super(zzem.class, new zzbr(zzag.class));
    }

    public final String zzb() {
        return "type.googleapis.com/google.crypto.tink.AesCtrHmacAeadKey";
    }

    public final zzhn zzc() {
        return zzhn.SYMMETRIC;
    }

    public final /* bridge */ /* synthetic */ zzaar zzd(zzym zzym) throws zzzw {
        return zzem.zzd(zzym, zzzb.zza());
    }

    public final /* bridge */ /* synthetic */ void zze(zzaar zzaar) throws GeneralSecurityException {
        zzem zzem = (zzem) zzaar;
        zzkr.zzb(zzem.zza(), 0);
        new zzbw();
        zzbw.zzk(zzem.zzb());
        new zzdv();
        zzdv.zzk(zzem.zzc());
    }

    public final zzaq<zzep, zzem> zzi() {
        return new zzbs(this, zzep.class);
    }
}

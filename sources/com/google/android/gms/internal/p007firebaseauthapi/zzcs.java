package com.google.android.gms.internal.p007firebaseauthapi;

import java.security.GeneralSecurityException;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzcs */
/* compiled from: com.google.firebase:firebase-auth@@20.0.1 */
public final class zzcs extends zzas<zzja> {
    zzcs() {
        super(zzja.class, new zzcq(zzag.class));
    }

    public final String zzb() {
        return "type.googleapis.com/google.crypto.tink.XChaCha20Poly1305Key";
    }

    public final zzhn zzc() {
        return zzhn.SYMMETRIC;
    }

    public final /* bridge */ /* synthetic */ zzaar zzd(zzym zzym) throws zzzw {
        return zzja.zzc(zzym, zzzb.zza());
    }

    public final /* bridge */ /* synthetic */ void zze(zzaar zzaar) throws GeneralSecurityException {
        zzja zzja = (zzja) zzaar;
        zzkr.zzb(zzja.zza(), 0);
        if (zzja.zzb().zzc() != 32) {
            throw new GeneralSecurityException("invalid XChaCha20Poly1305Key: incorrect key length");
        }
    }

    public final zzaq<zzjd, zzja> zzi() {
        return new zzcr(this, zzjd.class);
    }
}

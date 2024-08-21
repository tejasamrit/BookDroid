package com.google.android.gms.internal.p007firebaseauthapi;

import java.security.GeneralSecurityException;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzci */
/* compiled from: com.google.firebase:firebase-auth@@20.0.1 */
public final class zzci extends zzas<zzfw> {
    zzci() {
        super(zzfw.class, new zzcg(zzag.class));
    }

    public final String zzb() {
        return "type.googleapis.com/google.crypto.tink.ChaCha20Poly1305Key";
    }

    public final zzhn zzc() {
        return zzhn.SYMMETRIC;
    }

    public final /* bridge */ /* synthetic */ zzaar zzd(zzym zzym) throws zzzw {
        return zzfw.zzc(zzym, zzzb.zza());
    }

    public final /* bridge */ /* synthetic */ void zze(zzaar zzaar) throws GeneralSecurityException {
        zzfw zzfw = (zzfw) zzaar;
        zzkr.zzb(zzfw.zza(), 0);
        if (zzfw.zzb().zzc() != 32) {
            throw new GeneralSecurityException("invalid ChaCha20Poly1305Key: incorrect key length");
        }
    }

    public final zzaq<zzfz, zzfw> zzi() {
        return new zzch(this, zzfz.class);
    }
}

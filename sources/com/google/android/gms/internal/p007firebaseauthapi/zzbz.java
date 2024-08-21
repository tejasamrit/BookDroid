package com.google.android.gms.internal.p007firebaseauthapi;

import java.security.GeneralSecurityException;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzbz */
/* compiled from: com.google.firebase:firebase-auth@@20.0.1 */
public final class zzbz extends zzas<zzfb> {
    zzbz() {
        super(zzfb.class, new zzbx(zzag.class));
    }

    public final String zzb() {
        return "type.googleapis.com/google.crypto.tink.AesEaxKey";
    }

    public final zzhn zzc() {
        return zzhn.SYMMETRIC;
    }

    public final /* bridge */ /* synthetic */ zzaar zzd(zzym zzym) throws zzzw {
        return zzfb.zzd(zzym, zzzb.zza());
    }

    public final /* bridge */ /* synthetic */ void zze(zzaar zzaar) throws GeneralSecurityException {
        zzfb zzfb = (zzfb) zzaar;
        zzkr.zzb(zzfb.zza(), 0);
        zzkr.zza(zzfb.zzc().zzc());
        if (zzfb.zzb().zza() != 12 && zzfb.zzb().zza() != 16) {
            throw new GeneralSecurityException("invalid IV size; acceptable values have 12 or 16 bytes");
        }
    }

    public final zzaq<zzfe, zzfb> zzi() {
        return new zzby(this, zzfe.class);
    }
}

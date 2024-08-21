package com.google.android.gms.internal.p007firebaseauthapi;

import java.security.GeneralSecurityException;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzcx */
/* compiled from: com.google.firebase:firebase-auth@@20.0.1 */
public final class zzcx extends zzbf<zzgn, zzgq> {
    zzcx() {
        super(zzgn.class, zzgq.class, new zzcv(zzak.class));
    }

    public static final void zzj(zzgn zzgn) throws GeneralSecurityException {
        if (zzgn.zzc().zzc() != 0) {
            zzkr.zzb(zzgn.zza(), 0);
            zzdg.zza(zzgn.zzb().zzb());
            return;
        }
        throw new GeneralSecurityException("invalid ECIES private key");
    }

    public final String zzb() {
        return "type.googleapis.com/google.crypto.tink.EciesAeadHkdfPrivateKey";
    }

    public final zzhn zzc() {
        return zzhn.ASYMMETRIC_PRIVATE;
    }

    public final /* bridge */ /* synthetic */ zzaar zzd(zzym zzym) throws zzzw {
        return zzgn.zzd(zzym, zzzb.zza());
    }

    public final /* bridge */ /* synthetic */ void zze(zzaar zzaar) throws GeneralSecurityException {
        zzj((zzgn) zzaar);
    }

    public final zzaq<zzgh, zzgn> zzi() {
        return new zzcw(this, zzgh.class);
    }
}

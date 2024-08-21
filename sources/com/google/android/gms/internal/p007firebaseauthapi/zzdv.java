package com.google.android.gms.internal.p007firebaseauthapi;

import java.security.GeneralSecurityException;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzdv */
/* compiled from: com.google.firebase:firebase-auth@@20.0.1 */
public final class zzdv extends zzas<zzhd> {
    public zzdv() {
        super(zzhd.class, new zzdt(zzay.class));
    }

    public static final void zzk(zzhd zzhd) throws GeneralSecurityException {
        zzkr.zzb(zzhd.zza(), 0);
        if (zzhd.zzc().zzc() >= 16) {
            zzl(zzhd.zzb());
            return;
        }
        throw new GeneralSecurityException("key too short");
    }

    /* access modifiers changed from: private */
    public static void zzl(zzhj zzhj) throws GeneralSecurityException {
        if (zzhj.zzb() >= 10) {
            zzha zzha = zzha.UNKNOWN_HASH;
            int ordinal = zzhj.zza().ordinal();
            if (ordinal != 1) {
                if (ordinal != 3) {
                    if (ordinal != 4) {
                        throw new GeneralSecurityException("unknown hash type");
                    } else if (zzhj.zzb() > 64) {
                        throw new GeneralSecurityException("tag size too big");
                    }
                } else if (zzhj.zzb() > 32) {
                    throw new GeneralSecurityException("tag size too big");
                }
            } else if (zzhj.zzb() > 20) {
                throw new GeneralSecurityException("tag size too big");
            }
        } else {
            throw new GeneralSecurityException("tag size too small");
        }
    }

    public final String zzb() {
        return "type.googleapis.com/google.crypto.tink.HmacKey";
    }

    public final zzhn zzc() {
        return zzhn.SYMMETRIC;
    }

    public final /* bridge */ /* synthetic */ zzaar zzd(zzym zzym) throws zzzw {
        return zzhd.zzd(zzym, zzzb.zza());
    }

    public final /* bridge */ /* synthetic */ void zze(zzaar zzaar) throws GeneralSecurityException {
        zzk((zzhd) zzaar);
    }

    public final zzaq<zzhg, zzhd> zzi() {
        return new zzdu(this, zzhg.class);
    }
}

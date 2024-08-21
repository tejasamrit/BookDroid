package com.google.android.gms.internal.p007firebaseauthapi;

import java.security.GeneralSecurityException;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzds */
/* compiled from: com.google.firebase:firebase-auth@@20.0.1 */
public final class zzds extends zzas<zzed> {
    zzds() {
        super(zzed.class, new zzdq(zzay.class));
    }

    /* access modifiers changed from: private */
    public static void zzl(zzej zzej) throws GeneralSecurityException {
        if (zzej.zza() < 10) {
            throw new GeneralSecurityException("tag size too short");
        } else if (zzej.zza() > 16) {
            throw new GeneralSecurityException("tag size too long");
        }
    }

    /* access modifiers changed from: private */
    public static void zzm(int i) throws GeneralSecurityException {
        if (i != 32) {
            throw new GeneralSecurityException("AesCmacKey size wrong, must be 32 bytes");
        }
    }

    public final String zzb() {
        return "type.googleapis.com/google.crypto.tink.AesCmacKey";
    }

    public final zzhn zzc() {
        return zzhn.SYMMETRIC;
    }

    public final /* bridge */ /* synthetic */ zzaar zzd(zzym zzym) throws zzzw {
        return zzed.zzd(zzym, zzzb.zza());
    }

    public final /* bridge */ /* synthetic */ void zze(zzaar zzaar) throws GeneralSecurityException {
        zzed zzed = (zzed) zzaar;
        zzkr.zzb(zzed.zza(), 0);
        zzm(zzed.zzb().zzc());
        zzl(zzed.zzc());
    }

    public final zzaq<zzeg, zzed> zzi() {
        return new zzdr(this, zzeg.class);
    }
}

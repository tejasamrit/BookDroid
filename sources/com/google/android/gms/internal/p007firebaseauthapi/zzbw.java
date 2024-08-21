package com.google.android.gms.internal.p007firebaseauthapi;

import java.security.GeneralSecurityException;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzbw */
/* compiled from: com.google.firebase:firebase-auth@@20.0.1 */
public final class zzbw extends zzas<zzes> {
    zzbw() {
        super(zzes.class, new zzbu(zzki.class));
    }

    public static final void zzk(zzes zzes) throws GeneralSecurityException {
        zzkr.zzb(zzes.zza(), 0);
        zzkr.zza(zzes.zzc().zzc());
        zzl(zzes.zzb());
    }

    /* access modifiers changed from: private */
    public static final void zzl(zzey zzey) throws GeneralSecurityException {
        if (zzey.zza() < 12 || zzey.zza() > 16) {
            throw new GeneralSecurityException("invalid IV size");
        }
    }

    public final String zzb() {
        return "type.googleapis.com/google.crypto.tink.AesCtrKey";
    }

    public final zzhn zzc() {
        return zzhn.SYMMETRIC;
    }

    public final /* bridge */ /* synthetic */ zzaar zzd(zzym zzym) throws zzzw {
        return zzes.zzd(zzym, zzzb.zza());
    }

    public final /* bridge */ /* synthetic */ void zze(zzaar zzaar) throws GeneralSecurityException {
        zzk((zzes) zzaar);
    }

    public final zzaq<zzev, zzes> zzi() {
        return new zzbv(this, zzev.class);
    }
}

package com.google.android.gms.internal.p007firebaseauthapi;

import java.security.GeneralSecurityException;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzby */
/* compiled from: com.google.firebase:firebase-auth@@20.0.1 */
final class zzby extends zzaq<zzfe, zzfb> {
    final /* synthetic */ zzbz zza;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzby(zzbz zzbz, Class cls) {
        super(cls);
        this.zza = zzbz;
    }

    public final /* bridge */ /* synthetic */ void zzb(zzaar zzaar) throws GeneralSecurityException {
        zzfe zzfe = (zzfe) zzaar;
        zzkr.zza(zzfe.zzb());
        if (zzfe.zza().zza() != 12 && zzfe.zza().zza() != 16) {
            throw new GeneralSecurityException("invalid IV size; acceptable values have 12 or 16 bytes");
        }
    }

    public final /* bridge */ /* synthetic */ zzaar zzc(zzym zzym) throws zzzw {
        return zzfe.zzc(zzym, zzzb.zza());
    }

    public final /* bridge */ /* synthetic */ Object zzd(zzaar zzaar) throws GeneralSecurityException {
        zzfe zzfe = (zzfe) zzaar;
        zzfa zze = zzfb.zze();
        zze.zzc(zzym.zzm(zzkp.zza(zzfe.zzb())));
        zze.zzb(zzfe.zza());
        zze.zza(0);
        return (zzfb) zze.zzl();
    }
}

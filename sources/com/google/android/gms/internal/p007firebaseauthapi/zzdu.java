package com.google.android.gms.internal.p007firebaseauthapi;

import java.security.GeneralSecurityException;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzdu */
/* compiled from: com.google.firebase:firebase-auth@@20.0.1 */
final class zzdu extends zzaq<zzhg, zzhd> {
    final /* synthetic */ zzdv zza;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzdu(zzdv zzdv, Class cls) {
        super(cls);
        this.zza = zzdv;
    }

    public final /* bridge */ /* synthetic */ void zzb(zzaar zzaar) throws GeneralSecurityException {
        zzhg zzhg = (zzhg) zzaar;
        if (zzhg.zzb() >= 16) {
            zzdv.zzl(zzhg.zza());
            return;
        }
        throw new GeneralSecurityException("key too short");
    }

    public final /* bridge */ /* synthetic */ zzaar zzc(zzym zzym) throws zzzw {
        return zzhg.zzc(zzym, zzzb.zza());
    }

    public final /* bridge */ /* synthetic */ Object zzd(zzaar zzaar) throws GeneralSecurityException {
        zzhg zzhg = (zzhg) zzaar;
        zzhc zze = zzhd.zze();
        zze.zza(0);
        zze.zzb(zzhg.zza());
        zze.zzc(zzym.zzm(zzkp.zza(zzhg.zzb())));
        return (zzhd) zze.zzl();
    }
}

package com.google.android.gms.internal.p007firebaseauthapi;

import java.security.GeneralSecurityException;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzbs */
/* compiled from: com.google.firebase:firebase-auth@@20.0.1 */
final class zzbs extends zzaq<zzep, zzem> {
    final /* synthetic */ zzbt zza;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzbs(zzbt zzbt, Class cls) {
        super(cls);
        this.zza = zzbt;
    }

    public final /* bridge */ /* synthetic */ void zzb(zzaar zzaar) throws GeneralSecurityException {
        zzep zzep = (zzep) zzaar;
        ((zzbv) new zzbw().zzi()).zzb(zzep.zza());
        new zzdv().zzi().zzb(zzep.zzb());
        zzkr.zza(zzep.zza().zzb());
    }

    public final /* bridge */ /* synthetic */ zzaar zzc(zzym zzym) throws zzzw {
        return zzep.zzc(zzym, zzzb.zza());
    }

    public final /* bridge */ /* synthetic */ Object zzd(zzaar zzaar) throws GeneralSecurityException {
        zzep zzep = (zzep) zzaar;
        new zzbw();
        zzes zzf = zzbv.zzf(zzep.zza());
        Object zzd = new zzdv().zzi().zzd(zzep.zzb());
        zzel zze = zzem.zze();
        zze.zzb(zzf);
        zze.zzc((zzhd) zzd);
        zze.zza(0);
        return (zzem) zze.zzl();
    }
}

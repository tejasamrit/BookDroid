package com.google.android.gms.internal.p007firebaseauthapi;

import java.security.GeneralSecurityException;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzbv */
/* compiled from: com.google.firebase:firebase-auth@@20.0.1 */
final class zzbv extends zzaq<zzev, zzes> {
    final /* synthetic */ zzbw zza;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzbv(zzbw zzbw, Class cls) {
        super(cls);
        this.zza = zzbw;
    }

    public static final zzes zzf(zzev zzev) throws GeneralSecurityException {
        zzer zze = zzes.zze();
        zze.zzb(zzev.zza());
        zze.zzc(zzym.zzm(zzkp.zza(zzev.zzb())));
        zze.zza(0);
        return (zzes) zze.zzl();
    }

    public final /* bridge */ /* synthetic */ zzaar zzc(zzym zzym) throws zzzw {
        return zzev.zzc(zzym, zzzb.zza());
    }

    public final /* bridge */ /* synthetic */ Object zzd(zzaar zzaar) throws GeneralSecurityException {
        return zzf((zzev) zzaar);
    }

    /* renamed from: zze */
    public final void zzb(zzev zzev) throws GeneralSecurityException {
        zzkr.zza(zzev.zzb());
        zzbw.zzl(zzev.zza());
    }
}

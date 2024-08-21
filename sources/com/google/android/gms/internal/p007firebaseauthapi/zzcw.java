package com.google.android.gms.internal.p007firebaseauthapi;

import java.security.GeneralSecurityException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.interfaces.ECPrivateKey;
import java.security.interfaces.ECPublicKey;
import java.security.spec.ECParameterSpec;
import java.security.spec.ECPoint;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzcw */
/* compiled from: com.google.firebase:firebase-auth@@20.0.1 */
final class zzcw extends zzaq<zzgh, zzgn> {
    final /* synthetic */ zzcx zza;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzcw(zzcx zzcx, Class cls) {
        super(cls);
        this.zza = zzcx;
    }

    public final /* bridge */ /* synthetic */ void zzb(zzaar zzaar) throws GeneralSecurityException {
        zzdg.zza(((zzgh) zzaar).zza());
    }

    public final /* bridge */ /* synthetic */ zzaar zzc(zzym zzym) throws zzzw {
        return zzgh.zzb(zzym, zzzb.zza());
    }

    public final /* bridge */ /* synthetic */ Object zzd(zzaar zzaar) throws GeneralSecurityException {
        zzgh zzgh = (zzgh) zzaar;
        ECParameterSpec zze = zzjw.zze(zzdg.zzc(zzgh.zza().zza().zza()));
        KeyPairGenerator zza2 = zzjy.zzf.zza("EC");
        zza2.initialize(zze);
        KeyPair generateKeyPair = zza2.generateKeyPair();
        ECPoint w = ((ECPublicKey) generateKeyPair.getPublic()).getW();
        zzgp zzf = zzgq.zzf();
        zzf.zza(0);
        zzf.zzb(zzgh.zza());
        zzf.zzc(zzym.zzm(w.getAffineX().toByteArray()));
        zzf.zzd(zzym.zzm(w.getAffineY().toByteArray()));
        zzgm zze2 = zzgn.zze();
        zze2.zza(0);
        zze2.zzb((zzgq) zzf.zzl());
        zze2.zzc(zzym.zzm(((ECPrivateKey) generateKeyPair.getPrivate()).getS().toByteArray()));
        return (zzgn) zze2.zzl();
    }
}

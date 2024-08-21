package com.google.android.gms.internal.p007firebaseauthapi;

import java.math.BigInteger;
import java.security.GeneralSecurityException;
import java.security.interfaces.ECPrivateKey;
import java.security.spec.ECPrivateKeySpec;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzcv */
/* compiled from: com.google.firebase:firebase-auth@@20.0.1 */
final class zzcv extends zzar<zzak, zzgn> {
    zzcv(Class cls) {
        super(cls);
    }

    public final /* bridge */ /* synthetic */ Object zzb(Object obj) throws GeneralSecurityException {
        zzgn zzgn = (zzgn) obj;
        zzgk zzb = zzgn.zzb().zzb();
        zzgt zza = zzb.zza();
        int zzc = zzdg.zzc(zza.zza());
        byte[] zzp = zzgn.zzc().zzp();
        ECPrivateKeySpec eCPrivateKeySpec = new ECPrivateKeySpec(new BigInteger(1, zzp), zzjw.zze(zzc));
        return new zzjs((ECPrivateKey) zzjy.zzg.zza("EC").generatePrivate(eCPrivateKeySpec), zza.zzc().zzp(), zzdg.zzb(zza.zzb()), zzdg.zzd(zzb.zzc()), new zzdh(zzb.zzb().zza()));
    }
}

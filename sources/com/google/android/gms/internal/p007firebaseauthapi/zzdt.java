package com.google.android.gms.internal.p007firebaseauthapi;

import java.security.GeneralSecurityException;
import javax.crypto.spec.SecretKeySpec;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzdt */
/* compiled from: com.google.firebase:firebase-auth@@20.0.1 */
final class zzdt extends zzar<zzay, zzhd> {
    zzdt(Class cls) {
        super(cls);
    }

    public final /* bridge */ /* synthetic */ Object zzb(Object obj) throws GeneralSecurityException {
        zzhd zzhd = (zzhd) obj;
        zzha zza = zzhd.zzb().zza();
        SecretKeySpec secretKeySpec = new SecretKeySpec(zzhd.zzc().zzp(), "HMAC");
        int zzb = zzhd.zzb().zzb();
        zzha zzha = zzha.UNKNOWN_HASH;
        int ordinal = zza.ordinal();
        if (ordinal == 1) {
            return new zzkn(new zzkm("HMACSHA1", secretKeySpec), zzb);
        }
        if (ordinal == 3) {
            return new zzkn(new zzkm("HMACSHA256", secretKeySpec), zzb);
        }
        if (ordinal == 4) {
            return new zzkn(new zzkm("HMACSHA512", secretKeySpec), zzb);
        }
        throw new GeneralSecurityException("unknown hash");
    }
}

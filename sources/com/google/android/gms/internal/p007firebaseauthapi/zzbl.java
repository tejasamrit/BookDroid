package com.google.android.gms.internal.p007firebaseauthapi;

import com.bumptech.glide.load.Key;
import java.nio.charset.Charset;
import java.security.GeneralSecurityException;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzbl */
/* compiled from: com.google.firebase:firebase-auth@@20.0.1 */
final class zzbl {
    public static final Charset zza = Charset.forName(Key.STRING_CHARSET_NAME);

    public static zzig zza(zzib zzib) {
        zzid zzb = zzig.zzb();
        zzb.zza(zzib.zza());
        for (zzia next : zzib.zzb()) {
            zzie zzb2 = zzif.zzb();
            zzb2.zza(next.zzb().zza());
            zzb2.zzb(next.zzc());
            zzb2.zzd(next.zze());
            zzb2.zzc(next.zzd());
            zzb.zzb((zzif) zzb2.zzl());
        }
        return (zzig) zzb.zzl();
    }

    public static void zzb(zzib zzib) throws GeneralSecurityException {
        int zza2 = zzib.zza();
        int i = 0;
        boolean z = false;
        boolean z2 = true;
        for (zzia next : zzib.zzb()) {
            if (next.zzc() == zzhq.ENABLED) {
                if (!next.zza()) {
                    throw new GeneralSecurityException(String.format("key %d has no key data", new Object[]{Integer.valueOf(next.zzd())}));
                } else if (next.zze() == zziu.UNKNOWN_PREFIX) {
                    throw new GeneralSecurityException(String.format("key %d has unknown prefix", new Object[]{Integer.valueOf(next.zzd())}));
                } else if (next.zzc() != zzhq.UNKNOWN_STATUS) {
                    if (next.zzd() == zza2) {
                        if (!z) {
                            z = true;
                        } else {
                            throw new GeneralSecurityException("keyset contains multiple primary keys");
                        }
                    }
                    z2 &= next.zzb().zzc() == zzhn.ASYMMETRIC_PUBLIC;
                    i++;
                } else {
                    throw new GeneralSecurityException(String.format("key %d has unknown status", new Object[]{Integer.valueOf(next.zzd())}));
                }
            }
        }
        if (i == 0) {
            throw new GeneralSecurityException("keyset must contain at least one ENABLED key");
        } else if (!z && !z2) {
            throw new GeneralSecurityException("keyset doesn't contain a valid primary key");
        }
    }
}

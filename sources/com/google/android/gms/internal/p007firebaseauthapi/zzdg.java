package com.google.android.gms.internal.p007firebaseauthapi;

import java.security.GeneralSecurityException;
import java.security.NoSuchAlgorithmException;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzdg */
/* compiled from: com.google.firebase:firebase-auth@@20.0.1 */
final class zzdg {
    public static void zza(zzgk zzgk) throws GeneralSecurityException {
        zzjw.zze(zzc(zzgk.zza().zza()));
        zzb(zzgk.zza().zzb());
        if (zzgk.zzc() != zzgb.UNKNOWN_FORMAT) {
            zzbk.zze(zzgk.zzb().zza());
            return;
        }
        throw new GeneralSecurityException("unknown EC point format");
    }

    public static String zzb(zzha zzha) throws NoSuchAlgorithmException {
        zzgb zzgb = zzgb.UNKNOWN_FORMAT;
        zzgv zzgv = zzgv.UNKNOWN_CURVE;
        zzha zzha2 = zzha.UNKNOWN_HASH;
        int ordinal = zzha.ordinal();
        if (ordinal == 1) {
            return "HmacSha1";
        }
        if (ordinal == 3) {
            return "HmacSha256";
        }
        if (ordinal == 4) {
            return "HmacSha512";
        }
        String valueOf = String.valueOf(zzha);
        StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 27);
        sb.append("hash unsupported for HMAC: ");
        sb.append(valueOf);
        throw new NoSuchAlgorithmException(sb.toString());
    }

    public static int zzc(zzgv zzgv) throws GeneralSecurityException {
        zzgb zzgb = zzgb.UNKNOWN_FORMAT;
        zzgv zzgv2 = zzgv.UNKNOWN_CURVE;
        zzha zzha = zzha.UNKNOWN_HASH;
        int ordinal = zzgv.ordinal();
        int i = 1;
        if (ordinal != 1) {
            i = 2;
            if (ordinal != 2) {
                if (ordinal == 3) {
                    return 3;
                }
                String valueOf = String.valueOf(zzgv);
                StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 20);
                sb.append("unknown curve type: ");
                sb.append(valueOf);
                throw new GeneralSecurityException(sb.toString());
            }
        }
        return i;
    }

    public static int zzd(zzgb zzgb) throws GeneralSecurityException {
        zzgb zzgb2 = zzgb.UNKNOWN_FORMAT;
        zzgv zzgv = zzgv.UNKNOWN_CURVE;
        zzha zzha = zzha.UNKNOWN_HASH;
        int ordinal = zzgb.ordinal();
        int i = 1;
        if (ordinal != 1) {
            i = 2;
            if (ordinal != 2) {
                if (ordinal == 3) {
                    return 3;
                }
                String valueOf = String.valueOf(zzgb);
                StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 22);
                sb.append("unknown point format: ");
                sb.append(valueOf);
                throw new GeneralSecurityException(sb.toString());
            }
        }
        return i;
    }
}

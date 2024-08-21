package com.google.android.gms.internal.p007firebaseauthapi;

import com.google.common.base.Ascii;
import java.security.GeneralSecurityException;
import java.security.SecureRandom;
import java.util.Iterator;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzau */
/* compiled from: com.google.firebase:firebase-auth@@20.0.1 */
public final class zzau {
    private final zzhy zza;

    private zzau(zzhy zzhy) {
        this.zza = zzhy;
    }

    public static zzau zza(zzat zzat) {
        return new zzau((zzhy) zzat.zzb().zzu());
    }

    public static zzau zzb() {
        return new zzau(zzib.zzf());
    }

    private final synchronized zzia zzg(zzht zzht) throws GeneralSecurityException {
        zzhz zzf;
        zzho zze = zzbk.zze(zzht);
        int zzi = zzi();
        zziu zzc = zzht.zzc();
        if (zzc != zziu.UNKNOWN_PREFIX) {
            zzf = zzia.zzf();
            zzf.zza(zze);
            zzf.zzc(zzi);
            zzf.zzb(zzhq.ENABLED);
            zzf.zzd(zzc);
        } else {
            throw new GeneralSecurityException("unknown output prefix type");
        }
        return (zzia) zzf.zzl();
    }

    private final synchronized boolean zzh(int i) {
        boolean z;
        Iterator<zzia> it = this.zza.zzb().iterator();
        while (true) {
            if (it.hasNext()) {
                if (it.next().zzd() == i) {
                    z = true;
                    break;
                }
            } else {
                z = false;
                break;
            }
        }
        return z;
    }

    private final synchronized int zzi() {
        int zzj;
        zzj = zzj();
        while (zzh(zzj)) {
            zzj = zzj();
        }
        return zzj;
    }

    private static int zzj() {
        SecureRandom secureRandom = new SecureRandom();
        byte[] bArr = new byte[4];
        byte b = 0;
        while (b == 0) {
            secureRandom.nextBytes(bArr);
            b = ((bArr[0] & Byte.MAX_VALUE) << Ascii.CAN) | ((bArr[1] & 255) << Ascii.DLE) | ((bArr[2] & 255) << 8) | (bArr[3] & 255);
        }
        return b;
    }

    public final synchronized zzat zzc() throws GeneralSecurityException {
        return zzat.zza((zzib) this.zza.zzl());
    }

    public final synchronized zzau zzd(zzap zzap) throws GeneralSecurityException {
        zze(zzap.zza(), false);
        return this;
    }

    @Deprecated
    public final synchronized int zze(zzht zzht, boolean z) throws GeneralSecurityException {
        zzia zzg;
        zzg = zzg(zzht);
        this.zza.zze(zzg);
        return zzg.zzd();
    }

    public final synchronized zzau zzf(int i) throws GeneralSecurityException {
        int i2 = 0;
        while (i2 < this.zza.zzc()) {
            zzia zzd = this.zza.zzd(i2);
            if (zzd.zzd() != i) {
                i2++;
            } else if (zzd.zzc().equals(zzhq.ENABLED)) {
                this.zza.zza(i);
            } else {
                StringBuilder sb = new StringBuilder(63);
                sb.append("cannot set key as primary because it's not enabled: ");
                sb.append(i);
                throw new GeneralSecurityException(sb.toString());
            }
        }
        StringBuilder sb2 = new StringBuilder(26);
        sb2.append("key not found: ");
        sb2.append(i);
        throw new GeneralSecurityException(sb2.toString());
        return this;
    }
}

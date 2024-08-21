package com.google.android.gms.internal.p007firebaseauthapi;

import java.security.GeneralSecurityException;
import java.util.Arrays;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzdh */
/* compiled from: com.google.firebase:firebase-auth@@20.0.1 */
final class zzdh implements zzjr {
    private final String zza;
    private final int zzb;
    private zzfk zzc;
    private zzem zzd;
    private int zze;

    zzdh(zzht zzht) throws GeneralSecurityException {
        String zza2 = zzht.zza();
        this.zza = zza2;
        if (zza2.equals(zzbm.zzb)) {
            try {
                zzfn zzb2 = zzfn.zzb(zzht.zzb(), zzzb.zza());
                this.zzc = (zzfk) zzbk.zzf(zzht);
                this.zzb = zzb2.zza();
            } catch (zzzw e) {
                throw new GeneralSecurityException("invalid KeyFormat protobuf, expected AesGcmKeyFormat", e);
            }
        } else if (zza2.equals(zzbm.zza)) {
            try {
                zzep zzc2 = zzep.zzc(zzht.zzb(), zzzb.zza());
                this.zzd = (zzem) zzbk.zzf(zzht);
                this.zze = zzc2.zza().zzb();
                this.zzb = this.zze + zzc2.zzb().zzb();
            } catch (zzzw e2) {
                throw new GeneralSecurityException("invalid KeyFormat protobuf, expected AesCtrHmacAeadKeyFormat", e2);
            }
        } else {
            String valueOf = String.valueOf(zza2);
            throw new GeneralSecurityException(valueOf.length() != 0 ? "unsupported AEAD DEM key type: ".concat(valueOf) : new String("unsupported AEAD DEM key type: "));
        }
    }

    public final int zza() {
        return this.zzb;
    }

    public final zzag zzb(byte[] bArr) throws GeneralSecurityException {
        if (bArr.length != this.zzb) {
            throw new GeneralSecurityException("Symmetric key has incorrect length");
        } else if (this.zza.equals(zzbm.zzb)) {
            zzfj zzd2 = zzfk.zzd();
            zzd2.zzm(this.zzc);
            zzd2.zzb(zzym.zzl(bArr, 0, this.zzb));
            return (zzag) zzbk.zzh(this.zza, (zzfk) zzd2.zzl(), zzag.class);
        } else if (this.zza.equals(zzbm.zza)) {
            byte[] copyOfRange = Arrays.copyOfRange(bArr, 0, this.zze);
            byte[] copyOfRange2 = Arrays.copyOfRange(bArr, this.zze, this.zzb);
            zzer zze2 = zzes.zze();
            zze2.zzm(this.zzd.zzb());
            zze2.zzc(zzym.zzm(copyOfRange));
            zzhc zze3 = zzhd.zze();
            zze3.zzm(this.zzd.zzc());
            zze3.zzc(zzym.zzm(copyOfRange2));
            zzel zze4 = zzem.zze();
            zze4.zza(this.zzd.zza());
            zze4.zzb((zzes) zze2.zzl());
            zze4.zzc((zzhd) zze3.zzl());
            return (zzag) zzbk.zzh(this.zza, (zzem) zze4.zzl(), zzag.class);
        } else {
            throw new GeneralSecurityException("unknown DEM key type");
        }
    }
}

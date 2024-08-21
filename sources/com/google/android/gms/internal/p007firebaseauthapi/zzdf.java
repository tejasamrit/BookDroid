package com.google.android.gms.internal.p007firebaseauthapi;

@Deprecated
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzdf */
/* compiled from: com.google.firebase:firebase-auth@@20.0.1 */
public final class zzdf {
    public static final zzht zza;
    public static final zzht zzb;
    public static final zzht zzc;
    private static final byte[] zzd;

    static {
        byte[] bArr = new byte[0];
        zzd = bArr;
        byte[] bArr2 = bArr;
        zza = zza(zzgv.NIST_P256, zzha.SHA256, zzgb.UNCOMPRESSED, zzbn.zza, zziu.TINK, bArr2);
        zzb = zza(zzgv.NIST_P256, zzha.SHA256, zzgb.COMPRESSED, zzbn.zza, zziu.RAW, bArr2);
        zzc = zza(zzgv.NIST_P256, zzha.SHA256, zzgb.UNCOMPRESSED, zzbn.zze, zziu.TINK, bArr2);
    }

    public static zzht zza(zzgv zzgv, zzha zzha, zzgb zzgb, zzht zzht, zziu zziu, byte[] bArr) {
        zzgg zzc2 = zzgh.zzc();
        zzgs zzd2 = zzgt.zzd();
        zzd2.zza(zzgv);
        zzd2.zzb(zzha);
        zzd2.zzc(zzym.zzm(bArr));
        zzgd zzb2 = zzge.zzb();
        zzb2.zza(zzht);
        zzgj zzd3 = zzgk.zzd();
        zzd3.zza((zzgt) zzd2.zzl());
        zzd3.zzb((zzge) zzb2.zzl());
        zzd3.zzc(zzgb);
        zzc2.zza((zzgk) zzd3.zzl());
        zzhs zzd4 = zzht.zzd();
        new zzcx();
        zzd4.zza("type.googleapis.com/google.crypto.tink.EciesAeadHkdfPrivateKey");
        zzd4.zzc(zziu);
        zzd4.zzb(((zzgh) zzc2.zzl()).zzn());
        return (zzht) zzd4.zzl();
    }
}

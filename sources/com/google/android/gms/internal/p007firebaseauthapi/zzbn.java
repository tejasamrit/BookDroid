package com.google.android.gms.internal.p007firebaseauthapi;

@Deprecated
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzbn */
/* compiled from: com.google.firebase:firebase-auth@@20.0.1 */
public final class zzbn {
    public static final zzht zza = zza(16);
    public static final zzht zzb = zza(32);
    public static final zzht zzc = zzb(16, 16);
    public static final zzht zzd = zzb(32, 16);
    public static final zzht zze = zzc(16, 16, 32, 16, zzha.SHA256);
    public static final zzht zzf = zzc(32, 16, 32, 32, zzha.SHA256);
    public static final zzht zzg;
    public static final zzht zzh;

    static {
        zzhs zzd2 = zzht.zzd();
        new zzci();
        zzd2.zza("type.googleapis.com/google.crypto.tink.ChaCha20Poly1305Key");
        zzd2.zzc(zziu.TINK);
        zzg = (zzht) zzd2.zzl();
        zzhs zzd3 = zzht.zzd();
        new zzcs();
        zzd3.zza("type.googleapis.com/google.crypto.tink.XChaCha20Poly1305Key");
        zzd3.zzc(zziu.TINK);
        zzh = (zzht) zzd3.zzl();
    }

    public static zzht zza(int i) {
        zzfm zzc2 = zzfn.zzc();
        zzc2.zza(i);
        zzhs zzd2 = zzht.zzd();
        zzd2.zzb(((zzfn) zzc2.zzl()).zzn());
        new zzcc();
        zzd2.zza("type.googleapis.com/google.crypto.tink.AesGcmKey");
        zzd2.zzc(zziu.TINK);
        return (zzht) zzd2.zzl();
    }

    public static zzht zzb(int i, int i2) {
        zzfd zzd2 = zzfe.zzd();
        zzd2.zzb(i);
        zzfg zzb2 = zzfh.zzb();
        zzb2.zza(16);
        zzd2.zza((zzfh) zzb2.zzl());
        zzhs zzd3 = zzht.zzd();
        zzd3.zzb(((zzfe) zzd2.zzl()).zzn());
        new zzbz();
        zzd3.zza("type.googleapis.com/google.crypto.tink.AesEaxKey");
        zzd3.zzc(zziu.TINK);
        return (zzht) zzd3.zzl();
    }

    public static zzht zzc(int i, int i2, int i3, int i4, zzha zzha) {
        zzeu zzd2 = zzev.zzd();
        zzex zzb2 = zzey.zzb();
        zzb2.zza(16);
        zzd2.zza((zzey) zzb2.zzl());
        zzd2.zzb(i);
        zzhf zzd3 = zzhg.zzd();
        zzhi zzc2 = zzhj.zzc();
        zzc2.zza(zzha);
        zzc2.zzb(i4);
        zzd3.zza((zzhj) zzc2.zzl());
        zzd3.zzb(32);
        zzeo zzd4 = zzep.zzd();
        zzd4.zza((zzev) zzd2.zzl());
        zzd4.zzb((zzhg) zzd3.zzl());
        zzhs zzd5 = zzht.zzd();
        zzd5.zzb(((zzep) zzd4.zzl()).zzn());
        new zzbt();
        zzd5.zza("type.googleapis.com/google.crypto.tink.AesCtrHmacAeadKey");
        zzd5.zzc(zziu.TINK);
        return (zzht) zzd5.zzl();
    }
}

package com.google.android.gms.internal.p007firebaseauthapi;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzhj */
/* compiled from: com.google.firebase:firebase-auth@@20.0.1 */
public final class zzhj extends zzzo<zzhj, zzhi> implements zzaas {
    /* access modifiers changed from: private */
    public static final zzhj zzf;
    /* access modifiers changed from: private */
    public int zzb;
    /* access modifiers changed from: private */
    public int zze;

    static {
        zzhj zzhj = new zzhj();
        zzf = zzhj;
        zzzo.zzy(zzhj.class, zzhj);
    }

    private zzhj() {
    }

    public static zzhi zzc() {
        return (zzhi) zzf.zzs();
    }

    public static zzhj zzd() {
        return zzf;
    }

    public final zzha zza() {
        zzha zzb2 = zzha.zzb(this.zzb);
        return zzb2 == null ? zzha.UNRECOGNIZED : zzb2;
    }

    public final int zzb() {
        return this.zze;
    }

    /* access modifiers changed from: protected */
    public final Object zzj(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzz(zzf, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0000\u0000\u0001\f\u0002\u000b", new Object[]{"zzb", "zze"});
        } else if (i2 == 3) {
            return new zzhj();
        } else {
            if (i2 == 4) {
                return new zzhi((zzhh) null);
            }
            if (i2 != 5) {
                return null;
            }
            return zzf;
        }
    }
}

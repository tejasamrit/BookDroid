package com.google.android.gms.internal.p007firebaseauthapi;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzhd */
/* compiled from: com.google.firebase:firebase-auth@@20.0.1 */
public final class zzhd extends zzzo<zzhd, zzhc> implements zzaas {
    /* access modifiers changed from: private */
    public static final zzhd zzg;
    /* access modifiers changed from: private */
    public int zzb;
    private zzhj zze;
    private zzym zzf = zzym.zzb;

    static {
        zzhd zzhd = new zzhd();
        zzg = zzhd;
        zzzo.zzy(zzhd.class, zzhd);
    }

    private zzhd() {
    }

    public static zzhd zzd(zzym zzym, zzzb zzzb) throws zzzw {
        return (zzhd) zzzo.zzE(zzg, zzym, zzzb);
    }

    public static zzhc zze() {
        return (zzhc) zzg.zzs();
    }

    public static zzhd zzf() {
        return zzg;
    }

    static /* synthetic */ void zzi(zzhd zzhd, zzhj zzhj) {
        zzhj.getClass();
        zzhd.zze = zzhj;
    }

    static /* synthetic */ void zzk(zzhd zzhd, zzym zzym) {
        zzym.getClass();
        zzhd.zzf = zzym;
    }

    public final int zza() {
        return this.zzb;
    }

    public final zzhj zzb() {
        zzhj zzhj = this.zze;
        return zzhj == null ? zzhj.zzd() : zzhj;
    }

    public final zzym zzc() {
        return this.zzf;
    }

    /* access modifiers changed from: protected */
    public final Object zzj(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzz(zzg, "\u0000\u0003\u0000\u0000\u0001\u0003\u0003\u0000\u0000\u0000\u0001\u000b\u0002\t\u0003\n", new Object[]{"zzb", "zze", "zzf"});
        } else if (i2 == 3) {
            return new zzhd();
        } else {
            if (i2 == 4) {
                return new zzhc((zzhb) null);
            }
            if (i2 != 5) {
                return null;
            }
            return zzg;
        }
    }
}

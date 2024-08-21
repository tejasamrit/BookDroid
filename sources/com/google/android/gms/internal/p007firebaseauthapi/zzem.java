package com.google.android.gms.internal.p007firebaseauthapi;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzem */
/* compiled from: com.google.firebase:firebase-auth@@20.0.1 */
public final class zzem extends zzzo<zzem, zzel> implements zzaas {
    /* access modifiers changed from: private */
    public static final zzem zzg;
    /* access modifiers changed from: private */
    public int zzb;
    private zzes zze;
    private zzhd zzf;

    static {
        zzem zzem = new zzem();
        zzg = zzem;
        zzzo.zzy(zzem.class, zzem);
    }

    private zzem() {
    }

    public static zzem zzd(zzym zzym, zzzb zzzb) throws zzzw {
        return (zzem) zzzo.zzE(zzg, zzym, zzzb);
    }

    public static zzel zze() {
        return (zzel) zzg.zzs();
    }

    static /* synthetic */ void zzh(zzem zzem, zzes zzes) {
        zzes.getClass();
        zzem.zze = zzes;
    }

    static /* synthetic */ void zzi(zzem zzem, zzhd zzhd) {
        zzhd.getClass();
        zzem.zzf = zzhd;
    }

    public final int zza() {
        return this.zzb;
    }

    public final zzes zzb() {
        zzes zzes = this.zze;
        return zzes == null ? zzes.zzf() : zzes;
    }

    public final zzhd zzc() {
        zzhd zzhd = this.zzf;
        return zzhd == null ? zzhd.zzf() : zzhd;
    }

    /* access modifiers changed from: protected */
    public final Object zzj(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzz(zzg, "\u0000\u0003\u0000\u0000\u0001\u0003\u0003\u0000\u0000\u0000\u0001\u000b\u0002\t\u0003\t", new Object[]{"zzb", "zze", "zzf"});
        } else if (i2 == 3) {
            return new zzem();
        } else {
            if (i2 == 4) {
                return new zzel((zzek) null);
            }
            if (i2 != 5) {
                return null;
            }
            return zzg;
        }
    }
}

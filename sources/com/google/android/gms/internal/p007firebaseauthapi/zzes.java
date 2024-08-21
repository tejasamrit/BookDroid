package com.google.android.gms.internal.p007firebaseauthapi;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzes */
/* compiled from: com.google.firebase:firebase-auth@@20.0.1 */
public final class zzes extends zzzo<zzes, zzer> implements zzaas {
    /* access modifiers changed from: private */
    public static final zzes zzg;
    /* access modifiers changed from: private */
    public int zzb;
    private zzey zze;
    private zzym zzf = zzym.zzb;

    static {
        zzes zzes = new zzes();
        zzg = zzes;
        zzzo.zzy(zzes.class, zzes);
    }

    private zzes() {
    }

    public static zzes zzd(zzym zzym, zzzb zzzb) throws zzzw {
        return (zzes) zzzo.zzE(zzg, zzym, zzzb);
    }

    public static zzer zze() {
        return (zzer) zzg.zzs();
    }

    public static zzes zzf() {
        return zzg;
    }

    static /* synthetic */ void zzi(zzes zzes, zzey zzey) {
        zzey.getClass();
        zzes.zze = zzey;
    }

    static /* synthetic */ void zzk(zzes zzes, zzym zzym) {
        zzym.getClass();
        zzes.zzf = zzym;
    }

    public final int zza() {
        return this.zzb;
    }

    public final zzey zzb() {
        zzey zzey = this.zze;
        return zzey == null ? zzey.zzc() : zzey;
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
            return new zzes();
        } else {
            if (i2 == 4) {
                return new zzer((zzeq) null);
            }
            if (i2 != 5) {
                return null;
            }
            return zzg;
        }
    }
}

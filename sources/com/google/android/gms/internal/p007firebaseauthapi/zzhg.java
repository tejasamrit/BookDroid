package com.google.android.gms.internal.p007firebaseauthapi;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzhg */
/* compiled from: com.google.firebase:firebase-auth@@20.0.1 */
public final class zzhg extends zzzo<zzhg, zzhf> implements zzaas {
    /* access modifiers changed from: private */
    public static final zzhg zzg;
    private zzhj zzb;
    /* access modifiers changed from: private */
    public int zze;
    private int zzf;

    static {
        zzhg zzhg = new zzhg();
        zzg = zzhg;
        zzzo.zzy(zzhg.class, zzhg);
    }

    private zzhg() {
    }

    public static zzhg zzc(zzym zzym, zzzb zzzb) throws zzzw {
        return (zzhg) zzzo.zzE(zzg, zzym, zzzb);
    }

    public static zzhf zzd() {
        return (zzhf) zzg.zzs();
    }

    public static zzhg zze() {
        return zzg;
    }

    static /* synthetic */ void zzg(zzhg zzhg, zzhj zzhj) {
        zzhj.getClass();
        zzhg.zzb = zzhj;
    }

    public final zzhj zza() {
        zzhj zzhj = this.zzb;
        return zzhj == null ? zzhj.zzd() : zzhj;
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
            return zzz(zzg, "\u0000\u0003\u0000\u0000\u0001\u0003\u0003\u0000\u0000\u0000\u0001\t\u0002\u000b\u0003\u000b", new Object[]{"zzb", "zze", "zzf"});
        } else if (i2 == 3) {
            return new zzhg();
        } else {
            if (i2 == 4) {
                return new zzhf((zzhe) null);
            }
            if (i2 != 5) {
                return null;
            }
            return zzg;
        }
    }
}

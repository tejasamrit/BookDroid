package com.google.android.gms.internal.p007firebaseauthapi;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzep */
/* compiled from: com.google.firebase:firebase-auth@@20.0.1 */
public final class zzep extends zzzo<zzep, zzeo> implements zzaas {
    /* access modifiers changed from: private */
    public static final zzep zzf;
    private zzev zzb;
    private zzhg zze;

    static {
        zzep zzep = new zzep();
        zzf = zzep;
        zzzo.zzy(zzep.class, zzep);
    }

    private zzep() {
    }

    public static zzep zzc(zzym zzym, zzzb zzzb) throws zzzw {
        return (zzep) zzzo.zzE(zzf, zzym, zzzb);
    }

    public static zzeo zzd() {
        return (zzeo) zzf.zzs();
    }

    static /* synthetic */ void zzf(zzep zzep, zzev zzev) {
        zzev.getClass();
        zzep.zzb = zzev;
    }

    static /* synthetic */ void zzg(zzep zzep, zzhg zzhg) {
        zzhg.getClass();
        zzep.zze = zzhg;
    }

    public final zzev zza() {
        zzev zzev = this.zzb;
        return zzev == null ? zzev.zze() : zzev;
    }

    public final zzhg zzb() {
        zzhg zzhg = this.zze;
        return zzhg == null ? zzhg.zze() : zzhg;
    }

    /* access modifiers changed from: protected */
    public final Object zzj(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzz(zzf, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0000\u0000\u0001\t\u0002\t", new Object[]{"zzb", "zze"});
        } else if (i2 == 3) {
            return new zzep();
        } else {
            if (i2 == 4) {
                return new zzeo((zzen) null);
            }
            if (i2 != 5) {
                return null;
            }
            return zzf;
        }
    }
}

package com.google.android.gms.internal.p007firebaseauthapi;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzgq */
/* compiled from: com.google.firebase:firebase-auth@@20.0.1 */
public final class zzgq extends zzzo<zzgq, zzgp> implements zzaas {
    /* access modifiers changed from: private */
    public static final zzgq zzh;
    /* access modifiers changed from: private */
    public int zzb;
    private zzgk zze;
    private zzym zzf = zzym.zzb;
    private zzym zzg = zzym.zzb;

    static {
        zzgq zzgq = new zzgq();
        zzh = zzgq;
        zzzo.zzy(zzgq.class, zzgq);
    }

    private zzgq() {
    }

    public static zzgq zze(zzym zzym, zzzb zzzb) throws zzzw {
        return (zzgq) zzzo.zzE(zzh, zzym, zzzb);
    }

    public static zzgp zzf() {
        return (zzgp) zzh.zzs();
    }

    public static zzgq zzg() {
        return zzh;
    }

    static /* synthetic */ void zzk(zzgq zzgq, zzgk zzgk) {
        zzgk.getClass();
        zzgq.zze = zzgk;
    }

    static /* synthetic */ void zzl(zzgq zzgq, zzym zzym) {
        zzym.getClass();
        zzgq.zzf = zzym;
    }

    static /* synthetic */ void zzm(zzgq zzgq, zzym zzym) {
        zzym.getClass();
        zzgq.zzg = zzym;
    }

    public final int zza() {
        return this.zzb;
    }

    public final zzgk zzb() {
        zzgk zzgk = this.zze;
        return zzgk == null ? zzgk.zze() : zzgk;
    }

    public final zzym zzc() {
        return this.zzf;
    }

    public final zzym zzd() {
        return this.zzg;
    }

    /* access modifiers changed from: protected */
    public final Object zzj(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzz(zzh, "\u0000\u0004\u0000\u0000\u0001\u0004\u0004\u0000\u0000\u0000\u0001\u000b\u0002\t\u0003\n\u0004\n", new Object[]{"zzb", "zze", "zzf", "zzg"});
        } else if (i2 == 3) {
            return new zzgq();
        } else {
            if (i2 == 4) {
                return new zzgp((zzgo) null);
            }
            if (i2 != 5) {
                return null;
            }
            return zzh;
        }
    }
}

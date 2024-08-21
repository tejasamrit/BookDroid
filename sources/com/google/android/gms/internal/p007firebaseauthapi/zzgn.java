package com.google.android.gms.internal.p007firebaseauthapi;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzgn */
/* compiled from: com.google.firebase:firebase-auth@@20.0.1 */
public final class zzgn extends zzzo<zzgn, zzgm> implements zzaas {
    /* access modifiers changed from: private */
    public static final zzgn zzg;
    /* access modifiers changed from: private */
    public int zzb;
    private zzgq zze;
    private zzym zzf = zzym.zzb;

    static {
        zzgn zzgn = new zzgn();
        zzg = zzgn;
        zzzo.zzy(zzgn.class, zzgn);
    }

    private zzgn() {
    }

    public static zzgn zzd(zzym zzym, zzzb zzzb) throws zzzw {
        return (zzgn) zzzo.zzE(zzg, zzym, zzzb);
    }

    public static zzgm zze() {
        return (zzgm) zzg.zzs();
    }

    static /* synthetic */ void zzh(zzgn zzgn, zzgq zzgq) {
        zzgq.getClass();
        zzgn.zze = zzgq;
    }

    static /* synthetic */ void zzi(zzgn zzgn, zzym zzym) {
        zzym.getClass();
        zzgn.zzf = zzym;
    }

    public final int zza() {
        return this.zzb;
    }

    public final zzgq zzb() {
        zzgq zzgq = this.zze;
        return zzgq == null ? zzgq.zzg() : zzgq;
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
            return new zzgn();
        } else {
            if (i2 == 4) {
                return new zzgm((zzgl) null);
            }
            if (i2 != 5) {
                return null;
            }
            return zzg;
        }
    }
}

package com.google.android.gms.internal.p007firebaseauthapi;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzfb */
/* compiled from: com.google.firebase:firebase-auth@@20.0.1 */
public final class zzfb extends zzzo<zzfb, zzfa> implements zzaas {
    /* access modifiers changed from: private */
    public static final zzfb zzg;
    /* access modifiers changed from: private */
    public int zzb;
    private zzfh zze;
    private zzym zzf = zzym.zzb;

    static {
        zzfb zzfb = new zzfb();
        zzg = zzfb;
        zzzo.zzy(zzfb.class, zzfb);
    }

    private zzfb() {
    }

    public static zzfb zzd(zzym zzym, zzzb zzzb) throws zzzw {
        return (zzfb) zzzo.zzE(zzg, zzym, zzzb);
    }

    public static zzfa zze() {
        return (zzfa) zzg.zzs();
    }

    static /* synthetic */ void zzh(zzfb zzfb, zzfh zzfh) {
        zzfh.getClass();
        zzfb.zze = zzfh;
    }

    static /* synthetic */ void zzi(zzfb zzfb, zzym zzym) {
        zzym.getClass();
        zzfb.zzf = zzym;
    }

    public final int zza() {
        return this.zzb;
    }

    public final zzfh zzb() {
        zzfh zzfh = this.zze;
        return zzfh == null ? zzfh.zzc() : zzfh;
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
            return new zzfb();
        } else {
            if (i2 == 4) {
                return new zzfa((zzez) null);
            }
            if (i2 != 5) {
                return null;
            }
            return zzg;
        }
    }
}

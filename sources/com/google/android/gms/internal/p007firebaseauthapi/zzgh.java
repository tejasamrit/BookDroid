package com.google.android.gms.internal.p007firebaseauthapi;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzgh */
/* compiled from: com.google.firebase:firebase-auth@@20.0.1 */
public final class zzgh extends zzzo<zzgh, zzgg> implements zzaas {
    /* access modifiers changed from: private */
    public static final zzgh zze;
    private zzgk zzb;

    static {
        zzgh zzgh = new zzgh();
        zze = zzgh;
        zzzo.zzy(zzgh.class, zzgh);
    }

    private zzgh() {
    }

    public static zzgh zzb(zzym zzym, zzzb zzzb) throws zzzw {
        return (zzgh) zzzo.zzE(zze, zzym, zzzb);
    }

    public static zzgg zzc() {
        return (zzgg) zze.zzs();
    }

    static /* synthetic */ void zze(zzgh zzgh, zzgk zzgk) {
        zzgk.getClass();
        zzgh.zzb = zzgk;
    }

    public final zzgk zza() {
        zzgk zzgk = this.zzb;
        return zzgk == null ? zzgk.zze() : zzgk;
    }

    /* access modifiers changed from: protected */
    public final Object zzj(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzz(zze, "\u0000\u0001\u0000\u0000\u0001\u0001\u0001\u0000\u0000\u0000\u0001\t", new Object[]{"zzb"});
        } else if (i2 == 3) {
            return new zzgh();
        } else {
            if (i2 == 4) {
                return new zzgg((zzgf) null);
            }
            if (i2 != 5) {
                return null;
            }
            return zze;
        }
    }
}

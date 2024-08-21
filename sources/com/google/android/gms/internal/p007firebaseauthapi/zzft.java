package com.google.android.gms.internal.p007firebaseauthapi;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzft */
/* compiled from: com.google.firebase:firebase-auth@@20.0.1 */
public final class zzft extends zzzo<zzft, zzfs> implements zzaas {
    /* access modifiers changed from: private */
    public static final zzft zzf;
    private int zzb;
    private int zze;

    static {
        zzft zzft = new zzft();
        zzf = zzft;
        zzzo.zzy(zzft.class, zzft);
    }

    private zzft() {
    }

    public static zzft zzb(zzym zzym, zzzb zzzb) throws zzzw {
        return (zzft) zzzo.zzE(zzf, zzym, zzzb);
    }

    public final int zza() {
        return this.zzb;
    }

    /* access modifiers changed from: protected */
    public final Object zzj(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzz(zzf, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0000\u0000\u0001\u000b\u0002\u000b", new Object[]{"zze", "zzb"});
        } else if (i2 == 3) {
            return new zzft();
        } else {
            if (i2 == 4) {
                return new zzfs((zzfr) null);
            }
            if (i2 != 5) {
                return null;
            }
            return zzf;
        }
    }
}

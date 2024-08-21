package com.google.android.gms.internal.p007firebaseauthapi;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzjd */
/* compiled from: com.google.firebase:firebase-auth@@20.0.1 */
public final class zzjd extends zzzo<zzjd, zzjc> implements zzaas {
    /* access modifiers changed from: private */
    public static final zzjd zzb;

    static {
        zzjd zzjd = new zzjd();
        zzb = zzjd;
        zzzo.zzy(zzjd.class, zzjd);
    }

    private zzjd() {
    }

    public static zzjd zza(zzym zzym, zzzb zzzb) throws zzzw {
        return (zzjd) zzzo.zzE(zzb, zzym, zzzb);
    }

    /* access modifiers changed from: protected */
    public final Object zzj(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzz(zzb, "\u0000\u0000", (Object[]) null);
        }
        if (i2 == 3) {
            return new zzjd();
        }
        if (i2 == 4) {
            return new zzjc((zzjb) null);
        }
        if (i2 != 5) {
            return null;
        }
        return zzb;
    }
}

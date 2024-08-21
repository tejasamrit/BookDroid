package com.google.android.gms.internal.p007firebaseauthapi;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzey */
/* compiled from: com.google.firebase:firebase-auth@@20.0.1 */
public final class zzey extends zzzo<zzey, zzex> implements zzaas {
    /* access modifiers changed from: private */
    public static final zzey zze;
    /* access modifiers changed from: private */
    public int zzb;

    static {
        zzey zzey = new zzey();
        zze = zzey;
        zzzo.zzy(zzey.class, zzey);
    }

    private zzey() {
    }

    public static zzex zzb() {
        return (zzex) zze.zzs();
    }

    public static zzey zzc() {
        return zze;
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
            return zzz(zze, "\u0000\u0001\u0000\u0000\u0001\u0001\u0001\u0000\u0000\u0000\u0001\u000b", new Object[]{"zzb"});
        } else if (i2 == 3) {
            return new zzey();
        } else {
            if (i2 == 4) {
                return new zzex((zzew) null);
            }
            if (i2 != 5) {
                return null;
            }
            return zze;
        }
    }
}

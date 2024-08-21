package com.google.android.gms.internal.p007firebaseauthapi;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzej */
/* compiled from: com.google.firebase:firebase-auth@@20.0.1 */
public final class zzej extends zzzo<zzej, zzei> implements zzaas {
    /* access modifiers changed from: private */
    public static final zzej zze;
    private int zzb;

    static {
        zzej zzej = new zzej();
        zze = zzej;
        zzzo.zzy(zzej.class, zzej);
    }

    private zzej() {
    }

    public static zzej zzb() {
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
            return new zzej();
        } else {
            if (i2 == 4) {
                return new zzei((zzeh) null);
            }
            if (i2 != 5) {
                return null;
            }
            return zze;
        }
    }
}

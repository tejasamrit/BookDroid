package com.google.android.gms.internal.p007firebaseauthapi;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzfh */
/* compiled from: com.google.firebase:firebase-auth@@20.0.1 */
public final class zzfh extends zzzo<zzfh, zzfg> implements zzaas {
    /* access modifiers changed from: private */
    public static final zzfh zze;
    /* access modifiers changed from: private */
    public int zzb;

    static {
        zzfh zzfh = new zzfh();
        zze = zzfh;
        zzzo.zzy(zzfh.class, zzfh);
    }

    private zzfh() {
    }

    public static zzfg zzb() {
        return (zzfg) zze.zzs();
    }

    public static zzfh zzc() {
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
            return new zzfh();
        } else {
            if (i2 == 4) {
                return new zzfg((zzff) null);
            }
            if (i2 != 5) {
                return null;
            }
            return zze;
        }
    }
}

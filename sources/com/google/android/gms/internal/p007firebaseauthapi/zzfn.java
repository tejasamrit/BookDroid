package com.google.android.gms.internal.p007firebaseauthapi;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzfn */
/* compiled from: com.google.firebase:firebase-auth@@20.0.1 */
public final class zzfn extends zzzo<zzfn, zzfm> implements zzaas {
    /* access modifiers changed from: private */
    public static final zzfn zzf;
    /* access modifiers changed from: private */
    public int zzb;
    private int zze;

    static {
        zzfn zzfn = new zzfn();
        zzf = zzfn;
        zzzo.zzy(zzfn.class, zzfn);
    }

    private zzfn() {
    }

    public static zzfn zzb(zzym zzym, zzzb zzzb) throws zzzw {
        return (zzfn) zzzo.zzE(zzf, zzym, zzzb);
    }

    public static zzfm zzc() {
        return (zzfm) zzf.zzs();
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
            return zzz(zzf, "\u0000\u0002\u0000\u0000\u0002\u0003\u0002\u0000\u0000\u0000\u0002\u000b\u0003\u000b", new Object[]{"zzb", "zze"});
        } else if (i2 == 3) {
            return new zzfn();
        } else {
            if (i2 == 4) {
                return new zzfm((zzfl) null);
            }
            if (i2 != 5) {
                return null;
            }
            return zzf;
        }
    }
}

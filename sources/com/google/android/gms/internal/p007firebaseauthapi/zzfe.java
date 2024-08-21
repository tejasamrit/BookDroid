package com.google.android.gms.internal.p007firebaseauthapi;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzfe */
/* compiled from: com.google.firebase:firebase-auth@@20.0.1 */
public final class zzfe extends zzzo<zzfe, zzfd> implements zzaas {
    /* access modifiers changed from: private */
    public static final zzfe zzf;
    private zzfh zzb;
    /* access modifiers changed from: private */
    public int zze;

    static {
        zzfe zzfe = new zzfe();
        zzf = zzfe;
        zzzo.zzy(zzfe.class, zzfe);
    }

    private zzfe() {
    }

    public static zzfe zzc(zzym zzym, zzzb zzzb) throws zzzw {
        return (zzfe) zzzo.zzE(zzf, zzym, zzzb);
    }

    public static zzfd zzd() {
        return (zzfd) zzf.zzs();
    }

    static /* synthetic */ void zzf(zzfe zzfe, zzfh zzfh) {
        zzfh.getClass();
        zzfe.zzb = zzfh;
    }

    public final zzfh zza() {
        zzfh zzfh = this.zzb;
        return zzfh == null ? zzfh.zzc() : zzfh;
    }

    public final int zzb() {
        return this.zze;
    }

    /* access modifiers changed from: protected */
    public final Object zzj(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzz(zzf, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0000\u0000\u0001\t\u0002\u000b", new Object[]{"zzb", "zze"});
        } else if (i2 == 3) {
            return new zzfe();
        } else {
            if (i2 == 4) {
                return new zzfd((zzfc) null);
            }
            if (i2 != 5) {
                return null;
            }
            return zzf;
        }
    }
}

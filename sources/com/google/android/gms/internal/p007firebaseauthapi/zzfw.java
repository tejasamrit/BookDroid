package com.google.android.gms.internal.p007firebaseauthapi;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzfw */
/* compiled from: com.google.firebase:firebase-auth@@20.0.1 */
public final class zzfw extends zzzo<zzfw, zzfv> implements zzaas {
    /* access modifiers changed from: private */
    public static final zzfw zzf;
    /* access modifiers changed from: private */
    public int zzb;
    private zzym zze = zzym.zzb;

    static {
        zzfw zzfw = new zzfw();
        zzf = zzfw;
        zzzo.zzy(zzfw.class, zzfw);
    }

    private zzfw() {
    }

    public static zzfw zzc(zzym zzym, zzzb zzzb) throws zzzw {
        return (zzfw) zzzo.zzE(zzf, zzym, zzzb);
    }

    public static zzfv zzd() {
        return (zzfv) zzf.zzs();
    }

    static /* synthetic */ void zzg(zzfw zzfw, zzym zzym) {
        zzym.getClass();
        zzfw.zze = zzym;
    }

    public final int zza() {
        return this.zzb;
    }

    public final zzym zzb() {
        return this.zze;
    }

    /* access modifiers changed from: protected */
    public final Object zzj(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzz(zzf, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0000\u0000\u0001\u000b\u0002\n", new Object[]{"zzb", "zze"});
        } else if (i2 == 3) {
            return new zzfw();
        } else {
            if (i2 == 4) {
                return new zzfv((zzfu) null);
            }
            if (i2 != 5) {
                return null;
            }
            return zzf;
        }
    }
}

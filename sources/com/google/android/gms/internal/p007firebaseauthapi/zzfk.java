package com.google.android.gms.internal.p007firebaseauthapi;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzfk */
/* compiled from: com.google.firebase:firebase-auth@@20.0.1 */
public final class zzfk extends zzzo<zzfk, zzfj> implements zzaas {
    /* access modifiers changed from: private */
    public static final zzfk zzf;
    /* access modifiers changed from: private */
    public int zzb;
    private zzym zze = zzym.zzb;

    static {
        zzfk zzfk = new zzfk();
        zzf = zzfk;
        zzzo.zzy(zzfk.class, zzfk);
    }

    private zzfk() {
    }

    public static zzfk zzc(zzym zzym, zzzb zzzb) throws zzzw {
        return (zzfk) zzzo.zzE(zzf, zzym, zzzb);
    }

    public static zzfj zzd() {
        return (zzfj) zzf.zzs();
    }

    static /* synthetic */ void zzg(zzfk zzfk, zzym zzym) {
        zzym.getClass();
        zzfk.zze = zzym;
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
            return zzz(zzf, "\u0000\u0002\u0000\u0000\u0001\u0003\u0002\u0000\u0000\u0000\u0001\u000b\u0003\n", new Object[]{"zzb", "zze"});
        } else if (i2 == 3) {
            return new zzfk();
        } else {
            if (i2 == 4) {
                return new zzfj((zzfi) null);
            }
            if (i2 != 5) {
                return null;
            }
            return zzf;
        }
    }
}

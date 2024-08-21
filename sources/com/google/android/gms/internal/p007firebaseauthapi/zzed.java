package com.google.android.gms.internal.p007firebaseauthapi;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzed */
/* compiled from: com.google.firebase:firebase-auth@@20.0.1 */
public final class zzed extends zzzo<zzed, zzec> implements zzaas {
    /* access modifiers changed from: private */
    public static final zzed zzg;
    /* access modifiers changed from: private */
    public int zzb;
    private zzym zze = zzym.zzb;
    private zzej zzf;

    static {
        zzed zzed = new zzed();
        zzg = zzed;
        zzzo.zzy(zzed.class, zzed);
    }

    private zzed() {
    }

    public static zzed zzd(zzym zzym, zzzb zzzb) throws zzzw {
        return (zzed) zzzo.zzE(zzg, zzym, zzzb);
    }

    public static zzec zze() {
        return (zzec) zzg.zzs();
    }

    static /* synthetic */ void zzh(zzed zzed, zzym zzym) {
        zzym.getClass();
        zzed.zze = zzym;
    }

    static /* synthetic */ void zzi(zzed zzed, zzej zzej) {
        zzej.getClass();
        zzed.zzf = zzej;
    }

    public final int zza() {
        return this.zzb;
    }

    public final zzym zzb() {
        return this.zze;
    }

    public final zzej zzc() {
        zzej zzej = this.zzf;
        return zzej == null ? zzej.zzb() : zzej;
    }

    /* access modifiers changed from: protected */
    public final Object zzj(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzz(zzg, "\u0000\u0003\u0000\u0000\u0001\u0003\u0003\u0000\u0000\u0000\u0001\u000b\u0002\n\u0003\t", new Object[]{"zzb", "zze", "zzf"});
        } else if (i2 == 3) {
            return new zzed();
        } else {
            if (i2 == 4) {
                return new zzec((zzeb) null);
            }
            if (i2 != 5) {
                return null;
            }
            return zzg;
        }
    }
}

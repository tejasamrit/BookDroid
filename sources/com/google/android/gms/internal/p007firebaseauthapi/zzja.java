package com.google.android.gms.internal.p007firebaseauthapi;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzja */
/* compiled from: com.google.firebase:firebase-auth@@20.0.1 */
public final class zzja extends zzzo<zzja, zziz> implements zzaas {
    /* access modifiers changed from: private */
    public static final zzja zzf;
    /* access modifiers changed from: private */
    public int zzb;
    private zzym zze = zzym.zzb;

    static {
        zzja zzja = new zzja();
        zzf = zzja;
        zzzo.zzy(zzja.class, zzja);
    }

    private zzja() {
    }

    public static zzja zzc(zzym zzym, zzzb zzzb) throws zzzw {
        return (zzja) zzzo.zzE(zzf, zzym, zzzb);
    }

    public static zziz zzd() {
        return (zziz) zzf.zzs();
    }

    static /* synthetic */ void zzg(zzja zzja, zzym zzym) {
        zzym.getClass();
        zzja.zze = zzym;
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
            return new zzja();
        } else {
            if (i2 == 4) {
                return new zziz((zziy) null);
            }
            if (i2 != 5) {
                return null;
            }
            return zzf;
        }
    }
}

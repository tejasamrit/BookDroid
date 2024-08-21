package com.google.android.gms.internal.p007firebaseauthapi;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzgy */
/* compiled from: com.google.firebase:firebase-auth@@20.0.1 */
public final class zzgy extends zzzo<zzgy, zzgx> implements zzaas {
    /* access modifiers changed from: private */
    public static final zzgy zzf;
    private zzym zzb = zzym.zzb;
    private zzig zze;

    static {
        zzgy zzgy = new zzgy();
        zzf = zzgy;
        zzzo.zzy(zzgy.class, zzgy);
    }

    private zzgy() {
    }

    public static zzgy zzb(byte[] bArr, zzzb zzzb) throws zzzw {
        return (zzgy) zzzo.zzF(zzf, bArr, zzzb);
    }

    public static zzgx zzc() {
        return (zzgx) zzf.zzs();
    }

    static /* synthetic */ void zze(zzgy zzgy, zzym zzym) {
        zzym.getClass();
        zzgy.zzb = zzym;
    }

    static /* synthetic */ void zzf(zzgy zzgy, zzig zzig) {
        zzig.getClass();
        zzgy.zze = zzig;
    }

    public final zzym zza() {
        return this.zzb;
    }

    /* access modifiers changed from: protected */
    public final Object zzj(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzz(zzf, "\u0000\u0002\u0000\u0000\u0002\u0003\u0002\u0000\u0000\u0000\u0002\n\u0003\t", new Object[]{"zzb", "zze"});
        } else if (i2 == 3) {
            return new zzgy();
        } else {
            if (i2 == 4) {
                return new zzgx((zzgw) null);
            }
            if (i2 != 5) {
                return null;
            }
            return zzf;
        }
    }
}

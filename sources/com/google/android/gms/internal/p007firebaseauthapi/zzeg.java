package com.google.android.gms.internal.p007firebaseauthapi;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzeg */
/* compiled from: com.google.firebase:firebase-auth@@20.0.1 */
public final class zzeg extends zzzo<zzeg, zzef> implements zzaas {
    /* access modifiers changed from: private */
    public static final zzeg zzf;
    private int zzb;
    private zzej zze;

    static {
        zzeg zzeg = new zzeg();
        zzf = zzeg;
        zzzo.zzy(zzeg.class, zzeg);
    }

    private zzeg() {
    }

    public static zzeg zzc(zzym zzym, zzzb zzzb) throws zzzw {
        return (zzeg) zzzo.zzE(zzf, zzym, zzzb);
    }

    public final int zza() {
        return this.zzb;
    }

    public final zzej zzb() {
        zzej zzej = this.zze;
        return zzej == null ? zzej.zzb() : zzej;
    }

    /* access modifiers changed from: protected */
    public final Object zzj(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzz(zzf, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0000\u0000\u0001\u000b\u0002\t", new Object[]{"zzb", "zze"});
        } else if (i2 == 3) {
            return new zzeg();
        } else {
            if (i2 == 4) {
                return new zzef((zzee) null);
            }
            if (i2 != 5) {
                return null;
            }
            return zzf;
        }
    }
}

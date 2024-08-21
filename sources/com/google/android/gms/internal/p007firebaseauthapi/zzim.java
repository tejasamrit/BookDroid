package com.google.android.gms.internal.p007firebaseauthapi;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzim */
/* compiled from: com.google.firebase:firebase-auth@@20.0.1 */
public final class zzim extends zzzo<zzim, zzil> implements zzaas {
    /* access modifiers changed from: private */
    public static final zzim zze;
    private String zzb = "";

    static {
        zzim zzim = new zzim();
        zze = zzim;
        zzzo.zzy(zzim.class, zzim);
    }

    private zzim() {
    }

    public static zzim zzb(zzym zzym, zzzb zzzb) throws zzzw {
        return (zzim) zzzo.zzE(zze, zzym, zzzb);
    }

    public static zzim zzc() {
        return zze;
    }

    public final String zza() {
        return this.zzb;
    }

    /* access modifiers changed from: protected */
    public final Object zzj(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzz(zze, "\u0000\u0001\u0000\u0000\u0001\u0001\u0001\u0000\u0000\u0000\u0001Ȉ", new Object[]{"zzb"});
        } else if (i2 == 3) {
            return new zzim();
        } else {
            if (i2 == 4) {
                return new zzil((zzik) null);
            }
            if (i2 != 5) {
                return null;
            }
            return zze;
        }
    }
}

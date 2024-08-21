package com.google.android.gms.internal.p007firebaseauthapi;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzis */
/* compiled from: com.google.firebase:firebase-auth@@20.0.1 */
public final class zzis extends zzzo<zzis, zzir> implements zzaas {
    /* access modifiers changed from: private */
    public static final zzis zzf;
    private String zzb = "";
    private zzht zze;

    static {
        zzis zzis = new zzis();
        zzf = zzis;
        zzzo.zzy(zzis.class, zzis);
    }

    private zzis() {
    }

    public static zzis zzc(zzym zzym, zzzb zzzb) throws zzzw {
        return (zzis) zzzo.zzE(zzf, zzym, zzzb);
    }

    public static zzis zzd() {
        return zzf;
    }

    public final String zza() {
        return this.zzb;
    }

    public final zzht zzb() {
        zzht zzht = this.zze;
        return zzht == null ? zzht.zze() : zzht;
    }

    /* access modifiers changed from: protected */
    public final Object zzj(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzz(zzf, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0000\u0000\u0001Ȉ\u0002\t", new Object[]{"zzb", "zze"});
        } else if (i2 == 3) {
            return new zzis();
        } else {
            if (i2 == 4) {
                return new zzir((zziq) null);
            }
            if (i2 != 5) {
                return null;
            }
            return zzf;
        }
    }
}

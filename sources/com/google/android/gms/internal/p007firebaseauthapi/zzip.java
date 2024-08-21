package com.google.android.gms.internal.p007firebaseauthapi;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzip */
/* compiled from: com.google.firebase:firebase-auth@@20.0.1 */
public final class zzip extends zzzo<zzip, zzio> implements zzaas {
    /* access modifiers changed from: private */
    public static final zzip zzf;
    /* access modifiers changed from: private */
    public int zzb;
    private zzis zze;

    static {
        zzip zzip = new zzip();
        zzf = zzip;
        zzzo.zzy(zzip.class, zzip);
    }

    private zzip() {
    }

    public static zzip zzc(zzym zzym, zzzb zzzb) throws zzzw {
        return (zzip) zzzo.zzE(zzf, zzym, zzzb);
    }

    public static zzio zzd() {
        return (zzio) zzf.zzs();
    }

    static /* synthetic */ void zzg(zzip zzip, zzis zzis) {
        zzis.getClass();
        zzip.zze = zzis;
    }

    public final int zza() {
        return this.zzb;
    }

    public final zzis zzb() {
        zzis zzis = this.zze;
        return zzis == null ? zzis.zzd() : zzis;
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
            return new zzip();
        } else {
            if (i2 == 4) {
                return new zzio((zzin) null);
            }
            if (i2 != 5) {
                return null;
            }
            return zzf;
        }
    }
}

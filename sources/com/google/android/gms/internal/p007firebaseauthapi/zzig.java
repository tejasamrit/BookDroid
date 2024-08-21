package com.google.android.gms.internal.p007firebaseauthapi;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzig */
/* compiled from: com.google.firebase:firebase-auth@@20.0.1 */
public final class zzig extends zzzo<zzig, zzid> implements zzaas {
    /* access modifiers changed from: private */
    public static final zzig zzf;
    /* access modifiers changed from: private */
    public int zzb;
    private zzzt<zzif> zze = zzB();

    static {
        zzig zzig = new zzig();
        zzf = zzig;
        zzzo.zzy(zzig.class, zzig);
    }

    private zzig() {
    }

    public static zzid zzb() {
        return (zzid) zzf.zzs();
    }

    static /* synthetic */ void zze(zzig zzig, zzif zzif) {
        zzif.getClass();
        zzzt<zzif> zzzt = zzig.zze;
        if (!zzzt.zza()) {
            zzig.zze = zzzo.zzC(zzzt);
        }
        zzig.zze.add(zzif);
    }

    public final zzif zza(int i) {
        return (zzif) this.zze.get(0);
    }

    /* access modifiers changed from: protected */
    public final Object zzj(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzz(zzf, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0001\u0000\u0001\u000b\u0002\u001b", new Object[]{"zzb", "zze", zzif.class});
        } else if (i2 == 3) {
            return new zzig();
        } else {
            if (i2 == 4) {
                return new zzid((zzic) null);
            }
            if (i2 != 5) {
                return null;
            }
            return zzf;
        }
    }
}

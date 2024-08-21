package com.google.android.gms.internal.p007firebaseauthapi;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzif */
/* compiled from: com.google.firebase:firebase-auth@@20.0.1 */
public final class zzif extends zzzo<zzif, zzie> implements zzaas {
    /* access modifiers changed from: private */
    public static final zzif zzh;
    private String zzb = "";
    /* access modifiers changed from: private */
    public int zze;
    /* access modifiers changed from: private */
    public int zzf;
    /* access modifiers changed from: private */
    public int zzg;

    static {
        zzif zzif = new zzif();
        zzh = zzif;
        zzzo.zzy(zzif.class, zzif);
    }

    private zzif() {
    }

    public static zzie zzb() {
        return (zzie) zzh.zzs();
    }

    static /* synthetic */ void zzd(zzif zzif, String str) {
        str.getClass();
        zzif.zzb = str;
    }

    public final int zza() {
        return this.zzf;
    }

    /* access modifiers changed from: protected */
    public final Object zzj(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzz(zzh, "\u0000\u0004\u0000\u0000\u0001\u0004\u0004\u0000\u0000\u0000\u0001Ȉ\u0002\f\u0003\u000b\u0004\f", new Object[]{"zzb", "zze", "zzf", "zzg"});
        } else if (i2 == 3) {
            return new zzif();
        } else {
            if (i2 == 4) {
                return new zzie((zzic) null);
            }
            if (i2 != 5) {
                return null;
            }
            return zzh;
        }
    }
}

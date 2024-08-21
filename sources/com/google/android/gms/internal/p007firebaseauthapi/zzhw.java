package com.google.android.gms.internal.p007firebaseauthapi;

@Deprecated
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzhw */
/* compiled from: com.google.firebase:firebase-auth@@20.0.1 */
public final class zzhw extends zzzo<zzhw, zzhv> implements zzaas {
    /* access modifiers changed from: private */
    public static final zzhw zzi;
    private String zzb = "";
    private String zze = "";
    private int zzf;
    private boolean zzg;
    private String zzh = "";

    static {
        zzhw zzhw = new zzhw();
        zzi = zzhw;
        zzzo.zzy(zzhw.class, zzhw);
    }

    private zzhw() {
    }

    /* access modifiers changed from: protected */
    public final Object zzj(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzz(zzi, "\u0000\u0005\u0000\u0000\u0001\u0005\u0005\u0000\u0000\u0000\u0001Ȉ\u0002Ȉ\u0003\u000b\u0004\u0007\u0005Ȉ", new Object[]{"zzb", "zze", "zzf", "zzg", "zzh"});
        } else if (i2 == 3) {
            return new zzhw();
        } else {
            if (i2 == 4) {
                return new zzhv((zzhu) null);
            }
            if (i2 != 5) {
                return null;
            }
            return zzi;
        }
    }
}

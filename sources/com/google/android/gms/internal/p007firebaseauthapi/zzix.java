package com.google.android.gms.internal.p007firebaseauthapi;

@Deprecated
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzix */
/* compiled from: com.google.firebase:firebase-auth@@20.0.1 */
public final class zzix extends zzzo<zzix, zziw> implements zzaas {
    /* access modifiers changed from: private */
    public static final zzix zzf;
    private String zzb = "";
    private zzzt<zzhw> zze = zzB();

    static {
        zzix zzix = new zzix();
        zzf = zzix;
        zzzo.zzy(zzix.class, zzix);
    }

    private zzix() {
    }

    public static zzix zza() {
        return zzf;
    }

    /* access modifiers changed from: protected */
    public final Object zzj(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzz(zzf, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0001\u0000\u0001Ȉ\u0002\u001b", new Object[]{"zzb", "zze", zzhw.class});
        } else if (i2 == 3) {
            return new zzix();
        } else {
            if (i2 == 4) {
                return new zziw((zziv) null);
            }
            if (i2 != 5) {
                return null;
            }
            return zzf;
        }
    }
}

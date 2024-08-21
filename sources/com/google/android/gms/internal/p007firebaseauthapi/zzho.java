package com.google.android.gms.internal.p007firebaseauthapi;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzho */
/* compiled from: com.google.firebase:firebase-auth@@20.0.1 */
public final class zzho extends zzzo<zzho, zzhl> implements zzaas {
    /* access modifiers changed from: private */
    public static final zzho zzg;
    private String zzb = "";
    private zzym zze = zzym.zzb;
    /* access modifiers changed from: private */
    public int zzf;

    static {
        zzho zzho = new zzho();
        zzg = zzho;
        zzzo.zzy(zzho.class, zzho);
    }

    private zzho() {
    }

    public static zzhl zzd() {
        return (zzhl) zzg.zzs();
    }

    public static zzho zze() {
        return zzg;
    }

    static /* synthetic */ void zzg(zzho zzho, String str) {
        str.getClass();
        zzho.zzb = str;
    }

    static /* synthetic */ void zzh(zzho zzho, zzym zzym) {
        zzym.getClass();
        zzho.zze = zzym;
    }

    public final String zza() {
        return this.zzb;
    }

    public final zzym zzb() {
        return this.zze;
    }

    public final zzhn zzc() {
        zzhn zzb2 = zzhn.zzb(this.zzf);
        return zzb2 == null ? zzhn.UNRECOGNIZED : zzb2;
    }

    /* access modifiers changed from: protected */
    public final Object zzj(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzz(zzg, "\u0000\u0003\u0000\u0000\u0001\u0003\u0003\u0000\u0000\u0000\u0001Ȉ\u0002\n\u0003\f", new Object[]{"zzb", "zze", "zzf"});
        } else if (i2 == 3) {
            return new zzho();
        } else {
            if (i2 == 4) {
                return new zzhl((zzhk) null);
            }
            if (i2 != 5) {
                return null;
            }
            return zzg;
        }
    }
}

package com.google.android.gms.internal.p007firebaseauthapi;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzij */
/* compiled from: com.google.firebase:firebase-auth@@20.0.1 */
public final class zzij extends zzzo<zzij, zzii> implements zzaas {
    /* access modifiers changed from: private */
    public static final zzij zzf;
    /* access modifiers changed from: private */
    public int zzb;
    private zzim zze;

    static {
        zzij zzij = new zzij();
        zzf = zzij;
        zzzo.zzy(zzij.class, zzij);
    }

    private zzij() {
    }

    public static zzij zzc(zzym zzym, zzzb zzzb) throws zzzw {
        return (zzij) zzzo.zzE(zzf, zzym, zzzb);
    }

    public static zzii zzd() {
        return (zzii) zzf.zzs();
    }

    static /* synthetic */ void zzg(zzij zzij, zzim zzim) {
        zzim.getClass();
        zzij.zze = zzim;
    }

    public final int zza() {
        return this.zzb;
    }

    public final zzim zzb() {
        zzim zzim = this.zze;
        return zzim == null ? zzim.zzc() : zzim;
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
            return new zzij();
        } else {
            if (i2 == 4) {
                return new zzii((zzih) null);
            }
            if (i2 != 5) {
                return null;
            }
            return zzf;
        }
    }
}

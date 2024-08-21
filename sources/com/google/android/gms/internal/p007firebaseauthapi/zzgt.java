package com.google.android.gms.internal.p007firebaseauthapi;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzgt */
/* compiled from: com.google.firebase:firebase-auth@@20.0.1 */
public final class zzgt extends zzzo<zzgt, zzgs> implements zzaas {
    /* access modifiers changed from: private */
    public static final zzgt zzg;
    /* access modifiers changed from: private */
    public int zzb;
    /* access modifiers changed from: private */
    public int zze;
    private zzym zzf = zzym.zzb;

    static {
        zzgt zzgt = new zzgt();
        zzg = zzgt;
        zzzo.zzy(zzgt.class, zzgt);
    }

    private zzgt() {
    }

    public static zzgs zzd() {
        return (zzgs) zzg.zzs();
    }

    public static zzgt zze() {
        return zzg;
    }

    static /* synthetic */ void zzi(zzgt zzgt, zzym zzym) {
        zzym.getClass();
        zzgt.zzf = zzym;
    }

    public final zzgv zza() {
        zzgv zzb2 = zzgv.zzb(this.zzb);
        return zzb2 == null ? zzgv.UNRECOGNIZED : zzb2;
    }

    public final zzha zzb() {
        zzha zzb2 = zzha.zzb(this.zze);
        return zzb2 == null ? zzha.UNRECOGNIZED : zzb2;
    }

    public final zzym zzc() {
        return this.zzf;
    }

    /* access modifiers changed from: protected */
    public final Object zzj(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzz(zzg, "\u0000\u0003\u0000\u0000\u0001\u000b\u0003\u0000\u0000\u0000\u0001\f\u0002\f\u000b\n", new Object[]{"zzb", "zze", "zzf"});
        } else if (i2 == 3) {
            return new zzgt();
        } else {
            if (i2 == 4) {
                return new zzgs((zzgr) null);
            }
            if (i2 != 5) {
                return null;
            }
            return zzg;
        }
    }
}

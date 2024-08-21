package com.google.android.gms.internal.p007firebaseauthapi;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzgk */
/* compiled from: com.google.firebase:firebase-auth@@20.0.1 */
public final class zzgk extends zzzo<zzgk, zzgj> implements zzaas {
    /* access modifiers changed from: private */
    public static final zzgk zzg;
    private zzgt zzb;
    private zzge zze;
    /* access modifiers changed from: private */
    public int zzf;

    static {
        zzgk zzgk = new zzgk();
        zzg = zzgk;
        zzzo.zzy(zzgk.class, zzgk);
    }

    private zzgk() {
    }

    public static zzgj zzd() {
        return (zzgj) zzg.zzs();
    }

    public static zzgk zze() {
        return zzg;
    }

    static /* synthetic */ void zzg(zzgk zzgk, zzgt zzgt) {
        zzgt.getClass();
        zzgk.zzb = zzgt;
    }

    static /* synthetic */ void zzh(zzgk zzgk, zzge zzge) {
        zzge.getClass();
        zzgk.zze = zzge;
    }

    public final zzgt zza() {
        zzgt zzgt = this.zzb;
        return zzgt == null ? zzgt.zze() : zzgt;
    }

    public final zzge zzb() {
        zzge zzge = this.zze;
        return zzge == null ? zzge.zzc() : zzge;
    }

    public final zzgb zzc() {
        zzgb zzb2 = zzgb.zzb(this.zzf);
        return zzb2 == null ? zzgb.UNRECOGNIZED : zzb2;
    }

    /* access modifiers changed from: protected */
    public final Object zzj(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzz(zzg, "\u0000\u0003\u0000\u0000\u0001\u0003\u0003\u0000\u0000\u0000\u0001\t\u0002\t\u0003\f", new Object[]{"zzb", "zze", "zzf"});
        } else if (i2 == 3) {
            return new zzgk();
        } else {
            if (i2 == 4) {
                return new zzgj((zzgi) null);
            }
            if (i2 != 5) {
                return null;
            }
            return zzg;
        }
    }
}

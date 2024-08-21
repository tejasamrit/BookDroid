package com.google.android.gms.internal.p007firebaseauthapi;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzev */
/* compiled from: com.google.firebase:firebase-auth@@20.0.1 */
public final class zzev extends zzzo<zzev, zzeu> implements zzaas {
    /* access modifiers changed from: private */
    public static final zzev zzf;
    private zzey zzb;
    /* access modifiers changed from: private */
    public int zze;

    static {
        zzev zzev = new zzev();
        zzf = zzev;
        zzzo.zzy(zzev.class, zzev);
    }

    private zzev() {
    }

    public static zzev zzc(zzym zzym, zzzb zzzb) throws zzzw {
        return (zzev) zzzo.zzE(zzf, zzym, zzzb);
    }

    public static zzeu zzd() {
        return (zzeu) zzf.zzs();
    }

    public static zzev zze() {
        return zzf;
    }

    static /* synthetic */ void zzg(zzev zzev, zzey zzey) {
        zzey.getClass();
        zzev.zzb = zzey;
    }

    public final zzey zza() {
        zzey zzey = this.zzb;
        return zzey == null ? zzey.zzc() : zzey;
    }

    public final int zzb() {
        return this.zze;
    }

    /* access modifiers changed from: protected */
    public final Object zzj(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzz(zzf, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0000\u0000\u0001\t\u0002\u000b", new Object[]{"zzb", "zze"});
        } else if (i2 == 3) {
            return new zzev();
        } else {
            if (i2 == 4) {
                return new zzeu((zzet) null);
            }
            if (i2 != 5) {
                return null;
            }
            return zzf;
        }
    }
}

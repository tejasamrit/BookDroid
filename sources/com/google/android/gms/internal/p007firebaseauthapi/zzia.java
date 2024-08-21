package com.google.android.gms.internal.p007firebaseauthapi;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzia */
/* compiled from: com.google.firebase:firebase-auth@@20.0.1 */
public final class zzia extends zzzo<zzia, zzhz> implements zzaas {
    /* access modifiers changed from: private */
    public static final zzia zzh;
    private zzho zzb;
    /* access modifiers changed from: private */
    public int zze;
    /* access modifiers changed from: private */
    public int zzf;
    /* access modifiers changed from: private */
    public int zzg;

    static {
        zzia zzia = new zzia();
        zzh = zzia;
        zzzo.zzy(zzia.class, zzia);
    }

    private zzia() {
    }

    public static zzhz zzf() {
        return (zzhz) zzh.zzs();
    }

    static /* synthetic */ void zzh(zzia zzia, zzho zzho) {
        zzho.getClass();
        zzia.zzb = zzho;
    }

    public final boolean zza() {
        return this.zzb != null;
    }

    public final zzho zzb() {
        zzho zzho = this.zzb;
        return zzho == null ? zzho.zze() : zzho;
    }

    public final zzhq zzc() {
        zzhq zzb2 = zzhq.zzb(this.zze);
        return zzb2 == null ? zzhq.UNRECOGNIZED : zzb2;
    }

    public final int zzd() {
        return this.zzf;
    }

    public final zziu zze() {
        zziu zzb2 = zziu.zzb(this.zzg);
        return zzb2 == null ? zziu.UNRECOGNIZED : zzb2;
    }

    /* access modifiers changed from: protected */
    public final Object zzj(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzz(zzh, "\u0000\u0004\u0000\u0000\u0001\u0004\u0004\u0000\u0000\u0000\u0001\t\u0002\f\u0003\u000b\u0004\f", new Object[]{"zzb", "zze", "zzf", "zzg"});
        } else if (i2 == 3) {
            return new zzia();
        } else {
            if (i2 == 4) {
                return new zzhz((zzhx) null);
            }
            if (i2 != 5) {
                return null;
            }
            return zzh;
        }
    }
}

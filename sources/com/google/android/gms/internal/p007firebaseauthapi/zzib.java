package com.google.android.gms.internal.p007firebaseauthapi;

import java.util.List;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzib */
/* compiled from: com.google.firebase:firebase-auth@@20.0.1 */
public final class zzib extends zzzo<zzib, zzhy> implements zzaas {
    /* access modifiers changed from: private */
    public static final zzib zzf;
    /* access modifiers changed from: private */
    public int zzb;
    private zzzt<zzia> zze = zzB();

    static {
        zzib zzib = new zzib();
        zzf = zzib;
        zzzo.zzy(zzib.class, zzib);
    }

    private zzib() {
    }

    public static zzib zze(byte[] bArr, zzzb zzzb) throws zzzw {
        return (zzib) zzzo.zzF(zzf, bArr, zzzb);
    }

    public static zzhy zzf() {
        return (zzhy) zzf.zzs();
    }

    static /* synthetic */ void zzi(zzib zzib, zzia zzia) {
        zzia.getClass();
        zzzt<zzia> zzzt = zzib.zze;
        if (!zzzt.zza()) {
            zzib.zze = zzzo.zzC(zzzt);
        }
        zzib.zze.add(zzia);
    }

    public final int zza() {
        return this.zzb;
    }

    public final List<zzia> zzb() {
        return this.zze;
    }

    public final int zzc() {
        return this.zze.size();
    }

    public final zzia zzd(int i) {
        return (zzia) this.zze.get(i);
    }

    /* access modifiers changed from: protected */
    public final Object zzj(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzz(zzf, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0001\u0000\u0001\u000b\u0002\u001b", new Object[]{"zzb", "zze", zzia.class});
        } else if (i2 == 3) {
            return new zzib();
        } else {
            if (i2 == 4) {
                return new zzhy((zzhx) null);
            }
            if (i2 != 5) {
                return null;
            }
            return zzf;
        }
    }
}

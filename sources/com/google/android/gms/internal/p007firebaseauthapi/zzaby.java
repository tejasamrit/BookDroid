package com.google.android.gms.internal.p007firebaseauthapi;

import sun.misc.Unsafe;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzaby */
/* compiled from: com.google.firebase:firebase-auth@@20.0.1 */
final class zzaby extends zzacb {
    zzaby(Unsafe unsafe) {
        super(unsafe);
    }

    public final byte zza(Object obj, long j) {
        if (zzacc.zzb) {
            return zzacc.zzF(obj, j);
        }
        return zzacc.zzG(obj, j);
    }

    public final void zzb(Object obj, long j, byte b) {
        if (zzacc.zzb) {
            zzacc.zzH(obj, j, b);
        } else {
            zzacc.zzI(obj, j, b);
        }
    }

    public final boolean zzc(Object obj, long j) {
        if (zzacc.zzb) {
            return zzacc.zzw(obj, j);
        }
        return zzacc.zzx(obj, j);
    }

    public final void zzd(Object obj, long j, boolean z) {
        if (zzacc.zzb) {
            zzacc.zzH(obj, j, r3 ? (byte) 1 : 0);
        } else {
            zzacc.zzI(obj, j, r3 ? (byte) 1 : 0);
        }
    }

    public final float zze(Object obj, long j) {
        return Float.intBitsToFloat(zzj(obj, j));
    }

    public final void zzf(Object obj, long j, float f) {
        zzk(obj, j, Float.floatToIntBits(f));
    }

    public final double zzg(Object obj, long j) {
        return Double.longBitsToDouble(zzl(obj, j));
    }

    public final void zzh(Object obj, long j, double d) {
        zzm(obj, j, Double.doubleToLongBits(d));
    }
}

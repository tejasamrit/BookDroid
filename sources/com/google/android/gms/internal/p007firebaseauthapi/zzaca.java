package com.google.android.gms.internal.p007firebaseauthapi;

import sun.misc.Unsafe;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzaca */
/* compiled from: com.google.firebase:firebase-auth@@20.0.1 */
final class zzaca extends zzacb {
    zzaca(Unsafe unsafe) {
        super(unsafe);
    }

    public final byte zza(Object obj, long j) {
        return this.zza.getByte(obj, j);
    }

    public final void zzb(Object obj, long j, byte b) {
        this.zza.putByte(obj, j, b);
    }

    public final boolean zzc(Object obj, long j) {
        return this.zza.getBoolean(obj, j);
    }

    public final void zzd(Object obj, long j, boolean z) {
        this.zza.putBoolean(obj, j, z);
    }

    public final float zze(Object obj, long j) {
        return this.zza.getFloat(obj, j);
    }

    public final void zzf(Object obj, long j, float f) {
        this.zza.putFloat(obj, j, f);
    }

    public final double zzg(Object obj, long j) {
        return this.zza.getDouble(obj, j);
    }

    public final void zzh(Object obj, long j, double d) {
        this.zza.putDouble(obj, j, d);
    }
}

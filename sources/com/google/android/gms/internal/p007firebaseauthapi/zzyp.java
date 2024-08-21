package com.google.android.gms.internal.p007firebaseauthapi;

import java.io.IOException;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzyp */
/* compiled from: com.google.firebase:firebase-auth@@20.0.1 */
public abstract class zzyp {
    int zza;
    zzyq zzb;

    /* synthetic */ zzyp(zzyn zzyn) {
    }

    static zzyp zzs(byte[] bArr, int i, int i2, boolean z) {
        zzyo zzyo = new zzyo(bArr, 0, i2, z, (zzyn) null);
        try {
            zzyo.zzm(i2);
            return zzyo;
        } catch (zzzw e) {
            throw new IllegalArgumentException(e);
        }
    }

    public static int zzt(int i) {
        return (-(i & 1)) ^ (i >>> 1);
    }

    public static long zzu(long j) {
        return (-(j & 1)) ^ (j >>> 1);
    }

    public abstract int zza() throws IOException;

    public abstract void zzb(int i) throws zzzw;

    public abstract boolean zzc(int i) throws IOException;

    public abstract boolean zzd() throws IOException;

    public abstract String zze() throws IOException;

    public abstract String zzf() throws IOException;

    public abstract zzym zzg() throws IOException;

    public abstract int zzm(int i) throws zzzw;

    public abstract void zzn(int i);

    public abstract boolean zzo() throws IOException;

    public abstract int zzp();
}

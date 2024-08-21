package com.google.android.gms.internal.p007firebaseauthapi;

import java.util.Arrays;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzba */
/* compiled from: com.google.firebase:firebase-auth@@20.0.1 */
public final class zzba<P> {
    private final P zza;
    private final byte[] zzb;
    private final zzhq zzc;
    private final zziu zzd;

    zzba(P p, byte[] bArr, zzhq zzhq, zziu zziu, int i) {
        this.zza = p;
        this.zzb = Arrays.copyOf(bArr, bArr.length);
        this.zzc = zzhq;
        this.zzd = zziu;
    }

    public final P zza() {
        return this.zza;
    }

    public final zzhq zzb() {
        return this.zzc;
    }

    public final zziu zzc() {
        return this.zzd;
    }

    public final byte[] zzd() {
        byte[] bArr = this.zzb;
        if (bArr == null) {
            return null;
        }
        return Arrays.copyOf(bArr, bArr.length);
    }
}

package com.google.android.gms.internal.p007firebaseauthapi;

import java.security.GeneralSecurityException;
import java.security.InvalidAlgorithmParameterException;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzkn */
/* compiled from: com.google.firebase:firebase-auth@@20.0.1 */
public final class zzkn implements zzay {
    private final zzea zza;
    private final int zzb;

    public final void zza(byte[] bArr, byte[] bArr2) throws GeneralSecurityException {
        if (!zzjm.zza(this.zza.zza(bArr2, this.zzb), bArr)) {
            throw new GeneralSecurityException("invalid MAC");
        }
    }

    public zzkn(zzea zzea, int i) throws GeneralSecurityException {
        this.zza = zzea;
        this.zzb = i;
        if (i >= 10) {
            zzea.zza(new byte[0], i);
            return;
        }
        throw new InvalidAlgorithmParameterException("tag size too small, need at least 10 bytes");
    }
}

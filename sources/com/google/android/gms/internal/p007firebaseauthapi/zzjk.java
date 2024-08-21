package com.google.android.gms.internal.p007firebaseauthapi;

import java.security.GeneralSecurityException;
import java.security.spec.AlgorithmParameterSpec;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzjk */
/* compiled from: com.google.firebase:firebase-auth@@20.0.1 */
public final class zzjk implements zzag {
    private static final ThreadLocal<Cipher> zza = new zzjj();
    private final SecretKey zzb;

    public zzjk(byte[] bArr) throws GeneralSecurityException {
        zzkr.zza(bArr.length);
        this.zzb = new SecretKeySpec(bArr, "AES");
    }

    public final byte[] zza(byte[] bArr, byte[] bArr2) throws GeneralSecurityException {
        throw null;
    }

    public final byte[] zzb(byte[] bArr, byte[] bArr2) throws GeneralSecurityException {
        AlgorithmParameterSpec algorithmParameterSpec;
        int length = bArr.length;
        if (length >= 28) {
            if (!zzkq.zza() || zzkq.zzb() > 19) {
                algorithmParameterSpec = new GCMParameterSpec(128, bArr, 0, 12);
            } else {
                algorithmParameterSpec = new IvParameterSpec(bArr, 0, 12);
            }
            ThreadLocal<Cipher> threadLocal = zza;
            threadLocal.get().init(2, this.zzb, algorithmParameterSpec);
            if (bArr2.length != 0) {
                threadLocal.get().updateAAD(bArr2);
            }
            return threadLocal.get().doFinal(bArr, 12, length - 12);
        }
        throw new GeneralSecurityException("ciphertext too short");
    }
}

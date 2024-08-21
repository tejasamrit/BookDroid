package com.google.android.gms.internal.p007firebaseauthapi;

import java.security.GeneralSecurityException;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzbp */
/* compiled from: com.google.firebase:firebase-auth@@20.0.1 */
final class zzbp implements zzag {
    private final zzbc<zzag> zza;

    /* synthetic */ zzbp(zzbc zzbc, zzbo zzbo) {
        this.zza = zzbc;
    }

    public final byte[] zza(byte[] bArr, byte[] bArr2) throws GeneralSecurityException {
        throw null;
    }

    public final byte[] zzb(byte[] bArr, byte[] bArr2) throws GeneralSecurityException {
        int length = bArr.length;
        if (length > 5) {
            byte[] copyOfRange = Arrays.copyOfRange(bArr, 0, 5);
            byte[] copyOfRange2 = Arrays.copyOfRange(bArr, 5, length);
            for (zzba zza2 : this.zza.zza(copyOfRange)) {
                try {
                    return ((zzag) zza2.zza()).zzb(copyOfRange2, bArr2);
                } catch (GeneralSecurityException e) {
                    Logger zzd = zzbq.zza;
                    Level level = Level.INFO;
                    String valueOf = String.valueOf(e.toString());
                    zzd.logp(level, "com.google.crypto.tink.aead.AeadWrapper$WrappedAead", "decrypt", valueOf.length() != 0 ? "ciphertext prefix matches a key, but cannot decrypt: ".concat(valueOf) : new String("ciphertext prefix matches a key, but cannot decrypt: "));
                }
            }
        }
        for (zzba zza3 : this.zza.zza(zzaj.zza)) {
            try {
                return ((zzag) zza3.zza()).zzb(bArr, bArr2);
            } catch (GeneralSecurityException unused) {
            }
        }
        throw new GeneralSecurityException("decryption failed");
    }
}

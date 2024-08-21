package com.google.android.gms.internal.p007firebaseauthapi;

import java.security.GeneralSecurityException;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzdy */
/* compiled from: com.google.firebase:firebase-auth@@20.0.1 */
final class zzdy implements zzay {
    private final zzbc<zzay> zza;
    private final byte[] zzb = {0};

    /* synthetic */ zzdy(zzbc zzbc, zzdx zzdx) {
        this.zza = zzbc;
    }

    public final void zza(byte[] bArr, byte[] bArr2) throws GeneralSecurityException {
        int length = bArr.length;
        if (length > 5) {
            byte[] copyOf = Arrays.copyOf(bArr, 5);
            byte[] copyOfRange = Arrays.copyOfRange(bArr, 5, length);
            for (zzba next : this.zza.zza(copyOf)) {
                try {
                    if (next.zzc().equals(zziu.LEGACY)) {
                        ((zzay) next.zza()).zza(copyOfRange, zzjm.zzb(bArr2, this.zzb));
                        return;
                    }
                    ((zzay) next.zza()).zza(copyOfRange, bArr2);
                    return;
                } catch (GeneralSecurityException e) {
                    Logger zzd = zzdz.zza;
                    Level level = Level.INFO;
                    String valueOf = String.valueOf(e);
                    StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 45);
                    sb.append("tag prefix matches a key, but cannot verify: ");
                    sb.append(valueOf);
                    zzd.logp(level, "com.google.crypto.tink.mac.MacWrapper$WrappedMac", "verifyMac", sb.toString());
                }
            }
            for (zzba zza2 : this.zza.zza(zzaj.zza)) {
                try {
                    ((zzay) zza2.zza()).zza(bArr, bArr2);
                    return;
                } catch (GeneralSecurityException unused) {
                }
            }
            throw new GeneralSecurityException("invalid MAC");
        }
        throw new GeneralSecurityException("tag too short");
    }
}

package com.google.android.gms.internal.p007firebaseauthapi;

import java.security.GeneralSecurityException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzcf */
/* compiled from: com.google.firebase:firebase-auth@@20.0.1 */
public final class zzcf extends zzas<zzfq> {
    zzcf() {
        super(zzfq.class, new zzcd(zzag.class));
    }

    public static void zzj(boolean z) throws GeneralSecurityException {
        if (zzk()) {
            zzbk.zza(new zzcf(), true);
        }
    }

    private static boolean zzk() {
        try {
            Cipher.getInstance("AES/GCM-SIV/NoPadding");
            return true;
        } catch (NoSuchAlgorithmException | NoSuchPaddingException unused) {
            return false;
        }
    }

    public final String zzb() {
        return "type.googleapis.com/google.crypto.tink.AesGcmSivKey";
    }

    public final zzhn zzc() {
        return zzhn.SYMMETRIC;
    }

    public final /* bridge */ /* synthetic */ zzaar zzd(zzym zzym) throws zzzw {
        return zzfq.zzc(zzym, zzzb.zza());
    }

    public final /* bridge */ /* synthetic */ void zze(zzaar zzaar) throws GeneralSecurityException {
        zzfq zzfq = (zzfq) zzaar;
        zzkr.zzb(zzfq.zza(), 0);
        zzkr.zza(zzfq.zzb().zzc());
    }

    public final zzaq<zzft, zzfq> zzi() {
        return new zzce(this, zzft.class);
    }
}

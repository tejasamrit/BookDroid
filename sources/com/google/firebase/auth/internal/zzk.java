package com.google.firebase.auth.internal;

import android.content.Context;
import android.util.Base64;
import android.util.Log;
import com.bumptech.glide.load.Key;
import com.google.android.gms.internal.p007firebaseauthapi.zzah;
import com.google.android.gms.internal.p007firebaseauthapi.zzak;
import com.google.android.gms.internal.p007firebaseauthapi.zzda;
import com.google.android.gms.internal.p007firebaseauthapi.zzdf;
import com.google.android.gms.internal.p007firebaseauthapi.zzdj;
import com.google.android.gms.internal.p007firebaseauthapi.zzdk;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;

/* compiled from: com.google.firebase:firebase-auth@@20.0.1 */
public final class zzk {
    private static zzk zzc;
    private final String zza;
    private zzdk zzb;

    private zzk(Context context, String str, boolean z) {
        this.zza = str;
        try {
            zzda.zza();
            zzdj zzdj = new zzdj();
            zzdj.zza(context, "GenericIdpKeyset", String.format("com.google.firebase.auth.api.crypto.%s", new Object[]{str}));
            zzdj.zzc(zzdf.zza);
            zzdj.zzb(String.format("android-keystore://firebear_master_key_id.%s", new Object[]{str}));
            this.zzb = zzdj.zzd();
        } catch (IOException | GeneralSecurityException e) {
            String valueOf = String.valueOf(e.getMessage());
            Log.e("FirebearCryptoHelper", valueOf.length() != 0 ? "Exception encountered during crypto setup:\n".concat(valueOf) : new String("Exception encountered during crypto setup:\n"));
        }
    }

    public static zzk zza(Context context, String str) {
        String str2;
        zzk zzk = zzc;
        if (zzk == null || ((str2 = zzk.zza) != str && (str2 == null || !str2.equals(str)))) {
            zzc = new zzk(context, str, true);
        }
        return zzc;
    }

    public final String zzb() {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            this.zzb.zza().zzf().zze(zzah.zza(byteArrayOutputStream));
            return Base64.encodeToString(byteArrayOutputStream.toByteArray(), 8);
        } catch (IOException | GeneralSecurityException e) {
            String valueOf = String.valueOf(e.getMessage());
            Log.e("FirebearCryptoHelper", valueOf.length() != 0 ? "Exception encountered when attempting to get Public Key:\n".concat(valueOf) : new String("Exception encountered when attempting to get Public Key:\n"));
            return null;
        }
    }

    public final String zzc(String str) {
        try {
            return new String(((zzak) this.zzb.zza().zzh(zzak.class)).zza(Base64.decode(str, 8), (byte[]) null), Key.STRING_CHARSET_NAME);
        } catch (UnsupportedEncodingException | GeneralSecurityException e) {
            String valueOf = String.valueOf(e.getMessage());
            Log.e("FirebearCryptoHelper", valueOf.length() != 0 ? "Exception encountered while decrypting bytes:\n".concat(valueOf) : new String("Exception encountered while decrypting bytes:\n"));
            return null;
        }
    }
}

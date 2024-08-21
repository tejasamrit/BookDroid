package com.google.android.gms.internal.p007firebaseauthapi;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzti */
/* compiled from: com.google.firebase:firebase-auth@@20.0.1 */
public final /* synthetic */ class zzti {
    public static String zza(zztj zztj, String str) {
        try {
            String str2 = new String(MessageDigest.getInstance("SHA-256").digest(str.getBytes()));
            int length = str2.length();
            int i = 0;
            while (i < length) {
                if (zzk.zza(str2.charAt(i))) {
                    char[] charArray = str2.toCharArray();
                    while (i < length) {
                        char c = charArray[i];
                        if (zzk.zza(c)) {
                            charArray[i] = (char) (c ^ ' ');
                        }
                        i++;
                    }
                    return String.valueOf(charArray);
                }
                i++;
            }
            return str2;
        } catch (NoSuchAlgorithmException unused) {
            zztj.zza.mo13204e("Failed to get SHA-256 MessageDigest", new Object[0]);
            return null;
        }
    }
}

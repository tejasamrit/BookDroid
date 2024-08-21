package com.google.android.gms.internal.p007firebaseauthapi;

import android.os.Build;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.KeyStore;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzdm */
/* compiled from: com.google.firebase:firebase-auth@@20.0.1 */
public final class zzdm {
    KeyStore zza = null;

    public zzdm() {
        if (Build.VERSION.SDK_INT >= 23) {
            try {
                KeyStore instance = KeyStore.getInstance("AndroidKeyStore");
                this.zza = instance;
                instance.load((KeyStore.LoadStoreParameter) null);
            } catch (IOException | GeneralSecurityException e) {
                throw new IllegalStateException(e);
            }
        } else {
            throw new IllegalStateException("need Android Keystore on Android M or newer");
        }
    }
}

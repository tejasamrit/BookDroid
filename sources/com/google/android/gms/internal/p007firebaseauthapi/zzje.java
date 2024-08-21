package com.google.android.gms.internal.p007firebaseauthapi;

import java.security.GeneralSecurityException;
import javax.crypto.Cipher;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzje */
/* compiled from: com.google.firebase:firebase-auth@@20.0.1 */
final class zzje extends ThreadLocal<Cipher> {
    zzje() {
    }

    protected static final Cipher zza() {
        try {
            return zzjy.zza.zza("AES/CTR/NoPadding");
        } catch (GeneralSecurityException e) {
            throw new IllegalStateException(e);
        }
    }

    /* access modifiers changed from: protected */
    public final /* bridge */ /* synthetic */ Object initialValue() {
        return zza();
    }
}

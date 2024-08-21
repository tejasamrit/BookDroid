package com.google.android.gms.internal.p007firebaseauthapi;

import java.security.GeneralSecurityException;
import javax.crypto.Cipher;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzjh */
/* compiled from: com.google.firebase:firebase-auth@@20.0.1 */
final class zzjh extends ThreadLocal<Cipher> {
    zzjh() {
    }

    protected static final Cipher zza() {
        try {
            return zzjy.zza.zza("AES/CTR/NOPADDING");
        } catch (GeneralSecurityException e) {
            throw new IllegalStateException(e);
        }
    }

    /* access modifiers changed from: protected */
    public final /* bridge */ /* synthetic */ Object initialValue() {
        return zza();
    }
}

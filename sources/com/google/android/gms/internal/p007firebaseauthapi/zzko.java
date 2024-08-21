package com.google.android.gms.internal.p007firebaseauthapi;

import java.security.SecureRandom;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzko */
/* compiled from: com.google.firebase:firebase-auth@@20.0.1 */
final class zzko extends ThreadLocal<SecureRandom> {
    zzko() {
    }

    /* access modifiers changed from: protected */
    public final /* bridge */ /* synthetic */ Object initialValue() {
        SecureRandom secureRandom = new SecureRandom();
        secureRandom.nextLong();
        return secureRandom;
    }
}

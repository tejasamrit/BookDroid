package com.google.android.gms.internal.p007firebaseauthapi;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzvu */
/* compiled from: com.google.firebase:firebase-auth@@20.0.1 */
public enum zzvu {
    REFRESH_TOKEN("refresh_token"),
    AUTHORIZATION_CODE("authorization_code");
    
    private final String zzc;

    private zzvu(String str) {
        this.zzc = str;
    }

    public final String toString() {
        return this.zzc;
    }
}

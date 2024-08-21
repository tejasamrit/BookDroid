package com.google.android.gms.internal.p007firebaseauthapi;

import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzug */
/* compiled from: com.google.firebase:firebase-auth@@20.0.1 */
final class zzug implements zzul {
    final /* synthetic */ PhoneAuthCredential zza;

    zzug(zzuk zzuk, PhoneAuthCredential phoneAuthCredential) {
        this.zza = phoneAuthCredential;
    }

    public final void zza(PhoneAuthProvider.OnVerificationStateChangedCallbacks onVerificationStateChangedCallbacks, Object... objArr) {
        onVerificationStateChangedCallbacks.onVerificationCompleted(this.zza);
    }
}

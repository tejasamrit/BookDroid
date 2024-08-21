package com.google.android.gms.internal.p007firebaseauthapi;

import com.google.firebase.auth.PhoneAuthProvider;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzuf */
/* compiled from: com.google.firebase:firebase-auth@@20.0.1 */
final class zzuf implements zzul {
    final /* synthetic */ String zza;

    zzuf(zzuk zzuk, String str) {
        this.zza = str;
    }

    public final void zza(PhoneAuthProvider.OnVerificationStateChangedCallbacks onVerificationStateChangedCallbacks, Object... objArr) {
        onVerificationStateChangedCallbacks.onCodeSent(this.zza, PhoneAuthProvider.ForceResendingToken.zza());
    }
}

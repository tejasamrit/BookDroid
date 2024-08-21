package com.google.android.gms.internal.p007firebaseauthapi;

import com.google.firebase.auth.PhoneAuthProvider;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzuh */
/* compiled from: com.google.firebase:firebase-auth@@20.0.1 */
final class zzuh implements zzul {
    final /* synthetic */ String zza;

    zzuh(zzuk zzuk, String str) {
        this.zza = str;
    }

    public final void zza(PhoneAuthProvider.OnVerificationStateChangedCallbacks onVerificationStateChangedCallbacks, Object... objArr) {
        onVerificationStateChangedCallbacks.onCodeAutoRetrievalTimeOut(this.zza);
    }
}

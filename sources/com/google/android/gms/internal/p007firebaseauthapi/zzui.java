package com.google.android.gms.internal.p007firebaseauthapi;

import com.google.android.gms.common.api.Status;
import com.google.firebase.auth.PhoneAuthProvider;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzui */
/* compiled from: com.google.firebase:firebase-auth@@20.0.1 */
final class zzui implements zzul {
    final /* synthetic */ Status zza;

    zzui(zzuk zzuk, Status status) {
        this.zza = status;
    }

    public final void zza(PhoneAuthProvider.OnVerificationStateChangedCallbacks onVerificationStateChangedCallbacks, Object... objArr) {
        onVerificationStateChangedCallbacks.onVerificationFailed(zzte.zza(this.zza));
    }
}

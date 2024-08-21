package com.google.android.gms.internal.p007firebaseauthapi;

import com.google.android.gms.common.logging.Logger;
import com.google.android.gms.tasks.OnFailureListener;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzuz */
/* compiled from: com.google.firebase:firebase-auth@@20.0.1 */
final class zzuz implements OnFailureListener {
    zzuz(zzvd zzvd) {
    }

    public final void onFailure(Exception exc) {
        Logger zzh = zzvd.zza;
        String valueOf = String.valueOf(exc.getMessage());
        zzh.mo13204e(valueOf.length() != 0 ? "SmsRetrieverClient failed to start: ".concat(valueOf) : new String("SmsRetrieverClient failed to start: "), new Object[0]);
    }
}

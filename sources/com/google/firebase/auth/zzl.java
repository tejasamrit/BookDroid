package com.google.firebase.auth;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.internal.IdTokenListener;
import com.google.firebase.internal.InternalTokenResult;

/* compiled from: com.google.firebase:firebase-auth@@20.0.1 */
final class zzl implements Runnable {
    final /* synthetic */ InternalTokenResult zza;
    final /* synthetic */ FirebaseAuth zzb;

    zzl(FirebaseAuth firebaseAuth, InternalTokenResult internalTokenResult) {
        this.zzb = firebaseAuth;
        this.zza = internalTokenResult;
    }

    public final void run() {
        for (IdTokenListener onIdTokenChanged : this.zzb.zzc) {
            onIdTokenChanged.onIdTokenChanged(this.zza);
        }
        for (FirebaseAuth.IdTokenListener onIdTokenChanged2 : this.zzb.zzb) {
            onIdTokenChanged2.onIdTokenChanged(this.zzb);
        }
    }
}

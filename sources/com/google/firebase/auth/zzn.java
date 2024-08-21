package com.google.firebase.auth;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.internal.p007firebaseauthapi.zzwg;
import com.google.firebase.auth.internal.zzbk;

/* compiled from: com.google.firebase:firebase-auth@@20.0.1 */
final class zzn implements zzbk {
    final /* synthetic */ FirebaseAuth zza;

    zzn(FirebaseAuth firebaseAuth) {
        this.zza = firebaseAuth;
    }

    public final void zza(zzwg zzwg, FirebaseUser firebaseUser) {
        this.zza.zza(firebaseUser, zzwg, true, true);
    }

    public final void zzb(Status status) {
        int statusCode = status.getStatusCode();
        if (statusCode == 17011 || statusCode == 17021 || statusCode == 17005) {
            this.zza.signOut();
        }
    }
}

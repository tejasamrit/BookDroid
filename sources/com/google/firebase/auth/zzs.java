package com.google.firebase.auth;

import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.p007firebaseauthapi.zzwg;
import com.google.firebase.auth.internal.zzg;

/* compiled from: com.google.firebase:firebase-auth@@20.0.1 */
class zzs implements zzg {
    final /* synthetic */ FirebaseAuth zza;

    zzs(FirebaseAuth firebaseAuth) {
        this.zza = firebaseAuth;
    }

    public final void zza(zzwg zzwg, FirebaseUser firebaseUser) {
        Preconditions.checkNotNull(zzwg);
        Preconditions.checkNotNull(firebaseUser);
        firebaseUser.zzf(zzwg);
        this.zza.zza(firebaseUser, zzwg, true, false);
    }
}

package com.google.firebase.auth;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.p007firebaseauthapi.zzwg;
import com.google.firebase.auth.internal.zzbk;

/* compiled from: com.google.firebase:firebase-auth@@20.0.1 */
final class zzt implements zzbk {
    final /* synthetic */ FirebaseAuth zza;

    zzt(FirebaseAuth firebaseAuth) {
        this.zza = firebaseAuth;
    }

    public final void zza(zzwg zzwg, FirebaseUser firebaseUser) {
        Preconditions.checkNotNull(zzwg);
        Preconditions.checkNotNull(firebaseUser);
        firebaseUser.zzf(zzwg);
        this.zza.zza(firebaseUser, zzwg, true, true);
    }

    public final void zzb(Status status) {
        if (status.getStatusCode() == 17011 || status.getStatusCode() == 17021 || status.getStatusCode() == 17005 || status.getStatusCode() == 17091) {
            this.zza.signOut();
        }
    }
}

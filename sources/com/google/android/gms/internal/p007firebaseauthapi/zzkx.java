package com.google.android.gms.internal.p007firebaseauthapi;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzkx */
/* compiled from: com.google.firebase:firebase-auth@@20.0.1 */
final class zzkx extends zzku {
    private final zzkw zza = new zzkw();

    zzkx() {
    }

    public final void zza(Throwable th, Throwable th2) {
        if (th2 != th) {
            this.zza.zza(th, true).add(th2);
            return;
        }
        throw new IllegalArgumentException("Self suppression is not allowed.", th2);
    }
}

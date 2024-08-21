package com.google.android.gms.internal.p007firebaseauthapi;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzza */
/* compiled from: com.google.firebase:firebase-auth@@20.0.1 */
final class zzza {
    private final Object zza;
    private final int zzb;

    zzza(Object obj, int i) {
        this.zza = obj;
        this.zzb = i;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof zzza)) {
            return false;
        }
        zzza zzza = (zzza) obj;
        if (this.zza == zzza.zza && this.zzb == zzza.zzb) {
            return true;
        }
        return false;
    }

    public final int hashCode() {
        return (System.identityHashCode(this.zza) * 65535) + this.zzb;
    }
}

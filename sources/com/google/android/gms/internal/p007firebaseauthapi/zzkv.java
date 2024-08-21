package com.google.android.gms.internal.p007firebaseauthapi;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzkv */
/* compiled from: com.google.firebase:firebase-auth@@20.0.1 */
final class zzkv extends WeakReference<Throwable> {
    private final int zza;

    public zzkv(Throwable th, ReferenceQueue<Throwable> referenceQueue) {
        super(th, referenceQueue);
        this.zza = System.identityHashCode(th);
    }

    public final boolean equals(Object obj) {
        if (obj != null && obj.getClass() == getClass()) {
            if (this == obj) {
                return true;
            }
            zzkv zzkv = (zzkv) obj;
            return this.zza == zzkv.zza && get() == zzkv.get();
        }
    }

    public final int hashCode() {
        return this.zza;
    }
}

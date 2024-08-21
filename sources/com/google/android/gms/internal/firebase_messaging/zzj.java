package com.google.android.gms.internal.firebase_messaging;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

/* compiled from: com.google.firebase:firebase-messaging@@21.0.1 */
final class zzj extends WeakReference<Throwable> {
    private final int zza;

    public zzj(Throwable th, ReferenceQueue<Throwable> referenceQueue) {
        super(th, referenceQueue);
        this.zza = System.identityHashCode(th);
    }

    public final boolean equals(Object obj) {
        if (obj != null && obj.getClass() == getClass()) {
            if (this == obj) {
                return true;
            }
            zzj zzj = (zzj) obj;
            return this.zza == zzj.zza && get() == zzj.get();
        }
    }

    public final int hashCode() {
        return this.zza;
    }
}

package com.google.android.gms.internal.p007firebaseauthapi;

import com.google.firebase.messaging.Constants;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzaaz */
/* compiled from: com.google.firebase:firebase-auth@@20.0.1 */
final class zzaaz {
    private static final zzaaz zza = new zzaaz();
    private final zzabe zzb = new zzaaj();
    private final ConcurrentMap<Class<?>, zzabd<?>> zzc = new ConcurrentHashMap();

    private zzaaz() {
    }

    public static zzaaz zza() {
        return zza;
    }

    public final <T> zzabd<T> zzb(Class<T> cls) {
        zzzu.zzb(cls, Constants.FirelogAnalytics.PARAM_MESSAGE_TYPE);
        zzabd<T> zzabd = (zzabd) this.zzc.get(cls);
        if (zzabd == null) {
            zzabd = this.zzb.zza(cls);
            zzzu.zzb(cls, Constants.FirelogAnalytics.PARAM_MESSAGE_TYPE);
            zzzu.zzb(zzabd, "schema");
            zzabd<T> putIfAbsent = this.zzc.putIfAbsent(cls, zzabd);
            return putIfAbsent == null ? zzabd : putIfAbsent;
        }
    }
}

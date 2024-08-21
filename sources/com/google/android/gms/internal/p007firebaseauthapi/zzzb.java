package com.google.android.gms.internal.p007firebaseauthapi;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzzb */
/* compiled from: com.google.firebase:firebase-auth@@20.0.1 */
public final class zzzb {
    static final zzzb zza = new zzzb(true);
    private static volatile boolean zzb = false;
    private static volatile zzzb zzc;
    private final Map<zzza, zzzm<?, ?>> zzd;

    zzzb() {
        this.zzd = new HashMap();
    }

    public static zzzb zza() {
        zzzb zzzb = zzc;
        if (zzzb == null) {
            synchronized (zzzb.class) {
                zzzb = zzc;
                if (zzzb == null) {
                    zzzb = zza;
                    zzc = zzzb;
                }
            }
        }
        return zzzb;
    }

    public final <ContainingType extends zzaar> zzzm<ContainingType, ?> zzb(ContainingType containingtype, int i) {
        return this.zzd.get(new zzza(containingtype, i));
    }

    zzzb(boolean z) {
        this.zzd = Collections.emptyMap();
    }
}

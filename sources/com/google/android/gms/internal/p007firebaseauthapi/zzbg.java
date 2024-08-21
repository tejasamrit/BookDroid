package com.google.android.gms.internal.p007firebaseauthapi;

import java.security.GeneralSecurityException;
import java.util.Set;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzbg */
/* compiled from: com.google.firebase:firebase-auth@@20.0.1 */
final class zzbg implements zzbj {
    final /* synthetic */ zzas zza;

    zzbg(zzas zzas) {
        this.zza = zzas;
    }

    public final <Q> zzam<Q> zza(Class<Q> cls) throws GeneralSecurityException {
        try {
            return new zzao(this.zza, cls);
        } catch (IllegalArgumentException e) {
            throw new GeneralSecurityException("Primitive type not supported", e);
        }
    }

    public final zzam<?> zzb() {
        zzas zzas = this.zza;
        return new zzao(zzas, zzas.zzh());
    }

    public final Class<?> zzc() {
        return this.zza.getClass();
    }

    public final Set<Class<?>> zzd() {
        return this.zza.zzg();
    }

    public final Class<?> zze() {
        return null;
    }
}

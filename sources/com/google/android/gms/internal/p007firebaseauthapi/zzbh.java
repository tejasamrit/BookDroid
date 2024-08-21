package com.google.android.gms.internal.p007firebaseauthapi;

import java.security.GeneralSecurityException;
import java.util.Set;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzbh */
/* compiled from: com.google.firebase:firebase-auth@@20.0.1 */
final class zzbh implements zzbj {
    final /* synthetic */ zzbf zza;
    final /* synthetic */ zzas zzb;

    zzbh(zzbf zzbf, zzas zzas) {
        this.zza = zzbf;
        this.zzb = zzas;
    }

    public final <Q> zzam<Q> zza(Class<Q> cls) throws GeneralSecurityException {
        try {
            return new zzbe(this.zza, this.zzb, cls);
        } catch (IllegalArgumentException e) {
            throw new GeneralSecurityException("Primitive type not supported", e);
        }
    }

    public final zzam<?> zzb() {
        zzbf zzbf = this.zza;
        return new zzbe(zzbf, this.zzb, zzbf.zzh());
    }

    public final Class<?> zzc() {
        return this.zza.getClass();
    }

    public final Set<Class<?>> zzd() {
        return this.zza.zzg();
    }

    public final Class<?> zze() {
        return this.zzb.getClass();
    }
}

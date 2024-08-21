package com.google.android.gms.internal.p007firebaseauthapi;

import java.security.GeneralSecurityException;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzdw */
/* compiled from: com.google.firebase:firebase-auth@@20.0.1 */
public final class zzdw {
    @Deprecated
    public static final zzix zza;
    @Deprecated
    public static final zzix zzb;
    @Deprecated
    public static final zzix zzc;

    static {
        new zzdv();
        zzix zza2 = zzix.zza();
        zza = zza2;
        zzb = zza2;
        zzc = zza2;
        try {
            zza();
        } catch (GeneralSecurityException e) {
            throw new ExceptionInInitializerError(e);
        }
    }

    public static void zza() throws GeneralSecurityException {
        zzbk.zza(new zzdv(), true);
        zzbk.zza(new zzds(), true);
        zzbk.zzc(new zzdz());
    }
}

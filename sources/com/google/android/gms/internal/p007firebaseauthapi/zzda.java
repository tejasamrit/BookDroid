package com.google.android.gms.internal.p007firebaseauthapi;

import java.security.GeneralSecurityException;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzda */
/* compiled from: com.google.firebase:firebase-auth@@20.0.1 */
public final class zzda {
    @Deprecated
    public static final zzix zza = zzix.zza();
    @Deprecated
    public static final zzix zzb = zzix.zza();
    @Deprecated
    public static final zzix zzc = zzix.zza();

    static {
        new zzcz();
        new zzcx();
        try {
            zza();
        } catch (GeneralSecurityException e) {
            throw new ExceptionInInitializerError(e);
        }
    }

    public static void zza() throws GeneralSecurityException {
        zzbm.zza();
        zzbk.zzb(new zzcx(), new zzcz(), true);
        zzbk.zzc(new zzdc());
        zzbk.zzc(new zzde());
    }
}

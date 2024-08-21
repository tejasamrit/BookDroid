package com.google.android.gms.internal.p007firebaseauthapi;

import java.io.IOException;
import java.security.GeneralSecurityException;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzdk */
/* compiled from: com.google.firebase:firebase-auth@@20.0.1 */
public final class zzdk {
    public static final /* synthetic */ int zza = 0;
    /* access modifiers changed from: private */
    public static final String zzb = "zzdk";
    private final zzav zzc;
    private final zzag zzd;
    private final zzau zze;

    /* synthetic */ zzdk(zzdj zzdj, zzdi zzdi) throws GeneralSecurityException, IOException {
        this.zzc = zzdj.zza;
        this.zzd = zzdj.zzc;
        this.zze = zzdj.zze;
    }

    public final synchronized zzat zza() throws GeneralSecurityException {
        return this.zze.zzc();
    }
}

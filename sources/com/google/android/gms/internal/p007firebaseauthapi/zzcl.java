package com.google.android.gms.internal.p007firebaseauthapi;

import java.security.GeneralSecurityException;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzcl */
/* compiled from: com.google.firebase:firebase-auth@@20.0.1 */
public final class zzcl extends zzas<zzij> {
    zzcl() {
        super(zzij.class, new zzcj(zzag.class));
    }

    public final String zzb() {
        return "type.googleapis.com/google.crypto.tink.KmsAeadKey";
    }

    public final zzhn zzc() {
        return zzhn.REMOTE;
    }

    public final /* bridge */ /* synthetic */ zzaar zzd(zzym zzym) throws zzzw {
        return zzij.zzc(zzym, zzzb.zza());
    }

    public final /* bridge */ /* synthetic */ void zze(zzaar zzaar) throws GeneralSecurityException {
        zzkr.zzb(((zzij) zzaar).zza(), 0);
    }

    public final zzaq<zzim, zzij> zzi() {
        return new zzck(this, zzim.class);
    }
}

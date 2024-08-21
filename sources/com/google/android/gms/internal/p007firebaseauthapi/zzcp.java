package com.google.android.gms.internal.p007firebaseauthapi;

import java.security.GeneralSecurityException;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzcp */
/* compiled from: com.google.firebase:firebase-auth@@20.0.1 */
public final class zzcp extends zzas<zzip> {
    zzcp() {
        super(zzip.class, new zzcn(zzag.class));
    }

    public final String zzb() {
        return "type.googleapis.com/google.crypto.tink.KmsEnvelopeAeadKey";
    }

    public final zzhn zzc() {
        return zzhn.REMOTE;
    }

    public final /* bridge */ /* synthetic */ zzaar zzd(zzym zzym) throws zzzw {
        return zzip.zzc(zzym, zzzb.zza());
    }

    public final /* bridge */ /* synthetic */ void zze(zzaar zzaar) throws GeneralSecurityException {
        zzkr.zzb(((zzip) zzaar).zza(), 0);
    }

    public final zzaq<zzis, zzip> zzi() {
        return new zzco(this, zzis.class);
    }
}

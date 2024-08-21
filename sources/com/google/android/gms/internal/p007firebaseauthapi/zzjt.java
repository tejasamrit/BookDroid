package com.google.android.gms.internal.p007firebaseauthapi;

import java.security.GeneralSecurityException;
import java.security.interfaces.ECPublicKey;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzjt */
/* compiled from: com.google.firebase:firebase-auth@@20.0.1 */
public final class zzjt implements zzal {
    private final zzjv zza;
    private final String zzb;
    private final byte[] zzc;
    private final zzjr zzd;

    public zzjt(ECPublicKey eCPublicKey, byte[] bArr, String str, int i, zzjr zzjr) throws GeneralSecurityException {
        zzjw.zza(eCPublicKey.getW(), eCPublicKey.getParams().getCurve());
        this.zza = new zzjv(eCPublicKey);
        this.zzc = bArr;
        this.zzb = str;
        this.zzd = zzjr;
    }
}

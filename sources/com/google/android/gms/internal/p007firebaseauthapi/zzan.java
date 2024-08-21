package com.google.android.gms.internal.p007firebaseauthapi;

import com.google.android.gms.internal.p007firebaseauthapi.zzaar;
import java.security.GeneralSecurityException;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzan */
/* compiled from: com.google.firebase:firebase-auth@@20.0.1 */
final class zzan<KeyFormatProtoT extends zzaar, KeyProtoT extends zzaar> {
    final zzaq<KeyFormatProtoT, KeyProtoT> zza;

    zzan(zzaq<KeyFormatProtoT, KeyProtoT> zzaq) {
        this.zza = zzaq;
    }

    /* access modifiers changed from: package-private */
    public final KeyProtoT zza(zzym zzym) throws GeneralSecurityException, zzzw {
        KeyFormatProtoT zzc = this.zza.zzc(zzym);
        this.zza.zzb(zzc);
        return this.zza.zzd(zzc);
    }
}

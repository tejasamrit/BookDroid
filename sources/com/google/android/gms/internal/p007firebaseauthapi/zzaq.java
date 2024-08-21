package com.google.android.gms.internal.p007firebaseauthapi;

import com.google.android.gms.internal.p007firebaseauthapi.zzaar;
import java.security.GeneralSecurityException;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzaq */
/* compiled from: com.google.firebase:firebase-auth@@20.0.1 */
public abstract class zzaq<KeyFormatProtoT extends zzaar, KeyT> {
    private final Class<KeyFormatProtoT> zza;

    public zzaq(Class<KeyFormatProtoT> cls) {
        this.zza = cls;
    }

    public final Class<KeyFormatProtoT> zza() {
        return this.zza;
    }

    public abstract void zzb(KeyFormatProtoT keyformatprotot) throws GeneralSecurityException;

    public abstract KeyFormatProtoT zzc(zzym zzym) throws zzzw;

    public abstract KeyT zzd(KeyFormatProtoT keyformatprotot) throws GeneralSecurityException;
}

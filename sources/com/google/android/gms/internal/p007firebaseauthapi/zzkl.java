package com.google.android.gms.internal.p007firebaseauthapi;

import java.security.GeneralSecurityException;
import javax.crypto.Mac;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzkl */
/* compiled from: com.google.firebase:firebase-auth@@20.0.1 */
final class zzkl extends ThreadLocal<Mac> {
    final /* synthetic */ zzkm zza;

    zzkl(zzkm zzkm) {
        this.zza = zzkm;
    }

    /* access modifiers changed from: protected */
    /* renamed from: zza */
    public final Mac initialValue() {
        try {
            Mac zza2 = zzjy.zzb.zza(this.zza.zzb);
            zza2.init(this.zza.zzc);
            return zza2;
        } catch (GeneralSecurityException e) {
            throw new IllegalStateException(e);
        }
    }
}

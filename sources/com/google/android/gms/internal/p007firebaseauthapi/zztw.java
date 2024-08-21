package com.google.android.gms.internal.p007firebaseauthapi;

import android.content.Context;
import com.google.android.gms.common.api.Api;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zztw */
/* compiled from: com.google.firebase:firebase-auth@@20.0.1 */
public final class zztw {
    public static final Api.ClientKey<zztc> zza;
    public static final Api<zztv> zzb;
    private static final Api.AbstractClientBuilder<zztc, zztv> zzc;

    static {
        Api.ClientKey<zztc> clientKey = new Api.ClientKey<>();
        zza = clientKey;
        zztt zztt = new zztt();
        zzc = zztt;
        zzb = new Api<>("InternalFirebaseAuth.FIREBASE_AUTH_API", zztt, clientKey);
    }

    public static zzsy zza(Context context, zztv zztv) {
        return new zzsy(context, zztv);
    }
}

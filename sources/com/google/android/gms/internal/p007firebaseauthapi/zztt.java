package com.google.android.gms.internal.p007firebaseauthapi;

import android.content.Context;
import android.os.Looper;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.internal.ConnectionCallbacks;
import com.google.android.gms.common.api.internal.OnConnectionFailedListener;
import com.google.android.gms.common.internal.ClientSettings;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zztt */
/* compiled from: com.google.firebase:firebase-auth@@20.0.1 */
final class zztt extends Api.AbstractClientBuilder<zztc, zztv> {
    zztt() {
    }

    public final /* bridge */ /* synthetic */ Api.Client buildClient(Context context, Looper looper, ClientSettings clientSettings, Object obj, ConnectionCallbacks connectionCallbacks, OnConnectionFailedListener onConnectionFailedListener) {
        return new zztd(context, looper, clientSettings, (zztv) obj, connectionCallbacks, onConnectionFailedListener);
    }
}

package com.google.android.gms.auth.api;

import android.content.Context;
import android.os.Looper;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.internal.ClientSettings;
import com.google.android.gms.internal.p006authapi.zzq;

/* compiled from: com.google.android.gms:play-services-auth@@19.0.0 */
final class zzc extends Api.AbstractClientBuilder<zzq, Auth.AuthCredentialsOptions> {
    zzc() {
    }

    public final /* synthetic */ Api.Client buildClient(Context context, Looper looper, ClientSettings clientSettings, Object obj, GoogleApiClient.ConnectionCallbacks connectionCallbacks, GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
        return new zzq(context, looper, clientSettings, (Auth.AuthCredentialsOptions) obj, connectionCallbacks, onConnectionFailedListener);
    }
}

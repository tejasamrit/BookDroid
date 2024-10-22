package com.google.android.gms.internal.location;

import android.app.PendingIntent;
import android.os.RemoteException;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;

/* compiled from: com.google.android.gms:play-services-location@@17.1.0 */
final class zzv extends zzaa {
    private final /* synthetic */ LocationRequest zza;
    private final /* synthetic */ PendingIntent zzb;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzv(zzn zzn, GoogleApiClient googleApiClient, LocationRequest locationRequest, PendingIntent pendingIntent) {
        super(googleApiClient);
        this.zza = locationRequest;
        this.zzb = pendingIntent;
    }

    /* access modifiers changed from: protected */
    public final /* synthetic */ void doExecute(Api.AnyClient anyClient) throws RemoteException {
        ((zzay) anyClient).zza(this.zza, this.zzb, (zzai) new zzz(this));
    }
}

package com.google.android.gms.internal.location;

import android.app.PendingIntent;
import android.util.Log;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.BaseImplementation;
import com.google.android.gms.location.LocationStatusCodes;

/* compiled from: com.google.android.gms:play-services-location@@17.1.0 */
final class zzba extends zzam {
    private BaseImplementation.ResultHolder<Status> zza;

    public zzba(BaseImplementation.ResultHolder<Status> resultHolder) {
        this.zza = resultHolder;
    }

    public final void zza(int i, String[] strArr) {
        Log.wtf("LocationClientImpl", "Unexpected call to onAddGeofencesResult", new Exception());
    }

    public final void zzb(int i, String[] strArr) {
        zza(i);
    }

    public final void zza(int i, PendingIntent pendingIntent) {
        zza(i);
    }

    private final void zza(int i) {
        if (this.zza == null) {
            Log.wtf("LocationClientImpl", "onRemoveGeofencesResult called multiple times", new Exception());
            return;
        }
        this.zza.setResult(LocationStatusCodes.zzb(LocationStatusCodes.zza(i)));
        this.zza = null;
    }
}

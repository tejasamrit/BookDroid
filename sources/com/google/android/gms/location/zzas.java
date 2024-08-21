package com.google.android.gms.location;

import android.location.Location;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import com.google.android.gms.internal.location.zzb;
import com.google.android.gms.internal.location.zzd;

/* compiled from: com.google.android.gms:play-services-location@@17.1.0 */
public final class zzas extends zzb implements zzaq {
    zzas(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.location.ILocationListener");
    }

    public final void zza(Location location) throws RemoteException {
        Parcel b_ = mo14957b_();
        zzd.zza(b_, (Parcelable) location);
        zzc(1, b_);
    }
}

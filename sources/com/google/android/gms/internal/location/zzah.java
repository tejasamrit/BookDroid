package com.google.android.gms.internal.location;

import android.os.Parcel;
import android.os.RemoteException;

/* compiled from: com.google.android.gms:play-services-location@@17.1.0 */
public abstract class zzah extends zza implements zzai {
    public zzah() {
        super("com.google.android.gms.location.internal.IFusedLocationProviderCallback");
    }

    /* access modifiers changed from: protected */
    public final boolean zza(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        if (i == 1) {
            zza((zzac) zzd.zza(parcel, zzac.CREATOR));
        } else if (i != 2) {
            return false;
        } else {
            mo14892a_();
        }
        return true;
    }
}

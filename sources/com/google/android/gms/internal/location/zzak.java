package com.google.android.gms.internal.location;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;

/* compiled from: com.google.android.gms:play-services-location@@17.1.0 */
public final class zzak extends zzb implements zzai {
    zzak(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.location.internal.IFusedLocationProviderCallback");
    }

    public final void zza(zzac zzac) throws RemoteException {
        Parcel b_ = mo14957b_();
        zzd.zza(b_, (Parcelable) zzac);
        zzc(1, b_);
    }

    /* renamed from: a_ */
    public final void mo14892a_() throws RemoteException {
        zzc(2, mo14957b_());
    }
}

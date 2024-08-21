package com.google.android.gms.internal.p007firebaseauthapi;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzvy */
/* compiled from: com.google.firebase:firebase-auth@@20.0.1 */
public final class zzvy implements Parcelable.Creator<zzvx> {
    public final /* bridge */ /* synthetic */ Object createFromParcel(Parcel parcel) {
        int validateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        zzwb zzwb = null;
        while (parcel.dataPosition() < validateObjectHeader) {
            int readHeader = SafeParcelReader.readHeader(parcel);
            if (SafeParcelReader.getFieldId(readHeader) != 2) {
                SafeParcelReader.skipUnknownField(parcel, readHeader);
            } else {
                zzwb = (zzwb) SafeParcelReader.createParcelable(parcel, readHeader, zzwb.CREATOR);
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, validateObjectHeader);
        return new zzvx(zzwb);
    }

    public final /* bridge */ /* synthetic */ Object[] newArray(int i) {
        return new zzvx[i];
    }
}

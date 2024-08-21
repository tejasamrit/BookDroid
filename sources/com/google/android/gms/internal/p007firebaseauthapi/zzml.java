package com.google.android.gms.internal.p007firebaseauthapi;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzml */
/* compiled from: com.google.firebase:firebase-auth@@20.0.1 */
public final class zzml implements Parcelable.Creator<zzmk> {
    public final /* bridge */ /* synthetic */ Object createFromParcel(Parcel parcel) {
        int validateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        zzwt zzwt = null;
        while (parcel.dataPosition() < validateObjectHeader) {
            int readHeader = SafeParcelReader.readHeader(parcel);
            if (SafeParcelReader.getFieldId(readHeader) != 1) {
                SafeParcelReader.skipUnknownField(parcel, readHeader);
            } else {
                zzwt = (zzwt) SafeParcelReader.createParcelable(parcel, readHeader, zzwt.CREATOR);
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, validateObjectHeader);
        return new zzmk(zzwt);
    }

    public final /* bridge */ /* synthetic */ Object[] newArray(int i) {
        return new zzmk[i];
    }
}

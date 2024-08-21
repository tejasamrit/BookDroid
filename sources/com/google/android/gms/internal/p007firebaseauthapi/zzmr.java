package com.google.android.gms.internal.p007firebaseauthapi;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzmr */
/* compiled from: com.google.firebase:firebase-auth@@20.0.1 */
public final class zzmr implements Parcelable.Creator<zzmq> {
    public final /* bridge */ /* synthetic */ Object createFromParcel(Parcel parcel) {
        int validateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        zzxg zzxg = null;
        while (parcel.dataPosition() < validateObjectHeader) {
            int readHeader = SafeParcelReader.readHeader(parcel);
            if (SafeParcelReader.getFieldId(readHeader) != 1) {
                SafeParcelReader.skipUnknownField(parcel, readHeader);
            } else {
                zzxg = (zzxg) SafeParcelReader.createParcelable(parcel, readHeader, zzxg.CREATOR);
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, validateObjectHeader);
        return new zzmq(zzxg);
    }

    public final /* bridge */ /* synthetic */ Object[] newArray(int i) {
        return new zzmq[i];
    }
}

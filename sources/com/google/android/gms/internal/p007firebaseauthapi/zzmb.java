package com.google.android.gms.internal.p007firebaseauthapi;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzmb */
/* compiled from: com.google.firebase:firebase-auth@@20.0.1 */
public final class zzmb implements Parcelable.Creator<zzma> {
    public final /* bridge */ /* synthetic */ Object createFromParcel(Parcel parcel) {
        int validateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        String str = null;
        zzxg zzxg = null;
        while (parcel.dataPosition() < validateObjectHeader) {
            int readHeader = SafeParcelReader.readHeader(parcel);
            int fieldId = SafeParcelReader.getFieldId(readHeader);
            if (fieldId == 1) {
                str = SafeParcelReader.createString(parcel, readHeader);
            } else if (fieldId != 2) {
                SafeParcelReader.skipUnknownField(parcel, readHeader);
            } else {
                zzxg = (zzxg) SafeParcelReader.createParcelable(parcel, readHeader, zzxg.CREATOR);
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, validateObjectHeader);
        return new zzma(str, zzxg);
    }

    public final /* bridge */ /* synthetic */ Object[] newArray(int i) {
        return new zzma[i];
    }
}

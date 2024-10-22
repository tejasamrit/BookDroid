package com.google.android.gms.location;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;

@Deprecated
/* compiled from: com.google.android.gms:play-services-location@@17.1.0 */
public final class zzay extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzay> CREATOR = new zzaz();
    private final String zza;
    private final String zzb;
    private final String zzc;

    zzay(String str, String str2, String str3) {
        this.zzc = str;
        this.zza = str2;
        this.zzb = str3;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 1, this.zza, false);
        SafeParcelWriter.writeString(parcel, 2, this.zzb, false);
        SafeParcelWriter.writeString(parcel, 5, this.zzc, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}

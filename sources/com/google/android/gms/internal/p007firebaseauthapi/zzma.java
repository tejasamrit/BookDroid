package com.google.android.gms.internal.p007firebaseauthapi;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzma */
/* compiled from: com.google.firebase:firebase-auth@@20.0.1 */
public final class zzma extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzma> CREATOR = new zzmb();
    private final String zza;
    private final zzxg zzb;

    public zzma(String str, zzxg zzxg) {
        this.zza = str;
        this.zzb = zzxg;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 1, this.zza, false);
        SafeParcelWriter.writeParcelable(parcel, 2, this.zzb, i, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    public final String zza() {
        return this.zza;
    }

    public final zzxg zzb() {
        return this.zzb;
    }
}

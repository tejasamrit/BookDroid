package com.google.android.gms.internal.p007firebaseauthapi;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.firebase.auth.PhoneAuthCredential;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzmc */
/* compiled from: com.google.firebase:firebase-auth@@20.0.1 */
public final class zzmc extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzmc> CREATOR = new zzmd();
    private final String zza;
    private final PhoneAuthCredential zzb;

    public zzmc(String str, PhoneAuthCredential phoneAuthCredential) {
        this.zza = str;
        this.zzb = phoneAuthCredential;
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

    public final PhoneAuthCredential zzb() {
        return this.zzb;
    }
}

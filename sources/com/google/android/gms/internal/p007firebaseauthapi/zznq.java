package com.google.android.gms.internal.p007firebaseauthapi;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.firebase.auth.MultiFactorInfo;
import com.google.firebase.auth.internal.zzba;
import com.google.firebase.auth.zze;
import java.util.List;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zznq */
/* compiled from: com.google.firebase:firebase-auth@@20.0.1 */
public final class zznq extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zznq> CREATOR = new zznr();
    final String zza;
    final List<zzwk> zzb;
    final zze zzc;

    public zznq(String str, List<zzwk> list, zze zze) {
        this.zza = str;
        this.zzb = list;
        this.zzc = zze;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 1, this.zza, false);
        SafeParcelWriter.writeTypedList(parcel, 2, this.zzb, false);
        SafeParcelWriter.writeParcelable(parcel, 3, this.zzc, i, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    public final String zza() {
        return this.zza;
    }

    public final zze zzb() {
        return this.zzc;
    }

    public final List<MultiFactorInfo> zzc() {
        return zzba.zzb(this.zzb);
    }
}

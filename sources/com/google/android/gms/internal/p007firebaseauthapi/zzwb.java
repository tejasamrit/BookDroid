package com.google.android.gms.internal.p007firebaseauthapi;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzwb */
/* compiled from: com.google.firebase:firebase-auth@@20.0.1 */
public final class zzwb extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzwb> CREATOR = new zzwc();
    private final List<zzvz> zza;

    public zzwb() {
        this.zza = new ArrayList();
    }

    public static zzwb zzb(zzwb zzwb) {
        Preconditions.checkNotNull(zzwb);
        List<zzvz> list = zzwb.zza;
        zzwb zzwb2 = new zzwb();
        if (list != null && !list.isEmpty()) {
            zzwb2.zza.addAll(list);
        }
        return zzwb2;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeTypedList(parcel, 2, this.zza, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    public final List<zzvz> zza() {
        return this.zza;
    }

    zzwb(List<zzvz> list) {
        List<zzvz> list2;
        if (list == null) {
            list2 = Collections.emptyList();
        } else {
            list2 = Collections.unmodifiableList(list);
        }
        this.zza = list2;
    }
}

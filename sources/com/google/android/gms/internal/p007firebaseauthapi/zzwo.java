package com.google.android.gms.internal.p007firebaseauthapi;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.util.Strings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzwo */
/* compiled from: com.google.firebase:firebase-auth@@20.0.1 */
public final class zzwo extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzwo> CREATOR = new zzwp();
    private final List<zzwm> zza;

    public zzwo() {
        this.zza = new ArrayList();
    }

    public static zzwo zzb(zzwo zzwo) {
        List<zzwm> list = zzwo.zza;
        zzwo zzwo2 = new zzwo();
        if (list != null) {
            zzwo2.zza.addAll(list);
        }
        return zzwo2;
    }

    public static zzwo zzc(JSONArray jSONArray) throws JSONException {
        zzwm zzwm;
        if (jSONArray == null || jSONArray.length() == 0) {
            return new zzwo(new ArrayList());
        }
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < jSONArray.length(); i++) {
            JSONObject jSONObject = jSONArray.getJSONObject(i);
            if (jSONObject == null) {
                zzwm = new zzwm();
            } else {
                zzwm = new zzwm(Strings.emptyToNull(jSONObject.optString("federatedId", (String) null)), Strings.emptyToNull(jSONObject.optString("displayName", (String) null)), Strings.emptyToNull(jSONObject.optString("photoUrl", (String) null)), Strings.emptyToNull(jSONObject.optString("providerId", (String) null)), (String) null, Strings.emptyToNull(jSONObject.optString("phoneNumber", (String) null)), Strings.emptyToNull(jSONObject.optString("email", (String) null)));
            }
            arrayList.add(zzwm);
        }
        return new zzwo(arrayList);
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeTypedList(parcel, 2, this.zza, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    public final List<zzwm> zza() {
        return this.zza;
    }

    zzwo(List<zzwm> list) {
        if (list == null || list.isEmpty()) {
            this.zza = Collections.emptyList();
        } else {
            this.zza = Collections.unmodifiableList(list);
        }
    }
}

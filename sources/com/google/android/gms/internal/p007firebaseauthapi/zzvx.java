package com.google.android.gms.internal.p007firebaseauthapi;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.util.Strings;
import com.google.firebase.auth.zze;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzvx */
/* compiled from: com.google.firebase:firebase-auth@@20.0.1 */
public final class zzvx extends AbstractSafeParcelable implements zztz<zzvx> {
    public static final Parcelable.Creator<zzvx> CREATOR = new zzvy();
    private static final String zza = "zzvx";
    private zzwb zzb;

    public zzvx() {
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 2, this.zzb, i, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    public final /* bridge */ /* synthetic */ zztz zza(String str) throws zzpp {
        zzwb zzwb;
        int i;
        zzvz zzvz;
        String str2 = str;
        try {
            JSONObject jSONObject = new JSONObject(str2);
            if (!jSONObject.has("users")) {
                zzwb = new zzwb();
            } else {
                JSONArray optJSONArray = jSONObject.optJSONArray("users");
                Parcelable.Creator<zzwb> creator = zzwb.CREATOR;
                if (optJSONArray != null) {
                    if (optJSONArray.length() != 0) {
                        ArrayList arrayList = new ArrayList(optJSONArray.length());
                        boolean z = false;
                        int i2 = 0;
                        while (i2 < optJSONArray.length()) {
                            JSONObject jSONObject2 = optJSONArray.getJSONObject(i2);
                            if (jSONObject2 == null) {
                                zzvz = new zzvz();
                                i = i2;
                            } else {
                                String emptyToNull = Strings.emptyToNull(jSONObject2.optString("localId", (String) null));
                                String emptyToNull2 = Strings.emptyToNull(jSONObject2.optString("email", (String) null));
                                boolean optBoolean = jSONObject2.optBoolean("emailVerified", z);
                                String emptyToNull3 = Strings.emptyToNull(jSONObject2.optString("displayName", (String) null));
                                String emptyToNull4 = Strings.emptyToNull(jSONObject2.optString("photoUrl", (String) null));
                                zzwo zzc = zzwo.zzc(jSONObject2.optJSONArray("providerUserInfo"));
                                String emptyToNull5 = Strings.emptyToNull(jSONObject2.optString("rawPassword", (String) null));
                                String emptyToNull6 = Strings.emptyToNull(jSONObject2.optString("phoneNumber", (String) null));
                                i = i2;
                                long optLong = jSONObject2.optLong("createdAt", 0);
                                zzvz = new zzvz(emptyToNull, emptyToNull2, optBoolean, emptyToNull3, emptyToNull4, zzc, emptyToNull5, emptyToNull6, optLong, jSONObject2.optLong("lastLoginAt", 0), false, (zze) null, zzwk.zzf(jSONObject2.optJSONArray("mfaInfo")));
                            }
                            arrayList.add(zzvz);
                            i2 = i + 1;
                            z = false;
                        }
                        zzwb = new zzwb(arrayList);
                    }
                }
                zzwb = new zzwb(new ArrayList());
            }
            this.zzb = zzwb;
            return this;
        } catch (NullPointerException | JSONException e) {
            throw zzxs.zzb(e, zza, str2);
        }
    }

    public final List<zzvz> zzb() {
        return this.zzb.zza();
    }

    zzvx(zzwb zzwb) {
        zzwb zzwb2;
        if (zzwb == null) {
            zzwb2 = new zzwb();
        } else {
            zzwb2 = zzwb.zzb(zzwb);
        }
        this.zzb = zzwb2;
    }
}

package com.google.android.gms.internal.p007firebaseauthapi;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzvl */
/* compiled from: com.google.firebase:firebase-auth@@20.0.1 */
public final class zzvl extends AbstractSafeParcelable implements zztz<zzvl> {
    public static final Parcelable.Creator<zzvl> CREATOR = new zzvm();
    private static final String zza = "zzvl";
    private String zzb;
    private boolean zzc;
    private String zzd;
    private boolean zze;
    private zzxe zzf;
    private List<String> zzg;

    public zzvl() {
        this.zzf = new zzxe((List<String>) null);
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 2, this.zzb, false);
        SafeParcelWriter.writeBoolean(parcel, 3, this.zzc);
        SafeParcelWriter.writeString(parcel, 4, this.zzd, false);
        SafeParcelWriter.writeBoolean(parcel, 5, this.zze);
        SafeParcelWriter.writeParcelable(parcel, 6, this.zzf, i, false);
        SafeParcelWriter.writeStringList(parcel, 7, this.zzg, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    public final /* bridge */ /* synthetic */ zztz zza(String str) throws zzpp {
        try {
            JSONObject jSONObject = new JSONObject(str);
            this.zzb = jSONObject.optString("authUri", (String) null);
            this.zzc = jSONObject.optBoolean("registered", false);
            this.zzd = jSONObject.optString("providerId", (String) null);
            this.zze = jSONObject.optBoolean("forExistingProvider", false);
            if (!jSONObject.has("allProviders")) {
                this.zzf = new zzxe((List<String>) null);
            } else {
                this.zzf = new zzxe(1, zzxs.zza(jSONObject.optJSONArray("allProviders")));
            }
            this.zzg = zzxs.zza(jSONObject.optJSONArray("signinMethods"));
            return this;
        } catch (NullPointerException | JSONException e) {
            throw zzxs.zzb(e, zza, str);
        }
    }

    public final List<String> zzb() {
        return this.zzg;
    }

    public zzvl(String str, boolean z, String str2, boolean z2, zzxe zzxe, List<String> list) {
        zzxe zzxe2;
        this.zzb = str;
        this.zzc = z;
        this.zzd = str2;
        this.zze = z2;
        if (zzxe == null) {
            zzxe2 = new zzxe((List<String>) null);
        } else {
            zzxe2 = zzxe.zzb(zzxe);
        }
        this.zzf = zzxe2;
        this.zzg = list;
    }
}

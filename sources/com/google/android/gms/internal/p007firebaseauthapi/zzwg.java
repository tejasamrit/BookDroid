package com.google.android.gms.internal.p007firebaseauthapi;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.util.DefaultClock;
import com.google.android.gms.common.util.Strings;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzwg */
/* compiled from: com.google.firebase:firebase-auth@@20.0.1 */
public final class zzwg extends AbstractSafeParcelable implements zztz<zzwg> {
    public static final Parcelable.Creator<zzwg> CREATOR = new zzwh();
    private static final String zza = "zzwg";
    private String zzb;
    private String zzc;
    private Long zzd;
    private String zze;
    private Long zzf;

    public zzwg() {
        this.zzf = Long.valueOf(System.currentTimeMillis());
    }

    public static zzwg zzj(String str) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            zzwg zzwg = new zzwg();
            zzwg.zzb = jSONObject.optString("refresh_token", (String) null);
            zzwg.zzc = jSONObject.optString("access_token", (String) null);
            zzwg.zzd = Long.valueOf(jSONObject.optLong("expires_in"));
            zzwg.zze = jSONObject.optString("token_type", (String) null);
            zzwg.zzf = Long.valueOf(jSONObject.optLong("issued_at"));
            return zzwg;
        } catch (JSONException e) {
            Log.d(zza, "Failed to read GetTokenResponse from JSONObject");
            throw new zzlb(e);
        }
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 2, this.zzb, false);
        SafeParcelWriter.writeString(parcel, 3, this.zzc, false);
        SafeParcelWriter.writeLongObject(parcel, 4, Long.valueOf(zzf()), false);
        SafeParcelWriter.writeString(parcel, 5, this.zze, false);
        SafeParcelWriter.writeLongObject(parcel, 6, Long.valueOf(this.zzf.longValue()), false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    public final /* bridge */ /* synthetic */ zztz zza(String str) throws zzpp {
        try {
            JSONObject jSONObject = new JSONObject(str);
            this.zzb = Strings.emptyToNull(jSONObject.optString("refresh_token"));
            this.zzc = Strings.emptyToNull(jSONObject.optString("access_token"));
            this.zzd = Long.valueOf(jSONObject.optLong("expires_in", 0));
            this.zze = Strings.emptyToNull(jSONObject.optString("token_type"));
            this.zzf = Long.valueOf(System.currentTimeMillis());
            return this;
        } catch (NullPointerException | JSONException e) {
            throw zzxs.zzb(e, zza, str);
        }
    }

    public final boolean zzb() {
        return DefaultClock.getInstance().currentTimeMillis() + 300000 < this.zzf.longValue() + (this.zzd.longValue() * 1000);
    }

    public final void zzc(String str) {
        this.zzb = Preconditions.checkNotEmpty(str);
    }

    public final String zzd() {
        return this.zzb;
    }

    public final String zze() {
        return this.zzc;
    }

    public final long zzf() {
        Long l = this.zzd;
        if (l == null) {
            return 0;
        }
        return l.longValue();
    }

    public final String zzg() {
        return this.zze;
    }

    public final long zzh() {
        return this.zzf.longValue();
    }

    public final String zzi() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("refresh_token", this.zzb);
            jSONObject.put("access_token", this.zzc);
            jSONObject.put("expires_in", this.zzd);
            jSONObject.put("token_type", this.zze);
            jSONObject.put("issued_at", this.zzf);
            return jSONObject.toString();
        } catch (JSONException e) {
            Log.d(zza, "Failed to convert GetTokenResponse to JSON");
            throw new zzlb(e);
        }
    }

    public zzwg(String str, String str2, Long l, String str3) {
        this(str, str2, l, str3, Long.valueOf(System.currentTimeMillis()));
    }

    zzwg(String str, String str2, Long l, String str3, Long l2) {
        this.zzb = str;
        this.zzc = str2;
        this.zzd = l;
        this.zze = str3;
        this.zzf = l2;
    }
}

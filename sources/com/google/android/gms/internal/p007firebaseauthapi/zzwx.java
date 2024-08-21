package com.google.android.gms.internal.p007firebaseauthapi;

import com.google.android.gms.common.util.Strings;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzwx */
/* compiled from: com.google.firebase:firebase-auth@@20.0.1 */
public final class zzwx implements zztz<zzwx> {
    private static final String zza = "zzwx";
    private String zzb;
    private String zzc;
    private Boolean zzd;
    private String zze;
    private String zzf;
    private zzwo zzg;
    private String zzh;
    private String zzi;
    private long zzj;

    public final /* bridge */ /* synthetic */ zztz zza(String str) throws zzpp {
        try {
            JSONObject jSONObject = new JSONObject(str);
            this.zzb = Strings.emptyToNull(jSONObject.optString("email", (String) null));
            this.zzc = Strings.emptyToNull(jSONObject.optString("passwordHash", (String) null));
            this.zzd = Boolean.valueOf(jSONObject.optBoolean("emailVerified", false));
            this.zze = Strings.emptyToNull(jSONObject.optString("displayName", (String) null));
            this.zzf = Strings.emptyToNull(jSONObject.optString("photoUrl", (String) null));
            this.zzg = zzwo.zzc(jSONObject.optJSONArray("providerUserInfo"));
            this.zzh = Strings.emptyToNull(jSONObject.optString("idToken", (String) null));
            this.zzi = Strings.emptyToNull(jSONObject.optString("refreshToken", (String) null));
            this.zzj = jSONObject.optLong("expiresIn", 0);
            return this;
        } catch (NullPointerException | JSONException e) {
            throw zzxs.zzb(e, zza, str);
        }
    }

    public final String zzb() {
        return this.zzh;
    }

    public final String zzc() {
        return this.zzi;
    }

    public final long zzd() {
        return this.zzj;
    }

    public final String zze() {
        return this.zzb;
    }

    public final List<zzwm> zzf() {
        zzwo zzwo = this.zzg;
        if (zzwo != null) {
            return zzwo.zza();
        }
        return null;
    }
}

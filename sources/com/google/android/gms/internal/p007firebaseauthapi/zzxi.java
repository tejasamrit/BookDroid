package com.google.android.gms.internal.p007firebaseauthapi;

import android.text.TextUtils;
import com.google.android.gms.common.util.Strings;
import com.google.firebase.auth.zze;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzxi */
/* compiled from: com.google.firebase:firebase-auth@@20.0.1 */
public final class zzxi implements zztz<zzxi> {
    private static final String zza = "zzxi";
    private boolean zzb;
    private String zzc;
    private String zzd;
    private long zze;
    private String zzf;
    private String zzg;
    private String zzh;
    private String zzi;
    private String zzj;
    private String zzk;
    private boolean zzl;
    private String zzm;
    private String zzn;
    private String zzo;
    private String zzp;
    private String zzq;
    private String zzr;
    private List<zzwk> zzs;
    private String zzt;

    public final /* bridge */ /* synthetic */ zztz zza(String str) throws zzpp {
        try {
            JSONObject jSONObject = new JSONObject(str);
            this.zzb = jSONObject.optBoolean("needConfirmation", false);
            jSONObject.optBoolean("needEmail", false);
            this.zzc = Strings.emptyToNull(jSONObject.optString("idToken", (String) null));
            this.zzd = Strings.emptyToNull(jSONObject.optString("refreshToken", (String) null));
            this.zze = jSONObject.optLong("expiresIn", 0);
            this.zzf = Strings.emptyToNull(jSONObject.optString("localId", (String) null));
            this.zzg = Strings.emptyToNull(jSONObject.optString("email", (String) null));
            this.zzh = Strings.emptyToNull(jSONObject.optString("displayName", (String) null));
            this.zzi = Strings.emptyToNull(jSONObject.optString("photoUrl", (String) null));
            this.zzj = Strings.emptyToNull(jSONObject.optString("providerId", (String) null));
            this.zzk = Strings.emptyToNull(jSONObject.optString("rawUserInfo", (String) null));
            this.zzl = jSONObject.optBoolean("isNewUser", false);
            this.zzm = jSONObject.optString("oauthAccessToken", (String) null);
            this.zzn = jSONObject.optString("oauthIdToken", (String) null);
            this.zzp = Strings.emptyToNull(jSONObject.optString("errorMessage", (String) null));
            this.zzq = Strings.emptyToNull(jSONObject.optString("pendingToken", (String) null));
            this.zzr = Strings.emptyToNull(jSONObject.optString("tenantId", (String) null));
            this.zzs = zzwk.zzf(jSONObject.optJSONArray("mfaInfo"));
            this.zzt = Strings.emptyToNull(jSONObject.optString("mfaPendingCredential", (String) null));
            this.zzo = Strings.emptyToNull(jSONObject.optString("oauthTokenSecret", (String) null));
            return this;
        } catch (NullPointerException | JSONException e) {
            throw zzxs.zzb(e, zza, str);
        }
    }

    public final boolean zzb() {
        return this.zzb;
    }

    public final String zzc() {
        return this.zzc;
    }

    public final String zzd() {
        return this.zzg;
    }

    public final String zze() {
        return this.zzj;
    }

    public final String zzf() {
        return this.zzk;
    }

    public final String zzg() {
        return this.zzd;
    }

    public final long zzh() {
        return this.zze;
    }

    public final boolean zzi() {
        return this.zzl;
    }

    public final String zzj() {
        return this.zzp;
    }

    public final boolean zzk() {
        return this.zzb || !TextUtils.isEmpty(this.zzp);
    }

    public final String zzl() {
        return this.zzr;
    }

    public final List<zzwk> zzm() {
        return this.zzs;
    }

    public final String zzn() {
        return this.zzt;
    }

    public final boolean zzo() {
        return !TextUtils.isEmpty(this.zzt);
    }

    public final zze zzp() {
        if (!TextUtils.isEmpty(this.zzm) || !TextUtils.isEmpty(this.zzn)) {
            return zze.zzb(this.zzj, this.zzn, this.zzm, this.zzq, this.zzo);
        }
        return null;
    }
}

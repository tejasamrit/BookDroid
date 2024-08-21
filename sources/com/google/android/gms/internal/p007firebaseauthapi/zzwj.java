package com.google.android.gms.internal.p007firebaseauthapi;

import android.util.Log;
import java.io.UnsupportedEncodingException;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzwj */
/* compiled from: com.google.firebase:firebase-auth@@20.0.1 */
public final class zzwj {
    private String zza;
    private String zzb;
    private String zzc;
    private Long zzd;
    private Long zze;

    public static zzwj zzc(String str) throws UnsupportedEncodingException {
        try {
            zzwj zzwj = new zzwj();
            JSONObject jSONObject = new JSONObject(str);
            zzwj.zza = jSONObject.optString("iss");
            zzwj.zzb = jSONObject.optString("aud");
            zzwj.zzc = jSONObject.optString("sub");
            zzwj.zzd = Long.valueOf(jSONObject.optLong("iat"));
            zzwj.zze = Long.valueOf(jSONObject.optLong("exp"));
            jSONObject.optBoolean("is_anonymous");
            return zzwj;
        } catch (JSONException e) {
            if (Log.isLoggable("JwtToken", 3)) {
                String valueOf = String.valueOf(e);
                StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 41);
                sb.append("Failed to read JwtToken from JSONObject. ");
                sb.append(valueOf);
                Log.d("JwtToken", sb.toString());
            }
            String valueOf2 = String.valueOf(e);
            StringBuilder sb2 = new StringBuilder(String.valueOf(valueOf2).length() + 41);
            sb2.append("Failed to read JwtToken from JSONObject. ");
            sb2.append(valueOf2);
            throw new UnsupportedEncodingException(sb2.toString());
        }
    }

    public final Long zza() {
        return this.zzd;
    }

    public final Long zzb() {
        return this.zze;
    }
}

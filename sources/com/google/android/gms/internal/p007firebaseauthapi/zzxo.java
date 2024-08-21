package com.google.android.gms.internal.p007firebaseauthapi;

import android.text.TextUtils;
import com.google.android.gms.common.internal.Preconditions;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzxo */
/* compiled from: com.google.firebase:firebase-auth@@20.0.1 */
public final class zzxo implements zzty {
    private String zza;
    private String zzb;
    private String zzc;
    private String zzd;
    private String zze;
    private boolean zzf;

    private zzxo() {
    }

    public static zzxo zzb(String str, String str2, boolean z) {
        zzxo zzxo = new zzxo();
        zzxo.zzb = Preconditions.checkNotEmpty(str);
        zzxo.zzc = Preconditions.checkNotEmpty(str2);
        zzxo.zzf = z;
        return zzxo;
    }

    public static zzxo zzc(String str, String str2, boolean z) {
        zzxo zzxo = new zzxo();
        zzxo.zza = Preconditions.checkNotEmpty(str);
        zzxo.zzd = Preconditions.checkNotEmpty(str2);
        zzxo.zzf = z;
        return zzxo;
    }

    public final String zza() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        if (!TextUtils.isEmpty(this.zzd)) {
            jSONObject.put("phoneNumber", this.zza);
            jSONObject.put("temporaryProof", this.zzd);
        } else {
            jSONObject.put("sessionInfo", this.zzb);
            jSONObject.put("code", this.zzc);
        }
        String str = this.zze;
        if (str != null) {
            jSONObject.put("idToken", str);
        }
        if (!this.zzf) {
            jSONObject.put("operation", 2);
        }
        return jSONObject.toString();
    }

    public final void zzd(String str) {
        this.zze = str;
    }
}

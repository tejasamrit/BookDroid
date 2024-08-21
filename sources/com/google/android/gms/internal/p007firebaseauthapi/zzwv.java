package com.google.android.gms.internal.p007firebaseauthapi;

import com.google.android.gms.common.util.Strings;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzwv */
/* compiled from: com.google.firebase:firebase-auth@@20.0.1 */
public final class zzwv implements zztz<zzwv> {
    private static final String zza = "zzwv";
    private String zzb;

    public final /* bridge */ /* synthetic */ zztz zza(String str) throws zzpp {
        try {
            this.zzb = Strings.emptyToNull(new JSONObject(str).optString("sessionInfo", (String) null));
            return this;
        } catch (NullPointerException | JSONException e) {
            throw zzxs.zzb(e, zza, str);
        }
    }

    public final String zzb() {
        return this.zzb;
    }
}

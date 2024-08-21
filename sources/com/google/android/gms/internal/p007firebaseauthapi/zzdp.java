package com.google.android.gms.internal.p007firebaseauthapi;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import java.io.IOException;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzdp */
/* compiled from: com.google.firebase:firebase-auth@@20.0.1 */
public final class zzdp implements zzav {
    private final SharedPreferences.Editor zza;
    private final String zzb = "GenericIdpKeyset";

    public zzdp(Context context, String str, String str2) {
        Context applicationContext = context.getApplicationContext();
        if (str2 == null) {
            this.zza = PreferenceManager.getDefaultSharedPreferences(applicationContext).edit();
        } else {
            this.zza = applicationContext.getSharedPreferences(str2, 0).edit();
        }
    }

    public final void zzb(zzib zzib) throws IOException {
        if (!this.zza.putString(this.zzb, zzkh.zza(zzib.zzI())).commit()) {
            throw new IOException("Failed to write to SharedPreferences");
        }
    }

    public final void zzc(zzgy zzgy) throws IOException {
        if (!this.zza.putString(this.zzb, zzkh.zza(zzgy.zzI())).commit()) {
            throw new IOException("Failed to write to SharedPreferences");
        }
    }
}

package com.google.android.gms.internal.p007firebaseauthapi;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.logging.Logger;
import java.net.HttpURLConnection;
import java.net.URL;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zztj */
/* compiled from: com.google.firebase:firebase-auth@@20.0.1 */
public interface zztj {
    public static final Logger zza = new Logger("FirebaseAuth", "GetAuthDomainTaskResponseHandler");

    void zza(Uri uri, String str);

    HttpURLConnection zzb(URL url);

    String zzc(String str);

    Uri.Builder zzd(Intent intent, String str, String str2);

    void zze(String str, Status status);

    Context zzf();
}

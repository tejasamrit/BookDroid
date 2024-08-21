package com.google.android.gms.internal.p007firebaseauthapi;

import com.bumptech.glide.load.Key;
import com.google.common.net.HttpHeaders;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import org.json.JSONException;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzur */
/* compiled from: com.google.firebase:firebase-auth@@20.0.1 */
public final class zzur {
    public static void zza(String str, zzty zzty, zzup zzup, Type type, zzuc zzuc) {
        BufferedOutputStream bufferedOutputStream;
        InputStream inputStream;
        BufferedReader bufferedReader;
        try {
            HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
            httpURLConnection.setDoOutput(true);
            byte[] bytes = zzty.zza().getBytes(Charset.defaultCharset());
            int length = bytes.length;
            httpURLConnection.setFixedLengthStreamingMode(length);
            httpURLConnection.setRequestProperty(HttpHeaders.CONTENT_TYPE, "application/json");
            zzuc.zza(httpURLConnection);
            bufferedOutputStream = new BufferedOutputStream(httpURLConnection.getOutputStream(), length);
            bufferedOutputStream.write(bytes, 0, length);
            bufferedOutputStream.close();
            int responseCode = httpURLConnection.getResponseCode();
            if (zzb(responseCode)) {
                inputStream = httpURLConnection.getInputStream();
            } else {
                inputStream = httpURLConnection.getErrorStream();
            }
            StringBuilder sb = new StringBuilder();
            bufferedReader = new BufferedReader(new InputStreamReader(inputStream, Key.STRING_CHARSET_NAME));
            while (true) {
                String readLine = bufferedReader.readLine();
                if (readLine == null) {
                    break;
                }
                sb.append(readLine);
            }
            bufferedReader.close();
            String sb2 = sb.toString();
            if (!zzb(responseCode)) {
                zzup.zza((String) zztx.zza(sb2, String.class));
                return;
            } else {
                zzup.zzb((zztz) zztx.zza(sb2, type));
                return;
            }
            throw th;
            throw th;
        } catch (zzpp | IOException | JSONException e) {
            zzup.zza(e.getMessage());
        } catch (Throwable th) {
            zzla.zza(th, th);
        }
    }

    private static final boolean zzb(int i) {
        return i >= 200 && i < 300;
    }
}

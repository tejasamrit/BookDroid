package com.google.android.gms.internal.p007firebaseauthapi;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.text.TextUtils;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.logging.Logger;
import com.google.firebase.auth.internal.zzai;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.WeakReference;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzth */
/* compiled from: com.google.firebase:firebase-auth@@20.0.1 */
public final class zzth extends AsyncTask<Void, Void, zztg> {
    private static final Logger zza = new Logger("FirebaseAuth", "GetAuthDomainTask");
    private final String zzb;
    private final String zzc;
    private final WeakReference<zztj> zzd;
    private final Uri.Builder zze;
    private final String zzf;

    public zzth(String str, String str2, Intent intent, zztj zztj) {
        this.zzb = Preconditions.checkNotEmpty(str);
        Preconditions.checkNotEmpty(str2);
        Preconditions.checkNotNull(intent);
        String checkNotEmpty = Preconditions.checkNotEmpty(intent.getStringExtra("com.google.firebase.auth.KEY_API_KEY"));
        Uri.Builder buildUpon = Uri.parse(zztj.zzc(checkNotEmpty)).buildUpon();
        buildUpon.appendPath("getProjectConfig").appendQueryParameter("key", checkNotEmpty).appendQueryParameter("androidPackageName", str).appendQueryParameter("sha1Cert", (String) Preconditions.checkNotNull(str2));
        this.zzc = buildUpon.build().toString();
        this.zzd = new WeakReference<>(zztj);
        this.zze = zztj.zzd(intent, str, str2);
        this.zzf = intent.getStringExtra("com.google.firebase.auth.KEY_CUSTOM_AUTH_DOMAIN");
    }

    private static byte[] zza(InputStream inputStream, int i) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            byte[] bArr = new byte[128];
            while (true) {
                int read = inputStream.read(bArr);
                if (read == -1) {
                    return byteArrayOutputStream.toByteArray();
                }
                byteArrayOutputStream.write(bArr, 0, read);
            }
        } finally {
            byteArrayOutputStream.close();
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: zzb */
    public final void onPostExecute(zztg zztg) {
        String str;
        Uri.Builder builder;
        zztj zztj = (zztj) this.zzd.get();
        String str2 = null;
        if (zztg != null) {
            str2 = zztg.zza();
            str = zztg.zzb();
        } else {
            str = null;
        }
        if (zztj == null) {
            zza.mo13204e("An error has occurred: the handler reference has returned null.", new Object[0]);
        } else if (TextUtils.isEmpty(str2) || (builder = this.zze) == null) {
            zztj.zze(this.zzb, zzai.zza(str));
        } else {
            builder.authority(str2);
            zztj.zza(this.zze.build(), this.zzb);
        }
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x00ef, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x00f0, code lost:
        r2 = zza;
        r1 = java.lang.String.valueOf(r1);
        r4 = new java.lang.StringBuilder(java.lang.String.valueOf(r1).length() + 33);
        r4.append("ConversionException encountered: ");
        r4.append(r1);
        r2.mo13204e(r4.toString(), new java.lang.Object[0]);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x0117, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x0118, code lost:
        r2 = zza;
        r1 = java.lang.String.valueOf(r1);
        r4 = new java.lang.StringBuilder(java.lang.String.valueOf(r1).length() + 26);
        r4.append("Null pointer encountered: ");
        r4.append(r1);
        r2.mo13204e(r4.toString(), new java.lang.Object[0]);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x013f, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x0140, code lost:
        r2 = zza;
        r1 = java.lang.String.valueOf(r1);
        r4 = new java.lang.StringBuilder(java.lang.String.valueOf(r1).length() + 22);
        r4.append("IOException occurred: ");
        r4.append(r1);
        r2.mo13204e(r4.toString(), new java.lang.Object[0]);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:?, code lost:
        return null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:?, code lost:
        return null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:43:?, code lost:
        return null;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x00d4 A[Catch:{ IOException -> 0x013f, NullPointerException -> 0x0117, zzpp -> 0x00ef }] */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x00ef A[ExcHandler: zzpp (r1v6 'e' com.google.android.gms.internal.firebase-auth-api.zzpp A[CUSTOM_DECLARE]), Splitter:B:4:0x0014] */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x0117 A[ExcHandler: NullPointerException (r1v3 'e' java.lang.NullPointerException A[CUSTOM_DECLARE]), Splitter:B:4:0x0014] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final /* bridge */ /* synthetic */ java.lang.Object doInBackground(java.lang.Object[] r7) {
        /*
            r6 = this;
            java.lang.Void[] r7 = (java.lang.Void[]) r7
            java.lang.String r7 = r6.zzf
            boolean r7 = android.text.TextUtils.isEmpty(r7)
            r0 = 0
            if (r7 != 0) goto L_0x0013
            java.lang.String r7 = r6.zzf
            com.google.android.gms.internal.firebase-auth-api.zztg r0 = com.google.android.gms.internal.p007firebaseauthapi.zztg.zzc(r7)
            goto L_0x0166
        L_0x0013:
            r7 = 0
            java.net.URL r1 = new java.net.URL     // Catch:{ IOException -> 0x013f, NullPointerException -> 0x0117, zzpp -> 0x00ef }
            java.lang.String r2 = r6.zzc     // Catch:{ IOException -> 0x013f, NullPointerException -> 0x0117, zzpp -> 0x00ef }
            r1.<init>(r2)     // Catch:{ IOException -> 0x013f, NullPointerException -> 0x0117, zzpp -> 0x00ef }
            java.lang.ref.WeakReference<com.google.android.gms.internal.firebase-auth-api.zztj> r2 = r6.zzd     // Catch:{ IOException -> 0x013f, NullPointerException -> 0x0117, zzpp -> 0x00ef }
            java.lang.Object r2 = r2.get()     // Catch:{ IOException -> 0x013f, NullPointerException -> 0x0117, zzpp -> 0x00ef }
            com.google.android.gms.internal.firebase-auth-api.zztj r2 = (com.google.android.gms.internal.p007firebaseauthapi.zztj) r2     // Catch:{ IOException -> 0x013f, NullPointerException -> 0x0117, zzpp -> 0x00ef }
            java.net.HttpURLConnection r1 = r2.zzb(r1)     // Catch:{ IOException -> 0x013f, NullPointerException -> 0x0117, zzpp -> 0x00ef }
            java.lang.String r3 = "Content-Type"
            java.lang.String r4 = "application/json; charset=UTF-8"
            r1.addRequestProperty(r3, r4)     // Catch:{ IOException -> 0x013f, NullPointerException -> 0x0117, zzpp -> 0x00ef }
            com.google.android.gms.internal.firebase-auth-api.zzuc r3 = new com.google.android.gms.internal.firebase-auth-api.zzuc     // Catch:{ IOException -> 0x013f, NullPointerException -> 0x0117, zzpp -> 0x00ef }
            android.content.Context r2 = r2.zzf()     // Catch:{ IOException -> 0x013f, NullPointerException -> 0x0117, zzpp -> 0x00ef }
            com.google.android.gms.internal.firebase-auth-api.zzua r4 = com.google.android.gms.internal.p007firebaseauthapi.zzua.zzb()     // Catch:{ IOException -> 0x013f, NullPointerException -> 0x0117, zzpp -> 0x00ef }
            java.lang.String r4 = r4.zza()     // Catch:{ IOException -> 0x013f, NullPointerException -> 0x0117, zzpp -> 0x00ef }
            r3.<init>(r2, r4)     // Catch:{ IOException -> 0x013f, NullPointerException -> 0x0117, zzpp -> 0x00ef }
            r3.zza(r1)     // Catch:{ IOException -> 0x013f, NullPointerException -> 0x0117, zzpp -> 0x00ef }
            int r2 = r1.getResponseCode()     // Catch:{ IOException -> 0x013f, NullPointerException -> 0x0117, zzpp -> 0x00ef }
            r3 = 200(0xc8, float:2.8E-43)
            r4 = 128(0x80, float:1.794E-43)
            if (r2 == r3) goto L_0x00b1
            int r3 = r1.getResponseCode()     // Catch:{ IOException -> 0x006a, NullPointerException -> 0x0117, zzpp -> 0x00ef }
            r5 = 400(0x190, float:5.6E-43)
            if (r3 < r5) goto L_0x0068
            java.io.InputStream r1 = r1.getErrorStream()     // Catch:{ IOException -> 0x006a, NullPointerException -> 0x0117, zzpp -> 0x00ef }
            if (r1 != 0) goto L_0x005d
            java.lang.String r1 = "WEB_INTERNAL_ERROR:Could not retrieve the authDomain for this project but did not receive an error response from the network request. Please try again."
            goto L_0x0092
        L_0x005d:
            java.lang.String r3 = new java.lang.String     // Catch:{ IOException -> 0x006a, NullPointerException -> 0x0117, zzpp -> 0x00ef }
            byte[] r1 = zza(r1, r4)     // Catch:{ IOException -> 0x006a, NullPointerException -> 0x0117, zzpp -> 0x00ef }
            r3.<init>(r1)     // Catch:{ IOException -> 0x006a, NullPointerException -> 0x0117, zzpp -> 0x00ef }
            r1 = r3
            goto L_0x0092
        L_0x0068:
            r1 = r0
            goto L_0x0092
        L_0x006a:
            r1 = move-exception
            com.google.android.gms.common.logging.Logger r3 = zza     // Catch:{ IOException -> 0x013f, NullPointerException -> 0x0117, zzpp -> 0x00ef }
            java.lang.String r1 = java.lang.String.valueOf(r1)     // Catch:{ IOException -> 0x013f, NullPointerException -> 0x0117, zzpp -> 0x00ef }
            java.lang.String r4 = java.lang.String.valueOf(r1)     // Catch:{ IOException -> 0x013f, NullPointerException -> 0x0117, zzpp -> 0x00ef }
            int r4 = r4.length()     // Catch:{ IOException -> 0x013f, NullPointerException -> 0x0117, zzpp -> 0x00ef }
            int r4 = r4 + 75
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x013f, NullPointerException -> 0x0117, zzpp -> 0x00ef }
            r5.<init>(r4)     // Catch:{ IOException -> 0x013f, NullPointerException -> 0x0117, zzpp -> 0x00ef }
            java.lang.String r4 = "Error parsing error message from response body in getErrorMessageFromBody. "
            r5.append(r4)     // Catch:{ IOException -> 0x013f, NullPointerException -> 0x0117, zzpp -> 0x00ef }
            r5.append(r1)     // Catch:{ IOException -> 0x013f, NullPointerException -> 0x0117, zzpp -> 0x00ef }
            java.lang.String r1 = r5.toString()     // Catch:{ IOException -> 0x013f, NullPointerException -> 0x0117, zzpp -> 0x00ef }
            java.lang.Object[] r4 = new java.lang.Object[r7]     // Catch:{ IOException -> 0x013f, NullPointerException -> 0x0117, zzpp -> 0x00ef }
            r3.mo13211w(r1, r4)     // Catch:{ IOException -> 0x013f, NullPointerException -> 0x0117, zzpp -> 0x00ef }
            goto L_0x0068
        L_0x0092:
            com.google.android.gms.common.logging.Logger r3 = zza     // Catch:{ IOException -> 0x013f, NullPointerException -> 0x0117, zzpp -> 0x00ef }
            r4 = 2
            java.lang.Object[] r4 = new java.lang.Object[r4]     // Catch:{ IOException -> 0x013f, NullPointerException -> 0x0117, zzpp -> 0x00ef }
            r4[r7] = r1     // Catch:{ IOException -> 0x013f, NullPointerException -> 0x0117, zzpp -> 0x00ef }
            r5 = 1
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)     // Catch:{ IOException -> 0x013f, NullPointerException -> 0x0117, zzpp -> 0x00ef }
            r4[r5] = r2     // Catch:{ IOException -> 0x013f, NullPointerException -> 0x0117, zzpp -> 0x00ef }
            java.lang.String r2 = "Error getting project config. Failed with %s %s"
            java.lang.String r2 = java.lang.String.format(r2, r4)     // Catch:{ IOException -> 0x013f, NullPointerException -> 0x0117, zzpp -> 0x00ef }
            java.lang.Object[] r4 = new java.lang.Object[r7]     // Catch:{ IOException -> 0x013f, NullPointerException -> 0x0117, zzpp -> 0x00ef }
            r3.mo13204e(r2, r4)     // Catch:{ IOException -> 0x013f, NullPointerException -> 0x0117, zzpp -> 0x00ef }
            com.google.android.gms.internal.firebase-auth-api.zztg r0 = com.google.android.gms.internal.p007firebaseauthapi.zztg.zzd(r1)     // Catch:{ IOException -> 0x013f, NullPointerException -> 0x0117, zzpp -> 0x00ef }
            goto L_0x0166
        L_0x00b1:
            com.google.android.gms.internal.firebase-auth-api.zzwf r2 = new com.google.android.gms.internal.firebase-auth-api.zzwf     // Catch:{ IOException -> 0x013f, NullPointerException -> 0x0117, zzpp -> 0x00ef }
            r2.<init>()     // Catch:{ IOException -> 0x013f, NullPointerException -> 0x0117, zzpp -> 0x00ef }
            java.lang.String r3 = new java.lang.String     // Catch:{ IOException -> 0x013f, NullPointerException -> 0x0117, zzpp -> 0x00ef }
            java.io.InputStream r1 = r1.getInputStream()     // Catch:{ IOException -> 0x013f, NullPointerException -> 0x0117, zzpp -> 0x00ef }
            byte[] r1 = zza(r1, r4)     // Catch:{ IOException -> 0x013f, NullPointerException -> 0x0117, zzpp -> 0x00ef }
            r3.<init>(r1)     // Catch:{ IOException -> 0x013f, NullPointerException -> 0x0117, zzpp -> 0x00ef }
            r2.zzc(r3)     // Catch:{ IOException -> 0x013f, NullPointerException -> 0x0117, zzpp -> 0x00ef }
            java.util.List r1 = r2.zzb()     // Catch:{ IOException -> 0x013f, NullPointerException -> 0x0117, zzpp -> 0x00ef }
            java.util.Iterator r1 = r1.iterator()     // Catch:{ IOException -> 0x013f, NullPointerException -> 0x0117, zzpp -> 0x00ef }
        L_0x00ce:
            boolean r2 = r1.hasNext()     // Catch:{ IOException -> 0x013f, NullPointerException -> 0x0117, zzpp -> 0x00ef }
            if (r2 == 0) goto L_0x0166
            java.lang.Object r2 = r1.next()     // Catch:{ IOException -> 0x013f, NullPointerException -> 0x0117, zzpp -> 0x00ef }
            java.lang.String r2 = (java.lang.String) r2     // Catch:{ IOException -> 0x013f, NullPointerException -> 0x0117, zzpp -> 0x00ef }
            java.lang.String r3 = "firebaseapp.com"
            boolean r3 = r2.endsWith(r3)     // Catch:{ IOException -> 0x013f, NullPointerException -> 0x0117, zzpp -> 0x00ef }
            if (r3 != 0) goto L_0x00ea
            java.lang.String r3 = "web.app"
            boolean r3 = r2.endsWith(r3)     // Catch:{ IOException -> 0x013f, NullPointerException -> 0x0117, zzpp -> 0x00ef }
            if (r3 == 0) goto L_0x00ce
        L_0x00ea:
            com.google.android.gms.internal.firebase-auth-api.zztg r0 = com.google.android.gms.internal.p007firebaseauthapi.zztg.zzc(r2)     // Catch:{ IOException -> 0x013f, NullPointerException -> 0x0117, zzpp -> 0x00ef }
            goto L_0x0166
        L_0x00ef:
            r1 = move-exception
            com.google.android.gms.common.logging.Logger r2 = zza
            java.lang.String r1 = java.lang.String.valueOf(r1)
            java.lang.String r3 = java.lang.String.valueOf(r1)
            int r3 = r3.length()
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            int r3 = r3 + 33
            r4.<init>(r3)
            java.lang.String r3 = "ConversionException encountered: "
            r4.append(r3)
            r4.append(r1)
            java.lang.String r1 = r4.toString()
            java.lang.Object[] r7 = new java.lang.Object[r7]
            r2.mo13204e(r1, r7)
            goto L_0x0166
        L_0x0117:
            r1 = move-exception
            com.google.android.gms.common.logging.Logger r2 = zza
            java.lang.String r1 = java.lang.String.valueOf(r1)
            java.lang.String r3 = java.lang.String.valueOf(r1)
            int r3 = r3.length()
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            int r3 = r3 + 26
            r4.<init>(r3)
            java.lang.String r3 = "Null pointer encountered: "
            r4.append(r3)
            r4.append(r1)
            java.lang.String r1 = r4.toString()
            java.lang.Object[] r7 = new java.lang.Object[r7]
            r2.mo13204e(r1, r7)
            goto L_0x0166
        L_0x013f:
            r1 = move-exception
            com.google.android.gms.common.logging.Logger r2 = zza
            java.lang.String r1 = java.lang.String.valueOf(r1)
            java.lang.String r3 = java.lang.String.valueOf(r1)
            int r3 = r3.length()
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            int r3 = r3 + 22
            r4.<init>(r3)
            java.lang.String r3 = "IOException occurred: "
            r4.append(r3)
            r4.append(r1)
            java.lang.String r1 = r4.toString()
            java.lang.Object[] r7 = new java.lang.Object[r7]
            r2.mo13204e(r1, r7)
        L_0x0166:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.p007firebaseauthapi.zzth.doInBackground(java.lang.Object[]):java.lang.Object");
    }

    /* access modifiers changed from: protected */
    public final /* bridge */ /* synthetic */ void onCancelled(Object obj) {
        zztg zztg = (zztg) obj;
        onPostExecute((zztg) null);
    }
}

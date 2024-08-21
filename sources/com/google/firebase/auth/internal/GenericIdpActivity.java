package com.google.firebase.auth.internal;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import androidx.browser.customtabs.CustomTabsIntent;
import androidx.fragment.app.FragmentActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.safeparcel.SafeParcelableSerializer;
import com.google.android.gms.common.util.AndroidUtilsLight;
import com.google.android.gms.common.util.DefaultClock;
import com.google.android.gms.common.util.Hex;
import com.google.android.gms.internal.p007firebaseauthapi.zzh;
import com.google.android.gms.internal.p007firebaseauthapi.zzth;
import com.google.android.gms.internal.p007firebaseauthapi.zzti;
import com.google.android.gms.internal.p007firebaseauthapi.zztj;
import com.google.android.gms.internal.p007firebaseauthapi.zzvh;
import com.google.android.gms.internal.p007firebaseauthapi.zzxg;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.core.ServerValues;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.UUID;
import java.util.concurrent.Executor;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: com.google.firebase:firebase-auth@@20.0.1 */
public class GenericIdpActivity extends FragmentActivity implements zztj {
    private static long zzc;
    private static final zzbm zze = zzbm.zza();
    private final Executor zzb = zzh.zza().zza(1);
    private boolean zzd = false;

    private final void zzh() {
        zzc = 0;
        this.zzd = false;
        Intent intent = new Intent();
        intent.putExtra("com.google.firebase.auth.internal.EXTRA_CANCELED", true);
        intent.setAction("com.google.firebase.auth.ACTION_RECEIVE_FIREBASE_AUTH_INTENT");
        if (!LocalBroadcastManager.getInstance(this).sendBroadcast(intent)) {
            zze.zzg(this, zzai.zza("WEB_CONTEXT_CANCELED"));
        } else {
            zze.zzh(this);
        }
        finish();
    }

    private final void zzi(Status status) {
        zzc = 0;
        this.zzd = false;
        Intent intent = new Intent();
        zzbl.zza(intent, status);
        intent.setAction("com.google.firebase.auth.ACTION_RECEIVE_FIREBASE_AUTH_INTENT");
        if (!LocalBroadcastManager.getInstance(this).sendBroadcast(intent)) {
            zze.zzg(getApplicationContext(), status);
        } else {
            zze.zzh(this);
        }
        finish();
    }

    /* access modifiers changed from: protected */
    public final void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        String action = getIntent().getAction();
        if ("com.google.firebase.auth.internal.NONGMSCORE_SIGN_IN".equals(action) || "com.google.firebase.auth.internal.NONGMSCORE_LINK".equals(action) || "com.google.firebase.auth.internal.NONGMSCORE_REAUTHENTICATE".equals(action) || "android.intent.action.VIEW".equals(action)) {
            long currentTimeMillis = DefaultClock.getInstance().currentTimeMillis();
            if (currentTimeMillis - zzc < 30000) {
                Log.e("GenericIdpActivity", "Could not start operation - already in progress");
                return;
            }
            zzc = currentTimeMillis;
            if (bundle != null) {
                this.zzd = bundle.getBoolean("com.google.firebase.auth.internal.KEY_STARTED_SIGN_IN");
                return;
            }
            return;
        }
        String valueOf = String.valueOf(action);
        Log.e("GenericIdpActivity", valueOf.length() != 0 ? "Could not do operation - unknown action: ".concat(valueOf) : new String("Could not do operation - unknown action: "));
        zzh();
    }

    public final void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
    }

    /* access modifiers changed from: protected */
    public final void onResume() {
        super.onResume();
        if ("android.intent.action.VIEW".equals(getIntent().getAction())) {
            Intent intent = getIntent();
            if (intent.hasExtra("firebaseError")) {
                zzi(zzbl.zzd(intent.getStringExtra("firebaseError")));
            } else if (!intent.hasExtra("link") || !intent.hasExtra("eventId")) {
                zzh();
            } else {
                String stringExtra = intent.getStringExtra("link");
                String stringExtra2 = intent.getStringExtra("eventId");
                String packageName = getPackageName();
                boolean booleanExtra = intent.getBooleanExtra("encryptionEnabled", true);
                zzi zzd2 = zzj.zza().zzd(this, packageName, stringExtra2);
                if (zzd2 == null) {
                    zzh();
                }
                if (booleanExtra) {
                    stringExtra = zzk.zza(getApplicationContext(), FirebaseApp.getInstance(zzd2.zze()).getPersistenceKey()).zzc(stringExtra);
                }
                zzxg zzxg = new zzxg(zzd2, stringExtra);
                String zzd3 = zzd2.zzd();
                String zzb2 = zzd2.zzb();
                zzxg.zzd(zzd3);
                if ("com.google.firebase.auth.internal.NONGMSCORE_SIGN_IN".equals(zzb2) || "com.google.firebase.auth.internal.NONGMSCORE_LINK".equals(zzb2) || "com.google.firebase.auth.internal.NONGMSCORE_REAUTHENTICATE".equals(zzb2)) {
                    zzc = 0;
                    this.zzd = false;
                    Intent intent2 = new Intent();
                    SafeParcelableSerializer.serializeToIntentExtra(zzxg, intent2, "com.google.firebase.auth.internal.VERIFY_ASSERTION_REQUEST");
                    intent2.putExtra("com.google.firebase.auth.internal.OPERATION", zzb2);
                    intent2.setAction("com.google.firebase.auth.ACTION_RECEIVE_FIREBASE_AUTH_INTENT");
                    if (!LocalBroadcastManager.getInstance(this).sendBroadcast(intent2)) {
                        SharedPreferences.Editor edit = getApplicationContext().getSharedPreferences("com.google.firebase.auth.internal.ProcessDeathHelper", 0).edit();
                        edit.putString("verifyAssertionRequest", SafeParcelableSerializer.serializeToString(zzxg));
                        edit.putString("operation", zzb2);
                        edit.putString("tenantId", zzd3);
                        edit.putLong(ServerValues.NAME_OP_TIMESTAMP, DefaultClock.getInstance().currentTimeMillis());
                        edit.commit();
                    } else {
                        zze.zzh(this);
                    }
                    finish();
                    return;
                }
                Log.e("GenericIdpActivity", zzb2.length() != 0 ? "unsupported operation: ".concat(zzb2) : new String("unsupported operation: "));
                zzh();
            }
        } else if (!this.zzd) {
            String packageName2 = getPackageName();
            try {
                String lowerCase = Hex.bytesToStringUppercase(AndroidUtilsLight.getPackageCertificateHashBytes(this, packageName2)).toLowerCase(Locale.US);
                FirebaseApp instance = FirebaseApp.getInstance(getIntent().getStringExtra("com.google.firebase.auth.KEY_FIREBASE_APP_NAME"));
                if (!zzvh.zzb(instance)) {
                    new zzth(packageName2, lowerCase, getIntent(), this).executeOnExecutor(this.zzb, new Void[0]);
                } else {
                    zza(zzg(Uri.parse(zzvh.zzg(instance.getOptions().getApiKey())).buildUpon(), getIntent(), packageName2, lowerCase).build(), packageName2);
                }
            } catch (PackageManager.NameNotFoundException e) {
                String valueOf = String.valueOf(e);
                StringBuilder sb = new StringBuilder(String.valueOf(packageName2).length() + 34 + String.valueOf(valueOf).length());
                sb.append("Could not get package signature: ");
                sb.append(packageName2);
                sb.append(" ");
                sb.append(valueOf);
                Log.e("GenericIdpActivity", sb.toString());
                zze(packageName2, (Status) null);
            }
            this.zzd = true;
        } else {
            zzh();
        }
    }

    /* access modifiers changed from: protected */
    public final void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putBoolean("com.google.firebase.auth.internal.KEY_STARTED_SIGN_IN", this.zzd);
    }

    public final void zza(Uri uri, String str) {
        if (getPackageManager().resolveActivity(new Intent("android.intent.action.VIEW"), 0) != null) {
            List<ResolveInfo> queryIntentServices = getPackageManager().queryIntentServices(new Intent("android.support.customtabs.action.CustomTabsService"), 0);
            if (queryIntentServices == null || queryIntentServices.isEmpty()) {
                Intent intent = new Intent("android.intent.action.VIEW", uri);
                intent.putExtra("com.android.browser.application_id", str);
                intent.addFlags(1073741824);
                intent.addFlags(268435456);
                startActivity(intent);
                return;
            }
            CustomTabsIntent build = new CustomTabsIntent.Builder().build();
            build.intent.addFlags(1073741824);
            build.intent.addFlags(268435456);
            build.launchUrl(this, uri);
            return;
        }
        Log.e("GenericIdpActivity", "Device cannot resolve intent for: android.intent.action.VIEW");
        zze(str, (Status) null);
    }

    public final HttpURLConnection zzb(URL url) {
        try {
            return (HttpURLConnection) url.openConnection();
        } catch (IOException unused) {
            Log.e("GenericIdpActivity", "Error generating URL connection");
            return null;
        }
    }

    public final String zzc(String str) {
        return zzvh.zze(str);
    }

    public final Uri.Builder zzd(Intent intent, String str, String str2) {
        return zzg(new Uri.Builder().scheme("https").appendPath("__").appendPath("auth").appendPath("handler"), intent, str, str2);
    }

    public final void zze(String str, Status status) {
        if (status == null) {
            zzh();
        } else {
            zzi(status);
        }
    }

    public final Context zzf() {
        return getApplicationContext();
    }

    public final Uri.Builder zzg(Uri.Builder builder, Intent intent, String str, String str2) {
        String str3;
        String str4;
        Uri.Builder builder2 = builder;
        Intent intent2 = intent;
        String stringExtra = intent2.getStringExtra("com.google.firebase.auth.KEY_API_KEY");
        String stringExtra2 = intent2.getStringExtra("com.google.firebase.auth.KEY_PROVIDER_ID");
        String stringExtra3 = intent2.getStringExtra("com.google.firebase.auth.KEY_TENANT_ID");
        String stringExtra4 = intent2.getStringExtra("com.google.firebase.auth.KEY_FIREBASE_APP_NAME");
        ArrayList<String> stringArrayListExtra = intent2.getStringArrayListExtra("com.google.firebase.auth.KEY_PROVIDER_SCOPES");
        String join = (stringArrayListExtra == null || stringArrayListExtra.isEmpty()) ? null : TextUtils.join(",", stringArrayListExtra);
        Bundle bundleExtra = intent2.getBundleExtra("com.google.firebase.auth.KEY_PROVIDER_CUSTOM_PARAMS");
        if (bundleExtra == null) {
            str3 = null;
        } else {
            JSONObject jSONObject = new JSONObject();
            try {
                for (String str5 : bundleExtra.keySet()) {
                    String string = bundleExtra.getString(str5);
                    if (!TextUtils.isEmpty(string)) {
                        jSONObject.put(str5, string);
                    }
                }
            } catch (JSONException unused) {
                Log.e("GenericIdpActivity", "Unexpected JSON exception when serializing developer specified custom params");
            }
            str3 = jSONObject.toString();
        }
        String uuid = UUID.randomUUID().toString();
        String zza = zzti.zza(this, UUID.randomUUID().toString());
        String action = intent.getAction();
        String stringExtra5 = intent2.getStringExtra("com.google.firebase.auth.internal.CLIENT_VERSION");
        String str6 = uuid;
        String str7 = zza;
        String str8 = uuid;
        String str9 = action;
        String str10 = str3;
        String str11 = stringExtra2;
        String str12 = stringExtra2;
        String str13 = "GenericIdpActivity";
        String str14 = join;
        zzj.zza().zzb(getApplicationContext(), str, str6, zza, str9, str11, stringExtra3, stringExtra4);
        String zzb2 = zzk.zza(getApplicationContext(), FirebaseApp.getInstance(stringExtra4).getPersistenceKey()).zzb();
        if (TextUtils.isEmpty(zzb2)) {
            Log.e(str13, "Could not generate an encryption key for Generic IDP - cancelling flow.");
            zzi(zzai.zza("Failed to generate/retrieve public encryption key for Generic IDP flow."));
            return null;
        }
        String str15 = str7;
        if (str15 == null) {
            return null;
        }
        Uri.Builder appendQueryParameter = builder2.appendQueryParameter("eid", "p");
        String valueOf = String.valueOf(stringExtra5);
        if (valueOf.length() != 0) {
            str4 = "X".concat(valueOf);
        } else {
            str4 = new String("X");
        }
        appendQueryParameter.appendQueryParameter("v", str4).appendQueryParameter("authType", "signInWithRedirect").appendQueryParameter("apiKey", stringExtra).appendQueryParameter("providerId", str12).appendQueryParameter("sessionId", str15).appendQueryParameter("eventId", str8).appendQueryParameter("apn", str).appendQueryParameter("sha1Cert", str2).appendQueryParameter("publicKey", zzb2);
        if (!TextUtils.isEmpty(str14)) {
            builder2.appendQueryParameter("scopes", str14);
        }
        if (!TextUtils.isEmpty(str10)) {
            builder2.appendQueryParameter("customParameters", str10);
        }
        if (!TextUtils.isEmpty(stringExtra3)) {
            builder2.appendQueryParameter("tid", stringExtra3);
        }
        return builder2;
    }
}

package com.google.android.gms.internal.p007firebaseauthapi;

import android.content.Context;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Build;
import android.util.Base64;
import com.google.android.gms.auth.api.phone.SmsRetriever;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.logging.Logger;
import com.google.android.gms.common.wrappers.Wrappers;
import com.google.firebase.auth.PhoneAuthCredential;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzvd */
/* compiled from: com.google.firebase:firebase-auth@@20.0.1 */
final class zzvd {
    /* access modifiers changed from: private */
    public static final Logger zza = new Logger("FirebaseAuth", "SmsRetrieverHelper");
    private final Context zzb;
    private final ScheduledExecutorService zzc;
    /* access modifiers changed from: private */
    public final HashMap<String, zzvc> zzd = new HashMap<>();

    zzvd(Context context) {
        this.zzb = (Context) Preconditions.checkNotNull(context);
        zzh.zza();
        this.zzc = Executors.unconfigurableScheduledExecutorService(Executors.newScheduledThreadPool(1));
    }

    static String zzf(String str) {
        Matcher matcher = Pattern.compile("(?<!\\d)\\d{6}(?!\\d)").matcher(str);
        if (matcher.find()) {
            return matcher.group();
        }
        return null;
    }

    static /* synthetic */ void zzj(zzvd zzvd, String str) {
        zzvc zzvc = zzvd.zzd.get(str);
        if (zzvc != null && !zzaf.zzb(zzvc.zzd) && !zzaf.zzb(zzvc.zze) && !zzvc.zzb.isEmpty()) {
            for (zztb zzi : zzvc.zzb) {
                zzi.zzi(PhoneAuthCredential.zzb(zzvc.zzd, zzvc.zze));
            }
            zzvc.zzh = true;
        }
    }

    private static String zzm(String str, String str2) {
        StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 1 + String.valueOf(str2).length());
        sb.append(str);
        sb.append(" ");
        sb.append(str2);
        String sb2 = sb.toString();
        try {
            MessageDigest instance = MessageDigest.getInstance("SHA-256");
            instance.update(sb2.getBytes(zzq.zzc));
            String substring = Base64.encodeToString(Arrays.copyOf(instance.digest(), 9), 3).substring(0, 11);
            Logger logger = zza;
            StringBuilder sb3 = new StringBuilder(String.valueOf(str).length() + 19 + String.valueOf(substring).length());
            sb3.append("Package: ");
            sb3.append(str);
            sb3.append(" -- Hash: ");
            sb3.append(substring);
            logger.mo13202d(sb3.toString(), new Object[0]);
            return substring;
        } catch (NoSuchAlgorithmException e) {
            Logger logger2 = zza;
            String valueOf = String.valueOf(e.getMessage());
            logger2.mo13204e(valueOf.length() != 0 ? "NoSuchAlgorithm: ".concat(valueOf) : new String("NoSuchAlgorithm: "), new Object[0]);
            return null;
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: zzn */
    public final void zzg(String str) {
        zzvc zzvc = this.zzd.get(str);
        if (zzvc != null) {
            if (!zzvc.zzi) {
                zzo(str);
            }
            zze(str);
        }
    }

    /* access modifiers changed from: private */
    public final void zzo(String str) {
        zzvc zzvc = this.zzd.get(str);
        if (zzvc != null && !zzvc.zzh && !zzaf.zzb(zzvc.zzd)) {
            zza.mo13211w("Timed out waiting for SMS.", new Object[0]);
            for (zztb zzj : zzvc.zzb) {
                zzj.zzj(zzvc.zzd);
            }
            zzvc.zzi = true;
        }
    }

    /* access modifiers changed from: package-private */
    public final boolean zza(String str) {
        return this.zzd.get(str) != null;
    }

    /* access modifiers changed from: package-private */
    public final void zzb(String str, zztb zztb, long j, boolean z) {
        this.zzd.put(str, new zzvc(j, z));
        zzc(zztb, str);
        zzvc zzvc = this.zzd.get(str);
        if (zzvc.zza <= 0) {
            zza.mo13211w("Timeout of 0 specified; SmsRetriever will not start.", new Object[0]);
            return;
        }
        zzvc.zzf = this.zzc.schedule(new zzuy(this, str), zzvc.zza, TimeUnit.SECONDS);
        if (!zzvc.zzc) {
            zza.mo13211w("SMS auto-retrieval unavailable; SmsRetriever will not start.", new Object[0]);
            return;
        }
        zzvb zzvb = new zzvb(this, str);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(SmsRetriever.SMS_RETRIEVED_ACTION);
        this.zzb.getApplicationContext().registerReceiver(zzvb, intentFilter);
        SmsRetriever.getClient(this.zzb).startSmsRetriever().addOnFailureListener(new zzuz(this));
    }

    /* access modifiers changed from: package-private */
    public final void zzc(zztb zztb, String str) {
        zzvc zzvc = this.zzd.get(str);
        if (zzvc != null) {
            zzvc.zzb.add(zztb);
            if (zzvc.zzg) {
                zztb.zzh(zzvc.zzd);
            }
            if (zzvc.zzh) {
                zztb.zzi(PhoneAuthCredential.zzb(zzvc.zzd, zzvc.zze));
            }
            if (zzvc.zzi) {
                zztb.zzj(zzvc.zzd);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public final String zzd() {
        Signature[] signatureArr;
        try {
            String packageName = this.zzb.getPackageName();
            if (Build.VERSION.SDK_INT < 28) {
                signatureArr = Wrappers.packageManager(this.zzb).getPackageInfo(packageName, 64).signatures;
            } else {
                signatureArr = Wrappers.packageManager(this.zzb).getPackageInfo(packageName, 134217728).signingInfo.getApkContentsSigners();
            }
            String zzm = zzm(packageName, signatureArr[0].toCharsString());
            if (zzm != null) {
                return zzm;
            }
            zza.mo13204e("Hash generation failed.", new Object[0]);
            return null;
        } catch (PackageManager.NameNotFoundException unused) {
            zza.mo13204e("Unable to find package to obtain hash.", new Object[0]);
            return null;
        }
    }

    /* access modifiers changed from: package-private */
    public final void zze(String str) {
        zzvc zzvc = this.zzd.get(str);
        if (zzvc != null) {
            ScheduledFuture<?> scheduledFuture = zzvc.zzf;
            if (scheduledFuture != null && !scheduledFuture.isDone()) {
                zzvc.zzf.cancel(false);
            }
            zzvc.zzb.clear();
            this.zzd.remove(str);
        }
    }
}

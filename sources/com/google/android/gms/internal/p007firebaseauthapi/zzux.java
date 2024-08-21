package com.google.android.gms.internal.p007firebaseauthapi;

import android.app.Activity;
import androidx.collection.ArrayMap;
import com.google.android.gms.common.util.DefaultClock;
import com.google.firebase.auth.PhoneAuthProvider;
import java.util.Map;
import java.util.concurrent.Executor;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzux */
/* compiled from: com.google.firebase:firebase-auth@@20.0.1 */
public final class zzux {
    private static final Map<String, zzuw> zza = new ArrayMap();

    public static void zza() {
        zza.clear();
    }

    public static boolean zzb(String str, PhoneAuthProvider.OnVerificationStateChangedCallbacks onVerificationStateChangedCallbacks, Activity activity, Executor executor) {
        Map<String, zzuw> map = zza;
        if (map.containsKey(str)) {
            zzuw zzuw = map.get(str);
            if (DefaultClock.getInstance().currentTimeMillis() - zzuw.zzb < 120000) {
                zzun zzun = zzuw.zza;
                if (zzun == null) {
                    return true;
                }
                zzun.zzi(onVerificationStateChangedCallbacks, activity, executor, str);
                return true;
            }
            zze(str, (zzun) null);
            return false;
        }
        zze(str, (zzun) null);
        return false;
    }

    public static PhoneAuthProvider.OnVerificationStateChangedCallbacks zzc(String str, PhoneAuthProvider.OnVerificationStateChangedCallbacks onVerificationStateChangedCallbacks, zzun zzun) {
        zze(str, zzun);
        return new zzuv(onVerificationStateChangedCallbacks, str);
    }

    private static void zze(String str, zzun zzun) {
        zza.put(str, new zzuw(zzun, DefaultClock.getInstance().currentTimeMillis()));
    }
}

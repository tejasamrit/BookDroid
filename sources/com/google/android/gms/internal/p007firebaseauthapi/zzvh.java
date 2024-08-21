package com.google.android.gms.internal.p007firebaseauthapi;

import androidx.collection.ArrayMap;
import com.google.firebase.FirebaseApp;
import java.lang.ref.WeakReference;
import java.util.Map;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzvh */
/* compiled from: com.google.firebase:firebase-auth@@20.0.1 */
public final class zzvh {
    private static final Map<String, zzvf> zza = new ArrayMap();
    private static final Map<String, WeakReference<zzvg>> zzb = new ArrayMap();

    public static void zza(FirebaseApp firebaseApp, String str, int i) {
        String apiKey = firebaseApp.getOptions().getApiKey();
        Map<String, zzvf> map = zza;
        synchronized (map) {
            map.put(apiKey, new zzvf(str, i));
        }
        Map<String, WeakReference<zzvg>> map2 = zzb;
        synchronized (map2) {
            if (map2.containsKey(apiKey)) {
                zzvg zzvg = (zzvg) map2.get(apiKey).get();
                if (zzvg != null) {
                    zzvg.zza();
                } else {
                    map.remove(apiKey);
                }
            }
        }
    }

    public static boolean zzb(FirebaseApp firebaseApp) {
        return zza.containsKey(firebaseApp.getOptions().getApiKey());
    }

    public static void zzc(String str, zzvg zzvg) {
        Map<String, WeakReference<zzvg>> map = zzb;
        synchronized (map) {
            map.put(str, new WeakReference(zzvg));
        }
    }

    public static String zzd(String str) {
        zzvf zzvf;
        String str2;
        Map<String, zzvf> map = zza;
        synchronized (map) {
            zzvf = map.get(str);
        }
        if (zzvf != null) {
            String valueOf = String.valueOf(zzh(zzvf.zza(), zzvf.zzb(), zzvf.zza().contains(":")));
            str2 = valueOf.length() != 0 ? "".concat(valueOf) : new String("");
        } else {
            str2 = "https://";
        }
        return String.valueOf(str2).concat("securetoken.googleapis.com/v1");
    }

    public static String zze(String str) {
        zzvf zzvf;
        String str2;
        Map<String, zzvf> map = zza;
        synchronized (map) {
            zzvf = map.get(str);
        }
        if (zzvf != null) {
            String valueOf = String.valueOf(zzh(zzvf.zza(), zzvf.zzb(), zzvf.zza().contains(":")));
            str2 = valueOf.length() != 0 ? "".concat(valueOf) : new String("");
        } else {
            str2 = "https://";
        }
        return String.valueOf(str2).concat("www.googleapis.com/identitytoolkit/v3/relyingparty");
    }

    public static String zzf(String str) {
        zzvf zzvf;
        String str2;
        Map<String, zzvf> map = zza;
        synchronized (map) {
            zzvf = map.get(str);
        }
        if (zzvf != null) {
            String valueOf = String.valueOf(zzh(zzvf.zza(), zzvf.zzb(), zzvf.zza().contains(":")));
            str2 = valueOf.length() != 0 ? "".concat(valueOf) : new String("");
        } else {
            str2 = "https://";
        }
        return String.valueOf(str2).concat("identitytoolkit.googleapis.com/v2/accounts");
    }

    public static String zzg(String str) {
        zzvf zzvf;
        Map<String, zzvf> map = zza;
        synchronized (map) {
            zzvf = map.get(str);
        }
        if (zzvf != null) {
            return String.valueOf(zzh(zzvf.zza(), zzvf.zzb(), zzvf.zza().contains(":"))).concat("emulator/auth/handler");
        }
        throw new IllegalStateException("Tried to get the emulator widget endpoint, but no emulator endpoint overrides found.");
    }

    private static String zzh(String str, int i, boolean z) {
        if (z) {
            StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 22);
            sb.append("http://[");
            sb.append(str);
            sb.append("]:");
            sb.append(i);
            sb.append("/");
            return sb.toString();
        }
        StringBuilder sb2 = new StringBuilder(String.valueOf(str).length() + 20);
        sb2.append("http://");
        sb2.append(str);
        sb2.append(":");
        sb2.append(i);
        sb2.append("/");
        return sb2.toString();
    }
}

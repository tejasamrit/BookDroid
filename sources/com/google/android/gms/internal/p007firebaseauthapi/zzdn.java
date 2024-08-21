package com.google.android.gms.internal.p007firebaseauthapi;

import java.security.GeneralSecurityException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.util.Arrays;
import java.util.Locale;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzdn */
/* compiled from: com.google.firebase:firebase-auth@@20.0.1 */
public final class zzdn implements zzaw {
    private static final String zza = "zzdn";
    private KeyStore zzb = new zzdm().zza;

    public final synchronized boolean zza(String str) {
        return str.toLowerCase(Locale.US).startsWith("android-keystore://");
    }

    public final synchronized zzag zzb(String str) throws GeneralSecurityException {
        zzdl zzdl;
        zzdl = new zzdl(zzkr.zzc("android-keystore://", str), this.zzb);
        byte[] zza2 = zzkp.zza(10);
        byte[] bArr = new byte[0];
        if (!Arrays.equals(zza2, zzdl.zzb(zzdl.zza(zza2, bArr), bArr))) {
            throw new KeyStoreException("cannot use Android Keystore: encryption/decryption of non-empty message and empty aad returns an incorrect result");
        }
        return zzdl;
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:12:?, code lost:
        java.lang.Thread.sleep(20);
        r0 = java.security.KeyStore.getInstance("AndroidKeyStore");
        r2.zzb = r0;
        r0.load((java.security.KeyStore.LoadStoreParameter) null);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x002e, code lost:
        return r2.zzb.containsAlias(r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x002f, code lost:
        r3 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0035, code lost:
        throw new java.security.GeneralSecurityException(r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:?, code lost:
        android.util.Log.w(zza, "Keystore is temporarily unavailable, wait 20ms, reinitialize Keystore and try again.");
     */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* JADX WARNING: Missing exception handler attribute for start block: B:13:0x0027 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:8:0x000f */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final synchronized boolean zzc(java.lang.String r3) throws java.security.GeneralSecurityException {
        /*
            r2 = this;
            monitor-enter(r2)
            java.lang.String r0 = "android-keystore://"
            java.lang.String r3 = com.google.android.gms.internal.p007firebaseauthapi.zzkr.zzc(r0, r3)     // Catch:{ all -> 0x0036 }
            java.security.KeyStore r0 = r2.zzb     // Catch:{ NullPointerException -> 0x000f }
            boolean r3 = r0.containsAlias(r3)     // Catch:{ NullPointerException -> 0x000f }
            monitor-exit(r2)
            return r3
        L_0x000f:
            java.lang.String r0 = zza     // Catch:{ all -> 0x0036 }
            java.lang.String r1 = "Keystore is temporarily unavailable, wait 20ms, reinitialize Keystore and try again."
            android.util.Log.w(r0, r1)     // Catch:{ all -> 0x0036 }
            r0 = 20
            java.lang.Thread.sleep(r0)     // Catch:{ IOException -> 0x002f, InterruptedException -> 0x0027 }
            java.lang.String r0 = "AndroidKeyStore"
            java.security.KeyStore r0 = java.security.KeyStore.getInstance(r0)     // Catch:{ IOException -> 0x002f, InterruptedException -> 0x0027 }
            r2.zzb = r0     // Catch:{ IOException -> 0x002f, InterruptedException -> 0x0027 }
            r1 = 0
            r0.load(r1)     // Catch:{ IOException -> 0x002f, InterruptedException -> 0x0027 }
        L_0x0027:
            java.security.KeyStore r0 = r2.zzb     // Catch:{ all -> 0x0036 }
            boolean r3 = r0.containsAlias(r3)     // Catch:{ all -> 0x0036 }
            monitor-exit(r2)
            return r3
        L_0x002f:
            r3 = move-exception
            java.security.GeneralSecurityException r0 = new java.security.GeneralSecurityException     // Catch:{ all -> 0x0036 }
            r0.<init>(r3)     // Catch:{ all -> 0x0036 }
            throw r0     // Catch:{ all -> 0x0036 }
        L_0x0036:
            r3 = move-exception
            monitor-exit(r2)
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.p007firebaseauthapi.zzdn.zzc(java.lang.String):boolean");
    }
}

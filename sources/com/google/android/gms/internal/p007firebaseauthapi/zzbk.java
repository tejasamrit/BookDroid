package com.google.android.gms.internal.p007firebaseauthapi;

import java.security.GeneralSecurityException;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzbk */
/* compiled from: com.google.firebase:firebase-auth@@20.0.1 */
public final class zzbk {
    private static final Logger zza = Logger.getLogger(zzbk.class.getName());
    private static final ConcurrentMap<String, zzbj> zzb = new ConcurrentHashMap();
    private static final ConcurrentMap<String, zzbi> zzc = new ConcurrentHashMap();
    private static final ConcurrentMap<String, Boolean> zzd = new ConcurrentHashMap();
    private static final ConcurrentMap<String, Object> zze = new ConcurrentHashMap();
    private static final ConcurrentMap<Class<?>, zzbd<?, ?>> zzf = new ConcurrentHashMap();

    private zzbk() {
    }

    public static synchronized <KeyProtoT extends zzaar> void zza(zzas<KeyProtoT> zzas, boolean z) throws GeneralSecurityException {
        synchronized (zzbk.class) {
            String zzb2 = zzas.zzb();
            zzo(zzb2, zzas.getClass(), true);
            ConcurrentMap<String, zzbj> concurrentMap = zzb;
            if (!concurrentMap.containsKey(zzb2)) {
                concurrentMap.put(zzb2, new zzbg(zzas));
                zzc.put(zzb2, new zzbi(zzas));
            }
            zzd.put(zzb2, true);
        }
    }

    public static synchronized <KeyProtoT extends zzaar, PublicKeyProtoT extends zzaar> void zzb(zzbf<KeyProtoT, PublicKeyProtoT> zzbf, zzas<PublicKeyProtoT> zzas, boolean z) throws GeneralSecurityException {
        Class<?> zze2;
        synchronized (zzbk.class) {
            zzo("type.googleapis.com/google.crypto.tink.EciesAeadHkdfPrivateKey", zzbf.getClass(), true);
            zzo("type.googleapis.com/google.crypto.tink.EciesAeadHkdfPublicKey", zzas.getClass(), false);
            ConcurrentMap<String, zzbj> concurrentMap = zzb;
            if (concurrentMap.containsKey("type.googleapis.com/google.crypto.tink.EciesAeadHkdfPrivateKey") && (zze2 = ((zzbj) concurrentMap.get("type.googleapis.com/google.crypto.tink.EciesAeadHkdfPrivateKey")).zze()) != null) {
                if (!zze2.equals(zzas.getClass())) {
                    Logger logger = zza;
                    Level level = Level.WARNING;
                    StringBuilder sb = new StringBuilder(219);
                    sb.append("Attempted overwrite of a registered key manager for key type ");
                    sb.append("type.googleapis.com/google.crypto.tink.EciesAeadHkdfPrivateKey");
                    sb.append(" with inconsistent public key type ");
                    sb.append("type.googleapis.com/google.crypto.tink.EciesAeadHkdfPublicKey");
                    logger.logp(level, "com.google.crypto.tink.Registry", "registerAsymmetricKeyManagers", sb.toString());
                    throw new GeneralSecurityException(String.format("public key manager corresponding to %s is already registered with %s, cannot be re-registered with %s", new Object[]{zzbf.getClass().getName(), zze2.getName(), zzas.getClass().getName()}));
                }
            }
            if (!concurrentMap.containsKey("type.googleapis.com/google.crypto.tink.EciesAeadHkdfPrivateKey") || ((zzbj) concurrentMap.get("type.googleapis.com/google.crypto.tink.EciesAeadHkdfPrivateKey")).zze() == null) {
                concurrentMap.put("type.googleapis.com/google.crypto.tink.EciesAeadHkdfPrivateKey", new zzbh(zzbf, zzas));
                zzc.put("type.googleapis.com/google.crypto.tink.EciesAeadHkdfPrivateKey", new zzbi(zzbf));
            }
            ConcurrentMap<String, Boolean> concurrentMap2 = zzd;
            concurrentMap2.put("type.googleapis.com/google.crypto.tink.EciesAeadHkdfPrivateKey", true);
            if (!concurrentMap.containsKey("type.googleapis.com/google.crypto.tink.EciesAeadHkdfPublicKey")) {
                concurrentMap.put("type.googleapis.com/google.crypto.tink.EciesAeadHkdfPublicKey", new zzbg(zzas));
            }
            concurrentMap2.put("type.googleapis.com/google.crypto.tink.EciesAeadHkdfPublicKey", false);
        }
    }

    public static synchronized <B, P> void zzc(zzbd<B, P> zzbd) throws GeneralSecurityException {
        synchronized (zzbk.class) {
            if (zzbd != null) {
                Class<P> zzb2 = zzbd.zzb();
                ConcurrentMap<Class<?>, zzbd<?, ?>> concurrentMap = zzf;
                if (concurrentMap.containsKey(zzb2)) {
                    zzbd zzbd2 = (zzbd) concurrentMap.get(zzb2);
                    if (!zzbd.getClass().equals(zzbd2.getClass())) {
                        Logger logger = zza;
                        Level level = Level.WARNING;
                        String valueOf = String.valueOf(zzb2);
                        StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 56);
                        sb.append("Attempted overwrite of a registered SetWrapper for type ");
                        sb.append(valueOf);
                        logger.logp(level, "com.google.crypto.tink.Registry", "registerPrimitiveWrapper", sb.toString());
                        throw new GeneralSecurityException(String.format("SetWrapper for primitive (%s) is already registered to be %s, cannot be re-registered with %s", new Object[]{zzb2.getName(), zzbd2.getClass().getName(), zzbd.getClass().getName()}));
                    }
                }
                concurrentMap.put(zzb2, zzbd);
            } else {
                throw new IllegalArgumentException("wrapper must be non-null");
            }
        }
    }

    public static zzam<?> zzd(String str) throws GeneralSecurityException {
        return zzn(str).zzb();
    }

    public static synchronized zzho zze(zzht zzht) throws GeneralSecurityException {
        zzho zzd2;
        synchronized (zzbk.class) {
            zzam<?> zzd3 = zzd(zzht.zza());
            if (((Boolean) zzd.get(zzht.zza())).booleanValue()) {
                zzd2 = zzd3.zzd(zzht.zzb());
            } else {
                String valueOf = String.valueOf(zzht.zza());
                throw new GeneralSecurityException(valueOf.length() != 0 ? "newKey-operation not permitted for key type ".concat(valueOf) : new String("newKey-operation not permitted for key type "));
            }
        }
        return zzd2;
    }

    public static synchronized zzaar zzf(zzht zzht) throws GeneralSecurityException {
        zzaar zzc2;
        synchronized (zzbk.class) {
            zzam<?> zzd2 = zzd(zzht.zza());
            if (((Boolean) zzd.get(zzht.zza())).booleanValue()) {
                zzc2 = zzd2.zzc(zzht.zzb());
            } else {
                String valueOf = String.valueOf(zzht.zza());
                throw new GeneralSecurityException(valueOf.length() != 0 ? "newKey-operation not permitted for key type ".concat(valueOf) : new String("newKey-operation not permitted for key type "));
            }
        }
        return zzc2;
    }

    public static zzho zzg(String str, zzym zzym) throws GeneralSecurityException {
        zzam zzp = zzp(str, (Class) null);
        if (zzp instanceof zzbe) {
            return ((zzbe) zzp).zze(zzym);
        }
        StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 48);
        sb.append("manager for key type ");
        sb.append(str);
        sb.append(" is not a PrivateKeyManager");
        throw new GeneralSecurityException(sb.toString());
    }

    public static <P> P zzh(String str, zzaar zzaar, Class<P> cls) throws GeneralSecurityException {
        return zzp(str, cls).zzb(zzaar);
    }

    public static <P> P zzi(String str, byte[] bArr, Class<P> cls) throws GeneralSecurityException {
        return zzq(str, zzym.zzm(bArr), cls);
    }

    @Deprecated
    public static <P> P zzj(zzho zzho) throws GeneralSecurityException {
        return zzq(zzho.zza(), zzho.zzb(), (Class) null);
    }

    public static <P> P zzk(zzho zzho, Class<P> cls) throws GeneralSecurityException {
        return zzq(zzho.zza(), zzho.zzb(), cls);
    }

    /* JADX WARNING: type inference failed for: r4v0, types: [com.google.android.gms.internal.firebase-auth-api.zzbc<B>, com.google.android.gms.internal.firebase-auth-api.zzbc] */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static <B, P> P zzl(com.google.android.gms.internal.p007firebaseauthapi.zzbc<B> r4, java.lang.Class<P> r5) throws java.security.GeneralSecurityException {
        /*
            java.util.concurrent.ConcurrentMap<java.lang.Class<?>, com.google.android.gms.internal.firebase-auth-api.zzbd<?, ?>> r0 = zzf
            java.lang.Object r5 = r0.get(r5)
            com.google.android.gms.internal.firebase-auth-api.zzbd r5 = (com.google.android.gms.internal.p007firebaseauthapi.zzbd) r5
            if (r5 != 0) goto L_0x002e
            java.security.GeneralSecurityException r5 = new java.security.GeneralSecurityException
            java.lang.Class r4 = r4.zze()
            java.lang.String r4 = r4.getName()
            java.lang.String r4 = java.lang.String.valueOf(r4)
            java.lang.String r0 = "No wrapper found for "
            int r1 = r4.length()
            if (r1 == 0) goto L_0x0025
            java.lang.String r4 = r0.concat(r4)
            goto L_0x002a
        L_0x0025:
            java.lang.String r4 = new java.lang.String
            r4.<init>(r0)
        L_0x002a:
            r5.<init>(r4)
            throw r5
        L_0x002e:
            java.lang.Class r0 = r5.zzc()
            java.lang.Class r1 = r4.zze()
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x0041
            java.lang.Object r4 = r5.zza(r4)
            return r4
        L_0x0041:
            java.security.GeneralSecurityException r0 = new java.security.GeneralSecurityException
            java.lang.Class r5 = r5.zzc()
            java.lang.String r5 = java.lang.String.valueOf(r5)
            java.lang.Class r4 = r4.zze()
            java.lang.String r4 = java.lang.String.valueOf(r4)
            java.lang.String r1 = java.lang.String.valueOf(r5)
            int r1 = r1.length()
            java.lang.String r2 = java.lang.String.valueOf(r4)
            int r2 = r2.length()
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            int r1 = r1 + 44
            int r1 = r1 + r2
            r3.<init>(r1)
            java.lang.String r1 = "Wrong input primitive class, expected "
            r3.append(r1)
            r3.append(r5)
            java.lang.String r5 = ", got "
            r3.append(r5)
            r3.append(r4)
            java.lang.String r4 = r3.toString()
            r0.<init>(r4)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.p007firebaseauthapi.zzbk.zzl(com.google.android.gms.internal.firebase-auth-api.zzbc, java.lang.Class):java.lang.Object");
    }

    public static Class<?> zzm(Class<?> cls) {
        zzbd zzbd = (zzbd) zzf.get(cls);
        if (zzbd == null) {
            return null;
        }
        return zzbd.zzc();
    }

    private static synchronized zzbj zzn(String str) throws GeneralSecurityException {
        zzbj zzbj;
        synchronized (zzbk.class) {
            ConcurrentMap<String, zzbj> concurrentMap = zzb;
            if (!concurrentMap.containsKey(str)) {
                String valueOf = String.valueOf(str);
                throw new GeneralSecurityException(valueOf.length() != 0 ? "No key manager found for key type ".concat(valueOf) : new String("No key manager found for key type "));
            }
            zzbj = (zzbj) concurrentMap.get(str);
        }
        return zzbj;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:26:0x0088, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static synchronized void zzo(java.lang.String r6, java.lang.Class<?> r7, boolean r8) throws java.security.GeneralSecurityException {
        /*
            java.lang.Class<com.google.android.gms.internal.firebase-auth-api.zzbk> r0 = com.google.android.gms.internal.p007firebaseauthapi.zzbk.class
            monitor-enter(r0)
            java.util.concurrent.ConcurrentMap<java.lang.String, com.google.android.gms.internal.firebase-auth-api.zzbj> r1 = zzb     // Catch:{ all -> 0x0089 }
            boolean r2 = r1.containsKey(r6)     // Catch:{ all -> 0x0089 }
            if (r2 != 0) goto L_0x000d
            monitor-exit(r0)
            return
        L_0x000d:
            java.lang.Object r1 = r1.get(r6)     // Catch:{ all -> 0x0089 }
            com.google.android.gms.internal.firebase-auth-api.zzbj r1 = (com.google.android.gms.internal.p007firebaseauthapi.zzbj) r1     // Catch:{ all -> 0x0089 }
            java.lang.Class r2 = r1.zzc()     // Catch:{ all -> 0x0089 }
            boolean r2 = r2.equals(r7)     // Catch:{ all -> 0x0089 }
            if (r2 != 0) goto L_0x005f
            java.util.logging.Logger r8 = zza     // Catch:{ all -> 0x0089 }
            java.util.logging.Level r2 = java.util.logging.Level.WARNING     // Catch:{ all -> 0x0089 }
            java.lang.String r3 = "Attempted overwrite of a registered key manager for key type "
            int r4 = r6.length()     // Catch:{ all -> 0x0089 }
            if (r4 == 0) goto L_0x002e
            java.lang.String r3 = r3.concat(r6)     // Catch:{ all -> 0x0089 }
            goto L_0x0034
        L_0x002e:
            java.lang.String r4 = new java.lang.String     // Catch:{ all -> 0x0089 }
            r4.<init>(r3)     // Catch:{ all -> 0x0089 }
            r3 = r4
        L_0x0034:
            java.lang.String r4 = "com.google.crypto.tink.Registry"
            java.lang.String r5 = "ensureKeyManagerInsertable"
            r8.logp(r2, r4, r5, r3)     // Catch:{ all -> 0x0089 }
            java.security.GeneralSecurityException r8 = new java.security.GeneralSecurityException     // Catch:{ all -> 0x0089 }
            r2 = 3
            java.lang.Object[] r2 = new java.lang.Object[r2]     // Catch:{ all -> 0x0089 }
            r3 = 0
            r2[r3] = r6     // Catch:{ all -> 0x0089 }
            r6 = 1
            java.lang.Class r1 = r1.zzc()     // Catch:{ all -> 0x0089 }
            java.lang.String r1 = r1.getName()     // Catch:{ all -> 0x0089 }
            r2[r6] = r1     // Catch:{ all -> 0x0089 }
            r6 = 2
            java.lang.String r7 = r7.getName()     // Catch:{ all -> 0x0089 }
            r2[r6] = r7     // Catch:{ all -> 0x0089 }
            java.lang.String r6 = "typeUrl (%s) is already registered with %s, cannot be re-registered with %s"
            java.lang.String r6 = java.lang.String.format(r6, r2)     // Catch:{ all -> 0x0089 }
            r8.<init>(r6)     // Catch:{ all -> 0x0089 }
            throw r8     // Catch:{ all -> 0x0089 }
        L_0x005f:
            if (r8 == 0) goto L_0x0087
            java.util.concurrent.ConcurrentMap<java.lang.String, java.lang.Boolean> r7 = zzd     // Catch:{ all -> 0x0089 }
            java.lang.Object r7 = r7.get(r6)     // Catch:{ all -> 0x0089 }
            java.lang.Boolean r7 = (java.lang.Boolean) r7     // Catch:{ all -> 0x0089 }
            boolean r7 = r7.booleanValue()     // Catch:{ all -> 0x0089 }
            if (r7 != 0) goto L_0x0087
            java.security.GeneralSecurityException r7 = new java.security.GeneralSecurityException     // Catch:{ all -> 0x0089 }
            java.lang.String r8 = "New keys are already disallowed for key type "
            int r1 = r6.length()     // Catch:{ all -> 0x0089 }
            if (r1 == 0) goto L_0x007e
            java.lang.String r6 = r8.concat(r6)     // Catch:{ all -> 0x0089 }
            goto L_0x0083
        L_0x007e:
            java.lang.String r6 = new java.lang.String     // Catch:{ all -> 0x0089 }
            r6.<init>(r8)     // Catch:{ all -> 0x0089 }
        L_0x0083:
            r7.<init>(r6)     // Catch:{ all -> 0x0089 }
            throw r7     // Catch:{ all -> 0x0089 }
        L_0x0087:
            monitor-exit(r0)
            return
        L_0x0089:
            r6 = move-exception
            monitor-exit(r0)
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.p007firebaseauthapi.zzbk.zzo(java.lang.String, java.lang.Class, boolean):void");
    }

    private static <P> zzam<P> zzp(String str, Class<P> cls) throws GeneralSecurityException {
        zzbj zzn = zzn(str);
        if (cls == null) {
            return zzn.zzb();
        }
        if (zzn.zzd().contains(cls)) {
            return zzn.zza(cls);
        }
        String name = cls.getName();
        String valueOf = String.valueOf(zzn.zzc());
        Set<Class<?>> zzd2 = zzn.zzd();
        StringBuilder sb = new StringBuilder();
        boolean z = true;
        for (Class next : zzd2) {
            if (!z) {
                sb.append(", ");
            }
            sb.append(next.getCanonicalName());
            z = false;
        }
        String sb2 = sb.toString();
        int length = String.valueOf(name).length();
        StringBuilder sb3 = new StringBuilder(length + 77 + String.valueOf(valueOf).length() + String.valueOf(sb2).length());
        sb3.append("Primitive type ");
        sb3.append(name);
        sb3.append(" not supported by key manager of type ");
        sb3.append(valueOf);
        sb3.append(", supported primitives: ");
        sb3.append(sb2);
        throw new GeneralSecurityException(sb3.toString());
    }

    private static <P> P zzq(String str, zzym zzym, Class<P> cls) throws GeneralSecurityException {
        return zzp(str, cls).zza(zzym);
    }
}

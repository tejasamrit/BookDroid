package com.google.android.gms.internal.p007firebaseauthapi;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzze */
/* compiled from: com.google.firebase:firebase-auth@@20.0.1 */
final class zzze {
    private static final zzzc<?> zza = new zzzd();
    private static final zzzc<?> zzb;

    static {
        zzzc<?> zzzc;
        try {
            zzzc = (zzzc) Class.forName("com.google.protobuf.ExtensionSchemaFull").getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
        } catch (Exception unused) {
            zzzc = null;
        }
        zzb = zzzc;
    }

    static zzzc<?> zza() {
        return zza;
    }

    static zzzc<?> zzb() {
        zzzc<?> zzzc = zzb;
        if (zzzc != null) {
            return zzzc;
        }
        throw new IllegalStateException("Protobuf runtime is not correctly loaded.");
    }
}

package com.google.android.gms.internal.p007firebaseauthapi;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzaai */
/* compiled from: com.google.firebase:firebase-auth@@20.0.1 */
final class zzaai implements zzaap {
    private final zzaap[] zza;

    zzaai(zzaap... zzaapArr) {
        this.zza = zzaapArr;
    }

    public final boolean zzb(Class<?> cls) {
        zzaap[] zzaapArr = this.zza;
        for (int i = 0; i < 2; i++) {
            if (zzaapArr[i].zzb(cls)) {
                return true;
            }
        }
        return false;
    }

    public final zzaao zzc(Class<?> cls) {
        zzaap[] zzaapArr = this.zza;
        for (int i = 0; i < 2; i++) {
            zzaap zzaap = zzaapArr[i];
            if (zzaap.zzb(cls)) {
                return zzaap.zzc(cls);
            }
        }
        String valueOf = String.valueOf(cls.getName());
        throw new UnsupportedOperationException(valueOf.length() != 0 ? "No factory is available for message type: ".concat(valueOf) : new String("No factory is available for message type: "));
    }
}

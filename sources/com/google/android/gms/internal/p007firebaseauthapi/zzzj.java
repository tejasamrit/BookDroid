package com.google.android.gms.internal.p007firebaseauthapi;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzzj */
/* compiled from: com.google.firebase:firebase-auth@@20.0.1 */
final class zzzj implements zzaap {
    private static final zzzj zza = new zzzj();

    private zzzj() {
    }

    public static zzzj zza() {
        return zza;
    }

    public final boolean zzb(Class<?> cls) {
        return zzzo.class.isAssignableFrom(cls);
    }

    /* JADX WARNING: type inference failed for: r5v0, types: [java.lang.Class<?>, java.lang.Class] */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.google.android.gms.internal.p007firebaseauthapi.zzaao zzc(java.lang.Class<?> r5) {
        /*
            r4 = this;
            java.lang.Class<com.google.android.gms.internal.firebase-auth-api.zzzo> r0 = com.google.android.gms.internal.p007firebaseauthapi.zzzo.class
            boolean r0 = r0.isAssignableFrom(r5)
            if (r0 != 0) goto L_0x0028
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.String r5 = r5.getName()
            java.lang.String r5 = java.lang.String.valueOf(r5)
            java.lang.String r1 = "Unsupported message type: "
            int r2 = r5.length()
            if (r2 == 0) goto L_0x001f
            java.lang.String r5 = r1.concat(r5)
            goto L_0x0024
        L_0x001f:
            java.lang.String r5 = new java.lang.String
            r5.<init>(r1)
        L_0x0024:
            r0.<init>(r5)
            throw r0
        L_0x0028:
            java.lang.Class<com.google.android.gms.internal.firebase-auth-api.zzzo> r0 = com.google.android.gms.internal.p007firebaseauthapi.zzzo.class
            java.lang.Class r0 = r5.asSubclass(r0)     // Catch:{ Exception -> 0x003b }
            com.google.android.gms.internal.firebase-auth-api.zzzo r0 = com.google.android.gms.internal.p007firebaseauthapi.zzzo.zzx(r0)     // Catch:{ Exception -> 0x003b }
            r1 = 3
            r2 = 0
            java.lang.Object r0 = r0.zzj(r1, r2, r2)     // Catch:{ Exception -> 0x003b }
            com.google.android.gms.internal.firebase-auth-api.zzaao r0 = (com.google.android.gms.internal.p007firebaseauthapi.zzaao) r0     // Catch:{ Exception -> 0x003b }
            return r0
        L_0x003b:
            r0 = move-exception
            java.lang.RuntimeException r1 = new java.lang.RuntimeException
            java.lang.String r5 = r5.getName()
            java.lang.String r5 = java.lang.String.valueOf(r5)
            java.lang.String r2 = "Unable to get message info for "
            int r3 = r5.length()
            if (r3 == 0) goto L_0x0053
            java.lang.String r5 = r2.concat(r5)
            goto L_0x0058
        L_0x0053:
            java.lang.String r5 = new java.lang.String
            r5.<init>(r2)
        L_0x0058:
            r1.<init>(r5, r0)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.p007firebaseauthapi.zzzj.zzc(java.lang.Class):com.google.android.gms.internal.firebase-auth-api.zzaao");
    }
}

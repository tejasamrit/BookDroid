package com.google.android.gms.internal.p007firebaseauthapi;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzzz */
/* compiled from: com.google.firebase:firebase-auth@@20.0.1 */
public class zzzz {
    private static final zzzb zzb = zzzb.zza();
    protected volatile zzaar zza;
    private volatile zzym zzc;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzzz)) {
            return false;
        }
        zzzz zzzz = (zzzz) obj;
        zzaar zzaar = this.zza;
        zzaar zzaar2 = zzzz.zza;
        if (zzaar == null && zzaar2 == null) {
            return zzb().equals(zzzz.zzb());
        }
        if (zzaar != null && zzaar2 != null) {
            return zzaar.equals(zzaar2);
        }
        if (zzaar != null) {
            zzzz.zzc(zzaar.zzo());
            return zzaar.equals(zzzz.zza);
        }
        zzc(zzaar2.zzo());
        return this.zza.equals(zzaar2);
    }

    public int hashCode() {
        return 1;
    }

    public final int zza() {
        if (this.zzc != null) {
            return ((zzyk) this.zzc).zza.length;
        }
        if (this.zza != null) {
            return this.zza.zzw();
        }
        return 0;
    }

    public final zzym zzb() {
        if (this.zzc != null) {
            return this.zzc;
        }
        synchronized (this) {
            if (this.zzc != null) {
                zzym zzym = this.zzc;
                return zzym;
            }
            if (this.zza == null) {
                this.zzc = zzym.zzb;
            } else {
                this.zzc = this.zza.zzn();
            }
            zzym zzym2 = this.zzc;
            return zzym2;
        }
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Can't wrap try/catch for region: R(4:7|8|9|10) */
    /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0011 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zzc(com.google.android.gms.internal.p007firebaseauthapi.zzaar r2) {
        /*
            r1 = this;
            com.google.android.gms.internal.firebase-auth-api.zzaar r0 = r1.zza
            if (r0 == 0) goto L_0x0005
            return
        L_0x0005:
            monitor-enter(r1)
            com.google.android.gms.internal.firebase-auth-api.zzaar r0 = r1.zza     // Catch:{ all -> 0x001b }
            if (r0 != 0) goto L_0x0019
            r1.zza = r2     // Catch:{ zzzw -> 0x0011 }
            com.google.android.gms.internal.firebase-auth-api.zzym r0 = com.google.android.gms.internal.p007firebaseauthapi.zzym.zzb     // Catch:{ zzzw -> 0x0011 }
            r1.zzc = r0     // Catch:{ zzzw -> 0x0011 }
            goto L_0x0017
        L_0x0011:
            r1.zza = r2     // Catch:{ all -> 0x001b }
            com.google.android.gms.internal.firebase-auth-api.zzym r2 = com.google.android.gms.internal.p007firebaseauthapi.zzym.zzb     // Catch:{ all -> 0x001b }
            r1.zzc = r2     // Catch:{ all -> 0x001b }
        L_0x0017:
            monitor-exit(r1)     // Catch:{ all -> 0x001b }
            return
        L_0x0019:
            monitor-exit(r1)     // Catch:{ all -> 0x001b }
            return
        L_0x001b:
            r2 = move-exception
            monitor-exit(r1)     // Catch:{ all -> 0x001b }
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.p007firebaseauthapi.zzzz.zzc(com.google.android.gms.internal.firebase-auth-api.zzaar):void");
    }
}

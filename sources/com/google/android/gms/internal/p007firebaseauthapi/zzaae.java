package com.google.android.gms.internal.p007firebaseauthapi;

import java.util.List;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzaae */
/* compiled from: com.google.firebase:firebase-auth@@20.0.1 */
final class zzaae extends zzaaf {
    private zzaae() {
        super((zzaac) null);
    }

    /* synthetic */ zzaae(zzaac zzaac) {
        super((zzaac) null);
    }

    /* access modifiers changed from: package-private */
    public final <L> List<L> zza(Object obj, long j) {
        zzzt zzzt = (zzzt) zzacc.zzn(obj, j);
        if (zzzt.zza()) {
            return zzzt;
        }
        int size = zzzt.size();
        zzzt zze = zzzt.zze(size == 0 ? 10 : size + size);
        zzacc.zzo(obj, j, zze);
        return zze;
    }

    /* access modifiers changed from: package-private */
    public final void zzb(Object obj, long j) {
        ((zzzt) zzacc.zzn(obj, j)).zzb();
    }

    /* access modifiers changed from: package-private */
    public final <E> void zzc(Object obj, Object obj2, long j) {
        zzzt zzzt = (zzzt) zzacc.zzn(obj, j);
        zzzt zzzt2 = (zzzt) zzacc.zzn(obj2, j);
        int size = zzzt.size();
        int size2 = zzzt2.size();
        if (size > 0 && size2 > 0) {
            if (!zzzt.zza()) {
                zzzt = zzzt.zze(size2 + size);
            }
            zzzt.addAll(zzzt2);
        }
        if (size > 0) {
            zzzt2 = zzzt;
        }
        zzacc.zzo(obj, j, zzzt2);
    }
}

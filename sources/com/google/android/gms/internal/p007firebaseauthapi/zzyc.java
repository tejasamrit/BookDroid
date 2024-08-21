package com.google.android.gms.internal.p007firebaseauthapi;

import java.util.Comparator;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzyc */
/* compiled from: com.google.firebase:firebase-auth@@20.0.1 */
final class zzyc implements Comparator<zzym> {
    zzyc() {
    }

    public final /* bridge */ /* synthetic */ int compare(Object obj, Object obj2) {
        zzym zzym = (zzym) obj;
        zzym zzym2 = (zzym) obj2;
        zzyb zzyb = new zzyb(zzym);
        zzyb zzyb2 = new zzyb(zzym2);
        while (zzyb.hasNext() && zzyb2.hasNext()) {
            int compare = Integer.compare(zzyb.zza() & 255, zzyb2.zza() & 255);
            if (compare != 0) {
                return compare;
            }
        }
        return Integer.compare(zzym.zzc(), zzym2.zzc());
    }
}

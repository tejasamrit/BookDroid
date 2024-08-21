package com.google.android.gms.internal.p007firebaseauthapi;

import java.util.NoSuchElementException;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzyb */
/* compiled from: com.google.firebase:firebase-auth@@20.0.1 */
final class zzyb extends zzyd {
    final /* synthetic */ zzym zza;
    private int zzb = 0;
    private final int zzc;

    zzyb(zzym zzym) {
        this.zza = zzym;
        this.zzc = zzym.zzc();
    }

    public final boolean hasNext() {
        return this.zzb < this.zzc;
    }

    public final byte zza() {
        int i = this.zzb;
        if (i < this.zzc) {
            this.zzb = i + 1;
            return this.zza.zzb(i);
        }
        throw new NoSuchElementException();
    }
}

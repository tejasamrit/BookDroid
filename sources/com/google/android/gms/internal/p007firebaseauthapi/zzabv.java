package com.google.android.gms.internal.p007firebaseauthapi;

import java.util.Iterator;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzabv */
/* compiled from: com.google.firebase:firebase-auth@@20.0.1 */
final class zzabv implements Iterator<String> {
    final Iterator<String> zza;
    final /* synthetic */ zzabw zzb;

    zzabv(zzabw zzabw) {
        this.zzb = zzabw;
        this.zza = zzabw.zza.iterator();
    }

    public final boolean hasNext() {
        return this.zza.hasNext();
    }

    public final /* bridge */ /* synthetic */ Object next() {
        return this.zza.next();
    }

    public final void remove() {
        throw new UnsupportedOperationException();
    }
}

package com.google.android.gms.internal.p007firebaseauthapi;

import java.util.Iterator;
import java.util.Map;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzabl */
/* compiled from: com.google.firebase:firebase-auth@@20.0.1 */
final class zzabl implements Iterator<Map.Entry> {
    final /* synthetic */ zzabn zza;
    private int zzb = -1;
    private boolean zzc;
    private Iterator<Map.Entry> zzd;

    /* synthetic */ zzabl(zzabn zzabn, zzabg zzabg) {
        this.zza = zzabn;
    }

    private final Iterator<Map.Entry> zza() {
        if (this.zzd == null) {
            this.zzd = this.zza.zzc.entrySet().iterator();
        }
        return this.zzd;
    }

    public final boolean hasNext() {
        if (this.zzb + 1 >= this.zza.zzb.size()) {
            return !this.zza.zzc.isEmpty() && zza().hasNext();
        }
        return true;
    }

    public final /* bridge */ /* synthetic */ Object next() {
        this.zzc = true;
        int i = this.zzb + 1;
        this.zzb = i;
        return (Map.Entry) (i < this.zza.zzb.size() ? this.zza.zzb.get(this.zzb) : zza().next());
    }

    public final void remove() {
        if (this.zzc) {
            this.zzc = false;
            this.zza.zzm();
            if (this.zzb < this.zza.zzb.size()) {
                zzabn zzabn = this.zza;
                int i = this.zzb;
                this.zzb = i - 1;
                Object unused = zzabn.zzk(i);
                return;
            }
            zza().remove();
            return;
        }
        throw new IllegalStateException("remove() was called before next()");
    }
}

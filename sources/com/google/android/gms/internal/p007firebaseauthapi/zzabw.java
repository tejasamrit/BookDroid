package com.google.android.gms.internal.p007firebaseauthapi;

import java.util.AbstractList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.RandomAccess;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzabw */
/* compiled from: com.google.firebase:firebase-auth@@20.0.1 */
public final class zzabw extends AbstractList<String> implements RandomAccess, zzaab {
    /* access modifiers changed from: private */
    public final zzaab zza;

    public zzabw(zzaab zzaab) {
        this.zza = zzaab;
    }

    public final /* bridge */ /* synthetic */ Object get(int i) {
        return ((zzaaa) this.zza).get(i);
    }

    public final Iterator<String> iterator() {
        return new zzabv(this);
    }

    public final ListIterator<String> listIterator(int i) {
        return new zzabu(this, i);
    }

    public final int size() {
        return this.zza.size();
    }

    public final void zzf(zzym zzym) {
        throw new UnsupportedOperationException();
    }

    public final Object zzg(int i) {
        return this.zza.zzg(i);
    }

    public final List<?> zzh() {
        return this.zza.zzh();
    }

    public final zzaab zzi() {
        return this;
    }
}

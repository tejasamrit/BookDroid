package com.google.android.gms.internal.p007firebaseauthapi;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.RandomAccess;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzaaa */
/* compiled from: com.google.firebase:firebase-auth@@20.0.1 */
public final class zzaaa extends zzxv<String> implements RandomAccess, zzaab {
    public static final zzaab zza;
    private static final zzaaa zzb;
    private final List<Object> zzc;

    static {
        zzaaa zzaaa = new zzaaa(10);
        zzb = zzaaa;
        zzaaa.zzb();
        zza = zzaaa;
    }

    public zzaaa() {
        this(10);
    }

    private static String zzj(Object obj) {
        if (obj instanceof String) {
            return (String) obj;
        }
        if (obj instanceof zzym) {
            return ((zzym) obj).zzq(zzzu.zza);
        }
        return zzzu.zzd((byte[]) obj);
    }

    public final /* bridge */ /* synthetic */ void add(int i, Object obj) {
        zzc();
        this.zzc.add(i, (String) obj);
        this.modCount++;
    }

    public final boolean addAll(int i, Collection<? extends String> collection) {
        zzc();
        if (collection instanceof zzaab) {
            collection = ((zzaab) collection).zzh();
        }
        boolean addAll = this.zzc.addAll(i, collection);
        this.modCount++;
        return addAll;
    }

    public final void clear() {
        zzc();
        this.zzc.clear();
        this.modCount++;
    }

    public final /* bridge */ /* synthetic */ Object remove(int i) {
        zzc();
        Object remove = this.zzc.remove(i);
        this.modCount++;
        return zzj(remove);
    }

    public final /* bridge */ /* synthetic */ Object set(int i, Object obj) {
        zzc();
        return zzj(this.zzc.set(i, (String) obj));
    }

    public final int size() {
        return this.zzc.size();
    }

    /* renamed from: zzd */
    public final String get(int i) {
        Object obj = this.zzc.get(i);
        if (obj instanceof String) {
            return (String) obj;
        }
        if (obj instanceof zzym) {
            zzym zzym = (zzym) obj;
            String zzq = zzym.zzq(zzzu.zza);
            if (zzym.zzi()) {
                this.zzc.set(i, zzq);
            }
            return zzq;
        }
        byte[] bArr = (byte[]) obj;
        String zzd = zzzu.zzd(bArr);
        if (zzzu.zzc(bArr)) {
            this.zzc.set(i, zzd);
        }
        return zzd;
    }

    public final /* bridge */ /* synthetic */ zzzt zze(int i) {
        if (i >= size()) {
            ArrayList arrayList = new ArrayList(i);
            arrayList.addAll(this.zzc);
            return new zzaaa((ArrayList<Object>) arrayList);
        }
        throw new IllegalArgumentException();
    }

    public final void zzf(zzym zzym) {
        zzc();
        this.zzc.add(zzym);
        this.modCount++;
    }

    public final Object zzg(int i) {
        return this.zzc.get(i);
    }

    public final List<?> zzh() {
        return Collections.unmodifiableList(this.zzc);
    }

    public final zzaab zzi() {
        return zza() ? new zzabw(this) : this;
    }

    public zzaaa(int i) {
        this.zzc = new ArrayList(i);
    }

    private zzaaa(ArrayList<Object> arrayList) {
        this.zzc = arrayList;
    }

    public final boolean addAll(Collection<? extends String> collection) {
        return addAll(size(), collection);
    }
}

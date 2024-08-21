package com.google.android.gms.internal.p007firebaseauthapi;

import com.google.android.gms.internal.p007firebaseauthapi.zzzf;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzzg */
/* compiled from: com.google.firebase:firebase-auth@@20.0.1 */
final class zzzg<T extends zzzf<T>> {
    private static final zzzg zzd = new zzzg(true);
    final zzabn<T, Object> zza = new zzabg(16);
    private boolean zzb;
    private boolean zzc;

    private zzzg() {
    }

    public static <T extends zzzf<T>> zzzg<T> zza() {
        throw null;
    }

    private static final void zzd(zzacj zzacj, Object obj) {
        boolean z;
        zzzu.zza(obj);
        zzacj zzacj2 = zzacj.DOUBLE;
        zzack zzack = zzack.INT;
        switch (zzacj.zza().ordinal()) {
            case 0:
                z = obj instanceof Integer;
                break;
            case 1:
                z = obj instanceof Long;
                break;
            case 2:
                z = obj instanceof Float;
                break;
            case 3:
                z = obj instanceof Double;
                break;
            case 4:
                z = obj instanceof Boolean;
                break;
            case 5:
                z = obj instanceof String;
                break;
            case 6:
                if ((obj instanceof zzym) || (obj instanceof byte[])) {
                    return;
                }
            case 7:
                if ((obj instanceof Integer) || (obj instanceof zzzq)) {
                    return;
                }
            case 8:
                if ((obj instanceof zzaar) || (obj instanceof zzzy)) {
                    return;
                }
        }
        if (z) {
            return;
        }
        throw new IllegalArgumentException("Wrong object type used with protocol message reflection.");
    }

    public final /* bridge */ /* synthetic */ Object clone() throws CloneNotSupportedException {
        zzzg zzzg = new zzzg();
        for (int i = 0; i < this.zza.zzc(); i++) {
            Map.Entry<T, Object> zzd2 = this.zza.zzd(i);
            zzzg.zzc((zzzf) zzd2.getKey(), zzd2.getValue());
        }
        for (Map.Entry next : this.zza.zze()) {
            zzzg.zzc((zzzf) next.getKey(), next.getValue());
        }
        zzzg.zzc = this.zzc;
        return zzzg;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzzg)) {
            return false;
        }
        return this.zza.equals(((zzzg) obj).zza);
    }

    public final int hashCode() {
        return this.zza.hashCode();
    }

    public final void zzb() {
        if (!this.zzb) {
            this.zza.zza();
            this.zzb = true;
        }
    }

    public final void zzc(T t, Object obj) {
        if (!t.zzb()) {
            zzd(t.zza(), obj);
        } else if (obj instanceof List) {
            ArrayList arrayList = new ArrayList();
            arrayList.addAll((List) obj);
            int size = arrayList.size();
            for (int i = 0; i < size; i++) {
                zzd(t.zza(), arrayList.get(i));
            }
            obj = arrayList;
        } else {
            throw new IllegalArgumentException("Wrong object type used with protocol message reflection.");
        }
        if (obj instanceof zzzy) {
            this.zzc = true;
        }
        this.zza.put(t, obj);
    }

    private zzzg(boolean z) {
        zzb();
        zzb();
    }
}

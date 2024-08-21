package com.google.android.gms.internal.p007firebaseauthapi;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzaal */
/* compiled from: com.google.firebase:firebase-auth@@20.0.1 */
public final class zzaal<K, V> extends LinkedHashMap<K, V> {
    private static final zzaal zzb;
    private boolean zza = true;

    static {
        zzaal zzaal = new zzaal();
        zzb = zzaal;
        zzaal.zza = false;
    }

    private zzaal() {
    }

    public static <K, V> zzaal<K, V> zza() {
        return zzb;
    }

    private static int zzf(Object obj) {
        if (obj instanceof byte[]) {
            return zzzu.zzg((byte[]) obj);
        }
        if (!(obj instanceof zzzq)) {
            return obj.hashCode();
        }
        throw new UnsupportedOperationException();
    }

    private final void zzg() {
        if (!this.zza) {
            throw new UnsupportedOperationException();
        }
    }

    public final void clear() {
        zzg();
        super.clear();
    }

    public final Set<Map.Entry<K, V>> entrySet() {
        return isEmpty() ? Collections.emptySet() : super.entrySet();
    }

    public final boolean equals(Object obj) {
        boolean z;
        if (!(obj instanceof Map)) {
            return false;
        }
        Map map = (Map) obj;
        if (this == map) {
            return true;
        }
        if (size() != map.size()) {
            return false;
        }
        for (Map.Entry entry : entrySet()) {
            if (!map.containsKey(entry.getKey())) {
                return false;
            }
            Object value = entry.getValue();
            Object obj2 = map.get(entry.getKey());
            if (!(value instanceof byte[]) || !(obj2 instanceof byte[])) {
                z = value.equals(obj2);
                continue;
            } else {
                z = Arrays.equals((byte[]) value, (byte[]) obj2);
                continue;
            }
            if (!z) {
                return false;
            }
        }
        return true;
    }

    public final int hashCode() {
        int i = 0;
        for (Map.Entry entry : entrySet()) {
            i += zzf(entry.getValue()) ^ zzf(entry.getKey());
        }
        return i;
    }

    public final V put(K k, V v) {
        zzg();
        zzzu.zza(k);
        zzzu.zza(v);
        return super.put(k, v);
    }

    public final void putAll(Map<? extends K, ? extends V> map) {
        zzg();
        for (Object next : map.keySet()) {
            zzzu.zza(next);
            zzzu.zza(map.get(next));
        }
        super.putAll(map);
    }

    public final V remove(Object obj) {
        zzg();
        return super.remove(obj);
    }

    public final void zzb(zzaal<K, V> zzaal) {
        zzg();
        if (!zzaal.isEmpty()) {
            putAll(zzaal);
        }
    }

    public final zzaal<K, V> zzc() {
        return isEmpty() ? new zzaal<>() : new zzaal<>(this);
    }

    public final void zzd() {
        this.zza = false;
    }

    public final boolean zze() {
        return this.zza;
    }

    private zzaal(Map<K, V> map) {
        super(map);
    }
}

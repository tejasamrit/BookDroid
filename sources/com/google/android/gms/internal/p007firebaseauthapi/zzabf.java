package com.google.android.gms.internal.p007firebaseauthapi;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.RandomAccess;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzabf */
/* compiled from: com.google.firebase:firebase-auth@@20.0.1 */
final class zzabf {
    private static final Class<?> zza;
    private static final zzabr<?, ?> zzb = zzab(false);
    private static final zzabr<?, ?> zzc = zzab(true);
    private static final zzabr<?, ?> zzd = new zzabt();

    static {
        Class<?> cls;
        try {
            cls = Class.forName("com.google.protobuf.GeneratedMessage");
        } catch (Throwable unused) {
            cls = null;
        }
        zza = cls;
    }

    public static zzabr<?, ?> zzA() {
        return zzb;
    }

    public static zzabr<?, ?> zzB() {
        return zzc;
    }

    public static zzabr<?, ?> zzC() {
        return zzd;
    }

    static boolean zzD(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    static <T, FT extends zzzf<FT>> void zzE(zzzc<FT> zzzc, T t, T t2) {
        zzzc.zzb(t2);
        throw null;
    }

    static <T, UT, UB> void zzF(zzabr<UT, UB> zzabr, T t, T t2) {
        zzabr.zzi(t, zzabr.zzo(zzabr.zzj(t), zzabr.zzj(t2)));
    }

    static <UT, UB> UB zzG(int i, List<Integer> list, zzzs zzzs, UB ub, zzabr<UT, UB> zzabr) {
        if (zzzs == null) {
            return ub;
        }
        if (list instanceof RandomAccess) {
            int size = list.size();
            int i2 = 0;
            for (int i3 = 0; i3 < size; i3++) {
                int intValue = list.get(i3).intValue();
                if (zzzs.zza()) {
                    if (i3 != i2) {
                        list.set(i2, Integer.valueOf(intValue));
                    }
                    i2++;
                } else {
                    ub = zzH(i, intValue, ub, zzabr);
                }
            }
            if (i2 != size) {
                list.subList(i2, size).clear();
                return ub;
            }
        } else {
            Iterator<Integer> it = list.iterator();
            while (it.hasNext()) {
                int intValue2 = it.next().intValue();
                if (!zzzs.zza()) {
                    ub = zzH(i, intValue2, ub, zzabr);
                    it.remove();
                }
            }
        }
        return ub;
    }

    static <UT, UB> UB zzH(int i, int i2, UB ub, zzabr<UT, UB> zzabr) {
        if (ub == null) {
            ub = zzabr.zzg();
        }
        zzabr.zzb(ub, i, (long) i2);
        return ub;
    }

    static <T> void zzI(zzaam zzaam, T t, T t2, long j) {
        zzacc.zzo(t, j, zzaam.zzc(zzacc.zzn(t, j), zzacc.zzn(t2, j)));
    }

    public static void zzJ(int i, List<Double> list, zzyx zzyx, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzyx.zzB(i, list, z);
        }
    }

    public static void zzK(int i, List<Float> list, zzyx zzyx, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzyx.zzA(i, list, z);
        }
    }

    public static void zzL(int i, List<Long> list, zzyx zzyx, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzyx.zzx(i, list, z);
        }
    }

    public static void zzM(int i, List<Long> list, zzyx zzyx, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzyx.zzy(i, list, z);
        }
    }

    public static void zzN(int i, List<Long> list, zzyx zzyx, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzyx.zzK(i, list, z);
        }
    }

    public static void zzO(int i, List<Long> list, zzyx zzyx, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzyx.zzz(i, list, z);
        }
    }

    public static void zzP(int i, List<Long> list, zzyx zzyx, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzyx.zzI(i, list, z);
        }
    }

    public static void zzQ(int i, List<Integer> list, zzyx zzyx, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzyx.zzv(i, list, z);
        }
    }

    public static void zzR(int i, List<Integer> list, zzyx zzyx, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzyx.zzG(i, list, z);
        }
    }

    public static void zzS(int i, List<Integer> list, zzyx zzyx, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzyx.zzJ(i, list, z);
        }
    }

    public static void zzT(int i, List<Integer> list, zzyx zzyx, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzyx.zzw(i, list, z);
        }
    }

    public static void zzU(int i, List<Integer> list, zzyx zzyx, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzyx.zzH(i, list, z);
        }
    }

    public static void zzV(int i, List<Integer> list, zzyx zzyx, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzyx.zzC(i, list, z);
        }
    }

    public static void zzW(int i, List<Boolean> list, zzyx zzyx, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzyx.zzD(i, list, z);
        }
    }

    public static void zzX(int i, List<String> list, zzyx zzyx) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzyx.zzE(i, list);
        }
    }

    public static void zzY(int i, List<zzym> list, zzyx zzyx) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzyx.zzF(i, list);
        }
    }

    public static void zzZ(int i, List<?> list, zzyx zzyx, zzabd zzabd) throws IOException {
        if (list != null && !list.isEmpty()) {
            for (int i2 = 0; i2 < list.size(); i2++) {
                zzyx.zzr(i, list.get(i2), zzabd);
            }
        }
    }

    public static void zza(Class<?> cls) {
        Class<?> cls2;
        if (!zzzo.class.isAssignableFrom(cls) && (cls2 = zza) != null && !cls2.isAssignableFrom(cls)) {
            throw new IllegalArgumentException("Message classes must extend GeneratedMessage or GeneratedMessageLite");
        }
    }

    public static void zzaa(int i, List<?> list, zzyx zzyx, zzabd zzabd) throws IOException {
        if (list != null && !list.isEmpty()) {
            for (int i2 = 0; i2 < list.size(); i2++) {
                zzyx.zzs(i, list.get(i2), zzabd);
            }
        }
    }

    private static zzabr<?, ?> zzab(boolean z) {
        Class<?> cls;
        try {
            cls = Class.forName("com.google.protobuf.UnknownFieldSetSchema");
        } catch (Throwable unused) {
            cls = null;
        }
        if (cls == null) {
            return null;
        }
        try {
            return (zzabr) cls.getConstructor(new Class[]{Boolean.TYPE}).newInstance(new Object[]{Boolean.valueOf(z)});
        } catch (Throwable unused2) {
            return null;
        }
    }

    static int zzb(List<Long> list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzaag) {
            zzaag zzaag = (zzaag) list;
            i = 0;
            while (i2 < size) {
                i += zzyw.zzB(zzaag.zzd(i2));
                i2++;
            }
        } else {
            int i3 = 0;
            while (i2 < size) {
                i3 = i + zzyw.zzB(list.get(i2).longValue());
                i2++;
            }
        }
        return i;
    }

    static int zzc(int i, List<Long> list, boolean z) {
        if (list.size() == 0) {
            return 0;
        }
        return zzb(list) + (list.size() * zzyw.zzy(i));
    }

    static int zzd(List<Long> list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzaag) {
            zzaag zzaag = (zzaag) list;
            i = 0;
            while (i2 < size) {
                i += zzyw.zzB(zzaag.zzd(i2));
                i2++;
            }
        } else {
            int i3 = 0;
            while (i2 < size) {
                i3 = i + zzyw.zzB(list.get(i2).longValue());
                i2++;
            }
        }
        return i;
    }

    static int zze(int i, List<Long> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return zzd(list) + (size * zzyw.zzy(i));
    }

    static int zzf(List<Long> list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzaag) {
            zzaag zzaag = (zzaag) list;
            i = 0;
            while (i2 < size) {
                i += zzyw.zzB(zzyw.zzH(zzaag.zzd(i2)));
                i2++;
            }
        } else {
            int i3 = 0;
            while (i2 < size) {
                i3 = i + zzyw.zzB(zzyw.zzH(list.get(i2).longValue()));
                i2++;
            }
        }
        return i;
    }

    static int zzg(int i, List<Long> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return zzf(list) + (size * zzyw.zzy(i));
    }

    static int zzh(List<Integer> list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzzp) {
            zzzp zzzp = (zzzp) list;
            i = 0;
            while (i2 < size) {
                i += zzyw.zzz(zzzp.zzd(i2));
                i2++;
            }
        } else {
            int i3 = 0;
            while (i2 < size) {
                i3 = i + zzyw.zzz(list.get(i2).intValue());
                i2++;
            }
        }
        return i;
    }

    static int zzi(int i, List<Integer> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return zzh(list) + (size * zzyw.zzy(i));
    }

    static int zzj(List<Integer> list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzzp) {
            zzzp zzzp = (zzzp) list;
            i = 0;
            while (i2 < size) {
                i += zzyw.zzz(zzzp.zzd(i2));
                i2++;
            }
        } else {
            int i3 = 0;
            while (i2 < size) {
                i3 = i + zzyw.zzz(list.get(i2).intValue());
                i2++;
            }
        }
        return i;
    }

    static int zzk(int i, List<Integer> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return zzj(list) + (size * zzyw.zzy(i));
    }

    static int zzl(List<Integer> list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzzp) {
            zzzp zzzp = (zzzp) list;
            i = 0;
            while (i2 < size) {
                i += zzyw.zzA(zzzp.zzd(i2));
                i2++;
            }
        } else {
            int i3 = 0;
            while (i2 < size) {
                i3 = i + zzyw.zzA(list.get(i2).intValue());
                i2++;
            }
        }
        return i;
    }

    static int zzm(int i, List<Integer> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return zzl(list) + (size * zzyw.zzy(i));
    }

    static int zzn(List<Integer> list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzzp) {
            zzzp zzzp = (zzzp) list;
            i = 0;
            while (i2 < size) {
                i += zzyw.zzA(zzyw.zzG(zzzp.zzd(i2)));
                i2++;
            }
        } else {
            int i3 = 0;
            while (i2 < size) {
                i3 = i + zzyw.zzA(zzyw.zzG(list.get(i2).intValue()));
                i2++;
            }
        }
        return i;
    }

    static int zzo(int i, List<Integer> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return zzn(list) + (size * zzyw.zzy(i));
    }

    static int zzp(List<?> list) {
        return list.size() * 4;
    }

    static int zzq(int i, List<?> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return size * (zzyw.zzy(i) + 4);
    }

    static int zzr(List<?> list) {
        return list.size() * 8;
    }

    static int zzs(int i, List<?> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return size * (zzyw.zzy(i) + 8);
    }

    static int zzt(List<?> list) {
        return list.size();
    }

    static int zzu(int i, List<?> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return size * (zzyw.zzy(i) + 1);
    }

    static int zzv(int i, List<?> list) {
        int i2;
        int i3;
        int size = list.size();
        int i4 = 0;
        if (size == 0) {
            return 0;
        }
        int zzy = zzyw.zzy(i) * size;
        if (list instanceof zzaab) {
            zzaab zzaab = (zzaab) list;
            while (i4 < size) {
                Object zzg = zzaab.zzg(i4);
                if (zzg instanceof zzym) {
                    i3 = zzyw.zzE((zzym) zzg);
                } else {
                    i3 = zzyw.zzC((String) zzg);
                }
                zzy += i3;
                i4++;
            }
        } else {
            while (i4 < size) {
                Object obj = list.get(i4);
                if (obj instanceof zzym) {
                    i2 = zzyw.zzE((zzym) obj);
                } else {
                    i2 = zzyw.zzC((String) obj);
                }
                zzy += i2;
                i4++;
            }
        }
        return zzy;
    }

    static int zzw(int i, Object obj, zzabd zzabd) {
        if (!(obj instanceof zzzz)) {
            return zzyw.zzy(i) + zzyw.zzF((zzaar) obj, zzabd);
        }
        int zzy = zzyw.zzy(i);
        int zza2 = ((zzzz) obj).zza();
        return zzy + zzyw.zzA(zza2) + zza2;
    }

    static int zzx(int i, List<?> list, zzabd zzabd) {
        int i2;
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int zzy = zzyw.zzy(i) * size;
        for (int i3 = 0; i3 < size; i3++) {
            Object obj = list.get(i3);
            if (obj instanceof zzzz) {
                i2 = zzyw.zzD((zzzz) obj);
            } else {
                i2 = zzyw.zzF((zzaar) obj, zzabd);
            }
            zzy += i2;
        }
        return zzy;
    }

    static int zzy(int i, List<zzym> list) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int zzy = size * zzyw.zzy(i);
        for (int i2 = 0; i2 < list.size(); i2++) {
            zzy += zzyw.zzE(list.get(i2));
        }
        return zzy;
    }

    static int zzz(int i, List<zzaar> list, zzabd zzabd) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int i2 = 0;
        for (int i3 = 0; i3 < size; i3++) {
            i2 += zzyw.zzK(i, list.get(i3), zzabd);
        }
        return i2;
    }
}

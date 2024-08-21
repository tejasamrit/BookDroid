package com.google.android.gms.internal.p007firebaseauthapi;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import sun.misc.Unsafe;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzaau */
/* compiled from: com.google.firebase:firebase-auth@@20.0.1 */
final class zzaau<T> implements zzabd<T> {
    private static final int[] zza = new int[0];
    private static final Unsafe zzb = zzacc.zzr();
    private final int[] zzc;
    private final Object[] zzd;
    private final int zze;
    private final int zzf;
    private final zzaar zzg;
    private final boolean zzh;
    private final boolean zzi;
    private final boolean zzj;
    private final int[] zzk;
    private final int zzl;
    private final int zzm;
    private final zzaaf zzn;
    private final zzabr<?, ?> zzo;
    private final zzzc<?> zzp;
    private final zzaaw zzq;
    private final zzaam zzr;

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v0, resolved type: com.google.android.gms.internal.firebase-auth-api.zzaar} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v1, resolved type: java.lang.Object[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v5, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v8, resolved type: int[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v11, resolved type: com.google.android.gms.internal.firebase-auth-api.zzaaw} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v1, resolved type: com.google.android.gms.internal.firebase-auth-api.zzaam} */
    /* JADX WARNING: type inference failed for: r2v0, types: [com.google.android.gms.internal.firebase-auth-api.zzzc, com.google.android.gms.internal.firebase-auth-api.zzzc<?>] */
    /* JADX WARNING: type inference failed for: r3v2, types: [int] */
    /* JADX WARNING: type inference failed for: r3v9, types: [int] */
    /* JADX WARNING: type inference failed for: r3v12, types: [com.google.android.gms.internal.firebase-auth-api.zzaaf] */
    /* JADX WARNING: type inference failed for: r3v13, types: [com.google.android.gms.internal.firebase-auth-api.zzabr<?, ?>] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private zzaau(int[] r6, int[] r7, java.lang.Object[] r8, int r9, int r10, com.google.android.gms.internal.p007firebaseauthapi.zzaar r11, boolean r12, boolean r13, int[] r14, int r15, int r16, com.google.android.gms.internal.p007firebaseauthapi.zzaaw r17, com.google.android.gms.internal.p007firebaseauthapi.zzaaf r18, com.google.android.gms.internal.p007firebaseauthapi.zzabr<?, ?> r19, com.google.android.gms.internal.p007firebaseauthapi.zzzc<?> r20, com.google.android.gms.internal.p007firebaseauthapi.zzaam r21) {
        /*
            r5 = this;
            r0 = r5
            r1 = r10
            r2 = r19
            r5.<init>()
            r3 = r6
            r0.zzc = r3
            r3 = r7
            r0.zzd = r3
            r3 = r8
            r0.zze = r3
            r3 = r9
            r0.zzf = r3
            boolean r3 = r1 instanceof com.google.android.gms.internal.p007firebaseauthapi.zzzo
            r0.zzi = r3
            r3 = r11
            r0.zzj = r3
            r3 = 0
            if (r2 == 0) goto L_0x0024
            boolean r4 = r2.zza(r10)
            if (r4 == 0) goto L_0x0024
            r3 = 1
        L_0x0024:
            r0.zzh = r3
            r3 = r13
            r0.zzk = r3
            r3 = r14
            r0.zzl = r3
            r3 = r15
            r0.zzm = r3
            r3 = r16
            r0.zzq = r3
            r3 = r17
            r0.zzn = r3
            r3 = r18
            r0.zzo = r3
            r0.zzp = r2
            r0.zzg = r1
            r1 = r20
            r0.zzr = r1
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.p007firebaseauthapi.zzaau.<init>(int[], java.lang.Object[], int, int, com.google.android.gms.internal.firebase-auth-api.zzaar, boolean, boolean, int[], int, int, com.google.android.gms.internal.firebase-auth-api.zzaaw, com.google.android.gms.internal.firebase-auth-api.zzaaf, com.google.android.gms.internal.firebase-auth-api.zzabr, com.google.android.gms.internal.firebase-auth-api.zzzc, com.google.android.gms.internal.firebase-auth-api.zzaam, byte[]):void");
    }

    private final <UT, UB> UB zzA(Object obj, int i, UB ub, zzabr<UT, UB> zzabr) {
        int i2 = this.zzc[i];
        Object zzn2 = zzacc.zzn(obj, (long) (zzD(i) & 1048575));
        if (zzn2 == null || zzy(i) == null) {
            return ub;
        }
        zzaal zzaal = (zzaal) zzn2;
        zzaak zzaak = (zzaak) zzx(i);
        throw null;
    }

    private static boolean zzB(Object obj, int i, zzabd zzabd) {
        return zzabd.zzk(zzacc.zzn(obj, (long) (i & 1048575)));
    }

    private final void zzC(Object obj, int i, zzabc zzabc) throws IOException {
        if (zzG(i)) {
            zzacc.zzo(obj, (long) (i & 1048575), zzabc.zzn());
        } else if (this.zzi) {
            zzacc.zzo(obj, (long) (i & 1048575), zzabc.zzm());
        } else {
            zzacc.zzo(obj, (long) (i & 1048575), zzabc.zzq());
        }
    }

    private final int zzD(int i) {
        return this.zzc[i + 1];
    }

    private final int zzE(int i) {
        return this.zzc[i + 2];
    }

    private static int zzF(int i) {
        return (i >>> 20) & 255;
    }

    private static boolean zzG(int i) {
        return (i & 536870912) != 0;
    }

    private static <T> double zzH(T t, long j) {
        return ((Double) zzacc.zzn(t, j)).doubleValue();
    }

    private static <T> float zzI(T t, long j) {
        return ((Float) zzacc.zzn(t, j)).floatValue();
    }

    private static <T> int zzJ(T t, long j) {
        return ((Integer) zzacc.zzn(t, j)).intValue();
    }

    private static <T> long zzK(T t, long j) {
        return ((Long) zzacc.zzn(t, j)).longValue();
    }

    private static <T> boolean zzL(T t, long j) {
        return ((Boolean) zzacc.zzn(t, j)).booleanValue();
    }

    private final boolean zzM(T t, T t2, int i) {
        return zzO(t, i) == zzO(t2, i);
    }

    private final boolean zzN(T t, int i, int i2, int i3, int i4) {
        if (i2 == 1048575) {
            return zzO(t, i);
        }
        return (i3 & i4) != 0;
    }

    private final boolean zzO(T t, int i) {
        int zzE = zzE(i);
        long j = (long) (zzE & 1048575);
        if (j != 1048575) {
            return (zzacc.zzd(t, j) & (1 << (zzE >>> 20))) != 0;
        }
        int zzD = zzD(i);
        long j2 = (long) (zzD & 1048575);
        switch (zzF(zzD)) {
            case 0:
                return zzacc.zzl(t, j2) != 0.0d;
            case 1:
                return zzacc.zzj(t, j2) != 0.0f;
            case 2:
                return zzacc.zzf(t, j2) != 0;
            case 3:
                return zzacc.zzf(t, j2) != 0;
            case 4:
                return zzacc.zzd(t, j2) != 0;
            case 5:
                return zzacc.zzf(t, j2) != 0;
            case 6:
                return zzacc.zzd(t, j2) != 0;
            case 7:
                return zzacc.zzh(t, j2);
            case 8:
                Object zzn2 = zzacc.zzn(t, j2);
                if (zzn2 instanceof String) {
                    return !((String) zzn2).isEmpty();
                }
                if (zzn2 instanceof zzym) {
                    return !zzym.zzb.equals(zzn2);
                }
                throw new IllegalArgumentException();
            case 9:
                return zzacc.zzn(t, j2) != null;
            case 10:
                return !zzym.zzb.equals(zzacc.zzn(t, j2));
            case 11:
                return zzacc.zzd(t, j2) != 0;
            case 12:
                return zzacc.zzd(t, j2) != 0;
            case 13:
                return zzacc.zzd(t, j2) != 0;
            case 14:
                return zzacc.zzf(t, j2) != 0;
            case 15:
                return zzacc.zzd(t, j2) != 0;
            case 16:
                return zzacc.zzf(t, j2) != 0;
            case 17:
                return zzacc.zzn(t, j2) != null;
            default:
                throw new IllegalArgumentException();
        }
    }

    private final void zzP(T t, int i) {
        int zzE = zzE(i);
        long j = (long) (1048575 & zzE);
        if (j != 1048575) {
            zzacc.zze(t, j, (1 << (zzE >>> 20)) | zzacc.zzd(t, j));
        }
    }

    private final boolean zzQ(T t, int i, int i2) {
        return zzacc.zzd(t, (long) (zzE(i2) & 1048575)) == i;
    }

    private final void zzR(T t, int i, int i2) {
        zzacc.zze(t, (long) (zzE(i2) & 1048575), i);
    }

    private final int zzS(int i) {
        if (i < this.zze || i > this.zzf) {
            return -1;
        }
        return zzU(i, 0);
    }

    private final int zzT(int i, int i2) {
        if (i < this.zze || i > this.zzf) {
            return -1;
        }
        return zzU(i, i2);
    }

    private final int zzU(int i, int i2) {
        int length = (this.zzc.length / 3) - 1;
        while (i2 <= length) {
            int i3 = (length + i2) >>> 1;
            int i4 = i3 * 3;
            int i5 = this.zzc[i4];
            if (i == i5) {
                return i4;
            }
            if (i < i5) {
                length = i3 - 1;
            } else {
                i2 = i3 + 1;
            }
        }
        return -1;
    }

    private final void zzV(T t, zzyx zzyx) throws IOException {
        int i;
        T t2 = t;
        zzyx zzyx2 = zzyx;
        if (!this.zzh) {
            int length = this.zzc.length;
            Unsafe unsafe = zzb;
            int i2 = 1048575;
            int i3 = 0;
            int i4 = 0;
            int i5 = 1048575;
            while (i3 < length) {
                int zzD = zzD(i3);
                int i6 = this.zzc[i3];
                int zzF = zzF(zzD);
                if (zzF <= 17) {
                    int i7 = this.zzc[i3 + 2];
                    int i8 = i7 & i2;
                    if (i8 != i5) {
                        i4 = unsafe.getInt(t2, (long) i8);
                        i5 = i8;
                    }
                    i = 1 << (i7 >>> 20);
                } else {
                    i = 0;
                }
                long j = (long) (zzD & i2);
                switch (zzF) {
                    case 0:
                        if ((i4 & i) == 0) {
                            break;
                        } else {
                            zzyx2.zzf(i6, zzacc.zzl(t2, j));
                            continue;
                        }
                    case 1:
                        if ((i4 & i) != 0) {
                            zzyx2.zze(i6, zzacc.zzj(t2, j));
                            break;
                        } else {
                            continue;
                        }
                    case 2:
                        if ((i4 & i) != 0) {
                            zzyx2.zzc(i6, unsafe.getLong(t2, j));
                            break;
                        } else {
                            continue;
                        }
                    case 3:
                        if ((i4 & i) != 0) {
                            zzyx2.zzh(i6, unsafe.getLong(t2, j));
                            break;
                        } else {
                            continue;
                        }
                    case 4:
                        if ((i4 & i) != 0) {
                            zzyx2.zzi(i6, unsafe.getInt(t2, j));
                            break;
                        } else {
                            continue;
                        }
                    case 5:
                        if ((i4 & i) != 0) {
                            zzyx2.zzj(i6, unsafe.getLong(t2, j));
                            break;
                        } else {
                            continue;
                        }
                    case 6:
                        if ((i4 & i) != 0) {
                            zzyx2.zzk(i6, unsafe.getInt(t2, j));
                            break;
                        } else {
                            continue;
                        }
                    case 7:
                        if ((i4 & i) != 0) {
                            zzyx2.zzl(i6, zzacc.zzh(t2, j));
                            break;
                        } else {
                            continue;
                        }
                    case 8:
                        if ((i4 & i) != 0) {
                            zzX(i6, unsafe.getObject(t2, j), zzyx2);
                            break;
                        } else {
                            continue;
                        }
                    case 9:
                        if ((i4 & i) != 0) {
                            zzyx2.zzr(i6, unsafe.getObject(t2, j), zzw(i3));
                            break;
                        } else {
                            continue;
                        }
                    case 10:
                        if ((i4 & i) != 0) {
                            zzyx2.zzn(i6, (zzym) unsafe.getObject(t2, j));
                            break;
                        } else {
                            continue;
                        }
                    case 11:
                        if ((i4 & i) != 0) {
                            zzyx2.zzo(i6, unsafe.getInt(t2, j));
                            break;
                        } else {
                            continue;
                        }
                    case 12:
                        if ((i4 & i) != 0) {
                            zzyx2.zzg(i6, unsafe.getInt(t2, j));
                            break;
                        } else {
                            continue;
                        }
                    case 13:
                        if ((i4 & i) != 0) {
                            zzyx2.zzb(i6, unsafe.getInt(t2, j));
                            break;
                        } else {
                            continue;
                        }
                    case 14:
                        if ((i4 & i) != 0) {
                            zzyx2.zzd(i6, unsafe.getLong(t2, j));
                            break;
                        } else {
                            continue;
                        }
                    case 15:
                        if ((i4 & i) != 0) {
                            zzyx2.zzp(i6, unsafe.getInt(t2, j));
                            break;
                        } else {
                            continue;
                        }
                    case 16:
                        if ((i4 & i) != 0) {
                            zzyx2.zzq(i6, unsafe.getLong(t2, j));
                            break;
                        } else {
                            continue;
                        }
                    case 17:
                        if ((i4 & i) != 0) {
                            zzyx2.zzs(i6, unsafe.getObject(t2, j), zzw(i3));
                            break;
                        } else {
                            continue;
                        }
                    case 18:
                        zzabf.zzJ(this.zzc[i3], (List) unsafe.getObject(t2, j), zzyx2, false);
                        continue;
                    case 19:
                        zzabf.zzK(this.zzc[i3], (List) unsafe.getObject(t2, j), zzyx2, false);
                        continue;
                    case 20:
                        zzabf.zzL(this.zzc[i3], (List) unsafe.getObject(t2, j), zzyx2, false);
                        continue;
                    case 21:
                        zzabf.zzM(this.zzc[i3], (List) unsafe.getObject(t2, j), zzyx2, false);
                        continue;
                    case 22:
                        zzabf.zzQ(this.zzc[i3], (List) unsafe.getObject(t2, j), zzyx2, false);
                        continue;
                    case 23:
                        zzabf.zzO(this.zzc[i3], (List) unsafe.getObject(t2, j), zzyx2, false);
                        continue;
                    case 24:
                        zzabf.zzT(this.zzc[i3], (List) unsafe.getObject(t2, j), zzyx2, false);
                        continue;
                    case 25:
                        zzabf.zzW(this.zzc[i3], (List) unsafe.getObject(t2, j), zzyx2, false);
                        continue;
                    case 26:
                        zzabf.zzX(this.zzc[i3], (List) unsafe.getObject(t2, j), zzyx2);
                        break;
                    case 27:
                        zzabf.zzZ(this.zzc[i3], (List) unsafe.getObject(t2, j), zzyx2, zzw(i3));
                        break;
                    case 28:
                        zzabf.zzY(this.zzc[i3], (List) unsafe.getObject(t2, j), zzyx2);
                        break;
                    case 29:
                        zzabf.zzR(this.zzc[i3], (List) unsafe.getObject(t2, j), zzyx2, false);
                        break;
                    case 30:
                        zzabf.zzV(this.zzc[i3], (List) unsafe.getObject(t2, j), zzyx2, false);
                        break;
                    case 31:
                        zzabf.zzU(this.zzc[i3], (List) unsafe.getObject(t2, j), zzyx2, false);
                        break;
                    case 32:
                        zzabf.zzP(this.zzc[i3], (List) unsafe.getObject(t2, j), zzyx2, false);
                        break;
                    case 33:
                        zzabf.zzS(this.zzc[i3], (List) unsafe.getObject(t2, j), zzyx2, false);
                        break;
                    case 34:
                        zzabf.zzN(this.zzc[i3], (List) unsafe.getObject(t2, j), zzyx2, false);
                        break;
                    case 35:
                        zzabf.zzJ(this.zzc[i3], (List) unsafe.getObject(t2, j), zzyx2, true);
                        break;
                    case 36:
                        zzabf.zzK(this.zzc[i3], (List) unsafe.getObject(t2, j), zzyx2, true);
                        break;
                    case 37:
                        zzabf.zzL(this.zzc[i3], (List) unsafe.getObject(t2, j), zzyx2, true);
                        break;
                    case 38:
                        zzabf.zzM(this.zzc[i3], (List) unsafe.getObject(t2, j), zzyx2, true);
                        break;
                    case 39:
                        zzabf.zzQ(this.zzc[i3], (List) unsafe.getObject(t2, j), zzyx2, true);
                        break;
                    case 40:
                        zzabf.zzO(this.zzc[i3], (List) unsafe.getObject(t2, j), zzyx2, true);
                        break;
                    case 41:
                        zzabf.zzT(this.zzc[i3], (List) unsafe.getObject(t2, j), zzyx2, true);
                        break;
                    case 42:
                        zzabf.zzW(this.zzc[i3], (List) unsafe.getObject(t2, j), zzyx2, true);
                        break;
                    case 43:
                        zzabf.zzR(this.zzc[i3], (List) unsafe.getObject(t2, j), zzyx2, true);
                        break;
                    case 44:
                        zzabf.zzV(this.zzc[i3], (List) unsafe.getObject(t2, j), zzyx2, true);
                        break;
                    case 45:
                        zzabf.zzU(this.zzc[i3], (List) unsafe.getObject(t2, j), zzyx2, true);
                        break;
                    case 46:
                        zzabf.zzP(this.zzc[i3], (List) unsafe.getObject(t2, j), zzyx2, true);
                        break;
                    case 47:
                        zzabf.zzS(this.zzc[i3], (List) unsafe.getObject(t2, j), zzyx2, true);
                        break;
                    case 48:
                        zzabf.zzN(this.zzc[i3], (List) unsafe.getObject(t2, j), zzyx2, true);
                        break;
                    case 49:
                        zzabf.zzaa(this.zzc[i3], (List) unsafe.getObject(t2, j), zzyx2, zzw(i3));
                        break;
                    case 50:
                        zzW(zzyx2, i6, unsafe.getObject(t2, j), i3);
                        break;
                    case 51:
                        if (zzQ(t2, i6, i3)) {
                            zzyx2.zzf(i6, zzH(t2, j));
                            break;
                        }
                        break;
                    case 52:
                        if (zzQ(t2, i6, i3)) {
                            zzyx2.zze(i6, zzI(t2, j));
                            break;
                        }
                        break;
                    case 53:
                        if (zzQ(t2, i6, i3)) {
                            zzyx2.zzc(i6, zzK(t2, j));
                            break;
                        }
                        break;
                    case 54:
                        if (zzQ(t2, i6, i3)) {
                            zzyx2.zzh(i6, zzK(t2, j));
                            break;
                        }
                        break;
                    case 55:
                        if (zzQ(t2, i6, i3)) {
                            zzyx2.zzi(i6, zzJ(t2, j));
                            break;
                        }
                        break;
                    case 56:
                        if (zzQ(t2, i6, i3)) {
                            zzyx2.zzj(i6, zzK(t2, j));
                            break;
                        }
                        break;
                    case 57:
                        if (zzQ(t2, i6, i3)) {
                            zzyx2.zzk(i6, zzJ(t2, j));
                            break;
                        }
                        break;
                    case 58:
                        if (zzQ(t2, i6, i3)) {
                            zzyx2.zzl(i6, zzL(t2, j));
                            break;
                        }
                        break;
                    case 59:
                        if (zzQ(t2, i6, i3)) {
                            zzX(i6, unsafe.getObject(t2, j), zzyx2);
                            break;
                        }
                        break;
                    case 60:
                        if (zzQ(t2, i6, i3)) {
                            zzyx2.zzr(i6, unsafe.getObject(t2, j), zzw(i3));
                            break;
                        }
                        break;
                    case 61:
                        if (zzQ(t2, i6, i3)) {
                            zzyx2.zzn(i6, (zzym) unsafe.getObject(t2, j));
                            break;
                        }
                        break;
                    case 62:
                        if (zzQ(t2, i6, i3)) {
                            zzyx2.zzo(i6, zzJ(t2, j));
                            break;
                        }
                        break;
                    case 63:
                        if (zzQ(t2, i6, i3)) {
                            zzyx2.zzg(i6, zzJ(t2, j));
                            break;
                        }
                        break;
                    case 64:
                        if (zzQ(t2, i6, i3)) {
                            zzyx2.zzb(i6, zzJ(t2, j));
                            break;
                        }
                        break;
                    case 65:
                        if (zzQ(t2, i6, i3)) {
                            zzyx2.zzd(i6, zzK(t2, j));
                            break;
                        }
                        break;
                    case 66:
                        if (zzQ(t2, i6, i3)) {
                            zzyx2.zzp(i6, zzJ(t2, j));
                            break;
                        }
                        break;
                    case 67:
                        if (zzQ(t2, i6, i3)) {
                            zzyx2.zzq(i6, zzK(t2, j));
                            break;
                        }
                        break;
                    case 68:
                        if (zzQ(t2, i6, i3)) {
                            zzyx2.zzs(i6, unsafe.getObject(t2, j), zzw(i3));
                            break;
                        }
                        break;
                }
                i3 += 3;
                i2 = 1048575;
            }
            zzabr<?, ?> zzabr = this.zzo;
            zzabr.zzr(zzabr.zzj(t2), zzyx2);
            return;
        }
        this.zzp.zzb(t2);
        throw null;
    }

    private final <K, V> void zzW(zzyx zzyx, int i, Object obj, int i2) throws IOException {
        if (obj != null) {
            zzaak zzaak = (zzaak) zzx(i2);
            throw null;
        }
    }

    private static final void zzX(int i, Object obj, zzyx zzyx) throws IOException {
        if (obj instanceof String) {
            zzyx.zzm(i, (String) obj);
        } else {
            zzyx.zzn(i, (zzym) obj);
        }
    }

    static zzabs zzg(Object obj) {
        zzzo zzzo = (zzzo) obj;
        zzabs zzabs = zzzo.zzc;
        if (zzabs != zzabs.zza()) {
            return zzabs;
        }
        zzabs zzb2 = zzabs.zzb();
        zzzo.zzc = zzb2;
        return zzb2;
    }

    static <T> zzaau<T> zzl(Class<T> cls, zzaao zzaao, zzaaw zzaaw, zzaaf zzaaf, zzabr<?, ?> zzabr, zzzc<?> zzzc, zzaam zzaam) {
        if (zzaao instanceof zzabb) {
            return zzm((zzabb) zzaao, zzaaw, zzaaf, zzabr, zzzc, zzaam);
        }
        zzabo zzabo = (zzabo) zzaao;
        throw null;
    }

    /* JADX WARNING: Removed duplicated region for block: B:120:0x025d  */
    /* JADX WARNING: Removed duplicated region for block: B:121:0x0260  */
    /* JADX WARNING: Removed duplicated region for block: B:124:0x0278  */
    /* JADX WARNING: Removed duplicated region for block: B:125:0x027b  */
    /* JADX WARNING: Removed duplicated region for block: B:158:0x032f  */
    /* JADX WARNING: Removed duplicated region for block: B:171:0x0378  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static <T> com.google.android.gms.internal.p007firebaseauthapi.zzaau<T> zzm(com.google.android.gms.internal.p007firebaseauthapi.zzabb r34, com.google.android.gms.internal.p007firebaseauthapi.zzaaw r35, com.google.android.gms.internal.p007firebaseauthapi.zzaaf r36, com.google.android.gms.internal.p007firebaseauthapi.zzabr<?, ?> r37, com.google.android.gms.internal.p007firebaseauthapi.zzzc<?> r38, com.google.android.gms.internal.p007firebaseauthapi.zzaam r39) {
        /*
            int r0 = r34.zzc()
            r1 = 0
            r3 = 2
            if (r0 != r3) goto L_0x000a
            r10 = 1
            goto L_0x000b
        L_0x000a:
            r10 = 0
        L_0x000b:
            java.lang.String r0 = r34.zzd()
            int r3 = r0.length()
            char r4 = r0.charAt(r1)
            r5 = 55296(0xd800, float:7.7486E-41)
            if (r4 < r5) goto L_0x0027
            r4 = 1
        L_0x001d:
            int r6 = r4 + 1
            char r4 = r0.charAt(r4)
            if (r4 < r5) goto L_0x0028
            r4 = r6
            goto L_0x001d
        L_0x0027:
            r6 = 1
        L_0x0028:
            int r4 = r6 + 1
            char r6 = r0.charAt(r6)
            if (r6 < r5) goto L_0x0047
            r6 = r6 & 8191(0x1fff, float:1.1478E-41)
            r8 = 13
        L_0x0034:
            int r9 = r4 + 1
            char r4 = r0.charAt(r4)
            if (r4 < r5) goto L_0x0044
            r4 = r4 & 8191(0x1fff, float:1.1478E-41)
            int r4 = r4 << r8
            r6 = r6 | r4
            int r8 = r8 + 13
            r4 = r9
            goto L_0x0034
        L_0x0044:
            int r4 = r4 << r8
            r6 = r6 | r4
            r4 = r9
        L_0x0047:
            if (r6 != 0) goto L_0x0056
            int[] r6 = zza
            r13 = r6
            r6 = 0
            r8 = 0
            r9 = 0
            r11 = 0
            r12 = 0
            r14 = 0
            r16 = 0
            goto L_0x0165
        L_0x0056:
            int r6 = r4 + 1
            char r4 = r0.charAt(r4)
            if (r4 < r5) goto L_0x0075
            r4 = r4 & 8191(0x1fff, float:1.1478E-41)
            r8 = 13
        L_0x0062:
            int r9 = r6 + 1
            char r6 = r0.charAt(r6)
            if (r6 < r5) goto L_0x0072
            r6 = r6 & 8191(0x1fff, float:1.1478E-41)
            int r6 = r6 << r8
            r4 = r4 | r6
            int r8 = r8 + 13
            r6 = r9
            goto L_0x0062
        L_0x0072:
            int r6 = r6 << r8
            r4 = r4 | r6
            r6 = r9
        L_0x0075:
            int r8 = r6 + 1
            char r6 = r0.charAt(r6)
            if (r6 < r5) goto L_0x0094
            r6 = r6 & 8191(0x1fff, float:1.1478E-41)
            r9 = 13
        L_0x0081:
            int r11 = r8 + 1
            char r8 = r0.charAt(r8)
            if (r8 < r5) goto L_0x0091
            r8 = r8 & 8191(0x1fff, float:1.1478E-41)
            int r8 = r8 << r9
            r6 = r6 | r8
            int r9 = r9 + 13
            r8 = r11
            goto L_0x0081
        L_0x0091:
            int r8 = r8 << r9
            r6 = r6 | r8
            r8 = r11
        L_0x0094:
            int r9 = r8 + 1
            char r8 = r0.charAt(r8)
            if (r8 < r5) goto L_0x00b3
            r8 = r8 & 8191(0x1fff, float:1.1478E-41)
            r11 = 13
        L_0x00a0:
            int r12 = r9 + 1
            char r9 = r0.charAt(r9)
            if (r9 < r5) goto L_0x00b0
            r9 = r9 & 8191(0x1fff, float:1.1478E-41)
            int r9 = r9 << r11
            r8 = r8 | r9
            int r11 = r11 + 13
            r9 = r12
            goto L_0x00a0
        L_0x00b0:
            int r9 = r9 << r11
            r8 = r8 | r9
            r9 = r12
        L_0x00b3:
            int r11 = r9 + 1
            char r9 = r0.charAt(r9)
            if (r9 < r5) goto L_0x00d2
            r9 = r9 & 8191(0x1fff, float:1.1478E-41)
            r12 = 13
        L_0x00bf:
            int r13 = r11 + 1
            char r11 = r0.charAt(r11)
            if (r11 < r5) goto L_0x00cf
            r11 = r11 & 8191(0x1fff, float:1.1478E-41)
            int r11 = r11 << r12
            r9 = r9 | r11
            int r12 = r12 + 13
            r11 = r13
            goto L_0x00bf
        L_0x00cf:
            int r11 = r11 << r12
            r9 = r9 | r11
            r11 = r13
        L_0x00d2:
            int r12 = r11 + 1
            char r11 = r0.charAt(r11)
            if (r11 < r5) goto L_0x00f1
            r11 = r11 & 8191(0x1fff, float:1.1478E-41)
            r13 = 13
        L_0x00de:
            int r14 = r12 + 1
            char r12 = r0.charAt(r12)
            if (r12 < r5) goto L_0x00ee
            r12 = r12 & 8191(0x1fff, float:1.1478E-41)
            int r12 = r12 << r13
            r11 = r11 | r12
            int r13 = r13 + 13
            r12 = r14
            goto L_0x00de
        L_0x00ee:
            int r12 = r12 << r13
            r11 = r11 | r12
            r12 = r14
        L_0x00f1:
            int r13 = r12 + 1
            char r12 = r0.charAt(r12)
            if (r12 < r5) goto L_0x0110
            r12 = r12 & 8191(0x1fff, float:1.1478E-41)
            r14 = 13
        L_0x00fd:
            int r15 = r13 + 1
            char r13 = r0.charAt(r13)
            if (r13 < r5) goto L_0x010d
            r13 = r13 & 8191(0x1fff, float:1.1478E-41)
            int r13 = r13 << r14
            r12 = r12 | r13
            int r14 = r14 + 13
            r13 = r15
            goto L_0x00fd
        L_0x010d:
            int r13 = r13 << r14
            r12 = r12 | r13
            r13 = r15
        L_0x0110:
            int r14 = r13 + 1
            char r13 = r0.charAt(r13)
            if (r13 < r5) goto L_0x0131
            r13 = r13 & 8191(0x1fff, float:1.1478E-41)
            r15 = 13
        L_0x011c:
            int r16 = r14 + 1
            char r14 = r0.charAt(r14)
            if (r14 < r5) goto L_0x012d
            r14 = r14 & 8191(0x1fff, float:1.1478E-41)
            int r14 = r14 << r15
            r13 = r13 | r14
            int r15 = r15 + 13
            r14 = r16
            goto L_0x011c
        L_0x012d:
            int r14 = r14 << r15
            r13 = r13 | r14
            r14 = r16
        L_0x0131:
            int r15 = r14 + 1
            char r14 = r0.charAt(r14)
            if (r14 < r5) goto L_0x0154
            r14 = r14 & 8191(0x1fff, float:1.1478E-41)
            r16 = 13
        L_0x013d:
            int r17 = r15 + 1
            char r15 = r0.charAt(r15)
            if (r15 < r5) goto L_0x014f
            r15 = r15 & 8191(0x1fff, float:1.1478E-41)
            int r15 = r15 << r16
            r14 = r14 | r15
            int r16 = r16 + 13
            r15 = r17
            goto L_0x013d
        L_0x014f:
            int r15 = r15 << r16
            r14 = r14 | r15
            r15 = r17
        L_0x0154:
            int r16 = r14 + r12
            int r13 = r16 + r13
            int[] r13 = new int[r13]
            int r16 = r4 + r4
            int r16 = r16 + r6
            r6 = r4
            r4 = r15
            r33 = r12
            r12 = r9
            r9 = r33
        L_0x0165:
            sun.misc.Unsafe r15 = zzb
            java.lang.Object[] r17 = r34.zze()
            com.google.android.gms.internal.firebase-auth-api.zzaar r18 = r34.zzb()
            java.lang.Class r1 = r18.getClass()
            int r7 = r11 * 3
            int[] r7 = new int[r7]
            int r11 = r11 + r11
            java.lang.Object[] r11 = new java.lang.Object[r11]
            int r21 = r14 + r9
            r22 = r14
            r23 = r21
            r9 = 0
            r20 = 0
        L_0x0183:
            if (r4 >= r3) goto L_0x03ca
            int r24 = r4 + 1
            char r4 = r0.charAt(r4)
            if (r4 < r5) goto L_0x01ab
            r4 = r4 & 8191(0x1fff, float:1.1478E-41)
            r2 = r24
            r24 = 13
        L_0x0193:
            int r26 = r2 + 1
            char r2 = r0.charAt(r2)
            if (r2 < r5) goto L_0x01a5
            r2 = r2 & 8191(0x1fff, float:1.1478E-41)
            int r2 = r2 << r24
            r4 = r4 | r2
            int r24 = r24 + 13
            r2 = r26
            goto L_0x0193
        L_0x01a5:
            int r2 = r2 << r24
            r4 = r4 | r2
            r2 = r26
            goto L_0x01ad
        L_0x01ab:
            r2 = r24
        L_0x01ad:
            int r24 = r2 + 1
            char r2 = r0.charAt(r2)
            if (r2 < r5) goto L_0x01da
            r2 = r2 & 8191(0x1fff, float:1.1478E-41)
            r5 = r24
            r24 = 13
        L_0x01bb:
            int r27 = r5 + 1
            char r5 = r0.charAt(r5)
            r28 = r3
            r3 = 55296(0xd800, float:7.7486E-41)
            if (r5 < r3) goto L_0x01d4
            r3 = r5 & 8191(0x1fff, float:1.1478E-41)
            int r3 = r3 << r24
            r2 = r2 | r3
            int r24 = r24 + 13
            r5 = r27
            r3 = r28
            goto L_0x01bb
        L_0x01d4:
            int r3 = r5 << r24
            r2 = r2 | r3
            r3 = r27
            goto L_0x01de
        L_0x01da:
            r28 = r3
            r3 = r24
        L_0x01de:
            r5 = r2 & 255(0xff, float:3.57E-43)
            r24 = r14
            r14 = r2 & 1024(0x400, float:1.435E-42)
            if (r14 == 0) goto L_0x01ec
            int r14 = r20 + 1
            r13[r20] = r9
            r20 = r14
        L_0x01ec:
            r14 = 51
            if (r5 < r14) goto L_0x0295
            int r14 = r3 + 1
            char r3 = r0.charAt(r3)
            r27 = r14
            r14 = 55296(0xd800, float:7.7486E-41)
            if (r3 < r14) goto L_0x0222
            r3 = r3 & 8191(0x1fff, float:1.1478E-41)
            r14 = r27
            r27 = 13
        L_0x0203:
            int r31 = r14 + 1
            char r14 = r0.charAt(r14)
            r32 = r12
            r12 = 55296(0xd800, float:7.7486E-41)
            if (r14 < r12) goto L_0x021c
            r12 = r14 & 8191(0x1fff, float:1.1478E-41)
            int r12 = r12 << r27
            r3 = r3 | r12
            int r27 = r27 + 13
            r14 = r31
            r12 = r32
            goto L_0x0203
        L_0x021c:
            int r12 = r14 << r27
            r3 = r3 | r12
            r14 = r31
            goto L_0x0226
        L_0x0222:
            r32 = r12
            r14 = r27
        L_0x0226:
            int r12 = r5 + -51
            r27 = r14
            r14 = 9
            if (r12 == r14) goto L_0x0247
            r14 = 17
            if (r12 != r14) goto L_0x0233
            goto L_0x0247
        L_0x0233:
            r14 = 12
            if (r12 != r14) goto L_0x0256
            if (r10 != 0) goto L_0x0256
            int r12 = r9 / 3
            int r14 = r16 + 1
            int r12 = r12 + r12
            r25 = 1
            int r12 = r12 + 1
            r16 = r17[r16]
            r11[r12] = r16
            goto L_0x0254
        L_0x0247:
            int r12 = r9 / 3
            int r14 = r16 + 1
            int r12 = r12 + r12
            r25 = 1
            int r12 = r12 + 1
            r16 = r17[r16]
            r11[r12] = r16
        L_0x0254:
            r16 = r14
        L_0x0256:
            int r3 = r3 + r3
            r12 = r17[r3]
            boolean r14 = r12 instanceof java.lang.reflect.Field
            if (r14 == 0) goto L_0x0260
            java.lang.reflect.Field r12 = (java.lang.reflect.Field) r12
            goto L_0x0268
        L_0x0260:
            java.lang.String r12 = (java.lang.String) r12
            java.lang.reflect.Field r12 = zzo(r1, r12)
            r17[r3] = r12
        L_0x0268:
            r31 = r7
            r14 = r8
            long r7 = r15.objectFieldOffset(r12)
            int r8 = (int) r7
            int r3 = r3 + 1
            r7 = r17[r3]
            boolean r12 = r7 instanceof java.lang.reflect.Field
            if (r12 == 0) goto L_0x027b
            java.lang.reflect.Field r7 = (java.lang.reflect.Field) r7
            goto L_0x0283
        L_0x027b:
            java.lang.String r7 = (java.lang.String) r7
            java.lang.reflect.Field r7 = zzo(r1, r7)
            r17[r3] = r7
        L_0x0283:
            r3 = r8
            long r7 = r15.objectFieldOffset(r7)
            int r8 = (int) r7
            r30 = r0
            r7 = r1
            r1 = r8
            r29 = r11
            r25 = 1
            r8 = r3
            r3 = 0
            goto L_0x0391
        L_0x0295:
            r31 = r7
            r14 = r8
            r32 = r12
            int r7 = r16 + 1
            r8 = r17[r16]
            java.lang.String r8 = (java.lang.String) r8
            java.lang.reflect.Field r8 = zzo(r1, r8)
            r12 = 9
            if (r5 == r12) goto L_0x030d
            r12 = 17
            if (r5 != r12) goto L_0x02ad
            goto L_0x030d
        L_0x02ad:
            r12 = 27
            if (r5 == r12) goto L_0x02fd
            r12 = 49
            if (r5 != r12) goto L_0x02b6
            goto L_0x02fd
        L_0x02b6:
            r12 = 12
            if (r5 == r12) goto L_0x02ed
            r12 = 30
            if (r5 == r12) goto L_0x02ed
            r12 = 44
            if (r5 != r12) goto L_0x02c3
            goto L_0x02ed
        L_0x02c3:
            r12 = 50
            if (r5 != r12) goto L_0x02e3
            int r12 = r22 + 1
            r13[r22] = r9
            int r22 = r9 / 3
            int r22 = r22 + r22
            int r27 = r7 + 1
            r7 = r17[r7]
            r11[r22] = r7
            r7 = r2 & 2048(0x800, float:2.87E-42)
            if (r7 == 0) goto L_0x02e6
            int r7 = r27 + 1
            int r22 = r22 + 1
            r27 = r17[r27]
            r11[r22] = r27
            r22 = r12
        L_0x02e3:
            r25 = 1
            goto L_0x031a
        L_0x02e6:
            r22 = r12
            r12 = r27
            r25 = 1
            goto L_0x031b
        L_0x02ed:
            if (r10 != 0) goto L_0x02e3
            int r12 = r9 / 3
            int r27 = r7 + 1
            int r12 = r12 + r12
            r25 = 1
            int r12 = r12 + 1
            r7 = r17[r7]
            r11[r12] = r7
            goto L_0x030a
        L_0x02fd:
            r25 = 1
            int r12 = r9 / 3
            int r27 = r7 + 1
            int r12 = r12 + r12
            int r12 = r12 + 1
            r7 = r17[r7]
            r11[r12] = r7
        L_0x030a:
            r12 = r27
            goto L_0x031b
        L_0x030d:
            r25 = 1
            int r12 = r9 / 3
            int r12 = r12 + r12
            int r12 = r12 + 1
            java.lang.Class r27 = r8.getType()
            r11[r12] = r27
        L_0x031a:
            r12 = r7
        L_0x031b:
            long r7 = r15.objectFieldOffset(r8)
            int r8 = (int) r7
            r7 = r2 & 4096(0x1000, float:5.74E-42)
            r27 = 1048575(0xfffff, float:1.469367E-39)
            r29 = r11
            r11 = 4096(0x1000, float:5.74E-42)
            if (r7 != r11) goto L_0x0378
            r7 = 17
            if (r5 > r7) goto L_0x0378
            int r7 = r3 + 1
            char r3 = r0.charAt(r3)
            r11 = 55296(0xd800, float:7.7486E-41)
            if (r3 < r11) goto L_0x0354
            r3 = r3 & 8191(0x1fff, float:1.1478E-41)
            r26 = 13
        L_0x033e:
            int r27 = r7 + 1
            char r7 = r0.charAt(r7)
            if (r7 < r11) goto L_0x0350
            r7 = r7 & 8191(0x1fff, float:1.1478E-41)
            int r7 = r7 << r26
            r3 = r3 | r7
            int r26 = r26 + 13
            r7 = r27
            goto L_0x033e
        L_0x0350:
            int r7 = r7 << r26
            r3 = r3 | r7
            goto L_0x0356
        L_0x0354:
            r27 = r7
        L_0x0356:
            int r7 = r6 + r6
            int r26 = r3 / 32
            int r7 = r7 + r26
            r11 = r17[r7]
            r30 = r0
            boolean r0 = r11 instanceof java.lang.reflect.Field
            if (r0 == 0) goto L_0x0367
            java.lang.reflect.Field r11 = (java.lang.reflect.Field) r11
            goto L_0x036f
        L_0x0367:
            java.lang.String r11 = (java.lang.String) r11
            java.lang.reflect.Field r11 = zzo(r1, r11)
            r17[r7] = r11
        L_0x036f:
            r7 = r1
            long r0 = r15.objectFieldOffset(r11)
            int r1 = (int) r0
            int r3 = r3 % 32
            goto L_0x0381
        L_0x0378:
            r30 = r0
            r7 = r1
            r27 = r3
            r1 = 1048575(0xfffff, float:1.469367E-39)
            r3 = 0
        L_0x0381:
            r0 = 18
            if (r5 < r0) goto L_0x038f
            r0 = 49
            if (r5 > r0) goto L_0x038f
            int r0 = r23 + 1
            r13[r23] = r8
            r23 = r0
        L_0x038f:
            r16 = r12
        L_0x0391:
            int r0 = r9 + 1
            r31[r9] = r4
            int r4 = r0 + 1
            r9 = r2 & 512(0x200, float:7.175E-43)
            if (r9 == 0) goto L_0x039e
            r9 = 536870912(0x20000000, float:1.0842022E-19)
            goto L_0x039f
        L_0x039e:
            r9 = 0
        L_0x039f:
            r2 = r2 & 256(0x100, float:3.59E-43)
            if (r2 == 0) goto L_0x03a6
            r2 = 268435456(0x10000000, float:2.5243549E-29)
            goto L_0x03a7
        L_0x03a6:
            r2 = 0
        L_0x03a7:
            r2 = r2 | r9
            int r5 = r5 << 20
            r2 = r2 | r5
            r2 = r2 | r8
            r31[r0] = r2
            int r9 = r4 + 1
            int r0 = r3 << 20
            r0 = r0 | r1
            r31[r4] = r0
            r1 = r7
            r8 = r14
            r14 = r24
            r4 = r27
            r3 = r28
            r11 = r29
            r0 = r30
            r7 = r31
            r12 = r32
            r5 = 55296(0xd800, float:7.7486E-41)
            goto L_0x0183
        L_0x03ca:
            r31 = r7
            r29 = r11
            r32 = r12
            r24 = r14
            r14 = r8
            com.google.android.gms.internal.firebase-auth-api.zzaau r0 = new com.google.android.gms.internal.firebase-auth-api.zzaau
            r4 = r0
            com.google.android.gms.internal.firebase-auth-api.zzaar r9 = r34.zzb()
            r11 = 0
            r1 = r29
            r20 = 0
            r5 = r31
            r6 = r1
            r7 = r14
            r8 = r32
            r12 = r13
            r13 = r24
            r14 = r21
            r15 = r35
            r16 = r36
            r17 = r37
            r18 = r38
            r19 = r39
            r4.<init>(r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r17, r18, r19, r20)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.p007firebaseauthapi.zzaau.zzm(com.google.android.gms.internal.firebase-auth-api.zzabb, com.google.android.gms.internal.firebase-auth-api.zzaaw, com.google.android.gms.internal.firebase-auth-api.zzaaf, com.google.android.gms.internal.firebase-auth-api.zzabr, com.google.android.gms.internal.firebase-auth-api.zzzc, com.google.android.gms.internal.firebase-auth-api.zzaam):com.google.android.gms.internal.firebase-auth-api.zzaau");
    }

    private static Field zzo(Class<?> cls, String str) {
        try {
            return cls.getDeclaredField(str);
        } catch (NoSuchFieldException unused) {
            Field[] declaredFields = cls.getDeclaredFields();
            for (Field field : declaredFields) {
                if (str.equals(field.getName())) {
                    return field;
                }
            }
            String name = cls.getName();
            String arrays = Arrays.toString(declaredFields);
            StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 40 + String.valueOf(name).length() + String.valueOf(arrays).length());
            sb.append("Field ");
            sb.append(str);
            sb.append(" for ");
            sb.append(name);
            sb.append(" not found. Known fields are ");
            sb.append(arrays);
            throw new RuntimeException(sb.toString());
        }
    }

    private final void zzp(T t, T t2, int i) {
        long zzD = (long) (zzD(i) & 1048575);
        if (zzO(t2, i)) {
            Object zzn2 = zzacc.zzn(t, zzD);
            Object zzn3 = zzacc.zzn(t2, zzD);
            if (zzn2 != null && zzn3 != null) {
                zzacc.zzo(t, zzD, zzzu.zzi(zzn2, zzn3));
                zzP(t, i);
            } else if (zzn3 != null) {
                zzacc.zzo(t, zzD, zzn3);
                zzP(t, i);
            }
        }
    }

    private final void zzq(T t, T t2, int i) {
        int zzD = zzD(i);
        int i2 = this.zzc[i];
        long j = (long) (zzD & 1048575);
        if (zzQ(t2, i2, i)) {
            Object zzn2 = zzQ(t, i2, i) ? zzacc.zzn(t, j) : null;
            Object zzn3 = zzacc.zzn(t2, j);
            if (zzn2 != null && zzn3 != null) {
                zzacc.zzo(t, j, zzzu.zzi(zzn2, zzn3));
                zzR(t, i2, i);
            } else if (zzn3 != null) {
                zzacc.zzo(t, j, zzn3);
                zzR(t, i2, i);
            }
        }
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Code restructure failed: missing block: B:112:0x02df, code lost:
        r8 = r8 + r9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:155:0x0437, code lost:
        r8 = r8 + (r9 + r7);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:168:0x047a, code lost:
        r7 = r7 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:178:0x049f, code lost:
        r8 = r8 + r7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:179:0x04a0, code lost:
        r4 = r4 + r8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:186:0x04c3, code lost:
        r7 = r7 + r8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:190:0x04ce, code lost:
        r7 = r7 + 4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:194:0x04da, code lost:
        r7 = r7 + 8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:195:0x04de, code lost:
        r3 = r3 + 3;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final int zzr(T r15) {
        /*
            r14 = this;
            sun.misc.Unsafe r0 = zzb
            r1 = 1048575(0xfffff, float:1.469367E-39)
            r2 = 0
            r3 = 0
            r4 = 0
            r5 = 0
            r6 = 1048575(0xfffff, float:1.469367E-39)
        L_0x000c:
            int[] r7 = r14.zzc
            int r7 = r7.length
            if (r3 >= r7) goto L_0x04e2
            int r7 = r14.zzD(r3)
            int[] r8 = r14.zzc
            r8 = r8[r3]
            int r9 = zzF(r7)
            r10 = 17
            r11 = 1
            if (r9 > r10) goto L_0x0037
            int[] r10 = r14.zzc
            int r12 = r3 + 2
            r10 = r10[r12]
            r12 = r10 & r1
            int r10 = r10 >>> 20
            int r10 = r11 << r10
            if (r12 == r6) goto L_0x0038
            long r5 = (long) r12
            int r5 = r0.getInt(r15, r5)
            r6 = r12
            goto L_0x0038
        L_0x0037:
            r10 = 0
        L_0x0038:
            r7 = r7 & r1
            long r12 = (long) r7
            switch(r9) {
                case 0: goto L_0x04d2;
                case 1: goto L_0x04c6;
                case 2: goto L_0x04b3;
                case 3: goto L_0x04a2;
                case 4: goto L_0x048f;
                case 5: goto L_0x0486;
                case 6: goto L_0x047d;
                case 7: goto L_0x0472;
                case 8: goto L_0x044c;
                case 9: goto L_0x043a;
                case 10: goto L_0x0421;
                case 11: goto L_0x040f;
                case 12: goto L_0x03fd;
                case 13: goto L_0x03f3;
                case 14: goto L_0x03e9;
                case 15: goto L_0x03d3;
                case 16: goto L_0x03bd;
                case 17: goto L_0x03aa;
                case 18: goto L_0x039d;
                case 19: goto L_0x0392;
                case 20: goto L_0x0387;
                case 21: goto L_0x037c;
                case 22: goto L_0x0371;
                case 23: goto L_0x0366;
                case 24: goto L_0x035b;
                case 25: goto L_0x0350;
                case 26: goto L_0x0345;
                case 27: goto L_0x0336;
                case 28: goto L_0x032a;
                case 29: goto L_0x031e;
                case 30: goto L_0x0312;
                case 31: goto L_0x0306;
                case 32: goto L_0x02fa;
                case 33: goto L_0x02ee;
                case 34: goto L_0x02e2;
                case 35: goto L_0x02cb;
                case 36: goto L_0x02b6;
                case 37: goto L_0x02a1;
                case 38: goto L_0x028c;
                case 39: goto L_0x0277;
                case 40: goto L_0x0262;
                case 41: goto L_0x024c;
                case 42: goto L_0x0236;
                case 43: goto L_0x0220;
                case 44: goto L_0x020a;
                case 45: goto L_0x01f4;
                case 46: goto L_0x01de;
                case 47: goto L_0x01c8;
                case 48: goto L_0x01b2;
                case 49: goto L_0x01a2;
                case 50: goto L_0x0195;
                case 51: goto L_0x0189;
                case 52: goto L_0x017d;
                case 53: goto L_0x0169;
                case 54: goto L_0x0155;
                case 55: goto L_0x0141;
                case 56: goto L_0x0135;
                case 57: goto L_0x0129;
                case 58: goto L_0x011d;
                case 59: goto L_0x00f3;
                case 60: goto L_0x00df;
                case 61: goto L_0x00c5;
                case 62: goto L_0x00b1;
                case 63: goto L_0x009d;
                case 64: goto L_0x0091;
                case 65: goto L_0x0085;
                case 66: goto L_0x006d;
                case 67: goto L_0x0055;
                case 68: goto L_0x003f;
                default: goto L_0x003d;
            }
        L_0x003d:
            goto L_0x04de
        L_0x003f:
            boolean r7 = r14.zzQ(r15, r8, r3)
            if (r7 == 0) goto L_0x04de
            java.lang.Object r7 = r0.getObject(r15, r12)
            com.google.android.gms.internal.firebase-auth-api.zzaar r7 = (com.google.android.gms.internal.p007firebaseauthapi.zzaar) r7
            com.google.android.gms.internal.firebase-auth-api.zzabd r9 = r14.zzw(r3)
            int r7 = com.google.android.gms.internal.p007firebaseauthapi.zzyw.zzK(r8, r7, r9)
            goto L_0x03a7
        L_0x0055:
            boolean r7 = r14.zzQ(r15, r8, r3)
            if (r7 == 0) goto L_0x04de
            long r9 = zzK(r15, r12)
            int r7 = com.google.android.gms.internal.p007firebaseauthapi.zzyw.zzy(r8)
            long r8 = com.google.android.gms.internal.p007firebaseauthapi.zzyw.zzH(r9)
            int r8 = com.google.android.gms.internal.p007firebaseauthapi.zzyw.zzB(r8)
            goto L_0x04c3
        L_0x006d:
            boolean r7 = r14.zzQ(r15, r8, r3)
            if (r7 == 0) goto L_0x04de
            int r7 = zzJ(r15, r12)
            int r8 = com.google.android.gms.internal.p007firebaseauthapi.zzyw.zzy(r8)
            int r7 = com.google.android.gms.internal.p007firebaseauthapi.zzyw.zzG(r7)
            int r7 = com.google.android.gms.internal.p007firebaseauthapi.zzyw.zzA(r7)
            goto L_0x049f
        L_0x0085:
            boolean r7 = r14.zzQ(r15, r8, r3)
            if (r7 == 0) goto L_0x04de
            int r7 = com.google.android.gms.internal.p007firebaseauthapi.zzyw.zzy(r8)
            goto L_0x04da
        L_0x0091:
            boolean r7 = r14.zzQ(r15, r8, r3)
            if (r7 == 0) goto L_0x04de
            int r7 = com.google.android.gms.internal.p007firebaseauthapi.zzyw.zzy(r8)
            goto L_0x04ce
        L_0x009d:
            boolean r7 = r14.zzQ(r15, r8, r3)
            if (r7 == 0) goto L_0x04de
            int r7 = zzJ(r15, r12)
            int r8 = com.google.android.gms.internal.p007firebaseauthapi.zzyw.zzy(r8)
            int r7 = com.google.android.gms.internal.p007firebaseauthapi.zzyw.zzz(r7)
            goto L_0x049f
        L_0x00b1:
            boolean r7 = r14.zzQ(r15, r8, r3)
            if (r7 == 0) goto L_0x04de
            int r7 = zzJ(r15, r12)
            int r8 = com.google.android.gms.internal.p007firebaseauthapi.zzyw.zzy(r8)
            int r7 = com.google.android.gms.internal.p007firebaseauthapi.zzyw.zzA(r7)
            goto L_0x049f
        L_0x00c5:
            boolean r7 = r14.zzQ(r15, r8, r3)
            if (r7 == 0) goto L_0x04de
            java.lang.Object r7 = r0.getObject(r15, r12)
            com.google.android.gms.internal.firebase-auth-api.zzym r7 = (com.google.android.gms.internal.p007firebaseauthapi.zzym) r7
            int r8 = com.google.android.gms.internal.p007firebaseauthapi.zzyw.zzy(r8)
            int r7 = r7.zzc()
            int r9 = com.google.android.gms.internal.p007firebaseauthapi.zzyw.zzA(r7)
            goto L_0x0437
        L_0x00df:
            boolean r7 = r14.zzQ(r15, r8, r3)
            if (r7 == 0) goto L_0x04de
            java.lang.Object r7 = r0.getObject(r15, r12)
            com.google.android.gms.internal.firebase-auth-api.zzabd r9 = r14.zzw(r3)
            int r7 = com.google.android.gms.internal.p007firebaseauthapi.zzabf.zzw(r8, r7, r9)
            goto L_0x03a7
        L_0x00f3:
            boolean r7 = r14.zzQ(r15, r8, r3)
            if (r7 == 0) goto L_0x04de
            java.lang.Object r7 = r0.getObject(r15, r12)
            boolean r9 = r7 instanceof com.google.android.gms.internal.p007firebaseauthapi.zzym
            if (r9 == 0) goto L_0x0111
            com.google.android.gms.internal.firebase-auth-api.zzym r7 = (com.google.android.gms.internal.p007firebaseauthapi.zzym) r7
            int r8 = com.google.android.gms.internal.p007firebaseauthapi.zzyw.zzy(r8)
            int r7 = r7.zzc()
            int r9 = com.google.android.gms.internal.p007firebaseauthapi.zzyw.zzA(r7)
            goto L_0x0437
        L_0x0111:
            java.lang.String r7 = (java.lang.String) r7
            int r8 = com.google.android.gms.internal.p007firebaseauthapi.zzyw.zzy(r8)
            int r7 = com.google.android.gms.internal.p007firebaseauthapi.zzyw.zzC(r7)
            goto L_0x049f
        L_0x011d:
            boolean r7 = r14.zzQ(r15, r8, r3)
            if (r7 == 0) goto L_0x04de
            int r7 = com.google.android.gms.internal.p007firebaseauthapi.zzyw.zzy(r8)
            goto L_0x047a
        L_0x0129:
            boolean r7 = r14.zzQ(r15, r8, r3)
            if (r7 == 0) goto L_0x04de
            int r7 = com.google.android.gms.internal.p007firebaseauthapi.zzyw.zzy(r8)
            goto L_0x04ce
        L_0x0135:
            boolean r7 = r14.zzQ(r15, r8, r3)
            if (r7 == 0) goto L_0x04de
            int r7 = com.google.android.gms.internal.p007firebaseauthapi.zzyw.zzy(r8)
            goto L_0x04da
        L_0x0141:
            boolean r7 = r14.zzQ(r15, r8, r3)
            if (r7 == 0) goto L_0x04de
            int r7 = zzJ(r15, r12)
            int r8 = com.google.android.gms.internal.p007firebaseauthapi.zzyw.zzy(r8)
            int r7 = com.google.android.gms.internal.p007firebaseauthapi.zzyw.zzz(r7)
            goto L_0x049f
        L_0x0155:
            boolean r7 = r14.zzQ(r15, r8, r3)
            if (r7 == 0) goto L_0x04de
            long r9 = zzK(r15, r12)
            int r7 = com.google.android.gms.internal.p007firebaseauthapi.zzyw.zzy(r8)
            int r8 = com.google.android.gms.internal.p007firebaseauthapi.zzyw.zzB(r9)
            goto L_0x04c3
        L_0x0169:
            boolean r7 = r14.zzQ(r15, r8, r3)
            if (r7 == 0) goto L_0x04de
            long r9 = zzK(r15, r12)
            int r7 = com.google.android.gms.internal.p007firebaseauthapi.zzyw.zzy(r8)
            int r8 = com.google.android.gms.internal.p007firebaseauthapi.zzyw.zzB(r9)
            goto L_0x04c3
        L_0x017d:
            boolean r7 = r14.zzQ(r15, r8, r3)
            if (r7 == 0) goto L_0x04de
            int r7 = com.google.android.gms.internal.p007firebaseauthapi.zzyw.zzy(r8)
            goto L_0x04ce
        L_0x0189:
            boolean r7 = r14.zzQ(r15, r8, r3)
            if (r7 == 0) goto L_0x04de
            int r7 = com.google.android.gms.internal.p007firebaseauthapi.zzyw.zzy(r8)
            goto L_0x04da
        L_0x0195:
            java.lang.Object r7 = r0.getObject(r15, r12)
            java.lang.Object r9 = r14.zzx(r3)
            com.google.android.gms.internal.p007firebaseauthapi.zzaam.zza(r8, r7, r9)
            goto L_0x04de
        L_0x01a2:
            java.lang.Object r7 = r0.getObject(r15, r12)
            java.util.List r7 = (java.util.List) r7
            com.google.android.gms.internal.firebase-auth-api.zzabd r9 = r14.zzw(r3)
            int r7 = com.google.android.gms.internal.p007firebaseauthapi.zzabf.zzz(r8, r7, r9)
            goto L_0x03a7
        L_0x01b2:
            java.lang.Object r7 = r0.getObject(r15, r12)
            java.util.List r7 = (java.util.List) r7
            int r7 = com.google.android.gms.internal.p007firebaseauthapi.zzabf.zzf(r7)
            if (r7 <= 0) goto L_0x04de
            int r8 = com.google.android.gms.internal.p007firebaseauthapi.zzyw.zzy(r8)
            int r9 = com.google.android.gms.internal.p007firebaseauthapi.zzyw.zzA(r7)
            goto L_0x02df
        L_0x01c8:
            java.lang.Object r7 = r0.getObject(r15, r12)
            java.util.List r7 = (java.util.List) r7
            int r7 = com.google.android.gms.internal.p007firebaseauthapi.zzabf.zzn(r7)
            if (r7 <= 0) goto L_0x04de
            int r8 = com.google.android.gms.internal.p007firebaseauthapi.zzyw.zzy(r8)
            int r9 = com.google.android.gms.internal.p007firebaseauthapi.zzyw.zzA(r7)
            goto L_0x02df
        L_0x01de:
            java.lang.Object r7 = r0.getObject(r15, r12)
            java.util.List r7 = (java.util.List) r7
            int r7 = com.google.android.gms.internal.p007firebaseauthapi.zzabf.zzr(r7)
            if (r7 <= 0) goto L_0x04de
            int r8 = com.google.android.gms.internal.p007firebaseauthapi.zzyw.zzy(r8)
            int r9 = com.google.android.gms.internal.p007firebaseauthapi.zzyw.zzA(r7)
            goto L_0x02df
        L_0x01f4:
            java.lang.Object r7 = r0.getObject(r15, r12)
            java.util.List r7 = (java.util.List) r7
            int r7 = com.google.android.gms.internal.p007firebaseauthapi.zzabf.zzp(r7)
            if (r7 <= 0) goto L_0x04de
            int r8 = com.google.android.gms.internal.p007firebaseauthapi.zzyw.zzy(r8)
            int r9 = com.google.android.gms.internal.p007firebaseauthapi.zzyw.zzA(r7)
            goto L_0x02df
        L_0x020a:
            java.lang.Object r7 = r0.getObject(r15, r12)
            java.util.List r7 = (java.util.List) r7
            int r7 = com.google.android.gms.internal.p007firebaseauthapi.zzabf.zzh(r7)
            if (r7 <= 0) goto L_0x04de
            int r8 = com.google.android.gms.internal.p007firebaseauthapi.zzyw.zzy(r8)
            int r9 = com.google.android.gms.internal.p007firebaseauthapi.zzyw.zzA(r7)
            goto L_0x02df
        L_0x0220:
            java.lang.Object r7 = r0.getObject(r15, r12)
            java.util.List r7 = (java.util.List) r7
            int r7 = com.google.android.gms.internal.p007firebaseauthapi.zzabf.zzl(r7)
            if (r7 <= 0) goto L_0x04de
            int r8 = com.google.android.gms.internal.p007firebaseauthapi.zzyw.zzy(r8)
            int r9 = com.google.android.gms.internal.p007firebaseauthapi.zzyw.zzA(r7)
            goto L_0x02df
        L_0x0236:
            java.lang.Object r7 = r0.getObject(r15, r12)
            java.util.List r7 = (java.util.List) r7
            int r7 = com.google.android.gms.internal.p007firebaseauthapi.zzabf.zzt(r7)
            if (r7 <= 0) goto L_0x04de
            int r8 = com.google.android.gms.internal.p007firebaseauthapi.zzyw.zzy(r8)
            int r9 = com.google.android.gms.internal.p007firebaseauthapi.zzyw.zzA(r7)
            goto L_0x02df
        L_0x024c:
            java.lang.Object r7 = r0.getObject(r15, r12)
            java.util.List r7 = (java.util.List) r7
            int r7 = com.google.android.gms.internal.p007firebaseauthapi.zzabf.zzp(r7)
            if (r7 <= 0) goto L_0x04de
            int r8 = com.google.android.gms.internal.p007firebaseauthapi.zzyw.zzy(r8)
            int r9 = com.google.android.gms.internal.p007firebaseauthapi.zzyw.zzA(r7)
            goto L_0x02df
        L_0x0262:
            java.lang.Object r7 = r0.getObject(r15, r12)
            java.util.List r7 = (java.util.List) r7
            int r7 = com.google.android.gms.internal.p007firebaseauthapi.zzabf.zzr(r7)
            if (r7 <= 0) goto L_0x04de
            int r8 = com.google.android.gms.internal.p007firebaseauthapi.zzyw.zzy(r8)
            int r9 = com.google.android.gms.internal.p007firebaseauthapi.zzyw.zzA(r7)
            goto L_0x02df
        L_0x0277:
            java.lang.Object r7 = r0.getObject(r15, r12)
            java.util.List r7 = (java.util.List) r7
            int r7 = com.google.android.gms.internal.p007firebaseauthapi.zzabf.zzj(r7)
            if (r7 <= 0) goto L_0x04de
            int r8 = com.google.android.gms.internal.p007firebaseauthapi.zzyw.zzy(r8)
            int r9 = com.google.android.gms.internal.p007firebaseauthapi.zzyw.zzA(r7)
            goto L_0x02df
        L_0x028c:
            java.lang.Object r7 = r0.getObject(r15, r12)
            java.util.List r7 = (java.util.List) r7
            int r7 = com.google.android.gms.internal.p007firebaseauthapi.zzabf.zzd(r7)
            if (r7 <= 0) goto L_0x04de
            int r8 = com.google.android.gms.internal.p007firebaseauthapi.zzyw.zzy(r8)
            int r9 = com.google.android.gms.internal.p007firebaseauthapi.zzyw.zzA(r7)
            goto L_0x02df
        L_0x02a1:
            java.lang.Object r7 = r0.getObject(r15, r12)
            java.util.List r7 = (java.util.List) r7
            int r7 = com.google.android.gms.internal.p007firebaseauthapi.zzabf.zzb(r7)
            if (r7 <= 0) goto L_0x04de
            int r8 = com.google.android.gms.internal.p007firebaseauthapi.zzyw.zzy(r8)
            int r9 = com.google.android.gms.internal.p007firebaseauthapi.zzyw.zzA(r7)
            goto L_0x02df
        L_0x02b6:
            java.lang.Object r7 = r0.getObject(r15, r12)
            java.util.List r7 = (java.util.List) r7
            int r7 = com.google.android.gms.internal.p007firebaseauthapi.zzabf.zzp(r7)
            if (r7 <= 0) goto L_0x04de
            int r8 = com.google.android.gms.internal.p007firebaseauthapi.zzyw.zzy(r8)
            int r9 = com.google.android.gms.internal.p007firebaseauthapi.zzyw.zzA(r7)
            goto L_0x02df
        L_0x02cb:
            java.lang.Object r7 = r0.getObject(r15, r12)
            java.util.List r7 = (java.util.List) r7
            int r7 = com.google.android.gms.internal.p007firebaseauthapi.zzabf.zzr(r7)
            if (r7 <= 0) goto L_0x04de
            int r8 = com.google.android.gms.internal.p007firebaseauthapi.zzyw.zzy(r8)
            int r9 = com.google.android.gms.internal.p007firebaseauthapi.zzyw.zzA(r7)
        L_0x02df:
            int r8 = r8 + r9
            goto L_0x049f
        L_0x02e2:
            java.lang.Object r7 = r0.getObject(r15, r12)
            java.util.List r7 = (java.util.List) r7
            int r7 = com.google.android.gms.internal.p007firebaseauthapi.zzabf.zzg(r8, r7, r2)
            goto L_0x03a7
        L_0x02ee:
            java.lang.Object r7 = r0.getObject(r15, r12)
            java.util.List r7 = (java.util.List) r7
            int r7 = com.google.android.gms.internal.p007firebaseauthapi.zzabf.zzo(r8, r7, r2)
            goto L_0x03a7
        L_0x02fa:
            java.lang.Object r7 = r0.getObject(r15, r12)
            java.util.List r7 = (java.util.List) r7
            int r7 = com.google.android.gms.internal.p007firebaseauthapi.zzabf.zzs(r8, r7, r2)
            goto L_0x03a7
        L_0x0306:
            java.lang.Object r7 = r0.getObject(r15, r12)
            java.util.List r7 = (java.util.List) r7
            int r7 = com.google.android.gms.internal.p007firebaseauthapi.zzabf.zzq(r8, r7, r2)
            goto L_0x03a7
        L_0x0312:
            java.lang.Object r7 = r0.getObject(r15, r12)
            java.util.List r7 = (java.util.List) r7
            int r7 = com.google.android.gms.internal.p007firebaseauthapi.zzabf.zzi(r8, r7, r2)
            goto L_0x03a7
        L_0x031e:
            java.lang.Object r7 = r0.getObject(r15, r12)
            java.util.List r7 = (java.util.List) r7
            int r7 = com.google.android.gms.internal.p007firebaseauthapi.zzabf.zzm(r8, r7, r2)
            goto L_0x03a7
        L_0x032a:
            java.lang.Object r7 = r0.getObject(r15, r12)
            java.util.List r7 = (java.util.List) r7
            int r7 = com.google.android.gms.internal.p007firebaseauthapi.zzabf.zzy(r8, r7)
            goto L_0x03a7
        L_0x0336:
            java.lang.Object r7 = r0.getObject(r15, r12)
            java.util.List r7 = (java.util.List) r7
            com.google.android.gms.internal.firebase-auth-api.zzabd r9 = r14.zzw(r3)
            int r7 = com.google.android.gms.internal.p007firebaseauthapi.zzabf.zzx(r8, r7, r9)
            goto L_0x03a7
        L_0x0345:
            java.lang.Object r7 = r0.getObject(r15, r12)
            java.util.List r7 = (java.util.List) r7
            int r7 = com.google.android.gms.internal.p007firebaseauthapi.zzabf.zzv(r8, r7)
            goto L_0x03a7
        L_0x0350:
            java.lang.Object r7 = r0.getObject(r15, r12)
            java.util.List r7 = (java.util.List) r7
            int r7 = com.google.android.gms.internal.p007firebaseauthapi.zzabf.zzu(r8, r7, r2)
            goto L_0x03a7
        L_0x035b:
            java.lang.Object r7 = r0.getObject(r15, r12)
            java.util.List r7 = (java.util.List) r7
            int r7 = com.google.android.gms.internal.p007firebaseauthapi.zzabf.zzq(r8, r7, r2)
            goto L_0x03a7
        L_0x0366:
            java.lang.Object r7 = r0.getObject(r15, r12)
            java.util.List r7 = (java.util.List) r7
            int r7 = com.google.android.gms.internal.p007firebaseauthapi.zzabf.zzs(r8, r7, r2)
            goto L_0x03a7
        L_0x0371:
            java.lang.Object r7 = r0.getObject(r15, r12)
            java.util.List r7 = (java.util.List) r7
            int r7 = com.google.android.gms.internal.p007firebaseauthapi.zzabf.zzk(r8, r7, r2)
            goto L_0x03a7
        L_0x037c:
            java.lang.Object r7 = r0.getObject(r15, r12)
            java.util.List r7 = (java.util.List) r7
            int r7 = com.google.android.gms.internal.p007firebaseauthapi.zzabf.zze(r8, r7, r2)
            goto L_0x03a7
        L_0x0387:
            java.lang.Object r7 = r0.getObject(r15, r12)
            java.util.List r7 = (java.util.List) r7
            int r7 = com.google.android.gms.internal.p007firebaseauthapi.zzabf.zzc(r8, r7, r2)
            goto L_0x03a7
        L_0x0392:
            java.lang.Object r7 = r0.getObject(r15, r12)
            java.util.List r7 = (java.util.List) r7
            int r7 = com.google.android.gms.internal.p007firebaseauthapi.zzabf.zzq(r8, r7, r2)
            goto L_0x03a7
        L_0x039d:
            java.lang.Object r7 = r0.getObject(r15, r12)
            java.util.List r7 = (java.util.List) r7
            int r7 = com.google.android.gms.internal.p007firebaseauthapi.zzabf.zzs(r8, r7, r2)
        L_0x03a7:
            int r4 = r4 + r7
            goto L_0x04de
        L_0x03aa:
            r7 = r5 & r10
            if (r7 == 0) goto L_0x04de
            java.lang.Object r7 = r0.getObject(r15, r12)
            com.google.android.gms.internal.firebase-auth-api.zzaar r7 = (com.google.android.gms.internal.p007firebaseauthapi.zzaar) r7
            com.google.android.gms.internal.firebase-auth-api.zzabd r9 = r14.zzw(r3)
            int r7 = com.google.android.gms.internal.p007firebaseauthapi.zzyw.zzK(r8, r7, r9)
            goto L_0x03a7
        L_0x03bd:
            r7 = r5 & r10
            if (r7 == 0) goto L_0x04de
            long r9 = r0.getLong(r15, r12)
            int r7 = com.google.android.gms.internal.p007firebaseauthapi.zzyw.zzy(r8)
            long r8 = com.google.android.gms.internal.p007firebaseauthapi.zzyw.zzH(r9)
            int r8 = com.google.android.gms.internal.p007firebaseauthapi.zzyw.zzB(r8)
            goto L_0x04c3
        L_0x03d3:
            r7 = r5 & r10
            if (r7 == 0) goto L_0x04de
            int r7 = r0.getInt(r15, r12)
            int r8 = com.google.android.gms.internal.p007firebaseauthapi.zzyw.zzy(r8)
            int r7 = com.google.android.gms.internal.p007firebaseauthapi.zzyw.zzG(r7)
            int r7 = com.google.android.gms.internal.p007firebaseauthapi.zzyw.zzA(r7)
            goto L_0x049f
        L_0x03e9:
            r7 = r5 & r10
            if (r7 == 0) goto L_0x04de
            int r7 = com.google.android.gms.internal.p007firebaseauthapi.zzyw.zzy(r8)
            goto L_0x04da
        L_0x03f3:
            r7 = r5 & r10
            if (r7 == 0) goto L_0x04de
            int r7 = com.google.android.gms.internal.p007firebaseauthapi.zzyw.zzy(r8)
            goto L_0x04ce
        L_0x03fd:
            r7 = r5 & r10
            if (r7 == 0) goto L_0x04de
            int r7 = r0.getInt(r15, r12)
            int r8 = com.google.android.gms.internal.p007firebaseauthapi.zzyw.zzy(r8)
            int r7 = com.google.android.gms.internal.p007firebaseauthapi.zzyw.zzz(r7)
            goto L_0x049f
        L_0x040f:
            r7 = r5 & r10
            if (r7 == 0) goto L_0x04de
            int r7 = r0.getInt(r15, r12)
            int r8 = com.google.android.gms.internal.p007firebaseauthapi.zzyw.zzy(r8)
            int r7 = com.google.android.gms.internal.p007firebaseauthapi.zzyw.zzA(r7)
            goto L_0x049f
        L_0x0421:
            r7 = r5 & r10
            if (r7 == 0) goto L_0x04de
            java.lang.Object r7 = r0.getObject(r15, r12)
            com.google.android.gms.internal.firebase-auth-api.zzym r7 = (com.google.android.gms.internal.p007firebaseauthapi.zzym) r7
            int r8 = com.google.android.gms.internal.p007firebaseauthapi.zzyw.zzy(r8)
            int r7 = r7.zzc()
            int r9 = com.google.android.gms.internal.p007firebaseauthapi.zzyw.zzA(r7)
        L_0x0437:
            int r9 = r9 + r7
            int r8 = r8 + r9
            goto L_0x04a0
        L_0x043a:
            r7 = r5 & r10
            if (r7 == 0) goto L_0x04de
            java.lang.Object r7 = r0.getObject(r15, r12)
            com.google.android.gms.internal.firebase-auth-api.zzabd r9 = r14.zzw(r3)
            int r7 = com.google.android.gms.internal.p007firebaseauthapi.zzabf.zzw(r8, r7, r9)
            goto L_0x03a7
        L_0x044c:
            r7 = r5 & r10
            if (r7 == 0) goto L_0x04de
            java.lang.Object r7 = r0.getObject(r15, r12)
            boolean r9 = r7 instanceof com.google.android.gms.internal.p007firebaseauthapi.zzym
            if (r9 == 0) goto L_0x0467
            com.google.android.gms.internal.firebase-auth-api.zzym r7 = (com.google.android.gms.internal.p007firebaseauthapi.zzym) r7
            int r8 = com.google.android.gms.internal.p007firebaseauthapi.zzyw.zzy(r8)
            int r7 = r7.zzc()
            int r9 = com.google.android.gms.internal.p007firebaseauthapi.zzyw.zzA(r7)
            goto L_0x0437
        L_0x0467:
            java.lang.String r7 = (java.lang.String) r7
            int r8 = com.google.android.gms.internal.p007firebaseauthapi.zzyw.zzy(r8)
            int r7 = com.google.android.gms.internal.p007firebaseauthapi.zzyw.zzC(r7)
            goto L_0x049f
        L_0x0472:
            r7 = r5 & r10
            if (r7 == 0) goto L_0x04de
            int r7 = com.google.android.gms.internal.p007firebaseauthapi.zzyw.zzy(r8)
        L_0x047a:
            int r7 = r7 + r11
            goto L_0x03a7
        L_0x047d:
            r7 = r5 & r10
            if (r7 == 0) goto L_0x04de
            int r7 = com.google.android.gms.internal.p007firebaseauthapi.zzyw.zzy(r8)
            goto L_0x04ce
        L_0x0486:
            r7 = r5 & r10
            if (r7 == 0) goto L_0x04de
            int r7 = com.google.android.gms.internal.p007firebaseauthapi.zzyw.zzy(r8)
            goto L_0x04da
        L_0x048f:
            r7 = r5 & r10
            if (r7 == 0) goto L_0x04de
            int r7 = r0.getInt(r15, r12)
            int r8 = com.google.android.gms.internal.p007firebaseauthapi.zzyw.zzy(r8)
            int r7 = com.google.android.gms.internal.p007firebaseauthapi.zzyw.zzz(r7)
        L_0x049f:
            int r8 = r8 + r7
        L_0x04a0:
            int r4 = r4 + r8
            goto L_0x04de
        L_0x04a2:
            r7 = r5 & r10
            if (r7 == 0) goto L_0x04de
            long r9 = r0.getLong(r15, r12)
            int r7 = com.google.android.gms.internal.p007firebaseauthapi.zzyw.zzy(r8)
            int r8 = com.google.android.gms.internal.p007firebaseauthapi.zzyw.zzB(r9)
            goto L_0x04c3
        L_0x04b3:
            r7 = r5 & r10
            if (r7 == 0) goto L_0x04de
            long r9 = r0.getLong(r15, r12)
            int r7 = com.google.android.gms.internal.p007firebaseauthapi.zzyw.zzy(r8)
            int r8 = com.google.android.gms.internal.p007firebaseauthapi.zzyw.zzB(r9)
        L_0x04c3:
            int r7 = r7 + r8
            goto L_0x03a7
        L_0x04c6:
            r7 = r5 & r10
            if (r7 == 0) goto L_0x04de
            int r7 = com.google.android.gms.internal.p007firebaseauthapi.zzyw.zzy(r8)
        L_0x04ce:
            int r7 = r7 + 4
            goto L_0x03a7
        L_0x04d2:
            r7 = r5 & r10
            if (r7 == 0) goto L_0x04de
            int r7 = com.google.android.gms.internal.p007firebaseauthapi.zzyw.zzy(r8)
        L_0x04da:
            int r7 = r7 + 8
            goto L_0x03a7
        L_0x04de:
            int r3 = r3 + 3
            goto L_0x000c
        L_0x04e2:
            com.google.android.gms.internal.firebase-auth-api.zzabr<?, ?> r0 = r14.zzo
            java.lang.Object r1 = r0.zzj(r15)
            int r0 = r0.zzq(r1)
            int r4 = r4 + r0
            boolean r0 = r14.zzh
            if (r0 != 0) goto L_0x04f2
            return r4
        L_0x04f2:
            com.google.android.gms.internal.firebase-auth-api.zzzc<?> r0 = r14.zzp
            r0.zzb(r15)
            r15 = 0
            throw r15
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.p007firebaseauthapi.zzaau.zzr(java.lang.Object):int");
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Code restructure failed: missing block: B:110:0x02d6, code lost:
        r5 = r5 + r6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:153:0x043e, code lost:
        r5 = r5 + (r6 + r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:166:0x0488, code lost:
        r4 = r4 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:176:0x04b4, code lost:
        r5 = r5 + r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:177:0x04b5, code lost:
        r3 = r3 + r5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:184:0x04dc, code lost:
        r3 = r3 + (r6 + r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:188:0x04e9, code lost:
        r4 = r4 + 4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:192:0x04f7, code lost:
        r4 = r4 + 8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:193:0x04fb, code lost:
        r2 = r2 + 3;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final int zzs(T r11) {
        /*
            r10 = this;
            sun.misc.Unsafe r0 = zzb
            r1 = 0
            r2 = 0
            r3 = 0
        L_0x0005:
            int[] r4 = r10.zzc
            int r4 = r4.length
            if (r2 >= r4) goto L_0x04ff
            int r4 = r10.zzD(r2)
            int r5 = zzF(r4)
            int[] r6 = r10.zzc
            r6 = r6[r2]
            r7 = 1048575(0xfffff, float:1.469367E-39)
            r4 = r4 & r7
            long r7 = (long) r4
            com.google.android.gms.internal.firebase-auth-api.zzzh r4 = com.google.android.gms.internal.p007firebaseauthapi.zzzh.DOUBLE_LIST_PACKED
            int r4 = r4.zza()
            if (r5 < r4) goto L_0x0031
            com.google.android.gms.internal.firebase-auth-api.zzzh r4 = com.google.android.gms.internal.p007firebaseauthapi.zzzh.SINT64_LIST_PACKED
            int r4 = r4.zza()
            if (r5 > r4) goto L_0x0031
            int[] r4 = r10.zzc
            int r9 = r2 + 2
            r4 = r4[r9]
        L_0x0031:
            switch(r5) {
                case 0: goto L_0x04ed;
                case 1: goto L_0x04df;
                case 2: goto L_0x04ca;
                case 3: goto L_0x04b7;
                case 4: goto L_0x04a2;
                case 5: goto L_0x0497;
                case 6: goto L_0x048c;
                case 7: goto L_0x047e;
                case 8: goto L_0x0456;
                case 9: goto L_0x0442;
                case 10: goto L_0x0426;
                case 11: goto L_0x0412;
                case 12: goto L_0x03fe;
                case 13: goto L_0x03f2;
                case 14: goto L_0x03e6;
                case 15: goto L_0x03ce;
                case 16: goto L_0x03b6;
                case 17: goto L_0x03a1;
                case 18: goto L_0x0394;
                case 19: goto L_0x0389;
                case 20: goto L_0x037e;
                case 21: goto L_0x0373;
                case 22: goto L_0x0368;
                case 23: goto L_0x035d;
                case 24: goto L_0x0352;
                case 25: goto L_0x0347;
                case 26: goto L_0x033c;
                case 27: goto L_0x032d;
                case 28: goto L_0x0321;
                case 29: goto L_0x0315;
                case 30: goto L_0x0309;
                case 31: goto L_0x02fd;
                case 32: goto L_0x02f1;
                case 33: goto L_0x02e5;
                case 34: goto L_0x02d9;
                case 35: goto L_0x02c2;
                case 36: goto L_0x02ad;
                case 37: goto L_0x0298;
                case 38: goto L_0x0283;
                case 39: goto L_0x026e;
                case 40: goto L_0x0259;
                case 41: goto L_0x0243;
                case 42: goto L_0x022d;
                case 43: goto L_0x0217;
                case 44: goto L_0x0201;
                case 45: goto L_0x01eb;
                case 46: goto L_0x01d5;
                case 47: goto L_0x01bf;
                case 48: goto L_0x01a9;
                case 49: goto L_0x0199;
                case 50: goto L_0x018c;
                case 51: goto L_0x0180;
                case 52: goto L_0x0174;
                case 53: goto L_0x0160;
                case 54: goto L_0x014c;
                case 55: goto L_0x0138;
                case 56: goto L_0x012c;
                case 57: goto L_0x0120;
                case 58: goto L_0x0114;
                case 59: goto L_0x00ea;
                case 60: goto L_0x00d6;
                case 61: goto L_0x00bc;
                case 62: goto L_0x00a8;
                case 63: goto L_0x0094;
                case 64: goto L_0x0088;
                case 65: goto L_0x007c;
                case 66: goto L_0x0064;
                case 67: goto L_0x004c;
                case 68: goto L_0x0036;
                default: goto L_0x0034;
            }
        L_0x0034:
            goto L_0x04fb
        L_0x0036:
            boolean r4 = r10.zzQ(r11, r6, r2)
            if (r4 == 0) goto L_0x04fb
            java.lang.Object r4 = com.google.android.gms.internal.p007firebaseauthapi.zzacc.zzn(r11, r7)
            com.google.android.gms.internal.firebase-auth-api.zzaar r4 = (com.google.android.gms.internal.p007firebaseauthapi.zzaar) r4
            com.google.android.gms.internal.firebase-auth-api.zzabd r5 = r10.zzw(r2)
            int r4 = com.google.android.gms.internal.p007firebaseauthapi.zzyw.zzK(r6, r4, r5)
            goto L_0x039e
        L_0x004c:
            boolean r4 = r10.zzQ(r11, r6, r2)
            if (r4 == 0) goto L_0x04fb
            long r4 = zzK(r11, r7)
            int r6 = com.google.android.gms.internal.p007firebaseauthapi.zzyw.zzy(r6)
            long r4 = com.google.android.gms.internal.p007firebaseauthapi.zzyw.zzH(r4)
            int r4 = com.google.android.gms.internal.p007firebaseauthapi.zzyw.zzB(r4)
            goto L_0x04dc
        L_0x0064:
            boolean r4 = r10.zzQ(r11, r6, r2)
            if (r4 == 0) goto L_0x04fb
            int r4 = zzJ(r11, r7)
            int r5 = com.google.android.gms.internal.p007firebaseauthapi.zzyw.zzy(r6)
            int r4 = com.google.android.gms.internal.p007firebaseauthapi.zzyw.zzG(r4)
            int r4 = com.google.android.gms.internal.p007firebaseauthapi.zzyw.zzA(r4)
            goto L_0x04b4
        L_0x007c:
            boolean r4 = r10.zzQ(r11, r6, r2)
            if (r4 == 0) goto L_0x04fb
            int r4 = com.google.android.gms.internal.p007firebaseauthapi.zzyw.zzy(r6)
            goto L_0x04f7
        L_0x0088:
            boolean r4 = r10.zzQ(r11, r6, r2)
            if (r4 == 0) goto L_0x04fb
            int r4 = com.google.android.gms.internal.p007firebaseauthapi.zzyw.zzy(r6)
            goto L_0x04e9
        L_0x0094:
            boolean r4 = r10.zzQ(r11, r6, r2)
            if (r4 == 0) goto L_0x04fb
            int r4 = zzJ(r11, r7)
            int r5 = com.google.android.gms.internal.p007firebaseauthapi.zzyw.zzy(r6)
            int r4 = com.google.android.gms.internal.p007firebaseauthapi.zzyw.zzz(r4)
            goto L_0x04b4
        L_0x00a8:
            boolean r4 = r10.zzQ(r11, r6, r2)
            if (r4 == 0) goto L_0x04fb
            int r4 = zzJ(r11, r7)
            int r5 = com.google.android.gms.internal.p007firebaseauthapi.zzyw.zzy(r6)
            int r4 = com.google.android.gms.internal.p007firebaseauthapi.zzyw.zzA(r4)
            goto L_0x04b4
        L_0x00bc:
            boolean r4 = r10.zzQ(r11, r6, r2)
            if (r4 == 0) goto L_0x04fb
            java.lang.Object r4 = com.google.android.gms.internal.p007firebaseauthapi.zzacc.zzn(r11, r7)
            com.google.android.gms.internal.firebase-auth-api.zzym r4 = (com.google.android.gms.internal.p007firebaseauthapi.zzym) r4
            int r5 = com.google.android.gms.internal.p007firebaseauthapi.zzyw.zzy(r6)
            int r4 = r4.zzc()
            int r6 = com.google.android.gms.internal.p007firebaseauthapi.zzyw.zzA(r4)
            goto L_0x043e
        L_0x00d6:
            boolean r4 = r10.zzQ(r11, r6, r2)
            if (r4 == 0) goto L_0x04fb
            java.lang.Object r4 = com.google.android.gms.internal.p007firebaseauthapi.zzacc.zzn(r11, r7)
            com.google.android.gms.internal.firebase-auth-api.zzabd r5 = r10.zzw(r2)
            int r4 = com.google.android.gms.internal.p007firebaseauthapi.zzabf.zzw(r6, r4, r5)
            goto L_0x039e
        L_0x00ea:
            boolean r4 = r10.zzQ(r11, r6, r2)
            if (r4 == 0) goto L_0x04fb
            java.lang.Object r4 = com.google.android.gms.internal.p007firebaseauthapi.zzacc.zzn(r11, r7)
            boolean r5 = r4 instanceof com.google.android.gms.internal.p007firebaseauthapi.zzym
            if (r5 == 0) goto L_0x0108
            com.google.android.gms.internal.firebase-auth-api.zzym r4 = (com.google.android.gms.internal.p007firebaseauthapi.zzym) r4
            int r5 = com.google.android.gms.internal.p007firebaseauthapi.zzyw.zzy(r6)
            int r4 = r4.zzc()
            int r6 = com.google.android.gms.internal.p007firebaseauthapi.zzyw.zzA(r4)
            goto L_0x043e
        L_0x0108:
            java.lang.String r4 = (java.lang.String) r4
            int r5 = com.google.android.gms.internal.p007firebaseauthapi.zzyw.zzy(r6)
            int r4 = com.google.android.gms.internal.p007firebaseauthapi.zzyw.zzC(r4)
            goto L_0x04b4
        L_0x0114:
            boolean r4 = r10.zzQ(r11, r6, r2)
            if (r4 == 0) goto L_0x04fb
            int r4 = com.google.android.gms.internal.p007firebaseauthapi.zzyw.zzy(r6)
            goto L_0x0488
        L_0x0120:
            boolean r4 = r10.zzQ(r11, r6, r2)
            if (r4 == 0) goto L_0x04fb
            int r4 = com.google.android.gms.internal.p007firebaseauthapi.zzyw.zzy(r6)
            goto L_0x04e9
        L_0x012c:
            boolean r4 = r10.zzQ(r11, r6, r2)
            if (r4 == 0) goto L_0x04fb
            int r4 = com.google.android.gms.internal.p007firebaseauthapi.zzyw.zzy(r6)
            goto L_0x04f7
        L_0x0138:
            boolean r4 = r10.zzQ(r11, r6, r2)
            if (r4 == 0) goto L_0x04fb
            int r4 = zzJ(r11, r7)
            int r5 = com.google.android.gms.internal.p007firebaseauthapi.zzyw.zzy(r6)
            int r4 = com.google.android.gms.internal.p007firebaseauthapi.zzyw.zzz(r4)
            goto L_0x04b4
        L_0x014c:
            boolean r4 = r10.zzQ(r11, r6, r2)
            if (r4 == 0) goto L_0x04fb
            long r4 = zzK(r11, r7)
            int r6 = com.google.android.gms.internal.p007firebaseauthapi.zzyw.zzy(r6)
            int r4 = com.google.android.gms.internal.p007firebaseauthapi.zzyw.zzB(r4)
            goto L_0x04dc
        L_0x0160:
            boolean r4 = r10.zzQ(r11, r6, r2)
            if (r4 == 0) goto L_0x04fb
            long r4 = zzK(r11, r7)
            int r6 = com.google.android.gms.internal.p007firebaseauthapi.zzyw.zzy(r6)
            int r4 = com.google.android.gms.internal.p007firebaseauthapi.zzyw.zzB(r4)
            goto L_0x04dc
        L_0x0174:
            boolean r4 = r10.zzQ(r11, r6, r2)
            if (r4 == 0) goto L_0x04fb
            int r4 = com.google.android.gms.internal.p007firebaseauthapi.zzyw.zzy(r6)
            goto L_0x04e9
        L_0x0180:
            boolean r4 = r10.zzQ(r11, r6, r2)
            if (r4 == 0) goto L_0x04fb
            int r4 = com.google.android.gms.internal.p007firebaseauthapi.zzyw.zzy(r6)
            goto L_0x04f7
        L_0x018c:
            java.lang.Object r4 = com.google.android.gms.internal.p007firebaseauthapi.zzacc.zzn(r11, r7)
            java.lang.Object r5 = r10.zzx(r2)
            com.google.android.gms.internal.p007firebaseauthapi.zzaam.zza(r6, r4, r5)
            goto L_0x04fb
        L_0x0199:
            java.lang.Object r4 = com.google.android.gms.internal.p007firebaseauthapi.zzacc.zzn(r11, r7)
            java.util.List r4 = (java.util.List) r4
            com.google.android.gms.internal.firebase-auth-api.zzabd r5 = r10.zzw(r2)
            int r4 = com.google.android.gms.internal.p007firebaseauthapi.zzabf.zzz(r6, r4, r5)
            goto L_0x039e
        L_0x01a9:
            java.lang.Object r4 = r0.getObject(r11, r7)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.p007firebaseauthapi.zzabf.zzf(r4)
            if (r4 <= 0) goto L_0x04fb
            int r5 = com.google.android.gms.internal.p007firebaseauthapi.zzyw.zzy(r6)
            int r6 = com.google.android.gms.internal.p007firebaseauthapi.zzyw.zzA(r4)
            goto L_0x02d6
        L_0x01bf:
            java.lang.Object r4 = r0.getObject(r11, r7)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.p007firebaseauthapi.zzabf.zzn(r4)
            if (r4 <= 0) goto L_0x04fb
            int r5 = com.google.android.gms.internal.p007firebaseauthapi.zzyw.zzy(r6)
            int r6 = com.google.android.gms.internal.p007firebaseauthapi.zzyw.zzA(r4)
            goto L_0x02d6
        L_0x01d5:
            java.lang.Object r4 = r0.getObject(r11, r7)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.p007firebaseauthapi.zzabf.zzr(r4)
            if (r4 <= 0) goto L_0x04fb
            int r5 = com.google.android.gms.internal.p007firebaseauthapi.zzyw.zzy(r6)
            int r6 = com.google.android.gms.internal.p007firebaseauthapi.zzyw.zzA(r4)
            goto L_0x02d6
        L_0x01eb:
            java.lang.Object r4 = r0.getObject(r11, r7)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.p007firebaseauthapi.zzabf.zzp(r4)
            if (r4 <= 0) goto L_0x04fb
            int r5 = com.google.android.gms.internal.p007firebaseauthapi.zzyw.zzy(r6)
            int r6 = com.google.android.gms.internal.p007firebaseauthapi.zzyw.zzA(r4)
            goto L_0x02d6
        L_0x0201:
            java.lang.Object r4 = r0.getObject(r11, r7)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.p007firebaseauthapi.zzabf.zzh(r4)
            if (r4 <= 0) goto L_0x04fb
            int r5 = com.google.android.gms.internal.p007firebaseauthapi.zzyw.zzy(r6)
            int r6 = com.google.android.gms.internal.p007firebaseauthapi.zzyw.zzA(r4)
            goto L_0x02d6
        L_0x0217:
            java.lang.Object r4 = r0.getObject(r11, r7)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.p007firebaseauthapi.zzabf.zzl(r4)
            if (r4 <= 0) goto L_0x04fb
            int r5 = com.google.android.gms.internal.p007firebaseauthapi.zzyw.zzy(r6)
            int r6 = com.google.android.gms.internal.p007firebaseauthapi.zzyw.zzA(r4)
            goto L_0x02d6
        L_0x022d:
            java.lang.Object r4 = r0.getObject(r11, r7)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.p007firebaseauthapi.zzabf.zzt(r4)
            if (r4 <= 0) goto L_0x04fb
            int r5 = com.google.android.gms.internal.p007firebaseauthapi.zzyw.zzy(r6)
            int r6 = com.google.android.gms.internal.p007firebaseauthapi.zzyw.zzA(r4)
            goto L_0x02d6
        L_0x0243:
            java.lang.Object r4 = r0.getObject(r11, r7)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.p007firebaseauthapi.zzabf.zzp(r4)
            if (r4 <= 0) goto L_0x04fb
            int r5 = com.google.android.gms.internal.p007firebaseauthapi.zzyw.zzy(r6)
            int r6 = com.google.android.gms.internal.p007firebaseauthapi.zzyw.zzA(r4)
            goto L_0x02d6
        L_0x0259:
            java.lang.Object r4 = r0.getObject(r11, r7)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.p007firebaseauthapi.zzabf.zzr(r4)
            if (r4 <= 0) goto L_0x04fb
            int r5 = com.google.android.gms.internal.p007firebaseauthapi.zzyw.zzy(r6)
            int r6 = com.google.android.gms.internal.p007firebaseauthapi.zzyw.zzA(r4)
            goto L_0x02d6
        L_0x026e:
            java.lang.Object r4 = r0.getObject(r11, r7)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.p007firebaseauthapi.zzabf.zzj(r4)
            if (r4 <= 0) goto L_0x04fb
            int r5 = com.google.android.gms.internal.p007firebaseauthapi.zzyw.zzy(r6)
            int r6 = com.google.android.gms.internal.p007firebaseauthapi.zzyw.zzA(r4)
            goto L_0x02d6
        L_0x0283:
            java.lang.Object r4 = r0.getObject(r11, r7)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.p007firebaseauthapi.zzabf.zzd(r4)
            if (r4 <= 0) goto L_0x04fb
            int r5 = com.google.android.gms.internal.p007firebaseauthapi.zzyw.zzy(r6)
            int r6 = com.google.android.gms.internal.p007firebaseauthapi.zzyw.zzA(r4)
            goto L_0x02d6
        L_0x0298:
            java.lang.Object r4 = r0.getObject(r11, r7)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.p007firebaseauthapi.zzabf.zzb(r4)
            if (r4 <= 0) goto L_0x04fb
            int r5 = com.google.android.gms.internal.p007firebaseauthapi.zzyw.zzy(r6)
            int r6 = com.google.android.gms.internal.p007firebaseauthapi.zzyw.zzA(r4)
            goto L_0x02d6
        L_0x02ad:
            java.lang.Object r4 = r0.getObject(r11, r7)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.p007firebaseauthapi.zzabf.zzp(r4)
            if (r4 <= 0) goto L_0x04fb
            int r5 = com.google.android.gms.internal.p007firebaseauthapi.zzyw.zzy(r6)
            int r6 = com.google.android.gms.internal.p007firebaseauthapi.zzyw.zzA(r4)
            goto L_0x02d6
        L_0x02c2:
            java.lang.Object r4 = r0.getObject(r11, r7)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.p007firebaseauthapi.zzabf.zzr(r4)
            if (r4 <= 0) goto L_0x04fb
            int r5 = com.google.android.gms.internal.p007firebaseauthapi.zzyw.zzy(r6)
            int r6 = com.google.android.gms.internal.p007firebaseauthapi.zzyw.zzA(r4)
        L_0x02d6:
            int r5 = r5 + r6
            goto L_0x04b4
        L_0x02d9:
            java.lang.Object r4 = com.google.android.gms.internal.p007firebaseauthapi.zzacc.zzn(r11, r7)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.p007firebaseauthapi.zzabf.zzg(r6, r4, r1)
            goto L_0x039e
        L_0x02e5:
            java.lang.Object r4 = com.google.android.gms.internal.p007firebaseauthapi.zzacc.zzn(r11, r7)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.p007firebaseauthapi.zzabf.zzo(r6, r4, r1)
            goto L_0x039e
        L_0x02f1:
            java.lang.Object r4 = com.google.android.gms.internal.p007firebaseauthapi.zzacc.zzn(r11, r7)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.p007firebaseauthapi.zzabf.zzs(r6, r4, r1)
            goto L_0x039e
        L_0x02fd:
            java.lang.Object r4 = com.google.android.gms.internal.p007firebaseauthapi.zzacc.zzn(r11, r7)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.p007firebaseauthapi.zzabf.zzq(r6, r4, r1)
            goto L_0x039e
        L_0x0309:
            java.lang.Object r4 = com.google.android.gms.internal.p007firebaseauthapi.zzacc.zzn(r11, r7)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.p007firebaseauthapi.zzabf.zzi(r6, r4, r1)
            goto L_0x039e
        L_0x0315:
            java.lang.Object r4 = com.google.android.gms.internal.p007firebaseauthapi.zzacc.zzn(r11, r7)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.p007firebaseauthapi.zzabf.zzm(r6, r4, r1)
            goto L_0x039e
        L_0x0321:
            java.lang.Object r4 = com.google.android.gms.internal.p007firebaseauthapi.zzacc.zzn(r11, r7)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.p007firebaseauthapi.zzabf.zzy(r6, r4)
            goto L_0x039e
        L_0x032d:
            java.lang.Object r4 = com.google.android.gms.internal.p007firebaseauthapi.zzacc.zzn(r11, r7)
            java.util.List r4 = (java.util.List) r4
            com.google.android.gms.internal.firebase-auth-api.zzabd r5 = r10.zzw(r2)
            int r4 = com.google.android.gms.internal.p007firebaseauthapi.zzabf.zzx(r6, r4, r5)
            goto L_0x039e
        L_0x033c:
            java.lang.Object r4 = com.google.android.gms.internal.p007firebaseauthapi.zzacc.zzn(r11, r7)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.p007firebaseauthapi.zzabf.zzv(r6, r4)
            goto L_0x039e
        L_0x0347:
            java.lang.Object r4 = com.google.android.gms.internal.p007firebaseauthapi.zzacc.zzn(r11, r7)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.p007firebaseauthapi.zzabf.zzu(r6, r4, r1)
            goto L_0x039e
        L_0x0352:
            java.lang.Object r4 = com.google.android.gms.internal.p007firebaseauthapi.zzacc.zzn(r11, r7)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.p007firebaseauthapi.zzabf.zzq(r6, r4, r1)
            goto L_0x039e
        L_0x035d:
            java.lang.Object r4 = com.google.android.gms.internal.p007firebaseauthapi.zzacc.zzn(r11, r7)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.p007firebaseauthapi.zzabf.zzs(r6, r4, r1)
            goto L_0x039e
        L_0x0368:
            java.lang.Object r4 = com.google.android.gms.internal.p007firebaseauthapi.zzacc.zzn(r11, r7)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.p007firebaseauthapi.zzabf.zzk(r6, r4, r1)
            goto L_0x039e
        L_0x0373:
            java.lang.Object r4 = com.google.android.gms.internal.p007firebaseauthapi.zzacc.zzn(r11, r7)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.p007firebaseauthapi.zzabf.zze(r6, r4, r1)
            goto L_0x039e
        L_0x037e:
            java.lang.Object r4 = com.google.android.gms.internal.p007firebaseauthapi.zzacc.zzn(r11, r7)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.p007firebaseauthapi.zzabf.zzc(r6, r4, r1)
            goto L_0x039e
        L_0x0389:
            java.lang.Object r4 = com.google.android.gms.internal.p007firebaseauthapi.zzacc.zzn(r11, r7)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.p007firebaseauthapi.zzabf.zzq(r6, r4, r1)
            goto L_0x039e
        L_0x0394:
            java.lang.Object r4 = com.google.android.gms.internal.p007firebaseauthapi.zzacc.zzn(r11, r7)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.p007firebaseauthapi.zzabf.zzs(r6, r4, r1)
        L_0x039e:
            int r3 = r3 + r4
            goto L_0x04fb
        L_0x03a1:
            boolean r4 = r10.zzO(r11, r2)
            if (r4 == 0) goto L_0x04fb
            java.lang.Object r4 = com.google.android.gms.internal.p007firebaseauthapi.zzacc.zzn(r11, r7)
            com.google.android.gms.internal.firebase-auth-api.zzaar r4 = (com.google.android.gms.internal.p007firebaseauthapi.zzaar) r4
            com.google.android.gms.internal.firebase-auth-api.zzabd r5 = r10.zzw(r2)
            int r4 = com.google.android.gms.internal.p007firebaseauthapi.zzyw.zzK(r6, r4, r5)
            goto L_0x039e
        L_0x03b6:
            boolean r4 = r10.zzO(r11, r2)
            if (r4 == 0) goto L_0x04fb
            long r4 = com.google.android.gms.internal.p007firebaseauthapi.zzacc.zzf(r11, r7)
            int r6 = com.google.android.gms.internal.p007firebaseauthapi.zzyw.zzy(r6)
            long r4 = com.google.android.gms.internal.p007firebaseauthapi.zzyw.zzH(r4)
            int r4 = com.google.android.gms.internal.p007firebaseauthapi.zzyw.zzB(r4)
            goto L_0x04dc
        L_0x03ce:
            boolean r4 = r10.zzO(r11, r2)
            if (r4 == 0) goto L_0x04fb
            int r4 = com.google.android.gms.internal.p007firebaseauthapi.zzacc.zzd(r11, r7)
            int r5 = com.google.android.gms.internal.p007firebaseauthapi.zzyw.zzy(r6)
            int r4 = com.google.android.gms.internal.p007firebaseauthapi.zzyw.zzG(r4)
            int r4 = com.google.android.gms.internal.p007firebaseauthapi.zzyw.zzA(r4)
            goto L_0x04b4
        L_0x03e6:
            boolean r4 = r10.zzO(r11, r2)
            if (r4 == 0) goto L_0x04fb
            int r4 = com.google.android.gms.internal.p007firebaseauthapi.zzyw.zzy(r6)
            goto L_0x04f7
        L_0x03f2:
            boolean r4 = r10.zzO(r11, r2)
            if (r4 == 0) goto L_0x04fb
            int r4 = com.google.android.gms.internal.p007firebaseauthapi.zzyw.zzy(r6)
            goto L_0x04e9
        L_0x03fe:
            boolean r4 = r10.zzO(r11, r2)
            if (r4 == 0) goto L_0x04fb
            int r4 = com.google.android.gms.internal.p007firebaseauthapi.zzacc.zzd(r11, r7)
            int r5 = com.google.android.gms.internal.p007firebaseauthapi.zzyw.zzy(r6)
            int r4 = com.google.android.gms.internal.p007firebaseauthapi.zzyw.zzz(r4)
            goto L_0x04b4
        L_0x0412:
            boolean r4 = r10.zzO(r11, r2)
            if (r4 == 0) goto L_0x04fb
            int r4 = com.google.android.gms.internal.p007firebaseauthapi.zzacc.zzd(r11, r7)
            int r5 = com.google.android.gms.internal.p007firebaseauthapi.zzyw.zzy(r6)
            int r4 = com.google.android.gms.internal.p007firebaseauthapi.zzyw.zzA(r4)
            goto L_0x04b4
        L_0x0426:
            boolean r4 = r10.zzO(r11, r2)
            if (r4 == 0) goto L_0x04fb
            java.lang.Object r4 = com.google.android.gms.internal.p007firebaseauthapi.zzacc.zzn(r11, r7)
            com.google.android.gms.internal.firebase-auth-api.zzym r4 = (com.google.android.gms.internal.p007firebaseauthapi.zzym) r4
            int r5 = com.google.android.gms.internal.p007firebaseauthapi.zzyw.zzy(r6)
            int r4 = r4.zzc()
            int r6 = com.google.android.gms.internal.p007firebaseauthapi.zzyw.zzA(r4)
        L_0x043e:
            int r6 = r6 + r4
            int r5 = r5 + r6
            goto L_0x04b5
        L_0x0442:
            boolean r4 = r10.zzO(r11, r2)
            if (r4 == 0) goto L_0x04fb
            java.lang.Object r4 = com.google.android.gms.internal.p007firebaseauthapi.zzacc.zzn(r11, r7)
            com.google.android.gms.internal.firebase-auth-api.zzabd r5 = r10.zzw(r2)
            int r4 = com.google.android.gms.internal.p007firebaseauthapi.zzabf.zzw(r6, r4, r5)
            goto L_0x039e
        L_0x0456:
            boolean r4 = r10.zzO(r11, r2)
            if (r4 == 0) goto L_0x04fb
            java.lang.Object r4 = com.google.android.gms.internal.p007firebaseauthapi.zzacc.zzn(r11, r7)
            boolean r5 = r4 instanceof com.google.android.gms.internal.p007firebaseauthapi.zzym
            if (r5 == 0) goto L_0x0473
            com.google.android.gms.internal.firebase-auth-api.zzym r4 = (com.google.android.gms.internal.p007firebaseauthapi.zzym) r4
            int r5 = com.google.android.gms.internal.p007firebaseauthapi.zzyw.zzy(r6)
            int r4 = r4.zzc()
            int r6 = com.google.android.gms.internal.p007firebaseauthapi.zzyw.zzA(r4)
            goto L_0x043e
        L_0x0473:
            java.lang.String r4 = (java.lang.String) r4
            int r5 = com.google.android.gms.internal.p007firebaseauthapi.zzyw.zzy(r6)
            int r4 = com.google.android.gms.internal.p007firebaseauthapi.zzyw.zzC(r4)
            goto L_0x04b4
        L_0x047e:
            boolean r4 = r10.zzO(r11, r2)
            if (r4 == 0) goto L_0x04fb
            int r4 = com.google.android.gms.internal.p007firebaseauthapi.zzyw.zzy(r6)
        L_0x0488:
            int r4 = r4 + 1
            goto L_0x039e
        L_0x048c:
            boolean r4 = r10.zzO(r11, r2)
            if (r4 == 0) goto L_0x04fb
            int r4 = com.google.android.gms.internal.p007firebaseauthapi.zzyw.zzy(r6)
            goto L_0x04e9
        L_0x0497:
            boolean r4 = r10.zzO(r11, r2)
            if (r4 == 0) goto L_0x04fb
            int r4 = com.google.android.gms.internal.p007firebaseauthapi.zzyw.zzy(r6)
            goto L_0x04f7
        L_0x04a2:
            boolean r4 = r10.zzO(r11, r2)
            if (r4 == 0) goto L_0x04fb
            int r4 = com.google.android.gms.internal.p007firebaseauthapi.zzacc.zzd(r11, r7)
            int r5 = com.google.android.gms.internal.p007firebaseauthapi.zzyw.zzy(r6)
            int r4 = com.google.android.gms.internal.p007firebaseauthapi.zzyw.zzz(r4)
        L_0x04b4:
            int r5 = r5 + r4
        L_0x04b5:
            int r3 = r3 + r5
            goto L_0x04fb
        L_0x04b7:
            boolean r4 = r10.zzO(r11, r2)
            if (r4 == 0) goto L_0x04fb
            long r4 = com.google.android.gms.internal.p007firebaseauthapi.zzacc.zzf(r11, r7)
            int r6 = com.google.android.gms.internal.p007firebaseauthapi.zzyw.zzy(r6)
            int r4 = com.google.android.gms.internal.p007firebaseauthapi.zzyw.zzB(r4)
            goto L_0x04dc
        L_0x04ca:
            boolean r4 = r10.zzO(r11, r2)
            if (r4 == 0) goto L_0x04fb
            long r4 = com.google.android.gms.internal.p007firebaseauthapi.zzacc.zzf(r11, r7)
            int r6 = com.google.android.gms.internal.p007firebaseauthapi.zzyw.zzy(r6)
            int r4 = com.google.android.gms.internal.p007firebaseauthapi.zzyw.zzB(r4)
        L_0x04dc:
            int r6 = r6 + r4
            int r3 = r3 + r6
            goto L_0x04fb
        L_0x04df:
            boolean r4 = r10.zzO(r11, r2)
            if (r4 == 0) goto L_0x04fb
            int r4 = com.google.android.gms.internal.p007firebaseauthapi.zzyw.zzy(r6)
        L_0x04e9:
            int r4 = r4 + 4
            goto L_0x039e
        L_0x04ed:
            boolean r4 = r10.zzO(r11, r2)
            if (r4 == 0) goto L_0x04fb
            int r4 = com.google.android.gms.internal.p007firebaseauthapi.zzyw.zzy(r6)
        L_0x04f7:
            int r4 = r4 + 8
            goto L_0x039e
        L_0x04fb:
            int r2 = r2 + 3
            goto L_0x0005
        L_0x04ff:
            com.google.android.gms.internal.firebase-auth-api.zzabr<?, ?> r0 = r10.zzo
            java.lang.Object r11 = r0.zzj(r11)
            int r11 = r0.zzq(r11)
            int r3 = r3 + r11
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.p007firebaseauthapi.zzaau.zzs(java.lang.Object):int");
    }

    /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: Regions count limit reached
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:47)
        	at jadx.core.utils.ErrorsCounter.methodError(ErrorsCounter.java:81)
        */
    /* JADX WARNING: Removed duplicated region for block: B:255:0x044f A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:92:0x01cf  */
    private final int zzt(T r16, byte[] r17, int r18, int r19, int r20, int r21, int r22, int r23, long r24, int r26, long r27, com.google.android.gms.internal.p007firebaseauthapi.zzxx r29) throws java.io.IOException {
        /*
            r15 = this;
            r0 = r15
            r1 = r16
            r3 = r17
            r4 = r18
            r5 = r19
            r2 = r20
            r6 = r22
            r8 = r23
            r9 = r27
            r7 = r29
            sun.misc.Unsafe r11 = zzb
            java.lang.Object r12 = r11.getObject(r1, r9)
            com.google.android.gms.internal.firebase-auth-api.zzzt r12 = (com.google.android.gms.internal.p007firebaseauthapi.zzzt) r12
            boolean r13 = r12.zza()
            if (r13 != 0) goto L_0x0032
            int r13 = r12.size()
            if (r13 != 0) goto L_0x002a
            r13 = 10
            goto L_0x002b
        L_0x002a:
            int r13 = r13 + r13
        L_0x002b:
            com.google.android.gms.internal.firebase-auth-api.zzzt r12 = r12.zze(r13)
            r11.putObject(r1, r9, r12)
        L_0x0032:
            r9 = 5
            r10 = 0
            r13 = 1
            r14 = 2
            switch(r26) {
                case 18: goto L_0x03e1;
                case 19: goto L_0x0394;
                case 20: goto L_0x0351;
                case 21: goto L_0x0351;
                case 22: goto L_0x0336;
                case 23: goto L_0x02f5;
                case 24: goto L_0x02b4;
                case 25: goto L_0x025a;
                case 26: goto L_0x01a7;
                case 27: goto L_0x018c;
                case 28: goto L_0x0132;
                case 29: goto L_0x0336;
                case 30: goto L_0x00fa;
                case 31: goto L_0x02b4;
                case 32: goto L_0x02f5;
                case 33: goto L_0x00ab;
                case 34: goto L_0x005c;
                case 35: goto L_0x03e1;
                case 36: goto L_0x0394;
                case 37: goto L_0x0351;
                case 38: goto L_0x0351;
                case 39: goto L_0x0336;
                case 40: goto L_0x02f5;
                case 41: goto L_0x02b4;
                case 42: goto L_0x025a;
                case 43: goto L_0x0336;
                case 44: goto L_0x00fa;
                case 45: goto L_0x02b4;
                case 46: goto L_0x02f5;
                case 47: goto L_0x00ab;
                case 48: goto L_0x005c;
                default: goto L_0x003a;
            }
        L_0x003a:
            r1 = 3
            if (r6 != r1) goto L_0x044f
            com.google.android.gms.internal.firebase-auth-api.zzabd r1 = r15.zzw(r8)
            r6 = r2 & -8
            r6 = r6 | 4
            r21 = r1
            r22 = r17
            r23 = r18
            r24 = r19
            r25 = r6
            r26 = r29
            int r4 = com.google.android.gms.internal.p007firebaseauthapi.zzxy.zzj(r21, r22, r23, r24, r25, r26)
            java.lang.Object r8 = r7.zzc
            r12.add(r8)
            goto L_0x042d
        L_0x005c:
            if (r6 != r14) goto L_0x0080
            com.google.android.gms.internal.firebase-auth-api.zzaag r12 = (com.google.android.gms.internal.p007firebaseauthapi.zzaag) r12
            int r1 = com.google.android.gms.internal.p007firebaseauthapi.zzxy.zza(r3, r4, r7)
            int r2 = r7.zza
            int r2 = r2 + r1
        L_0x0067:
            if (r1 >= r2) goto L_0x0077
            int r1 = com.google.android.gms.internal.p007firebaseauthapi.zzxy.zzc(r3, r1, r7)
            long r4 = r7.zzb
            long r4 = com.google.android.gms.internal.p007firebaseauthapi.zzyp.zzu(r4)
            r12.zzf(r4)
            goto L_0x0067
        L_0x0077:
            if (r1 != r2) goto L_0x007b
            goto L_0x0450
        L_0x007b:
            com.google.android.gms.internal.firebase-auth-api.zzzw r1 = com.google.android.gms.internal.p007firebaseauthapi.zzzw.zzb()
            throw r1
        L_0x0080:
            if (r6 != 0) goto L_0x044f
            com.google.android.gms.internal.firebase-auth-api.zzaag r12 = (com.google.android.gms.internal.p007firebaseauthapi.zzaag) r12
            int r1 = com.google.android.gms.internal.p007firebaseauthapi.zzxy.zzc(r3, r4, r7)
            long r8 = r7.zzb
            long r8 = com.google.android.gms.internal.p007firebaseauthapi.zzyp.zzu(r8)
            r12.zzf(r8)
        L_0x0091:
            if (r1 >= r5) goto L_0x00aa
            int r4 = com.google.android.gms.internal.p007firebaseauthapi.zzxy.zza(r3, r1, r7)
            int r6 = r7.zza
            if (r2 == r6) goto L_0x009c
            goto L_0x00aa
        L_0x009c:
            int r1 = com.google.android.gms.internal.p007firebaseauthapi.zzxy.zzc(r3, r4, r7)
            long r8 = r7.zzb
            long r8 = com.google.android.gms.internal.p007firebaseauthapi.zzyp.zzu(r8)
            r12.zzf(r8)
            goto L_0x0091
        L_0x00aa:
            return r1
        L_0x00ab:
            if (r6 != r14) goto L_0x00cf
            com.google.android.gms.internal.firebase-auth-api.zzzp r12 = (com.google.android.gms.internal.p007firebaseauthapi.zzzp) r12
            int r1 = com.google.android.gms.internal.p007firebaseauthapi.zzxy.zza(r3, r4, r7)
            int r2 = r7.zza
            int r2 = r2 + r1
        L_0x00b6:
            if (r1 >= r2) goto L_0x00c6
            int r1 = com.google.android.gms.internal.p007firebaseauthapi.zzxy.zza(r3, r1, r7)
            int r4 = r7.zza
            int r4 = com.google.android.gms.internal.p007firebaseauthapi.zzyp.zzt(r4)
            r12.zzf(r4)
            goto L_0x00b6
        L_0x00c6:
            if (r1 != r2) goto L_0x00ca
            goto L_0x0450
        L_0x00ca:
            com.google.android.gms.internal.firebase-auth-api.zzzw r1 = com.google.android.gms.internal.p007firebaseauthapi.zzzw.zzb()
            throw r1
        L_0x00cf:
            if (r6 != 0) goto L_0x044f
            com.google.android.gms.internal.firebase-auth-api.zzzp r12 = (com.google.android.gms.internal.p007firebaseauthapi.zzzp) r12
            int r1 = com.google.android.gms.internal.p007firebaseauthapi.zzxy.zza(r3, r4, r7)
            int r4 = r7.zza
            int r4 = com.google.android.gms.internal.p007firebaseauthapi.zzyp.zzt(r4)
            r12.zzf(r4)
        L_0x00e0:
            if (r1 >= r5) goto L_0x00f9
            int r4 = com.google.android.gms.internal.p007firebaseauthapi.zzxy.zza(r3, r1, r7)
            int r6 = r7.zza
            if (r2 == r6) goto L_0x00eb
            goto L_0x00f9
        L_0x00eb:
            int r1 = com.google.android.gms.internal.p007firebaseauthapi.zzxy.zza(r3, r4, r7)
            int r4 = r7.zza
            int r4 = com.google.android.gms.internal.p007firebaseauthapi.zzyp.zzt(r4)
            r12.zzf(r4)
            goto L_0x00e0
        L_0x00f9:
            return r1
        L_0x00fa:
            if (r6 != r14) goto L_0x0101
            int r2 = com.google.android.gms.internal.p007firebaseauthapi.zzxy.zzl(r3, r4, r12, r7)
            goto L_0x0112
        L_0x0101:
            if (r6 != 0) goto L_0x044f
            r2 = r20
            r3 = r17
            r4 = r18
            r5 = r19
            r6 = r12
            r7 = r29
            int r2 = com.google.android.gms.internal.p007firebaseauthapi.zzxy.zzk(r2, r3, r4, r5, r6, r7)
        L_0x0112:
            com.google.android.gms.internal.firebase-auth-api.zzzo r1 = (com.google.android.gms.internal.p007firebaseauthapi.zzzo) r1
            com.google.android.gms.internal.firebase-auth-api.zzabs r3 = r1.zzc
            com.google.android.gms.internal.firebase-auth-api.zzabs r4 = com.google.android.gms.internal.p007firebaseauthapi.zzabs.zza()
            if (r3 != r4) goto L_0x011d
            r3 = 0
        L_0x011d:
            com.google.android.gms.internal.firebase-auth-api.zzzs r4 = r15.zzy(r8)
            com.google.android.gms.internal.firebase-auth-api.zzabr<?, ?> r5 = r0.zzo
            r6 = r21
            java.lang.Object r3 = com.google.android.gms.internal.p007firebaseauthapi.zzabf.zzG(r6, r12, r4, r3, r5)
            if (r3 != 0) goto L_0x012d
            goto L_0x027b
        L_0x012d:
            com.google.android.gms.internal.firebase-auth-api.zzabs r3 = (com.google.android.gms.internal.p007firebaseauthapi.zzabs) r3
            r1.zzc = r3
            return r2
        L_0x0132:
            if (r6 != r14) goto L_0x044f
            int r1 = com.google.android.gms.internal.p007firebaseauthapi.zzxy.zza(r3, r4, r7)
            int r4 = r7.zza
            if (r4 < 0) goto L_0x0187
            int r6 = r3.length
            int r6 = r6 - r1
            if (r4 > r6) goto L_0x0182
            if (r4 != 0) goto L_0x0148
            com.google.android.gms.internal.firebase-auth-api.zzym r4 = com.google.android.gms.internal.p007firebaseauthapi.zzym.zzb
            r12.add(r4)
            goto L_0x0150
        L_0x0148:
            com.google.android.gms.internal.firebase-auth-api.zzym r6 = com.google.android.gms.internal.p007firebaseauthapi.zzym.zzl(r3, r1, r4)
            r12.add(r6)
        L_0x014f:
            int r1 = r1 + r4
        L_0x0150:
            if (r1 >= r5) goto L_0x0181
            int r4 = com.google.android.gms.internal.p007firebaseauthapi.zzxy.zza(r3, r1, r7)
            int r6 = r7.zza
            if (r2 == r6) goto L_0x015b
            goto L_0x0181
        L_0x015b:
            int r1 = com.google.android.gms.internal.p007firebaseauthapi.zzxy.zza(r3, r4, r7)
            int r4 = r7.zza
            if (r4 < 0) goto L_0x017c
            int r6 = r3.length
            int r6 = r6 - r1
            if (r4 > r6) goto L_0x0177
            if (r4 != 0) goto L_0x016f
            com.google.android.gms.internal.firebase-auth-api.zzym r4 = com.google.android.gms.internal.p007firebaseauthapi.zzym.zzb
            r12.add(r4)
            goto L_0x0150
        L_0x016f:
            com.google.android.gms.internal.firebase-auth-api.zzym r6 = com.google.android.gms.internal.p007firebaseauthapi.zzym.zzl(r3, r1, r4)
            r12.add(r6)
            goto L_0x014f
        L_0x0177:
            com.google.android.gms.internal.firebase-auth-api.zzzw r1 = com.google.android.gms.internal.p007firebaseauthapi.zzzw.zzb()
            throw r1
        L_0x017c:
            com.google.android.gms.internal.firebase-auth-api.zzzw r1 = com.google.android.gms.internal.p007firebaseauthapi.zzzw.zzc()
            throw r1
        L_0x0181:
            return r1
        L_0x0182:
            com.google.android.gms.internal.firebase-auth-api.zzzw r1 = com.google.android.gms.internal.p007firebaseauthapi.zzzw.zzb()
            throw r1
        L_0x0187:
            com.google.android.gms.internal.firebase-auth-api.zzzw r1 = com.google.android.gms.internal.p007firebaseauthapi.zzzw.zzc()
            throw r1
        L_0x018c:
            if (r6 == r14) goto L_0x0190
            goto L_0x044f
        L_0x0190:
            com.google.android.gms.internal.firebase-auth-api.zzabd r1 = r15.zzw(r8)
            r21 = r1
            r22 = r20
            r23 = r17
            r24 = r18
            r25 = r19
            r26 = r12
            r27 = r29
            int r1 = com.google.android.gms.internal.p007firebaseauthapi.zzxy.zzm(r21, r22, r23, r24, r25, r26, r27)
            return r1
        L_0x01a7:
            if (r6 != r14) goto L_0x044f
            r8 = 536870912(0x20000000, double:2.652494739E-315)
            long r8 = r24 & r8
            java.lang.String r1 = ""
            int r6 = (r8 > r10 ? 1 : (r8 == r10 ? 0 : -1))
            if (r6 != 0) goto L_0x01fa
            int r4 = com.google.android.gms.internal.p007firebaseauthapi.zzxy.zza(r3, r4, r7)
            int r6 = r7.zza
            if (r6 < 0) goto L_0x01f5
            if (r6 != 0) goto L_0x01c2
            r12.add(r1)
            goto L_0x01cd
        L_0x01c2:
            java.lang.String r8 = new java.lang.String
            java.nio.charset.Charset r9 = com.google.android.gms.internal.p007firebaseauthapi.zzzu.zza
            r8.<init>(r3, r4, r6, r9)
            r12.add(r8)
        L_0x01cc:
            int r4 = r4 + r6
        L_0x01cd:
            if (r4 >= r5) goto L_0x044f
            int r6 = com.google.android.gms.internal.p007firebaseauthapi.zzxy.zza(r3, r4, r7)
            int r8 = r7.zza
            if (r2 != r8) goto L_0x044f
            int r4 = com.google.android.gms.internal.p007firebaseauthapi.zzxy.zza(r3, r6, r7)
            int r6 = r7.zza
            if (r6 < 0) goto L_0x01f0
            if (r6 != 0) goto L_0x01e5
            r12.add(r1)
            goto L_0x01cd
        L_0x01e5:
            java.lang.String r8 = new java.lang.String
            java.nio.charset.Charset r9 = com.google.android.gms.internal.p007firebaseauthapi.zzzu.zza
            r8.<init>(r3, r4, r6, r9)
            r12.add(r8)
            goto L_0x01cc
        L_0x01f0:
            com.google.android.gms.internal.firebase-auth-api.zzzw r1 = com.google.android.gms.internal.p007firebaseauthapi.zzzw.zzc()
            throw r1
        L_0x01f5:
            com.google.android.gms.internal.firebase-auth-api.zzzw r1 = com.google.android.gms.internal.p007firebaseauthapi.zzzw.zzc()
            throw r1
        L_0x01fa:
            int r4 = com.google.android.gms.internal.p007firebaseauthapi.zzxy.zza(r3, r4, r7)
            int r6 = r7.zza
            if (r6 < 0) goto L_0x0255
            if (r6 != 0) goto L_0x0208
            r12.add(r1)
            goto L_0x021b
        L_0x0208:
            int r8 = r4 + r6
            boolean r9 = com.google.android.gms.internal.p007firebaseauthapi.zzaci.zzb(r3, r4, r8)
            if (r9 == 0) goto L_0x0250
            java.lang.String r9 = new java.lang.String
            java.nio.charset.Charset r10 = com.google.android.gms.internal.p007firebaseauthapi.zzzu.zza
            r9.<init>(r3, r4, r6, r10)
            r12.add(r9)
        L_0x021a:
            r4 = r8
        L_0x021b:
            if (r4 >= r5) goto L_0x044f
            int r6 = com.google.android.gms.internal.p007firebaseauthapi.zzxy.zza(r3, r4, r7)
            int r8 = r7.zza
            if (r2 != r8) goto L_0x044f
            int r4 = com.google.android.gms.internal.p007firebaseauthapi.zzxy.zza(r3, r6, r7)
            int r6 = r7.zza
            if (r6 < 0) goto L_0x024b
            if (r6 != 0) goto L_0x0233
            r12.add(r1)
            goto L_0x021b
        L_0x0233:
            int r8 = r4 + r6
            boolean r9 = com.google.android.gms.internal.p007firebaseauthapi.zzaci.zzb(r3, r4, r8)
            if (r9 == 0) goto L_0x0246
            java.lang.String r9 = new java.lang.String
            java.nio.charset.Charset r10 = com.google.android.gms.internal.p007firebaseauthapi.zzzu.zza
            r9.<init>(r3, r4, r6, r10)
            r12.add(r9)
            goto L_0x021a
        L_0x0246:
            com.google.android.gms.internal.firebase-auth-api.zzzw r1 = com.google.android.gms.internal.p007firebaseauthapi.zzzw.zzi()
            throw r1
        L_0x024b:
            com.google.android.gms.internal.firebase-auth-api.zzzw r1 = com.google.android.gms.internal.p007firebaseauthapi.zzzw.zzc()
            throw r1
        L_0x0250:
            com.google.android.gms.internal.firebase-auth-api.zzzw r1 = com.google.android.gms.internal.p007firebaseauthapi.zzzw.zzi()
            throw r1
        L_0x0255:
            com.google.android.gms.internal.firebase-auth-api.zzzw r1 = com.google.android.gms.internal.p007firebaseauthapi.zzzw.zzc()
            throw r1
        L_0x025a:
            r1 = 0
            if (r6 != r14) goto L_0x0283
            com.google.android.gms.internal.firebase-auth-api.zzxz r12 = (com.google.android.gms.internal.p007firebaseauthapi.zzxz) r12
            int r2 = com.google.android.gms.internal.p007firebaseauthapi.zzxy.zza(r3, r4, r7)
            int r4 = r7.zza
            int r4 = r4 + r2
        L_0x0266:
            if (r2 >= r4) goto L_0x0279
            int r2 = com.google.android.gms.internal.p007firebaseauthapi.zzxy.zzc(r3, r2, r7)
            long r5 = r7.zzb
            int r8 = (r5 > r10 ? 1 : (r5 == r10 ? 0 : -1))
            if (r8 == 0) goto L_0x0274
            r5 = 1
            goto L_0x0275
        L_0x0274:
            r5 = 0
        L_0x0275:
            r12.zzd(r5)
            goto L_0x0266
        L_0x0279:
            if (r2 != r4) goto L_0x027e
        L_0x027b:
            r1 = r2
            goto L_0x0450
        L_0x027e:
            com.google.android.gms.internal.firebase-auth-api.zzzw r1 = com.google.android.gms.internal.p007firebaseauthapi.zzzw.zzb()
            throw r1
        L_0x0283:
            if (r6 != 0) goto L_0x044f
            com.google.android.gms.internal.firebase-auth-api.zzxz r12 = (com.google.android.gms.internal.p007firebaseauthapi.zzxz) r12
            int r4 = com.google.android.gms.internal.p007firebaseauthapi.zzxy.zzc(r3, r4, r7)
            long r8 = r7.zzb
            int r6 = (r8 > r10 ? 1 : (r8 == r10 ? 0 : -1))
            if (r6 == 0) goto L_0x0293
            r6 = 1
            goto L_0x0294
        L_0x0293:
            r6 = 0
        L_0x0294:
            r12.zzd(r6)
        L_0x0297:
            if (r4 >= r5) goto L_0x02b3
            int r6 = com.google.android.gms.internal.p007firebaseauthapi.zzxy.zza(r3, r4, r7)
            int r8 = r7.zza
            if (r2 == r8) goto L_0x02a2
            goto L_0x02b3
        L_0x02a2:
            int r4 = com.google.android.gms.internal.p007firebaseauthapi.zzxy.zzc(r3, r6, r7)
            long r8 = r7.zzb
            int r6 = (r8 > r10 ? 1 : (r8 == r10 ? 0 : -1))
            if (r6 == 0) goto L_0x02ae
            r6 = 1
            goto L_0x02af
        L_0x02ae:
            r6 = 0
        L_0x02af:
            r12.zzd(r6)
            goto L_0x0297
        L_0x02b3:
            return r4
        L_0x02b4:
            if (r6 != r14) goto L_0x02d4
            com.google.android.gms.internal.firebase-auth-api.zzzp r12 = (com.google.android.gms.internal.p007firebaseauthapi.zzzp) r12
            int r1 = com.google.android.gms.internal.p007firebaseauthapi.zzxy.zza(r3, r4, r7)
            int r2 = r7.zza
            int r2 = r2 + r1
        L_0x02bf:
            if (r1 >= r2) goto L_0x02cb
            int r4 = com.google.android.gms.internal.p007firebaseauthapi.zzxy.zzd(r3, r1)
            r12.zzf(r4)
            int r1 = r1 + 4
            goto L_0x02bf
        L_0x02cb:
            if (r1 != r2) goto L_0x02cf
            goto L_0x0450
        L_0x02cf:
            com.google.android.gms.internal.firebase-auth-api.zzzw r1 = com.google.android.gms.internal.p007firebaseauthapi.zzzw.zzb()
            throw r1
        L_0x02d4:
            if (r6 != r9) goto L_0x044f
            com.google.android.gms.internal.firebase-auth-api.zzzp r12 = (com.google.android.gms.internal.p007firebaseauthapi.zzzp) r12
            int r1 = com.google.android.gms.internal.p007firebaseauthapi.zzxy.zzd(r17, r18)
            r12.zzf(r1)
        L_0x02df:
            int r1 = r4 + 4
            if (r1 >= r5) goto L_0x02f4
            int r4 = com.google.android.gms.internal.p007firebaseauthapi.zzxy.zza(r3, r1, r7)
            int r6 = r7.zza
            if (r2 == r6) goto L_0x02ec
            goto L_0x02f4
        L_0x02ec:
            int r1 = com.google.android.gms.internal.p007firebaseauthapi.zzxy.zzd(r3, r4)
            r12.zzf(r1)
            goto L_0x02df
        L_0x02f4:
            return r1
        L_0x02f5:
            if (r6 != r14) goto L_0x0315
            com.google.android.gms.internal.firebase-auth-api.zzaag r12 = (com.google.android.gms.internal.p007firebaseauthapi.zzaag) r12
            int r1 = com.google.android.gms.internal.p007firebaseauthapi.zzxy.zza(r3, r4, r7)
            int r2 = r7.zza
            int r2 = r2 + r1
        L_0x0300:
            if (r1 >= r2) goto L_0x030c
            long r4 = com.google.android.gms.internal.p007firebaseauthapi.zzxy.zze(r3, r1)
            r12.zzf(r4)
            int r1 = r1 + 8
            goto L_0x0300
        L_0x030c:
            if (r1 != r2) goto L_0x0310
            goto L_0x0450
        L_0x0310:
            com.google.android.gms.internal.firebase-auth-api.zzzw r1 = com.google.android.gms.internal.p007firebaseauthapi.zzzw.zzb()
            throw r1
        L_0x0315:
            if (r6 != r13) goto L_0x044f
            com.google.android.gms.internal.firebase-auth-api.zzaag r12 = (com.google.android.gms.internal.p007firebaseauthapi.zzaag) r12
            long r8 = com.google.android.gms.internal.p007firebaseauthapi.zzxy.zze(r17, r18)
            r12.zzf(r8)
        L_0x0320:
            int r1 = r4 + 8
            if (r1 >= r5) goto L_0x0335
            int r4 = com.google.android.gms.internal.p007firebaseauthapi.zzxy.zza(r3, r1, r7)
            int r6 = r7.zza
            if (r2 == r6) goto L_0x032d
            goto L_0x0335
        L_0x032d:
            long r8 = com.google.android.gms.internal.p007firebaseauthapi.zzxy.zze(r3, r4)
            r12.zzf(r8)
            goto L_0x0320
        L_0x0335:
            return r1
        L_0x0336:
            if (r6 != r14) goto L_0x033e
            int r1 = com.google.android.gms.internal.p007firebaseauthapi.zzxy.zzl(r3, r4, r12, r7)
            goto L_0x0450
        L_0x033e:
            if (r6 == 0) goto L_0x0342
            goto L_0x044f
        L_0x0342:
            r21 = r17
            r22 = r18
            r23 = r19
            r24 = r12
            r25 = r29
            int r1 = com.google.android.gms.internal.p007firebaseauthapi.zzxy.zzk(r20, r21, r22, r23, r24, r25)
            return r1
        L_0x0351:
            if (r6 != r14) goto L_0x0371
            com.google.android.gms.internal.firebase-auth-api.zzaag r12 = (com.google.android.gms.internal.p007firebaseauthapi.zzaag) r12
            int r1 = com.google.android.gms.internal.p007firebaseauthapi.zzxy.zza(r3, r4, r7)
            int r2 = r7.zza
            int r2 = r2 + r1
        L_0x035c:
            if (r1 >= r2) goto L_0x0368
            int r1 = com.google.android.gms.internal.p007firebaseauthapi.zzxy.zzc(r3, r1, r7)
            long r4 = r7.zzb
            r12.zzf(r4)
            goto L_0x035c
        L_0x0368:
            if (r1 != r2) goto L_0x036c
            goto L_0x0450
        L_0x036c:
            com.google.android.gms.internal.firebase-auth-api.zzzw r1 = com.google.android.gms.internal.p007firebaseauthapi.zzzw.zzb()
            throw r1
        L_0x0371:
            if (r6 != 0) goto L_0x044f
            com.google.android.gms.internal.firebase-auth-api.zzaag r12 = (com.google.android.gms.internal.p007firebaseauthapi.zzaag) r12
            int r1 = com.google.android.gms.internal.p007firebaseauthapi.zzxy.zzc(r3, r4, r7)
            long r8 = r7.zzb
            r12.zzf(r8)
        L_0x037e:
            if (r1 >= r5) goto L_0x0393
            int r4 = com.google.android.gms.internal.p007firebaseauthapi.zzxy.zza(r3, r1, r7)
            int r6 = r7.zza
            if (r2 == r6) goto L_0x0389
            goto L_0x0393
        L_0x0389:
            int r1 = com.google.android.gms.internal.p007firebaseauthapi.zzxy.zzc(r3, r4, r7)
            long r8 = r7.zzb
            r12.zzf(r8)
            goto L_0x037e
        L_0x0393:
            return r1
        L_0x0394:
            if (r6 != r14) goto L_0x03b8
            com.google.android.gms.internal.firebase-auth-api.zzzi r12 = (com.google.android.gms.internal.p007firebaseauthapi.zzzi) r12
            int r1 = com.google.android.gms.internal.p007firebaseauthapi.zzxy.zza(r3, r4, r7)
            int r2 = r7.zza
            int r2 = r2 + r1
        L_0x039f:
            if (r1 >= r2) goto L_0x03af
            int r4 = com.google.android.gms.internal.p007firebaseauthapi.zzxy.zzd(r3, r1)
            float r4 = java.lang.Float.intBitsToFloat(r4)
            r12.zzd(r4)
            int r1 = r1 + 4
            goto L_0x039f
        L_0x03af:
            if (r1 != r2) goto L_0x03b3
            goto L_0x0450
        L_0x03b3:
            com.google.android.gms.internal.firebase-auth-api.zzzw r1 = com.google.android.gms.internal.p007firebaseauthapi.zzzw.zzb()
            throw r1
        L_0x03b8:
            if (r6 != r9) goto L_0x044f
            com.google.android.gms.internal.firebase-auth-api.zzzi r12 = (com.google.android.gms.internal.p007firebaseauthapi.zzzi) r12
            int r1 = com.google.android.gms.internal.p007firebaseauthapi.zzxy.zzd(r17, r18)
            float r1 = java.lang.Float.intBitsToFloat(r1)
            r12.zzd(r1)
        L_0x03c7:
            int r1 = r4 + 4
            if (r1 >= r5) goto L_0x03e0
            int r4 = com.google.android.gms.internal.p007firebaseauthapi.zzxy.zza(r3, r1, r7)
            int r6 = r7.zza
            if (r2 == r6) goto L_0x03d4
            goto L_0x03e0
        L_0x03d4:
            int r1 = com.google.android.gms.internal.p007firebaseauthapi.zzxy.zzd(r3, r4)
            float r1 = java.lang.Float.intBitsToFloat(r1)
            r12.zzd(r1)
            goto L_0x03c7
        L_0x03e0:
            return r1
        L_0x03e1:
            if (r6 != r14) goto L_0x0404
            com.google.android.gms.internal.firebase-auth-api.zzyy r12 = (com.google.android.gms.internal.p007firebaseauthapi.zzyy) r12
            int r1 = com.google.android.gms.internal.p007firebaseauthapi.zzxy.zza(r3, r4, r7)
            int r2 = r7.zza
            int r2 = r2 + r1
        L_0x03ec:
            if (r1 >= r2) goto L_0x03fc
            long r4 = com.google.android.gms.internal.p007firebaseauthapi.zzxy.zze(r3, r1)
            double r4 = java.lang.Double.longBitsToDouble(r4)
            r12.zzd(r4)
            int r1 = r1 + 8
            goto L_0x03ec
        L_0x03fc:
            if (r1 != r2) goto L_0x03ff
            goto L_0x0450
        L_0x03ff:
            com.google.android.gms.internal.firebase-auth-api.zzzw r1 = com.google.android.gms.internal.p007firebaseauthapi.zzzw.zzb()
            throw r1
        L_0x0404:
            if (r6 != r13) goto L_0x044f
            com.google.android.gms.internal.firebase-auth-api.zzyy r12 = (com.google.android.gms.internal.p007firebaseauthapi.zzyy) r12
            long r8 = com.google.android.gms.internal.p007firebaseauthapi.zzxy.zze(r17, r18)
            double r8 = java.lang.Double.longBitsToDouble(r8)
            r12.zzd(r8)
        L_0x0413:
            int r1 = r4 + 8
            if (r1 >= r5) goto L_0x042c
            int r4 = com.google.android.gms.internal.p007firebaseauthapi.zzxy.zza(r3, r1, r7)
            int r6 = r7.zza
            if (r2 == r6) goto L_0x0420
            goto L_0x042c
        L_0x0420:
            long r8 = com.google.android.gms.internal.p007firebaseauthapi.zzxy.zze(r3, r4)
            double r8 = java.lang.Double.longBitsToDouble(r8)
            r12.zzd(r8)
            goto L_0x0413
        L_0x042c:
            return r1
        L_0x042d:
            if (r4 >= r5) goto L_0x044e
            int r8 = com.google.android.gms.internal.p007firebaseauthapi.zzxy.zza(r3, r4, r7)
            int r9 = r7.zza
            if (r2 == r9) goto L_0x0438
            goto L_0x044e
        L_0x0438:
            r21 = r1
            r22 = r17
            r23 = r8
            r24 = r19
            r25 = r6
            r26 = r29
            int r4 = com.google.android.gms.internal.p007firebaseauthapi.zzxy.zzj(r21, r22, r23, r24, r25, r26)
            java.lang.Object r8 = r7.zzc
            r12.add(r8)
            goto L_0x042d
        L_0x044e:
            return r4
        L_0x044f:
            r1 = r4
        L_0x0450:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.p007firebaseauthapi.zzaau.zzt(java.lang.Object, byte[], int, int, int, int, int, int, long, int, long, com.google.android.gms.internal.firebase-auth-api.zzxx):int");
    }

    private final <K, V> int zzu(T t, byte[] bArr, int i, int i2, int i3, long j, zzxx zzxx) throws IOException {
        Unsafe unsafe = zzb;
        Object zzx = zzx(i3);
        Object object = unsafe.getObject(t, j);
        if (zzaam.zzb(object)) {
            zzaal zzc2 = zzaal.zza().zzc();
            zzaam.zzc(zzc2, object);
            unsafe.putObject(t, j, zzc2);
        }
        zzaak zzaak = (zzaak) zzx;
        throw null;
    }

    private final int zzv(T t, byte[] bArr, int i, int i2, int i3, int i4, int i5, int i6, int i7, long j, int i8, zzxx zzxx) throws IOException {
        T t2 = t;
        byte[] bArr2 = bArr;
        int i9 = i;
        int i10 = i3;
        int i11 = i4;
        int i12 = i5;
        long j2 = j;
        int i13 = i8;
        zzxx zzxx2 = zzxx;
        Unsafe unsafe = zzb;
        long j3 = (long) (this.zzc[i13 + 2] & 1048575);
        switch (i7) {
            case 51:
                if (i12 == 1) {
                    unsafe.putObject(t2, j2, Double.valueOf(Double.longBitsToDouble(zzxy.zze(bArr, i))));
                    unsafe.putInt(t2, j3, i11);
                    return i9 + 8;
                }
                break;
            case 52:
                if (i12 == 5) {
                    unsafe.putObject(t2, j2, Float.valueOf(Float.intBitsToFloat(zzxy.zzd(bArr, i))));
                    unsafe.putInt(t2, j3, i11);
                    return i9 + 4;
                }
                break;
            case 53:
            case 54:
                if (i12 == 0) {
                    int zzc2 = zzxy.zzc(bArr2, i9, zzxx2);
                    unsafe.putObject(t2, j2, Long.valueOf(zzxx2.zzb));
                    unsafe.putInt(t2, j3, i11);
                    return zzc2;
                }
                break;
            case 55:
            case 62:
                if (i12 == 0) {
                    int zza2 = zzxy.zza(bArr2, i9, zzxx2);
                    unsafe.putObject(t2, j2, Integer.valueOf(zzxx2.zza));
                    unsafe.putInt(t2, j3, i11);
                    return zza2;
                }
                break;
            case 56:
            case 65:
                if (i12 == 1) {
                    unsafe.putObject(t2, j2, Long.valueOf(zzxy.zze(bArr, i)));
                    unsafe.putInt(t2, j3, i11);
                    return i9 + 8;
                }
                break;
            case 57:
            case 64:
                if (i12 == 5) {
                    unsafe.putObject(t2, j2, Integer.valueOf(zzxy.zzd(bArr, i)));
                    unsafe.putInt(t2, j3, i11);
                    return i9 + 4;
                }
                break;
            case 58:
                if (i12 == 0) {
                    int zzc3 = zzxy.zzc(bArr2, i9, zzxx2);
                    unsafe.putObject(t2, j2, Boolean.valueOf(zzxx2.zzb != 0));
                    unsafe.putInt(t2, j3, i11);
                    return zzc3;
                }
                break;
            case 59:
                if (i12 == 2) {
                    int zza3 = zzxy.zza(bArr2, i9, zzxx2);
                    int i14 = zzxx2.zza;
                    if (i14 == 0) {
                        unsafe.putObject(t2, j2, "");
                    } else if ((i6 & 536870912) == 0 || zzaci.zzb(bArr2, zza3, zza3 + i14)) {
                        unsafe.putObject(t2, j2, new String(bArr2, zza3, i14, zzzu.zza));
                        zza3 += i14;
                    } else {
                        throw zzzw.zzi();
                    }
                    unsafe.putInt(t2, j3, i11);
                    return zza3;
                }
                break;
            case 60:
                if (i12 == 2) {
                    int zzi2 = zzxy.zzi(zzw(i13), bArr2, i9, i2, zzxx2);
                    Object object = unsafe.getInt(t2, j3) == i11 ? unsafe.getObject(t2, j2) : null;
                    if (object == null) {
                        unsafe.putObject(t2, j2, zzxx2.zzc);
                    } else {
                        unsafe.putObject(t2, j2, zzzu.zzi(object, zzxx2.zzc));
                    }
                    unsafe.putInt(t2, j3, i11);
                    return zzi2;
                }
                break;
            case 61:
                if (i12 == 2) {
                    int zzh2 = zzxy.zzh(bArr2, i9, zzxx2);
                    unsafe.putObject(t2, j2, zzxx2.zzc);
                    unsafe.putInt(t2, j3, i11);
                    return zzh2;
                }
                break;
            case 63:
                if (i12 == 0) {
                    int zza4 = zzxy.zza(bArr2, i9, zzxx2);
                    int i15 = zzxx2.zza;
                    zzzs zzy = zzy(i13);
                    if (zzy == null || zzy.zza()) {
                        unsafe.putObject(t2, j2, Integer.valueOf(i15));
                        unsafe.putInt(t2, j3, i11);
                    } else {
                        zzg(t).zzh(i10, Long.valueOf((long) i15));
                    }
                    return zza4;
                }
                break;
            case 66:
                if (i12 == 0) {
                    int zza5 = zzxy.zza(bArr2, i9, zzxx2);
                    unsafe.putObject(t2, j2, Integer.valueOf(zzyp.zzt(zzxx2.zza)));
                    unsafe.putInt(t2, j3, i11);
                    return zza5;
                }
                break;
            case 67:
                if (i12 == 0) {
                    int zzc4 = zzxy.zzc(bArr2, i9, zzxx2);
                    unsafe.putObject(t2, j2, Long.valueOf(zzyp.zzu(zzxx2.zzb)));
                    unsafe.putInt(t2, j3, i11);
                    return zzc4;
                }
                break;
            case 68:
                if (i12 == 3) {
                    int zzj2 = zzxy.zzj(zzw(i13), bArr, i, i2, (i10 & -8) | 4, zzxx);
                    Object object2 = unsafe.getInt(t2, j3) == i11 ? unsafe.getObject(t2, j2) : null;
                    if (object2 == null) {
                        unsafe.putObject(t2, j2, zzxx2.zzc);
                    } else {
                        unsafe.putObject(t2, j2, zzzu.zzi(object2, zzxx2.zzc));
                    }
                    unsafe.putInt(t2, j3, i11);
                    return zzj2;
                }
                break;
        }
        return i9;
    }

    private final zzabd zzw(int i) {
        int i2 = i / 3;
        int i3 = i2 + i2;
        zzabd zzabd = (zzabd) this.zzd[i3];
        if (zzabd != null) {
            return zzabd;
        }
        zzabd zzb2 = zzaaz.zza().zzb((Class) this.zzd[i3 + 1]);
        this.zzd[i3] = zzb2;
        return zzb2;
    }

    private final Object zzx(int i) {
        int i2 = i / 3;
        return this.zzd[i2 + i2];
    }

    private final zzzs zzy(int i) {
        int i2 = i / 3;
        return (zzzs) this.zzd[i2 + i2 + 1];
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r17v0, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v7, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r17v4, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r17v5, resolved type: byte} */
    /* JADX WARNING: Code restructure failed: missing block: B:101:0x02a8, code lost:
        if (r0 != r15) goto L_0x02aa;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:103:0x02be, code lost:
        r2 = r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:109:0x02f1, code lost:
        if (r0 != r15) goto L_0x02aa;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:113:0x0314, code lost:
        if (r0 != r15) goto L_0x02aa;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:64:0x017d, code lost:
        r6 = r6 | r21;
        r9 = r10;
        r1 = r20;
        r8 = 1048575;
        r10 = -1;
        r29 = r13;
        r13 = r2;
        r2 = r29;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:74:0x01d2, code lost:
        r6 = r6 | r21;
        r9 = r10;
        r2 = r13;
        r0 = r17;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:81:0x0207, code lost:
        r6 = r6 | r21;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:82:0x020a, code lost:
        r2 = r4;
        r28 = r10;
        r19 = r13;
     */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final int zzz(T r31, byte[] r32, int r33, int r34, com.google.android.gms.internal.p007firebaseauthapi.zzxx r35) throws java.io.IOException {
        /*
            r30 = this;
            r15 = r30
            r14 = r31
            r12 = r32
            r13 = r34
            r11 = r35
            sun.misc.Unsafe r9 = zzb
            r10 = -1
            r16 = 0
            r8 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r33
            r1 = -1
            r2 = 0
            r6 = 0
            r7 = 1048575(0xfffff, float:1.469367E-39)
        L_0x001a:
            if (r0 >= r13) goto L_0x033d
            int r3 = r0 + 1
            byte r0 = r12[r0]
            if (r0 >= 0) goto L_0x002c
            int r0 = com.google.android.gms.internal.p007firebaseauthapi.zzxy.zzb(r0, r12, r3, r11)
            int r3 = r11.zza
            r4 = r0
            r17 = r3
            goto L_0x002f
        L_0x002c:
            r17 = r0
            r4 = r3
        L_0x002f:
            int r5 = r17 >>> 3
            r3 = r17 & 7
            if (r5 <= r1) goto L_0x003c
            int r2 = r2 / 3
            int r0 = r15.zzT(r5, r2)
            goto L_0x0040
        L_0x003c:
            int r0 = r15.zzS(r5)
        L_0x0040:
            r2 = r0
            if (r2 != r10) goto L_0x004e
            r2 = r4
            r20 = r5
            r28 = r9
            r18 = -1
            r19 = 0
            goto L_0x0317
        L_0x004e:
            int[] r0 = r15.zzc
            int r1 = r2 + 1
            r1 = r0[r1]
            int r0 = zzF(r1)
            r10 = r1 & r8
            r19 = r9
            long r8 = (long) r10
            r10 = 17
            r33 = r5
            if (r0 > r10) goto L_0x0213
            int[] r10 = r15.zzc
            int r21 = r2 + 2
            r10 = r10[r21]
            int r21 = r10 >>> 20
            r5 = 1
            int r21 = r5 << r21
            r13 = 1048575(0xfffff, float:1.469367E-39)
            r10 = r10 & r13
            if (r10 == r7) goto L_0x0090
            r23 = r1
            r20 = r2
            if (r7 == r13) goto L_0x0081
            long r1 = (long) r7
            r7 = r19
            r7.putInt(r14, r1, r6)
            goto L_0x0083
        L_0x0081:
            r7 = r19
        L_0x0083:
            if (r10 == r13) goto L_0x008a
            long r1 = (long) r10
            int r6 = r7.getInt(r14, r1)
        L_0x008a:
            r29 = r10
            r10 = r7
            r7 = r29
            goto L_0x0096
        L_0x0090:
            r23 = r1
            r20 = r2
            r10 = r19
        L_0x0096:
            r1 = 5
            switch(r0) {
                case 0: goto L_0x01f1;
                case 1: goto L_0x01da;
                case 2: goto L_0x01bc;
                case 3: goto L_0x01bc;
                case 4: goto L_0x01a9;
                case 5: goto L_0x018d;
                case 6: goto L_0x0169;
                case 7: goto L_0x014c;
                case 8: goto L_0x012b;
                case 9: goto L_0x00f9;
                case 10: goto L_0x00e7;
                case 11: goto L_0x01a9;
                case 12: goto L_0x00d6;
                case 13: goto L_0x0169;
                case 14: goto L_0x018d;
                case 15: goto L_0x00c1;
                case 16: goto L_0x00a3;
                default: goto L_0x009a;
            }
        L_0x009a:
            r13 = r20
            r19 = 1048575(0xfffff, float:1.469367E-39)
            r20 = r33
            goto L_0x020a
        L_0x00a3:
            if (r3 != 0) goto L_0x00bc
            int r17 = com.google.android.gms.internal.p007firebaseauthapi.zzxy.zzc(r12, r4, r11)
            long r0 = r11.zzb
            long r4 = com.google.android.gms.internal.p007firebaseauthapi.zzyp.zzu(r0)
            r0 = r10
            r1 = r31
            r13 = r20
            r2 = r8
            r20 = r33
            r0.putLong(r1, r2, r4)
            goto L_0x01d2
        L_0x00bc:
            r13 = r20
            r20 = r33
            goto L_0x0126
        L_0x00c1:
            r13 = r20
            r20 = r33
            if (r3 != 0) goto L_0x0126
            int r0 = com.google.android.gms.internal.p007firebaseauthapi.zzxy.zza(r12, r4, r11)
            int r1 = r11.zza
            int r1 = com.google.android.gms.internal.p007firebaseauthapi.zzyp.zzt(r1)
            r10.putInt(r14, r8, r1)
            goto L_0x0207
        L_0x00d6:
            r13 = r20
            r20 = r33
            if (r3 != 0) goto L_0x0126
            int r0 = com.google.android.gms.internal.p007firebaseauthapi.zzxy.zza(r12, r4, r11)
            int r1 = r11.zza
            r10.putInt(r14, r8, r1)
            goto L_0x0207
        L_0x00e7:
            r13 = r20
            r0 = 2
            r20 = r33
            if (r3 != r0) goto L_0x0126
            int r0 = com.google.android.gms.internal.p007firebaseauthapi.zzxy.zzh(r12, r4, r11)
            java.lang.Object r1 = r11.zzc
            r10.putObject(r14, r8, r1)
            goto L_0x0207
        L_0x00f9:
            r13 = r20
            r0 = 2
            r20 = r33
            if (r3 != r0) goto L_0x0124
            com.google.android.gms.internal.firebase-auth-api.zzabd r0 = r15.zzw(r13)
            r2 = r34
            r19 = 1048575(0xfffff, float:1.469367E-39)
            int r0 = com.google.android.gms.internal.p007firebaseauthapi.zzxy.zzi(r0, r12, r4, r2, r11)
            java.lang.Object r1 = r10.getObject(r14, r8)
            if (r1 != 0) goto L_0x011a
            java.lang.Object r1 = r11.zzc
            r10.putObject(r14, r8, r1)
            goto L_0x017d
        L_0x011a:
            java.lang.Object r3 = r11.zzc
            java.lang.Object r1 = com.google.android.gms.internal.p007firebaseauthapi.zzzu.zzi(r1, r3)
            r10.putObject(r14, r8, r1)
            goto L_0x017d
        L_0x0124:
            r2 = r34
        L_0x0126:
            r19 = 1048575(0xfffff, float:1.469367E-39)
            goto L_0x020a
        L_0x012b:
            r2 = r34
            r13 = r20
            r0 = 2
            r19 = 1048575(0xfffff, float:1.469367E-39)
            r20 = r33
            if (r3 != r0) goto L_0x020a
            r0 = 536870912(0x20000000, float:1.0842022E-19)
            r0 = r23 & r0
            if (r0 != 0) goto L_0x0142
            int r0 = com.google.android.gms.internal.p007firebaseauthapi.zzxy.zzf(r12, r4, r11)
            goto L_0x0146
        L_0x0142:
            int r0 = com.google.android.gms.internal.p007firebaseauthapi.zzxy.zzg(r12, r4, r11)
        L_0x0146:
            java.lang.Object r1 = r11.zzc
            r10.putObject(r14, r8, r1)
            goto L_0x017d
        L_0x014c:
            r2 = r34
            r13 = r20
            r19 = 1048575(0xfffff, float:1.469367E-39)
            r20 = r33
            if (r3 != 0) goto L_0x020a
            int r0 = com.google.android.gms.internal.p007firebaseauthapi.zzxy.zzc(r12, r4, r11)
            long r3 = r11.zzb
            r22 = 0
            int r1 = (r3 > r22 ? 1 : (r3 == r22 ? 0 : -1))
            if (r1 == 0) goto L_0x0164
            goto L_0x0165
        L_0x0164:
            r5 = 0
        L_0x0165:
            com.google.android.gms.internal.p007firebaseauthapi.zzacc.zzi(r14, r8, r5)
            goto L_0x017d
        L_0x0169:
            r2 = r34
            r13 = r20
            r19 = 1048575(0xfffff, float:1.469367E-39)
            r20 = r33
            if (r3 != r1) goto L_0x020a
            int r0 = com.google.android.gms.internal.p007firebaseauthapi.zzxy.zzd(r12, r4)
            r10.putInt(r14, r8, r0)
            int r0 = r4 + 4
        L_0x017d:
            r6 = r6 | r21
            r9 = r10
            r1 = r20
            r8 = 1048575(0xfffff, float:1.469367E-39)
            r10 = -1
            r29 = r13
            r13 = r2
            r2 = r29
            goto L_0x001a
        L_0x018d:
            r2 = r34
            r13 = r20
            r19 = 1048575(0xfffff, float:1.469367E-39)
            r20 = r33
            if (r3 != r5) goto L_0x020a
            long r22 = com.google.android.gms.internal.p007firebaseauthapi.zzxy.zze(r12, r4)
            r0 = r10
            r1 = r31
            r2 = r8
            r8 = r4
            r4 = r22
            r0.putLong(r1, r2, r4)
            int r0 = r8 + 8
            goto L_0x0207
        L_0x01a9:
            r13 = r20
            r19 = 1048575(0xfffff, float:1.469367E-39)
            r20 = r33
            if (r3 != 0) goto L_0x020a
            int r0 = com.google.android.gms.internal.p007firebaseauthapi.zzxy.zza(r12, r4, r11)
            int r1 = r11.zza
            r10.putInt(r14, r8, r1)
            goto L_0x0207
        L_0x01bc:
            r13 = r20
            r19 = 1048575(0xfffff, float:1.469367E-39)
            r20 = r33
            if (r3 != 0) goto L_0x020a
            int r17 = com.google.android.gms.internal.p007firebaseauthapi.zzxy.zzc(r12, r4, r11)
            long r4 = r11.zzb
            r0 = r10
            r1 = r31
            r2 = r8
            r0.putLong(r1, r2, r4)
        L_0x01d2:
            r6 = r6 | r21
            r9 = r10
            r2 = r13
            r0 = r17
            goto L_0x0257
        L_0x01da:
            r13 = r20
            r19 = 1048575(0xfffff, float:1.469367E-39)
            r20 = r33
            if (r3 != r1) goto L_0x020a
            int r0 = com.google.android.gms.internal.p007firebaseauthapi.zzxy.zzd(r12, r4)
            float r0 = java.lang.Float.intBitsToFloat(r0)
            com.google.android.gms.internal.p007firebaseauthapi.zzacc.zzk(r14, r8, r0)
            int r0 = r4 + 4
            goto L_0x0207
        L_0x01f1:
            r13 = r20
            r19 = 1048575(0xfffff, float:1.469367E-39)
            r20 = r33
            if (r3 != r5) goto L_0x020a
            long r0 = com.google.android.gms.internal.p007firebaseauthapi.zzxy.zze(r12, r4)
            double r0 = java.lang.Double.longBitsToDouble(r0)
            com.google.android.gms.internal.p007firebaseauthapi.zzacc.zzm(r14, r8, r0)
            int r0 = r4 + 8
        L_0x0207:
            r6 = r6 | r21
            goto L_0x0255
        L_0x020a:
            r2 = r4
            r28 = r10
            r19 = r13
            r18 = -1
            goto L_0x0317
        L_0x0213:
            r20 = r33
            r23 = r1
            r13 = r2
            r10 = r19
            r19 = 1048575(0xfffff, float:1.469367E-39)
            r1 = 27
            if (r0 != r1) goto L_0x026e
            r1 = 2
            if (r3 != r1) goto L_0x0261
            java.lang.Object r0 = r10.getObject(r14, r8)
            com.google.android.gms.internal.firebase-auth-api.zzzt r0 = (com.google.android.gms.internal.p007firebaseauthapi.zzzt) r0
            boolean r1 = r0.zza()
            if (r1 != 0) goto L_0x0241
            int r1 = r0.size()
            if (r1 != 0) goto L_0x0239
            r1 = 10
            goto L_0x023a
        L_0x0239:
            int r1 = r1 + r1
        L_0x023a:
            com.google.android.gms.internal.firebase-auth-api.zzzt r0 = r0.zze(r1)
            r10.putObject(r14, r8, r0)
        L_0x0241:
            r5 = r0
            com.google.android.gms.internal.firebase-auth-api.zzabd r0 = r15.zzw(r13)
            r1 = r17
            r2 = r32
            r3 = r4
            r4 = r34
            r8 = r6
            r6 = r35
            int r0 = com.google.android.gms.internal.p007firebaseauthapi.zzxy.zzm(r0, r1, r2, r3, r4, r5, r6)
            r6 = r8
        L_0x0255:
            r9 = r10
            r2 = r13
        L_0x0257:
            r1 = r20
            r8 = 1048575(0xfffff, float:1.469367E-39)
            r10 = -1
            r13 = r34
            goto L_0x001a
        L_0x0261:
            r15 = r4
            r24 = r6
            r25 = r7
            r28 = r10
            r19 = r13
            r18 = -1
            goto L_0x02f4
        L_0x026e:
            r1 = 49
            if (r0 > r1) goto L_0x02c0
            r1 = r23
            long r1 = (long) r1
            r5 = r0
            r0 = r30
            r21 = r1
            r1 = r31
            r2 = r32
            r33 = r3
            r3 = r4
            r15 = r4
            r4 = r34
            r23 = r5
            r5 = r17
            r24 = r6
            r6 = r20
            r25 = r7
            r7 = r33
            r26 = r8
            r9 = 1048575(0xfffff, float:1.469367E-39)
            r8 = r13
            r28 = r10
            r18 = -1
            r9 = r21
            r11 = r23
            r19 = r13
            r12 = r26
            r14 = r35
            int r0 = r0.zzt(r1, r2, r3, r4, r5, r6, r7, r8, r9, r11, r12, r14)
            if (r0 == r15) goto L_0x02be
        L_0x02aa:
            r15 = r30
            r14 = r31
            r12 = r32
            r13 = r34
            r11 = r35
            r2 = r19
            r1 = r20
            r6 = r24
            r7 = r25
            goto L_0x0335
        L_0x02be:
            r2 = r0
            goto L_0x02f5
        L_0x02c0:
            r33 = r3
            r15 = r4
            r24 = r6
            r25 = r7
            r26 = r8
            r28 = r10
            r19 = r13
            r1 = r23
            r18 = -1
            r23 = r0
            r0 = 50
            r9 = r23
            if (r9 != r0) goto L_0x02fa
            r7 = r33
            r0 = 2
            if (r7 != r0) goto L_0x02f4
            r0 = r30
            r1 = r31
            r2 = r32
            r3 = r15
            r4 = r34
            r5 = r19
            r6 = r26
            r8 = r35
            int r0 = r0.zzu(r1, r2, r3, r4, r5, r6, r8)
            if (r0 == r15) goto L_0x02be
            goto L_0x02aa
        L_0x02f4:
            r2 = r15
        L_0x02f5:
            r6 = r24
            r7 = r25
            goto L_0x0317
        L_0x02fa:
            r7 = r33
            r0 = r30
            r8 = r1
            r1 = r31
            r2 = r32
            r3 = r15
            r4 = r34
            r5 = r17
            r6 = r20
            r10 = r26
            r12 = r19
            r13 = r35
            int r0 = r0.zzv(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r12, r13)
            if (r0 == r15) goto L_0x02be
            goto L_0x02aa
        L_0x0317:
            com.google.android.gms.internal.firebase-auth-api.zzabs r4 = zzg(r31)
            r0 = r17
            r1 = r32
            r3 = r34
            r5 = r35
            int r0 = com.google.android.gms.internal.p007firebaseauthapi.zzxy.zzn(r0, r1, r2, r3, r4, r5)
            r15 = r30
            r14 = r31
            r12 = r32
            r13 = r34
            r11 = r35
            r2 = r19
            r1 = r20
        L_0x0335:
            r9 = r28
            r8 = 1048575(0xfffff, float:1.469367E-39)
            r10 = -1
            goto L_0x001a
        L_0x033d:
            r24 = r6
            r28 = r9
            r1 = 1048575(0xfffff, float:1.469367E-39)
            if (r7 == r1) goto L_0x0350
            long r1 = (long) r7
            r3 = r31
            r6 = r24
            r4 = r28
            r4.putInt(r3, r1, r6)
        L_0x0350:
            r1 = r34
            if (r0 != r1) goto L_0x0355
            return r0
        L_0x0355:
            com.google.android.gms.internal.firebase-auth-api.zzzw r0 = com.google.android.gms.internal.p007firebaseauthapi.zzzw.zzh()
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.p007firebaseauthapi.zzaau.zzz(java.lang.Object, byte[], int, int, com.google.android.gms.internal.firebase-auth-api.zzxx):int");
    }

    public final T zza() {
        return ((zzzo) this.zzg).zzj(4, (Object) null, (Object) null);
    }

    public final boolean zzb(T t, T t2) {
        boolean z;
        int length = this.zzc.length;
        for (int i = 0; i < length; i += 3) {
            int zzD = zzD(i);
            long j = (long) (zzD & 1048575);
            switch (zzF(zzD)) {
                case 0:
                    if (zzM(t, t2, i) && Double.doubleToLongBits(zzacc.zzl(t, j)) == Double.doubleToLongBits(zzacc.zzl(t2, j))) {
                        continue;
                    }
                case 1:
                    if (zzM(t, t2, i) && Float.floatToIntBits(zzacc.zzj(t, j)) == Float.floatToIntBits(zzacc.zzj(t2, j))) {
                        continue;
                    }
                case 2:
                    if (zzM(t, t2, i) && zzacc.zzf(t, j) == zzacc.zzf(t2, j)) {
                        continue;
                    }
                case 3:
                    if (zzM(t, t2, i) && zzacc.zzf(t, j) == zzacc.zzf(t2, j)) {
                        continue;
                    }
                case 4:
                    if (zzM(t, t2, i) && zzacc.zzd(t, j) == zzacc.zzd(t2, j)) {
                        continue;
                    }
                case 5:
                    if (zzM(t, t2, i) && zzacc.zzf(t, j) == zzacc.zzf(t2, j)) {
                        continue;
                    }
                case 6:
                    if (zzM(t, t2, i) && zzacc.zzd(t, j) == zzacc.zzd(t2, j)) {
                        continue;
                    }
                case 7:
                    if (zzM(t, t2, i) && zzacc.zzh(t, j) == zzacc.zzh(t2, j)) {
                        continue;
                    }
                case 8:
                    if (zzM(t, t2, i) && zzabf.zzD(zzacc.zzn(t, j), zzacc.zzn(t2, j))) {
                        continue;
                    }
                case 9:
                    if (zzM(t, t2, i) && zzabf.zzD(zzacc.zzn(t, j), zzacc.zzn(t2, j))) {
                        continue;
                    }
                case 10:
                    if (zzM(t, t2, i) && zzabf.zzD(zzacc.zzn(t, j), zzacc.zzn(t2, j))) {
                        continue;
                    }
                case 11:
                    if (zzM(t, t2, i) && zzacc.zzd(t, j) == zzacc.zzd(t2, j)) {
                        continue;
                    }
                case 12:
                    if (zzM(t, t2, i) && zzacc.zzd(t, j) == zzacc.zzd(t2, j)) {
                        continue;
                    }
                case 13:
                    if (zzM(t, t2, i) && zzacc.zzd(t, j) == zzacc.zzd(t2, j)) {
                        continue;
                    }
                case 14:
                    if (zzM(t, t2, i) && zzacc.zzf(t, j) == zzacc.zzf(t2, j)) {
                        continue;
                    }
                case 15:
                    if (zzM(t, t2, i) && zzacc.zzd(t, j) == zzacc.zzd(t2, j)) {
                        continue;
                    }
                case 16:
                    if (zzM(t, t2, i) && zzacc.zzf(t, j) == zzacc.zzf(t2, j)) {
                        continue;
                    }
                case 17:
                    if (zzM(t, t2, i) && zzabf.zzD(zzacc.zzn(t, j), zzacc.zzn(t2, j))) {
                        continue;
                    }
                case 18:
                case 19:
                case 20:
                case 21:
                case 22:
                case 23:
                case 24:
                case 25:
                case 26:
                case 27:
                case 28:
                case 29:
                case 30:
                case 31:
                case 32:
                case 33:
                case 34:
                case 35:
                case 36:
                case 37:
                case 38:
                case 39:
                case 40:
                case 41:
                case 42:
                case 43:
                case 44:
                case 45:
                case 46:
                case 47:
                case 48:
                case 49:
                    z = zzabf.zzD(zzacc.zzn(t, j), zzacc.zzn(t2, j));
                    break;
                case 50:
                    z = zzabf.zzD(zzacc.zzn(t, j), zzacc.zzn(t2, j));
                    break;
                case 51:
                case 52:
                case 53:
                case 54:
                case 55:
                case 56:
                case 57:
                case 58:
                case 59:
                case 60:
                case 61:
                case 62:
                case 63:
                case 64:
                case 65:
                case 66:
                case 67:
                case 68:
                    long zzE = (long) (zzE(i) & 1048575);
                    if (zzacc.zzd(t, zzE) == zzacc.zzd(t2, zzE) && zzabf.zzD(zzacc.zzn(t, j), zzacc.zzn(t2, j))) {
                        continue;
                    }
            }
            if (!z) {
                return false;
            }
        }
        if (!this.zzo.zzj(t).equals(this.zzo.zzj(t2))) {
            return false;
        }
        if (!this.zzh) {
            return true;
        }
        this.zzp.zzb(t);
        this.zzp.zzb(t2);
        throw null;
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Code restructure failed: missing block: B:73:0x01c2, code lost:
        r2 = (r2 * 53) + r7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:83:0x0226, code lost:
        r2 = r2 + r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:84:0x0227, code lost:
        r1 = r1 + 3;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final int zzc(T r9) {
        /*
            r8 = this;
            int[] r0 = r8.zzc
            int r0 = r0.length
            r1 = 0
            r2 = 0
        L_0x0005:
            if (r1 >= r0) goto L_0x022b
            int r3 = r8.zzD(r1)
            int[] r4 = r8.zzc
            r4 = r4[r1]
            r5 = 1048575(0xfffff, float:1.469367E-39)
            r5 = r5 & r3
            long r5 = (long) r5
            int r3 = zzF(r3)
            r7 = 37
            switch(r3) {
                case 0: goto L_0x0218;
                case 1: goto L_0x020d;
                case 2: goto L_0x0202;
                case 3: goto L_0x01f7;
                case 4: goto L_0x01f0;
                case 5: goto L_0x01e5;
                case 6: goto L_0x01de;
                case 7: goto L_0x01d3;
                case 8: goto L_0x01c6;
                case 9: goto L_0x01b8;
                case 10: goto L_0x01ac;
                case 11: goto L_0x01a4;
                case 12: goto L_0x019c;
                case 13: goto L_0x0194;
                case 14: goto L_0x0188;
                case 15: goto L_0x0180;
                case 16: goto L_0x0174;
                case 17: goto L_0x0169;
                case 18: goto L_0x015d;
                case 19: goto L_0x015d;
                case 20: goto L_0x015d;
                case 21: goto L_0x015d;
                case 22: goto L_0x015d;
                case 23: goto L_0x015d;
                case 24: goto L_0x015d;
                case 25: goto L_0x015d;
                case 26: goto L_0x015d;
                case 27: goto L_0x015d;
                case 28: goto L_0x015d;
                case 29: goto L_0x015d;
                case 30: goto L_0x015d;
                case 31: goto L_0x015d;
                case 32: goto L_0x015d;
                case 33: goto L_0x015d;
                case 34: goto L_0x015d;
                case 35: goto L_0x015d;
                case 36: goto L_0x015d;
                case 37: goto L_0x015d;
                case 38: goto L_0x015d;
                case 39: goto L_0x015d;
                case 40: goto L_0x015d;
                case 41: goto L_0x015d;
                case 42: goto L_0x015d;
                case 43: goto L_0x015d;
                case 44: goto L_0x015d;
                case 45: goto L_0x015d;
                case 46: goto L_0x015d;
                case 47: goto L_0x015d;
                case 48: goto L_0x015d;
                case 49: goto L_0x015d;
                case 50: goto L_0x0151;
                case 51: goto L_0x013b;
                case 52: goto L_0x0129;
                case 53: goto L_0x0117;
                case 54: goto L_0x0105;
                case 55: goto L_0x00f7;
                case 56: goto L_0x00e5;
                case 57: goto L_0x00d7;
                case 58: goto L_0x00c5;
                case 59: goto L_0x00b1;
                case 60: goto L_0x009f;
                case 61: goto L_0x008d;
                case 62: goto L_0x007f;
                case 63: goto L_0x0071;
                case 64: goto L_0x0063;
                case 65: goto L_0x0051;
                case 66: goto L_0x0043;
                case 67: goto L_0x0031;
                case 68: goto L_0x001f;
                default: goto L_0x001d;
            }
        L_0x001d:
            goto L_0x0227
        L_0x001f:
            boolean r3 = r8.zzQ(r9, r4, r1)
            if (r3 == 0) goto L_0x0227
            int r2 = r2 * 53
            java.lang.Object r3 = com.google.android.gms.internal.p007firebaseauthapi.zzacc.zzn(r9, r5)
            int r3 = r3.hashCode()
            goto L_0x0226
        L_0x0031:
            boolean r3 = r8.zzQ(r9, r4, r1)
            if (r3 == 0) goto L_0x0227
            int r2 = r2 * 53
            long r3 = zzK(r9, r5)
            int r3 = com.google.android.gms.internal.p007firebaseauthapi.zzzu.zze(r3)
            goto L_0x0226
        L_0x0043:
            boolean r3 = r8.zzQ(r9, r4, r1)
            if (r3 == 0) goto L_0x0227
            int r2 = r2 * 53
            int r3 = zzJ(r9, r5)
            goto L_0x0226
        L_0x0051:
            boolean r3 = r8.zzQ(r9, r4, r1)
            if (r3 == 0) goto L_0x0227
            int r2 = r2 * 53
            long r3 = zzK(r9, r5)
            int r3 = com.google.android.gms.internal.p007firebaseauthapi.zzzu.zze(r3)
            goto L_0x0226
        L_0x0063:
            boolean r3 = r8.zzQ(r9, r4, r1)
            if (r3 == 0) goto L_0x0227
            int r2 = r2 * 53
            int r3 = zzJ(r9, r5)
            goto L_0x0226
        L_0x0071:
            boolean r3 = r8.zzQ(r9, r4, r1)
            if (r3 == 0) goto L_0x0227
            int r2 = r2 * 53
            int r3 = zzJ(r9, r5)
            goto L_0x0226
        L_0x007f:
            boolean r3 = r8.zzQ(r9, r4, r1)
            if (r3 == 0) goto L_0x0227
            int r2 = r2 * 53
            int r3 = zzJ(r9, r5)
            goto L_0x0226
        L_0x008d:
            boolean r3 = r8.zzQ(r9, r4, r1)
            if (r3 == 0) goto L_0x0227
            int r2 = r2 * 53
            java.lang.Object r3 = com.google.android.gms.internal.p007firebaseauthapi.zzacc.zzn(r9, r5)
            int r3 = r3.hashCode()
            goto L_0x0226
        L_0x009f:
            boolean r3 = r8.zzQ(r9, r4, r1)
            if (r3 == 0) goto L_0x0227
            int r2 = r2 * 53
            java.lang.Object r3 = com.google.android.gms.internal.p007firebaseauthapi.zzacc.zzn(r9, r5)
            int r3 = r3.hashCode()
            goto L_0x0226
        L_0x00b1:
            boolean r3 = r8.zzQ(r9, r4, r1)
            if (r3 == 0) goto L_0x0227
            int r2 = r2 * 53
            java.lang.Object r3 = com.google.android.gms.internal.p007firebaseauthapi.zzacc.zzn(r9, r5)
            java.lang.String r3 = (java.lang.String) r3
            int r3 = r3.hashCode()
            goto L_0x0226
        L_0x00c5:
            boolean r3 = r8.zzQ(r9, r4, r1)
            if (r3 == 0) goto L_0x0227
            int r2 = r2 * 53
            boolean r3 = zzL(r9, r5)
            int r3 = com.google.android.gms.internal.p007firebaseauthapi.zzzu.zzf(r3)
            goto L_0x0226
        L_0x00d7:
            boolean r3 = r8.zzQ(r9, r4, r1)
            if (r3 == 0) goto L_0x0227
            int r2 = r2 * 53
            int r3 = zzJ(r9, r5)
            goto L_0x0226
        L_0x00e5:
            boolean r3 = r8.zzQ(r9, r4, r1)
            if (r3 == 0) goto L_0x0227
            int r2 = r2 * 53
            long r3 = zzK(r9, r5)
            int r3 = com.google.android.gms.internal.p007firebaseauthapi.zzzu.zze(r3)
            goto L_0x0226
        L_0x00f7:
            boolean r3 = r8.zzQ(r9, r4, r1)
            if (r3 == 0) goto L_0x0227
            int r2 = r2 * 53
            int r3 = zzJ(r9, r5)
            goto L_0x0226
        L_0x0105:
            boolean r3 = r8.zzQ(r9, r4, r1)
            if (r3 == 0) goto L_0x0227
            int r2 = r2 * 53
            long r3 = zzK(r9, r5)
            int r3 = com.google.android.gms.internal.p007firebaseauthapi.zzzu.zze(r3)
            goto L_0x0226
        L_0x0117:
            boolean r3 = r8.zzQ(r9, r4, r1)
            if (r3 == 0) goto L_0x0227
            int r2 = r2 * 53
            long r3 = zzK(r9, r5)
            int r3 = com.google.android.gms.internal.p007firebaseauthapi.zzzu.zze(r3)
            goto L_0x0226
        L_0x0129:
            boolean r3 = r8.zzQ(r9, r4, r1)
            if (r3 == 0) goto L_0x0227
            int r2 = r2 * 53
            float r3 = zzI(r9, r5)
            int r3 = java.lang.Float.floatToIntBits(r3)
            goto L_0x0226
        L_0x013b:
            boolean r3 = r8.zzQ(r9, r4, r1)
            if (r3 == 0) goto L_0x0227
            int r2 = r2 * 53
            double r3 = zzH(r9, r5)
            long r3 = java.lang.Double.doubleToLongBits(r3)
            int r3 = com.google.android.gms.internal.p007firebaseauthapi.zzzu.zze(r3)
            goto L_0x0226
        L_0x0151:
            int r2 = r2 * 53
            java.lang.Object r3 = com.google.android.gms.internal.p007firebaseauthapi.zzacc.zzn(r9, r5)
            int r3 = r3.hashCode()
            goto L_0x0226
        L_0x015d:
            int r2 = r2 * 53
            java.lang.Object r3 = com.google.android.gms.internal.p007firebaseauthapi.zzacc.zzn(r9, r5)
            int r3 = r3.hashCode()
            goto L_0x0226
        L_0x0169:
            java.lang.Object r3 = com.google.android.gms.internal.p007firebaseauthapi.zzacc.zzn(r9, r5)
            if (r3 == 0) goto L_0x01c2
            int r7 = r3.hashCode()
            goto L_0x01c2
        L_0x0174:
            int r2 = r2 * 53
            long r3 = com.google.android.gms.internal.p007firebaseauthapi.zzacc.zzf(r9, r5)
            int r3 = com.google.android.gms.internal.p007firebaseauthapi.zzzu.zze(r3)
            goto L_0x0226
        L_0x0180:
            int r2 = r2 * 53
            int r3 = com.google.android.gms.internal.p007firebaseauthapi.zzacc.zzd(r9, r5)
            goto L_0x0226
        L_0x0188:
            int r2 = r2 * 53
            long r3 = com.google.android.gms.internal.p007firebaseauthapi.zzacc.zzf(r9, r5)
            int r3 = com.google.android.gms.internal.p007firebaseauthapi.zzzu.zze(r3)
            goto L_0x0226
        L_0x0194:
            int r2 = r2 * 53
            int r3 = com.google.android.gms.internal.p007firebaseauthapi.zzacc.zzd(r9, r5)
            goto L_0x0226
        L_0x019c:
            int r2 = r2 * 53
            int r3 = com.google.android.gms.internal.p007firebaseauthapi.zzacc.zzd(r9, r5)
            goto L_0x0226
        L_0x01a4:
            int r2 = r2 * 53
            int r3 = com.google.android.gms.internal.p007firebaseauthapi.zzacc.zzd(r9, r5)
            goto L_0x0226
        L_0x01ac:
            int r2 = r2 * 53
            java.lang.Object r3 = com.google.android.gms.internal.p007firebaseauthapi.zzacc.zzn(r9, r5)
            int r3 = r3.hashCode()
            goto L_0x0226
        L_0x01b8:
            java.lang.Object r3 = com.google.android.gms.internal.p007firebaseauthapi.zzacc.zzn(r9, r5)
            if (r3 == 0) goto L_0x01c2
            int r7 = r3.hashCode()
        L_0x01c2:
            int r2 = r2 * 53
            int r2 = r2 + r7
            goto L_0x0227
        L_0x01c6:
            int r2 = r2 * 53
            java.lang.Object r3 = com.google.android.gms.internal.p007firebaseauthapi.zzacc.zzn(r9, r5)
            java.lang.String r3 = (java.lang.String) r3
            int r3 = r3.hashCode()
            goto L_0x0226
        L_0x01d3:
            int r2 = r2 * 53
            boolean r3 = com.google.android.gms.internal.p007firebaseauthapi.zzacc.zzh(r9, r5)
            int r3 = com.google.android.gms.internal.p007firebaseauthapi.zzzu.zzf(r3)
            goto L_0x0226
        L_0x01de:
            int r2 = r2 * 53
            int r3 = com.google.android.gms.internal.p007firebaseauthapi.zzacc.zzd(r9, r5)
            goto L_0x0226
        L_0x01e5:
            int r2 = r2 * 53
            long r3 = com.google.android.gms.internal.p007firebaseauthapi.zzacc.zzf(r9, r5)
            int r3 = com.google.android.gms.internal.p007firebaseauthapi.zzzu.zze(r3)
            goto L_0x0226
        L_0x01f0:
            int r2 = r2 * 53
            int r3 = com.google.android.gms.internal.p007firebaseauthapi.zzacc.zzd(r9, r5)
            goto L_0x0226
        L_0x01f7:
            int r2 = r2 * 53
            long r3 = com.google.android.gms.internal.p007firebaseauthapi.zzacc.zzf(r9, r5)
            int r3 = com.google.android.gms.internal.p007firebaseauthapi.zzzu.zze(r3)
            goto L_0x0226
        L_0x0202:
            int r2 = r2 * 53
            long r3 = com.google.android.gms.internal.p007firebaseauthapi.zzacc.zzf(r9, r5)
            int r3 = com.google.android.gms.internal.p007firebaseauthapi.zzzu.zze(r3)
            goto L_0x0226
        L_0x020d:
            int r2 = r2 * 53
            float r3 = com.google.android.gms.internal.p007firebaseauthapi.zzacc.zzj(r9, r5)
            int r3 = java.lang.Float.floatToIntBits(r3)
            goto L_0x0226
        L_0x0218:
            int r2 = r2 * 53
            double r3 = com.google.android.gms.internal.p007firebaseauthapi.zzacc.zzl(r9, r5)
            long r3 = java.lang.Double.doubleToLongBits(r3)
            int r3 = com.google.android.gms.internal.p007firebaseauthapi.zzzu.zze(r3)
        L_0x0226:
            int r2 = r2 + r3
        L_0x0227:
            int r1 = r1 + 3
            goto L_0x0005
        L_0x022b:
            int r2 = r2 * 53
            com.google.android.gms.internal.firebase-auth-api.zzabr<?, ?> r0 = r8.zzo
            java.lang.Object r0 = r0.zzj(r9)
            int r0 = r0.hashCode()
            int r2 = r2 + r0
            boolean r0 = r8.zzh
            if (r0 != 0) goto L_0x023d
            return r2
        L_0x023d:
            com.google.android.gms.internal.firebase-auth-api.zzzc<?> r0 = r8.zzp
            r0.zzb(r9)
            r9 = 0
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.p007firebaseauthapi.zzaau.zzc(java.lang.Object):int");
    }

    public final int zze(T t) {
        return this.zzj ? zzs(t) : zzr(t);
    }

    /*  JADX ERROR: StackOverflow in pass: MarkFinallyVisitor
        jadx.core.utils.exceptions.JadxOverflowException: 
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:47)
        	at jadx.core.utils.ErrorsCounter.methodError(ErrorsCounter.java:81)
        */
    public final void zzf(T r13, com.google.android.gms.internal.p007firebaseauthapi.zzabc r14, com.google.android.gms.internal.p007firebaseauthapi.zzzb r15) throws java.io.IOException {
        /*
            r12 = this;
            java.util.Objects.requireNonNull(r15)
            com.google.android.gms.internal.firebase-auth-api.zzabr<?, ?> r7 = r12.zzo
            com.google.android.gms.internal.firebase-auth-api.zzzc<?> r8 = r12.zzp
            r9 = 0
            r0 = r9
            r10 = r0
        L_0x000a:
            int r1 = r14.zzb()     // Catch:{ all -> 0x0078 }
            int r2 = r12.zzS(r1)     // Catch:{ all -> 0x0078 }
            if (r2 >= 0) goto L_0x007b
            r2 = 2147483647(0x7fffffff, float:NaN)
            if (r1 != r2) goto L_0x0030
            int r14 = r12.zzl
        L_0x001b:
            int r15 = r12.zzm
            if (r14 >= r15) goto L_0x002a
            int[] r15 = r12.zzk
            r15 = r15[r14]
            java.lang.Object r10 = r12.zzA(r13, r15, r10, r7)
            int r14 = r14 + 1
            goto L_0x001b
        L_0x002a:
            if (r10 == 0) goto L_0x05c2
            r7.zzl(r13, r10)
            return
        L_0x0030:
            boolean r2 = r12.zzh     // Catch:{ all -> 0x0078 }
            if (r2 != 0) goto L_0x0036
            r2 = r9
            goto L_0x003d
        L_0x0036:
            com.google.android.gms.internal.firebase-auth-api.zzaar r2 = r12.zzg     // Catch:{ all -> 0x0078 }
            java.lang.Object r1 = r8.zzf(r15, r2, r1)     // Catch:{ all -> 0x0078 }
            r2 = r1
        L_0x003d:
            if (r2 == 0) goto L_0x0052
            if (r0 != 0) goto L_0x0045
            com.google.android.gms.internal.firebase-auth-api.zzzg r0 = r8.zzc(r13)     // Catch:{ all -> 0x0078 }
        L_0x0045:
            r11 = r0
            r0 = r8
            r1 = r14
            r3 = r15
            r4 = r11
            r5 = r10
            r6 = r7
            java.lang.Object r10 = r0.zze(r1, r2, r3, r4, r5, r6)     // Catch:{ all -> 0x0078 }
            r0 = r11
            goto L_0x000a
        L_0x0052:
            r7.zza(r14)     // Catch:{ all -> 0x0078 }
            if (r10 != 0) goto L_0x005b
            java.lang.Object r10 = r7.zzk(r13)     // Catch:{ all -> 0x0078 }
        L_0x005b:
            boolean r1 = r7.zzn(r10, r14)     // Catch:{ all -> 0x0078 }
            if (r1 != 0) goto L_0x000a
            int r14 = r12.zzl
        L_0x0063:
            int r15 = r12.zzm
            if (r14 >= r15) goto L_0x0072
            int[] r15 = r12.zzk
            r15 = r15[r14]
            java.lang.Object r10 = r12.zzA(r13, r15, r10, r7)
            int r14 = r14 + 1
            goto L_0x0063
        L_0x0072:
            if (r10 == 0) goto L_0x05c2
            r7.zzl(r13, r10)
            return
        L_0x0078:
            r14 = move-exception
            goto L_0x05c3
        L_0x007b:
            int r3 = r12.zzD(r2)     // Catch:{ all -> 0x0078 }
            int r4 = zzF(r3)     // Catch:{ zzzv -> 0x059c }
            r5 = 1048575(0xfffff, float:1.469367E-39)
            switch(r4) {
                case 0: goto L_0x0570;
                case 1: goto L_0x0561;
                case 2: goto L_0x0552;
                case 3: goto L_0x0543;
                case 4: goto L_0x0534;
                case 5: goto L_0x0525;
                case 6: goto L_0x0516;
                case 7: goto L_0x0507;
                case 8: goto L_0x04ff;
                case 9: goto L_0x04ce;
                case 10: goto L_0x04bf;
                case 11: goto L_0x04b0;
                case 12: goto L_0x048e;
                case 13: goto L_0x047f;
                case 14: goto L_0x0470;
                case 15: goto L_0x0461;
                case 16: goto L_0x0452;
                case 17: goto L_0x0421;
                case 18: goto L_0x0413;
                case 19: goto L_0x0405;
                case 20: goto L_0x03f7;
                case 21: goto L_0x03e9;
                case 22: goto L_0x03db;
                case 23: goto L_0x03cd;
                case 24: goto L_0x03bf;
                case 25: goto L_0x03b1;
                case 26: goto L_0x0387;
                case 27: goto L_0x0375;
                case 28: goto L_0x0367;
                case 29: goto L_0x0359;
                case 30: goto L_0x0344;
                case 31: goto L_0x0336;
                case 32: goto L_0x0328;
                case 33: goto L_0x031a;
                case 34: goto L_0x030c;
                case 35: goto L_0x02fe;
                case 36: goto L_0x02f0;
                case 37: goto L_0x02e2;
                case 38: goto L_0x02d4;
                case 39: goto L_0x02c6;
                case 40: goto L_0x02b8;
                case 41: goto L_0x02aa;
                case 42: goto L_0x029c;
                case 43: goto L_0x028e;
                case 44: goto L_0x0279;
                case 45: goto L_0x026b;
                case 46: goto L_0x025d;
                case 47: goto L_0x024f;
                case 48: goto L_0x0241;
                case 49: goto L_0x022f;
                case 50: goto L_0x01f9;
                case 51: goto L_0x01e7;
                case 52: goto L_0x01d5;
                case 53: goto L_0x01c3;
                case 54: goto L_0x01b1;
                case 55: goto L_0x019f;
                case 56: goto L_0x018d;
                case 57: goto L_0x017b;
                case 58: goto L_0x0169;
                case 59: goto L_0x0161;
                case 60: goto L_0x0130;
                case 61: goto L_0x0122;
                case 62: goto L_0x0110;
                case 63: goto L_0x00eb;
                case 64: goto L_0x00d9;
                case 65: goto L_0x00c7;
                case 66: goto L_0x00b5;
                case 67: goto L_0x00a3;
                case 68: goto L_0x0091;
                default: goto L_0x0089;
            }     // Catch:{ zzzv -> 0x059c }
        L_0x0089:
            if (r10 != 0) goto L_0x057f
            java.lang.Object r10 = r7.zzg()     // Catch:{ zzzv -> 0x059c }
            goto L_0x057f
        L_0x0091:
            r3 = r3 & r5
            long r3 = (long) r3     // Catch:{ zzzv -> 0x059c }
            com.google.android.gms.internal.firebase-auth-api.zzabd r5 = r12.zzw(r2)     // Catch:{ zzzv -> 0x059c }
            java.lang.Object r5 = r14.zzp(r5, r15)     // Catch:{ zzzv -> 0x059c }
            com.google.android.gms.internal.p007firebaseauthapi.zzacc.zzo(r13, r3, r5)     // Catch:{ zzzv -> 0x059c }
            r12.zzR(r13, r1, r2)     // Catch:{ zzzv -> 0x059c }
            goto L_0x000a
        L_0x00a3:
            r3 = r3 & r5
            long r3 = (long) r3     // Catch:{ zzzv -> 0x059c }
            long r5 = r14.zzw()     // Catch:{ zzzv -> 0x059c }
            java.lang.Long r5 = java.lang.Long.valueOf(r5)     // Catch:{ zzzv -> 0x059c }
            com.google.android.gms.internal.p007firebaseauthapi.zzacc.zzo(r13, r3, r5)     // Catch:{ zzzv -> 0x059c }
            r12.zzR(r13, r1, r2)     // Catch:{ zzzv -> 0x059c }
            goto L_0x000a
        L_0x00b5:
            r3 = r3 & r5
            long r3 = (long) r3     // Catch:{ zzzv -> 0x059c }
            int r5 = r14.zzv()     // Catch:{ zzzv -> 0x059c }
            java.lang.Integer r5 = java.lang.Integer.valueOf(r5)     // Catch:{ zzzv -> 0x059c }
            com.google.android.gms.internal.p007firebaseauthapi.zzacc.zzo(r13, r3, r5)     // Catch:{ zzzv -> 0x059c }
            r12.zzR(r13, r1, r2)     // Catch:{ zzzv -> 0x059c }
            goto L_0x000a
        L_0x00c7:
            r3 = r3 & r5
            long r3 = (long) r3     // Catch:{ zzzv -> 0x059c }
            long r5 = r14.zzu()     // Catch:{ zzzv -> 0x059c }
            java.lang.Long r5 = java.lang.Long.valueOf(r5)     // Catch:{ zzzv -> 0x059c }
            com.google.android.gms.internal.p007firebaseauthapi.zzacc.zzo(r13, r3, r5)     // Catch:{ zzzv -> 0x059c }
            r12.zzR(r13, r1, r2)     // Catch:{ zzzv -> 0x059c }
            goto L_0x000a
        L_0x00d9:
            r3 = r3 & r5
            long r3 = (long) r3     // Catch:{ zzzv -> 0x059c }
            int r5 = r14.zzt()     // Catch:{ zzzv -> 0x059c }
            java.lang.Integer r5 = java.lang.Integer.valueOf(r5)     // Catch:{ zzzv -> 0x059c }
            com.google.android.gms.internal.p007firebaseauthapi.zzacc.zzo(r13, r3, r5)     // Catch:{ zzzv -> 0x059c }
            r12.zzR(r13, r1, r2)     // Catch:{ zzzv -> 0x059c }
            goto L_0x000a
        L_0x00eb:
            int r4 = r14.zzs()     // Catch:{ zzzv -> 0x059c }
            com.google.android.gms.internal.firebase-auth-api.zzzs r6 = r12.zzy(r2)     // Catch:{ zzzv -> 0x059c }
            if (r6 == 0) goto L_0x0102
            boolean r6 = r6.zza()     // Catch:{ zzzv -> 0x059c }
            if (r6 == 0) goto L_0x00fc
            goto L_0x0102
        L_0x00fc:
            java.lang.Object r10 = com.google.android.gms.internal.p007firebaseauthapi.zzabf.zzH(r1, r4, r10, r7)     // Catch:{ zzzv -> 0x059c }
            goto L_0x000a
        L_0x0102:
            r3 = r3 & r5
            long r5 = (long) r3     // Catch:{ zzzv -> 0x059c }
            java.lang.Integer r3 = java.lang.Integer.valueOf(r4)     // Catch:{ zzzv -> 0x059c }
            com.google.android.gms.internal.p007firebaseauthapi.zzacc.zzo(r13, r5, r3)     // Catch:{ zzzv -> 0x059c }
            r12.zzR(r13, r1, r2)     // Catch:{ zzzv -> 0x059c }
            goto L_0x000a
        L_0x0110:
            r3 = r3 & r5
            long r3 = (long) r3     // Catch:{ zzzv -> 0x059c }
            int r5 = r14.zzr()     // Catch:{ zzzv -> 0x059c }
            java.lang.Integer r5 = java.lang.Integer.valueOf(r5)     // Catch:{ zzzv -> 0x059c }
            com.google.android.gms.internal.p007firebaseauthapi.zzacc.zzo(r13, r3, r5)     // Catch:{ zzzv -> 0x059c }
            r12.zzR(r13, r1, r2)     // Catch:{ zzzv -> 0x059c }
            goto L_0x000a
        L_0x0122:
            r3 = r3 & r5
            long r3 = (long) r3     // Catch:{ zzzv -> 0x059c }
            com.google.android.gms.internal.firebase-auth-api.zzym r5 = r14.zzq()     // Catch:{ zzzv -> 0x059c }
            com.google.android.gms.internal.p007firebaseauthapi.zzacc.zzo(r13, r3, r5)     // Catch:{ zzzv -> 0x059c }
            r12.zzR(r13, r1, r2)     // Catch:{ zzzv -> 0x059c }
            goto L_0x000a
        L_0x0130:
            boolean r4 = r12.zzQ(r13, r1, r2)     // Catch:{ zzzv -> 0x059c }
            if (r4 == 0) goto L_0x014c
            r3 = r3 & r5
            long r3 = (long) r3     // Catch:{ zzzv -> 0x059c }
            java.lang.Object r5 = com.google.android.gms.internal.p007firebaseauthapi.zzacc.zzn(r13, r3)     // Catch:{ zzzv -> 0x059c }
            com.google.android.gms.internal.firebase-auth-api.zzabd r6 = r12.zzw(r2)     // Catch:{ zzzv -> 0x059c }
            java.lang.Object r6 = r14.zzo(r6, r15)     // Catch:{ zzzv -> 0x059c }
            java.lang.Object r5 = com.google.android.gms.internal.p007firebaseauthapi.zzzu.zzi(r5, r6)     // Catch:{ zzzv -> 0x059c }
            com.google.android.gms.internal.p007firebaseauthapi.zzacc.zzo(r13, r3, r5)     // Catch:{ zzzv -> 0x059c }
            goto L_0x015c
        L_0x014c:
            r3 = r3 & r5
            long r3 = (long) r3     // Catch:{ zzzv -> 0x059c }
            com.google.android.gms.internal.firebase-auth-api.zzabd r5 = r12.zzw(r2)     // Catch:{ zzzv -> 0x059c }
            java.lang.Object r5 = r14.zzo(r5, r15)     // Catch:{ zzzv -> 0x059c }
            com.google.android.gms.internal.p007firebaseauthapi.zzacc.zzo(r13, r3, r5)     // Catch:{ zzzv -> 0x059c }
            r12.zzP(r13, r2)     // Catch:{ zzzv -> 0x059c }
        L_0x015c:
            r12.zzR(r13, r1, r2)     // Catch:{ zzzv -> 0x059c }
            goto L_0x000a
        L_0x0161:
            r12.zzC(r13, r3, r14)     // Catch:{ zzzv -> 0x059c }
            r12.zzR(r13, r1, r2)     // Catch:{ zzzv -> 0x059c }
            goto L_0x000a
        L_0x0169:
            r3 = r3 & r5
            long r3 = (long) r3     // Catch:{ zzzv -> 0x059c }
            boolean r5 = r14.zzl()     // Catch:{ zzzv -> 0x059c }
            java.lang.Boolean r5 = java.lang.Boolean.valueOf(r5)     // Catch:{ zzzv -> 0x059c }
            com.google.android.gms.internal.p007firebaseauthapi.zzacc.zzo(r13, r3, r5)     // Catch:{ zzzv -> 0x059c }
            r12.zzR(r13, r1, r2)     // Catch:{ zzzv -> 0x059c }
            goto L_0x000a
        L_0x017b:
            r3 = r3 & r5
            long r3 = (long) r3     // Catch:{ zzzv -> 0x059c }
            int r5 = r14.zzk()     // Catch:{ zzzv -> 0x059c }
            java.lang.Integer r5 = java.lang.Integer.valueOf(r5)     // Catch:{ zzzv -> 0x059c }
            com.google.android.gms.internal.p007firebaseauthapi.zzacc.zzo(r13, r3, r5)     // Catch:{ zzzv -> 0x059c }
            r12.zzR(r13, r1, r2)     // Catch:{ zzzv -> 0x059c }
            goto L_0x000a
        L_0x018d:
            r3 = r3 & r5
            long r3 = (long) r3     // Catch:{ zzzv -> 0x059c }
            long r5 = r14.zzj()     // Catch:{ zzzv -> 0x059c }
            java.lang.Long r5 = java.lang.Long.valueOf(r5)     // Catch:{ zzzv -> 0x059c }
            com.google.android.gms.internal.p007firebaseauthapi.zzacc.zzo(r13, r3, r5)     // Catch:{ zzzv -> 0x059c }
            r12.zzR(r13, r1, r2)     // Catch:{ zzzv -> 0x059c }
            goto L_0x000a
        L_0x019f:
            r3 = r3 & r5
            long r3 = (long) r3     // Catch:{ zzzv -> 0x059c }
            int r5 = r14.zzi()     // Catch:{ zzzv -> 0x059c }
            java.lang.Integer r5 = java.lang.Integer.valueOf(r5)     // Catch:{ zzzv -> 0x059c }
            com.google.android.gms.internal.p007firebaseauthapi.zzacc.zzo(r13, r3, r5)     // Catch:{ zzzv -> 0x059c }
            r12.zzR(r13, r1, r2)     // Catch:{ zzzv -> 0x059c }
            goto L_0x000a
        L_0x01b1:
            r3 = r3 & r5
            long r3 = (long) r3     // Catch:{ zzzv -> 0x059c }
            long r5 = r14.zzg()     // Catch:{ zzzv -> 0x059c }
            java.lang.Long r5 = java.lang.Long.valueOf(r5)     // Catch:{ zzzv -> 0x059c }
            com.google.android.gms.internal.p007firebaseauthapi.zzacc.zzo(r13, r3, r5)     // Catch:{ zzzv -> 0x059c }
            r12.zzR(r13, r1, r2)     // Catch:{ zzzv -> 0x059c }
            goto L_0x000a
        L_0x01c3:
            r3 = r3 & r5
            long r3 = (long) r3     // Catch:{ zzzv -> 0x059c }
            long r5 = r14.zzh()     // Catch:{ zzzv -> 0x059c }
            java.lang.Long r5 = java.lang.Long.valueOf(r5)     // Catch:{ zzzv -> 0x059c }
            com.google.android.gms.internal.p007firebaseauthapi.zzacc.zzo(r13, r3, r5)     // Catch:{ zzzv -> 0x059c }
            r12.zzR(r13, r1, r2)     // Catch:{ zzzv -> 0x059c }
            goto L_0x000a
        L_0x01d5:
            r3 = r3 & r5
            long r3 = (long) r3     // Catch:{ zzzv -> 0x059c }
            float r5 = r14.zzf()     // Catch:{ zzzv -> 0x059c }
            java.lang.Float r5 = java.lang.Float.valueOf(r5)     // Catch:{ zzzv -> 0x059c }
            com.google.android.gms.internal.p007firebaseauthapi.zzacc.zzo(r13, r3, r5)     // Catch:{ zzzv -> 0x059c }
            r12.zzR(r13, r1, r2)     // Catch:{ zzzv -> 0x059c }
            goto L_0x000a
        L_0x01e7:
            r3 = r3 & r5
            long r3 = (long) r3     // Catch:{ zzzv -> 0x059c }
            double r5 = r14.zze()     // Catch:{ zzzv -> 0x059c }
            java.lang.Double r5 = java.lang.Double.valueOf(r5)     // Catch:{ zzzv -> 0x059c }
            com.google.android.gms.internal.p007firebaseauthapi.zzacc.zzo(r13, r3, r5)     // Catch:{ zzzv -> 0x059c }
            r12.zzR(r13, r1, r2)     // Catch:{ zzzv -> 0x059c }
            goto L_0x000a
        L_0x01f9:
            java.lang.Object r1 = r12.zzx(r2)     // Catch:{ zzzv -> 0x059c }
            int r2 = r12.zzD(r2)     // Catch:{ zzzv -> 0x059c }
            r2 = r2 & r5
            long r2 = (long) r2     // Catch:{ zzzv -> 0x059c }
            java.lang.Object r4 = com.google.android.gms.internal.p007firebaseauthapi.zzacc.zzn(r13, r2)     // Catch:{ zzzv -> 0x059c }
            if (r4 == 0) goto L_0x021f
            boolean r5 = com.google.android.gms.internal.p007firebaseauthapi.zzaam.zzb(r4)     // Catch:{ zzzv -> 0x059c }
            if (r5 == 0) goto L_0x022a
            com.google.android.gms.internal.firebase-auth-api.zzaal r5 = com.google.android.gms.internal.p007firebaseauthapi.zzaal.zza()     // Catch:{ zzzv -> 0x059c }
            com.google.android.gms.internal.firebase-auth-api.zzaal r5 = r5.zzc()     // Catch:{ zzzv -> 0x059c }
            com.google.android.gms.internal.p007firebaseauthapi.zzaam.zzc(r5, r4)     // Catch:{ zzzv -> 0x059c }
            com.google.android.gms.internal.p007firebaseauthapi.zzacc.zzo(r13, r2, r5)     // Catch:{ zzzv -> 0x059c }
            r4 = r5
            goto L_0x022a
        L_0x021f:
            com.google.android.gms.internal.firebase-auth-api.zzaal r4 = com.google.android.gms.internal.p007firebaseauthapi.zzaal.zza()     // Catch:{ zzzv -> 0x059c }
            com.google.android.gms.internal.firebase-auth-api.zzaal r4 = r4.zzc()     // Catch:{ zzzv -> 0x059c }
            com.google.android.gms.internal.p007firebaseauthapi.zzacc.zzo(r13, r2, r4)     // Catch:{ zzzv -> 0x059c }
        L_0x022a:
            com.google.android.gms.internal.firebase-auth-api.zzaal r4 = (com.google.android.gms.internal.p007firebaseauthapi.zzaal) r4     // Catch:{ zzzv -> 0x059c }
            com.google.android.gms.internal.firebase-auth-api.zzaak r1 = (com.google.android.gms.internal.p007firebaseauthapi.zzaak) r1     // Catch:{ zzzv -> 0x059c }
            throw r9     // Catch:{ zzzv -> 0x059c }
        L_0x022f:
            r1 = r3 & r5
            long r3 = (long) r1     // Catch:{ zzzv -> 0x059c }
            com.google.android.gms.internal.firebase-auth-api.zzabd r1 = r12.zzw(r2)     // Catch:{ zzzv -> 0x059c }
            com.google.android.gms.internal.firebase-auth-api.zzaaf r2 = r12.zzn     // Catch:{ zzzv -> 0x059c }
            java.util.List r2 = r2.zza(r13, r3)     // Catch:{ zzzv -> 0x059c }
            r14.zzH(r2, r1, r15)     // Catch:{ zzzv -> 0x059c }
            goto L_0x000a
        L_0x0241:
            com.google.android.gms.internal.firebase-auth-api.zzaaf r1 = r12.zzn     // Catch:{ zzzv -> 0x059c }
            r2 = r3 & r5
            long r2 = (long) r2     // Catch:{ zzzv -> 0x059c }
            java.util.List r1 = r1.zza(r13, r2)     // Catch:{ zzzv -> 0x059c }
            r14.zzO(r1)     // Catch:{ zzzv -> 0x059c }
            goto L_0x000a
        L_0x024f:
            com.google.android.gms.internal.firebase-auth-api.zzaaf r1 = r12.zzn     // Catch:{ zzzv -> 0x059c }
            r2 = r3 & r5
            long r2 = (long) r2     // Catch:{ zzzv -> 0x059c }
            java.util.List r1 = r1.zza(r13, r2)     // Catch:{ zzzv -> 0x059c }
            r14.zzN(r1)     // Catch:{ zzzv -> 0x059c }
            goto L_0x000a
        L_0x025d:
            com.google.android.gms.internal.firebase-auth-api.zzaaf r1 = r12.zzn     // Catch:{ zzzv -> 0x059c }
            r2 = r3 & r5
            long r2 = (long) r2     // Catch:{ zzzv -> 0x059c }
            java.util.List r1 = r1.zza(r13, r2)     // Catch:{ zzzv -> 0x059c }
            r14.zzM(r1)     // Catch:{ zzzv -> 0x059c }
            goto L_0x000a
        L_0x026b:
            com.google.android.gms.internal.firebase-auth-api.zzaaf r1 = r12.zzn     // Catch:{ zzzv -> 0x059c }
            r2 = r3 & r5
            long r2 = (long) r2     // Catch:{ zzzv -> 0x059c }
            java.util.List r1 = r1.zza(r13, r2)     // Catch:{ zzzv -> 0x059c }
            r14.zzL(r1)     // Catch:{ zzzv -> 0x059c }
            goto L_0x000a
        L_0x0279:
            com.google.android.gms.internal.firebase-auth-api.zzaaf r4 = r12.zzn     // Catch:{ zzzv -> 0x059c }
            r3 = r3 & r5
            long r5 = (long) r3     // Catch:{ zzzv -> 0x059c }
            java.util.List r3 = r4.zza(r13, r5)     // Catch:{ zzzv -> 0x059c }
            r14.zzK(r3)     // Catch:{ zzzv -> 0x059c }
            com.google.android.gms.internal.firebase-auth-api.zzzs r2 = r12.zzy(r2)     // Catch:{ zzzv -> 0x059c }
            java.lang.Object r10 = com.google.android.gms.internal.p007firebaseauthapi.zzabf.zzG(r1, r3, r2, r10, r7)     // Catch:{ zzzv -> 0x059c }
            goto L_0x000a
        L_0x028e:
            com.google.android.gms.internal.firebase-auth-api.zzaaf r1 = r12.zzn     // Catch:{ zzzv -> 0x059c }
            r2 = r3 & r5
            long r2 = (long) r2     // Catch:{ zzzv -> 0x059c }
            java.util.List r1 = r1.zza(r13, r2)     // Catch:{ zzzv -> 0x059c }
            r14.zzJ(r1)     // Catch:{ zzzv -> 0x059c }
            goto L_0x000a
        L_0x029c:
            com.google.android.gms.internal.firebase-auth-api.zzaaf r1 = r12.zzn     // Catch:{ zzzv -> 0x059c }
            r2 = r3 & r5
            long r2 = (long) r2     // Catch:{ zzzv -> 0x059c }
            java.util.List r1 = r1.zza(r13, r2)     // Catch:{ zzzv -> 0x059c }
            r14.zzE(r1)     // Catch:{ zzzv -> 0x059c }
            goto L_0x000a
        L_0x02aa:
            com.google.android.gms.internal.firebase-auth-api.zzaaf r1 = r12.zzn     // Catch:{ zzzv -> 0x059c }
            r2 = r3 & r5
            long r2 = (long) r2     // Catch:{ zzzv -> 0x059c }
            java.util.List r1 = r1.zza(r13, r2)     // Catch:{ zzzv -> 0x059c }
            r14.zzD(r1)     // Catch:{ zzzv -> 0x059c }
            goto L_0x000a
        L_0x02b8:
            com.google.android.gms.internal.firebase-auth-api.zzaaf r1 = r12.zzn     // Catch:{ zzzv -> 0x059c }
            r2 = r3 & r5
            long r2 = (long) r2     // Catch:{ zzzv -> 0x059c }
            java.util.List r1 = r1.zza(r13, r2)     // Catch:{ zzzv -> 0x059c }
            r14.zzC(r1)     // Catch:{ zzzv -> 0x059c }
            goto L_0x000a
        L_0x02c6:
            com.google.android.gms.internal.firebase-auth-api.zzaaf r1 = r12.zzn     // Catch:{ zzzv -> 0x059c }
            r2 = r3 & r5
            long r2 = (long) r2     // Catch:{ zzzv -> 0x059c }
            java.util.List r1 = r1.zza(r13, r2)     // Catch:{ zzzv -> 0x059c }
            r14.zzB(r1)     // Catch:{ zzzv -> 0x059c }
            goto L_0x000a
        L_0x02d4:
            com.google.android.gms.internal.firebase-auth-api.zzaaf r1 = r12.zzn     // Catch:{ zzzv -> 0x059c }
            r2 = r3 & r5
            long r2 = (long) r2     // Catch:{ zzzv -> 0x059c }
            java.util.List r1 = r1.zza(r13, r2)     // Catch:{ zzzv -> 0x059c }
            r14.zzz(r1)     // Catch:{ zzzv -> 0x059c }
            goto L_0x000a
        L_0x02e2:
            com.google.android.gms.internal.firebase-auth-api.zzaaf r1 = r12.zzn     // Catch:{ zzzv -> 0x059c }
            r2 = r3 & r5
            long r2 = (long) r2     // Catch:{ zzzv -> 0x059c }
            java.util.List r1 = r1.zza(r13, r2)     // Catch:{ zzzv -> 0x059c }
            r14.zzA(r1)     // Catch:{ zzzv -> 0x059c }
            goto L_0x000a
        L_0x02f0:
            com.google.android.gms.internal.firebase-auth-api.zzaaf r1 = r12.zzn     // Catch:{ zzzv -> 0x059c }
            r2 = r3 & r5
            long r2 = (long) r2     // Catch:{ zzzv -> 0x059c }
            java.util.List r1 = r1.zza(r13, r2)     // Catch:{ zzzv -> 0x059c }
            r14.zzy(r1)     // Catch:{ zzzv -> 0x059c }
            goto L_0x000a
        L_0x02fe:
            com.google.android.gms.internal.firebase-auth-api.zzaaf r1 = r12.zzn     // Catch:{ zzzv -> 0x059c }
            r2 = r3 & r5
            long r2 = (long) r2     // Catch:{ zzzv -> 0x059c }
            java.util.List r1 = r1.zza(r13, r2)     // Catch:{ zzzv -> 0x059c }
            r14.zzx(r1)     // Catch:{ zzzv -> 0x059c }
            goto L_0x000a
        L_0x030c:
            com.google.android.gms.internal.firebase-auth-api.zzaaf r1 = r12.zzn     // Catch:{ zzzv -> 0x059c }
            r2 = r3 & r5
            long r2 = (long) r2     // Catch:{ zzzv -> 0x059c }
            java.util.List r1 = r1.zza(r13, r2)     // Catch:{ zzzv -> 0x059c }
            r14.zzO(r1)     // Catch:{ zzzv -> 0x059c }
            goto L_0x000a
        L_0x031a:
            com.google.android.gms.internal.firebase-auth-api.zzaaf r1 = r12.zzn     // Catch:{ zzzv -> 0x059c }
            r2 = r3 & r5
            long r2 = (long) r2     // Catch:{ zzzv -> 0x059c }
            java.util.List r1 = r1.zza(r13, r2)     // Catch:{ zzzv -> 0x059c }
            r14.zzN(r1)     // Catch:{ zzzv -> 0x059c }
            goto L_0x000a
        L_0x0328:
            com.google.android.gms.internal.firebase-auth-api.zzaaf r1 = r12.zzn     // Catch:{ zzzv -> 0x059c }
            r2 = r3 & r5
            long r2 = (long) r2     // Catch:{ zzzv -> 0x059c }
            java.util.List r1 = r1.zza(r13, r2)     // Catch:{ zzzv -> 0x059c }
            r14.zzM(r1)     // Catch:{ zzzv -> 0x059c }
            goto L_0x000a
        L_0x0336:
            com.google.android.gms.internal.firebase-auth-api.zzaaf r1 = r12.zzn     // Catch:{ zzzv -> 0x059c }
            r2 = r3 & r5
            long r2 = (long) r2     // Catch:{ zzzv -> 0x059c }
            java.util.List r1 = r1.zza(r13, r2)     // Catch:{ zzzv -> 0x059c }
            r14.zzL(r1)     // Catch:{ zzzv -> 0x059c }
            goto L_0x000a
        L_0x0344:
            com.google.android.gms.internal.firebase-auth-api.zzaaf r4 = r12.zzn     // Catch:{ zzzv -> 0x059c }
            r3 = r3 & r5
            long r5 = (long) r3     // Catch:{ zzzv -> 0x059c }
            java.util.List r3 = r4.zza(r13, r5)     // Catch:{ zzzv -> 0x059c }
            r14.zzK(r3)     // Catch:{ zzzv -> 0x059c }
            com.google.android.gms.internal.firebase-auth-api.zzzs r2 = r12.zzy(r2)     // Catch:{ zzzv -> 0x059c }
            java.lang.Object r10 = com.google.android.gms.internal.p007firebaseauthapi.zzabf.zzG(r1, r3, r2, r10, r7)     // Catch:{ zzzv -> 0x059c }
            goto L_0x000a
        L_0x0359:
            com.google.android.gms.internal.firebase-auth-api.zzaaf r1 = r12.zzn     // Catch:{ zzzv -> 0x059c }
            r2 = r3 & r5
            long r2 = (long) r2     // Catch:{ zzzv -> 0x059c }
            java.util.List r1 = r1.zza(r13, r2)     // Catch:{ zzzv -> 0x059c }
            r14.zzJ(r1)     // Catch:{ zzzv -> 0x059c }
            goto L_0x000a
        L_0x0367:
            com.google.android.gms.internal.firebase-auth-api.zzaaf r1 = r12.zzn     // Catch:{ zzzv -> 0x059c }
            r2 = r3 & r5
            long r2 = (long) r2     // Catch:{ zzzv -> 0x059c }
            java.util.List r1 = r1.zza(r13, r2)     // Catch:{ zzzv -> 0x059c }
            r14.zzI(r1)     // Catch:{ zzzv -> 0x059c }
            goto L_0x000a
        L_0x0375:
            com.google.android.gms.internal.firebase-auth-api.zzabd r1 = r12.zzw(r2)     // Catch:{ zzzv -> 0x059c }
            r2 = r3 & r5
            long r2 = (long) r2     // Catch:{ zzzv -> 0x059c }
            com.google.android.gms.internal.firebase-auth-api.zzaaf r4 = r12.zzn     // Catch:{ zzzv -> 0x059c }
            java.util.List r2 = r4.zza(r13, r2)     // Catch:{ zzzv -> 0x059c }
            r14.zzG(r2, r1, r15)     // Catch:{ zzzv -> 0x059c }
            goto L_0x000a
        L_0x0387:
            boolean r1 = zzG(r3)     // Catch:{ zzzv -> 0x059c }
            if (r1 == 0) goto L_0x039f
            com.google.android.gms.internal.firebase-auth-api.zzaaf r1 = r12.zzn     // Catch:{ zzzv -> 0x059c }
            r2 = r3 & r5
            long r2 = (long) r2     // Catch:{ zzzv -> 0x059c }
            java.util.List r1 = r1.zza(r13, r2)     // Catch:{ zzzv -> 0x059c }
            r2 = r14
            com.google.android.gms.internal.firebase-auth-api.zzyq r2 = (com.google.android.gms.internal.p007firebaseauthapi.zzyq) r2     // Catch:{ zzzv -> 0x059c }
            r3 = 1
            r2.zzF(r1, r3)     // Catch:{ zzzv -> 0x059c }
            goto L_0x000a
        L_0x039f:
            com.google.android.gms.internal.firebase-auth-api.zzaaf r1 = r12.zzn     // Catch:{ zzzv -> 0x059c }
            r2 = r3 & r5
            long r2 = (long) r2     // Catch:{ zzzv -> 0x059c }
            java.util.List r1 = r1.zza(r13, r2)     // Catch:{ zzzv -> 0x059c }
            r2 = r14
            com.google.android.gms.internal.firebase-auth-api.zzyq r2 = (com.google.android.gms.internal.p007firebaseauthapi.zzyq) r2     // Catch:{ zzzv -> 0x059c }
            r3 = 0
            r2.zzF(r1, r3)     // Catch:{ zzzv -> 0x059c }
            goto L_0x000a
        L_0x03b1:
            com.google.android.gms.internal.firebase-auth-api.zzaaf r1 = r12.zzn     // Catch:{ zzzv -> 0x059c }
            r2 = r3 & r5
            long r2 = (long) r2     // Catch:{ zzzv -> 0x059c }
            java.util.List r1 = r1.zza(r13, r2)     // Catch:{ zzzv -> 0x059c }
            r14.zzE(r1)     // Catch:{ zzzv -> 0x059c }
            goto L_0x000a
        L_0x03bf:
            com.google.android.gms.internal.firebase-auth-api.zzaaf r1 = r12.zzn     // Catch:{ zzzv -> 0x059c }
            r2 = r3 & r5
            long r2 = (long) r2     // Catch:{ zzzv -> 0x059c }
            java.util.List r1 = r1.zza(r13, r2)     // Catch:{ zzzv -> 0x059c }
            r14.zzD(r1)     // Catch:{ zzzv -> 0x059c }
            goto L_0x000a
        L_0x03cd:
            com.google.android.gms.internal.firebase-auth-api.zzaaf r1 = r12.zzn     // Catch:{ zzzv -> 0x059c }
            r2 = r3 & r5
            long r2 = (long) r2     // Catch:{ zzzv -> 0x059c }
            java.util.List r1 = r1.zza(r13, r2)     // Catch:{ zzzv -> 0x059c }
            r14.zzC(r1)     // Catch:{ zzzv -> 0x059c }
            goto L_0x000a
        L_0x03db:
            com.google.android.gms.internal.firebase-auth-api.zzaaf r1 = r12.zzn     // Catch:{ zzzv -> 0x059c }
            r2 = r3 & r5
            long r2 = (long) r2     // Catch:{ zzzv -> 0x059c }
            java.util.List r1 = r1.zza(r13, r2)     // Catch:{ zzzv -> 0x059c }
            r14.zzB(r1)     // Catch:{ zzzv -> 0x059c }
            goto L_0x000a
        L_0x03e9:
            com.google.android.gms.internal.firebase-auth-api.zzaaf r1 = r12.zzn     // Catch:{ zzzv -> 0x059c }
            r2 = r3 & r5
            long r2 = (long) r2     // Catch:{ zzzv -> 0x059c }
            java.util.List r1 = r1.zza(r13, r2)     // Catch:{ zzzv -> 0x059c }
            r14.zzz(r1)     // Catch:{ zzzv -> 0x059c }
            goto L_0x000a
        L_0x03f7:
            com.google.android.gms.internal.firebase-auth-api.zzaaf r1 = r12.zzn     // Catch:{ zzzv -> 0x059c }
            r2 = r3 & r5
            long r2 = (long) r2     // Catch:{ zzzv -> 0x059c }
            java.util.List r1 = r1.zza(r13, r2)     // Catch:{ zzzv -> 0x059c }
            r14.zzA(r1)     // Catch:{ zzzv -> 0x059c }
            goto L_0x000a
        L_0x0405:
            com.google.android.gms.internal.firebase-auth-api.zzaaf r1 = r12.zzn     // Catch:{ zzzv -> 0x059c }
            r2 = r3 & r5
            long r2 = (long) r2     // Catch:{ zzzv -> 0x059c }
            java.util.List r1 = r1.zza(r13, r2)     // Catch:{ zzzv -> 0x059c }
            r14.zzy(r1)     // Catch:{ zzzv -> 0x059c }
            goto L_0x000a
        L_0x0413:
            com.google.android.gms.internal.firebase-auth-api.zzaaf r1 = r12.zzn     // Catch:{ zzzv -> 0x059c }
            r2 = r3 & r5
            long r2 = (long) r2     // Catch:{ zzzv -> 0x059c }
            java.util.List r1 = r1.zza(r13, r2)     // Catch:{ zzzv -> 0x059c }
            r14.zzx(r1)     // Catch:{ zzzv -> 0x059c }
            goto L_0x000a
        L_0x0421:
            boolean r1 = r12.zzO(r13, r2)     // Catch:{ zzzv -> 0x059c }
            if (r1 == 0) goto L_0x043f
            r1 = r3 & r5
            long r3 = (long) r1     // Catch:{ zzzv -> 0x059c }
            java.lang.Object r1 = com.google.android.gms.internal.p007firebaseauthapi.zzacc.zzn(r13, r3)     // Catch:{ zzzv -> 0x059c }
            com.google.android.gms.internal.firebase-auth-api.zzabd r2 = r12.zzw(r2)     // Catch:{ zzzv -> 0x059c }
            java.lang.Object r2 = r14.zzp(r2, r15)     // Catch:{ zzzv -> 0x059c }
            java.lang.Object r1 = com.google.android.gms.internal.p007firebaseauthapi.zzzu.zzi(r1, r2)     // Catch:{ zzzv -> 0x059c }
            com.google.android.gms.internal.p007firebaseauthapi.zzacc.zzo(r13, r3, r1)     // Catch:{ zzzv -> 0x059c }
            goto L_0x000a
        L_0x043f:
            r1 = r3 & r5
            long r3 = (long) r1     // Catch:{ zzzv -> 0x059c }
            com.google.android.gms.internal.firebase-auth-api.zzabd r1 = r12.zzw(r2)     // Catch:{ zzzv -> 0x059c }
            java.lang.Object r1 = r14.zzp(r1, r15)     // Catch:{ zzzv -> 0x059c }
            com.google.android.gms.internal.p007firebaseauthapi.zzacc.zzo(r13, r3, r1)     // Catch:{ zzzv -> 0x059c }
            r12.zzP(r13, r2)     // Catch:{ zzzv -> 0x059c }
            goto L_0x000a
        L_0x0452:
            r1 = r3 & r5
            long r3 = (long) r1     // Catch:{ zzzv -> 0x059c }
            long r5 = r14.zzw()     // Catch:{ zzzv -> 0x059c }
            com.google.android.gms.internal.p007firebaseauthapi.zzacc.zzg(r13, r3, r5)     // Catch:{ zzzv -> 0x059c }
            r12.zzP(r13, r2)     // Catch:{ zzzv -> 0x059c }
            goto L_0x000a
        L_0x0461:
            r1 = r3 & r5
            long r3 = (long) r1     // Catch:{ zzzv -> 0x059c }
            int r1 = r14.zzv()     // Catch:{ zzzv -> 0x059c }
            com.google.android.gms.internal.p007firebaseauthapi.zzacc.zze(r13, r3, r1)     // Catch:{ zzzv -> 0x059c }
            r12.zzP(r13, r2)     // Catch:{ zzzv -> 0x059c }
            goto L_0x000a
        L_0x0470:
            r1 = r3 & r5
            long r3 = (long) r1     // Catch:{ zzzv -> 0x059c }
            long r5 = r14.zzu()     // Catch:{ zzzv -> 0x059c }
            com.google.android.gms.internal.p007firebaseauthapi.zzacc.zzg(r13, r3, r5)     // Catch:{ zzzv -> 0x059c }
            r12.zzP(r13, r2)     // Catch:{ zzzv -> 0x059c }
            goto L_0x000a
        L_0x047f:
            r1 = r3 & r5
            long r3 = (long) r1     // Catch:{ zzzv -> 0x059c }
            int r1 = r14.zzt()     // Catch:{ zzzv -> 0x059c }
            com.google.android.gms.internal.p007firebaseauthapi.zzacc.zze(r13, r3, r1)     // Catch:{ zzzv -> 0x059c }
            r12.zzP(r13, r2)     // Catch:{ zzzv -> 0x059c }
            goto L_0x000a
        L_0x048e:
            int r4 = r14.zzs()     // Catch:{ zzzv -> 0x059c }
            com.google.android.gms.internal.firebase-auth-api.zzzs r6 = r12.zzy(r2)     // Catch:{ zzzv -> 0x059c }
            if (r6 == 0) goto L_0x04a5
            boolean r6 = r6.zza()     // Catch:{ zzzv -> 0x059c }
            if (r6 == 0) goto L_0x049f
            goto L_0x04a5
        L_0x049f:
            java.lang.Object r10 = com.google.android.gms.internal.p007firebaseauthapi.zzabf.zzH(r1, r4, r10, r7)     // Catch:{ zzzv -> 0x059c }
            goto L_0x000a
        L_0x04a5:
            r1 = r3 & r5
            long r5 = (long) r1     // Catch:{ zzzv -> 0x059c }
            com.google.android.gms.internal.p007firebaseauthapi.zzacc.zze(r13, r5, r4)     // Catch:{ zzzv -> 0x059c }
            r12.zzP(r13, r2)     // Catch:{ zzzv -> 0x059c }
            goto L_0x000a
        L_0x04b0:
            r1 = r3 & r5
            long r3 = (long) r1     // Catch:{ zzzv -> 0x059c }
            int r1 = r14.zzr()     // Catch:{ zzzv -> 0x059c }
            com.google.android.gms.internal.p007firebaseauthapi.zzacc.zze(r13, r3, r1)     // Catch:{ zzzv -> 0x059c }
            r12.zzP(r13, r2)     // Catch:{ zzzv -> 0x059c }
            goto L_0x000a
        L_0x04bf:
            r1 = r3 & r5
            long r3 = (long) r1     // Catch:{ zzzv -> 0x059c }
            com.google.android.gms.internal.firebase-auth-api.zzym r1 = r14.zzq()     // Catch:{ zzzv -> 0x059c }
            com.google.android.gms.internal.p007firebaseauthapi.zzacc.zzo(r13, r3, r1)     // Catch:{ zzzv -> 0x059c }
            r12.zzP(r13, r2)     // Catch:{ zzzv -> 0x059c }
            goto L_0x000a
        L_0x04ce:
            boolean r1 = r12.zzO(r13, r2)     // Catch:{ zzzv -> 0x059c }
            if (r1 == 0) goto L_0x04ec
            r1 = r3 & r5
            long r3 = (long) r1     // Catch:{ zzzv -> 0x059c }
            java.lang.Object r1 = com.google.android.gms.internal.p007firebaseauthapi.zzacc.zzn(r13, r3)     // Catch:{ zzzv -> 0x059c }
            com.google.android.gms.internal.firebase-auth-api.zzabd r2 = r12.zzw(r2)     // Catch:{ zzzv -> 0x059c }
            java.lang.Object r2 = r14.zzo(r2, r15)     // Catch:{ zzzv -> 0x059c }
            java.lang.Object r1 = com.google.android.gms.internal.p007firebaseauthapi.zzzu.zzi(r1, r2)     // Catch:{ zzzv -> 0x059c }
            com.google.android.gms.internal.p007firebaseauthapi.zzacc.zzo(r13, r3, r1)     // Catch:{ zzzv -> 0x059c }
            goto L_0x000a
        L_0x04ec:
            r1 = r3 & r5
            long r3 = (long) r1     // Catch:{ zzzv -> 0x059c }
            com.google.android.gms.internal.firebase-auth-api.zzabd r1 = r12.zzw(r2)     // Catch:{ zzzv -> 0x059c }
            java.lang.Object r1 = r14.zzo(r1, r15)     // Catch:{ zzzv -> 0x059c }
            com.google.android.gms.internal.p007firebaseauthapi.zzacc.zzo(r13, r3, r1)     // Catch:{ zzzv -> 0x059c }
            r12.zzP(r13, r2)     // Catch:{ zzzv -> 0x059c }
            goto L_0x000a
        L_0x04ff:
            r12.zzC(r13, r3, r14)     // Catch:{ zzzv -> 0x059c }
            r12.zzP(r13, r2)     // Catch:{ zzzv -> 0x059c }
            goto L_0x000a
        L_0x0507:
            r1 = r3 & r5
            long r3 = (long) r1     // Catch:{ zzzv -> 0x059c }
            boolean r1 = r14.zzl()     // Catch:{ zzzv -> 0x059c }
            com.google.android.gms.internal.p007firebaseauthapi.zzacc.zzi(r13, r3, r1)     // Catch:{ zzzv -> 0x059c }
            r12.zzP(r13, r2)     // Catch:{ zzzv -> 0x059c }
            goto L_0x000a
        L_0x0516:
            r1 = r3 & r5
            long r3 = (long) r1     // Catch:{ zzzv -> 0x059c }
            int r1 = r14.zzk()     // Catch:{ zzzv -> 0x059c }
            com.google.android.gms.internal.p007firebaseauthapi.zzacc.zze(r13, r3, r1)     // Catch:{ zzzv -> 0x059c }
            r12.zzP(r13, r2)     // Catch:{ zzzv -> 0x059c }
            goto L_0x000a
        L_0x0525:
            r1 = r3 & r5
            long r3 = (long) r1     // Catch:{ zzzv -> 0x059c }
            long r5 = r14.zzj()     // Catch:{ zzzv -> 0x059c }
            com.google.android.gms.internal.p007firebaseauthapi.zzacc.zzg(r13, r3, r5)     // Catch:{ zzzv -> 0x059c }
            r12.zzP(r13, r2)     // Catch:{ zzzv -> 0x059c }
            goto L_0x000a
        L_0x0534:
            r1 = r3 & r5
            long r3 = (long) r1     // Catch:{ zzzv -> 0x059c }
            int r1 = r14.zzi()     // Catch:{ zzzv -> 0x059c }
            com.google.android.gms.internal.p007firebaseauthapi.zzacc.zze(r13, r3, r1)     // Catch:{ zzzv -> 0x059c }
            r12.zzP(r13, r2)     // Catch:{ zzzv -> 0x059c }
            goto L_0x000a
        L_0x0543:
            r1 = r3 & r5
            long r3 = (long) r1     // Catch:{ zzzv -> 0x059c }
            long r5 = r14.zzg()     // Catch:{ zzzv -> 0x059c }
            com.google.android.gms.internal.p007firebaseauthapi.zzacc.zzg(r13, r3, r5)     // Catch:{ zzzv -> 0x059c }
            r12.zzP(r13, r2)     // Catch:{ zzzv -> 0x059c }
            goto L_0x000a
        L_0x0552:
            r1 = r3 & r5
            long r3 = (long) r1     // Catch:{ zzzv -> 0x059c }
            long r5 = r14.zzh()     // Catch:{ zzzv -> 0x059c }
            com.google.android.gms.internal.p007firebaseauthapi.zzacc.zzg(r13, r3, r5)     // Catch:{ zzzv -> 0x059c }
            r12.zzP(r13, r2)     // Catch:{ zzzv -> 0x059c }
            goto L_0x000a
        L_0x0561:
            r1 = r3 & r5
            long r3 = (long) r1     // Catch:{ zzzv -> 0x059c }
            float r1 = r14.zzf()     // Catch:{ zzzv -> 0x059c }
            com.google.android.gms.internal.p007firebaseauthapi.zzacc.zzk(r13, r3, r1)     // Catch:{ zzzv -> 0x059c }
            r12.zzP(r13, r2)     // Catch:{ zzzv -> 0x059c }
            goto L_0x000a
        L_0x0570:
            r1 = r3 & r5
            long r3 = (long) r1     // Catch:{ zzzv -> 0x059c }
            double r5 = r14.zze()     // Catch:{ zzzv -> 0x059c }
            com.google.android.gms.internal.p007firebaseauthapi.zzacc.zzm(r13, r3, r5)     // Catch:{ zzzv -> 0x059c }
            r12.zzP(r13, r2)     // Catch:{ zzzv -> 0x059c }
            goto L_0x000a
        L_0x057f:
            boolean r1 = r7.zzn(r10, r14)     // Catch:{ zzzv -> 0x059c }
            if (r1 != 0) goto L_0x000a
            int r14 = r12.zzl
        L_0x0587:
            int r15 = r12.zzm
            if (r14 >= r15) goto L_0x0596
            int[] r15 = r12.zzk
            r15 = r15[r14]
            java.lang.Object r10 = r12.zzA(r13, r15, r10, r7)
            int r14 = r14 + 1
            goto L_0x0587
        L_0x0596:
            if (r10 == 0) goto L_0x05c2
            r7.zzl(r13, r10)
            return
        L_0x059c:
            r7.zza(r14)     // Catch:{ all -> 0x0078 }
            if (r10 != 0) goto L_0x05a6
            java.lang.Object r1 = r7.zzk(r13)     // Catch:{ all -> 0x0078 }
            r10 = r1
        L_0x05a6:
            boolean r1 = r7.zzn(r10, r14)     // Catch:{ all -> 0x0078 }
            if (r1 != 0) goto L_0x000a
            int r14 = r12.zzl
        L_0x05ae:
            int r15 = r12.zzm
            if (r14 >= r15) goto L_0x05bd
            int[] r15 = r12.zzk
            r15 = r15[r14]
            java.lang.Object r10 = r12.zzA(r13, r15, r10, r7)
            int r14 = r14 + 1
            goto L_0x05ae
        L_0x05bd:
            if (r10 == 0) goto L_0x05c2
            r7.zzl(r13, r10)
        L_0x05c2:
            return
        L_0x05c3:
            int r15 = r12.zzl
        L_0x05c5:
            int r0 = r12.zzm
            if (r15 >= r0) goto L_0x05d4
            int[] r0 = r12.zzk
            r0 = r0[r15]
            java.lang.Object r10 = r12.zzA(r13, r0, r10, r7)
            int r15 = r15 + 1
            goto L_0x05c5
        L_0x05d4:
            if (r10 == 0) goto L_0x05d9
            r7.zzl(r13, r10)
        L_0x05d9:
            throw r14
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.p007firebaseauthapi.zzaau.zzf(java.lang.Object, com.google.android.gms.internal.firebase-auth-api.zzabc, com.google.android.gms.internal.firebase-auth-api.zzzb):void");
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:109:0x0340, code lost:
        if (r0 != r15) goto L_0x0342;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:111:0x035c, code lost:
        r7 = r33;
        r2 = r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:117:0x0386, code lost:
        if (r0 != r15) goto L_0x0342;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:121:0x03aa, code lost:
        if (r0 != r15) goto L_0x0342;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:72:0x020b, code lost:
        r2 = r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:79:0x0246, code lost:
        r5 = r6 | r24;
        r3 = r7;
        r0 = r8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:86:0x0283, code lost:
        r5 = r6 | r24;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:87:0x0285, code lost:
        r3 = r7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:88:0x0286, code lost:
        r1 = r11;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:91:0x029d, code lost:
        r18 = r6;
        r19 = r7;
        r26 = r10;
        r8 = r11;
        r23 = r20;
        r6 = r25;
        r7 = r33;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final int zzh(T r29, byte[] r30, int r31, int r32, int r33, com.google.android.gms.internal.p007firebaseauthapi.zzxx r34) throws java.io.IOException {
        /*
            r28 = this;
            r15 = r28
            r14 = r29
            r12 = r30
            r13 = r32
            r11 = r33
            r9 = r34
            sun.misc.Unsafe r10 = zzb
            r16 = 0
            r0 = r31
            r1 = 0
            r2 = -1
            r3 = 0
            r5 = 0
            r6 = 1048575(0xfffff, float:1.469367E-39)
        L_0x0019:
            if (r0 >= r13) goto L_0x041e
            int r1 = r0 + 1
            byte r0 = r12[r0]
            if (r0 >= 0) goto L_0x0028
            int r0 = com.google.android.gms.internal.p007firebaseauthapi.zzxy.zzb(r0, r12, r1, r9)
            int r1 = r9.zza
            goto L_0x002d
        L_0x0028:
            r27 = r1
            r1 = r0
            r0 = r27
        L_0x002d:
            int r7 = r1 >>> 3
            r8 = r1 & 7
            r4 = 3
            if (r7 <= r2) goto L_0x003a
            int r3 = r3 / r4
            int r2 = r15.zzT(r7, r3)
            goto L_0x003e
        L_0x003a:
            int r2 = r15.zzS(r7)
        L_0x003e:
            r3 = -1
            if (r2 != r3) goto L_0x0050
            r2 = r0
            r8 = r1
            r18 = r5
            r23 = r7
            r26 = r10
            r7 = r11
            r17 = -1
            r19 = 0
            goto L_0x03ad
        L_0x0050:
            int[] r3 = r15.zzc
            int r19 = r2 + 1
            r3 = r3[r19]
            int r4 = zzF(r3)
            r20 = r1
            r17 = 1048575(0xfffff, float:1.469367E-39)
            r1 = r3 & r17
            r21 = r0
            long r0 = (long) r1
            r22 = r0
            r0 = 17
            if (r4 > r0) goto L_0x02ac
            int[] r0 = r15.zzc
            int r24 = r2 + 2
            r0 = r0[r24]
            int r24 = r0 >>> 20
            r1 = 1
            int r24 = r1 << r24
            r11 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r11
            if (r0 == r6) goto L_0x008b
            r17 = r2
            if (r6 == r11) goto L_0x0083
            long r1 = (long) r6
            r10.putInt(r14, r1, r5)
        L_0x0083:
            long r1 = (long) r0
            int r5 = r10.getInt(r14, r1)
            r25 = r0
            goto L_0x008f
        L_0x008b:
            r17 = r2
            r25 = r6
        L_0x008f:
            r6 = r5
            r0 = 5
            switch(r4) {
                case 0: goto L_0x0267;
                case 1: goto L_0x024b;
                case 2: goto L_0x0227;
                case 3: goto L_0x0227;
                case 4: goto L_0x020e;
                case 5: goto L_0x01ea;
                case 6: goto L_0x01d1;
                case 7: goto L_0x01af;
                case 8: goto L_0x018b;
                case 9: goto L_0x015a;
                case 10: goto L_0x0140;
                case 11: goto L_0x020e;
                case 12: goto L_0x010b;
                case 13: goto L_0x01d1;
                case 14: goto L_0x01ea;
                case 15: goto L_0x00ee;
                case 16: goto L_0x00c2;
                default: goto L_0x0094;
            }
        L_0x0094:
            r11 = r20
            r2 = r21
            r4 = r22
            r0 = 3
            r20 = r7
            r7 = r17
            r17 = -1
            if (r8 != r0) goto L_0x029d
            com.google.android.gms.internal.firebase-auth-api.zzabd r0 = r15.zzw(r7)
            int r1 = r20 << 3
            r8 = r1 | 4
            r1 = r30
            r3 = r32
            r12 = r4
            r4 = r8
            r5 = r34
            int r0 = com.google.android.gms.internal.p007firebaseauthapi.zzxy.zzj(r0, r1, r2, r3, r4, r5)
            r1 = r6 & r24
            if (r1 != 0) goto L_0x0289
            java.lang.Object r1 = r9.zzc
            r10.putObject(r14, r12, r1)
            goto L_0x0296
        L_0x00c2:
            if (r8 != 0) goto L_0x00e2
            r1 = r21
            int r8 = com.google.android.gms.internal.p007firebaseauthapi.zzxy.zzc(r12, r1, r9)
            long r0 = r9.zzb
            long r4 = com.google.android.gms.internal.p007firebaseauthapi.zzyp.zzu(r0)
            r2 = r22
            r0 = r10
            r11 = r20
            r1 = r29
            r20 = r7
            r7 = r17
            r17 = -1
            r0.putLong(r1, r2, r4)
            goto L_0x0246
        L_0x00e2:
            r11 = r20
            r20 = r7
            r7 = r17
            r17 = -1
            r2 = r21
            goto L_0x029d
        L_0x00ee:
            r11 = r20
            r1 = r21
            r2 = r22
            r20 = r7
            r7 = r17
            r17 = -1
            if (r8 != 0) goto L_0x020b
            int r0 = com.google.android.gms.internal.p007firebaseauthapi.zzxy.zza(r12, r1, r9)
            int r1 = r9.zza
            int r1 = com.google.android.gms.internal.p007firebaseauthapi.zzyp.zzt(r1)
            r10.putInt(r14, r2, r1)
            goto L_0x0283
        L_0x010b:
            r11 = r20
            r1 = r21
            r2 = r22
            r20 = r7
            r7 = r17
            r17 = -1
            if (r8 != 0) goto L_0x020b
            int r0 = com.google.android.gms.internal.p007firebaseauthapi.zzxy.zza(r12, r1, r9)
            int r1 = r9.zza
            com.google.android.gms.internal.firebase-auth-api.zzzs r4 = r15.zzy(r7)
            if (r4 == 0) goto L_0x013b
            boolean r4 = r4.zza()
            if (r4 == 0) goto L_0x012c
            goto L_0x013b
        L_0x012c:
            com.google.android.gms.internal.firebase-auth-api.zzabs r2 = zzg(r29)
            long r3 = (long) r1
            java.lang.Long r1 = java.lang.Long.valueOf(r3)
            r2.zzh(r11, r1)
            r5 = r6
            goto L_0x0285
        L_0x013b:
            r10.putInt(r14, r2, r1)
            goto L_0x0283
        L_0x0140:
            r11 = r20
            r1 = r21
            r2 = r22
            r0 = 2
            r20 = r7
            r7 = r17
            r17 = -1
            if (r8 != r0) goto L_0x020b
            int r0 = com.google.android.gms.internal.p007firebaseauthapi.zzxy.zzh(r12, r1, r9)
            java.lang.Object r1 = r9.zzc
            r10.putObject(r14, r2, r1)
            goto L_0x0283
        L_0x015a:
            r11 = r20
            r1 = r21
            r2 = r22
            r0 = 2
            r20 = r7
            r7 = r17
            r17 = -1
            if (r8 != r0) goto L_0x020b
            com.google.android.gms.internal.firebase-auth-api.zzabd r0 = r15.zzw(r7)
            int r0 = com.google.android.gms.internal.p007firebaseauthapi.zzxy.zzi(r0, r12, r1, r13, r9)
            r1 = r6 & r24
            if (r1 != 0) goto L_0x017c
            java.lang.Object r1 = r9.zzc
            r10.putObject(r14, r2, r1)
            goto L_0x0283
        L_0x017c:
            java.lang.Object r1 = r10.getObject(r14, r2)
            java.lang.Object r4 = r9.zzc
            java.lang.Object r1 = com.google.android.gms.internal.p007firebaseauthapi.zzzu.zzi(r1, r4)
            r10.putObject(r14, r2, r1)
            goto L_0x0283
        L_0x018b:
            r11 = r20
            r1 = r21
            r4 = r22
            r0 = 2
            r20 = r7
            r7 = r17
            r17 = -1
            if (r8 != r0) goto L_0x020b
            r0 = 536870912(0x20000000, float:1.0842022E-19)
            r0 = r0 & r3
            if (r0 != 0) goto L_0x01a4
            int r0 = com.google.android.gms.internal.p007firebaseauthapi.zzxy.zzf(r12, r1, r9)
            goto L_0x01a8
        L_0x01a4:
            int r0 = com.google.android.gms.internal.p007firebaseauthapi.zzxy.zzg(r12, r1, r9)
        L_0x01a8:
            java.lang.Object r1 = r9.zzc
            r10.putObject(r14, r4, r1)
            goto L_0x0283
        L_0x01af:
            r11 = r20
            r1 = r21
            r4 = r22
            r20 = r7
            r7 = r17
            r17 = -1
            if (r8 != 0) goto L_0x020b
            int r0 = com.google.android.gms.internal.p007firebaseauthapi.zzxy.zzc(r12, r1, r9)
            long r1 = r9.zzb
            r21 = 0
            int r3 = (r1 > r21 ? 1 : (r1 == r21 ? 0 : -1))
            if (r3 == 0) goto L_0x01cb
            r1 = 1
            goto L_0x01cc
        L_0x01cb:
            r1 = 0
        L_0x01cc:
            com.google.android.gms.internal.p007firebaseauthapi.zzacc.zzi(r14, r4, r1)
            goto L_0x0283
        L_0x01d1:
            r11 = r20
            r1 = r21
            r4 = r22
            r20 = r7
            r7 = r17
            r17 = -1
            if (r8 != r0) goto L_0x020b
            int r0 = com.google.android.gms.internal.p007firebaseauthapi.zzxy.zzd(r12, r1)
            r10.putInt(r14, r4, r0)
            int r0 = r1 + 4
            goto L_0x0283
        L_0x01ea:
            r11 = r20
            r1 = r21
            r4 = r22
            r0 = 1
            r20 = r7
            r7 = r17
            r17 = -1
            if (r8 != r0) goto L_0x020b
            long r21 = com.google.android.gms.internal.p007firebaseauthapi.zzxy.zze(r12, r1)
            r8 = r1
            r0 = r10
            r1 = r29
            r2 = r4
            r4 = r21
            r0.putLong(r1, r2, r4)
            int r0 = r8 + 8
            goto L_0x0283
        L_0x020b:
            r2 = r1
            goto L_0x029d
        L_0x020e:
            r11 = r20
            r2 = r21
            r4 = r22
            r20 = r7
            r7 = r17
            r17 = -1
            if (r8 != 0) goto L_0x029d
            int r0 = com.google.android.gms.internal.p007firebaseauthapi.zzxy.zza(r12, r2, r9)
            int r1 = r9.zza
            r10.putInt(r14, r4, r1)
            goto L_0x0283
        L_0x0227:
            r11 = r20
            r2 = r21
            r4 = r22
            r20 = r7
            r7 = r17
            r17 = -1
            if (r8 != 0) goto L_0x029d
            int r8 = com.google.android.gms.internal.p007firebaseauthapi.zzxy.zzc(r12, r2, r9)
            long r2 = r9.zzb
            r0 = r10
            r1 = r29
            r21 = r2
            r2 = r4
            r4 = r21
            r0.putLong(r1, r2, r4)
        L_0x0246:
            r5 = r6 | r24
            r3 = r7
            r0 = r8
            goto L_0x0286
        L_0x024b:
            r11 = r20
            r2 = r21
            r4 = r22
            r20 = r7
            r7 = r17
            r17 = -1
            if (r8 != r0) goto L_0x029d
            int r0 = com.google.android.gms.internal.p007firebaseauthapi.zzxy.zzd(r12, r2)
            float r0 = java.lang.Float.intBitsToFloat(r0)
            com.google.android.gms.internal.p007firebaseauthapi.zzacc.zzk(r14, r4, r0)
            int r0 = r2 + 4
            goto L_0x0283
        L_0x0267:
            r11 = r20
            r2 = r21
            r4 = r22
            r0 = 1
            r20 = r7
            r7 = r17
            r17 = -1
            if (r8 != r0) goto L_0x029d
            long r0 = com.google.android.gms.internal.p007firebaseauthapi.zzxy.zze(r12, r2)
            double r0 = java.lang.Double.longBitsToDouble(r0)
            com.google.android.gms.internal.p007firebaseauthapi.zzacc.zzm(r14, r4, r0)
            int r0 = r2 + 8
        L_0x0283:
            r5 = r6 | r24
        L_0x0285:
            r3 = r7
        L_0x0286:
            r1 = r11
            goto L_0x02f8
        L_0x0289:
            java.lang.Object r1 = r10.getObject(r14, r12)
            java.lang.Object r2 = r9.zzc
            java.lang.Object r1 = com.google.android.gms.internal.p007firebaseauthapi.zzzu.zzi(r1, r2)
            r10.putObject(r14, r12, r1)
        L_0x0296:
            r5 = r6 | r24
            r12 = r30
            r13 = r32
            goto L_0x0285
        L_0x029d:
            r18 = r6
            r19 = r7
            r26 = r10
            r8 = r11
            r23 = r20
            r6 = r25
            r7 = r33
            goto L_0x03ad
        L_0x02ac:
            r11 = r20
            r12 = r22
            r17 = -1
            r20 = r7
            r7 = r2
            r2 = r21
            r0 = 27
            if (r4 != r0) goto L_0x030f
            r0 = 2
            if (r8 != r0) goto L_0x0300
            java.lang.Object r0 = r10.getObject(r14, r12)
            com.google.android.gms.internal.firebase-auth-api.zzzt r0 = (com.google.android.gms.internal.p007firebaseauthapi.zzzt) r0
            boolean r1 = r0.zza()
            if (r1 != 0) goto L_0x02db
            int r1 = r0.size()
            if (r1 != 0) goto L_0x02d3
            r1 = 10
            goto L_0x02d4
        L_0x02d3:
            int r1 = r1 + r1
        L_0x02d4:
            com.google.android.gms.internal.firebase-auth-api.zzzt r0 = r0.zze(r1)
            r10.putObject(r14, r12, r0)
        L_0x02db:
            r8 = r0
            com.google.android.gms.internal.firebase-auth-api.zzabd r0 = r15.zzw(r7)
            r1 = r11
            r3 = r2
            r2 = r30
            r4 = r32
            r18 = r5
            r5 = r8
            r25 = r6
            r6 = r34
            int r0 = com.google.android.gms.internal.p007firebaseauthapi.zzxy.zzm(r0, r1, r2, r3, r4, r5, r6)
            r12 = r30
            r13 = r32
            r3 = r7
            r5 = r18
        L_0x02f8:
            r2 = r20
            r6 = r25
            r11 = r33
            goto L_0x0019
        L_0x0300:
            r18 = r5
            r25 = r6
            r15 = r2
            r19 = r7
            r26 = r10
            r23 = r20
            r20 = r11
            goto L_0x0389
        L_0x030f:
            r18 = r5
            r25 = r6
            r6 = r2
            r0 = 49
            if (r4 > r0) goto L_0x0360
            long r2 = (long) r3
            r0 = r28
            r1 = r29
            r21 = r2
            r2 = r30
            r3 = r6
            r5 = r4
            r4 = r32
            r31 = r5
            r5 = r11
            r15 = r6
            r6 = r20
            r19 = r7
            r23 = r20
            r7 = r8
            r8 = r19
            r26 = r10
            r9 = r21
            r20 = r11
            r11 = r31
            r14 = r34
            int r0 = r0.zzt(r1, r2, r3, r4, r5, r6, r7, r8, r9, r11, r12, r14)
            if (r0 == r15) goto L_0x035c
        L_0x0342:
            r15 = r28
            r14 = r29
            r12 = r30
            r13 = r32
            r11 = r33
            r9 = r34
            r5 = r18
            r3 = r19
            r1 = r20
            r2 = r23
            r6 = r25
            r10 = r26
            goto L_0x0019
        L_0x035c:
            r7 = r33
            r2 = r0
            goto L_0x038c
        L_0x0360:
            r31 = r4
            r15 = r6
            r19 = r7
            r26 = r10
            r23 = r20
            r20 = r11
            r0 = 50
            r9 = r31
            if (r9 != r0) goto L_0x0391
            r0 = 2
            if (r8 != r0) goto L_0x0389
            r0 = r28
            r1 = r29
            r2 = r30
            r3 = r15
            r4 = r32
            r5 = r19
            r6 = r12
            r8 = r34
            int r0 = r0.zzu(r1, r2, r3, r4, r5, r6, r8)
            if (r0 == r15) goto L_0x035c
            goto L_0x0342
        L_0x0389:
            r7 = r33
            r2 = r15
        L_0x038c:
            r8 = r20
            r6 = r25
            goto L_0x03ad
        L_0x0391:
            r0 = r28
            r1 = r29
            r2 = r30
            r10 = r3
            r3 = r15
            r4 = r32
            r5 = r20
            r6 = r23
            r7 = r8
            r8 = r10
            r10 = r12
            r12 = r19
            r13 = r34
            int r0 = r0.zzv(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r12, r13)
            if (r0 == r15) goto L_0x035c
            goto L_0x0342
        L_0x03ad:
            if (r8 != r7) goto L_0x03bf
            if (r7 == 0) goto L_0x03bf
            r3 = 1048575(0xfffff, float:1.469367E-39)
            r9 = r28
            r12 = r29
            r0 = r2
            r1 = r8
            r5 = r18
            r2 = 0
            goto L_0x042b
        L_0x03bf:
            r9 = r28
            boolean r0 = r9.zzh
            if (r0 == 0) goto L_0x03f7
            r10 = r34
            com.google.android.gms.internal.firebase-auth-api.zzzb r0 = r10.zzd
            com.google.android.gms.internal.firebase-auth-api.zzzb r1 = com.google.android.gms.internal.p007firebaseauthapi.zzzb.zza()
            if (r0 == r1) goto L_0x03f4
            com.google.android.gms.internal.firebase-auth-api.zzaar r0 = r9.zzg
            com.google.android.gms.internal.firebase-auth-api.zzzb r1 = r10.zzd
            r11 = r23
            com.google.android.gms.internal.firebase-auth-api.zzzm r0 = r1.zzb(r0, r11)
            if (r0 != 0) goto L_0x03ed
            com.google.android.gms.internal.firebase-auth-api.zzabs r4 = zzg(r29)
            r0 = r8
            r1 = r30
            r3 = r32
            r5 = r34
            int r0 = com.google.android.gms.internal.p007firebaseauthapi.zzxy.zzn(r0, r1, r2, r3, r4, r5)
            r12 = r29
            goto L_0x040c
        L_0x03ed:
            r12 = r29
            r0 = r12
            com.google.android.gms.internal.firebase-auth-api.zzzl r0 = (com.google.android.gms.internal.p007firebaseauthapi.zzzl) r0
            r2 = 0
            throw r2
        L_0x03f4:
            r12 = r29
            goto L_0x03fb
        L_0x03f7:
            r12 = r29
            r10 = r34
        L_0x03fb:
            r11 = r23
            com.google.android.gms.internal.firebase-auth-api.zzabs r4 = zzg(r29)
            r0 = r8
            r1 = r30
            r3 = r32
            r5 = r34
            int r0 = com.google.android.gms.internal.p007firebaseauthapi.zzxy.zzn(r0, r1, r2, r3, r4, r5)
        L_0x040c:
            r13 = r32
            r1 = r8
            r15 = r9
            r9 = r10
            r2 = r11
            r14 = r12
            r5 = r18
            r3 = r19
            r10 = r26
            r12 = r30
            r11 = r7
            goto L_0x0019
        L_0x041e:
            r18 = r5
            r25 = r6
            r26 = r10
            r7 = r11
            r12 = r14
            r9 = r15
            r2 = 0
            r3 = 1048575(0xfffff, float:1.469367E-39)
        L_0x042b:
            if (r6 == r3) goto L_0x0433
            long r3 = (long) r6
            r6 = r26
            r6.putInt(r12, r3, r5)
        L_0x0433:
            int r3 = r9.zzl
        L_0x0435:
            int r4 = r9.zzm
            if (r3 >= r4) goto L_0x0445
            int[] r4 = r9.zzk
            r4 = r4[r3]
            com.google.android.gms.internal.firebase-auth-api.zzabr<?, ?> r5 = r9.zzo
            r9.zzA(r12, r4, r2, r5)
            int r3 = r3 + 1
            goto L_0x0435
        L_0x0445:
            if (r7 != 0) goto L_0x0451
            r2 = r32
            if (r0 != r2) goto L_0x044c
            goto L_0x0457
        L_0x044c:
            com.google.android.gms.internal.firebase-auth-api.zzzw r0 = com.google.android.gms.internal.p007firebaseauthapi.zzzw.zzh()
            throw r0
        L_0x0451:
            r2 = r32
            if (r0 > r2) goto L_0x0458
            if (r1 != r7) goto L_0x0458
        L_0x0457:
            return r0
        L_0x0458:
            com.google.android.gms.internal.firebase-auth-api.zzzw r0 = com.google.android.gms.internal.p007firebaseauthapi.zzzw.zzh()
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.p007firebaseauthapi.zzaau.zzh(java.lang.Object, byte[], int, int, int, com.google.android.gms.internal.firebase-auth-api.zzxx):int");
    }

    public final void zzi(T t, byte[] bArr, int i, int i2, zzxx zzxx) throws IOException {
        if (this.zzj) {
            zzz(t, bArr, i, i2, zzxx);
        } else {
            zzh(t, bArr, i, i2, 0, zzxx);
        }
    }

    public final void zzj(T t) {
        int i;
        int i2 = this.zzl;
        while (true) {
            i = this.zzm;
            if (i2 >= i) {
                break;
            }
            long zzD = (long) (zzD(this.zzk[i2]) & 1048575);
            Object zzn2 = zzacc.zzn(t, zzD);
            if (zzn2 != null) {
                ((zzaal) zzn2).zzd();
                zzacc.zzo(t, zzD, zzn2);
            }
            i2++;
        }
        int length = this.zzk.length;
        while (i < length) {
            this.zzn.zzb(t, (long) this.zzk[i]);
            i++;
        }
        this.zzo.zzm(t);
        if (this.zzh) {
            this.zzp.zzd(t);
        }
    }

    public final boolean zzk(T t) {
        int i;
        int i2;
        T t2 = t;
        int i3 = 1048575;
        int i4 = 0;
        int i5 = 0;
        while (i5 < this.zzl) {
            int i6 = this.zzk[i5];
            int i7 = this.zzc[i6];
            int zzD = zzD(i6);
            int i8 = this.zzc[i6 + 2];
            int i9 = i8 & 1048575;
            int i10 = 1 << (i8 >>> 20);
            if (i9 != i3) {
                if (i9 != 1048575) {
                    i4 = zzb.getInt(t2, (long) i9);
                }
                i = i4;
                i2 = i9;
            } else {
                i2 = i3;
                i = i4;
            }
            if ((268435456 & zzD) != 0 && !zzN(t, i6, i2, i, i10)) {
                return false;
            }
            int zzF = zzF(zzD);
            if (zzF != 9 && zzF != 17) {
                if (zzF != 27) {
                    if (zzF == 60 || zzF == 68) {
                        if (zzQ(t2, i7, i6) && !zzB(t2, zzD, zzw(i6))) {
                            return false;
                        }
                    } else if (zzF != 49) {
                        if (zzF == 50 && !((zzaal) zzacc.zzn(t2, (long) (zzD & 1048575))).isEmpty()) {
                            zzaak zzaak = (zzaak) zzx(i6);
                            throw null;
                        }
                    }
                }
                List list = (List) zzacc.zzn(t2, (long) (zzD & 1048575));
                if (!list.isEmpty()) {
                    zzabd zzw = zzw(i6);
                    for (int i11 = 0; i11 < list.size(); i11++) {
                        if (!zzw.zzk(list.get(i11))) {
                            return false;
                        }
                    }
                    continue;
                } else {
                    continue;
                }
            } else if (zzN(t, i6, i2, i, i10) && !zzB(t2, zzD, zzw(i6))) {
                return false;
            }
            i5++;
            i3 = i2;
            i4 = i;
        }
        if (!this.zzh) {
            return true;
        }
        this.zzp.zzb(t2);
        throw null;
    }

    public final void zzn(T t, zzyx zzyx) throws IOException {
        if (!this.zzj) {
            zzV(t, zzyx);
        } else if (!this.zzh) {
            int length = this.zzc.length;
            for (int i = 0; i < length; i += 3) {
                int zzD = zzD(i);
                int i2 = this.zzc[i];
                switch (zzF(zzD)) {
                    case 0:
                        if (!zzO(t, i)) {
                            break;
                        } else {
                            zzyx.zzf(i2, zzacc.zzl(t, (long) (zzD & 1048575)));
                            break;
                        }
                    case 1:
                        if (!zzO(t, i)) {
                            break;
                        } else {
                            zzyx.zze(i2, zzacc.zzj(t, (long) (zzD & 1048575)));
                            break;
                        }
                    case 2:
                        if (!zzO(t, i)) {
                            break;
                        } else {
                            zzyx.zzc(i2, zzacc.zzf(t, (long) (zzD & 1048575)));
                            break;
                        }
                    case 3:
                        if (!zzO(t, i)) {
                            break;
                        } else {
                            zzyx.zzh(i2, zzacc.zzf(t, (long) (zzD & 1048575)));
                            break;
                        }
                    case 4:
                        if (!zzO(t, i)) {
                            break;
                        } else {
                            zzyx.zzi(i2, zzacc.zzd(t, (long) (zzD & 1048575)));
                            break;
                        }
                    case 5:
                        if (!zzO(t, i)) {
                            break;
                        } else {
                            zzyx.zzj(i2, zzacc.zzf(t, (long) (zzD & 1048575)));
                            break;
                        }
                    case 6:
                        if (!zzO(t, i)) {
                            break;
                        } else {
                            zzyx.zzk(i2, zzacc.zzd(t, (long) (zzD & 1048575)));
                            break;
                        }
                    case 7:
                        if (!zzO(t, i)) {
                            break;
                        } else {
                            zzyx.zzl(i2, zzacc.zzh(t, (long) (zzD & 1048575)));
                            break;
                        }
                    case 8:
                        if (!zzO(t, i)) {
                            break;
                        } else {
                            zzX(i2, zzacc.zzn(t, (long) (zzD & 1048575)), zzyx);
                            break;
                        }
                    case 9:
                        if (!zzO(t, i)) {
                            break;
                        } else {
                            zzyx.zzr(i2, zzacc.zzn(t, (long) (zzD & 1048575)), zzw(i));
                            break;
                        }
                    case 10:
                        if (!zzO(t, i)) {
                            break;
                        } else {
                            zzyx.zzn(i2, (zzym) zzacc.zzn(t, (long) (zzD & 1048575)));
                            break;
                        }
                    case 11:
                        if (!zzO(t, i)) {
                            break;
                        } else {
                            zzyx.zzo(i2, zzacc.zzd(t, (long) (zzD & 1048575)));
                            break;
                        }
                    case 12:
                        if (!zzO(t, i)) {
                            break;
                        } else {
                            zzyx.zzg(i2, zzacc.zzd(t, (long) (zzD & 1048575)));
                            break;
                        }
                    case 13:
                        if (!zzO(t, i)) {
                            break;
                        } else {
                            zzyx.zzb(i2, zzacc.zzd(t, (long) (zzD & 1048575)));
                            break;
                        }
                    case 14:
                        if (!zzO(t, i)) {
                            break;
                        } else {
                            zzyx.zzd(i2, zzacc.zzf(t, (long) (zzD & 1048575)));
                            break;
                        }
                    case 15:
                        if (!zzO(t, i)) {
                            break;
                        } else {
                            zzyx.zzp(i2, zzacc.zzd(t, (long) (zzD & 1048575)));
                            break;
                        }
                    case 16:
                        if (!zzO(t, i)) {
                            break;
                        } else {
                            zzyx.zzq(i2, zzacc.zzf(t, (long) (zzD & 1048575)));
                            break;
                        }
                    case 17:
                        if (!zzO(t, i)) {
                            break;
                        } else {
                            zzyx.zzs(i2, zzacc.zzn(t, (long) (zzD & 1048575)), zzw(i));
                            break;
                        }
                    case 18:
                        zzabf.zzJ(this.zzc[i], (List) zzacc.zzn(t, (long) (zzD & 1048575)), zzyx, false);
                        break;
                    case 19:
                        zzabf.zzK(this.zzc[i], (List) zzacc.zzn(t, (long) (zzD & 1048575)), zzyx, false);
                        break;
                    case 20:
                        zzabf.zzL(this.zzc[i], (List) zzacc.zzn(t, (long) (zzD & 1048575)), zzyx, false);
                        break;
                    case 21:
                        zzabf.zzM(this.zzc[i], (List) zzacc.zzn(t, (long) (zzD & 1048575)), zzyx, false);
                        break;
                    case 22:
                        zzabf.zzQ(this.zzc[i], (List) zzacc.zzn(t, (long) (zzD & 1048575)), zzyx, false);
                        break;
                    case 23:
                        zzabf.zzO(this.zzc[i], (List) zzacc.zzn(t, (long) (zzD & 1048575)), zzyx, false);
                        break;
                    case 24:
                        zzabf.zzT(this.zzc[i], (List) zzacc.zzn(t, (long) (zzD & 1048575)), zzyx, false);
                        break;
                    case 25:
                        zzabf.zzW(this.zzc[i], (List) zzacc.zzn(t, (long) (zzD & 1048575)), zzyx, false);
                        break;
                    case 26:
                        zzabf.zzX(this.zzc[i], (List) zzacc.zzn(t, (long) (zzD & 1048575)), zzyx);
                        break;
                    case 27:
                        zzabf.zzZ(this.zzc[i], (List) zzacc.zzn(t, (long) (zzD & 1048575)), zzyx, zzw(i));
                        break;
                    case 28:
                        zzabf.zzY(this.zzc[i], (List) zzacc.zzn(t, (long) (zzD & 1048575)), zzyx);
                        break;
                    case 29:
                        zzabf.zzR(this.zzc[i], (List) zzacc.zzn(t, (long) (zzD & 1048575)), zzyx, false);
                        break;
                    case 30:
                        zzabf.zzV(this.zzc[i], (List) zzacc.zzn(t, (long) (zzD & 1048575)), zzyx, false);
                        break;
                    case 31:
                        zzabf.zzU(this.zzc[i], (List) zzacc.zzn(t, (long) (zzD & 1048575)), zzyx, false);
                        break;
                    case 32:
                        zzabf.zzP(this.zzc[i], (List) zzacc.zzn(t, (long) (zzD & 1048575)), zzyx, false);
                        break;
                    case 33:
                        zzabf.zzS(this.zzc[i], (List) zzacc.zzn(t, (long) (zzD & 1048575)), zzyx, false);
                        break;
                    case 34:
                        zzabf.zzN(this.zzc[i], (List) zzacc.zzn(t, (long) (zzD & 1048575)), zzyx, false);
                        break;
                    case 35:
                        zzabf.zzJ(this.zzc[i], (List) zzacc.zzn(t, (long) (zzD & 1048575)), zzyx, true);
                        break;
                    case 36:
                        zzabf.zzK(this.zzc[i], (List) zzacc.zzn(t, (long) (zzD & 1048575)), zzyx, true);
                        break;
                    case 37:
                        zzabf.zzL(this.zzc[i], (List) zzacc.zzn(t, (long) (zzD & 1048575)), zzyx, true);
                        break;
                    case 38:
                        zzabf.zzM(this.zzc[i], (List) zzacc.zzn(t, (long) (zzD & 1048575)), zzyx, true);
                        break;
                    case 39:
                        zzabf.zzQ(this.zzc[i], (List) zzacc.zzn(t, (long) (zzD & 1048575)), zzyx, true);
                        break;
                    case 40:
                        zzabf.zzO(this.zzc[i], (List) zzacc.zzn(t, (long) (zzD & 1048575)), zzyx, true);
                        break;
                    case 41:
                        zzabf.zzT(this.zzc[i], (List) zzacc.zzn(t, (long) (zzD & 1048575)), zzyx, true);
                        break;
                    case 42:
                        zzabf.zzW(this.zzc[i], (List) zzacc.zzn(t, (long) (zzD & 1048575)), zzyx, true);
                        break;
                    case 43:
                        zzabf.zzR(this.zzc[i], (List) zzacc.zzn(t, (long) (zzD & 1048575)), zzyx, true);
                        break;
                    case 44:
                        zzabf.zzV(this.zzc[i], (List) zzacc.zzn(t, (long) (zzD & 1048575)), zzyx, true);
                        break;
                    case 45:
                        zzabf.zzU(this.zzc[i], (List) zzacc.zzn(t, (long) (zzD & 1048575)), zzyx, true);
                        break;
                    case 46:
                        zzabf.zzP(this.zzc[i], (List) zzacc.zzn(t, (long) (zzD & 1048575)), zzyx, true);
                        break;
                    case 47:
                        zzabf.zzS(this.zzc[i], (List) zzacc.zzn(t, (long) (zzD & 1048575)), zzyx, true);
                        break;
                    case 48:
                        zzabf.zzN(this.zzc[i], (List) zzacc.zzn(t, (long) (zzD & 1048575)), zzyx, true);
                        break;
                    case 49:
                        zzabf.zzaa(this.zzc[i], (List) zzacc.zzn(t, (long) (zzD & 1048575)), zzyx, zzw(i));
                        break;
                    case 50:
                        zzW(zzyx, i2, zzacc.zzn(t, (long) (zzD & 1048575)), i);
                        break;
                    case 51:
                        if (!zzQ(t, i2, i)) {
                            break;
                        } else {
                            zzyx.zzf(i2, zzH(t, (long) (zzD & 1048575)));
                            break;
                        }
                    case 52:
                        if (!zzQ(t, i2, i)) {
                            break;
                        } else {
                            zzyx.zze(i2, zzI(t, (long) (zzD & 1048575)));
                            break;
                        }
                    case 53:
                        if (!zzQ(t, i2, i)) {
                            break;
                        } else {
                            zzyx.zzc(i2, zzK(t, (long) (zzD & 1048575)));
                            break;
                        }
                    case 54:
                        if (!zzQ(t, i2, i)) {
                            break;
                        } else {
                            zzyx.zzh(i2, zzK(t, (long) (zzD & 1048575)));
                            break;
                        }
                    case 55:
                        if (!zzQ(t, i2, i)) {
                            break;
                        } else {
                            zzyx.zzi(i2, zzJ(t, (long) (zzD & 1048575)));
                            break;
                        }
                    case 56:
                        if (!zzQ(t, i2, i)) {
                            break;
                        } else {
                            zzyx.zzj(i2, zzK(t, (long) (zzD & 1048575)));
                            break;
                        }
                    case 57:
                        if (!zzQ(t, i2, i)) {
                            break;
                        } else {
                            zzyx.zzk(i2, zzJ(t, (long) (zzD & 1048575)));
                            break;
                        }
                    case 58:
                        if (!zzQ(t, i2, i)) {
                            break;
                        } else {
                            zzyx.zzl(i2, zzL(t, (long) (zzD & 1048575)));
                            break;
                        }
                    case 59:
                        if (!zzQ(t, i2, i)) {
                            break;
                        } else {
                            zzX(i2, zzacc.zzn(t, (long) (zzD & 1048575)), zzyx);
                            break;
                        }
                    case 60:
                        if (!zzQ(t, i2, i)) {
                            break;
                        } else {
                            zzyx.zzr(i2, zzacc.zzn(t, (long) (zzD & 1048575)), zzw(i));
                            break;
                        }
                    case 61:
                        if (!zzQ(t, i2, i)) {
                            break;
                        } else {
                            zzyx.zzn(i2, (zzym) zzacc.zzn(t, (long) (zzD & 1048575)));
                            break;
                        }
                    case 62:
                        if (!zzQ(t, i2, i)) {
                            break;
                        } else {
                            zzyx.zzo(i2, zzJ(t, (long) (zzD & 1048575)));
                            break;
                        }
                    case 63:
                        if (!zzQ(t, i2, i)) {
                            break;
                        } else {
                            zzyx.zzg(i2, zzJ(t, (long) (zzD & 1048575)));
                            break;
                        }
                    case 64:
                        if (!zzQ(t, i2, i)) {
                            break;
                        } else {
                            zzyx.zzb(i2, zzJ(t, (long) (zzD & 1048575)));
                            break;
                        }
                    case 65:
                        if (!zzQ(t, i2, i)) {
                            break;
                        } else {
                            zzyx.zzd(i2, zzK(t, (long) (zzD & 1048575)));
                            break;
                        }
                    case 66:
                        if (!zzQ(t, i2, i)) {
                            break;
                        } else {
                            zzyx.zzp(i2, zzJ(t, (long) (zzD & 1048575)));
                            break;
                        }
                    case 67:
                        if (!zzQ(t, i2, i)) {
                            break;
                        } else {
                            zzyx.zzq(i2, zzK(t, (long) (zzD & 1048575)));
                            break;
                        }
                    case 68:
                        if (!zzQ(t, i2, i)) {
                            break;
                        } else {
                            zzyx.zzs(i2, zzacc.zzn(t, (long) (zzD & 1048575)), zzw(i));
                            break;
                        }
                }
            }
            zzabr<?, ?> zzabr = this.zzo;
            zzabr.zzr(zzabr.zzj(t), zzyx);
        } else {
            this.zzp.zzb(t);
            throw null;
        }
    }

    public final void zzd(T t, T t2) {
        Objects.requireNonNull(t2);
        for (int i = 0; i < this.zzc.length; i += 3) {
            int zzD = zzD(i);
            long j = (long) (1048575 & zzD);
            int i2 = this.zzc[i];
            switch (zzF(zzD)) {
                case 0:
                    if (!zzO(t2, i)) {
                        break;
                    } else {
                        zzacc.zzm(t, j, zzacc.zzl(t2, j));
                        zzP(t, i);
                        break;
                    }
                case 1:
                    if (!zzO(t2, i)) {
                        break;
                    } else {
                        zzacc.zzk(t, j, zzacc.zzj(t2, j));
                        zzP(t, i);
                        break;
                    }
                case 2:
                    if (!zzO(t2, i)) {
                        break;
                    } else {
                        zzacc.zzg(t, j, zzacc.zzf(t2, j));
                        zzP(t, i);
                        break;
                    }
                case 3:
                    if (!zzO(t2, i)) {
                        break;
                    } else {
                        zzacc.zzg(t, j, zzacc.zzf(t2, j));
                        zzP(t, i);
                        break;
                    }
                case 4:
                    if (!zzO(t2, i)) {
                        break;
                    } else {
                        zzacc.zze(t, j, zzacc.zzd(t2, j));
                        zzP(t, i);
                        break;
                    }
                case 5:
                    if (!zzO(t2, i)) {
                        break;
                    } else {
                        zzacc.zzg(t, j, zzacc.zzf(t2, j));
                        zzP(t, i);
                        break;
                    }
                case 6:
                    if (!zzO(t2, i)) {
                        break;
                    } else {
                        zzacc.zze(t, j, zzacc.zzd(t2, j));
                        zzP(t, i);
                        break;
                    }
                case 7:
                    if (!zzO(t2, i)) {
                        break;
                    } else {
                        zzacc.zzi(t, j, zzacc.zzh(t2, j));
                        zzP(t, i);
                        break;
                    }
                case 8:
                    if (!zzO(t2, i)) {
                        break;
                    } else {
                        zzacc.zzo(t, j, zzacc.zzn(t2, j));
                        zzP(t, i);
                        break;
                    }
                case 9:
                    zzp(t, t2, i);
                    break;
                case 10:
                    if (!zzO(t2, i)) {
                        break;
                    } else {
                        zzacc.zzo(t, j, zzacc.zzn(t2, j));
                        zzP(t, i);
                        break;
                    }
                case 11:
                    if (!zzO(t2, i)) {
                        break;
                    } else {
                        zzacc.zze(t, j, zzacc.zzd(t2, j));
                        zzP(t, i);
                        break;
                    }
                case 12:
                    if (!zzO(t2, i)) {
                        break;
                    } else {
                        zzacc.zze(t, j, zzacc.zzd(t2, j));
                        zzP(t, i);
                        break;
                    }
                case 13:
                    if (!zzO(t2, i)) {
                        break;
                    } else {
                        zzacc.zze(t, j, zzacc.zzd(t2, j));
                        zzP(t, i);
                        break;
                    }
                case 14:
                    if (!zzO(t2, i)) {
                        break;
                    } else {
                        zzacc.zzg(t, j, zzacc.zzf(t2, j));
                        zzP(t, i);
                        break;
                    }
                case 15:
                    if (!zzO(t2, i)) {
                        break;
                    } else {
                        zzacc.zze(t, j, zzacc.zzd(t2, j));
                        zzP(t, i);
                        break;
                    }
                case 16:
                    if (!zzO(t2, i)) {
                        break;
                    } else {
                        zzacc.zzg(t, j, zzacc.zzf(t2, j));
                        zzP(t, i);
                        break;
                    }
                case 17:
                    zzp(t, t2, i);
                    break;
                case 18:
                case 19:
                case 20:
                case 21:
                case 22:
                case 23:
                case 24:
                case 25:
                case 26:
                case 27:
                case 28:
                case 29:
                case 30:
                case 31:
                case 32:
                case 33:
                case 34:
                case 35:
                case 36:
                case 37:
                case 38:
                case 39:
                case 40:
                case 41:
                case 42:
                case 43:
                case 44:
                case 45:
                case 46:
                case 47:
                case 48:
                case 49:
                    this.zzn.zzc(t, t2, j);
                    break;
                case 50:
                    zzabf.zzI(this.zzr, t, t2, j);
                    break;
                case 51:
                case 52:
                case 53:
                case 54:
                case 55:
                case 56:
                case 57:
                case 58:
                case 59:
                    if (!zzQ(t2, i2, i)) {
                        break;
                    } else {
                        zzacc.zzo(t, j, zzacc.zzn(t2, j));
                        zzR(t, i2, i);
                        break;
                    }
                case 60:
                    zzq(t, t2, i);
                    break;
                case 61:
                case 62:
                case 63:
                case 64:
                case 65:
                case 66:
                case 67:
                    if (!zzQ(t2, i2, i)) {
                        break;
                    } else {
                        zzacc.zzo(t, j, zzacc.zzn(t2, j));
                        zzR(t, i2, i);
                        break;
                    }
                case 68:
                    zzq(t, t2, i);
                    break;
            }
        }
        zzabf.zzF(this.zzo, t, t2);
        if (this.zzh) {
            zzabf.zzE(this.zzp, t, t2);
        }
    }
}

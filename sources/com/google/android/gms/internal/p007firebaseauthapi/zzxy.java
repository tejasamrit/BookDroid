package com.google.android.gms.internal.p007firebaseauthapi;

import com.google.common.base.Ascii;
import java.io.IOException;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzxy */
/* compiled from: com.google.firebase:firebase-auth@@20.0.1 */
final class zzxy {
    static int zza(byte[] bArr, int i, zzxx zzxx) {
        int i2 = i + 1;
        byte b = bArr[i];
        if (b < 0) {
            return zzb(b, bArr, i2, zzxx);
        }
        zzxx.zza = b;
        return i2;
    }

    static int zzb(int i, byte[] bArr, int i2, zzxx zzxx) {
        int i3 = i & 127;
        int i4 = i2 + 1;
        byte b = bArr[i2];
        if (b >= 0) {
            zzxx.zza = i3 | (b << 7);
            return i4;
        }
        int i5 = i3 | ((b & Byte.MAX_VALUE) << 7);
        int i6 = i4 + 1;
        byte b2 = bArr[i4];
        if (b2 >= 0) {
            zzxx.zza = i5 | (b2 << Ascii.f234SO);
            return i6;
        }
        int i7 = i5 | ((b2 & Byte.MAX_VALUE) << Ascii.f234SO);
        int i8 = i6 + 1;
        byte b3 = bArr[i6];
        if (b3 >= 0) {
            zzxx.zza = i7 | (b3 << Ascii.NAK);
            return i8;
        }
        int i9 = i7 | ((b3 & Byte.MAX_VALUE) << Ascii.NAK);
        int i10 = i8 + 1;
        byte b4 = bArr[i8];
        if (b4 >= 0) {
            zzxx.zza = i9 | (b4 << Ascii.f227FS);
            return i10;
        }
        int i11 = i9 | ((b4 & Byte.MAX_VALUE) << Ascii.f227FS);
        while (true) {
            int i12 = i10 + 1;
            if (bArr[i10] < 0) {
                i10 = i12;
            } else {
                zzxx.zza = i11;
                return i12;
            }
        }
    }

    static int zzc(byte[] bArr, int i, zzxx zzxx) {
        int i2 = i + 1;
        long j = (long) bArr[i];
        if (j < 0) {
            int i3 = i2 + 1;
            byte b = bArr[i2];
            long j2 = (j & 127) | (((long) (b & Byte.MAX_VALUE)) << 7);
            int i4 = 7;
            while (b < 0) {
                int i5 = i3 + 1;
                byte b2 = bArr[i3];
                i4 += 7;
                j2 |= ((long) (b2 & Byte.MAX_VALUE)) << i4;
                int i6 = i5;
                b = b2;
                i3 = i6;
            }
            zzxx.zzb = j2;
            return i3;
        }
        zzxx.zzb = j;
        return i2;
    }

    static int zzd(byte[] bArr, int i) {
        return ((bArr[i + 3] & 255) << Ascii.CAN) | (bArr[i] & 255) | ((bArr[i + 1] & 255) << 8) | ((bArr[i + 2] & 255) << Ascii.DLE);
    }

    static long zze(byte[] bArr, int i) {
        return ((((long) bArr[i + 7]) & 255) << 56) | (((long) bArr[i]) & 255) | ((((long) bArr[i + 1]) & 255) << 8) | ((((long) bArr[i + 2]) & 255) << 16) | ((((long) bArr[i + 3]) & 255) << 24) | ((((long) bArr[i + 4]) & 255) << 32) | ((((long) bArr[i + 5]) & 255) << 40) | ((((long) bArr[i + 6]) & 255) << 48);
    }

    static int zzf(byte[] bArr, int i, zzxx zzxx) throws zzzw {
        int zza = zza(bArr, i, zzxx);
        int i2 = zzxx.zza;
        if (i2 < 0) {
            throw zzzw.zzc();
        } else if (i2 == 0) {
            zzxx.zzc = "";
            return zza;
        } else {
            zzxx.zzc = new String(bArr, zza, i2, zzzu.zza);
            return zza + i2;
        }
    }

    static int zzg(byte[] bArr, int i, zzxx zzxx) throws zzzw {
        int zza = zza(bArr, i, zzxx);
        int i2 = zzxx.zza;
        if (i2 < 0) {
            throw zzzw.zzc();
        } else if (i2 == 0) {
            zzxx.zzc = "";
            return zza;
        } else {
            zzxx.zzc = zzaci.zze(bArr, zza, i2);
            return zza + i2;
        }
    }

    static int zzh(byte[] bArr, int i, zzxx zzxx) throws zzzw {
        int zza = zza(bArr, i, zzxx);
        int i2 = zzxx.zza;
        if (i2 < 0) {
            throw zzzw.zzc();
        } else if (i2 > bArr.length - zza) {
            throw zzzw.zzb();
        } else if (i2 == 0) {
            zzxx.zzc = zzym.zzb;
            return zza;
        } else {
            zzxx.zzc = zzym.zzl(bArr, zza, i2);
            return zza + i2;
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v2, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v0, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v5, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v6, resolved type: byte} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static int zzi(com.google.android.gms.internal.p007firebaseauthapi.zzabd r6, byte[] r7, int r8, int r9, com.google.android.gms.internal.p007firebaseauthapi.zzxx r10) throws java.io.IOException {
        /*
            int r0 = r8 + 1
            byte r8 = r7[r8]
            if (r8 >= 0) goto L_0x000c
            int r0 = zzb(r8, r7, r0, r10)
            int r8 = r10.zza
        L_0x000c:
            r3 = r0
            if (r8 < 0) goto L_0x0025
            int r9 = r9 - r3
            if (r8 > r9) goto L_0x0025
            java.lang.Object r9 = r6.zza()
            int r8 = r8 + r3
            r0 = r6
            r1 = r9
            r2 = r7
            r4 = r8
            r5 = r10
            r0.zzi(r1, r2, r3, r4, r5)
            r6.zzj(r9)
            r10.zzc = r9
            return r8
        L_0x0025:
            com.google.android.gms.internal.firebase-auth-api.zzzw r6 = com.google.android.gms.internal.p007firebaseauthapi.zzzw.zzb()
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.p007firebaseauthapi.zzxy.zzi(com.google.android.gms.internal.firebase-auth-api.zzabd, byte[], int, int, com.google.android.gms.internal.firebase-auth-api.zzxx):int");
    }

    static int zzj(zzabd zzabd, byte[] bArr, int i, int i2, int i3, zzxx zzxx) throws IOException {
        zzaau zzaau = (zzaau) zzabd;
        Object zza = zzaau.zza();
        int zzh = zzaau.zzh(zza, bArr, i, i2, i3, zzxx);
        zzaau.zzj(zza);
        zzxx.zzc = zza;
        return zzh;
    }

    static int zzk(int i, byte[] bArr, int i2, int i3, zzzt<?> zzzt, zzxx zzxx) {
        zzzp zzzp = (zzzp) zzzt;
        int zza = zza(bArr, i2, zzxx);
        zzzp.zzf(zzxx.zza);
        while (zza < i3) {
            int zza2 = zza(bArr, zza, zzxx);
            if (i != zzxx.zza) {
                break;
            }
            zza = zza(bArr, zza2, zzxx);
            zzzp.zzf(zzxx.zza);
        }
        return zza;
    }

    static int zzl(byte[] bArr, int i, zzzt<?> zzzt, zzxx zzxx) throws IOException {
        zzzp zzzp = (zzzp) zzzt;
        int zza = zza(bArr, i, zzxx);
        int i2 = zzxx.zza + zza;
        while (zza < i2) {
            zza = zza(bArr, zza, zzxx);
            zzzp.zzf(zzxx.zza);
        }
        if (zza == i2) {
            return zza;
        }
        throw zzzw.zzb();
    }

    static int zzm(zzabd<?> zzabd, int i, byte[] bArr, int i2, int i3, zzzt<?> zzzt, zzxx zzxx) throws IOException {
        int zzi = zzi(zzabd, bArr, i2, i3, zzxx);
        zzzt.add(zzxx.zzc);
        while (zzi < i3) {
            int zza = zza(bArr, zzi, zzxx);
            if (i != zzxx.zza) {
                break;
            }
            zzi = zzi(zzabd, bArr, zza, i3, zzxx);
            zzzt.add(zzxx.zzc);
        }
        return zzi;
    }

    static int zzn(int i, byte[] bArr, int i2, int i3, zzabs zzabs, zzxx zzxx) throws zzzw {
        if ((i >>> 3) != 0) {
            int i4 = i & 7;
            if (i4 == 0) {
                int zzc = zzc(bArr, i2, zzxx);
                zzabs.zzh(i, Long.valueOf(zzxx.zzb));
                return zzc;
            } else if (i4 == 1) {
                zzabs.zzh(i, Long.valueOf(zze(bArr, i2)));
                return i2 + 8;
            } else if (i4 == 2) {
                int zza = zza(bArr, i2, zzxx);
                int i5 = zzxx.zza;
                if (i5 < 0) {
                    throw zzzw.zzc();
                } else if (i5 <= bArr.length - zza) {
                    if (i5 == 0) {
                        zzabs.zzh(i, zzym.zzb);
                    } else {
                        zzabs.zzh(i, zzym.zzl(bArr, zza, i5));
                    }
                    return zza + i5;
                } else {
                    throw zzzw.zzb();
                }
            } else if (i4 == 3) {
                int i6 = (i & -8) | 4;
                zzabs zzb = zzabs.zzb();
                int i7 = 0;
                while (true) {
                    if (i2 >= i3) {
                        break;
                    }
                    int zza2 = zza(bArr, i2, zzxx);
                    int i8 = zzxx.zza;
                    if (i8 == i6) {
                        i7 = i8;
                        i2 = zza2;
                        break;
                    }
                    i7 = i8;
                    i2 = zzn(i8, bArr, zza2, i3, zzb, zzxx);
                }
                if (i2 > i3 || i7 != i6) {
                    throw zzzw.zzh();
                }
                zzabs.zzh(i, zzb);
                return i2;
            } else if (i4 == 5) {
                zzabs.zzh(i, Integer.valueOf(zzd(bArr, i2)));
                return i2 + 4;
            } else {
                throw zzzw.zze();
            }
        } else {
            throw zzzw.zze();
        }
    }
}

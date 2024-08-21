package com.google.android.gms.internal.p007firebaseauthapi;

import com.google.common.base.Ascii;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzach */
/* compiled from: com.google.firebase:firebase-auth@@20.0.1 */
final class zzach extends zzace {
    zzach() {
    }

    private static int zze(byte[] bArr, int i, long j, int i2) {
        if (i2 == 0) {
            return zzaci.zzj(i);
        }
        if (i2 == 1) {
            return zzaci.zzk(i, zzacc.zzp(bArr, j));
        }
        if (i2 == 2) {
            return zzaci.zzl(i, zzacc.zzp(bArr, j), zzacc.zzp(bArr, j + 1));
        }
        throw new AssertionError();
    }

    /* access modifiers changed from: package-private */
    public final int zzb(int i, byte[] bArr, int i2, int i3) {
        int i4;
        long j;
        byte[] bArr2 = bArr;
        int i5 = i2;
        int i6 = i3;
        int length = bArr2.length;
        if ((i5 | i6 | (length - i6)) >= 0) {
            long j2 = (long) i5;
            int i7 = (int) (((long) i6) - j2);
            if (i7 >= 16) {
                long j3 = j2;
                i4 = 0;
                while (true) {
                    if (i4 >= i7) {
                        i4 = i7;
                        break;
                    }
                    long j4 = j3 + 1;
                    if (zzacc.zzp(bArr2, j3) < 0) {
                        break;
                    }
                    i4++;
                    j3 = j4;
                }
            } else {
                i4 = 0;
            }
            int i8 = i7 - i4;
            long j5 = j2 + ((long) i4);
            while (true) {
                byte b = 0;
                while (true) {
                    if (i8 <= 0) {
                        break;
                    }
                    long j6 = j5 + 1;
                    b = zzacc.zzp(bArr2, j5);
                    if (b < 0) {
                        j5 = j6;
                        break;
                    }
                    i8--;
                    j5 = j6;
                }
                if (i8 == 0) {
                    return 0;
                }
                int i9 = i8 - 1;
                if (b >= -32) {
                    if (b >= -16) {
                        if (i9 >= 3) {
                            i8 = i9 - 3;
                            long j7 = j5 + 1;
                            byte zzp = zzacc.zzp(bArr2, j5);
                            if (zzp > -65 || (((b << Ascii.f227FS) + (zzp + 112)) >> 30) != 0) {
                                break;
                            }
                            long j8 = j7 + 1;
                            if (zzacc.zzp(bArr2, j7) > -65) {
                                break;
                            }
                            j = j8 + 1;
                            if (zzacc.zzp(bArr2, j8) > -65) {
                                break;
                            }
                        } else {
                            return zze(bArr2, b, j5, i9);
                        }
                    } else if (i9 >= 2) {
                        i8 = i9 - 2;
                        long j9 = j5 + 1;
                        byte zzp2 = zzacc.zzp(bArr2, j5);
                        if (zzp2 > -65 || ((b == -32 && zzp2 < -96) || (b == -19 && zzp2 >= -96))) {
                            break;
                        }
                        j5 = j9 + 1;
                        if (zzacc.zzp(bArr2, j9) > -65) {
                            break;
                        }
                    } else {
                        return zze(bArr2, b, j5, i9);
                    }
                } else if (i9 != 0) {
                    i8 = i9 - 1;
                    if (b < -62) {
                        break;
                    }
                    j = j5 + 1;
                    if (zzacc.zzp(bArr2, j5) > -65) {
                        break;
                    }
                } else {
                    return b;
                }
                j5 = j;
            }
            return -1;
        }
        throw new ArrayIndexOutOfBoundsException(String.format("Array length=%d, index=%d, limit=%d", new Object[]{Integer.valueOf(length), Integer.valueOf(i2), Integer.valueOf(i3)}));
    }

    /* access modifiers changed from: package-private */
    public final String zzc(byte[] bArr, int i, int i2) throws zzzw {
        int i3;
        int length = bArr.length;
        if ((i | i2 | ((length - i) - i2)) >= 0) {
            int i4 = i + i2;
            char[] cArr = new char[i2];
            int i5 = 0;
            while (r12 < i4) {
                byte zzp = zzacc.zzp(bArr, (long) r12);
                if (!zzacd.zza(zzp)) {
                    break;
                }
                i = r12 + 1;
                cArr[i3] = (char) zzp;
                i5 = i3 + 1;
            }
            while (r12 < i4) {
                int i6 = r12 + 1;
                byte zzp2 = zzacc.zzp(bArr, (long) r12);
                if (zzacd.zza(zzp2)) {
                    int i7 = i3 + 1;
                    cArr[i3] = (char) zzp2;
                    r12 = i6;
                    while (true) {
                        i3 = i7;
                        if (r12 >= i4) {
                            break;
                        }
                        byte zzp3 = zzacc.zzp(bArr, (long) r12);
                        if (!zzacd.zza(zzp3)) {
                            break;
                        }
                        r12++;
                        i7 = i3 + 1;
                        cArr[i3] = (char) zzp3;
                    }
                } else if (zzacd.zzb(zzp2)) {
                    if (i6 < i4) {
                        zzacd.zzc(zzp2, zzacc.zzp(bArr, (long) i6), cArr, i3);
                        r12 = i6 + 1;
                        i3++;
                    } else {
                        throw zzzw.zzi();
                    }
                } else if (zzacd.zzd(zzp2)) {
                    if (i6 < i4 - 1) {
                        int i8 = i6 + 1;
                        zzacd.zze(zzp2, zzacc.zzp(bArr, (long) i6), zzacc.zzp(bArr, (long) i8), cArr, i3);
                        r12 = i8 + 1;
                        i3++;
                    } else {
                        throw zzzw.zzi();
                    }
                } else if (i6 < i4 - 2) {
                    int i9 = i6 + 1;
                    int i10 = i9 + 1;
                    zzacd.zzf(zzp2, zzacc.zzp(bArr, (long) i6), zzacc.zzp(bArr, (long) i9), zzacc.zzp(bArr, (long) i10), cArr, i3);
                    i3 += 2;
                    r12 = i10 + 1;
                } else {
                    throw zzzw.zzi();
                }
            }
            return new String(cArr, 0, i3);
        }
        throw new ArrayIndexOutOfBoundsException(String.format("buffer length=%d, index=%d, size=%d", new Object[]{Integer.valueOf(length), Integer.valueOf(i), Integer.valueOf(i2)}));
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:11:0x0031 A[LOOP:1: B:11:0x0031->B:36:0x00f6, LOOP_START, PHI: r2 r3 r4 r11 
      PHI: (r2v3 int) = (r2v2 int), (r2v5 int) binds: [B:10:0x002f, B:36:0x00f6] A[DONT_GENERATE, DONT_INLINE]
      PHI: (r3v2 char) = (r3v1 char), (r3v3 char) binds: [B:10:0x002f, B:36:0x00f6] A[DONT_GENERATE, DONT_INLINE]
      PHI: (r4v3 long) = (r4v2 long), (r4v5 long) binds: [B:10:0x002f, B:36:0x00f6] A[DONT_GENERATE, DONT_INLINE]
      PHI: (r11v3 long) = (r11v2 long), (r11v4 long) binds: [B:10:0x002f, B:36:0x00f6] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARNING: Removed duplicated region for block: B:52:0x0141  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final int zzd(java.lang.CharSequence r23, byte[] r24, int r25, int r26) {
        /*
            r22 = this;
            r0 = r23
            r1 = r24
            r2 = r25
            r3 = r26
            long r4 = (long) r2
            long r6 = (long) r3
            long r6 = r6 + r4
            int r8 = r23.length()
            java.lang.String r9 = " at index "
            java.lang.String r10 = "Failed writing "
            if (r8 > r3) goto L_0x0143
            int r11 = r1.length
            int r11 = r11 - r3
            if (r11 < r2) goto L_0x0143
            r2 = 0
        L_0x001a:
            r3 = 128(0x80, float:1.794E-43)
            r11 = 1
            if (r2 >= r8) goto L_0x002f
            char r13 = r0.charAt(r2)
            if (r13 >= r3) goto L_0x002f
            long r11 = r11 + r4
            byte r3 = (byte) r13
            com.google.android.gms.internal.p007firebaseauthapi.zzacc.zzq(r1, r4, r3)
            int r2 = r2 + 1
            r4 = r11
            goto L_0x001a
        L_0x002f:
            if (r2 == r8) goto L_0x0141
        L_0x0031:
            if (r2 >= r8) goto L_0x013f
            char r13 = r0.charAt(r2)
            if (r13 >= r3) goto L_0x0049
            int r14 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r14 >= 0) goto L_0x0049
            long r14 = r4 + r11
            byte r13 = (byte) r13
            com.google.android.gms.internal.p007firebaseauthapi.zzacc.zzq(r1, r4, r13)
            r4 = r11
            r12 = r14
        L_0x0045:
            r11 = 128(0x80, float:1.794E-43)
            goto L_0x00f6
        L_0x0049:
            r14 = 2048(0x800, float:2.87E-42)
            if (r13 >= r14) goto L_0x0072
            r14 = -2
            long r14 = r14 + r6
            int r16 = (r4 > r14 ? 1 : (r4 == r14 ? 0 : -1))
            if (r16 > 0) goto L_0x0072
            long r14 = r4 + r11
            int r3 = r13 >>> 6
            r3 = r3 | 960(0x3c0, float:1.345E-42)
            byte r3 = (byte) r3
            com.google.android.gms.internal.p007firebaseauthapi.zzacc.zzq(r1, r4, r3)
            long r3 = r14 + r11
            r5 = r13 & 63
            r13 = 128(0x80, float:1.794E-43)
            r5 = r5 | r13
            byte r5 = (byte) r5
            com.google.android.gms.internal.p007firebaseauthapi.zzacc.zzq(r1, r14, r5)
            r20 = r11
            r11 = 128(0x80, float:1.794E-43)
            r12 = r3
            r4 = r20
            goto L_0x00f6
        L_0x0072:
            r3 = 57343(0xdfff, float:8.0355E-41)
            r14 = 55296(0xd800, float:7.7486E-41)
            if (r13 < r14) goto L_0x007c
            if (r13 <= r3) goto L_0x00aa
        L_0x007c:
            r15 = -3
            long r15 = r15 + r6
            int r17 = (r4 > r15 ? 1 : (r4 == r15 ? 0 : -1))
            if (r17 > 0) goto L_0x00aa
            long r14 = r4 + r11
            int r3 = r13 >>> 12
            r3 = r3 | 480(0x1e0, float:6.73E-43)
            byte r3 = (byte) r3
            com.google.android.gms.internal.p007firebaseauthapi.zzacc.zzq(r1, r4, r3)
            long r3 = r14 + r11
            int r5 = r13 >>> 6
            r5 = r5 & 63
            r11 = 128(0x80, float:1.794E-43)
            r5 = r5 | r11
            byte r5 = (byte) r5
            com.google.android.gms.internal.p007firebaseauthapi.zzacc.zzq(r1, r14, r5)
            r14 = 1
            long r18 = r3 + r14
            r5 = r13 & 63
            r5 = r5 | r11
            byte r5 = (byte) r5
            com.google.android.gms.internal.p007firebaseauthapi.zzacc.zzq(r1, r3, r5)
            r12 = r18
            r4 = 1
            goto L_0x0045
        L_0x00aa:
            r11 = -4
            long r11 = r11 + r6
            int r15 = (r4 > r11 ? 1 : (r4 == r11 ? 0 : -1))
            if (r15 > 0) goto L_0x010a
            int r3 = r2 + 1
            if (r3 == r8) goto L_0x0102
            char r2 = r0.charAt(r3)
            boolean r11 = java.lang.Character.isSurrogatePair(r13, r2)
            if (r11 == 0) goto L_0x0101
            int r2 = java.lang.Character.toCodePoint(r13, r2)
            r11 = 1
            long r13 = r4 + r11
            int r15 = r2 >>> 18
            r15 = r15 | 240(0xf0, float:3.36E-43)
            byte r15 = (byte) r15
            com.google.android.gms.internal.p007firebaseauthapi.zzacc.zzq(r1, r4, r15)
            long r4 = r13 + r11
            int r15 = r2 >>> 12
            r15 = r15 & 63
            r11 = 128(0x80, float:1.794E-43)
            r12 = r15 | 128(0x80, float:1.794E-43)
            byte r12 = (byte) r12
            com.google.android.gms.internal.p007firebaseauthapi.zzacc.zzq(r1, r13, r12)
            r12 = 1
            long r14 = r4 + r12
            int r16 = r2 >>> 6
            r12 = r16 & 63
            r12 = r12 | r11
            byte r12 = (byte) r12
            com.google.android.gms.internal.p007firebaseauthapi.zzacc.zzq(r1, r4, r12)
            r4 = 1
            long r12 = r14 + r4
            r2 = r2 & 63
            r2 = r2 | r11
            byte r2 = (byte) r2
            com.google.android.gms.internal.p007firebaseauthapi.zzacc.zzq(r1, r14, r2)
            r2 = r3
        L_0x00f6:
            int r2 = r2 + 1
            r3 = 128(0x80, float:1.794E-43)
            r20 = r4
            r4 = r12
            r11 = r20
            goto L_0x0031
        L_0x0101:
            r2 = r3
        L_0x0102:
            com.google.android.gms.internal.firebase-auth-api.zzacg r0 = new com.google.android.gms.internal.firebase-auth-api.zzacg
            int r2 = r2 + -1
            r0.<init>(r2, r8)
            throw r0
        L_0x010a:
            if (r13 < r14) goto L_0x0122
            if (r13 > r3) goto L_0x0122
            int r1 = r2 + 1
            if (r1 == r8) goto L_0x011c
            char r0 = r0.charAt(r1)
            boolean r0 = java.lang.Character.isSurrogatePair(r13, r0)
            if (r0 != 0) goto L_0x0122
        L_0x011c:
            com.google.android.gms.internal.firebase-auth-api.zzacg r0 = new com.google.android.gms.internal.firebase-auth-api.zzacg
            r0.<init>(r2, r8)
            throw r0
        L_0x0122:
            java.lang.ArrayIndexOutOfBoundsException r0 = new java.lang.ArrayIndexOutOfBoundsException
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r2 = 46
            r1.<init>(r2)
            r1.append(r10)
            r1.append(r13)
            r1.append(r9)
            r1.append(r4)
            java.lang.String r1 = r1.toString()
            r0.<init>(r1)
            throw r0
        L_0x013f:
            int r0 = (int) r4
            return r0
        L_0x0141:
            int r0 = (int) r4
            return r0
        L_0x0143:
            java.lang.ArrayIndexOutOfBoundsException r1 = new java.lang.ArrayIndexOutOfBoundsException
            int r8 = r8 + -1
            char r0 = r0.charAt(r8)
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r5 = 37
            r4.<init>(r5)
            r4.append(r10)
            r4.append(r0)
            r4.append(r9)
            int r0 = r2 + r3
            r4.append(r0)
            java.lang.String r0 = r4.toString()
            r1.<init>(r0)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.p007firebaseauthapi.zzach.zzd(java.lang.CharSequence, byte[], int, int):int");
    }
}

package com.google.android.gms.internal.p007firebaseauthapi;

import java.io.IOException;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzyt */
/* compiled from: com.google.firebase:firebase-auth@@20.0.1 */
final class zzyt extends zzyw {
    private final byte[] zza;
    private final int zzb;
    private int zzc;

    public final void zzO(int i, int i2) throws IOException {
        zzo((i << 3) | i2);
    }

    public final void zzP(int i, int i2) throws IOException {
        zzo(i << 3);
        zzn(i2);
    }

    public final void zzQ(int i, int i2) throws IOException {
        zzo(i << 3);
        zzo(i2);
    }

    public final void zzR(int i, int i2) throws IOException {
        zzo((i << 3) | 5);
        zzp(i2);
    }

    public final void zzS(int i, long j) throws IOException {
        zzo(i << 3);
        zzq(j);
    }

    public final void zza(byte[] bArr, int i, int i2) throws IOException {
        zzs(bArr, 0, i2);
    }

    public final int zzb() {
        return this.zzb - this.zzc;
    }

    public final void zzh(int i, long j) throws IOException {
        zzo((i << 3) | 1);
        zzr(j);
    }

    public final void zzi(int i, boolean z) throws IOException {
        zzo(i << 3);
        zzm(z ? (byte) 1 : 0);
    }

    public final void zzj(int i, String str) throws IOException {
        zzo((i << 3) | 2);
        zzt(str);
    }

    public final void zzk(int i, zzym zzym) throws IOException {
        zzo((i << 3) | 2);
        zzo(zzym.zzc());
        zzym.zzg(this);
    }

    /* access modifiers changed from: package-private */
    public final void zzl(int i, zzaar zzaar, zzabd zzabd) throws IOException {
        zzo((i << 3) | 2);
        zzxu zzxu = (zzxu) zzaar;
        int zzq = zzxu.zzq();
        if (zzq == -1) {
            zzq = zzabd.zze(zzxu);
            zzxu.zzr(zzq);
        }
        zzo(zzq);
        zzabd.zzn(zzaar, this.zze);
    }

    public final void zzn(int i) throws IOException {
        if (i >= 0) {
            zzo(i);
        } else {
            zzq((long) i);
        }
    }

    public final void zzo(int i) throws IOException {
        if (zzyw.zzb && !zzxw.zza()) {
            int i2 = this.zzb;
            int i3 = this.zzc;
            if (i2 - i3 >= 5) {
                if ((i & -128) == 0) {
                    byte[] bArr = this.zza;
                    this.zzc = i3 + 1;
                    zzacc.zzq(bArr, (long) i3, (byte) i);
                    return;
                }
                byte[] bArr2 = this.zza;
                this.zzc = i3 + 1;
                zzacc.zzq(bArr2, (long) i3, (byte) (i | 128));
                int i4 = i >>> 7;
                if ((i4 & -128) == 0) {
                    byte[] bArr3 = this.zza;
                    int i5 = this.zzc;
                    this.zzc = i5 + 1;
                    zzacc.zzq(bArr3, (long) i5, (byte) i4);
                    return;
                }
                byte[] bArr4 = this.zza;
                int i6 = this.zzc;
                this.zzc = i6 + 1;
                zzacc.zzq(bArr4, (long) i6, (byte) (i4 | 128));
                int i7 = i4 >>> 7;
                if ((i7 & -128) == 0) {
                    byte[] bArr5 = this.zza;
                    int i8 = this.zzc;
                    this.zzc = i8 + 1;
                    zzacc.zzq(bArr5, (long) i8, (byte) i7);
                    return;
                }
                byte[] bArr6 = this.zza;
                int i9 = this.zzc;
                this.zzc = i9 + 1;
                zzacc.zzq(bArr6, (long) i9, (byte) (i7 | 128));
                int i10 = i7 >>> 7;
                if ((i10 & -128) == 0) {
                    byte[] bArr7 = this.zza;
                    int i11 = this.zzc;
                    this.zzc = i11 + 1;
                    zzacc.zzq(bArr7, (long) i11, (byte) i10);
                    return;
                }
                byte[] bArr8 = this.zza;
                int i12 = this.zzc;
                this.zzc = i12 + 1;
                zzacc.zzq(bArr8, (long) i12, (byte) (i10 | 128));
                byte[] bArr9 = this.zza;
                int i13 = this.zzc;
                this.zzc = i13 + 1;
                zzacc.zzq(bArr9, (long) i13, (byte) (i10 >>> 7));
                return;
            }
        }
        while ((i & -128) != 0) {
            byte[] bArr10 = this.zza;
            int i14 = this.zzc;
            this.zzc = i14 + 1;
            bArr10[i14] = (byte) ((i & 127) | 128);
            i >>>= 7;
        }
        try {
            byte[] bArr11 = this.zza;
            int i15 = this.zzc;
            this.zzc = i15 + 1;
            bArr11[i15] = (byte) i;
        } catch (IndexOutOfBoundsException e) {
            throw new zzyu(String.format("Pos: %d, limit: %d, len: %d", new Object[]{Integer.valueOf(this.zzc), Integer.valueOf(this.zzb), 1}), e);
        }
    }

    public final void zzp(int i) throws IOException {
        try {
            byte[] bArr = this.zza;
            int i2 = this.zzc;
            int i3 = i2 + 1;
            this.zzc = i3;
            bArr[i2] = (byte) (i & 255);
            int i4 = i3 + 1;
            this.zzc = i4;
            bArr[i3] = (byte) ((i >> 8) & 255);
            int i5 = i4 + 1;
            this.zzc = i5;
            bArr[i4] = (byte) ((i >> 16) & 255);
            this.zzc = i5 + 1;
            bArr[i5] = (byte) ((i >> 24) & 255);
        } catch (IndexOutOfBoundsException e) {
            throw new zzyu(String.format("Pos: %d, limit: %d, len: %d", new Object[]{Integer.valueOf(this.zzc), Integer.valueOf(this.zzb), 1}), e);
        }
    }

    public final void zzr(long j) throws IOException {
        try {
            byte[] bArr = this.zza;
            int i = this.zzc;
            int i2 = i + 1;
            this.zzc = i2;
            bArr[i] = (byte) (((int) j) & 255);
            int i3 = i2 + 1;
            this.zzc = i3;
            bArr[i2] = (byte) (((int) (j >> 8)) & 255);
            int i4 = i3 + 1;
            this.zzc = i4;
            bArr[i3] = (byte) (((int) (j >> 16)) & 255);
            int i5 = i4 + 1;
            this.zzc = i5;
            bArr[i4] = (byte) (((int) (j >> 24)) & 255);
            int i6 = i5 + 1;
            this.zzc = i6;
            bArr[i5] = (byte) (((int) (j >> 32)) & 255);
            int i7 = i6 + 1;
            this.zzc = i7;
            bArr[i6] = (byte) (((int) (j >> 40)) & 255);
            int i8 = i7 + 1;
            this.zzc = i8;
            bArr[i7] = (byte) (((int) (j >> 48)) & 255);
            this.zzc = i8 + 1;
            bArr[i8] = (byte) (((int) (j >> 56)) & 255);
        } catch (IndexOutOfBoundsException e) {
            throw new zzyu(String.format("Pos: %d, limit: %d, len: %d", new Object[]{Integer.valueOf(this.zzc), Integer.valueOf(this.zzb), 1}), e);
        }
    }

    public final void zzt(String str) throws IOException {
        int i = this.zzc;
        try {
            int zzA = zzA(str.length() * 3);
            int zzA2 = zzA(str.length());
            if (zzA2 == zzA) {
                int i2 = i + zzA2;
                this.zzc = i2;
                int zzd = zzaci.zzd(str, this.zza, i2, this.zzb - i2);
                this.zzc = i;
                zzo((zzd - i) - zzA2);
                this.zzc = zzd;
                return;
            }
            zzo(zzaci.zzc(str));
            byte[] bArr = this.zza;
            int i3 = this.zzc;
            this.zzc = zzaci.zzd(str, bArr, i3, this.zzb - i3);
        } catch (zzacg e) {
            this.zzc = i;
            zzJ(str, e);
        } catch (IndexOutOfBoundsException e2) {
            throw new zzyu(e2);
        }
    }

    public final void zzu() {
    }

    zzyt(byte[] bArr, int i, int i2) {
        super((zzyr) null);
        int length = bArr.length;
        if (((length - i2) | i2) >= 0) {
            this.zza = bArr;
            this.zzc = 0;
            this.zzb = i2;
            return;
        }
        throw new IllegalArgumentException(String.format("Array range is invalid. Buffer.length=%d, offset=%d, length=%d", new Object[]{Integer.valueOf(length), 0, Integer.valueOf(i2)}));
    }

    public final void zzm(byte b) throws IOException {
        try {
            byte[] bArr = this.zza;
            int i = this.zzc;
            this.zzc = i + 1;
            bArr[i] = b;
        } catch (IndexOutOfBoundsException e) {
            throw new zzyu(String.format("Pos: %d, limit: %d, len: %d", new Object[]{Integer.valueOf(this.zzc), Integer.valueOf(this.zzb), 1}), e);
        }
    }

    public final void zzs(byte[] bArr, int i, int i2) throws IOException {
        try {
            System.arraycopy(bArr, 0, this.zza, this.zzc, i2);
            this.zzc += i2;
        } catch (IndexOutOfBoundsException e) {
            throw new zzyu(String.format("Pos: %d, limit: %d, len: %d", new Object[]{Integer.valueOf(this.zzc), Integer.valueOf(this.zzb), Integer.valueOf(i2)}), e);
        }
    }

    public final void zzq(long j) throws IOException {
        if (!zzyw.zzb || this.zzb - this.zzc < 10) {
            while ((j & -128) != 0) {
                byte[] bArr = this.zza;
                int i = this.zzc;
                this.zzc = i + 1;
                bArr[i] = (byte) ((((int) j) & 127) | 128);
                j >>>= 7;
            }
            try {
                byte[] bArr2 = this.zza;
                int i2 = this.zzc;
                this.zzc = i2 + 1;
                bArr2[i2] = (byte) ((int) j);
            } catch (IndexOutOfBoundsException e) {
                throw new zzyu(String.format("Pos: %d, limit: %d, len: %d", new Object[]{Integer.valueOf(this.zzc), Integer.valueOf(this.zzb), 1}), e);
            }
        } else {
            while ((j & -128) != 0) {
                byte[] bArr3 = this.zza;
                int i3 = this.zzc;
                this.zzc = i3 + 1;
                zzacc.zzq(bArr3, (long) i3, (byte) ((((int) j) & 127) | 128));
                j >>>= 7;
            }
            byte[] bArr4 = this.zza;
            int i4 = this.zzc;
            this.zzc = i4 + 1;
            zzacc.zzq(bArr4, (long) i4, (byte) ((int) j));
        }
    }
}

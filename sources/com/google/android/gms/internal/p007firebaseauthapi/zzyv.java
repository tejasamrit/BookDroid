package com.google.android.gms.internal.p007firebaseauthapi;

import java.io.IOException;
import java.io.OutputStream;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzyv */
/* compiled from: com.google.firebase:firebase-auth@@20.0.1 */
final class zzyv extends zzys {
    private final OutputStream zzf;

    zzyv(OutputStream outputStream, int i) {
        super(i);
        this.zzf = outputStream;
    }

    private final void zzM(int i) throws IOException {
        if (this.zzb - this.zzc < i) {
            zzN();
        }
    }

    private final void zzN() throws IOException {
        this.zzf.write(this.zza, 0, this.zzc);
        this.zzc = 0;
    }

    public final void zzO(int i, int i2) throws IOException {
        zzo((i << 3) | i2);
    }

    public final void zzP(int i, int i2) throws IOException {
        zzM(20);
        zzd(i << 3);
        if (i2 >= 0) {
            zzd(i2);
        } else {
            zze((long) i2);
        }
    }

    public final void zzQ(int i, int i2) throws IOException {
        zzM(20);
        zzd(i << 3);
        zzd(i2);
    }

    public final void zzR(int i, int i2) throws IOException {
        zzM(14);
        zzd((i << 3) | 5);
        zzf(i2);
    }

    public final void zzS(int i, long j) throws IOException {
        zzM(20);
        zzd(i << 3);
        zze(j);
    }

    public final void zza(byte[] bArr, int i, int i2) throws IOException {
        zzt(bArr, 0, i2);
    }

    public final void zzh(int i, long j) throws IOException {
        zzM(18);
        zzd((i << 3) | 1);
        zzg(j);
    }

    public final void zzi(int i, boolean z) throws IOException {
        zzM(11);
        zzd(i << 3);
        zzc(z ? (byte) 1 : 0);
    }

    public final void zzj(int i, String str) throws IOException {
        zzo((i << 3) | 2);
        zzs(str);
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

    public final void zzm(byte b) throws IOException {
        if (this.zzc == this.zzb) {
            zzN();
        }
        zzc(b);
    }

    public final void zzn(int i) throws IOException {
        if (i >= 0) {
            zzo(i);
        } else {
            zzq((long) i);
        }
    }

    public final void zzo(int i) throws IOException {
        zzM(5);
        zzd(i);
    }

    public final void zzp(int i) throws IOException {
        zzM(4);
        zzf(i);
    }

    public final void zzq(long j) throws IOException {
        zzM(10);
        zze(j);
    }

    public final void zzr(long j) throws IOException {
        zzM(8);
        zzg(j);
    }

    public final void zzs(String str) throws IOException {
        int i;
        int i2;
        try {
            int length = str.length() * 3;
            int zzA = zzA(length);
            int i3 = zzA + length;
            int i4 = this.zzb;
            if (i3 > i4) {
                byte[] bArr = new byte[length];
                int zzd = zzaci.zzd(str, bArr, 0, length);
                zzo(zzd);
                zzt(bArr, 0, zzd);
                return;
            }
            if (i3 > i4 - this.zzc) {
                zzN();
            }
            int zzA2 = zzA(str.length());
            i = this.zzc;
            if (zzA2 == zzA) {
                int i5 = i + zzA2;
                this.zzc = i5;
                int zzd2 = zzaci.zzd(str, this.zza, i5, this.zzb - i5);
                this.zzc = i;
                i2 = (zzd2 - i) - zzA2;
                zzd(i2);
                this.zzc = zzd2;
            } else {
                i2 = zzaci.zzc(str);
                zzd(i2);
                this.zzc = zzaci.zzd(str, this.zza, this.zzc, i2);
            }
            this.zzd += i2;
        } catch (zzacg e) {
            this.zzd -= this.zzc - i;
            this.zzc = i;
            throw e;
        } catch (ArrayIndexOutOfBoundsException e2) {
            throw new zzyu(e2);
        } catch (zzacg e3) {
            zzJ(str, e3);
        }
    }

    public final void zzt(byte[] bArr, int i, int i2) throws IOException {
        int i3 = this.zzb;
        int i4 = this.zzc;
        int i5 = i3 - i4;
        if (i5 >= i2) {
            System.arraycopy(bArr, 0, this.zza, i4, i2);
            this.zzc += i2;
            this.zzd += i2;
            return;
        }
        System.arraycopy(bArr, 0, this.zza, i4, i5);
        int i6 = i2 - i5;
        this.zzc = this.zzb;
        this.zzd += i5;
        zzN();
        if (i6 <= this.zzb) {
            System.arraycopy(bArr, i5, this.zza, 0, i6);
            this.zzc = i6;
        } else {
            this.zzf.write(bArr, i5, i6);
        }
        this.zzd += i6;
    }

    public final void zzu() throws IOException {
        if (this.zzc > 0) {
            zzN();
        }
    }
}

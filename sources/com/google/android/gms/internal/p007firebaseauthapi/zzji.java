package com.google.android.gms.internal.p007firebaseauthapi;

import java.security.GeneralSecurityException;
import java.util.Arrays;
import javax.crypto.AEADBadTagException;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzji */
/* compiled from: com.google.firebase:firebase-auth@@20.0.1 */
public final class zzji implements zzag {
    private static final ThreadLocal<Cipher> zza = new zzjg();
    private static final ThreadLocal<Cipher> zzb = new zzjh();
    private final byte[] zzc;
    private final byte[] zzd;
    private final SecretKeySpec zze;
    private final int zzf;

    private static byte[] zzc(byte[] bArr, byte[] bArr2) {
        int length = bArr.length;
        byte[] bArr3 = new byte[length];
        for (int i = 0; i < length; i++) {
            bArr3[i] = (byte) (bArr[i] ^ bArr2[i]);
        }
        return bArr3;
    }

    private static byte[] zzd(byte[] bArr) {
        byte[] bArr2 = new byte[16];
        int i = 0;
        int i2 = 0;
        while (i2 < 15) {
            byte b = bArr[i2];
            int i3 = i2 + 1;
            bArr2[i2] = (byte) (((b + b) ^ ((bArr[i3] & 255) >>> 7)) & 255);
            i2 = i3;
        }
        byte b2 = bArr[15];
        int i4 = b2 + b2;
        if ((bArr[0] & 128) != 0) {
            i = 135;
        }
        bArr2[15] = (byte) (i4 ^ i);
        return bArr2;
    }

    private final byte[] zze(Cipher cipher, int i, byte[] bArr, int i2, int i3) throws IllegalBlockSizeException, BadPaddingException {
        byte[] bArr2;
        int length;
        byte[] bArr3 = new byte[16];
        bArr3[15] = (byte) i;
        if (i3 == 0) {
            return cipher.doFinal(zzc(bArr3, this.zzc));
        }
        byte[] doFinal = cipher.doFinal(bArr3);
        int i4 = 0;
        int i5 = 0;
        while (i3 - i5 > 16) {
            for (int i6 = 0; i6 < 16; i6++) {
                doFinal[i6] = (byte) (doFinal[i6] ^ bArr[(i2 + i5) + i6]);
            }
            doFinal = cipher.doFinal(doFinal);
            i5 += 16;
        }
        byte[] copyOfRange = Arrays.copyOfRange(bArr, i5 + i2, i2 + i3);
        if (copyOfRange.length == 16) {
            bArr2 = zzc(copyOfRange, this.zzc);
        } else {
            byte[] copyOf = Arrays.copyOf(this.zzd, 16);
            while (true) {
                length = copyOfRange.length;
                if (i4 >= length) {
                    break;
                }
                copyOf[i4] = (byte) (copyOf[i4] ^ copyOfRange[i4]);
                i4++;
            }
            copyOf[length] = (byte) (copyOf[length] ^ 128);
            bArr2 = copyOf;
        }
        return cipher.doFinal(zzc(doFinal, bArr2));
    }

    public final byte[] zza(byte[] bArr, byte[] bArr2) throws GeneralSecurityException {
        throw null;
    }

    public final byte[] zzb(byte[] bArr, byte[] bArr2) throws GeneralSecurityException {
        int length = bArr.length;
        int i = (length - this.zzf) - 16;
        if (i >= 0) {
            Cipher cipher = zza.get();
            cipher.init(1, this.zze);
            Cipher cipher2 = cipher;
            byte[] zze2 = zze(cipher2, 0, bArr, 0, this.zzf);
            byte[] zze3 = zze(cipher2, 1, bArr2, 0, bArr2.length);
            byte[] zze4 = zze(cipher, 2, bArr, this.zzf, i);
            int i2 = length - 16;
            byte b = 0;
            for (int i3 = 0; i3 < 16; i3++) {
                b = (byte) (b | (((bArr[i2 + i3] ^ zze3[i3]) ^ zze2[i3]) ^ zze4[i3]));
            }
            if (b == 0) {
                Cipher cipher3 = zzb.get();
                cipher3.init(1, this.zze, new IvParameterSpec(zze2));
                return cipher3.doFinal(bArr, this.zzf, i);
            }
            throw new AEADBadTagException("tag mismatch");
        }
        throw new GeneralSecurityException("ciphertext too short");
    }

    public zzji(byte[] bArr, int i) throws GeneralSecurityException {
        if (i == 12 || i == 16) {
            this.zzf = i;
            zzkr.zza(bArr.length);
            SecretKeySpec secretKeySpec = new SecretKeySpec(bArr, "AES");
            this.zze = secretKeySpec;
            Cipher cipher = zza.get();
            cipher.init(1, secretKeySpec);
            byte[] zzd2 = zzd(cipher.doFinal(new byte[16]));
            this.zzc = zzd2;
            this.zzd = zzd(zzd2);
            return;
        }
        throw new IllegalArgumentException("IV size should be either 12 or 16 bytes");
    }
}

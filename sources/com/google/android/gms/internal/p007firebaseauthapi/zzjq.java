package com.google.android.gms.internal.p007firebaseauthapi;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.security.GeneralSecurityException;
import java.security.InvalidKeyException;
import javax.crypto.AEADBadTagException;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzjq */
/* compiled from: com.google.firebase:firebase-auth@@20.0.1 */
abstract class zzjq implements zzag {
    private final zzjo zza;
    private final zzjo zzb;

    public zzjq(byte[] bArr) throws InvalidKeyException {
        this.zza = zzc(bArr, 1);
        this.zzb = zzc(bArr, 0);
    }

    private final byte[] zzd(ByteBuffer byteBuffer, byte[] bArr) throws GeneralSecurityException {
        if (byteBuffer.remaining() >= this.zza.zzc() + 16) {
            int position = byteBuffer.position();
            byte[] bArr2 = new byte[16];
            byteBuffer.position(byteBuffer.limit() - 16);
            byteBuffer.get(bArr2);
            byteBuffer.position(position);
            byteBuffer.limit(byteBuffer.limit() - 16);
            byte[] bArr3 = new byte[this.zza.zzc()];
            byteBuffer.get(bArr3);
            try {
                byte[] bArr4 = new byte[32];
                this.zzb.zze(bArr3, 0).get(bArr4);
                int length = bArr.length;
                int i = length & 15;
                int i2 = i == 0 ? length : (length + 16) - i;
                int remaining = byteBuffer.remaining();
                int i3 = remaining % 16;
                int i4 = (i3 == 0 ? remaining : (remaining + 16) - i3) + i2;
                ByteBuffer order = ByteBuffer.allocate(i4 + 16).order(ByteOrder.LITTLE_ENDIAN);
                order.put(bArr);
                order.position(i2);
                order.put(byteBuffer);
                order.position(i4);
                order.putLong((long) length);
                order.putLong((long) remaining);
                if (zzjm.zza(zzkj.zza(bArr4, order.array()), bArr2)) {
                    byteBuffer.position(position);
                    return this.zza.zzd(byteBuffer);
                }
                throw new GeneralSecurityException("invalid MAC");
            } catch (GeneralSecurityException e) {
                throw new AEADBadTagException(e.toString());
            }
        } else {
            throw new GeneralSecurityException("ciphertext too short");
        }
    }

    public final byte[] zza(byte[] bArr, byte[] bArr2) throws GeneralSecurityException {
        throw null;
    }

    public final byte[] zzb(byte[] bArr, byte[] bArr2) throws GeneralSecurityException {
        return zzd(ByteBuffer.wrap(bArr), bArr2);
    }

    /* access modifiers changed from: package-private */
    public abstract zzjo zzc(byte[] bArr, int i) throws InvalidKeyException;
}

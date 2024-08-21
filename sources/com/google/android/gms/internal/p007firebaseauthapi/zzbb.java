package com.google.android.gms.internal.p007firebaseauthapi;

import java.util.Arrays;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzbb */
/* compiled from: com.google.firebase:firebase-auth@@20.0.1 */
final class zzbb implements Comparable<zzbb> {
    private final byte[] zza;

    /* synthetic */ zzbb(byte[] bArr, zzaz zzaz) {
        this.zza = Arrays.copyOf(bArr, bArr.length);
    }

    public final /* bridge */ /* synthetic */ int compareTo(Object obj) {
        zzbb zzbb = (zzbb) obj;
        int length = this.zza.length;
        int length2 = zzbb.zza.length;
        if (length != length2) {
            return length - length2;
        }
        int i = 0;
        while (true) {
            byte[] bArr = this.zza;
            if (i >= bArr.length) {
                return 0;
            }
            byte b = bArr[i];
            byte b2 = zzbb.zza[i];
            if (b != b2) {
                return b - b2;
            }
            i++;
        }
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof zzbb)) {
            return false;
        }
        return Arrays.equals(this.zza, ((zzbb) obj).zza);
    }

    public final int hashCode() {
        return Arrays.hashCode(this.zza);
    }

    public final String toString() {
        return zzkh.zza(this.zza);
    }
}

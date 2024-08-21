package com.google.android.gms.internal.p007firebaseauthapi;

import java.io.IOException;
import java.io.Serializable;
import java.nio.charset.Charset;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Locale;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzym */
/* compiled from: com.google.firebase:firebase-auth@@20.0.1 */
public abstract class zzym implements Iterable<Byte>, Serializable {
    private static final zzyg zza = (zzxw.zza() ? new zzyl((zzyb) null) : new zzye((zzyb) null));
    public static final zzym zzb = new zzyk(zzzu.zzc);
    private static final Comparator<zzym> zzd = new zzyc();
    private int zzc = 0;

    zzym() {
    }

    public static zzym zzl(byte[] bArr, int i, int i2) {
        zzt(i, i + i2, bArr.length);
        return new zzyk(zza.zza(bArr, i, i2));
    }

    public static zzym zzm(byte[] bArr) {
        return zzl(bArr, 0, bArr.length);
    }

    static zzym zzn(byte[] bArr) {
        return new zzyk(bArr);
    }

    public static zzym zzo(String str) {
        return new zzyk(str.getBytes(zzzu.zza));
    }

    static zzyi zzr(int i) {
        return new zzyi(i, (zzyb) null);
    }

    public abstract boolean equals(Object obj);

    public final int hashCode() {
        int i = this.zzc;
        if (i == 0) {
            int zzc2 = zzc();
            i = zzj(zzc2, 0, zzc2);
            if (i == 0) {
                i = 1;
            }
            this.zzc = i;
        }
        return i;
    }

    public final /* bridge */ /* synthetic */ Iterator iterator() {
        return new zzyb(this);
    }

    public final String toString() {
        Locale locale = Locale.ROOT;
        Object[] objArr = new Object[3];
        objArr[0] = Integer.toHexString(System.identityHashCode(this));
        objArr[1] = Integer.valueOf(zzc());
        objArr[2] = zzc() <= 50 ? zzabp.zza(this) : String.valueOf(zzabp.zza(zzf(0, 47))).concat("...");
        return String.format(locale, "<ByteString@%s size=%d contents=\"%s\">", objArr);
    }

    public abstract byte zza(int i);

    /* access modifiers changed from: package-private */
    public abstract byte zzb(int i);

    public abstract int zzc();

    /* access modifiers changed from: protected */
    public abstract void zze(byte[] bArr, int i, int i2, int i3);

    public abstract zzym zzf(int i, int i2);

    /* access modifiers changed from: package-private */
    public abstract void zzg(zzya zzya) throws IOException;

    /* access modifiers changed from: protected */
    public abstract String zzh(Charset charset);

    public abstract boolean zzi();

    /* access modifiers changed from: protected */
    public abstract int zzj(int i, int i2, int i3);

    public abstract zzyp zzk();

    public final byte[] zzp() {
        int zzc2 = zzc();
        if (zzc2 == 0) {
            return zzzu.zzc;
        }
        byte[] bArr = new byte[zzc2];
        zze(bArr, 0, 0, zzc2);
        return bArr;
    }

    public final String zzq(Charset charset) {
        return zzc() == 0 ? "" : zzh(charset);
    }

    /* access modifiers changed from: protected */
    public final int zzs() {
        return this.zzc;
    }

    static int zzt(int i, int i2, int i3) {
        int i4 = i2 - i;
        if ((i | i2 | i4 | (i3 - i2)) >= 0) {
            return i4;
        }
        if (i < 0) {
            StringBuilder sb = new StringBuilder(32);
            sb.append("Beginning index: ");
            sb.append(i);
            sb.append(" < 0");
            throw new IndexOutOfBoundsException(sb.toString());
        } else if (i2 < i) {
            StringBuilder sb2 = new StringBuilder(66);
            sb2.append("Beginning index larger than ending index: ");
            sb2.append(i);
            sb2.append(", ");
            sb2.append(i2);
            throw new IndexOutOfBoundsException(sb2.toString());
        } else {
            StringBuilder sb3 = new StringBuilder(37);
            sb3.append("End index: ");
            sb3.append(i2);
            sb3.append(" >= ");
            sb3.append(i3);
            throw new IndexOutOfBoundsException(sb3.toString());
        }
    }
}

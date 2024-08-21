package com.google.android.gms.internal.p007firebaseauthapi;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Objects;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzyk */
/* compiled from: com.google.firebase:firebase-auth@@20.0.1 */
class zzyk extends zzyj {
    protected final byte[] zza;

    zzyk(byte[] bArr) {
        Objects.requireNonNull(bArr);
        this.zza = bArr;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzym) || zzc() != ((zzym) obj).zzc()) {
            return false;
        }
        if (zzc() == 0) {
            return true;
        }
        if (!(obj instanceof zzyk)) {
            return obj.equals(this);
        }
        zzyk zzyk = (zzyk) obj;
        int zzs = zzs();
        int zzs2 = zzyk.zzs();
        if (zzs != 0 && zzs2 != 0 && zzs != zzs2) {
            return false;
        }
        int zzc = zzc();
        if (zzc > zzyk.zzc()) {
            int zzc2 = zzc();
            StringBuilder sb = new StringBuilder(40);
            sb.append("Length too large: ");
            sb.append(zzc);
            sb.append(zzc2);
            throw new IllegalArgumentException(sb.toString());
        } else if (zzc > zzyk.zzc()) {
            int zzc3 = zzyk.zzc();
            StringBuilder sb2 = new StringBuilder(59);
            sb2.append("Ran off end of other: ");
            sb2.append(0);
            sb2.append(", ");
            sb2.append(zzc);
            sb2.append(", ");
            sb2.append(zzc3);
            throw new IllegalArgumentException(sb2.toString());
        } else if (!(zzyk instanceof zzyk)) {
            return zzyk.zzf(0, zzc).equals(zzf(0, zzc));
        } else {
            byte[] bArr = this.zza;
            byte[] bArr2 = zzyk.zza;
            zzyk.zzd();
            int i = 0;
            int i2 = 0;
            while (i < zzc) {
                if (bArr[i] != bArr2[i2]) {
                    return false;
                }
                i++;
                i2++;
            }
            return true;
        }
    }

    public byte zza(int i) {
        return this.zza[i];
    }

    /* access modifiers changed from: package-private */
    public byte zzb(int i) {
        return this.zza[i];
    }

    public int zzc() {
        return this.zza.length;
    }

    /* access modifiers changed from: protected */
    public int zzd() {
        return 0;
    }

    /* access modifiers changed from: protected */
    public void zze(byte[] bArr, int i, int i2, int i3) {
        System.arraycopy(this.zza, 0, bArr, 0, i3);
    }

    public final zzym zzf(int i, int i2) {
        int zzt = zzt(0, i2, zzc());
        if (zzt == 0) {
            return zzym.zzb;
        }
        return new zzyf(this.zza, 0, zzt);
    }

    /* access modifiers changed from: package-private */
    public final void zzg(zzya zzya) throws IOException {
        zzya.zza(this.zza, 0, zzc());
    }

    /* access modifiers changed from: protected */
    public final String zzh(Charset charset) {
        return new String(this.zza, 0, zzc(), charset);
    }

    public final boolean zzi() {
        return zzaci.zzb(this.zza, 0, zzc());
    }

    /* access modifiers changed from: protected */
    public final int zzj(int i, int i2, int i3) {
        return zzzu.zzh(i, this.zza, 0, i3);
    }

    public final zzyp zzk() {
        return zzyp.zzs(this.zza, 0, zzc(), true);
    }
}

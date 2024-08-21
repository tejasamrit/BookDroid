package com.google.android.gms.internal.p007firebaseauthapi;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzap */
/* compiled from: com.google.firebase:firebase-auth@@20.0.1 */
public final class zzap {
    private final zzht zza;

    private zzap(zzht zzht) {
        this.zza = zzht;
    }

    public static zzap zzb(String str, byte[] bArr, int i) {
        zziu zziu;
        zzhs zzd = zzht.zzd();
        zzd.zza(str);
        zzd.zzb(zzym.zzm(bArr));
        zziu zziu2 = zziu.UNKNOWN_PREFIX;
        int i2 = i - 1;
        if (i2 != 0) {
            zziu = i2 != 1 ? i2 != 2 ? zziu.CRUNCHY : zziu.RAW : zziu.LEGACY;
        } else {
            zziu = zziu.TINK;
        }
        zzd.zzc(zziu);
        return new zzap((zzht) zzd.zzl());
    }

    /* access modifiers changed from: package-private */
    public final zzht zza() {
        return this.zza;
    }
}

package com.google.android.gms.internal.p007firebaseauthapi;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzge */
/* compiled from: com.google.firebase:firebase-auth@@20.0.1 */
public final class zzge extends zzzo<zzge, zzgd> implements zzaas {
    /* access modifiers changed from: private */
    public static final zzge zze;
    private zzht zzb;

    static {
        zzge zzge = new zzge();
        zze = zzge;
        zzzo.zzy(zzge.class, zzge);
    }

    private zzge() {
    }

    public static zzgd zzb() {
        return (zzgd) zze.zzs();
    }

    public static zzge zzc() {
        return zze;
    }

    static /* synthetic */ void zze(zzge zzge, zzht zzht) {
        zzht.getClass();
        zzge.zzb = zzht;
    }

    public final zzht zza() {
        zzht zzht = this.zzb;
        return zzht == null ? zzht.zze() : zzht;
    }

    /* access modifiers changed from: protected */
    public final Object zzj(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzz(zze, "\u0000\u0001\u0000\u0000\u0002\u0002\u0001\u0000\u0000\u0000\u0002\t", new Object[]{"zzb"});
        } else if (i2 == 3) {
            return new zzge();
        } else {
            if (i2 == 4) {
                return new zzgd((zzgc) null);
            }
            if (i2 != 5) {
                return null;
            }
            return zze;
        }
    }
}

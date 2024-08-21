package com.google.android.gms.internal.p007firebaseauthapi;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzht */
/* compiled from: com.google.firebase:firebase-auth@@20.0.1 */
public final class zzht extends zzzo<zzht, zzhs> implements zzaas {
    /* access modifiers changed from: private */
    public static final zzht zzg;
    private String zzb = "";
    private zzym zze = zzym.zzb;
    /* access modifiers changed from: private */
    public int zzf;

    static {
        zzht zzht = new zzht();
        zzg = zzht;
        zzzo.zzy(zzht.class, zzht);
    }

    private zzht() {
    }

    public static zzhs zzd() {
        return (zzhs) zzg.zzs();
    }

    public static zzht zze() {
        return zzg;
    }

    static /* synthetic */ void zzg(zzht zzht, String str) {
        str.getClass();
        zzht.zzb = str;
    }

    static /* synthetic */ void zzh(zzht zzht, zzym zzym) {
        zzym.getClass();
        zzht.zze = zzym;
    }

    public final String zza() {
        return this.zzb;
    }

    public final zzym zzb() {
        return this.zze;
    }

    public final zziu zzc() {
        zziu zzb2 = zziu.zzb(this.zzf);
        return zzb2 == null ? zziu.UNRECOGNIZED : zzb2;
    }

    /* access modifiers changed from: protected */
    public final Object zzj(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzz(zzg, "\u0000\u0003\u0000\u0000\u0001\u0003\u0003\u0000\u0000\u0000\u0001Ȉ\u0002\n\u0003\f", new Object[]{"zzb", "zze", "zzf"});
        } else if (i2 == 3) {
            return new zzht();
        } else {
            if (i2 == 4) {
                return new zzhs((zzhr) null);
            }
            if (i2 != 5) {
                return null;
            }
            return zzg;
        }
    }
}

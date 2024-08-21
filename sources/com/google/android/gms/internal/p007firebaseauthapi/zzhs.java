package com.google.android.gms.internal.p007firebaseauthapi;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzhs */
/* compiled from: com.google.firebase:firebase-auth@@20.0.1 */
public final class zzhs extends zzzk<zzht, zzhs> implements zzaas {
    private zzhs() {
        super(zzht.zzg);
    }

    /* synthetic */ zzhs(zzhr zzhr) {
        super(zzht.zzg);
    }

    public final zzhs zza(String str) {
        if (this.zzb) {
            zzi();
            this.zzb = false;
        }
        zzht.zzg((zzht) this.zza, str);
        return this;
    }

    public final zzhs zzb(zzym zzym) {
        if (this.zzb) {
            zzi();
            this.zzb = false;
        }
        zzht.zzh((zzht) this.zza, zzym);
        return this;
    }

    public final zzhs zzc(zziu zziu) {
        if (this.zzb) {
            zzi();
            this.zzb = false;
        }
        ((zzht) this.zza).zzf = zziu.zza();
        return this;
    }
}

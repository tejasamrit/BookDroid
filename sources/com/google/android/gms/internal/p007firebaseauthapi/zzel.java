package com.google.android.gms.internal.p007firebaseauthapi;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzel */
/* compiled from: com.google.firebase:firebase-auth@@20.0.1 */
public final class zzel extends zzzk<zzem, zzel> implements zzaas {
    private zzel() {
        super(zzem.zzg);
    }

    /* synthetic */ zzel(zzek zzek) {
        super(zzem.zzg);
    }

    public final zzel zza(int i) {
        if (this.zzb) {
            zzi();
            this.zzb = false;
        }
        ((zzem) this.zza).zzb = i;
        return this;
    }

    public final zzel zzb(zzes zzes) {
        if (this.zzb) {
            zzi();
            this.zzb = false;
        }
        zzem.zzh((zzem) this.zza, zzes);
        return this;
    }

    public final zzel zzc(zzhd zzhd) {
        if (this.zzb) {
            zzi();
            this.zzb = false;
        }
        zzem.zzi((zzem) this.zza, zzhd);
        return this;
    }
}

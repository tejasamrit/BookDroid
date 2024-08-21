package com.google.android.gms.internal.p007firebaseauthapi;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzhc */
/* compiled from: com.google.firebase:firebase-auth@@20.0.1 */
public final class zzhc extends zzzk<zzhd, zzhc> implements zzaas {
    private zzhc() {
        super(zzhd.zzg);
    }

    /* synthetic */ zzhc(zzhb zzhb) {
        super(zzhd.zzg);
    }

    public final zzhc zza(int i) {
        if (this.zzb) {
            zzi();
            this.zzb = false;
        }
        ((zzhd) this.zza).zzb = 0;
        return this;
    }

    public final zzhc zzb(zzhj zzhj) {
        if (this.zzb) {
            zzi();
            this.zzb = false;
        }
        zzhd.zzi((zzhd) this.zza, zzhj);
        return this;
    }

    public final zzhc zzc(zzym zzym) {
        if (this.zzb) {
            zzi();
            this.zzb = false;
        }
        zzhd.zzk((zzhd) this.zza, zzym);
        return this;
    }
}

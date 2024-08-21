package com.google.android.gms.internal.p007firebaseauthapi;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzhf */
/* compiled from: com.google.firebase:firebase-auth@@20.0.1 */
public final class zzhf extends zzzk<zzhg, zzhf> implements zzaas {
    private zzhf() {
        super(zzhg.zzg);
    }

    /* synthetic */ zzhf(zzhe zzhe) {
        super(zzhg.zzg);
    }

    public final zzhf zza(zzhj zzhj) {
        if (this.zzb) {
            zzi();
            this.zzb = false;
        }
        zzhg.zzg((zzhg) this.zza, zzhj);
        return this;
    }

    public final zzhf zzb(int i) {
        if (this.zzb) {
            zzi();
            this.zzb = false;
        }
        ((zzhg) this.zza).zze = 32;
        return this;
    }
}

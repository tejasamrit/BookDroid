package com.google.android.gms.internal.p007firebaseauthapi;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzhz */
/* compiled from: com.google.firebase:firebase-auth@@20.0.1 */
public final class zzhz extends zzzk<zzia, zzhz> implements zzaas {
    private zzhz() {
        super(zzia.zzh);
    }

    /* synthetic */ zzhz(zzhx zzhx) {
        super(zzia.zzh);
    }

    public final zzhz zza(zzho zzho) {
        if (this.zzb) {
            zzi();
            this.zzb = false;
        }
        zzia.zzh((zzia) this.zza, zzho);
        return this;
    }

    public final zzhz zzb(zzhq zzhq) {
        if (this.zzb) {
            zzi();
            this.zzb = false;
        }
        ((zzia) this.zza).zze = zzhq.zza();
        return this;
    }

    public final zzhz zzc(int i) {
        if (this.zzb) {
            zzi();
            this.zzb = false;
        }
        ((zzia) this.zza).zzf = i;
        return this;
    }

    public final zzhz zzd(zziu zziu) {
        if (this.zzb) {
            zzi();
            this.zzb = false;
        }
        ((zzia) this.zza).zzg = zziu.zza();
        return this;
    }
}

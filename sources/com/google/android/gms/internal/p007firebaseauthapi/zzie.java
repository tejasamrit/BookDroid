package com.google.android.gms.internal.p007firebaseauthapi;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzie */
/* compiled from: com.google.firebase:firebase-auth@@20.0.1 */
public final class zzie extends zzzk<zzif, zzie> implements zzaas {
    private zzie() {
        super(zzif.zzh);
    }

    /* synthetic */ zzie(zzic zzic) {
        super(zzif.zzh);
    }

    public final zzie zza(String str) {
        if (this.zzb) {
            zzi();
            this.zzb = false;
        }
        zzif.zzd((zzif) this.zza, str);
        return this;
    }

    public final zzie zzb(zzhq zzhq) {
        if (this.zzb) {
            zzi();
            this.zzb = false;
        }
        ((zzif) this.zza).zze = zzhq.zza();
        return this;
    }

    public final zzie zzc(int i) {
        if (this.zzb) {
            zzi();
            this.zzb = false;
        }
        ((zzif) this.zza).zzf = i;
        return this;
    }

    public final zzie zzd(zziu zziu) {
        if (this.zzb) {
            zzi();
            this.zzb = false;
        }
        ((zzif) this.zza).zzg = zziu.zza();
        return this;
    }
}

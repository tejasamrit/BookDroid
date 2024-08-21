package com.google.android.gms.internal.p007firebaseauthapi;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzhl */
/* compiled from: com.google.firebase:firebase-auth@@20.0.1 */
public final class zzhl extends zzzk<zzho, zzhl> implements zzaas {
    private zzhl() {
        super(zzho.zzg);
    }

    /* synthetic */ zzhl(zzhk zzhk) {
        super(zzho.zzg);
    }

    public final zzhl zza(String str) {
        if (this.zzb) {
            zzi();
            this.zzb = false;
        }
        zzho.zzg((zzho) this.zza, str);
        return this;
    }

    public final zzhl zzb(zzym zzym) {
        if (this.zzb) {
            zzi();
            this.zzb = false;
        }
        zzho.zzh((zzho) this.zza, zzym);
        return this;
    }

    public final zzhl zzc(zzhn zzhn) {
        if (this.zzb) {
            zzi();
            this.zzb = false;
        }
        ((zzho) this.zza).zzf = zzhn.zza();
        return this;
    }
}

package com.google.android.gms.internal.p007firebaseauthapi;

import java.util.Collections;
import java.util.List;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzhy */
/* compiled from: com.google.firebase:firebase-auth@@20.0.1 */
public final class zzhy extends zzzk<zzib, zzhy> implements zzaas {
    private zzhy() {
        super(zzib.zzf);
    }

    /* synthetic */ zzhy(zzhx zzhx) {
        super(zzib.zzf);
    }

    public final zzhy zza(int i) {
        if (this.zzb) {
            zzi();
            this.zzb = false;
        }
        ((zzib) this.zza).zzb = i;
        return this;
    }

    public final List<zzia> zzb() {
        return Collections.unmodifiableList(((zzib) this.zza).zzb());
    }

    public final int zzc() {
        return ((zzib) this.zza).zzc();
    }

    public final zzia zzd(int i) {
        return ((zzib) this.zza).zzd(i);
    }

    public final zzhy zze(zzia zzia) {
        if (this.zzb) {
            zzi();
            this.zzb = false;
        }
        zzib.zzi((zzib) this.zza, zzia);
        return this;
    }
}

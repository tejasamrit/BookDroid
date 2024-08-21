package com.google.android.gms.internal.p007firebaseauthapi;

import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zztv */
/* compiled from: com.google.firebase:firebase-auth@@20.0.1 */
public final class zztv extends zzpl implements Api.ApiOptions.HasOptions {
    private final String zzb;

    /* synthetic */ zztv(String str, zztt zztt) {
        this.zzb = Preconditions.checkNotEmpty(str, "A valid API key must be provided");
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zztv)) {
            return false;
        }
        zztv zztv = (zztv) obj;
        return Objects.equal(this.zzb, zztv.zzb) && this.zza == zztv.zza;
    }

    public final int hashCode() {
        return Objects.hashCode(this.zzb) + (true ^ this.zza ? 1 : 0);
    }

    public final String zzb() {
        return this.zzb;
    }

    /* renamed from: zzc */
    public final zztv zza() {
        return new zztv(Preconditions.checkNotEmpty(this.zzb), (zztt) null);
    }
}

package com.google.android.gms.internal.p007firebaseauthapi;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzaa */
/* compiled from: com.google.firebase:firebase-auth@@20.0.1 */
final class zzaa extends zzac {
    final /* synthetic */ zzr zza;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzaa(zzab zzab, zzae zzae, CharSequence charSequence, zzr zzr) {
        super(zzae, charSequence);
        this.zza = zzr;
    }

    public final int zzc(int i) {
        if (((zzt) this.zza).zza.find(i)) {
            return ((zzt) this.zza).zza.start();
        }
        return -1;
    }

    public final int zzd(int i) {
        return ((zzt) this.zza).zza.end();
    }
}

package com.google.android.gms.internal.p007firebaseauthapi;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzuy */
/* compiled from: com.google.firebase:firebase-auth@@20.0.1 */
final /* synthetic */ class zzuy implements Runnable {
    private final zzvd zza;
    private final String zzb;

    zzuy(zzvd zzvd, String str) {
        this.zza = zzvd;
        this.zzb = str;
    }

    public final void run() {
        this.zza.zzg(this.zzb);
    }
}

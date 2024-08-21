package com.google.android.gms.internal.p007firebaseauthapi;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzuj */
/* compiled from: com.google.firebase:firebase-auth@@20.0.1 */
final class zzuj implements Runnable {
    final /* synthetic */ zzul zza;
    final /* synthetic */ zzuk zzb;

    zzuj(zzuk zzuk, zzul zzul) {
        this.zzb = zzuk;
        this.zza = zzul;
    }

    public final void run() {
        synchronized (this.zzb.zza.zzh) {
            if (!this.zzb.zza.zzh.isEmpty()) {
                this.zza.zza(this.zzb.zza.zzh.get(0), new Object[0]);
            }
        }
    }
}

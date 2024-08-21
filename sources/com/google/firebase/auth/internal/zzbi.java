package com.google.firebase.auth.internal;

import android.app.Application;
import android.content.Context;
import com.google.android.gms.common.api.internal.BackgroundDetector;
import com.google.android.gms.internal.p007firebaseauthapi.zzwg;
import com.google.firebase.FirebaseApp;

/* compiled from: com.google.firebase:firebase-auth@@20.0.1 */
public final class zzbi {
    private volatile int zza = 0;
    /* access modifiers changed from: private */
    public final zzam zzb;
    /* access modifiers changed from: private */
    public volatile boolean zzc = false;

    public zzbi(FirebaseApp firebaseApp) {
        Context applicationContext = firebaseApp.getApplicationContext();
        zzam zzam = new zzam(firebaseApp);
        this.zzb = zzam;
        BackgroundDetector.initialize((Application) applicationContext.getApplicationContext());
        BackgroundDetector.getInstance().addListener(new zzbh(this));
    }

    /* access modifiers changed from: private */
    public final boolean zzg() {
        return this.zza > 0 && !this.zzc;
    }

    public final void zzb(zzwg zzwg) {
        if (zzwg != null) {
            long zzf = zzwg.zzf();
            if (zzf <= 0) {
                zzf = 3600;
            }
            long zzh = zzwg.zzh();
            zzam zzam = this.zzb;
            zzam.zza = zzh + (zzf * 1000);
            zzam.zzb = -1;
            if (zzg()) {
                this.zzb.zza();
            }
        }
    }

    public final void zzc() {
        this.zzb.zzc();
    }

    public final void zza(int i) {
        if (i > 0 && this.zza == 0) {
            this.zza = i;
            if (zzg()) {
                this.zzb.zza();
            }
        } else if (i == 0 && this.zza != 0) {
            this.zzb.zzc();
        }
        this.zza = i;
    }
}

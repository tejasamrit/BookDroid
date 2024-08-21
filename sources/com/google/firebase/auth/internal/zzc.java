package com.google.firebase.auth.internal;

import android.util.Log;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.TaskCompletionSource;

/* compiled from: com.google.firebase:firebase-auth@@20.0.1 */
final class zzc implements OnFailureListener {
    final /* synthetic */ TaskCompletionSource zza;

    zzc(zzf zzf, TaskCompletionSource taskCompletionSource) {
        this.zza = taskCompletionSource;
    }

    public final void onFailure(Exception exc) {
        Log.e(zzf.zza, "Failed to get reCAPTCHA token - calling backend without app verification");
        this.zza.setResult(new zze((String) null, (String) null));
    }
}

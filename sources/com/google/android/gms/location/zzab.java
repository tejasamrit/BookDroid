package com.google.android.gms.location;

import com.google.android.gms.common.api.internal.RemoteCall;
import com.google.android.gms.internal.location.zzay;
import com.google.android.gms.tasks.TaskCompletionSource;

/* compiled from: com.google.android.gms:play-services-location@@17.1.0 */
final /* synthetic */ class zzab implements RemoteCall {
    private final boolean zza;

    zzab(boolean z) {
        this.zza = z;
    }

    public final void accept(Object obj, Object obj2) {
        ((zzay) obj).zza(this.zza);
        ((TaskCompletionSource) obj2).setResult(null);
    }
}

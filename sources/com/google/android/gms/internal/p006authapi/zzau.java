package com.google.android.gms.internal.p006authapi;

import android.os.RemoteException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.IStatusCallback;
import com.google.android.gms.common.api.internal.TaskUtil;
import com.google.android.gms.tasks.TaskCompletionSource;

/* renamed from: com.google.android.gms.internal.auth-api.zzau */
/* compiled from: com.google.android.gms:play-services-auth@@19.0.0 */
final class zzau extends IStatusCallback.Stub {
    private final /* synthetic */ TaskCompletionSource zzbq;

    zzau(zzao zzao, TaskCompletionSource taskCompletionSource) {
        this.zzbq = taskCompletionSource;
    }

    public final void onResult(Status status) throws RemoteException {
        TaskUtil.setResultOrApiException(status, this.zzbq);
    }
}

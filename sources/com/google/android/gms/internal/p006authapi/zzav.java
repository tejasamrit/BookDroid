package com.google.android.gms.internal.p006authapi;

import android.os.RemoteException;
import com.google.android.gms.auth.api.identity.BeginSignInResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.TaskUtil;
import com.google.android.gms.tasks.TaskCompletionSource;

/* renamed from: com.google.android.gms.internal.auth-api.zzav */
/* compiled from: com.google.android.gms:play-services-auth@@19.0.0 */
final class zzav extends zzad {
    private final /* synthetic */ TaskCompletionSource zzbq;

    zzav(zzao zzao, TaskCompletionSource taskCompletionSource) {
        this.zzbq = taskCompletionSource;
    }

    public final void zzc(Status status, BeginSignInResult beginSignInResult) throws RemoteException {
        TaskUtil.setResultOrApiException(status, beginSignInResult, this.zzbq);
    }
}

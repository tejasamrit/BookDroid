package com.google.android.gms.internal.p006authapi;

import com.google.android.gms.auth.api.identity.BeginSignInRequest;
import com.google.android.gms.common.api.internal.RemoteCall;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.tasks.TaskCompletionSource;

/* renamed from: com.google.android.gms.internal.auth-api.zzar */
/* compiled from: com.google.android.gms:play-services-auth@@19.0.0 */
final /* synthetic */ class zzar implements RemoteCall {
    private final zzao zzbr;
    private final BeginSignInRequest zzbs;

    zzar(zzao zzao, BeginSignInRequest beginSignInRequest) {
        this.zzbr = zzao;
        this.zzbs = beginSignInRequest;
    }

    public final void accept(Object obj, Object obj2) {
        zzao zzao = this.zzbr;
        BeginSignInRequest beginSignInRequest = this.zzbs;
        ((zzai) ((zzaw) obj).getService()).zzc((zzaa) new zzav(zzao, (TaskCompletionSource) obj2), (BeginSignInRequest) Preconditions.checkNotNull(beginSignInRequest));
    }
}

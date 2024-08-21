package com.google.firebase.iid;

import com.google.firebase.iid.WithinAppServiceConnection;

/* compiled from: com.google.firebase:firebase-iid@@21.0.1 */
final /* synthetic */ class WithinAppServiceConnection$BindRequest$$Lambda$0 implements Runnable {
    private final WithinAppServiceConnection.BindRequest arg$1;

    WithinAppServiceConnection$BindRequest$$Lambda$0(WithinAppServiceConnection.BindRequest bindRequest) {
        this.arg$1 = bindRequest;
    }

    public void run() {
        this.arg$1.lambda$arrangeTimeout$0$WithinAppServiceConnection$BindRequest();
    }
}

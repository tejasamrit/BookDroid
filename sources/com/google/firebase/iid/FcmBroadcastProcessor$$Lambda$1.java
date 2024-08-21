package com.google.firebase.iid;

import android.content.Context;
import android.content.Intent;
import java.util.concurrent.Callable;

/* compiled from: com.google.firebase:firebase-iid@@21.0.1 */
final /* synthetic */ class FcmBroadcastProcessor$$Lambda$1 implements Callable {
    private final Context arg$1;
    private final Intent arg$2;

    FcmBroadcastProcessor$$Lambda$1(Context context, Intent intent) {
        this.arg$1 = context;
        this.arg$2 = intent;
    }

    public Object call() {
        return Integer.valueOf(ServiceStarter.getInstance().startMessagingService(this.arg$1, this.arg$2));
    }
}

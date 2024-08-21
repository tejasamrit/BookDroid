package com.google.firebase.storage.internal;

import android.os.Handler;
import android.os.Looper;
import com.google.android.gms.common.internal.Preconditions;
import com.google.firebase.storage.StorageTaskScheduler;
import java.util.concurrent.Executor;

public class SmartHandler {
    static boolean testMode = false;
    private final Executor executor;
    private final Handler handler;

    public SmartHandler(Executor executor2) {
        this.executor = executor2;
        if (executor2 != null) {
            this.handler = null;
        } else if (!testMode) {
            this.handler = new Handler(Looper.getMainLooper());
        } else {
            this.handler = null;
        }
    }

    public void callBack(Runnable runnable) {
        Preconditions.checkNotNull(runnable);
        Handler handler2 = this.handler;
        if (handler2 == null) {
            Executor executor2 = this.executor;
            if (executor2 != null) {
                executor2.execute(runnable);
            } else {
                StorageTaskScheduler.getInstance().scheduleCallback(runnable);
            }
        } else {
            handler2.post(runnable);
        }
    }
}

package com.firebase.geofire;

import android.os.Handler;
import android.os.Looper;

class AndroidEventRaiser implements EventRaiser {
    private final Handler mainThreadHandler = new Handler(Looper.getMainLooper());

    public void raiseEvent(Runnable runnable) {
        this.mainThreadHandler.post(runnable);
    }
}

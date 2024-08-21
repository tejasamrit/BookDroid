package com.firebase.geofire;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class ThreadEventRaiser implements EventRaiser {
    private final ExecutorService executorService = Executors.newSingleThreadExecutor();

    public void raiseEvent(Runnable runnable) {
        this.executorService.submit(runnable);
    }
}

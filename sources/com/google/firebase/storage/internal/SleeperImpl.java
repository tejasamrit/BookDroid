package com.google.firebase.storage.internal;

public class SleeperImpl implements Sleeper {
    public void sleep(int i) throws InterruptedException {
        Thread.sleep((long) i);
    }
}

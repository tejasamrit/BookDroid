package com.google.firebase.storage.internal;

public interface Sleeper {
    void sleep(int i) throws InterruptedException;
}

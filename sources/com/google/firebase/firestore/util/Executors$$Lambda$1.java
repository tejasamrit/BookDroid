package com.google.firebase.firestore.util;

import java.util.concurrent.Executor;

/* compiled from: Executors */
final /* synthetic */ class Executors$$Lambda$1 implements Executor {
    private static final Executors$$Lambda$1 instance = new Executors$$Lambda$1();

    private Executors$$Lambda$1() {
    }

    public static Executor lambdaFactory$() {
        return instance;
    }

    public void execute(Runnable runnable) {
        runnable.run();
    }
}

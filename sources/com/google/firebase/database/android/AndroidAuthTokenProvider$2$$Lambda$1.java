package com.google.firebase.database.android;

import com.google.firebase.database.core.AuthTokenProvider;

/* compiled from: AndroidAuthTokenProvider */
final /* synthetic */ class AndroidAuthTokenProvider$2$$Lambda$1 implements Runnable {
    private final AuthTokenProvider.TokenChangeListener arg$1;

    private AndroidAuthTokenProvider$2$$Lambda$1(AuthTokenProvider.TokenChangeListener tokenChangeListener) {
        this.arg$1 = tokenChangeListener;
    }

    public static Runnable lambdaFactory$(AuthTokenProvider.TokenChangeListener tokenChangeListener) {
        return new AndroidAuthTokenProvider$2$$Lambda$1(tokenChangeListener);
    }

    public void run() {
        this.arg$1.onTokenChange((String) null);
    }
}

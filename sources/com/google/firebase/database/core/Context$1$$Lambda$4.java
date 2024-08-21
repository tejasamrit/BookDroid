package com.google.firebase.database.core;

import com.google.firebase.database.connection.ConnectionAuthTokenProvider;

/* compiled from: Context */
final /* synthetic */ class Context$1$$Lambda$4 implements Runnable {
    private final ConnectionAuthTokenProvider.GetTokenCallback arg$1;
    private final String arg$2;

    private Context$1$$Lambda$4(ConnectionAuthTokenProvider.GetTokenCallback getTokenCallback, String str) {
        this.arg$1 = getTokenCallback;
        this.arg$2 = str;
    }

    public static Runnable lambdaFactory$(ConnectionAuthTokenProvider.GetTokenCallback getTokenCallback, String str) {
        return new Context$1$$Lambda$4(getTokenCallback, str);
    }

    public void run() {
        this.arg$1.onError(this.arg$2);
    }
}

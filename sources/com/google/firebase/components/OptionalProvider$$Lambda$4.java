package com.google.firebase.components;

import com.google.firebase.inject.Deferred;
import com.google.firebase.inject.Provider;

/* compiled from: OptionalProvider */
final /* synthetic */ class OptionalProvider$$Lambda$4 implements Deferred.DeferredHandler {
    private static final OptionalProvider$$Lambda$4 instance = new OptionalProvider$$Lambda$4();

    private OptionalProvider$$Lambda$4() {
    }

    public static Deferred.DeferredHandler lambdaFactory$() {
        return instance;
    }

    public void handle(Provider provider) {
        OptionalProvider.lambda$static$0(provider);
    }
}

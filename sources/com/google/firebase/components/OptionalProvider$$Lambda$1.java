package com.google.firebase.components;

import com.google.firebase.inject.Deferred;
import com.google.firebase.inject.Provider;

/* compiled from: OptionalProvider */
final /* synthetic */ class OptionalProvider$$Lambda$1 implements Deferred.DeferredHandler {
    private final Deferred.DeferredHandler arg$1;
    private final Deferred.DeferredHandler arg$2;

    private OptionalProvider$$Lambda$1(Deferred.DeferredHandler deferredHandler, Deferred.DeferredHandler deferredHandler2) {
        this.arg$1 = deferredHandler;
        this.arg$2 = deferredHandler2;
    }

    public static Deferred.DeferredHandler lambdaFactory$(Deferred.DeferredHandler deferredHandler, Deferred.DeferredHandler deferredHandler2) {
        return new OptionalProvider$$Lambda$1(deferredHandler, deferredHandler2);
    }

    public void handle(Provider provider) {
        OptionalProvider.lambda$whenAvailable$2(this.arg$1, this.arg$2, provider);
    }
}

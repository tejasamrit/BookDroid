package com.google.firebase.components;

import com.google.firebase.inject.Provider;

/* compiled from: OptionalProvider */
final /* synthetic */ class OptionalProvider$$Lambda$5 implements Provider {
    private static final OptionalProvider$$Lambda$5 instance = new OptionalProvider$$Lambda$5();

    private OptionalProvider$$Lambda$5() {
    }

    public static Provider lambdaFactory$() {
        return instance;
    }

    public Object get() {
        return OptionalProvider.lambda$static$1();
    }
}

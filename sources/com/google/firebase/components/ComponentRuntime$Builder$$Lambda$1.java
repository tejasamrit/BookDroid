package com.google.firebase.components;

import com.google.firebase.components.ComponentRuntime;
import com.google.firebase.inject.Provider;

/* compiled from: ComponentRuntime */
final /* synthetic */ class ComponentRuntime$Builder$$Lambda$1 implements Provider {
    private final ComponentRegistrar arg$1;

    private ComponentRuntime$Builder$$Lambda$1(ComponentRegistrar componentRegistrar) {
        this.arg$1 = componentRegistrar;
    }

    public static Provider lambdaFactory$(ComponentRegistrar componentRegistrar) {
        return new ComponentRuntime$Builder$$Lambda$1(componentRegistrar);
    }

    public Object get() {
        return ComponentRuntime.Builder.lambda$addComponentRegistrar$0(this.arg$1);
    }
}

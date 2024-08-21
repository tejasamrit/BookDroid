package com.google.firebase.database;

import com.google.firebase.components.ComponentContainer;
import com.google.firebase.components.ComponentFactory;

/* compiled from: DatabaseRegistrar */
final /* synthetic */ class DatabaseRegistrar$$Lambda$1 implements ComponentFactory {
    private static final DatabaseRegistrar$$Lambda$1 instance = new DatabaseRegistrar$$Lambda$1();

    private DatabaseRegistrar$$Lambda$1() {
    }

    public static ComponentFactory lambdaFactory$() {
        return instance;
    }

    public Object create(ComponentContainer componentContainer) {
        return DatabaseRegistrar.lambda$getComponents$0(componentContainer);
    }
}

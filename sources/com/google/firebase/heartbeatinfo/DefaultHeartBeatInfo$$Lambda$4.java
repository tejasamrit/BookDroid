package com.google.firebase.heartbeatinfo;

import com.google.firebase.components.ComponentContainer;
import com.google.firebase.components.ComponentFactory;

/* compiled from: DefaultHeartBeatInfo */
final /* synthetic */ class DefaultHeartBeatInfo$$Lambda$4 implements ComponentFactory {
    private static final DefaultHeartBeatInfo$$Lambda$4 instance = new DefaultHeartBeatInfo$$Lambda$4();

    private DefaultHeartBeatInfo$$Lambda$4() {
    }

    public static ComponentFactory lambdaFactory$() {
        return instance;
    }

    public Object create(ComponentContainer componentContainer) {
        return DefaultHeartBeatInfo.lambda$component$4(componentContainer);
    }
}

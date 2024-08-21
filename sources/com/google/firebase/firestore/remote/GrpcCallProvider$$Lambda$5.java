package com.google.firebase.firestore.remote;

import java.util.concurrent.Callable;

/* compiled from: GrpcCallProvider */
final /* synthetic */ class GrpcCallProvider$$Lambda$5 implements Callable {
    private final GrpcCallProvider arg$1;

    private GrpcCallProvider$$Lambda$5(GrpcCallProvider grpcCallProvider) {
        this.arg$1 = grpcCallProvider;
    }

    public static Callable lambdaFactory$(GrpcCallProvider grpcCallProvider) {
        return new GrpcCallProvider$$Lambda$5(grpcCallProvider);
    }

    public Object call() {
        return GrpcCallProvider.lambda$initChannelTask$6(this.arg$1);
    }
}

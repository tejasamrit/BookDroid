package com.google.firebase.firestore.remote;

import p012io.grpc.ManagedChannel;

/* compiled from: GrpcCallProvider */
final /* synthetic */ class GrpcCallProvider$$Lambda$3 implements Runnable {
    private final GrpcCallProvider arg$1;
    private final ManagedChannel arg$2;

    private GrpcCallProvider$$Lambda$3(GrpcCallProvider grpcCallProvider, ManagedChannel managedChannel) {
        this.arg$1 = grpcCallProvider;
        this.arg$2 = managedChannel;
    }

    public static Runnable lambdaFactory$(GrpcCallProvider grpcCallProvider, ManagedChannel managedChannel) {
        return new GrpcCallProvider$$Lambda$3(grpcCallProvider, managedChannel);
    }

    public void run() {
        this.arg$1.asyncQueue.enqueueAndForget(GrpcCallProvider$$Lambda$7.lambdaFactory$(this.arg$1, this.arg$2));
    }
}

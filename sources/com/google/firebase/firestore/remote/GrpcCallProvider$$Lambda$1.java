package com.google.firebase.firestore.remote;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import p012io.grpc.ManagedChannel;
import p012io.grpc.MethodDescriptor;

/* compiled from: GrpcCallProvider */
final /* synthetic */ class GrpcCallProvider$$Lambda$1 implements Continuation {
    private final GrpcCallProvider arg$1;
    private final MethodDescriptor arg$2;

    private GrpcCallProvider$$Lambda$1(GrpcCallProvider grpcCallProvider, MethodDescriptor methodDescriptor) {
        this.arg$1 = grpcCallProvider;
        this.arg$2 = methodDescriptor;
    }

    public static Continuation lambdaFactory$(GrpcCallProvider grpcCallProvider, MethodDescriptor methodDescriptor) {
        return new GrpcCallProvider$$Lambda$1(grpcCallProvider, methodDescriptor);
    }

    public Object then(Task task) {
        return Tasks.forResult(((ManagedChannel) task.getResult()).newCall(this.arg$2, this.arg$1.callOptions));
    }
}

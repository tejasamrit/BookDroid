package p012io.grpc.internal;

import java.util.concurrent.Executor;
import p012io.grpc.CallOptions;
import p012io.grpc.InternalChannelz;
import p012io.grpc.InternalInstrumented;
import p012io.grpc.Metadata;
import p012io.grpc.MethodDescriptor;

/* renamed from: io.grpc.internal.ClientTransport */
public interface ClientTransport extends InternalInstrumented<InternalChannelz.SocketStats> {

    /* renamed from: io.grpc.internal.ClientTransport$PingCallback */
    public interface PingCallback {
        void onFailure(Throwable th);

        void onSuccess(long j);
    }

    ClientStream newStream(MethodDescriptor<?, ?> methodDescriptor, Metadata metadata, CallOptions callOptions);

    void ping(PingCallback pingCallback, Executor executor);
}

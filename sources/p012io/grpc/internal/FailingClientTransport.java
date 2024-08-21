package p012io.grpc.internal;

import com.google.common.base.Preconditions;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.SettableFuture;
import java.util.concurrent.Executor;
import p012io.grpc.CallOptions;
import p012io.grpc.InternalChannelz;
import p012io.grpc.InternalLogId;
import p012io.grpc.Metadata;
import p012io.grpc.MethodDescriptor;
import p012io.grpc.Status;
import p012io.grpc.internal.ClientStreamListener;
import p012io.grpc.internal.ClientTransport;

/* renamed from: io.grpc.internal.FailingClientTransport */
class FailingClientTransport implements ClientTransport {
    final Status error;
    private final ClientStreamListener.RpcProgress rpcProgress;

    FailingClientTransport(Status status, ClientStreamListener.RpcProgress rpcProgress2) {
        Preconditions.checkArgument(!status.isOk(), "error must not be OK");
        this.error = status;
        this.rpcProgress = rpcProgress2;
    }

    public ClientStream newStream(MethodDescriptor<?, ?> methodDescriptor, Metadata metadata, CallOptions callOptions) {
        return new FailingClientStream(this.error, this.rpcProgress);
    }

    public void ping(final ClientTransport.PingCallback pingCallback, Executor executor) {
        executor.execute(new Runnable() {
            public void run() {
                pingCallback.onFailure(FailingClientTransport.this.error.asException());
            }
        });
    }

    public ListenableFuture<InternalChannelz.SocketStats> getStats() {
        SettableFuture create = SettableFuture.create();
        create.set(null);
        return create;
    }

    public InternalLogId getLogId() {
        throw new UnsupportedOperationException("Not a real transport");
    }
}

package p012io.grpc.internal;

import com.google.common.base.MoreObjects;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.concurrent.Executor;
import p012io.grpc.Attributes;
import p012io.grpc.CallOptions;
import p012io.grpc.InternalChannelz;
import p012io.grpc.InternalLogId;
import p012io.grpc.Metadata;
import p012io.grpc.MethodDescriptor;
import p012io.grpc.Status;
import p012io.grpc.internal.ClientTransport;
import p012io.grpc.internal.ManagedClientTransport;

/* renamed from: io.grpc.internal.ForwardingConnectionClientTransport */
abstract class ForwardingConnectionClientTransport implements ConnectionClientTransport {
    /* access modifiers changed from: protected */
    public abstract ConnectionClientTransport delegate();

    ForwardingConnectionClientTransport() {
    }

    public Runnable start(ManagedClientTransport.C2463Listener listener) {
        return delegate().start(listener);
    }

    public void shutdown(Status status) {
        delegate().shutdown(status);
    }

    public void shutdownNow(Status status) {
        delegate().shutdownNow(status);
    }

    public ClientStream newStream(MethodDescriptor<?, ?> methodDescriptor, Metadata metadata, CallOptions callOptions) {
        return delegate().newStream(methodDescriptor, metadata, callOptions);
    }

    public void ping(ClientTransport.PingCallback pingCallback, Executor executor) {
        delegate().ping(pingCallback, executor);
    }

    public InternalLogId getLogId() {
        return delegate().getLogId();
    }

    public Attributes getAttributes() {
        return delegate().getAttributes();
    }

    public String toString() {
        return MoreObjects.toStringHelper((Object) this).add("delegate", (Object) delegate()).toString();
    }

    public ListenableFuture<InternalChannelz.SocketStats> getStats() {
        return delegate().getStats();
    }
}

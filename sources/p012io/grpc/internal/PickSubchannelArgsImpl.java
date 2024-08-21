package p012io.grpc.internal;

import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import p012io.grpc.CallOptions;
import p012io.grpc.LoadBalancer;
import p012io.grpc.Metadata;
import p012io.grpc.MethodDescriptor;

/* renamed from: io.grpc.internal.PickSubchannelArgsImpl */
final class PickSubchannelArgsImpl extends LoadBalancer.PickSubchannelArgs {
    private final CallOptions callOptions;
    private final Metadata headers;
    private final MethodDescriptor<?, ?> method;

    PickSubchannelArgsImpl(MethodDescriptor<?, ?> methodDescriptor, Metadata metadata, CallOptions callOptions2) {
        this.method = (MethodDescriptor) Preconditions.checkNotNull(methodDescriptor, "method");
        this.headers = (Metadata) Preconditions.checkNotNull(metadata, "headers");
        this.callOptions = (CallOptions) Preconditions.checkNotNull(callOptions2, "callOptions");
    }

    public Metadata getHeaders() {
        return this.headers;
    }

    public CallOptions getCallOptions() {
        return this.callOptions;
    }

    public MethodDescriptor<?, ?> getMethodDescriptor() {
        return this.method;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        PickSubchannelArgsImpl pickSubchannelArgsImpl = (PickSubchannelArgsImpl) obj;
        if (!Objects.equal(this.callOptions, pickSubchannelArgsImpl.callOptions) || !Objects.equal(this.headers, pickSubchannelArgsImpl.headers) || !Objects.equal(this.method, pickSubchannelArgsImpl.method)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return Objects.hashCode(this.callOptions, this.headers, this.method);
    }

    public final String toString() {
        return "[method=" + this.method + " headers=" + this.headers + " callOptions=" + this.callOptions + "]";
    }
}

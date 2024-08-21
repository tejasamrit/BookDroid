package p012io.grpc.internal;

import com.google.common.base.Objects;
import javax.annotation.Nullable;
import p012io.grpc.Attributes;
import p012io.grpc.MethodDescriptor;
import p012io.grpc.ServerStreamTracer;

/* renamed from: io.grpc.internal.ServerCallInfoImpl */
final class ServerCallInfoImpl<ReqT, RespT> extends ServerStreamTracer.ServerCallInfo<ReqT, RespT> {
    private final Attributes attributes;
    private final String authority;
    private final MethodDescriptor<ReqT, RespT> methodDescriptor;

    ServerCallInfoImpl(MethodDescriptor<ReqT, RespT> methodDescriptor2, Attributes attributes2, @Nullable String str) {
        this.methodDescriptor = methodDescriptor2;
        this.attributes = attributes2;
        this.authority = str;
    }

    public MethodDescriptor<ReqT, RespT> getMethodDescriptor() {
        return this.methodDescriptor;
    }

    public Attributes getAttributes() {
        return this.attributes;
    }

    @Nullable
    public String getAuthority() {
        return this.authority;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof ServerCallInfoImpl)) {
            return false;
        }
        ServerCallInfoImpl serverCallInfoImpl = (ServerCallInfoImpl) obj;
        if (!Objects.equal(this.methodDescriptor, serverCallInfoImpl.methodDescriptor) || !Objects.equal(this.attributes, serverCallInfoImpl.attributes) || !Objects.equal(this.authority, serverCallInfoImpl.authority)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return Objects.hashCode(this.methodDescriptor, this.attributes, this.authority);
    }
}

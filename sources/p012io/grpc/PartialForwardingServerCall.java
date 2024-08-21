package p012io.grpc;

import com.google.common.base.MoreObjects;

/* renamed from: io.grpc.PartialForwardingServerCall */
abstract class PartialForwardingServerCall<ReqT, RespT> extends ServerCall<ReqT, RespT> {
    /* access modifiers changed from: protected */
    public abstract ServerCall<?, ?> delegate();

    PartialForwardingServerCall() {
    }

    public void request(int i) {
        delegate().request(i);
    }

    public void sendHeaders(Metadata metadata) {
        delegate().sendHeaders(metadata);
    }

    public boolean isReady() {
        return delegate().isReady();
    }

    public void close(Status status, Metadata metadata) {
        delegate().close(status, metadata);
    }

    public boolean isCancelled() {
        return delegate().isCancelled();
    }

    public void setMessageCompression(boolean z) {
        delegate().setMessageCompression(z);
    }

    public void setCompression(String str) {
        delegate().setCompression(str);
    }

    public Attributes getAttributes() {
        return delegate().getAttributes();
    }

    public String getAuthority() {
        return delegate().getAuthority();
    }

    public String toString() {
        return MoreObjects.toStringHelper((Object) this).add("delegate", (Object) delegate()).toString();
    }
}

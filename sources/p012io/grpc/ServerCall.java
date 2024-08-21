package p012io.grpc;

import javax.annotation.Nullable;

/* renamed from: io.grpc.ServerCall */
public abstract class ServerCall<ReqT, RespT> {

    /* renamed from: io.grpc.ServerCall$Listener */
    public static abstract class C2354Listener<ReqT> {
        public void onCancel() {
        }

        public void onComplete() {
        }

        public void onHalfClose() {
        }

        public void onMessage(ReqT reqt) {
        }

        public void onReady() {
        }
    }

    public abstract void close(Status status, Metadata metadata);

    @Nullable
    public String getAuthority() {
        return null;
    }

    public abstract MethodDescriptor<ReqT, RespT> getMethodDescriptor();

    public abstract boolean isCancelled();

    public boolean isReady() {
        return true;
    }

    public abstract void request(int i);

    public abstract void sendHeaders(Metadata metadata);

    public abstract void sendMessage(RespT respt);

    public void setCompression(String str) {
    }

    public void setMessageCompression(boolean z) {
    }

    public Attributes getAttributes() {
        return Attributes.EMPTY;
    }
}

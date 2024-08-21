package p012io.grpc;

import p012io.grpc.ClientCall;

/* renamed from: io.grpc.ForwardingClientCallListener */
public abstract class ForwardingClientCallListener<RespT> extends PartialForwardingClientCallListener<RespT> {
    /* access modifiers changed from: protected */
    public abstract ClientCall.C2333Listener<RespT> delegate();

    public /* bridge */ /* synthetic */ void onClose(Status status, Metadata metadata) {
        super.onClose(status, metadata);
    }

    public /* bridge */ /* synthetic */ void onHeaders(Metadata metadata) {
        super.onHeaders(metadata);
    }

    public /* bridge */ /* synthetic */ void onReady() {
        super.onReady();
    }

    public /* bridge */ /* synthetic */ String toString() {
        return super.toString();
    }

    public void onMessage(RespT respt) {
        delegate().onMessage(respt);
    }

    /* renamed from: io.grpc.ForwardingClientCallListener$SimpleForwardingClientCallListener */
    public static abstract class SimpleForwardingClientCallListener<RespT> extends ForwardingClientCallListener<RespT> {
        private final ClientCall.C2333Listener<RespT> delegate;

        public /* bridge */ /* synthetic */ void onClose(Status status, Metadata metadata) {
            ForwardingClientCallListener.super.onClose(status, metadata);
        }

        public /* bridge */ /* synthetic */ void onHeaders(Metadata metadata) {
            ForwardingClientCallListener.super.onHeaders(metadata);
        }

        public /* bridge */ /* synthetic */ void onReady() {
            ForwardingClientCallListener.super.onReady();
        }

        public /* bridge */ /* synthetic */ String toString() {
            return ForwardingClientCallListener.super.toString();
        }

        protected SimpleForwardingClientCallListener(ClientCall.C2333Listener<RespT> listener) {
            this.delegate = listener;
        }

        /* access modifiers changed from: protected */
        public ClientCall.C2333Listener<RespT> delegate() {
            return this.delegate;
        }
    }
}

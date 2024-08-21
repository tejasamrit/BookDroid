package p012io.grpc.stub;

import com.google.common.base.Preconditions;
import java.util.concurrent.atomic.AtomicReference;
import p012io.grpc.CallOptions;
import p012io.grpc.Channel;
import p012io.grpc.ClientCall;
import p012io.grpc.ClientInterceptor;
import p012io.grpc.ForwardingClientCall;
import p012io.grpc.ForwardingClientCallListener;
import p012io.grpc.Metadata;
import p012io.grpc.MethodDescriptor;
import p012io.grpc.Status;

/* renamed from: io.grpc.stub.MetadataUtils */
public final class MetadataUtils {
    private MetadataUtils() {
    }

    public static <T extends AbstractStub<T>> T attachHeaders(T t, Metadata metadata) {
        return t.withInterceptors(newAttachHeadersInterceptor(metadata));
    }

    public static ClientInterceptor newAttachHeadersInterceptor(Metadata metadata) {
        return new HeaderAttachingClientInterceptor(metadata);
    }

    /* renamed from: io.grpc.stub.MetadataUtils$HeaderAttachingClientInterceptor */
    private static final class HeaderAttachingClientInterceptor implements ClientInterceptor {
        /* access modifiers changed from: private */
        public final Metadata extraHeaders;

        HeaderAttachingClientInterceptor(Metadata metadata) {
            this.extraHeaders = (Metadata) Preconditions.checkNotNull(metadata, "extraHeaders");
        }

        public <ReqT, RespT> ClientCall<ReqT, RespT> interceptCall(MethodDescriptor<ReqT, RespT> methodDescriptor, CallOptions callOptions, Channel channel) {
            return new HeaderAttachingClientCall(channel.newCall(methodDescriptor, callOptions));
        }

        /* renamed from: io.grpc.stub.MetadataUtils$HeaderAttachingClientInterceptor$HeaderAttachingClientCall */
        private final class HeaderAttachingClientCall<ReqT, RespT> extends ForwardingClientCall.SimpleForwardingClientCall<ReqT, RespT> {
            HeaderAttachingClientCall(ClientCall<ReqT, RespT> clientCall) {
                super(clientCall);
            }

            public void start(ClientCall.C2333Listener<RespT> listener, Metadata metadata) {
                metadata.merge(HeaderAttachingClientInterceptor.this.extraHeaders);
                super.start(listener, metadata);
            }
        }
    }

    public static <T extends AbstractStub<T>> T captureMetadata(T t, AtomicReference<Metadata> atomicReference, AtomicReference<Metadata> atomicReference2) {
        return t.withInterceptors(newCaptureMetadataInterceptor(atomicReference, atomicReference2));
    }

    public static ClientInterceptor newCaptureMetadataInterceptor(AtomicReference<Metadata> atomicReference, AtomicReference<Metadata> atomicReference2) {
        return new MetadataCapturingClientInterceptor(atomicReference, atomicReference2);
    }

    /* renamed from: io.grpc.stub.MetadataUtils$MetadataCapturingClientInterceptor */
    private static final class MetadataCapturingClientInterceptor implements ClientInterceptor {
        final AtomicReference<Metadata> headersCapture;
        final AtomicReference<Metadata> trailersCapture;

        MetadataCapturingClientInterceptor(AtomicReference<Metadata> atomicReference, AtomicReference<Metadata> atomicReference2) {
            this.headersCapture = (AtomicReference) Preconditions.checkNotNull(atomicReference, "headersCapture");
            this.trailersCapture = (AtomicReference) Preconditions.checkNotNull(atomicReference2, "trailersCapture");
        }

        public <ReqT, RespT> ClientCall<ReqT, RespT> interceptCall(MethodDescriptor<ReqT, RespT> methodDescriptor, CallOptions callOptions, Channel channel) {
            return new MetadataCapturingClientCall(channel.newCall(methodDescriptor, callOptions));
        }

        /* renamed from: io.grpc.stub.MetadataUtils$MetadataCapturingClientInterceptor$MetadataCapturingClientCall */
        private final class MetadataCapturingClientCall<ReqT, RespT> extends ForwardingClientCall.SimpleForwardingClientCall<ReqT, RespT> {
            MetadataCapturingClientCall(ClientCall<ReqT, RespT> clientCall) {
                super(clientCall);
            }

            public void start(ClientCall.C2333Listener<RespT> listener, Metadata metadata) {
                MetadataCapturingClientInterceptor.this.headersCapture.set((Object) null);
                MetadataCapturingClientInterceptor.this.trailersCapture.set((Object) null);
                super.start(new MetadataCapturingClientCallListener(listener), metadata);
            }

            /* renamed from: io.grpc.stub.MetadataUtils$MetadataCapturingClientInterceptor$MetadataCapturingClientCall$MetadataCapturingClientCallListener */
            private final class MetadataCapturingClientCallListener extends ForwardingClientCallListener.SimpleForwardingClientCallListener<RespT> {
                MetadataCapturingClientCallListener(ClientCall.C2333Listener<RespT> listener) {
                    super(listener);
                }

                public void onHeaders(Metadata metadata) {
                    MetadataCapturingClientInterceptor.this.headersCapture.set(metadata);
                    super.onHeaders(metadata);
                }

                public void onClose(Status status, Metadata metadata) {
                    MetadataCapturingClientInterceptor.this.trailersCapture.set(metadata);
                    super.onClose(status, metadata);
                }
            }
        }
    }
}

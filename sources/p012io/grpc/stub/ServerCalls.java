package p012io.grpc.stub;

import com.google.common.base.Preconditions;
import p012io.grpc.Metadata;
import p012io.grpc.MethodDescriptor;
import p012io.grpc.ServerCall;
import p012io.grpc.ServerCallHandler;
import p012io.grpc.Status;

/* renamed from: io.grpc.stub.ServerCalls */
public final class ServerCalls {
    static final String MISSING_REQUEST = "Half-closed without a request";
    static final String TOO_MANY_REQUESTS = "Too many requests";

    /* renamed from: io.grpc.stub.ServerCalls$BidiStreamingMethod */
    public interface BidiStreamingMethod<ReqT, RespT> extends StreamingRequestMethod<ReqT, RespT> {
    }

    /* renamed from: io.grpc.stub.ServerCalls$ClientStreamingMethod */
    public interface ClientStreamingMethod<ReqT, RespT> extends StreamingRequestMethod<ReqT, RespT> {
    }

    /* renamed from: io.grpc.stub.ServerCalls$ServerStreamingMethod */
    public interface ServerStreamingMethod<ReqT, RespT> extends UnaryRequestMethod<ReqT, RespT> {
    }

    /* renamed from: io.grpc.stub.ServerCalls$StreamingRequestMethod */
    private interface StreamingRequestMethod<ReqT, RespT> {
        StreamObserver<ReqT> invoke(StreamObserver<RespT> streamObserver);
    }

    /* renamed from: io.grpc.stub.ServerCalls$UnaryMethod */
    public interface UnaryMethod<ReqT, RespT> extends UnaryRequestMethod<ReqT, RespT> {
    }

    /* renamed from: io.grpc.stub.ServerCalls$UnaryRequestMethod */
    private interface UnaryRequestMethod<ReqT, RespT> {
        void invoke(ReqT reqt, StreamObserver<RespT> streamObserver);
    }

    private ServerCalls() {
    }

    public static <ReqT, RespT> ServerCallHandler<ReqT, RespT> asyncUnaryCall(UnaryMethod<ReqT, RespT> unaryMethod) {
        return asyncUnaryRequestCall(unaryMethod);
    }

    public static <ReqT, RespT> ServerCallHandler<ReqT, RespT> asyncServerStreamingCall(ServerStreamingMethod<ReqT, RespT> serverStreamingMethod) {
        return asyncUnaryRequestCall(serverStreamingMethod);
    }

    public static <ReqT, RespT> ServerCallHandler<ReqT, RespT> asyncClientStreamingCall(ClientStreamingMethod<ReqT, RespT> clientStreamingMethod) {
        return asyncStreamingRequestCall(clientStreamingMethod);
    }

    public static <ReqT, RespT> ServerCallHandler<ReqT, RespT> asyncBidiStreamingCall(BidiStreamingMethod<ReqT, RespT> bidiStreamingMethod) {
        return asyncStreamingRequestCall(bidiStreamingMethod);
    }

    /* renamed from: io.grpc.stub.ServerCalls$UnaryServerCallHandler */
    private static final class UnaryServerCallHandler<ReqT, RespT> implements ServerCallHandler<ReqT, RespT> {
        /* access modifiers changed from: private */
        public final UnaryRequestMethod<ReqT, RespT> method;

        UnaryServerCallHandler(UnaryRequestMethod<ReqT, RespT> unaryRequestMethod) {
            this.method = unaryRequestMethod;
        }

        public ServerCall.C2354Listener<ReqT> startCall(ServerCall<ReqT, RespT> serverCall, Metadata metadata) {
            Preconditions.checkArgument(serverCall.getMethodDescriptor().getType().clientSendsOneMessage(), "asyncUnaryRequestCall is only for clientSendsOneMessage methods");
            ServerCallStreamObserverImpl serverCallStreamObserverImpl = new ServerCallStreamObserverImpl(serverCall);
            serverCall.request(2);
            return new UnaryServerCallListener(serverCallStreamObserverImpl, serverCall);
        }

        /* renamed from: io.grpc.stub.ServerCalls$UnaryServerCallHandler$UnaryServerCallListener */
        private final class UnaryServerCallListener extends ServerCall.C2354Listener<ReqT> {
            private final ServerCall<ReqT, RespT> call;
            private boolean canInvoke = true;
            private ReqT request;
            private final ServerCallStreamObserverImpl<ReqT, RespT> responseObserver;
            private boolean wasReady;

            UnaryServerCallListener(ServerCallStreamObserverImpl<ReqT, RespT> serverCallStreamObserverImpl, ServerCall<ReqT, RespT> serverCall) {
                this.call = serverCall;
                this.responseObserver = serverCallStreamObserverImpl;
            }

            public void onMessage(ReqT reqt) {
                if (this.request != null) {
                    this.call.close(Status.INTERNAL.withDescription(ServerCalls.TOO_MANY_REQUESTS), new Metadata());
                    this.canInvoke = false;
                    return;
                }
                this.request = reqt;
            }

            public void onHalfClose() {
                if (this.canInvoke) {
                    if (this.request == null) {
                        this.call.close(Status.INTERNAL.withDescription(ServerCalls.MISSING_REQUEST), new Metadata());
                        return;
                    }
                    UnaryServerCallHandler.this.method.invoke(this.request, this.responseObserver);
                    this.request = null;
                    this.responseObserver.freeze();
                    if (this.wasReady) {
                        onReady();
                    }
                }
            }

            public void onCancel() {
                this.responseObserver.cancelled = true;
                if (this.responseObserver.onCancelHandler != null) {
                    this.responseObserver.onCancelHandler.run();
                }
            }

            public void onReady() {
                this.wasReady = true;
                if (this.responseObserver.onReadyHandler != null) {
                    this.responseObserver.onReadyHandler.run();
                }
            }
        }
    }

    private static <ReqT, RespT> ServerCallHandler<ReqT, RespT> asyncUnaryRequestCall(UnaryRequestMethod<ReqT, RespT> unaryRequestMethod) {
        return new UnaryServerCallHandler(unaryRequestMethod);
    }

    /* renamed from: io.grpc.stub.ServerCalls$StreamingServerCallHandler */
    private static final class StreamingServerCallHandler<ReqT, RespT> implements ServerCallHandler<ReqT, RespT> {
        private final StreamingRequestMethod<ReqT, RespT> method;

        StreamingServerCallHandler(StreamingRequestMethod<ReqT, RespT> streamingRequestMethod) {
            this.method = streamingRequestMethod;
        }

        public ServerCall.C2354Listener<ReqT> startCall(ServerCall<ReqT, RespT> serverCall, Metadata metadata) {
            ServerCallStreamObserverImpl serverCallStreamObserverImpl = new ServerCallStreamObserverImpl(serverCall);
            StreamObserver<ReqT> invoke = this.method.invoke(serverCallStreamObserverImpl);
            serverCallStreamObserverImpl.freeze();
            if (serverCallStreamObserverImpl.autoFlowControlEnabled) {
                serverCall.request(1);
            }
            return new StreamingServerCallListener(invoke, serverCallStreamObserverImpl, serverCall);
        }

        /* renamed from: io.grpc.stub.ServerCalls$StreamingServerCallHandler$StreamingServerCallListener */
        private final class StreamingServerCallListener extends ServerCall.C2354Listener<ReqT> {
            private final ServerCall<ReqT, RespT> call;
            private boolean halfClosed = false;
            private final StreamObserver<ReqT> requestObserver;
            private final ServerCallStreamObserverImpl<ReqT, RespT> responseObserver;

            StreamingServerCallListener(StreamObserver<ReqT> streamObserver, ServerCallStreamObserverImpl<ReqT, RespT> serverCallStreamObserverImpl, ServerCall<ReqT, RespT> serverCall) {
                this.requestObserver = streamObserver;
                this.responseObserver = serverCallStreamObserverImpl;
                this.call = serverCall;
            }

            public void onMessage(ReqT reqt) {
                this.requestObserver.onNext(reqt);
                if (this.responseObserver.autoFlowControlEnabled) {
                    this.call.request(1);
                }
            }

            public void onHalfClose() {
                this.halfClosed = true;
                this.requestObserver.onCompleted();
            }

            public void onCancel() {
                this.responseObserver.cancelled = true;
                if (this.responseObserver.onCancelHandler != null) {
                    this.responseObserver.onCancelHandler.run();
                }
                if (!this.halfClosed) {
                    this.requestObserver.onError(Status.CANCELLED.withDescription("cancelled before receiving half close").asRuntimeException());
                }
            }

            public void onReady() {
                if (this.responseObserver.onReadyHandler != null) {
                    this.responseObserver.onReadyHandler.run();
                }
            }
        }
    }

    private static <ReqT, RespT> ServerCallHandler<ReqT, RespT> asyncStreamingRequestCall(StreamingRequestMethod<ReqT, RespT> streamingRequestMethod) {
        return new StreamingServerCallHandler(streamingRequestMethod);
    }

    /* renamed from: io.grpc.stub.ServerCalls$ServerCallStreamObserverImpl */
    private static final class ServerCallStreamObserverImpl<ReqT, RespT> extends ServerCallStreamObserver<RespT> {
        private boolean aborted = false;
        /* access modifiers changed from: private */
        public boolean autoFlowControlEnabled = true;
        final ServerCall<ReqT, RespT> call;
        volatile boolean cancelled;
        private boolean completed = false;
        private boolean frozen;
        /* access modifiers changed from: private */
        public Runnable onCancelHandler;
        /* access modifiers changed from: private */
        public Runnable onReadyHandler;
        private boolean sentHeaders;

        ServerCallStreamObserverImpl(ServerCall<ReqT, RespT> serverCall) {
            this.call = serverCall;
        }

        /* access modifiers changed from: private */
        public void freeze() {
            this.frozen = true;
        }

        public void setMessageCompression(boolean z) {
            this.call.setMessageCompression(z);
        }

        public void setCompression(String str) {
            this.call.setCompression(str);
        }

        public void onNext(RespT respt) {
            if (!this.cancelled) {
                Preconditions.checkState(!this.aborted, "Stream was terminated by error, no further calls are allowed");
                Preconditions.checkState(!this.completed, "Stream is already completed, no further calls are allowed");
                if (!this.sentHeaders) {
                    this.call.sendHeaders(new Metadata());
                    this.sentHeaders = true;
                }
                this.call.sendMessage(respt);
            } else if (this.onCancelHandler == null) {
                throw Status.CANCELLED.withDescription("call already cancelled").asRuntimeException();
            }
        }

        public void onError(Throwable th) {
            Metadata trailersFromThrowable = Status.trailersFromThrowable(th);
            if (trailersFromThrowable == null) {
                trailersFromThrowable = new Metadata();
            }
            this.call.close(Status.fromThrowable(th), trailersFromThrowable);
            this.aborted = true;
        }

        public void onCompleted() {
            if (!this.cancelled) {
                this.call.close(Status.f489OK, new Metadata());
                this.completed = true;
            } else if (this.onCancelHandler == null) {
                throw Status.CANCELLED.withDescription("call already cancelled").asRuntimeException();
            }
        }

        public boolean isReady() {
            return this.call.isReady();
        }

        public void setOnReadyHandler(Runnable runnable) {
            Preconditions.checkState(!this.frozen, "Cannot alter onReadyHandler after initialization. May only be called during the initial call to the application, before the service returns its StreamObserver");
            this.onReadyHandler = runnable;
        }

        public boolean isCancelled() {
            return this.call.isCancelled();
        }

        public void setOnCancelHandler(Runnable runnable) {
            Preconditions.checkState(!this.frozen, "Cannot alter onCancelHandler after initialization. May only be called during the initial call to the application, before the service returns its StreamObserver");
            this.onCancelHandler = runnable;
        }

        public void disableAutoInboundFlowControl() {
            Preconditions.checkState(!this.frozen, "Cannot disable auto flow control after initialization");
            this.autoFlowControlEnabled = false;
        }

        public void request(int i) {
            this.call.request(i);
        }
    }

    public static void asyncUnimplementedUnaryCall(MethodDescriptor<?, ?> methodDescriptor, StreamObserver<?> streamObserver) {
        Preconditions.checkNotNull(methodDescriptor, "methodDescriptor");
        Preconditions.checkNotNull(streamObserver, "responseObserver");
        streamObserver.onError(Status.UNIMPLEMENTED.withDescription(String.format("Method %s is unimplemented", new Object[]{methodDescriptor.getFullMethodName()})).asRuntimeException());
    }

    public static <T> StreamObserver<T> asyncUnimplementedStreamingCall(MethodDescriptor<?, ?> methodDescriptor, StreamObserver<?> streamObserver) {
        asyncUnimplementedUnaryCall(methodDescriptor, streamObserver);
        return new NoopStreamObserver();
    }

    /* renamed from: io.grpc.stub.ServerCalls$NoopStreamObserver */
    static class NoopStreamObserver<V> implements StreamObserver<V> {
        public void onCompleted() {
        }

        public void onError(Throwable th) {
        }

        public void onNext(V v) {
        }

        NoopStreamObserver() {
        }
    }
}

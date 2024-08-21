package p012io.grpc.stub;

import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import com.google.common.util.concurrent.AbstractFuture;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Future;
import java.util.concurrent.locks.LockSupport;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Nullable;
import p012io.grpc.CallOptions;
import p012io.grpc.Channel;
import p012io.grpc.ClientCall;
import p012io.grpc.Metadata;
import p012io.grpc.MethodDescriptor;
import p012io.grpc.Status;
import p012io.grpc.StatusException;
import p012io.grpc.StatusRuntimeException;

/* renamed from: io.grpc.stub.ClientCalls */
public final class ClientCalls {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    static final CallOptions.Key<StubType> STUB_TYPE_OPTION = CallOptions.Key.create("internal-stub-type");
    private static final Logger logger = Logger.getLogger(ClientCalls.class.getName());

    /* renamed from: io.grpc.stub.ClientCalls$StubType */
    enum StubType {
        BLOCKING,
        FUTURE,
        ASYNC
    }

    private ClientCalls() {
    }

    public static <ReqT, RespT> void asyncUnaryCall(ClientCall<ReqT, RespT> clientCall, ReqT reqt, StreamObserver<RespT> streamObserver) {
        asyncUnaryRequestCall(clientCall, reqt, streamObserver, false);
    }

    public static <ReqT, RespT> void asyncServerStreamingCall(ClientCall<ReqT, RespT> clientCall, ReqT reqt, StreamObserver<RespT> streamObserver) {
        asyncUnaryRequestCall(clientCall, reqt, streamObserver, true);
    }

    public static <ReqT, RespT> StreamObserver<ReqT> asyncClientStreamingCall(ClientCall<ReqT, RespT> clientCall, StreamObserver<RespT> streamObserver) {
        return asyncStreamingRequestCall(clientCall, streamObserver, false);
    }

    public static <ReqT, RespT> StreamObserver<ReqT> asyncBidiStreamingCall(ClientCall<ReqT, RespT> clientCall, StreamObserver<RespT> streamObserver) {
        return asyncStreamingRequestCall(clientCall, streamObserver, true);
    }

    public static <ReqT, RespT> RespT blockingUnaryCall(ClientCall<ReqT, RespT> clientCall, ReqT reqt) {
        try {
            return getUnchecked(futureUnaryCall(clientCall, reqt));
        } catch (RuntimeException e) {
            throw cancelThrow(clientCall, e);
        } catch (Error e2) {
            throw cancelThrow(clientCall, e2);
        }
    }

    /* JADX WARNING: type inference failed for: r3v0, types: [io.grpc.MethodDescriptor, io.grpc.MethodDescriptor<ReqT, RespT>] */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x004c  */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static <ReqT, RespT> RespT blockingUnaryCall(p012io.grpc.Channel r2, p012io.grpc.MethodDescriptor<ReqT, RespT> r3, p012io.grpc.CallOptions r4, ReqT r5) {
        /*
            io.grpc.stub.ClientCalls$ThreadlessExecutor r0 = new io.grpc.stub.ClientCalls$ThreadlessExecutor
            r0.<init>()
            io.grpc.CallOptions r4 = r4.withExecutor(r0)
            io.grpc.ClientCall r2 = r2.newCall(r3, r4)
            r3 = 0
            com.google.common.util.concurrent.ListenableFuture r4 = futureUnaryCall(r2, r5)     // Catch:{ RuntimeException -> 0x0044, Error -> 0x003e }
        L_0x0012:
            boolean r5 = r4.isDone()     // Catch:{ RuntimeException -> 0x0044, Error -> 0x003e }
            if (r5 != 0) goto L_0x002e
            r0.waitAndDrain()     // Catch:{ InterruptedException -> 0x001c }
            goto L_0x0012
        L_0x001c:
            r3 = move-exception
            r5 = 1
            java.lang.String r1 = "Thread interrupted"
            r2.cancel(r1, r3)     // Catch:{ RuntimeException -> 0x002b, Error -> 0x0028, all -> 0x0025 }
            r3 = 1
            goto L_0x0012
        L_0x0025:
            r2 = move-exception
            r3 = 1
            goto L_0x004a
        L_0x0028:
            r4 = move-exception
            r3 = 1
            goto L_0x003f
        L_0x002b:
            r4 = move-exception
            r3 = 1
            goto L_0x0045
        L_0x002e:
            java.lang.Object r2 = getUnchecked(r4)     // Catch:{ RuntimeException -> 0x0044, Error -> 0x003e }
            if (r3 == 0) goto L_0x003b
            java.lang.Thread r3 = java.lang.Thread.currentThread()
            r3.interrupt()
        L_0x003b:
            return r2
        L_0x003c:
            r2 = move-exception
            goto L_0x004a
        L_0x003e:
            r4 = move-exception
        L_0x003f:
            java.lang.RuntimeException r2 = cancelThrow(r2, r4)     // Catch:{ all -> 0x003c }
            throw r2     // Catch:{ all -> 0x003c }
        L_0x0044:
            r4 = move-exception
        L_0x0045:
            java.lang.RuntimeException r2 = cancelThrow(r2, r4)     // Catch:{ all -> 0x003c }
            throw r2     // Catch:{ all -> 0x003c }
        L_0x004a:
            if (r3 == 0) goto L_0x0053
            java.lang.Thread r3 = java.lang.Thread.currentThread()
            r3.interrupt()
        L_0x0053:
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: p012io.grpc.stub.ClientCalls.blockingUnaryCall(io.grpc.Channel, io.grpc.MethodDescriptor, io.grpc.CallOptions, java.lang.Object):java.lang.Object");
    }

    public static <ReqT, RespT> Iterator<RespT> blockingServerStreamingCall(ClientCall<ReqT, RespT> clientCall, ReqT reqt) {
        BlockingResponseStream blockingResponseStream = new BlockingResponseStream(clientCall);
        asyncUnaryRequestCall(clientCall, reqt, blockingResponseStream.listener(), true);
        return blockingResponseStream;
    }

    public static <ReqT, RespT> Iterator<RespT> blockingServerStreamingCall(Channel channel, MethodDescriptor<ReqT, RespT> methodDescriptor, CallOptions callOptions, ReqT reqt) {
        ThreadlessExecutor threadlessExecutor = new ThreadlessExecutor();
        ClientCall<RequestT, ResponseT> newCall = channel.newCall(methodDescriptor, callOptions.withExecutor(threadlessExecutor));
        BlockingResponseStream blockingResponseStream = new BlockingResponseStream(newCall, threadlessExecutor);
        asyncUnaryRequestCall(newCall, reqt, blockingResponseStream.listener(), true);
        return blockingResponseStream;
    }

    public static <ReqT, RespT> ListenableFuture<RespT> futureUnaryCall(ClientCall<ReqT, RespT> clientCall, ReqT reqt) {
        GrpcFuture grpcFuture = new GrpcFuture(clientCall);
        asyncUnaryRequestCall(clientCall, reqt, new UnaryStreamToFuture(grpcFuture), false);
        return grpcFuture;
    }

    private static <V> V getUnchecked(Future<V> future) {
        try {
            return future.get();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw Status.CANCELLED.withDescription("Thread interrupted").withCause(e).asRuntimeException();
        } catch (ExecutionException e2) {
            throw toStatusRuntimeException(e2.getCause());
        }
    }

    private static StatusRuntimeException toStatusRuntimeException(Throwable th) {
        Throwable th2 = (Throwable) Preconditions.checkNotNull(th, "t");
        while (th2 != null) {
            if (th2 instanceof StatusException) {
                StatusException statusException = (StatusException) th2;
                return new StatusRuntimeException(statusException.getStatus(), statusException.getTrailers());
            } else if (th2 instanceof StatusRuntimeException) {
                StatusRuntimeException statusRuntimeException = (StatusRuntimeException) th2;
                return new StatusRuntimeException(statusRuntimeException.getStatus(), statusRuntimeException.getTrailers());
            } else {
                th2 = th2.getCause();
            }
        }
        return Status.UNKNOWN.withDescription("unexpected exception").withCause(th).asRuntimeException();
    }

    private static RuntimeException cancelThrow(ClientCall<?, ?> clientCall, Throwable th) {
        try {
            clientCall.cancel((String) null, th);
        } catch (Throwable th2) {
            logger.log(Level.SEVERE, "RuntimeException encountered while closing call", th2);
        }
        if (th instanceof RuntimeException) {
            throw ((RuntimeException) th);
        } else if (th instanceof Error) {
            throw ((Error) th);
        } else {
            throw new AssertionError(th);
        }
    }

    private static <ReqT, RespT> void asyncUnaryRequestCall(ClientCall<ReqT, RespT> clientCall, ReqT reqt, StreamObserver<RespT> streamObserver, boolean z) {
        asyncUnaryRequestCall(clientCall, reqt, new StreamObserverToCallListenerAdapter(streamObserver, new CallToStreamObserverAdapter(clientCall), z), z);
    }

    private static <ReqT, RespT> void asyncUnaryRequestCall(ClientCall<ReqT, RespT> clientCall, ReqT reqt, ClientCall.C2333Listener<RespT> listener, boolean z) {
        startCall(clientCall, listener, z);
        try {
            clientCall.sendMessage(reqt);
            clientCall.halfClose();
        } catch (RuntimeException e) {
            throw cancelThrow(clientCall, e);
        } catch (Error e2) {
            throw cancelThrow(clientCall, e2);
        }
    }

    private static <ReqT, RespT> StreamObserver<ReqT> asyncStreamingRequestCall(ClientCall<ReqT, RespT> clientCall, StreamObserver<RespT> streamObserver, boolean z) {
        CallToStreamObserverAdapter callToStreamObserverAdapter = new CallToStreamObserverAdapter(clientCall);
        startCall(clientCall, new StreamObserverToCallListenerAdapter(streamObserver, callToStreamObserverAdapter, z), z);
        return callToStreamObserverAdapter;
    }

    private static <ReqT, RespT> void startCall(ClientCall<ReqT, RespT> clientCall, ClientCall.C2333Listener<RespT> listener, boolean z) {
        clientCall.start(listener, new Metadata());
        if (z) {
            clientCall.request(1);
        } else {
            clientCall.request(2);
        }
    }

    /* renamed from: io.grpc.stub.ClientCalls$CallToStreamObserverAdapter */
    private static final class CallToStreamObserverAdapter<T> extends ClientCallStreamObserver<T> {
        private boolean aborted = false;
        /* access modifiers changed from: private */
        public boolean autoFlowControlEnabled = true;
        private final ClientCall<T, ?> call;
        private boolean completed = false;
        private boolean frozen;
        /* access modifiers changed from: private */
        public Runnable onReadyHandler;

        CallToStreamObserverAdapter(ClientCall<T, ?> clientCall) {
            this.call = clientCall;
        }

        /* access modifiers changed from: private */
        public void freeze() {
            this.frozen = true;
        }

        public void onNext(T t) {
            Preconditions.checkState(!this.aborted, "Stream was terminated by error, no further calls are allowed");
            Preconditions.checkState(!this.completed, "Stream is already completed, no further calls are allowed");
            this.call.sendMessage(t);
        }

        public void onError(Throwable th) {
            this.call.cancel("Cancelled by client with StreamObserver.onError()", th);
            this.aborted = true;
        }

        public void onCompleted() {
            this.call.halfClose();
            this.completed = true;
        }

        public boolean isReady() {
            return this.call.isReady();
        }

        public void setOnReadyHandler(Runnable runnable) {
            if (!this.frozen) {
                this.onReadyHandler = runnable;
                return;
            }
            throw new IllegalStateException("Cannot alter onReadyHandler after call started. Use ClientResponseObserver");
        }

        public void disableAutoInboundFlowControl() {
            if (!this.frozen) {
                this.autoFlowControlEnabled = false;
                return;
            }
            throw new IllegalStateException("Cannot disable auto flow control after call started. Use ClientResponseObserver");
        }

        public void request(int i) {
            this.call.request(i);
        }

        public void setMessageCompression(boolean z) {
            this.call.setMessageCompression(z);
        }

        public void cancel(@Nullable String str, @Nullable Throwable th) {
            this.call.cancel(str, th);
        }
    }

    /* renamed from: io.grpc.stub.ClientCalls$StreamObserverToCallListenerAdapter */
    private static final class StreamObserverToCallListenerAdapter<ReqT, RespT> extends ClientCall.C2333Listener<RespT> {
        private final CallToStreamObserverAdapter<ReqT> adapter;
        private boolean firstResponseReceived;
        private final StreamObserver<RespT> observer;
        private final boolean streamingResponse;

        public void onHeaders(Metadata metadata) {
        }

        StreamObserverToCallListenerAdapter(StreamObserver<RespT> streamObserver, CallToStreamObserverAdapter<ReqT> callToStreamObserverAdapter, boolean z) {
            this.observer = streamObserver;
            this.streamingResponse = z;
            this.adapter = callToStreamObserverAdapter;
            if (streamObserver instanceof ClientResponseObserver) {
                ((ClientResponseObserver) streamObserver).beforeStart(callToStreamObserverAdapter);
            }
            callToStreamObserverAdapter.freeze();
        }

        public void onMessage(RespT respt) {
            if (!this.firstResponseReceived || this.streamingResponse) {
                this.firstResponseReceived = true;
                this.observer.onNext(respt);
                if (this.streamingResponse && this.adapter.autoFlowControlEnabled) {
                    this.adapter.request(1);
                    return;
                }
                return;
            }
            throw Status.INTERNAL.withDescription("More than one responses received for unary or client-streaming call").asRuntimeException();
        }

        public void onClose(Status status, Metadata metadata) {
            if (status.isOk()) {
                this.observer.onCompleted();
            } else {
                this.observer.onError(status.asRuntimeException(metadata));
            }
        }

        public void onReady() {
            if (this.adapter.onReadyHandler != null) {
                this.adapter.onReadyHandler.run();
            }
        }
    }

    /* renamed from: io.grpc.stub.ClientCalls$UnaryStreamToFuture */
    private static final class UnaryStreamToFuture<RespT> extends ClientCall.C2333Listener<RespT> {
        private final GrpcFuture<RespT> responseFuture;
        private RespT value;

        public void onHeaders(Metadata metadata) {
        }

        UnaryStreamToFuture(GrpcFuture<RespT> grpcFuture) {
            this.responseFuture = grpcFuture;
        }

        public void onMessage(RespT respt) {
            if (this.value == null) {
                this.value = respt;
                return;
            }
            throw Status.INTERNAL.withDescription("More than one value received for unary call").asRuntimeException();
        }

        public void onClose(Status status, Metadata metadata) {
            if (status.isOk()) {
                if (this.value == null) {
                    this.responseFuture.setException(Status.INTERNAL.withDescription("No value received for unary call").asRuntimeException(metadata));
                }
                this.responseFuture.set(this.value);
                return;
            }
            this.responseFuture.setException(status.asRuntimeException(metadata));
        }
    }

    /* renamed from: io.grpc.stub.ClientCalls$GrpcFuture */
    private static final class GrpcFuture<RespT> extends AbstractFuture<RespT> {
        private final ClientCall<?, RespT> call;

        GrpcFuture(ClientCall<?, RespT> clientCall) {
            this.call = clientCall;
        }

        /* access modifiers changed from: protected */
        public void interruptTask() {
            this.call.cancel("GrpcFuture was cancelled", (Throwable) null);
        }

        /* access modifiers changed from: protected */
        public boolean set(@Nullable RespT respt) {
            return super.set(respt);
        }

        /* access modifiers changed from: protected */
        public boolean setException(Throwable th) {
            return super.setException(th);
        }

        /* access modifiers changed from: protected */
        public String pendingToString() {
            return MoreObjects.toStringHelper((Object) this).add("clientCall", (Object) this.call).toString();
        }
    }

    /* renamed from: io.grpc.stub.ClientCalls$BlockingResponseStream */
    private static final class BlockingResponseStream<T> implements Iterator<T> {
        /* access modifiers changed from: private */
        public final BlockingQueue<Object> buffer;
        private final ClientCall<?, T> call;
        private Object last;
        private final ClientCall.C2333Listener<T> listener;
        private final ThreadlessExecutor threadless;

        BlockingResponseStream(ClientCall<?, T> clientCall) {
            this(clientCall, (ThreadlessExecutor) null);
        }

        BlockingResponseStream(ClientCall<?, T> clientCall, ThreadlessExecutor threadlessExecutor) {
            this.buffer = new ArrayBlockingQueue(2);
            this.listener = new QueuingListener();
            this.call = clientCall;
            this.threadless = threadlessExecutor;
        }

        /* access modifiers changed from: package-private */
        public ClientCall.C2333Listener<T> listener() {
            return this.listener;
        }

        /* JADX WARNING: Removed duplicated region for block: B:30:0x0047  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        private java.lang.Object waitForNext() {
            /*
                r4 = this;
                r0 = 1
                r1 = 0
                io.grpc.stub.ClientCalls$ThreadlessExecutor r2 = r4.threadless     // Catch:{ all -> 0x0044 }
                java.lang.String r3 = "Thread interrupted"
                if (r2 != 0) goto L_0x0024
            L_0x0008:
                java.util.concurrent.BlockingQueue<java.lang.Object> r2 = r4.buffer     // Catch:{ InterruptedException -> 0x0018 }
                java.lang.Object r0 = r2.take()     // Catch:{ InterruptedException -> 0x0018 }
                if (r1 == 0) goto L_0x0017
                java.lang.Thread r1 = java.lang.Thread.currentThread()
                r1.interrupt()
            L_0x0017:
                return r0
            L_0x0018:
                r1 = move-exception
                io.grpc.ClientCall<?, T> r2 = r4.call     // Catch:{ all -> 0x0020 }
                r2.cancel(r3, r1)     // Catch:{ all -> 0x0020 }
                r1 = 1
                goto L_0x0008
            L_0x0020:
                r1 = move-exception
                r0 = r1
                r1 = 1
                goto L_0x0045
            L_0x0024:
                java.util.concurrent.BlockingQueue<java.lang.Object> r2 = r4.buffer     // Catch:{ all -> 0x0044 }
                java.lang.Object r2 = r2.poll()     // Catch:{ all -> 0x0044 }
                if (r2 != 0) goto L_0x003a
                io.grpc.stub.ClientCalls$ThreadlessExecutor r2 = r4.threadless     // Catch:{ InterruptedException -> 0x0032 }
                r2.waitAndDrain()     // Catch:{ InterruptedException -> 0x0032 }
                goto L_0x0024
            L_0x0032:
                r1 = move-exception
                io.grpc.ClientCall<?, T> r2 = r4.call     // Catch:{ all -> 0x0020 }
                r2.cancel(r3, r1)     // Catch:{ all -> 0x0020 }
                r1 = 1
                goto L_0x0024
            L_0x003a:
                if (r1 == 0) goto L_0x0043
                java.lang.Thread r0 = java.lang.Thread.currentThread()
                r0.interrupt()
            L_0x0043:
                return r2
            L_0x0044:
                r0 = move-exception
            L_0x0045:
                if (r1 == 0) goto L_0x004e
                java.lang.Thread r1 = java.lang.Thread.currentThread()
                r1.interrupt()
            L_0x004e:
                throw r0
            */
            throw new UnsupportedOperationException("Method not decompiled: p012io.grpc.stub.ClientCalls.BlockingResponseStream.waitForNext():java.lang.Object");
        }

        public boolean hasNext() {
            Object obj;
            while (true) {
                obj = this.last;
                if (obj != null) {
                    break;
                }
                this.last = waitForNext();
            }
            if (!(obj instanceof StatusRuntimeException)) {
                return obj != this;
            }
            StatusRuntimeException statusRuntimeException = (StatusRuntimeException) obj;
            throw statusRuntimeException.getStatus().asRuntimeException(statusRuntimeException.getTrailers());
        }

        public T next() {
            if (hasNext()) {
                try {
                    this.call.request(1);
                    return this.last;
                } finally {
                    this.last = null;
                }
            } else {
                throw new NoSuchElementException();
            }
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        /* renamed from: io.grpc.stub.ClientCalls$BlockingResponseStream$QueuingListener */
        private final class QueuingListener extends ClientCall.C2333Listener<T> {
            private boolean done = false;

            public void onHeaders(Metadata metadata) {
            }

            QueuingListener() {
            }

            public void onMessage(T t) {
                Preconditions.checkState(!this.done, "ClientCall already closed");
                BlockingResponseStream.this.buffer.add(t);
            }

            public void onClose(Status status, Metadata metadata) {
                Preconditions.checkState(!this.done, "ClientCall already closed");
                if (status.isOk()) {
                    BlockingResponseStream.this.buffer.add(BlockingResponseStream.this);
                } else {
                    BlockingResponseStream.this.buffer.add(status.asRuntimeException(metadata));
                }
                this.done = true;
            }
        }
    }

    /* renamed from: io.grpc.stub.ClientCalls$ThreadlessExecutor */
    private static final class ThreadlessExecutor extends ConcurrentLinkedQueue<Runnable> implements Executor {
        private static final Logger log = Logger.getLogger(ThreadlessExecutor.class.getName());
        private volatile Thread waiter;

        ThreadlessExecutor() {
        }

        /* JADX INFO: finally extract failed */
        public void waitAndDrain() throws InterruptedException {
            Runnable runnable;
            throwIfInterrupted();
            Runnable runnable2 = (Runnable) poll();
            if (runnable2 == null) {
                this.waiter = Thread.currentThread();
                while (true) {
                    try {
                        runnable = (Runnable) poll();
                        if (runnable != null) {
                            break;
                        }
                        LockSupport.park(this);
                        throwIfInterrupted();
                    } catch (Throwable th) {
                        this.waiter = null;
                        throw th;
                    }
                }
                this.waiter = null;
                runnable2 = runnable;
            }
            do {
                try {
                    runnable2.run();
                } catch (Throwable th2) {
                    log.log(Level.WARNING, "Runnable threw exception", th2);
                }
                runnable2 = (Runnable) poll();
            } while (runnable2 != null);
        }

        private static void throwIfInterrupted() throws InterruptedException {
            if (Thread.interrupted()) {
                throw new InterruptedException();
            }
        }

        public void execute(Runnable runnable) {
            add(runnable);
            LockSupport.unpark(this.waiter);
        }
    }
}

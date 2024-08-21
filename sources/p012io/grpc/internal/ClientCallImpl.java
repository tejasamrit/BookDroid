package p012io.grpc.internal;

import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import com.google.common.util.concurrent.MoreExecutors;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.concurrent.CancellationException;
import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Nullable;
import p012io.grpc.Attributes;
import p012io.grpc.CallOptions;
import p012io.grpc.ClientCall;
import p012io.grpc.Codec;
import p012io.grpc.Compressor;
import p012io.grpc.CompressorRegistry;
import p012io.grpc.Context;
import p012io.grpc.Contexts;
import p012io.grpc.Deadline;
import p012io.grpc.DecompressorRegistry;
import p012io.grpc.InternalDecompressorRegistry;
import p012io.grpc.LoadBalancer;
import p012io.grpc.Metadata;
import p012io.grpc.MethodDescriptor;
import p012io.grpc.Status;
import p012io.grpc.internal.ClientStreamListener;
import p012io.grpc.internal.StreamListener;
import p012io.perfmark.Link;
import p012io.perfmark.PerfMark;
import p012io.perfmark.Tag;

/* renamed from: io.grpc.internal.ClientCallImpl */
final class ClientCallImpl<ReqT, RespT> extends ClientCall<ReqT, RespT> {
    static final long DEADLINE_EXPIRATION_CANCEL_DELAY_NANOS = TimeUnit.SECONDS.toNanos(1);
    private static final byte[] FULL_STREAM_DECOMPRESSION_ENCODINGS = "gzip".getBytes(Charset.forName("US-ASCII"));
    private static final Logger log = Logger.getLogger(ClientCallImpl.class.getName());
    /* access modifiers changed from: private */
    public final Executor callExecutor;
    private final CallOptions callOptions;
    private boolean cancelCalled;
    /* access modifiers changed from: private */
    public volatile boolean cancelListenersShouldBeRemoved;
    private ClientCallImpl<ReqT, RespT>.ContextCancellationListener cancellationListener;
    /* access modifiers changed from: private */
    public final CallTracer channelCallsTracer;
    private final ClientTransportProvider clientTransportProvider;
    private CompressorRegistry compressorRegistry = CompressorRegistry.getDefaultInstance();
    /* access modifiers changed from: private */
    public final Context context;
    private final ScheduledExecutorService deadlineCancellationExecutor;
    private volatile ScheduledFuture<?> deadlineCancellationNotifyApplicationFuture;
    private volatile ScheduledFuture<?> deadlineCancellationSendToServerFuture;
    private DecompressorRegistry decompressorRegistry = DecompressorRegistry.getDefaultInstance();
    private boolean fullStreamDecompression;
    private boolean halfCloseCalled;
    /* access modifiers changed from: private */
    public final MethodDescriptor<ReqT, RespT> method;
    private boolean observerClosed;
    private final boolean retryEnabled;
    /* access modifiers changed from: private */
    public ClientStream stream;
    /* access modifiers changed from: private */
    public final Tag tag;
    private final boolean unaryRequest;

    /* renamed from: io.grpc.internal.ClientCallImpl$ClientTransportProvider */
    interface ClientTransportProvider {
        ClientTransport get(LoadBalancer.PickSubchannelArgs pickSubchannelArgs);

        <ReqT> ClientStream newRetriableStream(MethodDescriptor<ReqT, ?> methodDescriptor, CallOptions callOptions, Metadata metadata, Context context);
    }

    ClientCallImpl(MethodDescriptor<ReqT, RespT> methodDescriptor, Executor executor, CallOptions callOptions2, ClientTransportProvider clientTransportProvider2, ScheduledExecutorService scheduledExecutorService, CallTracer callTracer, boolean z) {
        Executor executor2;
        boolean z2 = false;
        this.observerClosed = false;
        this.method = methodDescriptor;
        Tag createTag = PerfMark.createTag(methodDescriptor.getFullMethodName(), (long) System.identityHashCode(this));
        this.tag = createTag;
        if (executor == MoreExecutors.directExecutor()) {
            executor2 = new SerializeReentrantCallsDirectExecutor();
        } else {
            executor2 = new SerializingExecutor(executor);
        }
        this.callExecutor = executor2;
        this.channelCallsTracer = callTracer;
        this.context = Context.current();
        this.unaryRequest = (methodDescriptor.getType() == MethodDescriptor.MethodType.UNARY || methodDescriptor.getType() == MethodDescriptor.MethodType.SERVER_STREAMING) ? true : z2;
        this.callOptions = callOptions2;
        this.clientTransportProvider = clientTransportProvider2;
        this.deadlineCancellationExecutor = scheduledExecutorService;
        this.retryEnabled = z;
        PerfMark.event("ClientCall.<init>", createTag);
    }

    /* renamed from: io.grpc.internal.ClientCallImpl$ContextCancellationListener */
    private final class ContextCancellationListener implements Context.CancellationListener {
        private ClientCall.C2333Listener<RespT> observer;

        private ContextCancellationListener(ClientCall.C2333Listener<RespT> listener) {
            this.observer = listener;
        }

        public void cancelled(Context context) {
            if (context.getDeadline() == null || !context.getDeadline().isExpired()) {
                ClientCallImpl.this.stream.cancel(Contexts.statusFromCancelled(context));
                return;
            }
            ClientCallImpl.this.delayedCancelOnDeadlineExceeded(Contexts.statusFromCancelled(context), this.observer);
        }
    }

    /* access modifiers changed from: package-private */
    public ClientCallImpl<ReqT, RespT> setFullStreamDecompression(boolean z) {
        this.fullStreamDecompression = z;
        return this;
    }

    /* access modifiers changed from: package-private */
    public ClientCallImpl<ReqT, RespT> setDecompressorRegistry(DecompressorRegistry decompressorRegistry2) {
        this.decompressorRegistry = decompressorRegistry2;
        return this;
    }

    /* access modifiers changed from: package-private */
    public ClientCallImpl<ReqT, RespT> setCompressorRegistry(CompressorRegistry compressorRegistry2) {
        this.compressorRegistry = compressorRegistry2;
        return this;
    }

    static void prepareHeaders(Metadata metadata, DecompressorRegistry decompressorRegistry2, Compressor compressor, boolean z) {
        metadata.discardAll(GrpcUtil.MESSAGE_ENCODING_KEY);
        if (compressor != Codec.Identity.NONE) {
            metadata.put(GrpcUtil.MESSAGE_ENCODING_KEY, compressor.getMessageEncoding());
        }
        metadata.discardAll(GrpcUtil.MESSAGE_ACCEPT_ENCODING_KEY);
        byte[] rawAdvertisedMessageEncodings = InternalDecompressorRegistry.getRawAdvertisedMessageEncodings(decompressorRegistry2);
        if (rawAdvertisedMessageEncodings.length != 0) {
            metadata.put(GrpcUtil.MESSAGE_ACCEPT_ENCODING_KEY, rawAdvertisedMessageEncodings);
        }
        metadata.discardAll(GrpcUtil.CONTENT_ENCODING_KEY);
        metadata.discardAll(GrpcUtil.CONTENT_ACCEPT_ENCODING_KEY);
        if (z) {
            metadata.put(GrpcUtil.CONTENT_ACCEPT_ENCODING_KEY, FULL_STREAM_DECOMPRESSION_ENCODINGS);
        }
    }

    public void start(ClientCall.C2333Listener<RespT> listener, Metadata metadata) {
        PerfMark.startTask("ClientCall.start", this.tag);
        try {
            startInternal(listener, metadata);
        } finally {
            PerfMark.stopTask("ClientCall.start", this.tag);
        }
    }

    private void startInternal(ClientCall.C2333Listener<RespT> listener, Metadata metadata) {
        Compressor compressor;
        boolean z = false;
        Preconditions.checkState(this.stream == null, "Already started");
        Preconditions.checkState(!this.cancelCalled, "call was cancelled");
        Preconditions.checkNotNull(listener, "observer");
        Preconditions.checkNotNull(metadata, "headers");
        if (this.context.isCancelled()) {
            this.stream = NoopClientStream.INSTANCE;
            executeCloseObserverInContext(listener, Contexts.statusFromCancelled(this.context));
            return;
        }
        String compressor2 = this.callOptions.getCompressor();
        if (compressor2 != null) {
            compressor = this.compressorRegistry.lookupCompressor(compressor2);
            if (compressor == null) {
                this.stream = NoopClientStream.INSTANCE;
                executeCloseObserverInContext(listener, Status.INTERNAL.withDescription(String.format("Unable to find compressor by name %s", new Object[]{compressor2})));
                return;
            }
        } else {
            compressor = Codec.Identity.NONE;
        }
        prepareHeaders(metadata, this.decompressorRegistry, compressor, this.fullStreamDecompression);
        Deadline effectiveDeadline = effectiveDeadline();
        if (effectiveDeadline != null && effectiveDeadline.isExpired()) {
            z = true;
        }
        if (!z) {
            logIfContextNarrowedTimeout(effectiveDeadline, this.context.getDeadline(), this.callOptions.getDeadline());
            if (this.retryEnabled) {
                this.stream = this.clientTransportProvider.newRetriableStream(this.method, this.callOptions, metadata, this.context);
            } else {
                ClientTransport clientTransport = this.clientTransportProvider.get(new PickSubchannelArgsImpl(this.method, metadata, this.callOptions));
                Context attach = this.context.attach();
                try {
                    this.stream = clientTransport.newStream(this.method, metadata, this.callOptions);
                } finally {
                    this.context.detach(attach);
                }
            }
        } else {
            Status status = Status.DEADLINE_EXCEEDED;
            this.stream = new FailingClientStream(status.withDescription("ClientCall started after deadline exceeded: " + effectiveDeadline));
        }
        if (this.callOptions.getAuthority() != null) {
            this.stream.setAuthority(this.callOptions.getAuthority());
        }
        if (this.callOptions.getMaxInboundMessageSize() != null) {
            this.stream.setMaxInboundMessageSize(this.callOptions.getMaxInboundMessageSize().intValue());
        }
        if (this.callOptions.getMaxOutboundMessageSize() != null) {
            this.stream.setMaxOutboundMessageSize(this.callOptions.getMaxOutboundMessageSize().intValue());
        }
        if (effectiveDeadline != null) {
            this.stream.setDeadline(effectiveDeadline);
        }
        this.stream.setCompressor(compressor);
        boolean z2 = this.fullStreamDecompression;
        if (z2) {
            this.stream.setFullStreamDecompression(z2);
        }
        this.stream.setDecompressorRegistry(this.decompressorRegistry);
        this.channelCallsTracer.reportCallStarted();
        this.cancellationListener = new ContextCancellationListener(listener);
        this.stream.start(new ClientStreamListenerImpl(listener));
        this.context.addListener(this.cancellationListener, MoreExecutors.directExecutor());
        if (effectiveDeadline != null && !effectiveDeadline.equals(this.context.getDeadline()) && this.deadlineCancellationExecutor != null && !(this.stream instanceof FailingClientStream)) {
            this.deadlineCancellationNotifyApplicationFuture = startDeadlineNotifyApplicationTimer(effectiveDeadline, listener);
        }
        if (this.cancelListenersShouldBeRemoved) {
            removeContextListenerAndCancelDeadlineFuture();
        }
    }

    private static void logIfContextNarrowedTimeout(Deadline deadline, @Nullable Deadline deadline2, @Nullable Deadline deadline3) {
        Logger logger = log;
        if (logger.isLoggable(Level.FINE) && deadline != null && deadline.equals(deadline2)) {
            StringBuilder sb = new StringBuilder(String.format("Call timeout set to '%d' ns, due to context deadline.", new Object[]{Long.valueOf(Math.max(0, deadline.timeRemaining(TimeUnit.NANOSECONDS)))}));
            if (deadline3 == null) {
                sb.append(" Explicit call timeout was not set.");
            } else {
                sb.append(String.format(" Explicit call timeout was '%d' ns.", new Object[]{Long.valueOf(deadline3.timeRemaining(TimeUnit.NANOSECONDS))}));
            }
            logger.fine(sb.toString());
        }
    }

    /* access modifiers changed from: private */
    public void removeContextListenerAndCancelDeadlineFuture() {
        this.context.removeListener(this.cancellationListener);
        ScheduledFuture<?> scheduledFuture = this.deadlineCancellationSendToServerFuture;
        if (scheduledFuture != null) {
            scheduledFuture.cancel(false);
        }
        ScheduledFuture<?> scheduledFuture2 = this.deadlineCancellationNotifyApplicationFuture;
        if (scheduledFuture2 != null) {
            scheduledFuture2.cancel(false);
        }
    }

    private ScheduledFuture<?> startDeadlineNotifyApplicationTimer(Deadline deadline, final ClientCall.C2333Listener<RespT> listener) {
        final long timeRemaining = deadline.timeRemaining(TimeUnit.NANOSECONDS);
        return this.deadlineCancellationExecutor.schedule(new LogExceptionRunnable(new Runnable() {
            public void run() {
                ClientCallImpl.this.delayedCancelOnDeadlineExceeded(ClientCallImpl.this.buildDeadlineExceededStatusWithRemainingNanos(timeRemaining), listener);
            }
        }), timeRemaining, TimeUnit.NANOSECONDS);
    }

    /* access modifiers changed from: private */
    public Status buildDeadlineExceededStatusWithRemainingNanos(long j) {
        InsightBuilder insightBuilder = new InsightBuilder();
        this.stream.appendTimeoutInsight(insightBuilder);
        long abs = Math.abs(j) / TimeUnit.SECONDS.toNanos(1);
        long abs2 = Math.abs(j) % TimeUnit.SECONDS.toNanos(1);
        StringBuilder sb = new StringBuilder();
        sb.append("deadline exceeded after ");
        if (j < 0) {
            sb.append('-');
        }
        sb.append(abs);
        sb.append(String.format(".%09d", new Object[]{Long.valueOf(abs2)}));
        sb.append("s. ");
        sb.append(insightBuilder);
        return Status.DEADLINE_EXCEEDED.augmentDescription(sb.toString());
    }

    /* access modifiers changed from: private */
    public void delayedCancelOnDeadlineExceeded(final Status status, ClientCall.C2333Listener<RespT> listener) {
        if (this.deadlineCancellationSendToServerFuture == null) {
            this.deadlineCancellationSendToServerFuture = this.deadlineCancellationExecutor.schedule(new LogExceptionRunnable(new Runnable() {
                public void run() {
                    ClientCallImpl.this.stream.cancel(status);
                }
            }), DEADLINE_EXPIRATION_CANCEL_DELAY_NANOS, TimeUnit.NANOSECONDS);
            executeCloseObserverInContext(listener, status);
        }
    }

    private void executeCloseObserverInContext(final ClientCall.C2333Listener<RespT> listener, final Status status) {
        this.callExecutor.execute(new ContextRunnable() {
            public void runInContext() {
                ClientCallImpl.this.closeObserver(listener, status, new Metadata());
            }
        });
    }

    /* access modifiers changed from: private */
    public void closeObserver(ClientCall.C2333Listener<RespT> listener, Status status, Metadata metadata) {
        if (!this.observerClosed) {
            this.observerClosed = true;
            listener.onClose(status, metadata);
        }
    }

    /* access modifiers changed from: private */
    @Nullable
    public Deadline effectiveDeadline() {
        return min(this.callOptions.getDeadline(), this.context.getDeadline());
    }

    @Nullable
    private static Deadline min(@Nullable Deadline deadline, @Nullable Deadline deadline2) {
        if (deadline == null) {
            return deadline2;
        }
        return deadline2 == null ? deadline : deadline.minimum(deadline2);
    }

    public void request(int i) {
        PerfMark.startTask("ClientCall.request", this.tag);
        try {
            boolean z = true;
            Preconditions.checkState(this.stream != null, "Not started");
            if (i < 0) {
                z = false;
            }
            Preconditions.checkArgument(z, "Number requested must be non-negative");
            this.stream.request(i);
        } finally {
            PerfMark.stopTask("ClientCall.cancel", this.tag);
        }
    }

    public void cancel(@Nullable String str, @Nullable Throwable th) {
        PerfMark.startTask("ClientCall.cancel", this.tag);
        try {
            cancelInternal(str, th);
        } finally {
            PerfMark.stopTask("ClientCall.cancel", this.tag);
        }
    }

    private void cancelInternal(@Nullable String str, @Nullable Throwable th) {
        Status status;
        if (str == null && th == null) {
            th = new CancellationException("Cancelled without a message or cause");
            log.log(Level.WARNING, "Cancelling without a message or cause is suboptimal", th);
        }
        if (!this.cancelCalled) {
            this.cancelCalled = true;
            try {
                if (this.stream != null) {
                    Status status2 = Status.CANCELLED;
                    if (str != null) {
                        status = status2.withDescription(str);
                    } else {
                        status = status2.withDescription("Call cancelled without message");
                    }
                    if (th != null) {
                        status = status.withCause(th);
                    }
                    this.stream.cancel(status);
                }
            } finally {
                removeContextListenerAndCancelDeadlineFuture();
            }
        }
    }

    public void halfClose() {
        PerfMark.startTask("ClientCall.halfClose", this.tag);
        try {
            halfCloseInternal();
        } finally {
            PerfMark.stopTask("ClientCall.halfClose", this.tag);
        }
    }

    private void halfCloseInternal() {
        Preconditions.checkState(this.stream != null, "Not started");
        Preconditions.checkState(!this.cancelCalled, "call was cancelled");
        Preconditions.checkState(!this.halfCloseCalled, "call already half-closed");
        this.halfCloseCalled = true;
        this.stream.halfClose();
    }

    public void sendMessage(ReqT reqt) {
        PerfMark.startTask("ClientCall.sendMessage", this.tag);
        try {
            sendMessageInternal(reqt);
        } finally {
            PerfMark.stopTask("ClientCall.sendMessage", this.tag);
        }
    }

    private void sendMessageInternal(ReqT reqt) {
        Preconditions.checkState(this.stream != null, "Not started");
        Preconditions.checkState(!this.cancelCalled, "call was cancelled");
        Preconditions.checkState(!this.halfCloseCalled, "call was half-closed");
        try {
            ClientStream clientStream = this.stream;
            if (clientStream instanceof RetriableStream) {
                ((RetriableStream) clientStream).sendMessage(reqt);
            } else {
                clientStream.writeMessage(this.method.streamRequest(reqt));
            }
            if (!this.unaryRequest) {
                this.stream.flush();
            }
        } catch (RuntimeException e) {
            this.stream.cancel(Status.CANCELLED.withCause(e).withDescription("Failed to stream message"));
        } catch (Error e2) {
            this.stream.cancel(Status.CANCELLED.withDescription("Client sendMessage() failed with Error"));
            throw e2;
        }
    }

    public void setMessageCompression(boolean z) {
        Preconditions.checkState(this.stream != null, "Not started");
        this.stream.setMessageCompression(z);
    }

    public boolean isReady() {
        return this.stream.isReady();
    }

    public Attributes getAttributes() {
        ClientStream clientStream = this.stream;
        if (clientStream != null) {
            return clientStream.getAttributes();
        }
        return Attributes.EMPTY;
    }

    public String toString() {
        return MoreObjects.toStringHelper((Object) this).add("method", (Object) this.method).toString();
    }

    /* renamed from: io.grpc.internal.ClientCallImpl$ClientStreamListenerImpl */
    private class ClientStreamListenerImpl implements ClientStreamListener {
        /* access modifiers changed from: private */
        public boolean closed;
        /* access modifiers changed from: private */
        public final ClientCall.C2333Listener<RespT> observer;

        public ClientStreamListenerImpl(ClientCall.C2333Listener<RespT> listener) {
            this.observer = (ClientCall.C2333Listener) Preconditions.checkNotNull(listener, "observer");
        }

        public void headersRead(final Metadata metadata) {
            PerfMark.startTask("ClientStreamListener.headersRead", ClientCallImpl.this.tag);
            final Link linkOut = PerfMark.linkOut();
            try {
                ClientCallImpl.this.callExecutor.execute(new ContextRunnable() {
                    public void runInContext() {
                        PerfMark.startTask("ClientCall$Listener.headersRead", ClientCallImpl.this.tag);
                        PerfMark.linkIn(linkOut);
                        try {
                            runInternal();
                        } finally {
                            PerfMark.stopTask("ClientCall$Listener.headersRead", ClientCallImpl.this.tag);
                        }
                    }

                    private void runInternal() {
                        if (!ClientStreamListenerImpl.this.closed) {
                            try {
                                ClientStreamListenerImpl.this.observer.onHeaders(metadata);
                            } catch (Throwable th) {
                                Status withDescription = Status.CANCELLED.withCause(th).withDescription("Failed to read headers");
                                ClientCallImpl.this.stream.cancel(withDescription);
                                ClientStreamListenerImpl.this.close(withDescription, new Metadata());
                            }
                        }
                    }
                });
            } finally {
                PerfMark.stopTask("ClientStreamListener.headersRead", ClientCallImpl.this.tag);
            }
        }

        public void messagesAvailable(final StreamListener.MessageProducer messageProducer) {
            PerfMark.startTask("ClientStreamListener.messagesAvailable", ClientCallImpl.this.tag);
            final Link linkOut = PerfMark.linkOut();
            try {
                ClientCallImpl.this.callExecutor.execute(new ContextRunnable() {
                    public void runInContext() {
                        PerfMark.startTask("ClientCall$Listener.messagesAvailable", ClientCallImpl.this.tag);
                        PerfMark.linkIn(linkOut);
                        try {
                            runInternal();
                        } finally {
                            PerfMark.stopTask("ClientCall$Listener.messagesAvailable", ClientCallImpl.this.tag);
                        }
                    }

                    private void runInternal() {
                        InputStream next;
                        if (ClientStreamListenerImpl.this.closed) {
                            GrpcUtil.closeQuietly(messageProducer);
                            return;
                        }
                        while (true) {
                            try {
                                next = messageProducer.next();
                                if (next != null) {
                                    ClientStreamListenerImpl.this.observer.onMessage(ClientCallImpl.this.method.parseResponse(next));
                                    next.close();
                                } else {
                                    return;
                                }
                            } catch (Throwable th) {
                                GrpcUtil.closeQuietly(messageProducer);
                                Status withDescription = Status.CANCELLED.withCause(th).withDescription("Failed to read message.");
                                ClientCallImpl.this.stream.cancel(withDescription);
                                ClientStreamListenerImpl.this.close(withDescription, new Metadata());
                                return;
                            }
                        }
                    }
                });
            } finally {
                PerfMark.stopTask("ClientStreamListener.messagesAvailable", ClientCallImpl.this.tag);
            }
        }

        /* access modifiers changed from: private */
        public void close(Status status, Metadata metadata) {
            this.closed = true;
            boolean unused = ClientCallImpl.this.cancelListenersShouldBeRemoved = true;
            try {
                ClientCallImpl.this.closeObserver(this.observer, status, metadata);
            } finally {
                ClientCallImpl.this.removeContextListenerAndCancelDeadlineFuture();
                ClientCallImpl.this.channelCallsTracer.reportCallEnded(status.isOk());
            }
        }

        public void closed(Status status, Metadata metadata) {
            closed(status, ClientStreamListener.RpcProgress.PROCESSED, metadata);
        }

        public void closed(Status status, ClientStreamListener.RpcProgress rpcProgress, Metadata metadata) {
            PerfMark.startTask("ClientStreamListener.closed", ClientCallImpl.this.tag);
            try {
                closedInternal(status, rpcProgress, metadata);
            } finally {
                PerfMark.stopTask("ClientStreamListener.closed", ClientCallImpl.this.tag);
            }
        }

        private void closedInternal(final Status status, ClientStreamListener.RpcProgress rpcProgress, final Metadata metadata) {
            Deadline access$1500 = ClientCallImpl.this.effectiveDeadline();
            if (status.getCode() == Status.Code.CANCELLED && access$1500 != null && access$1500.isExpired()) {
                InsightBuilder insightBuilder = new InsightBuilder();
                ClientCallImpl.this.stream.appendTimeoutInsight(insightBuilder);
                Status status2 = Status.DEADLINE_EXCEEDED;
                status = status2.augmentDescription("ClientCall was cancelled at or after deadline. " + insightBuilder);
                metadata = new Metadata();
            }
            final Link linkOut = PerfMark.linkOut();
            ClientCallImpl.this.callExecutor.execute(new ContextRunnable() {
                public void runInContext() {
                    PerfMark.startTask("ClientCall$Listener.onClose", ClientCallImpl.this.tag);
                    PerfMark.linkIn(linkOut);
                    try {
                        runInternal();
                    } finally {
                        PerfMark.stopTask("ClientCall$Listener.onClose", ClientCallImpl.this.tag);
                    }
                }

                private void runInternal() {
                    if (!ClientStreamListenerImpl.this.closed) {
                        ClientStreamListenerImpl.this.close(status, metadata);
                    }
                }
            });
        }

        public void onReady() {
            if (!ClientCallImpl.this.method.getType().clientSendsOneMessage()) {
                PerfMark.startTask("ClientStreamListener.onReady", ClientCallImpl.this.tag);
                final Link linkOut = PerfMark.linkOut();
                try {
                    ClientCallImpl.this.callExecutor.execute(new ContextRunnable() {
                        public void runInContext() {
                            PerfMark.startTask("ClientCall$Listener.onReady", ClientCallImpl.this.tag);
                            PerfMark.linkIn(linkOut);
                            try {
                                runInternal();
                            } finally {
                                PerfMark.stopTask("ClientCall$Listener.onReady", ClientCallImpl.this.tag);
                            }
                        }

                        private void runInternal() {
                            try {
                                ClientStreamListenerImpl.this.observer.onReady();
                            } catch (Throwable th) {
                                Status withDescription = Status.CANCELLED.withCause(th).withDescription("Failed to call onReady.");
                                ClientCallImpl.this.stream.cancel(withDescription);
                                ClientStreamListenerImpl.this.close(withDescription, new Metadata());
                            }
                        }
                    });
                } finally {
                    PerfMark.stopTask("ClientStreamListener.onReady", ClientCallImpl.this.tag);
                }
            }
        }
    }
}

package p012io.grpc.internal;

import androidx.core.app.NotificationCompat;
import com.google.common.base.Preconditions;
import com.google.common.base.Throwables;
import com.google.common.util.concurrent.MoreExecutors;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import p012io.grpc.Attributes;
import p012io.grpc.Codec;
import p012io.grpc.Compressor;
import p012io.grpc.CompressorRegistry;
import p012io.grpc.Context;
import p012io.grpc.DecompressorRegistry;
import p012io.grpc.InternalDecompressorRegistry;
import p012io.grpc.Metadata;
import p012io.grpc.MethodDescriptor;
import p012io.grpc.ServerCall;
import p012io.grpc.Status;
import p012io.grpc.internal.StreamListener;
import p012io.perfmark.PerfMark;
import p012io.perfmark.Tag;

/* renamed from: io.grpc.internal.ServerCallImpl */
final class ServerCallImpl<ReqT, RespT> extends ServerCall<ReqT, RespT> {
    static final String MISSING_RESPONSE = "Completed without a response";
    static final String TOO_MANY_RESPONSES = "Too many responses";
    private static final Logger log = Logger.getLogger(ServerCallImpl.class.getName());
    /* access modifiers changed from: private */
    public volatile boolean cancelled;
    private boolean closeCalled;
    private Compressor compressor;
    private final CompressorRegistry compressorRegistry;
    private final Context.CancellableContext context;
    private final DecompressorRegistry decompressorRegistry;
    private final byte[] messageAcceptEncoding;
    private boolean messageSent;
    /* access modifiers changed from: private */
    public final MethodDescriptor<ReqT, RespT> method;
    private boolean sendHeadersCalled;
    private CallTracer serverCallTracer;
    private final ServerStream stream;
    /* access modifiers changed from: private */
    public final Tag tag;

    ServerCallImpl(ServerStream serverStream, MethodDescriptor<ReqT, RespT> methodDescriptor, Metadata metadata, Context.CancellableContext cancellableContext, DecompressorRegistry decompressorRegistry2, CompressorRegistry compressorRegistry2, CallTracer callTracer, Tag tag2) {
        this.stream = serverStream;
        this.method = methodDescriptor;
        this.context = cancellableContext;
        this.messageAcceptEncoding = (byte[]) metadata.get(GrpcUtil.MESSAGE_ACCEPT_ENCODING_KEY);
        this.decompressorRegistry = decompressorRegistry2;
        this.compressorRegistry = compressorRegistry2;
        this.serverCallTracer = callTracer;
        callTracer.reportCallStarted();
        this.tag = tag2;
    }

    public void request(int i) {
        PerfMark.startTask("ServerCall.request", this.tag);
        try {
            this.stream.request(i);
        } finally {
            PerfMark.stopTask("ServerCall.request", this.tag);
        }
    }

    public void sendHeaders(Metadata metadata) {
        PerfMark.startTask("ServerCall.sendHeaders", this.tag);
        try {
            sendHeadersInternal(metadata);
        } finally {
            PerfMark.stopTask("ServerCall.sendHeaders", this.tag);
        }
    }

    private void sendHeadersInternal(Metadata metadata) {
        Preconditions.checkState(!this.sendHeadersCalled, "sendHeaders has already been called");
        Preconditions.checkState(!this.closeCalled, "call is closed");
        metadata.discardAll(GrpcUtil.MESSAGE_ENCODING_KEY);
        if (this.compressor == null) {
            this.compressor = Codec.Identity.NONE;
        } else if (this.messageAcceptEncoding == null) {
            this.compressor = Codec.Identity.NONE;
        } else if (!GrpcUtil.iterableContains(GrpcUtil.ACCEPT_ENCODING_SPLITTER.split(new String(this.messageAcceptEncoding, GrpcUtil.US_ASCII)), this.compressor.getMessageEncoding())) {
            this.compressor = Codec.Identity.NONE;
        }
        metadata.put(GrpcUtil.MESSAGE_ENCODING_KEY, this.compressor.getMessageEncoding());
        this.stream.setCompressor(this.compressor);
        metadata.discardAll(GrpcUtil.MESSAGE_ACCEPT_ENCODING_KEY);
        byte[] rawAdvertisedMessageEncodings = InternalDecompressorRegistry.getRawAdvertisedMessageEncodings(this.decompressorRegistry);
        if (rawAdvertisedMessageEncodings.length != 0) {
            metadata.put(GrpcUtil.MESSAGE_ACCEPT_ENCODING_KEY, rawAdvertisedMessageEncodings);
        }
        this.sendHeadersCalled = true;
        this.stream.writeHeaders(metadata);
    }

    public void sendMessage(RespT respt) {
        PerfMark.startTask("ServerCall.sendMessage", this.tag);
        try {
            sendMessageInternal(respt);
        } finally {
            PerfMark.stopTask("ServerCall.sendMessage", this.tag);
        }
    }

    private void sendMessageInternal(RespT respt) {
        Preconditions.checkState(this.sendHeadersCalled, "sendHeaders has not been called");
        Preconditions.checkState(!this.closeCalled, "call is closed");
        if (!this.method.getType().serverSendsOneMessage() || !this.messageSent) {
            this.messageSent = true;
            try {
                this.stream.writeMessage(this.method.streamResponse(respt));
                this.stream.flush();
            } catch (RuntimeException e) {
                close(Status.fromThrowable(e), new Metadata());
            } catch (Error e2) {
                close(Status.CANCELLED.withDescription("Server sendMessage() failed with Error"), new Metadata());
                throw e2;
            }
        } else {
            internalClose(Status.INTERNAL.withDescription(TOO_MANY_RESPONSES));
        }
    }

    public void setMessageCompression(boolean z) {
        this.stream.setMessageCompression(z);
    }

    public void setCompression(String str) {
        boolean z = true;
        Preconditions.checkState(!this.sendHeadersCalled, "sendHeaders has been called");
        Compressor lookupCompressor = this.compressorRegistry.lookupCompressor(str);
        this.compressor = lookupCompressor;
        if (lookupCompressor == null) {
            z = false;
        }
        Preconditions.checkArgument(z, "Unable to find compressor by name %s", (Object) str);
    }

    public boolean isReady() {
        return this.stream.isReady();
    }

    public void close(Status status, Metadata metadata) {
        PerfMark.startTask("ServerCall.close", this.tag);
        try {
            closeInternal(status, metadata);
        } finally {
            PerfMark.stopTask("ServerCall.close", this.tag);
        }
    }

    private void closeInternal(Status status, Metadata metadata) {
        Preconditions.checkState(!this.closeCalled, "call already closed");
        try {
            this.closeCalled = true;
            if (!status.isOk() || !this.method.getType().serverSendsOneMessage() || this.messageSent) {
                this.stream.close(status, metadata);
                this.serverCallTracer.reportCallEnded(status.isOk());
                return;
            }
            internalClose(Status.INTERNAL.withDescription(MISSING_RESPONSE));
        } finally {
            this.serverCallTracer.reportCallEnded(status.isOk());
        }
    }

    public boolean isCancelled() {
        return this.cancelled;
    }

    /* access modifiers changed from: package-private */
    public ServerStreamListener newServerStreamListener(ServerCall.C2354Listener<ReqT> listener) {
        return new ServerStreamListenerImpl(this, listener, this.context);
    }

    public Attributes getAttributes() {
        return this.stream.getAttributes();
    }

    public String getAuthority() {
        return this.stream.getAuthority();
    }

    public MethodDescriptor<ReqT, RespT> getMethodDescriptor() {
        return this.method;
    }

    private void internalClose(Status status) {
        log.log(Level.WARNING, "Cancelling the stream with status {0}", new Object[]{status});
        this.stream.cancel(status);
        this.serverCallTracer.reportCallEnded(status.isOk());
    }

    /* renamed from: io.grpc.internal.ServerCallImpl$ServerStreamListenerImpl */
    static final class ServerStreamListenerImpl<ReqT> implements ServerStreamListener {
        /* access modifiers changed from: private */
        public final ServerCallImpl<ReqT, ?> call;
        private final Context.CancellableContext context;
        private final ServerCall.C2354Listener<ReqT> listener;

        public ServerStreamListenerImpl(ServerCallImpl<ReqT, ?> serverCallImpl, ServerCall.C2354Listener<ReqT> listener2, Context.CancellableContext cancellableContext) {
            this.call = (ServerCallImpl) Preconditions.checkNotNull(serverCallImpl, NotificationCompat.CATEGORY_CALL);
            this.listener = (ServerCall.C2354Listener) Preconditions.checkNotNull(listener2, "listener must not be null");
            Context.CancellableContext cancellableContext2 = (Context.CancellableContext) Preconditions.checkNotNull(cancellableContext, "context");
            this.context = cancellableContext2;
            cancellableContext2.addListener(new Context.CancellationListener() {
                public void cancelled(Context context) {
                    boolean unused = ServerStreamListenerImpl.this.call.cancelled = true;
                }
            }, MoreExecutors.directExecutor());
        }

        public void messagesAvailable(StreamListener.MessageProducer messageProducer) {
            PerfMark.startTask("ServerStreamListener.messagesAvailable", this.call.tag);
            try {
                messagesAvailableInternal(messageProducer);
            } finally {
                PerfMark.stopTask("ServerStreamListener.messagesAvailable", this.call.tag);
            }
        }

        private void messagesAvailableInternal(StreamListener.MessageProducer messageProducer) {
            InputStream next;
            if (this.call.cancelled) {
                GrpcUtil.closeQuietly(messageProducer);
                return;
            }
            while (true) {
                try {
                    next = messageProducer.next();
                    if (next != null) {
                        this.listener.onMessage(this.call.method.parseRequest(next));
                        next.close();
                    } else {
                        return;
                    }
                } catch (Throwable th) {
                    GrpcUtil.closeQuietly(messageProducer);
                    Throwables.throwIfUnchecked(th);
                    throw new RuntimeException(th);
                }
            }
        }

        public void halfClosed() {
            PerfMark.startTask("ServerStreamListener.halfClosed", this.call.tag);
            try {
                if (!this.call.cancelled) {
                    this.listener.onHalfClose();
                    PerfMark.stopTask("ServerStreamListener.halfClosed", this.call.tag);
                }
            } finally {
                PerfMark.stopTask("ServerStreamListener.halfClosed", this.call.tag);
            }
        }

        public void closed(Status status) {
            PerfMark.startTask("ServerStreamListener.closed", this.call.tag);
            try {
                closedInternal(status);
            } finally {
                PerfMark.stopTask("ServerStreamListener.closed", this.call.tag);
            }
        }

        private void closedInternal(Status status) {
            try {
                if (status.isOk()) {
                    this.listener.onComplete();
                } else {
                    boolean unused = this.call.cancelled = true;
                    this.listener.onCancel();
                }
            } finally {
                this.context.cancel((Throwable) null);
            }
        }

        public void onReady() {
            PerfMark.startTask("ServerStreamListener.onReady", this.call.tag);
            try {
                if (!this.call.cancelled) {
                    this.listener.onReady();
                    PerfMark.stopTask("ServerCall.closed", this.call.tag);
                }
            } finally {
                PerfMark.stopTask("ServerCall.closed", this.call.tag);
            }
        }
    }
}

package p012io.grpc.okhttp;

import com.google.common.base.Preconditions;
import com.google.common.p008io.BaseEncoding;
import java.util.List;
import okio.Buffer;
import org.aspectj.lang.JoinPoint;
import p012io.grpc.Attributes;
import p012io.grpc.CallOptions;
import p012io.grpc.Metadata;
import p012io.grpc.MethodDescriptor;
import p012io.grpc.Status;
import p012io.grpc.internal.AbstractClientStream;
import p012io.grpc.internal.ClientStreamListener;
import p012io.grpc.internal.Http2ClientStreamTransportState;
import p012io.grpc.internal.StatsTraceContext;
import p012io.grpc.internal.TransportTracer;
import p012io.grpc.internal.WritableBuffer;
import p012io.grpc.okhttp.internal.framed.ErrorCode;
import p012io.grpc.okhttp.internal.framed.Header;
import p012io.perfmark.PerfMark;
import p012io.perfmark.Tag;

/* renamed from: io.grpc.okhttp.OkHttpClientStream */
class OkHttpClientStream extends AbstractClientStream {
    public static final int ABSENT_ID = -1;
    /* access modifiers changed from: private */
    public static final Buffer EMPTY_BUFFER = new Buffer();
    private final Attributes attributes;
    /* access modifiers changed from: private */
    public String authority;
    /* access modifiers changed from: private */

    /* renamed from: id */
    public volatile int f493id;
    /* access modifiers changed from: private */
    public final MethodDescriptor<?, ?> method;
    private Object outboundFlowState;
    private final Sink sink;
    /* access modifiers changed from: private */
    public final TransportState state;
    /* access modifiers changed from: private */
    public final StatsTraceContext statsTraceCtx;
    /* access modifiers changed from: private */
    public boolean useGet;
    /* access modifiers changed from: private */
    public final String userAgent;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    OkHttpClientStream(MethodDescriptor<?, ?> methodDescriptor, Metadata metadata, ExceptionHandlingFrameWriter exceptionHandlingFrameWriter, OkHttpClientTransport okHttpClientTransport, OutboundFlowController outboundFlowController, Object obj, int i, int i2, String str, String str2, StatsTraceContext statsTraceContext, TransportTracer transportTracer, CallOptions callOptions, boolean z) {
        super(new OkHttpWritableBufferAllocator(), statsTraceContext, transportTracer, metadata, callOptions, z && methodDescriptor.isSafe());
        this.f493id = -1;
        this.sink = new Sink();
        this.useGet = false;
        StatsTraceContext statsTraceContext2 = statsTraceContext;
        this.statsTraceCtx = (StatsTraceContext) Preconditions.checkNotNull(statsTraceContext2, "statsTraceCtx");
        this.method = methodDescriptor;
        this.authority = str;
        this.userAgent = str2;
        this.attributes = okHttpClientTransport.getAttributes();
        this.state = new TransportState(i, statsTraceContext2, obj, exceptionHandlingFrameWriter, outboundFlowController, okHttpClientTransport, i2, methodDescriptor.getFullMethodName());
    }

    /* access modifiers changed from: protected */
    public TransportState transportState() {
        return this.state;
    }

    /* access modifiers changed from: protected */
    public Sink abstractClientStreamSink() {
        return this.sink;
    }

    public MethodDescriptor.MethodType getType() {
        return this.method.getType();
    }

    /* renamed from: id */
    public int mo36530id() {
        return this.f493id;
    }

    /* access modifiers changed from: package-private */
    public boolean useGet() {
        return this.useGet;
    }

    public void setAuthority(String str) {
        this.authority = (String) Preconditions.checkNotNull(str, "authority");
    }

    public Attributes getAttributes() {
        return this.attributes;
    }

    /* renamed from: io.grpc.okhttp.OkHttpClientStream$Sink */
    class Sink implements AbstractClientStream.Sink {
        Sink() {
        }

        public void writeHeaders(Metadata metadata, byte[] bArr) {
            PerfMark.startTask("OkHttpClientStream$Sink.writeHeaders");
            String str = "/" + OkHttpClientStream.this.method.getFullMethodName();
            if (bArr != null) {
                boolean unused = OkHttpClientStream.this.useGet = true;
                str = str + "?" + BaseEncoding.base64().encode(bArr);
            }
            try {
                synchronized (OkHttpClientStream.this.state.lock) {
                    OkHttpClientStream.this.state.streamReady(metadata, str);
                }
                PerfMark.stopTask("OkHttpClientStream$Sink.writeHeaders");
            } catch (Throwable th) {
                PerfMark.stopTask("OkHttpClientStream$Sink.writeHeaders");
                throw th;
            }
        }

        public void writeFrame(WritableBuffer writableBuffer, boolean z, boolean z2, int i) {
            Buffer buffer;
            PerfMark.startTask("OkHttpClientStream$Sink.writeFrame");
            if (writableBuffer == null) {
                buffer = OkHttpClientStream.EMPTY_BUFFER;
            } else {
                buffer = ((OkHttpWritableBuffer) writableBuffer).buffer();
                int size = (int) buffer.size();
                if (size > 0) {
                    OkHttpClientStream.this.onSendingBytes(size);
                }
            }
            try {
                synchronized (OkHttpClientStream.this.state.lock) {
                    OkHttpClientStream.this.state.sendBuffer(buffer, z, z2);
                    OkHttpClientStream.this.getTransportTracer().reportMessageSent(i);
                }
                PerfMark.stopTask("OkHttpClientStream$Sink.writeFrame");
            } catch (Throwable th) {
                PerfMark.stopTask("OkHttpClientStream$Sink.writeFrame");
                throw th;
            }
        }

        public void request(int i) {
            PerfMark.startTask("OkHttpClientStream$Sink.request");
            try {
                synchronized (OkHttpClientStream.this.state.lock) {
                    OkHttpClientStream.this.state.requestMessagesFromDeframer(i);
                }
                PerfMark.stopTask("OkHttpClientStream$Sink.request");
            } catch (Throwable th) {
                PerfMark.stopTask("OkHttpClientStream$Sink.request");
                throw th;
            }
        }

        public void cancel(Status status) {
            PerfMark.startTask("OkHttpClientStream$Sink.cancel");
            try {
                synchronized (OkHttpClientStream.this.state.lock) {
                    OkHttpClientStream.this.state.cancel(status, true, (Metadata) null);
                }
                PerfMark.stopTask("OkHttpClientStream$Sink.cancel");
            } catch (Throwable th) {
                PerfMark.stopTask("OkHttpClientStream$Sink.cancel");
                throw th;
            }
        }
    }

    /* renamed from: io.grpc.okhttp.OkHttpClientStream$TransportState */
    class TransportState extends Http2ClientStreamTransportState {
        private boolean canStart = true;
        private boolean cancelSent = false;
        private boolean flushPendingData = false;
        private final ExceptionHandlingFrameWriter frameWriter;
        private final int initialWindowSize;
        /* access modifiers changed from: private */
        public final Object lock;
        private final OutboundFlowController outboundFlow;
        private Buffer pendingData = new Buffer();
        private boolean pendingDataHasEndOfStream = false;
        private int processedWindow;
        private List<Header> requestHeaders;
        private final Tag tag;
        private final OkHttpClientTransport transport;
        private int window;

        public TransportState(int i, StatsTraceContext statsTraceContext, Object obj, ExceptionHandlingFrameWriter exceptionHandlingFrameWriter, OutboundFlowController outboundFlowController, OkHttpClientTransport okHttpClientTransport, int i2, String str) {
            super(i, statsTraceContext, OkHttpClientStream.this.getTransportTracer());
            this.lock = Preconditions.checkNotNull(obj, JoinPoint.SYNCHRONIZATION_LOCK);
            this.frameWriter = exceptionHandlingFrameWriter;
            this.outboundFlow = outboundFlowController;
            this.transport = okHttpClientTransport;
            this.window = i2;
            this.processedWindow = i2;
            this.initialWindowSize = i2;
            this.tag = PerfMark.createTag(str);
        }

        public void start(int i) {
            Preconditions.checkState(OkHttpClientStream.this.f493id == -1, "the stream has been started with id %s", i);
            int unused = OkHttpClientStream.this.f493id = i;
            OkHttpClientStream.this.state.onStreamAllocated();
            if (this.canStart) {
                this.frameWriter.synStream(OkHttpClientStream.this.useGet, false, OkHttpClientStream.this.f493id, 0, this.requestHeaders);
                OkHttpClientStream.this.statsTraceCtx.clientOutboundHeaders();
                this.requestHeaders = null;
                if (this.pendingData.size() > 0) {
                    this.outboundFlow.data(this.pendingDataHasEndOfStream, OkHttpClientStream.this.f493id, this.pendingData, this.flushPendingData);
                }
                this.canStart = false;
            }
        }

        /* access modifiers changed from: protected */
        public void onStreamAllocated() {
            super.onStreamAllocated();
            getTransportTracer().reportLocalStreamStarted();
        }

        /* access modifiers changed from: protected */
        public void http2ProcessingFailed(Status status, boolean z, Metadata metadata) {
            cancel(status, z, metadata);
        }

        public void deframeFailed(Throwable th) {
            http2ProcessingFailed(Status.fromThrowable(th), true, new Metadata());
        }

        public void bytesRead(int i) {
            int i2 = this.processedWindow - i;
            this.processedWindow = i2;
            int i3 = this.initialWindowSize;
            if (((float) i2) <= ((float) i3) * 0.5f) {
                int i4 = i3 - i2;
                this.window += i4;
                this.processedWindow = i2 + i4;
                this.frameWriter.windowUpdate(OkHttpClientStream.this.mo36530id(), (long) i4);
            }
        }

        public void deframerClosed(boolean z) {
            onEndOfStream();
            super.deframerClosed(z);
        }

        public void runOnTransportThread(Runnable runnable) {
            synchronized (this.lock) {
                runnable.run();
            }
        }

        public void transportHeadersReceived(List<Header> list, boolean z) {
            if (z) {
                transportTrailersReceived(Utils.convertTrailers(list));
            } else {
                transportHeadersReceived(Utils.convertHeaders(list));
            }
        }

        public void transportDataReceived(Buffer buffer, boolean z) {
            int size = this.window - ((int) buffer.size());
            this.window = size;
            if (size < 0) {
                this.frameWriter.rstStream(OkHttpClientStream.this.mo36530id(), ErrorCode.FLOW_CONTROL_ERROR);
                this.transport.finishStream(OkHttpClientStream.this.mo36530id(), Status.INTERNAL.withDescription("Received data size exceeded our receiving window size"), ClientStreamListener.RpcProgress.PROCESSED, false, (ErrorCode) null, (Metadata) null);
                return;
            }
            super.transportDataReceived(new OkHttpReadableBuffer(buffer), z);
        }

        private void onEndOfStream() {
            if (!isOutboundClosed()) {
                this.transport.finishStream(OkHttpClientStream.this.mo36530id(), (Status) null, ClientStreamListener.RpcProgress.PROCESSED, false, ErrorCode.CANCEL, (Metadata) null);
            } else {
                this.transport.finishStream(OkHttpClientStream.this.mo36530id(), (Status) null, ClientStreamListener.RpcProgress.PROCESSED, false, (ErrorCode) null, (Metadata) null);
            }
        }

        /* access modifiers changed from: private */
        public void cancel(Status status, boolean z, Metadata metadata) {
            if (!this.cancelSent) {
                this.cancelSent = true;
                if (this.canStart) {
                    this.transport.removePendingStream(OkHttpClientStream.this);
                    this.requestHeaders = null;
                    this.pendingData.clear();
                    this.canStart = false;
                    if (metadata == null) {
                        metadata = new Metadata();
                    }
                    transportReportStatus(status, true, metadata);
                    return;
                }
                this.transport.finishStream(OkHttpClientStream.this.mo36530id(), status, ClientStreamListener.RpcProgress.PROCESSED, z, ErrorCode.CANCEL, metadata);
            }
        }

        /* access modifiers changed from: private */
        public void sendBuffer(Buffer buffer, boolean z, boolean z2) {
            if (!this.cancelSent) {
                if (this.canStart) {
                    this.pendingData.write(buffer, (long) ((int) buffer.size()));
                    this.pendingDataHasEndOfStream |= z;
                    this.flushPendingData |= z2;
                    return;
                }
                Preconditions.checkState(OkHttpClientStream.this.mo36530id() != -1, "streamId should be set");
                this.outboundFlow.data(z, OkHttpClientStream.this.mo36530id(), buffer, z2);
            }
        }

        /* access modifiers changed from: private */
        public void streamReady(Metadata metadata, String str) {
            this.requestHeaders = Headers.createRequestHeaders(metadata, str, OkHttpClientStream.this.authority, OkHttpClientStream.this.userAgent, OkHttpClientStream.this.useGet, this.transport.isUsingPlaintext());
            this.transport.streamReadyToStart(OkHttpClientStream.this);
        }

        /* access modifiers changed from: package-private */
        public Tag tag() {
            return this.tag;
        }
    }

    /* access modifiers changed from: package-private */
    public void setOutboundFlowState(Object obj) {
        this.outboundFlowState = obj;
    }

    /* access modifiers changed from: package-private */
    public Object getOutboundFlowState() {
        return this.outboundFlowState;
    }
}

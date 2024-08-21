package p012io.grpc.internal;

import com.google.common.base.Preconditions;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import p012io.grpc.Attributes;
import p012io.grpc.CallOptions;
import p012io.grpc.ClientStreamTracer;
import p012io.grpc.Context;
import p012io.grpc.Metadata;
import p012io.grpc.ServerStreamTracer;
import p012io.grpc.Status;
import p012io.grpc.StreamTracer;

/* renamed from: io.grpc.internal.StatsTraceContext */
public final class StatsTraceContext {
    public static final StatsTraceContext NOOP = new StatsTraceContext(new StreamTracer[0]);
    private final AtomicBoolean closed = new AtomicBoolean(false);
    private final StreamTracer[] tracers;

    public static StatsTraceContext newClientContext(CallOptions callOptions, Attributes attributes, Metadata metadata) {
        List<ClientStreamTracer.Factory> streamTracerFactories = callOptions.getStreamTracerFactories();
        if (streamTracerFactories.isEmpty()) {
            return NOOP;
        }
        ClientStreamTracer.StreamInfo build = ClientStreamTracer.StreamInfo.newBuilder().setTransportAttrs(attributes).setCallOptions(callOptions).build();
        int size = streamTracerFactories.size();
        StreamTracer[] streamTracerArr = new StreamTracer[size];
        for (int i = 0; i < size; i++) {
            streamTracerArr[i] = streamTracerFactories.get(i).newClientStreamTracer(build, metadata);
        }
        return new StatsTraceContext(streamTracerArr);
    }

    public static StatsTraceContext newServerContext(List<? extends ServerStreamTracer.Factory> list, String str, Metadata metadata) {
        if (list.isEmpty()) {
            return NOOP;
        }
        int size = list.size();
        StreamTracer[] streamTracerArr = new StreamTracer[size];
        for (int i = 0; i < size; i++) {
            streamTracerArr[i] = ((ServerStreamTracer.Factory) list.get(i)).newServerStreamTracer(str, metadata);
        }
        return new StatsTraceContext(streamTracerArr);
    }

    StatsTraceContext(StreamTracer[] streamTracerArr) {
        this.tracers = streamTracerArr;
    }

    public List<StreamTracer> getTracersForTest() {
        return new ArrayList(Arrays.asList(this.tracers));
    }

    public void clientOutboundHeaders() {
        for (StreamTracer streamTracer : this.tracers) {
            ((ClientStreamTracer) streamTracer).outboundHeaders();
        }
    }

    public void clientInboundHeaders() {
        for (StreamTracer streamTracer : this.tracers) {
            ((ClientStreamTracer) streamTracer).inboundHeaders();
        }
    }

    public void clientInboundTrailers(Metadata metadata) {
        for (StreamTracer streamTracer : this.tracers) {
            ((ClientStreamTracer) streamTracer).inboundTrailers(metadata);
        }
    }

    public <ReqT, RespT> Context serverFilterContext(Context context) {
        Context context2 = (Context) Preconditions.checkNotNull(context, "context");
        for (StreamTracer streamTracer : this.tracers) {
            context2 = ((ServerStreamTracer) streamTracer).filterContext(context2);
            Preconditions.checkNotNull(context2, "%s returns null context", (Object) streamTracer);
        }
        return context2;
    }

    public void serverCallStarted(ServerStreamTracer.ServerCallInfo<?, ?> serverCallInfo) {
        for (StreamTracer streamTracer : this.tracers) {
            ((ServerStreamTracer) streamTracer).serverCallStarted(serverCallInfo);
        }
    }

    public void streamClosed(Status status) {
        if (this.closed.compareAndSet(false, true)) {
            for (StreamTracer streamClosed : this.tracers) {
                streamClosed.streamClosed(status);
            }
        }
    }

    public void outboundMessage(int i) {
        for (StreamTracer outboundMessage : this.tracers) {
            outboundMessage.outboundMessage(i);
        }
    }

    public void inboundMessage(int i) {
        for (StreamTracer inboundMessage : this.tracers) {
            inboundMessage.inboundMessage(i);
        }
    }

    public void outboundMessageSent(int i, long j, long j2) {
        for (StreamTracer outboundMessageSent : this.tracers) {
            outboundMessageSent.outboundMessageSent(i, j, j2);
        }
    }

    public void inboundMessageRead(int i, long j, long j2) {
        for (StreamTracer inboundMessageRead : this.tracers) {
            inboundMessageRead.inboundMessageRead(i, j, j2);
        }
    }

    public void outboundUncompressedSize(long j) {
        for (StreamTracer outboundUncompressedSize : this.tracers) {
            outboundUncompressedSize.outboundUncompressedSize(j);
        }
    }

    public void outboundWireSize(long j) {
        for (StreamTracer outboundWireSize : this.tracers) {
            outboundWireSize.outboundWireSize(j);
        }
    }

    public void inboundUncompressedSize(long j) {
        for (StreamTracer inboundUncompressedSize : this.tracers) {
            inboundUncompressedSize.inboundUncompressedSize(j);
        }
    }

    public void inboundWireSize(long j) {
        for (StreamTracer inboundWireSize : this.tracers) {
            inboundWireSize.inboundWireSize(j);
        }
    }
}

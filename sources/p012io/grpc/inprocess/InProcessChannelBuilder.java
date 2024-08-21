package p012io.grpc.inprocess;

import com.google.common.base.Preconditions;
import java.net.SocketAddress;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import p012io.grpc.ChannelLogger;
import p012io.grpc.internal.AbstractManagedChannelImplBuilder;
import p012io.grpc.internal.ClientTransportFactory;
import p012io.grpc.internal.ConnectionClientTransport;
import p012io.grpc.internal.GrpcUtil;
import p012io.grpc.internal.SharedResourceHolder;

/* renamed from: io.grpc.inprocess.InProcessChannelBuilder */
public final class InProcessChannelBuilder extends AbstractManagedChannelImplBuilder<InProcessChannelBuilder> {
    private int maxInboundMetadataSize = Integer.MAX_VALUE;
    private final String name;
    private ScheduledExecutorService scheduledExecutorService;

    public InProcessChannelBuilder keepAliveTime(long j, TimeUnit timeUnit) {
        return this;
    }

    public InProcessChannelBuilder keepAliveTimeout(long j, TimeUnit timeUnit) {
        return this;
    }

    public InProcessChannelBuilder keepAliveWithoutCalls(boolean z) {
        return this;
    }

    public InProcessChannelBuilder usePlaintext() {
        return this;
    }

    public InProcessChannelBuilder useTransportSecurity() {
        return this;
    }

    public static InProcessChannelBuilder forName(String str) {
        return new InProcessChannelBuilder(str);
    }

    public static InProcessChannelBuilder forTarget(String str) {
        throw new UnsupportedOperationException("call forName() instead");
    }

    public static InProcessChannelBuilder forAddress(String str, int i) {
        throw new UnsupportedOperationException("call forName() instead");
    }

    private InProcessChannelBuilder(String str) {
        super(new InProcessSocketAddress(str), "localhost");
        this.name = (String) Preconditions.checkNotNull(str, "name");
        setStatsRecordStartedRpcs(false);
        setStatsRecordFinishedRpcs(false);
    }

    public final InProcessChannelBuilder maxInboundMessageSize(int i) {
        return (InProcessChannelBuilder) super.maxInboundMessageSize(i);
    }

    public InProcessChannelBuilder scheduledExecutorService(ScheduledExecutorService scheduledExecutorService2) {
        this.scheduledExecutorService = (ScheduledExecutorService) Preconditions.checkNotNull(scheduledExecutorService2, "scheduledExecutorService");
        return this;
    }

    public InProcessChannelBuilder maxInboundMetadataSize(int i) {
        Preconditions.checkArgument(i > 0, "maxInboundMetadataSize must be > 0");
        this.maxInboundMetadataSize = i;
        return this;
    }

    /* access modifiers changed from: protected */
    public ClientTransportFactory buildTransportFactory() {
        return new InProcessClientTransportFactory(this.name, this.scheduledExecutorService, this.maxInboundMetadataSize);
    }

    /* renamed from: io.grpc.inprocess.InProcessChannelBuilder$InProcessClientTransportFactory */
    static final class InProcessClientTransportFactory implements ClientTransportFactory {
        private boolean closed;
        private final int maxInboundMetadataSize;
        private final String name;
        private final ScheduledExecutorService timerService;
        private final boolean useSharedTimer;

        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v3, resolved type: java.lang.Object} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v2, resolved type: java.util.concurrent.ScheduledExecutorService} */
        /* JADX WARNING: Multi-variable type inference failed */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        private InProcessClientTransportFactory(java.lang.String r1, @javax.annotation.Nullable java.util.concurrent.ScheduledExecutorService r2, int r3) {
            /*
                r0 = this;
                r0.<init>()
                r0.name = r1
                if (r2 != 0) goto L_0x0009
                r1 = 1
                goto L_0x000a
            L_0x0009:
                r1 = 0
            L_0x000a:
                r0.useSharedTimer = r1
                if (r1 == 0) goto L_0x0017
                io.grpc.internal.SharedResourceHolder$Resource<java.util.concurrent.ScheduledExecutorService> r1 = p012io.grpc.internal.GrpcUtil.TIMER_SERVICE
                java.lang.Object r1 = p012io.grpc.internal.SharedResourceHolder.get(r1)
                r2 = r1
                java.util.concurrent.ScheduledExecutorService r2 = (java.util.concurrent.ScheduledExecutorService) r2
            L_0x0017:
                r0.timerService = r2
                r0.maxInboundMetadataSize = r3
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: p012io.grpc.inprocess.InProcessChannelBuilder.InProcessClientTransportFactory.<init>(java.lang.String, java.util.concurrent.ScheduledExecutorService, int):void");
        }

        public ConnectionClientTransport newClientTransport(SocketAddress socketAddress, ClientTransportFactory.ClientTransportOptions clientTransportOptions, ChannelLogger channelLogger) {
            if (!this.closed) {
                return new InProcessTransport(this.name, this.maxInboundMetadataSize, clientTransportOptions.getAuthority(), clientTransportOptions.getUserAgent(), clientTransportOptions.getEagAttributes());
            }
            throw new IllegalStateException("The transport factory is closed.");
        }

        public ScheduledExecutorService getScheduledExecutorService() {
            return this.timerService;
        }

        public void close() {
            if (!this.closed) {
                this.closed = true;
                if (this.useSharedTimer) {
                    SharedResourceHolder.release(GrpcUtil.TIMER_SERVICE, this.timerService);
                }
            }
        }
    }
}

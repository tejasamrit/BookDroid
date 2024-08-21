package p012io.grpc.okhttp;

import com.google.common.base.Preconditions;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.security.GeneralSecurityException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nullable;
import javax.net.SocketFactory;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import p012io.grpc.ChannelLogger;
import p012io.grpc.internal.AbstractManagedChannelImplBuilder;
import p012io.grpc.internal.AtomicBackoff;
import p012io.grpc.internal.ClientTransportFactory;
import p012io.grpc.internal.ConnectionClientTransport;
import p012io.grpc.internal.GrpcUtil;
import p012io.grpc.internal.KeepAliveManager;
import p012io.grpc.internal.SharedResourceHolder;
import p012io.grpc.internal.TransportTracer;
import p012io.grpc.okhttp.internal.CipherSuite;
import p012io.grpc.okhttp.internal.ConnectionSpec;
import p012io.grpc.okhttp.internal.Platform;
import p012io.grpc.okhttp.internal.TlsVersion;

/* renamed from: io.grpc.okhttp.OkHttpChannelBuilder */
public class OkHttpChannelBuilder extends AbstractManagedChannelImplBuilder<OkHttpChannelBuilder> {
    private static final long AS_LARGE_AS_INFINITE = TimeUnit.DAYS.toNanos(1000);
    public static final int DEFAULT_FLOW_CONTROL_WINDOW = 65535;
    static final ConnectionSpec INTERNAL_DEFAULT_CONNECTION_SPEC = new ConnectionSpec.Builder(ConnectionSpec.MODERN_TLS).cipherSuites(CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_256_GCM_SHA384, CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_128_GCM_SHA256, CipherSuite.TLS_ECDHE_RSA_WITH_AES_256_GCM_SHA384, CipherSuite.TLS_ECDHE_RSA_WITH_AES_128_GCM_SHA256, CipherSuite.TLS_DHE_RSA_WITH_AES_128_GCM_SHA256, CipherSuite.TLS_DHE_DSS_WITH_AES_128_GCM_SHA256, CipherSuite.TLS_DHE_RSA_WITH_AES_256_GCM_SHA384, CipherSuite.TLS_DHE_DSS_WITH_AES_256_GCM_SHA384).tlsVersions(TlsVersion.TLS_1_2).supportsTlsExtensions(true).build();
    /* access modifiers changed from: private */
    public static final SharedResourceHolder.Resource<Executor> SHARED_EXECUTOR = new SharedResourceHolder.Resource<Executor>() {
        public Executor create() {
            return Executors.newCachedThreadPool(GrpcUtil.getThreadFactory("grpc-okhttp-%d", true));
        }

        public void close(Executor executor) {
            ((ExecutorService) executor).shutdown();
        }
    };
    private ConnectionSpec connectionSpec;
    private int flowControlWindow;
    private HostnameVerifier hostnameVerifier;
    private long keepAliveTimeNanos;
    private long keepAliveTimeoutNanos;
    private boolean keepAliveWithoutCalls;
    private int maxInboundMetadataSize;
    private NegotiationType negotiationType;
    private ScheduledExecutorService scheduledExecutorService;
    private SocketFactory socketFactory;
    private SSLSocketFactory sslSocketFactory;
    private Executor transportExecutor;
    private final boolean useGetForSafeMethods;

    /* renamed from: io.grpc.okhttp.OkHttpChannelBuilder$NegotiationType */
    private enum NegotiationType {
        TLS,
        PLAINTEXT
    }

    public static OkHttpChannelBuilder forAddress(String str, int i) {
        return new OkHttpChannelBuilder(str, i);
    }

    public static OkHttpChannelBuilder forTarget(String str) {
        return new OkHttpChannelBuilder(str);
    }

    protected OkHttpChannelBuilder(String str, int i) {
        this(GrpcUtil.authorityFromHostAndPort(str, i));
    }

    private OkHttpChannelBuilder(String str) {
        super(str);
        this.connectionSpec = INTERNAL_DEFAULT_CONNECTION_SPEC;
        this.negotiationType = NegotiationType.TLS;
        this.keepAliveTimeNanos = Long.MAX_VALUE;
        this.keepAliveTimeoutNanos = GrpcUtil.DEFAULT_KEEPALIVE_TIMEOUT_NANOS;
        this.flowControlWindow = 65535;
        this.maxInboundMetadataSize = Integer.MAX_VALUE;
        this.useGetForSafeMethods = false;
    }

    /* access modifiers changed from: package-private */
    public final OkHttpChannelBuilder setTransportTracerFactory(TransportTracer.Factory factory) {
        this.transportTracerFactory = factory;
        return this;
    }

    public final OkHttpChannelBuilder transportExecutor(@Nullable Executor executor) {
        this.transportExecutor = executor;
        return this;
    }

    public final OkHttpChannelBuilder socketFactory(@Nullable SocketFactory socketFactory2) {
        this.socketFactory = socketFactory2;
        return this;
    }

    @Deprecated
    public final OkHttpChannelBuilder negotiationType(NegotiationType negotiationType2) {
        Preconditions.checkNotNull(negotiationType2, "type");
        int i = C24992.$SwitchMap$io$grpc$okhttp$NegotiationType[negotiationType2.ordinal()];
        if (i == 1) {
            this.negotiationType = NegotiationType.TLS;
        } else if (i == 2) {
            this.negotiationType = NegotiationType.PLAINTEXT;
        } else {
            throw new AssertionError("Unknown negotiation type: " + negotiationType2);
        }
        return this;
    }

    public OkHttpChannelBuilder keepAliveTime(long j, TimeUnit timeUnit) {
        Preconditions.checkArgument(j > 0, "keepalive time must be positive");
        long nanos = timeUnit.toNanos(j);
        this.keepAliveTimeNanos = nanos;
        long clampKeepAliveTimeInNanos = KeepAliveManager.clampKeepAliveTimeInNanos(nanos);
        this.keepAliveTimeNanos = clampKeepAliveTimeInNanos;
        if (clampKeepAliveTimeInNanos >= AS_LARGE_AS_INFINITE) {
            this.keepAliveTimeNanos = Long.MAX_VALUE;
        }
        return this;
    }

    public OkHttpChannelBuilder keepAliveTimeout(long j, TimeUnit timeUnit) {
        Preconditions.checkArgument(j > 0, "keepalive timeout must be positive");
        long nanos = timeUnit.toNanos(j);
        this.keepAliveTimeoutNanos = nanos;
        this.keepAliveTimeoutNanos = KeepAliveManager.clampKeepAliveTimeoutInNanos(nanos);
        return this;
    }

    public OkHttpChannelBuilder flowControlWindow(int i) {
        Preconditions.checkState(i > 0, "flowControlWindow must be positive");
        this.flowControlWindow = i;
        return this;
    }

    public OkHttpChannelBuilder keepAliveWithoutCalls(boolean z) {
        this.keepAliveWithoutCalls = z;
        return this;
    }

    public final OkHttpChannelBuilder sslSocketFactory(SSLSocketFactory sSLSocketFactory) {
        this.sslSocketFactory = sSLSocketFactory;
        this.negotiationType = NegotiationType.TLS;
        return this;
    }

    public final OkHttpChannelBuilder hostnameVerifier(@Nullable HostnameVerifier hostnameVerifier2) {
        this.hostnameVerifier = hostnameVerifier2;
        return this;
    }

    public final OkHttpChannelBuilder connectionSpec(com.squareup.okhttp.ConnectionSpec connectionSpec2) {
        Preconditions.checkArgument(connectionSpec2.isTls(), "plaintext ConnectionSpec is not accepted");
        this.connectionSpec = Utils.convertSpec(connectionSpec2);
        return this;
    }

    public final OkHttpChannelBuilder usePlaintext() {
        this.negotiationType = NegotiationType.PLAINTEXT;
        return this;
    }

    public final OkHttpChannelBuilder useTransportSecurity() {
        this.negotiationType = NegotiationType.TLS;
        return this;
    }

    public final OkHttpChannelBuilder scheduledExecutorService(ScheduledExecutorService scheduledExecutorService2) {
        this.scheduledExecutorService = (ScheduledExecutorService) Preconditions.checkNotNull(scheduledExecutorService2, "scheduledExecutorService");
        return this;
    }

    public OkHttpChannelBuilder maxInboundMetadataSize(int i) {
        Preconditions.checkArgument(i > 0, "maxInboundMetadataSize must be > 0");
        this.maxInboundMetadataSize = i;
        return this;
    }

    /* access modifiers changed from: protected */
    public final ClientTransportFactory buildTransportFactory() {
        OkHttpTransportFactory okHttpTransportFactory = r2;
        OkHttpTransportFactory okHttpTransportFactory2 = new OkHttpTransportFactory(this.transportExecutor, this.scheduledExecutorService, this.socketFactory, createSslSocketFactory(), this.hostnameVerifier, this.connectionSpec, maxInboundMessageSize(), this.keepAliveTimeNanos != Long.MAX_VALUE, this.keepAliveTimeNanos, this.keepAliveTimeoutNanos, this.flowControlWindow, this.keepAliveWithoutCalls, this.maxInboundMetadataSize, this.transportTracerFactory, false);
        return okHttpTransportFactory;
    }

    /* renamed from: io.grpc.okhttp.OkHttpChannelBuilder$2 */
    static /* synthetic */ class C24992 {
        static final /* synthetic */ int[] $SwitchMap$io$grpc$okhttp$NegotiationType;
        static final /* synthetic */ int[] $SwitchMap$io$grpc$okhttp$OkHttpChannelBuilder$NegotiationType;

        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x002e */
        static {
            /*
                io.grpc.okhttp.OkHttpChannelBuilder$NegotiationType[] r0 = p012io.grpc.okhttp.OkHttpChannelBuilder.NegotiationType.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$io$grpc$okhttp$OkHttpChannelBuilder$NegotiationType = r0
                r1 = 1
                io.grpc.okhttp.OkHttpChannelBuilder$NegotiationType r2 = p012io.grpc.okhttp.OkHttpChannelBuilder.NegotiationType.PLAINTEXT     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r2 = r2.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r0[r2] = r1     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                r0 = 2
                int[] r2 = $SwitchMap$io$grpc$okhttp$OkHttpChannelBuilder$NegotiationType     // Catch:{ NoSuchFieldError -> 0x001d }
                io.grpc.okhttp.OkHttpChannelBuilder$NegotiationType r3 = p012io.grpc.okhttp.OkHttpChannelBuilder.NegotiationType.TLS     // Catch:{ NoSuchFieldError -> 0x001d }
                int r3 = r3.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2[r3] = r0     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                io.grpc.okhttp.NegotiationType[] r2 = p012io.grpc.okhttp.NegotiationType.values()
                int r2 = r2.length
                int[] r2 = new int[r2]
                $SwitchMap$io$grpc$okhttp$NegotiationType = r2
                io.grpc.okhttp.NegotiationType r3 = p012io.grpc.okhttp.NegotiationType.TLS     // Catch:{ NoSuchFieldError -> 0x002e }
                int r3 = r3.ordinal()     // Catch:{ NoSuchFieldError -> 0x002e }
                r2[r3] = r1     // Catch:{ NoSuchFieldError -> 0x002e }
            L_0x002e:
                int[] r1 = $SwitchMap$io$grpc$okhttp$NegotiationType     // Catch:{ NoSuchFieldError -> 0x0038 }
                io.grpc.okhttp.NegotiationType r2 = p012io.grpc.okhttp.NegotiationType.PLAINTEXT     // Catch:{ NoSuchFieldError -> 0x0038 }
                int r2 = r2.ordinal()     // Catch:{ NoSuchFieldError -> 0x0038 }
                r1[r2] = r0     // Catch:{ NoSuchFieldError -> 0x0038 }
            L_0x0038:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: p012io.grpc.okhttp.OkHttpChannelBuilder.C24992.<clinit>():void");
        }
    }

    /* access modifiers changed from: protected */
    public int getDefaultPort() {
        int i = C24992.$SwitchMap$io$grpc$okhttp$OkHttpChannelBuilder$NegotiationType[this.negotiationType.ordinal()];
        if (i == 1) {
            return 80;
        }
        if (i == 2) {
            return GrpcUtil.DEFAULT_PORT_SSL;
        }
        throw new AssertionError(this.negotiationType + " not handled");
    }

    /* access modifiers changed from: package-private */
    @Nullable
    public SSLSocketFactory createSslSocketFactory() {
        int i = C24992.$SwitchMap$io$grpc$okhttp$OkHttpChannelBuilder$NegotiationType[this.negotiationType.ordinal()];
        if (i == 1) {
            return null;
        }
        if (i == 2) {
            try {
                if (this.sslSocketFactory == null) {
                    this.sslSocketFactory = SSLContext.getInstance("Default", Platform.get().getProvider()).getSocketFactory();
                }
                return this.sslSocketFactory;
            } catch (GeneralSecurityException e) {
                throw new RuntimeException("TLS Provider failure", e);
            }
        } else {
            throw new RuntimeException("Unknown negotiation type: " + this.negotiationType);
        }
    }

    /* renamed from: io.grpc.okhttp.OkHttpChannelBuilder$OkHttpTransportFactory */
    static final class OkHttpTransportFactory implements ClientTransportFactory {
        private boolean closed;
        private final ConnectionSpec connectionSpec;
        private final boolean enableKeepAlive;
        private final Executor executor;
        private final int flowControlWindow;
        @Nullable
        private final HostnameVerifier hostnameVerifier;
        private final AtomicBackoff keepAliveTimeNanos;
        private final long keepAliveTimeoutNanos;
        private final boolean keepAliveWithoutCalls;
        private final int maxInboundMetadataSize;
        private final int maxMessageSize;
        private final SocketFactory socketFactory;
        @Nullable
        private final SSLSocketFactory sslSocketFactory;
        private final ScheduledExecutorService timeoutService;
        private final TransportTracer.Factory transportTracerFactory;
        private final boolean useGetForSafeMethods;
        private final boolean usingSharedExecutor;
        private final boolean usingSharedScheduler;

        private OkHttpTransportFactory(Executor executor2, @Nullable ScheduledExecutorService scheduledExecutorService, @Nullable SocketFactory socketFactory2, @Nullable SSLSocketFactory sSLSocketFactory, @Nullable HostnameVerifier hostnameVerifier2, ConnectionSpec connectionSpec2, int i, boolean z, long j, long j2, int i2, boolean z2, int i3, TransportTracer.Factory factory, boolean z3) {
            Executor executor3 = executor2;
            boolean z4 = true;
            boolean z5 = scheduledExecutorService == null;
            this.usingSharedScheduler = z5;
            this.timeoutService = z5 ? (ScheduledExecutorService) SharedResourceHolder.get(GrpcUtil.TIMER_SERVICE) : scheduledExecutorService;
            this.socketFactory = socketFactory2;
            this.sslSocketFactory = sSLSocketFactory;
            this.hostnameVerifier = hostnameVerifier2;
            this.connectionSpec = connectionSpec2;
            this.maxMessageSize = i;
            this.enableKeepAlive = z;
            this.keepAliveTimeNanos = new AtomicBackoff("keepalive time nanos", j);
            this.keepAliveTimeoutNanos = j2;
            this.flowControlWindow = i2;
            this.keepAliveWithoutCalls = z2;
            this.maxInboundMetadataSize = i3;
            this.useGetForSafeMethods = z3;
            z4 = executor3 != null ? false : z4;
            this.usingSharedExecutor = z4;
            this.transportTracerFactory = (TransportTracer.Factory) Preconditions.checkNotNull(factory, "transportTracerFactory");
            if (z4) {
                this.executor = (Executor) SharedResourceHolder.get(OkHttpChannelBuilder.SHARED_EXECUTOR);
            } else {
                this.executor = executor3;
            }
        }

        public ConnectionClientTransport newClientTransport(SocketAddress socketAddress, ClientTransportFactory.ClientTransportOptions clientTransportOptions, ChannelLogger channelLogger) {
            if (!this.closed) {
                final AtomicBackoff.State state = this.keepAliveTimeNanos.getState();
                C25001 r15 = r2;
                C25001 r2 = new Runnable() {
                    public void run() {
                        state.backoff();
                    }
                };
                AtomicBackoff.State state2 = state;
                OkHttpClientTransport okHttpClientTransport = new OkHttpClientTransport((InetSocketAddress) socketAddress, clientTransportOptions.getAuthority(), clientTransportOptions.getUserAgent(), clientTransportOptions.getEagAttributes(), this.executor, this.socketFactory, this.sslSocketFactory, this.hostnameVerifier, this.connectionSpec, this.maxMessageSize, this.flowControlWindow, clientTransportOptions.getHttpConnectProxiedSocketAddress(), r15, this.maxInboundMetadataSize, this.transportTracerFactory.create(), this.useGetForSafeMethods);
                if (this.enableKeepAlive) {
                    okHttpClientTransport.enableKeepAlive(true, state2.get(), this.keepAliveTimeoutNanos, this.keepAliveWithoutCalls);
                }
                return okHttpClientTransport;
            }
            throw new IllegalStateException("The transport factory is closed.");
        }

        public ScheduledExecutorService getScheduledExecutorService() {
            return this.timeoutService;
        }

        public void close() {
            if (!this.closed) {
                this.closed = true;
                if (this.usingSharedScheduler) {
                    SharedResourceHolder.release(GrpcUtil.TIMER_SERVICE, this.timeoutService);
                }
                if (this.usingSharedExecutor) {
                    SharedResourceHolder.release(OkHttpChannelBuilder.SHARED_EXECUTOR, this.executor);
                }
            }
        }
    }
}

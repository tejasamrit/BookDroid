package p012io.grpc.internal;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.common.base.Stopwatch;
import com.google.common.base.Supplier;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.SettableFuture;
import java.lang.Thread;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javax.annotation.Nullable;
import p012io.grpc.Attributes;
import p012io.grpc.CallOptions;
import p012io.grpc.Channel;
import p012io.grpc.ChannelLogger;
import p012io.grpc.ClientCall;
import p012io.grpc.ClientInterceptor;
import p012io.grpc.ClientInterceptors;
import p012io.grpc.ClientStreamTracer;
import p012io.grpc.CompressorRegistry;
import p012io.grpc.ConnectivityState;
import p012io.grpc.ConnectivityStateInfo;
import p012io.grpc.Context;
import p012io.grpc.DecompressorRegistry;
import p012io.grpc.EquivalentAddressGroup;
import p012io.grpc.InternalChannelz;
import p012io.grpc.InternalInstrumented;
import p012io.grpc.InternalLogId;
import p012io.grpc.LoadBalancer;
import p012io.grpc.ManagedChannel;
import p012io.grpc.Metadata;
import p012io.grpc.MethodDescriptor;
import p012io.grpc.NameResolver;
import p012io.grpc.NameResolverRegistry;
import p012io.grpc.ProxyDetector;
import p012io.grpc.Status;
import p012io.grpc.SynchronizationContext;
import p012io.grpc.internal.AutoConfiguredLoadBalancerFactory;
import p012io.grpc.internal.BackoffPolicy;
import p012io.grpc.internal.CallTracer;
import p012io.grpc.internal.ClientCallImpl;
import p012io.grpc.internal.InternalSubchannel;
import p012io.grpc.internal.ManagedClientTransport;
import p012io.grpc.internal.RetriableStream;

/* renamed from: io.grpc.internal.ManagedChannelImpl */
final class ManagedChannelImpl extends ManagedChannel implements InternalInstrumented<InternalChannelz.ChannelStats> {
    /* access modifiers changed from: private */
    public static final ServiceConfigHolder EMPTY_SERVICE_CONFIG = new ServiceConfigHolder(Collections.emptyMap(), ManagedChannelServiceConfig.empty());
    static final long IDLE_TIMEOUT_MILLIS_DISABLE = -1;
    static final Status SHUTDOWN_NOW_STATUS = Status.UNAVAILABLE.withDescription("Channel shutdownNow invoked");
    static final Status SHUTDOWN_STATUS = Status.UNAVAILABLE.withDescription("Channel shutdown invoked");
    static final long SUBCHANNEL_SHUTDOWN_DELAY_SECONDS = 5;
    static final Status SUBCHANNEL_SHUTDOWN_STATUS = Status.UNAVAILABLE.withDescription("Subchannel shutdown invoked");
    static final Pattern URI_PATTERN = Pattern.compile("[a-zA-Z][a-zA-Z0-9+.-]*:/.*");
    static final Logger logger = Logger.getLogger(ManagedChannelImpl.class.getName());
    /* access modifiers changed from: private */
    public final BackoffPolicy.Provider backoffPolicyProvider;
    /* access modifiers changed from: private */
    public final ExecutorHolder balancerRpcExecutorHolder;
    /* access modifiers changed from: private */
    public final ObjectPool<? extends Executor> balancerRpcExecutorPool;
    /* access modifiers changed from: private */
    public final CallTracer.Factory callTracerFactory;
    /* access modifiers changed from: private */
    public final long channelBufferLimit;
    /* access modifiers changed from: private */
    public final RetriableStream.ChannelBufferMeter channelBufferUsed = new RetriableStream.ChannelBufferMeter();
    /* access modifiers changed from: private */
    public final CallTracer channelCallTracer;
    /* access modifiers changed from: private */
    public final ChannelLogger channelLogger;
    /* access modifiers changed from: private */
    public final ConnectivityStateManager channelStateManager = new ConnectivityStateManager();
    /* access modifiers changed from: private */
    public final ChannelTracer channelTracer;
    /* access modifiers changed from: private */
    public final InternalChannelz channelz;
    /* access modifiers changed from: private */
    public final CompressorRegistry compressorRegistry;
    /* access modifiers changed from: private */
    public final DecompressorRegistry decompressorRegistry;
    /* access modifiers changed from: private */
    @Nullable
    public final ServiceConfigHolder defaultServiceConfig;
    /* access modifiers changed from: private */
    public final DelayedClientTransport delayedTransport;
    private final ManagedClientTransport.C2463Listener delayedTransportListener;
    /* access modifiers changed from: private */
    public final Executor executor;
    private final ObjectPool<? extends Executor> executorPool;
    /* access modifiers changed from: private */
    public boolean fullStreamDecompression;
    private final long idleTimeoutMillis;
    private final Rescheduler idleTimer;
    final InUseStateAggregator<Object> inUseStateAggregator;
    private final Channel interceptorChannel;
    /* access modifiers changed from: private */
    public ResolutionState lastResolutionState = ResolutionState.NO_RESOLUTION;
    /* access modifiers changed from: private */
    public ServiceConfigHolder lastServiceConfig = EMPTY_SERVICE_CONFIG;
    /* access modifiers changed from: private */
    @Nullable
    public LbHelperImpl lbHelper;
    private final AutoConfiguredLoadBalancerFactory loadBalancerFactory;
    private final InternalLogId logId;
    /* access modifiers changed from: private */
    public final boolean lookUpServiceConfig;
    /* access modifiers changed from: private */
    public final int maxTraceEvents;
    private NameResolver nameResolver;
    /* access modifiers changed from: private */
    public final NameResolver.Args nameResolverArgs;
    /* access modifiers changed from: private */
    @Nullable
    public BackoffPolicy nameResolverBackoffPolicy;
    /* access modifiers changed from: private */
    public final NameResolver.Factory nameResolverFactory;
    /* access modifiers changed from: private */
    public final NameResolverRegistry nameResolverRegistry;
    /* access modifiers changed from: private */
    public boolean nameResolverStarted;
    /* access modifiers changed from: private */
    public final ExecutorHolder offloadExecutorHolder;
    /* access modifiers changed from: private */
    public final Set<OobChannel> oobChannels = new HashSet(1, 0.75f);
    private boolean panicMode;
    /* access modifiers changed from: private */
    public final long perRpcBufferLimit;
    /* access modifiers changed from: private */
    public final boolean retryEnabled;
    /* access modifiers changed from: private */
    public final RestrictedScheduledExecutor scheduledExecutor;
    /* access modifiers changed from: private */
    @Nullable
    public SynchronizationContext.ScheduledHandle scheduledNameResolverRefresh;
    private final ServiceConfigInterceptor serviceConfigInterceptor;
    /* access modifiers changed from: private */
    public boolean serviceConfigUpdated = false;
    /* access modifiers changed from: private */
    public final AtomicBoolean shutdown = new AtomicBoolean(false);
    /* access modifiers changed from: private */
    public boolean shutdownNowed;
    /* access modifiers changed from: private */
    public final Supplier<Stopwatch> stopwatchSupplier;
    /* access modifiers changed from: private */
    @Nullable
    public volatile LoadBalancer.SubchannelPicker subchannelPicker;
    /* access modifiers changed from: private */
    public final Set<InternalSubchannel> subchannels = new HashSet(16, 0.75f);
    final SynchronizationContext syncContext;
    /* access modifiers changed from: private */
    public final String target;
    /* access modifiers changed from: private */
    public volatile boolean terminated;
    private final CountDownLatch terminatedLatch = new CountDownLatch(1);
    /* access modifiers changed from: private */
    public volatile boolean terminating;
    /* access modifiers changed from: private */
    public final TimeProvider timeProvider;
    /* access modifiers changed from: private */
    public final ClientTransportFactory transportFactory;
    /* access modifiers changed from: private */
    public final ClientCallImpl.ClientTransportProvider transportProvider;
    /* access modifiers changed from: private */
    public final UncommittedRetriableStreamsRegistry uncommittedRetriableStreamsRegistry = new UncommittedRetriableStreamsRegistry();
    /* access modifiers changed from: private */
    @Nullable
    public final String userAgent;

    /* renamed from: io.grpc.internal.ManagedChannelImpl$ResolutionState */
    enum ResolutionState {
        NO_RESOLUTION,
        SUCCESS,
        ERROR
    }

    /* access modifiers changed from: private */
    public void maybeShutdownNowSubchannels() {
        if (this.shutdownNowed) {
            for (InternalSubchannel shutdownNow : this.subchannels) {
                shutdownNow.shutdownNow(SHUTDOWN_NOW_STATUS);
            }
            for (OobChannel internalSubchannel : this.oobChannels) {
                internalSubchannel.getInternalSubchannel().shutdownNow(SHUTDOWN_NOW_STATUS);
            }
        }
    }

    public ListenableFuture<InternalChannelz.ChannelStats> getStats() {
        final SettableFuture create = SettableFuture.create();
        this.syncContext.execute(new Runnable() {
            public void run() {
                InternalChannelz.ChannelStats.Builder builder = new InternalChannelz.ChannelStats.Builder();
                ManagedChannelImpl.this.channelCallTracer.updateBuilder(builder);
                ManagedChannelImpl.this.channelTracer.updateBuilder(builder);
                builder.setTarget(ManagedChannelImpl.this.target).setState(ManagedChannelImpl.this.channelStateManager.getState());
                ArrayList arrayList = new ArrayList();
                arrayList.addAll(ManagedChannelImpl.this.subchannels);
                arrayList.addAll(ManagedChannelImpl.this.oobChannels);
                builder.setSubchannels(arrayList);
                create.set(builder.build());
            }
        });
        return create;
    }

    public InternalLogId getLogId() {
        return this.logId;
    }

    /* renamed from: io.grpc.internal.ManagedChannelImpl$IdleModeTimer */
    private class IdleModeTimer implements Runnable {
        private IdleModeTimer() {
        }

        public void run() {
            ManagedChannelImpl.this.enterIdleMode();
        }
    }

    /* access modifiers changed from: private */
    public void shutdownNameResolverAndLoadBalancer(boolean z) {
        this.syncContext.throwIfNotInThisSynchronizationContext();
        if (z) {
            Preconditions.checkState(this.nameResolverStarted, "nameResolver is not started");
            Preconditions.checkState(this.lbHelper != null, "lbHelper is null");
        }
        if (this.nameResolver != null) {
            cancelNameResolverBackoff();
            this.nameResolver.shutdown();
            this.nameResolverStarted = false;
            if (z) {
                this.nameResolver = getNameResolver(this.target, this.nameResolverFactory, this.nameResolverArgs);
            } else {
                this.nameResolver = null;
            }
        }
        LbHelperImpl lbHelperImpl = this.lbHelper;
        if (lbHelperImpl != null) {
            lbHelperImpl.f492lb.shutdown();
            this.lbHelper = null;
        }
        this.subchannelPicker = null;
    }

    /* access modifiers changed from: package-private */
    public void exitIdleMode() {
        this.syncContext.throwIfNotInThisSynchronizationContext();
        if (!this.shutdown.get() && !this.panicMode) {
            if (this.inUseStateAggregator.isInUse()) {
                cancelIdleTimer(false);
            } else {
                rescheduleIdleTimer();
            }
            if (this.lbHelper == null) {
                this.channelLogger.log(ChannelLogger.ChannelLogLevel.INFO, "Exiting idle mode");
                LbHelperImpl lbHelperImpl = new LbHelperImpl();
                lbHelperImpl.f492lb = this.loadBalancerFactory.newLoadBalancer(lbHelperImpl);
                this.lbHelper = lbHelperImpl;
                this.nameResolver.start((NameResolver.Listener2) new NameResolverListener(lbHelperImpl, this.nameResolver));
                this.nameResolverStarted = true;
            }
        }
    }

    /* access modifiers changed from: private */
    public void enterIdleMode() {
        shutdownNameResolverAndLoadBalancer(true);
        this.delayedTransport.reprocess((LoadBalancer.SubchannelPicker) null);
        this.channelLogger.log(ChannelLogger.ChannelLogLevel.INFO, "Entering IDLE state");
        this.channelStateManager.gotoState(ConnectivityState.IDLE);
        if (this.inUseStateAggregator.isInUse()) {
            exitIdleMode();
        }
    }

    /* access modifiers changed from: private */
    public void cancelIdleTimer(boolean z) {
        this.idleTimer.cancel(z);
    }

    /* access modifiers changed from: private */
    public void rescheduleIdleTimer() {
        long j = this.idleTimeoutMillis;
        if (j != -1) {
            this.idleTimer.reschedule(j, TimeUnit.MILLISECONDS);
        }
    }

    /* renamed from: io.grpc.internal.ManagedChannelImpl$DelayedNameResolverRefresh */
    class DelayedNameResolverRefresh implements Runnable {
        DelayedNameResolverRefresh() {
        }

        public void run() {
            SynchronizationContext.ScheduledHandle unused = ManagedChannelImpl.this.scheduledNameResolverRefresh = null;
            ManagedChannelImpl.this.refreshNameResolution();
        }
    }

    private void cancelNameResolverBackoff() {
        this.syncContext.throwIfNotInThisSynchronizationContext();
        SynchronizationContext.ScheduledHandle scheduledHandle = this.scheduledNameResolverRefresh;
        if (scheduledHandle != null) {
            scheduledHandle.cancel();
            this.scheduledNameResolverRefresh = null;
            this.nameResolverBackoffPolicy = null;
        }
    }

    /* access modifiers changed from: private */
    public void refreshAndResetNameResolution() {
        this.syncContext.throwIfNotInThisSynchronizationContext();
        cancelNameResolverBackoff();
        refreshNameResolution();
    }

    /* access modifiers changed from: private */
    public void refreshNameResolution() {
        this.syncContext.throwIfNotInThisSynchronizationContext();
        if (this.nameResolverStarted) {
            this.nameResolver.refresh();
        }
    }

    /* renamed from: io.grpc.internal.ManagedChannelImpl$ChannelTransportProvider */
    private final class ChannelTransportProvider implements ClientCallImpl.ClientTransportProvider {
        private ChannelTransportProvider() {
        }

        public ClientTransport get(LoadBalancer.PickSubchannelArgs pickSubchannelArgs) {
            LoadBalancer.SubchannelPicker access$1300 = ManagedChannelImpl.this.subchannelPicker;
            if (ManagedChannelImpl.this.shutdown.get()) {
                return ManagedChannelImpl.this.delayedTransport;
            }
            if (access$1300 == null) {
                ManagedChannelImpl.this.syncContext.execute(new Runnable() {
                    public void run() {
                        ManagedChannelImpl.this.exitIdleMode();
                    }
                });
                return ManagedChannelImpl.this.delayedTransport;
            }
            ClientTransport transportFromPickResult = GrpcUtil.getTransportFromPickResult(access$1300.pickSubchannel(pickSubchannelArgs), pickSubchannelArgs.getCallOptions().isWaitForReady());
            if (transportFromPickResult != null) {
                return transportFromPickResult;
            }
            return ManagedChannelImpl.this.delayedTransport;
        }

        public <ReqT> ClientStream newRetriableStream(MethodDescriptor<ReqT, ?> methodDescriptor, CallOptions callOptions, Metadata metadata, Context context) {
            Preconditions.checkState(ManagedChannelImpl.this.retryEnabled, "retry should be enabled");
            return new RetriableStream<ReqT>(this, methodDescriptor, metadata, callOptions, ManagedChannelImpl.this.lastServiceConfig.managedChannelServiceConfig.getRetryThrottling(), context) {
                final /* synthetic */ ChannelTransportProvider this$1;
                final /* synthetic */ CallOptions val$callOptions;
                final /* synthetic */ Context val$context;
                final /* synthetic */ Metadata val$headers;
                final /* synthetic */ MethodDescriptor val$method;
                final /* synthetic */ RetriableStream.Throttle val$throttle;

                {
                    ChannelTransportProvider channelTransportProvider = r16;
                    CallOptions callOptions = r19;
                    this.this$1 = channelTransportProvider;
                    this.val$method = r17;
                    this.val$headers = r18;
                    this.val$callOptions = callOptions;
                    RetriableStream.Throttle throttle = r20;
                    this.val$throttle = throttle;
                    this.val$context = r21;
                    RetriableStream.ChannelBufferMeter access$1800 = ManagedChannelImpl.this.channelBufferUsed;
                    long access$1900 = ManagedChannelImpl.this.perRpcBufferLimit;
                    long access$2000 = ManagedChannelImpl.this.channelBufferLimit;
                    Executor access$2100 = ManagedChannelImpl.this.getCallExecutor(callOptions);
                    ScheduledExecutorService scheduledExecutorService = ManagedChannelImpl.this.transportFactory.getScheduledExecutorService();
                }

                /* access modifiers changed from: package-private */
                public Status prestart() {
                    return ManagedChannelImpl.this.uncommittedRetriableStreamsRegistry.add(this);
                }

                /* access modifiers changed from: package-private */
                public void postCommit() {
                    ManagedChannelImpl.this.uncommittedRetriableStreamsRegistry.remove(this);
                }

                /* access modifiers changed from: package-private */
                public ClientStream newSubstream(ClientStreamTracer.Factory factory, Metadata metadata) {
                    CallOptions withStreamTracerFactory = this.val$callOptions.withStreamTracerFactory(factory);
                    ClientTransport clientTransport = this.this$1.get(new PickSubchannelArgsImpl(this.val$method, metadata, withStreamTracerFactory));
                    Context attach = this.val$context.attach();
                    try {
                        return clientTransport.newStream(this.val$method, metadata, withStreamTracerFactory);
                    } finally {
                        this.val$context.detach(attach);
                    }
                }
            };
        }
    }

    ManagedChannelImpl(AbstractManagedChannelImplBuilder<?> abstractManagedChannelImplBuilder, ClientTransportFactory clientTransportFactory, BackoffPolicy.Provider provider, ObjectPool<? extends Executor> objectPool, Supplier<Stopwatch> supplier, List<ClientInterceptor> list, TimeProvider timeProvider2) {
        C24571 r3;
        AbstractManagedChannelImplBuilder<?> abstractManagedChannelImplBuilder2 = abstractManagedChannelImplBuilder;
        ObjectPool<? extends Executor> objectPool2 = objectPool;
        TimeProvider timeProvider3 = timeProvider2;
        SynchronizationContext synchronizationContext = new SynchronizationContext(new Thread.UncaughtExceptionHandler() {
            public void uncaughtException(Thread thread, Throwable th) {
                Logger logger = ManagedChannelImpl.logger;
                Level level = Level.SEVERE;
                logger.log(level, "[" + ManagedChannelImpl.this.getLogId() + "] Uncaught exception in the SynchronizationContext. Panic!", th);
                ManagedChannelImpl.this.panic(th);
            }
        });
        this.syncContext = synchronizationContext;
        DelayedTransportListener delayedTransportListener2 = new DelayedTransportListener();
        this.delayedTransportListener = delayedTransportListener2;
        this.inUseStateAggregator = new IdleModeStateAggregator();
        this.transportProvider = new ChannelTransportProvider();
        String str = (String) Preconditions.checkNotNull(abstractManagedChannelImplBuilder2.target, "target");
        this.target = str;
        InternalLogId allocate = InternalLogId.allocate("Channel", str);
        this.logId = allocate;
        this.timeProvider = (TimeProvider) Preconditions.checkNotNull(timeProvider3, "timeProvider");
        ObjectPool<? extends Executor> objectPool3 = (ObjectPool) Preconditions.checkNotNull(abstractManagedChannelImplBuilder2.executorPool, "executorPool");
        this.executorPool = objectPool3;
        Executor executor2 = (Executor) Preconditions.checkNotNull(objectPool3.getObject(), "executor");
        this.executor = executor2;
        CallCredentialsApplyingTransportFactory callCredentialsApplyingTransportFactory = new CallCredentialsApplyingTransportFactory(clientTransportFactory, executor2);
        this.transportFactory = callCredentialsApplyingTransportFactory;
        RestrictedScheduledExecutor restrictedScheduledExecutor = new RestrictedScheduledExecutor(callCredentialsApplyingTransportFactory.getScheduledExecutorService());
        this.scheduledExecutor = restrictedScheduledExecutor;
        this.maxTraceEvents = abstractManagedChannelImplBuilder2.maxTraceEvents;
        ChannelTracer channelTracer2 = r11;
        CallCredentialsApplyingTransportFactory callCredentialsApplyingTransportFactory2 = callCredentialsApplyingTransportFactory;
        RestrictedScheduledExecutor restrictedScheduledExecutor2 = restrictedScheduledExecutor;
        ChannelTracer channelTracer3 = new ChannelTracer(allocate, abstractManagedChannelImplBuilder2.maxTraceEvents, timeProvider2.currentTimeNanos(), "Channel for '" + str + "'");
        this.channelTracer = channelTracer2;
        ChannelLoggerImpl channelLoggerImpl = new ChannelLoggerImpl(channelTracer2, timeProvider3);
        this.channelLogger = channelLoggerImpl;
        NameResolver.Factory nameResolverFactory2 = abstractManagedChannelImplBuilder.getNameResolverFactory();
        this.nameResolverFactory = nameResolverFactory2;
        ProxyDetector proxyDetector = abstractManagedChannelImplBuilder2.proxyDetector != null ? abstractManagedChannelImplBuilder2.proxyDetector : GrpcUtil.DEFAULT_PROXY_DETECTOR;
        boolean z = abstractManagedChannelImplBuilder2.retryEnabled && !abstractManagedChannelImplBuilder2.temporarilyDisableRetry;
        this.retryEnabled = z;
        AutoConfiguredLoadBalancerFactory autoConfiguredLoadBalancerFactory = new AutoConfiguredLoadBalancerFactory(abstractManagedChannelImplBuilder2.defaultLbPolicy);
        this.loadBalancerFactory = autoConfiguredLoadBalancerFactory;
        this.offloadExecutorHolder = new ExecutorHolder((ObjectPool) Preconditions.checkNotNull(abstractManagedChannelImplBuilder2.offloadExecutorPool, "offloadExecutorPool"));
        this.nameResolverRegistry = abstractManagedChannelImplBuilder2.nameResolverRegistry;
        ScParser scParser = new ScParser(z, abstractManagedChannelImplBuilder2.maxRetryAttempts, abstractManagedChannelImplBuilder2.maxHedgedAttempts, autoConfiguredLoadBalancerFactory, channelLoggerImpl);
        NameResolver.Args build = NameResolver.Args.newBuilder().setDefaultPort(abstractManagedChannelImplBuilder.getDefaultPort()).setProxyDetector(proxyDetector).setSynchronizationContext(synchronizationContext).setScheduledExecutorService(restrictedScheduledExecutor2).setServiceConfigParser(scParser).setChannelLogger(channelLoggerImpl).setOffloadExecutor(new Executor() {
            public void execute(Runnable runnable) {
                ManagedChannelImpl.this.offloadExecutorHolder.getExecutor().execute(runnable);
            }
        }).build();
        this.nameResolverArgs = build;
        this.nameResolver = getNameResolver(str, nameResolverFactory2, build);
        this.balancerRpcExecutorPool = (ObjectPool) Preconditions.checkNotNull(objectPool2, "balancerRpcExecutorPool");
        this.balancerRpcExecutorHolder = new ExecutorHolder(objectPool2);
        DelayedClientTransport delayedClientTransport = new DelayedClientTransport(executor2, synchronizationContext);
        this.delayedTransport = delayedClientTransport;
        delayedClientTransport.start(delayedTransportListener2);
        this.backoffPolicyProvider = provider;
        ServiceConfigInterceptor serviceConfigInterceptor2 = new ServiceConfigInterceptor(z);
        this.serviceConfigInterceptor = serviceConfigInterceptor2;
        if (abstractManagedChannelImplBuilder2.defaultServiceConfig != null) {
            NameResolver.ConfigOrError parseServiceConfig = scParser.parseServiceConfig(abstractManagedChannelImplBuilder2.defaultServiceConfig);
            Preconditions.checkState(parseServiceConfig.getError() == null, "Default config is invalid: %s", (Object) parseServiceConfig.getError());
            ServiceConfigHolder serviceConfigHolder = new ServiceConfigHolder(abstractManagedChannelImplBuilder2.defaultServiceConfig, (ManagedChannelServiceConfig) parseServiceConfig.getConfig());
            this.defaultServiceConfig = serviceConfigHolder;
            this.lastServiceConfig = serviceConfigHolder;
            r3 = null;
        } else {
            r3 = null;
            this.defaultServiceConfig = null;
        }
        boolean z2 = abstractManagedChannelImplBuilder2.lookUpServiceConfig;
        this.lookUpServiceConfig = z2;
        boolean z3 = false;
        Channel intercept = ClientInterceptors.intercept((Channel) new RealChannel(this.nameResolver.getServiceAuthority()), serviceConfigInterceptor2);
        this.interceptorChannel = ClientInterceptors.intercept(abstractManagedChannelImplBuilder2.binlog != null ? abstractManagedChannelImplBuilder2.binlog.wrapChannel(intercept) : intercept, (List<? extends ClientInterceptor>) list);
        this.stopwatchSupplier = (Supplier) Preconditions.checkNotNull(supplier, "stopwatchSupplier");
        if (abstractManagedChannelImplBuilder2.idleTimeoutMillis == -1) {
            this.idleTimeoutMillis = abstractManagedChannelImplBuilder2.idleTimeoutMillis;
        } else {
            Preconditions.checkArgument(abstractManagedChannelImplBuilder2.idleTimeoutMillis >= AbstractManagedChannelImplBuilder.IDLE_MODE_MIN_TIMEOUT_MILLIS ? true : z3, "invalid idleTimeoutMillis %s", abstractManagedChannelImplBuilder2.idleTimeoutMillis);
            this.idleTimeoutMillis = abstractManagedChannelImplBuilder2.idleTimeoutMillis;
        }
        this.idleTimer = new Rescheduler(new IdleModeTimer(), synchronizationContext, callCredentialsApplyingTransportFactory2.getScheduledExecutorService(), supplier.get());
        this.fullStreamDecompression = abstractManagedChannelImplBuilder2.fullStreamDecompression;
        this.decompressorRegistry = (DecompressorRegistry) Preconditions.checkNotNull(abstractManagedChannelImplBuilder2.decompressorRegistry, "decompressorRegistry");
        this.compressorRegistry = (CompressorRegistry) Preconditions.checkNotNull(abstractManagedChannelImplBuilder2.compressorRegistry, "compressorRegistry");
        this.userAgent = abstractManagedChannelImplBuilder2.userAgent;
        this.channelBufferLimit = abstractManagedChannelImplBuilder2.retryBufferSize;
        this.perRpcBufferLimit = abstractManagedChannelImplBuilder2.perRpcBufferLimit;
        final TimeProvider timeProvider4 = timeProvider2;
        AnonymousClass1ChannelCallTracerFactory r2 = new CallTracer.Factory() {
            public CallTracer create() {
                return new CallTracer(timeProvider4);
            }
        };
        this.callTracerFactory = r2;
        this.channelCallTracer = r2.create();
        InternalChannelz internalChannelz = (InternalChannelz) Preconditions.checkNotNull(abstractManagedChannelImplBuilder2.channelz);
        this.channelz = internalChannelz;
        internalChannelz.addRootChannel(this);
        if (!z2) {
            if (this.defaultServiceConfig != null) {
                channelLoggerImpl.log(ChannelLogger.ChannelLogLevel.INFO, "Service config look-up disabled, using default service config");
            }
            handleServiceConfigUpdate();
        }
    }

    /* access modifiers changed from: private */
    public void handleServiceConfigUpdate() {
        this.serviceConfigUpdated = true;
        this.serviceConfigInterceptor.handleUpdate(this.lastServiceConfig.managedChannelServiceConfig);
    }

    static NameResolver getNameResolver(String str, NameResolver.Factory factory, NameResolver.Args args) {
        URI uri;
        NameResolver newNameResolver;
        StringBuilder sb = new StringBuilder();
        try {
            uri = new URI(str);
        } catch (URISyntaxException e) {
            sb.append(e.getMessage());
            uri = null;
        }
        if (uri != null && (newNameResolver = factory.newNameResolver(uri, args)) != null) {
            return newNameResolver;
        }
        String str2 = "";
        if (!URI_PATTERN.matcher(str).matches()) {
            try {
                NameResolver newNameResolver2 = factory.newNameResolver(new URI(factory.getDefaultScheme(), str2, "/" + str, (String) null), args);
                if (newNameResolver2 != null) {
                    return newNameResolver2;
                }
            } catch (URISyntaxException e2) {
                throw new IllegalArgumentException(e2);
            }
        }
        Object[] objArr = new Object[2];
        objArr[0] = str;
        if (sb.length() > 0) {
            str2 = " (" + sb + ")";
        }
        objArr[1] = str2;
        throw new IllegalArgumentException(String.format("cannot find a NameResolver for %s%s", objArr));
    }

    public ManagedChannelImpl shutdown() {
        this.channelLogger.log(ChannelLogger.ChannelLogLevel.DEBUG, "shutdown() called");
        if (!this.shutdown.compareAndSet(false, true)) {
            return this;
        }
        this.syncContext.executeLater(new Runnable() {
            public void run() {
                ManagedChannelImpl.this.channelLogger.log(ChannelLogger.ChannelLogLevel.INFO, "Entering SHUTDOWN state");
                ManagedChannelImpl.this.channelStateManager.gotoState(ConnectivityState.SHUTDOWN);
            }
        });
        this.uncommittedRetriableStreamsRegistry.onShutdown(SHUTDOWN_STATUS);
        this.syncContext.execute(new Runnable() {
            public void run() {
                ManagedChannelImpl.this.cancelIdleTimer(true);
            }
        });
        return this;
    }

    public ManagedChannelImpl shutdownNow() {
        this.channelLogger.log(ChannelLogger.ChannelLogLevel.DEBUG, "shutdownNow() called");
        shutdown();
        this.uncommittedRetriableStreamsRegistry.onShutdownNow(SHUTDOWN_NOW_STATUS);
        this.syncContext.execute(new Runnable() {
            public void run() {
                if (!ManagedChannelImpl.this.shutdownNowed) {
                    boolean unused = ManagedChannelImpl.this.shutdownNowed = true;
                    ManagedChannelImpl.this.maybeShutdownNowSubchannels();
                }
            }
        });
        return this;
    }

    /* access modifiers changed from: package-private */
    public void panic(Throwable th) {
        if (!this.panicMode) {
            this.panicMode = true;
            cancelIdleTimer(true);
            shutdownNameResolverAndLoadBalancer(false);
            updateSubchannelPicker(new LoadBalancer.SubchannelPicker(th) {
                private final LoadBalancer.PickResult panicPickResult;
                final /* synthetic */ Throwable val$t;

                {
                    this.val$t = r3;
                    this.panicPickResult = LoadBalancer.PickResult.withDrop(Status.INTERNAL.withDescription("Panic! This is a bug!").withCause(r3));
                }

                public LoadBalancer.PickResult pickSubchannel(LoadBalancer.PickSubchannelArgs pickSubchannelArgs) {
                    return this.panicPickResult;
                }

                public String toString() {
                    return MoreObjects.toStringHelper((Class<?>) AnonymousClass1PanicSubchannelPicker.class).add("panicPickResult", (Object) this.panicPickResult).toString();
                }
            });
            this.channelLogger.log(ChannelLogger.ChannelLogLevel.ERROR, "PANIC! Entering TRANSIENT_FAILURE");
            this.channelStateManager.gotoState(ConnectivityState.TRANSIENT_FAILURE);
        }
    }

    /* access modifiers changed from: package-private */
    public boolean isInPanicMode() {
        return this.panicMode;
    }

    /* access modifiers changed from: private */
    public void updateSubchannelPicker(LoadBalancer.SubchannelPicker subchannelPicker2) {
        this.subchannelPicker = subchannelPicker2;
        this.delayedTransport.reprocess(subchannelPicker2);
    }

    public boolean isShutdown() {
        return this.shutdown.get();
    }

    public boolean awaitTermination(long j, TimeUnit timeUnit) throws InterruptedException {
        return this.terminatedLatch.await(j, timeUnit);
    }

    public boolean isTerminated() {
        return this.terminated;
    }

    public <ReqT, RespT> ClientCall<ReqT, RespT> newCall(MethodDescriptor<ReqT, RespT> methodDescriptor, CallOptions callOptions) {
        return this.interceptorChannel.newCall(methodDescriptor, callOptions);
    }

    public String authority() {
        return this.interceptorChannel.authority();
    }

    /* access modifiers changed from: private */
    public Executor getCallExecutor(CallOptions callOptions) {
        Executor executor2 = callOptions.getExecutor();
        return executor2 == null ? this.executor : executor2;
    }

    /* renamed from: io.grpc.internal.ManagedChannelImpl$RealChannel */
    private class RealChannel extends Channel {
        private final String authority;

        private RealChannel(String str) {
            this.authority = (String) Preconditions.checkNotNull(str, "authority");
        }

        public <ReqT, RespT> ClientCall<ReqT, RespT> newCall(MethodDescriptor<ReqT, RespT> methodDescriptor, CallOptions callOptions) {
            return new ClientCallImpl(methodDescriptor, ManagedChannelImpl.this.getCallExecutor(callOptions), callOptions, ManagedChannelImpl.this.transportProvider, ManagedChannelImpl.this.terminated ? null : ManagedChannelImpl.this.transportFactory.getScheduledExecutorService(), ManagedChannelImpl.this.channelCallTracer, ManagedChannelImpl.this.retryEnabled).setFullStreamDecompression(ManagedChannelImpl.this.fullStreamDecompression).setDecompressorRegistry(ManagedChannelImpl.this.decompressorRegistry).setCompressorRegistry(ManagedChannelImpl.this.compressorRegistry);
        }

        public String authority() {
            return this.authority;
        }
    }

    /* access modifiers changed from: private */
    public void maybeTerminateChannel() {
        if (!this.terminated && this.shutdown.get() && this.subchannels.isEmpty() && this.oobChannels.isEmpty()) {
            this.channelLogger.log(ChannelLogger.ChannelLogLevel.INFO, "Terminated");
            this.channelz.removeRootChannel(this);
            this.executorPool.returnObject(this.executor);
            this.balancerRpcExecutorHolder.release();
            this.offloadExecutorHolder.release();
            this.transportFactory.close();
            this.terminated = true;
            this.terminatedLatch.countDown();
        }
    }

    /* access modifiers changed from: private */
    public void handleInternalSubchannelState(ConnectivityStateInfo connectivityStateInfo) {
        if (connectivityStateInfo.getState() == ConnectivityState.TRANSIENT_FAILURE || connectivityStateInfo.getState() == ConnectivityState.IDLE) {
            refreshAndResetNameResolution();
        }
    }

    public ConnectivityState getState(boolean z) {
        ConnectivityState state = this.channelStateManager.getState();
        if (z && state == ConnectivityState.IDLE) {
            this.syncContext.execute(new Runnable() {
                public void run() {
                    ManagedChannelImpl.this.exitIdleMode();
                    if (ManagedChannelImpl.this.subchannelPicker != null) {
                        ManagedChannelImpl.this.subchannelPicker.requestConnection();
                    }
                    if (ManagedChannelImpl.this.lbHelper != null) {
                        ManagedChannelImpl.this.lbHelper.f492lb.requestConnection();
                    }
                }
            });
        }
        return state;
    }

    public void notifyWhenStateChanged(final ConnectivityState connectivityState, final Runnable runnable) {
        this.syncContext.execute(new Runnable() {
            public void run() {
                ManagedChannelImpl.this.channelStateManager.notifyWhenStateChanged(runnable, ManagedChannelImpl.this.executor, connectivityState);
            }
        });
    }

    public void resetConnectBackoff() {
        this.syncContext.execute(new Runnable() {
            public void run() {
                if (!ManagedChannelImpl.this.shutdown.get()) {
                    if (ManagedChannelImpl.this.scheduledNameResolverRefresh != null && ManagedChannelImpl.this.scheduledNameResolverRefresh.isPending()) {
                        Preconditions.checkState(ManagedChannelImpl.this.nameResolverStarted, "name resolver must be started");
                        ManagedChannelImpl.this.refreshAndResetNameResolution();
                    }
                    for (InternalSubchannel resetConnectBackoff : ManagedChannelImpl.this.subchannels) {
                        resetConnectBackoff.resetConnectBackoff();
                    }
                    for (OobChannel resetConnectBackoff2 : ManagedChannelImpl.this.oobChannels) {
                        resetConnectBackoff2.resetConnectBackoff();
                    }
                }
            }
        });
    }

    public void enterIdle() {
        this.syncContext.execute(new Runnable() {
            public void run() {
                if (!ManagedChannelImpl.this.shutdown.get() && ManagedChannelImpl.this.lbHelper != null) {
                    ManagedChannelImpl.this.cancelIdleTimer(false);
                    ManagedChannelImpl.this.enterIdleMode();
                }
            }
        });
    }

    /* renamed from: io.grpc.internal.ManagedChannelImpl$UncommittedRetriableStreamsRegistry */
    private final class UncommittedRetriableStreamsRegistry {
        final Object lock;
        Status shutdownStatus;
        Collection<ClientStream> uncommittedRetriableStreams;

        private UncommittedRetriableStreamsRegistry() {
            this.lock = new Object();
            this.uncommittedRetriableStreams = new HashSet();
        }

        /* access modifiers changed from: package-private */
        /* JADX WARNING: Code restructure failed: missing block: B:10:0x0014, code lost:
            p012io.grpc.internal.ManagedChannelImpl.access$1500(r2.this$0).shutdown(r3);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:18:?, code lost:
            return;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:19:?, code lost:
            return;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:9:0x0012, code lost:
            if (r1 == false) goto L_?;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void onShutdown(p012io.grpc.Status r3) {
            /*
                r2 = this;
                java.lang.Object r0 = r2.lock
                monitor-enter(r0)
                io.grpc.Status r1 = r2.shutdownStatus     // Catch:{ all -> 0x001e }
                if (r1 == 0) goto L_0x0009
                monitor-exit(r0)     // Catch:{ all -> 0x001e }
                return
            L_0x0009:
                r2.shutdownStatus = r3     // Catch:{ all -> 0x001e }
                java.util.Collection<io.grpc.internal.ClientStream> r1 = r2.uncommittedRetriableStreams     // Catch:{ all -> 0x001e }
                boolean r1 = r1.isEmpty()     // Catch:{ all -> 0x001e }
                monitor-exit(r0)     // Catch:{ all -> 0x001e }
                if (r1 == 0) goto L_0x001d
                io.grpc.internal.ManagedChannelImpl r0 = p012io.grpc.internal.ManagedChannelImpl.this
                io.grpc.internal.DelayedClientTransport r0 = r0.delayedTransport
                r0.shutdown(r3)
            L_0x001d:
                return
            L_0x001e:
                r3 = move-exception
                monitor-exit(r0)     // Catch:{ all -> 0x001e }
                throw r3
            */
            throw new UnsupportedOperationException("Method not decompiled: p012io.grpc.internal.ManagedChannelImpl.UncommittedRetriableStreamsRegistry.onShutdown(io.grpc.Status):void");
        }

        /* access modifiers changed from: package-private */
        public void onShutdownNow(Status status) {
            ArrayList<ClientStream> arrayList;
            onShutdown(status);
            synchronized (this.lock) {
                arrayList = new ArrayList<>(this.uncommittedRetriableStreams);
            }
            for (ClientStream cancel : arrayList) {
                cancel.cancel(status);
            }
            ManagedChannelImpl.this.delayedTransport.shutdownNow(status);
        }

        /* access modifiers changed from: package-private */
        @Nullable
        public Status add(RetriableStream<?> retriableStream) {
            synchronized (this.lock) {
                Status status = this.shutdownStatus;
                if (status != null) {
                    return status;
                }
                this.uncommittedRetriableStreams.add(retriableStream);
                return null;
            }
        }

        /* access modifiers changed from: package-private */
        public void remove(RetriableStream<?> retriableStream) {
            Status status;
            synchronized (this.lock) {
                this.uncommittedRetriableStreams.remove(retriableStream);
                if (this.uncommittedRetriableStreams.isEmpty()) {
                    status = this.shutdownStatus;
                    this.uncommittedRetriableStreams = new HashSet();
                } else {
                    status = null;
                }
            }
            if (status != null) {
                ManagedChannelImpl.this.delayedTransport.shutdown(status);
            }
        }
    }

    /* renamed from: io.grpc.internal.ManagedChannelImpl$LbHelperImpl */
    private class LbHelperImpl extends LoadBalancer.Helper {

        /* renamed from: lb */
        AutoConfiguredLoadBalancerFactory.AutoConfiguredLoadBalancer f492lb;

        private LbHelperImpl() {
        }

        @Deprecated
        public AbstractSubchannel createSubchannel(List<EquivalentAddressGroup> list, Attributes attributes) {
            ManagedChannelImpl.this.logWarningIfNotInSyncContext("createSubchannel()");
            Preconditions.checkNotNull(list, "addressGroups");
            Preconditions.checkNotNull(attributes, "attrs");
            final SubchannelImpl createSubchannelInternal = createSubchannelInternal(LoadBalancer.CreateSubchannelArgs.newBuilder().setAddresses(list).setAttributes(attributes).build());
            createSubchannelInternal.internalStart(new LoadBalancer.SubchannelStateListener() {
                public void onSubchannelState(ConnectivityStateInfo connectivityStateInfo) {
                    LbHelperImpl lbHelperImpl = LbHelperImpl.this;
                    if (lbHelperImpl == ManagedChannelImpl.this.lbHelper) {
                        LbHelperImpl.this.f492lb.handleSubchannelState(createSubchannelInternal, connectivityStateInfo);
                    }
                }
            });
            return createSubchannelInternal;
        }

        public AbstractSubchannel createSubchannel(LoadBalancer.CreateSubchannelArgs createSubchannelArgs) {
            ManagedChannelImpl.this.syncContext.throwIfNotInThisSynchronizationContext();
            return createSubchannelInternal(createSubchannelArgs);
        }

        private SubchannelImpl createSubchannelInternal(LoadBalancer.CreateSubchannelArgs createSubchannelArgs) {
            Preconditions.checkState(!ManagedChannelImpl.this.terminated, "Channel is terminated");
            return new SubchannelImpl(createSubchannelArgs, this);
        }

        public void updateBalancingState(final ConnectivityState connectivityState, final LoadBalancer.SubchannelPicker subchannelPicker) {
            Preconditions.checkNotNull(connectivityState, "newState");
            Preconditions.checkNotNull(subchannelPicker, "newPicker");
            ManagedChannelImpl.this.logWarningIfNotInSyncContext("updateBalancingState()");
            ManagedChannelImpl.this.syncContext.execute(new Runnable() {
                public void run() {
                    LbHelperImpl lbHelperImpl = LbHelperImpl.this;
                    if (lbHelperImpl == ManagedChannelImpl.this.lbHelper) {
                        ManagedChannelImpl.this.updateSubchannelPicker(subchannelPicker);
                        if (connectivityState != ConnectivityState.SHUTDOWN) {
                            ManagedChannelImpl.this.channelLogger.log(ChannelLogger.ChannelLogLevel.INFO, "Entering {0} state with picker: {1}", connectivityState, subchannelPicker);
                            ManagedChannelImpl.this.channelStateManager.gotoState(connectivityState);
                        }
                    }
                }
            });
        }

        public void refreshNameResolution() {
            ManagedChannelImpl.this.logWarningIfNotInSyncContext("refreshNameResolution()");
            ManagedChannelImpl.this.syncContext.execute(new Runnable() {
                public void run() {
                    ManagedChannelImpl.this.refreshAndResetNameResolution();
                }
            });
        }

        @Deprecated
        public void updateSubchannelAddresses(LoadBalancer.Subchannel subchannel, List<EquivalentAddressGroup> list) {
            Preconditions.checkArgument(subchannel instanceof SubchannelImpl, "subchannel must have been returned from createSubchannel");
            ManagedChannelImpl.this.logWarningIfNotInSyncContext("updateSubchannelAddresses()");
            ((InternalSubchannel) subchannel.getInternalSubchannel()).updateAddresses(list);
        }

        public ManagedChannel createOobChannel(EquivalentAddressGroup equivalentAddressGroup, String str) {
            EquivalentAddressGroup equivalentAddressGroup2 = equivalentAddressGroup;
            Preconditions.checkState(!ManagedChannelImpl.this.terminated, "Channel is terminated");
            long currentTimeNanos = ManagedChannelImpl.this.timeProvider.currentTimeNanos();
            InternalLogId allocate = InternalLogId.allocate("OobChannel", (String) null);
            InternalLogId allocate2 = InternalLogId.allocate("Subchannel-OOB", str);
            int access$4600 = ManagedChannelImpl.this.maxTraceEvents;
            long j = currentTimeNanos;
            ChannelTracer channelTracer = new ChannelTracer(allocate, access$4600, j, "OobChannel for " + equivalentAddressGroup2);
            String str2 = str;
            OobChannel oobChannel = new OobChannel(str2, ManagedChannelImpl.this.balancerRpcExecutorPool, ManagedChannelImpl.this.transportFactory.getScheduledExecutorService(), ManagedChannelImpl.this.syncContext, ManagedChannelImpl.this.callTracerFactory.create(), channelTracer, ManagedChannelImpl.this.channelz, ManagedChannelImpl.this.timeProvider);
            ManagedChannelImpl.this.channelTracer.reportEvent(new InternalChannelz.ChannelTrace.Event.Builder().setDescription("Child OobChannel created").setSeverity(InternalChannelz.ChannelTrace.Event.Severity.CT_INFO).setTimestampNanos(currentTimeNanos).setChannelRef(oobChannel).build());
            int access$46002 = ManagedChannelImpl.this.maxTraceEvents;
            final OobChannel oobChannel2 = oobChannel;
            ChannelTracer channelTracer2 = new ChannelTracer(allocate2, access$46002, j, "Subchannel for " + equivalentAddressGroup2);
            ChannelLoggerImpl channelLoggerImpl = new ChannelLoggerImpl(channelTracer2, ManagedChannelImpl.this.timeProvider);
            final OobChannel oobChannel3 = oobChannel2;
            InternalSubchannel internalSubchannel = new InternalSubchannel(Collections.singletonList(equivalentAddressGroup), str, ManagedChannelImpl.this.userAgent, ManagedChannelImpl.this.backoffPolicyProvider, ManagedChannelImpl.this.transportFactory, ManagedChannelImpl.this.transportFactory.getScheduledExecutorService(), ManagedChannelImpl.this.stopwatchSupplier, ManagedChannelImpl.this.syncContext, new InternalSubchannel.Callback() {
                /* access modifiers changed from: package-private */
                public void onTerminated(InternalSubchannel internalSubchannel) {
                    ManagedChannelImpl.this.oobChannels.remove(oobChannel2);
                    ManagedChannelImpl.this.channelz.removeSubchannel(internalSubchannel);
                    oobChannel2.handleSubchannelTerminated();
                    ManagedChannelImpl.this.maybeTerminateChannel();
                }

                /* access modifiers changed from: package-private */
                public void onStateChange(InternalSubchannel internalSubchannel, ConnectivityStateInfo connectivityStateInfo) {
                    ManagedChannelImpl.this.handleInternalSubchannelState(connectivityStateInfo);
                    oobChannel2.handleSubchannelStateChange(connectivityStateInfo);
                }
            }, ManagedChannelImpl.this.channelz, ManagedChannelImpl.this.callTracerFactory.create(), channelTracer2, allocate2, channelLoggerImpl);
            channelTracer.reportEvent(new InternalChannelz.ChannelTrace.Event.Builder().setDescription("Child Subchannel created").setSeverity(InternalChannelz.ChannelTrace.Event.Severity.CT_INFO).setTimestampNanos(currentTimeNanos).setSubchannelRef(internalSubchannel).build());
            ManagedChannelImpl.this.channelz.addSubchannel(oobChannel3);
            ManagedChannelImpl.this.channelz.addSubchannel(internalSubchannel);
            oobChannel3.setSubchannel(internalSubchannel);
            ManagedChannelImpl.this.syncContext.execute(new Runnable() {
                public void run() {
                    if (ManagedChannelImpl.this.terminating) {
                        oobChannel3.shutdown();
                    }
                    if (!ManagedChannelImpl.this.terminated) {
                        ManagedChannelImpl.this.oobChannels.add(oobChannel3);
                    }
                }
            });
            return oobChannel3;
        }

        public void updateOobChannelAddresses(ManagedChannel managedChannel, EquivalentAddressGroup equivalentAddressGroup) {
            Preconditions.checkArgument(managedChannel instanceof OobChannel, "channel must have been returned from createOobChannel");
            ((OobChannel) managedChannel).updateAddresses(equivalentAddressGroup);
        }

        public String getAuthority() {
            return ManagedChannelImpl.this.authority();
        }

        @Deprecated
        public NameResolver.Factory getNameResolverFactory() {
            return ManagedChannelImpl.this.nameResolverFactory;
        }

        public SynchronizationContext getSynchronizationContext() {
            return ManagedChannelImpl.this.syncContext;
        }

        public ScheduledExecutorService getScheduledExecutorService() {
            return ManagedChannelImpl.this.scheduledExecutor;
        }

        public ChannelLogger getChannelLogger() {
            return ManagedChannelImpl.this.channelLogger;
        }

        public NameResolver.Args getNameResolverArgs() {
            return ManagedChannelImpl.this.nameResolverArgs;
        }

        public NameResolverRegistry getNameResolverRegistry() {
            return ManagedChannelImpl.this.nameResolverRegistry;
        }
    }

    /* renamed from: io.grpc.internal.ManagedChannelImpl$NameResolverListener */
    private final class NameResolverListener extends NameResolver.Listener2 {
        final LbHelperImpl helper;
        final NameResolver resolver;

        NameResolverListener(LbHelperImpl lbHelperImpl, NameResolver nameResolver) {
            this.helper = (LbHelperImpl) Preconditions.checkNotNull(lbHelperImpl, "helperImpl");
            this.resolver = (NameResolver) Preconditions.checkNotNull(nameResolver, "resolver");
        }

        public void onResult(final NameResolver.ResolutionResult resolutionResult) {
            ManagedChannelImpl.this.syncContext.execute(new Runnable() {
                public void run() {
                    Status status;
                    ServiceConfigHolder serviceConfigHolder;
                    ServiceConfigHolder access$6400;
                    List<EquivalentAddressGroup> addresses = resolutionResult.getAddresses();
                    Attributes attributes = resolutionResult.getAttributes();
                    ManagedChannelImpl.this.channelLogger.log(ChannelLogger.ChannelLogLevel.DEBUG, "Resolved address: {0}, config={1}", addresses, attributes);
                    ResolutionState access$6000 = ManagedChannelImpl.this.lastResolutionState;
                    if (ManagedChannelImpl.this.lastResolutionState != ResolutionState.SUCCESS) {
                        ManagedChannelImpl.this.channelLogger.log(ChannelLogger.ChannelLogLevel.INFO, "Address resolved: {0}", addresses);
                        ResolutionState unused = ManagedChannelImpl.this.lastResolutionState = ResolutionState.SUCCESS;
                    }
                    ServiceConfigHolder serviceConfigHolder2 = null;
                    BackoffPolicy unused2 = ManagedChannelImpl.this.nameResolverBackoffPolicy = null;
                    NameResolver.ConfigOrError serviceConfig = resolutionResult.getServiceConfig();
                    if (serviceConfig != null) {
                        Map map = (Map) resolutionResult.getAttributes().get(GrpcAttributes.NAME_RESOLVER_SERVICE_CONFIG);
                        if (serviceConfig.getConfig() != null) {
                            serviceConfigHolder2 = new ServiceConfigHolder(map, (ManagedChannelServiceConfig) serviceConfig.getConfig());
                        }
                        status = serviceConfig.getError();
                    } else {
                        status = null;
                    }
                    if (!ManagedChannelImpl.this.lookUpServiceConfig) {
                        if (serviceConfigHolder2 != null) {
                            ManagedChannelImpl.this.channelLogger.log(ChannelLogger.ChannelLogLevel.INFO, "Service config from name resolver discarded by channel settings");
                        }
                        serviceConfigHolder = ManagedChannelImpl.this.defaultServiceConfig == null ? ManagedChannelImpl.EMPTY_SERVICE_CONFIG : ManagedChannelImpl.this.defaultServiceConfig;
                        attributes = attributes.toBuilder().discard(GrpcAttributes.NAME_RESOLVER_SERVICE_CONFIG).build();
                    } else {
                        if (serviceConfigHolder2 != null) {
                            access$6400 = serviceConfigHolder2;
                        } else if (ManagedChannelImpl.this.defaultServiceConfig != null) {
                            access$6400 = ManagedChannelImpl.this.defaultServiceConfig;
                            ManagedChannelImpl.this.channelLogger.log(ChannelLogger.ChannelLogLevel.INFO, "Received no service config, using default service config");
                        } else if (status == null) {
                            access$6400 = ManagedChannelImpl.EMPTY_SERVICE_CONFIG;
                        } else if (!ManagedChannelImpl.this.serviceConfigUpdated) {
                            ManagedChannelImpl.this.channelLogger.log(ChannelLogger.ChannelLogLevel.INFO, "Fallback to error due to invalid first service config without default config");
                            NameResolverListener.this.onError(serviceConfig.getError());
                            return;
                        } else {
                            access$6400 = ManagedChannelImpl.this.lastServiceConfig;
                        }
                        if (!serviceConfigHolder.equals(ManagedChannelImpl.this.lastServiceConfig)) {
                            ChannelLogger access$2900 = ManagedChannelImpl.this.channelLogger;
                            ChannelLogger.ChannelLogLevel channelLogLevel = ChannelLogger.ChannelLogLevel.INFO;
                            Object[] objArr = new Object[1];
                            objArr[0] = serviceConfigHolder == ManagedChannelImpl.EMPTY_SERVICE_CONFIG ? " to empty" : "";
                            access$2900.log(channelLogLevel, "Service config changed{0}", objArr);
                            ServiceConfigHolder unused3 = ManagedChannelImpl.this.lastServiceConfig = serviceConfigHolder;
                        }
                        try {
                            ManagedChannelImpl.this.handleServiceConfigUpdate();
                        } catch (RuntimeException e) {
                            ManagedChannelImpl.logger.log(Level.WARNING, "[" + ManagedChannelImpl.this.getLogId() + "] Unexpected exception from parsing service config", e);
                        }
                    }
                    if (NameResolverListener.this.helper == ManagedChannelImpl.this.lbHelper) {
                        if (serviceConfigHolder != serviceConfigHolder2) {
                            attributes = attributes.toBuilder().set(GrpcAttributes.NAME_RESOLVER_SERVICE_CONFIG, serviceConfigHolder.rawServiceConfig).build();
                        }
                        Status tryHandleResolvedAddresses = NameResolverListener.this.helper.f492lb.tryHandleResolvedAddresses(LoadBalancer.ResolvedAddresses.newBuilder().setAddresses(addresses).setAttributes(attributes).setLoadBalancingPolicyConfig(serviceConfigHolder.managedChannelServiceConfig.getLoadBalancingConfig()).build());
                        if (tryHandleResolvedAddresses.isOk()) {
                            return;
                        }
                        if (!addresses.isEmpty() || access$6000 != ResolutionState.SUCCESS) {
                            NameResolverListener.this.handleErrorInSyncContext(tryHandleResolvedAddresses.augmentDescription(NameResolverListener.this.resolver + " was used"));
                            return;
                        }
                        NameResolverListener.this.scheduleExponentialBackOffInSyncContext();
                    }
                }
            });
        }

        public void onError(final Status status) {
            Preconditions.checkArgument(!status.isOk(), "the error status must not be OK");
            ManagedChannelImpl.this.syncContext.execute(new Runnable() {
                public void run() {
                    NameResolverListener.this.handleErrorInSyncContext(status);
                }
            });
        }

        /* access modifiers changed from: private */
        public void handleErrorInSyncContext(Status status) {
            ManagedChannelImpl.logger.log(Level.WARNING, "[{0}] Failed to resolve name. status={1}", new Object[]{ManagedChannelImpl.this.getLogId(), status});
            if (ManagedChannelImpl.this.lastResolutionState != ResolutionState.ERROR) {
                ManagedChannelImpl.this.channelLogger.log(ChannelLogger.ChannelLogLevel.WARNING, "Failed to resolve name: {0}", status);
                ResolutionState unused = ManagedChannelImpl.this.lastResolutionState = ResolutionState.ERROR;
            }
            if (this.helper == ManagedChannelImpl.this.lbHelper) {
                this.helper.f492lb.handleNameResolutionError(status);
                scheduleExponentialBackOffInSyncContext();
            }
        }

        /* access modifiers changed from: private */
        public void scheduleExponentialBackOffInSyncContext() {
            if (ManagedChannelImpl.this.scheduledNameResolverRefresh == null || !ManagedChannelImpl.this.scheduledNameResolverRefresh.isPending()) {
                if (ManagedChannelImpl.this.nameResolverBackoffPolicy == null) {
                    ManagedChannelImpl managedChannelImpl = ManagedChannelImpl.this;
                    BackoffPolicy unused = managedChannelImpl.nameResolverBackoffPolicy = managedChannelImpl.backoffPolicyProvider.get();
                }
                long nextBackoffNanos = ManagedChannelImpl.this.nameResolverBackoffPolicy.nextBackoffNanos();
                ManagedChannelImpl.this.channelLogger.log(ChannelLogger.ChannelLogLevel.DEBUG, "Scheduling DNS resolution backoff for {0} ns", Long.valueOf(nextBackoffNanos));
                ManagedChannelImpl managedChannelImpl2 = ManagedChannelImpl.this;
                SynchronizationContext.ScheduledHandle unused2 = managedChannelImpl2.scheduledNameResolverRefresh = managedChannelImpl2.syncContext.schedule(new DelayedNameResolverRefresh(), nextBackoffNanos, TimeUnit.NANOSECONDS, ManagedChannelImpl.this.transportFactory.getScheduledExecutorService());
            }
        }
    }

    /* renamed from: io.grpc.internal.ManagedChannelImpl$SubchannelImpl */
    private final class SubchannelImpl extends AbstractSubchannel {
        final LoadBalancer.CreateSubchannelArgs args;
        SynchronizationContext.ScheduledHandle delayedShutdownTask;
        final LbHelperImpl helper;
        LoadBalancer.SubchannelStateListener listener;
        boolean shutdown;
        boolean started;
        InternalSubchannel subchannel;
        final InternalLogId subchannelLogId;
        final ChannelLoggerImpl subchannelLogger;
        final ChannelTracer subchannelTracer;

        SubchannelImpl(LoadBalancer.CreateSubchannelArgs createSubchannelArgs, LbHelperImpl lbHelperImpl) {
            this.args = (LoadBalancer.CreateSubchannelArgs) Preconditions.checkNotNull(createSubchannelArgs, "args");
            this.helper = (LbHelperImpl) Preconditions.checkNotNull(lbHelperImpl, "helper");
            InternalLogId allocate = InternalLogId.allocate("Subchannel", ManagedChannelImpl.this.authority());
            this.subchannelLogId = allocate;
            int access$4600 = ManagedChannelImpl.this.maxTraceEvents;
            long currentTimeNanos = ManagedChannelImpl.this.timeProvider.currentTimeNanos();
            ChannelTracer channelTracer = new ChannelTracer(allocate, access$4600, currentTimeNanos, "Subchannel for " + createSubchannelArgs.getAddresses());
            this.subchannelTracer = channelTracer;
            this.subchannelLogger = new ChannelLoggerImpl(channelTracer, ManagedChannelImpl.this.timeProvider);
        }

        /* access modifiers changed from: private */
        public void internalStart(LoadBalancer.SubchannelStateListener subchannelStateListener) {
            final LoadBalancer.SubchannelStateListener subchannelStateListener2 = subchannelStateListener;
            Preconditions.checkState(!this.started, "already started");
            Preconditions.checkState(!this.shutdown, "already shutdown");
            this.started = true;
            this.listener = subchannelStateListener2;
            if (ManagedChannelImpl.this.terminating) {
                ManagedChannelImpl.this.syncContext.execute(new Runnable() {
                    public void run() {
                        subchannelStateListener2.onSubchannelState(ConnectivityStateInfo.forNonError(ConnectivityState.SHUTDOWN));
                    }
                });
                return;
            }
            List<EquivalentAddressGroup> addresses = this.args.getAddresses();
            String authority = ManagedChannelImpl.this.authority();
            String access$5200 = ManagedChannelImpl.this.userAgent;
            BackoffPolicy.Provider access$5300 = ManagedChannelImpl.this.backoffPolicyProvider;
            ClientTransportFactory access$2200 = ManagedChannelImpl.this.transportFactory;
            ScheduledExecutorService scheduledExecutorService = ManagedChannelImpl.this.transportFactory.getScheduledExecutorService();
            Supplier access$5400 = ManagedChannelImpl.this.stopwatchSupplier;
            SynchronizationContext synchronizationContext = ManagedChannelImpl.this.syncContext;
            AnonymousClass1ManagedInternalSubchannelCallback r13 = new InternalSubchannel.Callback() {
                /* access modifiers changed from: package-private */
                public void onTerminated(InternalSubchannel internalSubchannel) {
                    ManagedChannelImpl.this.subchannels.remove(internalSubchannel);
                    ManagedChannelImpl.this.channelz.removeSubchannel(internalSubchannel);
                    ManagedChannelImpl.this.maybeTerminateChannel();
                }

                /* access modifiers changed from: package-private */
                public void onStateChange(InternalSubchannel internalSubchannel, ConnectivityStateInfo connectivityStateInfo) {
                    ManagedChannelImpl.this.handleInternalSubchannelState(connectivityStateInfo);
                    Preconditions.checkState(subchannelStateListener2 != null, "listener is null");
                    subchannelStateListener2.onSubchannelState(connectivityStateInfo);
                }

                /* access modifiers changed from: package-private */
                public void onInUse(InternalSubchannel internalSubchannel) {
                    ManagedChannelImpl.this.inUseStateAggregator.updateObjectInUse(internalSubchannel, true);
                }

                /* access modifiers changed from: package-private */
                public void onNotInUse(InternalSubchannel internalSubchannel) {
                    ManagedChannelImpl.this.inUseStateAggregator.updateObjectInUse(internalSubchannel, false);
                }
            };
            InternalChannelz access$4900 = ManagedChannelImpl.this.channelz;
            CallTracer create = ManagedChannelImpl.this.callTracerFactory.create();
            ChannelTracer channelTracer = this.subchannelTracer;
            final InternalSubchannel internalSubchannel = new InternalSubchannel(addresses, authority, access$5200, access$5300, access$2200, scheduledExecutorService, access$5400, synchronizationContext, r13, access$4900, create, channelTracer, this.subchannelLogId, this.subchannelLogger);
            ManagedChannelImpl.this.channelTracer.reportEvent(new InternalChannelz.ChannelTrace.Event.Builder().setDescription("Child Subchannel started").setSeverity(InternalChannelz.ChannelTrace.Event.Severity.CT_INFO).setTimestampNanos(ManagedChannelImpl.this.timeProvider.currentTimeNanos()).setSubchannelRef(internalSubchannel).build());
            this.subchannel = internalSubchannel;
            ManagedChannelImpl.this.syncContext.execute(new Runnable() {
                public void run() {
                    ManagedChannelImpl.this.channelz.addSubchannel(internalSubchannel);
                    ManagedChannelImpl.this.subchannels.add(internalSubchannel);
                }
            });
        }

        public void start(LoadBalancer.SubchannelStateListener subchannelStateListener) {
            ManagedChannelImpl.this.syncContext.throwIfNotInThisSynchronizationContext();
            internalStart(subchannelStateListener);
        }

        /* access modifiers changed from: package-private */
        public InternalInstrumented<InternalChannelz.ChannelStats> getInstrumentedInternalSubchannel() {
            Preconditions.checkState(this.started, "not started");
            return this.subchannel;
        }

        public void shutdown() {
            ManagedChannelImpl.this.logWarningIfNotInSyncContext("Subchannel.shutdown()");
            ManagedChannelImpl.this.syncContext.execute(new Runnable() {
                public void run() {
                    SubchannelImpl.this.internalShutdown();
                }
            });
        }

        /* access modifiers changed from: private */
        public void internalShutdown() {
            SynchronizationContext.ScheduledHandle scheduledHandle;
            ManagedChannelImpl.this.syncContext.throwIfNotInThisSynchronizationContext();
            if (this.subchannel == null) {
                this.shutdown = true;
                return;
            }
            if (!this.shutdown) {
                this.shutdown = true;
            } else if (ManagedChannelImpl.this.terminating && (scheduledHandle = this.delayedShutdownTask) != null) {
                scheduledHandle.cancel();
                this.delayedShutdownTask = null;
            } else {
                return;
            }
            if (!ManagedChannelImpl.this.terminating) {
                this.delayedShutdownTask = ManagedChannelImpl.this.syncContext.schedule(new LogExceptionRunnable(new Runnable() {
                    public void run() {
                        SubchannelImpl.this.subchannel.shutdown(ManagedChannelImpl.SUBCHANNEL_SHUTDOWN_STATUS);
                    }
                }), 5, TimeUnit.SECONDS, ManagedChannelImpl.this.transportFactory.getScheduledExecutorService());
            } else {
                this.subchannel.shutdown(ManagedChannelImpl.SHUTDOWN_STATUS);
            }
        }

        public void requestConnection() {
            ManagedChannelImpl.this.logWarningIfNotInSyncContext("Subchannel.requestConnection()");
            Preconditions.checkState(this.started, "not started");
            this.subchannel.obtainActiveTransport();
        }

        public List<EquivalentAddressGroup> getAllAddresses() {
            ManagedChannelImpl.this.logWarningIfNotInSyncContext("Subchannel.getAllAddresses()");
            Preconditions.checkState(this.started, "not started");
            return this.subchannel.getAddressGroups();
        }

        public Attributes getAttributes() {
            return this.args.getAttributes();
        }

        public String toString() {
            return this.subchannelLogId.toString();
        }

        public Channel asChannel() {
            Preconditions.checkState(this.started, "not started");
            return new SubchannelChannel(this.subchannel, ManagedChannelImpl.this.balancerRpcExecutorHolder.getExecutor(), ManagedChannelImpl.this.transportFactory.getScheduledExecutorService(), ManagedChannelImpl.this.callTracerFactory.create());
        }

        public Object getInternalSubchannel() {
            Preconditions.checkState(this.started, "Subchannel is not started");
            return this.subchannel;
        }

        public ChannelLogger getChannelLogger() {
            return this.subchannelLogger;
        }

        public void updateAddresses(List<EquivalentAddressGroup> list) {
            ManagedChannelImpl.this.syncContext.throwIfNotInThisSynchronizationContext();
            this.subchannel.updateAddresses(list);
        }
    }

    public String toString() {
        return MoreObjects.toStringHelper((Object) this).add("logId", this.logId.getId()).add("target", (Object) this.target).toString();
    }

    /* renamed from: io.grpc.internal.ManagedChannelImpl$DelayedTransportListener */
    private final class DelayedTransportListener implements ManagedClientTransport.C2463Listener {
        public void transportReady() {
        }

        private DelayedTransportListener() {
        }

        public void transportShutdown(Status status) {
            Preconditions.checkState(ManagedChannelImpl.this.shutdown.get(), "Channel must have been shut down");
        }

        public void transportInUse(boolean z) {
            ManagedChannelImpl.this.inUseStateAggregator.updateObjectInUse(ManagedChannelImpl.this.delayedTransport, z);
        }

        public void transportTerminated() {
            Preconditions.checkState(ManagedChannelImpl.this.shutdown.get(), "Channel must have been shut down");
            boolean unused = ManagedChannelImpl.this.terminating = true;
            ManagedChannelImpl.this.shutdownNameResolverAndLoadBalancer(false);
            ManagedChannelImpl.this.maybeShutdownNowSubchannels();
            ManagedChannelImpl.this.maybeTerminateChannel();
        }
    }

    /* renamed from: io.grpc.internal.ManagedChannelImpl$IdleModeStateAggregator */
    private final class IdleModeStateAggregator extends InUseStateAggregator<Object> {
        private IdleModeStateAggregator() {
        }

        /* access modifiers changed from: protected */
        public void handleInUse() {
            ManagedChannelImpl.this.exitIdleMode();
        }

        /* access modifiers changed from: protected */
        public void handleNotInUse() {
            if (!ManagedChannelImpl.this.shutdown.get()) {
                ManagedChannelImpl.this.rescheduleIdleTimer();
            }
        }
    }

    /* renamed from: io.grpc.internal.ManagedChannelImpl$ExecutorHolder */
    private static final class ExecutorHolder {
        private Executor executor;
        private final ObjectPool<? extends Executor> pool;

        ExecutorHolder(ObjectPool<? extends Executor> objectPool) {
            this.pool = (ObjectPool) Preconditions.checkNotNull(objectPool, "executorPool");
        }

        /* access modifiers changed from: package-private */
        public synchronized Executor getExecutor() {
            if (this.executor == null) {
                this.executor = (Executor) Preconditions.checkNotNull(this.pool.getObject(), "%s.getObject()", (Object) this.executor);
            }
            return this.executor;
        }

        /* access modifiers changed from: package-private */
        public synchronized void release() {
            Executor executor2 = this.executor;
            if (executor2 != null) {
                this.executor = (Executor) this.pool.returnObject(executor2);
            }
        }
    }

    /* renamed from: io.grpc.internal.ManagedChannelImpl$RestrictedScheduledExecutor */
    private static final class RestrictedScheduledExecutor implements ScheduledExecutorService {
        final ScheduledExecutorService delegate;

        private RestrictedScheduledExecutor(ScheduledExecutorService scheduledExecutorService) {
            this.delegate = (ScheduledExecutorService) Preconditions.checkNotNull(scheduledExecutorService, "delegate");
        }

        public <V> ScheduledFuture<V> schedule(Callable<V> callable, long j, TimeUnit timeUnit) {
            return this.delegate.schedule(callable, j, timeUnit);
        }

        public ScheduledFuture<?> schedule(Runnable runnable, long j, TimeUnit timeUnit) {
            return this.delegate.schedule(runnable, j, timeUnit);
        }

        public ScheduledFuture<?> scheduleAtFixedRate(Runnable runnable, long j, long j2, TimeUnit timeUnit) {
            return this.delegate.scheduleAtFixedRate(runnable, j, j2, timeUnit);
        }

        public ScheduledFuture<?> scheduleWithFixedDelay(Runnable runnable, long j, long j2, TimeUnit timeUnit) {
            return this.delegate.scheduleWithFixedDelay(runnable, j, j2, timeUnit);
        }

        public boolean awaitTermination(long j, TimeUnit timeUnit) throws InterruptedException {
            return this.delegate.awaitTermination(j, timeUnit);
        }

        public <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> collection) throws InterruptedException {
            return this.delegate.invokeAll(collection);
        }

        public <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> collection, long j, TimeUnit timeUnit) throws InterruptedException {
            return this.delegate.invokeAll(collection, j, timeUnit);
        }

        public <T> T invokeAny(Collection<? extends Callable<T>> collection) throws InterruptedException, ExecutionException {
            return this.delegate.invokeAny(collection);
        }

        public <T> T invokeAny(Collection<? extends Callable<T>> collection, long j, TimeUnit timeUnit) throws InterruptedException, ExecutionException, TimeoutException {
            return this.delegate.invokeAny(collection, j, timeUnit);
        }

        public boolean isShutdown() {
            return this.delegate.isShutdown();
        }

        public boolean isTerminated() {
            return this.delegate.isTerminated();
        }

        public void shutdown() {
            throw new UnsupportedOperationException("Restricted: shutdown() is not allowed");
        }

        public List<Runnable> shutdownNow() {
            throw new UnsupportedOperationException("Restricted: shutdownNow() is not allowed");
        }

        public <T> Future<T> submit(Callable<T> callable) {
            return this.delegate.submit(callable);
        }

        public Future<?> submit(Runnable runnable) {
            return this.delegate.submit(runnable);
        }

        public <T> Future<T> submit(Runnable runnable, T t) {
            return this.delegate.submit(runnable, t);
        }

        public void execute(Runnable runnable) {
            this.delegate.execute(runnable);
        }
    }

    /* renamed from: io.grpc.internal.ManagedChannelImpl$ScParser */
    static final class ScParser extends NameResolver.ServiceConfigParser {
        private final AutoConfiguredLoadBalancerFactory autoLoadBalancerFactory;
        private final ChannelLogger channelLogger;
        private final int maxHedgedAttemptsLimit;
        private final int maxRetryAttemptsLimit;
        private final boolean retryEnabled;

        ScParser(boolean z, int i, int i2, AutoConfiguredLoadBalancerFactory autoConfiguredLoadBalancerFactory, ChannelLogger channelLogger2) {
            this.retryEnabled = z;
            this.maxRetryAttemptsLimit = i;
            this.maxHedgedAttemptsLimit = i2;
            this.autoLoadBalancerFactory = (AutoConfiguredLoadBalancerFactory) Preconditions.checkNotNull(autoConfiguredLoadBalancerFactory, "autoLoadBalancerFactory");
            this.channelLogger = (ChannelLogger) Preconditions.checkNotNull(channelLogger2, "channelLogger");
        }

        public NameResolver.ConfigOrError parseServiceConfig(Map<String, ?> map) {
            Object obj;
            try {
                NameResolver.ConfigOrError parseLoadBalancerPolicy = this.autoLoadBalancerFactory.parseLoadBalancerPolicy(map, this.channelLogger);
                if (parseLoadBalancerPolicy == null) {
                    obj = null;
                } else if (parseLoadBalancerPolicy.getError() != null) {
                    return NameResolver.ConfigOrError.fromError(parseLoadBalancerPolicy.getError());
                } else {
                    obj = parseLoadBalancerPolicy.getConfig();
                }
                return NameResolver.ConfigOrError.fromConfig(ManagedChannelServiceConfig.fromServiceConfig(map, this.retryEnabled, this.maxRetryAttemptsLimit, this.maxHedgedAttemptsLimit, obj));
            } catch (RuntimeException e) {
                return NameResolver.ConfigOrError.fromError(Status.UNKNOWN.withDescription("failed to parse service config").withCause(e));
            }
        }
    }

    /* access modifiers changed from: private */
    public void logWarningIfNotInSyncContext(String str) {
        try {
            this.syncContext.throwIfNotInThisSynchronizationContext();
        } catch (IllegalStateException e) {
            Logger logger2 = logger;
            Level level = Level.WARNING;
            logger2.log(level, str + " should be called from SynchronizationContext. This warning will become an exception in a future release. See https://github.com/grpc/grpc-java/issues/5015 for more details", e);
        }
    }

    /* renamed from: io.grpc.internal.ManagedChannelImpl$ServiceConfigHolder */
    private static final class ServiceConfigHolder {
        ManagedChannelServiceConfig managedChannelServiceConfig;
        Map<String, ?> rawServiceConfig;

        ServiceConfigHolder(Map<String, ?> map, ManagedChannelServiceConfig managedChannelServiceConfig2) {
            this.rawServiceConfig = (Map) Preconditions.checkNotNull(map, "rawServiceConfig");
            this.managedChannelServiceConfig = (ManagedChannelServiceConfig) Preconditions.checkNotNull(managedChannelServiceConfig2, "managedChannelServiceConfig");
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            ServiceConfigHolder serviceConfigHolder = (ServiceConfigHolder) obj;
            if (!Objects.equal(this.rawServiceConfig, serviceConfigHolder.rawServiceConfig) || !Objects.equal(this.managedChannelServiceConfig, serviceConfigHolder.managedChannelServiceConfig)) {
                return false;
            }
            return true;
        }

        public int hashCode() {
            return Objects.hashCode(this.rawServiceConfig, this.managedChannelServiceConfig);
        }

        public String toString() {
            return MoreObjects.toStringHelper((Object) this).add("rawServiceConfig", (Object) this.rawServiceConfig).add("managedChannelServiceConfig", (Object) this.managedChannelServiceConfig).toString();
        }
    }
}

package p012io.grpc.internal;

import androidx.core.app.NotificationCompat;
import com.google.common.base.Preconditions;
import com.google.common.util.concurrent.MoreExecutors;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;
import javax.annotation.Nullable;
import p012io.grpc.BinaryLog;
import p012io.grpc.BindableService;
import p012io.grpc.CompressorRegistry;
import p012io.grpc.Context;
import p012io.grpc.Deadline;
import p012io.grpc.DecompressorRegistry;
import p012io.grpc.HandlerRegistry;
import p012io.grpc.InternalChannelz;
import p012io.grpc.InternalNotifyOnServerBuild;
import p012io.grpc.Server;
import p012io.grpc.ServerBuilder;
import p012io.grpc.ServerInterceptor;
import p012io.grpc.ServerMethodDefinition;
import p012io.grpc.ServerServiceDefinition;
import p012io.grpc.ServerStreamTracer;
import p012io.grpc.ServerTransportFilter;
import p012io.grpc.internal.AbstractServerImplBuilder;
import p012io.grpc.internal.CallTracer;
import p012io.grpc.internal.InternalHandlerRegistry;
import p012io.grpc.internal.TransportTracer;

/* renamed from: io.grpc.internal.AbstractServerImplBuilder */
public abstract class AbstractServerImplBuilder<T extends AbstractServerImplBuilder<T>> extends ServerBuilder<T> {
    private static final CompressorRegistry DEFAULT_COMPRESSOR_REGISTRY = CompressorRegistry.getDefaultInstance();
    private static final DecompressorRegistry DEFAULT_DECOMPRESSOR_REGISTRY = DecompressorRegistry.getDefaultInstance();
    private static final ObjectPool<? extends Executor> DEFAULT_EXECUTOR_POOL = SharedResourcePool.forResource(GrpcUtil.SHARED_CHANNEL_EXECUTOR);
    private static final HandlerRegistry DEFAULT_FALLBACK_REGISTRY = new DefaultFallbackRegistry();
    private static final long DEFAULT_HANDSHAKE_TIMEOUT_MILLIS = TimeUnit.SECONDS.toMillis(120);
    private static final Logger log = Logger.getLogger(AbstractServerImplBuilder.class.getName());
    @Nullable
    BinaryLog binlog;
    CallTracer.Factory callTracerFactory = CallTracer.getDefaultFactory();
    InternalChannelz channelz = InternalChannelz.instance();
    CompressorRegistry compressorRegistry = DEFAULT_COMPRESSOR_REGISTRY;
    DecompressorRegistry decompressorRegistry = DEFAULT_DECOMPRESSOR_REGISTRY;
    ObjectPool<? extends Executor> executorPool = DEFAULT_EXECUTOR_POOL;
    HandlerRegistry fallbackRegistry = DEFAULT_FALLBACK_REGISTRY;
    long handshakeTimeoutMillis = DEFAULT_HANDSHAKE_TIMEOUT_MILLIS;
    final List<ServerInterceptor> interceptors = new ArrayList();
    private final List<InternalNotifyOnServerBuild> notifyOnBuildList = new ArrayList();
    private boolean recordFinishedRpcs = true;
    private boolean recordRealTimeMetrics = false;
    private boolean recordStartedRpcs = true;
    final InternalHandlerRegistry.Builder registryBuilder = new InternalHandlerRegistry.Builder();
    private boolean statsEnabled = true;
    private final List<ServerStreamTracer.Factory> streamTracerFactories = new ArrayList();
    Deadline.Ticker ticker = Deadline.getSystemTicker();
    private boolean tracingEnabled = true;
    final List<ServerTransportFilter> transportFilters = new ArrayList();
    TransportTracer.Factory transportTracerFactory = TransportTracer.getDefaultFactory();

    private T thisT() {
        return this;
    }

    /* access modifiers changed from: protected */
    public abstract List<? extends InternalServer> buildTransportServers(List<? extends ServerStreamTracer.Factory> list);

    public static ServerBuilder<?> forPort(int i) {
        throw new UnsupportedOperationException("Subclass failed to hide static factory");
    }

    public final T directExecutor() {
        return executor(MoreExecutors.directExecutor());
    }

    public final T executor(@Nullable Executor executor) {
        this.executorPool = executor != null ? new FixedObjectPool<>(executor) : DEFAULT_EXECUTOR_POOL;
        return thisT();
    }

    public final T addService(ServerServiceDefinition serverServiceDefinition) {
        this.registryBuilder.addService((ServerServiceDefinition) Preconditions.checkNotNull(serverServiceDefinition, NotificationCompat.CATEGORY_SERVICE));
        return thisT();
    }

    public final T addService(BindableService bindableService) {
        if (bindableService instanceof InternalNotifyOnServerBuild) {
            this.notifyOnBuildList.add((InternalNotifyOnServerBuild) bindableService);
        }
        return addService(((BindableService) Preconditions.checkNotNull(bindableService, "bindableService")).bindService());
    }

    public final T addTransportFilter(ServerTransportFilter serverTransportFilter) {
        this.transportFilters.add(Preconditions.checkNotNull(serverTransportFilter, "filter"));
        return thisT();
    }

    public final T intercept(ServerInterceptor serverInterceptor) {
        this.interceptors.add(Preconditions.checkNotNull(serverInterceptor, "interceptor"));
        return thisT();
    }

    public final T addStreamTracerFactory(ServerStreamTracer.Factory factory) {
        this.streamTracerFactories.add(Preconditions.checkNotNull(factory, "factory"));
        return thisT();
    }

    public final T fallbackHandlerRegistry(@Nullable HandlerRegistry handlerRegistry) {
        if (handlerRegistry == null) {
            handlerRegistry = DEFAULT_FALLBACK_REGISTRY;
        }
        this.fallbackRegistry = handlerRegistry;
        return thisT();
    }

    public final T decompressorRegistry(@Nullable DecompressorRegistry decompressorRegistry2) {
        if (decompressorRegistry2 == null) {
            decompressorRegistry2 = DEFAULT_DECOMPRESSOR_REGISTRY;
        }
        this.decompressorRegistry = decompressorRegistry2;
        return thisT();
    }

    public final T compressorRegistry(@Nullable CompressorRegistry compressorRegistry2) {
        if (compressorRegistry2 == null) {
            compressorRegistry2 = DEFAULT_COMPRESSOR_REGISTRY;
        }
        this.compressorRegistry = compressorRegistry2;
        return thisT();
    }

    public final T handshakeTimeout(long j, TimeUnit timeUnit) {
        Preconditions.checkArgument(j > 0, "handshake timeout is %s, but must be positive", j);
        this.handshakeTimeoutMillis = ((TimeUnit) Preconditions.checkNotNull(timeUnit, "unit")).toMillis(j);
        return thisT();
    }

    public final T setBinaryLog(@Nullable BinaryLog binaryLog) {
        this.binlog = binaryLog;
        return thisT();
    }

    public final T setTransportTracerFactory(TransportTracer.Factory factory) {
        this.transportTracerFactory = factory;
        return thisT();
    }

    /* access modifiers changed from: protected */
    public void setStatsEnabled(boolean z) {
        this.statsEnabled = z;
    }

    /* access modifiers changed from: protected */
    public void setStatsRecordStartedRpcs(boolean z) {
        this.recordStartedRpcs = z;
    }

    /* access modifiers changed from: protected */
    public void setStatsRecordFinishedRpcs(boolean z) {
        this.recordFinishedRpcs = z;
    }

    /* access modifiers changed from: protected */
    public void setStatsRecordRealTimeMetrics(boolean z) {
        this.recordRealTimeMetrics = z;
    }

    /* access modifiers changed from: protected */
    public void setTracingEnabled(boolean z) {
        this.tracingEnabled = z;
    }

    /* access modifiers changed from: protected */
    public void setDeadlineTicker(Deadline.Ticker ticker2) {
        this.ticker = (Deadline.Ticker) Preconditions.checkNotNull(ticker2, "ticker");
    }

    public final Server build() {
        ServerImpl serverImpl = new ServerImpl(this, buildTransportServers(getTracerFactories()), Context.ROOT);
        for (InternalNotifyOnServerBuild notifyOnBuild : this.notifyOnBuildList) {
            notifyOnBuild.notifyOnBuild(serverImpl);
        }
        return serverImpl;
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x0071  */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x0078  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.util.List<? extends p012io.grpc.ServerStreamTracer.Factory> getTracerFactories() {
        /*
            r11 = this;
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            boolean r1 = r11.statsEnabled
            java.lang.String r2 = "getServerStreamTracerFactory"
            r3 = 0
            r4 = 0
            java.lang.String r5 = "Unable to apply census stats"
            if (r1 == 0) goto L_0x0074
            java.lang.String r1 = "io.grpc.census.InternalCensusStatsAccessor"
            java.lang.Class r1 = java.lang.Class.forName(r1)     // Catch:{ ClassNotFoundException -> 0x0066, NoSuchMethodException -> 0x005d, IllegalAccessException -> 0x0054, InvocationTargetException -> 0x004b }
            r6 = 3
            java.lang.Class[] r7 = new java.lang.Class[r6]     // Catch:{ ClassNotFoundException -> 0x0066, NoSuchMethodException -> 0x005d, IllegalAccessException -> 0x0054, InvocationTargetException -> 0x004b }
            java.lang.Class r8 = java.lang.Boolean.TYPE     // Catch:{ ClassNotFoundException -> 0x0066, NoSuchMethodException -> 0x005d, IllegalAccessException -> 0x0054, InvocationTargetException -> 0x004b }
            r7[r3] = r8     // Catch:{ ClassNotFoundException -> 0x0066, NoSuchMethodException -> 0x005d, IllegalAccessException -> 0x0054, InvocationTargetException -> 0x004b }
            java.lang.Class r8 = java.lang.Boolean.TYPE     // Catch:{ ClassNotFoundException -> 0x0066, NoSuchMethodException -> 0x005d, IllegalAccessException -> 0x0054, InvocationTargetException -> 0x004b }
            r9 = 1
            r7[r9] = r8     // Catch:{ ClassNotFoundException -> 0x0066, NoSuchMethodException -> 0x005d, IllegalAccessException -> 0x0054, InvocationTargetException -> 0x004b }
            java.lang.Class r8 = java.lang.Boolean.TYPE     // Catch:{ ClassNotFoundException -> 0x0066, NoSuchMethodException -> 0x005d, IllegalAccessException -> 0x0054, InvocationTargetException -> 0x004b }
            r10 = 2
            r7[r10] = r8     // Catch:{ ClassNotFoundException -> 0x0066, NoSuchMethodException -> 0x005d, IllegalAccessException -> 0x0054, InvocationTargetException -> 0x004b }
            java.lang.reflect.Method r1 = r1.getDeclaredMethod(r2, r7)     // Catch:{ ClassNotFoundException -> 0x0066, NoSuchMethodException -> 0x005d, IllegalAccessException -> 0x0054, InvocationTargetException -> 0x004b }
            java.lang.Object[] r6 = new java.lang.Object[r6]     // Catch:{ ClassNotFoundException -> 0x0066, NoSuchMethodException -> 0x005d, IllegalAccessException -> 0x0054, InvocationTargetException -> 0x004b }
            boolean r7 = r11.recordStartedRpcs     // Catch:{ ClassNotFoundException -> 0x0066, NoSuchMethodException -> 0x005d, IllegalAccessException -> 0x0054, InvocationTargetException -> 0x004b }
            java.lang.Boolean r7 = java.lang.Boolean.valueOf(r7)     // Catch:{ ClassNotFoundException -> 0x0066, NoSuchMethodException -> 0x005d, IllegalAccessException -> 0x0054, InvocationTargetException -> 0x004b }
            r6[r3] = r7     // Catch:{ ClassNotFoundException -> 0x0066, NoSuchMethodException -> 0x005d, IllegalAccessException -> 0x0054, InvocationTargetException -> 0x004b }
            boolean r7 = r11.recordFinishedRpcs     // Catch:{ ClassNotFoundException -> 0x0066, NoSuchMethodException -> 0x005d, IllegalAccessException -> 0x0054, InvocationTargetException -> 0x004b }
            java.lang.Boolean r7 = java.lang.Boolean.valueOf(r7)     // Catch:{ ClassNotFoundException -> 0x0066, NoSuchMethodException -> 0x005d, IllegalAccessException -> 0x0054, InvocationTargetException -> 0x004b }
            r6[r9] = r7     // Catch:{ ClassNotFoundException -> 0x0066, NoSuchMethodException -> 0x005d, IllegalAccessException -> 0x0054, InvocationTargetException -> 0x004b }
            boolean r7 = r11.recordRealTimeMetrics     // Catch:{ ClassNotFoundException -> 0x0066, NoSuchMethodException -> 0x005d, IllegalAccessException -> 0x0054, InvocationTargetException -> 0x004b }
            java.lang.Boolean r7 = java.lang.Boolean.valueOf(r7)     // Catch:{ ClassNotFoundException -> 0x0066, NoSuchMethodException -> 0x005d, IllegalAccessException -> 0x0054, InvocationTargetException -> 0x004b }
            r6[r10] = r7     // Catch:{ ClassNotFoundException -> 0x0066, NoSuchMethodException -> 0x005d, IllegalAccessException -> 0x0054, InvocationTargetException -> 0x004b }
            java.lang.Object r1 = r1.invoke(r4, r6)     // Catch:{ ClassNotFoundException -> 0x0066, NoSuchMethodException -> 0x005d, IllegalAccessException -> 0x0054, InvocationTargetException -> 0x004b }
            io.grpc.ServerStreamTracer$Factory r1 = (p012io.grpc.ServerStreamTracer.Factory) r1     // Catch:{ ClassNotFoundException -> 0x0066, NoSuchMethodException -> 0x005d, IllegalAccessException -> 0x0054, InvocationTargetException -> 0x004b }
            goto L_0x006f
        L_0x004b:
            r1 = move-exception
            java.util.logging.Logger r6 = log
            java.util.logging.Level r7 = java.util.logging.Level.FINE
            r6.log(r7, r5, r1)
            goto L_0x006e
        L_0x0054:
            r1 = move-exception
            java.util.logging.Logger r6 = log
            java.util.logging.Level r7 = java.util.logging.Level.FINE
            r6.log(r7, r5, r1)
            goto L_0x006e
        L_0x005d:
            r1 = move-exception
            java.util.logging.Logger r6 = log
            java.util.logging.Level r7 = java.util.logging.Level.FINE
            r6.log(r7, r5, r1)
            goto L_0x006e
        L_0x0066:
            r1 = move-exception
            java.util.logging.Logger r6 = log
            java.util.logging.Level r7 = java.util.logging.Level.FINE
            r6.log(r7, r5, r1)
        L_0x006e:
            r1 = r4
        L_0x006f:
            if (r1 == 0) goto L_0x0074
            r0.add(r1)
        L_0x0074:
            boolean r1 = r11.tracingEnabled
            if (r1 == 0) goto L_0x00b6
            java.lang.String r1 = "io.grpc.census.InternalCensusTracingAccessor"
            java.lang.Class r1 = java.lang.Class.forName(r1)     // Catch:{ ClassNotFoundException -> 0x00a9, NoSuchMethodException -> 0x00a0, IllegalAccessException -> 0x0097, InvocationTargetException -> 0x008e }
            java.lang.Class[] r6 = new java.lang.Class[r3]     // Catch:{ ClassNotFoundException -> 0x00a9, NoSuchMethodException -> 0x00a0, IllegalAccessException -> 0x0097, InvocationTargetException -> 0x008e }
            java.lang.reflect.Method r1 = r1.getDeclaredMethod(r2, r6)     // Catch:{ ClassNotFoundException -> 0x00a9, NoSuchMethodException -> 0x00a0, IllegalAccessException -> 0x0097, InvocationTargetException -> 0x008e }
            java.lang.Object[] r2 = new java.lang.Object[r3]     // Catch:{ ClassNotFoundException -> 0x00a9, NoSuchMethodException -> 0x00a0, IllegalAccessException -> 0x0097, InvocationTargetException -> 0x008e }
            java.lang.Object r1 = r1.invoke(r4, r2)     // Catch:{ ClassNotFoundException -> 0x00a9, NoSuchMethodException -> 0x00a0, IllegalAccessException -> 0x0097, InvocationTargetException -> 0x008e }
            io.grpc.ServerStreamTracer$Factory r1 = (p012io.grpc.ServerStreamTracer.Factory) r1     // Catch:{ ClassNotFoundException -> 0x00a9, NoSuchMethodException -> 0x00a0, IllegalAccessException -> 0x0097, InvocationTargetException -> 0x008e }
            r4 = r1
            goto L_0x00b1
        L_0x008e:
            r1 = move-exception
            java.util.logging.Logger r2 = log
            java.util.logging.Level r3 = java.util.logging.Level.FINE
            r2.log(r3, r5, r1)
            goto L_0x00b1
        L_0x0097:
            r1 = move-exception
            java.util.logging.Logger r2 = log
            java.util.logging.Level r3 = java.util.logging.Level.FINE
            r2.log(r3, r5, r1)
            goto L_0x00b1
        L_0x00a0:
            r1 = move-exception
            java.util.logging.Logger r2 = log
            java.util.logging.Level r3 = java.util.logging.Level.FINE
            r2.log(r3, r5, r1)
            goto L_0x00b1
        L_0x00a9:
            r1 = move-exception
            java.util.logging.Logger r2 = log
            java.util.logging.Level r3 = java.util.logging.Level.FINE
            r2.log(r3, r5, r1)
        L_0x00b1:
            if (r4 == 0) goto L_0x00b6
            r0.add(r4)
        L_0x00b6:
            java.util.List<io.grpc.ServerStreamTracer$Factory> r1 = r11.streamTracerFactories
            r0.addAll(r1)
            r0.trimToSize()
            java.util.List r0 = java.util.Collections.unmodifiableList(r0)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: p012io.grpc.internal.AbstractServerImplBuilder.getTracerFactories():java.util.List");
    }

    /* access modifiers changed from: protected */
    public final InternalChannelz getChannelz() {
        return this.channelz;
    }

    /* access modifiers changed from: protected */
    public final TransportTracer.Factory getTransportTracerFactory() {
        return this.transportTracerFactory;
    }

    /* renamed from: io.grpc.internal.AbstractServerImplBuilder$DefaultFallbackRegistry */
    private static final class DefaultFallbackRegistry extends HandlerRegistry {
        @Nullable
        public ServerMethodDefinition<?, ?> lookupMethod(String str, @Nullable String str2) {
            return null;
        }

        private DefaultFallbackRegistry() {
        }

        public List<ServerServiceDefinition> getServices() {
            return Collections.emptyList();
        }
    }

    /* access modifiers changed from: protected */
    public ObjectPool<? extends Executor> getExecutorPool() {
        return this.executorPool;
    }
}

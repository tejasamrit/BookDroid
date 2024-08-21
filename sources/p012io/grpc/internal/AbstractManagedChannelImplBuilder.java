package p012io.grpc.internal;

import com.google.common.base.Preconditions;
import com.google.common.util.concurrent.MoreExecutors;
import java.net.SocketAddress;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;
import javax.annotation.Nullable;
import p012io.grpc.Attributes;
import p012io.grpc.BinaryLog;
import p012io.grpc.ClientInterceptor;
import p012io.grpc.CompressorRegistry;
import p012io.grpc.DecompressorRegistry;
import p012io.grpc.EquivalentAddressGroup;
import p012io.grpc.InternalChannelz;
import p012io.grpc.ManagedChannel;
import p012io.grpc.ManagedChannelBuilder;
import p012io.grpc.NameResolver;
import p012io.grpc.NameResolverRegistry;
import p012io.grpc.ProxyDetector;
import p012io.grpc.internal.AbstractManagedChannelImplBuilder;
import p012io.grpc.internal.ExponentialBackoffPolicy;
import p012io.grpc.internal.TransportTracer;

/* renamed from: io.grpc.internal.AbstractManagedChannelImplBuilder */
public abstract class AbstractManagedChannelImplBuilder<T extends AbstractManagedChannelImplBuilder<T>> extends ManagedChannelBuilder<T> {
    private static final CompressorRegistry DEFAULT_COMPRESSOR_REGISTRY = CompressorRegistry.getDefaultInstance();
    private static final DecompressorRegistry DEFAULT_DECOMPRESSOR_REGISTRY = DecompressorRegistry.getDefaultInstance();
    private static final ObjectPool<? extends Executor> DEFAULT_EXECUTOR_POOL = SharedResourcePool.forResource(GrpcUtil.SHARED_CHANNEL_EXECUTOR);
    private static final long DEFAULT_PER_RPC_BUFFER_LIMIT_IN_BYTES = 1048576;
    private static final long DEFAULT_RETRY_BUFFER_SIZE_IN_BYTES = 16777216;
    private static final String DIRECT_ADDRESS_SCHEME = "directaddress";
    static final long IDLE_MODE_DEFAULT_TIMEOUT_MILLIS = TimeUnit.MINUTES.toMillis(IDLE_MODE_MAX_TIMEOUT_DAYS);
    static final long IDLE_MODE_MAX_TIMEOUT_DAYS = 30;
    static final long IDLE_MODE_MIN_TIMEOUT_MILLIS = TimeUnit.SECONDS.toMillis(1);
    private static final Logger log = Logger.getLogger(AbstractManagedChannelImplBuilder.class.getName());
    @Nullable
    String authorityOverride;
    @Nullable
    BinaryLog binlog;
    InternalChannelz channelz;
    CompressorRegistry compressorRegistry;
    DecompressorRegistry decompressorRegistry;
    String defaultLbPolicy;
    @Nullable
    Map<String, ?> defaultServiceConfig;
    @Nullable
    private final SocketAddress directServerAddress;
    ObjectPool<? extends Executor> executorPool;
    boolean fullStreamDecompression;
    long idleTimeoutMillis;
    private final List<ClientInterceptor> interceptors = new ArrayList();
    boolean lookUpServiceConfig;
    int maxHedgedAttempts;
    private int maxInboundMessageSize;
    int maxRetryAttempts;
    int maxTraceEvents;
    private NameResolver.Factory nameResolverFactory;
    final NameResolverRegistry nameResolverRegistry;
    ObjectPool<? extends Executor> offloadExecutorPool;
    long perRpcBufferLimit;
    @Nullable
    ProxyDetector proxyDetector;
    private boolean recordFinishedRpcs;
    private boolean recordRealTimeMetrics;
    private boolean recordStartedRpcs;
    long retryBufferSize;
    boolean retryEnabled;
    private boolean statsEnabled;
    final String target;
    boolean temporarilyDisableRetry;
    private boolean tracingEnabled;
    protected TransportTracer.Factory transportTracerFactory;
    @Nullable
    String userAgent;

    private T thisT() {
        return this;
    }

    /* access modifiers changed from: protected */
    public abstract ClientTransportFactory buildTransportFactory();

    /* access modifiers changed from: protected */
    public int getDefaultPort() {
        return GrpcUtil.DEFAULT_PORT_SSL;
    }

    public static ManagedChannelBuilder<?> forAddress(String str, int i) {
        throw new UnsupportedOperationException("Subclass failed to hide static factory");
    }

    public static ManagedChannelBuilder<?> forTarget(String str) {
        throw new UnsupportedOperationException("Subclass failed to hide static factory");
    }

    public T maxInboundMessageSize(int i) {
        Preconditions.checkArgument(i >= 0, "negative max");
        this.maxInboundMessageSize = i;
        return thisT();
    }

    /* access modifiers changed from: protected */
    public final int maxInboundMessageSize() {
        return this.maxInboundMessageSize;
    }

    protected AbstractManagedChannelImplBuilder(String str) {
        ObjectPool<? extends Executor> objectPool = DEFAULT_EXECUTOR_POOL;
        this.executorPool = objectPool;
        this.offloadExecutorPool = objectPool;
        NameResolverRegistry defaultRegistry = NameResolverRegistry.getDefaultRegistry();
        this.nameResolverRegistry = defaultRegistry;
        this.nameResolverFactory = defaultRegistry.asFactory();
        this.defaultLbPolicy = GrpcUtil.DEFAULT_LB_POLICY;
        this.decompressorRegistry = DEFAULT_DECOMPRESSOR_REGISTRY;
        this.compressorRegistry = DEFAULT_COMPRESSOR_REGISTRY;
        this.idleTimeoutMillis = IDLE_MODE_DEFAULT_TIMEOUT_MILLIS;
        this.maxRetryAttempts = 5;
        this.maxHedgedAttempts = 5;
        this.retryBufferSize = DEFAULT_RETRY_BUFFER_SIZE_IN_BYTES;
        this.perRpcBufferLimit = 1048576;
        this.retryEnabled = false;
        this.channelz = InternalChannelz.instance();
        this.lookUpServiceConfig = true;
        this.transportTracerFactory = TransportTracer.getDefaultFactory();
        this.maxInboundMessageSize = 4194304;
        this.statsEnabled = true;
        this.recordStartedRpcs = true;
        this.recordFinishedRpcs = true;
        this.recordRealTimeMetrics = false;
        this.tracingEnabled = true;
        this.target = (String) Preconditions.checkNotNull(str, "target");
        this.directServerAddress = null;
    }

    static String makeTargetStringForDirectAddress(SocketAddress socketAddress) {
        try {
            return new URI(DIRECT_ADDRESS_SCHEME, "", "/" + socketAddress, (String) null).toString();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    protected AbstractManagedChannelImplBuilder(SocketAddress socketAddress, String str) {
        ObjectPool<? extends Executor> objectPool = DEFAULT_EXECUTOR_POOL;
        this.executorPool = objectPool;
        this.offloadExecutorPool = objectPool;
        NameResolverRegistry defaultRegistry = NameResolverRegistry.getDefaultRegistry();
        this.nameResolverRegistry = defaultRegistry;
        this.nameResolverFactory = defaultRegistry.asFactory();
        this.defaultLbPolicy = GrpcUtil.DEFAULT_LB_POLICY;
        this.decompressorRegistry = DEFAULT_DECOMPRESSOR_REGISTRY;
        this.compressorRegistry = DEFAULT_COMPRESSOR_REGISTRY;
        this.idleTimeoutMillis = IDLE_MODE_DEFAULT_TIMEOUT_MILLIS;
        this.maxRetryAttempts = 5;
        this.maxHedgedAttempts = 5;
        this.retryBufferSize = DEFAULT_RETRY_BUFFER_SIZE_IN_BYTES;
        this.perRpcBufferLimit = 1048576;
        this.retryEnabled = false;
        this.channelz = InternalChannelz.instance();
        this.lookUpServiceConfig = true;
        this.transportTracerFactory = TransportTracer.getDefaultFactory();
        this.maxInboundMessageSize = 4194304;
        this.statsEnabled = true;
        this.recordStartedRpcs = true;
        this.recordFinishedRpcs = true;
        this.recordRealTimeMetrics = false;
        this.tracingEnabled = true;
        this.target = makeTargetStringForDirectAddress(socketAddress);
        this.directServerAddress = socketAddress;
        this.nameResolverFactory = new DirectAddressNameResolverFactory(socketAddress, str);
    }

    public final T directExecutor() {
        return executor(MoreExecutors.directExecutor());
    }

    public final T executor(Executor executor) {
        if (executor != null) {
            this.executorPool = new FixedObjectPool(executor);
        } else {
            this.executorPool = DEFAULT_EXECUTOR_POOL;
        }
        return thisT();
    }

    public final T offloadExecutor(Executor executor) {
        if (executor != null) {
            this.offloadExecutorPool = new FixedObjectPool(executor);
        } else {
            this.offloadExecutorPool = DEFAULT_EXECUTOR_POOL;
        }
        return thisT();
    }

    public final T intercept(List<ClientInterceptor> list) {
        this.interceptors.addAll(list);
        return thisT();
    }

    public final T intercept(ClientInterceptor... clientInterceptorArr) {
        return intercept(Arrays.asList(clientInterceptorArr));
    }

    public final T nameResolverFactory(NameResolver.Factory factory) {
        SocketAddress socketAddress = this.directServerAddress;
        Preconditions.checkState(socketAddress == null, "directServerAddress is set (%s), which forbids the use of NameResolverFactory", (Object) socketAddress);
        if (factory != null) {
            this.nameResolverFactory = factory;
        } else {
            this.nameResolverFactory = this.nameResolverRegistry.asFactory();
        }
        return thisT();
    }

    public final T defaultLoadBalancingPolicy(String str) {
        SocketAddress socketAddress = this.directServerAddress;
        boolean z = true;
        Preconditions.checkState(socketAddress == null, "directServerAddress is set (%s), which forbids the use of load-balancing policy", (Object) socketAddress);
        if (str == null) {
            z = false;
        }
        Preconditions.checkArgument(z, "policy cannot be null");
        this.defaultLbPolicy = str;
        return thisT();
    }

    public final T enableFullStreamDecompression() {
        this.fullStreamDecompression = true;
        return thisT();
    }

    public final T decompressorRegistry(DecompressorRegistry decompressorRegistry2) {
        if (decompressorRegistry2 != null) {
            this.decompressorRegistry = decompressorRegistry2;
        } else {
            this.decompressorRegistry = DEFAULT_DECOMPRESSOR_REGISTRY;
        }
        return thisT();
    }

    public final T compressorRegistry(CompressorRegistry compressorRegistry2) {
        if (compressorRegistry2 != null) {
            this.compressorRegistry = compressorRegistry2;
        } else {
            this.compressorRegistry = DEFAULT_COMPRESSOR_REGISTRY;
        }
        return thisT();
    }

    public final T userAgent(@Nullable String str) {
        this.userAgent = str;
        return thisT();
    }

    public final T overrideAuthority(String str) {
        this.authorityOverride = checkAuthority(str);
        return thisT();
    }

    public final T idleTimeout(long j, TimeUnit timeUnit) {
        Preconditions.checkArgument(j > 0, "idle timeout is %s, but must be positive", j);
        if (timeUnit.toDays(j) >= IDLE_MODE_MAX_TIMEOUT_DAYS) {
            this.idleTimeoutMillis = -1;
        } else {
            this.idleTimeoutMillis = Math.max(timeUnit.toMillis(j), IDLE_MODE_MIN_TIMEOUT_MILLIS);
        }
        return thisT();
    }

    public final T maxRetryAttempts(int i) {
        this.maxRetryAttempts = i;
        return thisT();
    }

    public final T maxHedgedAttempts(int i) {
        this.maxHedgedAttempts = i;
        return thisT();
    }

    public final T retryBufferSize(long j) {
        Preconditions.checkArgument(j > 0, "retry buffer size must be positive");
        this.retryBufferSize = j;
        return thisT();
    }

    public final T perRpcBufferLimit(long j) {
        Preconditions.checkArgument(j > 0, "per RPC buffer limit must be positive");
        this.perRpcBufferLimit = j;
        return thisT();
    }

    public final T disableRetry() {
        this.retryEnabled = false;
        return thisT();
    }

    public final T enableRetry() {
        this.retryEnabled = true;
        this.statsEnabled = false;
        this.tracingEnabled = false;
        return thisT();
    }

    public final T setBinaryLog(BinaryLog binaryLog) {
        this.binlog = binaryLog;
        return thisT();
    }

    public T maxTraceEvents(int i) {
        Preconditions.checkArgument(i >= 0, "maxTraceEvents must be non-negative");
        this.maxTraceEvents = i;
        return thisT();
    }

    public T proxyDetector(@Nullable ProxyDetector proxyDetector2) {
        this.proxyDetector = proxyDetector2;
        return thisT();
    }

    public T defaultServiceConfig(@Nullable Map<String, ?> map) {
        this.defaultServiceConfig = checkMapEntryTypes(map);
        return thisT();
    }

    @Nullable
    private static Map<String, ?> checkMapEntryTypes(@Nullable Map<?, ?> map) {
        if (map == null) {
            return null;
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (Map.Entry next : map.entrySet()) {
            Preconditions.checkArgument(next.getKey() instanceof String, "The key of the entry '%s' is not of String type", (Object) next);
            String str = (String) next.getKey();
            Object value = next.getValue();
            if (value == null) {
                linkedHashMap.put(str, (Object) null);
            } else if (value instanceof Map) {
                linkedHashMap.put(str, checkMapEntryTypes((Map) value));
            } else if (value instanceof List) {
                linkedHashMap.put(str, checkListEntryTypes((List) value));
            } else if (value instanceof String) {
                linkedHashMap.put(str, value);
            } else if (value instanceof Double) {
                linkedHashMap.put(str, value);
            } else if (value instanceof Boolean) {
                linkedHashMap.put(str, value);
            } else {
                throw new IllegalArgumentException("The value of the map entry '" + next + "' is of type '" + value.getClass() + "', which is not supported");
            }
        }
        return Collections.unmodifiableMap(linkedHashMap);
    }

    private static List<?> checkListEntryTypes(List<?> list) {
        ArrayList arrayList = new ArrayList(list.size());
        for (Object next : list) {
            if (next == null) {
                arrayList.add((Object) null);
            } else if (next instanceof Map) {
                arrayList.add(checkMapEntryTypes((Map) next));
            } else if (next instanceof List) {
                arrayList.add(checkListEntryTypes((List) next));
            } else if (next instanceof String) {
                arrayList.add(next);
            } else if (next instanceof Double) {
                arrayList.add(next);
            } else if (next instanceof Boolean) {
                arrayList.add(next);
            } else {
                throw new IllegalArgumentException("The entry '" + next + "' is of type '" + next.getClass() + "', which is not supported");
            }
        }
        return Collections.unmodifiableList(arrayList);
    }

    public T disableServiceConfigLookUp() {
        this.lookUpServiceConfig = false;
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

    /* access modifiers changed from: package-private */
    public final long getIdleTimeoutMillis() {
        return this.idleTimeoutMillis;
    }

    /* access modifiers changed from: protected */
    public String checkAuthority(String str) {
        return GrpcUtil.checkAuthority(str);
    }

    public ManagedChannel build() {
        return new ManagedChannelOrphanWrapper(new ManagedChannelImpl(this, buildTransportFactory(), new ExponentialBackoffPolicy.Provider(), SharedResourcePool.forResource(GrpcUtil.SHARED_CHANNEL_EXECUTOR), GrpcUtil.STOPWATCH_SUPPLIER, getEffectiveInterceptors(), TimeProvider.SYSTEM_TIME_PROVIDER));
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x0077  */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x007e  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.util.List<p012io.grpc.ClientInterceptor> getEffectiveInterceptors() {
        /*
            r11 = this;
            java.util.ArrayList r0 = new java.util.ArrayList
            java.util.List<io.grpc.ClientInterceptor> r1 = r11.interceptors
            r0.<init>(r1)
            r1 = 0
            r11.temporarilyDisableRetry = r1
            boolean r2 = r11.statsEnabled
            java.lang.String r3 = "getClientInterceptor"
            r4 = 0
            r5 = 1
            java.lang.String r6 = "Unable to apply census stats"
            if (r2 == 0) goto L_0x007a
            r11.temporarilyDisableRetry = r5
            java.lang.String r2 = "io.grpc.census.InternalCensusStatsAccessor"
            java.lang.Class r2 = java.lang.Class.forName(r2)     // Catch:{ ClassNotFoundException -> 0x006c, NoSuchMethodException -> 0x0063, IllegalAccessException -> 0x005a, InvocationTargetException -> 0x0051 }
            r7 = 3
            java.lang.Class[] r8 = new java.lang.Class[r7]     // Catch:{ ClassNotFoundException -> 0x006c, NoSuchMethodException -> 0x0063, IllegalAccessException -> 0x005a, InvocationTargetException -> 0x0051 }
            java.lang.Class r9 = java.lang.Boolean.TYPE     // Catch:{ ClassNotFoundException -> 0x006c, NoSuchMethodException -> 0x0063, IllegalAccessException -> 0x005a, InvocationTargetException -> 0x0051 }
            r8[r1] = r9     // Catch:{ ClassNotFoundException -> 0x006c, NoSuchMethodException -> 0x0063, IllegalAccessException -> 0x005a, InvocationTargetException -> 0x0051 }
            java.lang.Class r9 = java.lang.Boolean.TYPE     // Catch:{ ClassNotFoundException -> 0x006c, NoSuchMethodException -> 0x0063, IllegalAccessException -> 0x005a, InvocationTargetException -> 0x0051 }
            r8[r5] = r9     // Catch:{ ClassNotFoundException -> 0x006c, NoSuchMethodException -> 0x0063, IllegalAccessException -> 0x005a, InvocationTargetException -> 0x0051 }
            java.lang.Class r9 = java.lang.Boolean.TYPE     // Catch:{ ClassNotFoundException -> 0x006c, NoSuchMethodException -> 0x0063, IllegalAccessException -> 0x005a, InvocationTargetException -> 0x0051 }
            r10 = 2
            r8[r10] = r9     // Catch:{ ClassNotFoundException -> 0x006c, NoSuchMethodException -> 0x0063, IllegalAccessException -> 0x005a, InvocationTargetException -> 0x0051 }
            java.lang.reflect.Method r2 = r2.getDeclaredMethod(r3, r8)     // Catch:{ ClassNotFoundException -> 0x006c, NoSuchMethodException -> 0x0063, IllegalAccessException -> 0x005a, InvocationTargetException -> 0x0051 }
            java.lang.Object[] r7 = new java.lang.Object[r7]     // Catch:{ ClassNotFoundException -> 0x006c, NoSuchMethodException -> 0x0063, IllegalAccessException -> 0x005a, InvocationTargetException -> 0x0051 }
            boolean r8 = r11.recordStartedRpcs     // Catch:{ ClassNotFoundException -> 0x006c, NoSuchMethodException -> 0x0063, IllegalAccessException -> 0x005a, InvocationTargetException -> 0x0051 }
            java.lang.Boolean r8 = java.lang.Boolean.valueOf(r8)     // Catch:{ ClassNotFoundException -> 0x006c, NoSuchMethodException -> 0x0063, IllegalAccessException -> 0x005a, InvocationTargetException -> 0x0051 }
            r7[r1] = r8     // Catch:{ ClassNotFoundException -> 0x006c, NoSuchMethodException -> 0x0063, IllegalAccessException -> 0x005a, InvocationTargetException -> 0x0051 }
            boolean r8 = r11.recordFinishedRpcs     // Catch:{ ClassNotFoundException -> 0x006c, NoSuchMethodException -> 0x0063, IllegalAccessException -> 0x005a, InvocationTargetException -> 0x0051 }
            java.lang.Boolean r8 = java.lang.Boolean.valueOf(r8)     // Catch:{ ClassNotFoundException -> 0x006c, NoSuchMethodException -> 0x0063, IllegalAccessException -> 0x005a, InvocationTargetException -> 0x0051 }
            r7[r5] = r8     // Catch:{ ClassNotFoundException -> 0x006c, NoSuchMethodException -> 0x0063, IllegalAccessException -> 0x005a, InvocationTargetException -> 0x0051 }
            boolean r8 = r11.recordRealTimeMetrics     // Catch:{ ClassNotFoundException -> 0x006c, NoSuchMethodException -> 0x0063, IllegalAccessException -> 0x005a, InvocationTargetException -> 0x0051 }
            java.lang.Boolean r8 = java.lang.Boolean.valueOf(r8)     // Catch:{ ClassNotFoundException -> 0x006c, NoSuchMethodException -> 0x0063, IllegalAccessException -> 0x005a, InvocationTargetException -> 0x0051 }
            r7[r10] = r8     // Catch:{ ClassNotFoundException -> 0x006c, NoSuchMethodException -> 0x0063, IllegalAccessException -> 0x005a, InvocationTargetException -> 0x0051 }
            java.lang.Object r2 = r2.invoke(r4, r7)     // Catch:{ ClassNotFoundException -> 0x006c, NoSuchMethodException -> 0x0063, IllegalAccessException -> 0x005a, InvocationTargetException -> 0x0051 }
            io.grpc.ClientInterceptor r2 = (p012io.grpc.ClientInterceptor) r2     // Catch:{ ClassNotFoundException -> 0x006c, NoSuchMethodException -> 0x0063, IllegalAccessException -> 0x005a, InvocationTargetException -> 0x0051 }
            goto L_0x0075
        L_0x0051:
            r2 = move-exception
            java.util.logging.Logger r7 = log
            java.util.logging.Level r8 = java.util.logging.Level.FINE
            r7.log(r8, r6, r2)
            goto L_0x0074
        L_0x005a:
            r2 = move-exception
            java.util.logging.Logger r7 = log
            java.util.logging.Level r8 = java.util.logging.Level.FINE
            r7.log(r8, r6, r2)
            goto L_0x0074
        L_0x0063:
            r2 = move-exception
            java.util.logging.Logger r7 = log
            java.util.logging.Level r8 = java.util.logging.Level.FINE
            r7.log(r8, r6, r2)
            goto L_0x0074
        L_0x006c:
            r2 = move-exception
            java.util.logging.Logger r7 = log
            java.util.logging.Level r8 = java.util.logging.Level.FINE
            r7.log(r8, r6, r2)
        L_0x0074:
            r2 = r4
        L_0x0075:
            if (r2 == 0) goto L_0x007a
            r0.add(r1, r2)
        L_0x007a:
            boolean r2 = r11.tracingEnabled
            if (r2 == 0) goto L_0x00be
            r11.temporarilyDisableRetry = r5
            java.lang.String r2 = "io.grpc.census.InternalCensusTracingAccessor"
            java.lang.Class r2 = java.lang.Class.forName(r2)     // Catch:{ ClassNotFoundException -> 0x00b1, NoSuchMethodException -> 0x00a8, IllegalAccessException -> 0x009f, InvocationTargetException -> 0x0096 }
            java.lang.Class[] r5 = new java.lang.Class[r1]     // Catch:{ ClassNotFoundException -> 0x00b1, NoSuchMethodException -> 0x00a8, IllegalAccessException -> 0x009f, InvocationTargetException -> 0x0096 }
            java.lang.reflect.Method r2 = r2.getDeclaredMethod(r3, r5)     // Catch:{ ClassNotFoundException -> 0x00b1, NoSuchMethodException -> 0x00a8, IllegalAccessException -> 0x009f, InvocationTargetException -> 0x0096 }
            java.lang.Object[] r3 = new java.lang.Object[r1]     // Catch:{ ClassNotFoundException -> 0x00b1, NoSuchMethodException -> 0x00a8, IllegalAccessException -> 0x009f, InvocationTargetException -> 0x0096 }
            java.lang.Object r2 = r2.invoke(r4, r3)     // Catch:{ ClassNotFoundException -> 0x00b1, NoSuchMethodException -> 0x00a8, IllegalAccessException -> 0x009f, InvocationTargetException -> 0x0096 }
            io.grpc.ClientInterceptor r2 = (p012io.grpc.ClientInterceptor) r2     // Catch:{ ClassNotFoundException -> 0x00b1, NoSuchMethodException -> 0x00a8, IllegalAccessException -> 0x009f, InvocationTargetException -> 0x0096 }
            r4 = r2
            goto L_0x00b9
        L_0x0096:
            r2 = move-exception
            java.util.logging.Logger r3 = log
            java.util.logging.Level r5 = java.util.logging.Level.FINE
            r3.log(r5, r6, r2)
            goto L_0x00b9
        L_0x009f:
            r2 = move-exception
            java.util.logging.Logger r3 = log
            java.util.logging.Level r5 = java.util.logging.Level.FINE
            r3.log(r5, r6, r2)
            goto L_0x00b9
        L_0x00a8:
            r2 = move-exception
            java.util.logging.Logger r3 = log
            java.util.logging.Level r5 = java.util.logging.Level.FINE
            r3.log(r5, r6, r2)
            goto L_0x00b9
        L_0x00b1:
            r2 = move-exception
            java.util.logging.Logger r3 = log
            java.util.logging.Level r5 = java.util.logging.Level.FINE
            r3.log(r5, r6, r2)
        L_0x00b9:
            if (r4 == 0) goto L_0x00be
            r0.add(r1, r4)
        L_0x00be:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: p012io.grpc.internal.AbstractManagedChannelImplBuilder.getEffectiveInterceptors():java.util.List");
    }

    /* access modifiers changed from: package-private */
    public NameResolver.Factory getNameResolverFactory() {
        String str = this.authorityOverride;
        if (str == null) {
            return this.nameResolverFactory;
        }
        return new OverrideAuthorityNameResolverFactory(this.nameResolverFactory, str);
    }

    /* renamed from: io.grpc.internal.AbstractManagedChannelImplBuilder$DirectAddressNameResolverFactory */
    private static class DirectAddressNameResolverFactory extends NameResolver.Factory {
        final SocketAddress address;
        final String authority;

        public String getDefaultScheme() {
            return AbstractManagedChannelImplBuilder.DIRECT_ADDRESS_SCHEME;
        }

        DirectAddressNameResolverFactory(SocketAddress socketAddress, String str) {
            this.address = socketAddress;
            this.authority = str;
        }

        public NameResolver newNameResolver(URI uri, NameResolver.Args args) {
            return new NameResolver() {
                public void shutdown() {
                }

                public String getServiceAuthority() {
                    return DirectAddressNameResolverFactory.this.authority;
                }

                public void start(NameResolver.Listener2 listener2) {
                    listener2.onResult(NameResolver.ResolutionResult.newBuilder().setAddresses(Collections.singletonList(new EquivalentAddressGroup(DirectAddressNameResolverFactory.this.address))).setAttributes(Attributes.EMPTY).build());
                }
            };
        }
    }

    /* access modifiers changed from: protected */
    public ObjectPool<? extends Executor> getOffloadExecutorPool() {
        return this.offloadExecutorPool;
    }
}

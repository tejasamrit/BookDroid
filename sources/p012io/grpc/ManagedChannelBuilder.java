package p012io.grpc;

import com.google.common.base.Preconditions;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nullable;
import p012io.grpc.ManagedChannelBuilder;
import p012io.grpc.NameResolver;

/* renamed from: io.grpc.ManagedChannelBuilder */
public abstract class ManagedChannelBuilder<T extends ManagedChannelBuilder<T>> {
    private T thisT() {
        return this;
    }

    public abstract ManagedChannel build();

    public abstract T compressorRegistry(CompressorRegistry compressorRegistry);

    public abstract T decompressorRegistry(DecompressorRegistry decompressorRegistry);

    public abstract T directExecutor();

    public abstract T executor(Executor executor);

    public abstract T idleTimeout(long j, TimeUnit timeUnit);

    public abstract T intercept(List<ClientInterceptor> list);

    public abstract T intercept(ClientInterceptor... clientInterceptorArr);

    public abstract T nameResolverFactory(NameResolver.Factory factory);

    public abstract T overrideAuthority(String str);

    public abstract T userAgent(String str);

    public static ManagedChannelBuilder<?> forAddress(String str, int i) {
        return ManagedChannelProvider.provider().builderForAddress(str, i);
    }

    public static ManagedChannelBuilder<?> forTarget(String str) {
        return ManagedChannelProvider.provider().builderForTarget(str);
    }

    public T offloadExecutor(Executor executor) {
        throw new UnsupportedOperationException();
    }

    @Deprecated
    public T blockingExecutor(Executor executor) {
        return offloadExecutor(executor);
    }

    public T usePlaintext() {
        throw new UnsupportedOperationException();
    }

    public T useTransportSecurity() {
        throw new UnsupportedOperationException();
    }

    public T defaultLoadBalancingPolicy(String str) {
        throw new UnsupportedOperationException();
    }

    public T enableFullStreamDecompression() {
        throw new UnsupportedOperationException();
    }

    public T maxInboundMessageSize(int i) {
        Preconditions.checkArgument(i >= 0, "bytes must be >= 0");
        return thisT();
    }

    public T maxInboundMetadataSize(int i) {
        Preconditions.checkArgument(i > 0, "maxInboundMetadataSize must be > 0");
        return thisT();
    }

    public T keepAliveTime(long j, TimeUnit timeUnit) {
        throw new UnsupportedOperationException();
    }

    public T keepAliveTimeout(long j, TimeUnit timeUnit) {
        throw new UnsupportedOperationException();
    }

    public T keepAliveWithoutCalls(boolean z) {
        throw new UnsupportedOperationException();
    }

    public T maxRetryAttempts(int i) {
        throw new UnsupportedOperationException();
    }

    public T maxHedgedAttempts(int i) {
        throw new UnsupportedOperationException();
    }

    public T retryBufferSize(long j) {
        throw new UnsupportedOperationException();
    }

    public T perRpcBufferLimit(long j) {
        throw new UnsupportedOperationException();
    }

    public T disableRetry() {
        throw new UnsupportedOperationException();
    }

    public T enableRetry() {
        throw new UnsupportedOperationException();
    }

    public T setBinaryLog(BinaryLog binaryLog) {
        throw new UnsupportedOperationException();
    }

    public T maxTraceEvents(int i) {
        throw new UnsupportedOperationException();
    }

    public T proxyDetector(ProxyDetector proxyDetector) {
        throw new UnsupportedOperationException();
    }

    public T defaultServiceConfig(@Nullable Map<String, ?> map) {
        throw new UnsupportedOperationException();
    }

    public T disableServiceConfigLookUp() {
        throw new UnsupportedOperationException();
    }
}

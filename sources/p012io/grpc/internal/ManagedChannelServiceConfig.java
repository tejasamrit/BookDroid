package p012io.grpc.internal;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Nullable;
import p012io.grpc.MethodDescriptor;
import p012io.grpc.internal.RetriableStream;

/* renamed from: io.grpc.internal.ManagedChannelServiceConfig */
final class ManagedChannelServiceConfig {
    @Nullable
    private final Object loadBalancingConfig;
    @Nullable
    private final RetriableStream.Throttle retryThrottling;
    private final Map<String, MethodInfo> serviceMap;
    private final Map<String, MethodInfo> serviceMethodMap;

    ManagedChannelServiceConfig(Map<String, MethodInfo> map, Map<String, MethodInfo> map2, @Nullable RetriableStream.Throttle throttle, @Nullable Object obj) {
        this.serviceMethodMap = Collections.unmodifiableMap(new HashMap(map));
        this.serviceMap = Collections.unmodifiableMap(new HashMap(map2));
        this.retryThrottling = throttle;
        this.loadBalancingConfig = obj;
    }

    static ManagedChannelServiceConfig empty() {
        return new ManagedChannelServiceConfig(new HashMap(), new HashMap(), (RetriableStream.Throttle) null, (Object) null);
    }

    static ManagedChannelServiceConfig fromServiceConfig(Map<String, ?> map, boolean z, int i, int i2, @Nullable Object obj) {
        RetriableStream.Throttle throttlePolicy = z ? ServiceConfigUtil.getThrottlePolicy(map) : null;
        HashMap hashMap = new HashMap();
        HashMap hashMap2 = new HashMap();
        List<Map<String, ?>> methodConfigFromServiceConfig = ServiceConfigUtil.getMethodConfigFromServiceConfig(map);
        if (methodConfigFromServiceConfig == null) {
            return new ManagedChannelServiceConfig(hashMap, hashMap2, throttlePolicy, obj);
        }
        for (Map next : methodConfigFromServiceConfig) {
            MethodInfo methodInfo = new MethodInfo(next, z, i, i2);
            List<Map<String, ?>> nameListFromMethodConfig = ServiceConfigUtil.getNameListFromMethodConfig(next);
            Preconditions.checkArgument(nameListFromMethodConfig != null && !nameListFromMethodConfig.isEmpty(), "no names in method config %s", (Object) next);
            for (Map next2 : nameListFromMethodConfig) {
                String serviceFromName = ServiceConfigUtil.getServiceFromName(next2);
                Preconditions.checkArgument(!Strings.isNullOrEmpty(serviceFromName), "missing service name");
                String methodFromName = ServiceConfigUtil.getMethodFromName(next2);
                if (Strings.isNullOrEmpty(methodFromName)) {
                    Preconditions.checkArgument(!hashMap2.containsKey(serviceFromName), "Duplicate service %s", (Object) serviceFromName);
                    hashMap2.put(serviceFromName, methodInfo);
                } else {
                    String generateFullMethodName = MethodDescriptor.generateFullMethodName(serviceFromName, methodFromName);
                    Preconditions.checkArgument(!hashMap.containsKey(generateFullMethodName), "Duplicate method name %s", (Object) generateFullMethodName);
                    hashMap.put(generateFullMethodName, methodInfo);
                }
            }
        }
        return new ManagedChannelServiceConfig(hashMap, hashMap2, throttlePolicy, obj);
    }

    /* access modifiers changed from: package-private */
    public Map<String, MethodInfo> getServiceMap() {
        return this.serviceMap;
    }

    /* access modifiers changed from: package-private */
    public Map<String, MethodInfo> getServiceMethodMap() {
        return this.serviceMethodMap;
    }

    /* access modifiers changed from: package-private */
    @Nullable
    public Object getLoadBalancingConfig() {
        return this.loadBalancingConfig;
    }

    /* access modifiers changed from: package-private */
    @Nullable
    public RetriableStream.Throttle getRetryThrottling() {
        return this.retryThrottling;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        ManagedChannelServiceConfig managedChannelServiceConfig = (ManagedChannelServiceConfig) obj;
        if (!Objects.equal(this.serviceMethodMap, managedChannelServiceConfig.serviceMethodMap) || !Objects.equal(this.serviceMap, managedChannelServiceConfig.serviceMap) || !Objects.equal(this.retryThrottling, managedChannelServiceConfig.retryThrottling) || !Objects.equal(this.loadBalancingConfig, managedChannelServiceConfig.loadBalancingConfig)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return Objects.hashCode(this.serviceMethodMap, this.serviceMap, this.retryThrottling, this.loadBalancingConfig);
    }

    public String toString() {
        return MoreObjects.toStringHelper((Object) this).add("serviceMethodMap", (Object) this.serviceMethodMap).add("serviceMap", (Object) this.serviceMap).add("retryThrottling", (Object) this.retryThrottling).add("loadBalancingConfig", this.loadBalancingConfig).toString();
    }

    /* renamed from: io.grpc.internal.ManagedChannelServiceConfig$MethodInfo */
    static final class MethodInfo {
        final HedgingPolicy hedgingPolicy;
        final Integer maxInboundMessageSize;
        final Integer maxOutboundMessageSize;
        final RetryPolicy retryPolicy;
        final Long timeoutNanos;
        final Boolean waitForReady;

        MethodInfo(Map<String, ?> map, boolean z, int i, int i2) {
            this.timeoutNanos = ServiceConfigUtil.getTimeoutFromMethodConfig(map);
            this.waitForReady = ServiceConfigUtil.getWaitForReadyFromMethodConfig(map);
            Integer maxResponseMessageBytesFromMethodConfig = ServiceConfigUtil.getMaxResponseMessageBytesFromMethodConfig(map);
            this.maxInboundMessageSize = maxResponseMessageBytesFromMethodConfig;
            boolean z2 = true;
            if (maxResponseMessageBytesFromMethodConfig != null) {
                Preconditions.checkArgument(maxResponseMessageBytesFromMethodConfig.intValue() >= 0, "maxInboundMessageSize %s exceeds bounds", (Object) maxResponseMessageBytesFromMethodConfig);
            }
            Integer maxRequestMessageBytesFromMethodConfig = ServiceConfigUtil.getMaxRequestMessageBytesFromMethodConfig(map);
            this.maxOutboundMessageSize = maxRequestMessageBytesFromMethodConfig;
            if (maxRequestMessageBytesFromMethodConfig != null) {
                Preconditions.checkArgument(maxRequestMessageBytesFromMethodConfig.intValue() < 0 ? false : z2, "maxOutboundMessageSize %s exceeds bounds", (Object) maxRequestMessageBytesFromMethodConfig);
            }
            Map<String, ?> map2 = null;
            Map<String, ?> retryPolicyFromMethodConfig = z ? ServiceConfigUtil.getRetryPolicyFromMethodConfig(map) : null;
            this.retryPolicy = retryPolicyFromMethodConfig == null ? RetryPolicy.DEFAULT : retryPolicy(retryPolicyFromMethodConfig, i);
            map2 = z ? ServiceConfigUtil.getHedgingPolicyFromMethodConfig(map) : map2;
            this.hedgingPolicy = map2 == null ? HedgingPolicy.DEFAULT : hedgingPolicy(map2, i2);
        }

        public int hashCode() {
            return Objects.hashCode(this.timeoutNanos, this.waitForReady, this.maxInboundMessageSize, this.maxOutboundMessageSize, this.retryPolicy, this.hedgingPolicy);
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof MethodInfo)) {
                return false;
            }
            MethodInfo methodInfo = (MethodInfo) obj;
            if (!Objects.equal(this.timeoutNanos, methodInfo.timeoutNanos) || !Objects.equal(this.waitForReady, methodInfo.waitForReady) || !Objects.equal(this.maxInboundMessageSize, methodInfo.maxInboundMessageSize) || !Objects.equal(this.maxOutboundMessageSize, methodInfo.maxOutboundMessageSize) || !Objects.equal(this.retryPolicy, methodInfo.retryPolicy) || !Objects.equal(this.hedgingPolicy, methodInfo.hedgingPolicy)) {
                return false;
            }
            return true;
        }

        public String toString() {
            return MoreObjects.toStringHelper((Object) this).add("timeoutNanos", (Object) this.timeoutNanos).add("waitForReady", (Object) this.waitForReady).add("maxInboundMessageSize", (Object) this.maxInboundMessageSize).add("maxOutboundMessageSize", (Object) this.maxOutboundMessageSize).add("retryPolicy", (Object) this.retryPolicy).add("hedgingPolicy", (Object) this.hedgingPolicy).toString();
        }

        private static RetryPolicy retryPolicy(Map<String, ?> map, int i) {
            int intValue = ((Integer) Preconditions.checkNotNull(ServiceConfigUtil.getMaxAttemptsFromRetryPolicy(map), "maxAttempts cannot be empty")).intValue();
            boolean z = true;
            Preconditions.checkArgument(intValue >= 2, "maxAttempts must be greater than 1: %s", intValue);
            int min = Math.min(intValue, i);
            long longValue = ((Long) Preconditions.checkNotNull(ServiceConfigUtil.getInitialBackoffNanosFromRetryPolicy(map), "initialBackoff cannot be empty")).longValue();
            Preconditions.checkArgument(longValue > 0, "initialBackoffNanos must be greater than 0: %s", longValue);
            long longValue2 = ((Long) Preconditions.checkNotNull(ServiceConfigUtil.getMaxBackoffNanosFromRetryPolicy(map), "maxBackoff cannot be empty")).longValue();
            Preconditions.checkArgument(longValue2 > 0, "maxBackoff must be greater than 0: %s", longValue2);
            double doubleValue = ((Double) Preconditions.checkNotNull(ServiceConfigUtil.getBackoffMultiplierFromRetryPolicy(map), "backoffMultiplier cannot be empty")).doubleValue();
            if (doubleValue <= 0.0d) {
                z = false;
            }
            Preconditions.checkArgument(z, "backoffMultiplier must be greater than 0: %s", (Object) Double.valueOf(doubleValue));
            return new RetryPolicy(min, longValue, longValue2, doubleValue, ServiceConfigUtil.getRetryableStatusCodesFromRetryPolicy(map));
        }

        private static HedgingPolicy hedgingPolicy(Map<String, ?> map, int i) {
            int intValue = ((Integer) Preconditions.checkNotNull(ServiceConfigUtil.getMaxAttemptsFromHedgingPolicy(map), "maxAttempts cannot be empty")).intValue();
            boolean z = true;
            Preconditions.checkArgument(intValue >= 2, "maxAttempts must be greater than 1: %s", intValue);
            int min = Math.min(intValue, i);
            long longValue = ((Long) Preconditions.checkNotNull(ServiceConfigUtil.getHedgingDelayNanosFromHedgingPolicy(map), "hedgingDelay cannot be empty")).longValue();
            if (longValue < 0) {
                z = false;
            }
            Preconditions.checkArgument(z, "hedgingDelay must not be negative: %s", longValue);
            return new HedgingPolicy(min, longValue, ServiceConfigUtil.getNonFatalStatusCodesFromHedgingPolicy(map));
        }
    }
}

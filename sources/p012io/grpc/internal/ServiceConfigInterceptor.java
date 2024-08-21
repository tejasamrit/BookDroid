package p012io.grpc.internal;

import com.google.common.base.Verify;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import javax.annotation.CheckForNull;
import javax.annotation.Nullable;
import p012io.grpc.CallOptions;
import p012io.grpc.Channel;
import p012io.grpc.ClientCall;
import p012io.grpc.ClientInterceptor;
import p012io.grpc.Deadline;
import p012io.grpc.MethodDescriptor;
import p012io.grpc.internal.HedgingPolicy;
import p012io.grpc.internal.ManagedChannelServiceConfig;
import p012io.grpc.internal.RetryPolicy;

/* renamed from: io.grpc.internal.ServiceConfigInterceptor */
final class ServiceConfigInterceptor implements ClientInterceptor {
    static final CallOptions.Key<HedgingPolicy.Provider> HEDGING_POLICY_KEY = CallOptions.Key.create("internal-hedging-policy");
    static final CallOptions.Key<RetryPolicy.Provider> RETRY_POLICY_KEY = CallOptions.Key.create("internal-retry-policy");
    /* access modifiers changed from: private */
    public volatile boolean initComplete;
    final AtomicReference<ManagedChannelServiceConfig> managedChannelServiceConfig = new AtomicReference<>();
    private final boolean retryEnabled;

    ServiceConfigInterceptor(boolean z) {
        this.retryEnabled = z;
    }

    /* access modifiers changed from: package-private */
    public void handleUpdate(@Nullable ManagedChannelServiceConfig managedChannelServiceConfig2) {
        this.managedChannelServiceConfig.set(managedChannelServiceConfig2);
        this.initComplete = true;
    }

    public <ReqT, RespT> ClientCall<ReqT, RespT> interceptCall(final MethodDescriptor<ReqT, RespT> methodDescriptor, CallOptions callOptions, Channel channel) {
        if (this.retryEnabled) {
            if (this.initComplete) {
                final RetryPolicy retryPolicyFromConfig = getRetryPolicyFromConfig(methodDescriptor);
                final HedgingPolicy hedgingPolicyFromConfig = getHedgingPolicyFromConfig(methodDescriptor);
                Verify.verify(retryPolicyFromConfig.equals(RetryPolicy.DEFAULT) || hedgingPolicyFromConfig.equals(HedgingPolicy.DEFAULT), "Can not apply both retry and hedging policy for the method '%s'", (Object) methodDescriptor);
                callOptions = callOptions.withOption(RETRY_POLICY_KEY, new RetryPolicy.Provider() {
                    public RetryPolicy get() {
                        return retryPolicyFromConfig;
                    }
                }).withOption(HEDGING_POLICY_KEY, new HedgingPolicy.Provider() {
                    public HedgingPolicy get() {
                        return hedgingPolicyFromConfig;
                    }
                });
            } else {
                callOptions = callOptions.withOption(RETRY_POLICY_KEY, new RetryPolicy.Provider() {
                    public RetryPolicy get() {
                        if (!ServiceConfigInterceptor.this.initComplete) {
                            return RetryPolicy.DEFAULT;
                        }
                        return ServiceConfigInterceptor.this.getRetryPolicyFromConfig(methodDescriptor);
                    }
                }).withOption(HEDGING_POLICY_KEY, new HedgingPolicy.Provider() {
                    public HedgingPolicy get() {
                        if (!ServiceConfigInterceptor.this.initComplete) {
                            return HedgingPolicy.DEFAULT;
                        }
                        HedgingPolicy hedgingPolicyFromConfig = ServiceConfigInterceptor.this.getHedgingPolicyFromConfig(methodDescriptor);
                        Verify.verify(hedgingPolicyFromConfig.equals(HedgingPolicy.DEFAULT) || ServiceConfigInterceptor.this.getRetryPolicyFromConfig(methodDescriptor).equals(RetryPolicy.DEFAULT), "Can not apply both retry and hedging policy for the method '%s'", (Object) methodDescriptor);
                        return hedgingPolicyFromConfig;
                    }
                });
            }
        }
        ManagedChannelServiceConfig.MethodInfo methodInfo = getMethodInfo(methodDescriptor);
        if (methodInfo == null) {
            return channel.newCall(methodDescriptor, callOptions);
        }
        if (methodInfo.timeoutNanos != null) {
            Deadline after = Deadline.after(methodInfo.timeoutNanos.longValue(), TimeUnit.NANOSECONDS);
            Deadline deadline = callOptions.getDeadline();
            if (deadline == null || after.compareTo(deadline) < 0) {
                callOptions = callOptions.withDeadline(after);
            }
        }
        if (methodInfo.waitForReady != null) {
            callOptions = methodInfo.waitForReady.booleanValue() ? callOptions.withWaitForReady() : callOptions.withoutWaitForReady();
        }
        if (methodInfo.maxInboundMessageSize != null) {
            Integer maxInboundMessageSize = callOptions.getMaxInboundMessageSize();
            if (maxInboundMessageSize != null) {
                callOptions = callOptions.withMaxInboundMessageSize(Math.min(maxInboundMessageSize.intValue(), methodInfo.maxInboundMessageSize.intValue()));
            } else {
                callOptions = callOptions.withMaxInboundMessageSize(methodInfo.maxInboundMessageSize.intValue());
            }
        }
        if (methodInfo.maxOutboundMessageSize != null) {
            Integer maxOutboundMessageSize = callOptions.getMaxOutboundMessageSize();
            if (maxOutboundMessageSize != null) {
                callOptions = callOptions.withMaxOutboundMessageSize(Math.min(maxOutboundMessageSize.intValue(), methodInfo.maxOutboundMessageSize.intValue()));
            } else {
                callOptions = callOptions.withMaxOutboundMessageSize(methodInfo.maxOutboundMessageSize.intValue());
            }
        }
        return channel.newCall(methodDescriptor, callOptions);
    }

    @CheckForNull
    private ManagedChannelServiceConfig.MethodInfo getMethodInfo(MethodDescriptor<?, ?> methodDescriptor) {
        ManagedChannelServiceConfig managedChannelServiceConfig2 = this.managedChannelServiceConfig.get();
        ManagedChannelServiceConfig.MethodInfo methodInfo = managedChannelServiceConfig2 != null ? managedChannelServiceConfig2.getServiceMethodMap().get(methodDescriptor.getFullMethodName()) : null;
        if (methodInfo != null || managedChannelServiceConfig2 == null) {
            return methodInfo;
        }
        return managedChannelServiceConfig2.getServiceMap().get(methodDescriptor.getServiceName());
    }

    /* access modifiers changed from: package-private */
    public RetryPolicy getRetryPolicyFromConfig(MethodDescriptor<?, ?> methodDescriptor) {
        ManagedChannelServiceConfig.MethodInfo methodInfo = getMethodInfo(methodDescriptor);
        return methodInfo == null ? RetryPolicy.DEFAULT : methodInfo.retryPolicy;
    }

    /* access modifiers changed from: package-private */
    public HedgingPolicy getHedgingPolicyFromConfig(MethodDescriptor<?, ?> methodDescriptor) {
        ManagedChannelServiceConfig.MethodInfo methodInfo = getMethodInfo(methodDescriptor);
        return methodInfo == null ? HedgingPolicy.DEFAULT : methodInfo.hedgingPolicy;
    }
}

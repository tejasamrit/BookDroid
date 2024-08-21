package p012io.grpc.internal;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.annotation.Nullable;
import p012io.grpc.Attributes;
import p012io.grpc.ChannelLogger;
import p012io.grpc.ConnectivityState;
import p012io.grpc.ConnectivityStateInfo;
import p012io.grpc.EquivalentAddressGroup;
import p012io.grpc.LoadBalancer;
import p012io.grpc.LoadBalancerProvider;
import p012io.grpc.LoadBalancerRegistry;
import p012io.grpc.NameResolver;
import p012io.grpc.Status;
import p012io.grpc.internal.ServiceConfigUtil;

/* renamed from: io.grpc.internal.AutoConfiguredLoadBalancerFactory */
public final class AutoConfiguredLoadBalancerFactory {
    /* access modifiers changed from: private */
    public final String defaultPolicy;
    /* access modifiers changed from: private */
    public final LoadBalancerRegistry registry;

    public AutoConfiguredLoadBalancerFactory(String str) {
        this(LoadBalancerRegistry.getDefaultRegistry(), str);
    }

    AutoConfiguredLoadBalancerFactory(LoadBalancerRegistry loadBalancerRegistry, String str) {
        this.registry = (LoadBalancerRegistry) Preconditions.checkNotNull(loadBalancerRegistry, "registry");
        this.defaultPolicy = (String) Preconditions.checkNotNull(str, "defaultPolicy");
    }

    public AutoConfiguredLoadBalancer newLoadBalancer(LoadBalancer.Helper helper) {
        return new AutoConfiguredLoadBalancer(helper);
    }

    /* renamed from: io.grpc.internal.AutoConfiguredLoadBalancerFactory$NoopLoadBalancer */
    private static final class NoopLoadBalancer extends LoadBalancer {
        public void handleNameResolutionError(Status status) {
        }

        @Deprecated
        public void handleResolvedAddressGroups(List<EquivalentAddressGroup> list, Attributes attributes) {
        }

        public void handleResolvedAddresses(LoadBalancer.ResolvedAddresses resolvedAddresses) {
        }

        public void shutdown() {
        }

        private NoopLoadBalancer() {
        }
    }

    /* renamed from: io.grpc.internal.AutoConfiguredLoadBalancerFactory$AutoConfiguredLoadBalancer */
    public final class AutoConfiguredLoadBalancer {
        private LoadBalancer delegate;
        private LoadBalancerProvider delegateProvider;
        private final LoadBalancer.Helper helper;

        AutoConfiguredLoadBalancer(LoadBalancer.Helper helper2) {
            this.helper = helper2;
            LoadBalancerProvider provider = AutoConfiguredLoadBalancerFactory.this.registry.getProvider(AutoConfiguredLoadBalancerFactory.this.defaultPolicy);
            this.delegateProvider = provider;
            if (provider != null) {
                this.delegate = provider.newLoadBalancer(helper2);
                return;
            }
            throw new IllegalStateException("Could not find policy '" + AutoConfiguredLoadBalancerFactory.this.defaultPolicy + "'. Make sure its implementation is either registered to LoadBalancerRegistry or included in META-INF/services/io.grpc.LoadBalancerProvider from your jar files.");
        }

        public void handleResolvedAddresses(LoadBalancer.ResolvedAddresses resolvedAddresses) {
            tryHandleResolvedAddresses(resolvedAddresses);
        }

        /* access modifiers changed from: package-private */
        public Status tryHandleResolvedAddresses(LoadBalancer.ResolvedAddresses resolvedAddresses) {
            List<EquivalentAddressGroup> addresses = resolvedAddresses.getAddresses();
            Attributes attributes = resolvedAddresses.getAttributes();
            if (attributes.get(LoadBalancer.ATTR_LOAD_BALANCING_CONFIG) == null) {
                PolicySelection policySelection = (PolicySelection) resolvedAddresses.getLoadBalancingPolicyConfig();
                if (policySelection == null) {
                    try {
                        AutoConfiguredLoadBalancerFactory autoConfiguredLoadBalancerFactory = AutoConfiguredLoadBalancerFactory.this;
                        policySelection = new PolicySelection(autoConfiguredLoadBalancerFactory.getProviderOrThrow(autoConfiguredLoadBalancerFactory.defaultPolicy, "using default policy"), (Map<String, ?>) null, (Object) null);
                    } catch (PolicyException e) {
                        this.helper.updateBalancingState(ConnectivityState.TRANSIENT_FAILURE, new FailingPicker(Status.INTERNAL.withDescription(e.getMessage())));
                        this.delegate.shutdown();
                        this.delegateProvider = null;
                        this.delegate = new NoopLoadBalancer();
                        return Status.f489OK;
                    }
                }
                if (this.delegateProvider == null || !policySelection.provider.getPolicyName().equals(this.delegateProvider.getPolicyName())) {
                    this.helper.updateBalancingState(ConnectivityState.CONNECTING, new EmptyPicker());
                    this.delegate.shutdown();
                    LoadBalancerProvider loadBalancerProvider = policySelection.provider;
                    this.delegateProvider = loadBalancerProvider;
                    LoadBalancer loadBalancer = this.delegate;
                    this.delegate = loadBalancerProvider.newLoadBalancer(this.helper);
                    this.helper.getChannelLogger().log(ChannelLogger.ChannelLogLevel.INFO, "Load balancer changed from {0} to {1}", loadBalancer.getClass().getSimpleName(), this.delegate.getClass().getSimpleName());
                }
                Object obj = policySelection.config;
                if (obj != null) {
                    this.helper.getChannelLogger().log(ChannelLogger.ChannelLogLevel.DEBUG, "Load-balancing config: {0}", policySelection.config);
                    attributes = attributes.toBuilder().set(LoadBalancer.ATTR_LOAD_BALANCING_CONFIG, policySelection.rawConfig).build();
                }
                LoadBalancer delegate2 = getDelegate();
                if (!resolvedAddresses.getAddresses().isEmpty() || delegate2.canHandleEmptyAddressListFromNameResolution()) {
                    delegate2.handleResolvedAddresses(LoadBalancer.ResolvedAddresses.newBuilder().setAddresses(resolvedAddresses.getAddresses()).setAttributes(attributes).setLoadBalancingPolicyConfig(obj).build());
                    return Status.f489OK;
                }
                Status status = Status.UNAVAILABLE;
                return status.withDescription("NameResolver returned no usable address. addrs=" + addresses + ", attrs=" + attributes);
            }
            throw new IllegalArgumentException("Unexpected ATTR_LOAD_BALANCING_CONFIG from upstream: " + attributes.get(LoadBalancer.ATTR_LOAD_BALANCING_CONFIG));
        }

        /* access modifiers changed from: package-private */
        public void handleNameResolutionError(Status status) {
            getDelegate().handleNameResolutionError(status);
        }

        /* access modifiers changed from: package-private */
        @Deprecated
        public void handleSubchannelState(LoadBalancer.Subchannel subchannel, ConnectivityStateInfo connectivityStateInfo) {
            getDelegate().handleSubchannelState(subchannel, connectivityStateInfo);
        }

        /* access modifiers changed from: package-private */
        public void requestConnection() {
            getDelegate().requestConnection();
        }

        /* access modifiers changed from: package-private */
        public void shutdown() {
            this.delegate.shutdown();
            this.delegate = null;
        }

        public LoadBalancer getDelegate() {
            return this.delegate;
        }

        /* access modifiers changed from: package-private */
        public void setDelegate(LoadBalancer loadBalancer) {
            this.delegate = loadBalancer;
        }

        /* access modifiers changed from: package-private */
        public LoadBalancerProvider getDelegateProvider() {
            return this.delegateProvider;
        }
    }

    /* access modifiers changed from: private */
    public LoadBalancerProvider getProviderOrThrow(String str, String str2) throws PolicyException {
        LoadBalancerProvider provider = this.registry.getProvider(str);
        if (provider != null) {
            return provider;
        }
        throw new PolicyException("Trying to load '" + str + "' because " + str2 + ", but it's unavailable");
    }

    /* access modifiers changed from: package-private */
    @Nullable
    public NameResolver.ConfigOrError parseLoadBalancerPolicy(Map<String, ?> map, ChannelLogger channelLogger) {
        List<ServiceConfigUtil.LbConfig> list;
        if (map != null) {
            try {
                list = ServiceConfigUtil.unwrapLoadBalancingConfigList(ServiceConfigUtil.getLoadBalancingConfigsFromServiceConfig(map));
            } catch (RuntimeException e) {
                return NameResolver.ConfigOrError.fromError(Status.UNKNOWN.withDescription("can't parse load balancer configuration").withCause(e));
            }
        } else {
            list = null;
        }
        if (list == null || list.isEmpty()) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (ServiceConfigUtil.LbConfig next : list) {
            String policyName = next.getPolicyName();
            LoadBalancerProvider provider = this.registry.getProvider(policyName);
            if (provider == null) {
                arrayList.add(policyName);
            } else {
                if (!arrayList.isEmpty()) {
                    channelLogger.log(ChannelLogger.ChannelLogLevel.DEBUG, "{0} specified by Service Config are not available", arrayList);
                }
                NameResolver.ConfigOrError parseLoadBalancingPolicyConfig = provider.parseLoadBalancingPolicyConfig(next.getRawConfigValue());
                if (parseLoadBalancingPolicyConfig.getError() != null) {
                    return parseLoadBalancingPolicyConfig;
                }
                return NameResolver.ConfigOrError.fromConfig(new PolicySelection(provider, next.getRawConfigValue(), parseLoadBalancingPolicyConfig.getConfig()));
            }
        }
        return NameResolver.ConfigOrError.fromError(Status.UNKNOWN.withDescription("None of " + arrayList + " specified by Service Config are available."));
    }

    /* renamed from: io.grpc.internal.AutoConfiguredLoadBalancerFactory$PolicyException */
    static final class PolicyException extends Exception {
        private static final long serialVersionUID = 1;

        private PolicyException(String str) {
            super(str);
        }
    }

    /* renamed from: io.grpc.internal.AutoConfiguredLoadBalancerFactory$PolicySelection */
    static final class PolicySelection {
        @Nullable
        final Object config;
        final LoadBalancerProvider provider;
        @Nullable
        final Map<String, ?> rawConfig;

        PolicySelection(LoadBalancerProvider loadBalancerProvider, @Nullable Map<String, ?> map, @Nullable Object obj) {
            this.provider = (LoadBalancerProvider) Preconditions.checkNotNull(loadBalancerProvider, "provider");
            this.rawConfig = map;
            this.config = obj;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            PolicySelection policySelection = (PolicySelection) obj;
            if (!Objects.equal(this.provider, policySelection.provider) || !Objects.equal(this.rawConfig, policySelection.rawConfig) || !Objects.equal(this.config, policySelection.config)) {
                return false;
            }
            return true;
        }

        public int hashCode() {
            return Objects.hashCode(this.provider, this.rawConfig, this.config);
        }

        public String toString() {
            return MoreObjects.toStringHelper((Object) this).add("provider", (Object) this.provider).add("rawConfig", (Object) this.rawConfig).add("config", this.config).toString();
        }
    }

    /* renamed from: io.grpc.internal.AutoConfiguredLoadBalancerFactory$EmptyPicker */
    private static final class EmptyPicker extends LoadBalancer.SubchannelPicker {
        private EmptyPicker() {
        }

        public LoadBalancer.PickResult pickSubchannel(LoadBalancer.PickSubchannelArgs pickSubchannelArgs) {
            return LoadBalancer.PickResult.withNoResult();
        }

        public String toString() {
            return MoreObjects.toStringHelper((Class<?>) EmptyPicker.class).toString();
        }
    }

    /* renamed from: io.grpc.internal.AutoConfiguredLoadBalancerFactory$FailingPicker */
    private static final class FailingPicker extends LoadBalancer.SubchannelPicker {
        private final Status failure;

        FailingPicker(Status status) {
            this.failure = status;
        }

        public LoadBalancer.PickResult pickSubchannel(LoadBalancer.PickSubchannelArgs pickSubchannelArgs) {
            return LoadBalancer.PickResult.withError(this.failure);
        }
    }
}

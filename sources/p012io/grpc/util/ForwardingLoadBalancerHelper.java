package p012io.grpc.util;

import com.google.common.base.MoreObjects;
import java.util.List;
import java.util.concurrent.ScheduledExecutorService;
import p012io.grpc.Attributes;
import p012io.grpc.ChannelLogger;
import p012io.grpc.ConnectivityState;
import p012io.grpc.EquivalentAddressGroup;
import p012io.grpc.LoadBalancer;
import p012io.grpc.ManagedChannel;
import p012io.grpc.NameResolver;
import p012io.grpc.NameResolverRegistry;
import p012io.grpc.SynchronizationContext;

/* renamed from: io.grpc.util.ForwardingLoadBalancerHelper */
public abstract class ForwardingLoadBalancerHelper extends LoadBalancer.Helper {
    /* access modifiers changed from: protected */
    public abstract LoadBalancer.Helper delegate();

    @Deprecated
    public LoadBalancer.Subchannel createSubchannel(List<EquivalentAddressGroup> list, Attributes attributes) {
        return delegate().createSubchannel(list, attributes);
    }

    public LoadBalancer.Subchannel createSubchannel(LoadBalancer.CreateSubchannelArgs createSubchannelArgs) {
        return delegate().createSubchannel(createSubchannelArgs);
    }

    @Deprecated
    public void updateSubchannelAddresses(LoadBalancer.Subchannel subchannel, List<EquivalentAddressGroup> list) {
        delegate().updateSubchannelAddresses(subchannel, list);
    }

    public ManagedChannel createOobChannel(EquivalentAddressGroup equivalentAddressGroup, String str) {
        return delegate().createOobChannel(equivalentAddressGroup, str);
    }

    public void updateOobChannelAddresses(ManagedChannel managedChannel, EquivalentAddressGroup equivalentAddressGroup) {
        delegate().updateOobChannelAddresses(managedChannel, equivalentAddressGroup);
    }

    public ManagedChannel createResolvingOobChannel(String str) {
        return delegate().createResolvingOobChannel(str);
    }

    public void updateBalancingState(ConnectivityState connectivityState, LoadBalancer.SubchannelPicker subchannelPicker) {
        delegate().updateBalancingState(connectivityState, subchannelPicker);
    }

    public void refreshNameResolution() {
        delegate().refreshNameResolution();
    }

    @Deprecated
    public void runSerialized(Runnable runnable) {
        delegate().runSerialized(runnable);
    }

    @Deprecated
    public NameResolver.Factory getNameResolverFactory() {
        return delegate().getNameResolverFactory();
    }

    public String getAuthority() {
        return delegate().getAuthority();
    }

    public SynchronizationContext getSynchronizationContext() {
        return delegate().getSynchronizationContext();
    }

    public ScheduledExecutorService getScheduledExecutorService() {
        return delegate().getScheduledExecutorService();
    }

    public ChannelLogger getChannelLogger() {
        return delegate().getChannelLogger();
    }

    public NameResolver.Args getNameResolverArgs() {
        return delegate().getNameResolverArgs();
    }

    public NameResolverRegistry getNameResolverRegistry() {
        return delegate().getNameResolverRegistry();
    }

    public String toString() {
        return MoreObjects.toStringHelper((Object) this).add("delegate", (Object) delegate()).toString();
    }
}

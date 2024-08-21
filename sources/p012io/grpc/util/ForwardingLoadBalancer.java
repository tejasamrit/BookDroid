package p012io.grpc.util;

import com.google.common.base.MoreObjects;
import java.util.List;
import p012io.grpc.Attributes;
import p012io.grpc.ConnectivityStateInfo;
import p012io.grpc.EquivalentAddressGroup;
import p012io.grpc.LoadBalancer;
import p012io.grpc.Status;

/* renamed from: io.grpc.util.ForwardingLoadBalancer */
public abstract class ForwardingLoadBalancer extends LoadBalancer {
    /* access modifiers changed from: protected */
    public abstract LoadBalancer delegate();

    @Deprecated
    public void handleResolvedAddressGroups(List<EquivalentAddressGroup> list, Attributes attributes) {
        delegate().handleResolvedAddressGroups(list, attributes);
    }

    public void handleResolvedAddresses(LoadBalancer.ResolvedAddresses resolvedAddresses) {
        delegate().handleResolvedAddresses(resolvedAddresses);
    }

    public void handleNameResolutionError(Status status) {
        delegate().handleNameResolutionError(status);
    }

    @Deprecated
    public void handleSubchannelState(LoadBalancer.Subchannel subchannel, ConnectivityStateInfo connectivityStateInfo) {
        delegate().handleSubchannelState(subchannel, connectivityStateInfo);
    }

    public void shutdown() {
        delegate().shutdown();
    }

    public boolean canHandleEmptyAddressListFromNameResolution() {
        return delegate().canHandleEmptyAddressListFromNameResolution();
    }

    public void requestConnection() {
        delegate().requestConnection();
    }

    public String toString() {
        return MoreObjects.toStringHelper((Object) this).add("delegate", (Object) delegate()).toString();
    }
}

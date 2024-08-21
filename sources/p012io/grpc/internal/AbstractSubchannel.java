package p012io.grpc.internal;

import p012io.grpc.InternalChannelz;
import p012io.grpc.InternalInstrumented;
import p012io.grpc.LoadBalancer;

/* renamed from: io.grpc.internal.AbstractSubchannel */
abstract class AbstractSubchannel extends LoadBalancer.Subchannel {
    /* access modifiers changed from: package-private */
    public abstract InternalInstrumented<InternalChannelz.ChannelStats> getInstrumentedInternalSubchannel();

    AbstractSubchannel() {
    }
}

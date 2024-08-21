package p012io.grpc.util;

import androidx.core.app.NotificationCompat;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import javax.annotation.Nonnull;
import p012io.grpc.Attributes;
import p012io.grpc.ConnectivityState;
import p012io.grpc.ConnectivityStateInfo;
import p012io.grpc.EquivalentAddressGroup;
import p012io.grpc.LoadBalancer;
import p012io.grpc.Status;

/* renamed from: io.grpc.util.RoundRobinLoadBalancer */
final class RoundRobinLoadBalancer extends LoadBalancer {
    private static final Status EMPTY_OK = Status.f489OK.withDescription("no subchannels ready");
    static final Attributes.Key<Ref<ConnectivityStateInfo>> STATE_INFO = Attributes.Key.create("state-info");
    private RoundRobinPicker currentPicker = new EmptyPicker(EMPTY_OK);
    private ConnectivityState currentState;
    private final LoadBalancer.Helper helper;
    private final Random random;
    private final Map<EquivalentAddressGroup, LoadBalancer.Subchannel> subchannels = new HashMap();

    RoundRobinLoadBalancer(LoadBalancer.Helper helper2) {
        this.helper = (LoadBalancer.Helper) Preconditions.checkNotNull(helper2, "helper");
        this.random = new Random();
    }

    public void handleResolvedAddresses(LoadBalancer.ResolvedAddresses resolvedAddresses) {
        List<EquivalentAddressGroup> addresses = resolvedAddresses.getAddresses();
        Set<EquivalentAddressGroup> keySet = this.subchannels.keySet();
        Map<EquivalentAddressGroup, EquivalentAddressGroup> stripAttrs = stripAttrs(addresses);
        Set<T> set = setsDifference(keySet, stripAttrs.keySet());
        for (Map.Entry next : stripAttrs.entrySet()) {
            EquivalentAddressGroup equivalentAddressGroup = (EquivalentAddressGroup) next.getKey();
            EquivalentAddressGroup equivalentAddressGroup2 = (EquivalentAddressGroup) next.getValue();
            LoadBalancer.Subchannel subchannel = this.subchannels.get(equivalentAddressGroup);
            if (subchannel != null) {
                subchannel.updateAddresses(Collections.singletonList(equivalentAddressGroup2));
            } else {
                final LoadBalancer.Subchannel subchannel2 = (LoadBalancer.Subchannel) Preconditions.checkNotNull(this.helper.createSubchannel(LoadBalancer.CreateSubchannelArgs.newBuilder().setAddresses(equivalentAddressGroup2).setAttributes(Attributes.newBuilder().set(STATE_INFO, new Ref(ConnectivityStateInfo.forNonError(ConnectivityState.IDLE))).build()).build()), "subchannel");
                subchannel2.start(new LoadBalancer.SubchannelStateListener() {
                    public void onSubchannelState(ConnectivityStateInfo connectivityStateInfo) {
                        RoundRobinLoadBalancer.this.processSubchannelState(subchannel2, connectivityStateInfo);
                    }
                });
                this.subchannels.put(equivalentAddressGroup, subchannel2);
                subchannel2.requestConnection();
            }
        }
        ArrayList arrayList = new ArrayList();
        for (T remove : set) {
            arrayList.add(this.subchannels.remove(remove));
        }
        updateBalancingState();
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            shutdownSubchannel((LoadBalancer.Subchannel) it.next());
        }
    }

    public void handleNameResolutionError(Status status) {
        ConnectivityState connectivityState = ConnectivityState.TRANSIENT_FAILURE;
        RoundRobinPicker roundRobinPicker = this.currentPicker;
        if (!(roundRobinPicker instanceof ReadyPicker)) {
            roundRobinPicker = new EmptyPicker(status);
        }
        updateBalancingState(connectivityState, roundRobinPicker);
    }

    /* access modifiers changed from: private */
    public void processSubchannelState(LoadBalancer.Subchannel subchannel, ConnectivityStateInfo connectivityStateInfo) {
        if (this.subchannels.get(stripAttrs(subchannel.getAddresses())) == subchannel) {
            if (connectivityStateInfo.getState() == ConnectivityState.IDLE) {
                subchannel.requestConnection();
            }
            getSubchannelStateInfoRef(subchannel).value = connectivityStateInfo;
            updateBalancingState();
        }
    }

    private void shutdownSubchannel(LoadBalancer.Subchannel subchannel) {
        subchannel.shutdown();
        getSubchannelStateInfoRef(subchannel).value = ConnectivityStateInfo.forNonError(ConnectivityState.SHUTDOWN);
    }

    public void shutdown() {
        for (LoadBalancer.Subchannel shutdownSubchannel : getSubchannels()) {
            shutdownSubchannel(shutdownSubchannel);
        }
    }

    private void updateBalancingState() {
        List<LoadBalancer.Subchannel> filterNonFailingSubchannels = filterNonFailingSubchannels(getSubchannels());
        if (filterNonFailingSubchannels.isEmpty()) {
            boolean z = false;
            Status status = EMPTY_OK;
            for (LoadBalancer.Subchannel subchannelStateInfoRef : getSubchannels()) {
                ConnectivityStateInfo connectivityStateInfo = (ConnectivityStateInfo) getSubchannelStateInfoRef(subchannelStateInfoRef).value;
                if (connectivityStateInfo.getState() == ConnectivityState.CONNECTING || connectivityStateInfo.getState() == ConnectivityState.IDLE) {
                    z = true;
                }
                if (status == EMPTY_OK || !status.isOk()) {
                    status = connectivityStateInfo.getStatus();
                }
            }
            updateBalancingState(z ? ConnectivityState.CONNECTING : ConnectivityState.TRANSIENT_FAILURE, new EmptyPicker(status));
            return;
        }
        updateBalancingState(ConnectivityState.READY, new ReadyPicker(filterNonFailingSubchannels, this.random.nextInt(filterNonFailingSubchannels.size())));
    }

    private void updateBalancingState(ConnectivityState connectivityState, RoundRobinPicker roundRobinPicker) {
        if (connectivityState != this.currentState || !roundRobinPicker.isEquivalentTo(this.currentPicker)) {
            this.helper.updateBalancingState(connectivityState, roundRobinPicker);
            this.currentState = connectivityState;
            this.currentPicker = roundRobinPicker;
        }
    }

    private static List<LoadBalancer.Subchannel> filterNonFailingSubchannels(Collection<LoadBalancer.Subchannel> collection) {
        ArrayList arrayList = new ArrayList(collection.size());
        for (LoadBalancer.Subchannel next : collection) {
            if (isReady(next)) {
                arrayList.add(next);
            }
        }
        return arrayList;
    }

    private static Map<EquivalentAddressGroup, EquivalentAddressGroup> stripAttrs(List<EquivalentAddressGroup> list) {
        HashMap hashMap = new HashMap(list.size() * 2);
        for (EquivalentAddressGroup next : list) {
            hashMap.put(stripAttrs(next), next);
        }
        return hashMap;
    }

    private static EquivalentAddressGroup stripAttrs(EquivalentAddressGroup equivalentAddressGroup) {
        return new EquivalentAddressGroup(equivalentAddressGroup.getAddresses());
    }

    /* access modifiers changed from: package-private */
    public Collection<LoadBalancer.Subchannel> getSubchannels() {
        return this.subchannels.values();
    }

    private static Ref<ConnectivityStateInfo> getSubchannelStateInfoRef(LoadBalancer.Subchannel subchannel) {
        return (Ref) Preconditions.checkNotNull(subchannel.getAttributes().get(STATE_INFO), "STATE_INFO");
    }

    static boolean isReady(LoadBalancer.Subchannel subchannel) {
        return ((ConnectivityStateInfo) getSubchannelStateInfoRef(subchannel).value).getState() == ConnectivityState.READY;
    }

    private static <T> Set<T> setsDifference(Set<T> set, Set<T> set2) {
        HashSet hashSet = new HashSet(set);
        hashSet.removeAll(set2);
        return hashSet;
    }

    /* renamed from: io.grpc.util.RoundRobinLoadBalancer$RoundRobinPicker */
    private static abstract class RoundRobinPicker extends LoadBalancer.SubchannelPicker {
        /* access modifiers changed from: package-private */
        public abstract boolean isEquivalentTo(RoundRobinPicker roundRobinPicker);

        private RoundRobinPicker() {
        }
    }

    /* renamed from: io.grpc.util.RoundRobinLoadBalancer$ReadyPicker */
    static final class ReadyPicker extends RoundRobinPicker {
        private static final AtomicIntegerFieldUpdater<ReadyPicker> indexUpdater = AtomicIntegerFieldUpdater.newUpdater(ReadyPicker.class, "index");
        private volatile int index;
        private final List<LoadBalancer.Subchannel> list;

        ReadyPicker(List<LoadBalancer.Subchannel> list2, int i) {
            super();
            Preconditions.checkArgument(!list2.isEmpty(), "empty list");
            this.list = list2;
            this.index = i - 1;
        }

        public LoadBalancer.PickResult pickSubchannel(LoadBalancer.PickSubchannelArgs pickSubchannelArgs) {
            return LoadBalancer.PickResult.withSubchannel(nextSubchannel());
        }

        public String toString() {
            return MoreObjects.toStringHelper((Class<?>) ReadyPicker.class).add("list", (Object) this.list).toString();
        }

        private LoadBalancer.Subchannel nextSubchannel() {
            int size = this.list.size();
            AtomicIntegerFieldUpdater<ReadyPicker> atomicIntegerFieldUpdater = indexUpdater;
            int incrementAndGet = atomicIntegerFieldUpdater.incrementAndGet(this);
            if (incrementAndGet >= size) {
                int i = incrementAndGet % size;
                atomicIntegerFieldUpdater.compareAndSet(this, incrementAndGet, i);
                incrementAndGet = i;
            }
            return this.list.get(incrementAndGet);
        }

        /* access modifiers changed from: package-private */
        public List<LoadBalancer.Subchannel> getList() {
            return this.list;
        }

        /* access modifiers changed from: package-private */
        public boolean isEquivalentTo(RoundRobinPicker roundRobinPicker) {
            if (!(roundRobinPicker instanceof ReadyPicker)) {
                return false;
            }
            ReadyPicker readyPicker = (ReadyPicker) roundRobinPicker;
            if (readyPicker == this || (this.list.size() == readyPicker.list.size() && new HashSet(this.list).containsAll(readyPicker.list))) {
                return true;
            }
            return false;
        }
    }

    /* renamed from: io.grpc.util.RoundRobinLoadBalancer$EmptyPicker */
    static final class EmptyPicker extends RoundRobinPicker {
        private final Status status;

        EmptyPicker(@Nonnull Status status2) {
            super();
            this.status = (Status) Preconditions.checkNotNull(status2, NotificationCompat.CATEGORY_STATUS);
        }

        public LoadBalancer.PickResult pickSubchannel(LoadBalancer.PickSubchannelArgs pickSubchannelArgs) {
            return this.status.isOk() ? LoadBalancer.PickResult.withNoResult() : LoadBalancer.PickResult.withError(this.status);
        }

        /* access modifiers changed from: package-private */
        public boolean isEquivalentTo(RoundRobinPicker roundRobinPicker) {
            if (roundRobinPicker instanceof EmptyPicker) {
                EmptyPicker emptyPicker = (EmptyPicker) roundRobinPicker;
                if (Objects.equal(this.status, emptyPicker.status) || (this.status.isOk() && emptyPicker.status.isOk())) {
                    return true;
                }
            }
            return false;
        }

        public String toString() {
            return MoreObjects.toStringHelper((Class<?>) EmptyPicker.class).add(NotificationCompat.CATEGORY_STATUS, (Object) this.status).toString();
        }
    }

    /* renamed from: io.grpc.util.RoundRobinLoadBalancer$Ref */
    static final class Ref<T> {
        T value;

        Ref(T t) {
            this.value = t;
        }
    }
}

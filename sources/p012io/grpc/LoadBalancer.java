package p012io.grpc;

import androidx.core.app.NotificationCompat;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ScheduledExecutorService;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import p012io.grpc.Attributes;
import p012io.grpc.ClientStreamTracer;
import p012io.grpc.NameResolver;

/* renamed from: io.grpc.LoadBalancer */
public abstract class LoadBalancer {
    @Deprecated
    public static final Attributes.Key<Map<String, ?>> ATTR_LOAD_BALANCING_CONFIG = Attributes.Key.create("io.grpc.LoadBalancer.loadBalancingConfig");
    private int recursionCount;

    /* renamed from: io.grpc.LoadBalancer$Factory */
    public static abstract class Factory {
        public abstract LoadBalancer newLoadBalancer(Helper helper);
    }

    /* renamed from: io.grpc.LoadBalancer$PickSubchannelArgs */
    public static abstract class PickSubchannelArgs {
        public abstract CallOptions getCallOptions();

        public abstract Metadata getHeaders();

        public abstract MethodDescriptor<?, ?> getMethodDescriptor();
    }

    /* renamed from: io.grpc.LoadBalancer$SubchannelPicker */
    public static abstract class SubchannelPicker {
        public abstract PickResult pickSubchannel(PickSubchannelArgs pickSubchannelArgs);

        @Deprecated
        public void requestConnection() {
        }
    }

    /* renamed from: io.grpc.LoadBalancer$SubchannelStateListener */
    public interface SubchannelStateListener {
        void onSubchannelState(ConnectivityStateInfo connectivityStateInfo);
    }

    public boolean canHandleEmptyAddressListFromNameResolution() {
        return false;
    }

    public abstract void handleNameResolutionError(Status status);

    @Deprecated
    public void handleSubchannelState(Subchannel subchannel, ConnectivityStateInfo connectivityStateInfo) {
    }

    public void requestConnection() {
    }

    public abstract void shutdown();

    @Deprecated
    public void handleResolvedAddressGroups(List<EquivalentAddressGroup> list, Attributes attributes) {
        int i = this.recursionCount;
        this.recursionCount = i + 1;
        if (i == 0) {
            handleResolvedAddresses(ResolvedAddresses.newBuilder().setAddresses(list).setAttributes(attributes).build());
        }
        this.recursionCount = 0;
    }

    public void handleResolvedAddresses(ResolvedAddresses resolvedAddresses) {
        int i = this.recursionCount;
        this.recursionCount = i + 1;
        if (i == 0) {
            handleResolvedAddressGroups(resolvedAddresses.getAddresses(), resolvedAddresses.getAttributes());
        }
        this.recursionCount = 0;
    }

    /* renamed from: io.grpc.LoadBalancer$ResolvedAddresses */
    public static final class ResolvedAddresses {
        private final List<EquivalentAddressGroup> addresses;
        private final Attributes attributes;
        @Nullable
        private final Object loadBalancingPolicyConfig;

        private ResolvedAddresses(List<EquivalentAddressGroup> list, Attributes attributes2, Object obj) {
            this.addresses = Collections.unmodifiableList(new ArrayList((Collection) Preconditions.checkNotNull(list, "addresses")));
            this.attributes = (Attributes) Preconditions.checkNotNull(attributes2, "attributes");
            this.loadBalancingPolicyConfig = obj;
        }

        public static Builder newBuilder() {
            return new Builder();
        }

        public Builder toBuilder() {
            return newBuilder().setAddresses(this.addresses).setAttributes(this.attributes).setLoadBalancingPolicyConfig(this.loadBalancingPolicyConfig);
        }

        public List<EquivalentAddressGroup> getAddresses() {
            return this.addresses;
        }

        public Attributes getAttributes() {
            return this.attributes;
        }

        @Nullable
        public Object getLoadBalancingPolicyConfig() {
            return this.loadBalancingPolicyConfig;
        }

        /* renamed from: io.grpc.LoadBalancer$ResolvedAddresses$Builder */
        public static final class Builder {
            private List<EquivalentAddressGroup> addresses;
            private Attributes attributes = Attributes.EMPTY;
            @Nullable
            private Object loadBalancingPolicyConfig;

            Builder() {
            }

            public Builder setAddresses(List<EquivalentAddressGroup> list) {
                this.addresses = list;
                return this;
            }

            public Builder setAttributes(Attributes attributes2) {
                this.attributes = attributes2;
                return this;
            }

            public Builder setLoadBalancingPolicyConfig(@Nullable Object obj) {
                this.loadBalancingPolicyConfig = obj;
                return this;
            }

            public ResolvedAddresses build() {
                return new ResolvedAddresses(this.addresses, this.attributes, this.loadBalancingPolicyConfig);
            }
        }

        public String toString() {
            return MoreObjects.toStringHelper((Object) this).add("addresses", (Object) this.addresses).add("attributes", (Object) this.attributes).add("loadBalancingPolicyConfig", this.loadBalancingPolicyConfig).toString();
        }

        public int hashCode() {
            return Objects.hashCode(this.addresses, this.attributes, this.loadBalancingPolicyConfig);
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof ResolvedAddresses)) {
                return false;
            }
            ResolvedAddresses resolvedAddresses = (ResolvedAddresses) obj;
            if (!Objects.equal(this.addresses, resolvedAddresses.addresses) || !Objects.equal(this.attributes, resolvedAddresses.attributes) || !Objects.equal(this.loadBalancingPolicyConfig, resolvedAddresses.loadBalancingPolicyConfig)) {
                return false;
            }
            return true;
        }
    }

    /* renamed from: io.grpc.LoadBalancer$PickResult */
    public static final class PickResult {
        private static final PickResult NO_RESULT = new PickResult((Subchannel) null, (ClientStreamTracer.Factory) null, Status.f489OK, false);
        private final boolean drop;
        private final Status status;
        @Nullable
        private final ClientStreamTracer.Factory streamTracerFactory;
        @Nullable
        private final Subchannel subchannel;

        private PickResult(@Nullable Subchannel subchannel2, @Nullable ClientStreamTracer.Factory factory, Status status2, boolean z) {
            this.subchannel = subchannel2;
            this.streamTracerFactory = factory;
            this.status = (Status) Preconditions.checkNotNull(status2, NotificationCompat.CATEGORY_STATUS);
            this.drop = z;
        }

        public static PickResult withSubchannel(Subchannel subchannel2, @Nullable ClientStreamTracer.Factory factory) {
            return new PickResult((Subchannel) Preconditions.checkNotNull(subchannel2, "subchannel"), factory, Status.f489OK, false);
        }

        public static PickResult withSubchannel(Subchannel subchannel2) {
            return withSubchannel(subchannel2, (ClientStreamTracer.Factory) null);
        }

        public static PickResult withError(Status status2) {
            Preconditions.checkArgument(!status2.isOk(), "error status shouldn't be OK");
            return new PickResult((Subchannel) null, (ClientStreamTracer.Factory) null, status2, false);
        }

        public static PickResult withDrop(Status status2) {
            Preconditions.checkArgument(!status2.isOk(), "drop status shouldn't be OK");
            return new PickResult((Subchannel) null, (ClientStreamTracer.Factory) null, status2, true);
        }

        public static PickResult withNoResult() {
            return NO_RESULT;
        }

        @Nullable
        public Subchannel getSubchannel() {
            return this.subchannel;
        }

        @Nullable
        public ClientStreamTracer.Factory getStreamTracerFactory() {
            return this.streamTracerFactory;
        }

        public Status getStatus() {
            return this.status;
        }

        public boolean isDrop() {
            return this.drop;
        }

        public String toString() {
            return MoreObjects.toStringHelper((Object) this).add("subchannel", (Object) this.subchannel).add("streamTracerFactory", (Object) this.streamTracerFactory).add(NotificationCompat.CATEGORY_STATUS, (Object) this.status).add("drop", this.drop).toString();
        }

        public int hashCode() {
            return Objects.hashCode(this.subchannel, this.status, this.streamTracerFactory, Boolean.valueOf(this.drop));
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof PickResult)) {
                return false;
            }
            PickResult pickResult = (PickResult) obj;
            if (!Objects.equal(this.subchannel, pickResult.subchannel) || !Objects.equal(this.status, pickResult.status) || !Objects.equal(this.streamTracerFactory, pickResult.streamTracerFactory) || this.drop != pickResult.drop) {
                return false;
            }
            return true;
        }
    }

    /* renamed from: io.grpc.LoadBalancer$CreateSubchannelArgs */
    public static final class CreateSubchannelArgs {
        private final List<EquivalentAddressGroup> addrs;
        private final Attributes attrs;
        private final Object[][] customOptions;

        private CreateSubchannelArgs(List<EquivalentAddressGroup> list, Attributes attributes, Object[][] objArr) {
            this.addrs = (List) Preconditions.checkNotNull(list, "addresses are not set");
            this.attrs = (Attributes) Preconditions.checkNotNull(attributes, "attrs");
            this.customOptions = (Object[][]) Preconditions.checkNotNull(objArr, "customOptions");
        }

        public List<EquivalentAddressGroup> getAddresses() {
            return this.addrs;
        }

        public Attributes getAttributes() {
            return this.attrs;
        }

        public <T> T getOption(Key<T> key) {
            Preconditions.checkNotNull(key, "key");
            int i = 0;
            while (true) {
                Object[][] objArr = this.customOptions;
                if (i >= objArr.length) {
                    return key.defaultValue;
                }
                if (key.equals(objArr[i][0])) {
                    return this.customOptions[i][1];
                }
                i++;
            }
        }

        public Builder toBuilder() {
            return newBuilder().setAddresses(this.addrs).setAttributes(this.attrs).copyCustomOptions(this.customOptions);
        }

        public static Builder newBuilder() {
            return new Builder();
        }

        public String toString() {
            return MoreObjects.toStringHelper((Object) this).add("addrs", (Object) this.addrs).add("attrs", (Object) this.attrs).add("customOptions", (Object) Arrays.deepToString(this.customOptions)).toString();
        }

        /* renamed from: io.grpc.LoadBalancer$CreateSubchannelArgs$Builder */
        public static final class Builder {
            private List<EquivalentAddressGroup> addrs;
            private Attributes attrs = Attributes.EMPTY;
            private Object[][] customOptions = ((Object[][]) Array.newInstance(Object.class, new int[]{0, 2}));

            Builder() {
            }

            /* access modifiers changed from: private */
            public <T> Builder copyCustomOptions(Object[][] objArr) {
                int length = objArr.length;
                int[] iArr = new int[2];
                iArr[1] = 2;
                iArr[0] = length;
                Object[][] objArr2 = (Object[][]) Array.newInstance(Object.class, iArr);
                this.customOptions = objArr2;
                System.arraycopy(objArr, 0, objArr2, 0, objArr.length);
                return this;
            }

            public <T> Builder addOption(Key<T> key, T t) {
                Preconditions.checkNotNull(key, "key");
                Preconditions.checkNotNull(t, "value");
                int i = 0;
                while (true) {
                    Object[][] objArr = this.customOptions;
                    if (i >= objArr.length) {
                        i = -1;
                        break;
                    } else if (key.equals(objArr[i][0])) {
                        break;
                    } else {
                        i++;
                    }
                }
                if (i == -1) {
                    int[] iArr = new int[2];
                    iArr[1] = 2;
                    iArr[0] = this.customOptions.length + 1;
                    Object[][] objArr2 = (Object[][]) Array.newInstance(Object.class, iArr);
                    Object[][] objArr3 = this.customOptions;
                    System.arraycopy(objArr3, 0, objArr2, 0, objArr3.length);
                    this.customOptions = objArr2;
                    i = objArr2.length - 1;
                }
                this.customOptions[i] = new Object[]{key, t};
                return this;
            }

            public Builder setAddresses(EquivalentAddressGroup equivalentAddressGroup) {
                this.addrs = Collections.singletonList(equivalentAddressGroup);
                return this;
            }

            public Builder setAddresses(List<EquivalentAddressGroup> list) {
                Preconditions.checkArgument(!list.isEmpty(), "addrs is empty");
                this.addrs = Collections.unmodifiableList(new ArrayList(list));
                return this;
            }

            public Builder setAttributes(Attributes attributes) {
                this.attrs = (Attributes) Preconditions.checkNotNull(attributes, "attrs");
                return this;
            }

            public CreateSubchannelArgs build() {
                return new CreateSubchannelArgs(this.addrs, this.attrs, this.customOptions);
            }
        }

        /* renamed from: io.grpc.LoadBalancer$CreateSubchannelArgs$Key */
        public static final class Key<T> {
            private final String debugString;
            /* access modifiers changed from: private */
            public final T defaultValue;

            private Key(String str, T t) {
                this.debugString = str;
                this.defaultValue = t;
            }

            public static <T> Key<T> create(String str) {
                Preconditions.checkNotNull(str, "debugString");
                return new Key<>(str, (Object) null);
            }

            public static <T> Key<T> createWithDefault(String str, T t) {
                Preconditions.checkNotNull(str, "debugString");
                return new Key<>(str, t);
            }

            public T getDefault() {
                return this.defaultValue;
            }

            public String toString() {
                return this.debugString;
            }
        }
    }

    /* renamed from: io.grpc.LoadBalancer$Helper */
    public static abstract class Helper {
        public abstract ManagedChannel createOobChannel(EquivalentAddressGroup equivalentAddressGroup, String str);

        public abstract String getAuthority();

        @Deprecated
        public abstract NameResolver.Factory getNameResolverFactory();

        public abstract void updateBalancingState(@Nonnull ConnectivityState connectivityState, @Nonnull SubchannelPicker subchannelPicker);

        @Deprecated
        public final Subchannel createSubchannel(EquivalentAddressGroup equivalentAddressGroup, Attributes attributes) {
            Preconditions.checkNotNull(equivalentAddressGroup, "addrs");
            return createSubchannel((List<EquivalentAddressGroup>) Collections.singletonList(equivalentAddressGroup), attributes);
        }

        @Deprecated
        public Subchannel createSubchannel(List<EquivalentAddressGroup> list, Attributes attributes) {
            throw new UnsupportedOperationException();
        }

        public Subchannel createSubchannel(CreateSubchannelArgs createSubchannelArgs) {
            throw new UnsupportedOperationException();
        }

        @Deprecated
        public final void updateSubchannelAddresses(Subchannel subchannel, EquivalentAddressGroup equivalentAddressGroup) {
            Preconditions.checkNotNull(equivalentAddressGroup, "addrs");
            updateSubchannelAddresses(subchannel, (List<EquivalentAddressGroup>) Collections.singletonList(equivalentAddressGroup));
        }

        @Deprecated
        public void updateSubchannelAddresses(Subchannel subchannel, List<EquivalentAddressGroup> list) {
            throw new UnsupportedOperationException();
        }

        public void updateOobChannelAddresses(ManagedChannel managedChannel, EquivalentAddressGroup equivalentAddressGroup) {
            throw new UnsupportedOperationException();
        }

        public ManagedChannel createResolvingOobChannel(String str) {
            throw new UnsupportedOperationException("Not implemented");
        }

        public void refreshNameResolution() {
            throw new UnsupportedOperationException();
        }

        @Deprecated
        public void runSerialized(Runnable runnable) {
            getSynchronizationContext().execute(runnable);
        }

        public SynchronizationContext getSynchronizationContext() {
            throw new UnsupportedOperationException();
        }

        public ScheduledExecutorService getScheduledExecutorService() {
            throw new UnsupportedOperationException();
        }

        public ChannelLogger getChannelLogger() {
            throw new UnsupportedOperationException();
        }

        public NameResolver.Args getNameResolverArgs() {
            throw new UnsupportedOperationException();
        }

        public NameResolverRegistry getNameResolverRegistry() {
            throw new UnsupportedOperationException();
        }
    }

    /* renamed from: io.grpc.LoadBalancer$Subchannel */
    public static abstract class Subchannel {
        public abstract Attributes getAttributes();

        public abstract void requestConnection();

        public abstract void shutdown();

        public void start(SubchannelStateListener subchannelStateListener) {
            throw new UnsupportedOperationException("Not implemented");
        }

        public final EquivalentAddressGroup getAddresses() {
            List<EquivalentAddressGroup> allAddresses = getAllAddresses();
            boolean z = true;
            if (allAddresses.size() != 1) {
                z = false;
            }
            Preconditions.checkState(z, "%s does not have exactly one group", (Object) allAddresses);
            return allAddresses.get(0);
        }

        public List<EquivalentAddressGroup> getAllAddresses() {
            throw new UnsupportedOperationException();
        }

        public Channel asChannel() {
            throw new UnsupportedOperationException();
        }

        public ChannelLogger getChannelLogger() {
            throw new UnsupportedOperationException();
        }

        public void updateAddresses(List<EquivalentAddressGroup> list) {
            throw new UnsupportedOperationException();
        }

        public Object getInternalSubchannel() {
            throw new UnsupportedOperationException();
        }
    }
}

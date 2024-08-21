package p012io.grpc.internal;

import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.SettableFuture;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import p012io.grpc.Attributes;
import p012io.grpc.CallOptions;
import p012io.grpc.ClientCall;
import p012io.grpc.ConnectivityState;
import p012io.grpc.ConnectivityStateInfo;
import p012io.grpc.Context;
import p012io.grpc.EquivalentAddressGroup;
import p012io.grpc.InternalChannelz;
import p012io.grpc.InternalInstrumented;
import p012io.grpc.InternalLogId;
import p012io.grpc.LoadBalancer;
import p012io.grpc.ManagedChannel;
import p012io.grpc.Metadata;
import p012io.grpc.MethodDescriptor;
import p012io.grpc.Status;
import p012io.grpc.SynchronizationContext;
import p012io.grpc.internal.ClientCallImpl;
import p012io.grpc.internal.ManagedClientTransport;

/* renamed from: io.grpc.internal.OobChannel */
final class OobChannel extends ManagedChannel implements InternalInstrumented<InternalChannelz.ChannelStats> {
    private static final Logger log = Logger.getLogger(OobChannel.class.getName());
    private final String authority;
    private final CallTracer channelCallsTracer;
    private final ChannelTracer channelTracer;
    private final InternalChannelz channelz;
    private final ScheduledExecutorService deadlineCancellationExecutor;
    /* access modifiers changed from: private */
    public final DelayedClientTransport delayedTransport;
    private final Executor executor;
    private final ObjectPool<? extends Executor> executorPool;
    private final InternalLogId logId;
    private volatile boolean shutdown;
    private InternalSubchannel subchannel;
    /* access modifiers changed from: private */
    public AbstractSubchannel subchannelImpl;
    private LoadBalancer.SubchannelPicker subchannelPicker;
    private final CountDownLatch terminatedLatch = new CountDownLatch(1);
    private final TimeProvider timeProvider;
    private final ClientCallImpl.ClientTransportProvider transportProvider = new ClientCallImpl.ClientTransportProvider() {
        public ClientTransport get(LoadBalancer.PickSubchannelArgs pickSubchannelArgs) {
            return OobChannel.this.delayedTransport;
        }

        public <ReqT> ClientStream newRetriableStream(MethodDescriptor<ReqT, ?> methodDescriptor, CallOptions callOptions, Metadata metadata, Context context) {
            throw new UnsupportedOperationException("OobChannel should not create retriable streams");
        }
    };

    OobChannel(String str, ObjectPool<? extends Executor> objectPool, ScheduledExecutorService scheduledExecutorService, SynchronizationContext synchronizationContext, CallTracer callTracer, ChannelTracer channelTracer2, InternalChannelz internalChannelz, TimeProvider timeProvider2) {
        this.authority = (String) Preconditions.checkNotNull(str, "authority");
        this.logId = InternalLogId.allocate(getClass(), str);
        this.executorPool = (ObjectPool) Preconditions.checkNotNull(objectPool, "executorPool");
        Executor executor2 = (Executor) Preconditions.checkNotNull(objectPool.getObject(), "executor");
        this.executor = executor2;
        this.deadlineCancellationExecutor = (ScheduledExecutorService) Preconditions.checkNotNull(scheduledExecutorService, "deadlineCancellationExecutor");
        DelayedClientTransport delayedClientTransport = new DelayedClientTransport(executor2, synchronizationContext);
        this.delayedTransport = delayedClientTransport;
        this.channelz = (InternalChannelz) Preconditions.checkNotNull(internalChannelz);
        delayedClientTransport.start(new ManagedClientTransport.C2463Listener() {
            public void transportInUse(boolean z) {
            }

            public void transportReady() {
            }

            public void transportShutdown(Status status) {
            }

            public void transportTerminated() {
                OobChannel.this.subchannelImpl.shutdown();
            }
        });
        this.channelCallsTracer = callTracer;
        this.channelTracer = (ChannelTracer) Preconditions.checkNotNull(channelTracer2, "channelTracer");
        this.timeProvider = (TimeProvider) Preconditions.checkNotNull(timeProvider2, "timeProvider");
    }

    /* access modifiers changed from: package-private */
    public void setSubchannel(final InternalSubchannel internalSubchannel) {
        log.log(Level.FINE, "[{0}] Created with [{1}]", new Object[]{this, internalSubchannel});
        this.subchannel = internalSubchannel;
        this.subchannelImpl = new AbstractSubchannel() {
            public void shutdown() {
                internalSubchannel.shutdown(Status.UNAVAILABLE.withDescription("OobChannel is shutdown"));
            }

            /* access modifiers changed from: package-private */
            public InternalInstrumented<InternalChannelz.ChannelStats> getInstrumentedInternalSubchannel() {
                return internalSubchannel;
            }

            public void requestConnection() {
                internalSubchannel.obtainActiveTransport();
            }

            public List<EquivalentAddressGroup> getAllAddresses() {
                return internalSubchannel.getAddressGroups();
            }

            public Attributes getAttributes() {
                return Attributes.EMPTY;
            }

            public Object getInternalSubchannel() {
                return internalSubchannel;
            }
        };
        AnonymousClass1OobSubchannelPicker r5 = new LoadBalancer.SubchannelPicker() {
            final LoadBalancer.PickResult result;

            {
                this.result = LoadBalancer.PickResult.withSubchannel(OobChannel.this.subchannelImpl);
            }

            public LoadBalancer.PickResult pickSubchannel(LoadBalancer.PickSubchannelArgs pickSubchannelArgs) {
                return this.result;
            }

            public String toString() {
                return MoreObjects.toStringHelper((Class<?>) AnonymousClass1OobSubchannelPicker.class).add("result", (Object) this.result).toString();
            }
        };
        this.subchannelPicker = r5;
        this.delayedTransport.reprocess(r5);
    }

    /* access modifiers changed from: package-private */
    public void updateAddresses(EquivalentAddressGroup equivalentAddressGroup) {
        this.subchannel.updateAddresses(Collections.singletonList(equivalentAddressGroup));
    }

    public <RequestT, ResponseT> ClientCall<RequestT, ResponseT> newCall(MethodDescriptor<RequestT, ResponseT> methodDescriptor, CallOptions callOptions) {
        return new ClientCallImpl(methodDescriptor, callOptions.getExecutor() == null ? this.executor : callOptions.getExecutor(), callOptions, this.transportProvider, this.deadlineCancellationExecutor, this.channelCallsTracer, false);
    }

    public String authority() {
        return this.authority;
    }

    public boolean isTerminated() {
        return this.terminatedLatch.getCount() == 0;
    }

    public boolean awaitTermination(long j, TimeUnit timeUnit) throws InterruptedException {
        return this.terminatedLatch.await(j, timeUnit);
    }

    public ConnectivityState getState(boolean z) {
        InternalSubchannel internalSubchannel = this.subchannel;
        if (internalSubchannel == null) {
            return ConnectivityState.IDLE;
        }
        return internalSubchannel.getState();
    }

    public ManagedChannel shutdown() {
        this.shutdown = true;
        this.delayedTransport.shutdown(Status.UNAVAILABLE.withDescription("OobChannel.shutdown() called"));
        return this;
    }

    public boolean isShutdown() {
        return this.shutdown;
    }

    public ManagedChannel shutdownNow() {
        this.shutdown = true;
        this.delayedTransport.shutdownNow(Status.UNAVAILABLE.withDescription("OobChannel.shutdownNow() called"));
        return this;
    }

    /* access modifiers changed from: package-private */
    public void handleSubchannelStateChange(ConnectivityStateInfo connectivityStateInfo) {
        ChannelTracer channelTracer2 = this.channelTracer;
        InternalChannelz.ChannelTrace.Event.Builder builder = new InternalChannelz.ChannelTrace.Event.Builder();
        channelTracer2.reportEvent(builder.setDescription("Entering " + connectivityStateInfo.getState() + " state").setSeverity(InternalChannelz.ChannelTrace.Event.Severity.CT_INFO).setTimestampNanos(this.timeProvider.currentTimeNanos()).build());
        int i = C24704.$SwitchMap$io$grpc$ConnectivityState[connectivityStateInfo.getState().ordinal()];
        if (i == 1 || i == 2) {
            this.delayedTransport.reprocess(this.subchannelPicker);
        } else if (i == 3) {
            this.delayedTransport.reprocess(new LoadBalancer.SubchannelPicker(connectivityStateInfo) {
                final LoadBalancer.PickResult errorResult;
                final /* synthetic */ ConnectivityStateInfo val$newState;

                {
                    this.val$newState = r2;
                    this.errorResult = LoadBalancer.PickResult.withError(r2.getStatus());
                }

                public LoadBalancer.PickResult pickSubchannel(LoadBalancer.PickSubchannelArgs pickSubchannelArgs) {
                    return this.errorResult;
                }

                public String toString() {
                    return MoreObjects.toStringHelper((Class<?>) AnonymousClass1OobErrorPicker.class).add("errorResult", (Object) this.errorResult).toString();
                }
            });
        }
    }

    /* renamed from: io.grpc.internal.OobChannel$4 */
    static /* synthetic */ class C24704 {
        static final /* synthetic */ int[] $SwitchMap$io$grpc$ConnectivityState;

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|(3:5|6|8)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        static {
            /*
                io.grpc.ConnectivityState[] r0 = p012io.grpc.ConnectivityState.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$io$grpc$ConnectivityState = r0
                io.grpc.ConnectivityState r1 = p012io.grpc.ConnectivityState.READY     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$io$grpc$ConnectivityState     // Catch:{ NoSuchFieldError -> 0x001d }
                io.grpc.ConnectivityState r1 = p012io.grpc.ConnectivityState.IDLE     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$io$grpc$ConnectivityState     // Catch:{ NoSuchFieldError -> 0x0028 }
                io.grpc.ConnectivityState r1 = p012io.grpc.ConnectivityState.TRANSIENT_FAILURE     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: p012io.grpc.internal.OobChannel.C24704.<clinit>():void");
        }
    }

    /* access modifiers changed from: package-private */
    public void handleSubchannelTerminated() {
        this.channelz.removeSubchannel(this);
        this.executorPool.returnObject(this.executor);
        this.terminatedLatch.countDown();
    }

    /* access modifiers changed from: package-private */
    public LoadBalancer.Subchannel getSubchannel() {
        return this.subchannelImpl;
    }

    /* access modifiers changed from: package-private */
    public InternalSubchannel getInternalSubchannel() {
        return this.subchannel;
    }

    public ListenableFuture<InternalChannelz.ChannelStats> getStats() {
        SettableFuture create = SettableFuture.create();
        InternalChannelz.ChannelStats.Builder builder = new InternalChannelz.ChannelStats.Builder();
        this.channelCallsTracer.updateBuilder(builder);
        this.channelTracer.updateBuilder(builder);
        builder.setTarget(this.authority).setState(this.subchannel.getState()).setSubchannels(Collections.singletonList(this.subchannel));
        create.set(builder.build());
        return create;
    }

    public InternalLogId getLogId() {
        return this.logId;
    }

    public String toString() {
        return MoreObjects.toStringHelper((Object) this).add("logId", this.logId.getId()).add("authority", (Object) this.authority).toString();
    }

    public void resetConnectBackoff() {
        this.subchannel.resetConnectBackoff();
    }
}

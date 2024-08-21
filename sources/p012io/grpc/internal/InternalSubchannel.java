package p012io.grpc.internal;

import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import com.google.common.base.Stopwatch;
import com.google.common.base.Supplier;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.SettableFuture;
import java.net.SocketAddress;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nullable;
import p012io.grpc.Attributes;
import p012io.grpc.CallOptions;
import p012io.grpc.ChannelLogger;
import p012io.grpc.ConnectivityState;
import p012io.grpc.ConnectivityStateInfo;
import p012io.grpc.EquivalentAddressGroup;
import p012io.grpc.HttpConnectProxiedSocketAddress;
import p012io.grpc.InternalChannelz;
import p012io.grpc.InternalInstrumented;
import p012io.grpc.InternalLogId;
import p012io.grpc.Metadata;
import p012io.grpc.MethodDescriptor;
import p012io.grpc.Status;
import p012io.grpc.SynchronizationContext;
import p012io.grpc.internal.BackoffPolicy;
import p012io.grpc.internal.ClientStreamListener;
import p012io.grpc.internal.ClientTransportFactory;
import p012io.grpc.internal.ManagedClientTransport;

/* renamed from: io.grpc.internal.InternalSubchannel */
final class InternalSubchannel implements InternalInstrumented<InternalChannelz.ChannelStats>, TransportProvider {
    /* access modifiers changed from: private */
    @Nullable
    public volatile ManagedClientTransport activeTransport;
    /* access modifiers changed from: private */
    public volatile List<EquivalentAddressGroup> addressGroups;
    /* access modifiers changed from: private */
    public final Index addressIndex;
    private final String authority;
    private final BackoffPolicy.Provider backoffPolicyProvider;
    /* access modifiers changed from: private */
    public final Callback callback;
    /* access modifiers changed from: private */
    public final CallTracer callsTracer;
    /* access modifiers changed from: private */
    public final ChannelLogger channelLogger;
    /* access modifiers changed from: private */
    public final ChannelTracer channelTracer;
    /* access modifiers changed from: private */
    public final InternalChannelz channelz;
    private final Stopwatch connectingTimer;
    /* access modifiers changed from: private */
    public final InUseStateAggregator<ConnectionClientTransport> inUseStateAggregator = new InUseStateAggregator<ConnectionClientTransport>() {
        /* access modifiers changed from: protected */
        public void handleInUse() {
            InternalSubchannel.this.callback.onInUse(InternalSubchannel.this);
        }

        /* access modifiers changed from: protected */
        public void handleNotInUse() {
            InternalSubchannel.this.callback.onNotInUse(InternalSubchannel.this);
        }
    };
    private final InternalLogId logId;
    /* access modifiers changed from: private */
    @Nullable
    public ConnectionClientTransport pendingTransport;
    /* access modifiers changed from: private */
    public BackoffPolicy reconnectPolicy;
    /* access modifiers changed from: private */
    @Nullable
    public SynchronizationContext.ScheduledHandle reconnectTask;
    private final ScheduledExecutorService scheduledExecutor;
    /* access modifiers changed from: private */
    public Status shutdownReason;
    /* access modifiers changed from: private */
    public volatile ConnectivityStateInfo state = ConnectivityStateInfo.forNonError(ConnectivityState.IDLE);
    /* access modifiers changed from: private */
    public final SynchronizationContext syncContext;
    private final ClientTransportFactory transportFactory;
    /* access modifiers changed from: private */
    public final Collection<ConnectionClientTransport> transports = new ArrayList();
    private final String userAgent;

    InternalSubchannel(List<EquivalentAddressGroup> list, String str, String str2, BackoffPolicy.Provider provider, ClientTransportFactory clientTransportFactory, ScheduledExecutorService scheduledExecutorService, Supplier<Stopwatch> supplier, SynchronizationContext synchronizationContext, Callback callback2, InternalChannelz internalChannelz, CallTracer callTracer, ChannelTracer channelTracer2, InternalLogId internalLogId, ChannelLogger channelLogger2) {
        List<EquivalentAddressGroup> list2 = list;
        Preconditions.checkNotNull(list, "addressGroups");
        Preconditions.checkArgument(!list.isEmpty(), "addressGroups is empty");
        checkListHasNoNulls(list, "addressGroups contains null entry");
        List<EquivalentAddressGroup> unmodifiableList = Collections.unmodifiableList(new ArrayList(list));
        this.addressGroups = unmodifiableList;
        this.addressIndex = new Index(unmodifiableList);
        this.authority = str;
        this.userAgent = str2;
        this.backoffPolicyProvider = provider;
        this.transportFactory = clientTransportFactory;
        this.scheduledExecutor = scheduledExecutorService;
        this.connectingTimer = supplier.get();
        this.syncContext = synchronizationContext;
        this.callback = callback2;
        this.channelz = internalChannelz;
        this.callsTracer = callTracer;
        this.channelTracer = (ChannelTracer) Preconditions.checkNotNull(channelTracer2, "channelTracer");
        this.logId = (InternalLogId) Preconditions.checkNotNull(internalLogId, "logId");
        this.channelLogger = (ChannelLogger) Preconditions.checkNotNull(channelLogger2, "channelLogger");
    }

    /* access modifiers changed from: package-private */
    public ChannelLogger getChannelLogger() {
        return this.channelLogger;
    }

    public ClientTransport obtainActiveTransport() {
        ManagedClientTransport managedClientTransport = this.activeTransport;
        if (managedClientTransport != null) {
            return managedClientTransport;
        }
        this.syncContext.execute(new Runnable() {
            public void run() {
                if (InternalSubchannel.this.state.getState() == ConnectivityState.IDLE) {
                    InternalSubchannel.this.channelLogger.log(ChannelLogger.ChannelLogLevel.INFO, "CONNECTING as requested");
                    InternalSubchannel.this.gotoNonErrorState(ConnectivityState.CONNECTING);
                    InternalSubchannel.this.startNewTransport();
                }
            }
        });
        return null;
    }

    /* access modifiers changed from: package-private */
    @Nullable
    public ClientTransport getTransport() {
        return this.activeTransport;
    }

    /* access modifiers changed from: package-private */
    public String getAuthority() {
        return this.authority;
    }

    /* access modifiers changed from: private */
    public void startNewTransport() {
        SocketAddress socketAddress;
        HttpConnectProxiedSocketAddress httpConnectProxiedSocketAddress;
        this.syncContext.throwIfNotInThisSynchronizationContext();
        Preconditions.checkState(this.reconnectTask == null, "Should have no reconnectTask scheduled");
        if (this.addressIndex.isAtBeginning()) {
            this.connectingTimer.reset().start();
        }
        SocketAddress currentAddress = this.addressIndex.getCurrentAddress();
        if (currentAddress instanceof HttpConnectProxiedSocketAddress) {
            httpConnectProxiedSocketAddress = (HttpConnectProxiedSocketAddress) currentAddress;
            socketAddress = httpConnectProxiedSocketAddress.getTargetAddress();
        } else {
            socketAddress = currentAddress;
            httpConnectProxiedSocketAddress = null;
        }
        Attributes currentEagAttributes = this.addressIndex.getCurrentEagAttributes();
        String str = (String) currentEagAttributes.get(EquivalentAddressGroup.ATTR_AUTHORITY_OVERRIDE);
        ClientTransportFactory.ClientTransportOptions clientTransportOptions = new ClientTransportFactory.ClientTransportOptions();
        if (str == null) {
            str = this.authority;
        }
        ClientTransportFactory.ClientTransportOptions httpConnectProxiedSocketAddress2 = clientTransportOptions.setAuthority(str).setEagAttributes(currentEagAttributes).setUserAgent(this.userAgent).setHttpConnectProxiedSocketAddress(httpConnectProxiedSocketAddress);
        TransportLogger transportLogger = new TransportLogger();
        transportLogger.logId = getLogId();
        CallTracingTransport callTracingTransport = new CallTracingTransport(this.transportFactory.newClientTransport(socketAddress, httpConnectProxiedSocketAddress2, transportLogger), this.callsTracer);
        transportLogger.logId = callTracingTransport.getLogId();
        this.channelz.addClientSocket(callTracingTransport);
        this.pendingTransport = callTracingTransport;
        this.transports.add(callTracingTransport);
        Runnable start = callTracingTransport.start(new TransportListener(callTracingTransport, socketAddress));
        if (start != null) {
            this.syncContext.executeLater(start);
        }
        this.channelLogger.log(ChannelLogger.ChannelLogLevel.INFO, "Started transport {0}", transportLogger.logId);
    }

    /* access modifiers changed from: private */
    public void scheduleBackoff(Status status) {
        this.syncContext.throwIfNotInThisSynchronizationContext();
        gotoState(ConnectivityStateInfo.forTransientFailure(status));
        if (this.reconnectPolicy == null) {
            this.reconnectPolicy = this.backoffPolicyProvider.get();
        }
        long nextBackoffNanos = this.reconnectPolicy.nextBackoffNanos() - this.connectingTimer.elapsed(TimeUnit.NANOSECONDS);
        boolean z = false;
        this.channelLogger.log(ChannelLogger.ChannelLogLevel.INFO, "TRANSIENT_FAILURE ({0}). Will reconnect after {1} ns", printShortStatus(status), Long.valueOf(nextBackoffNanos));
        if (this.reconnectTask == null) {
            z = true;
        }
        Preconditions.checkState(z, "previous reconnectTask is not done");
        this.reconnectTask = this.syncContext.schedule(new Runnable() {
            public void run() {
                SynchronizationContext.ScheduledHandle unused = InternalSubchannel.this.reconnectTask = null;
                InternalSubchannel.this.channelLogger.log(ChannelLogger.ChannelLogLevel.INFO, "CONNECTING after backoff");
                InternalSubchannel.this.gotoNonErrorState(ConnectivityState.CONNECTING);
                InternalSubchannel.this.startNewTransport();
            }
        }, nextBackoffNanos, TimeUnit.NANOSECONDS, this.scheduledExecutor);
    }

    /* access modifiers changed from: package-private */
    public void resetConnectBackoff() {
        this.syncContext.execute(new Runnable() {
            public void run() {
                if (InternalSubchannel.this.state.getState() == ConnectivityState.TRANSIENT_FAILURE) {
                    InternalSubchannel.this.cancelReconnectTask();
                    InternalSubchannel.this.channelLogger.log(ChannelLogger.ChannelLogLevel.INFO, "CONNECTING; backoff interrupted");
                    InternalSubchannel.this.gotoNonErrorState(ConnectivityState.CONNECTING);
                    InternalSubchannel.this.startNewTransport();
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public void gotoNonErrorState(ConnectivityState connectivityState) {
        this.syncContext.throwIfNotInThisSynchronizationContext();
        gotoState(ConnectivityStateInfo.forNonError(connectivityState));
    }

    private void gotoState(ConnectivityStateInfo connectivityStateInfo) {
        this.syncContext.throwIfNotInThisSynchronizationContext();
        if (this.state.getState() != connectivityStateInfo.getState()) {
            boolean z = this.state.getState() != ConnectivityState.SHUTDOWN;
            Preconditions.checkState(z, "Cannot transition out of SHUTDOWN to " + connectivityStateInfo);
            this.state = connectivityStateInfo;
            this.callback.onStateChange(this, connectivityStateInfo);
        }
    }

    public void updateAddresses(final List<EquivalentAddressGroup> list) {
        Preconditions.checkNotNull(list, "newAddressGroups");
        checkListHasNoNulls(list, "newAddressGroups contains null entry");
        Preconditions.checkArgument(!list.isEmpty(), "newAddressGroups is empty");
        this.syncContext.execute(new Runnable() {
            public void run() {
                ManagedClientTransport managedClientTransport;
                List unmodifiableList = Collections.unmodifiableList(new ArrayList(list));
                SocketAddress currentAddress = InternalSubchannel.this.addressIndex.getCurrentAddress();
                InternalSubchannel.this.addressIndex.updateGroups(unmodifiableList);
                List unused = InternalSubchannel.this.addressGroups = unmodifiableList;
                ManagedClientTransport managedClientTransport2 = null;
                if ((InternalSubchannel.this.state.getState() == ConnectivityState.READY || InternalSubchannel.this.state.getState() == ConnectivityState.CONNECTING) && !InternalSubchannel.this.addressIndex.seekTo(currentAddress)) {
                    if (InternalSubchannel.this.state.getState() == ConnectivityState.READY) {
                        managedClientTransport = InternalSubchannel.this.activeTransport;
                        ManagedClientTransport unused2 = InternalSubchannel.this.activeTransport = null;
                        InternalSubchannel.this.addressIndex.reset();
                        InternalSubchannel.this.gotoNonErrorState(ConnectivityState.IDLE);
                    } else {
                        managedClientTransport = InternalSubchannel.this.pendingTransport;
                        ConnectionClientTransport unused3 = InternalSubchannel.this.pendingTransport = null;
                        InternalSubchannel.this.addressIndex.reset();
                        InternalSubchannel.this.startNewTransport();
                    }
                    managedClientTransport2 = managedClientTransport;
                }
                if (managedClientTransport2 != null) {
                    managedClientTransport2.shutdown(Status.UNAVAILABLE.withDescription("InternalSubchannel closed transport due to address change"));
                }
            }
        });
    }

    public void shutdown(final Status status) {
        this.syncContext.execute(new Runnable() {
            public void run() {
                if (InternalSubchannel.this.state.getState() != ConnectivityState.SHUTDOWN) {
                    Status unused = InternalSubchannel.this.shutdownReason = status;
                    ManagedClientTransport access$1000 = InternalSubchannel.this.activeTransport;
                    ConnectionClientTransport access$1100 = InternalSubchannel.this.pendingTransport;
                    ManagedClientTransport unused2 = InternalSubchannel.this.activeTransport = null;
                    ConnectionClientTransport unused3 = InternalSubchannel.this.pendingTransport = null;
                    InternalSubchannel.this.gotoNonErrorState(ConnectivityState.SHUTDOWN);
                    InternalSubchannel.this.addressIndex.reset();
                    if (InternalSubchannel.this.transports.isEmpty()) {
                        InternalSubchannel.this.handleTermination();
                    }
                    InternalSubchannel.this.cancelReconnectTask();
                    if (access$1000 != null) {
                        access$1000.shutdown(status);
                    }
                    if (access$1100 != null) {
                        access$1100.shutdown(status);
                    }
                }
            }
        });
    }

    public String toString() {
        return MoreObjects.toStringHelper((Object) this).add("logId", this.logId.getId()).add("addressGroups", (Object) this.addressGroups).toString();
    }

    /* access modifiers changed from: private */
    public void handleTermination() {
        this.syncContext.execute(new Runnable() {
            public void run() {
                InternalSubchannel.this.channelLogger.log(ChannelLogger.ChannelLogLevel.INFO, "Terminated");
                InternalSubchannel.this.callback.onTerminated(InternalSubchannel.this);
            }
        });
    }

    /* access modifiers changed from: private */
    public void handleTransportInUseState(final ConnectionClientTransport connectionClientTransport, final boolean z) {
        this.syncContext.execute(new Runnable() {
            public void run() {
                InternalSubchannel.this.inUseStateAggregator.updateObjectInUse(connectionClientTransport, z);
            }
        });
    }

    /* access modifiers changed from: package-private */
    public void shutdownNow(final Status status) {
        shutdown(status);
        this.syncContext.execute(new Runnable() {
            public void run() {
                for (ManagedClientTransport shutdownNow : new ArrayList(InternalSubchannel.this.transports)) {
                    shutdownNow.shutdownNow(status);
                }
            }
        });
    }

    /* access modifiers changed from: package-private */
    public List<EquivalentAddressGroup> getAddressGroups() {
        return this.addressGroups;
    }

    /* access modifiers changed from: private */
    public void cancelReconnectTask() {
        this.syncContext.throwIfNotInThisSynchronizationContext();
        SynchronizationContext.ScheduledHandle scheduledHandle = this.reconnectTask;
        if (scheduledHandle != null) {
            scheduledHandle.cancel();
            this.reconnectTask = null;
            this.reconnectPolicy = null;
        }
    }

    public InternalLogId getLogId() {
        return this.logId;
    }

    public ListenableFuture<InternalChannelz.ChannelStats> getStats() {
        final SettableFuture create = SettableFuture.create();
        this.syncContext.execute(new Runnable() {
            public void run() {
                InternalChannelz.ChannelStats.Builder builder = new InternalChannelz.ChannelStats.Builder();
                List<EquivalentAddressGroup> groups = InternalSubchannel.this.addressIndex.getGroups();
                ArrayList arrayList = new ArrayList(InternalSubchannel.this.transports);
                builder.setTarget(groups.toString()).setState(InternalSubchannel.this.getState());
                builder.setSockets(arrayList);
                InternalSubchannel.this.callsTracer.updateBuilder(builder);
                InternalSubchannel.this.channelTracer.updateBuilder(builder);
                create.set(builder.build());
            }
        });
        return create;
    }

    /* access modifiers changed from: package-private */
    public ConnectivityState getState() {
        return this.state.getState();
    }

    private static void checkListHasNoNulls(List<?> list, String str) {
        for (Object checkNotNull : list) {
            Preconditions.checkNotNull(checkNotNull, str);
        }
    }

    /* renamed from: io.grpc.internal.InternalSubchannel$TransportListener */
    private class TransportListener implements ManagedClientTransport.C2463Listener {
        final SocketAddress address;
        boolean shutdownInitiated = false;
        final ConnectionClientTransport transport;

        TransportListener(ConnectionClientTransport connectionClientTransport, SocketAddress socketAddress) {
            this.transport = connectionClientTransport;
            this.address = socketAddress;
        }

        public void transportReady() {
            InternalSubchannel.this.channelLogger.log(ChannelLogger.ChannelLogLevel.INFO, "READY");
            InternalSubchannel.this.syncContext.execute(new Runnable() {
                public void run() {
                    BackoffPolicy unused = InternalSubchannel.this.reconnectPolicy = null;
                    if (InternalSubchannel.this.shutdownReason != null) {
                        Preconditions.checkState(InternalSubchannel.this.activeTransport == null, "Unexpected non-null activeTransport");
                        TransportListener.this.transport.shutdown(InternalSubchannel.this.shutdownReason);
                    } else if (InternalSubchannel.this.pendingTransport == TransportListener.this.transport) {
                        ManagedClientTransport unused2 = InternalSubchannel.this.activeTransport = TransportListener.this.transport;
                        ConnectionClientTransport unused3 = InternalSubchannel.this.pendingTransport = null;
                        InternalSubchannel.this.gotoNonErrorState(ConnectivityState.READY);
                    }
                }
            });
        }

        public void transportInUse(boolean z) {
            InternalSubchannel.this.handleTransportInUseState(this.transport, z);
        }

        public void transportShutdown(final Status status) {
            InternalSubchannel.this.channelLogger.log(ChannelLogger.ChannelLogLevel.INFO, "{0} SHUTDOWN with {1}", this.transport.getLogId(), InternalSubchannel.this.printShortStatus(status));
            this.shutdownInitiated = true;
            InternalSubchannel.this.syncContext.execute(new Runnable() {
                public void run() {
                    if (InternalSubchannel.this.state.getState() != ConnectivityState.SHUTDOWN) {
                        if (InternalSubchannel.this.activeTransport == TransportListener.this.transport) {
                            ManagedClientTransport unused = InternalSubchannel.this.activeTransport = null;
                            InternalSubchannel.this.addressIndex.reset();
                            InternalSubchannel.this.gotoNonErrorState(ConnectivityState.IDLE);
                        } else if (InternalSubchannel.this.pendingTransport == TransportListener.this.transport) {
                            Preconditions.checkState(InternalSubchannel.this.state.getState() == ConnectivityState.CONNECTING, "Expected state is CONNECTING, actual state is %s", (Object) InternalSubchannel.this.state.getState());
                            InternalSubchannel.this.addressIndex.increment();
                            if (!InternalSubchannel.this.addressIndex.isValid()) {
                                ConnectionClientTransport unused2 = InternalSubchannel.this.pendingTransport = null;
                                InternalSubchannel.this.addressIndex.reset();
                                InternalSubchannel.this.scheduleBackoff(status);
                                return;
                            }
                            InternalSubchannel.this.startNewTransport();
                        }
                    }
                }
            });
        }

        public void transportTerminated() {
            Preconditions.checkState(this.shutdownInitiated, "transportShutdown() must be called before transportTerminated().");
            InternalSubchannel.this.channelLogger.log(ChannelLogger.ChannelLogLevel.INFO, "{0} Terminated", this.transport.getLogId());
            InternalSubchannel.this.channelz.removeClientSocket(this.transport);
            InternalSubchannel.this.handleTransportInUseState(this.transport, false);
            InternalSubchannel.this.syncContext.execute(new Runnable() {
                public void run() {
                    InternalSubchannel.this.transports.remove(TransportListener.this.transport);
                    if (InternalSubchannel.this.state.getState() == ConnectivityState.SHUTDOWN && InternalSubchannel.this.transports.isEmpty()) {
                        InternalSubchannel.this.handleTermination();
                    }
                }
            });
        }
    }

    /* renamed from: io.grpc.internal.InternalSubchannel$Callback */
    static abstract class Callback {
        /* access modifiers changed from: package-private */
        public void onInUse(InternalSubchannel internalSubchannel) {
        }

        /* access modifiers changed from: package-private */
        public void onNotInUse(InternalSubchannel internalSubchannel) {
        }

        /* access modifiers changed from: package-private */
        public void onStateChange(InternalSubchannel internalSubchannel, ConnectivityStateInfo connectivityStateInfo) {
        }

        /* access modifiers changed from: package-private */
        public void onTerminated(InternalSubchannel internalSubchannel) {
        }

        Callback() {
        }
    }

    /* renamed from: io.grpc.internal.InternalSubchannel$CallTracingTransport */
    static final class CallTracingTransport extends ForwardingConnectionClientTransport {
        /* access modifiers changed from: private */
        public final CallTracer callTracer;
        private final ConnectionClientTransport delegate;

        private CallTracingTransport(ConnectionClientTransport connectionClientTransport, CallTracer callTracer2) {
            this.delegate = connectionClientTransport;
            this.callTracer = callTracer2;
        }

        /* access modifiers changed from: protected */
        public ConnectionClientTransport delegate() {
            return this.delegate;
        }

        public ClientStream newStream(MethodDescriptor<?, ?> methodDescriptor, Metadata metadata, CallOptions callOptions) {
            final ClientStream newStream = super.newStream(methodDescriptor, metadata, callOptions);
            return new ForwardingClientStream() {
                /* access modifiers changed from: protected */
                public ClientStream delegate() {
                    return newStream;
                }

                public void start(final ClientStreamListener clientStreamListener) {
                    CallTracingTransport.this.callTracer.reportCallStarted();
                    super.start(new ForwardingClientStreamListener() {
                        /* access modifiers changed from: protected */
                        public ClientStreamListener delegate() {
                            return clientStreamListener;
                        }

                        public void closed(Status status, Metadata metadata) {
                            CallTracingTransport.this.callTracer.reportCallEnded(status.isOk());
                            super.closed(status, metadata);
                        }

                        public void closed(Status status, ClientStreamListener.RpcProgress rpcProgress, Metadata metadata) {
                            CallTracingTransport.this.callTracer.reportCallEnded(status.isOk());
                            super.closed(status, rpcProgress, metadata);
                        }
                    });
                }
            };
        }
    }

    /* renamed from: io.grpc.internal.InternalSubchannel$Index */
    static final class Index {
        private List<EquivalentAddressGroup> addressGroups;
        private int addressIndex;
        private int groupIndex;

        public Index(List<EquivalentAddressGroup> list) {
            this.addressGroups = list;
        }

        public boolean isValid() {
            return this.groupIndex < this.addressGroups.size();
        }

        public boolean isAtBeginning() {
            return this.groupIndex == 0 && this.addressIndex == 0;
        }

        public void increment() {
            int i = this.addressIndex + 1;
            this.addressIndex = i;
            if (i >= this.addressGroups.get(this.groupIndex).getAddresses().size()) {
                this.groupIndex++;
                this.addressIndex = 0;
            }
        }

        public void reset() {
            this.groupIndex = 0;
            this.addressIndex = 0;
        }

        public SocketAddress getCurrentAddress() {
            return this.addressGroups.get(this.groupIndex).getAddresses().get(this.addressIndex);
        }

        public Attributes getCurrentEagAttributes() {
            return this.addressGroups.get(this.groupIndex).getAttributes();
        }

        public List<EquivalentAddressGroup> getGroups() {
            return this.addressGroups;
        }

        public void updateGroups(List<EquivalentAddressGroup> list) {
            this.addressGroups = list;
            reset();
        }

        public boolean seekTo(SocketAddress socketAddress) {
            int i = 0;
            while (i < this.addressGroups.size()) {
                int indexOf = this.addressGroups.get(i).getAddresses().indexOf(socketAddress);
                if (indexOf == -1) {
                    i++;
                } else {
                    this.groupIndex = i;
                    this.addressIndex = indexOf;
                    return true;
                }
            }
            return false;
        }
    }

    /* access modifiers changed from: private */
    public String printShortStatus(Status status) {
        StringBuilder sb = new StringBuilder();
        sb.append(status.getCode());
        if (status.getDescription() != null) {
            sb.append("(");
            sb.append(status.getDescription());
            sb.append(")");
        }
        return sb.toString();
    }

    /* renamed from: io.grpc.internal.InternalSubchannel$TransportLogger */
    static final class TransportLogger extends ChannelLogger {
        InternalLogId logId;

        TransportLogger() {
        }

        public void log(ChannelLogger.ChannelLogLevel channelLogLevel, String str) {
            ChannelLoggerImpl.logOnly(this.logId, channelLogLevel, str);
        }

        public void log(ChannelLogger.ChannelLogLevel channelLogLevel, String str, Object... objArr) {
            ChannelLoggerImpl.logOnly(this.logId, channelLogLevel, str, objArr);
        }
    }
}

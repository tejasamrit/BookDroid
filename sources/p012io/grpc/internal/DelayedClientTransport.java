package p012io.grpc.internal;

import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.SettableFuture;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.concurrent.Executor;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import p012io.grpc.CallOptions;
import p012io.grpc.Context;
import p012io.grpc.InternalChannelz;
import p012io.grpc.InternalLogId;
import p012io.grpc.LoadBalancer;
import p012io.grpc.Metadata;
import p012io.grpc.MethodDescriptor;
import p012io.grpc.Status;
import p012io.grpc.SynchronizationContext;
import p012io.grpc.internal.ClientTransport;
import p012io.grpc.internal.ManagedClientTransport;

/* renamed from: io.grpc.internal.DelayedClientTransport */
final class DelayedClientTransport implements ManagedClientTransport {
    private final Executor defaultAppExecutor;
    @Nullable
    private LoadBalancer.SubchannelPicker lastPicker;
    private long lastPickerVersion;
    /* access modifiers changed from: private */
    public ManagedClientTransport.C2463Listener listener;
    /* access modifiers changed from: private */
    public final Object lock = new Object();
    private final InternalLogId logId = InternalLogId.allocate((Class<?>) DelayedClientTransport.class, (String) null);
    /* access modifiers changed from: private */
    @Nonnull
    public Collection<PendingStream> pendingStreams = new LinkedHashSet();
    private Runnable reportTransportInUse;
    /* access modifiers changed from: private */
    public Runnable reportTransportNotInUse;
    /* access modifiers changed from: private */
    public Runnable reportTransportTerminated;
    /* access modifiers changed from: private */
    public Status shutdownStatus;
    /* access modifiers changed from: private */
    public final SynchronizationContext syncContext;

    DelayedClientTransport(Executor executor, SynchronizationContext synchronizationContext) {
        this.defaultAppExecutor = executor;
        this.syncContext = synchronizationContext;
    }

    public final Runnable start(final ManagedClientTransport.C2463Listener listener2) {
        this.listener = listener2;
        this.reportTransportInUse = new Runnable() {
            public void run() {
                listener2.transportInUse(true);
            }
        };
        this.reportTransportNotInUse = new Runnable() {
            public void run() {
                listener2.transportInUse(false);
            }
        };
        this.reportTransportTerminated = new Runnable() {
            public void run() {
                listener2.transportTerminated();
            }
        };
        return null;
    }

    public final ClientStream newStream(MethodDescriptor<?, ?> methodDescriptor, Metadata metadata, CallOptions callOptions) {
        ClientStream failingClientStream;
        try {
            PickSubchannelArgsImpl pickSubchannelArgsImpl = new PickSubchannelArgsImpl(methodDescriptor, metadata, callOptions);
            LoadBalancer.SubchannelPicker subchannelPicker = null;
            long j = -1;
            while (true) {
                synchronized (this.lock) {
                    if (this.shutdownStatus == null) {
                        LoadBalancer.SubchannelPicker subchannelPicker2 = this.lastPicker;
                        if (subchannelPicker2 != null) {
                            if (subchannelPicker != null && j == this.lastPickerVersion) {
                                failingClientStream = createPendingStream(pickSubchannelArgsImpl);
                                break;
                            }
                            j = this.lastPickerVersion;
                            ClientTransport transportFromPickResult = GrpcUtil.getTransportFromPickResult(subchannelPicker2.pickSubchannel(pickSubchannelArgsImpl), callOptions.isWaitForReady());
                            if (transportFromPickResult != null) {
                                failingClientStream = transportFromPickResult.newStream(pickSubchannelArgsImpl.getMethodDescriptor(), pickSubchannelArgsImpl.getHeaders(), pickSubchannelArgsImpl.getCallOptions());
                                break;
                            }
                            subchannelPicker = subchannelPicker2;
                        } else {
                            failingClientStream = createPendingStream(pickSubchannelArgsImpl);
                            break;
                        }
                    } else {
                        failingClientStream = new FailingClientStream(this.shutdownStatus);
                        break;
                    }
                }
            }
            this.syncContext.drain();
            return failingClientStream;
        } catch (Throwable th) {
            this.syncContext.drain();
            throw th;
        }
    }

    private PendingStream createPendingStream(LoadBalancer.PickSubchannelArgs pickSubchannelArgs) {
        PendingStream pendingStream = new PendingStream(pickSubchannelArgs);
        this.pendingStreams.add(pendingStream);
        if (getPendingStreamsCount() == 1) {
            this.syncContext.executeLater(this.reportTransportInUse);
        }
        return pendingStream;
    }

    public final void ping(ClientTransport.PingCallback pingCallback, Executor executor) {
        throw new UnsupportedOperationException("This method is not expected to be called");
    }

    public ListenableFuture<InternalChannelz.SocketStats> getStats() {
        SettableFuture create = SettableFuture.create();
        create.set(null);
        return create;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0028, code lost:
        r3.syncContext.drain();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x002d, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void shutdown(final p012io.grpc.Status r4) {
        /*
            r3 = this;
            java.lang.Object r0 = r3.lock
            monitor-enter(r0)
            io.grpc.Status r1 = r3.shutdownStatus     // Catch:{ all -> 0x002e }
            if (r1 == 0) goto L_0x0009
            monitor-exit(r0)     // Catch:{ all -> 0x002e }
            return
        L_0x0009:
            r3.shutdownStatus = r4     // Catch:{ all -> 0x002e }
            io.grpc.SynchronizationContext r1 = r3.syncContext     // Catch:{ all -> 0x002e }
            io.grpc.internal.DelayedClientTransport$4 r2 = new io.grpc.internal.DelayedClientTransport$4     // Catch:{ all -> 0x002e }
            r2.<init>(r4)     // Catch:{ all -> 0x002e }
            r1.executeLater(r2)     // Catch:{ all -> 0x002e }
            boolean r4 = r3.hasPendingStreams()     // Catch:{ all -> 0x002e }
            if (r4 != 0) goto L_0x0027
            java.lang.Runnable r4 = r3.reportTransportTerminated     // Catch:{ all -> 0x002e }
            if (r4 == 0) goto L_0x0027
            io.grpc.SynchronizationContext r1 = r3.syncContext     // Catch:{ all -> 0x002e }
            r1.executeLater(r4)     // Catch:{ all -> 0x002e }
            r4 = 0
            r3.reportTransportTerminated = r4     // Catch:{ all -> 0x002e }
        L_0x0027:
            monitor-exit(r0)     // Catch:{ all -> 0x002e }
            io.grpc.SynchronizationContext r4 = r3.syncContext
            r4.drain()
            return
        L_0x002e:
            r4 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x002e }
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: p012io.grpc.internal.DelayedClientTransport.shutdown(io.grpc.Status):void");
    }

    public final void shutdownNow(Status status) {
        Collection<PendingStream> collection;
        Runnable runnable;
        shutdown(status);
        synchronized (this.lock) {
            collection = this.pendingStreams;
            runnable = this.reportTransportTerminated;
            this.reportTransportTerminated = null;
            if (!collection.isEmpty()) {
                this.pendingStreams = Collections.emptyList();
            }
        }
        if (runnable != null) {
            for (PendingStream cancel : collection) {
                cancel.cancel(status);
            }
            this.syncContext.execute(runnable);
        }
    }

    public final boolean hasPendingStreams() {
        boolean z;
        synchronized (this.lock) {
            z = !this.pendingStreams.isEmpty();
        }
        return z;
    }

    /* access modifiers changed from: package-private */
    public final int getPendingStreamsCount() {
        int size;
        synchronized (this.lock) {
            size = this.pendingStreams.size();
        }
        return size;
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:10:0x001e, code lost:
        r0 = new java.util.ArrayList();
        r1 = r1.iterator();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x002b, code lost:
        if (r1.hasNext() == false) goto L_0x0065;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x002d, code lost:
        r2 = (p012io.grpc.internal.DelayedClientTransport.PendingStream) r1.next();
        r3 = r8.pickSubchannel(p012io.grpc.internal.DelayedClientTransport.PendingStream.access$200(r2));
        r4 = p012io.grpc.internal.DelayedClientTransport.PendingStream.access$200(r2).getCallOptions();
        r3 = p012io.grpc.internal.GrpcUtil.getTransportFromPickResult(r3, r4.isWaitForReady());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x004b, code lost:
        if (r3 == null) goto L_0x0027;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x004d, code lost:
        r5 = r7.defaultAppExecutor;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0053, code lost:
        if (r4.getExecutor() == null) goto L_0x0059;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0055, code lost:
        r5 = r4.getExecutor();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0059, code lost:
        r5.execute(new p012io.grpc.internal.DelayedClientTransport.C24065(r7));
        r0.add(r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0065, code lost:
        r8 = r7.lock;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0067, code lost:
        monitor-enter(r8);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x006c, code lost:
        if (hasPendingStreams() != false) goto L_0x0070;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x006e, code lost:
        monitor-exit(r8);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x006f, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x0070, code lost:
        r7.pendingStreams.removeAll(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x007b, code lost:
        if (r7.pendingStreams.isEmpty() == false) goto L_0x0084;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x007d, code lost:
        r7.pendingStreams = new java.util.LinkedHashSet();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x0088, code lost:
        if (hasPendingStreams() != false) goto L_0x00a1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x008a, code lost:
        r7.syncContext.executeLater(r7.reportTransportNotInUse);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x0093, code lost:
        if (r7.shutdownStatus == null) goto L_0x00a1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x0095, code lost:
        r0 = r7.reportTransportTerminated;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x0097, code lost:
        if (r0 == null) goto L_0x00a1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x0099, code lost:
        r7.syncContext.executeLater(r0);
        r7.reportTransportTerminated = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x00a1, code lost:
        monitor-exit(r8);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x00a2, code lost:
        r7.syncContext.drain();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x00a7, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void reprocess(@javax.annotation.Nullable p012io.grpc.LoadBalancer.SubchannelPicker r8) {
        /*
            r7 = this;
            java.lang.Object r0 = r7.lock
            monitor-enter(r0)
            r7.lastPicker = r8     // Catch:{ all -> 0x00ad }
            long r1 = r7.lastPickerVersion     // Catch:{ all -> 0x00ad }
            r3 = 1
            long r1 = r1 + r3
            r7.lastPickerVersion = r1     // Catch:{ all -> 0x00ad }
            if (r8 == 0) goto L_0x00ab
            boolean r1 = r7.hasPendingStreams()     // Catch:{ all -> 0x00ad }
            if (r1 != 0) goto L_0x0016
            goto L_0x00ab
        L_0x0016:
            java.util.ArrayList r1 = new java.util.ArrayList     // Catch:{ all -> 0x00ad }
            java.util.Collection<io.grpc.internal.DelayedClientTransport$PendingStream> r2 = r7.pendingStreams     // Catch:{ all -> 0x00ad }
            r1.<init>(r2)     // Catch:{ all -> 0x00ad }
            monitor-exit(r0)     // Catch:{ all -> 0x00ad }
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            java.util.Iterator r1 = r1.iterator()
        L_0x0027:
            boolean r2 = r1.hasNext()
            if (r2 == 0) goto L_0x0065
            java.lang.Object r2 = r1.next()
            io.grpc.internal.DelayedClientTransport$PendingStream r2 = (p012io.grpc.internal.DelayedClientTransport.PendingStream) r2
            io.grpc.LoadBalancer$PickSubchannelArgs r3 = r2.args
            io.grpc.LoadBalancer$PickResult r3 = r8.pickSubchannel(r3)
            io.grpc.LoadBalancer$PickSubchannelArgs r4 = r2.args
            io.grpc.CallOptions r4 = r4.getCallOptions()
            boolean r5 = r4.isWaitForReady()
            io.grpc.internal.ClientTransport r3 = p012io.grpc.internal.GrpcUtil.getTransportFromPickResult(r3, r5)
            if (r3 == 0) goto L_0x0027
            java.util.concurrent.Executor r5 = r7.defaultAppExecutor
            java.util.concurrent.Executor r6 = r4.getExecutor()
            if (r6 == 0) goto L_0x0059
            java.util.concurrent.Executor r5 = r4.getExecutor()
        L_0x0059:
            io.grpc.internal.DelayedClientTransport$5 r4 = new io.grpc.internal.DelayedClientTransport$5
            r4.<init>(r2, r3)
            r5.execute(r4)
            r0.add(r2)
            goto L_0x0027
        L_0x0065:
            java.lang.Object r8 = r7.lock
            monitor-enter(r8)
            boolean r1 = r7.hasPendingStreams()     // Catch:{ all -> 0x00a8 }
            if (r1 != 0) goto L_0x0070
            monitor-exit(r8)     // Catch:{ all -> 0x00a8 }
            return
        L_0x0070:
            java.util.Collection<io.grpc.internal.DelayedClientTransport$PendingStream> r1 = r7.pendingStreams     // Catch:{ all -> 0x00a8 }
            r1.removeAll(r0)     // Catch:{ all -> 0x00a8 }
            java.util.Collection<io.grpc.internal.DelayedClientTransport$PendingStream> r0 = r7.pendingStreams     // Catch:{ all -> 0x00a8 }
            boolean r0 = r0.isEmpty()     // Catch:{ all -> 0x00a8 }
            if (r0 == 0) goto L_0x0084
            java.util.LinkedHashSet r0 = new java.util.LinkedHashSet     // Catch:{ all -> 0x00a8 }
            r0.<init>()     // Catch:{ all -> 0x00a8 }
            r7.pendingStreams = r0     // Catch:{ all -> 0x00a8 }
        L_0x0084:
            boolean r0 = r7.hasPendingStreams()     // Catch:{ all -> 0x00a8 }
            if (r0 != 0) goto L_0x00a1
            io.grpc.SynchronizationContext r0 = r7.syncContext     // Catch:{ all -> 0x00a8 }
            java.lang.Runnable r1 = r7.reportTransportNotInUse     // Catch:{ all -> 0x00a8 }
            r0.executeLater(r1)     // Catch:{ all -> 0x00a8 }
            io.grpc.Status r0 = r7.shutdownStatus     // Catch:{ all -> 0x00a8 }
            if (r0 == 0) goto L_0x00a1
            java.lang.Runnable r0 = r7.reportTransportTerminated     // Catch:{ all -> 0x00a8 }
            if (r0 == 0) goto L_0x00a1
            io.grpc.SynchronizationContext r1 = r7.syncContext     // Catch:{ all -> 0x00a8 }
            r1.executeLater(r0)     // Catch:{ all -> 0x00a8 }
            r0 = 0
            r7.reportTransportTerminated = r0     // Catch:{ all -> 0x00a8 }
        L_0x00a1:
            monitor-exit(r8)     // Catch:{ all -> 0x00a8 }
            io.grpc.SynchronizationContext r8 = r7.syncContext
            r8.drain()
            return
        L_0x00a8:
            r0 = move-exception
            monitor-exit(r8)     // Catch:{ all -> 0x00a8 }
            throw r0
        L_0x00ab:
            monitor-exit(r0)     // Catch:{ all -> 0x00ad }
            return
        L_0x00ad:
            r8 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x00ad }
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: p012io.grpc.internal.DelayedClientTransport.reprocess(io.grpc.LoadBalancer$SubchannelPicker):void");
    }

    public InternalLogId getLogId() {
        return this.logId;
    }

    /* renamed from: io.grpc.internal.DelayedClientTransport$PendingStream */
    private class PendingStream extends DelayedStream {
        /* access modifiers changed from: private */
        public final LoadBalancer.PickSubchannelArgs args;
        private final Context context;

        private PendingStream(LoadBalancer.PickSubchannelArgs pickSubchannelArgs) {
            this.context = Context.current();
            this.args = pickSubchannelArgs;
        }

        /* JADX INFO: finally extract failed */
        /* access modifiers changed from: private */
        public void createRealStream(ClientTransport clientTransport) {
            Context attach = this.context.attach();
            try {
                ClientStream newStream = clientTransport.newStream(this.args.getMethodDescriptor(), this.args.getHeaders(), this.args.getCallOptions());
                this.context.detach(attach);
                setStream(newStream);
            } catch (Throwable th) {
                this.context.detach(attach);
                throw th;
            }
        }

        public void cancel(Status status) {
            super.cancel(status);
            synchronized (DelayedClientTransport.this.lock) {
                if (DelayedClientTransport.this.reportTransportTerminated != null) {
                    boolean remove = DelayedClientTransport.this.pendingStreams.remove(this);
                    if (!DelayedClientTransport.this.hasPendingStreams() && remove) {
                        DelayedClientTransport.this.syncContext.executeLater(DelayedClientTransport.this.reportTransportNotInUse);
                        if (DelayedClientTransport.this.shutdownStatus != null) {
                            DelayedClientTransport.this.syncContext.executeLater(DelayedClientTransport.this.reportTransportTerminated);
                            Runnable unused = DelayedClientTransport.this.reportTransportTerminated = null;
                        }
                    }
                }
            }
            DelayedClientTransport.this.syncContext.drain();
        }
    }
}

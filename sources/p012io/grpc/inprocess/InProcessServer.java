package p012io.grpc.inprocess;

import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import java.io.IOException;
import java.net.SocketAddress;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ScheduledExecutorService;
import p012io.grpc.InternalChannelz;
import p012io.grpc.InternalInstrumented;
import p012io.grpc.ServerStreamTracer;
import p012io.grpc.internal.InternalServer;
import p012io.grpc.internal.ObjectPool;
import p012io.grpc.internal.ServerListener;
import p012io.grpc.internal.ServerTransportListener;

/* renamed from: io.grpc.inprocess.InProcessServer */
final class InProcessServer implements InternalServer {
    private static final ConcurrentMap<String, InProcessServer> registry = new ConcurrentHashMap();
    private ServerListener listener;
    private final int maxInboundMetadataSize;
    private final String name;
    private ScheduledExecutorService scheduler;
    private final ObjectPool<ScheduledExecutorService> schedulerPool;
    private boolean shutdown;
    private final List<ServerStreamTracer.Factory> streamTracerFactories;

    public InternalInstrumented<InternalChannelz.SocketStats> getListenSocketStats() {
        return null;
    }

    static InProcessServer findServer(String str) {
        return (InProcessServer) registry.get(str);
    }

    InProcessServer(InProcessServerBuilder inProcessServerBuilder, List<? extends ServerStreamTracer.Factory> list) {
        this.name = inProcessServerBuilder.name;
        this.schedulerPool = inProcessServerBuilder.schedulerPool;
        this.maxInboundMetadataSize = inProcessServerBuilder.maxInboundMetadataSize;
        this.streamTracerFactories = Collections.unmodifiableList((List) Preconditions.checkNotNull(list, "streamTracerFactories"));
    }

    public void start(ServerListener serverListener) throws IOException {
        this.listener = serverListener;
        this.scheduler = this.schedulerPool.getObject();
        if (registry.putIfAbsent(this.name, this) != null) {
            throw new IOException("name already registered: " + this.name);
        }
    }

    public SocketAddress getListenSocketAddress() {
        return new InProcessSocketAddress(this.name);
    }

    public void shutdown() {
        if (registry.remove(this.name, this)) {
            this.scheduler = this.schedulerPool.returnObject(this.scheduler);
            synchronized (this) {
                this.shutdown = true;
                this.listener.serverShutdown();
            }
            return;
        }
        throw new AssertionError();
    }

    public String toString() {
        return MoreObjects.toStringHelper((Object) this).add("name", (Object) this.name).toString();
    }

    /* access modifiers changed from: package-private */
    public synchronized ServerTransportListener register(InProcessTransport inProcessTransport) {
        if (this.shutdown) {
            return null;
        }
        return this.listener.transportCreated(inProcessTransport);
    }

    /* access modifiers changed from: package-private */
    public ObjectPool<ScheduledExecutorService> getScheduledExecutorServicePool() {
        return this.schedulerPool;
    }

    /* access modifiers changed from: package-private */
    public int getMaxInboundMetadataSize() {
        return this.maxInboundMetadataSize;
    }

    /* access modifiers changed from: package-private */
    public List<ServerStreamTracer.Factory> getStreamTracerFactories() {
        return this.streamTracerFactories;
    }
}

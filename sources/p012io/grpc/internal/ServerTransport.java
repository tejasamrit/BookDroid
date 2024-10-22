package p012io.grpc.internal;

import java.util.concurrent.ScheduledExecutorService;
import p012io.grpc.InternalChannelz;
import p012io.grpc.InternalInstrumented;
import p012io.grpc.Status;

/* renamed from: io.grpc.internal.ServerTransport */
public interface ServerTransport extends InternalInstrumented<InternalChannelz.SocketStats> {
    ScheduledExecutorService getScheduledExecutorService();

    void shutdown();

    void shutdownNow(Status status);
}

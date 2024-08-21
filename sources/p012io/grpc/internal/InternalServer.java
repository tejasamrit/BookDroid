package p012io.grpc.internal;

import java.io.IOException;
import java.net.SocketAddress;
import javax.annotation.Nullable;
import p012io.grpc.InternalChannelz;
import p012io.grpc.InternalInstrumented;

/* renamed from: io.grpc.internal.InternalServer */
public interface InternalServer {
    SocketAddress getListenSocketAddress();

    @Nullable
    InternalInstrumented<InternalChannelz.SocketStats> getListenSocketStats();

    void shutdown();

    void start(ServerListener serverListener) throws IOException;
}

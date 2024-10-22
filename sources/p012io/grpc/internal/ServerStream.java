package p012io.grpc.internal;

import javax.annotation.Nullable;
import p012io.grpc.Attributes;
import p012io.grpc.Decompressor;
import p012io.grpc.Metadata;
import p012io.grpc.Status;

/* renamed from: io.grpc.internal.ServerStream */
public interface ServerStream extends Stream {
    void cancel(Status status);

    void close(Status status, Metadata metadata);

    Attributes getAttributes();

    @Nullable
    String getAuthority();

    void setDecompressor(Decompressor decompressor);

    void setListener(ServerStreamListener serverStreamListener);

    StatsTraceContext statsTraceContext();

    int streamId();

    void writeHeaders(Metadata metadata);
}

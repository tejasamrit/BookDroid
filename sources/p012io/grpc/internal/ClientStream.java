package p012io.grpc.internal;

import javax.annotation.Nonnull;
import p012io.grpc.Attributes;
import p012io.grpc.Deadline;
import p012io.grpc.DecompressorRegistry;
import p012io.grpc.Status;

/* renamed from: io.grpc.internal.ClientStream */
public interface ClientStream extends Stream {
    void appendTimeoutInsight(InsightBuilder insightBuilder);

    void cancel(Status status);

    Attributes getAttributes();

    void halfClose();

    void setAuthority(String str);

    void setDeadline(@Nonnull Deadline deadline);

    void setDecompressorRegistry(DecompressorRegistry decompressorRegistry);

    void setFullStreamDecompression(boolean z);

    void setMaxInboundMessageSize(int i);

    void setMaxOutboundMessageSize(int i);

    void start(ClientStreamListener clientStreamListener);
}

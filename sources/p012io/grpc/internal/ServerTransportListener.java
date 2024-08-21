package p012io.grpc.internal;

import p012io.grpc.Attributes;
import p012io.grpc.Metadata;

/* renamed from: io.grpc.internal.ServerTransportListener */
public interface ServerTransportListener {
    void streamCreated(ServerStream serverStream, String str, Metadata metadata);

    Attributes transportReady(Attributes attributes);

    void transportTerminated();
}

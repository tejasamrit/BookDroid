package p012io.grpc.internal;

import java.io.InputStream;
import p012io.grpc.Compressor;

/* renamed from: io.grpc.internal.Framer */
public interface Framer {
    void close();

    void dispose();

    void flush();

    boolean isClosed();

    Framer setCompressor(Compressor compressor);

    void setMaxOutboundMessageSize(int i);

    Framer setMessageCompression(boolean z);

    void writePayload(InputStream inputStream);
}

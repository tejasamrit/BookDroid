package p012io.grpc.internal;

import p012io.grpc.Decompressor;

/* renamed from: io.grpc.internal.Deframer */
public interface Deframer {
    void close();

    void closeWhenComplete();

    void deframe(ReadableBuffer readableBuffer);

    void request(int i);

    void setDecompressor(Decompressor decompressor);

    void setFullStreamDecompressor(GzipInflatingBuffer gzipInflatingBuffer);

    void setMaxInboundMessageSize(int i);
}

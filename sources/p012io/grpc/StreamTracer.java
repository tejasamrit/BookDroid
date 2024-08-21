package p012io.grpc;

/* renamed from: io.grpc.StreamTracer */
public abstract class StreamTracer {
    public void inboundMessage(int i) {
    }

    public void inboundMessageRead(int i, long j, long j2) {
    }

    public void inboundUncompressedSize(long j) {
    }

    public void inboundWireSize(long j) {
    }

    public void outboundMessage(int i) {
    }

    public void outboundMessageSent(int i, long j, long j2) {
    }

    public void outboundUncompressedSize(long j) {
    }

    public void outboundWireSize(long j) {
    }

    public void streamClosed(Status status) {
    }
}

package p012io.grpc.internal;

import com.google.common.base.Preconditions;
import com.google.firebase.messaging.Constants;
import java.io.Closeable;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.DataFormatException;
import javax.annotation.Nullable;
import p012io.grpc.Codec;
import p012io.grpc.Decompressor;
import p012io.grpc.Status;
import p012io.grpc.internal.StreamListener;

/* renamed from: io.grpc.internal.MessageDeframer */
public class MessageDeframer implements Closeable, Deframer {
    private static final int COMPRESSED_FLAG_MASK = 1;
    private static final int HEADER_LENGTH = 5;
    private static final int MAX_BUFFER_SIZE = 2097152;
    private static final int RESERVED_MASK = 254;
    private boolean closeWhenComplete = false;
    private boolean compressedFlag;
    private int currentMessageSeqNo = -1;
    private Decompressor decompressor;
    private GzipInflatingBuffer fullStreamDecompressor;
    private boolean inDelivery = false;
    private int inboundBodyWireSize;
    private byte[] inflatedBuffer;
    private int inflatedIndex;
    private C2465Listener listener;
    private int maxInboundMessageSize;
    private CompositeReadableBuffer nextFrame;
    private long pendingDeliveries;
    private int requiredLength = 5;
    private State state = State.HEADER;
    private final StatsTraceContext statsTraceCtx;
    private volatile boolean stopDelivery = false;
    private final TransportTracer transportTracer;
    private CompositeReadableBuffer unprocessed = new CompositeReadableBuffer();

    /* renamed from: io.grpc.internal.MessageDeframer$Listener */
    public interface C2465Listener {
        void bytesRead(int i);

        void deframeFailed(Throwable th);

        void deframerClosed(boolean z);

        void messagesAvailable(StreamListener.MessageProducer messageProducer);
    }

    /* renamed from: io.grpc.internal.MessageDeframer$State */
    private enum State {
        HEADER,
        BODY
    }

    public MessageDeframer(C2465Listener listener2, Decompressor decompressor2, int i, StatsTraceContext statsTraceContext, TransportTracer transportTracer2) {
        this.listener = (C2465Listener) Preconditions.checkNotNull(listener2, "sink");
        this.decompressor = (Decompressor) Preconditions.checkNotNull(decompressor2, "decompressor");
        this.maxInboundMessageSize = i;
        this.statsTraceCtx = (StatsTraceContext) Preconditions.checkNotNull(statsTraceContext, "statsTraceCtx");
        this.transportTracer = (TransportTracer) Preconditions.checkNotNull(transportTracer2, "transportTracer");
    }

    /* access modifiers changed from: package-private */
    public void setListener(C2465Listener listener2) {
        this.listener = listener2;
    }

    public void setMaxInboundMessageSize(int i) {
        this.maxInboundMessageSize = i;
    }

    public void setDecompressor(Decompressor decompressor2) {
        Preconditions.checkState(this.fullStreamDecompressor == null, "Already set full stream decompressor");
        this.decompressor = (Decompressor) Preconditions.checkNotNull(decompressor2, "Can't pass an empty decompressor");
    }

    public void setFullStreamDecompressor(GzipInflatingBuffer gzipInflatingBuffer) {
        boolean z = true;
        Preconditions.checkState(this.decompressor == Codec.Identity.NONE, "per-message decompressor already set");
        if (this.fullStreamDecompressor != null) {
            z = false;
        }
        Preconditions.checkState(z, "full stream decompressor already set");
        this.fullStreamDecompressor = (GzipInflatingBuffer) Preconditions.checkNotNull(gzipInflatingBuffer, "Can't pass a null full stream decompressor");
        this.unprocessed = null;
    }

    public void request(int i) {
        Preconditions.checkArgument(i > 0, "numMessages must be > 0");
        if (!isClosed()) {
            this.pendingDeliveries += (long) i;
            deliver();
        }
    }

    public void deframe(ReadableBuffer readableBuffer) {
        Preconditions.checkNotNull(readableBuffer, Constants.ScionAnalytics.MessageType.DATA_MESSAGE);
        boolean z = true;
        try {
            if (!isClosedOrScheduledToClose()) {
                GzipInflatingBuffer gzipInflatingBuffer = this.fullStreamDecompressor;
                if (gzipInflatingBuffer != null) {
                    gzipInflatingBuffer.addGzippedBytes(readableBuffer);
                } else {
                    this.unprocessed.addBuffer(readableBuffer);
                }
                z = false;
                deliver();
            }
        } finally {
            if (z) {
                readableBuffer.close();
            }
        }
    }

    public void closeWhenComplete() {
        if (!isClosed()) {
            if (isStalled()) {
                close();
            } else {
                this.closeWhenComplete = true;
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void stopDelivery() {
        this.stopDelivery = true;
    }

    /* JADX INFO: finally extract failed */
    public void close() {
        if (!isClosed()) {
            CompositeReadableBuffer compositeReadableBuffer = this.nextFrame;
            boolean z = true;
            boolean z2 = compositeReadableBuffer != null && compositeReadableBuffer.readableBytes() > 0;
            try {
                GzipInflatingBuffer gzipInflatingBuffer = this.fullStreamDecompressor;
                if (gzipInflatingBuffer != null) {
                    if (!z2) {
                        if (!gzipInflatingBuffer.hasPartialData()) {
                            z = false;
                        }
                    }
                    this.fullStreamDecompressor.close();
                    z2 = z;
                }
                CompositeReadableBuffer compositeReadableBuffer2 = this.unprocessed;
                if (compositeReadableBuffer2 != null) {
                    compositeReadableBuffer2.close();
                }
                CompositeReadableBuffer compositeReadableBuffer3 = this.nextFrame;
                if (compositeReadableBuffer3 != null) {
                    compositeReadableBuffer3.close();
                }
                this.fullStreamDecompressor = null;
                this.unprocessed = null;
                this.nextFrame = null;
                this.listener.deframerClosed(z2);
            } catch (Throwable th) {
                this.fullStreamDecompressor = null;
                this.unprocessed = null;
                this.nextFrame = null;
                throw th;
            }
        }
    }

    public boolean isClosed() {
        return this.unprocessed == null && this.fullStreamDecompressor == null;
    }

    private boolean isClosedOrScheduledToClose() {
        return isClosed() || this.closeWhenComplete;
    }

    private boolean isStalled() {
        GzipInflatingBuffer gzipInflatingBuffer = this.fullStreamDecompressor;
        if (gzipInflatingBuffer != null) {
            return gzipInflatingBuffer.isStalled();
        }
        return this.unprocessed.readableBytes() == 0;
    }

    private void deliver() {
        if (!this.inDelivery) {
            this.inDelivery = true;
            while (!this.stopDelivery && this.pendingDeliveries > 0 && readRequiredBytes()) {
                try {
                    int i = C24641.$SwitchMap$io$grpc$internal$MessageDeframer$State[this.state.ordinal()];
                    if (i == 1) {
                        processHeader();
                    } else if (i == 2) {
                        processBody();
                        this.pendingDeliveries--;
                    } else {
                        throw new AssertionError("Invalid state: " + this.state);
                    }
                } finally {
                    this.inDelivery = false;
                }
            }
            if (this.stopDelivery) {
                close();
                return;
            }
            if (this.closeWhenComplete && isStalled()) {
                close();
            }
            this.inDelivery = false;
        }
    }

    /* renamed from: io.grpc.internal.MessageDeframer$1 */
    static /* synthetic */ class C24641 {
        static final /* synthetic */ int[] $SwitchMap$io$grpc$internal$MessageDeframer$State;

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|6) */
        /* JADX WARNING: Code restructure failed: missing block: B:7:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        static {
            /*
                io.grpc.internal.MessageDeframer$State[] r0 = p012io.grpc.internal.MessageDeframer.State.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$io$grpc$internal$MessageDeframer$State = r0
                io.grpc.internal.MessageDeframer$State r1 = p012io.grpc.internal.MessageDeframer.State.HEADER     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$io$grpc$internal$MessageDeframer$State     // Catch:{ NoSuchFieldError -> 0x001d }
                io.grpc.internal.MessageDeframer$State r1 = p012io.grpc.internal.MessageDeframer.State.BODY     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: p012io.grpc.internal.MessageDeframer.C24641.<clinit>():void");
        }
    }

    private boolean readRequiredBytes() {
        int i;
        int i2 = 0;
        try {
            if (this.nextFrame == null) {
                this.nextFrame = new CompositeReadableBuffer();
            }
            int i3 = 0;
            i = 0;
            while (true) {
                try {
                    int readableBytes = this.requiredLength - this.nextFrame.readableBytes();
                    if (readableBytes <= 0) {
                        if (i3 > 0) {
                            this.listener.bytesRead(i3);
                            if (this.state == State.BODY) {
                                if (this.fullStreamDecompressor != null) {
                                    this.statsTraceCtx.inboundWireSize((long) i);
                                    this.inboundBodyWireSize += i;
                                } else {
                                    this.statsTraceCtx.inboundWireSize((long) i3);
                                    this.inboundBodyWireSize += i3;
                                }
                            }
                        }
                        return true;
                    } else if (this.fullStreamDecompressor != null) {
                        byte[] bArr = this.inflatedBuffer;
                        if (bArr == null || this.inflatedIndex == bArr.length) {
                            this.inflatedBuffer = new byte[Math.min(readableBytes, 2097152)];
                            this.inflatedIndex = 0;
                        }
                        int inflateBytes = this.fullStreamDecompressor.inflateBytes(this.inflatedBuffer, this.inflatedIndex, Math.min(readableBytes, this.inflatedBuffer.length - this.inflatedIndex));
                        i3 += this.fullStreamDecompressor.getAndResetBytesConsumed();
                        i += this.fullStreamDecompressor.getAndResetDeflatedBytesConsumed();
                        if (inflateBytes == 0) {
                            if (i3 > 0) {
                                this.listener.bytesRead(i3);
                                if (this.state == State.BODY) {
                                    if (this.fullStreamDecompressor != null) {
                                        this.statsTraceCtx.inboundWireSize((long) i);
                                        this.inboundBodyWireSize += i;
                                    } else {
                                        this.statsTraceCtx.inboundWireSize((long) i3);
                                        this.inboundBodyWireSize += i3;
                                    }
                                }
                            }
                            return false;
                        }
                        this.nextFrame.addBuffer(ReadableBuffers.wrap(this.inflatedBuffer, this.inflatedIndex, inflateBytes));
                        this.inflatedIndex += inflateBytes;
                    } else if (this.unprocessed.readableBytes() == 0) {
                        if (i3 > 0) {
                            this.listener.bytesRead(i3);
                            if (this.state == State.BODY) {
                                if (this.fullStreamDecompressor != null) {
                                    this.statsTraceCtx.inboundWireSize((long) i);
                                    this.inboundBodyWireSize += i;
                                } else {
                                    this.statsTraceCtx.inboundWireSize((long) i3);
                                    this.inboundBodyWireSize += i3;
                                }
                            }
                        }
                        return false;
                    } else {
                        int min = Math.min(readableBytes, this.unprocessed.readableBytes());
                        i3 += min;
                        this.nextFrame.addBuffer(this.unprocessed.readBytes(min));
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                } catch (DataFormatException e2) {
                    throw new RuntimeException(e2);
                } catch (Throwable th) {
                    int i4 = i3;
                    th = th;
                    i2 = i4;
                }
            }
        } catch (Throwable th2) {
            th = th2;
            i = 0;
            if (i2 > 0) {
                this.listener.bytesRead(i2);
                if (this.state == State.BODY) {
                    if (this.fullStreamDecompressor != null) {
                        this.statsTraceCtx.inboundWireSize((long) i);
                        this.inboundBodyWireSize += i;
                    } else {
                        this.statsTraceCtx.inboundWireSize((long) i2);
                        this.inboundBodyWireSize += i2;
                    }
                }
            }
            throw th;
        }
    }

    private void processHeader() {
        int readUnsignedByte = this.nextFrame.readUnsignedByte();
        if ((readUnsignedByte & RESERVED_MASK) == 0) {
            this.compressedFlag = (readUnsignedByte & 1) != 0;
            int readInt = this.nextFrame.readInt();
            this.requiredLength = readInt;
            if (readInt < 0 || readInt > this.maxInboundMessageSize) {
                throw Status.RESOURCE_EXHAUSTED.withDescription(String.format("gRPC message exceeds maximum size %d: %d", new Object[]{Integer.valueOf(this.maxInboundMessageSize), Integer.valueOf(this.requiredLength)})).asRuntimeException();
            }
            int i = this.currentMessageSeqNo + 1;
            this.currentMessageSeqNo = i;
            this.statsTraceCtx.inboundMessage(i);
            this.transportTracer.reportMessageReceived();
            this.state = State.BODY;
            return;
        }
        throw Status.INTERNAL.withDescription("gRPC frame header malformed: reserved bits not zero").asRuntimeException();
    }

    private void processBody() {
        this.statsTraceCtx.inboundMessageRead(this.currentMessageSeqNo, (long) this.inboundBodyWireSize, -1);
        this.inboundBodyWireSize = 0;
        InputStream compressedBody = this.compressedFlag ? getCompressedBody() : getUncompressedBody();
        this.nextFrame = null;
        this.listener.messagesAvailable(new SingleMessageProducer(compressedBody, (C24641) null));
        this.state = State.HEADER;
        this.requiredLength = 5;
    }

    private InputStream getUncompressedBody() {
        this.statsTraceCtx.inboundUncompressedSize((long) this.nextFrame.readableBytes());
        return ReadableBuffers.openStream(this.nextFrame, true);
    }

    private InputStream getCompressedBody() {
        if (this.decompressor != Codec.Identity.NONE) {
            try {
                return new SizeEnforcingInputStream(this.decompressor.decompress(ReadableBuffers.openStream(this.nextFrame, true)), this.maxInboundMessageSize, this.statsTraceCtx);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            throw Status.INTERNAL.withDescription("Can't decode compressed gRPC message as compression not configured").asRuntimeException();
        }
    }

    /* renamed from: io.grpc.internal.MessageDeframer$SizeEnforcingInputStream */
    static final class SizeEnforcingInputStream extends FilterInputStream {
        private long count;
        private long mark = -1;
        private long maxCount;
        private final int maxMessageSize;
        private final StatsTraceContext statsTraceCtx;

        SizeEnforcingInputStream(InputStream inputStream, int i, StatsTraceContext statsTraceContext) {
            super(inputStream);
            this.maxMessageSize = i;
            this.statsTraceCtx = statsTraceContext;
        }

        public int read() throws IOException {
            int read = this.in.read();
            if (read != -1) {
                this.count++;
            }
            verifySize();
            reportCount();
            return read;
        }

        public int read(byte[] bArr, int i, int i2) throws IOException {
            int read = this.in.read(bArr, i, i2);
            if (read != -1) {
                this.count += (long) read;
            }
            verifySize();
            reportCount();
            return read;
        }

        public long skip(long j) throws IOException {
            long skip = this.in.skip(j);
            this.count += skip;
            verifySize();
            reportCount();
            return skip;
        }

        public synchronized void mark(int i) {
            this.in.mark(i);
            this.mark = this.count;
        }

        public synchronized void reset() throws IOException {
            if (!this.in.markSupported()) {
                throw new IOException("Mark not supported");
            } else if (this.mark != -1) {
                this.in.reset();
                this.count = this.mark;
            } else {
                throw new IOException("Mark not set");
            }
        }

        private void reportCount() {
            long j = this.count;
            long j2 = this.maxCount;
            if (j > j2) {
                this.statsTraceCtx.inboundUncompressedSize(j - j2);
                this.maxCount = this.count;
            }
        }

        private void verifySize() {
            if (this.count > ((long) this.maxMessageSize)) {
                throw Status.RESOURCE_EXHAUSTED.withDescription(String.format("Compressed gRPC message exceeds maximum size %d: %d bytes read", new Object[]{Integer.valueOf(this.maxMessageSize), Long.valueOf(this.count)})).asRuntimeException();
            }
        }
    }

    /* renamed from: io.grpc.internal.MessageDeframer$SingleMessageProducer */
    private static class SingleMessageProducer implements StreamListener.MessageProducer {
        private InputStream message;

        /* synthetic */ SingleMessageProducer(InputStream inputStream, C24641 r2) {
            this(inputStream);
        }

        private SingleMessageProducer(InputStream inputStream) {
            this.message = inputStream;
        }

        @Nullable
        public InputStream next() {
            InputStream inputStream = this.message;
            this.message = null;
            return inputStream;
        }
    }
}

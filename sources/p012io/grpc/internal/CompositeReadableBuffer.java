package p012io.grpc.internal;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.util.ArrayDeque;
import java.util.Queue;

/* renamed from: io.grpc.internal.CompositeReadableBuffer */
public class CompositeReadableBuffer extends AbstractReadableBuffer {
    private final Queue<ReadableBuffer> buffers = new ArrayDeque();
    private int readableBytes;

    public void addBuffer(ReadableBuffer readableBuffer) {
        if (!(readableBuffer instanceof CompositeReadableBuffer)) {
            this.buffers.add(readableBuffer);
            this.readableBytes += readableBuffer.readableBytes();
            return;
        }
        CompositeReadableBuffer compositeReadableBuffer = (CompositeReadableBuffer) readableBuffer;
        while (!compositeReadableBuffer.buffers.isEmpty()) {
            this.buffers.add(compositeReadableBuffer.buffers.remove());
        }
        this.readableBytes += compositeReadableBuffer.readableBytes;
        compositeReadableBuffer.readableBytes = 0;
        compositeReadableBuffer.close();
    }

    public int readableBytes() {
        return this.readableBytes;
    }

    public int readUnsignedByte() {
        C23961 r0 = new ReadOperation() {
            /* access modifiers changed from: package-private */
            public int readInternal(ReadableBuffer readableBuffer, int i) {
                return readableBuffer.readUnsignedByte();
            }
        };
        execute(r0, 1);
        return r0.value;
    }

    public void skipBytes(int i) {
        execute(new ReadOperation() {
            public int readInternal(ReadableBuffer readableBuffer, int i) {
                readableBuffer.skipBytes(i);
                return 0;
            }
        }, i);
    }

    public void readBytes(byte[] bArr, int i, int i2) {
        execute(new ReadOperation(i, bArr) {
            int currentOffset;
            final /* synthetic */ byte[] val$dest;
            final /* synthetic */ int val$destOffset;

            {
                this.val$destOffset = r2;
                this.val$dest = r3;
                this.currentOffset = r2;
            }

            public int readInternal(ReadableBuffer readableBuffer, int i) {
                readableBuffer.readBytes(this.val$dest, this.currentOffset, i);
                this.currentOffset += i;
                return 0;
            }
        }, i2);
    }

    public void readBytes(final ByteBuffer byteBuffer) {
        execute(new ReadOperation() {
            public int readInternal(ReadableBuffer readableBuffer, int i) {
                int limit = byteBuffer.limit();
                ByteBuffer byteBuffer = byteBuffer;
                byteBuffer.limit(byteBuffer.position() + i);
                readableBuffer.readBytes(byteBuffer);
                byteBuffer.limit(limit);
                return 0;
            }
        }, byteBuffer.remaining());
    }

    public void readBytes(final OutputStream outputStream, int i) throws IOException {
        C24005 r0 = new ReadOperation() {
            public int readInternal(ReadableBuffer readableBuffer, int i) throws IOException {
                readableBuffer.readBytes(outputStream, i);
                return 0;
            }
        };
        execute(r0, i);
        if (r0.isError()) {
            throw r0.f491ex;
        }
    }

    public CompositeReadableBuffer readBytes(int i) {
        checkReadable(i);
        this.readableBytes -= i;
        CompositeReadableBuffer compositeReadableBuffer = new CompositeReadableBuffer();
        while (i > 0) {
            ReadableBuffer peek = this.buffers.peek();
            if (peek.readableBytes() > i) {
                compositeReadableBuffer.addBuffer(peek.readBytes(i));
                i = 0;
            } else {
                compositeReadableBuffer.addBuffer(this.buffers.poll());
                i -= peek.readableBytes();
            }
        }
        return compositeReadableBuffer;
    }

    public void close() {
        while (!this.buffers.isEmpty()) {
            this.buffers.remove().close();
        }
    }

    private void execute(ReadOperation readOperation, int i) {
        checkReadable(i);
        if (!this.buffers.isEmpty()) {
            advanceBufferIfNecessary();
        }
        while (i > 0 && !this.buffers.isEmpty()) {
            ReadableBuffer peek = this.buffers.peek();
            int min = Math.min(i, peek.readableBytes());
            readOperation.read(peek, min);
            if (!readOperation.isError()) {
                i -= min;
                this.readableBytes -= min;
                advanceBufferIfNecessary();
            } else {
                return;
            }
        }
        if (i > 0) {
            throw new AssertionError("Failed executing read operation");
        }
    }

    private void advanceBufferIfNecessary() {
        if (this.buffers.peek().readableBytes() == 0) {
            this.buffers.remove().close();
        }
    }

    /* renamed from: io.grpc.internal.CompositeReadableBuffer$ReadOperation */
    private static abstract class ReadOperation {

        /* renamed from: ex */
        IOException f491ex;
        int value;

        /* access modifiers changed from: package-private */
        public abstract int readInternal(ReadableBuffer readableBuffer, int i) throws IOException;

        private ReadOperation() {
        }

        /* access modifiers changed from: package-private */
        public final void read(ReadableBuffer readableBuffer, int i) {
            try {
                this.value = readInternal(readableBuffer, i);
            } catch (IOException e) {
                this.f491ex = e;
            }
        }

        /* access modifiers changed from: package-private */
        public final boolean isError() {
            return this.f491ex != null;
        }
    }
}

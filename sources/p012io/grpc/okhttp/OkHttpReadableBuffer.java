package p012io.grpc.okhttp;

import java.io.EOFException;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import okio.Buffer;
import p012io.grpc.internal.AbstractReadableBuffer;
import p012io.grpc.internal.ReadableBuffer;

/* renamed from: io.grpc.okhttp.OkHttpReadableBuffer */
class OkHttpReadableBuffer extends AbstractReadableBuffer {
    private final Buffer buffer;

    OkHttpReadableBuffer(Buffer buffer2) {
        this.buffer = buffer2;
    }

    public int readableBytes() {
        return (int) this.buffer.size();
    }

    public int readUnsignedByte() {
        return this.buffer.readByte() & 255;
    }

    public void skipBytes(int i) {
        try {
            this.buffer.skip((long) i);
        } catch (EOFException e) {
            throw new IndexOutOfBoundsException(e.getMessage());
        }
    }

    public void readBytes(byte[] bArr, int i, int i2) {
        while (i2 > 0) {
            int read = this.buffer.read(bArr, i, i2);
            if (read != -1) {
                i2 -= read;
                i += read;
            } else {
                throw new IndexOutOfBoundsException("EOF trying to read " + i2 + " bytes");
            }
        }
    }

    public void readBytes(ByteBuffer byteBuffer) {
        throw new UnsupportedOperationException();
    }

    public void readBytes(OutputStream outputStream, int i) throws IOException {
        this.buffer.writeTo(outputStream, (long) i);
    }

    public ReadableBuffer readBytes(int i) {
        Buffer buffer2 = new Buffer();
        buffer2.write(this.buffer, (long) i);
        return new OkHttpReadableBuffer(buffer2);
    }

    public void close() {
        this.buffer.clear();
    }
}

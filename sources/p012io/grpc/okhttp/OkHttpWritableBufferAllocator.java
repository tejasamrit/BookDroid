package p012io.grpc.okhttp;

import okio.Buffer;
import p012io.grpc.internal.WritableBuffer;
import p012io.grpc.internal.WritableBufferAllocator;

/* renamed from: io.grpc.okhttp.OkHttpWritableBufferAllocator */
class OkHttpWritableBufferAllocator implements WritableBufferAllocator {
    private static final int MAX_BUFFER = 1048576;
    private static final int MIN_BUFFER = 4096;

    OkHttpWritableBufferAllocator() {
    }

    public WritableBuffer allocate(int i) {
        return new OkHttpWritableBuffer(new Buffer(), Math.min(1048576, Math.max(4096, i)));
    }
}

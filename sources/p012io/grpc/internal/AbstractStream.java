package p012io.grpc.internal;

import com.google.common.base.Preconditions;
import java.io.InputStream;
import p012io.grpc.Codec;
import p012io.grpc.Compressor;
import p012io.grpc.Decompressor;
import p012io.grpc.internal.ApplicationThreadDeframer;
import p012io.grpc.internal.MessageDeframer;
import p012io.grpc.internal.StreamListener;

/* renamed from: io.grpc.internal.AbstractStream */
public abstract class AbstractStream implements Stream {
    /* access modifiers changed from: protected */
    public abstract Framer framer();

    /* access modifiers changed from: protected */
    public abstract TransportState transportState();

    public final void setMessageCompression(boolean z) {
        framer().setMessageCompression(z);
    }

    public final void writeMessage(InputStream inputStream) {
        Preconditions.checkNotNull(inputStream, "message");
        try {
            if (!framer().isClosed()) {
                framer().writePayload(inputStream);
            }
        } finally {
            GrpcUtil.closeQuietly(inputStream);
        }
    }

    public final void flush() {
        if (!framer().isClosed()) {
            framer().flush();
        }
    }

    /* access modifiers changed from: protected */
    public final void endOfMessages() {
        framer().close();
    }

    public final void setCompressor(Compressor compressor) {
        framer().setCompressor((Compressor) Preconditions.checkNotNull(compressor, "compressor"));
    }

    public boolean isReady() {
        if (framer().isClosed()) {
            return false;
        }
        return transportState().isReady();
    }

    /* access modifiers changed from: protected */
    public final void onSendingBytes(int i) {
        transportState().onSendingBytes(i);
    }

    /* renamed from: io.grpc.internal.AbstractStream$TransportState */
    public static abstract class TransportState implements ApplicationThreadDeframer.TransportExecutor, MessageDeframer.C2465Listener {
        public static final int DEFAULT_ONREADY_THRESHOLD = 32768;
        private boolean allocated;
        private boolean deallocated;
        private Deframer deframer;
        private int numSentBytesQueued;
        private final Object onReadyLock = new Object();
        private final StatsTraceContext statsTraceCtx;
        private final TransportTracer transportTracer;

        /* access modifiers changed from: protected */
        public abstract StreamListener listener();

        protected TransportState(int i, StatsTraceContext statsTraceContext, TransportTracer transportTracer2) {
            this.statsTraceCtx = (StatsTraceContext) Preconditions.checkNotNull(statsTraceContext, "statsTraceCtx");
            this.transportTracer = (TransportTracer) Preconditions.checkNotNull(transportTracer2, "transportTracer");
            this.deframer = new MessageDeframer(this, Codec.Identity.NONE, i, statsTraceContext, transportTracer2);
        }

        /* access modifiers changed from: protected */
        public void setFullStreamDecompressor(GzipInflatingBuffer gzipInflatingBuffer) {
            this.deframer.setFullStreamDecompressor(gzipInflatingBuffer);
            this.deframer = new ApplicationThreadDeframer(this, this, (MessageDeframer) this.deframer);
        }

        /* access modifiers changed from: package-private */
        public final void setMaxInboundMessageSize(int i) {
            this.deframer.setMaxInboundMessageSize(i);
        }

        public void messagesAvailable(StreamListener.MessageProducer messageProducer) {
            listener().messagesAvailable(messageProducer);
        }

        /* access modifiers changed from: protected */
        public final void closeDeframer(boolean z) {
            if (z) {
                this.deframer.close();
            } else {
                this.deframer.closeWhenComplete();
            }
        }

        /* access modifiers changed from: protected */
        public final void deframe(ReadableBuffer readableBuffer) {
            try {
                this.deframer.deframe(readableBuffer);
            } catch (Throwable th) {
                deframeFailed(th);
            }
        }

        public final void requestMessagesFromDeframer(int i) {
            try {
                this.deframer.request(i);
            } catch (Throwable th) {
                deframeFailed(th);
            }
        }

        public final StatsTraceContext getStatsTraceContext() {
            return this.statsTraceCtx;
        }

        /* access modifiers changed from: protected */
        public final void setDecompressor(Decompressor decompressor) {
            this.deframer.setDecompressor(decompressor);
        }

        /* access modifiers changed from: private */
        public boolean isReady() {
            boolean z;
            synchronized (this.onReadyLock) {
                z = this.allocated && this.numSentBytesQueued < 32768 && !this.deallocated;
            }
            return z;
        }

        /* access modifiers changed from: protected */
        public void onStreamAllocated() {
            boolean z = false;
            Preconditions.checkState(listener() != null);
            synchronized (this.onReadyLock) {
                if (!this.allocated) {
                    z = true;
                }
                Preconditions.checkState(z, "Already allocated");
                this.allocated = true;
            }
            notifyIfReady();
        }

        /* access modifiers changed from: protected */
        public final void onStreamDeallocated() {
            synchronized (this.onReadyLock) {
                this.deallocated = true;
            }
        }

        /* access modifiers changed from: private */
        public void onSendingBytes(int i) {
            synchronized (this.onReadyLock) {
                this.numSentBytesQueued += i;
            }
        }

        public final void onSentBytes(int i) {
            boolean z;
            synchronized (this.onReadyLock) {
                Preconditions.checkState(this.allocated, "onStreamAllocated was not called, but it seems the stream is active");
                int i2 = this.numSentBytesQueued;
                z = true;
                boolean z2 = i2 < 32768;
                int i3 = i2 - i;
                this.numSentBytesQueued = i3;
                boolean z3 = i3 < 32768;
                if (z2 || !z3) {
                    z = false;
                }
            }
            if (z) {
                notifyIfReady();
            }
        }

        /* access modifiers changed from: protected */
        public TransportTracer getTransportTracer() {
            return this.transportTracer;
        }

        private void notifyIfReady() {
            boolean isReady;
            synchronized (this.onReadyLock) {
                isReady = isReady();
            }
            if (isReady) {
                listener().onReady();
            }
        }
    }
}

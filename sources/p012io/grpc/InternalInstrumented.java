package p012io.grpc;

import com.google.common.util.concurrent.ListenableFuture;

/* renamed from: io.grpc.InternalInstrumented */
public interface InternalInstrumented<T> extends InternalWithLogId {
    ListenableFuture<T> getStats();
}

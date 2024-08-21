package p012io.grpc.internal;

import androidx.core.app.NotificationCompat;
import com.google.common.base.Preconditions;
import com.google.firebase.messaging.Constants;
import p012io.grpc.Metadata;
import p012io.grpc.Status;
import p012io.grpc.internal.ClientStreamListener;

/* renamed from: io.grpc.internal.FailingClientStream */
public final class FailingClientStream extends NoopClientStream {
    private final Status error;
    private final ClientStreamListener.RpcProgress rpcProgress;
    private boolean started;

    public FailingClientStream(Status status) {
        this(status, ClientStreamListener.RpcProgress.PROCESSED);
    }

    public FailingClientStream(Status status, ClientStreamListener.RpcProgress rpcProgress2) {
        Preconditions.checkArgument(!status.isOk(), "error must not be OK");
        this.error = status;
        this.rpcProgress = rpcProgress2;
    }

    public void start(ClientStreamListener clientStreamListener) {
        Preconditions.checkState(!this.started, "already started");
        this.started = true;
        clientStreamListener.closed(this.error, this.rpcProgress, new Metadata());
    }

    /* access modifiers changed from: package-private */
    public Status getError() {
        return this.error;
    }

    public void appendTimeoutInsight(InsightBuilder insightBuilder) {
        insightBuilder.appendKeyValue(Constants.IPC_BUNDLE_KEY_SEND_ERROR, this.error).appendKeyValue(NotificationCompat.CATEGORY_PROGRESS, this.rpcProgress);
    }
}

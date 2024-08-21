package p012io.grpc;

import java.util.concurrent.Executor;
import p012io.grpc.CallCredentials;

@Deprecated
/* renamed from: io.grpc.CallCredentials2 */
public abstract class CallCredentials2 extends CallCredentials {

    /* renamed from: io.grpc.CallCredentials2$MetadataApplier */
    public static abstract class MetadataApplier extends CallCredentials.MetadataApplier {
    }

    public abstract void applyRequestMetadata(CallCredentials.RequestInfo requestInfo, Executor executor, MetadataApplier metadataApplier);

    public final void applyRequestMetadata(CallCredentials.RequestInfo requestInfo, Executor executor, final CallCredentials.MetadataApplier metadataApplier) {
        applyRequestMetadata(requestInfo, executor, (MetadataApplier) new MetadataApplier() {
            public void apply(Metadata metadata) {
                metadataApplier.apply(metadata);
            }

            public void fail(Status status) {
                metadataApplier.fail(status);
            }
        });
    }
}

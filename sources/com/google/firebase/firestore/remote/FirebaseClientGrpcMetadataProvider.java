package com.google.firebase.firestore.remote;

import com.google.firebase.FirebaseOptions;
import com.google.firebase.heartbeatinfo.HeartBeatInfo;
import com.google.firebase.inject.Provider;
import com.google.firebase.platforminfo.UserAgentPublisher;
import p012io.grpc.Metadata;

public class FirebaseClientGrpcMetadataProvider implements GrpcMetadataProvider {
    private static final Metadata.Key<String> GMP_APP_ID_HEADER = Metadata.Key.m262of("x-firebase-gmpid", Metadata.ASCII_STRING_MARSHALLER);
    private static final Metadata.Key<String> HEART_BEAT_HEADER = Metadata.Key.m262of("x-firebase-client-log-type", Metadata.ASCII_STRING_MARSHALLER);
    private static final String HEART_BEAT_TAG = "fire-fst";
    private static final Metadata.Key<String> USER_AGENT_HEADER = Metadata.Key.m262of("x-firebase-client", Metadata.ASCII_STRING_MARSHALLER);
    private final FirebaseOptions firebaseOptions;
    private final Provider<HeartBeatInfo> heartBeatInfoProvider;
    private final Provider<UserAgentPublisher> userAgentPublisherProvider;

    public FirebaseClientGrpcMetadataProvider(Provider<UserAgentPublisher> provider, Provider<HeartBeatInfo> provider2, FirebaseOptions firebaseOptions2) {
        this.userAgentPublisherProvider = provider;
        this.heartBeatInfoProvider = provider2;
        this.firebaseOptions = firebaseOptions2;
    }

    public void updateMetadata(Metadata metadata) {
        if (this.heartBeatInfoProvider.get() != null && this.userAgentPublisherProvider.get() != null) {
            int code = this.heartBeatInfoProvider.get().getHeartBeatCode(HEART_BEAT_TAG).getCode();
            if (code != 0) {
                metadata.put(HEART_BEAT_HEADER, Integer.toString(code));
            }
            metadata.put(USER_AGENT_HEADER, this.userAgentPublisherProvider.get().getUserAgent());
            maybeAddGmpAppId(metadata);
        }
    }

    private void maybeAddGmpAppId(Metadata metadata) {
        FirebaseOptions firebaseOptions2 = this.firebaseOptions;
        if (firebaseOptions2 != null) {
            String applicationId = firebaseOptions2.getApplicationId();
            if (applicationId.length() != 0) {
                metadata.put(GMP_APP_ID_HEADER, applicationId);
            }
        }
    }
}

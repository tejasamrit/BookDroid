package com.google.firebase.firestore.remote;

import p012io.grpc.Metadata;

public interface GrpcMetadataProvider {
    void updateMetadata(Metadata metadata);
}

package com.google.firebase.storage.network;

import android.net.Uri;
import com.google.android.gms.actions.SearchIntents;
import com.google.firebase.FirebaseApp;
import p012io.grpc.internal.GrpcUtil;

public class ResumableUploadQueryRequest extends ResumableNetworkRequest {
    private final Uri uploadURL;

    /* access modifiers changed from: protected */
    public String getAction() {
        return GrpcUtil.HTTP_METHOD;
    }

    public ResumableUploadQueryRequest(Uri uri, FirebaseApp firebaseApp, Uri uri2) {
        super(uri, firebaseApp);
        this.uploadURL = uri2;
        super.setCustomHeader("X-Goog-Upload-Protocol", "resumable");
        super.setCustomHeader("X-Goog-Upload-Command", SearchIntents.EXTRA_QUERY);
    }

    /* access modifiers changed from: protected */
    public Uri getURL() {
        return this.uploadURL;
    }
}

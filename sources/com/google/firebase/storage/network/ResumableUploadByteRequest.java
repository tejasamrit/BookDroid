package com.google.firebase.storage.network;

import android.net.Uri;
import com.google.firebase.FirebaseApp;
import p012io.grpc.internal.GrpcUtil;

public class ResumableUploadByteRequest extends ResumableNetworkRequest {
    private final int bytesToWrite;
    private final byte[] chunk;
    private final boolean isFinal;
    private final long offset;
    private final Uri uploadURL;

    /* access modifiers changed from: protected */
    public String getAction() {
        return GrpcUtil.HTTP_METHOD;
    }

    public ResumableUploadByteRequest(Uri uri, FirebaseApp firebaseApp, Uri uri2, byte[] bArr, long j, int i, boolean z) {
        super(uri, firebaseApp);
        if (bArr == null && i != -1) {
            this.mException = new IllegalArgumentException("contentType is null or empty");
        }
        if (j < 0) {
            this.mException = new IllegalArgumentException("offset cannot be negative");
        }
        this.bytesToWrite = i;
        this.uploadURL = uri2;
        this.chunk = i <= 0 ? null : bArr;
        this.offset = j;
        this.isFinal = z;
        super.setCustomHeader("X-Goog-Upload-Protocol", "resumable");
        if (z && i > 0) {
            super.setCustomHeader("X-Goog-Upload-Command", "upload, finalize");
        } else if (z) {
            super.setCustomHeader("X-Goog-Upload-Command", "finalize");
        } else {
            super.setCustomHeader("X-Goog-Upload-Command", "upload");
        }
        super.setCustomHeader("X-Goog-Upload-Offset", Long.toString(j));
    }

    /* access modifiers changed from: protected */
    public Uri getURL() {
        return this.uploadURL;
    }

    /* access modifiers changed from: protected */
    public byte[] getOutputRaw() {
        return this.chunk;
    }

    /* access modifiers changed from: protected */
    public int getOutputRawSize() {
        int i = this.bytesToWrite;
        if (i > 0) {
            return i;
        }
        return 0;
    }
}

package com.google.firebase.storage.network;

import android.net.Uri;
import com.google.firebase.FirebaseApp;

public class GetMetadataNetworkRequest extends NetworkRequest {
    /* access modifiers changed from: protected */
    public String getAction() {
        return "GET";
    }

    public GetMetadataNetworkRequest(Uri uri, FirebaseApp firebaseApp) {
        super(uri, firebaseApp);
    }
}

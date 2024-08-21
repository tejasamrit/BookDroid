package com.google.firebase.storage.network;

import android.net.Uri;
import com.google.common.net.HttpHeaders;
import com.google.firebase.FirebaseApp;
import java.util.Collections;
import java.util.Map;

public class GetNetworkRequest extends NetworkRequest {
    private static final String TAG = "GetNetworkRequest";

    /* access modifiers changed from: protected */
    public String getAction() {
        return "GET";
    }

    public GetNetworkRequest(Uri uri, FirebaseApp firebaseApp, long j) {
        super(uri, firebaseApp);
        if (j != 0) {
            super.setCustomHeader(HttpHeaders.RANGE, "bytes=" + j + "-");
        }
    }

    /* access modifiers changed from: protected */
    public Map<String, String> getQueryParameters() {
        return Collections.singletonMap("alt", "media");
    }
}

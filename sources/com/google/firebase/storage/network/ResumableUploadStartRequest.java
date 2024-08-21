package com.google.firebase.storage.network;

import android.net.Uri;
import android.text.TextUtils;
import com.google.firebase.FirebaseApp;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;
import p012io.grpc.internal.GrpcUtil;

public class ResumableUploadStartRequest extends ResumableNetworkRequest {
    private final String contentType;
    private final JSONObject metadata;

    /* access modifiers changed from: protected */
    public String getAction() {
        return GrpcUtil.HTTP_METHOD;
    }

    public ResumableUploadStartRequest(Uri uri, FirebaseApp firebaseApp, JSONObject jSONObject, String str) {
        super(uri, firebaseApp);
        this.metadata = jSONObject;
        this.contentType = str;
        if (TextUtils.isEmpty(str)) {
            this.mException = new IllegalArgumentException("mContentType is null or empty");
        }
        super.setCustomHeader("X-Goog-Upload-Protocol", "resumable");
        super.setCustomHeader("X-Goog-Upload-Command", "start");
        super.setCustomHeader("X-Goog-Upload-Header-Content-Type", str);
    }

    /* access modifiers changed from: protected */
    public Uri getURL() {
        Uri.Builder buildUpon = sNetworkRequestUrl.buildUpon();
        buildUpon.appendPath("b");
        buildUpon.appendPath(this.mGsUri.getAuthority());
        buildUpon.appendPath("o");
        return buildUpon.build();
    }

    /* access modifiers changed from: protected */
    public Map<String, String> getQueryParameters() {
        HashMap hashMap = new HashMap();
        hashMap.put("name", getPathWithoutBucket());
        hashMap.put("uploadType", "resumable");
        return hashMap;
    }

    /* access modifiers changed from: protected */
    public JSONObject getOutputJSON() {
        return this.metadata;
    }
}

package com.google.firebase.storage.network;

import android.net.Uri;
import android.text.TextUtils;
import com.google.firebase.FirebaseApp;
import java.util.HashMap;
import java.util.Map;

public class ListNetworkRequest extends NetworkRequest {
    private final Integer maxPageSize;
    private final String nextPageToken;

    /* access modifiers changed from: protected */
    public String getAction() {
        return "GET";
    }

    public ListNetworkRequest(Uri uri, FirebaseApp firebaseApp, Integer num, String str) {
        super(uri, firebaseApp);
        this.maxPageSize = num;
        this.nextPageToken = str;
    }

    /* access modifiers changed from: protected */
    public Uri getURL() {
        return Uri.parse(sNetworkRequestUrl + "/b/" + this.mGsUri.getAuthority() + "/o");
    }

    /* access modifiers changed from: protected */
    public Map<String, String> getQueryParameters() {
        HashMap hashMap = new HashMap();
        String pathWithoutBucket = getPathWithoutBucket();
        if (!pathWithoutBucket.isEmpty()) {
            hashMap.put("prefix", pathWithoutBucket + "/");
        }
        hashMap.put("delimiter", "/");
        Integer num = this.maxPageSize;
        if (num != null) {
            hashMap.put("maxResults", Integer.toString(num.intValue()));
        }
        if (!TextUtils.isEmpty(this.nextPageToken)) {
            hashMap.put("pageToken", this.nextPageToken);
        }
        return hashMap;
    }
}

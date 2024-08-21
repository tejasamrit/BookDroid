package com.google.firebase.storage.network;

import android.net.Uri;
import com.google.firebase.FirebaseApp;
import org.json.JSONObject;

public class UpdateMetadataNetworkRequest extends NetworkRequest {
    private final JSONObject metadata;

    /* access modifiers changed from: protected */
    public String getAction() {
        return "PUT";
    }

    public UpdateMetadataNetworkRequest(Uri uri, FirebaseApp firebaseApp, JSONObject jSONObject) {
        super(uri, firebaseApp);
        this.metadata = jSONObject;
        setCustomHeader("X-HTTP-Method-Override", "PATCH");
    }

    /* access modifiers changed from: protected */
    public JSONObject getOutputJSON() {
        return this.metadata;
    }
}

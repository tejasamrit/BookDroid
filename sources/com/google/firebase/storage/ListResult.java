package com.google.firebase.storage;

import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public final class ListResult {
    private static final String ITEMS_KEY = "items";
    private static final String NAME_KEY = "name";
    private static final String PAGE_TOKEN_KEY = "nextPageToken";
    private static final String PREFIXES_KEY = "prefixes";
    private final List<StorageReference> items;
    private final String pageToken;
    private final List<StorageReference> prefixes;

    ListResult(List<StorageReference> list, List<StorageReference> list2, String str) {
        this.prefixes = list;
        this.items = list2;
        this.pageToken = str;
    }

    static ListResult fromJSON(FirebaseStorage firebaseStorage, JSONObject jSONObject) throws JSONException {
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        if (jSONObject.has(PREFIXES_KEY)) {
            JSONArray jSONArray = jSONObject.getJSONArray(PREFIXES_KEY);
            for (int i = 0; i < jSONArray.length(); i++) {
                String string = jSONArray.getString(i);
                if (string.endsWith("/")) {
                    string = string.substring(0, string.length() - 1);
                }
                arrayList.add(firebaseStorage.getReference(string));
            }
        }
        if (jSONObject.has(ITEMS_KEY)) {
            JSONArray jSONArray2 = jSONObject.getJSONArray(ITEMS_KEY);
            for (int i2 = 0; i2 < jSONArray2.length(); i2++) {
                arrayList2.add(firebaseStorage.getReference(jSONArray2.getJSONObject(i2).getString(NAME_KEY)));
            }
        }
        return new ListResult(arrayList, arrayList2, jSONObject.optString(PAGE_TOKEN_KEY, (String) null));
    }

    public List<StorageReference> getPrefixes() {
        return this.prefixes;
    }

    public List<StorageReference> getItems() {
        return this.items;
    }

    public String getPageToken() {
        return this.pageToken;
    }
}

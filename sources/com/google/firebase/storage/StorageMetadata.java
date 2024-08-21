package com.google.firebase.storage;

import android.net.Uri;
import android.text.TextUtils;
import com.google.android.gms.common.internal.Preconditions;
import com.google.firebase.storage.internal.Slashes;
import com.google.firebase.storage.internal.Util;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import org.json.JSONException;
import org.json.JSONObject;

public class StorageMetadata {
    private static final String BUCKET_KEY = "bucket";
    private static final String CACHE_CONTROL = "cacheControl";
    private static final String CONTENT_DISPOSITION = "contentDisposition";
    private static final String CONTENT_ENCODING = "contentEncoding";
    private static final String CONTENT_LANGUAGE = "contentLanguage";
    private static final String CONTENT_TYPE_KEY = "contentType";
    private static final String CUSTOM_METADATA_KEY = "metadata";
    private static final String GENERATION_KEY = "generation";
    private static final String MD5_HASH_KEY = "md5Hash";
    private static final String META_GENERATION_KEY = "metageneration";
    private static final String NAME_KEY = "name";
    private static final String SIZE_KEY = "size";
    private static final String TAG = "StorageMetadata";
    private static final String TIME_CREATED_KEY = "timeCreated";
    private static final String TIME_UPDATED_KEY = "updated";
    /* access modifiers changed from: private */
    public String mBucket;
    /* access modifiers changed from: private */
    public MetadataValue<String> mCacheControl;
    /* access modifiers changed from: private */
    public MetadataValue<String> mContentDisposition;
    /* access modifiers changed from: private */
    public MetadataValue<String> mContentEncoding;
    /* access modifiers changed from: private */
    public MetadataValue<String> mContentLanguage;
    /* access modifiers changed from: private */
    public MetadataValue<String> mContentType;
    /* access modifiers changed from: private */
    public String mCreationTime;
    /* access modifiers changed from: private */
    public MetadataValue<Map<String, String>> mCustomMetadata;
    /* access modifiers changed from: private */
    public String mGeneration;
    /* access modifiers changed from: private */
    public String mMD5Hash;
    /* access modifiers changed from: private */
    public String mMetadataGeneration;
    /* access modifiers changed from: private */
    public String mPath;
    /* access modifiers changed from: private */
    public long mSize;
    private FirebaseStorage mStorage;
    /* access modifiers changed from: private */
    public StorageReference mStorageRef;
    /* access modifiers changed from: private */
    public String mUpdatedTime;

    private static class MetadataValue<T> {
        private final boolean userProvided;
        private final T value;

        MetadataValue(T t, boolean z) {
            this.userProvided = z;
            this.value = t;
        }

        static <T> MetadataValue<T> withDefaultValue(T t) {
            return new MetadataValue<>(t, false);
        }

        static <T> MetadataValue<T> withUserValue(T t) {
            return new MetadataValue<>(t, true);
        }

        /* access modifiers changed from: package-private */
        public boolean isUserProvided() {
            return this.userProvided;
        }

        /* access modifiers changed from: package-private */
        public T getValue() {
            return this.value;
        }
    }

    public StorageMetadata() {
        this.mPath = null;
        this.mStorage = null;
        this.mStorageRef = null;
        this.mBucket = null;
        this.mGeneration = null;
        this.mContentType = MetadataValue.withDefaultValue("");
        this.mMetadataGeneration = null;
        this.mCreationTime = null;
        this.mUpdatedTime = null;
        this.mMD5Hash = null;
        this.mCacheControl = MetadataValue.withDefaultValue("");
        this.mContentDisposition = MetadataValue.withDefaultValue("");
        this.mContentEncoding = MetadataValue.withDefaultValue("");
        this.mContentLanguage = MetadataValue.withDefaultValue("");
        this.mCustomMetadata = MetadataValue.withDefaultValue(Collections.emptyMap());
    }

    private StorageMetadata(StorageMetadata storageMetadata, boolean z) {
        this.mPath = null;
        this.mStorage = null;
        this.mStorageRef = null;
        this.mBucket = null;
        this.mGeneration = null;
        this.mContentType = MetadataValue.withDefaultValue("");
        this.mMetadataGeneration = null;
        this.mCreationTime = null;
        this.mUpdatedTime = null;
        this.mMD5Hash = null;
        this.mCacheControl = MetadataValue.withDefaultValue("");
        this.mContentDisposition = MetadataValue.withDefaultValue("");
        this.mContentEncoding = MetadataValue.withDefaultValue("");
        this.mContentLanguage = MetadataValue.withDefaultValue("");
        this.mCustomMetadata = MetadataValue.withDefaultValue(Collections.emptyMap());
        Preconditions.checkNotNull(storageMetadata);
        this.mPath = storageMetadata.mPath;
        this.mStorage = storageMetadata.mStorage;
        this.mStorageRef = storageMetadata.mStorageRef;
        this.mBucket = storageMetadata.mBucket;
        this.mContentType = storageMetadata.mContentType;
        this.mCacheControl = storageMetadata.mCacheControl;
        this.mContentDisposition = storageMetadata.mContentDisposition;
        this.mContentEncoding = storageMetadata.mContentEncoding;
        this.mContentLanguage = storageMetadata.mContentLanguage;
        this.mCustomMetadata = storageMetadata.mCustomMetadata;
        if (z) {
            this.mMD5Hash = storageMetadata.mMD5Hash;
            this.mSize = storageMetadata.mSize;
            this.mUpdatedTime = storageMetadata.mUpdatedTime;
            this.mCreationTime = storageMetadata.mCreationTime;
            this.mMetadataGeneration = storageMetadata.mMetadataGeneration;
            this.mGeneration = storageMetadata.mGeneration;
        }
    }

    public String getContentType() {
        return this.mContentType.getValue();
    }

    public String getCustomMetadata(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        return (String) this.mCustomMetadata.getValue().get(str);
    }

    public Set<String> getCustomMetadataKeys() {
        return this.mCustomMetadata.getValue().keySet();
    }

    public String getPath() {
        String str = this.mPath;
        return str != null ? str : "";
    }

    public String getName() {
        String path = getPath();
        if (TextUtils.isEmpty(path)) {
            return null;
        }
        int lastIndexOf = path.lastIndexOf(47);
        return lastIndexOf != -1 ? path.substring(lastIndexOf + 1) : path;
    }

    public String getBucket() {
        return this.mBucket;
    }

    public String getGeneration() {
        return this.mGeneration;
    }

    public String getMetadataGeneration() {
        return this.mMetadataGeneration;
    }

    public long getCreationTimeMillis() {
        return Util.parseDateTime(this.mCreationTime);
    }

    public long getUpdatedTimeMillis() {
        return Util.parseDateTime(this.mUpdatedTime);
    }

    public long getSizeBytes() {
        return this.mSize;
    }

    public String getMd5Hash() {
        return this.mMD5Hash;
    }

    public String getCacheControl() {
        return this.mCacheControl.getValue();
    }

    public String getContentDisposition() {
        return this.mContentDisposition.getValue();
    }

    public String getContentEncoding() {
        return this.mContentEncoding.getValue();
    }

    public String getContentLanguage() {
        return this.mContentLanguage.getValue();
    }

    public StorageReference getReference() {
        StorageReference storageReference = this.mStorageRef;
        if (storageReference != null || this.mStorage == null) {
            return storageReference;
        }
        String bucket = getBucket();
        String path = getPath();
        if (TextUtils.isEmpty(bucket) || TextUtils.isEmpty(path)) {
            return null;
        }
        return new StorageReference(new Uri.Builder().scheme("gs").authority(bucket).encodedPath(Slashes.preserveSlashEncode(path)).build(), this.mStorage);
    }

    /* access modifiers changed from: package-private */
    public JSONObject createJSONObject() {
        HashMap hashMap = new HashMap();
        if (this.mContentType.isUserProvided()) {
            hashMap.put(CONTENT_TYPE_KEY, getContentType());
        }
        if (this.mCustomMetadata.isUserProvided()) {
            hashMap.put(CUSTOM_METADATA_KEY, new JSONObject(this.mCustomMetadata.getValue()));
        }
        if (this.mCacheControl.isUserProvided()) {
            hashMap.put(CACHE_CONTROL, getCacheControl());
        }
        if (this.mContentDisposition.isUserProvided()) {
            hashMap.put(CONTENT_DISPOSITION, getContentDisposition());
        }
        if (this.mContentEncoding.isUserProvided()) {
            hashMap.put(CONTENT_ENCODING, getContentEncoding());
        }
        if (this.mContentLanguage.isUserProvided()) {
            hashMap.put(CONTENT_LANGUAGE, getContentLanguage());
        }
        return new JSONObject(hashMap);
    }

    public static class Builder {
        boolean mFromJSON;
        StorageMetadata mMetadata;

        public Builder() {
            this.mMetadata = new StorageMetadata();
        }

        public Builder(StorageMetadata storageMetadata) {
            this.mMetadata = new StorageMetadata(false);
        }

        Builder(JSONObject jSONObject, StorageReference storageReference) throws JSONException {
            this(jSONObject);
            StorageReference unused = this.mMetadata.mStorageRef = storageReference;
        }

        Builder(JSONObject jSONObject) throws JSONException {
            this.mMetadata = new StorageMetadata();
            if (jSONObject != null) {
                parseJSON(jSONObject);
                this.mFromJSON = true;
            }
        }

        private String extractString(JSONObject jSONObject, String str) throws JSONException {
            if (!jSONObject.has(str) || jSONObject.isNull(str)) {
                return null;
            }
            return jSONObject.getString(str);
        }

        private void parseJSON(JSONObject jSONObject) throws JSONException {
            String unused = this.mMetadata.mGeneration = jSONObject.optString(StorageMetadata.GENERATION_KEY);
            String unused2 = this.mMetadata.mPath = jSONObject.optString(StorageMetadata.NAME_KEY);
            String unused3 = this.mMetadata.mBucket = jSONObject.optString(StorageMetadata.BUCKET_KEY);
            String unused4 = this.mMetadata.mMetadataGeneration = jSONObject.optString(StorageMetadata.META_GENERATION_KEY);
            String unused5 = this.mMetadata.mCreationTime = jSONObject.optString(StorageMetadata.TIME_CREATED_KEY);
            String unused6 = this.mMetadata.mUpdatedTime = jSONObject.optString(StorageMetadata.TIME_UPDATED_KEY);
            long unused7 = this.mMetadata.mSize = jSONObject.optLong(StorageMetadata.SIZE_KEY);
            String unused8 = this.mMetadata.mMD5Hash = jSONObject.optString(StorageMetadata.MD5_HASH_KEY);
            if (jSONObject.has(StorageMetadata.CUSTOM_METADATA_KEY) && !jSONObject.isNull(StorageMetadata.CUSTOM_METADATA_KEY)) {
                JSONObject jSONObject2 = jSONObject.getJSONObject(StorageMetadata.CUSTOM_METADATA_KEY);
                Iterator<String> keys = jSONObject2.keys();
                while (keys.hasNext()) {
                    String next = keys.next();
                    setCustomMetadata(next, jSONObject2.getString(next));
                }
            }
            String extractString = extractString(jSONObject, StorageMetadata.CONTENT_TYPE_KEY);
            if (extractString != null) {
                setContentType(extractString);
            }
            String extractString2 = extractString(jSONObject, StorageMetadata.CACHE_CONTROL);
            if (extractString2 != null) {
                setCacheControl(extractString2);
            }
            String extractString3 = extractString(jSONObject, StorageMetadata.CONTENT_DISPOSITION);
            if (extractString3 != null) {
                setContentDisposition(extractString3);
            }
            String extractString4 = extractString(jSONObject, StorageMetadata.CONTENT_ENCODING);
            if (extractString4 != null) {
                setContentEncoding(extractString4);
            }
            String extractString5 = extractString(jSONObject, StorageMetadata.CONTENT_LANGUAGE);
            if (extractString5 != null) {
                setContentLanguage(extractString5);
            }
        }

        public StorageMetadata build() {
            return new StorageMetadata(this.mFromJSON);
        }

        public Builder setContentLanguage(String str) {
            MetadataValue unused = this.mMetadata.mContentLanguage = MetadataValue.withUserValue(str);
            return this;
        }

        public String getContentLanguage() {
            return (String) this.mMetadata.mContentLanguage.getValue();
        }

        public Builder setContentEncoding(String str) {
            MetadataValue unused = this.mMetadata.mContentEncoding = MetadataValue.withUserValue(str);
            return this;
        }

        public String getContentEncoding() {
            return (String) this.mMetadata.mContentEncoding.getValue();
        }

        public Builder setContentDisposition(String str) {
            MetadataValue unused = this.mMetadata.mContentDisposition = MetadataValue.withUserValue(str);
            return this;
        }

        public String getContentDisposition() {
            return (String) this.mMetadata.mContentDisposition.getValue();
        }

        public Builder setCacheControl(String str) {
            MetadataValue unused = this.mMetadata.mCacheControl = MetadataValue.withUserValue(str);
            return this;
        }

        public String getCacheControl() {
            return (String) this.mMetadata.mCacheControl.getValue();
        }

        public Builder setCustomMetadata(String str, String str2) {
            if (!this.mMetadata.mCustomMetadata.isUserProvided()) {
                MetadataValue unused = this.mMetadata.mCustomMetadata = MetadataValue.withUserValue(new HashMap());
            }
            ((Map) this.mMetadata.mCustomMetadata.getValue()).put(str, str2);
            return this;
        }

        public Builder setContentType(String str) {
            MetadataValue unused = this.mMetadata.mContentType = MetadataValue.withUserValue(str);
            return this;
        }

        public String getContentType() {
            return (String) this.mMetadata.mContentType.getValue();
        }
    }
}

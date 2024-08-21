package com.google.firebase.firestore;

import com.google.firebase.Timestamp;
import com.google.firebase.firestore.model.Document;
import com.google.firebase.firestore.model.DocumentKey;
import com.google.firebase.firestore.model.FieldPath;
import com.google.firebase.firestore.util.CustomClassMapper;
import com.google.firebase.firestore.util.Preconditions;
import com.google.firestore.p009v1.Value;
import java.util.Date;
import java.util.Map;

public class DocumentSnapshot {
    private final Document doc;
    private final FirebaseFirestore firestore;
    private final DocumentKey key;
    private final SnapshotMetadata metadata;

    public enum ServerTimestampBehavior {
        NONE,
        ESTIMATE,
        PREVIOUS;
        
        static final ServerTimestampBehavior DEFAULT = null;

        static {
            ServerTimestampBehavior serverTimestampBehavior;
            DEFAULT = serverTimestampBehavior;
        }
    }

    DocumentSnapshot(FirebaseFirestore firebaseFirestore, DocumentKey documentKey, Document document, boolean z, boolean z2) {
        this.firestore = (FirebaseFirestore) Preconditions.checkNotNull(firebaseFirestore);
        this.key = (DocumentKey) Preconditions.checkNotNull(documentKey);
        this.doc = document;
        this.metadata = new SnapshotMetadata(z2, z);
    }

    static DocumentSnapshot fromDocument(FirebaseFirestore firebaseFirestore, Document document, boolean z, boolean z2) {
        return new DocumentSnapshot(firebaseFirestore, document.getKey(), document, z, z2);
    }

    static DocumentSnapshot fromNoDocument(FirebaseFirestore firebaseFirestore, DocumentKey documentKey, boolean z, boolean z2) {
        return new DocumentSnapshot(firebaseFirestore, documentKey, (Document) null, z, z2);
    }

    public String getId() {
        return this.key.getPath().getLastSegment();
    }

    public SnapshotMetadata getMetadata() {
        return this.metadata;
    }

    public boolean exists() {
        return this.doc != null;
    }

    /* access modifiers changed from: package-private */
    public Document getDocument() {
        return this.doc;
    }

    public Map<String, Object> getData() {
        return getData(ServerTimestampBehavior.DEFAULT);
    }

    public Map<String, Object> getData(ServerTimestampBehavior serverTimestampBehavior) {
        Preconditions.checkNotNull(serverTimestampBehavior, "Provided serverTimestampBehavior value must not be null.");
        UserDataWriter userDataWriter = new UserDataWriter(this.firestore, serverTimestampBehavior);
        Document document = this.doc;
        if (document == null) {
            return null;
        }
        return userDataWriter.convertObject(document.getData().getFieldsMap());
    }

    public <T> T toObject(Class<T> cls) {
        return toObject(cls, ServerTimestampBehavior.DEFAULT);
    }

    public <T> T toObject(Class<T> cls, ServerTimestampBehavior serverTimestampBehavior) {
        Preconditions.checkNotNull(cls, "Provided POJO type must not be null.");
        Preconditions.checkNotNull(serverTimestampBehavior, "Provided serverTimestampBehavior value must not be null.");
        Map<String, Object> data = getData(serverTimestampBehavior);
        if (data == null) {
            return null;
        }
        return CustomClassMapper.convertToCustomClass(data, cls, getReference());
    }

    public boolean contains(String str) {
        return contains(FieldPath.fromDotSeparatedPath(str));
    }

    public boolean contains(FieldPath fieldPath) {
        Preconditions.checkNotNull(fieldPath, "Provided field path must not be null.");
        Document document = this.doc;
        return (document == null || document.getField(fieldPath.getInternalPath()) == null) ? false : true;
    }

    public Object get(String str) {
        return get(FieldPath.fromDotSeparatedPath(str), ServerTimestampBehavior.DEFAULT);
    }

    public Object get(String str, ServerTimestampBehavior serverTimestampBehavior) {
        return get(FieldPath.fromDotSeparatedPath(str), serverTimestampBehavior);
    }

    public Object get(FieldPath fieldPath) {
        return get(fieldPath, ServerTimestampBehavior.DEFAULT);
    }

    public Object get(FieldPath fieldPath, ServerTimestampBehavior serverTimestampBehavior) {
        Preconditions.checkNotNull(fieldPath, "Provided field path must not be null.");
        Preconditions.checkNotNull(serverTimestampBehavior, "Provided serverTimestampBehavior value must not be null.");
        return getInternal(fieldPath.getInternalPath(), serverTimestampBehavior);
    }

    public <T> T get(String str, Class<T> cls) {
        return get(FieldPath.fromDotSeparatedPath(str), cls, ServerTimestampBehavior.DEFAULT);
    }

    public <T> T get(String str, Class<T> cls, ServerTimestampBehavior serverTimestampBehavior) {
        return get(FieldPath.fromDotSeparatedPath(str), cls, serverTimestampBehavior);
    }

    public <T> T get(FieldPath fieldPath, Class<T> cls) {
        return get(fieldPath, cls, ServerTimestampBehavior.DEFAULT);
    }

    public <T> T get(FieldPath fieldPath, Class<T> cls, ServerTimestampBehavior serverTimestampBehavior) {
        Object obj = get(fieldPath, serverTimestampBehavior);
        if (obj == null) {
            return null;
        }
        return CustomClassMapper.convertToCustomClass(obj, cls, getReference());
    }

    public Boolean getBoolean(String str) {
        return (Boolean) getTypedValue(str, Boolean.class);
    }

    public Double getDouble(String str) {
        Number number = (Number) getTypedValue(str, Number.class);
        if (number != null) {
            return Double.valueOf(number.doubleValue());
        }
        return null;
    }

    public String getString(String str) {
        return (String) getTypedValue(str, String.class);
    }

    public Long getLong(String str) {
        Number number = (Number) getTypedValue(str, Number.class);
        if (number != null) {
            return Long.valueOf(number.longValue());
        }
        return null;
    }

    public Date getDate(String str) {
        return getDate(str, ServerTimestampBehavior.DEFAULT);
    }

    public Date getDate(String str, ServerTimestampBehavior serverTimestampBehavior) {
        Preconditions.checkNotNull(str, "Provided field path must not be null.");
        Preconditions.checkNotNull(serverTimestampBehavior, "Provided serverTimestampBehavior value must not be null.");
        Timestamp timestamp = getTimestamp(str, serverTimestampBehavior);
        if (timestamp != null) {
            return timestamp.toDate();
        }
        return null;
    }

    public Timestamp getTimestamp(String str) {
        return getTimestamp(str, ServerTimestampBehavior.DEFAULT);
    }

    public Timestamp getTimestamp(String str, ServerTimestampBehavior serverTimestampBehavior) {
        Preconditions.checkNotNull(str, "Provided field path must not be null.");
        Preconditions.checkNotNull(serverTimestampBehavior, "Provided serverTimestampBehavior value must not be null.");
        return (Timestamp) castTypedValue(getInternal(FieldPath.fromDotSeparatedPath(str).getInternalPath(), serverTimestampBehavior), str, Timestamp.class);
    }

    public Blob getBlob(String str) {
        return (Blob) getTypedValue(str, Blob.class);
    }

    public GeoPoint getGeoPoint(String str) {
        return (GeoPoint) getTypedValue(str, GeoPoint.class);
    }

    public DocumentReference getDocumentReference(String str) {
        return (DocumentReference) getTypedValue(str, DocumentReference.class);
    }

    public DocumentReference getReference() {
        return new DocumentReference(this.key, this.firestore);
    }

    private <T> T getTypedValue(String str, Class<T> cls) {
        Preconditions.checkNotNull(str, "Provided field must not be null.");
        return castTypedValue(get(str, ServerTimestampBehavior.DEFAULT), str, cls);
    }

    private <T> T castTypedValue(Object obj, String str, Class<T> cls) {
        if (obj == null) {
            return null;
        }
        if (cls.isInstance(obj)) {
            return cls.cast(obj);
        }
        throw new RuntimeException("Field '" + str + "' is not a " + cls.getName());
    }

    private Object getInternal(FieldPath fieldPath, ServerTimestampBehavior serverTimestampBehavior) {
        Value field;
        Document document = this.doc;
        if (document == null || (field = document.getField(fieldPath)) == null) {
            return null;
        }
        return new UserDataWriter(this.firestore, serverTimestampBehavior).convertValue(field);
    }

    public boolean equals(Object obj) {
        Document document;
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof DocumentSnapshot)) {
            return false;
        }
        DocumentSnapshot documentSnapshot = (DocumentSnapshot) obj;
        if (!this.firestore.equals(documentSnapshot.firestore) || !this.key.equals(documentSnapshot.key) || ((document = this.doc) != null ? !document.equals(documentSnapshot.doc) : documentSnapshot.doc != null) || !this.metadata.equals(documentSnapshot.metadata)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        int hashCode = ((this.firestore.hashCode() * 31) + this.key.hashCode()) * 31;
        Document document = this.doc;
        return ((hashCode + (document != null ? document.hashCode() : 0)) * 31) + this.metadata.hashCode();
    }

    public String toString() {
        return "DocumentSnapshot{key=" + this.key + ", metadata=" + this.metadata + ", doc=" + this.doc + '}';
    }
}

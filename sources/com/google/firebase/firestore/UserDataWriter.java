package com.google.firebase.firestore;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.model.DatabaseId;
import com.google.firebase.firestore.model.DocumentKey;
import com.google.firebase.firestore.model.ServerTimestamps;
import com.google.firebase.firestore.model.Values;
import com.google.firebase.firestore.util.Assert;
import com.google.firebase.firestore.util.Logger;
import com.google.firestore.p009v1.ArrayValue;
import com.google.firestore.p009v1.Value;
import com.google.protobuf.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserDataWriter {
    private final FirebaseFirestore firestore;
    private final DocumentSnapshot.ServerTimestampBehavior serverTimestampBehavior;

    UserDataWriter(FirebaseFirestore firebaseFirestore, DocumentSnapshot.ServerTimestampBehavior serverTimestampBehavior2) {
        this.firestore = firebaseFirestore;
        this.serverTimestampBehavior = serverTimestampBehavior2;
    }

    /* access modifiers changed from: package-private */
    public Object convertValue(Value value) {
        switch (Values.typeOrder(value)) {
            case 0:
                return null;
            case 1:
                return Boolean.valueOf(value.getBooleanValue());
            case 2:
                if (value.getValueTypeCase().equals(Value.ValueTypeCase.INTEGER_VALUE)) {
                    return Long.valueOf(value.getIntegerValue());
                }
                return Double.valueOf(value.getDoubleValue());
            case 3:
                return convertTimestamp(value.getTimestampValue());
            case 4:
                return convertServerTimestamp(value);
            case 5:
                return value.getStringValue();
            case 6:
                return Blob.fromByteString(value.getBytesValue());
            case 7:
                return convertReference(value);
            case 8:
                return new GeoPoint(value.getGeoPointValue().getLatitude(), value.getGeoPointValue().getLongitude());
            case 9:
                return convertArray(value.getArrayValue());
            case 10:
                return convertObject(value.getMapValue().getFieldsMap());
            default:
                throw Assert.fail("Unknown value type: " + value.getValueTypeCase(), new Object[0]);
        }
    }

    /* access modifiers changed from: package-private */
    public Map<String, Object> convertObject(Map<String, Value> map) {
        HashMap hashMap = new HashMap();
        for (Map.Entry next : map.entrySet()) {
            hashMap.put((String) next.getKey(), convertValue((Value) next.getValue()));
        }
        return hashMap;
    }

    /* renamed from: com.google.firebase.firestore.UserDataWriter$1 */
    static /* synthetic */ class C18811 {

        /* renamed from: $SwitchMap$com$google$firebase$firestore$DocumentSnapshot$ServerTimestampBehavior */
        static final /* synthetic */ int[] f326x1f31a5ae;

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|6) */
        /* JADX WARNING: Code restructure failed: missing block: B:7:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        static {
            /*
                com.google.firebase.firestore.DocumentSnapshot$ServerTimestampBehavior[] r0 = com.google.firebase.firestore.DocumentSnapshot.ServerTimestampBehavior.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                f326x1f31a5ae = r0
                com.google.firebase.firestore.DocumentSnapshot$ServerTimestampBehavior r1 = com.google.firebase.firestore.DocumentSnapshot.ServerTimestampBehavior.PREVIOUS     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = f326x1f31a5ae     // Catch:{ NoSuchFieldError -> 0x001d }
                com.google.firebase.firestore.DocumentSnapshot$ServerTimestampBehavior r1 = com.google.firebase.firestore.DocumentSnapshot.ServerTimestampBehavior.ESTIMATE     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.firestore.UserDataWriter.C18811.<clinit>():void");
        }
    }

    private Object convertServerTimestamp(Value value) {
        int i = C18811.f326x1f31a5ae[this.serverTimestampBehavior.ordinal()];
        if (i == 1) {
            Value previousValue = ServerTimestamps.getPreviousValue(value);
            if (previousValue == null) {
                return null;
            }
            return convertValue(previousValue);
        } else if (i != 2) {
            return null;
        } else {
            return convertTimestamp(ServerTimestamps.getLocalWriteTime(value));
        }
    }

    private Object convertTimestamp(Timestamp timestamp) {
        return new com.google.firebase.Timestamp(timestamp.getSeconds(), timestamp.getNanos());
    }

    private List<Object> convertArray(ArrayValue arrayValue) {
        ArrayList arrayList = new ArrayList(arrayValue.getValuesCount());
        for (Value convertValue : arrayValue.getValuesList()) {
            arrayList.add(convertValue(convertValue));
        }
        return arrayList;
    }

    private Object convertReference(Value value) {
        DatabaseId fromName = DatabaseId.fromName(value.getReferenceValue());
        DocumentKey fromName2 = DocumentKey.fromName(value.getReferenceValue());
        DatabaseId databaseId = this.firestore.getDatabaseId();
        if (!fromName.equals(databaseId)) {
            Logger.warn("DocumentSnapshot", "Document %s contains a document reference within a different database (%s/%s) which is not supported. It will be treated as a reference in the current database (%s/%s) instead.", fromName2.getPath(), fromName.getProjectId(), fromName.getDatabaseId(), databaseId.getProjectId(), databaseId.getDatabaseId());
        }
        return new DocumentReference(fromName2, this.firestore);
    }
}

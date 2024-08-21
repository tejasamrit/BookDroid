package com.google.firebase.firestore.model;

import com.google.firebase.firestore.model.mutation.FieldMask;
import com.google.firebase.firestore.util.Assert;
import com.google.firestore.p009v1.MapValue;
import com.google.firestore.p009v1.Value;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ObjectValue {
    private static final ObjectValue EMPTY_INSTANCE = new ObjectValue((Value) Value.newBuilder().setMapValue(MapValue.getDefaultInstance()).build());
    private Value internalValue;

    public static ObjectValue fromMap(Map<String, Value> map) {
        return new ObjectValue((Value) Value.newBuilder().setMapValue(MapValue.newBuilder().putAllFields(map)).build());
    }

    public ObjectValue(Value value) {
        Assert.hardAssert(value.getValueTypeCase() == Value.ValueTypeCase.MAP_VALUE, "ObjectValues should be backed by a MapValue", new Object[0]);
        Assert.hardAssert(!ServerTimestamps.isServerTimestamp(value), "ServerTimestamps should not be used as an ObjectValue", new Object[0]);
        this.internalValue = value;
    }

    public static ObjectValue emptyObject() {
        return EMPTY_INSTANCE;
    }

    public static Builder newBuilder() {
        return EMPTY_INSTANCE.toBuilder();
    }

    public Map<String, Value> getFieldsMap() {
        return this.internalValue.getMapValue().getFieldsMap();
    }

    public FieldMask getFieldMask() {
        return extractFieldMask(this.internalValue.getMapValue());
    }

    private FieldMask extractFieldMask(MapValue mapValue) {
        HashSet hashSet = new HashSet();
        for (Map.Entry next : mapValue.getFieldsMap().entrySet()) {
            FieldPath fromSingleSegment = FieldPath.fromSingleSegment((String) next.getKey());
            if (Values.isMapValue((Value) next.getValue())) {
                Set<FieldPath> mask = extractFieldMask(((Value) next.getValue()).getMapValue()).getMask();
                if (mask.isEmpty()) {
                    hashSet.add(fromSingleSegment);
                } else {
                    for (FieldPath append : mask) {
                        hashSet.add((FieldPath) fromSingleSegment.append(append));
                    }
                }
            } else {
                hashSet.add(fromSingleSegment);
            }
        }
        return FieldMask.fromSet(hashSet);
    }

    public Value get(FieldPath fieldPath) {
        if (fieldPath.isEmpty()) {
            return this.internalValue;
        }
        Value value = this.internalValue;
        for (int i = 0; i < fieldPath.length() - 1; i++) {
            value = value.getMapValue().getFieldsOrDefault(fieldPath.getSegment(i), (Value) null);
            if (!Values.isMapValue(value)) {
                return null;
            }
        }
        return value.getMapValue().getFieldsOrDefault(fieldPath.getLastSegment(), (Value) null);
    }

    public Value getProto() {
        return this.internalValue;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof ObjectValue) {
            return Values.equals(this.internalValue, ((ObjectValue) obj).internalValue);
        }
        return false;
    }

    public int hashCode() {
        return this.internalValue.hashCode();
    }

    public Builder toBuilder() {
        return new Builder(this);
    }

    public static class Builder {
        private ObjectValue baseObject;
        private Map<String, Object> overlayMap = new HashMap();

        Builder(ObjectValue objectValue) {
            this.baseObject = objectValue;
        }

        public Builder set(FieldPath fieldPath, Value value) {
            Assert.hardAssert(!fieldPath.isEmpty(), "Cannot set field for empty path on ObjectValue", new Object[0]);
            setOverlay(fieldPath, value);
            return this;
        }

        public Builder delete(FieldPath fieldPath) {
            Assert.hardAssert(!fieldPath.isEmpty(), "Cannot delete field for empty path on ObjectValue", new Object[0]);
            setOverlay(fieldPath, (Value) null);
            return this;
        }

        private void setOverlay(FieldPath fieldPath, Value value) {
            Map<String, Object> hashMap;
            Map<String, Object> map = this.overlayMap;
            for (int i = 0; i < fieldPath.length() - 1; i++) {
                String segment = fieldPath.getSegment(i);
                Object obj = map.get(segment);
                if (obj instanceof Map) {
                    hashMap = (Map) obj;
                } else {
                    if (obj instanceof Value) {
                        Value value2 = (Value) obj;
                        if (value2.getValueTypeCase() == Value.ValueTypeCase.MAP_VALUE) {
                            HashMap hashMap2 = new HashMap(value2.getMapValue().getFieldsMap());
                            map.put(segment, hashMap2);
                            map = hashMap2;
                        }
                    }
                    hashMap = new HashMap<>();
                    map.put(segment, hashMap);
                }
                map = hashMap;
            }
            map.put(fieldPath.getLastSegment(), value);
        }

        public ObjectValue build() {
            MapValue applyOverlay = applyOverlay(FieldPath.EMPTY_PATH, this.overlayMap);
            if (applyOverlay != null) {
                return new ObjectValue((Value) Value.newBuilder().setMapValue(applyOverlay).build());
            }
            return this.baseObject;
        }

        private MapValue applyOverlay(FieldPath fieldPath, Map<String, Object> map) {
            MapValue.Builder builder;
            Value value = this.baseObject.get(fieldPath);
            if (Values.isMapValue(value)) {
                builder = (MapValue.Builder) value.getMapValue().toBuilder();
            } else {
                builder = MapValue.newBuilder();
            }
            boolean z = false;
            for (Map.Entry next : map.entrySet()) {
                String str = (String) next.getKey();
                Object value2 = next.getValue();
                if (value2 instanceof Map) {
                    MapValue applyOverlay = applyOverlay((FieldPath) fieldPath.append(str), (Map) value2);
                    if (applyOverlay != null) {
                        builder.putFields(str, (Value) Value.newBuilder().setMapValue(applyOverlay).build());
                    }
                } else if (value2 instanceof Value) {
                    builder.putFields(str, (Value) value2);
                } else if (builder.containsFields(str)) {
                    Assert.hardAssert(value2 == null, "Expected entry to be a Map, a Value or null", new Object[0]);
                    builder.removeFields(str);
                }
                z = true;
            }
            if (z) {
                return (MapValue) builder.build();
            }
            return null;
        }
    }
}

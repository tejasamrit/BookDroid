package com.google.firebase.firestore;

import com.google.firebase.Timestamp;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.core.UserData;
import com.google.firebase.firestore.model.DatabaseId;
import com.google.firebase.firestore.model.FieldPath;
import com.google.firebase.firestore.model.ObjectValue;
import com.google.firebase.firestore.model.mutation.ArrayTransformOperation;
import com.google.firebase.firestore.model.mutation.FieldMask;
import com.google.firebase.firestore.model.mutation.NumericIncrementTransformOperation;
import com.google.firebase.firestore.model.mutation.ServerTimestampOperation;
import com.google.firebase.firestore.util.Assert;
import com.google.firebase.firestore.util.CustomClassMapper;
import com.google.firebase.firestore.util.Preconditions;
import com.google.firebase.firestore.util.Util;
import com.google.firestore.p009v1.ArrayValue;
import com.google.firestore.p009v1.MapValue;
import com.google.firestore.p009v1.Value;
import com.google.protobuf.NullValue;
import com.google.type.LatLng;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public final class UserDataReader {
    private final DatabaseId databaseId;

    public UserDataReader(DatabaseId databaseId2) {
        this.databaseId = databaseId2;
    }

    public UserData.ParsedSetData parseSetData(Object obj) {
        UserData.ParseAccumulator parseAccumulator = new UserData.ParseAccumulator(UserData.Source.Set);
        return parseAccumulator.toSetData(convertAndParseDocumentData(obj, parseAccumulator.rootContext()));
    }

    public UserData.ParsedSetData parseMergeData(Object obj, FieldMask fieldMask) {
        UserData.ParseAccumulator parseAccumulator = new UserData.ParseAccumulator(UserData.Source.MergeSet);
        ObjectValue convertAndParseDocumentData = convertAndParseDocumentData(obj, parseAccumulator.rootContext());
        if (fieldMask == null) {
            return parseAccumulator.toMergeData(convertAndParseDocumentData);
        }
        for (FieldPath next : fieldMask.getMask()) {
            if (!parseAccumulator.contains(next)) {
                throw new IllegalArgumentException("Field '" + next.toString() + "' is specified in your field mask but not in your input data.");
            }
        }
        return parseAccumulator.toMergeData(convertAndParseDocumentData, fieldMask);
    }

    public UserData.ParsedUpdateData parseUpdateData(Map<String, Object> map) {
        Preconditions.checkNotNull(map, "Provided update data must not be null.");
        UserData.ParseAccumulator parseAccumulator = new UserData.ParseAccumulator(UserData.Source.Update);
        UserData.ParseContext rootContext = parseAccumulator.rootContext();
        ObjectValue.Builder newBuilder = ObjectValue.newBuilder();
        for (Map.Entry next : map.entrySet()) {
            FieldPath internalPath = FieldPath.fromDotSeparatedPath((String) next.getKey()).getInternalPath();
            Object value = next.getValue();
            if (value instanceof FieldValue.DeleteFieldValue) {
                rootContext.addToFieldMask(internalPath);
            } else {
                Value convertAndParseFieldData = convertAndParseFieldData(value, rootContext.childContext(internalPath));
                if (convertAndParseFieldData != null) {
                    rootContext.addToFieldMask(internalPath);
                    newBuilder.set(internalPath, convertAndParseFieldData);
                }
            }
        }
        return parseAccumulator.toUpdateData(newBuilder.build());
    }

    public UserData.ParsedUpdateData parseUpdateData(List<Object> list) {
        FieldPath fieldPath;
        Assert.hardAssert(list.size() % 2 == 0, "Expected fieldAndValues to contain an even number of elements", new Object[0]);
        UserData.ParseAccumulator parseAccumulator = new UserData.ParseAccumulator(UserData.Source.Update);
        UserData.ParseContext rootContext = parseAccumulator.rootContext();
        ObjectValue.Builder newBuilder = ObjectValue.newBuilder();
        Iterator<Object> it = list.iterator();
        while (it.hasNext()) {
            Object next = it.next();
            Object next2 = it.next();
            boolean z = next instanceof String;
            Assert.hardAssert(z || (next instanceof FieldPath), "Expected argument to be String or FieldPath.", new Object[0]);
            if (z) {
                fieldPath = FieldPath.fromDotSeparatedPath((String) next).getInternalPath();
            } else {
                fieldPath = ((FieldPath) next).getInternalPath();
            }
            if (next2 instanceof FieldValue.DeleteFieldValue) {
                rootContext.addToFieldMask(fieldPath);
            } else {
                Value convertAndParseFieldData = convertAndParseFieldData(next2, rootContext.childContext(fieldPath));
                if (convertAndParseFieldData != null) {
                    rootContext.addToFieldMask(fieldPath);
                    newBuilder.set(fieldPath, convertAndParseFieldData);
                }
            }
        }
        return parseAccumulator.toUpdateData(newBuilder.build());
    }

    public Value parseQueryValue(Object obj) {
        return parseQueryValue(obj, false);
    }

    public Value parseQueryValue(Object obj, boolean z) {
        UserData.ParseAccumulator parseAccumulator = new UserData.ParseAccumulator(z ? UserData.Source.ArrayArgument : UserData.Source.Argument);
        Value convertAndParseFieldData = convertAndParseFieldData(obj, parseAccumulator.rootContext());
        Assert.hardAssert(convertAndParseFieldData != null, "Parsed data should not be null.", new Object[0]);
        Assert.hardAssert(parseAccumulator.getFieldTransforms().isEmpty(), "Field transforms should have been disallowed.", new Object[0]);
        return convertAndParseFieldData;
    }

    private Value convertAndParseFieldData(Object obj, UserData.ParseContext parseContext) {
        return parseData(CustomClassMapper.convertToPlainJavaTypes(obj), parseContext);
    }

    private ObjectValue convertAndParseDocumentData(Object obj, UserData.ParseContext parseContext) {
        if (!obj.getClass().isArray()) {
            Value parseData = parseData(CustomClassMapper.convertToPlainJavaTypes(obj), parseContext);
            if (parseData.getValueTypeCase() == Value.ValueTypeCase.MAP_VALUE) {
                return new ObjectValue(parseData);
            }
            throw new IllegalArgumentException("Invalid data. Data must be a Map<String, Object> or a suitable POJO object, but it was " + "of type: " + Util.typeName(obj));
        }
        throw new IllegalArgumentException("Invalid data. Data must be a Map<String, Object> or a suitable POJO object, but it was " + "an array");
    }

    private Value parseData(Object obj, UserData.ParseContext parseContext) {
        if (obj instanceof Map) {
            return parseMap((Map) obj, parseContext);
        }
        if (obj instanceof FieldValue) {
            parseSentinelFieldValue((FieldValue) obj, parseContext);
            return null;
        }
        if (parseContext.getPath() != null) {
            parseContext.addToFieldMask(parseContext.getPath());
        }
        if (!(obj instanceof List)) {
            return parseScalarValue(obj, parseContext);
        }
        if (!parseContext.isArrayElement() || parseContext.getDataSource() == UserData.Source.ArrayArgument) {
            return parseList((List) obj, parseContext);
        }
        throw parseContext.createError("Nested arrays are not supported");
    }

    private <K, V> Value parseMap(Map<K, V> map, UserData.ParseContext parseContext) {
        if (map.isEmpty()) {
            if (parseContext.getPath() != null && !parseContext.getPath().isEmpty()) {
                parseContext.addToFieldMask(parseContext.getPath());
            }
            return (Value) Value.newBuilder().setMapValue(MapValue.getDefaultInstance()).build();
        }
        MapValue.Builder newBuilder = MapValue.newBuilder();
        for (Map.Entry next : map.entrySet()) {
            if (next.getKey() instanceof String) {
                String str = (String) next.getKey();
                Value parseData = parseData(next.getValue(), parseContext.childContext(str));
                if (parseData != null) {
                    newBuilder.putFields(str, parseData);
                }
            } else {
                throw parseContext.createError(String.format("Non-String Map key (%s) is not allowed", new Object[]{next.getValue()}));
            }
        }
        return (Value) Value.newBuilder().setMapValue(newBuilder).build();
    }

    private <T> Value parseList(List<T> list, UserData.ParseContext parseContext) {
        ArrayValue.Builder newBuilder = ArrayValue.newBuilder();
        int i = 0;
        for (T parseData : list) {
            Value parseData2 = parseData(parseData, parseContext.childContext(i));
            if (parseData2 == null) {
                parseData2 = (Value) Value.newBuilder().setNullValue(NullValue.NULL_VALUE).build();
            }
            newBuilder.addValues(parseData2);
            i++;
        }
        return (Value) Value.newBuilder().setArrayValue(newBuilder).build();
    }

    private void parseSentinelFieldValue(FieldValue fieldValue, UserData.ParseContext parseContext) {
        boolean z = true;
        if (!parseContext.isWrite()) {
            throw parseContext.createError(String.format("%s() can only be used with set() and update()", new Object[]{fieldValue.getMethodName()}));
        } else if (parseContext.getPath() == null) {
            throw parseContext.createError(String.format("%s() is not currently supported inside arrays", new Object[]{fieldValue.getMethodName()}));
        } else if (fieldValue instanceof FieldValue.DeleteFieldValue) {
            if (parseContext.getDataSource() == UserData.Source.MergeSet) {
                parseContext.addToFieldMask(parseContext.getPath());
            } else if (parseContext.getDataSource() == UserData.Source.Update) {
                if (parseContext.getPath().length() <= 0) {
                    z = false;
                }
                Assert.hardAssert(z, "FieldValue.delete() at the top level should have already been handled.", new Object[0]);
                throw parseContext.createError("FieldValue.delete() can only appear at the top level of your update data");
            } else {
                throw parseContext.createError("FieldValue.delete() can only be used with update() and set() with SetOptions.merge()");
            }
        } else if (fieldValue instanceof FieldValue.ServerTimestampFieldValue) {
            parseContext.addToFieldTransforms(parseContext.getPath(), ServerTimestampOperation.getInstance());
        } else if (fieldValue instanceof FieldValue.ArrayUnionFieldValue) {
            parseContext.addToFieldTransforms(parseContext.getPath(), new ArrayTransformOperation.Union(parseArrayTransformElements(((FieldValue.ArrayUnionFieldValue) fieldValue).getElements())));
        } else if (fieldValue instanceof FieldValue.ArrayRemoveFieldValue) {
            parseContext.addToFieldTransforms(parseContext.getPath(), new ArrayTransformOperation.Remove(parseArrayTransformElements(((FieldValue.ArrayRemoveFieldValue) fieldValue).getElements())));
        } else if (fieldValue instanceof FieldValue.NumericIncrementFieldValue) {
            parseContext.addToFieldTransforms(parseContext.getPath(), new NumericIncrementTransformOperation(parseQueryValue(((FieldValue.NumericIncrementFieldValue) fieldValue).getOperand())));
        } else {
            throw Assert.fail("Unknown FieldValue type: %s", Util.typeName(fieldValue));
        }
    }

    private Value parseScalarValue(Object obj, UserData.ParseContext parseContext) {
        if (obj == null) {
            return (Value) Value.newBuilder().setNullValue(NullValue.NULL_VALUE).build();
        }
        if (obj instanceof Integer) {
            return (Value) Value.newBuilder().setIntegerValue((long) ((Integer) obj).intValue()).build();
        }
        if (obj instanceof Long) {
            return (Value) Value.newBuilder().setIntegerValue(((Long) obj).longValue()).build();
        }
        if (obj instanceof Float) {
            return (Value) Value.newBuilder().setDoubleValue(((Float) obj).doubleValue()).build();
        }
        if (obj instanceof Double) {
            return (Value) Value.newBuilder().setDoubleValue(((Double) obj).doubleValue()).build();
        }
        if (obj instanceof Boolean) {
            return (Value) Value.newBuilder().setBooleanValue(((Boolean) obj).booleanValue()).build();
        }
        if (obj instanceof String) {
            return (Value) Value.newBuilder().setStringValue((String) obj).build();
        }
        if (obj instanceof Date) {
            return parseTimestamp(new Timestamp((Date) obj));
        }
        if (obj instanceof Timestamp) {
            return parseTimestamp((Timestamp) obj);
        }
        if (obj instanceof GeoPoint) {
            GeoPoint geoPoint = (GeoPoint) obj;
            return (Value) Value.newBuilder().setGeoPointValue(LatLng.newBuilder().setLatitude(geoPoint.getLatitude()).setLongitude(geoPoint.getLongitude())).build();
        } else if (obj instanceof Blob) {
            return (Value) Value.newBuilder().setBytesValue(((Blob) obj).toByteString()).build();
        } else {
            if (obj instanceof DocumentReference) {
                DocumentReference documentReference = (DocumentReference) obj;
                if (documentReference.getFirestore() != null) {
                    DatabaseId databaseId2 = documentReference.getFirestore().getDatabaseId();
                    if (!databaseId2.equals(this.databaseId)) {
                        throw parseContext.createError(String.format("Document reference is for database %s/%s but should be for database %s/%s", new Object[]{databaseId2.getProjectId(), databaseId2.getDatabaseId(), this.databaseId.getProjectId(), this.databaseId.getDatabaseId()}));
                    }
                }
                return (Value) Value.newBuilder().setReferenceValue(String.format("projects/%s/databases/%s/documents/%s", new Object[]{this.databaseId.getProjectId(), this.databaseId.getDatabaseId(), documentReference.getPath()})).build();
            } else if (obj.getClass().isArray()) {
                throw parseContext.createError("Arrays are not supported; use a List instead");
            } else {
                throw parseContext.createError("Unsupported type: " + Util.typeName(obj));
            }
        }
    }

    private Value parseTimestamp(Timestamp timestamp) {
        return (Value) Value.newBuilder().setTimestampValue(com.google.protobuf.Timestamp.newBuilder().setSeconds(timestamp.getSeconds()).setNanos((timestamp.getNanoseconds() / 1000) * 1000)).build();
    }

    private List<Value> parseArrayTransformElements(List<Object> list) {
        UserData.ParseAccumulator parseAccumulator = new UserData.ParseAccumulator(UserData.Source.Argument);
        ArrayList arrayList = new ArrayList(list.size());
        for (int i = 0; i < list.size(); i++) {
            arrayList.add(convertAndParseFieldData(list.get(i), parseAccumulator.rootContext().childContext(i)));
        }
        return arrayList;
    }
}

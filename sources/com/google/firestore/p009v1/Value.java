package com.google.firestore.p009v1;

import com.google.firestore.p009v1.ArrayValue;
import com.google.firestore.p009v1.MapValue;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.NullValue;
import com.google.protobuf.Parser;
import com.google.protobuf.Timestamp;
import com.google.type.LatLng;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

/* renamed from: com.google.firestore.v1.Value */
public final class Value extends GeneratedMessageLite<Value, Builder> implements ValueOrBuilder {
    public static final int ARRAY_VALUE_FIELD_NUMBER = 9;
    public static final int BOOLEAN_VALUE_FIELD_NUMBER = 1;
    public static final int BYTES_VALUE_FIELD_NUMBER = 18;
    /* access modifiers changed from: private */
    public static final Value DEFAULT_INSTANCE;
    public static final int DOUBLE_VALUE_FIELD_NUMBER = 3;
    public static final int GEO_POINT_VALUE_FIELD_NUMBER = 8;
    public static final int INTEGER_VALUE_FIELD_NUMBER = 2;
    public static final int MAP_VALUE_FIELD_NUMBER = 6;
    public static final int NULL_VALUE_FIELD_NUMBER = 11;
    private static volatile Parser<Value> PARSER = null;
    public static final int REFERENCE_VALUE_FIELD_NUMBER = 5;
    public static final int STRING_VALUE_FIELD_NUMBER = 17;
    public static final int TIMESTAMP_VALUE_FIELD_NUMBER = 10;
    private int valueTypeCase_ = 0;
    private Object valueType_;

    private Value() {
    }

    /* renamed from: com.google.firestore.v1.Value$ValueTypeCase */
    public enum ValueTypeCase {
        NULL_VALUE(11),
        BOOLEAN_VALUE(1),
        INTEGER_VALUE(2),
        DOUBLE_VALUE(3),
        TIMESTAMP_VALUE(10),
        STRING_VALUE(17),
        BYTES_VALUE(18),
        REFERENCE_VALUE(5),
        GEO_POINT_VALUE(8),
        ARRAY_VALUE(9),
        MAP_VALUE(6),
        VALUETYPE_NOT_SET(0);
        
        private final int value;

        private ValueTypeCase(int i) {
            this.value = i;
        }

        @Deprecated
        public static ValueTypeCase valueOf(int i) {
            return forNumber(i);
        }

        public static ValueTypeCase forNumber(int i) {
            if (i == 0) {
                return VALUETYPE_NOT_SET;
            }
            if (i == 1) {
                return BOOLEAN_VALUE;
            }
            if (i == 2) {
                return INTEGER_VALUE;
            }
            if (i == 3) {
                return DOUBLE_VALUE;
            }
            if (i == 5) {
                return REFERENCE_VALUE;
            }
            if (i == 6) {
                return MAP_VALUE;
            }
            if (i == 17) {
                return STRING_VALUE;
            }
            if (i == 18) {
                return BYTES_VALUE;
            }
            switch (i) {
                case 8:
                    return GEO_POINT_VALUE;
                case 9:
                    return ARRAY_VALUE;
                case 10:
                    return TIMESTAMP_VALUE;
                case 11:
                    return NULL_VALUE;
                default:
                    return null;
            }
        }

        public int getNumber() {
            return this.value;
        }
    }

    public ValueTypeCase getValueTypeCase() {
        return ValueTypeCase.forNumber(this.valueTypeCase_);
    }

    /* access modifiers changed from: private */
    public void clearValueType() {
        this.valueTypeCase_ = 0;
        this.valueType_ = null;
    }

    public int getNullValueValue() {
        if (this.valueTypeCase_ == 11) {
            return ((Integer) this.valueType_).intValue();
        }
        return 0;
    }

    public NullValue getNullValue() {
        if (this.valueTypeCase_ != 11) {
            return NullValue.NULL_VALUE;
        }
        NullValue forNumber = NullValue.forNumber(((Integer) this.valueType_).intValue());
        return forNumber == null ? NullValue.UNRECOGNIZED : forNumber;
    }

    /* access modifiers changed from: private */
    public void setNullValueValue(int i) {
        this.valueTypeCase_ = 11;
        this.valueType_ = Integer.valueOf(i);
    }

    /* access modifiers changed from: private */
    public void setNullValue(NullValue nullValue) {
        this.valueType_ = Integer.valueOf(nullValue.getNumber());
        this.valueTypeCase_ = 11;
    }

    /* access modifiers changed from: private */
    public void clearNullValue() {
        if (this.valueTypeCase_ == 11) {
            this.valueTypeCase_ = 0;
            this.valueType_ = null;
        }
    }

    public boolean getBooleanValue() {
        if (this.valueTypeCase_ == 1) {
            return ((Boolean) this.valueType_).booleanValue();
        }
        return false;
    }

    /* access modifiers changed from: private */
    public void setBooleanValue(boolean z) {
        this.valueTypeCase_ = 1;
        this.valueType_ = Boolean.valueOf(z);
    }

    /* access modifiers changed from: private */
    public void clearBooleanValue() {
        if (this.valueTypeCase_ == 1) {
            this.valueTypeCase_ = 0;
            this.valueType_ = null;
        }
    }

    public long getIntegerValue() {
        if (this.valueTypeCase_ == 2) {
            return ((Long) this.valueType_).longValue();
        }
        return 0;
    }

    /* access modifiers changed from: private */
    public void setIntegerValue(long j) {
        this.valueTypeCase_ = 2;
        this.valueType_ = Long.valueOf(j);
    }

    /* access modifiers changed from: private */
    public void clearIntegerValue() {
        if (this.valueTypeCase_ == 2) {
            this.valueTypeCase_ = 0;
            this.valueType_ = null;
        }
    }

    public double getDoubleValue() {
        if (this.valueTypeCase_ == 3) {
            return ((Double) this.valueType_).doubleValue();
        }
        return 0.0d;
    }

    /* access modifiers changed from: private */
    public void setDoubleValue(double d) {
        this.valueTypeCase_ = 3;
        this.valueType_ = Double.valueOf(d);
    }

    /* access modifiers changed from: private */
    public void clearDoubleValue() {
        if (this.valueTypeCase_ == 3) {
            this.valueTypeCase_ = 0;
            this.valueType_ = null;
        }
    }

    public boolean hasTimestampValue() {
        return this.valueTypeCase_ == 10;
    }

    public Timestamp getTimestampValue() {
        if (this.valueTypeCase_ == 10) {
            return (Timestamp) this.valueType_;
        }
        return Timestamp.getDefaultInstance();
    }

    /* access modifiers changed from: private */
    public void setTimestampValue(Timestamp timestamp) {
        timestamp.getClass();
        this.valueType_ = timestamp;
        this.valueTypeCase_ = 10;
    }

    /* access modifiers changed from: private */
    public void mergeTimestampValue(Timestamp timestamp) {
        timestamp.getClass();
        if (this.valueTypeCase_ != 10 || this.valueType_ == Timestamp.getDefaultInstance()) {
            this.valueType_ = timestamp;
        } else {
            this.valueType_ = ((Timestamp.Builder) Timestamp.newBuilder((Timestamp) this.valueType_).mergeFrom(timestamp)).buildPartial();
        }
        this.valueTypeCase_ = 10;
    }

    /* access modifiers changed from: private */
    public void clearTimestampValue() {
        if (this.valueTypeCase_ == 10) {
            this.valueTypeCase_ = 0;
            this.valueType_ = null;
        }
    }

    public String getStringValue() {
        return this.valueTypeCase_ == 17 ? (String) this.valueType_ : "";
    }

    public ByteString getStringValueBytes() {
        return ByteString.copyFromUtf8(this.valueTypeCase_ == 17 ? (String) this.valueType_ : "");
    }

    /* access modifiers changed from: private */
    public void setStringValue(String str) {
        str.getClass();
        this.valueTypeCase_ = 17;
        this.valueType_ = str;
    }

    /* access modifiers changed from: private */
    public void clearStringValue() {
        if (this.valueTypeCase_ == 17) {
            this.valueTypeCase_ = 0;
            this.valueType_ = null;
        }
    }

    /* access modifiers changed from: private */
    public void setStringValueBytes(ByteString byteString) {
        checkByteStringIsUtf8(byteString);
        this.valueType_ = byteString.toStringUtf8();
        this.valueTypeCase_ = 17;
    }

    public ByteString getBytesValue() {
        if (this.valueTypeCase_ == 18) {
            return (ByteString) this.valueType_;
        }
        return ByteString.EMPTY;
    }

    /* access modifiers changed from: private */
    public void setBytesValue(ByteString byteString) {
        byteString.getClass();
        this.valueTypeCase_ = 18;
        this.valueType_ = byteString;
    }

    /* access modifiers changed from: private */
    public void clearBytesValue() {
        if (this.valueTypeCase_ == 18) {
            this.valueTypeCase_ = 0;
            this.valueType_ = null;
        }
    }

    public String getReferenceValue() {
        return this.valueTypeCase_ == 5 ? (String) this.valueType_ : "";
    }

    public ByteString getReferenceValueBytes() {
        return ByteString.copyFromUtf8(this.valueTypeCase_ == 5 ? (String) this.valueType_ : "");
    }

    /* access modifiers changed from: private */
    public void setReferenceValue(String str) {
        str.getClass();
        this.valueTypeCase_ = 5;
        this.valueType_ = str;
    }

    /* access modifiers changed from: private */
    public void clearReferenceValue() {
        if (this.valueTypeCase_ == 5) {
            this.valueTypeCase_ = 0;
            this.valueType_ = null;
        }
    }

    /* access modifiers changed from: private */
    public void setReferenceValueBytes(ByteString byteString) {
        checkByteStringIsUtf8(byteString);
        this.valueType_ = byteString.toStringUtf8();
        this.valueTypeCase_ = 5;
    }

    public boolean hasGeoPointValue() {
        return this.valueTypeCase_ == 8;
    }

    public LatLng getGeoPointValue() {
        if (this.valueTypeCase_ == 8) {
            return (LatLng) this.valueType_;
        }
        return LatLng.getDefaultInstance();
    }

    /* access modifiers changed from: private */
    public void setGeoPointValue(LatLng latLng) {
        latLng.getClass();
        this.valueType_ = latLng;
        this.valueTypeCase_ = 8;
    }

    /* access modifiers changed from: private */
    public void mergeGeoPointValue(LatLng latLng) {
        latLng.getClass();
        if (this.valueTypeCase_ != 8 || this.valueType_ == LatLng.getDefaultInstance()) {
            this.valueType_ = latLng;
        } else {
            this.valueType_ = ((LatLng.Builder) LatLng.newBuilder((LatLng) this.valueType_).mergeFrom(latLng)).buildPartial();
        }
        this.valueTypeCase_ = 8;
    }

    /* access modifiers changed from: private */
    public void clearGeoPointValue() {
        if (this.valueTypeCase_ == 8) {
            this.valueTypeCase_ = 0;
            this.valueType_ = null;
        }
    }

    public boolean hasArrayValue() {
        return this.valueTypeCase_ == 9;
    }

    public ArrayValue getArrayValue() {
        if (this.valueTypeCase_ == 9) {
            return (ArrayValue) this.valueType_;
        }
        return ArrayValue.getDefaultInstance();
    }

    /* access modifiers changed from: private */
    public void setArrayValue(ArrayValue arrayValue) {
        arrayValue.getClass();
        this.valueType_ = arrayValue;
        this.valueTypeCase_ = 9;
    }

    /* access modifiers changed from: private */
    public void mergeArrayValue(ArrayValue arrayValue) {
        arrayValue.getClass();
        if (this.valueTypeCase_ != 9 || this.valueType_ == ArrayValue.getDefaultInstance()) {
            this.valueType_ = arrayValue;
        } else {
            this.valueType_ = ((ArrayValue.Builder) ArrayValue.newBuilder((ArrayValue) this.valueType_).mergeFrom(arrayValue)).buildPartial();
        }
        this.valueTypeCase_ = 9;
    }

    /* access modifiers changed from: private */
    public void clearArrayValue() {
        if (this.valueTypeCase_ == 9) {
            this.valueTypeCase_ = 0;
            this.valueType_ = null;
        }
    }

    public boolean hasMapValue() {
        return this.valueTypeCase_ == 6;
    }

    public MapValue getMapValue() {
        if (this.valueTypeCase_ == 6) {
            return (MapValue) this.valueType_;
        }
        return MapValue.getDefaultInstance();
    }

    /* access modifiers changed from: private */
    public void setMapValue(MapValue mapValue) {
        mapValue.getClass();
        this.valueType_ = mapValue;
        this.valueTypeCase_ = 6;
    }

    /* access modifiers changed from: private */
    public void mergeMapValue(MapValue mapValue) {
        mapValue.getClass();
        if (this.valueTypeCase_ != 6 || this.valueType_ == MapValue.getDefaultInstance()) {
            this.valueType_ = mapValue;
        } else {
            this.valueType_ = ((MapValue.Builder) MapValue.newBuilder((MapValue) this.valueType_).mergeFrom(mapValue)).buildPartial();
        }
        this.valueTypeCase_ = 6;
    }

    /* access modifiers changed from: private */
    public void clearMapValue() {
        if (this.valueTypeCase_ == 6) {
            this.valueTypeCase_ = 0;
            this.valueType_ = null;
        }
    }

    public static Value parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return (Value) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
    }

    public static Value parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (Value) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
    }

    public static Value parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (Value) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    public static Value parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (Value) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
    }

    public static Value parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (Value) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static Value parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (Value) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
    }

    public static Value parseFrom(InputStream inputStream) throws IOException {
        return (Value) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static Value parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (Value) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static Value parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (Value) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static Value parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (Value) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static Value parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (Value) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
    }

    public static Value parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (Value) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.createBuilder();
    }

    public static Builder newBuilder(Value value) {
        return (Builder) DEFAULT_INSTANCE.createBuilder(value);
    }

    /* renamed from: com.google.firestore.v1.Value$Builder */
    public static final class Builder extends GeneratedMessageLite.Builder<Value, Builder> implements ValueOrBuilder {
        /* synthetic */ Builder(C19991 r1) {
            this();
        }

        private Builder() {
            super(Value.DEFAULT_INSTANCE);
        }

        public ValueTypeCase getValueTypeCase() {
            return ((Value) this.instance).getValueTypeCase();
        }

        public Builder clearValueType() {
            copyOnWrite();
            ((Value) this.instance).clearValueType();
            return this;
        }

        public int getNullValueValue() {
            return ((Value) this.instance).getNullValueValue();
        }

        public Builder setNullValueValue(int i) {
            copyOnWrite();
            ((Value) this.instance).setNullValueValue(i);
            return this;
        }

        public NullValue getNullValue() {
            return ((Value) this.instance).getNullValue();
        }

        public Builder setNullValue(NullValue nullValue) {
            copyOnWrite();
            ((Value) this.instance).setNullValue(nullValue);
            return this;
        }

        public Builder clearNullValue() {
            copyOnWrite();
            ((Value) this.instance).clearNullValue();
            return this;
        }

        public boolean getBooleanValue() {
            return ((Value) this.instance).getBooleanValue();
        }

        public Builder setBooleanValue(boolean z) {
            copyOnWrite();
            ((Value) this.instance).setBooleanValue(z);
            return this;
        }

        public Builder clearBooleanValue() {
            copyOnWrite();
            ((Value) this.instance).clearBooleanValue();
            return this;
        }

        public long getIntegerValue() {
            return ((Value) this.instance).getIntegerValue();
        }

        public Builder setIntegerValue(long j) {
            copyOnWrite();
            ((Value) this.instance).setIntegerValue(j);
            return this;
        }

        public Builder clearIntegerValue() {
            copyOnWrite();
            ((Value) this.instance).clearIntegerValue();
            return this;
        }

        public double getDoubleValue() {
            return ((Value) this.instance).getDoubleValue();
        }

        public Builder setDoubleValue(double d) {
            copyOnWrite();
            ((Value) this.instance).setDoubleValue(d);
            return this;
        }

        public Builder clearDoubleValue() {
            copyOnWrite();
            ((Value) this.instance).clearDoubleValue();
            return this;
        }

        public boolean hasTimestampValue() {
            return ((Value) this.instance).hasTimestampValue();
        }

        public Timestamp getTimestampValue() {
            return ((Value) this.instance).getTimestampValue();
        }

        public Builder setTimestampValue(Timestamp timestamp) {
            copyOnWrite();
            ((Value) this.instance).setTimestampValue(timestamp);
            return this;
        }

        public Builder setTimestampValue(Timestamp.Builder builder) {
            copyOnWrite();
            ((Value) this.instance).setTimestampValue((Timestamp) builder.build());
            return this;
        }

        public Builder mergeTimestampValue(Timestamp timestamp) {
            copyOnWrite();
            ((Value) this.instance).mergeTimestampValue(timestamp);
            return this;
        }

        public Builder clearTimestampValue() {
            copyOnWrite();
            ((Value) this.instance).clearTimestampValue();
            return this;
        }

        public String getStringValue() {
            return ((Value) this.instance).getStringValue();
        }

        public ByteString getStringValueBytes() {
            return ((Value) this.instance).getStringValueBytes();
        }

        public Builder setStringValue(String str) {
            copyOnWrite();
            ((Value) this.instance).setStringValue(str);
            return this;
        }

        public Builder clearStringValue() {
            copyOnWrite();
            ((Value) this.instance).clearStringValue();
            return this;
        }

        public Builder setStringValueBytes(ByteString byteString) {
            copyOnWrite();
            ((Value) this.instance).setStringValueBytes(byteString);
            return this;
        }

        public ByteString getBytesValue() {
            return ((Value) this.instance).getBytesValue();
        }

        public Builder setBytesValue(ByteString byteString) {
            copyOnWrite();
            ((Value) this.instance).setBytesValue(byteString);
            return this;
        }

        public Builder clearBytesValue() {
            copyOnWrite();
            ((Value) this.instance).clearBytesValue();
            return this;
        }

        public String getReferenceValue() {
            return ((Value) this.instance).getReferenceValue();
        }

        public ByteString getReferenceValueBytes() {
            return ((Value) this.instance).getReferenceValueBytes();
        }

        public Builder setReferenceValue(String str) {
            copyOnWrite();
            ((Value) this.instance).setReferenceValue(str);
            return this;
        }

        public Builder clearReferenceValue() {
            copyOnWrite();
            ((Value) this.instance).clearReferenceValue();
            return this;
        }

        public Builder setReferenceValueBytes(ByteString byteString) {
            copyOnWrite();
            ((Value) this.instance).setReferenceValueBytes(byteString);
            return this;
        }

        public boolean hasGeoPointValue() {
            return ((Value) this.instance).hasGeoPointValue();
        }

        public LatLng getGeoPointValue() {
            return ((Value) this.instance).getGeoPointValue();
        }

        public Builder setGeoPointValue(LatLng latLng) {
            copyOnWrite();
            ((Value) this.instance).setGeoPointValue(latLng);
            return this;
        }

        public Builder setGeoPointValue(LatLng.Builder builder) {
            copyOnWrite();
            ((Value) this.instance).setGeoPointValue((LatLng) builder.build());
            return this;
        }

        public Builder mergeGeoPointValue(LatLng latLng) {
            copyOnWrite();
            ((Value) this.instance).mergeGeoPointValue(latLng);
            return this;
        }

        public Builder clearGeoPointValue() {
            copyOnWrite();
            ((Value) this.instance).clearGeoPointValue();
            return this;
        }

        public boolean hasArrayValue() {
            return ((Value) this.instance).hasArrayValue();
        }

        public ArrayValue getArrayValue() {
            return ((Value) this.instance).getArrayValue();
        }

        public Builder setArrayValue(ArrayValue arrayValue) {
            copyOnWrite();
            ((Value) this.instance).setArrayValue(arrayValue);
            return this;
        }

        public Builder setArrayValue(ArrayValue.Builder builder) {
            copyOnWrite();
            ((Value) this.instance).setArrayValue((ArrayValue) builder.build());
            return this;
        }

        public Builder mergeArrayValue(ArrayValue arrayValue) {
            copyOnWrite();
            ((Value) this.instance).mergeArrayValue(arrayValue);
            return this;
        }

        public Builder clearArrayValue() {
            copyOnWrite();
            ((Value) this.instance).clearArrayValue();
            return this;
        }

        public boolean hasMapValue() {
            return ((Value) this.instance).hasMapValue();
        }

        public MapValue getMapValue() {
            return ((Value) this.instance).getMapValue();
        }

        public Builder setMapValue(MapValue mapValue) {
            copyOnWrite();
            ((Value) this.instance).setMapValue(mapValue);
            return this;
        }

        public Builder setMapValue(MapValue.Builder builder) {
            copyOnWrite();
            ((Value) this.instance).setMapValue((MapValue) builder.build());
            return this;
        }

        public Builder mergeMapValue(MapValue mapValue) {
            copyOnWrite();
            ((Value) this.instance).mergeMapValue(mapValue);
            return this;
        }

        public Builder clearMapValue() {
            copyOnWrite();
            ((Value) this.instance).clearMapValue();
            return this;
        }
    }

    /* renamed from: com.google.firestore.v1.Value$1 */
    static /* synthetic */ class C19991 {

        /* renamed from: $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke */
        static final /* synthetic */ int[] f400xa1df5c61;

        /* JADX WARNING: Can't wrap try/catch for region: R(14:0|1|2|3|4|5|6|7|8|9|10|11|12|(3:13|14|16)) */
        /* JADX WARNING: Can't wrap try/catch for region: R(16:0|1|2|3|4|5|6|7|8|9|10|11|12|13|14|16) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x003e */
        /* JADX WARNING: Missing exception handler attribute for start block: B:13:0x0049 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0033 */
        static {
            /*
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke[] r0 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                f400xa1df5c61 = r0
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r1 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.NEW_MUTABLE_INSTANCE     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = f400xa1df5c61     // Catch:{ NoSuchFieldError -> 0x001d }
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r1 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.NEW_BUILDER     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = f400xa1df5c61     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r1 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.BUILD_MESSAGE_INFO     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = f400xa1df5c61     // Catch:{ NoSuchFieldError -> 0x0033 }
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r1 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.GET_DEFAULT_INSTANCE     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                int[] r0 = f400xa1df5c61     // Catch:{ NoSuchFieldError -> 0x003e }
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r1 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.GET_PARSER     // Catch:{ NoSuchFieldError -> 0x003e }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x003e }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x003e }
            L_0x003e:
                int[] r0 = f400xa1df5c61     // Catch:{ NoSuchFieldError -> 0x0049 }
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r1 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.GET_MEMOIZED_IS_INITIALIZED     // Catch:{ NoSuchFieldError -> 0x0049 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0049 }
                r2 = 6
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0049 }
            L_0x0049:
                int[] r0 = f400xa1df5c61     // Catch:{ NoSuchFieldError -> 0x0054 }
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r1 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.SET_MEMOIZED_IS_INITIALIZED     // Catch:{ NoSuchFieldError -> 0x0054 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0054 }
                r2 = 7
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0054 }
            L_0x0054:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.firestore.p009v1.Value.C19991.<clinit>():void");
        }
    }

    /* access modifiers changed from: protected */
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        switch (C19991.f400xa1df5c61[methodToInvoke.ordinal()]) {
            case 1:
                return new Value();
            case 2:
                return new Builder((C19991) null);
            case 3:
                return newMessageInfo(DEFAULT_INSTANCE, "\u0000\u000b\u0001\u0000\u0001\u0012\u000b\u0000\u0000\u0000\u0001:\u0000\u00025\u0000\u00033\u0000\u0005Ȼ\u0000\u0006<\u0000\b<\u0000\t<\u0000\n<\u0000\u000b?\u0000\u0011Ȼ\u0000\u0012=\u0000", new Object[]{"valueType_", "valueTypeCase_", MapValue.class, LatLng.class, ArrayValue.class, Timestamp.class});
            case 4:
                return DEFAULT_INSTANCE;
            case 5:
                Parser<Value> parser = PARSER;
                if (parser == null) {
                    synchronized (Value.class) {
                        parser = PARSER;
                        if (parser == null) {
                            parser = new GeneratedMessageLite.DefaultInstanceBasedParser<>(DEFAULT_INSTANCE);
                            PARSER = parser;
                        }
                    }
                }
                return parser;
            case 6:
                return (byte) 1;
            case 7:
                return null;
            default:
                throw new UnsupportedOperationException();
        }
    }

    static {
        Value value = new Value();
        DEFAULT_INSTANCE = value;
        GeneratedMessageLite.registerDefaultInstance(Value.class, value);
    }

    public static Value getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<Value> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}

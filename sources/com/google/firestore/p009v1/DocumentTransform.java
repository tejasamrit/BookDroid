package com.google.firestore.p009v1;

import com.google.firestore.p009v1.ArrayValue;
import com.google.firestore.p009v1.Value;
import com.google.protobuf.AbstractMessageLite;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Internal;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Parser;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.Collections;
import java.util.List;

/* renamed from: com.google.firestore.v1.DocumentTransform */
public final class DocumentTransform extends GeneratedMessageLite<DocumentTransform, Builder> implements DocumentTransformOrBuilder {
    /* access modifiers changed from: private */
    public static final DocumentTransform DEFAULT_INSTANCE;
    public static final int DOCUMENT_FIELD_NUMBER = 1;
    public static final int FIELD_TRANSFORMS_FIELD_NUMBER = 2;
    private static volatile Parser<DocumentTransform> PARSER;
    private String document_ = "";
    private Internal.ProtobufList<FieldTransform> fieldTransforms_ = emptyProtobufList();

    /* renamed from: com.google.firestore.v1.DocumentTransform$FieldTransformOrBuilder */
    public interface FieldTransformOrBuilder extends MessageLiteOrBuilder {
        ArrayValue getAppendMissingElements();

        String getFieldPath();

        ByteString getFieldPathBytes();

        Value getIncrement();

        Value getMaximum();

        Value getMinimum();

        ArrayValue getRemoveAllFromArray();

        FieldTransform.ServerValue getSetToServerValue();

        int getSetToServerValueValue();

        FieldTransform.TransformTypeCase getTransformTypeCase();

        boolean hasAppendMissingElements();

        boolean hasIncrement();

        boolean hasMaximum();

        boolean hasMinimum();

        boolean hasRemoveAllFromArray();
    }

    private DocumentTransform() {
    }

    /* renamed from: com.google.firestore.v1.DocumentTransform$FieldTransform */
    public static final class FieldTransform extends GeneratedMessageLite<FieldTransform, Builder> implements FieldTransformOrBuilder {
        public static final int APPEND_MISSING_ELEMENTS_FIELD_NUMBER = 6;
        /* access modifiers changed from: private */
        public static final FieldTransform DEFAULT_INSTANCE;
        public static final int FIELD_PATH_FIELD_NUMBER = 1;
        public static final int INCREMENT_FIELD_NUMBER = 3;
        public static final int MAXIMUM_FIELD_NUMBER = 4;
        public static final int MINIMUM_FIELD_NUMBER = 5;
        private static volatile Parser<FieldTransform> PARSER = null;
        public static final int REMOVE_ALL_FROM_ARRAY_FIELD_NUMBER = 7;
        public static final int SET_TO_SERVER_VALUE_FIELD_NUMBER = 2;
        private String fieldPath_ = "";
        private int transformTypeCase_ = 0;
        private Object transformType_;

        private FieldTransform() {
        }

        /* renamed from: com.google.firestore.v1.DocumentTransform$FieldTransform$ServerValue */
        public enum ServerValue implements Internal.EnumLite {
            SERVER_VALUE_UNSPECIFIED(0),
            REQUEST_TIME(1),
            UNRECOGNIZED(-1);
            
            public static final int REQUEST_TIME_VALUE = 1;
            public static final int SERVER_VALUE_UNSPECIFIED_VALUE = 0;
            private static final Internal.EnumLiteMap<ServerValue> internalValueMap = null;
            private final int value;

            static {
                internalValueMap = new Internal.EnumLiteMap<ServerValue>() {
                    public ServerValue findValueByNumber(int i) {
                        return ServerValue.forNumber(i);
                    }
                };
            }

            public final int getNumber() {
                if (this != UNRECOGNIZED) {
                    return this.value;
                }
                throw new IllegalArgumentException("Can't get the number of an unknown enum value.");
            }

            @Deprecated
            public static ServerValue valueOf(int i) {
                return forNumber(i);
            }

            public static ServerValue forNumber(int i) {
                if (i == 0) {
                    return SERVER_VALUE_UNSPECIFIED;
                }
                if (i != 1) {
                    return null;
                }
                return REQUEST_TIME;
            }

            public static Internal.EnumLiteMap<ServerValue> internalGetValueMap() {
                return internalValueMap;
            }

            public static Internal.EnumVerifier internalGetVerifier() {
                return ServerValueVerifier.INSTANCE;
            }

            /* renamed from: com.google.firestore.v1.DocumentTransform$FieldTransform$ServerValue$ServerValueVerifier */
            private static final class ServerValueVerifier implements Internal.EnumVerifier {
                static final Internal.EnumVerifier INSTANCE = null;

                private ServerValueVerifier() {
                }

                static {
                    INSTANCE = new ServerValueVerifier();
                }

                public boolean isInRange(int i) {
                    return ServerValue.forNumber(i) != null;
                }
            }

            private ServerValue(int i) {
                this.value = i;
            }
        }

        /* renamed from: com.google.firestore.v1.DocumentTransform$FieldTransform$TransformTypeCase */
        public enum TransformTypeCase {
            SET_TO_SERVER_VALUE(2),
            INCREMENT(3),
            MAXIMUM(4),
            MINIMUM(5),
            APPEND_MISSING_ELEMENTS(6),
            REMOVE_ALL_FROM_ARRAY(7),
            TRANSFORMTYPE_NOT_SET(0);
            
            private final int value;

            private TransformTypeCase(int i) {
                this.value = i;
            }

            @Deprecated
            public static TransformTypeCase valueOf(int i) {
                return forNumber(i);
            }

            public static TransformTypeCase forNumber(int i) {
                if (i == 0) {
                    return TRANSFORMTYPE_NOT_SET;
                }
                switch (i) {
                    case 2:
                        return SET_TO_SERVER_VALUE;
                    case 3:
                        return INCREMENT;
                    case 4:
                        return MAXIMUM;
                    case 5:
                        return MINIMUM;
                    case 6:
                        return APPEND_MISSING_ELEMENTS;
                    case 7:
                        return REMOVE_ALL_FROM_ARRAY;
                    default:
                        return null;
                }
            }

            public int getNumber() {
                return this.value;
            }
        }

        public TransformTypeCase getTransformTypeCase() {
            return TransformTypeCase.forNumber(this.transformTypeCase_);
        }

        /* access modifiers changed from: private */
        public void clearTransformType() {
            this.transformTypeCase_ = 0;
            this.transformType_ = null;
        }

        public String getFieldPath() {
            return this.fieldPath_;
        }

        public ByteString getFieldPathBytes() {
            return ByteString.copyFromUtf8(this.fieldPath_);
        }

        /* access modifiers changed from: private */
        public void setFieldPath(String str) {
            str.getClass();
            this.fieldPath_ = str;
        }

        /* access modifiers changed from: private */
        public void clearFieldPath() {
            this.fieldPath_ = getDefaultInstance().getFieldPath();
        }

        /* access modifiers changed from: private */
        public void setFieldPathBytes(ByteString byteString) {
            checkByteStringIsUtf8(byteString);
            this.fieldPath_ = byteString.toStringUtf8();
        }

        public int getSetToServerValueValue() {
            if (this.transformTypeCase_ == 2) {
                return ((Integer) this.transformType_).intValue();
            }
            return 0;
        }

        public ServerValue getSetToServerValue() {
            if (this.transformTypeCase_ != 2) {
                return ServerValue.SERVER_VALUE_UNSPECIFIED;
            }
            ServerValue forNumber = ServerValue.forNumber(((Integer) this.transformType_).intValue());
            return forNumber == null ? ServerValue.UNRECOGNIZED : forNumber;
        }

        /* access modifiers changed from: private */
        public void setSetToServerValueValue(int i) {
            this.transformTypeCase_ = 2;
            this.transformType_ = Integer.valueOf(i);
        }

        /* access modifiers changed from: private */
        public void setSetToServerValue(ServerValue serverValue) {
            this.transformType_ = Integer.valueOf(serverValue.getNumber());
            this.transformTypeCase_ = 2;
        }

        /* access modifiers changed from: private */
        public void clearSetToServerValue() {
            if (this.transformTypeCase_ == 2) {
                this.transformTypeCase_ = 0;
                this.transformType_ = null;
            }
        }

        public boolean hasIncrement() {
            return this.transformTypeCase_ == 3;
        }

        public Value getIncrement() {
            if (this.transformTypeCase_ == 3) {
                return (Value) this.transformType_;
            }
            return Value.getDefaultInstance();
        }

        /* access modifiers changed from: private */
        public void setIncrement(Value value) {
            value.getClass();
            this.transformType_ = value;
            this.transformTypeCase_ = 3;
        }

        /* access modifiers changed from: private */
        public void mergeIncrement(Value value) {
            value.getClass();
            if (this.transformTypeCase_ != 3 || this.transformType_ == Value.getDefaultInstance()) {
                this.transformType_ = value;
            } else {
                this.transformType_ = ((Value.Builder) Value.newBuilder((Value) this.transformType_).mergeFrom(value)).buildPartial();
            }
            this.transformTypeCase_ = 3;
        }

        /* access modifiers changed from: private */
        public void clearIncrement() {
            if (this.transformTypeCase_ == 3) {
                this.transformTypeCase_ = 0;
                this.transformType_ = null;
            }
        }

        public boolean hasMaximum() {
            return this.transformTypeCase_ == 4;
        }

        public Value getMaximum() {
            if (this.transformTypeCase_ == 4) {
                return (Value) this.transformType_;
            }
            return Value.getDefaultInstance();
        }

        /* access modifiers changed from: private */
        public void setMaximum(Value value) {
            value.getClass();
            this.transformType_ = value;
            this.transformTypeCase_ = 4;
        }

        /* access modifiers changed from: private */
        public void mergeMaximum(Value value) {
            value.getClass();
            if (this.transformTypeCase_ != 4 || this.transformType_ == Value.getDefaultInstance()) {
                this.transformType_ = value;
            } else {
                this.transformType_ = ((Value.Builder) Value.newBuilder((Value) this.transformType_).mergeFrom(value)).buildPartial();
            }
            this.transformTypeCase_ = 4;
        }

        /* access modifiers changed from: private */
        public void clearMaximum() {
            if (this.transformTypeCase_ == 4) {
                this.transformTypeCase_ = 0;
                this.transformType_ = null;
            }
        }

        public boolean hasMinimum() {
            return this.transformTypeCase_ == 5;
        }

        public Value getMinimum() {
            if (this.transformTypeCase_ == 5) {
                return (Value) this.transformType_;
            }
            return Value.getDefaultInstance();
        }

        /* access modifiers changed from: private */
        public void setMinimum(Value value) {
            value.getClass();
            this.transformType_ = value;
            this.transformTypeCase_ = 5;
        }

        /* access modifiers changed from: private */
        public void mergeMinimum(Value value) {
            value.getClass();
            if (this.transformTypeCase_ != 5 || this.transformType_ == Value.getDefaultInstance()) {
                this.transformType_ = value;
            } else {
                this.transformType_ = ((Value.Builder) Value.newBuilder((Value) this.transformType_).mergeFrom(value)).buildPartial();
            }
            this.transformTypeCase_ = 5;
        }

        /* access modifiers changed from: private */
        public void clearMinimum() {
            if (this.transformTypeCase_ == 5) {
                this.transformTypeCase_ = 0;
                this.transformType_ = null;
            }
        }

        public boolean hasAppendMissingElements() {
            return this.transformTypeCase_ == 6;
        }

        public ArrayValue getAppendMissingElements() {
            if (this.transformTypeCase_ == 6) {
                return (ArrayValue) this.transformType_;
            }
            return ArrayValue.getDefaultInstance();
        }

        /* access modifiers changed from: private */
        public void setAppendMissingElements(ArrayValue arrayValue) {
            arrayValue.getClass();
            this.transformType_ = arrayValue;
            this.transformTypeCase_ = 6;
        }

        /* access modifiers changed from: private */
        public void mergeAppendMissingElements(ArrayValue arrayValue) {
            arrayValue.getClass();
            if (this.transformTypeCase_ != 6 || this.transformType_ == ArrayValue.getDefaultInstance()) {
                this.transformType_ = arrayValue;
            } else {
                this.transformType_ = ((ArrayValue.Builder) ArrayValue.newBuilder((ArrayValue) this.transformType_).mergeFrom(arrayValue)).buildPartial();
            }
            this.transformTypeCase_ = 6;
        }

        /* access modifiers changed from: private */
        public void clearAppendMissingElements() {
            if (this.transformTypeCase_ == 6) {
                this.transformTypeCase_ = 0;
                this.transformType_ = null;
            }
        }

        public boolean hasRemoveAllFromArray() {
            return this.transformTypeCase_ == 7;
        }

        public ArrayValue getRemoveAllFromArray() {
            if (this.transformTypeCase_ == 7) {
                return (ArrayValue) this.transformType_;
            }
            return ArrayValue.getDefaultInstance();
        }

        /* access modifiers changed from: private */
        public void setRemoveAllFromArray(ArrayValue arrayValue) {
            arrayValue.getClass();
            this.transformType_ = arrayValue;
            this.transformTypeCase_ = 7;
        }

        /* access modifiers changed from: private */
        public void mergeRemoveAllFromArray(ArrayValue arrayValue) {
            arrayValue.getClass();
            if (this.transformTypeCase_ != 7 || this.transformType_ == ArrayValue.getDefaultInstance()) {
                this.transformType_ = arrayValue;
            } else {
                this.transformType_ = ((ArrayValue.Builder) ArrayValue.newBuilder((ArrayValue) this.transformType_).mergeFrom(arrayValue)).buildPartial();
            }
            this.transformTypeCase_ = 7;
        }

        /* access modifiers changed from: private */
        public void clearRemoveAllFromArray() {
            if (this.transformTypeCase_ == 7) {
                this.transformTypeCase_ = 0;
                this.transformType_ = null;
            }
        }

        public static FieldTransform parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (FieldTransform) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static FieldTransform parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (FieldTransform) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
        }

        public static FieldTransform parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (FieldTransform) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        public static FieldTransform parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (FieldTransform) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
        }

        public static FieldTransform parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (FieldTransform) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static FieldTransform parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (FieldTransform) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
        }

        public static FieldTransform parseFrom(InputStream inputStream) throws IOException {
            return (FieldTransform) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static FieldTransform parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (FieldTransform) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static FieldTransform parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (FieldTransform) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static FieldTransform parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (FieldTransform) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static FieldTransform parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (FieldTransform) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
        }

        public static FieldTransform parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (FieldTransform) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.createBuilder();
        }

        public static Builder newBuilder(FieldTransform fieldTransform) {
            return (Builder) DEFAULT_INSTANCE.createBuilder(fieldTransform);
        }

        /* renamed from: com.google.firestore.v1.DocumentTransform$FieldTransform$Builder */
        public static final class Builder extends GeneratedMessageLite.Builder<FieldTransform, Builder> implements FieldTransformOrBuilder {
            /* synthetic */ Builder(C19711 r1) {
                this();
            }

            private Builder() {
                super(FieldTransform.DEFAULT_INSTANCE);
            }

            public TransformTypeCase getTransformTypeCase() {
                return ((FieldTransform) this.instance).getTransformTypeCase();
            }

            public Builder clearTransformType() {
                copyOnWrite();
                ((FieldTransform) this.instance).clearTransformType();
                return this;
            }

            public String getFieldPath() {
                return ((FieldTransform) this.instance).getFieldPath();
            }

            public ByteString getFieldPathBytes() {
                return ((FieldTransform) this.instance).getFieldPathBytes();
            }

            public Builder setFieldPath(String str) {
                copyOnWrite();
                ((FieldTransform) this.instance).setFieldPath(str);
                return this;
            }

            public Builder clearFieldPath() {
                copyOnWrite();
                ((FieldTransform) this.instance).clearFieldPath();
                return this;
            }

            public Builder setFieldPathBytes(ByteString byteString) {
                copyOnWrite();
                ((FieldTransform) this.instance).setFieldPathBytes(byteString);
                return this;
            }

            public int getSetToServerValueValue() {
                return ((FieldTransform) this.instance).getSetToServerValueValue();
            }

            public Builder setSetToServerValueValue(int i) {
                copyOnWrite();
                ((FieldTransform) this.instance).setSetToServerValueValue(i);
                return this;
            }

            public ServerValue getSetToServerValue() {
                return ((FieldTransform) this.instance).getSetToServerValue();
            }

            public Builder setSetToServerValue(ServerValue serverValue) {
                copyOnWrite();
                ((FieldTransform) this.instance).setSetToServerValue(serverValue);
                return this;
            }

            public Builder clearSetToServerValue() {
                copyOnWrite();
                ((FieldTransform) this.instance).clearSetToServerValue();
                return this;
            }

            public boolean hasIncrement() {
                return ((FieldTransform) this.instance).hasIncrement();
            }

            public Value getIncrement() {
                return ((FieldTransform) this.instance).getIncrement();
            }

            public Builder setIncrement(Value value) {
                copyOnWrite();
                ((FieldTransform) this.instance).setIncrement(value);
                return this;
            }

            public Builder setIncrement(Value.Builder builder) {
                copyOnWrite();
                ((FieldTransform) this.instance).setIncrement((Value) builder.build());
                return this;
            }

            public Builder mergeIncrement(Value value) {
                copyOnWrite();
                ((FieldTransform) this.instance).mergeIncrement(value);
                return this;
            }

            public Builder clearIncrement() {
                copyOnWrite();
                ((FieldTransform) this.instance).clearIncrement();
                return this;
            }

            public boolean hasMaximum() {
                return ((FieldTransform) this.instance).hasMaximum();
            }

            public Value getMaximum() {
                return ((FieldTransform) this.instance).getMaximum();
            }

            public Builder setMaximum(Value value) {
                copyOnWrite();
                ((FieldTransform) this.instance).setMaximum(value);
                return this;
            }

            public Builder setMaximum(Value.Builder builder) {
                copyOnWrite();
                ((FieldTransform) this.instance).setMaximum((Value) builder.build());
                return this;
            }

            public Builder mergeMaximum(Value value) {
                copyOnWrite();
                ((FieldTransform) this.instance).mergeMaximum(value);
                return this;
            }

            public Builder clearMaximum() {
                copyOnWrite();
                ((FieldTransform) this.instance).clearMaximum();
                return this;
            }

            public boolean hasMinimum() {
                return ((FieldTransform) this.instance).hasMinimum();
            }

            public Value getMinimum() {
                return ((FieldTransform) this.instance).getMinimum();
            }

            public Builder setMinimum(Value value) {
                copyOnWrite();
                ((FieldTransform) this.instance).setMinimum(value);
                return this;
            }

            public Builder setMinimum(Value.Builder builder) {
                copyOnWrite();
                ((FieldTransform) this.instance).setMinimum((Value) builder.build());
                return this;
            }

            public Builder mergeMinimum(Value value) {
                copyOnWrite();
                ((FieldTransform) this.instance).mergeMinimum(value);
                return this;
            }

            public Builder clearMinimum() {
                copyOnWrite();
                ((FieldTransform) this.instance).clearMinimum();
                return this;
            }

            public boolean hasAppendMissingElements() {
                return ((FieldTransform) this.instance).hasAppendMissingElements();
            }

            public ArrayValue getAppendMissingElements() {
                return ((FieldTransform) this.instance).getAppendMissingElements();
            }

            public Builder setAppendMissingElements(ArrayValue arrayValue) {
                copyOnWrite();
                ((FieldTransform) this.instance).setAppendMissingElements(arrayValue);
                return this;
            }

            public Builder setAppendMissingElements(ArrayValue.Builder builder) {
                copyOnWrite();
                ((FieldTransform) this.instance).setAppendMissingElements((ArrayValue) builder.build());
                return this;
            }

            public Builder mergeAppendMissingElements(ArrayValue arrayValue) {
                copyOnWrite();
                ((FieldTransform) this.instance).mergeAppendMissingElements(arrayValue);
                return this;
            }

            public Builder clearAppendMissingElements() {
                copyOnWrite();
                ((FieldTransform) this.instance).clearAppendMissingElements();
                return this;
            }

            public boolean hasRemoveAllFromArray() {
                return ((FieldTransform) this.instance).hasRemoveAllFromArray();
            }

            public ArrayValue getRemoveAllFromArray() {
                return ((FieldTransform) this.instance).getRemoveAllFromArray();
            }

            public Builder setRemoveAllFromArray(ArrayValue arrayValue) {
                copyOnWrite();
                ((FieldTransform) this.instance).setRemoveAllFromArray(arrayValue);
                return this;
            }

            public Builder setRemoveAllFromArray(ArrayValue.Builder builder) {
                copyOnWrite();
                ((FieldTransform) this.instance).setRemoveAllFromArray((ArrayValue) builder.build());
                return this;
            }

            public Builder mergeRemoveAllFromArray(ArrayValue arrayValue) {
                copyOnWrite();
                ((FieldTransform) this.instance).mergeRemoveAllFromArray(arrayValue);
                return this;
            }

            public Builder clearRemoveAllFromArray() {
                copyOnWrite();
                ((FieldTransform) this.instance).clearRemoveAllFromArray();
                return this;
            }
        }

        /* access modifiers changed from: protected */
        public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
            switch (C19711.f380xa1df5c61[methodToInvoke.ordinal()]) {
                case 1:
                    return new FieldTransform();
                case 2:
                    return new Builder((C19711) null);
                case 3:
                    return newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0007\u0001\u0000\u0001\u0007\u0007\u0000\u0000\u0000\u0001Ȉ\u0002?\u0000\u0003<\u0000\u0004<\u0000\u0005<\u0000\u0006<\u0000\u0007<\u0000", new Object[]{"transformType_", "transformTypeCase_", "fieldPath_", Value.class, Value.class, Value.class, ArrayValue.class, ArrayValue.class});
                case 4:
                    return DEFAULT_INSTANCE;
                case 5:
                    Parser<FieldTransform> parser = PARSER;
                    if (parser == null) {
                        synchronized (FieldTransform.class) {
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
            FieldTransform fieldTransform = new FieldTransform();
            DEFAULT_INSTANCE = fieldTransform;
            GeneratedMessageLite.registerDefaultInstance(FieldTransform.class, fieldTransform);
        }

        public static FieldTransform getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<FieldTransform> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    /* renamed from: com.google.firestore.v1.DocumentTransform$1 */
    static /* synthetic */ class C19711 {

        /* renamed from: $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke */
        static final /* synthetic */ int[] f380xa1df5c61;

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
                f380xa1df5c61 = r0
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r1 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.NEW_MUTABLE_INSTANCE     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = f380xa1df5c61     // Catch:{ NoSuchFieldError -> 0x001d }
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r1 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.NEW_BUILDER     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = f380xa1df5c61     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r1 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.BUILD_MESSAGE_INFO     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = f380xa1df5c61     // Catch:{ NoSuchFieldError -> 0x0033 }
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r1 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.GET_DEFAULT_INSTANCE     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                int[] r0 = f380xa1df5c61     // Catch:{ NoSuchFieldError -> 0x003e }
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r1 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.GET_PARSER     // Catch:{ NoSuchFieldError -> 0x003e }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x003e }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x003e }
            L_0x003e:
                int[] r0 = f380xa1df5c61     // Catch:{ NoSuchFieldError -> 0x0049 }
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r1 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.GET_MEMOIZED_IS_INITIALIZED     // Catch:{ NoSuchFieldError -> 0x0049 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0049 }
                r2 = 6
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0049 }
            L_0x0049:
                int[] r0 = f380xa1df5c61     // Catch:{ NoSuchFieldError -> 0x0054 }
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r1 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.SET_MEMOIZED_IS_INITIALIZED     // Catch:{ NoSuchFieldError -> 0x0054 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0054 }
                r2 = 7
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0054 }
            L_0x0054:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.firestore.p009v1.DocumentTransform.C19711.<clinit>():void");
        }
    }

    public String getDocument() {
        return this.document_;
    }

    public ByteString getDocumentBytes() {
        return ByteString.copyFromUtf8(this.document_);
    }

    /* access modifiers changed from: private */
    public void setDocument(String str) {
        str.getClass();
        this.document_ = str;
    }

    /* access modifiers changed from: private */
    public void clearDocument() {
        this.document_ = getDefaultInstance().getDocument();
    }

    /* access modifiers changed from: private */
    public void setDocumentBytes(ByteString byteString) {
        checkByteStringIsUtf8(byteString);
        this.document_ = byteString.toStringUtf8();
    }

    public List<FieldTransform> getFieldTransformsList() {
        return this.fieldTransforms_;
    }

    public List<? extends FieldTransformOrBuilder> getFieldTransformsOrBuilderList() {
        return this.fieldTransforms_;
    }

    public int getFieldTransformsCount() {
        return this.fieldTransforms_.size();
    }

    public FieldTransform getFieldTransforms(int i) {
        return (FieldTransform) this.fieldTransforms_.get(i);
    }

    public FieldTransformOrBuilder getFieldTransformsOrBuilder(int i) {
        return (FieldTransformOrBuilder) this.fieldTransforms_.get(i);
    }

    private void ensureFieldTransformsIsMutable() {
        Internal.ProtobufList<FieldTransform> protobufList = this.fieldTransforms_;
        if (!protobufList.isModifiable()) {
            this.fieldTransforms_ = GeneratedMessageLite.mutableCopy(protobufList);
        }
    }

    /* access modifiers changed from: private */
    public void setFieldTransforms(int i, FieldTransform fieldTransform) {
        fieldTransform.getClass();
        ensureFieldTransformsIsMutable();
        this.fieldTransforms_.set(i, fieldTransform);
    }

    /* access modifiers changed from: private */
    public void addFieldTransforms(FieldTransform fieldTransform) {
        fieldTransform.getClass();
        ensureFieldTransformsIsMutable();
        this.fieldTransforms_.add(fieldTransform);
    }

    /* access modifiers changed from: private */
    public void addFieldTransforms(int i, FieldTransform fieldTransform) {
        fieldTransform.getClass();
        ensureFieldTransformsIsMutable();
        this.fieldTransforms_.add(i, fieldTransform);
    }

    /* access modifiers changed from: private */
    public void addAllFieldTransforms(Iterable<? extends FieldTransform> iterable) {
        ensureFieldTransformsIsMutable();
        AbstractMessageLite.addAll(iterable, this.fieldTransforms_);
    }

    /* access modifiers changed from: private */
    public void clearFieldTransforms() {
        this.fieldTransforms_ = emptyProtobufList();
    }

    /* access modifiers changed from: private */
    public void removeFieldTransforms(int i) {
        ensureFieldTransformsIsMutable();
        this.fieldTransforms_.remove(i);
    }

    public static DocumentTransform parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return (DocumentTransform) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
    }

    public static DocumentTransform parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (DocumentTransform) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
    }

    public static DocumentTransform parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (DocumentTransform) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    public static DocumentTransform parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (DocumentTransform) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
    }

    public static DocumentTransform parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (DocumentTransform) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static DocumentTransform parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (DocumentTransform) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
    }

    public static DocumentTransform parseFrom(InputStream inputStream) throws IOException {
        return (DocumentTransform) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static DocumentTransform parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (DocumentTransform) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static DocumentTransform parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (DocumentTransform) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static DocumentTransform parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (DocumentTransform) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static DocumentTransform parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (DocumentTransform) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
    }

    public static DocumentTransform parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (DocumentTransform) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.createBuilder();
    }

    public static Builder newBuilder(DocumentTransform documentTransform) {
        return (Builder) DEFAULT_INSTANCE.createBuilder(documentTransform);
    }

    /* renamed from: com.google.firestore.v1.DocumentTransform$Builder */
    public static final class Builder extends GeneratedMessageLite.Builder<DocumentTransform, Builder> implements DocumentTransformOrBuilder {
        /* synthetic */ Builder(C19711 r1) {
            this();
        }

        private Builder() {
            super(DocumentTransform.DEFAULT_INSTANCE);
        }

        public String getDocument() {
            return ((DocumentTransform) this.instance).getDocument();
        }

        public ByteString getDocumentBytes() {
            return ((DocumentTransform) this.instance).getDocumentBytes();
        }

        public Builder setDocument(String str) {
            copyOnWrite();
            ((DocumentTransform) this.instance).setDocument(str);
            return this;
        }

        public Builder clearDocument() {
            copyOnWrite();
            ((DocumentTransform) this.instance).clearDocument();
            return this;
        }

        public Builder setDocumentBytes(ByteString byteString) {
            copyOnWrite();
            ((DocumentTransform) this.instance).setDocumentBytes(byteString);
            return this;
        }

        public List<FieldTransform> getFieldTransformsList() {
            return Collections.unmodifiableList(((DocumentTransform) this.instance).getFieldTransformsList());
        }

        public int getFieldTransformsCount() {
            return ((DocumentTransform) this.instance).getFieldTransformsCount();
        }

        public FieldTransform getFieldTransforms(int i) {
            return ((DocumentTransform) this.instance).getFieldTransforms(i);
        }

        public Builder setFieldTransforms(int i, FieldTransform fieldTransform) {
            copyOnWrite();
            ((DocumentTransform) this.instance).setFieldTransforms(i, fieldTransform);
            return this;
        }

        public Builder setFieldTransforms(int i, FieldTransform.Builder builder) {
            copyOnWrite();
            ((DocumentTransform) this.instance).setFieldTransforms(i, (FieldTransform) builder.build());
            return this;
        }

        public Builder addFieldTransforms(FieldTransform fieldTransform) {
            copyOnWrite();
            ((DocumentTransform) this.instance).addFieldTransforms(fieldTransform);
            return this;
        }

        public Builder addFieldTransforms(int i, FieldTransform fieldTransform) {
            copyOnWrite();
            ((DocumentTransform) this.instance).addFieldTransforms(i, fieldTransform);
            return this;
        }

        public Builder addFieldTransforms(FieldTransform.Builder builder) {
            copyOnWrite();
            ((DocumentTransform) this.instance).addFieldTransforms((FieldTransform) builder.build());
            return this;
        }

        public Builder addFieldTransforms(int i, FieldTransform.Builder builder) {
            copyOnWrite();
            ((DocumentTransform) this.instance).addFieldTransforms(i, (FieldTransform) builder.build());
            return this;
        }

        public Builder addAllFieldTransforms(Iterable<? extends FieldTransform> iterable) {
            copyOnWrite();
            ((DocumentTransform) this.instance).addAllFieldTransforms(iterable);
            return this;
        }

        public Builder clearFieldTransforms() {
            copyOnWrite();
            ((DocumentTransform) this.instance).clearFieldTransforms();
            return this;
        }

        public Builder removeFieldTransforms(int i) {
            copyOnWrite();
            ((DocumentTransform) this.instance).removeFieldTransforms(i);
            return this;
        }
    }

    /* access modifiers changed from: protected */
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        switch (C19711.f380xa1df5c61[methodToInvoke.ordinal()]) {
            case 1:
                return new DocumentTransform();
            case 2:
                return new Builder((C19711) null);
            case 3:
                return newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0001\u0000\u0001Ȉ\u0002\u001b", new Object[]{"document_", "fieldTransforms_", FieldTransform.class});
            case 4:
                return DEFAULT_INSTANCE;
            case 5:
                Parser<DocumentTransform> parser = PARSER;
                if (parser == null) {
                    synchronized (DocumentTransform.class) {
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
        DocumentTransform documentTransform = new DocumentTransform();
        DEFAULT_INSTANCE = documentTransform;
        GeneratedMessageLite.registerDefaultInstance(DocumentTransform.class, documentTransform);
    }

    public static DocumentTransform getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<DocumentTransform> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}

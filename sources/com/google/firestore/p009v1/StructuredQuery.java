package com.google.firestore.p009v1;

import com.google.firestore.p009v1.Cursor;
import com.google.firestore.p009v1.Value;
import com.google.protobuf.AbstractMessageLite;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Int32Value;
import com.google.protobuf.Internal;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Parser;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.Collections;
import java.util.List;

/* renamed from: com.google.firestore.v1.StructuredQuery */
public final class StructuredQuery extends GeneratedMessageLite<StructuredQuery, Builder> implements StructuredQueryOrBuilder {
    /* access modifiers changed from: private */
    public static final StructuredQuery DEFAULT_INSTANCE;
    public static final int END_AT_FIELD_NUMBER = 8;
    public static final int FROM_FIELD_NUMBER = 2;
    public static final int LIMIT_FIELD_NUMBER = 5;
    public static final int OFFSET_FIELD_NUMBER = 6;
    public static final int ORDER_BY_FIELD_NUMBER = 4;
    private static volatile Parser<StructuredQuery> PARSER = null;
    public static final int SELECT_FIELD_NUMBER = 1;
    public static final int START_AT_FIELD_NUMBER = 7;
    public static final int WHERE_FIELD_NUMBER = 3;
    private Cursor endAt_;
    private Internal.ProtobufList<CollectionSelector> from_ = emptyProtobufList();
    private Int32Value limit_;
    private int offset_;
    private Internal.ProtobufList<Order> orderBy_ = emptyProtobufList();
    private Projection select_;
    private Cursor startAt_;
    private Filter where_;

    /* renamed from: com.google.firestore.v1.StructuredQuery$CollectionSelectorOrBuilder */
    public interface CollectionSelectorOrBuilder extends MessageLiteOrBuilder {
        boolean getAllDescendants();

        String getCollectionId();

        ByteString getCollectionIdBytes();
    }

    /* renamed from: com.google.firestore.v1.StructuredQuery$CompositeFilterOrBuilder */
    public interface CompositeFilterOrBuilder extends MessageLiteOrBuilder {
        Filter getFilters(int i);

        int getFiltersCount();

        List<Filter> getFiltersList();

        CompositeFilter.Operator getOp();

        int getOpValue();
    }

    /* renamed from: com.google.firestore.v1.StructuredQuery$FieldFilterOrBuilder */
    public interface FieldFilterOrBuilder extends MessageLiteOrBuilder {
        FieldReference getField();

        FieldFilter.Operator getOp();

        int getOpValue();

        Value getValue();

        boolean hasField();

        boolean hasValue();
    }

    /* renamed from: com.google.firestore.v1.StructuredQuery$FieldReferenceOrBuilder */
    public interface FieldReferenceOrBuilder extends MessageLiteOrBuilder {
        String getFieldPath();

        ByteString getFieldPathBytes();
    }

    /* renamed from: com.google.firestore.v1.StructuredQuery$FilterOrBuilder */
    public interface FilterOrBuilder extends MessageLiteOrBuilder {
        CompositeFilter getCompositeFilter();

        FieldFilter getFieldFilter();

        Filter.FilterTypeCase getFilterTypeCase();

        UnaryFilter getUnaryFilter();

        boolean hasCompositeFilter();

        boolean hasFieldFilter();

        boolean hasUnaryFilter();
    }

    /* renamed from: com.google.firestore.v1.StructuredQuery$OrderOrBuilder */
    public interface OrderOrBuilder extends MessageLiteOrBuilder {
        Direction getDirection();

        int getDirectionValue();

        FieldReference getField();

        boolean hasField();
    }

    /* renamed from: com.google.firestore.v1.StructuredQuery$ProjectionOrBuilder */
    public interface ProjectionOrBuilder extends MessageLiteOrBuilder {
        FieldReference getFields(int i);

        int getFieldsCount();

        List<FieldReference> getFieldsList();
    }

    /* renamed from: com.google.firestore.v1.StructuredQuery$UnaryFilterOrBuilder */
    public interface UnaryFilterOrBuilder extends MessageLiteOrBuilder {
        FieldReference getField();

        UnaryFilter.Operator getOp();

        int getOpValue();

        UnaryFilter.OperandTypeCase getOperandTypeCase();

        boolean hasField();
    }

    private StructuredQuery() {
    }

    /* renamed from: com.google.firestore.v1.StructuredQuery$Direction */
    public enum Direction implements Internal.EnumLite {
        DIRECTION_UNSPECIFIED(0),
        ASCENDING(1),
        DESCENDING(2),
        UNRECOGNIZED(-1);
        
        public static final int ASCENDING_VALUE = 1;
        public static final int DESCENDING_VALUE = 2;
        public static final int DIRECTION_UNSPECIFIED_VALUE = 0;
        private static final Internal.EnumLiteMap<Direction> internalValueMap = null;
        private final int value;

        static {
            internalValueMap = new Internal.EnumLiteMap<Direction>() {
                public Direction findValueByNumber(int i) {
                    return Direction.forNumber(i);
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
        public static Direction valueOf(int i) {
            return forNumber(i);
        }

        public static Direction forNumber(int i) {
            if (i == 0) {
                return DIRECTION_UNSPECIFIED;
            }
            if (i == 1) {
                return ASCENDING;
            }
            if (i != 2) {
                return null;
            }
            return DESCENDING;
        }

        public static Internal.EnumLiteMap<Direction> internalGetValueMap() {
            return internalValueMap;
        }

        public static Internal.EnumVerifier internalGetVerifier() {
            return DirectionVerifier.INSTANCE;
        }

        /* renamed from: com.google.firestore.v1.StructuredQuery$Direction$DirectionVerifier */
        private static final class DirectionVerifier implements Internal.EnumVerifier {
            static final Internal.EnumVerifier INSTANCE = null;

            private DirectionVerifier() {
            }

            static {
                INSTANCE = new DirectionVerifier();
            }

            public boolean isInRange(int i) {
                return Direction.forNumber(i) != null;
            }
        }

        private Direction(int i) {
            this.value = i;
        }
    }

    /* renamed from: com.google.firestore.v1.StructuredQuery$CollectionSelector */
    public static final class CollectionSelector extends GeneratedMessageLite<CollectionSelector, Builder> implements CollectionSelectorOrBuilder {
        public static final int ALL_DESCENDANTS_FIELD_NUMBER = 3;
        public static final int COLLECTION_ID_FIELD_NUMBER = 2;
        /* access modifiers changed from: private */
        public static final CollectionSelector DEFAULT_INSTANCE;
        private static volatile Parser<CollectionSelector> PARSER;
        private boolean allDescendants_;
        private String collectionId_ = "";

        private CollectionSelector() {
        }

        public String getCollectionId() {
            return this.collectionId_;
        }

        public ByteString getCollectionIdBytes() {
            return ByteString.copyFromUtf8(this.collectionId_);
        }

        /* access modifiers changed from: private */
        public void setCollectionId(String str) {
            str.getClass();
            this.collectionId_ = str;
        }

        /* access modifiers changed from: private */
        public void clearCollectionId() {
            this.collectionId_ = getDefaultInstance().getCollectionId();
        }

        /* access modifiers changed from: private */
        public void setCollectionIdBytes(ByteString byteString) {
            checkByteStringIsUtf8(byteString);
            this.collectionId_ = byteString.toStringUtf8();
        }

        public boolean getAllDescendants() {
            return this.allDescendants_;
        }

        /* access modifiers changed from: private */
        public void setAllDescendants(boolean z) {
            this.allDescendants_ = z;
        }

        /* access modifiers changed from: private */
        public void clearAllDescendants() {
            this.allDescendants_ = false;
        }

        public static CollectionSelector parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (CollectionSelector) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static CollectionSelector parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (CollectionSelector) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
        }

        public static CollectionSelector parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (CollectionSelector) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        public static CollectionSelector parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (CollectionSelector) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
        }

        public static CollectionSelector parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (CollectionSelector) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static CollectionSelector parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (CollectionSelector) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
        }

        public static CollectionSelector parseFrom(InputStream inputStream) throws IOException {
            return (CollectionSelector) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static CollectionSelector parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (CollectionSelector) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static CollectionSelector parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (CollectionSelector) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static CollectionSelector parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (CollectionSelector) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static CollectionSelector parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (CollectionSelector) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
        }

        public static CollectionSelector parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (CollectionSelector) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.createBuilder();
        }

        public static Builder newBuilder(CollectionSelector collectionSelector) {
            return (Builder) DEFAULT_INSTANCE.createBuilder(collectionSelector);
        }

        /* renamed from: com.google.firestore.v1.StructuredQuery$CollectionSelector$Builder */
        public static final class Builder extends GeneratedMessageLite.Builder<CollectionSelector, Builder> implements CollectionSelectorOrBuilder {
            /* synthetic */ Builder(C19891 r1) {
                this();
            }

            private Builder() {
                super(CollectionSelector.DEFAULT_INSTANCE);
            }

            public String getCollectionId() {
                return ((CollectionSelector) this.instance).getCollectionId();
            }

            public ByteString getCollectionIdBytes() {
                return ((CollectionSelector) this.instance).getCollectionIdBytes();
            }

            public Builder setCollectionId(String str) {
                copyOnWrite();
                ((CollectionSelector) this.instance).setCollectionId(str);
                return this;
            }

            public Builder clearCollectionId() {
                copyOnWrite();
                ((CollectionSelector) this.instance).clearCollectionId();
                return this;
            }

            public Builder setCollectionIdBytes(ByteString byteString) {
                copyOnWrite();
                ((CollectionSelector) this.instance).setCollectionIdBytes(byteString);
                return this;
            }

            public boolean getAllDescendants() {
                return ((CollectionSelector) this.instance).getAllDescendants();
            }

            public Builder setAllDescendants(boolean z) {
                copyOnWrite();
                ((CollectionSelector) this.instance).setAllDescendants(z);
                return this;
            }

            public Builder clearAllDescendants() {
                copyOnWrite();
                ((CollectionSelector) this.instance).clearAllDescendants();
                return this;
            }
        }

        /* access modifiers changed from: protected */
        public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
            switch (C19891.f394xa1df5c61[methodToInvoke.ordinal()]) {
                case 1:
                    return new CollectionSelector();
                case 2:
                    return new Builder((C19891) null);
                case 3:
                    return newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0002\u0000\u0000\u0002\u0003\u0002\u0000\u0000\u0000\u0002Ȉ\u0003\u0007", new Object[]{"collectionId_", "allDescendants_"});
                case 4:
                    return DEFAULT_INSTANCE;
                case 5:
                    Parser<CollectionSelector> parser = PARSER;
                    if (parser == null) {
                        synchronized (CollectionSelector.class) {
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
            CollectionSelector collectionSelector = new CollectionSelector();
            DEFAULT_INSTANCE = collectionSelector;
            GeneratedMessageLite.registerDefaultInstance(CollectionSelector.class, collectionSelector);
        }

        public static CollectionSelector getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<CollectionSelector> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    /* renamed from: com.google.firestore.v1.StructuredQuery$1 */
    static /* synthetic */ class C19891 {

        /* renamed from: $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke */
        static final /* synthetic */ int[] f394xa1df5c61;

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
                f394xa1df5c61 = r0
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r1 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.NEW_MUTABLE_INSTANCE     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = f394xa1df5c61     // Catch:{ NoSuchFieldError -> 0x001d }
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r1 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.NEW_BUILDER     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = f394xa1df5c61     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r1 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.BUILD_MESSAGE_INFO     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = f394xa1df5c61     // Catch:{ NoSuchFieldError -> 0x0033 }
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r1 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.GET_DEFAULT_INSTANCE     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                int[] r0 = f394xa1df5c61     // Catch:{ NoSuchFieldError -> 0x003e }
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r1 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.GET_PARSER     // Catch:{ NoSuchFieldError -> 0x003e }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x003e }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x003e }
            L_0x003e:
                int[] r0 = f394xa1df5c61     // Catch:{ NoSuchFieldError -> 0x0049 }
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r1 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.GET_MEMOIZED_IS_INITIALIZED     // Catch:{ NoSuchFieldError -> 0x0049 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0049 }
                r2 = 6
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0049 }
            L_0x0049:
                int[] r0 = f394xa1df5c61     // Catch:{ NoSuchFieldError -> 0x0054 }
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r1 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.SET_MEMOIZED_IS_INITIALIZED     // Catch:{ NoSuchFieldError -> 0x0054 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0054 }
                r2 = 7
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0054 }
            L_0x0054:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.firestore.p009v1.StructuredQuery.C19891.<clinit>():void");
        }
    }

    /* renamed from: com.google.firestore.v1.StructuredQuery$Filter */
    public static final class Filter extends GeneratedMessageLite<Filter, Builder> implements FilterOrBuilder {
        public static final int COMPOSITE_FILTER_FIELD_NUMBER = 1;
        /* access modifiers changed from: private */
        public static final Filter DEFAULT_INSTANCE;
        public static final int FIELD_FILTER_FIELD_NUMBER = 2;
        private static volatile Parser<Filter> PARSER = null;
        public static final int UNARY_FILTER_FIELD_NUMBER = 3;
        private int filterTypeCase_ = 0;
        private Object filterType_;

        private Filter() {
        }

        /* renamed from: com.google.firestore.v1.StructuredQuery$Filter$FilterTypeCase */
        public enum FilterTypeCase {
            COMPOSITE_FILTER(1),
            FIELD_FILTER(2),
            UNARY_FILTER(3),
            FILTERTYPE_NOT_SET(0);
            
            private final int value;

            private FilterTypeCase(int i) {
                this.value = i;
            }

            @Deprecated
            public static FilterTypeCase valueOf(int i) {
                return forNumber(i);
            }

            public static FilterTypeCase forNumber(int i) {
                if (i == 0) {
                    return FILTERTYPE_NOT_SET;
                }
                if (i == 1) {
                    return COMPOSITE_FILTER;
                }
                if (i == 2) {
                    return FIELD_FILTER;
                }
                if (i != 3) {
                    return null;
                }
                return UNARY_FILTER;
            }

            public int getNumber() {
                return this.value;
            }
        }

        public FilterTypeCase getFilterTypeCase() {
            return FilterTypeCase.forNumber(this.filterTypeCase_);
        }

        /* access modifiers changed from: private */
        public void clearFilterType() {
            this.filterTypeCase_ = 0;
            this.filterType_ = null;
        }

        public boolean hasCompositeFilter() {
            return this.filterTypeCase_ == 1;
        }

        public CompositeFilter getCompositeFilter() {
            if (this.filterTypeCase_ == 1) {
                return (CompositeFilter) this.filterType_;
            }
            return CompositeFilter.getDefaultInstance();
        }

        /* access modifiers changed from: private */
        public void setCompositeFilter(CompositeFilter compositeFilter) {
            compositeFilter.getClass();
            this.filterType_ = compositeFilter;
            this.filterTypeCase_ = 1;
        }

        /* access modifiers changed from: private */
        public void mergeCompositeFilter(CompositeFilter compositeFilter) {
            compositeFilter.getClass();
            if (this.filterTypeCase_ != 1 || this.filterType_ == CompositeFilter.getDefaultInstance()) {
                this.filterType_ = compositeFilter;
            } else {
                this.filterType_ = ((CompositeFilter.Builder) CompositeFilter.newBuilder((CompositeFilter) this.filterType_).mergeFrom(compositeFilter)).buildPartial();
            }
            this.filterTypeCase_ = 1;
        }

        /* access modifiers changed from: private */
        public void clearCompositeFilter() {
            if (this.filterTypeCase_ == 1) {
                this.filterTypeCase_ = 0;
                this.filterType_ = null;
            }
        }

        public boolean hasFieldFilter() {
            return this.filterTypeCase_ == 2;
        }

        public FieldFilter getFieldFilter() {
            if (this.filterTypeCase_ == 2) {
                return (FieldFilter) this.filterType_;
            }
            return FieldFilter.getDefaultInstance();
        }

        /* access modifiers changed from: private */
        public void setFieldFilter(FieldFilter fieldFilter) {
            fieldFilter.getClass();
            this.filterType_ = fieldFilter;
            this.filterTypeCase_ = 2;
        }

        /* access modifiers changed from: private */
        public void mergeFieldFilter(FieldFilter fieldFilter) {
            fieldFilter.getClass();
            if (this.filterTypeCase_ != 2 || this.filterType_ == FieldFilter.getDefaultInstance()) {
                this.filterType_ = fieldFilter;
            } else {
                this.filterType_ = ((FieldFilter.Builder) FieldFilter.newBuilder((FieldFilter) this.filterType_).mergeFrom(fieldFilter)).buildPartial();
            }
            this.filterTypeCase_ = 2;
        }

        /* access modifiers changed from: private */
        public void clearFieldFilter() {
            if (this.filterTypeCase_ == 2) {
                this.filterTypeCase_ = 0;
                this.filterType_ = null;
            }
        }

        public boolean hasUnaryFilter() {
            return this.filterTypeCase_ == 3;
        }

        public UnaryFilter getUnaryFilter() {
            if (this.filterTypeCase_ == 3) {
                return (UnaryFilter) this.filterType_;
            }
            return UnaryFilter.getDefaultInstance();
        }

        /* access modifiers changed from: private */
        public void setUnaryFilter(UnaryFilter unaryFilter) {
            unaryFilter.getClass();
            this.filterType_ = unaryFilter;
            this.filterTypeCase_ = 3;
        }

        /* access modifiers changed from: private */
        public void mergeUnaryFilter(UnaryFilter unaryFilter) {
            unaryFilter.getClass();
            if (this.filterTypeCase_ != 3 || this.filterType_ == UnaryFilter.getDefaultInstance()) {
                this.filterType_ = unaryFilter;
            } else {
                this.filterType_ = ((UnaryFilter.Builder) UnaryFilter.newBuilder((UnaryFilter) this.filterType_).mergeFrom(unaryFilter)).buildPartial();
            }
            this.filterTypeCase_ = 3;
        }

        /* access modifiers changed from: private */
        public void clearUnaryFilter() {
            if (this.filterTypeCase_ == 3) {
                this.filterTypeCase_ = 0;
                this.filterType_ = null;
            }
        }

        public static Filter parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (Filter) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static Filter parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (Filter) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
        }

        public static Filter parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (Filter) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        public static Filter parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (Filter) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
        }

        public static Filter parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (Filter) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static Filter parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (Filter) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
        }

        public static Filter parseFrom(InputStream inputStream) throws IOException {
            return (Filter) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static Filter parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (Filter) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static Filter parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (Filter) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static Filter parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (Filter) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static Filter parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (Filter) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
        }

        public static Filter parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (Filter) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.createBuilder();
        }

        public static Builder newBuilder(Filter filter) {
            return (Builder) DEFAULT_INSTANCE.createBuilder(filter);
        }

        /* renamed from: com.google.firestore.v1.StructuredQuery$Filter$Builder */
        public static final class Builder extends GeneratedMessageLite.Builder<Filter, Builder> implements FilterOrBuilder {
            /* synthetic */ Builder(C19891 r1) {
                this();
            }

            private Builder() {
                super(Filter.DEFAULT_INSTANCE);
            }

            public FilterTypeCase getFilterTypeCase() {
                return ((Filter) this.instance).getFilterTypeCase();
            }

            public Builder clearFilterType() {
                copyOnWrite();
                ((Filter) this.instance).clearFilterType();
                return this;
            }

            public boolean hasCompositeFilter() {
                return ((Filter) this.instance).hasCompositeFilter();
            }

            public CompositeFilter getCompositeFilter() {
                return ((Filter) this.instance).getCompositeFilter();
            }

            public Builder setCompositeFilter(CompositeFilter compositeFilter) {
                copyOnWrite();
                ((Filter) this.instance).setCompositeFilter(compositeFilter);
                return this;
            }

            public Builder setCompositeFilter(CompositeFilter.Builder builder) {
                copyOnWrite();
                ((Filter) this.instance).setCompositeFilter((CompositeFilter) builder.build());
                return this;
            }

            public Builder mergeCompositeFilter(CompositeFilter compositeFilter) {
                copyOnWrite();
                ((Filter) this.instance).mergeCompositeFilter(compositeFilter);
                return this;
            }

            public Builder clearCompositeFilter() {
                copyOnWrite();
                ((Filter) this.instance).clearCompositeFilter();
                return this;
            }

            public boolean hasFieldFilter() {
                return ((Filter) this.instance).hasFieldFilter();
            }

            public FieldFilter getFieldFilter() {
                return ((Filter) this.instance).getFieldFilter();
            }

            public Builder setFieldFilter(FieldFilter fieldFilter) {
                copyOnWrite();
                ((Filter) this.instance).setFieldFilter(fieldFilter);
                return this;
            }

            public Builder setFieldFilter(FieldFilter.Builder builder) {
                copyOnWrite();
                ((Filter) this.instance).setFieldFilter((FieldFilter) builder.build());
                return this;
            }

            public Builder mergeFieldFilter(FieldFilter fieldFilter) {
                copyOnWrite();
                ((Filter) this.instance).mergeFieldFilter(fieldFilter);
                return this;
            }

            public Builder clearFieldFilter() {
                copyOnWrite();
                ((Filter) this.instance).clearFieldFilter();
                return this;
            }

            public boolean hasUnaryFilter() {
                return ((Filter) this.instance).hasUnaryFilter();
            }

            public UnaryFilter getUnaryFilter() {
                return ((Filter) this.instance).getUnaryFilter();
            }

            public Builder setUnaryFilter(UnaryFilter unaryFilter) {
                copyOnWrite();
                ((Filter) this.instance).setUnaryFilter(unaryFilter);
                return this;
            }

            public Builder setUnaryFilter(UnaryFilter.Builder builder) {
                copyOnWrite();
                ((Filter) this.instance).setUnaryFilter((UnaryFilter) builder.build());
                return this;
            }

            public Builder mergeUnaryFilter(UnaryFilter unaryFilter) {
                copyOnWrite();
                ((Filter) this.instance).mergeUnaryFilter(unaryFilter);
                return this;
            }

            public Builder clearUnaryFilter() {
                copyOnWrite();
                ((Filter) this.instance).clearUnaryFilter();
                return this;
            }
        }

        /* access modifiers changed from: protected */
        public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
            switch (C19891.f394xa1df5c61[methodToInvoke.ordinal()]) {
                case 1:
                    return new Filter();
                case 2:
                    return new Builder((C19891) null);
                case 3:
                    return newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0003\u0001\u0000\u0001\u0003\u0003\u0000\u0000\u0000\u0001<\u0000\u0002<\u0000\u0003<\u0000", new Object[]{"filterType_", "filterTypeCase_", CompositeFilter.class, FieldFilter.class, UnaryFilter.class});
                case 4:
                    return DEFAULT_INSTANCE;
                case 5:
                    Parser<Filter> parser = PARSER;
                    if (parser == null) {
                        synchronized (Filter.class) {
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
            Filter filter = new Filter();
            DEFAULT_INSTANCE = filter;
            GeneratedMessageLite.registerDefaultInstance(Filter.class, filter);
        }

        public static Filter getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<Filter> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    /* renamed from: com.google.firestore.v1.StructuredQuery$CompositeFilter */
    public static final class CompositeFilter extends GeneratedMessageLite<CompositeFilter, Builder> implements CompositeFilterOrBuilder {
        /* access modifiers changed from: private */
        public static final CompositeFilter DEFAULT_INSTANCE;
        public static final int FILTERS_FIELD_NUMBER = 2;
        public static final int OP_FIELD_NUMBER = 1;
        private static volatile Parser<CompositeFilter> PARSER;
        private Internal.ProtobufList<Filter> filters_ = emptyProtobufList();
        private int op_;

        private CompositeFilter() {
        }

        /* renamed from: com.google.firestore.v1.StructuredQuery$CompositeFilter$Operator */
        public enum Operator implements Internal.EnumLite {
            OPERATOR_UNSPECIFIED(0),
            AND(1),
            UNRECOGNIZED(-1);
            
            public static final int AND_VALUE = 1;
            public static final int OPERATOR_UNSPECIFIED_VALUE = 0;
            private static final Internal.EnumLiteMap<Operator> internalValueMap = null;
            private final int value;

            static {
                internalValueMap = new Internal.EnumLiteMap<Operator>() {
                    public Operator findValueByNumber(int i) {
                        return Operator.forNumber(i);
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
            public static Operator valueOf(int i) {
                return forNumber(i);
            }

            public static Operator forNumber(int i) {
                if (i == 0) {
                    return OPERATOR_UNSPECIFIED;
                }
                if (i != 1) {
                    return null;
                }
                return AND;
            }

            public static Internal.EnumLiteMap<Operator> internalGetValueMap() {
                return internalValueMap;
            }

            public static Internal.EnumVerifier internalGetVerifier() {
                return OperatorVerifier.INSTANCE;
            }

            /* renamed from: com.google.firestore.v1.StructuredQuery$CompositeFilter$Operator$OperatorVerifier */
            private static final class OperatorVerifier implements Internal.EnumVerifier {
                static final Internal.EnumVerifier INSTANCE = null;

                private OperatorVerifier() {
                }

                static {
                    INSTANCE = new OperatorVerifier();
                }

                public boolean isInRange(int i) {
                    return Operator.forNumber(i) != null;
                }
            }

            private Operator(int i) {
                this.value = i;
            }
        }

        public int getOpValue() {
            return this.op_;
        }

        public Operator getOp() {
            Operator forNumber = Operator.forNumber(this.op_);
            return forNumber == null ? Operator.UNRECOGNIZED : forNumber;
        }

        /* access modifiers changed from: private */
        public void setOpValue(int i) {
            this.op_ = i;
        }

        /* access modifiers changed from: private */
        public void setOp(Operator operator) {
            this.op_ = operator.getNumber();
        }

        /* access modifiers changed from: private */
        public void clearOp() {
            this.op_ = 0;
        }

        public List<Filter> getFiltersList() {
            return this.filters_;
        }

        public List<? extends FilterOrBuilder> getFiltersOrBuilderList() {
            return this.filters_;
        }

        public int getFiltersCount() {
            return this.filters_.size();
        }

        public Filter getFilters(int i) {
            return (Filter) this.filters_.get(i);
        }

        public FilterOrBuilder getFiltersOrBuilder(int i) {
            return (FilterOrBuilder) this.filters_.get(i);
        }

        private void ensureFiltersIsMutable() {
            Internal.ProtobufList<Filter> protobufList = this.filters_;
            if (!protobufList.isModifiable()) {
                this.filters_ = GeneratedMessageLite.mutableCopy(protobufList);
            }
        }

        /* access modifiers changed from: private */
        public void setFilters(int i, Filter filter) {
            filter.getClass();
            ensureFiltersIsMutable();
            this.filters_.set(i, filter);
        }

        /* access modifiers changed from: private */
        public void addFilters(Filter filter) {
            filter.getClass();
            ensureFiltersIsMutable();
            this.filters_.add(filter);
        }

        /* access modifiers changed from: private */
        public void addFilters(int i, Filter filter) {
            filter.getClass();
            ensureFiltersIsMutable();
            this.filters_.add(i, filter);
        }

        /* access modifiers changed from: private */
        public void addAllFilters(Iterable<? extends Filter> iterable) {
            ensureFiltersIsMutable();
            AbstractMessageLite.addAll(iterable, this.filters_);
        }

        /* access modifiers changed from: private */
        public void clearFilters() {
            this.filters_ = emptyProtobufList();
        }

        /* access modifiers changed from: private */
        public void removeFilters(int i) {
            ensureFiltersIsMutable();
            this.filters_.remove(i);
        }

        public static CompositeFilter parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (CompositeFilter) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static CompositeFilter parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (CompositeFilter) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
        }

        public static CompositeFilter parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (CompositeFilter) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        public static CompositeFilter parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (CompositeFilter) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
        }

        public static CompositeFilter parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (CompositeFilter) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static CompositeFilter parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (CompositeFilter) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
        }

        public static CompositeFilter parseFrom(InputStream inputStream) throws IOException {
            return (CompositeFilter) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static CompositeFilter parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (CompositeFilter) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static CompositeFilter parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (CompositeFilter) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static CompositeFilter parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (CompositeFilter) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static CompositeFilter parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (CompositeFilter) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
        }

        public static CompositeFilter parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (CompositeFilter) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.createBuilder();
        }

        public static Builder newBuilder(CompositeFilter compositeFilter) {
            return (Builder) DEFAULT_INSTANCE.createBuilder(compositeFilter);
        }

        /* renamed from: com.google.firestore.v1.StructuredQuery$CompositeFilter$Builder */
        public static final class Builder extends GeneratedMessageLite.Builder<CompositeFilter, Builder> implements CompositeFilterOrBuilder {
            /* synthetic */ Builder(C19891 r1) {
                this();
            }

            private Builder() {
                super(CompositeFilter.DEFAULT_INSTANCE);
            }

            public int getOpValue() {
                return ((CompositeFilter) this.instance).getOpValue();
            }

            public Builder setOpValue(int i) {
                copyOnWrite();
                ((CompositeFilter) this.instance).setOpValue(i);
                return this;
            }

            public Operator getOp() {
                return ((CompositeFilter) this.instance).getOp();
            }

            public Builder setOp(Operator operator) {
                copyOnWrite();
                ((CompositeFilter) this.instance).setOp(operator);
                return this;
            }

            public Builder clearOp() {
                copyOnWrite();
                ((CompositeFilter) this.instance).clearOp();
                return this;
            }

            public List<Filter> getFiltersList() {
                return Collections.unmodifiableList(((CompositeFilter) this.instance).getFiltersList());
            }

            public int getFiltersCount() {
                return ((CompositeFilter) this.instance).getFiltersCount();
            }

            public Filter getFilters(int i) {
                return ((CompositeFilter) this.instance).getFilters(i);
            }

            public Builder setFilters(int i, Filter filter) {
                copyOnWrite();
                ((CompositeFilter) this.instance).setFilters(i, filter);
                return this;
            }

            public Builder setFilters(int i, Filter.Builder builder) {
                copyOnWrite();
                ((CompositeFilter) this.instance).setFilters(i, (Filter) builder.build());
                return this;
            }

            public Builder addFilters(Filter filter) {
                copyOnWrite();
                ((CompositeFilter) this.instance).addFilters(filter);
                return this;
            }

            public Builder addFilters(int i, Filter filter) {
                copyOnWrite();
                ((CompositeFilter) this.instance).addFilters(i, filter);
                return this;
            }

            public Builder addFilters(Filter.Builder builder) {
                copyOnWrite();
                ((CompositeFilter) this.instance).addFilters((Filter) builder.build());
                return this;
            }

            public Builder addFilters(int i, Filter.Builder builder) {
                copyOnWrite();
                ((CompositeFilter) this.instance).addFilters(i, (Filter) builder.build());
                return this;
            }

            public Builder addAllFilters(Iterable<? extends Filter> iterable) {
                copyOnWrite();
                ((CompositeFilter) this.instance).addAllFilters(iterable);
                return this;
            }

            public Builder clearFilters() {
                copyOnWrite();
                ((CompositeFilter) this.instance).clearFilters();
                return this;
            }

            public Builder removeFilters(int i) {
                copyOnWrite();
                ((CompositeFilter) this.instance).removeFilters(i);
                return this;
            }
        }

        /* access modifiers changed from: protected */
        public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
            switch (C19891.f394xa1df5c61[methodToInvoke.ordinal()]) {
                case 1:
                    return new CompositeFilter();
                case 2:
                    return new Builder((C19891) null);
                case 3:
                    return newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0001\u0000\u0001\f\u0002\u001b", new Object[]{"op_", "filters_", Filter.class});
                case 4:
                    return DEFAULT_INSTANCE;
                case 5:
                    Parser<CompositeFilter> parser = PARSER;
                    if (parser == null) {
                        synchronized (CompositeFilter.class) {
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
            CompositeFilter compositeFilter = new CompositeFilter();
            DEFAULT_INSTANCE = compositeFilter;
            GeneratedMessageLite.registerDefaultInstance(CompositeFilter.class, compositeFilter);
        }

        public static CompositeFilter getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<CompositeFilter> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    /* renamed from: com.google.firestore.v1.StructuredQuery$FieldFilter */
    public static final class FieldFilter extends GeneratedMessageLite<FieldFilter, Builder> implements FieldFilterOrBuilder {
        /* access modifiers changed from: private */
        public static final FieldFilter DEFAULT_INSTANCE;
        public static final int FIELD_FIELD_NUMBER = 1;
        public static final int OP_FIELD_NUMBER = 2;
        private static volatile Parser<FieldFilter> PARSER = null;
        public static final int VALUE_FIELD_NUMBER = 3;
        private FieldReference field_;
        private int op_;
        private Value value_;

        private FieldFilter() {
        }

        /* renamed from: com.google.firestore.v1.StructuredQuery$FieldFilter$Operator */
        public enum Operator implements Internal.EnumLite {
            OPERATOR_UNSPECIFIED(0),
            LESS_THAN(1),
            LESS_THAN_OR_EQUAL(2),
            GREATER_THAN(3),
            GREATER_THAN_OR_EQUAL(4),
            EQUAL(5),
            NOT_EQUAL(6),
            ARRAY_CONTAINS(7),
            IN(8),
            ARRAY_CONTAINS_ANY(9),
            NOT_IN(10),
            UNRECOGNIZED(-1);
            
            public static final int ARRAY_CONTAINS_ANY_VALUE = 9;
            public static final int ARRAY_CONTAINS_VALUE = 7;
            public static final int EQUAL_VALUE = 5;
            public static final int GREATER_THAN_OR_EQUAL_VALUE = 4;
            public static final int GREATER_THAN_VALUE = 3;
            public static final int IN_VALUE = 8;
            public static final int LESS_THAN_OR_EQUAL_VALUE = 2;
            public static final int LESS_THAN_VALUE = 1;
            public static final int NOT_EQUAL_VALUE = 6;
            public static final int NOT_IN_VALUE = 10;
            public static final int OPERATOR_UNSPECIFIED_VALUE = 0;
            private static final Internal.EnumLiteMap<Operator> internalValueMap = null;
            private final int value;

            static {
                internalValueMap = new Internal.EnumLiteMap<Operator>() {
                    public Operator findValueByNumber(int i) {
                        return Operator.forNumber(i);
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
            public static Operator valueOf(int i) {
                return forNumber(i);
            }

            public static Operator forNumber(int i) {
                switch (i) {
                    case 0:
                        return OPERATOR_UNSPECIFIED;
                    case 1:
                        return LESS_THAN;
                    case 2:
                        return LESS_THAN_OR_EQUAL;
                    case 3:
                        return GREATER_THAN;
                    case 4:
                        return GREATER_THAN_OR_EQUAL;
                    case 5:
                        return EQUAL;
                    case 6:
                        return NOT_EQUAL;
                    case 7:
                        return ARRAY_CONTAINS;
                    case 8:
                        return IN;
                    case 9:
                        return ARRAY_CONTAINS_ANY;
                    case 10:
                        return NOT_IN;
                    default:
                        return null;
                }
            }

            public static Internal.EnumLiteMap<Operator> internalGetValueMap() {
                return internalValueMap;
            }

            public static Internal.EnumVerifier internalGetVerifier() {
                return OperatorVerifier.INSTANCE;
            }

            /* renamed from: com.google.firestore.v1.StructuredQuery$FieldFilter$Operator$OperatorVerifier */
            private static final class OperatorVerifier implements Internal.EnumVerifier {
                static final Internal.EnumVerifier INSTANCE = null;

                private OperatorVerifier() {
                }

                static {
                    INSTANCE = new OperatorVerifier();
                }

                public boolean isInRange(int i) {
                    return Operator.forNumber(i) != null;
                }
            }

            private Operator(int i) {
                this.value = i;
            }
        }

        public boolean hasField() {
            return this.field_ != null;
        }

        public FieldReference getField() {
            FieldReference fieldReference = this.field_;
            return fieldReference == null ? FieldReference.getDefaultInstance() : fieldReference;
        }

        /* access modifiers changed from: private */
        public void setField(FieldReference fieldReference) {
            fieldReference.getClass();
            this.field_ = fieldReference;
        }

        /* access modifiers changed from: private */
        public void mergeField(FieldReference fieldReference) {
            fieldReference.getClass();
            FieldReference fieldReference2 = this.field_;
            if (fieldReference2 == null || fieldReference2 == FieldReference.getDefaultInstance()) {
                this.field_ = fieldReference;
            } else {
                this.field_ = (FieldReference) ((FieldReference.Builder) FieldReference.newBuilder(this.field_).mergeFrom(fieldReference)).buildPartial();
            }
        }

        /* access modifiers changed from: private */
        public void clearField() {
            this.field_ = null;
        }

        public int getOpValue() {
            return this.op_;
        }

        public Operator getOp() {
            Operator forNumber = Operator.forNumber(this.op_);
            return forNumber == null ? Operator.UNRECOGNIZED : forNumber;
        }

        /* access modifiers changed from: private */
        public void setOpValue(int i) {
            this.op_ = i;
        }

        /* access modifiers changed from: private */
        public void setOp(Operator operator) {
            this.op_ = operator.getNumber();
        }

        /* access modifiers changed from: private */
        public void clearOp() {
            this.op_ = 0;
        }

        public boolean hasValue() {
            return this.value_ != null;
        }

        public Value getValue() {
            Value value = this.value_;
            return value == null ? Value.getDefaultInstance() : value;
        }

        /* access modifiers changed from: private */
        public void setValue(Value value) {
            value.getClass();
            this.value_ = value;
        }

        /* access modifiers changed from: private */
        public void mergeValue(Value value) {
            value.getClass();
            Value value2 = this.value_;
            if (value2 == null || value2 == Value.getDefaultInstance()) {
                this.value_ = value;
            } else {
                this.value_ = (Value) ((Value.Builder) Value.newBuilder(this.value_).mergeFrom(value)).buildPartial();
            }
        }

        /* access modifiers changed from: private */
        public void clearValue() {
            this.value_ = null;
        }

        public static FieldFilter parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (FieldFilter) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static FieldFilter parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (FieldFilter) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
        }

        public static FieldFilter parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (FieldFilter) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        public static FieldFilter parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (FieldFilter) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
        }

        public static FieldFilter parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (FieldFilter) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static FieldFilter parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (FieldFilter) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
        }

        public static FieldFilter parseFrom(InputStream inputStream) throws IOException {
            return (FieldFilter) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static FieldFilter parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (FieldFilter) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static FieldFilter parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (FieldFilter) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static FieldFilter parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (FieldFilter) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static FieldFilter parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (FieldFilter) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
        }

        public static FieldFilter parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (FieldFilter) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.createBuilder();
        }

        public static Builder newBuilder(FieldFilter fieldFilter) {
            return (Builder) DEFAULT_INSTANCE.createBuilder(fieldFilter);
        }

        /* renamed from: com.google.firestore.v1.StructuredQuery$FieldFilter$Builder */
        public static final class Builder extends GeneratedMessageLite.Builder<FieldFilter, Builder> implements FieldFilterOrBuilder {
            /* synthetic */ Builder(C19891 r1) {
                this();
            }

            private Builder() {
                super(FieldFilter.DEFAULT_INSTANCE);
            }

            public boolean hasField() {
                return ((FieldFilter) this.instance).hasField();
            }

            public FieldReference getField() {
                return ((FieldFilter) this.instance).getField();
            }

            public Builder setField(FieldReference fieldReference) {
                copyOnWrite();
                ((FieldFilter) this.instance).setField(fieldReference);
                return this;
            }

            public Builder setField(FieldReference.Builder builder) {
                copyOnWrite();
                ((FieldFilter) this.instance).setField((FieldReference) builder.build());
                return this;
            }

            public Builder mergeField(FieldReference fieldReference) {
                copyOnWrite();
                ((FieldFilter) this.instance).mergeField(fieldReference);
                return this;
            }

            public Builder clearField() {
                copyOnWrite();
                ((FieldFilter) this.instance).clearField();
                return this;
            }

            public int getOpValue() {
                return ((FieldFilter) this.instance).getOpValue();
            }

            public Builder setOpValue(int i) {
                copyOnWrite();
                ((FieldFilter) this.instance).setOpValue(i);
                return this;
            }

            public Operator getOp() {
                return ((FieldFilter) this.instance).getOp();
            }

            public Builder setOp(Operator operator) {
                copyOnWrite();
                ((FieldFilter) this.instance).setOp(operator);
                return this;
            }

            public Builder clearOp() {
                copyOnWrite();
                ((FieldFilter) this.instance).clearOp();
                return this;
            }

            public boolean hasValue() {
                return ((FieldFilter) this.instance).hasValue();
            }

            public Value getValue() {
                return ((FieldFilter) this.instance).getValue();
            }

            public Builder setValue(Value value) {
                copyOnWrite();
                ((FieldFilter) this.instance).setValue(value);
                return this;
            }

            public Builder setValue(Value.Builder builder) {
                copyOnWrite();
                ((FieldFilter) this.instance).setValue((Value) builder.build());
                return this;
            }

            public Builder mergeValue(Value value) {
                copyOnWrite();
                ((FieldFilter) this.instance).mergeValue(value);
                return this;
            }

            public Builder clearValue() {
                copyOnWrite();
                ((FieldFilter) this.instance).clearValue();
                return this;
            }
        }

        /* access modifiers changed from: protected */
        public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
            switch (C19891.f394xa1df5c61[methodToInvoke.ordinal()]) {
                case 1:
                    return new FieldFilter();
                case 2:
                    return new Builder((C19891) null);
                case 3:
                    return newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0003\u0000\u0000\u0001\u0003\u0003\u0000\u0000\u0000\u0001\t\u0002\f\u0003\t", new Object[]{"field_", "op_", "value_"});
                case 4:
                    return DEFAULT_INSTANCE;
                case 5:
                    Parser<FieldFilter> parser = PARSER;
                    if (parser == null) {
                        synchronized (FieldFilter.class) {
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
            FieldFilter fieldFilter = new FieldFilter();
            DEFAULT_INSTANCE = fieldFilter;
            GeneratedMessageLite.registerDefaultInstance(FieldFilter.class, fieldFilter);
        }

        public static FieldFilter getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<FieldFilter> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    /* renamed from: com.google.firestore.v1.StructuredQuery$UnaryFilter */
    public static final class UnaryFilter extends GeneratedMessageLite<UnaryFilter, Builder> implements UnaryFilterOrBuilder {
        /* access modifiers changed from: private */
        public static final UnaryFilter DEFAULT_INSTANCE;
        public static final int FIELD_FIELD_NUMBER = 2;
        public static final int OP_FIELD_NUMBER = 1;
        private static volatile Parser<UnaryFilter> PARSER;
        private int op_;
        private int operandTypeCase_ = 0;
        private Object operandType_;

        private UnaryFilter() {
        }

        /* renamed from: com.google.firestore.v1.StructuredQuery$UnaryFilter$Operator */
        public enum Operator implements Internal.EnumLite {
            OPERATOR_UNSPECIFIED(0),
            IS_NAN(2),
            IS_NULL(3),
            IS_NOT_NAN(4),
            IS_NOT_NULL(5),
            UNRECOGNIZED(-1);
            
            public static final int IS_NAN_VALUE = 2;
            public static final int IS_NOT_NAN_VALUE = 4;
            public static final int IS_NOT_NULL_VALUE = 5;
            public static final int IS_NULL_VALUE = 3;
            public static final int OPERATOR_UNSPECIFIED_VALUE = 0;
            private static final Internal.EnumLiteMap<Operator> internalValueMap = null;
            private final int value;

            static {
                internalValueMap = new Internal.EnumLiteMap<Operator>() {
                    public Operator findValueByNumber(int i) {
                        return Operator.forNumber(i);
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
            public static Operator valueOf(int i) {
                return forNumber(i);
            }

            public static Operator forNumber(int i) {
                if (i == 0) {
                    return OPERATOR_UNSPECIFIED;
                }
                if (i == 2) {
                    return IS_NAN;
                }
                if (i == 3) {
                    return IS_NULL;
                }
                if (i == 4) {
                    return IS_NOT_NAN;
                }
                if (i != 5) {
                    return null;
                }
                return IS_NOT_NULL;
            }

            public static Internal.EnumLiteMap<Operator> internalGetValueMap() {
                return internalValueMap;
            }

            public static Internal.EnumVerifier internalGetVerifier() {
                return OperatorVerifier.INSTANCE;
            }

            /* renamed from: com.google.firestore.v1.StructuredQuery$UnaryFilter$Operator$OperatorVerifier */
            private static final class OperatorVerifier implements Internal.EnumVerifier {
                static final Internal.EnumVerifier INSTANCE = null;

                private OperatorVerifier() {
                }

                static {
                    INSTANCE = new OperatorVerifier();
                }

                public boolean isInRange(int i) {
                    return Operator.forNumber(i) != null;
                }
            }

            private Operator(int i) {
                this.value = i;
            }
        }

        /* renamed from: com.google.firestore.v1.StructuredQuery$UnaryFilter$OperandTypeCase */
        public enum OperandTypeCase {
            FIELD(2),
            OPERANDTYPE_NOT_SET(0);
            
            private final int value;

            private OperandTypeCase(int i) {
                this.value = i;
            }

            @Deprecated
            public static OperandTypeCase valueOf(int i) {
                return forNumber(i);
            }

            public static OperandTypeCase forNumber(int i) {
                if (i == 0) {
                    return OPERANDTYPE_NOT_SET;
                }
                if (i != 2) {
                    return null;
                }
                return FIELD;
            }

            public int getNumber() {
                return this.value;
            }
        }

        public OperandTypeCase getOperandTypeCase() {
            return OperandTypeCase.forNumber(this.operandTypeCase_);
        }

        /* access modifiers changed from: private */
        public void clearOperandType() {
            this.operandTypeCase_ = 0;
            this.operandType_ = null;
        }

        public int getOpValue() {
            return this.op_;
        }

        public Operator getOp() {
            Operator forNumber = Operator.forNumber(this.op_);
            return forNumber == null ? Operator.UNRECOGNIZED : forNumber;
        }

        /* access modifiers changed from: private */
        public void setOpValue(int i) {
            this.op_ = i;
        }

        /* access modifiers changed from: private */
        public void setOp(Operator operator) {
            this.op_ = operator.getNumber();
        }

        /* access modifiers changed from: private */
        public void clearOp() {
            this.op_ = 0;
        }

        public boolean hasField() {
            return this.operandTypeCase_ == 2;
        }

        public FieldReference getField() {
            if (this.operandTypeCase_ == 2) {
                return (FieldReference) this.operandType_;
            }
            return FieldReference.getDefaultInstance();
        }

        /* access modifiers changed from: private */
        public void setField(FieldReference fieldReference) {
            fieldReference.getClass();
            this.operandType_ = fieldReference;
            this.operandTypeCase_ = 2;
        }

        /* access modifiers changed from: private */
        public void mergeField(FieldReference fieldReference) {
            fieldReference.getClass();
            if (this.operandTypeCase_ != 2 || this.operandType_ == FieldReference.getDefaultInstance()) {
                this.operandType_ = fieldReference;
            } else {
                this.operandType_ = ((FieldReference.Builder) FieldReference.newBuilder((FieldReference) this.operandType_).mergeFrom(fieldReference)).buildPartial();
            }
            this.operandTypeCase_ = 2;
        }

        /* access modifiers changed from: private */
        public void clearField() {
            if (this.operandTypeCase_ == 2) {
                this.operandTypeCase_ = 0;
                this.operandType_ = null;
            }
        }

        public static UnaryFilter parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (UnaryFilter) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static UnaryFilter parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (UnaryFilter) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
        }

        public static UnaryFilter parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (UnaryFilter) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        public static UnaryFilter parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (UnaryFilter) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
        }

        public static UnaryFilter parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (UnaryFilter) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static UnaryFilter parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (UnaryFilter) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
        }

        public static UnaryFilter parseFrom(InputStream inputStream) throws IOException {
            return (UnaryFilter) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static UnaryFilter parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (UnaryFilter) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static UnaryFilter parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (UnaryFilter) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static UnaryFilter parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (UnaryFilter) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static UnaryFilter parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (UnaryFilter) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
        }

        public static UnaryFilter parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (UnaryFilter) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.createBuilder();
        }

        public static Builder newBuilder(UnaryFilter unaryFilter) {
            return (Builder) DEFAULT_INSTANCE.createBuilder(unaryFilter);
        }

        /* renamed from: com.google.firestore.v1.StructuredQuery$UnaryFilter$Builder */
        public static final class Builder extends GeneratedMessageLite.Builder<UnaryFilter, Builder> implements UnaryFilterOrBuilder {
            /* synthetic */ Builder(C19891 r1) {
                this();
            }

            private Builder() {
                super(UnaryFilter.DEFAULT_INSTANCE);
            }

            public OperandTypeCase getOperandTypeCase() {
                return ((UnaryFilter) this.instance).getOperandTypeCase();
            }

            public Builder clearOperandType() {
                copyOnWrite();
                ((UnaryFilter) this.instance).clearOperandType();
                return this;
            }

            public int getOpValue() {
                return ((UnaryFilter) this.instance).getOpValue();
            }

            public Builder setOpValue(int i) {
                copyOnWrite();
                ((UnaryFilter) this.instance).setOpValue(i);
                return this;
            }

            public Operator getOp() {
                return ((UnaryFilter) this.instance).getOp();
            }

            public Builder setOp(Operator operator) {
                copyOnWrite();
                ((UnaryFilter) this.instance).setOp(operator);
                return this;
            }

            public Builder clearOp() {
                copyOnWrite();
                ((UnaryFilter) this.instance).clearOp();
                return this;
            }

            public boolean hasField() {
                return ((UnaryFilter) this.instance).hasField();
            }

            public FieldReference getField() {
                return ((UnaryFilter) this.instance).getField();
            }

            public Builder setField(FieldReference fieldReference) {
                copyOnWrite();
                ((UnaryFilter) this.instance).setField(fieldReference);
                return this;
            }

            public Builder setField(FieldReference.Builder builder) {
                copyOnWrite();
                ((UnaryFilter) this.instance).setField((FieldReference) builder.build());
                return this;
            }

            public Builder mergeField(FieldReference fieldReference) {
                copyOnWrite();
                ((UnaryFilter) this.instance).mergeField(fieldReference);
                return this;
            }

            public Builder clearField() {
                copyOnWrite();
                ((UnaryFilter) this.instance).clearField();
                return this;
            }
        }

        /* access modifiers changed from: protected */
        public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
            switch (C19891.f394xa1df5c61[methodToInvoke.ordinal()]) {
                case 1:
                    return new UnaryFilter();
                case 2:
                    return new Builder((C19891) null);
                case 3:
                    return newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0002\u0001\u0000\u0001\u0002\u0002\u0000\u0000\u0000\u0001\f\u0002<\u0000", new Object[]{"operandType_", "operandTypeCase_", "op_", FieldReference.class});
                case 4:
                    return DEFAULT_INSTANCE;
                case 5:
                    Parser<UnaryFilter> parser = PARSER;
                    if (parser == null) {
                        synchronized (UnaryFilter.class) {
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
            UnaryFilter unaryFilter = new UnaryFilter();
            DEFAULT_INSTANCE = unaryFilter;
            GeneratedMessageLite.registerDefaultInstance(UnaryFilter.class, unaryFilter);
        }

        public static UnaryFilter getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<UnaryFilter> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    /* renamed from: com.google.firestore.v1.StructuredQuery$Order */
    public static final class Order extends GeneratedMessageLite<Order, Builder> implements OrderOrBuilder {
        /* access modifiers changed from: private */
        public static final Order DEFAULT_INSTANCE;
        public static final int DIRECTION_FIELD_NUMBER = 2;
        public static final int FIELD_FIELD_NUMBER = 1;
        private static volatile Parser<Order> PARSER;
        private int direction_;
        private FieldReference field_;

        private Order() {
        }

        public boolean hasField() {
            return this.field_ != null;
        }

        public FieldReference getField() {
            FieldReference fieldReference = this.field_;
            return fieldReference == null ? FieldReference.getDefaultInstance() : fieldReference;
        }

        /* access modifiers changed from: private */
        public void setField(FieldReference fieldReference) {
            fieldReference.getClass();
            this.field_ = fieldReference;
        }

        /* access modifiers changed from: private */
        public void mergeField(FieldReference fieldReference) {
            fieldReference.getClass();
            FieldReference fieldReference2 = this.field_;
            if (fieldReference2 == null || fieldReference2 == FieldReference.getDefaultInstance()) {
                this.field_ = fieldReference;
            } else {
                this.field_ = (FieldReference) ((FieldReference.Builder) FieldReference.newBuilder(this.field_).mergeFrom(fieldReference)).buildPartial();
            }
        }

        /* access modifiers changed from: private */
        public void clearField() {
            this.field_ = null;
        }

        public int getDirectionValue() {
            return this.direction_;
        }

        public Direction getDirection() {
            Direction forNumber = Direction.forNumber(this.direction_);
            return forNumber == null ? Direction.UNRECOGNIZED : forNumber;
        }

        /* access modifiers changed from: private */
        public void setDirectionValue(int i) {
            this.direction_ = i;
        }

        /* access modifiers changed from: private */
        public void setDirection(Direction direction) {
            this.direction_ = direction.getNumber();
        }

        /* access modifiers changed from: private */
        public void clearDirection() {
            this.direction_ = 0;
        }

        public static Order parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (Order) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static Order parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (Order) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
        }

        public static Order parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (Order) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        public static Order parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (Order) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
        }

        public static Order parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (Order) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static Order parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (Order) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
        }

        public static Order parseFrom(InputStream inputStream) throws IOException {
            return (Order) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static Order parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (Order) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static Order parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (Order) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static Order parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (Order) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static Order parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (Order) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
        }

        public static Order parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (Order) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.createBuilder();
        }

        public static Builder newBuilder(Order order) {
            return (Builder) DEFAULT_INSTANCE.createBuilder(order);
        }

        /* renamed from: com.google.firestore.v1.StructuredQuery$Order$Builder */
        public static final class Builder extends GeneratedMessageLite.Builder<Order, Builder> implements OrderOrBuilder {
            /* synthetic */ Builder(C19891 r1) {
                this();
            }

            private Builder() {
                super(Order.DEFAULT_INSTANCE);
            }

            public boolean hasField() {
                return ((Order) this.instance).hasField();
            }

            public FieldReference getField() {
                return ((Order) this.instance).getField();
            }

            public Builder setField(FieldReference fieldReference) {
                copyOnWrite();
                ((Order) this.instance).setField(fieldReference);
                return this;
            }

            public Builder setField(FieldReference.Builder builder) {
                copyOnWrite();
                ((Order) this.instance).setField((FieldReference) builder.build());
                return this;
            }

            public Builder mergeField(FieldReference fieldReference) {
                copyOnWrite();
                ((Order) this.instance).mergeField(fieldReference);
                return this;
            }

            public Builder clearField() {
                copyOnWrite();
                ((Order) this.instance).clearField();
                return this;
            }

            public int getDirectionValue() {
                return ((Order) this.instance).getDirectionValue();
            }

            public Builder setDirectionValue(int i) {
                copyOnWrite();
                ((Order) this.instance).setDirectionValue(i);
                return this;
            }

            public Direction getDirection() {
                return ((Order) this.instance).getDirection();
            }

            public Builder setDirection(Direction direction) {
                copyOnWrite();
                ((Order) this.instance).setDirection(direction);
                return this;
            }

            public Builder clearDirection() {
                copyOnWrite();
                ((Order) this.instance).clearDirection();
                return this;
            }
        }

        /* access modifiers changed from: protected */
        public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
            switch (C19891.f394xa1df5c61[methodToInvoke.ordinal()]) {
                case 1:
                    return new Order();
                case 2:
                    return new Builder((C19891) null);
                case 3:
                    return newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0000\u0000\u0001\t\u0002\f", new Object[]{"field_", "direction_"});
                case 4:
                    return DEFAULT_INSTANCE;
                case 5:
                    Parser<Order> parser = PARSER;
                    if (parser == null) {
                        synchronized (Order.class) {
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
            Order order = new Order();
            DEFAULT_INSTANCE = order;
            GeneratedMessageLite.registerDefaultInstance(Order.class, order);
        }

        public static Order getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<Order> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    /* renamed from: com.google.firestore.v1.StructuredQuery$FieldReference */
    public static final class FieldReference extends GeneratedMessageLite<FieldReference, Builder> implements FieldReferenceOrBuilder {
        /* access modifiers changed from: private */
        public static final FieldReference DEFAULT_INSTANCE;
        public static final int FIELD_PATH_FIELD_NUMBER = 2;
        private static volatile Parser<FieldReference> PARSER;
        private String fieldPath_ = "";

        private FieldReference() {
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

        public static FieldReference parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (FieldReference) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static FieldReference parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (FieldReference) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
        }

        public static FieldReference parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (FieldReference) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        public static FieldReference parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (FieldReference) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
        }

        public static FieldReference parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (FieldReference) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static FieldReference parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (FieldReference) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
        }

        public static FieldReference parseFrom(InputStream inputStream) throws IOException {
            return (FieldReference) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static FieldReference parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (FieldReference) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static FieldReference parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (FieldReference) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static FieldReference parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (FieldReference) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static FieldReference parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (FieldReference) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
        }

        public static FieldReference parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (FieldReference) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.createBuilder();
        }

        public static Builder newBuilder(FieldReference fieldReference) {
            return (Builder) DEFAULT_INSTANCE.createBuilder(fieldReference);
        }

        /* renamed from: com.google.firestore.v1.StructuredQuery$FieldReference$Builder */
        public static final class Builder extends GeneratedMessageLite.Builder<FieldReference, Builder> implements FieldReferenceOrBuilder {
            /* synthetic */ Builder(C19891 r1) {
                this();
            }

            private Builder() {
                super(FieldReference.DEFAULT_INSTANCE);
            }

            public String getFieldPath() {
                return ((FieldReference) this.instance).getFieldPath();
            }

            public ByteString getFieldPathBytes() {
                return ((FieldReference) this.instance).getFieldPathBytes();
            }

            public Builder setFieldPath(String str) {
                copyOnWrite();
                ((FieldReference) this.instance).setFieldPath(str);
                return this;
            }

            public Builder clearFieldPath() {
                copyOnWrite();
                ((FieldReference) this.instance).clearFieldPath();
                return this;
            }

            public Builder setFieldPathBytes(ByteString byteString) {
                copyOnWrite();
                ((FieldReference) this.instance).setFieldPathBytes(byteString);
                return this;
            }
        }

        /* access modifiers changed from: protected */
        public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
            switch (C19891.f394xa1df5c61[methodToInvoke.ordinal()]) {
                case 1:
                    return new FieldReference();
                case 2:
                    return new Builder((C19891) null);
                case 3:
                    return newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0001\u0000\u0000\u0002\u0002\u0001\u0000\u0000\u0000\u0002Ȉ", new Object[]{"fieldPath_"});
                case 4:
                    return DEFAULT_INSTANCE;
                case 5:
                    Parser<FieldReference> parser = PARSER;
                    if (parser == null) {
                        synchronized (FieldReference.class) {
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
            FieldReference fieldReference = new FieldReference();
            DEFAULT_INSTANCE = fieldReference;
            GeneratedMessageLite.registerDefaultInstance(FieldReference.class, fieldReference);
        }

        public static FieldReference getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<FieldReference> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    /* renamed from: com.google.firestore.v1.StructuredQuery$Projection */
    public static final class Projection extends GeneratedMessageLite<Projection, Builder> implements ProjectionOrBuilder {
        /* access modifiers changed from: private */
        public static final Projection DEFAULT_INSTANCE;
        public static final int FIELDS_FIELD_NUMBER = 2;
        private static volatile Parser<Projection> PARSER;
        private Internal.ProtobufList<FieldReference> fields_ = emptyProtobufList();

        private Projection() {
        }

        public List<FieldReference> getFieldsList() {
            return this.fields_;
        }

        public List<? extends FieldReferenceOrBuilder> getFieldsOrBuilderList() {
            return this.fields_;
        }

        public int getFieldsCount() {
            return this.fields_.size();
        }

        public FieldReference getFields(int i) {
            return (FieldReference) this.fields_.get(i);
        }

        public FieldReferenceOrBuilder getFieldsOrBuilder(int i) {
            return (FieldReferenceOrBuilder) this.fields_.get(i);
        }

        private void ensureFieldsIsMutable() {
            Internal.ProtobufList<FieldReference> protobufList = this.fields_;
            if (!protobufList.isModifiable()) {
                this.fields_ = GeneratedMessageLite.mutableCopy(protobufList);
            }
        }

        /* access modifiers changed from: private */
        public void setFields(int i, FieldReference fieldReference) {
            fieldReference.getClass();
            ensureFieldsIsMutable();
            this.fields_.set(i, fieldReference);
        }

        /* access modifiers changed from: private */
        public void addFields(FieldReference fieldReference) {
            fieldReference.getClass();
            ensureFieldsIsMutable();
            this.fields_.add(fieldReference);
        }

        /* access modifiers changed from: private */
        public void addFields(int i, FieldReference fieldReference) {
            fieldReference.getClass();
            ensureFieldsIsMutable();
            this.fields_.add(i, fieldReference);
        }

        /* access modifiers changed from: private */
        public void addAllFields(Iterable<? extends FieldReference> iterable) {
            ensureFieldsIsMutable();
            AbstractMessageLite.addAll(iterable, this.fields_);
        }

        /* access modifiers changed from: private */
        public void clearFields() {
            this.fields_ = emptyProtobufList();
        }

        /* access modifiers changed from: private */
        public void removeFields(int i) {
            ensureFieldsIsMutable();
            this.fields_.remove(i);
        }

        public static Projection parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (Projection) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static Projection parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (Projection) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
        }

        public static Projection parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (Projection) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        public static Projection parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (Projection) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
        }

        public static Projection parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (Projection) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static Projection parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (Projection) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
        }

        public static Projection parseFrom(InputStream inputStream) throws IOException {
            return (Projection) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static Projection parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (Projection) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static Projection parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (Projection) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static Projection parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (Projection) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static Projection parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (Projection) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
        }

        public static Projection parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (Projection) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.createBuilder();
        }

        public static Builder newBuilder(Projection projection) {
            return (Builder) DEFAULT_INSTANCE.createBuilder(projection);
        }

        /* renamed from: com.google.firestore.v1.StructuredQuery$Projection$Builder */
        public static final class Builder extends GeneratedMessageLite.Builder<Projection, Builder> implements ProjectionOrBuilder {
            /* synthetic */ Builder(C19891 r1) {
                this();
            }

            private Builder() {
                super(Projection.DEFAULT_INSTANCE);
            }

            public List<FieldReference> getFieldsList() {
                return Collections.unmodifiableList(((Projection) this.instance).getFieldsList());
            }

            public int getFieldsCount() {
                return ((Projection) this.instance).getFieldsCount();
            }

            public FieldReference getFields(int i) {
                return ((Projection) this.instance).getFields(i);
            }

            public Builder setFields(int i, FieldReference fieldReference) {
                copyOnWrite();
                ((Projection) this.instance).setFields(i, fieldReference);
                return this;
            }

            public Builder setFields(int i, FieldReference.Builder builder) {
                copyOnWrite();
                ((Projection) this.instance).setFields(i, (FieldReference) builder.build());
                return this;
            }

            public Builder addFields(FieldReference fieldReference) {
                copyOnWrite();
                ((Projection) this.instance).addFields(fieldReference);
                return this;
            }

            public Builder addFields(int i, FieldReference fieldReference) {
                copyOnWrite();
                ((Projection) this.instance).addFields(i, fieldReference);
                return this;
            }

            public Builder addFields(FieldReference.Builder builder) {
                copyOnWrite();
                ((Projection) this.instance).addFields((FieldReference) builder.build());
                return this;
            }

            public Builder addFields(int i, FieldReference.Builder builder) {
                copyOnWrite();
                ((Projection) this.instance).addFields(i, (FieldReference) builder.build());
                return this;
            }

            public Builder addAllFields(Iterable<? extends FieldReference> iterable) {
                copyOnWrite();
                ((Projection) this.instance).addAllFields(iterable);
                return this;
            }

            public Builder clearFields() {
                copyOnWrite();
                ((Projection) this.instance).clearFields();
                return this;
            }

            public Builder removeFields(int i) {
                copyOnWrite();
                ((Projection) this.instance).removeFields(i);
                return this;
            }
        }

        /* access modifiers changed from: protected */
        public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
            switch (C19891.f394xa1df5c61[methodToInvoke.ordinal()]) {
                case 1:
                    return new Projection();
                case 2:
                    return new Builder((C19891) null);
                case 3:
                    return newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0001\u0000\u0000\u0002\u0002\u0001\u0000\u0001\u0000\u0002\u001b", new Object[]{"fields_", FieldReference.class});
                case 4:
                    return DEFAULT_INSTANCE;
                case 5:
                    Parser<Projection> parser = PARSER;
                    if (parser == null) {
                        synchronized (Projection.class) {
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
            Projection projection = new Projection();
            DEFAULT_INSTANCE = projection;
            GeneratedMessageLite.registerDefaultInstance(Projection.class, projection);
        }

        public static Projection getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<Projection> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    public boolean hasSelect() {
        return this.select_ != null;
    }

    public Projection getSelect() {
        Projection projection = this.select_;
        return projection == null ? Projection.getDefaultInstance() : projection;
    }

    /* access modifiers changed from: private */
    public void setSelect(Projection projection) {
        projection.getClass();
        this.select_ = projection;
    }

    /* access modifiers changed from: private */
    public void mergeSelect(Projection projection) {
        projection.getClass();
        Projection projection2 = this.select_;
        if (projection2 == null || projection2 == Projection.getDefaultInstance()) {
            this.select_ = projection;
        } else {
            this.select_ = (Projection) ((Projection.Builder) Projection.newBuilder(this.select_).mergeFrom(projection)).buildPartial();
        }
    }

    /* access modifiers changed from: private */
    public void clearSelect() {
        this.select_ = null;
    }

    public List<CollectionSelector> getFromList() {
        return this.from_;
    }

    public List<? extends CollectionSelectorOrBuilder> getFromOrBuilderList() {
        return this.from_;
    }

    public int getFromCount() {
        return this.from_.size();
    }

    public CollectionSelector getFrom(int i) {
        return (CollectionSelector) this.from_.get(i);
    }

    public CollectionSelectorOrBuilder getFromOrBuilder(int i) {
        return (CollectionSelectorOrBuilder) this.from_.get(i);
    }

    private void ensureFromIsMutable() {
        Internal.ProtobufList<CollectionSelector> protobufList = this.from_;
        if (!protobufList.isModifiable()) {
            this.from_ = GeneratedMessageLite.mutableCopy(protobufList);
        }
    }

    /* access modifiers changed from: private */
    public void setFrom(int i, CollectionSelector collectionSelector) {
        collectionSelector.getClass();
        ensureFromIsMutable();
        this.from_.set(i, collectionSelector);
    }

    /* access modifiers changed from: private */
    public void addFrom(CollectionSelector collectionSelector) {
        collectionSelector.getClass();
        ensureFromIsMutable();
        this.from_.add(collectionSelector);
    }

    /* access modifiers changed from: private */
    public void addFrom(int i, CollectionSelector collectionSelector) {
        collectionSelector.getClass();
        ensureFromIsMutable();
        this.from_.add(i, collectionSelector);
    }

    /* access modifiers changed from: private */
    public void addAllFrom(Iterable<? extends CollectionSelector> iterable) {
        ensureFromIsMutable();
        AbstractMessageLite.addAll(iterable, this.from_);
    }

    /* access modifiers changed from: private */
    public void clearFrom() {
        this.from_ = emptyProtobufList();
    }

    /* access modifiers changed from: private */
    public void removeFrom(int i) {
        ensureFromIsMutable();
        this.from_.remove(i);
    }

    public boolean hasWhere() {
        return this.where_ != null;
    }

    public Filter getWhere() {
        Filter filter = this.where_;
        return filter == null ? Filter.getDefaultInstance() : filter;
    }

    /* access modifiers changed from: private */
    public void setWhere(Filter filter) {
        filter.getClass();
        this.where_ = filter;
    }

    /* access modifiers changed from: private */
    public void mergeWhere(Filter filter) {
        filter.getClass();
        Filter filter2 = this.where_;
        if (filter2 == null || filter2 == Filter.getDefaultInstance()) {
            this.where_ = filter;
        } else {
            this.where_ = (Filter) ((Filter.Builder) Filter.newBuilder(this.where_).mergeFrom(filter)).buildPartial();
        }
    }

    /* access modifiers changed from: private */
    public void clearWhere() {
        this.where_ = null;
    }

    public List<Order> getOrderByList() {
        return this.orderBy_;
    }

    public List<? extends OrderOrBuilder> getOrderByOrBuilderList() {
        return this.orderBy_;
    }

    public int getOrderByCount() {
        return this.orderBy_.size();
    }

    public Order getOrderBy(int i) {
        return (Order) this.orderBy_.get(i);
    }

    public OrderOrBuilder getOrderByOrBuilder(int i) {
        return (OrderOrBuilder) this.orderBy_.get(i);
    }

    private void ensureOrderByIsMutable() {
        Internal.ProtobufList<Order> protobufList = this.orderBy_;
        if (!protobufList.isModifiable()) {
            this.orderBy_ = GeneratedMessageLite.mutableCopy(protobufList);
        }
    }

    /* access modifiers changed from: private */
    public void setOrderBy(int i, Order order) {
        order.getClass();
        ensureOrderByIsMutable();
        this.orderBy_.set(i, order);
    }

    /* access modifiers changed from: private */
    public void addOrderBy(Order order) {
        order.getClass();
        ensureOrderByIsMutable();
        this.orderBy_.add(order);
    }

    /* access modifiers changed from: private */
    public void addOrderBy(int i, Order order) {
        order.getClass();
        ensureOrderByIsMutable();
        this.orderBy_.add(i, order);
    }

    /* access modifiers changed from: private */
    public void addAllOrderBy(Iterable<? extends Order> iterable) {
        ensureOrderByIsMutable();
        AbstractMessageLite.addAll(iterable, this.orderBy_);
    }

    /* access modifiers changed from: private */
    public void clearOrderBy() {
        this.orderBy_ = emptyProtobufList();
    }

    /* access modifiers changed from: private */
    public void removeOrderBy(int i) {
        ensureOrderByIsMutable();
        this.orderBy_.remove(i);
    }

    public boolean hasStartAt() {
        return this.startAt_ != null;
    }

    public Cursor getStartAt() {
        Cursor cursor = this.startAt_;
        return cursor == null ? Cursor.getDefaultInstance() : cursor;
    }

    /* access modifiers changed from: private */
    public void setStartAt(Cursor cursor) {
        cursor.getClass();
        this.startAt_ = cursor;
    }

    /* access modifiers changed from: private */
    public void mergeStartAt(Cursor cursor) {
        cursor.getClass();
        Cursor cursor2 = this.startAt_;
        if (cursor2 == null || cursor2 == Cursor.getDefaultInstance()) {
            this.startAt_ = cursor;
        } else {
            this.startAt_ = (Cursor) ((Cursor.Builder) Cursor.newBuilder(this.startAt_).mergeFrom(cursor)).buildPartial();
        }
    }

    /* access modifiers changed from: private */
    public void clearStartAt() {
        this.startAt_ = null;
    }

    public boolean hasEndAt() {
        return this.endAt_ != null;
    }

    public Cursor getEndAt() {
        Cursor cursor = this.endAt_;
        return cursor == null ? Cursor.getDefaultInstance() : cursor;
    }

    /* access modifiers changed from: private */
    public void setEndAt(Cursor cursor) {
        cursor.getClass();
        this.endAt_ = cursor;
    }

    /* access modifiers changed from: private */
    public void mergeEndAt(Cursor cursor) {
        cursor.getClass();
        Cursor cursor2 = this.endAt_;
        if (cursor2 == null || cursor2 == Cursor.getDefaultInstance()) {
            this.endAt_ = cursor;
        } else {
            this.endAt_ = (Cursor) ((Cursor.Builder) Cursor.newBuilder(this.endAt_).mergeFrom(cursor)).buildPartial();
        }
    }

    /* access modifiers changed from: private */
    public void clearEndAt() {
        this.endAt_ = null;
    }

    public int getOffset() {
        return this.offset_;
    }

    /* access modifiers changed from: private */
    public void setOffset(int i) {
        this.offset_ = i;
    }

    /* access modifiers changed from: private */
    public void clearOffset() {
        this.offset_ = 0;
    }

    public boolean hasLimit() {
        return this.limit_ != null;
    }

    public Int32Value getLimit() {
        Int32Value int32Value = this.limit_;
        return int32Value == null ? Int32Value.getDefaultInstance() : int32Value;
    }

    /* access modifiers changed from: private */
    public void setLimit(Int32Value int32Value) {
        int32Value.getClass();
        this.limit_ = int32Value;
    }

    /* access modifiers changed from: private */
    public void mergeLimit(Int32Value int32Value) {
        int32Value.getClass();
        Int32Value int32Value2 = this.limit_;
        if (int32Value2 == null || int32Value2 == Int32Value.getDefaultInstance()) {
            this.limit_ = int32Value;
        } else {
            this.limit_ = (Int32Value) ((Int32Value.Builder) Int32Value.newBuilder(this.limit_).mergeFrom(int32Value)).buildPartial();
        }
    }

    /* access modifiers changed from: private */
    public void clearLimit() {
        this.limit_ = null;
    }

    public static StructuredQuery parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return (StructuredQuery) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
    }

    public static StructuredQuery parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (StructuredQuery) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
    }

    public static StructuredQuery parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (StructuredQuery) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    public static StructuredQuery parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (StructuredQuery) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
    }

    public static StructuredQuery parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (StructuredQuery) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static StructuredQuery parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (StructuredQuery) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
    }

    public static StructuredQuery parseFrom(InputStream inputStream) throws IOException {
        return (StructuredQuery) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static StructuredQuery parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (StructuredQuery) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static StructuredQuery parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (StructuredQuery) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static StructuredQuery parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (StructuredQuery) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static StructuredQuery parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (StructuredQuery) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
    }

    public static StructuredQuery parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (StructuredQuery) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.createBuilder();
    }

    public static Builder newBuilder(StructuredQuery structuredQuery) {
        return (Builder) DEFAULT_INSTANCE.createBuilder(structuredQuery);
    }

    /* renamed from: com.google.firestore.v1.StructuredQuery$Builder */
    public static final class Builder extends GeneratedMessageLite.Builder<StructuredQuery, Builder> implements StructuredQueryOrBuilder {
        /* synthetic */ Builder(C19891 r1) {
            this();
        }

        private Builder() {
            super(StructuredQuery.DEFAULT_INSTANCE);
        }

        public boolean hasSelect() {
            return ((StructuredQuery) this.instance).hasSelect();
        }

        public Projection getSelect() {
            return ((StructuredQuery) this.instance).getSelect();
        }

        public Builder setSelect(Projection projection) {
            copyOnWrite();
            ((StructuredQuery) this.instance).setSelect(projection);
            return this;
        }

        public Builder setSelect(Projection.Builder builder) {
            copyOnWrite();
            ((StructuredQuery) this.instance).setSelect((Projection) builder.build());
            return this;
        }

        public Builder mergeSelect(Projection projection) {
            copyOnWrite();
            ((StructuredQuery) this.instance).mergeSelect(projection);
            return this;
        }

        public Builder clearSelect() {
            copyOnWrite();
            ((StructuredQuery) this.instance).clearSelect();
            return this;
        }

        public List<CollectionSelector> getFromList() {
            return Collections.unmodifiableList(((StructuredQuery) this.instance).getFromList());
        }

        public int getFromCount() {
            return ((StructuredQuery) this.instance).getFromCount();
        }

        public CollectionSelector getFrom(int i) {
            return ((StructuredQuery) this.instance).getFrom(i);
        }

        public Builder setFrom(int i, CollectionSelector collectionSelector) {
            copyOnWrite();
            ((StructuredQuery) this.instance).setFrom(i, collectionSelector);
            return this;
        }

        public Builder setFrom(int i, CollectionSelector.Builder builder) {
            copyOnWrite();
            ((StructuredQuery) this.instance).setFrom(i, (CollectionSelector) builder.build());
            return this;
        }

        public Builder addFrom(CollectionSelector collectionSelector) {
            copyOnWrite();
            ((StructuredQuery) this.instance).addFrom(collectionSelector);
            return this;
        }

        public Builder addFrom(int i, CollectionSelector collectionSelector) {
            copyOnWrite();
            ((StructuredQuery) this.instance).addFrom(i, collectionSelector);
            return this;
        }

        public Builder addFrom(CollectionSelector.Builder builder) {
            copyOnWrite();
            ((StructuredQuery) this.instance).addFrom((CollectionSelector) builder.build());
            return this;
        }

        public Builder addFrom(int i, CollectionSelector.Builder builder) {
            copyOnWrite();
            ((StructuredQuery) this.instance).addFrom(i, (CollectionSelector) builder.build());
            return this;
        }

        public Builder addAllFrom(Iterable<? extends CollectionSelector> iterable) {
            copyOnWrite();
            ((StructuredQuery) this.instance).addAllFrom(iterable);
            return this;
        }

        public Builder clearFrom() {
            copyOnWrite();
            ((StructuredQuery) this.instance).clearFrom();
            return this;
        }

        public Builder removeFrom(int i) {
            copyOnWrite();
            ((StructuredQuery) this.instance).removeFrom(i);
            return this;
        }

        public boolean hasWhere() {
            return ((StructuredQuery) this.instance).hasWhere();
        }

        public Filter getWhere() {
            return ((StructuredQuery) this.instance).getWhere();
        }

        public Builder setWhere(Filter filter) {
            copyOnWrite();
            ((StructuredQuery) this.instance).setWhere(filter);
            return this;
        }

        public Builder setWhere(Filter.Builder builder) {
            copyOnWrite();
            ((StructuredQuery) this.instance).setWhere((Filter) builder.build());
            return this;
        }

        public Builder mergeWhere(Filter filter) {
            copyOnWrite();
            ((StructuredQuery) this.instance).mergeWhere(filter);
            return this;
        }

        public Builder clearWhere() {
            copyOnWrite();
            ((StructuredQuery) this.instance).clearWhere();
            return this;
        }

        public List<Order> getOrderByList() {
            return Collections.unmodifiableList(((StructuredQuery) this.instance).getOrderByList());
        }

        public int getOrderByCount() {
            return ((StructuredQuery) this.instance).getOrderByCount();
        }

        public Order getOrderBy(int i) {
            return ((StructuredQuery) this.instance).getOrderBy(i);
        }

        public Builder setOrderBy(int i, Order order) {
            copyOnWrite();
            ((StructuredQuery) this.instance).setOrderBy(i, order);
            return this;
        }

        public Builder setOrderBy(int i, Order.Builder builder) {
            copyOnWrite();
            ((StructuredQuery) this.instance).setOrderBy(i, (Order) builder.build());
            return this;
        }

        public Builder addOrderBy(Order order) {
            copyOnWrite();
            ((StructuredQuery) this.instance).addOrderBy(order);
            return this;
        }

        public Builder addOrderBy(int i, Order order) {
            copyOnWrite();
            ((StructuredQuery) this.instance).addOrderBy(i, order);
            return this;
        }

        public Builder addOrderBy(Order.Builder builder) {
            copyOnWrite();
            ((StructuredQuery) this.instance).addOrderBy((Order) builder.build());
            return this;
        }

        public Builder addOrderBy(int i, Order.Builder builder) {
            copyOnWrite();
            ((StructuredQuery) this.instance).addOrderBy(i, (Order) builder.build());
            return this;
        }

        public Builder addAllOrderBy(Iterable<? extends Order> iterable) {
            copyOnWrite();
            ((StructuredQuery) this.instance).addAllOrderBy(iterable);
            return this;
        }

        public Builder clearOrderBy() {
            copyOnWrite();
            ((StructuredQuery) this.instance).clearOrderBy();
            return this;
        }

        public Builder removeOrderBy(int i) {
            copyOnWrite();
            ((StructuredQuery) this.instance).removeOrderBy(i);
            return this;
        }

        public boolean hasStartAt() {
            return ((StructuredQuery) this.instance).hasStartAt();
        }

        public Cursor getStartAt() {
            return ((StructuredQuery) this.instance).getStartAt();
        }

        public Builder setStartAt(Cursor cursor) {
            copyOnWrite();
            ((StructuredQuery) this.instance).setStartAt(cursor);
            return this;
        }

        public Builder setStartAt(Cursor.Builder builder) {
            copyOnWrite();
            ((StructuredQuery) this.instance).setStartAt((Cursor) builder.build());
            return this;
        }

        public Builder mergeStartAt(Cursor cursor) {
            copyOnWrite();
            ((StructuredQuery) this.instance).mergeStartAt(cursor);
            return this;
        }

        public Builder clearStartAt() {
            copyOnWrite();
            ((StructuredQuery) this.instance).clearStartAt();
            return this;
        }

        public boolean hasEndAt() {
            return ((StructuredQuery) this.instance).hasEndAt();
        }

        public Cursor getEndAt() {
            return ((StructuredQuery) this.instance).getEndAt();
        }

        public Builder setEndAt(Cursor cursor) {
            copyOnWrite();
            ((StructuredQuery) this.instance).setEndAt(cursor);
            return this;
        }

        public Builder setEndAt(Cursor.Builder builder) {
            copyOnWrite();
            ((StructuredQuery) this.instance).setEndAt((Cursor) builder.build());
            return this;
        }

        public Builder mergeEndAt(Cursor cursor) {
            copyOnWrite();
            ((StructuredQuery) this.instance).mergeEndAt(cursor);
            return this;
        }

        public Builder clearEndAt() {
            copyOnWrite();
            ((StructuredQuery) this.instance).clearEndAt();
            return this;
        }

        public int getOffset() {
            return ((StructuredQuery) this.instance).getOffset();
        }

        public Builder setOffset(int i) {
            copyOnWrite();
            ((StructuredQuery) this.instance).setOffset(i);
            return this;
        }

        public Builder clearOffset() {
            copyOnWrite();
            ((StructuredQuery) this.instance).clearOffset();
            return this;
        }

        public boolean hasLimit() {
            return ((StructuredQuery) this.instance).hasLimit();
        }

        public Int32Value getLimit() {
            return ((StructuredQuery) this.instance).getLimit();
        }

        public Builder setLimit(Int32Value int32Value) {
            copyOnWrite();
            ((StructuredQuery) this.instance).setLimit(int32Value);
            return this;
        }

        public Builder setLimit(Int32Value.Builder builder) {
            copyOnWrite();
            ((StructuredQuery) this.instance).setLimit((Int32Value) builder.build());
            return this;
        }

        public Builder mergeLimit(Int32Value int32Value) {
            copyOnWrite();
            ((StructuredQuery) this.instance).mergeLimit(int32Value);
            return this;
        }

        public Builder clearLimit() {
            copyOnWrite();
            ((StructuredQuery) this.instance).clearLimit();
            return this;
        }
    }

    /* access modifiers changed from: protected */
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        switch (C19891.f394xa1df5c61[methodToInvoke.ordinal()]) {
            case 1:
                return new StructuredQuery();
            case 2:
                return new Builder((C19891) null);
            case 3:
                return newMessageInfo(DEFAULT_INSTANCE, "\u0000\b\u0000\u0000\u0001\b\b\u0000\u0002\u0000\u0001\t\u0002\u001b\u0003\t\u0004\u001b\u0005\t\u0006\u0004\u0007\t\b\t", new Object[]{"select_", "from_", CollectionSelector.class, "where_", "orderBy_", Order.class, "limit_", "offset_", "startAt_", "endAt_"});
            case 4:
                return DEFAULT_INSTANCE;
            case 5:
                Parser<StructuredQuery> parser = PARSER;
                if (parser == null) {
                    synchronized (StructuredQuery.class) {
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
        StructuredQuery structuredQuery = new StructuredQuery();
        DEFAULT_INSTANCE = structuredQuery;
        GeneratedMessageLite.registerDefaultInstance(StructuredQuery.class, structuredQuery);
    }

    public static StructuredQuery getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<StructuredQuery> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}

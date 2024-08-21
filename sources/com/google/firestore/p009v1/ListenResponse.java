package com.google.firestore.p009v1;

import com.google.firestore.p009v1.DocumentChange;
import com.google.firestore.p009v1.DocumentDelete;
import com.google.firestore.p009v1.DocumentRemove;
import com.google.firestore.p009v1.ExistenceFilter;
import com.google.firestore.p009v1.TargetChange;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Parser;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

/* renamed from: com.google.firestore.v1.ListenResponse */
public final class ListenResponse extends GeneratedMessageLite<ListenResponse, Builder> implements ListenResponseOrBuilder {
    /* access modifiers changed from: private */
    public static final ListenResponse DEFAULT_INSTANCE;
    public static final int DOCUMENT_CHANGE_FIELD_NUMBER = 3;
    public static final int DOCUMENT_DELETE_FIELD_NUMBER = 4;
    public static final int DOCUMENT_REMOVE_FIELD_NUMBER = 6;
    public static final int FILTER_FIELD_NUMBER = 5;
    private static volatile Parser<ListenResponse> PARSER = null;
    public static final int TARGET_CHANGE_FIELD_NUMBER = 2;
    private int responseTypeCase_ = 0;
    private Object responseType_;

    private ListenResponse() {
    }

    /* renamed from: com.google.firestore.v1.ListenResponse$ResponseTypeCase */
    public enum ResponseTypeCase {
        TARGET_CHANGE(2),
        DOCUMENT_CHANGE(3),
        DOCUMENT_DELETE(4),
        DOCUMENT_REMOVE(6),
        FILTER(5),
        RESPONSETYPE_NOT_SET(0);
        
        private final int value;

        private ResponseTypeCase(int i) {
            this.value = i;
        }

        @Deprecated
        public static ResponseTypeCase valueOf(int i) {
            return forNumber(i);
        }

        public static ResponseTypeCase forNumber(int i) {
            if (i == 0) {
                return RESPONSETYPE_NOT_SET;
            }
            if (i == 2) {
                return TARGET_CHANGE;
            }
            if (i == 3) {
                return DOCUMENT_CHANGE;
            }
            if (i == 4) {
                return DOCUMENT_DELETE;
            }
            if (i == 5) {
                return FILTER;
            }
            if (i != 6) {
                return null;
            }
            return DOCUMENT_REMOVE;
        }

        public int getNumber() {
            return this.value;
        }
    }

    public ResponseTypeCase getResponseTypeCase() {
        return ResponseTypeCase.forNumber(this.responseTypeCase_);
    }

    /* access modifiers changed from: private */
    public void clearResponseType() {
        this.responseTypeCase_ = 0;
        this.responseType_ = null;
    }

    public boolean hasTargetChange() {
        return this.responseTypeCase_ == 2;
    }

    public TargetChange getTargetChange() {
        if (this.responseTypeCase_ == 2) {
            return (TargetChange) this.responseType_;
        }
        return TargetChange.getDefaultInstance();
    }

    /* access modifiers changed from: private */
    public void setTargetChange(TargetChange targetChange) {
        targetChange.getClass();
        this.responseType_ = targetChange;
        this.responseTypeCase_ = 2;
    }

    /* access modifiers changed from: private */
    public void mergeTargetChange(TargetChange targetChange) {
        targetChange.getClass();
        if (this.responseTypeCase_ != 2 || this.responseType_ == TargetChange.getDefaultInstance()) {
            this.responseType_ = targetChange;
        } else {
            this.responseType_ = ((TargetChange.Builder) TargetChange.newBuilder((TargetChange) this.responseType_).mergeFrom(targetChange)).buildPartial();
        }
        this.responseTypeCase_ = 2;
    }

    /* access modifiers changed from: private */
    public void clearTargetChange() {
        if (this.responseTypeCase_ == 2) {
            this.responseTypeCase_ = 0;
            this.responseType_ = null;
        }
    }

    public boolean hasDocumentChange() {
        return this.responseTypeCase_ == 3;
    }

    public DocumentChange getDocumentChange() {
        if (this.responseTypeCase_ == 3) {
            return (DocumentChange) this.responseType_;
        }
        return DocumentChange.getDefaultInstance();
    }

    /* access modifiers changed from: private */
    public void setDocumentChange(DocumentChange documentChange) {
        documentChange.getClass();
        this.responseType_ = documentChange;
        this.responseTypeCase_ = 3;
    }

    /* access modifiers changed from: private */
    public void mergeDocumentChange(DocumentChange documentChange) {
        documentChange.getClass();
        if (this.responseTypeCase_ != 3 || this.responseType_ == DocumentChange.getDefaultInstance()) {
            this.responseType_ = documentChange;
        } else {
            this.responseType_ = ((DocumentChange.Builder) DocumentChange.newBuilder((DocumentChange) this.responseType_).mergeFrom(documentChange)).buildPartial();
        }
        this.responseTypeCase_ = 3;
    }

    /* access modifiers changed from: private */
    public void clearDocumentChange() {
        if (this.responseTypeCase_ == 3) {
            this.responseTypeCase_ = 0;
            this.responseType_ = null;
        }
    }

    public boolean hasDocumentDelete() {
        return this.responseTypeCase_ == 4;
    }

    public DocumentDelete getDocumentDelete() {
        if (this.responseTypeCase_ == 4) {
            return (DocumentDelete) this.responseType_;
        }
        return DocumentDelete.getDefaultInstance();
    }

    /* access modifiers changed from: private */
    public void setDocumentDelete(DocumentDelete documentDelete) {
        documentDelete.getClass();
        this.responseType_ = documentDelete;
        this.responseTypeCase_ = 4;
    }

    /* access modifiers changed from: private */
    public void mergeDocumentDelete(DocumentDelete documentDelete) {
        documentDelete.getClass();
        if (this.responseTypeCase_ != 4 || this.responseType_ == DocumentDelete.getDefaultInstance()) {
            this.responseType_ = documentDelete;
        } else {
            this.responseType_ = ((DocumentDelete.Builder) DocumentDelete.newBuilder((DocumentDelete) this.responseType_).mergeFrom(documentDelete)).buildPartial();
        }
        this.responseTypeCase_ = 4;
    }

    /* access modifiers changed from: private */
    public void clearDocumentDelete() {
        if (this.responseTypeCase_ == 4) {
            this.responseTypeCase_ = 0;
            this.responseType_ = null;
        }
    }

    public boolean hasDocumentRemove() {
        return this.responseTypeCase_ == 6;
    }

    public DocumentRemove getDocumentRemove() {
        if (this.responseTypeCase_ == 6) {
            return (DocumentRemove) this.responseType_;
        }
        return DocumentRemove.getDefaultInstance();
    }

    /* access modifiers changed from: private */
    public void setDocumentRemove(DocumentRemove documentRemove) {
        documentRemove.getClass();
        this.responseType_ = documentRemove;
        this.responseTypeCase_ = 6;
    }

    /* access modifiers changed from: private */
    public void mergeDocumentRemove(DocumentRemove documentRemove) {
        documentRemove.getClass();
        if (this.responseTypeCase_ != 6 || this.responseType_ == DocumentRemove.getDefaultInstance()) {
            this.responseType_ = documentRemove;
        } else {
            this.responseType_ = ((DocumentRemove.Builder) DocumentRemove.newBuilder((DocumentRemove) this.responseType_).mergeFrom(documentRemove)).buildPartial();
        }
        this.responseTypeCase_ = 6;
    }

    /* access modifiers changed from: private */
    public void clearDocumentRemove() {
        if (this.responseTypeCase_ == 6) {
            this.responseTypeCase_ = 0;
            this.responseType_ = null;
        }
    }

    public boolean hasFilter() {
        return this.responseTypeCase_ == 5;
    }

    public ExistenceFilter getFilter() {
        if (this.responseTypeCase_ == 5) {
            return (ExistenceFilter) this.responseType_;
        }
        return ExistenceFilter.getDefaultInstance();
    }

    /* access modifiers changed from: private */
    public void setFilter(ExistenceFilter existenceFilter) {
        existenceFilter.getClass();
        this.responseType_ = existenceFilter;
        this.responseTypeCase_ = 5;
    }

    /* access modifiers changed from: private */
    public void mergeFilter(ExistenceFilter existenceFilter) {
        existenceFilter.getClass();
        if (this.responseTypeCase_ != 5 || this.responseType_ == ExistenceFilter.getDefaultInstance()) {
            this.responseType_ = existenceFilter;
        } else {
            this.responseType_ = ((ExistenceFilter.Builder) ExistenceFilter.newBuilder((ExistenceFilter) this.responseType_).mergeFrom(existenceFilter)).buildPartial();
        }
        this.responseTypeCase_ = 5;
    }

    /* access modifiers changed from: private */
    public void clearFilter() {
        if (this.responseTypeCase_ == 5) {
            this.responseTypeCase_ = 0;
            this.responseType_ = null;
        }
    }

    public static ListenResponse parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return (ListenResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
    }

    public static ListenResponse parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (ListenResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
    }

    public static ListenResponse parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (ListenResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    public static ListenResponse parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (ListenResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
    }

    public static ListenResponse parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (ListenResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static ListenResponse parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (ListenResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
    }

    public static ListenResponse parseFrom(InputStream inputStream) throws IOException {
        return (ListenResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static ListenResponse parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (ListenResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static ListenResponse parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (ListenResponse) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static ListenResponse parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (ListenResponse) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static ListenResponse parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (ListenResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
    }

    public static ListenResponse parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (ListenResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.createBuilder();
    }

    public static Builder newBuilder(ListenResponse listenResponse) {
        return (Builder) DEFAULT_INSTANCE.createBuilder(listenResponse);
    }

    /* renamed from: com.google.firestore.v1.ListenResponse$Builder */
    public static final class Builder extends GeneratedMessageLite.Builder<ListenResponse, Builder> implements ListenResponseOrBuilder {
        /* synthetic */ Builder(C19831 r1) {
            this();
        }

        private Builder() {
            super(ListenResponse.DEFAULT_INSTANCE);
        }

        public ResponseTypeCase getResponseTypeCase() {
            return ((ListenResponse) this.instance).getResponseTypeCase();
        }

        public Builder clearResponseType() {
            copyOnWrite();
            ((ListenResponse) this.instance).clearResponseType();
            return this;
        }

        public boolean hasTargetChange() {
            return ((ListenResponse) this.instance).hasTargetChange();
        }

        public TargetChange getTargetChange() {
            return ((ListenResponse) this.instance).getTargetChange();
        }

        public Builder setTargetChange(TargetChange targetChange) {
            copyOnWrite();
            ((ListenResponse) this.instance).setTargetChange(targetChange);
            return this;
        }

        public Builder setTargetChange(TargetChange.Builder builder) {
            copyOnWrite();
            ((ListenResponse) this.instance).setTargetChange((TargetChange) builder.build());
            return this;
        }

        public Builder mergeTargetChange(TargetChange targetChange) {
            copyOnWrite();
            ((ListenResponse) this.instance).mergeTargetChange(targetChange);
            return this;
        }

        public Builder clearTargetChange() {
            copyOnWrite();
            ((ListenResponse) this.instance).clearTargetChange();
            return this;
        }

        public boolean hasDocumentChange() {
            return ((ListenResponse) this.instance).hasDocumentChange();
        }

        public DocumentChange getDocumentChange() {
            return ((ListenResponse) this.instance).getDocumentChange();
        }

        public Builder setDocumentChange(DocumentChange documentChange) {
            copyOnWrite();
            ((ListenResponse) this.instance).setDocumentChange(documentChange);
            return this;
        }

        public Builder setDocumentChange(DocumentChange.Builder builder) {
            copyOnWrite();
            ((ListenResponse) this.instance).setDocumentChange((DocumentChange) builder.build());
            return this;
        }

        public Builder mergeDocumentChange(DocumentChange documentChange) {
            copyOnWrite();
            ((ListenResponse) this.instance).mergeDocumentChange(documentChange);
            return this;
        }

        public Builder clearDocumentChange() {
            copyOnWrite();
            ((ListenResponse) this.instance).clearDocumentChange();
            return this;
        }

        public boolean hasDocumentDelete() {
            return ((ListenResponse) this.instance).hasDocumentDelete();
        }

        public DocumentDelete getDocumentDelete() {
            return ((ListenResponse) this.instance).getDocumentDelete();
        }

        public Builder setDocumentDelete(DocumentDelete documentDelete) {
            copyOnWrite();
            ((ListenResponse) this.instance).setDocumentDelete(documentDelete);
            return this;
        }

        public Builder setDocumentDelete(DocumentDelete.Builder builder) {
            copyOnWrite();
            ((ListenResponse) this.instance).setDocumentDelete((DocumentDelete) builder.build());
            return this;
        }

        public Builder mergeDocumentDelete(DocumentDelete documentDelete) {
            copyOnWrite();
            ((ListenResponse) this.instance).mergeDocumentDelete(documentDelete);
            return this;
        }

        public Builder clearDocumentDelete() {
            copyOnWrite();
            ((ListenResponse) this.instance).clearDocumentDelete();
            return this;
        }

        public boolean hasDocumentRemove() {
            return ((ListenResponse) this.instance).hasDocumentRemove();
        }

        public DocumentRemove getDocumentRemove() {
            return ((ListenResponse) this.instance).getDocumentRemove();
        }

        public Builder setDocumentRemove(DocumentRemove documentRemove) {
            copyOnWrite();
            ((ListenResponse) this.instance).setDocumentRemove(documentRemove);
            return this;
        }

        public Builder setDocumentRemove(DocumentRemove.Builder builder) {
            copyOnWrite();
            ((ListenResponse) this.instance).setDocumentRemove((DocumentRemove) builder.build());
            return this;
        }

        public Builder mergeDocumentRemove(DocumentRemove documentRemove) {
            copyOnWrite();
            ((ListenResponse) this.instance).mergeDocumentRemove(documentRemove);
            return this;
        }

        public Builder clearDocumentRemove() {
            copyOnWrite();
            ((ListenResponse) this.instance).clearDocumentRemove();
            return this;
        }

        public boolean hasFilter() {
            return ((ListenResponse) this.instance).hasFilter();
        }

        public ExistenceFilter getFilter() {
            return ((ListenResponse) this.instance).getFilter();
        }

        public Builder setFilter(ExistenceFilter existenceFilter) {
            copyOnWrite();
            ((ListenResponse) this.instance).setFilter(existenceFilter);
            return this;
        }

        public Builder setFilter(ExistenceFilter.Builder builder) {
            copyOnWrite();
            ((ListenResponse) this.instance).setFilter((ExistenceFilter) builder.build());
            return this;
        }

        public Builder mergeFilter(ExistenceFilter existenceFilter) {
            copyOnWrite();
            ((ListenResponse) this.instance).mergeFilter(existenceFilter);
            return this;
        }

        public Builder clearFilter() {
            copyOnWrite();
            ((ListenResponse) this.instance).clearFilter();
            return this;
        }
    }

    /* renamed from: com.google.firestore.v1.ListenResponse$1 */
    static /* synthetic */ class C19831 {

        /* renamed from: $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke */
        static final /* synthetic */ int[] f388xa1df5c61;

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
                f388xa1df5c61 = r0
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r1 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.NEW_MUTABLE_INSTANCE     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = f388xa1df5c61     // Catch:{ NoSuchFieldError -> 0x001d }
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r1 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.NEW_BUILDER     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = f388xa1df5c61     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r1 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.BUILD_MESSAGE_INFO     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = f388xa1df5c61     // Catch:{ NoSuchFieldError -> 0x0033 }
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r1 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.GET_DEFAULT_INSTANCE     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                int[] r0 = f388xa1df5c61     // Catch:{ NoSuchFieldError -> 0x003e }
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r1 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.GET_PARSER     // Catch:{ NoSuchFieldError -> 0x003e }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x003e }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x003e }
            L_0x003e:
                int[] r0 = f388xa1df5c61     // Catch:{ NoSuchFieldError -> 0x0049 }
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r1 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.GET_MEMOIZED_IS_INITIALIZED     // Catch:{ NoSuchFieldError -> 0x0049 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0049 }
                r2 = 6
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0049 }
            L_0x0049:
                int[] r0 = f388xa1df5c61     // Catch:{ NoSuchFieldError -> 0x0054 }
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r1 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.SET_MEMOIZED_IS_INITIALIZED     // Catch:{ NoSuchFieldError -> 0x0054 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0054 }
                r2 = 7
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0054 }
            L_0x0054:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.firestore.p009v1.ListenResponse.C19831.<clinit>():void");
        }
    }

    /* access modifiers changed from: protected */
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        switch (C19831.f388xa1df5c61[methodToInvoke.ordinal()]) {
            case 1:
                return new ListenResponse();
            case 2:
                return new Builder((C19831) null);
            case 3:
                return newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0005\u0001\u0000\u0002\u0006\u0005\u0000\u0000\u0000\u0002<\u0000\u0003<\u0000\u0004<\u0000\u0005<\u0000\u0006<\u0000", new Object[]{"responseType_", "responseTypeCase_", TargetChange.class, DocumentChange.class, DocumentDelete.class, ExistenceFilter.class, DocumentRemove.class});
            case 4:
                return DEFAULT_INSTANCE;
            case 5:
                Parser<ListenResponse> parser = PARSER;
                if (parser == null) {
                    synchronized (ListenResponse.class) {
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
        ListenResponse listenResponse = new ListenResponse();
        DEFAULT_INSTANCE = listenResponse;
        GeneratedMessageLite.registerDefaultInstance(ListenResponse.class, listenResponse);
    }

    public static ListenResponse getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<ListenResponse> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}

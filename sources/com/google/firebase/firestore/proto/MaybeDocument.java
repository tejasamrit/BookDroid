package com.google.firebase.firestore.proto;

import com.google.firebase.firestore.proto.NoDocument;
import com.google.firebase.firestore.proto.UnknownDocument;
import com.google.firestore.p009v1.Document;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Parser;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

public final class MaybeDocument extends GeneratedMessageLite<MaybeDocument, Builder> implements MaybeDocumentOrBuilder {
    /* access modifiers changed from: private */
    public static final MaybeDocument DEFAULT_INSTANCE;
    public static final int DOCUMENT_FIELD_NUMBER = 2;
    public static final int HAS_COMMITTED_MUTATIONS_FIELD_NUMBER = 4;
    public static final int NO_DOCUMENT_FIELD_NUMBER = 1;
    private static volatile Parser<MaybeDocument> PARSER = null;
    public static final int UNKNOWN_DOCUMENT_FIELD_NUMBER = 3;
    private int documentTypeCase_ = 0;
    private Object documentType_;
    private boolean hasCommittedMutations_;

    private MaybeDocument() {
    }

    public enum DocumentTypeCase {
        NO_DOCUMENT(1),
        DOCUMENT(2),
        UNKNOWN_DOCUMENT(3),
        DOCUMENTTYPE_NOT_SET(0);
        
        private final int value;

        private DocumentTypeCase(int i) {
            this.value = i;
        }

        @Deprecated
        public static DocumentTypeCase valueOf(int i) {
            return forNumber(i);
        }

        public static DocumentTypeCase forNumber(int i) {
            if (i == 0) {
                return DOCUMENTTYPE_NOT_SET;
            }
            if (i == 1) {
                return NO_DOCUMENT;
            }
            if (i == 2) {
                return DOCUMENT;
            }
            if (i != 3) {
                return null;
            }
            return UNKNOWN_DOCUMENT;
        }

        public int getNumber() {
            return this.value;
        }
    }

    public DocumentTypeCase getDocumentTypeCase() {
        return DocumentTypeCase.forNumber(this.documentTypeCase_);
    }

    /* access modifiers changed from: private */
    public void clearDocumentType() {
        this.documentTypeCase_ = 0;
        this.documentType_ = null;
    }

    public boolean hasNoDocument() {
        return this.documentTypeCase_ == 1;
    }

    public NoDocument getNoDocument() {
        if (this.documentTypeCase_ == 1) {
            return (NoDocument) this.documentType_;
        }
        return NoDocument.getDefaultInstance();
    }

    /* access modifiers changed from: private */
    public void setNoDocument(NoDocument noDocument) {
        noDocument.getClass();
        this.documentType_ = noDocument;
        this.documentTypeCase_ = 1;
    }

    /* access modifiers changed from: private */
    public void mergeNoDocument(NoDocument noDocument) {
        noDocument.getClass();
        if (this.documentTypeCase_ != 1 || this.documentType_ == NoDocument.getDefaultInstance()) {
            this.documentType_ = noDocument;
        } else {
            this.documentType_ = ((NoDocument.Builder) NoDocument.newBuilder((NoDocument) this.documentType_).mergeFrom(noDocument)).buildPartial();
        }
        this.documentTypeCase_ = 1;
    }

    /* access modifiers changed from: private */
    public void clearNoDocument() {
        if (this.documentTypeCase_ == 1) {
            this.documentTypeCase_ = 0;
            this.documentType_ = null;
        }
    }

    public boolean hasDocument() {
        return this.documentTypeCase_ == 2;
    }

    public Document getDocument() {
        if (this.documentTypeCase_ == 2) {
            return (Document) this.documentType_;
        }
        return Document.getDefaultInstance();
    }

    /* access modifiers changed from: private */
    public void setDocument(Document document) {
        document.getClass();
        this.documentType_ = document;
        this.documentTypeCase_ = 2;
    }

    /* access modifiers changed from: private */
    public void mergeDocument(Document document) {
        document.getClass();
        if (this.documentTypeCase_ != 2 || this.documentType_ == Document.getDefaultInstance()) {
            this.documentType_ = document;
        } else {
            this.documentType_ = ((Document.Builder) Document.newBuilder((Document) this.documentType_).mergeFrom(document)).buildPartial();
        }
        this.documentTypeCase_ = 2;
    }

    /* access modifiers changed from: private */
    public void clearDocument() {
        if (this.documentTypeCase_ == 2) {
            this.documentTypeCase_ = 0;
            this.documentType_ = null;
        }
    }

    public boolean hasUnknownDocument() {
        return this.documentTypeCase_ == 3;
    }

    public UnknownDocument getUnknownDocument() {
        if (this.documentTypeCase_ == 3) {
            return (UnknownDocument) this.documentType_;
        }
        return UnknownDocument.getDefaultInstance();
    }

    /* access modifiers changed from: private */
    public void setUnknownDocument(UnknownDocument unknownDocument) {
        unknownDocument.getClass();
        this.documentType_ = unknownDocument;
        this.documentTypeCase_ = 3;
    }

    /* access modifiers changed from: private */
    public void mergeUnknownDocument(UnknownDocument unknownDocument) {
        unknownDocument.getClass();
        if (this.documentTypeCase_ != 3 || this.documentType_ == UnknownDocument.getDefaultInstance()) {
            this.documentType_ = unknownDocument;
        } else {
            this.documentType_ = ((UnknownDocument.Builder) UnknownDocument.newBuilder((UnknownDocument) this.documentType_).mergeFrom(unknownDocument)).buildPartial();
        }
        this.documentTypeCase_ = 3;
    }

    /* access modifiers changed from: private */
    public void clearUnknownDocument() {
        if (this.documentTypeCase_ == 3) {
            this.documentTypeCase_ = 0;
            this.documentType_ = null;
        }
    }

    public boolean getHasCommittedMutations() {
        return this.hasCommittedMutations_;
    }

    /* access modifiers changed from: private */
    public void setHasCommittedMutations(boolean z) {
        this.hasCommittedMutations_ = z;
    }

    /* access modifiers changed from: private */
    public void clearHasCommittedMutations() {
        this.hasCommittedMutations_ = false;
    }

    public static MaybeDocument parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return (MaybeDocument) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
    }

    public static MaybeDocument parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (MaybeDocument) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
    }

    public static MaybeDocument parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (MaybeDocument) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    public static MaybeDocument parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (MaybeDocument) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
    }

    public static MaybeDocument parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (MaybeDocument) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static MaybeDocument parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (MaybeDocument) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
    }

    public static MaybeDocument parseFrom(InputStream inputStream) throws IOException {
        return (MaybeDocument) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static MaybeDocument parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (MaybeDocument) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static MaybeDocument parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (MaybeDocument) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static MaybeDocument parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (MaybeDocument) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static MaybeDocument parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (MaybeDocument) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
    }

    public static MaybeDocument parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (MaybeDocument) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.createBuilder();
    }

    public static Builder newBuilder(MaybeDocument maybeDocument) {
        return (Builder) DEFAULT_INSTANCE.createBuilder(maybeDocument);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<MaybeDocument, Builder> implements MaybeDocumentOrBuilder {
        /* synthetic */ Builder(C18981 r1) {
            this();
        }

        private Builder() {
            super(MaybeDocument.DEFAULT_INSTANCE);
        }

        public DocumentTypeCase getDocumentTypeCase() {
            return ((MaybeDocument) this.instance).getDocumentTypeCase();
        }

        public Builder clearDocumentType() {
            copyOnWrite();
            ((MaybeDocument) this.instance).clearDocumentType();
            return this;
        }

        public boolean hasNoDocument() {
            return ((MaybeDocument) this.instance).hasNoDocument();
        }

        public NoDocument getNoDocument() {
            return ((MaybeDocument) this.instance).getNoDocument();
        }

        public Builder setNoDocument(NoDocument noDocument) {
            copyOnWrite();
            ((MaybeDocument) this.instance).setNoDocument(noDocument);
            return this;
        }

        public Builder setNoDocument(NoDocument.Builder builder) {
            copyOnWrite();
            ((MaybeDocument) this.instance).setNoDocument((NoDocument) builder.build());
            return this;
        }

        public Builder mergeNoDocument(NoDocument noDocument) {
            copyOnWrite();
            ((MaybeDocument) this.instance).mergeNoDocument(noDocument);
            return this;
        }

        public Builder clearNoDocument() {
            copyOnWrite();
            ((MaybeDocument) this.instance).clearNoDocument();
            return this;
        }

        public boolean hasDocument() {
            return ((MaybeDocument) this.instance).hasDocument();
        }

        public Document getDocument() {
            return ((MaybeDocument) this.instance).getDocument();
        }

        public Builder setDocument(Document document) {
            copyOnWrite();
            ((MaybeDocument) this.instance).setDocument(document);
            return this;
        }

        public Builder setDocument(Document.Builder builder) {
            copyOnWrite();
            ((MaybeDocument) this.instance).setDocument((Document) builder.build());
            return this;
        }

        public Builder mergeDocument(Document document) {
            copyOnWrite();
            ((MaybeDocument) this.instance).mergeDocument(document);
            return this;
        }

        public Builder clearDocument() {
            copyOnWrite();
            ((MaybeDocument) this.instance).clearDocument();
            return this;
        }

        public boolean hasUnknownDocument() {
            return ((MaybeDocument) this.instance).hasUnknownDocument();
        }

        public UnknownDocument getUnknownDocument() {
            return ((MaybeDocument) this.instance).getUnknownDocument();
        }

        public Builder setUnknownDocument(UnknownDocument unknownDocument) {
            copyOnWrite();
            ((MaybeDocument) this.instance).setUnknownDocument(unknownDocument);
            return this;
        }

        public Builder setUnknownDocument(UnknownDocument.Builder builder) {
            copyOnWrite();
            ((MaybeDocument) this.instance).setUnknownDocument((UnknownDocument) builder.build());
            return this;
        }

        public Builder mergeUnknownDocument(UnknownDocument unknownDocument) {
            copyOnWrite();
            ((MaybeDocument) this.instance).mergeUnknownDocument(unknownDocument);
            return this;
        }

        public Builder clearUnknownDocument() {
            copyOnWrite();
            ((MaybeDocument) this.instance).clearUnknownDocument();
            return this;
        }

        public boolean getHasCommittedMutations() {
            return ((MaybeDocument) this.instance).getHasCommittedMutations();
        }

        public Builder setHasCommittedMutations(boolean z) {
            copyOnWrite();
            ((MaybeDocument) this.instance).setHasCommittedMutations(z);
            return this;
        }

        public Builder clearHasCommittedMutations() {
            copyOnWrite();
            ((MaybeDocument) this.instance).clearHasCommittedMutations();
            return this;
        }
    }

    /* renamed from: com.google.firebase.firestore.proto.MaybeDocument$1 */
    static /* synthetic */ class C18981 {

        /* renamed from: $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke */
        static final /* synthetic */ int[] f342xa1df5c61;

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
                f342xa1df5c61 = r0
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r1 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.NEW_MUTABLE_INSTANCE     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = f342xa1df5c61     // Catch:{ NoSuchFieldError -> 0x001d }
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r1 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.NEW_BUILDER     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = f342xa1df5c61     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r1 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.BUILD_MESSAGE_INFO     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = f342xa1df5c61     // Catch:{ NoSuchFieldError -> 0x0033 }
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r1 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.GET_DEFAULT_INSTANCE     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                int[] r0 = f342xa1df5c61     // Catch:{ NoSuchFieldError -> 0x003e }
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r1 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.GET_PARSER     // Catch:{ NoSuchFieldError -> 0x003e }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x003e }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x003e }
            L_0x003e:
                int[] r0 = f342xa1df5c61     // Catch:{ NoSuchFieldError -> 0x0049 }
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r1 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.GET_MEMOIZED_IS_INITIALIZED     // Catch:{ NoSuchFieldError -> 0x0049 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0049 }
                r2 = 6
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0049 }
            L_0x0049:
                int[] r0 = f342xa1df5c61     // Catch:{ NoSuchFieldError -> 0x0054 }
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r1 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.SET_MEMOIZED_IS_INITIALIZED     // Catch:{ NoSuchFieldError -> 0x0054 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0054 }
                r2 = 7
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0054 }
            L_0x0054:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.firestore.proto.MaybeDocument.C18981.<clinit>():void");
        }
    }

    /* access modifiers changed from: protected */
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        switch (C18981.f342xa1df5c61[methodToInvoke.ordinal()]) {
            case 1:
                return new MaybeDocument();
            case 2:
                return new Builder((C18981) null);
            case 3:
                return newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0004\u0001\u0000\u0001\u0004\u0004\u0000\u0000\u0000\u0001<\u0000\u0002<\u0000\u0003<\u0000\u0004\u0007", new Object[]{"documentType_", "documentTypeCase_", NoDocument.class, Document.class, UnknownDocument.class, "hasCommittedMutations_"});
            case 4:
                return DEFAULT_INSTANCE;
            case 5:
                Parser<MaybeDocument> parser = PARSER;
                if (parser == null) {
                    synchronized (MaybeDocument.class) {
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
        MaybeDocument maybeDocument = new MaybeDocument();
        DEFAULT_INSTANCE = maybeDocument;
        GeneratedMessageLite.registerDefaultInstance(MaybeDocument.class, maybeDocument);
    }

    public static MaybeDocument getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<MaybeDocument> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}

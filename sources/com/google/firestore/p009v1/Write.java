package com.google.firestore.p009v1;

import com.google.firestore.p009v1.Document;
import com.google.firestore.p009v1.DocumentMask;
import com.google.firestore.p009v1.DocumentTransform;
import com.google.firestore.p009v1.Precondition;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Parser;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

/* renamed from: com.google.firestore.v1.Write */
public final class Write extends GeneratedMessageLite<Write, Builder> implements WriteOrBuilder {
    public static final int CURRENT_DOCUMENT_FIELD_NUMBER = 4;
    /* access modifiers changed from: private */
    public static final Write DEFAULT_INSTANCE;
    public static final int DELETE_FIELD_NUMBER = 2;
    private static volatile Parser<Write> PARSER = null;
    public static final int TRANSFORM_FIELD_NUMBER = 6;
    public static final int UPDATE_FIELD_NUMBER = 1;
    public static final int UPDATE_MASK_FIELD_NUMBER = 3;
    public static final int VERIFY_FIELD_NUMBER = 5;
    private Precondition currentDocument_;
    private int operationCase_ = 0;
    private Object operation_;
    private DocumentMask updateMask_;

    private Write() {
    }

    /* renamed from: com.google.firestore.v1.Write$OperationCase */
    public enum OperationCase {
        UPDATE(1),
        DELETE(2),
        VERIFY(5),
        TRANSFORM(6),
        OPERATION_NOT_SET(0);
        
        private final int value;

        private OperationCase(int i) {
            this.value = i;
        }

        @Deprecated
        public static OperationCase valueOf(int i) {
            return forNumber(i);
        }

        public static OperationCase forNumber(int i) {
            if (i == 0) {
                return OPERATION_NOT_SET;
            }
            if (i == 1) {
                return UPDATE;
            }
            if (i == 2) {
                return DELETE;
            }
            if (i == 5) {
                return VERIFY;
            }
            if (i != 6) {
                return null;
            }
            return TRANSFORM;
        }

        public int getNumber() {
            return this.value;
        }
    }

    public OperationCase getOperationCase() {
        return OperationCase.forNumber(this.operationCase_);
    }

    /* access modifiers changed from: private */
    public void clearOperation() {
        this.operationCase_ = 0;
        this.operation_ = null;
    }

    public boolean hasUpdate() {
        return this.operationCase_ == 1;
    }

    public Document getUpdate() {
        if (this.operationCase_ == 1) {
            return (Document) this.operation_;
        }
        return Document.getDefaultInstance();
    }

    /* access modifiers changed from: private */
    public void setUpdate(Document document) {
        document.getClass();
        this.operation_ = document;
        this.operationCase_ = 1;
    }

    /* access modifiers changed from: private */
    public void mergeUpdate(Document document) {
        document.getClass();
        if (this.operationCase_ != 1 || this.operation_ == Document.getDefaultInstance()) {
            this.operation_ = document;
        } else {
            this.operation_ = ((Document.Builder) Document.newBuilder((Document) this.operation_).mergeFrom(document)).buildPartial();
        }
        this.operationCase_ = 1;
    }

    /* access modifiers changed from: private */
    public void clearUpdate() {
        if (this.operationCase_ == 1) {
            this.operationCase_ = 0;
            this.operation_ = null;
        }
    }

    public String getDelete() {
        return this.operationCase_ == 2 ? (String) this.operation_ : "";
    }

    public ByteString getDeleteBytes() {
        return ByteString.copyFromUtf8(this.operationCase_ == 2 ? (String) this.operation_ : "");
    }

    /* access modifiers changed from: private */
    public void setDelete(String str) {
        str.getClass();
        this.operationCase_ = 2;
        this.operation_ = str;
    }

    /* access modifiers changed from: private */
    public void clearDelete() {
        if (this.operationCase_ == 2) {
            this.operationCase_ = 0;
            this.operation_ = null;
        }
    }

    /* access modifiers changed from: private */
    public void setDeleteBytes(ByteString byteString) {
        checkByteStringIsUtf8(byteString);
        this.operation_ = byteString.toStringUtf8();
        this.operationCase_ = 2;
    }

    public String getVerify() {
        return this.operationCase_ == 5 ? (String) this.operation_ : "";
    }

    public ByteString getVerifyBytes() {
        return ByteString.copyFromUtf8(this.operationCase_ == 5 ? (String) this.operation_ : "");
    }

    /* access modifiers changed from: private */
    public void setVerify(String str) {
        str.getClass();
        this.operationCase_ = 5;
        this.operation_ = str;
    }

    /* access modifiers changed from: private */
    public void clearVerify() {
        if (this.operationCase_ == 5) {
            this.operationCase_ = 0;
            this.operation_ = null;
        }
    }

    /* access modifiers changed from: private */
    public void setVerifyBytes(ByteString byteString) {
        checkByteStringIsUtf8(byteString);
        this.operation_ = byteString.toStringUtf8();
        this.operationCase_ = 5;
    }

    public boolean hasTransform() {
        return this.operationCase_ == 6;
    }

    public DocumentTransform getTransform() {
        if (this.operationCase_ == 6) {
            return (DocumentTransform) this.operation_;
        }
        return DocumentTransform.getDefaultInstance();
    }

    /* access modifiers changed from: private */
    public void setTransform(DocumentTransform documentTransform) {
        documentTransform.getClass();
        this.operation_ = documentTransform;
        this.operationCase_ = 6;
    }

    /* access modifiers changed from: private */
    public void mergeTransform(DocumentTransform documentTransform) {
        documentTransform.getClass();
        if (this.operationCase_ != 6 || this.operation_ == DocumentTransform.getDefaultInstance()) {
            this.operation_ = documentTransform;
        } else {
            this.operation_ = ((DocumentTransform.Builder) DocumentTransform.newBuilder((DocumentTransform) this.operation_).mergeFrom(documentTransform)).buildPartial();
        }
        this.operationCase_ = 6;
    }

    /* access modifiers changed from: private */
    public void clearTransform() {
        if (this.operationCase_ == 6) {
            this.operationCase_ = 0;
            this.operation_ = null;
        }
    }

    public boolean hasUpdateMask() {
        return this.updateMask_ != null;
    }

    public DocumentMask getUpdateMask() {
        DocumentMask documentMask = this.updateMask_;
        return documentMask == null ? DocumentMask.getDefaultInstance() : documentMask;
    }

    /* access modifiers changed from: private */
    public void setUpdateMask(DocumentMask documentMask) {
        documentMask.getClass();
        this.updateMask_ = documentMask;
    }

    /* access modifiers changed from: private */
    public void mergeUpdateMask(DocumentMask documentMask) {
        documentMask.getClass();
        DocumentMask documentMask2 = this.updateMask_;
        if (documentMask2 == null || documentMask2 == DocumentMask.getDefaultInstance()) {
            this.updateMask_ = documentMask;
        } else {
            this.updateMask_ = (DocumentMask) ((DocumentMask.Builder) DocumentMask.newBuilder(this.updateMask_).mergeFrom(documentMask)).buildPartial();
        }
    }

    /* access modifiers changed from: private */
    public void clearUpdateMask() {
        this.updateMask_ = null;
    }

    public boolean hasCurrentDocument() {
        return this.currentDocument_ != null;
    }

    public Precondition getCurrentDocument() {
        Precondition precondition = this.currentDocument_;
        return precondition == null ? Precondition.getDefaultInstance() : precondition;
    }

    /* access modifiers changed from: private */
    public void setCurrentDocument(Precondition precondition) {
        precondition.getClass();
        this.currentDocument_ = precondition;
    }

    /* access modifiers changed from: private */
    public void mergeCurrentDocument(Precondition precondition) {
        precondition.getClass();
        Precondition precondition2 = this.currentDocument_;
        if (precondition2 == null || precondition2 == Precondition.getDefaultInstance()) {
            this.currentDocument_ = precondition;
        } else {
            this.currentDocument_ = (Precondition) ((Precondition.Builder) Precondition.newBuilder(this.currentDocument_).mergeFrom(precondition)).buildPartial();
        }
    }

    /* access modifiers changed from: private */
    public void clearCurrentDocument() {
        this.currentDocument_ = null;
    }

    public static Write parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return (Write) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
    }

    public static Write parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (Write) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
    }

    public static Write parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (Write) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    public static Write parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (Write) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
    }

    public static Write parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (Write) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static Write parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (Write) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
    }

    public static Write parseFrom(InputStream inputStream) throws IOException {
        return (Write) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static Write parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (Write) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static Write parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (Write) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static Write parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (Write) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static Write parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (Write) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
    }

    public static Write parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (Write) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.createBuilder();
    }

    public static Builder newBuilder(Write write) {
        return (Builder) DEFAULT_INSTANCE.createBuilder(write);
    }

    /* renamed from: com.google.firestore.v1.Write$Builder */
    public static final class Builder extends GeneratedMessageLite.Builder<Write, Builder> implements WriteOrBuilder {
        /* synthetic */ Builder(C20001 r1) {
            this();
        }

        private Builder() {
            super(Write.DEFAULT_INSTANCE);
        }

        public OperationCase getOperationCase() {
            return ((Write) this.instance).getOperationCase();
        }

        public Builder clearOperation() {
            copyOnWrite();
            ((Write) this.instance).clearOperation();
            return this;
        }

        public boolean hasUpdate() {
            return ((Write) this.instance).hasUpdate();
        }

        public Document getUpdate() {
            return ((Write) this.instance).getUpdate();
        }

        public Builder setUpdate(Document document) {
            copyOnWrite();
            ((Write) this.instance).setUpdate(document);
            return this;
        }

        public Builder setUpdate(Document.Builder builder) {
            copyOnWrite();
            ((Write) this.instance).setUpdate((Document) builder.build());
            return this;
        }

        public Builder mergeUpdate(Document document) {
            copyOnWrite();
            ((Write) this.instance).mergeUpdate(document);
            return this;
        }

        public Builder clearUpdate() {
            copyOnWrite();
            ((Write) this.instance).clearUpdate();
            return this;
        }

        public String getDelete() {
            return ((Write) this.instance).getDelete();
        }

        public ByteString getDeleteBytes() {
            return ((Write) this.instance).getDeleteBytes();
        }

        public Builder setDelete(String str) {
            copyOnWrite();
            ((Write) this.instance).setDelete(str);
            return this;
        }

        public Builder clearDelete() {
            copyOnWrite();
            ((Write) this.instance).clearDelete();
            return this;
        }

        public Builder setDeleteBytes(ByteString byteString) {
            copyOnWrite();
            ((Write) this.instance).setDeleteBytes(byteString);
            return this;
        }

        public String getVerify() {
            return ((Write) this.instance).getVerify();
        }

        public ByteString getVerifyBytes() {
            return ((Write) this.instance).getVerifyBytes();
        }

        public Builder setVerify(String str) {
            copyOnWrite();
            ((Write) this.instance).setVerify(str);
            return this;
        }

        public Builder clearVerify() {
            copyOnWrite();
            ((Write) this.instance).clearVerify();
            return this;
        }

        public Builder setVerifyBytes(ByteString byteString) {
            copyOnWrite();
            ((Write) this.instance).setVerifyBytes(byteString);
            return this;
        }

        public boolean hasTransform() {
            return ((Write) this.instance).hasTransform();
        }

        public DocumentTransform getTransform() {
            return ((Write) this.instance).getTransform();
        }

        public Builder setTransform(DocumentTransform documentTransform) {
            copyOnWrite();
            ((Write) this.instance).setTransform(documentTransform);
            return this;
        }

        public Builder setTransform(DocumentTransform.Builder builder) {
            copyOnWrite();
            ((Write) this.instance).setTransform((DocumentTransform) builder.build());
            return this;
        }

        public Builder mergeTransform(DocumentTransform documentTransform) {
            copyOnWrite();
            ((Write) this.instance).mergeTransform(documentTransform);
            return this;
        }

        public Builder clearTransform() {
            copyOnWrite();
            ((Write) this.instance).clearTransform();
            return this;
        }

        public boolean hasUpdateMask() {
            return ((Write) this.instance).hasUpdateMask();
        }

        public DocumentMask getUpdateMask() {
            return ((Write) this.instance).getUpdateMask();
        }

        public Builder setUpdateMask(DocumentMask documentMask) {
            copyOnWrite();
            ((Write) this.instance).setUpdateMask(documentMask);
            return this;
        }

        public Builder setUpdateMask(DocumentMask.Builder builder) {
            copyOnWrite();
            ((Write) this.instance).setUpdateMask((DocumentMask) builder.build());
            return this;
        }

        public Builder mergeUpdateMask(DocumentMask documentMask) {
            copyOnWrite();
            ((Write) this.instance).mergeUpdateMask(documentMask);
            return this;
        }

        public Builder clearUpdateMask() {
            copyOnWrite();
            ((Write) this.instance).clearUpdateMask();
            return this;
        }

        public boolean hasCurrentDocument() {
            return ((Write) this.instance).hasCurrentDocument();
        }

        public Precondition getCurrentDocument() {
            return ((Write) this.instance).getCurrentDocument();
        }

        public Builder setCurrentDocument(Precondition precondition) {
            copyOnWrite();
            ((Write) this.instance).setCurrentDocument(precondition);
            return this;
        }

        public Builder setCurrentDocument(Precondition.Builder builder) {
            copyOnWrite();
            ((Write) this.instance).setCurrentDocument((Precondition) builder.build());
            return this;
        }

        public Builder mergeCurrentDocument(Precondition precondition) {
            copyOnWrite();
            ((Write) this.instance).mergeCurrentDocument(precondition);
            return this;
        }

        public Builder clearCurrentDocument() {
            copyOnWrite();
            ((Write) this.instance).clearCurrentDocument();
            return this;
        }
    }

    /* renamed from: com.google.firestore.v1.Write$1 */
    static /* synthetic */ class C20001 {

        /* renamed from: $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke */
        static final /* synthetic */ int[] f401xa1df5c61;

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
                f401xa1df5c61 = r0
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r1 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.NEW_MUTABLE_INSTANCE     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = f401xa1df5c61     // Catch:{ NoSuchFieldError -> 0x001d }
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r1 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.NEW_BUILDER     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = f401xa1df5c61     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r1 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.BUILD_MESSAGE_INFO     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = f401xa1df5c61     // Catch:{ NoSuchFieldError -> 0x0033 }
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r1 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.GET_DEFAULT_INSTANCE     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                int[] r0 = f401xa1df5c61     // Catch:{ NoSuchFieldError -> 0x003e }
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r1 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.GET_PARSER     // Catch:{ NoSuchFieldError -> 0x003e }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x003e }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x003e }
            L_0x003e:
                int[] r0 = f401xa1df5c61     // Catch:{ NoSuchFieldError -> 0x0049 }
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r1 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.GET_MEMOIZED_IS_INITIALIZED     // Catch:{ NoSuchFieldError -> 0x0049 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0049 }
                r2 = 6
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0049 }
            L_0x0049:
                int[] r0 = f401xa1df5c61     // Catch:{ NoSuchFieldError -> 0x0054 }
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r1 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.SET_MEMOIZED_IS_INITIALIZED     // Catch:{ NoSuchFieldError -> 0x0054 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0054 }
                r2 = 7
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0054 }
            L_0x0054:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.firestore.p009v1.Write.C20001.<clinit>():void");
        }
    }

    /* access modifiers changed from: protected */
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        switch (C20001.f401xa1df5c61[methodToInvoke.ordinal()]) {
            case 1:
                return new Write();
            case 2:
                return new Builder((C20001) null);
            case 3:
                return newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0006\u0001\u0000\u0001\u0006\u0006\u0000\u0000\u0000\u0001<\u0000\u0002Ȼ\u0000\u0003\t\u0004\t\u0005Ȼ\u0000\u0006<\u0000", new Object[]{"operation_", "operationCase_", Document.class, "updateMask_", "currentDocument_", DocumentTransform.class});
            case 4:
                return DEFAULT_INSTANCE;
            case 5:
                Parser<Write> parser = PARSER;
                if (parser == null) {
                    synchronized (Write.class) {
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
        Write write = new Write();
        DEFAULT_INSTANCE = write;
        GeneratedMessageLite.registerDefaultInstance(Write.class, write);
    }

    public static Write getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<Write> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}

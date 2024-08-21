package com.google.firestore.p009v1;

import com.google.firestore.p009v1.Document;
import com.google.protobuf.AbstractMessageLite;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Internal;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Parser;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.Collections;
import java.util.List;

/* renamed from: com.google.firestore.v1.ListDocumentsResponse */
public final class ListDocumentsResponse extends GeneratedMessageLite<ListDocumentsResponse, Builder> implements ListDocumentsResponseOrBuilder {
    /* access modifiers changed from: private */
    public static final ListDocumentsResponse DEFAULT_INSTANCE;
    public static final int DOCUMENTS_FIELD_NUMBER = 1;
    public static final int NEXT_PAGE_TOKEN_FIELD_NUMBER = 2;
    private static volatile Parser<ListDocumentsResponse> PARSER;
    private Internal.ProtobufList<Document> documents_ = emptyProtobufList();
    private String nextPageToken_ = "";

    private ListDocumentsResponse() {
    }

    public List<Document> getDocumentsList() {
        return this.documents_;
    }

    public List<? extends DocumentOrBuilder> getDocumentsOrBuilderList() {
        return this.documents_;
    }

    public int getDocumentsCount() {
        return this.documents_.size();
    }

    public Document getDocuments(int i) {
        return (Document) this.documents_.get(i);
    }

    public DocumentOrBuilder getDocumentsOrBuilder(int i) {
        return (DocumentOrBuilder) this.documents_.get(i);
    }

    private void ensureDocumentsIsMutable() {
        Internal.ProtobufList<Document> protobufList = this.documents_;
        if (!protobufList.isModifiable()) {
            this.documents_ = GeneratedMessageLite.mutableCopy(protobufList);
        }
    }

    /* access modifiers changed from: private */
    public void setDocuments(int i, Document document) {
        document.getClass();
        ensureDocumentsIsMutable();
        this.documents_.set(i, document);
    }

    /* access modifiers changed from: private */
    public void addDocuments(Document document) {
        document.getClass();
        ensureDocumentsIsMutable();
        this.documents_.add(document);
    }

    /* access modifiers changed from: private */
    public void addDocuments(int i, Document document) {
        document.getClass();
        ensureDocumentsIsMutable();
        this.documents_.add(i, document);
    }

    /* access modifiers changed from: private */
    public void addAllDocuments(Iterable<? extends Document> iterable) {
        ensureDocumentsIsMutable();
        AbstractMessageLite.addAll(iterable, this.documents_);
    }

    /* access modifiers changed from: private */
    public void clearDocuments() {
        this.documents_ = emptyProtobufList();
    }

    /* access modifiers changed from: private */
    public void removeDocuments(int i) {
        ensureDocumentsIsMutable();
        this.documents_.remove(i);
    }

    public String getNextPageToken() {
        return this.nextPageToken_;
    }

    public ByteString getNextPageTokenBytes() {
        return ByteString.copyFromUtf8(this.nextPageToken_);
    }

    /* access modifiers changed from: private */
    public void setNextPageToken(String str) {
        str.getClass();
        this.nextPageToken_ = str;
    }

    /* access modifiers changed from: private */
    public void clearNextPageToken() {
        this.nextPageToken_ = getDefaultInstance().getNextPageToken();
    }

    /* access modifiers changed from: private */
    public void setNextPageTokenBytes(ByteString byteString) {
        checkByteStringIsUtf8(byteString);
        this.nextPageToken_ = byteString.toStringUtf8();
    }

    public static ListDocumentsResponse parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return (ListDocumentsResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
    }

    public static ListDocumentsResponse parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (ListDocumentsResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
    }

    public static ListDocumentsResponse parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (ListDocumentsResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    public static ListDocumentsResponse parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (ListDocumentsResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
    }

    public static ListDocumentsResponse parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (ListDocumentsResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static ListDocumentsResponse parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (ListDocumentsResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
    }

    public static ListDocumentsResponse parseFrom(InputStream inputStream) throws IOException {
        return (ListDocumentsResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static ListDocumentsResponse parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (ListDocumentsResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static ListDocumentsResponse parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (ListDocumentsResponse) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static ListDocumentsResponse parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (ListDocumentsResponse) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static ListDocumentsResponse parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (ListDocumentsResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
    }

    public static ListDocumentsResponse parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (ListDocumentsResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.createBuilder();
    }

    public static Builder newBuilder(ListDocumentsResponse listDocumentsResponse) {
        return (Builder) DEFAULT_INSTANCE.createBuilder(listDocumentsResponse);
    }

    /* renamed from: com.google.firestore.v1.ListDocumentsResponse$Builder */
    public static final class Builder extends GeneratedMessageLite.Builder<ListDocumentsResponse, Builder> implements ListDocumentsResponseOrBuilder {
        /* synthetic */ Builder(C19811 r1) {
            this();
        }

        private Builder() {
            super(ListDocumentsResponse.DEFAULT_INSTANCE);
        }

        public List<Document> getDocumentsList() {
            return Collections.unmodifiableList(((ListDocumentsResponse) this.instance).getDocumentsList());
        }

        public int getDocumentsCount() {
            return ((ListDocumentsResponse) this.instance).getDocumentsCount();
        }

        public Document getDocuments(int i) {
            return ((ListDocumentsResponse) this.instance).getDocuments(i);
        }

        public Builder setDocuments(int i, Document document) {
            copyOnWrite();
            ((ListDocumentsResponse) this.instance).setDocuments(i, document);
            return this;
        }

        public Builder setDocuments(int i, Document.Builder builder) {
            copyOnWrite();
            ((ListDocumentsResponse) this.instance).setDocuments(i, (Document) builder.build());
            return this;
        }

        public Builder addDocuments(Document document) {
            copyOnWrite();
            ((ListDocumentsResponse) this.instance).addDocuments(document);
            return this;
        }

        public Builder addDocuments(int i, Document document) {
            copyOnWrite();
            ((ListDocumentsResponse) this.instance).addDocuments(i, document);
            return this;
        }

        public Builder addDocuments(Document.Builder builder) {
            copyOnWrite();
            ((ListDocumentsResponse) this.instance).addDocuments((Document) builder.build());
            return this;
        }

        public Builder addDocuments(int i, Document.Builder builder) {
            copyOnWrite();
            ((ListDocumentsResponse) this.instance).addDocuments(i, (Document) builder.build());
            return this;
        }

        public Builder addAllDocuments(Iterable<? extends Document> iterable) {
            copyOnWrite();
            ((ListDocumentsResponse) this.instance).addAllDocuments(iterable);
            return this;
        }

        public Builder clearDocuments() {
            copyOnWrite();
            ((ListDocumentsResponse) this.instance).clearDocuments();
            return this;
        }

        public Builder removeDocuments(int i) {
            copyOnWrite();
            ((ListDocumentsResponse) this.instance).removeDocuments(i);
            return this;
        }

        public String getNextPageToken() {
            return ((ListDocumentsResponse) this.instance).getNextPageToken();
        }

        public ByteString getNextPageTokenBytes() {
            return ((ListDocumentsResponse) this.instance).getNextPageTokenBytes();
        }

        public Builder setNextPageToken(String str) {
            copyOnWrite();
            ((ListDocumentsResponse) this.instance).setNextPageToken(str);
            return this;
        }

        public Builder clearNextPageToken() {
            copyOnWrite();
            ((ListDocumentsResponse) this.instance).clearNextPageToken();
            return this;
        }

        public Builder setNextPageTokenBytes(ByteString byteString) {
            copyOnWrite();
            ((ListDocumentsResponse) this.instance).setNextPageTokenBytes(byteString);
            return this;
        }
    }

    /* renamed from: com.google.firestore.v1.ListDocumentsResponse$1 */
    static /* synthetic */ class C19811 {

        /* renamed from: $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke */
        static final /* synthetic */ int[] f386xa1df5c61;

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
                f386xa1df5c61 = r0
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r1 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.NEW_MUTABLE_INSTANCE     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = f386xa1df5c61     // Catch:{ NoSuchFieldError -> 0x001d }
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r1 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.NEW_BUILDER     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = f386xa1df5c61     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r1 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.BUILD_MESSAGE_INFO     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = f386xa1df5c61     // Catch:{ NoSuchFieldError -> 0x0033 }
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r1 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.GET_DEFAULT_INSTANCE     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                int[] r0 = f386xa1df5c61     // Catch:{ NoSuchFieldError -> 0x003e }
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r1 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.GET_PARSER     // Catch:{ NoSuchFieldError -> 0x003e }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x003e }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x003e }
            L_0x003e:
                int[] r0 = f386xa1df5c61     // Catch:{ NoSuchFieldError -> 0x0049 }
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r1 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.GET_MEMOIZED_IS_INITIALIZED     // Catch:{ NoSuchFieldError -> 0x0049 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0049 }
                r2 = 6
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0049 }
            L_0x0049:
                int[] r0 = f386xa1df5c61     // Catch:{ NoSuchFieldError -> 0x0054 }
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r1 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.SET_MEMOIZED_IS_INITIALIZED     // Catch:{ NoSuchFieldError -> 0x0054 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0054 }
                r2 = 7
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0054 }
            L_0x0054:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.firestore.p009v1.ListDocumentsResponse.C19811.<clinit>():void");
        }
    }

    /* access modifiers changed from: protected */
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        switch (C19811.f386xa1df5c61[methodToInvoke.ordinal()]) {
            case 1:
                return new ListDocumentsResponse();
            case 2:
                return new Builder((C19811) null);
            case 3:
                return newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0001\u0000\u0001\u001b\u0002Ȉ", new Object[]{"documents_", Document.class, "nextPageToken_"});
            case 4:
                return DEFAULT_INSTANCE;
            case 5:
                Parser<ListDocumentsResponse> parser = PARSER;
                if (parser == null) {
                    synchronized (ListDocumentsResponse.class) {
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
        ListDocumentsResponse listDocumentsResponse = new ListDocumentsResponse();
        DEFAULT_INSTANCE = listDocumentsResponse;
        GeneratedMessageLite.registerDefaultInstance(ListDocumentsResponse.class, listDocumentsResponse);
    }

    public static ListDocumentsResponse getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<ListDocumentsResponse> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}

package com.google.firestore.p009v1;

import com.google.firestore.p009v1.WriteResult;
import com.google.protobuf.AbstractMessageLite;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Internal;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Parser;
import com.google.protobuf.Timestamp;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.Collections;
import java.util.List;

/* renamed from: com.google.firestore.v1.WriteResponse */
public final class WriteResponse extends GeneratedMessageLite<WriteResponse, Builder> implements WriteResponseOrBuilder {
    public static final int COMMIT_TIME_FIELD_NUMBER = 4;
    /* access modifiers changed from: private */
    public static final WriteResponse DEFAULT_INSTANCE;
    private static volatile Parser<WriteResponse> PARSER = null;
    public static final int STREAM_ID_FIELD_NUMBER = 1;
    public static final int STREAM_TOKEN_FIELD_NUMBER = 2;
    public static final int WRITE_RESULTS_FIELD_NUMBER = 3;
    private Timestamp commitTime_;
    private String streamId_ = "";
    private ByteString streamToken_ = ByteString.EMPTY;
    private Internal.ProtobufList<WriteResult> writeResults_ = emptyProtobufList();

    private WriteResponse() {
    }

    public String getStreamId() {
        return this.streamId_;
    }

    public ByteString getStreamIdBytes() {
        return ByteString.copyFromUtf8(this.streamId_);
    }

    /* access modifiers changed from: private */
    public void setStreamId(String str) {
        str.getClass();
        this.streamId_ = str;
    }

    /* access modifiers changed from: private */
    public void clearStreamId() {
        this.streamId_ = getDefaultInstance().getStreamId();
    }

    /* access modifiers changed from: private */
    public void setStreamIdBytes(ByteString byteString) {
        checkByteStringIsUtf8(byteString);
        this.streamId_ = byteString.toStringUtf8();
    }

    public ByteString getStreamToken() {
        return this.streamToken_;
    }

    /* access modifiers changed from: private */
    public void setStreamToken(ByteString byteString) {
        byteString.getClass();
        this.streamToken_ = byteString;
    }

    /* access modifiers changed from: private */
    public void clearStreamToken() {
        this.streamToken_ = getDefaultInstance().getStreamToken();
    }

    public List<WriteResult> getWriteResultsList() {
        return this.writeResults_;
    }

    public List<? extends WriteResultOrBuilder> getWriteResultsOrBuilderList() {
        return this.writeResults_;
    }

    public int getWriteResultsCount() {
        return this.writeResults_.size();
    }

    public WriteResult getWriteResults(int i) {
        return (WriteResult) this.writeResults_.get(i);
    }

    public WriteResultOrBuilder getWriteResultsOrBuilder(int i) {
        return (WriteResultOrBuilder) this.writeResults_.get(i);
    }

    private void ensureWriteResultsIsMutable() {
        Internal.ProtobufList<WriteResult> protobufList = this.writeResults_;
        if (!protobufList.isModifiable()) {
            this.writeResults_ = GeneratedMessageLite.mutableCopy(protobufList);
        }
    }

    /* access modifiers changed from: private */
    public void setWriteResults(int i, WriteResult writeResult) {
        writeResult.getClass();
        ensureWriteResultsIsMutable();
        this.writeResults_.set(i, writeResult);
    }

    /* access modifiers changed from: private */
    public void addWriteResults(WriteResult writeResult) {
        writeResult.getClass();
        ensureWriteResultsIsMutable();
        this.writeResults_.add(writeResult);
    }

    /* access modifiers changed from: private */
    public void addWriteResults(int i, WriteResult writeResult) {
        writeResult.getClass();
        ensureWriteResultsIsMutable();
        this.writeResults_.add(i, writeResult);
    }

    /* access modifiers changed from: private */
    public void addAllWriteResults(Iterable<? extends WriteResult> iterable) {
        ensureWriteResultsIsMutable();
        AbstractMessageLite.addAll(iterable, this.writeResults_);
    }

    /* access modifiers changed from: private */
    public void clearWriteResults() {
        this.writeResults_ = emptyProtobufList();
    }

    /* access modifiers changed from: private */
    public void removeWriteResults(int i) {
        ensureWriteResultsIsMutable();
        this.writeResults_.remove(i);
    }

    public boolean hasCommitTime() {
        return this.commitTime_ != null;
    }

    public Timestamp getCommitTime() {
        Timestamp timestamp = this.commitTime_;
        return timestamp == null ? Timestamp.getDefaultInstance() : timestamp;
    }

    /* access modifiers changed from: private */
    public void setCommitTime(Timestamp timestamp) {
        timestamp.getClass();
        this.commitTime_ = timestamp;
    }

    /* access modifiers changed from: private */
    public void mergeCommitTime(Timestamp timestamp) {
        timestamp.getClass();
        Timestamp timestamp2 = this.commitTime_;
        if (timestamp2 == null || timestamp2 == Timestamp.getDefaultInstance()) {
            this.commitTime_ = timestamp;
        } else {
            this.commitTime_ = (Timestamp) ((Timestamp.Builder) Timestamp.newBuilder(this.commitTime_).mergeFrom(timestamp)).buildPartial();
        }
    }

    /* access modifiers changed from: private */
    public void clearCommitTime() {
        this.commitTime_ = null;
    }

    public static WriteResponse parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return (WriteResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
    }

    public static WriteResponse parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (WriteResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
    }

    public static WriteResponse parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (WriteResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    public static WriteResponse parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (WriteResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
    }

    public static WriteResponse parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (WriteResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static WriteResponse parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (WriteResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
    }

    public static WriteResponse parseFrom(InputStream inputStream) throws IOException {
        return (WriteResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static WriteResponse parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (WriteResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static WriteResponse parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (WriteResponse) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static WriteResponse parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (WriteResponse) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static WriteResponse parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (WriteResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
    }

    public static WriteResponse parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (WriteResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.createBuilder();
    }

    public static Builder newBuilder(WriteResponse writeResponse) {
        return (Builder) DEFAULT_INSTANCE.createBuilder(writeResponse);
    }

    /* renamed from: com.google.firestore.v1.WriteResponse$Builder */
    public static final class Builder extends GeneratedMessageLite.Builder<WriteResponse, Builder> implements WriteResponseOrBuilder {
        /* synthetic */ Builder(C20021 r1) {
            this();
        }

        private Builder() {
            super(WriteResponse.DEFAULT_INSTANCE);
        }

        public String getStreamId() {
            return ((WriteResponse) this.instance).getStreamId();
        }

        public ByteString getStreamIdBytes() {
            return ((WriteResponse) this.instance).getStreamIdBytes();
        }

        public Builder setStreamId(String str) {
            copyOnWrite();
            ((WriteResponse) this.instance).setStreamId(str);
            return this;
        }

        public Builder clearStreamId() {
            copyOnWrite();
            ((WriteResponse) this.instance).clearStreamId();
            return this;
        }

        public Builder setStreamIdBytes(ByteString byteString) {
            copyOnWrite();
            ((WriteResponse) this.instance).setStreamIdBytes(byteString);
            return this;
        }

        public ByteString getStreamToken() {
            return ((WriteResponse) this.instance).getStreamToken();
        }

        public Builder setStreamToken(ByteString byteString) {
            copyOnWrite();
            ((WriteResponse) this.instance).setStreamToken(byteString);
            return this;
        }

        public Builder clearStreamToken() {
            copyOnWrite();
            ((WriteResponse) this.instance).clearStreamToken();
            return this;
        }

        public List<WriteResult> getWriteResultsList() {
            return Collections.unmodifiableList(((WriteResponse) this.instance).getWriteResultsList());
        }

        public int getWriteResultsCount() {
            return ((WriteResponse) this.instance).getWriteResultsCount();
        }

        public WriteResult getWriteResults(int i) {
            return ((WriteResponse) this.instance).getWriteResults(i);
        }

        public Builder setWriteResults(int i, WriteResult writeResult) {
            copyOnWrite();
            ((WriteResponse) this.instance).setWriteResults(i, writeResult);
            return this;
        }

        public Builder setWriteResults(int i, WriteResult.Builder builder) {
            copyOnWrite();
            ((WriteResponse) this.instance).setWriteResults(i, (WriteResult) builder.build());
            return this;
        }

        public Builder addWriteResults(WriteResult writeResult) {
            copyOnWrite();
            ((WriteResponse) this.instance).addWriteResults(writeResult);
            return this;
        }

        public Builder addWriteResults(int i, WriteResult writeResult) {
            copyOnWrite();
            ((WriteResponse) this.instance).addWriteResults(i, writeResult);
            return this;
        }

        public Builder addWriteResults(WriteResult.Builder builder) {
            copyOnWrite();
            ((WriteResponse) this.instance).addWriteResults((WriteResult) builder.build());
            return this;
        }

        public Builder addWriteResults(int i, WriteResult.Builder builder) {
            copyOnWrite();
            ((WriteResponse) this.instance).addWriteResults(i, (WriteResult) builder.build());
            return this;
        }

        public Builder addAllWriteResults(Iterable<? extends WriteResult> iterable) {
            copyOnWrite();
            ((WriteResponse) this.instance).addAllWriteResults(iterable);
            return this;
        }

        public Builder clearWriteResults() {
            copyOnWrite();
            ((WriteResponse) this.instance).clearWriteResults();
            return this;
        }

        public Builder removeWriteResults(int i) {
            copyOnWrite();
            ((WriteResponse) this.instance).removeWriteResults(i);
            return this;
        }

        public boolean hasCommitTime() {
            return ((WriteResponse) this.instance).hasCommitTime();
        }

        public Timestamp getCommitTime() {
            return ((WriteResponse) this.instance).getCommitTime();
        }

        public Builder setCommitTime(Timestamp timestamp) {
            copyOnWrite();
            ((WriteResponse) this.instance).setCommitTime(timestamp);
            return this;
        }

        public Builder setCommitTime(Timestamp.Builder builder) {
            copyOnWrite();
            ((WriteResponse) this.instance).setCommitTime((Timestamp) builder.build());
            return this;
        }

        public Builder mergeCommitTime(Timestamp timestamp) {
            copyOnWrite();
            ((WriteResponse) this.instance).mergeCommitTime(timestamp);
            return this;
        }

        public Builder clearCommitTime() {
            copyOnWrite();
            ((WriteResponse) this.instance).clearCommitTime();
            return this;
        }
    }

    /* renamed from: com.google.firestore.v1.WriteResponse$1 */
    static /* synthetic */ class C20021 {

        /* renamed from: $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke */
        static final /* synthetic */ int[] f403xa1df5c61;

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
                f403xa1df5c61 = r0
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r1 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.NEW_MUTABLE_INSTANCE     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = f403xa1df5c61     // Catch:{ NoSuchFieldError -> 0x001d }
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r1 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.NEW_BUILDER     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = f403xa1df5c61     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r1 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.BUILD_MESSAGE_INFO     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = f403xa1df5c61     // Catch:{ NoSuchFieldError -> 0x0033 }
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r1 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.GET_DEFAULT_INSTANCE     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                int[] r0 = f403xa1df5c61     // Catch:{ NoSuchFieldError -> 0x003e }
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r1 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.GET_PARSER     // Catch:{ NoSuchFieldError -> 0x003e }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x003e }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x003e }
            L_0x003e:
                int[] r0 = f403xa1df5c61     // Catch:{ NoSuchFieldError -> 0x0049 }
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r1 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.GET_MEMOIZED_IS_INITIALIZED     // Catch:{ NoSuchFieldError -> 0x0049 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0049 }
                r2 = 6
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0049 }
            L_0x0049:
                int[] r0 = f403xa1df5c61     // Catch:{ NoSuchFieldError -> 0x0054 }
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r1 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.SET_MEMOIZED_IS_INITIALIZED     // Catch:{ NoSuchFieldError -> 0x0054 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0054 }
                r2 = 7
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0054 }
            L_0x0054:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.firestore.p009v1.WriteResponse.C20021.<clinit>():void");
        }
    }

    /* access modifiers changed from: protected */
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        switch (C20021.f403xa1df5c61[methodToInvoke.ordinal()]) {
            case 1:
                return new WriteResponse();
            case 2:
                return new Builder((C20021) null);
            case 3:
                return newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0004\u0000\u0000\u0001\u0004\u0004\u0000\u0001\u0000\u0001Ȉ\u0002\n\u0003\u001b\u0004\t", new Object[]{"streamId_", "streamToken_", "writeResults_", WriteResult.class, "commitTime_"});
            case 4:
                return DEFAULT_INSTANCE;
            case 5:
                Parser<WriteResponse> parser = PARSER;
                if (parser == null) {
                    synchronized (WriteResponse.class) {
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
        WriteResponse writeResponse = new WriteResponse();
        DEFAULT_INSTANCE = writeResponse;
        GeneratedMessageLite.registerDefaultInstance(WriteResponse.class, writeResponse);
    }

    public static WriteResponse getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<WriteResponse> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}

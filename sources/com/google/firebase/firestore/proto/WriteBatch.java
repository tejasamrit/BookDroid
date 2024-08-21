package com.google.firebase.firestore.proto;

import com.google.firestore.p009v1.Write;
import com.google.firestore.p009v1.WriteOrBuilder;
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

public final class WriteBatch extends GeneratedMessageLite<WriteBatch, Builder> implements WriteBatchOrBuilder {
    public static final int BASE_WRITES_FIELD_NUMBER = 4;
    public static final int BATCH_ID_FIELD_NUMBER = 1;
    /* access modifiers changed from: private */
    public static final WriteBatch DEFAULT_INSTANCE;
    public static final int LOCAL_WRITE_TIME_FIELD_NUMBER = 3;
    private static volatile Parser<WriteBatch> PARSER = null;
    public static final int WRITES_FIELD_NUMBER = 2;
    private Internal.ProtobufList<Write> baseWrites_ = emptyProtobufList();
    private int batchId_;
    private Timestamp localWriteTime_;
    private Internal.ProtobufList<Write> writes_ = emptyProtobufList();

    private WriteBatch() {
    }

    public int getBatchId() {
        return this.batchId_;
    }

    /* access modifiers changed from: private */
    public void setBatchId(int i) {
        this.batchId_ = i;
    }

    /* access modifiers changed from: private */
    public void clearBatchId() {
        this.batchId_ = 0;
    }

    public List<Write> getWritesList() {
        return this.writes_;
    }

    public List<? extends WriteOrBuilder> getWritesOrBuilderList() {
        return this.writes_;
    }

    public int getWritesCount() {
        return this.writes_.size();
    }

    public Write getWrites(int i) {
        return (Write) this.writes_.get(i);
    }

    public WriteOrBuilder getWritesOrBuilder(int i) {
        return (WriteOrBuilder) this.writes_.get(i);
    }

    private void ensureWritesIsMutable() {
        Internal.ProtobufList<Write> protobufList = this.writes_;
        if (!protobufList.isModifiable()) {
            this.writes_ = GeneratedMessageLite.mutableCopy(protobufList);
        }
    }

    /* access modifiers changed from: private */
    public void setWrites(int i, Write write) {
        write.getClass();
        ensureWritesIsMutable();
        this.writes_.set(i, write);
    }

    /* access modifiers changed from: private */
    public void addWrites(Write write) {
        write.getClass();
        ensureWritesIsMutable();
        this.writes_.add(write);
    }

    /* access modifiers changed from: private */
    public void addWrites(int i, Write write) {
        write.getClass();
        ensureWritesIsMutable();
        this.writes_.add(i, write);
    }

    /* access modifiers changed from: private */
    public void addAllWrites(Iterable<? extends Write> iterable) {
        ensureWritesIsMutable();
        AbstractMessageLite.addAll(iterable, this.writes_);
    }

    /* access modifiers changed from: private */
    public void clearWrites() {
        this.writes_ = emptyProtobufList();
    }

    /* access modifiers changed from: private */
    public void removeWrites(int i) {
        ensureWritesIsMutable();
        this.writes_.remove(i);
    }

    public boolean hasLocalWriteTime() {
        return this.localWriteTime_ != null;
    }

    public Timestamp getLocalWriteTime() {
        Timestamp timestamp = this.localWriteTime_;
        return timestamp == null ? Timestamp.getDefaultInstance() : timestamp;
    }

    /* access modifiers changed from: private */
    public void setLocalWriteTime(Timestamp timestamp) {
        timestamp.getClass();
        this.localWriteTime_ = timestamp;
    }

    /* access modifiers changed from: private */
    public void mergeLocalWriteTime(Timestamp timestamp) {
        timestamp.getClass();
        Timestamp timestamp2 = this.localWriteTime_;
        if (timestamp2 == null || timestamp2 == Timestamp.getDefaultInstance()) {
            this.localWriteTime_ = timestamp;
        } else {
            this.localWriteTime_ = (Timestamp) ((Timestamp.Builder) Timestamp.newBuilder(this.localWriteTime_).mergeFrom(timestamp)).buildPartial();
        }
    }

    /* access modifiers changed from: private */
    public void clearLocalWriteTime() {
        this.localWriteTime_ = null;
    }

    public List<Write> getBaseWritesList() {
        return this.baseWrites_;
    }

    public List<? extends WriteOrBuilder> getBaseWritesOrBuilderList() {
        return this.baseWrites_;
    }

    public int getBaseWritesCount() {
        return this.baseWrites_.size();
    }

    public Write getBaseWrites(int i) {
        return (Write) this.baseWrites_.get(i);
    }

    public WriteOrBuilder getBaseWritesOrBuilder(int i) {
        return (WriteOrBuilder) this.baseWrites_.get(i);
    }

    private void ensureBaseWritesIsMutable() {
        Internal.ProtobufList<Write> protobufList = this.baseWrites_;
        if (!protobufList.isModifiable()) {
            this.baseWrites_ = GeneratedMessageLite.mutableCopy(protobufList);
        }
    }

    /* access modifiers changed from: private */
    public void setBaseWrites(int i, Write write) {
        write.getClass();
        ensureBaseWritesIsMutable();
        this.baseWrites_.set(i, write);
    }

    /* access modifiers changed from: private */
    public void addBaseWrites(Write write) {
        write.getClass();
        ensureBaseWritesIsMutable();
        this.baseWrites_.add(write);
    }

    /* access modifiers changed from: private */
    public void addBaseWrites(int i, Write write) {
        write.getClass();
        ensureBaseWritesIsMutable();
        this.baseWrites_.add(i, write);
    }

    /* access modifiers changed from: private */
    public void addAllBaseWrites(Iterable<? extends Write> iterable) {
        ensureBaseWritesIsMutable();
        AbstractMessageLite.addAll(iterable, this.baseWrites_);
    }

    /* access modifiers changed from: private */
    public void clearBaseWrites() {
        this.baseWrites_ = emptyProtobufList();
    }

    /* access modifiers changed from: private */
    public void removeBaseWrites(int i) {
        ensureBaseWritesIsMutable();
        this.baseWrites_.remove(i);
    }

    public static WriteBatch parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return (WriteBatch) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
    }

    public static WriteBatch parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (WriteBatch) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
    }

    public static WriteBatch parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (WriteBatch) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    public static WriteBatch parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (WriteBatch) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
    }

    public static WriteBatch parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (WriteBatch) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static WriteBatch parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (WriteBatch) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
    }

    public static WriteBatch parseFrom(InputStream inputStream) throws IOException {
        return (WriteBatch) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static WriteBatch parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (WriteBatch) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static WriteBatch parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (WriteBatch) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static WriteBatch parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (WriteBatch) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static WriteBatch parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (WriteBatch) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
    }

    public static WriteBatch parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (WriteBatch) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.createBuilder();
    }

    public static Builder newBuilder(WriteBatch writeBatch) {
        return (Builder) DEFAULT_INSTANCE.createBuilder(writeBatch);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<WriteBatch, Builder> implements WriteBatchOrBuilder {
        /* synthetic */ Builder(C19041 r1) {
            this();
        }

        private Builder() {
            super(WriteBatch.DEFAULT_INSTANCE);
        }

        public int getBatchId() {
            return ((WriteBatch) this.instance).getBatchId();
        }

        public Builder setBatchId(int i) {
            copyOnWrite();
            ((WriteBatch) this.instance).setBatchId(i);
            return this;
        }

        public Builder clearBatchId() {
            copyOnWrite();
            ((WriteBatch) this.instance).clearBatchId();
            return this;
        }

        public List<Write> getWritesList() {
            return Collections.unmodifiableList(((WriteBatch) this.instance).getWritesList());
        }

        public int getWritesCount() {
            return ((WriteBatch) this.instance).getWritesCount();
        }

        public Write getWrites(int i) {
            return ((WriteBatch) this.instance).getWrites(i);
        }

        public Builder setWrites(int i, Write write) {
            copyOnWrite();
            ((WriteBatch) this.instance).setWrites(i, write);
            return this;
        }

        public Builder setWrites(int i, Write.Builder builder) {
            copyOnWrite();
            ((WriteBatch) this.instance).setWrites(i, (Write) builder.build());
            return this;
        }

        public Builder addWrites(Write write) {
            copyOnWrite();
            ((WriteBatch) this.instance).addWrites(write);
            return this;
        }

        public Builder addWrites(int i, Write write) {
            copyOnWrite();
            ((WriteBatch) this.instance).addWrites(i, write);
            return this;
        }

        public Builder addWrites(Write.Builder builder) {
            copyOnWrite();
            ((WriteBatch) this.instance).addWrites((Write) builder.build());
            return this;
        }

        public Builder addWrites(int i, Write.Builder builder) {
            copyOnWrite();
            ((WriteBatch) this.instance).addWrites(i, (Write) builder.build());
            return this;
        }

        public Builder addAllWrites(Iterable<? extends Write> iterable) {
            copyOnWrite();
            ((WriteBatch) this.instance).addAllWrites(iterable);
            return this;
        }

        public Builder clearWrites() {
            copyOnWrite();
            ((WriteBatch) this.instance).clearWrites();
            return this;
        }

        public Builder removeWrites(int i) {
            copyOnWrite();
            ((WriteBatch) this.instance).removeWrites(i);
            return this;
        }

        public boolean hasLocalWriteTime() {
            return ((WriteBatch) this.instance).hasLocalWriteTime();
        }

        public Timestamp getLocalWriteTime() {
            return ((WriteBatch) this.instance).getLocalWriteTime();
        }

        public Builder setLocalWriteTime(Timestamp timestamp) {
            copyOnWrite();
            ((WriteBatch) this.instance).setLocalWriteTime(timestamp);
            return this;
        }

        public Builder setLocalWriteTime(Timestamp.Builder builder) {
            copyOnWrite();
            ((WriteBatch) this.instance).setLocalWriteTime((Timestamp) builder.build());
            return this;
        }

        public Builder mergeLocalWriteTime(Timestamp timestamp) {
            copyOnWrite();
            ((WriteBatch) this.instance).mergeLocalWriteTime(timestamp);
            return this;
        }

        public Builder clearLocalWriteTime() {
            copyOnWrite();
            ((WriteBatch) this.instance).clearLocalWriteTime();
            return this;
        }

        public List<Write> getBaseWritesList() {
            return Collections.unmodifiableList(((WriteBatch) this.instance).getBaseWritesList());
        }

        public int getBaseWritesCount() {
            return ((WriteBatch) this.instance).getBaseWritesCount();
        }

        public Write getBaseWrites(int i) {
            return ((WriteBatch) this.instance).getBaseWrites(i);
        }

        public Builder setBaseWrites(int i, Write write) {
            copyOnWrite();
            ((WriteBatch) this.instance).setBaseWrites(i, write);
            return this;
        }

        public Builder setBaseWrites(int i, Write.Builder builder) {
            copyOnWrite();
            ((WriteBatch) this.instance).setBaseWrites(i, (Write) builder.build());
            return this;
        }

        public Builder addBaseWrites(Write write) {
            copyOnWrite();
            ((WriteBatch) this.instance).addBaseWrites(write);
            return this;
        }

        public Builder addBaseWrites(int i, Write write) {
            copyOnWrite();
            ((WriteBatch) this.instance).addBaseWrites(i, write);
            return this;
        }

        public Builder addBaseWrites(Write.Builder builder) {
            copyOnWrite();
            ((WriteBatch) this.instance).addBaseWrites((Write) builder.build());
            return this;
        }

        public Builder addBaseWrites(int i, Write.Builder builder) {
            copyOnWrite();
            ((WriteBatch) this.instance).addBaseWrites(i, (Write) builder.build());
            return this;
        }

        public Builder addAllBaseWrites(Iterable<? extends Write> iterable) {
            copyOnWrite();
            ((WriteBatch) this.instance).addAllBaseWrites(iterable);
            return this;
        }

        public Builder clearBaseWrites() {
            copyOnWrite();
            ((WriteBatch) this.instance).clearBaseWrites();
            return this;
        }

        public Builder removeBaseWrites(int i) {
            copyOnWrite();
            ((WriteBatch) this.instance).removeBaseWrites(i);
            return this;
        }
    }

    /* renamed from: com.google.firebase.firestore.proto.WriteBatch$1 */
    static /* synthetic */ class C19041 {

        /* renamed from: $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke */
        static final /* synthetic */ int[] f348xa1df5c61;

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
                f348xa1df5c61 = r0
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r1 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.NEW_MUTABLE_INSTANCE     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = f348xa1df5c61     // Catch:{ NoSuchFieldError -> 0x001d }
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r1 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.NEW_BUILDER     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = f348xa1df5c61     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r1 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.BUILD_MESSAGE_INFO     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = f348xa1df5c61     // Catch:{ NoSuchFieldError -> 0x0033 }
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r1 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.GET_DEFAULT_INSTANCE     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                int[] r0 = f348xa1df5c61     // Catch:{ NoSuchFieldError -> 0x003e }
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r1 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.GET_PARSER     // Catch:{ NoSuchFieldError -> 0x003e }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x003e }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x003e }
            L_0x003e:
                int[] r0 = f348xa1df5c61     // Catch:{ NoSuchFieldError -> 0x0049 }
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r1 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.GET_MEMOIZED_IS_INITIALIZED     // Catch:{ NoSuchFieldError -> 0x0049 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0049 }
                r2 = 6
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0049 }
            L_0x0049:
                int[] r0 = f348xa1df5c61     // Catch:{ NoSuchFieldError -> 0x0054 }
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r1 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.SET_MEMOIZED_IS_INITIALIZED     // Catch:{ NoSuchFieldError -> 0x0054 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0054 }
                r2 = 7
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0054 }
            L_0x0054:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.firestore.proto.WriteBatch.C19041.<clinit>():void");
        }
    }

    /* access modifiers changed from: protected */
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        switch (C19041.f348xa1df5c61[methodToInvoke.ordinal()]) {
            case 1:
                return new WriteBatch();
            case 2:
                return new Builder((C19041) null);
            case 3:
                return newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0004\u0000\u0000\u0001\u0004\u0004\u0000\u0002\u0000\u0001\u0004\u0002\u001b\u0003\t\u0004\u001b", new Object[]{"batchId_", "writes_", Write.class, "localWriteTime_", "baseWrites_", Write.class});
            case 4:
                return DEFAULT_INSTANCE;
            case 5:
                Parser<WriteBatch> parser = PARSER;
                if (parser == null) {
                    synchronized (WriteBatch.class) {
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
        WriteBatch writeBatch = new WriteBatch();
        DEFAULT_INSTANCE = writeBatch;
        GeneratedMessageLite.registerDefaultInstance(WriteBatch.class, writeBatch);
    }

    public static WriteBatch getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<WriteBatch> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}

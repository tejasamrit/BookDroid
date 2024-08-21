package com.google.firestore.p009v1;

import com.google.firestore.p009v1.Value;
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

/* renamed from: com.google.firestore.v1.WriteResult */
public final class WriteResult extends GeneratedMessageLite<WriteResult, Builder> implements WriteResultOrBuilder {
    /* access modifiers changed from: private */
    public static final WriteResult DEFAULT_INSTANCE;
    private static volatile Parser<WriteResult> PARSER = null;
    public static final int TRANSFORM_RESULTS_FIELD_NUMBER = 2;
    public static final int UPDATE_TIME_FIELD_NUMBER = 1;
    private Internal.ProtobufList<Value> transformResults_ = emptyProtobufList();
    private Timestamp updateTime_;

    private WriteResult() {
    }

    public boolean hasUpdateTime() {
        return this.updateTime_ != null;
    }

    public Timestamp getUpdateTime() {
        Timestamp timestamp = this.updateTime_;
        return timestamp == null ? Timestamp.getDefaultInstance() : timestamp;
    }

    /* access modifiers changed from: private */
    public void setUpdateTime(Timestamp timestamp) {
        timestamp.getClass();
        this.updateTime_ = timestamp;
    }

    /* access modifiers changed from: private */
    public void mergeUpdateTime(Timestamp timestamp) {
        timestamp.getClass();
        Timestamp timestamp2 = this.updateTime_;
        if (timestamp2 == null || timestamp2 == Timestamp.getDefaultInstance()) {
            this.updateTime_ = timestamp;
        } else {
            this.updateTime_ = (Timestamp) ((Timestamp.Builder) Timestamp.newBuilder(this.updateTime_).mergeFrom(timestamp)).buildPartial();
        }
    }

    /* access modifiers changed from: private */
    public void clearUpdateTime() {
        this.updateTime_ = null;
    }

    public List<Value> getTransformResultsList() {
        return this.transformResults_;
    }

    public List<? extends ValueOrBuilder> getTransformResultsOrBuilderList() {
        return this.transformResults_;
    }

    public int getTransformResultsCount() {
        return this.transformResults_.size();
    }

    public Value getTransformResults(int i) {
        return (Value) this.transformResults_.get(i);
    }

    public ValueOrBuilder getTransformResultsOrBuilder(int i) {
        return (ValueOrBuilder) this.transformResults_.get(i);
    }

    private void ensureTransformResultsIsMutable() {
        Internal.ProtobufList<Value> protobufList = this.transformResults_;
        if (!protobufList.isModifiable()) {
            this.transformResults_ = GeneratedMessageLite.mutableCopy(protobufList);
        }
    }

    /* access modifiers changed from: private */
    public void setTransformResults(int i, Value value) {
        value.getClass();
        ensureTransformResultsIsMutable();
        this.transformResults_.set(i, value);
    }

    /* access modifiers changed from: private */
    public void addTransformResults(Value value) {
        value.getClass();
        ensureTransformResultsIsMutable();
        this.transformResults_.add(value);
    }

    /* access modifiers changed from: private */
    public void addTransformResults(int i, Value value) {
        value.getClass();
        ensureTransformResultsIsMutable();
        this.transformResults_.add(i, value);
    }

    /* access modifiers changed from: private */
    public void addAllTransformResults(Iterable<? extends Value> iterable) {
        ensureTransformResultsIsMutable();
        AbstractMessageLite.addAll(iterable, this.transformResults_);
    }

    /* access modifiers changed from: private */
    public void clearTransformResults() {
        this.transformResults_ = emptyProtobufList();
    }

    /* access modifiers changed from: private */
    public void removeTransformResults(int i) {
        ensureTransformResultsIsMutable();
        this.transformResults_.remove(i);
    }

    public static WriteResult parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return (WriteResult) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
    }

    public static WriteResult parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (WriteResult) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
    }

    public static WriteResult parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (WriteResult) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    public static WriteResult parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (WriteResult) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
    }

    public static WriteResult parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (WriteResult) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static WriteResult parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (WriteResult) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
    }

    public static WriteResult parseFrom(InputStream inputStream) throws IOException {
        return (WriteResult) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static WriteResult parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (WriteResult) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static WriteResult parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (WriteResult) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static WriteResult parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (WriteResult) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static WriteResult parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (WriteResult) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
    }

    public static WriteResult parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (WriteResult) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.createBuilder();
    }

    public static Builder newBuilder(WriteResult writeResult) {
        return (Builder) DEFAULT_INSTANCE.createBuilder(writeResult);
    }

    /* renamed from: com.google.firestore.v1.WriteResult$Builder */
    public static final class Builder extends GeneratedMessageLite.Builder<WriteResult, Builder> implements WriteResultOrBuilder {
        /* synthetic */ Builder(C20031 r1) {
            this();
        }

        private Builder() {
            super(WriteResult.DEFAULT_INSTANCE);
        }

        public boolean hasUpdateTime() {
            return ((WriteResult) this.instance).hasUpdateTime();
        }

        public Timestamp getUpdateTime() {
            return ((WriteResult) this.instance).getUpdateTime();
        }

        public Builder setUpdateTime(Timestamp timestamp) {
            copyOnWrite();
            ((WriteResult) this.instance).setUpdateTime(timestamp);
            return this;
        }

        public Builder setUpdateTime(Timestamp.Builder builder) {
            copyOnWrite();
            ((WriteResult) this.instance).setUpdateTime((Timestamp) builder.build());
            return this;
        }

        public Builder mergeUpdateTime(Timestamp timestamp) {
            copyOnWrite();
            ((WriteResult) this.instance).mergeUpdateTime(timestamp);
            return this;
        }

        public Builder clearUpdateTime() {
            copyOnWrite();
            ((WriteResult) this.instance).clearUpdateTime();
            return this;
        }

        public List<Value> getTransformResultsList() {
            return Collections.unmodifiableList(((WriteResult) this.instance).getTransformResultsList());
        }

        public int getTransformResultsCount() {
            return ((WriteResult) this.instance).getTransformResultsCount();
        }

        public Value getTransformResults(int i) {
            return ((WriteResult) this.instance).getTransformResults(i);
        }

        public Builder setTransformResults(int i, Value value) {
            copyOnWrite();
            ((WriteResult) this.instance).setTransformResults(i, value);
            return this;
        }

        public Builder setTransformResults(int i, Value.Builder builder) {
            copyOnWrite();
            ((WriteResult) this.instance).setTransformResults(i, (Value) builder.build());
            return this;
        }

        public Builder addTransformResults(Value value) {
            copyOnWrite();
            ((WriteResult) this.instance).addTransformResults(value);
            return this;
        }

        public Builder addTransformResults(int i, Value value) {
            copyOnWrite();
            ((WriteResult) this.instance).addTransformResults(i, value);
            return this;
        }

        public Builder addTransformResults(Value.Builder builder) {
            copyOnWrite();
            ((WriteResult) this.instance).addTransformResults((Value) builder.build());
            return this;
        }

        public Builder addTransformResults(int i, Value.Builder builder) {
            copyOnWrite();
            ((WriteResult) this.instance).addTransformResults(i, (Value) builder.build());
            return this;
        }

        public Builder addAllTransformResults(Iterable<? extends Value> iterable) {
            copyOnWrite();
            ((WriteResult) this.instance).addAllTransformResults(iterable);
            return this;
        }

        public Builder clearTransformResults() {
            copyOnWrite();
            ((WriteResult) this.instance).clearTransformResults();
            return this;
        }

        public Builder removeTransformResults(int i) {
            copyOnWrite();
            ((WriteResult) this.instance).removeTransformResults(i);
            return this;
        }
    }

    /* renamed from: com.google.firestore.v1.WriteResult$1 */
    static /* synthetic */ class C20031 {

        /* renamed from: $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke */
        static final /* synthetic */ int[] f404xa1df5c61;

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
                f404xa1df5c61 = r0
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r1 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.NEW_MUTABLE_INSTANCE     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = f404xa1df5c61     // Catch:{ NoSuchFieldError -> 0x001d }
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r1 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.NEW_BUILDER     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = f404xa1df5c61     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r1 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.BUILD_MESSAGE_INFO     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = f404xa1df5c61     // Catch:{ NoSuchFieldError -> 0x0033 }
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r1 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.GET_DEFAULT_INSTANCE     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                int[] r0 = f404xa1df5c61     // Catch:{ NoSuchFieldError -> 0x003e }
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r1 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.GET_PARSER     // Catch:{ NoSuchFieldError -> 0x003e }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x003e }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x003e }
            L_0x003e:
                int[] r0 = f404xa1df5c61     // Catch:{ NoSuchFieldError -> 0x0049 }
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r1 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.GET_MEMOIZED_IS_INITIALIZED     // Catch:{ NoSuchFieldError -> 0x0049 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0049 }
                r2 = 6
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0049 }
            L_0x0049:
                int[] r0 = f404xa1df5c61     // Catch:{ NoSuchFieldError -> 0x0054 }
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r1 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.SET_MEMOIZED_IS_INITIALIZED     // Catch:{ NoSuchFieldError -> 0x0054 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0054 }
                r2 = 7
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0054 }
            L_0x0054:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.firestore.p009v1.WriteResult.C20031.<clinit>():void");
        }
    }

    /* access modifiers changed from: protected */
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        switch (C20031.f404xa1df5c61[methodToInvoke.ordinal()]) {
            case 1:
                return new WriteResult();
            case 2:
                return new Builder((C20031) null);
            case 3:
                return newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0001\u0000\u0001\t\u0002\u001b", new Object[]{"updateTime_", "transformResults_", Value.class});
            case 4:
                return DEFAULT_INSTANCE;
            case 5:
                Parser<WriteResult> parser = PARSER;
                if (parser == null) {
                    synchronized (WriteResult.class) {
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
        WriteResult writeResult = new WriteResult();
        DEFAULT_INSTANCE = writeResult;
        GeneratedMessageLite.registerDefaultInstance(WriteResult.class, writeResult);
    }

    public static WriteResult getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<WriteResult> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}

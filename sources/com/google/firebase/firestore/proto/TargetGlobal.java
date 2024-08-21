package com.google.firebase.firestore.proto;

import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Parser;
import com.google.protobuf.Timestamp;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

public final class TargetGlobal extends GeneratedMessageLite<TargetGlobal, Builder> implements TargetGlobalOrBuilder {
    /* access modifiers changed from: private */
    public static final TargetGlobal DEFAULT_INSTANCE;
    public static final int HIGHEST_LISTEN_SEQUENCE_NUMBER_FIELD_NUMBER = 2;
    public static final int HIGHEST_TARGET_ID_FIELD_NUMBER = 1;
    public static final int LAST_REMOTE_SNAPSHOT_VERSION_FIELD_NUMBER = 3;
    private static volatile Parser<TargetGlobal> PARSER = null;
    public static final int TARGET_COUNT_FIELD_NUMBER = 4;
    private long highestListenSequenceNumber_;
    private int highestTargetId_;
    private Timestamp lastRemoteSnapshotVersion_;
    private int targetCount_;

    private TargetGlobal() {
    }

    public int getHighestTargetId() {
        return this.highestTargetId_;
    }

    /* access modifiers changed from: private */
    public void setHighestTargetId(int i) {
        this.highestTargetId_ = i;
    }

    /* access modifiers changed from: private */
    public void clearHighestTargetId() {
        this.highestTargetId_ = 0;
    }

    public long getHighestListenSequenceNumber() {
        return this.highestListenSequenceNumber_;
    }

    /* access modifiers changed from: private */
    public void setHighestListenSequenceNumber(long j) {
        this.highestListenSequenceNumber_ = j;
    }

    /* access modifiers changed from: private */
    public void clearHighestListenSequenceNumber() {
        this.highestListenSequenceNumber_ = 0;
    }

    public boolean hasLastRemoteSnapshotVersion() {
        return this.lastRemoteSnapshotVersion_ != null;
    }

    public Timestamp getLastRemoteSnapshotVersion() {
        Timestamp timestamp = this.lastRemoteSnapshotVersion_;
        return timestamp == null ? Timestamp.getDefaultInstance() : timestamp;
    }

    /* access modifiers changed from: private */
    public void setLastRemoteSnapshotVersion(Timestamp timestamp) {
        timestamp.getClass();
        this.lastRemoteSnapshotVersion_ = timestamp;
    }

    /* access modifiers changed from: private */
    public void mergeLastRemoteSnapshotVersion(Timestamp timestamp) {
        timestamp.getClass();
        Timestamp timestamp2 = this.lastRemoteSnapshotVersion_;
        if (timestamp2 == null || timestamp2 == Timestamp.getDefaultInstance()) {
            this.lastRemoteSnapshotVersion_ = timestamp;
        } else {
            this.lastRemoteSnapshotVersion_ = (Timestamp) ((Timestamp.Builder) Timestamp.newBuilder(this.lastRemoteSnapshotVersion_).mergeFrom(timestamp)).buildPartial();
        }
    }

    /* access modifiers changed from: private */
    public void clearLastRemoteSnapshotVersion() {
        this.lastRemoteSnapshotVersion_ = null;
    }

    public int getTargetCount() {
        return this.targetCount_;
    }

    /* access modifiers changed from: private */
    public void setTargetCount(int i) {
        this.targetCount_ = i;
    }

    /* access modifiers changed from: private */
    public void clearTargetCount() {
        this.targetCount_ = 0;
    }

    public static TargetGlobal parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return (TargetGlobal) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
    }

    public static TargetGlobal parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (TargetGlobal) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
    }

    public static TargetGlobal parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (TargetGlobal) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    public static TargetGlobal parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (TargetGlobal) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
    }

    public static TargetGlobal parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (TargetGlobal) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static TargetGlobal parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (TargetGlobal) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
    }

    public static TargetGlobal parseFrom(InputStream inputStream) throws IOException {
        return (TargetGlobal) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static TargetGlobal parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (TargetGlobal) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static TargetGlobal parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (TargetGlobal) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static TargetGlobal parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (TargetGlobal) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static TargetGlobal parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (TargetGlobal) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
    }

    public static TargetGlobal parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (TargetGlobal) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.createBuilder();
    }

    public static Builder newBuilder(TargetGlobal targetGlobal) {
        return (Builder) DEFAULT_INSTANCE.createBuilder(targetGlobal);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<TargetGlobal, Builder> implements TargetGlobalOrBuilder {
        /* synthetic */ Builder(C19021 r1) {
            this();
        }

        private Builder() {
            super(TargetGlobal.DEFAULT_INSTANCE);
        }

        public int getHighestTargetId() {
            return ((TargetGlobal) this.instance).getHighestTargetId();
        }

        public Builder setHighestTargetId(int i) {
            copyOnWrite();
            ((TargetGlobal) this.instance).setHighestTargetId(i);
            return this;
        }

        public Builder clearHighestTargetId() {
            copyOnWrite();
            ((TargetGlobal) this.instance).clearHighestTargetId();
            return this;
        }

        public long getHighestListenSequenceNumber() {
            return ((TargetGlobal) this.instance).getHighestListenSequenceNumber();
        }

        public Builder setHighestListenSequenceNumber(long j) {
            copyOnWrite();
            ((TargetGlobal) this.instance).setHighestListenSequenceNumber(j);
            return this;
        }

        public Builder clearHighestListenSequenceNumber() {
            copyOnWrite();
            ((TargetGlobal) this.instance).clearHighestListenSequenceNumber();
            return this;
        }

        public boolean hasLastRemoteSnapshotVersion() {
            return ((TargetGlobal) this.instance).hasLastRemoteSnapshotVersion();
        }

        public Timestamp getLastRemoteSnapshotVersion() {
            return ((TargetGlobal) this.instance).getLastRemoteSnapshotVersion();
        }

        public Builder setLastRemoteSnapshotVersion(Timestamp timestamp) {
            copyOnWrite();
            ((TargetGlobal) this.instance).setLastRemoteSnapshotVersion(timestamp);
            return this;
        }

        public Builder setLastRemoteSnapshotVersion(Timestamp.Builder builder) {
            copyOnWrite();
            ((TargetGlobal) this.instance).setLastRemoteSnapshotVersion((Timestamp) builder.build());
            return this;
        }

        public Builder mergeLastRemoteSnapshotVersion(Timestamp timestamp) {
            copyOnWrite();
            ((TargetGlobal) this.instance).mergeLastRemoteSnapshotVersion(timestamp);
            return this;
        }

        public Builder clearLastRemoteSnapshotVersion() {
            copyOnWrite();
            ((TargetGlobal) this.instance).clearLastRemoteSnapshotVersion();
            return this;
        }

        public int getTargetCount() {
            return ((TargetGlobal) this.instance).getTargetCount();
        }

        public Builder setTargetCount(int i) {
            copyOnWrite();
            ((TargetGlobal) this.instance).setTargetCount(i);
            return this;
        }

        public Builder clearTargetCount() {
            copyOnWrite();
            ((TargetGlobal) this.instance).clearTargetCount();
            return this;
        }
    }

    /* renamed from: com.google.firebase.firestore.proto.TargetGlobal$1 */
    static /* synthetic */ class C19021 {

        /* renamed from: $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke */
        static final /* synthetic */ int[] f346xa1df5c61;

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
                f346xa1df5c61 = r0
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r1 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.NEW_MUTABLE_INSTANCE     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = f346xa1df5c61     // Catch:{ NoSuchFieldError -> 0x001d }
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r1 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.NEW_BUILDER     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = f346xa1df5c61     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r1 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.BUILD_MESSAGE_INFO     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = f346xa1df5c61     // Catch:{ NoSuchFieldError -> 0x0033 }
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r1 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.GET_DEFAULT_INSTANCE     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                int[] r0 = f346xa1df5c61     // Catch:{ NoSuchFieldError -> 0x003e }
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r1 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.GET_PARSER     // Catch:{ NoSuchFieldError -> 0x003e }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x003e }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x003e }
            L_0x003e:
                int[] r0 = f346xa1df5c61     // Catch:{ NoSuchFieldError -> 0x0049 }
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r1 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.GET_MEMOIZED_IS_INITIALIZED     // Catch:{ NoSuchFieldError -> 0x0049 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0049 }
                r2 = 6
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0049 }
            L_0x0049:
                int[] r0 = f346xa1df5c61     // Catch:{ NoSuchFieldError -> 0x0054 }
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r1 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.SET_MEMOIZED_IS_INITIALIZED     // Catch:{ NoSuchFieldError -> 0x0054 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0054 }
                r2 = 7
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0054 }
            L_0x0054:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.firestore.proto.TargetGlobal.C19021.<clinit>():void");
        }
    }

    /* access modifiers changed from: protected */
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        switch (C19021.f346xa1df5c61[methodToInvoke.ordinal()]) {
            case 1:
                return new TargetGlobal();
            case 2:
                return new Builder((C19021) null);
            case 3:
                return newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0004\u0000\u0000\u0001\u0004\u0004\u0000\u0000\u0000\u0001\u0004\u0002\u0002\u0003\t\u0004\u0004", new Object[]{"highestTargetId_", "highestListenSequenceNumber_", "lastRemoteSnapshotVersion_", "targetCount_"});
            case 4:
                return DEFAULT_INSTANCE;
            case 5:
                Parser<TargetGlobal> parser = PARSER;
                if (parser == null) {
                    synchronized (TargetGlobal.class) {
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
        TargetGlobal targetGlobal = new TargetGlobal();
        DEFAULT_INSTANCE = targetGlobal;
        GeneratedMessageLite.registerDefaultInstance(TargetGlobal.class, targetGlobal);
    }

    public static TargetGlobal getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<TargetGlobal> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}

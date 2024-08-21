package com.google.firestore.p009v1;

import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Parser;
import com.google.protobuf.Timestamp;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

/* renamed from: com.google.firestore.v1.TransactionOptions */
public final class TransactionOptions extends GeneratedMessageLite<TransactionOptions, Builder> implements TransactionOptionsOrBuilder {
    /* access modifiers changed from: private */
    public static final TransactionOptions DEFAULT_INSTANCE;
    private static volatile Parser<TransactionOptions> PARSER = null;
    public static final int READ_ONLY_FIELD_NUMBER = 2;
    public static final int READ_WRITE_FIELD_NUMBER = 3;
    private int modeCase_ = 0;
    private Object mode_;

    /* renamed from: com.google.firestore.v1.TransactionOptions$ReadOnlyOrBuilder */
    public interface ReadOnlyOrBuilder extends MessageLiteOrBuilder {
        ReadOnly.ConsistencySelectorCase getConsistencySelectorCase();

        Timestamp getReadTime();

        boolean hasReadTime();
    }

    /* renamed from: com.google.firestore.v1.TransactionOptions$ReadWriteOrBuilder */
    public interface ReadWriteOrBuilder extends MessageLiteOrBuilder {
        ByteString getRetryTransaction();
    }

    private TransactionOptions() {
    }

    /* renamed from: com.google.firestore.v1.TransactionOptions$ReadWrite */
    public static final class ReadWrite extends GeneratedMessageLite<ReadWrite, Builder> implements ReadWriteOrBuilder {
        /* access modifiers changed from: private */
        public static final ReadWrite DEFAULT_INSTANCE;
        private static volatile Parser<ReadWrite> PARSER = null;
        public static final int RETRY_TRANSACTION_FIELD_NUMBER = 1;
        private ByteString retryTransaction_ = ByteString.EMPTY;

        private ReadWrite() {
        }

        public ByteString getRetryTransaction() {
            return this.retryTransaction_;
        }

        /* access modifiers changed from: private */
        public void setRetryTransaction(ByteString byteString) {
            byteString.getClass();
            this.retryTransaction_ = byteString;
        }

        /* access modifiers changed from: private */
        public void clearRetryTransaction() {
            this.retryTransaction_ = getDefaultInstance().getRetryTransaction();
        }

        public static ReadWrite parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (ReadWrite) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static ReadWrite parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (ReadWrite) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
        }

        public static ReadWrite parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (ReadWrite) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        public static ReadWrite parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (ReadWrite) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
        }

        public static ReadWrite parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (ReadWrite) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static ReadWrite parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (ReadWrite) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
        }

        public static ReadWrite parseFrom(InputStream inputStream) throws IOException {
            return (ReadWrite) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static ReadWrite parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (ReadWrite) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static ReadWrite parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (ReadWrite) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static ReadWrite parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (ReadWrite) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static ReadWrite parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (ReadWrite) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
        }

        public static ReadWrite parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (ReadWrite) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.createBuilder();
        }

        public static Builder newBuilder(ReadWrite readWrite) {
            return (Builder) DEFAULT_INSTANCE.createBuilder(readWrite);
        }

        /* renamed from: com.google.firestore.v1.TransactionOptions$ReadWrite$Builder */
        public static final class Builder extends GeneratedMessageLite.Builder<ReadWrite, Builder> implements ReadWriteOrBuilder {
            /* synthetic */ Builder(C19971 r1) {
                this();
            }

            private Builder() {
                super(ReadWrite.DEFAULT_INSTANCE);
            }

            public ByteString getRetryTransaction() {
                return ((ReadWrite) this.instance).getRetryTransaction();
            }

            public Builder setRetryTransaction(ByteString byteString) {
                copyOnWrite();
                ((ReadWrite) this.instance).setRetryTransaction(byteString);
                return this;
            }

            public Builder clearRetryTransaction() {
                copyOnWrite();
                ((ReadWrite) this.instance).clearRetryTransaction();
                return this;
            }
        }

        /* access modifiers changed from: protected */
        public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
            switch (C19971.f398xa1df5c61[methodToInvoke.ordinal()]) {
                case 1:
                    return new ReadWrite();
                case 2:
                    return new Builder((C19971) null);
                case 3:
                    return newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0001\u0000\u0000\u0001\u0001\u0001\u0000\u0000\u0000\u0001\n", new Object[]{"retryTransaction_"});
                case 4:
                    return DEFAULT_INSTANCE;
                case 5:
                    Parser<ReadWrite> parser = PARSER;
                    if (parser == null) {
                        synchronized (ReadWrite.class) {
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
            ReadWrite readWrite = new ReadWrite();
            DEFAULT_INSTANCE = readWrite;
            GeneratedMessageLite.registerDefaultInstance(ReadWrite.class, readWrite);
        }

        public static ReadWrite getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<ReadWrite> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    /* renamed from: com.google.firestore.v1.TransactionOptions$1 */
    static /* synthetic */ class C19971 {

        /* renamed from: $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke */
        static final /* synthetic */ int[] f398xa1df5c61;

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
                f398xa1df5c61 = r0
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r1 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.NEW_MUTABLE_INSTANCE     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = f398xa1df5c61     // Catch:{ NoSuchFieldError -> 0x001d }
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r1 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.NEW_BUILDER     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = f398xa1df5c61     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r1 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.BUILD_MESSAGE_INFO     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = f398xa1df5c61     // Catch:{ NoSuchFieldError -> 0x0033 }
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r1 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.GET_DEFAULT_INSTANCE     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                int[] r0 = f398xa1df5c61     // Catch:{ NoSuchFieldError -> 0x003e }
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r1 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.GET_PARSER     // Catch:{ NoSuchFieldError -> 0x003e }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x003e }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x003e }
            L_0x003e:
                int[] r0 = f398xa1df5c61     // Catch:{ NoSuchFieldError -> 0x0049 }
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r1 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.GET_MEMOIZED_IS_INITIALIZED     // Catch:{ NoSuchFieldError -> 0x0049 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0049 }
                r2 = 6
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0049 }
            L_0x0049:
                int[] r0 = f398xa1df5c61     // Catch:{ NoSuchFieldError -> 0x0054 }
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r1 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.SET_MEMOIZED_IS_INITIALIZED     // Catch:{ NoSuchFieldError -> 0x0054 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0054 }
                r2 = 7
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0054 }
            L_0x0054:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.firestore.p009v1.TransactionOptions.C19971.<clinit>():void");
        }
    }

    /* renamed from: com.google.firestore.v1.TransactionOptions$ReadOnly */
    public static final class ReadOnly extends GeneratedMessageLite<ReadOnly, Builder> implements ReadOnlyOrBuilder {
        /* access modifiers changed from: private */
        public static final ReadOnly DEFAULT_INSTANCE;
        private static volatile Parser<ReadOnly> PARSER = null;
        public static final int READ_TIME_FIELD_NUMBER = 2;
        private int consistencySelectorCase_ = 0;
        private Object consistencySelector_;

        private ReadOnly() {
        }

        /* renamed from: com.google.firestore.v1.TransactionOptions$ReadOnly$ConsistencySelectorCase */
        public enum ConsistencySelectorCase {
            READ_TIME(2),
            CONSISTENCYSELECTOR_NOT_SET(0);
            
            private final int value;

            private ConsistencySelectorCase(int i) {
                this.value = i;
            }

            @Deprecated
            public static ConsistencySelectorCase valueOf(int i) {
                return forNumber(i);
            }

            public static ConsistencySelectorCase forNumber(int i) {
                if (i == 0) {
                    return CONSISTENCYSELECTOR_NOT_SET;
                }
                if (i != 2) {
                    return null;
                }
                return READ_TIME;
            }

            public int getNumber() {
                return this.value;
            }
        }

        public ConsistencySelectorCase getConsistencySelectorCase() {
            return ConsistencySelectorCase.forNumber(this.consistencySelectorCase_);
        }

        /* access modifiers changed from: private */
        public void clearConsistencySelector() {
            this.consistencySelectorCase_ = 0;
            this.consistencySelector_ = null;
        }

        public boolean hasReadTime() {
            return this.consistencySelectorCase_ == 2;
        }

        public Timestamp getReadTime() {
            if (this.consistencySelectorCase_ == 2) {
                return (Timestamp) this.consistencySelector_;
            }
            return Timestamp.getDefaultInstance();
        }

        /* access modifiers changed from: private */
        public void setReadTime(Timestamp timestamp) {
            timestamp.getClass();
            this.consistencySelector_ = timestamp;
            this.consistencySelectorCase_ = 2;
        }

        /* access modifiers changed from: private */
        public void mergeReadTime(Timestamp timestamp) {
            timestamp.getClass();
            if (this.consistencySelectorCase_ != 2 || this.consistencySelector_ == Timestamp.getDefaultInstance()) {
                this.consistencySelector_ = timestamp;
            } else {
                this.consistencySelector_ = ((Timestamp.Builder) Timestamp.newBuilder((Timestamp) this.consistencySelector_).mergeFrom(timestamp)).buildPartial();
            }
            this.consistencySelectorCase_ = 2;
        }

        /* access modifiers changed from: private */
        public void clearReadTime() {
            if (this.consistencySelectorCase_ == 2) {
                this.consistencySelectorCase_ = 0;
                this.consistencySelector_ = null;
            }
        }

        public static ReadOnly parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (ReadOnly) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static ReadOnly parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (ReadOnly) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
        }

        public static ReadOnly parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (ReadOnly) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        public static ReadOnly parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (ReadOnly) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
        }

        public static ReadOnly parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (ReadOnly) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static ReadOnly parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (ReadOnly) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
        }

        public static ReadOnly parseFrom(InputStream inputStream) throws IOException {
            return (ReadOnly) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static ReadOnly parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (ReadOnly) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static ReadOnly parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (ReadOnly) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static ReadOnly parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (ReadOnly) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static ReadOnly parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (ReadOnly) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
        }

        public static ReadOnly parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (ReadOnly) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.createBuilder();
        }

        public static Builder newBuilder(ReadOnly readOnly) {
            return (Builder) DEFAULT_INSTANCE.createBuilder(readOnly);
        }

        /* renamed from: com.google.firestore.v1.TransactionOptions$ReadOnly$Builder */
        public static final class Builder extends GeneratedMessageLite.Builder<ReadOnly, Builder> implements ReadOnlyOrBuilder {
            /* synthetic */ Builder(C19971 r1) {
                this();
            }

            private Builder() {
                super(ReadOnly.DEFAULT_INSTANCE);
            }

            public ConsistencySelectorCase getConsistencySelectorCase() {
                return ((ReadOnly) this.instance).getConsistencySelectorCase();
            }

            public Builder clearConsistencySelector() {
                copyOnWrite();
                ((ReadOnly) this.instance).clearConsistencySelector();
                return this;
            }

            public boolean hasReadTime() {
                return ((ReadOnly) this.instance).hasReadTime();
            }

            public Timestamp getReadTime() {
                return ((ReadOnly) this.instance).getReadTime();
            }

            public Builder setReadTime(Timestamp timestamp) {
                copyOnWrite();
                ((ReadOnly) this.instance).setReadTime(timestamp);
                return this;
            }

            public Builder setReadTime(Timestamp.Builder builder) {
                copyOnWrite();
                ((ReadOnly) this.instance).setReadTime((Timestamp) builder.build());
                return this;
            }

            public Builder mergeReadTime(Timestamp timestamp) {
                copyOnWrite();
                ((ReadOnly) this.instance).mergeReadTime(timestamp);
                return this;
            }

            public Builder clearReadTime() {
                copyOnWrite();
                ((ReadOnly) this.instance).clearReadTime();
                return this;
            }
        }

        /* access modifiers changed from: protected */
        public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
            switch (C19971.f398xa1df5c61[methodToInvoke.ordinal()]) {
                case 1:
                    return new ReadOnly();
                case 2:
                    return new Builder((C19971) null);
                case 3:
                    return newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0001\u0001\u0000\u0002\u0002\u0001\u0000\u0000\u0000\u0002<\u0000", new Object[]{"consistencySelector_", "consistencySelectorCase_", Timestamp.class});
                case 4:
                    return DEFAULT_INSTANCE;
                case 5:
                    Parser<ReadOnly> parser = PARSER;
                    if (parser == null) {
                        synchronized (ReadOnly.class) {
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
            ReadOnly readOnly = new ReadOnly();
            DEFAULT_INSTANCE = readOnly;
            GeneratedMessageLite.registerDefaultInstance(ReadOnly.class, readOnly);
        }

        public static ReadOnly getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<ReadOnly> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    /* renamed from: com.google.firestore.v1.TransactionOptions$ModeCase */
    public enum ModeCase {
        READ_ONLY(2),
        READ_WRITE(3),
        MODE_NOT_SET(0);
        
        private final int value;

        private ModeCase(int i) {
            this.value = i;
        }

        @Deprecated
        public static ModeCase valueOf(int i) {
            return forNumber(i);
        }

        public static ModeCase forNumber(int i) {
            if (i == 0) {
                return MODE_NOT_SET;
            }
            if (i == 2) {
                return READ_ONLY;
            }
            if (i != 3) {
                return null;
            }
            return READ_WRITE;
        }

        public int getNumber() {
            return this.value;
        }
    }

    public ModeCase getModeCase() {
        return ModeCase.forNumber(this.modeCase_);
    }

    /* access modifiers changed from: private */
    public void clearMode() {
        this.modeCase_ = 0;
        this.mode_ = null;
    }

    public boolean hasReadOnly() {
        return this.modeCase_ == 2;
    }

    public ReadOnly getReadOnly() {
        if (this.modeCase_ == 2) {
            return (ReadOnly) this.mode_;
        }
        return ReadOnly.getDefaultInstance();
    }

    /* access modifiers changed from: private */
    public void setReadOnly(ReadOnly readOnly) {
        readOnly.getClass();
        this.mode_ = readOnly;
        this.modeCase_ = 2;
    }

    /* access modifiers changed from: private */
    public void mergeReadOnly(ReadOnly readOnly) {
        readOnly.getClass();
        if (this.modeCase_ != 2 || this.mode_ == ReadOnly.getDefaultInstance()) {
            this.mode_ = readOnly;
        } else {
            this.mode_ = ((ReadOnly.Builder) ReadOnly.newBuilder((ReadOnly) this.mode_).mergeFrom(readOnly)).buildPartial();
        }
        this.modeCase_ = 2;
    }

    /* access modifiers changed from: private */
    public void clearReadOnly() {
        if (this.modeCase_ == 2) {
            this.modeCase_ = 0;
            this.mode_ = null;
        }
    }

    public boolean hasReadWrite() {
        return this.modeCase_ == 3;
    }

    public ReadWrite getReadWrite() {
        if (this.modeCase_ == 3) {
            return (ReadWrite) this.mode_;
        }
        return ReadWrite.getDefaultInstance();
    }

    /* access modifiers changed from: private */
    public void setReadWrite(ReadWrite readWrite) {
        readWrite.getClass();
        this.mode_ = readWrite;
        this.modeCase_ = 3;
    }

    /* access modifiers changed from: private */
    public void mergeReadWrite(ReadWrite readWrite) {
        readWrite.getClass();
        if (this.modeCase_ != 3 || this.mode_ == ReadWrite.getDefaultInstance()) {
            this.mode_ = readWrite;
        } else {
            this.mode_ = ((ReadWrite.Builder) ReadWrite.newBuilder((ReadWrite) this.mode_).mergeFrom(readWrite)).buildPartial();
        }
        this.modeCase_ = 3;
    }

    /* access modifiers changed from: private */
    public void clearReadWrite() {
        if (this.modeCase_ == 3) {
            this.modeCase_ = 0;
            this.mode_ = null;
        }
    }

    public static TransactionOptions parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return (TransactionOptions) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
    }

    public static TransactionOptions parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (TransactionOptions) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
    }

    public static TransactionOptions parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (TransactionOptions) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    public static TransactionOptions parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (TransactionOptions) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
    }

    public static TransactionOptions parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (TransactionOptions) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static TransactionOptions parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (TransactionOptions) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
    }

    public static TransactionOptions parseFrom(InputStream inputStream) throws IOException {
        return (TransactionOptions) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static TransactionOptions parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (TransactionOptions) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static TransactionOptions parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (TransactionOptions) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static TransactionOptions parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (TransactionOptions) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static TransactionOptions parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (TransactionOptions) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
    }

    public static TransactionOptions parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (TransactionOptions) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.createBuilder();
    }

    public static Builder newBuilder(TransactionOptions transactionOptions) {
        return (Builder) DEFAULT_INSTANCE.createBuilder(transactionOptions);
    }

    /* renamed from: com.google.firestore.v1.TransactionOptions$Builder */
    public static final class Builder extends GeneratedMessageLite.Builder<TransactionOptions, Builder> implements TransactionOptionsOrBuilder {
        /* synthetic */ Builder(C19971 r1) {
            this();
        }

        private Builder() {
            super(TransactionOptions.DEFAULT_INSTANCE);
        }

        public ModeCase getModeCase() {
            return ((TransactionOptions) this.instance).getModeCase();
        }

        public Builder clearMode() {
            copyOnWrite();
            ((TransactionOptions) this.instance).clearMode();
            return this;
        }

        public boolean hasReadOnly() {
            return ((TransactionOptions) this.instance).hasReadOnly();
        }

        public ReadOnly getReadOnly() {
            return ((TransactionOptions) this.instance).getReadOnly();
        }

        public Builder setReadOnly(ReadOnly readOnly) {
            copyOnWrite();
            ((TransactionOptions) this.instance).setReadOnly(readOnly);
            return this;
        }

        public Builder setReadOnly(ReadOnly.Builder builder) {
            copyOnWrite();
            ((TransactionOptions) this.instance).setReadOnly((ReadOnly) builder.build());
            return this;
        }

        public Builder mergeReadOnly(ReadOnly readOnly) {
            copyOnWrite();
            ((TransactionOptions) this.instance).mergeReadOnly(readOnly);
            return this;
        }

        public Builder clearReadOnly() {
            copyOnWrite();
            ((TransactionOptions) this.instance).clearReadOnly();
            return this;
        }

        public boolean hasReadWrite() {
            return ((TransactionOptions) this.instance).hasReadWrite();
        }

        public ReadWrite getReadWrite() {
            return ((TransactionOptions) this.instance).getReadWrite();
        }

        public Builder setReadWrite(ReadWrite readWrite) {
            copyOnWrite();
            ((TransactionOptions) this.instance).setReadWrite(readWrite);
            return this;
        }

        public Builder setReadWrite(ReadWrite.Builder builder) {
            copyOnWrite();
            ((TransactionOptions) this.instance).setReadWrite((ReadWrite) builder.build());
            return this;
        }

        public Builder mergeReadWrite(ReadWrite readWrite) {
            copyOnWrite();
            ((TransactionOptions) this.instance).mergeReadWrite(readWrite);
            return this;
        }

        public Builder clearReadWrite() {
            copyOnWrite();
            ((TransactionOptions) this.instance).clearReadWrite();
            return this;
        }
    }

    /* access modifiers changed from: protected */
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        switch (C19971.f398xa1df5c61[methodToInvoke.ordinal()]) {
            case 1:
                return new TransactionOptions();
            case 2:
                return new Builder((C19971) null);
            case 3:
                return newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0002\u0001\u0000\u0002\u0003\u0002\u0000\u0000\u0000\u0002<\u0000\u0003<\u0000", new Object[]{"mode_", "modeCase_", ReadOnly.class, ReadWrite.class});
            case 4:
                return DEFAULT_INSTANCE;
            case 5:
                Parser<TransactionOptions> parser = PARSER;
                if (parser == null) {
                    synchronized (TransactionOptions.class) {
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
        TransactionOptions transactionOptions = new TransactionOptions();
        DEFAULT_INSTANCE = transactionOptions;
        GeneratedMessageLite.registerDefaultInstance(TransactionOptions.class, transactionOptions);
    }

    public static TransactionOptions getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<TransactionOptions> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}

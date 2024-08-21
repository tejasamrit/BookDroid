package com.google.api;

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

public final class BackendRule extends GeneratedMessageLite<BackendRule, Builder> implements BackendRuleOrBuilder {
    public static final int ADDRESS_FIELD_NUMBER = 2;
    public static final int DEADLINE_FIELD_NUMBER = 3;
    /* access modifiers changed from: private */
    public static final BackendRule DEFAULT_INSTANCE;
    public static final int DISABLE_AUTH_FIELD_NUMBER = 8;
    public static final int JWT_AUDIENCE_FIELD_NUMBER = 7;
    public static final int MIN_DEADLINE_FIELD_NUMBER = 4;
    public static final int OPERATION_DEADLINE_FIELD_NUMBER = 5;
    private static volatile Parser<BackendRule> PARSER = null;
    public static final int PATH_TRANSLATION_FIELD_NUMBER = 6;
    public static final int PROTOCOL_FIELD_NUMBER = 9;
    public static final int SELECTOR_FIELD_NUMBER = 1;
    private String address_ = "";
    private int authenticationCase_ = 0;
    private Object authentication_;
    private double deadline_;
    private double minDeadline_;
    private double operationDeadline_;
    private int pathTranslation_;
    private String protocol_ = "";
    private String selector_ = "";

    private BackendRule() {
    }

    public enum PathTranslation implements Internal.EnumLite {
        PATH_TRANSLATION_UNSPECIFIED(0),
        CONSTANT_ADDRESS(1),
        APPEND_PATH_TO_ADDRESS(2),
        UNRECOGNIZED(-1);
        
        public static final int APPEND_PATH_TO_ADDRESS_VALUE = 2;
        public static final int CONSTANT_ADDRESS_VALUE = 1;
        public static final int PATH_TRANSLATION_UNSPECIFIED_VALUE = 0;
        private static final Internal.EnumLiteMap<PathTranslation> internalValueMap = null;
        private final int value;

        static {
            internalValueMap = new Internal.EnumLiteMap<PathTranslation>() {
                public PathTranslation findValueByNumber(int i) {
                    return PathTranslation.forNumber(i);
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
        public static PathTranslation valueOf(int i) {
            return forNumber(i);
        }

        public static PathTranslation forNumber(int i) {
            if (i == 0) {
                return PATH_TRANSLATION_UNSPECIFIED;
            }
            if (i == 1) {
                return CONSTANT_ADDRESS;
            }
            if (i != 2) {
                return null;
            }
            return APPEND_PATH_TO_ADDRESS;
        }

        public static Internal.EnumLiteMap<PathTranslation> internalGetValueMap() {
            return internalValueMap;
        }

        public static Internal.EnumVerifier internalGetVerifier() {
            return PathTranslationVerifier.INSTANCE;
        }

        private static final class PathTranslationVerifier implements Internal.EnumVerifier {
            static final Internal.EnumVerifier INSTANCE = null;

            private PathTranslationVerifier() {
            }

            static {
                INSTANCE = new PathTranslationVerifier();
            }

            public boolean isInRange(int i) {
                return PathTranslation.forNumber(i) != null;
            }
        }

        private PathTranslation(int i) {
            this.value = i;
        }
    }

    public enum AuthenticationCase {
        JWT_AUDIENCE(7),
        DISABLE_AUTH(8),
        AUTHENTICATION_NOT_SET(0);
        
        private final int value;

        private AuthenticationCase(int i) {
            this.value = i;
        }

        @Deprecated
        public static AuthenticationCase valueOf(int i) {
            return forNumber(i);
        }

        public static AuthenticationCase forNumber(int i) {
            if (i == 0) {
                return AUTHENTICATION_NOT_SET;
            }
            if (i == 7) {
                return JWT_AUDIENCE;
            }
            if (i != 8) {
                return null;
            }
            return DISABLE_AUTH;
        }

        public int getNumber() {
            return this.value;
        }
    }

    public AuthenticationCase getAuthenticationCase() {
        return AuthenticationCase.forNumber(this.authenticationCase_);
    }

    /* access modifiers changed from: private */
    public void clearAuthentication() {
        this.authenticationCase_ = 0;
        this.authentication_ = null;
    }

    public String getSelector() {
        return this.selector_;
    }

    public ByteString getSelectorBytes() {
        return ByteString.copyFromUtf8(this.selector_);
    }

    /* access modifiers changed from: private */
    public void setSelector(String str) {
        str.getClass();
        this.selector_ = str;
    }

    /* access modifiers changed from: private */
    public void clearSelector() {
        this.selector_ = getDefaultInstance().getSelector();
    }

    /* access modifiers changed from: private */
    public void setSelectorBytes(ByteString byteString) {
        checkByteStringIsUtf8(byteString);
        this.selector_ = byteString.toStringUtf8();
    }

    public String getAddress() {
        return this.address_;
    }

    public ByteString getAddressBytes() {
        return ByteString.copyFromUtf8(this.address_);
    }

    /* access modifiers changed from: private */
    public void setAddress(String str) {
        str.getClass();
        this.address_ = str;
    }

    /* access modifiers changed from: private */
    public void clearAddress() {
        this.address_ = getDefaultInstance().getAddress();
    }

    /* access modifiers changed from: private */
    public void setAddressBytes(ByteString byteString) {
        checkByteStringIsUtf8(byteString);
        this.address_ = byteString.toStringUtf8();
    }

    public double getDeadline() {
        return this.deadline_;
    }

    /* access modifiers changed from: private */
    public void setDeadline(double d) {
        this.deadline_ = d;
    }

    /* access modifiers changed from: private */
    public void clearDeadline() {
        this.deadline_ = 0.0d;
    }

    public double getMinDeadline() {
        return this.minDeadline_;
    }

    /* access modifiers changed from: private */
    public void setMinDeadline(double d) {
        this.minDeadline_ = d;
    }

    /* access modifiers changed from: private */
    public void clearMinDeadline() {
        this.minDeadline_ = 0.0d;
    }

    public double getOperationDeadline() {
        return this.operationDeadline_;
    }

    /* access modifiers changed from: private */
    public void setOperationDeadline(double d) {
        this.operationDeadline_ = d;
    }

    /* access modifiers changed from: private */
    public void clearOperationDeadline() {
        this.operationDeadline_ = 0.0d;
    }

    public int getPathTranslationValue() {
        return this.pathTranslation_;
    }

    public PathTranslation getPathTranslation() {
        PathTranslation forNumber = PathTranslation.forNumber(this.pathTranslation_);
        return forNumber == null ? PathTranslation.UNRECOGNIZED : forNumber;
    }

    /* access modifiers changed from: private */
    public void setPathTranslationValue(int i) {
        this.pathTranslation_ = i;
    }

    /* access modifiers changed from: private */
    public void setPathTranslation(PathTranslation pathTranslation) {
        this.pathTranslation_ = pathTranslation.getNumber();
    }

    /* access modifiers changed from: private */
    public void clearPathTranslation() {
        this.pathTranslation_ = 0;
    }

    public String getJwtAudience() {
        return this.authenticationCase_ == 7 ? (String) this.authentication_ : "";
    }

    public ByteString getJwtAudienceBytes() {
        return ByteString.copyFromUtf8(this.authenticationCase_ == 7 ? (String) this.authentication_ : "");
    }

    /* access modifiers changed from: private */
    public void setJwtAudience(String str) {
        str.getClass();
        this.authenticationCase_ = 7;
        this.authentication_ = str;
    }

    /* access modifiers changed from: private */
    public void clearJwtAudience() {
        if (this.authenticationCase_ == 7) {
            this.authenticationCase_ = 0;
            this.authentication_ = null;
        }
    }

    /* access modifiers changed from: private */
    public void setJwtAudienceBytes(ByteString byteString) {
        checkByteStringIsUtf8(byteString);
        this.authentication_ = byteString.toStringUtf8();
        this.authenticationCase_ = 7;
    }

    public boolean getDisableAuth() {
        if (this.authenticationCase_ == 8) {
            return ((Boolean) this.authentication_).booleanValue();
        }
        return false;
    }

    /* access modifiers changed from: private */
    public void setDisableAuth(boolean z) {
        this.authenticationCase_ = 8;
        this.authentication_ = Boolean.valueOf(z);
    }

    /* access modifiers changed from: private */
    public void clearDisableAuth() {
        if (this.authenticationCase_ == 8) {
            this.authenticationCase_ = 0;
            this.authentication_ = null;
        }
    }

    public String getProtocol() {
        return this.protocol_;
    }

    public ByteString getProtocolBytes() {
        return ByteString.copyFromUtf8(this.protocol_);
    }

    /* access modifiers changed from: private */
    public void setProtocol(String str) {
        str.getClass();
        this.protocol_ = str;
    }

    /* access modifiers changed from: private */
    public void clearProtocol() {
        this.protocol_ = getDefaultInstance().getProtocol();
    }

    /* access modifiers changed from: private */
    public void setProtocolBytes(ByteString byteString) {
        checkByteStringIsUtf8(byteString);
        this.protocol_ = byteString.toStringUtf8();
    }

    public static BackendRule parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return (BackendRule) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
    }

    public static BackendRule parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (BackendRule) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
    }

    public static BackendRule parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (BackendRule) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    public static BackendRule parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (BackendRule) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
    }

    public static BackendRule parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (BackendRule) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static BackendRule parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (BackendRule) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
    }

    public static BackendRule parseFrom(InputStream inputStream) throws IOException {
        return (BackendRule) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static BackendRule parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (BackendRule) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static BackendRule parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (BackendRule) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static BackendRule parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (BackendRule) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static BackendRule parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (BackendRule) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
    }

    public static BackendRule parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (BackendRule) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.createBuilder();
    }

    public static Builder newBuilder(BackendRule backendRule) {
        return (Builder) DEFAULT_INSTANCE.createBuilder(backendRule);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<BackendRule, Builder> implements BackendRuleOrBuilder {
        /* synthetic */ Builder(C11351 r1) {
            this();
        }

        private Builder() {
            super(BackendRule.DEFAULT_INSTANCE);
        }

        public AuthenticationCase getAuthenticationCase() {
            return ((BackendRule) this.instance).getAuthenticationCase();
        }

        public Builder clearAuthentication() {
            copyOnWrite();
            ((BackendRule) this.instance).clearAuthentication();
            return this;
        }

        public String getSelector() {
            return ((BackendRule) this.instance).getSelector();
        }

        public ByteString getSelectorBytes() {
            return ((BackendRule) this.instance).getSelectorBytes();
        }

        public Builder setSelector(String str) {
            copyOnWrite();
            ((BackendRule) this.instance).setSelector(str);
            return this;
        }

        public Builder clearSelector() {
            copyOnWrite();
            ((BackendRule) this.instance).clearSelector();
            return this;
        }

        public Builder setSelectorBytes(ByteString byteString) {
            copyOnWrite();
            ((BackendRule) this.instance).setSelectorBytes(byteString);
            return this;
        }

        public String getAddress() {
            return ((BackendRule) this.instance).getAddress();
        }

        public ByteString getAddressBytes() {
            return ((BackendRule) this.instance).getAddressBytes();
        }

        public Builder setAddress(String str) {
            copyOnWrite();
            ((BackendRule) this.instance).setAddress(str);
            return this;
        }

        public Builder clearAddress() {
            copyOnWrite();
            ((BackendRule) this.instance).clearAddress();
            return this;
        }

        public Builder setAddressBytes(ByteString byteString) {
            copyOnWrite();
            ((BackendRule) this.instance).setAddressBytes(byteString);
            return this;
        }

        public double getDeadline() {
            return ((BackendRule) this.instance).getDeadline();
        }

        public Builder setDeadline(double d) {
            copyOnWrite();
            ((BackendRule) this.instance).setDeadline(d);
            return this;
        }

        public Builder clearDeadline() {
            copyOnWrite();
            ((BackendRule) this.instance).clearDeadline();
            return this;
        }

        public double getMinDeadline() {
            return ((BackendRule) this.instance).getMinDeadline();
        }

        public Builder setMinDeadline(double d) {
            copyOnWrite();
            ((BackendRule) this.instance).setMinDeadline(d);
            return this;
        }

        public Builder clearMinDeadline() {
            copyOnWrite();
            ((BackendRule) this.instance).clearMinDeadline();
            return this;
        }

        public double getOperationDeadline() {
            return ((BackendRule) this.instance).getOperationDeadline();
        }

        public Builder setOperationDeadline(double d) {
            copyOnWrite();
            ((BackendRule) this.instance).setOperationDeadline(d);
            return this;
        }

        public Builder clearOperationDeadline() {
            copyOnWrite();
            ((BackendRule) this.instance).clearOperationDeadline();
            return this;
        }

        public int getPathTranslationValue() {
            return ((BackendRule) this.instance).getPathTranslationValue();
        }

        public Builder setPathTranslationValue(int i) {
            copyOnWrite();
            ((BackendRule) this.instance).setPathTranslationValue(i);
            return this;
        }

        public PathTranslation getPathTranslation() {
            return ((BackendRule) this.instance).getPathTranslation();
        }

        public Builder setPathTranslation(PathTranslation pathTranslation) {
            copyOnWrite();
            ((BackendRule) this.instance).setPathTranslation(pathTranslation);
            return this;
        }

        public Builder clearPathTranslation() {
            copyOnWrite();
            ((BackendRule) this.instance).clearPathTranslation();
            return this;
        }

        public String getJwtAudience() {
            return ((BackendRule) this.instance).getJwtAudience();
        }

        public ByteString getJwtAudienceBytes() {
            return ((BackendRule) this.instance).getJwtAudienceBytes();
        }

        public Builder setJwtAudience(String str) {
            copyOnWrite();
            ((BackendRule) this.instance).setJwtAudience(str);
            return this;
        }

        public Builder clearJwtAudience() {
            copyOnWrite();
            ((BackendRule) this.instance).clearJwtAudience();
            return this;
        }

        public Builder setJwtAudienceBytes(ByteString byteString) {
            copyOnWrite();
            ((BackendRule) this.instance).setJwtAudienceBytes(byteString);
            return this;
        }

        public boolean getDisableAuth() {
            return ((BackendRule) this.instance).getDisableAuth();
        }

        public Builder setDisableAuth(boolean z) {
            copyOnWrite();
            ((BackendRule) this.instance).setDisableAuth(z);
            return this;
        }

        public Builder clearDisableAuth() {
            copyOnWrite();
            ((BackendRule) this.instance).clearDisableAuth();
            return this;
        }

        public String getProtocol() {
            return ((BackendRule) this.instance).getProtocol();
        }

        public ByteString getProtocolBytes() {
            return ((BackendRule) this.instance).getProtocolBytes();
        }

        public Builder setProtocol(String str) {
            copyOnWrite();
            ((BackendRule) this.instance).setProtocol(str);
            return this;
        }

        public Builder clearProtocol() {
            copyOnWrite();
            ((BackendRule) this.instance).clearProtocol();
            return this;
        }

        public Builder setProtocolBytes(ByteString byteString) {
            copyOnWrite();
            ((BackendRule) this.instance).setProtocolBytes(byteString);
            return this;
        }
    }

    /* renamed from: com.google.api.BackendRule$1 */
    static /* synthetic */ class C11351 {

        /* renamed from: $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke */
        static final /* synthetic */ int[] f178xa1df5c61;

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
                f178xa1df5c61 = r0
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r1 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.NEW_MUTABLE_INSTANCE     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = f178xa1df5c61     // Catch:{ NoSuchFieldError -> 0x001d }
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r1 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.NEW_BUILDER     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = f178xa1df5c61     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r1 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.BUILD_MESSAGE_INFO     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = f178xa1df5c61     // Catch:{ NoSuchFieldError -> 0x0033 }
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r1 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.GET_DEFAULT_INSTANCE     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                int[] r0 = f178xa1df5c61     // Catch:{ NoSuchFieldError -> 0x003e }
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r1 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.GET_PARSER     // Catch:{ NoSuchFieldError -> 0x003e }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x003e }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x003e }
            L_0x003e:
                int[] r0 = f178xa1df5c61     // Catch:{ NoSuchFieldError -> 0x0049 }
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r1 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.GET_MEMOIZED_IS_INITIALIZED     // Catch:{ NoSuchFieldError -> 0x0049 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0049 }
                r2 = 6
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0049 }
            L_0x0049:
                int[] r0 = f178xa1df5c61     // Catch:{ NoSuchFieldError -> 0x0054 }
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r1 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.SET_MEMOIZED_IS_INITIALIZED     // Catch:{ NoSuchFieldError -> 0x0054 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0054 }
                r2 = 7
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0054 }
            L_0x0054:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.api.BackendRule.C11351.<clinit>():void");
        }
    }

    /* access modifiers changed from: protected */
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        switch (C11351.f178xa1df5c61[methodToInvoke.ordinal()]) {
            case 1:
                return new BackendRule();
            case 2:
                return new Builder((C11351) null);
            case 3:
                return newMessageInfo(DEFAULT_INSTANCE, "\u0000\t\u0001\u0000\u0001\t\t\u0000\u0000\u0000\u0001Ȉ\u0002Ȉ\u0003\u0000\u0004\u0000\u0005\u0000\u0006\f\u0007Ȼ\u0000\b:\u0000\tȈ", new Object[]{"authentication_", "authenticationCase_", "selector_", "address_", "deadline_", "minDeadline_", "operationDeadline_", "pathTranslation_", "protocol_"});
            case 4:
                return DEFAULT_INSTANCE;
            case 5:
                Parser<BackendRule> parser = PARSER;
                if (parser == null) {
                    synchronized (BackendRule.class) {
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
        BackendRule backendRule = new BackendRule();
        DEFAULT_INSTANCE = backendRule;
        GeneratedMessageLite.registerDefaultInstance(BackendRule.class, backendRule);
    }

    public static BackendRule getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<BackendRule> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}

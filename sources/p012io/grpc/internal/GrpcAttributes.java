package p012io.grpc.internal;

import java.util.List;
import java.util.Map;
import p012io.grpc.Attributes;
import p012io.grpc.EquivalentAddressGroup;
import p012io.grpc.SecurityLevel;

/* renamed from: io.grpc.internal.GrpcAttributes */
public final class GrpcAttributes {
    public static final Attributes.Key<Attributes> ATTR_CLIENT_EAG_ATTRS = Attributes.Key.create("io.grpc.internal.GrpcAttributes.clientEagAttrs");
    @Deprecated
    public static final Attributes.Key<List<EquivalentAddressGroup>> ATTR_LB_ADDRS = Attributes.Key.create("io.grpc.grpclb.lbAddrs");
    @Deprecated
    public static final Attributes.Key<String> ATTR_LB_ADDR_AUTHORITY = Attributes.Key.create("io.grpc.grpclb.lbAddrAuthority");
    public static final Attributes.Key<Boolean> ATTR_LB_PROVIDED_BACKEND = Attributes.Key.create("io.grpc.grpclb.lbProvidedBackend");
    public static final Attributes.Key<SecurityLevel> ATTR_SECURITY_LEVEL = Attributes.Key.create("io.grpc.internal.GrpcAttributes.securityLevel");
    @Deprecated
    public static final Attributes.Key<Map<String, ?>> NAME_RESOLVER_SERVICE_CONFIG = Attributes.Key.create("service-config");

    private GrpcAttributes() {
    }
}

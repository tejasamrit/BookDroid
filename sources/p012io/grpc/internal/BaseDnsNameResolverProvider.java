package p012io.grpc.internal;

import com.google.common.base.Preconditions;
import com.google.common.base.Stopwatch;
import java.net.URI;
import p012io.grpc.InternalServiceProviders;
import p012io.grpc.NameResolver;
import p012io.grpc.NameResolverProvider;

/* renamed from: io.grpc.internal.BaseDnsNameResolverProvider */
public abstract class BaseDnsNameResolverProvider extends NameResolverProvider {
    public static final String ENABLE_GRPCLB_PROPERTY_NAME = "io.grpc.internal.DnsNameResolverProvider.enable_grpclb";
    private static final String SCHEME = "dns";

    public String getDefaultScheme() {
        return SCHEME;
    }

    /* access modifiers changed from: protected */
    public boolean isAvailable() {
        return true;
    }

    /* access modifiers changed from: protected */
    public abstract boolean isSrvEnabled();

    public DnsNameResolver newNameResolver(URI uri, NameResolver.Args args) {
        if (!SCHEME.equals(uri.getScheme())) {
            return null;
        }
        String str = (String) Preconditions.checkNotNull(uri.getPath(), "targetPath");
        Preconditions.checkArgument(str.startsWith("/"), "the path component (%s) of the target (%s) must start with '/'", (Object) str, (Object) uri);
        return new DnsNameResolver(uri.getAuthority(), str.substring(1), args, GrpcUtil.SHARED_CHANNEL_EXECUTOR, Stopwatch.createUnstarted(), InternalServiceProviders.isAndroid(getClass().getClassLoader()), isSrvEnabled());
    }
}

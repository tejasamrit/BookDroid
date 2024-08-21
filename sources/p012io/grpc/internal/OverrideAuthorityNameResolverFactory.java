package p012io.grpc.internal;

import java.net.URI;
import javax.annotation.Nullable;
import p012io.grpc.NameResolver;

/* renamed from: io.grpc.internal.OverrideAuthorityNameResolverFactory */
final class OverrideAuthorityNameResolverFactory extends NameResolver.Factory {
    /* access modifiers changed from: private */
    public final String authorityOverride;
    private final NameResolver.Factory delegate;

    OverrideAuthorityNameResolverFactory(NameResolver.Factory factory, String str) {
        this.delegate = factory;
        this.authorityOverride = str;
    }

    @Nullable
    public NameResolver newNameResolver(URI uri, NameResolver.Args args) {
        NameResolver newNameResolver = this.delegate.newNameResolver(uri, args);
        if (newNameResolver == null) {
            return null;
        }
        return new ForwardingNameResolver(newNameResolver) {
            public String getServiceAuthority() {
                return OverrideAuthorityNameResolverFactory.this.authorityOverride;
            }
        };
    }

    public String getDefaultScheme() {
        return this.delegate.getDefaultScheme();
    }
}

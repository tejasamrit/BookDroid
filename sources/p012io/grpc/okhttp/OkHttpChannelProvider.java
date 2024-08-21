package p012io.grpc.okhttp;

import p012io.grpc.InternalServiceProviders;
import p012io.grpc.ManagedChannelProvider;

/* renamed from: io.grpc.okhttp.OkHttpChannelProvider */
public final class OkHttpChannelProvider extends ManagedChannelProvider {
    public boolean isAvailable() {
        return true;
    }

    public int priority() {
        return InternalServiceProviders.isAndroid(getClass().getClassLoader()) ? 8 : 3;
    }

    public OkHttpChannelBuilder builderForAddress(String str, int i) {
        return OkHttpChannelBuilder.forAddress(str, i);
    }

    public OkHttpChannelBuilder builderForTarget(String str) {
        return OkHttpChannelBuilder.forTarget(str);
    }
}

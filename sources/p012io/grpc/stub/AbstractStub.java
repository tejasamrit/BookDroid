package p012io.grpc.stub;

import com.google.common.base.Preconditions;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import javax.annotation.CheckReturnValue;
import javax.annotation.Nullable;
import p012io.grpc.CallCredentials;
import p012io.grpc.CallOptions;
import p012io.grpc.Channel;
import p012io.grpc.ClientInterceptor;
import p012io.grpc.ClientInterceptors;
import p012io.grpc.Deadline;
import p012io.grpc.stub.AbstractStub;

@CheckReturnValue
/* renamed from: io.grpc.stub.AbstractStub */
public abstract class AbstractStub<S extends AbstractStub<S>> {
    private final CallOptions callOptions;
    private final Channel channel;

    /* renamed from: io.grpc.stub.AbstractStub$StubFactory */
    public interface StubFactory<T extends AbstractStub<T>> {
        T newStub(Channel channel, CallOptions callOptions);
    }

    /* access modifiers changed from: protected */
    public abstract S build(Channel channel2, CallOptions callOptions2);

    protected AbstractStub(Channel channel2) {
        this(channel2, CallOptions.DEFAULT);
    }

    protected AbstractStub(Channel channel2, CallOptions callOptions2) {
        this.channel = (Channel) Preconditions.checkNotNull(channel2, "channel");
        this.callOptions = (CallOptions) Preconditions.checkNotNull(callOptions2, "callOptions");
    }

    public final Channel getChannel() {
        return this.channel;
    }

    public final CallOptions getCallOptions() {
        return this.callOptions;
    }

    public static <T extends AbstractStub<T>> T newStub(StubFactory<T> stubFactory, Channel channel2) {
        return newStub(stubFactory, channel2, CallOptions.DEFAULT);
    }

    public static <T extends AbstractStub<T>> T newStub(StubFactory<T> stubFactory, Channel channel2, CallOptions callOptions2) {
        return stubFactory.newStub(channel2, callOptions2);
    }

    public final S withDeadline(@Nullable Deadline deadline) {
        return build(this.channel, this.callOptions.withDeadline(deadline));
    }

    public final S withDeadlineAfter(long j, TimeUnit timeUnit) {
        return build(this.channel, this.callOptions.withDeadlineAfter(j, timeUnit));
    }

    public final S withExecutor(Executor executor) {
        return build(this.channel, this.callOptions.withExecutor(executor));
    }

    public final S withCompression(String str) {
        return build(this.channel, this.callOptions.withCompression(str));
    }

    @Deprecated
    public final S withChannel(Channel channel2) {
        return build(channel2, this.callOptions);
    }

    public final <T> S withOption(CallOptions.Key<T> key, T t) {
        return build(this.channel, this.callOptions.withOption(key, t));
    }

    public final S withInterceptors(ClientInterceptor... clientInterceptorArr) {
        return build(ClientInterceptors.intercept(this.channel, clientInterceptorArr), this.callOptions);
    }

    public final S withCallCredentials(CallCredentials callCredentials) {
        return build(this.channel, this.callOptions.withCallCredentials(callCredentials));
    }

    public final S withWaitForReady() {
        return build(this.channel, this.callOptions.withWaitForReady());
    }

    public final S withMaxInboundMessageSize(int i) {
        return build(this.channel, this.callOptions.withMaxInboundMessageSize(i));
    }

    public final S withMaxOutboundMessageSize(int i) {
        return build(this.channel, this.callOptions.withMaxOutboundMessageSize(i));
    }
}

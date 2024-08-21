package p012io.grpc.stub;

import javax.annotation.CheckReturnValue;
import p012io.grpc.CallOptions;
import p012io.grpc.Channel;
import p012io.grpc.stub.AbstractAsyncStub;
import p012io.grpc.stub.AbstractStub;
import p012io.grpc.stub.ClientCalls;

@CheckReturnValue
/* renamed from: io.grpc.stub.AbstractAsyncStub */
public abstract class AbstractAsyncStub<S extends AbstractAsyncStub<S>> extends AbstractStub<S> {
    static final /* synthetic */ boolean $assertionsDisabled = false;

    protected AbstractAsyncStub(Channel channel, CallOptions callOptions) {
        super(channel, callOptions);
    }

    public static <T extends AbstractStub<T>> T newStub(AbstractStub.StubFactory<T> stubFactory, Channel channel) {
        return newStub(stubFactory, channel, CallOptions.DEFAULT);
    }

    public static <T extends AbstractStub<T>> T newStub(AbstractStub.StubFactory<T> stubFactory, Channel channel, CallOptions callOptions) {
        return stubFactory.newStub(channel, callOptions.withOption(ClientCalls.STUB_TYPE_OPTION, ClientCalls.StubType.ASYNC));
    }
}

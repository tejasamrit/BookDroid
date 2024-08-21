package p012io.grpc.stub;

import p012io.grpc.CallOptions;
import p012io.grpc.stub.ClientCalls;

/* renamed from: io.grpc.stub.InternalClientCalls */
public final class InternalClientCalls {
    public static CallOptions.Key<ClientCalls.StubType> getStubTypeOption() {
        return ClientCalls.STUB_TYPE_OPTION;
    }

    public static StubType getStubType(CallOptions callOptions) {
        return StubType.m268of((ClientCalls.StubType) callOptions.getOption(ClientCalls.STUB_TYPE_OPTION));
    }

    /* renamed from: io.grpc.stub.InternalClientCalls$StubType */
    public enum StubType {
        BLOCKING(ClientCalls.StubType.BLOCKING),
        ASYNC(ClientCalls.StubType.ASYNC),
        FUTURE(ClientCalls.StubType.FUTURE);
        
        private final ClientCalls.StubType internalType;

        private StubType(ClientCalls.StubType stubType) {
            this.internalType = stubType;
        }

        /* renamed from: of */
        public static StubType m268of(ClientCalls.StubType stubType) {
            for (StubType stubType2 : values()) {
                if (stubType2.internalType == stubType) {
                    return stubType2;
                }
            }
            throw new AssertionError("Unknown StubType: " + stubType.name());
        }
    }
}

package p012io.grpc;

import p012io.grpc.ServerCall;

/* renamed from: io.grpc.ServerCallHandler */
public interface ServerCallHandler<RequestT, ResponseT> {
    ServerCall.C2354Listener<RequestT> startCall(ServerCall<RequestT, ResponseT> serverCall, Metadata metadata);
}

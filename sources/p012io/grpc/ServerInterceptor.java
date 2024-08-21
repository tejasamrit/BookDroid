package p012io.grpc;

import p012io.grpc.ServerCall;

/* renamed from: io.grpc.ServerInterceptor */
public interface ServerInterceptor {
    <ReqT, RespT> ServerCall.C2354Listener<ReqT> interceptCall(ServerCall<ReqT, RespT> serverCall, Metadata metadata, ServerCallHandler<ReqT, RespT> serverCallHandler);
}

package p012io.grpc;

import p012io.grpc.ServerInterceptors;

/* renamed from: io.grpc.InternalServerInterceptors */
public final class InternalServerInterceptors {
    public static <ReqT, RespT> ServerCallHandler<ReqT, RespT> interceptCallHandler(ServerInterceptor serverInterceptor, ServerCallHandler<ReqT, RespT> serverCallHandler) {
        return ServerInterceptors.InterceptCallHandler.create(serverInterceptor, serverCallHandler);
    }

    public static <OrigReqT, OrigRespT, WrapReqT, WrapRespT> ServerMethodDefinition<WrapReqT, WrapRespT> wrapMethod(ServerMethodDefinition<OrigReqT, OrigRespT> serverMethodDefinition, MethodDescriptor<WrapReqT, WrapRespT> methodDescriptor) {
        return ServerInterceptors.wrapMethod(serverMethodDefinition, methodDescriptor);
    }

    public static <ReqT, RespT> ServerCallHandler<ReqT, RespT> interceptCallHandlerCreate(ServerInterceptor serverInterceptor, ServerCallHandler<ReqT, RespT> serverCallHandler) {
        return ServerInterceptors.InterceptCallHandler.create(serverInterceptor, serverCallHandler);
    }

    private InternalServerInterceptors() {
    }
}

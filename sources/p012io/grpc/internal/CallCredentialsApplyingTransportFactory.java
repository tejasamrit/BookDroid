package p012io.grpc.internal;

import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import java.net.SocketAddress;
import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledExecutorService;
import p012io.grpc.Attributes;
import p012io.grpc.CallCredentials;
import p012io.grpc.CallOptions;
import p012io.grpc.ChannelLogger;
import p012io.grpc.Metadata;
import p012io.grpc.MethodDescriptor;
import p012io.grpc.SecurityLevel;
import p012io.grpc.Status;
import p012io.grpc.internal.ClientTransportFactory;

/* renamed from: io.grpc.internal.CallCredentialsApplyingTransportFactory */
final class CallCredentialsApplyingTransportFactory implements ClientTransportFactory {
    /* access modifiers changed from: private */
    public final Executor appExecutor;
    private final ClientTransportFactory delegate;

    CallCredentialsApplyingTransportFactory(ClientTransportFactory clientTransportFactory, Executor executor) {
        this.delegate = (ClientTransportFactory) Preconditions.checkNotNull(clientTransportFactory, "delegate");
        this.appExecutor = (Executor) Preconditions.checkNotNull(executor, "appExecutor");
    }

    public ConnectionClientTransport newClientTransport(SocketAddress socketAddress, ClientTransportFactory.ClientTransportOptions clientTransportOptions, ChannelLogger channelLogger) {
        return new CallCredentialsApplyingTransport(this.delegate.newClientTransport(socketAddress, clientTransportOptions, channelLogger), clientTransportOptions.getAuthority());
    }

    public ScheduledExecutorService getScheduledExecutorService() {
        return this.delegate.getScheduledExecutorService();
    }

    public void close() {
        this.delegate.close();
    }

    /* renamed from: io.grpc.internal.CallCredentialsApplyingTransportFactory$CallCredentialsApplyingTransport */
    private class CallCredentialsApplyingTransport extends ForwardingConnectionClientTransport {
        /* access modifiers changed from: private */
        public final String authority;
        /* access modifiers changed from: private */
        public final ConnectionClientTransport delegate;

        CallCredentialsApplyingTransport(ConnectionClientTransport connectionClientTransport, String str) {
            this.delegate = (ConnectionClientTransport) Preconditions.checkNotNull(connectionClientTransport, "delegate");
            this.authority = (String) Preconditions.checkNotNull(str, "authority");
        }

        /* access modifiers changed from: protected */
        public ConnectionClientTransport delegate() {
            return this.delegate;
        }

        public ClientStream newStream(final MethodDescriptor<?, ?> methodDescriptor, Metadata metadata, final CallOptions callOptions) {
            CallCredentials credentials = callOptions.getCredentials();
            if (credentials == null) {
                return this.delegate.newStream(methodDescriptor, metadata, callOptions);
            }
            MetadataApplierImpl metadataApplierImpl = new MetadataApplierImpl(this.delegate, methodDescriptor, metadata, callOptions);
            try {
                credentials.applyRequestMetadata(new CallCredentials.RequestInfo() {
                    public MethodDescriptor<?, ?> getMethodDescriptor() {
                        return methodDescriptor;
                    }

                    public SecurityLevel getSecurityLevel() {
                        return (SecurityLevel) MoreObjects.firstNonNull(CallCredentialsApplyingTransport.this.delegate.getAttributes().get(GrpcAttributes.ATTR_SECURITY_LEVEL), SecurityLevel.NONE);
                    }

                    public String getAuthority() {
                        return (String) MoreObjects.firstNonNull(callOptions.getAuthority(), CallCredentialsApplyingTransport.this.authority);
                    }

                    public Attributes getTransportAttrs() {
                        return CallCredentialsApplyingTransport.this.delegate.getAttributes();
                    }
                }, (Executor) MoreObjects.firstNonNull(callOptions.getExecutor(), CallCredentialsApplyingTransportFactory.this.appExecutor), metadataApplierImpl);
            } catch (Throwable th) {
                metadataApplierImpl.fail(Status.UNAUTHENTICATED.withDescription("Credentials should use fail() instead of throwing exceptions").withCause(th));
            }
            return metadataApplierImpl.returnStream();
        }
    }
}

package p012io.grpc.okhttp;

import com.google.common.base.Preconditions;
import java.io.IOException;
import java.net.Socket;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import p012io.grpc.okhttp.internal.ConnectionSpec;
import p012io.grpc.okhttp.internal.OkHostnameVerifier;
import p012io.grpc.okhttp.internal.Protocol;

/* renamed from: io.grpc.okhttp.OkHttpTlsUpgrader */
final class OkHttpTlsUpgrader {
    static final List<Protocol> TLS_PROTOCOLS = Collections.unmodifiableList(Arrays.asList(new Protocol[]{Protocol.HTTP_2}));

    OkHttpTlsUpgrader() {
    }

    public static SSLSocket upgrade(SSLSocketFactory sSLSocketFactory, HostnameVerifier hostnameVerifier, Socket socket, String str, int i, ConnectionSpec connectionSpec) throws IOException {
        Preconditions.checkNotNull(sSLSocketFactory, "sslSocketFactory");
        Preconditions.checkNotNull(socket, "socket");
        Preconditions.checkNotNull(connectionSpec, "spec");
        SSLSocket sSLSocket = (SSLSocket) sSLSocketFactory.createSocket(socket, str, i, true);
        connectionSpec.apply(sSLSocket, false);
        String negotiate = OkHttpProtocolNegotiator.get().negotiate(sSLSocket, str, connectionSpec.supportsTlsExtensions() ? TLS_PROTOCOLS : null);
        List<Protocol> list = TLS_PROTOCOLS;
        boolean contains = list.contains(Protocol.get(negotiate));
        Preconditions.checkState(contains, "Only " + list + " are supported, but negotiated protocol is %s", (Object) negotiate);
        if (hostnameVerifier == null) {
            hostnameVerifier = OkHostnameVerifier.INSTANCE;
        }
        if (hostnameVerifier.verify(canonicalizeHost(str), sSLSocket.getSession())) {
            return sSLSocket;
        }
        throw new SSLPeerUnverifiedException("Cannot verify hostname: " + str);
    }

    static String canonicalizeHost(String str) {
        return (!str.startsWith("[") || !str.endsWith("]")) ? str : str.substring(1, str.length() - 1);
    }
}

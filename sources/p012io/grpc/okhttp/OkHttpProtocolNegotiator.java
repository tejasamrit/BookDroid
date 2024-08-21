package p012io.grpc.okhttp;

import com.google.common.base.Preconditions;
import java.io.IOException;
import java.net.Socket;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Nullable;
import javax.net.ssl.SSLSocket;
import p012io.grpc.okhttp.internal.OptionalMethod;
import p012io.grpc.okhttp.internal.Platform;
import p012io.grpc.okhttp.internal.Protocol;
import p012io.grpc.okhttp.internal.Util;

/* renamed from: io.grpc.okhttp.OkHttpProtocolNegotiator */
class OkHttpProtocolNegotiator {
    private static final Platform DEFAULT_PLATFORM = Platform.get();
    private static OkHttpProtocolNegotiator NEGOTIATOR;
    /* access modifiers changed from: private */
    public static final Logger logger;
    protected final Platform platform;

    static {
        Class<OkHttpProtocolNegotiator> cls = OkHttpProtocolNegotiator.class;
        logger = Logger.getLogger(cls.getName());
        NEGOTIATOR = createNegotiator(cls.getClassLoader());
    }

    OkHttpProtocolNegotiator(Platform platform2) {
        this.platform = (Platform) Preconditions.checkNotNull(platform2, "platform");
    }

    public static OkHttpProtocolNegotiator get() {
        return NEGOTIATOR;
    }

    static OkHttpProtocolNegotiator createNegotiator(ClassLoader classLoader) {
        boolean z;
        try {
            classLoader.loadClass("com.android.org.conscrypt.OpenSSLSocketImpl");
        } catch (ClassNotFoundException e) {
            logger.log(Level.FINE, "Unable to find Conscrypt. Skipping", e);
            try {
                classLoader.loadClass("org.apache.harmony.xnet.provider.jsse.OpenSSLSocketImpl");
            } catch (ClassNotFoundException e2) {
                logger.log(Level.FINE, "Unable to find any OpenSSLSocketImpl. Skipping", e2);
                z = false;
            }
        }
        z = true;
        if (z) {
            return new AndroidNegotiator(DEFAULT_PLATFORM);
        }
        return new OkHttpProtocolNegotiator(DEFAULT_PLATFORM);
    }

    public String negotiate(SSLSocket sSLSocket, String str, @Nullable List<Protocol> list) throws IOException {
        if (list != null) {
            configureTlsExtensions(sSLSocket, str, list);
        }
        try {
            sSLSocket.startHandshake();
            String selectedProtocol = getSelectedProtocol(sSLSocket);
            if (selectedProtocol != null) {
                return selectedProtocol;
            }
            throw new RuntimeException("TLS ALPN negotiation failed with protocols: " + list);
        } finally {
            this.platform.afterHandshake(sSLSocket);
        }
    }

    /* access modifiers changed from: protected */
    public void configureTlsExtensions(SSLSocket sSLSocket, String str, List<Protocol> list) {
        this.platform.configureTlsExtensions(sSLSocket, str, list);
    }

    public String getSelectedProtocol(SSLSocket sSLSocket) {
        return this.platform.getSelectedProtocol(sSLSocket);
    }

    /* renamed from: io.grpc.okhttp.OkHttpProtocolNegotiator$AndroidNegotiator */
    static final class AndroidNegotiator extends OkHttpProtocolNegotiator {
        private static final OptionalMethod<Socket> GET_ALPN_SELECTED_PROTOCOL;
        private static final OptionalMethod<Socket> GET_NPN_SELECTED_PROTOCOL;
        private static final OptionalMethod<Socket> SET_ALPN_PROTOCOLS;
        private static final OptionalMethod<Socket> SET_HOSTNAME = new OptionalMethod<>((Class<?>) null, "setHostname", String.class);
        private static final OptionalMethod<Socket> SET_NPN_PROTOCOLS;
        private static final OptionalMethod<Socket> SET_USE_SESSION_TICKETS = new OptionalMethod<>((Class<?>) null, "setUseSessionTickets", Boolean.TYPE);

        static {
            Class<byte[]> cls = byte[].class;
            GET_ALPN_SELECTED_PROTOCOL = new OptionalMethod<>(cls, "getAlpnSelectedProtocol", new Class[0]);
            SET_ALPN_PROTOCOLS = new OptionalMethod<>((Class<?>) null, "setAlpnProtocols", cls);
            GET_NPN_SELECTED_PROTOCOL = new OptionalMethod<>(cls, "getNpnSelectedProtocol", new Class[0]);
            SET_NPN_PROTOCOLS = new OptionalMethod<>((Class<?>) null, "setNpnProtocols", cls);
        }

        AndroidNegotiator(Platform platform) {
            super(platform);
        }

        public String negotiate(SSLSocket sSLSocket, String str, List<Protocol> list) throws IOException {
            String selectedProtocol = getSelectedProtocol(sSLSocket);
            return selectedProtocol == null ? OkHttpProtocolNegotiator.super.negotiate(sSLSocket, str, list) : selectedProtocol;
        }

        /* access modifiers changed from: protected */
        public void configureTlsExtensions(SSLSocket sSLSocket, String str, List<Protocol> list) {
            if (str != null) {
                SET_USE_SESSION_TICKETS.invokeOptionalWithoutCheckedException(sSLSocket, true);
                SET_HOSTNAME.invokeOptionalWithoutCheckedException(sSLSocket, str);
            }
            Object[] objArr = {Platform.concatLengthPrefixed(list)};
            if (this.platform.getTlsExtensionType() == Platform.TlsExtensionType.ALPN_AND_NPN) {
                SET_ALPN_PROTOCOLS.invokeWithoutCheckedException(sSLSocket, objArr);
            }
            if (this.platform.getTlsExtensionType() != Platform.TlsExtensionType.NONE) {
                SET_NPN_PROTOCOLS.invokeWithoutCheckedException(sSLSocket, objArr);
                return;
            }
            throw new RuntimeException("We can not do TLS handshake on this Android version, please install the Google Play Services Dynamic Security Provider to use TLS");
        }

        public String getSelectedProtocol(SSLSocket sSLSocket) {
            if (this.platform.getTlsExtensionType() == Platform.TlsExtensionType.ALPN_AND_NPN) {
                try {
                    byte[] bArr = (byte[]) GET_ALPN_SELECTED_PROTOCOL.invokeWithoutCheckedException(sSLSocket, new Object[0]);
                    if (bArr != null) {
                        return new String(bArr, Util.UTF_8);
                    }
                } catch (Exception e) {
                    OkHttpProtocolNegotiator.logger.log(Level.FINE, "Failed calling getAlpnSelectedProtocol()", e);
                }
            }
            if (this.platform.getTlsExtensionType() == Platform.TlsExtensionType.NONE) {
                return null;
            }
            try {
                byte[] bArr2 = (byte[]) GET_NPN_SELECTED_PROTOCOL.invokeWithoutCheckedException(sSLSocket, new Object[0]);
                if (bArr2 != null) {
                    return new String(bArr2, Util.UTF_8);
                }
                return null;
            } catch (Exception e2) {
                OkHttpProtocolNegotiator.logger.log(Level.FINE, "Failed calling getNpnSelectedProtocol()", e2);
                return null;
            }
        }
    }
}

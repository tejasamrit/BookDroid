package com.squareup.okhttp.internal;

import android.util.Log;
import com.squareup.okhttp.Protocol;
import com.squareup.okhttp.internal.tls.AndroidTrustRootIndex;
import com.squareup.okhttp.internal.tls.RealTrustRootIndex;
import com.squareup.okhttp.internal.tls.TrustRootIndex;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.X509TrustManager;
import okio.Buffer;

public class Platform {
    private static final Platform PLATFORM = findPlatform();

    public void afterHandshake(SSLSocket sSLSocket) {
    }

    public void configureTlsExtensions(SSLSocket sSLSocket, String str, List<Protocol> list) {
    }

    public String getPrefix() {
        return "OkHttp";
    }

    public String getSelectedProtocol(SSLSocket sSLSocket) {
        return null;
    }

    public void tagSocket(Socket socket) throws SocketException {
    }

    public X509TrustManager trustManager(SSLSocketFactory sSLSocketFactory) {
        return null;
    }

    public void untagSocket(Socket socket) throws SocketException {
    }

    public static Platform get() {
        return PLATFORM;
    }

    public void logW(String str) {
        System.out.println(str);
    }

    public TrustRootIndex trustRootIndex(X509TrustManager x509TrustManager) {
        return new RealTrustRootIndex(x509TrustManager.getAcceptedIssuers());
    }

    public void connectSocket(Socket socket, InetSocketAddress inetSocketAddress, int i) throws IOException {
        socket.connect(inetSocketAddress, i);
    }

    public void log(String str) {
        System.out.println(str);
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(3:40|41|42) */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x00f9, code lost:
        return new com.squareup.okhttp.internal.Platform.JdkPlatform(r0);
     */
    /* JADX WARNING: Missing exception handler attribute for start block: B:40:0x00f4 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static com.squareup.okhttp.internal.Platform findPlatform() {
        /*
            java.lang.Class<byte[]> r0 = byte[].class
            r1 = 1
            r2 = 0
            java.lang.String r3 = "com.android.org.conscrypt.SSLParametersImpl"
            java.lang.Class r3 = java.lang.Class.forName(r3)     // Catch:{ ClassNotFoundException -> 0x000c }
        L_0x000a:
            r5 = r3
            goto L_0x0013
        L_0x000c:
            java.lang.String r3 = "org.apache.harmony.xnet.provider.jsse.SSLParametersImpl"
            java.lang.Class r3 = java.lang.Class.forName(r3)     // Catch:{ ClassNotFoundException -> 0x007a }
            goto L_0x000a
        L_0x0013:
            com.squareup.okhttp.internal.OptionalMethod r6 = new com.squareup.okhttp.internal.OptionalMethod     // Catch:{ ClassNotFoundException -> 0x007a }
            java.lang.String r3 = "setUseSessionTickets"
            java.lang.Class[] r4 = new java.lang.Class[r1]     // Catch:{ ClassNotFoundException -> 0x007a }
            java.lang.Class r7 = java.lang.Boolean.TYPE     // Catch:{ ClassNotFoundException -> 0x007a }
            r4[r2] = r7     // Catch:{ ClassNotFoundException -> 0x007a }
            r7 = 0
            r6.<init>(r7, r3, r4)     // Catch:{ ClassNotFoundException -> 0x007a }
            com.squareup.okhttp.internal.OptionalMethod r3 = new com.squareup.okhttp.internal.OptionalMethod     // Catch:{ ClassNotFoundException -> 0x007a }
            java.lang.String r4 = "setHostname"
            java.lang.Class[] r8 = new java.lang.Class[r1]     // Catch:{ ClassNotFoundException -> 0x007a }
            java.lang.Class<java.lang.String> r9 = java.lang.String.class
            r8[r2] = r9     // Catch:{ ClassNotFoundException -> 0x007a }
            r3.<init>(r7, r4, r8)     // Catch:{ ClassNotFoundException -> 0x007a }
            java.lang.String r4 = "android.net.TrafficStats"
            java.lang.Class r4 = java.lang.Class.forName(r4)     // Catch:{ ClassNotFoundException | NoSuchMethodException -> 0x006c }
            java.lang.String r8 = "tagSocket"
            java.lang.Class[] r9 = new java.lang.Class[r1]     // Catch:{ ClassNotFoundException | NoSuchMethodException -> 0x006c }
            java.lang.Class<java.net.Socket> r10 = java.net.Socket.class
            r9[r2] = r10     // Catch:{ ClassNotFoundException | NoSuchMethodException -> 0x006c }
            java.lang.reflect.Method r8 = r4.getMethod(r8, r9)     // Catch:{ ClassNotFoundException | NoSuchMethodException -> 0x006c }
            java.lang.String r9 = "untagSocket"
            java.lang.Class[] r10 = new java.lang.Class[r1]     // Catch:{ ClassNotFoundException | NoSuchMethodException -> 0x0069 }
            java.lang.Class<java.net.Socket> r11 = java.net.Socket.class
            r10[r2] = r11     // Catch:{ ClassNotFoundException | NoSuchMethodException -> 0x0069 }
            java.lang.reflect.Method r4 = r4.getMethod(r9, r10)     // Catch:{ ClassNotFoundException | NoSuchMethodException -> 0x0069 }
            java.lang.String r9 = "android.net.Network"
            java.lang.Class.forName(r9)     // Catch:{ ClassNotFoundException | NoSuchMethodException -> 0x0067 }
            com.squareup.okhttp.internal.OptionalMethod r9 = new com.squareup.okhttp.internal.OptionalMethod     // Catch:{ ClassNotFoundException | NoSuchMethodException -> 0x0067 }
            java.lang.String r10 = "getAlpnSelectedProtocol"
            java.lang.Class[] r11 = new java.lang.Class[r2]     // Catch:{ ClassNotFoundException | NoSuchMethodException -> 0x0067 }
            r9.<init>(r0, r10, r11)     // Catch:{ ClassNotFoundException | NoSuchMethodException -> 0x0067 }
            com.squareup.okhttp.internal.OptionalMethod r10 = new com.squareup.okhttp.internal.OptionalMethod     // Catch:{ ClassNotFoundException | NoSuchMethodException -> 0x006f }
            java.lang.String r11 = "setAlpnProtocols"
            java.lang.Class[] r12 = new java.lang.Class[r1]     // Catch:{ ClassNotFoundException | NoSuchMethodException -> 0x006f }
            r12[r2] = r0     // Catch:{ ClassNotFoundException | NoSuchMethodException -> 0x006f }
            r10.<init>(r7, r11, r12)     // Catch:{ ClassNotFoundException | NoSuchMethodException -> 0x006f }
            r7 = r10
            goto L_0x006f
        L_0x0067:
            r9 = r7
            goto L_0x006f
        L_0x0069:
            r4 = r7
            r9 = r4
            goto L_0x006f
        L_0x006c:
            r4 = r7
            r8 = r4
            r9 = r8
        L_0x006f:
            r11 = r7
            r10 = r9
            r9 = r4
            com.squareup.okhttp.internal.Platform$Android r0 = new com.squareup.okhttp.internal.Platform$Android     // Catch:{ ClassNotFoundException -> 0x007a }
            r4 = r0
            r7 = r3
            r4.<init>(r5, r6, r7, r8, r9, r10, r11)     // Catch:{ ClassNotFoundException -> 0x007a }
            return r0
        L_0x007a:
            java.lang.String r0 = "sun.security.ssl.SSLContextImpl"
            java.lang.Class r0 = java.lang.Class.forName(r0)     // Catch:{ ClassNotFoundException -> 0x00fa }
            java.lang.String r3 = "org.eclipse.jetty.alpn.ALPN"
            java.lang.Class r4 = java.lang.Class.forName(r3)     // Catch:{ ClassNotFoundException | NoSuchMethodException -> 0x00f4 }
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ ClassNotFoundException | NoSuchMethodException -> 0x00f4 }
            r5.<init>()     // Catch:{ ClassNotFoundException | NoSuchMethodException -> 0x00f4 }
            r5.append(r3)     // Catch:{ ClassNotFoundException | NoSuchMethodException -> 0x00f4 }
            java.lang.String r6 = "$Provider"
            r5.append(r6)     // Catch:{ ClassNotFoundException | NoSuchMethodException -> 0x00f4 }
            java.lang.String r5 = r5.toString()     // Catch:{ ClassNotFoundException | NoSuchMethodException -> 0x00f4 }
            java.lang.Class r5 = java.lang.Class.forName(r5)     // Catch:{ ClassNotFoundException | NoSuchMethodException -> 0x00f4 }
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ ClassNotFoundException | NoSuchMethodException -> 0x00f4 }
            r6.<init>()     // Catch:{ ClassNotFoundException | NoSuchMethodException -> 0x00f4 }
            r6.append(r3)     // Catch:{ ClassNotFoundException | NoSuchMethodException -> 0x00f4 }
            java.lang.String r7 = "$ClientProvider"
            r6.append(r7)     // Catch:{ ClassNotFoundException | NoSuchMethodException -> 0x00f4 }
            java.lang.String r6 = r6.toString()     // Catch:{ ClassNotFoundException | NoSuchMethodException -> 0x00f4 }
            java.lang.Class r8 = java.lang.Class.forName(r6)     // Catch:{ ClassNotFoundException | NoSuchMethodException -> 0x00f4 }
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ ClassNotFoundException | NoSuchMethodException -> 0x00f4 }
            r6.<init>()     // Catch:{ ClassNotFoundException | NoSuchMethodException -> 0x00f4 }
            r6.append(r3)     // Catch:{ ClassNotFoundException | NoSuchMethodException -> 0x00f4 }
            java.lang.String r3 = "$ServerProvider"
            r6.append(r3)     // Catch:{ ClassNotFoundException | NoSuchMethodException -> 0x00f4 }
            java.lang.String r3 = r6.toString()     // Catch:{ ClassNotFoundException | NoSuchMethodException -> 0x00f4 }
            java.lang.Class r9 = java.lang.Class.forName(r3)     // Catch:{ ClassNotFoundException | NoSuchMethodException -> 0x00f4 }
            java.lang.String r3 = "put"
            r6 = 2
            java.lang.Class[] r6 = new java.lang.Class[r6]     // Catch:{ ClassNotFoundException | NoSuchMethodException -> 0x00f4 }
            java.lang.Class<javax.net.ssl.SSLSocket> r7 = javax.net.ssl.SSLSocket.class
            r6[r2] = r7     // Catch:{ ClassNotFoundException | NoSuchMethodException -> 0x00f4 }
            r6[r1] = r5     // Catch:{ ClassNotFoundException | NoSuchMethodException -> 0x00f4 }
            java.lang.reflect.Method r5 = r4.getMethod(r3, r6)     // Catch:{ ClassNotFoundException | NoSuchMethodException -> 0x00f4 }
            java.lang.String r3 = "get"
            java.lang.Class[] r6 = new java.lang.Class[r1]     // Catch:{ ClassNotFoundException | NoSuchMethodException -> 0x00f4 }
            java.lang.Class<javax.net.ssl.SSLSocket> r7 = javax.net.ssl.SSLSocket.class
            r6[r2] = r7     // Catch:{ ClassNotFoundException | NoSuchMethodException -> 0x00f4 }
            java.lang.reflect.Method r6 = r4.getMethod(r3, r6)     // Catch:{ ClassNotFoundException | NoSuchMethodException -> 0x00f4 }
            java.lang.String r3 = "remove"
            java.lang.Class[] r1 = new java.lang.Class[r1]     // Catch:{ ClassNotFoundException | NoSuchMethodException -> 0x00f4 }
            java.lang.Class<javax.net.ssl.SSLSocket> r7 = javax.net.ssl.SSLSocket.class
            r1[r2] = r7     // Catch:{ ClassNotFoundException | NoSuchMethodException -> 0x00f4 }
            java.lang.reflect.Method r7 = r4.getMethod(r3, r1)     // Catch:{ ClassNotFoundException | NoSuchMethodException -> 0x00f4 }
            com.squareup.okhttp.internal.Platform$JdkWithJettyBootPlatform r1 = new com.squareup.okhttp.internal.Platform$JdkWithJettyBootPlatform     // Catch:{ ClassNotFoundException | NoSuchMethodException -> 0x00f4 }
            r3 = r1
            r4 = r0
            r3.<init>(r4, r5, r6, r7, r8, r9)     // Catch:{ ClassNotFoundException | NoSuchMethodException -> 0x00f4 }
            return r1
        L_0x00f4:
            com.squareup.okhttp.internal.Platform$JdkPlatform r1 = new com.squareup.okhttp.internal.Platform$JdkPlatform     // Catch:{ ClassNotFoundException -> 0x00fa }
            r1.<init>(r0)     // Catch:{ ClassNotFoundException -> 0x00fa }
            return r1
        L_0x00fa:
            com.squareup.okhttp.internal.Platform r0 = new com.squareup.okhttp.internal.Platform
            r0.<init>()
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.squareup.okhttp.internal.Platform.findPlatform():com.squareup.okhttp.internal.Platform");
    }

    private static class Android extends Platform {
        private static final int MAX_LOG_LENGTH = 4000;
        private final OptionalMethod<Socket> getAlpnSelectedProtocol;
        private final OptionalMethod<Socket> setAlpnProtocols;
        private final OptionalMethod<Socket> setHostname;
        private final OptionalMethod<Socket> setUseSessionTickets;
        private final Class<?> sslParametersClass;
        private final Method trafficStatsTagSocket;
        private final Method trafficStatsUntagSocket;

        public Android(Class<?> cls, OptionalMethod<Socket> optionalMethod, OptionalMethod<Socket> optionalMethod2, Method method, Method method2, OptionalMethod<Socket> optionalMethod3, OptionalMethod<Socket> optionalMethod4) {
            this.sslParametersClass = cls;
            this.setUseSessionTickets = optionalMethod;
            this.setHostname = optionalMethod2;
            this.trafficStatsTagSocket = method;
            this.trafficStatsUntagSocket = method2;
            this.getAlpnSelectedProtocol = optionalMethod3;
            this.setAlpnProtocols = optionalMethod4;
        }

        public void connectSocket(Socket socket, InetSocketAddress inetSocketAddress, int i) throws IOException {
            try {
                socket.connect(inetSocketAddress, i);
            } catch (AssertionError e) {
                if (Util.isAndroidGetsocknameError(e)) {
                    throw new IOException(e);
                }
                throw e;
            } catch (SecurityException e2) {
                IOException iOException = new IOException("Exception in connect");
                iOException.initCause(e2);
                throw iOException;
            }
        }

        public X509TrustManager trustManager(SSLSocketFactory sSLSocketFactory) {
            Object readFieldOrNull = readFieldOrNull(sSLSocketFactory, this.sslParametersClass, "sslParameters");
            if (readFieldOrNull == null) {
                try {
                    readFieldOrNull = readFieldOrNull(sSLSocketFactory, Class.forName("com.google.android.gms.org.conscrypt.SSLParametersImpl", false, sSLSocketFactory.getClass().getClassLoader()), "sslParameters");
                } catch (ClassNotFoundException unused) {
                    return null;
                }
            }
            X509TrustManager x509TrustManager = (X509TrustManager) readFieldOrNull(readFieldOrNull, X509TrustManager.class, "x509TrustManager");
            if (x509TrustManager != null) {
                return x509TrustManager;
            }
            return (X509TrustManager) readFieldOrNull(readFieldOrNull, X509TrustManager.class, "trustManager");
        }

        public TrustRootIndex trustRootIndex(X509TrustManager x509TrustManager) {
            TrustRootIndex trustRootIndex = AndroidTrustRootIndex.get(x509TrustManager);
            if (trustRootIndex != null) {
                return trustRootIndex;
            }
            return Platform.super.trustRootIndex(x509TrustManager);
        }

        public void configureTlsExtensions(SSLSocket sSLSocket, String str, List<Protocol> list) {
            if (str != null) {
                this.setUseSessionTickets.invokeOptionalWithoutCheckedException(sSLSocket, true);
                this.setHostname.invokeOptionalWithoutCheckedException(sSLSocket, str);
            }
            OptionalMethod<Socket> optionalMethod = this.setAlpnProtocols;
            if (optionalMethod != null && optionalMethod.isSupported(sSLSocket)) {
                this.setAlpnProtocols.invokeWithoutCheckedException(sSLSocket, concatLengthPrefixed(list));
            }
        }

        public String getSelectedProtocol(SSLSocket sSLSocket) {
            byte[] bArr;
            OptionalMethod<Socket> optionalMethod = this.getAlpnSelectedProtocol;
            if (optionalMethod == null || !optionalMethod.isSupported(sSLSocket) || (bArr = (byte[]) this.getAlpnSelectedProtocol.invokeWithoutCheckedException(sSLSocket, new Object[0])) == null) {
                return null;
            }
            return new String(bArr, Util.UTF_8);
        }

        public void tagSocket(Socket socket) throws SocketException {
            Method method = this.trafficStatsTagSocket;
            if (method != null) {
                try {
                    method.invoke((Object) null, new Object[]{socket});
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                } catch (InvocationTargetException e2) {
                    throw new RuntimeException(e2.getCause());
                }
            }
        }

        public void untagSocket(Socket socket) throws SocketException {
            Method method = this.trafficStatsUntagSocket;
            if (method != null) {
                try {
                    method.invoke((Object) null, new Object[]{socket});
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                } catch (InvocationTargetException e2) {
                    throw new RuntimeException(e2.getCause());
                }
            }
        }

        public void log(String str) {
            int min;
            int length = str.length();
            int i = 0;
            while (i < length) {
                int indexOf = str.indexOf(10, i);
                if (indexOf == -1) {
                    indexOf = length;
                }
                while (true) {
                    min = Math.min(indexOf, i + MAX_LOG_LENGTH);
                    Log.d("OkHttp", str.substring(i, min));
                    if (min >= indexOf) {
                        break;
                    }
                    i = min;
                }
                i = min + 1;
            }
        }
    }

    private static class JdkPlatform extends Platform {
        private final Class<?> sslContextClass;

        public JdkPlatform(Class<?> cls) {
            this.sslContextClass = cls;
        }

        public X509TrustManager trustManager(SSLSocketFactory sSLSocketFactory) {
            Object readFieldOrNull = readFieldOrNull(sSLSocketFactory, this.sslContextClass, "context");
            if (readFieldOrNull == null) {
                return null;
            }
            return (X509TrustManager) readFieldOrNull(readFieldOrNull, X509TrustManager.class, "trustManager");
        }
    }

    private static class JdkWithJettyBootPlatform extends JdkPlatform {
        private final Class<?> clientProviderClass;
        private final Method getMethod;
        private final Method putMethod;
        private final Method removeMethod;
        private final Class<?> serverProviderClass;

        public JdkWithJettyBootPlatform(Class<?> cls, Method method, Method method2, Method method3, Class<?> cls2, Class<?> cls3) {
            super(cls);
            this.putMethod = method;
            this.getMethod = method2;
            this.removeMethod = method3;
            this.clientProviderClass = cls2;
            this.serverProviderClass = cls3;
        }

        public void configureTlsExtensions(SSLSocket sSLSocket, String str, List<Protocol> list) {
            ArrayList arrayList = new ArrayList(list.size());
            int size = list.size();
            for (int i = 0; i < size; i++) {
                Protocol protocol = list.get(i);
                if (protocol != Protocol.HTTP_1_0) {
                    arrayList.add(protocol.toString());
                }
            }
            try {
                Object newProxyInstance = Proxy.newProxyInstance(Platform.class.getClassLoader(), new Class[]{this.clientProviderClass, this.serverProviderClass}, new JettyNegoProvider(arrayList));
                this.putMethod.invoke((Object) null, new Object[]{sSLSocket, newProxyInstance});
            } catch (IllegalAccessException | InvocationTargetException e) {
                throw new AssertionError(e);
            }
        }

        public void afterHandshake(SSLSocket sSLSocket) {
            try {
                this.removeMethod.invoke((Object) null, new Object[]{sSLSocket});
            } catch (IllegalAccessException | InvocationTargetException unused) {
                throw new AssertionError();
            }
        }

        public String getSelectedProtocol(SSLSocket sSLSocket) {
            try {
                JettyNegoProvider jettyNegoProvider = (JettyNegoProvider) Proxy.getInvocationHandler(this.getMethod.invoke((Object) null, new Object[]{sSLSocket}));
                if (!jettyNegoProvider.unsupported && jettyNegoProvider.selected == null) {
                    Internal.logger.log(Level.INFO, "ALPN callback dropped: SPDY and HTTP/2 are disabled. Is alpn-boot on the boot class path?");
                    return null;
                } else if (jettyNegoProvider.unsupported) {
                    return null;
                } else {
                    return jettyNegoProvider.selected;
                }
            } catch (IllegalAccessException | InvocationTargetException unused) {
                throw new AssertionError();
            }
        }
    }

    private static class JettyNegoProvider implements InvocationHandler {
        private final List<String> protocols;
        /* access modifiers changed from: private */
        public String selected;
        /* access modifiers changed from: private */
        public boolean unsupported;

        public JettyNegoProvider(List<String> list) {
            this.protocols = list;
        }

        public Object invoke(Object obj, Method method, Object[] objArr) throws Throwable {
            String name = method.getName();
            Class<?> returnType = method.getReturnType();
            if (objArr == null) {
                objArr = Util.EMPTY_STRING_ARRAY;
            }
            if (name.equals("supports") && Boolean.TYPE == returnType) {
                return true;
            }
            if (name.equals("unsupported") && Void.TYPE == returnType) {
                this.unsupported = true;
                return null;
            } else if (name.equals("protocols") && objArr.length == 0) {
                return this.protocols;
            } else {
                if ((name.equals("selectProtocol") || name.equals("select")) && String.class == returnType && objArr.length == 1 && (objArr[0] instanceof List)) {
                    List list = (List) objArr[0];
                    int size = list.size();
                    for (int i = 0; i < size; i++) {
                        if (this.protocols.contains(list.get(i))) {
                            String str = (String) list.get(i);
                            this.selected = str;
                            return str;
                        }
                    }
                    String str2 = this.protocols.get(0);
                    this.selected = str2;
                    return str2;
                } else if ((!name.equals("protocolSelected") && !name.equals("selected")) || objArr.length != 1) {
                    return method.invoke(this, objArr);
                } else {
                    this.selected = (String) objArr[0];
                    return null;
                }
            }
        }
    }

    static byte[] concatLengthPrefixed(List<Protocol> list) {
        Buffer buffer = new Buffer();
        int size = list.size();
        for (int i = 0; i < size; i++) {
            Protocol protocol = list.get(i);
            if (protocol != Protocol.HTTP_1_0) {
                buffer.writeByte(protocol.toString().length());
                buffer.writeUtf8(protocol.toString());
            }
        }
        return buffer.readByteArray();
    }

    static <T> T readFieldOrNull(Object obj, Class<T> cls, String str) {
        Object readFieldOrNull;
        Class cls2 = obj.getClass();
        while (cls2 != Object.class) {
            try {
                Field declaredField = cls2.getDeclaredField(str);
                declaredField.setAccessible(true);
                Object obj2 = declaredField.get(obj);
                if (obj2 != null) {
                    if (cls.isInstance(obj2)) {
                        return cls.cast(obj2);
                    }
                }
                return null;
            } catch (NoSuchFieldException unused) {
                cls2 = cls2.getSuperclass();
            } catch (IllegalAccessException unused2) {
                throw new AssertionError();
            }
        }
        if (str.equals("delegate") || (readFieldOrNull = readFieldOrNull(obj, Object.class, "delegate")) == null) {
            return null;
        }
        return readFieldOrNull(readFieldOrNull, cls, str);
    }
}

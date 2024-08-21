package com.google.firebase.database.tubesock;

import android.net.SSLCertificateSocketFactory;
import android.net.SSLSessionCache;
import com.bumptech.glide.load.Key;
import com.google.firebase.database.connection.ConnectionContext;
import com.google.firebase.database.logging.LogWrapper;
import com.google.firebase.database.logging.Logger;
import java.io.File;
import java.io.IOException;
import java.lang.Thread;
import java.net.Socket;
import java.net.URI;
import java.net.UnknownHostException;
import java.nio.charset.Charset;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSocket;
import p012io.grpc.internal.GrpcUtil;

public class WebSocket {
    static final byte OPCODE_BINARY = 2;
    static final byte OPCODE_CLOSE = 8;
    static final byte OPCODE_NONE = 0;
    static final byte OPCODE_PING = 9;
    static final byte OPCODE_PONG = 10;
    static final byte OPCODE_TEXT = 1;
    private static final int SSL_HANDSHAKE_TIMEOUT_MS = 60000;
    private static final String THREAD_BASE_NAME = "TubeSock";
    private static final Charset UTF8 = Charset.forName(Key.STRING_CHARSET_NAME);
    private static final AtomicInteger clientCount = new AtomicInteger(0);
    private static ThreadInitializer intializer = new ThreadInitializer() {
        public void setName(Thread thread, String str) {
            thread.setName(str);
        }
    };
    private static ThreadFactory threadFactory = Executors.defaultThreadFactory();
    private final int clientId;
    private WebSocketEventHandler eventHandler;
    private final WebSocketHandshake handshake;
    private final Thread innerThread;
    private final LogWrapper logger;
    private final WebSocketReceiver receiver;
    private volatile Socket socket;
    private final String sslCacheDirectory;
    private volatile State state;
    private final URI url;
    private final WebSocketWriter writer;

    private enum State {
        NONE,
        CONNECTING,
        CONNECTED,
        DISCONNECTING,
        DISCONNECTED
    }

    static ThreadFactory getThreadFactory() {
        return threadFactory;
    }

    static ThreadInitializer getIntializer() {
        return intializer;
    }

    public static void setThreadFactory(ThreadFactory threadFactory2, ThreadInitializer threadInitializer) {
        threadFactory = threadFactory2;
        intializer = threadInitializer;
    }

    public WebSocket(ConnectionContext connectionContext, URI uri) {
        this(connectionContext, uri, (String) null);
    }

    public WebSocket(ConnectionContext connectionContext, URI uri, String str) {
        this(connectionContext, uri, str, (Map<String, String>) null);
    }

    public WebSocket(ConnectionContext connectionContext, URI uri, String str, Map<String, String> map) {
        this.state = State.NONE;
        this.socket = null;
        this.eventHandler = null;
        int incrementAndGet = clientCount.incrementAndGet();
        this.clientId = incrementAndGet;
        this.innerThread = getThreadFactory().newThread(new Runnable() {
            public void run() {
                WebSocket.this.runReader();
            }
        });
        this.url = uri;
        this.sslCacheDirectory = connectionContext.getSslCacheDirectory();
        Logger logger2 = connectionContext.getLogger();
        this.logger = new LogWrapper(logger2, "WebSocket", "sk_" + incrementAndGet);
        this.handshake = new WebSocketHandshake(uri, str, map);
        this.receiver = new WebSocketReceiver(this);
        this.writer = new WebSocketWriter(this, THREAD_BASE_NAME, incrementAndGet);
    }

    public void setEventHandler(WebSocketEventHandler webSocketEventHandler) {
        this.eventHandler = webSocketEventHandler;
    }

    /* access modifiers changed from: package-private */
    public WebSocketEventHandler getEventHandler() {
        return this.eventHandler;
    }

    public synchronized void connect() {
        if (this.state != State.NONE) {
            this.eventHandler.onError(new WebSocketException("connect() already called"));
            close();
            return;
        }
        ThreadInitializer intializer2 = getIntializer();
        Thread innerThread2 = getInnerThread();
        intializer2.setName(innerThread2, "TubeSockReader-" + this.clientId);
        this.state = State.CONNECTING;
        getInnerThread().start();
    }

    public synchronized void send(String str) {
        send((byte) 1, str.getBytes(UTF8));
    }

    public synchronized void send(byte[] bArr) {
        send((byte) 2, bArr);
    }

    /* access modifiers changed from: package-private */
    public synchronized void pong(byte[] bArr) {
        send((byte) 10, bArr);
    }

    private synchronized void send(byte b, byte[] bArr) {
        if (this.state != State.CONNECTED) {
            this.eventHandler.onError(new WebSocketException("error while sending data: not connected"));
        } else {
            try {
                this.writer.send(b, true, bArr);
            } catch (IOException e) {
                this.eventHandler.onError(new WebSocketException("Failed to send frame", e));
                close();
            }
        }
        return;
    }

    /* access modifiers changed from: package-private */
    public void handleReceiverError(WebSocketException webSocketException) {
        this.eventHandler.onError(webSocketException);
        if (this.state == State.CONNECTED) {
            close();
        }
        closeSocket();
    }

    /* renamed from: com.google.firebase.database.tubesock.WebSocket$3 */
    static /* synthetic */ class C18653 {
        static final /* synthetic */ int[] $SwitchMap$com$google$firebase$database$tubesock$WebSocket$State;

        /* JADX WARNING: Can't wrap try/catch for region: R(12:0|1|2|3|4|5|6|7|8|9|10|12) */
        /* JADX WARNING: Code restructure failed: missing block: B:13:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0033 */
        static {
            /*
                com.google.firebase.database.tubesock.WebSocket$State[] r0 = com.google.firebase.database.tubesock.WebSocket.State.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$google$firebase$database$tubesock$WebSocket$State = r0
                com.google.firebase.database.tubesock.WebSocket$State r1 = com.google.firebase.database.tubesock.WebSocket.State.NONE     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$com$google$firebase$database$tubesock$WebSocket$State     // Catch:{ NoSuchFieldError -> 0x001d }
                com.google.firebase.database.tubesock.WebSocket$State r1 = com.google.firebase.database.tubesock.WebSocket.State.CONNECTING     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$com$google$firebase$database$tubesock$WebSocket$State     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.google.firebase.database.tubesock.WebSocket$State r1 = com.google.firebase.database.tubesock.WebSocket.State.CONNECTED     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = $SwitchMap$com$google$firebase$database$tubesock$WebSocket$State     // Catch:{ NoSuchFieldError -> 0x0033 }
                com.google.firebase.database.tubesock.WebSocket$State r1 = com.google.firebase.database.tubesock.WebSocket.State.DISCONNECTING     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                int[] r0 = $SwitchMap$com$google$firebase$database$tubesock$WebSocket$State     // Catch:{ NoSuchFieldError -> 0x003e }
                com.google.firebase.database.tubesock.WebSocket$State r1 = com.google.firebase.database.tubesock.WebSocket.State.DISCONNECTED     // Catch:{ NoSuchFieldError -> 0x003e }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x003e }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x003e }
            L_0x003e:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.database.tubesock.WebSocket.C18653.<clinit>():void");
        }
    }

    public synchronized void close() {
        int i = C18653.$SwitchMap$com$google$firebase$database$tubesock$WebSocket$State[this.state.ordinal()];
        if (i == 1) {
            this.state = State.DISCONNECTED;
        } else if (i == 2) {
            closeSocket();
        } else if (i == 3) {
            sendCloseHandshake();
        } else if (i == 4) {
        } else {
            if (i == 5) {
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void onCloseOpReceived() {
        closeSocket();
    }

    private synchronized void closeSocket() {
        if (this.state != State.DISCONNECTED) {
            this.receiver.stopit();
            this.writer.stopIt();
            if (this.socket != null) {
                try {
                    this.socket.close();
                } catch (Exception e) {
                    this.eventHandler.onError(new WebSocketException("Failed to close", e));
                }
            }
            this.state = State.DISCONNECTED;
            this.eventHandler.onClose();
        }
    }

    private void sendCloseHandshake() {
        try {
            this.state = State.DISCONNECTING;
            this.writer.stopIt();
            this.writer.send((byte) 8, true, new byte[0]);
        } catch (IOException e) {
            this.eventHandler.onError(new WebSocketException("Failed to send close frame", e));
        }
    }

    private Socket createSocket() {
        String scheme = this.url.getScheme();
        String host = this.url.getHost();
        int port = this.url.getPort();
        if (scheme != null && scheme.equals("ws")) {
            if (port == -1) {
                port = 80;
            }
            try {
                return new Socket(host, port);
            } catch (UnknownHostException e) {
                throw new WebSocketException("unknown host: " + host, e);
            } catch (IOException e2) {
                throw new WebSocketException("error while creating socket to " + this.url, e2);
            }
        } else if (scheme == null || !scheme.equals("wss")) {
            throw new WebSocketException("unsupported protocol: " + scheme);
        } else {
            if (port == -1) {
                port = GrpcUtil.DEFAULT_PORT_SSL;
            }
            SSLSessionCache sSLSessionCache = null;
            try {
                if (this.sslCacheDirectory != null) {
                    sSLSessionCache = new SSLSessionCache(new File(this.sslCacheDirectory));
                }
            } catch (IOException e3) {
                this.logger.debug("Failed to initialize SSL session cache", e3, new Object[0]);
            }
            try {
                SSLSocket sSLSocket = (SSLSocket) SSLCertificateSocketFactory.getDefault(SSL_HANDSHAKE_TIMEOUT_MS, sSLSessionCache).createSocket(host, port);
                if (HttpsURLConnection.getDefaultHostnameVerifier().verify(host, sSLSocket.getSession())) {
                    return sSLSocket;
                }
                throw new WebSocketException("Error while verifying secure socket to " + this.url);
            } catch (UnknownHostException e4) {
                throw new WebSocketException("unknown host: " + host, e4);
            } catch (IOException e5) {
                throw new WebSocketException("error while creating secure socket to " + this.url, e5);
            }
        }
    }

    public void blockClose() throws InterruptedException {
        if (this.writer.getInnerThread().getState() != Thread.State.NEW) {
            this.writer.getInnerThread().join();
        }
        getInnerThread().join();
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:20:?, code lost:
        r1 = new java.io.DataInputStream(r0.getInputStream());
        r0 = r0.getOutputStream();
        r0.write(r11.handshake.getHandshake());
        r3 = new byte[1000];
        r4 = new java.util.ArrayList();
        r6 = false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0043, code lost:
        r7 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0045, code lost:
        if (r6 != false) goto L_0x00ab;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0047, code lost:
        r9 = r1.read();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x004c, code lost:
        if (r9 == -1) goto L_0x00a3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x004e, code lost:
        r3[r7] = (byte) r9;
        r7 = r7 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x0059, code lost:
        if (r3[r7 - 1] != 10) goto L_0x0082;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x0061, code lost:
        if (r3[r7 - 2] != 13) goto L_0x0082;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x0063, code lost:
        r7 = new java.lang.String(r3, UTF8);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x0074, code lost:
        if (r7.trim().equals("") == false) goto L_0x0078;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x0076, code lost:
        r6 = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x0078, code lost:
        r4.add(r7.trim());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x007f, code lost:
        r3 = new byte[1000];
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x0082, code lost:
        if (r7 == 1000) goto L_0x0085;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x0085, code lost:
        r0 = new java.lang.String(r3, UTF8);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x00a2, code lost:
        throw new com.google.firebase.database.tubesock.WebSocketException("Unexpected long line in handshake: " + r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x00aa, code lost:
        throw new com.google.firebase.database.tubesock.WebSocketException("Connection closed before handshake was complete");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x00ab, code lost:
        r11.handshake.verifyServerStatusLine((java.lang.String) r4.get(0));
        r4.remove(0);
        r2 = new java.util.HashMap();
        r3 = r4.iterator();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:43:0x00c6, code lost:
        if (r3.hasNext() == false) goto L_0x00e9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:0x00c8, code lost:
        r4 = ((java.lang.String) r3.next()).split(": ", 2);
        r2.put(r4[0].toLowerCase(java.util.Locale.US), r4[1].toLowerCase(java.util.Locale.US));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:0x00e9, code lost:
        r11.handshake.verifyServerHandshakeHeaders(r2);
        r11.writer.setOutput(r0);
        r11.receiver.setInput(r1);
        r11.state = com.google.firebase.database.tubesock.WebSocket.State.CONNECTED;
        r11.writer.getInnerThread().start();
        r11.eventHandler.onOpen();
        r11.receiver.run();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void runReader() {
        /*
            r11 = this;
            java.net.Socket r0 = r11.createSocket()     // Catch:{ WebSocketException -> 0x0134, all -> 0x0113 }
            monitor-enter(r11)     // Catch:{ WebSocketException -> 0x0134, all -> 0x0113 }
            r11.socket = r0     // Catch:{ all -> 0x0110 }
            com.google.firebase.database.tubesock.WebSocket$State r1 = r11.state     // Catch:{ all -> 0x0110 }
            com.google.firebase.database.tubesock.WebSocket$State r2 = com.google.firebase.database.tubesock.WebSocket.State.DISCONNECTED     // Catch:{ all -> 0x0110 }
            if (r1 != r2) goto L_0x0021
            java.net.Socket r0 = r11.socket     // Catch:{ IOException -> 0x001a }
            r0.close()     // Catch:{ IOException -> 0x001a }
            r0 = 0
            r11.socket = r0     // Catch:{ all -> 0x0110 }
            monitor-exit(r11)     // Catch:{ all -> 0x0110 }
            r11.close()
            return
        L_0x001a:
            r0 = move-exception
            java.lang.RuntimeException r1 = new java.lang.RuntimeException     // Catch:{ all -> 0x0110 }
            r1.<init>(r0)     // Catch:{ all -> 0x0110 }
            throw r1     // Catch:{ all -> 0x0110 }
        L_0x0021:
            monitor-exit(r11)     // Catch:{ all -> 0x0110 }
            java.io.DataInputStream r1 = new java.io.DataInputStream     // Catch:{ WebSocketException -> 0x0134, all -> 0x0113 }
            java.io.InputStream r2 = r0.getInputStream()     // Catch:{ WebSocketException -> 0x0134, all -> 0x0113 }
            r1.<init>(r2)     // Catch:{ WebSocketException -> 0x0134, all -> 0x0113 }
            java.io.OutputStream r0 = r0.getOutputStream()     // Catch:{ WebSocketException -> 0x0134, all -> 0x0113 }
            com.google.firebase.database.tubesock.WebSocketHandshake r2 = r11.handshake     // Catch:{ WebSocketException -> 0x0134, all -> 0x0113 }
            byte[] r2 = r2.getHandshake()     // Catch:{ WebSocketException -> 0x0134, all -> 0x0113 }
            r0.write(r2)     // Catch:{ WebSocketException -> 0x0134, all -> 0x0113 }
            r2 = 1000(0x3e8, float:1.401E-42)
            byte[] r3 = new byte[r2]     // Catch:{ WebSocketException -> 0x0134, all -> 0x0113 }
            java.util.ArrayList r4 = new java.util.ArrayList     // Catch:{ WebSocketException -> 0x0134, all -> 0x0113 }
            r4.<init>()     // Catch:{ WebSocketException -> 0x0134, all -> 0x0113 }
            r5 = 0
            r6 = 0
        L_0x0043:
            r7 = 0
        L_0x0044:
            r8 = 1
            if (r6 != 0) goto L_0x00ab
            int r9 = r1.read()     // Catch:{ WebSocketException -> 0x0134, all -> 0x0113 }
            r10 = -1
            if (r9 == r10) goto L_0x00a3
            byte r9 = (byte) r9     // Catch:{ WebSocketException -> 0x0134, all -> 0x0113 }
            r3[r7] = r9     // Catch:{ WebSocketException -> 0x0134, all -> 0x0113 }
            int r7 = r7 + 1
            int r9 = r7 + -1
            byte r9 = r3[r9]     // Catch:{ WebSocketException -> 0x0134, all -> 0x0113 }
            r10 = 10
            if (r9 != r10) goto L_0x0082
            int r9 = r7 + -2
            byte r9 = r3[r9]     // Catch:{ WebSocketException -> 0x0134, all -> 0x0113 }
            r10 = 13
            if (r9 != r10) goto L_0x0082
            java.lang.String r7 = new java.lang.String     // Catch:{ WebSocketException -> 0x0134, all -> 0x0113 }
            java.nio.charset.Charset r9 = UTF8     // Catch:{ WebSocketException -> 0x0134, all -> 0x0113 }
            r7.<init>(r3, r9)     // Catch:{ WebSocketException -> 0x0134, all -> 0x0113 }
            java.lang.String r3 = r7.trim()     // Catch:{ WebSocketException -> 0x0134, all -> 0x0113 }
            java.lang.String r9 = ""
            boolean r3 = r3.equals(r9)     // Catch:{ WebSocketException -> 0x0134, all -> 0x0113 }
            if (r3 == 0) goto L_0x0078
            r6 = 1
            goto L_0x007f
        L_0x0078:
            java.lang.String r3 = r7.trim()     // Catch:{ WebSocketException -> 0x0134, all -> 0x0113 }
            r4.add(r3)     // Catch:{ WebSocketException -> 0x0134, all -> 0x0113 }
        L_0x007f:
            byte[] r3 = new byte[r2]     // Catch:{ WebSocketException -> 0x0134, all -> 0x0113 }
            goto L_0x0043
        L_0x0082:
            if (r7 == r2) goto L_0x0085
            goto L_0x0044
        L_0x0085:
            java.lang.String r0 = new java.lang.String     // Catch:{ WebSocketException -> 0x0134, all -> 0x0113 }
            java.nio.charset.Charset r1 = UTF8     // Catch:{ WebSocketException -> 0x0134, all -> 0x0113 }
            r0.<init>(r3, r1)     // Catch:{ WebSocketException -> 0x0134, all -> 0x0113 }
            com.google.firebase.database.tubesock.WebSocketException r1 = new com.google.firebase.database.tubesock.WebSocketException     // Catch:{ WebSocketException -> 0x0134, all -> 0x0113 }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ WebSocketException -> 0x0134, all -> 0x0113 }
            r2.<init>()     // Catch:{ WebSocketException -> 0x0134, all -> 0x0113 }
            java.lang.String r3 = "Unexpected long line in handshake: "
            r2.append(r3)     // Catch:{ WebSocketException -> 0x0134, all -> 0x0113 }
            r2.append(r0)     // Catch:{ WebSocketException -> 0x0134, all -> 0x0113 }
            java.lang.String r0 = r2.toString()     // Catch:{ WebSocketException -> 0x0134, all -> 0x0113 }
            r1.<init>(r0)     // Catch:{ WebSocketException -> 0x0134, all -> 0x0113 }
            throw r1     // Catch:{ WebSocketException -> 0x0134, all -> 0x0113 }
        L_0x00a3:
            com.google.firebase.database.tubesock.WebSocketException r0 = new com.google.firebase.database.tubesock.WebSocketException     // Catch:{ WebSocketException -> 0x0134, all -> 0x0113 }
            java.lang.String r1 = "Connection closed before handshake was complete"
            r0.<init>(r1)     // Catch:{ WebSocketException -> 0x0134, all -> 0x0113 }
            throw r0     // Catch:{ WebSocketException -> 0x0134, all -> 0x0113 }
        L_0x00ab:
            com.google.firebase.database.tubesock.WebSocketHandshake r2 = r11.handshake     // Catch:{ WebSocketException -> 0x0134, all -> 0x0113 }
            java.lang.Object r3 = r4.get(r5)     // Catch:{ WebSocketException -> 0x0134, all -> 0x0113 }
            java.lang.String r3 = (java.lang.String) r3     // Catch:{ WebSocketException -> 0x0134, all -> 0x0113 }
            r2.verifyServerStatusLine(r3)     // Catch:{ WebSocketException -> 0x0134, all -> 0x0113 }
            r4.remove(r5)     // Catch:{ WebSocketException -> 0x0134, all -> 0x0113 }
            java.util.HashMap r2 = new java.util.HashMap     // Catch:{ WebSocketException -> 0x0134, all -> 0x0113 }
            r2.<init>()     // Catch:{ WebSocketException -> 0x0134, all -> 0x0113 }
            java.util.Iterator r3 = r4.iterator()     // Catch:{ WebSocketException -> 0x0134, all -> 0x0113 }
        L_0x00c2:
            boolean r4 = r3.hasNext()     // Catch:{ WebSocketException -> 0x0134, all -> 0x0113 }
            if (r4 == 0) goto L_0x00e9
            java.lang.Object r4 = r3.next()     // Catch:{ WebSocketException -> 0x0134, all -> 0x0113 }
            java.lang.String r4 = (java.lang.String) r4     // Catch:{ WebSocketException -> 0x0134, all -> 0x0113 }
            java.lang.String r6 = ": "
            r7 = 2
            java.lang.String[] r4 = r4.split(r6, r7)     // Catch:{ WebSocketException -> 0x0134, all -> 0x0113 }
            r6 = r4[r5]     // Catch:{ WebSocketException -> 0x0134, all -> 0x0113 }
            java.util.Locale r7 = java.util.Locale.US     // Catch:{ WebSocketException -> 0x0134, all -> 0x0113 }
            java.lang.String r6 = r6.toLowerCase(r7)     // Catch:{ WebSocketException -> 0x0134, all -> 0x0113 }
            r4 = r4[r8]     // Catch:{ WebSocketException -> 0x0134, all -> 0x0113 }
            java.util.Locale r7 = java.util.Locale.US     // Catch:{ WebSocketException -> 0x0134, all -> 0x0113 }
            java.lang.String r4 = r4.toLowerCase(r7)     // Catch:{ WebSocketException -> 0x0134, all -> 0x0113 }
            r2.put(r6, r4)     // Catch:{ WebSocketException -> 0x0134, all -> 0x0113 }
            goto L_0x00c2
        L_0x00e9:
            com.google.firebase.database.tubesock.WebSocketHandshake r3 = r11.handshake     // Catch:{ WebSocketException -> 0x0134, all -> 0x0113 }
            r3.verifyServerHandshakeHeaders(r2)     // Catch:{ WebSocketException -> 0x0134, all -> 0x0113 }
            com.google.firebase.database.tubesock.WebSocketWriter r2 = r11.writer     // Catch:{ WebSocketException -> 0x0134, all -> 0x0113 }
            r2.setOutput(r0)     // Catch:{ WebSocketException -> 0x0134, all -> 0x0113 }
            com.google.firebase.database.tubesock.WebSocketReceiver r0 = r11.receiver     // Catch:{ WebSocketException -> 0x0134, all -> 0x0113 }
            r0.setInput(r1)     // Catch:{ WebSocketException -> 0x0134, all -> 0x0113 }
            com.google.firebase.database.tubesock.WebSocket$State r0 = com.google.firebase.database.tubesock.WebSocket.State.CONNECTED     // Catch:{ WebSocketException -> 0x0134, all -> 0x0113 }
            r11.state = r0     // Catch:{ WebSocketException -> 0x0134, all -> 0x0113 }
            com.google.firebase.database.tubesock.WebSocketWriter r0 = r11.writer     // Catch:{ WebSocketException -> 0x0134, all -> 0x0113 }
            java.lang.Thread r0 = r0.getInnerThread()     // Catch:{ WebSocketException -> 0x0134, all -> 0x0113 }
            r0.start()     // Catch:{ WebSocketException -> 0x0134, all -> 0x0113 }
            com.google.firebase.database.tubesock.WebSocketEventHandler r0 = r11.eventHandler     // Catch:{ WebSocketException -> 0x0134, all -> 0x0113 }
            r0.onOpen()     // Catch:{ WebSocketException -> 0x0134, all -> 0x0113 }
            com.google.firebase.database.tubesock.WebSocketReceiver r0 = r11.receiver     // Catch:{ WebSocketException -> 0x0134, all -> 0x0113 }
            r0.run()     // Catch:{ WebSocketException -> 0x0134, all -> 0x0113 }
            goto L_0x013a
        L_0x0110:
            r0 = move-exception
            monitor-exit(r11)     // Catch:{ all -> 0x0110 }
            throw r0     // Catch:{ WebSocketException -> 0x0134, all -> 0x0113 }
        L_0x0113:
            r0 = move-exception
            com.google.firebase.database.tubesock.WebSocketEventHandler r1 = r11.eventHandler     // Catch:{ all -> 0x013e }
            com.google.firebase.database.tubesock.WebSocketException r2 = new com.google.firebase.database.tubesock.WebSocketException     // Catch:{ all -> 0x013e }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ all -> 0x013e }
            r3.<init>()     // Catch:{ all -> 0x013e }
            java.lang.String r4 = "error while connecting: "
            r3.append(r4)     // Catch:{ all -> 0x013e }
            java.lang.String r4 = r0.getMessage()     // Catch:{ all -> 0x013e }
            r3.append(r4)     // Catch:{ all -> 0x013e }
            java.lang.String r3 = r3.toString()     // Catch:{ all -> 0x013e }
            r2.<init>(r3, r0)     // Catch:{ all -> 0x013e }
            r1.onError(r2)     // Catch:{ all -> 0x013e }
            goto L_0x013a
        L_0x0134:
            r0 = move-exception
            com.google.firebase.database.tubesock.WebSocketEventHandler r1 = r11.eventHandler     // Catch:{ all -> 0x013e }
            r1.onError(r0)     // Catch:{ all -> 0x013e }
        L_0x013a:
            r11.close()
            return
        L_0x013e:
            r0 = move-exception
            r11.close()
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.database.tubesock.WebSocket.runReader():void");
    }

    /* access modifiers changed from: package-private */
    public Thread getInnerThread() {
        return this.innerThread;
    }
}

package com.google.firebase.database.tubesock;

import android.util.Base64;
import com.google.common.net.HttpHeaders;
import java.net.URI;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

class WebSocketHandshake {
    private static final String WEBSOCKET_VERSION = "13";
    private Map<String, String> extraHeaders = null;
    private String nonce = null;
    private String protocol = null;
    private URI url = null;

    public WebSocketHandshake(URI uri, String str, Map<String, String> map) {
        this.url = uri;
        this.protocol = str;
        this.extraHeaders = map;
        this.nonce = createNonce();
    }

    public byte[] getHandshake() {
        String str;
        String path = this.url.getPath();
        String query = this.url.getQuery();
        StringBuilder sb = new StringBuilder();
        sb.append(path);
        if (query == null) {
            str = "";
        } else {
            str = "?" + query;
        }
        sb.append(str);
        String sb2 = sb.toString();
        String host = this.url.getHost();
        if (this.url.getPort() != -1) {
            host = host + ":" + this.url.getPort();
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put(HttpHeaders.HOST, host);
        linkedHashMap.put(HttpHeaders.UPGRADE, "websocket");
        linkedHashMap.put(HttpHeaders.CONNECTION, HttpHeaders.UPGRADE);
        linkedHashMap.put(HttpHeaders.SEC_WEBSOCKET_VERSION, WEBSOCKET_VERSION);
        linkedHashMap.put(HttpHeaders.SEC_WEBSOCKET_KEY, this.nonce);
        String str2 = this.protocol;
        if (str2 != null) {
            linkedHashMap.put(HttpHeaders.SEC_WEBSOCKET_PROTOCOL, str2);
        }
        Map<String, String> map = this.extraHeaders;
        if (map != null) {
            for (String next : map.keySet()) {
                if (!linkedHashMap.containsKey(next)) {
                    linkedHashMap.put(next, this.extraHeaders.get(next));
                }
            }
        }
        byte[] bytes = ((("GET " + sb2 + " HTTP/1.1\r\n") + generateHeader(linkedHashMap)) + "\r\n").getBytes(Charset.defaultCharset());
        byte[] bArr = new byte[bytes.length];
        System.arraycopy(bytes, 0, bArr, 0, bytes.length);
        return bArr;
    }

    private String generateHeader(LinkedHashMap<String, String> linkedHashMap) {
        String str = new String();
        for (String next : linkedHashMap.keySet()) {
            str = str + next + ": " + linkedHashMap.get(next) + "\r\n";
        }
        return str;
    }

    private String createNonce() {
        byte[] bArr = new byte[16];
        for (int i = 0; i < 16; i++) {
            bArr[i] = (byte) rand(0, 255);
        }
        return Base64.encodeToString(bArr, 2);
    }

    public void verifyServerStatusLine(String str) {
        int parseInt = Integer.parseInt(str.substring(9, 12));
        if (parseInt == 407) {
            throw new WebSocketException("connection failed: proxy authentication not supported");
        } else if (parseInt == 404) {
            throw new WebSocketException("connection failed: 404 not found");
        } else if (parseInt != 101) {
            throw new WebSocketException("connection failed: unknown status code " + parseInt);
        }
    }

    public void verifyServerHandshakeHeaders(HashMap<String, String> hashMap) {
        if (!"websocket".equals(hashMap.get("upgrade"))) {
            throw new WebSocketException("connection failed: missing header field in server handshake: Upgrade");
        } else if (!"upgrade".equals(hashMap.get("connection"))) {
            throw new WebSocketException("connection failed: missing header field in server handshake: Connection");
        }
    }

    private int rand(int i, int i2) {
        return (int) ((Math.random() * ((double) i2)) + ((double) i));
    }
}

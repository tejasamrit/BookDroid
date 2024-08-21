package com.google.firebase.database.tubesock;

import com.google.common.base.Ascii;
import com.google.firebase.database.tubesock.MessageBuilderFactory;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.SocketTimeoutException;

class WebSocketReceiver {
    private WebSocketEventHandler eventHandler = null;
    private DataInputStream input = null;
    private byte[] inputHeader = new byte[112];
    private MessageBuilderFactory.Builder pendingBuilder;
    private volatile boolean stop = false;
    private WebSocket websocket = null;

    WebSocketReceiver(WebSocket webSocket) {
        this.websocket = webSocket;
    }

    /* access modifiers changed from: package-private */
    public void setInput(DataInputStream dataInputStream) {
        this.input = dataInputStream;
    }

    /* access modifiers changed from: package-private */
    public void run() {
        this.eventHandler = this.websocket.getEventHandler();
        while (!this.stop) {
            try {
                int read = read(this.inputHeader, 0, 1) + 0;
                byte[] bArr = this.inputHeader;
                boolean z = (bArr[0] & 128) != 0;
                if (!((bArr[0] & 112) != 0)) {
                    byte b = (byte) (bArr[0] & Ascii.f233SI);
                    int read2 = read + read(bArr, read, 1);
                    byte[] bArr2 = this.inputHeader;
                    byte b2 = bArr2[1];
                    long j = 0;
                    if (b2 < 126) {
                        j = (long) b2;
                    } else if (b2 == 126) {
                        read(bArr2, read2, 2);
                        byte[] bArr3 = this.inputHeader;
                        j = (((long) (bArr3[2] & 255)) << 8) | ((long) (bArr3[3] & 255));
                    } else if (b2 == Byte.MAX_VALUE) {
                        j = parseLong(this.inputHeader, (read2 + read(bArr2, read2, 8)) - 8);
                    }
                    int i = (int) j;
                    byte[] bArr4 = new byte[i];
                    read(bArr4, 0, i);
                    if (b == 8) {
                        this.websocket.onCloseOpReceived();
                    } else if (b != 10) {
                        if (!(b == 1 || b == 2 || b == 9)) {
                            if (b != 0) {
                                throw new WebSocketException("Unsupported opcode: " + b);
                            }
                        }
                        appendBytes(z, b, bArr4);
                    }
                } else {
                    throw new WebSocketException("Invalid frame received");
                }
            } catch (SocketTimeoutException unused) {
            } catch (IOException e) {
                handleError(new WebSocketException("IO Error", e));
            } catch (WebSocketException e2) {
                handleError(e2);
            }
        }
    }

    private void appendBytes(boolean z, byte b, byte[] bArr) {
        if (b != 9) {
            MessageBuilderFactory.Builder builder = this.pendingBuilder;
            if (builder != null && b != 0) {
                throw new WebSocketException("Failed to continue outstanding frame");
            } else if (builder == null && b == 0) {
                throw new WebSocketException("Received continuing frame, but there's nothing to continue");
            } else {
                if (builder == null) {
                    this.pendingBuilder = MessageBuilderFactory.builder(b);
                }
                if (!this.pendingBuilder.appendBytes(bArr)) {
                    throw new WebSocketException("Failed to decode frame");
                } else if (z) {
                    WebSocketMessage message = this.pendingBuilder.toMessage();
                    this.pendingBuilder = null;
                    if (message != null) {
                        this.eventHandler.onMessage(message);
                        return;
                    }
                    throw new WebSocketException("Failed to decode whole message");
                }
            }
        } else if (z) {
            handlePing(bArr);
        } else {
            throw new WebSocketException("PING must not fragment across frames");
        }
    }

    private void handlePing(byte[] bArr) {
        if (bArr.length <= 125) {
            this.websocket.pong(bArr);
            return;
        }
        throw new WebSocketException("PING frame too long");
    }

    private long parseLong(byte[] bArr, int i) {
        return (((long) bArr[i + 0]) << 56) + (((long) (bArr[i + 1] & 255)) << 48) + (((long) (bArr[i + 2] & 255)) << 40) + (((long) (bArr[i + 3] & 255)) << 32) + (((long) (bArr[i + 4] & 255)) << 24) + ((long) ((bArr[i + 5] & 255) << Ascii.DLE)) + ((long) ((bArr[i + 6] & 255) << 8)) + ((long) ((bArr[i + 7] & 255) << 0));
    }

    private int read(byte[] bArr, int i, int i2) throws IOException {
        this.input.readFully(bArr, i, i2);
        return i2;
    }

    /* access modifiers changed from: package-private */
    public void stopit() {
        this.stop = true;
    }

    /* access modifiers changed from: package-private */
    public boolean isRunning() {
        return !this.stop;
    }

    private void handleError(WebSocketException webSocketException) {
        stopit();
        this.websocket.handleReceiverError(webSocketException);
    }
}

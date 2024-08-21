package com.google.firebase.messaging;

import java.util.concurrent.Callable;

/* compiled from: com.google.firebase:firebase-messaging@@21.0.1 */
final /* synthetic */ class ImageDownload$$Lambda$0 implements Callable {
    private final ImageDownload arg$1;

    ImageDownload$$Lambda$0(ImageDownload imageDownload) {
        this.arg$1 = imageDownload;
    }

    public Object call() {
        return this.arg$1.blockingDownload();
    }
}

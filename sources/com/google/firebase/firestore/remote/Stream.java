package com.google.firebase.firestore.remote;

import com.google.firebase.firestore.remote.Stream.StreamCallback;
import p012io.grpc.Status;

public interface Stream<CallbackType extends StreamCallback> {

    public enum State {
        Initial,
        Starting,
        Open,
        Error,
        Backoff
    }

    public interface StreamCallback {
        void onClose(Status status);

        void onOpen();
    }

    void inhibitBackoff();

    boolean isOpen();

    boolean isStarted();

    void start();

    void stop();
}

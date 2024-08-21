package com.google.firebase.storage;

import java.io.IOException;

class CancelException extends IOException {
    CancelException() {
        super("The operation was canceled.");
    }
}

package com.google.firebase.storage;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.TaskCompletionSource;

/* compiled from: StorageTask */
final /* synthetic */ class StorageTask$$Lambda$13 implements OnSuccessListener {
    private final TaskCompletionSource arg$1;

    private StorageTask$$Lambda$13(TaskCompletionSource taskCompletionSource) {
        this.arg$1 = taskCompletionSource;
    }

    public static OnSuccessListener lambdaFactory$(TaskCompletionSource taskCompletionSource) {
        return new StorageTask$$Lambda$13(taskCompletionSource);
    }

    public void onSuccess(Object obj) {
        this.arg$1.setResult(obj);
    }
}

package com.google.firebase.storage;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.TaskListenerImpl;

/* compiled from: StorageTask */
final /* synthetic */ class StorageTask$$Lambda$1 implements TaskListenerImpl.OnRaise {
    private final StorageTask arg$1;

    private StorageTask$$Lambda$1(StorageTask storageTask) {
        this.arg$1 = storageTask;
    }

    public static TaskListenerImpl.OnRaise lambdaFactory$(StorageTask storageTask) {
        return new StorageTask$$Lambda$1(storageTask);
    }

    public void raise(Object obj, Object obj2) {
        StorageTask.lambda$new$0(this.arg$1, (OnSuccessListener) obj, (StorageTask.ProvideError) obj2);
    }
}

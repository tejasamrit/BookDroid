package com.google.firebase.firestore.util;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;

/* compiled from: AsyncQueue */
final /* synthetic */ class AsyncQueue$$Lambda$7 implements Continuation {
    private final TaskCompletionSource arg$1;

    private AsyncQueue$$Lambda$7(TaskCompletionSource taskCompletionSource) {
        this.arg$1 = taskCompletionSource;
    }

    public static Continuation lambdaFactory$(TaskCompletionSource taskCompletionSource) {
        return new AsyncQueue$$Lambda$7(taskCompletionSource);
    }

    public Object then(Task task) {
        return AsyncQueue.lambda$callTask$0(this.arg$1, task);
    }
}

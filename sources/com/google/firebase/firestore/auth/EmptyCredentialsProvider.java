package com.google.firebase.firestore.auth;

import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.firebase.firestore.util.C1920Listener;

public class EmptyCredentialsProvider extends CredentialsProvider {
    public void invalidateToken() {
    }

    public void removeChangeListener() {
    }

    public Task<String> getToken() {
        TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        taskCompletionSource.setResult(null);
        return taskCompletionSource.getTask();
    }

    public void setChangeListener(C1920Listener<User> listener) {
        listener.onValue(User.UNAUTHENTICATED);
    }
}

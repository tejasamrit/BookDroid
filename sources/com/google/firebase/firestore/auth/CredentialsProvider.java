package com.google.firebase.firestore.auth;

import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.util.C1920Listener;

public abstract class CredentialsProvider {
    public abstract Task<String> getToken();

    public abstract void invalidateToken();

    public abstract void removeChangeListener();

    public abstract void setChangeListener(C1920Listener<User> listener);
}

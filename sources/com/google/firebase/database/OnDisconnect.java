package com.google.firebase.database;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.core.Path;
import com.google.firebase.database.core.Repo;
import com.google.firebase.database.core.ValidationPath;
import com.google.firebase.database.core.utilities.Pair;
import com.google.firebase.database.core.utilities.Utilities;
import com.google.firebase.database.core.utilities.Validation;
import com.google.firebase.database.core.utilities.encoding.CustomClassMapper;
import com.google.firebase.database.snapshot.Node;
import com.google.firebase.database.snapshot.NodeUtilities;
import com.google.firebase.database.snapshot.PriorityUtilities;
import java.util.Map;

public class OnDisconnect {
    /* access modifiers changed from: private */
    public Path path;
    /* access modifiers changed from: private */
    public Repo repo;

    OnDisconnect(Repo repo2, Path path2) {
        this.repo = repo2;
        this.path = path2;
    }

    public Task<Void> setValue(Object obj) {
        return onDisconnectSetInternal(obj, PriorityUtilities.NullPriority(), (DatabaseReference.CompletionListener) null);
    }

    public Task<Void> setValue(Object obj, String str) {
        return onDisconnectSetInternal(obj, PriorityUtilities.parsePriority(this.path, str), (DatabaseReference.CompletionListener) null);
    }

    public Task<Void> setValue(Object obj, double d) {
        return onDisconnectSetInternal(obj, PriorityUtilities.parsePriority(this.path, Double.valueOf(d)), (DatabaseReference.CompletionListener) null);
    }

    public void setValue(Object obj, DatabaseReference.CompletionListener completionListener) {
        onDisconnectSetInternal(obj, PriorityUtilities.NullPriority(), completionListener);
    }

    public void setValue(Object obj, String str, DatabaseReference.CompletionListener completionListener) {
        onDisconnectSetInternal(obj, PriorityUtilities.parsePriority(this.path, str), completionListener);
    }

    public void setValue(Object obj, double d, DatabaseReference.CompletionListener completionListener) {
        onDisconnectSetInternal(obj, PriorityUtilities.parsePriority(this.path, Double.valueOf(d)), completionListener);
    }

    public void setValue(Object obj, Map map, DatabaseReference.CompletionListener completionListener) {
        onDisconnectSetInternal(obj, PriorityUtilities.parsePriority(this.path, map), completionListener);
    }

    private Task<Void> onDisconnectSetInternal(Object obj, Node node, DatabaseReference.CompletionListener completionListener) {
        Validation.validateWritablePath(this.path);
        ValidationPath.validateWithObject(this.path, obj);
        Object convertToPlainJavaTypes = CustomClassMapper.convertToPlainJavaTypes(obj);
        Validation.validateWritableObject(convertToPlainJavaTypes);
        final Node NodeFromJSON = NodeUtilities.NodeFromJSON(convertToPlainJavaTypes, node);
        final Pair<Task<Void>, DatabaseReference.CompletionListener> wrapOnComplete = Utilities.wrapOnComplete(completionListener);
        this.repo.scheduleNow(new Runnable() {
            public void run() {
                OnDisconnect.this.repo.onDisconnectSetValue(OnDisconnect.this.path, NodeFromJSON, (DatabaseReference.CompletionListener) wrapOnComplete.getSecond());
            }
        });
        return wrapOnComplete.getFirst();
    }

    public Task<Void> updateChildren(Map<String, Object> map) {
        return updateChildrenInternal(map, (DatabaseReference.CompletionListener) null);
    }

    public void updateChildren(Map<String, Object> map, DatabaseReference.CompletionListener completionListener) {
        updateChildrenInternal(map, completionListener);
    }

    private Task<Void> updateChildrenInternal(final Map<String, Object> map, DatabaseReference.CompletionListener completionListener) {
        final Map<Path, Node> parseAndValidateUpdate = Validation.parseAndValidateUpdate(this.path, map);
        final Pair<Task<Void>, DatabaseReference.CompletionListener> wrapOnComplete = Utilities.wrapOnComplete(completionListener);
        this.repo.scheduleNow(new Runnable() {
            public void run() {
                OnDisconnect.this.repo.onDisconnectUpdate(OnDisconnect.this.path, parseAndValidateUpdate, (DatabaseReference.CompletionListener) wrapOnComplete.getSecond(), map);
            }
        });
        return wrapOnComplete.getFirst();
    }

    public Task<Void> removeValue() {
        return setValue((Object) null);
    }

    public void removeValue(DatabaseReference.CompletionListener completionListener) {
        setValue((Object) null, completionListener);
    }

    public Task<Void> cancel() {
        return cancelInternal((DatabaseReference.CompletionListener) null);
    }

    public void cancel(DatabaseReference.CompletionListener completionListener) {
        cancelInternal(completionListener);
    }

    private Task<Void> cancelInternal(DatabaseReference.CompletionListener completionListener) {
        final Pair<Task<Void>, DatabaseReference.CompletionListener> wrapOnComplete = Utilities.wrapOnComplete(completionListener);
        this.repo.scheduleNow(new Runnable() {
            public void run() {
                OnDisconnect.this.repo.onDisconnectCancel(OnDisconnect.this.path, (DatabaseReference.CompletionListener) wrapOnComplete.getSecond());
            }
        });
        return wrapOnComplete.getFirst();
    }
}

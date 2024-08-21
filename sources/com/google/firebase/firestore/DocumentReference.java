package com.google.firebase.firestore;

import android.app.Activity;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.core.ActivityScope;
import com.google.firebase.firestore.core.AsyncEventListener;
import com.google.firebase.firestore.core.EventManager;
import com.google.firebase.firestore.core.ListenerRegistrationImpl;
import com.google.firebase.firestore.core.Query;
import com.google.firebase.firestore.core.UserData;
import com.google.firebase.firestore.core.ViewSnapshot;
import com.google.firebase.firestore.model.Document;
import com.google.firebase.firestore.model.DocumentKey;
import com.google.firebase.firestore.model.ResourcePath;
import com.google.firebase.firestore.model.mutation.DeleteMutation;
import com.google.firebase.firestore.model.mutation.Precondition;
import com.google.firebase.firestore.util.Assert;
import com.google.firebase.firestore.util.Executors;
import com.google.firebase.firestore.util.Preconditions;
import com.google.firebase.firestore.util.Util;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;

public class DocumentReference {
    private final FirebaseFirestore firestore;
    private final DocumentKey key;

    DocumentReference(DocumentKey documentKey, FirebaseFirestore firebaseFirestore) {
        this.key = (DocumentKey) Preconditions.checkNotNull(documentKey);
        this.firestore = firebaseFirestore;
    }

    static DocumentReference forPath(ResourcePath resourcePath, FirebaseFirestore firebaseFirestore) {
        if (resourcePath.length() % 2 == 0) {
            return new DocumentReference(DocumentKey.fromPath(resourcePath), firebaseFirestore);
        }
        throw new IllegalArgumentException("Invalid document reference. Document references must have an even number of segments, but " + resourcePath.canonicalString() + " has " + resourcePath.length());
    }

    /* access modifiers changed from: package-private */
    public DocumentKey getKey() {
        return this.key;
    }

    public FirebaseFirestore getFirestore() {
        return this.firestore;
    }

    public String getId() {
        return this.key.getPath().getLastSegment();
    }

    public CollectionReference getParent() {
        return new CollectionReference((ResourcePath) this.key.getPath().popLast(), this.firestore);
    }

    public String getPath() {
        return this.key.getPath().canonicalString();
    }

    public CollectionReference collection(String str) {
        Preconditions.checkNotNull(str, "Provided collection path must not be null.");
        return new CollectionReference((ResourcePath) this.key.getPath().append(ResourcePath.fromString(str)), this.firestore);
    }

    public Task<Void> set(Object obj) {
        return set(obj, SetOptions.OVERWRITE);
    }

    public Task<Void> set(Object obj, SetOptions setOptions) {
        UserData.ParsedSetData parsedSetData;
        Preconditions.checkNotNull(obj, "Provided data must not be null.");
        Preconditions.checkNotNull(setOptions, "Provided options must not be null.");
        if (setOptions.isMerge()) {
            parsedSetData = this.firestore.getUserDataReader().parseMergeData(obj, setOptions.getFieldMask());
        } else {
            parsedSetData = this.firestore.getUserDataReader().parseSetData(obj);
        }
        return this.firestore.getClient().write(parsedSetData.toMutationList(this.key, Precondition.NONE)).continueWith(Executors.DIRECT_EXECUTOR, Util.voidErrorTransformer());
    }

    public Task<Void> update(Map<String, Object> map) {
        return update(this.firestore.getUserDataReader().parseUpdateData(map));
    }

    public Task<Void> update(String str, Object obj, Object... objArr) {
        return update(this.firestore.getUserDataReader().parseUpdateData(Util.collectUpdateArguments(1, str, obj, objArr)));
    }

    public Task<Void> update(FieldPath fieldPath, Object obj, Object... objArr) {
        return update(this.firestore.getUserDataReader().parseUpdateData(Util.collectUpdateArguments(1, fieldPath, obj, objArr)));
    }

    private Task<Void> update(UserData.ParsedUpdateData parsedUpdateData) {
        return this.firestore.getClient().write(parsedUpdateData.toMutationList(this.key, Precondition.exists(true))).continueWith(Executors.DIRECT_EXECUTOR, Util.voidErrorTransformer());
    }

    public Task<Void> delete() {
        return this.firestore.getClient().write(Collections.singletonList(new DeleteMutation(this.key, Precondition.NONE))).continueWith(Executors.DIRECT_EXECUTOR, Util.voidErrorTransformer());
    }

    public Task<DocumentSnapshot> get() {
        return get(Source.DEFAULT);
    }

    public Task<DocumentSnapshot> get(Source source) {
        if (source == Source.CACHE) {
            return this.firestore.getClient().getDocumentFromLocalCache(this.key).continueWith(Executors.DIRECT_EXECUTOR, DocumentReference$$Lambda$1.lambdaFactory$(this));
        }
        return getViaSnapshotListener(source);
    }

    static /* synthetic */ DocumentSnapshot lambda$get$0(DocumentReference documentReference, Task task) throws Exception {
        Document document = (Document) task.getResult();
        return new DocumentSnapshot(documentReference.firestore, documentReference.key, document, true, document != null && document.hasLocalMutations());
    }

    private Task<DocumentSnapshot> getViaSnapshotListener(Source source) {
        TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        TaskCompletionSource taskCompletionSource2 = new TaskCompletionSource();
        EventManager.ListenOptions listenOptions = new EventManager.ListenOptions();
        listenOptions.includeDocumentMetadataChanges = true;
        listenOptions.includeQueryMetadataChanges = true;
        listenOptions.waitForSyncWhenOnline = true;
        taskCompletionSource2.setResult(addSnapshotListenerInternal(Executors.DIRECT_EXECUTOR, listenOptions, (Activity) null, DocumentReference$$Lambda$2.lambdaFactory$(taskCompletionSource, taskCompletionSource2, source)));
        return taskCompletionSource.getTask();
    }

    static /* synthetic */ void lambda$getViaSnapshotListener$1(TaskCompletionSource taskCompletionSource, TaskCompletionSource taskCompletionSource2, Source source, DocumentSnapshot documentSnapshot, FirebaseFirestoreException firebaseFirestoreException) {
        if (firebaseFirestoreException != null) {
            taskCompletionSource.setException(firebaseFirestoreException);
            return;
        }
        try {
            ((ListenerRegistration) Tasks.await(taskCompletionSource2.getTask())).remove();
            if (!documentSnapshot.exists() && documentSnapshot.getMetadata().isFromCache()) {
                taskCompletionSource.setException(new FirebaseFirestoreException("Failed to get document because the client is offline.", FirebaseFirestoreException.Code.UNAVAILABLE));
            } else if (!documentSnapshot.exists() || !documentSnapshot.getMetadata().isFromCache() || source != Source.SERVER) {
                taskCompletionSource.setResult(documentSnapshot);
            } else {
                taskCompletionSource.setException(new FirebaseFirestoreException("Failed to get document from server. (However, this document does exist in the local cache. Run again without setting source to SERVER to retrieve the cached document.)", FirebaseFirestoreException.Code.UNAVAILABLE));
            }
        } catch (ExecutionException e) {
            throw Assert.fail(e, "Failed to register a listener for a single document", new Object[0]);
        } catch (InterruptedException e2) {
            Thread.currentThread().interrupt();
            throw Assert.fail(e2, "Failed to register a listener for a single document", new Object[0]);
        }
    }

    public ListenerRegistration addSnapshotListener(EventListener<DocumentSnapshot> eventListener) {
        return addSnapshotListener(MetadataChanges.EXCLUDE, eventListener);
    }

    public ListenerRegistration addSnapshotListener(Executor executor, EventListener<DocumentSnapshot> eventListener) {
        return addSnapshotListener(executor, MetadataChanges.EXCLUDE, eventListener);
    }

    public ListenerRegistration addSnapshotListener(Activity activity, EventListener<DocumentSnapshot> eventListener) {
        return addSnapshotListener(activity, MetadataChanges.EXCLUDE, eventListener);
    }

    public ListenerRegistration addSnapshotListener(MetadataChanges metadataChanges, EventListener<DocumentSnapshot> eventListener) {
        return addSnapshotListener(Executors.DEFAULT_CALLBACK_EXECUTOR, metadataChanges, eventListener);
    }

    public ListenerRegistration addSnapshotListener(Executor executor, MetadataChanges metadataChanges, EventListener<DocumentSnapshot> eventListener) {
        Preconditions.checkNotNull(executor, "Provided executor must not be null.");
        Preconditions.checkNotNull(metadataChanges, "Provided MetadataChanges value must not be null.");
        Preconditions.checkNotNull(eventListener, "Provided EventListener must not be null.");
        return addSnapshotListenerInternal(executor, internalOptions(metadataChanges), (Activity) null, eventListener);
    }

    public ListenerRegistration addSnapshotListener(Activity activity, MetadataChanges metadataChanges, EventListener<DocumentSnapshot> eventListener) {
        Preconditions.checkNotNull(activity, "Provided activity must not be null.");
        Preconditions.checkNotNull(metadataChanges, "Provided MetadataChanges value must not be null.");
        Preconditions.checkNotNull(eventListener, "Provided EventListener must not be null.");
        return addSnapshotListenerInternal(Executors.DEFAULT_CALLBACK_EXECUTOR, internalOptions(metadataChanges), activity, eventListener);
    }

    private ListenerRegistration addSnapshotListenerInternal(Executor executor, EventManager.ListenOptions listenOptions, Activity activity, EventListener<DocumentSnapshot> eventListener) {
        AsyncEventListener asyncEventListener = new AsyncEventListener(executor, DocumentReference$$Lambda$3.lambdaFactory$(this, eventListener));
        return ActivityScope.bind(activity, new ListenerRegistrationImpl(this.firestore.getClient(), this.firestore.getClient().listen(asQuery(), listenOptions, asyncEventListener), asyncEventListener));
    }

    static /* synthetic */ void lambda$addSnapshotListenerInternal$2(DocumentReference documentReference, EventListener eventListener, ViewSnapshot viewSnapshot, FirebaseFirestoreException firebaseFirestoreException) {
        DocumentSnapshot documentSnapshot;
        if (firebaseFirestoreException != null) {
            eventListener.onEvent(null, firebaseFirestoreException);
            return;
        }
        boolean z = true;
        Assert.hardAssert(viewSnapshot != null, "Got event without value or error set", new Object[0]);
        if (viewSnapshot.getDocuments().size() > 1) {
            z = false;
        }
        Assert.hardAssert(z, "Too many documents returned on a document query", new Object[0]);
        Document document = viewSnapshot.getDocuments().getDocument(documentReference.key);
        if (document != null) {
            documentSnapshot = DocumentSnapshot.fromDocument(documentReference.firestore, document, viewSnapshot.isFromCache(), viewSnapshot.getMutatedKeys().contains(document.getKey()));
        } else {
            documentSnapshot = DocumentSnapshot.fromNoDocument(documentReference.firestore, documentReference.key, viewSnapshot.isFromCache(), false);
        }
        eventListener.onEvent(documentSnapshot, (FirebaseFirestoreException) null);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof DocumentReference)) {
            return false;
        }
        DocumentReference documentReference = (DocumentReference) obj;
        if (!this.key.equals(documentReference.key) || !this.firestore.equals(documentReference.firestore)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return (this.key.hashCode() * 31) + this.firestore.hashCode();
    }

    private Query asQuery() {
        return Query.atPath(this.key.getPath());
    }

    private static EventManager.ListenOptions internalOptions(MetadataChanges metadataChanges) {
        EventManager.ListenOptions listenOptions = new EventManager.ListenOptions();
        boolean z = true;
        listenOptions.includeDocumentMetadataChanges = metadataChanges == MetadataChanges.INCLUDE;
        if (metadataChanges != MetadataChanges.INCLUDE) {
            z = false;
        }
        listenOptions.includeQueryMetadataChanges = z;
        listenOptions.waitForSyncWhenOnline = false;
        return listenOptions;
    }
}

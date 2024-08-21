package com.google.firebase.firestore.core;

import android.content.Context;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.FirebaseFirestoreSettings;
import com.google.firebase.firestore.auth.CredentialsProvider;
import com.google.firebase.firestore.auth.User;
import com.google.firebase.firestore.core.ComponentProvider;
import com.google.firebase.firestore.core.EventManager;
import com.google.firebase.firestore.local.GarbageCollectionScheduler;
import com.google.firebase.firestore.local.LocalStore;
import com.google.firebase.firestore.local.Persistence;
import com.google.firebase.firestore.local.QueryResult;
import com.google.firebase.firestore.model.Document;
import com.google.firebase.firestore.model.DocumentKey;
import com.google.firebase.firestore.model.MaybeDocument;
import com.google.firebase.firestore.model.NoDocument;
import com.google.firebase.firestore.model.mutation.Mutation;
import com.google.firebase.firestore.remote.Datastore;
import com.google.firebase.firestore.remote.GrpcMetadataProvider;
import com.google.firebase.firestore.remote.RemoteStore;
import com.google.firebase.firestore.util.Assert;
import com.google.firebase.firestore.util.AsyncQueue;
import com.google.firebase.firestore.util.Function;
import com.google.firebase.firestore.util.Logger;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.atomic.AtomicBoolean;

public final class FirestoreClient {
    private static final String LOG_TAG = "FirestoreClient";
    private static final int MAX_CONCURRENT_LIMBO_RESOLUTIONS = 100;
    private final AsyncQueue asyncQueue;
    private final CredentialsProvider credentialsProvider;
    private final DatabaseInfo databaseInfo;
    private EventManager eventManager;
    private GarbageCollectionScheduler gcScheduler;
    private LocalStore localStore;
    private final GrpcMetadataProvider metadataProvider;
    private Persistence persistence;
    private RemoteStore remoteStore;
    private SyncEngine syncEngine;

    public FirestoreClient(Context context, DatabaseInfo databaseInfo2, FirebaseFirestoreSettings firebaseFirestoreSettings, CredentialsProvider credentialsProvider2, AsyncQueue asyncQueue2, GrpcMetadataProvider grpcMetadataProvider) {
        this.databaseInfo = databaseInfo2;
        this.credentialsProvider = credentialsProvider2;
        this.asyncQueue = asyncQueue2;
        this.metadataProvider = grpcMetadataProvider;
        TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        AtomicBoolean atomicBoolean = new AtomicBoolean(false);
        asyncQueue2.enqueueAndForget(FirestoreClient$$Lambda$1.lambdaFactory$(this, taskCompletionSource, context, firebaseFirestoreSettings));
        credentialsProvider2.setChangeListener(FirestoreClient$$Lambda$2.lambdaFactory$(this, atomicBoolean, taskCompletionSource, asyncQueue2));
    }

    static /* synthetic */ void lambda$new$0(FirestoreClient firestoreClient, TaskCompletionSource taskCompletionSource, Context context, FirebaseFirestoreSettings firebaseFirestoreSettings) {
        try {
            firestoreClient.initialize(context, (User) Tasks.await(taskCompletionSource.getTask()), firebaseFirestoreSettings);
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    static /* synthetic */ void lambda$new$2(FirestoreClient firestoreClient, AtomicBoolean atomicBoolean, TaskCompletionSource taskCompletionSource, AsyncQueue asyncQueue2, User user) {
        if (atomicBoolean.compareAndSet(false, true)) {
            Assert.hardAssert(!taskCompletionSource.getTask().isComplete(), "Already fulfilled first user task", new Object[0]);
            taskCompletionSource.setResult(user);
            return;
        }
        asyncQueue2.enqueueAndForget(FirestoreClient$$Lambda$16.lambdaFactory$(firestoreClient, user));
    }

    static /* synthetic */ void lambda$new$1(FirestoreClient firestoreClient, User user) {
        Assert.hardAssert(firestoreClient.syncEngine != null, "SyncEngine not yet initialized", new Object[0]);
        Logger.debug(LOG_TAG, "Credential changed. Current user: %s", user.getUid());
        firestoreClient.syncEngine.handleCredentialChange(user);
    }

    public Task<Void> disableNetwork() {
        verifyNotTerminated();
        return this.asyncQueue.enqueue(FirestoreClient$$Lambda$3.lambdaFactory$(this));
    }

    public Task<Void> enableNetwork() {
        verifyNotTerminated();
        return this.asyncQueue.enqueue(FirestoreClient$$Lambda$4.lambdaFactory$(this));
    }

    public Task<Void> terminate() {
        this.credentialsProvider.removeChangeListener();
        return this.asyncQueue.enqueueAndInitiateShutdown(FirestoreClient$$Lambda$5.lambdaFactory$(this));
    }

    static /* synthetic */ void lambda$terminate$5(FirestoreClient firestoreClient) {
        firestoreClient.remoteStore.shutdown();
        firestoreClient.persistence.shutdown();
        GarbageCollectionScheduler garbageCollectionScheduler = firestoreClient.gcScheduler;
        if (garbageCollectionScheduler != null) {
            garbageCollectionScheduler.stop();
        }
    }

    public boolean isTerminated() {
        return this.asyncQueue.isShuttingDown();
    }

    public QueryListener listen(Query query, EventManager.ListenOptions listenOptions, EventListener<ViewSnapshot> eventListener) {
        verifyNotTerminated();
        QueryListener queryListener = new QueryListener(query, listenOptions, eventListener);
        this.asyncQueue.enqueueAndForget(FirestoreClient$$Lambda$6.lambdaFactory$(this, queryListener));
        return queryListener;
    }

    public void stopListening(QueryListener queryListener) {
        if (!isTerminated()) {
            this.asyncQueue.enqueueAndForget(FirestoreClient$$Lambda$7.lambdaFactory$(this, queryListener));
        }
    }

    public Task<Document> getDocumentFromLocalCache(DocumentKey documentKey) {
        verifyNotTerminated();
        return this.asyncQueue.enqueue(FirestoreClient$$Lambda$8.lambdaFactory$(this, documentKey)).continueWith(FirestoreClient$$Lambda$9.lambdaFactory$());
    }

    static /* synthetic */ Document lambda$getDocumentFromLocalCache$9(Task task) throws Exception {
        MaybeDocument maybeDocument = (MaybeDocument) task.getResult();
        if (maybeDocument instanceof Document) {
            return (Document) maybeDocument;
        }
        if (maybeDocument instanceof NoDocument) {
            return null;
        }
        throw new FirebaseFirestoreException("Failed to get document from cache. (However, this document may exist on the server. Run again without setting source to CACHE to attempt to retrieve the document from the server.)", FirebaseFirestoreException.Code.UNAVAILABLE);
    }

    public Task<ViewSnapshot> getDocumentsFromLocalCache(Query query) {
        verifyNotTerminated();
        return this.asyncQueue.enqueue(FirestoreClient$$Lambda$10.lambdaFactory$(this, query));
    }

    static /* synthetic */ ViewSnapshot lambda$getDocumentsFromLocalCache$10(FirestoreClient firestoreClient, Query query) throws Exception {
        QueryResult executeQuery = firestoreClient.localStore.executeQuery(query, true);
        View view = new View(query, executeQuery.getRemoteKeys());
        return view.applyChanges(view.computeDocChanges(executeQuery.getDocuments())).getSnapshot();
    }

    public Task<Void> write(List<Mutation> list) {
        verifyNotTerminated();
        TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        this.asyncQueue.enqueueAndForget(FirestoreClient$$Lambda$11.lambdaFactory$(this, list, taskCompletionSource));
        return taskCompletionSource.getTask();
    }

    public <TResult> Task<TResult> transaction(Function<Transaction, Task<TResult>> function) {
        verifyNotTerminated();
        return AsyncQueue.callTask(this.asyncQueue.getExecutor(), FirestoreClient$$Lambda$12.lambdaFactory$(this, function));
    }

    public Task<Void> waitForPendingWrites() {
        verifyNotTerminated();
        TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        this.asyncQueue.enqueueAndForget(FirestoreClient$$Lambda$13.lambdaFactory$(this, taskCompletionSource));
        return taskCompletionSource.getTask();
    }

    private void initialize(Context context, User user, FirebaseFirestoreSettings firebaseFirestoreSettings) {
        ComponentProvider componentProvider;
        Logger.debug(LOG_TAG, "Initializing. user=%s", user.getUid());
        Context context2 = context;
        ComponentProvider.Configuration configuration = new ComponentProvider.Configuration(context2, this.asyncQueue, this.databaseInfo, new Datastore(this.databaseInfo, this.asyncQueue, this.credentialsProvider, context, this.metadataProvider), user, 100, firebaseFirestoreSettings);
        if (firebaseFirestoreSettings.isPersistenceEnabled()) {
            componentProvider = new SQLiteComponentProvider();
        } else {
            componentProvider = new MemoryComponentProvider();
        }
        componentProvider.initialize(configuration);
        this.persistence = componentProvider.getPersistence();
        this.gcScheduler = componentProvider.getGargabeCollectionScheduler();
        this.localStore = componentProvider.getLocalStore();
        this.remoteStore = componentProvider.getRemoteStore();
        this.syncEngine = componentProvider.getSyncEngine();
        this.eventManager = componentProvider.getEventManager();
        GarbageCollectionScheduler garbageCollectionScheduler = this.gcScheduler;
        if (garbageCollectionScheduler != null) {
            garbageCollectionScheduler.start();
        }
    }

    public void addSnapshotsInSyncListener(EventListener<Void> eventListener) {
        verifyNotTerminated();
        this.asyncQueue.enqueueAndForget(FirestoreClient$$Lambda$14.lambdaFactory$(this, eventListener));
    }

    public void removeSnapshotsInSyncListener(EventListener<Void> eventListener) {
        if (!isTerminated()) {
            this.asyncQueue.enqueueAndForget(FirestoreClient$$Lambda$15.lambdaFactory$(this, eventListener));
        }
    }

    private void verifyNotTerminated() {
        if (isTerminated()) {
            throw new IllegalStateException("The client has already been terminated");
        }
    }
}

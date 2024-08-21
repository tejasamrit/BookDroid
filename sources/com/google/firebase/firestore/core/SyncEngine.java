package com.google.firebase.firestore.core;

import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.firebase.database.collection.ImmutableSortedMap;
import com.google.firebase.database.collection.ImmutableSortedSet;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.auth.User;
import com.google.firebase.firestore.core.View;
import com.google.firebase.firestore.core.ViewSnapshot;
import com.google.firebase.firestore.local.LocalStore;
import com.google.firebase.firestore.local.LocalViewChanges;
import com.google.firebase.firestore.local.LocalWriteResult;
import com.google.firebase.firestore.local.QueryPurpose;
import com.google.firebase.firestore.local.QueryResult;
import com.google.firebase.firestore.local.ReferenceSet;
import com.google.firebase.firestore.local.TargetData;
import com.google.firebase.firestore.model.DocumentKey;
import com.google.firebase.firestore.model.MaybeDocument;
import com.google.firebase.firestore.model.NoDocument;
import com.google.firebase.firestore.model.SnapshotVersion;
import com.google.firebase.firestore.model.mutation.Mutation;
import com.google.firebase.firestore.model.mutation.MutationBatchResult;
import com.google.firebase.firestore.remote.RemoteEvent;
import com.google.firebase.firestore.remote.RemoteStore;
import com.google.firebase.firestore.remote.TargetChange;
import com.google.firebase.firestore.util.Assert;
import com.google.firebase.firestore.util.AsyncQueue;
import com.google.firebase.firestore.util.Function;
import com.google.firebase.firestore.util.Logger;
import com.google.firebase.firestore.util.Util;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import p012io.grpc.Status;

public class SyncEngine implements RemoteStore.RemoteStoreCallback {
    private static final String TAG = "SyncEngine";
    private final Map<Integer, LimboResolution> activeLimboResolutionsByTarget = new HashMap();
    private final Map<DocumentKey, Integer> activeLimboTargetsByKey = new HashMap();
    private User currentUser;
    private final Queue<DocumentKey> enqueuedLimboResolutions = new ArrayDeque();
    private final ReferenceSet limboDocumentRefs = new ReferenceSet();
    private final LocalStore localStore;
    private final int maxConcurrentLimboResolutions;
    private final Map<User, Map<Integer, TaskCompletionSource<Void>>> mutationUserCallbacks = new HashMap();
    private final Map<Integer, List<TaskCompletionSource<Void>>> pendingWritesCallbacks;
    private final Map<Integer, List<Query>> queriesByTarget = new HashMap();
    private final Map<Query, QueryView> queryViewsByQuery = new HashMap();
    private final RemoteStore remoteStore;
    private SyncEngineCallback syncEngineListener;
    private final TargetIdGenerator targetIdGenerator = TargetIdGenerator.forSyncEngine();

    interface SyncEngineCallback {
        void handleOnlineStateChange(OnlineState onlineState);

        void onError(Query query, Status status);

        void onViewSnapshots(List<ViewSnapshot> list);
    }

    private static class LimboResolution {
        /* access modifiers changed from: private */
        public final DocumentKey key;
        /* access modifiers changed from: private */
        public boolean receivedDocument;

        LimboResolution(DocumentKey documentKey) {
            this.key = documentKey;
        }
    }

    public SyncEngine(LocalStore localStore2, RemoteStore remoteStore2, User user, int i) {
        this.localStore = localStore2;
        this.remoteStore = remoteStore2;
        this.maxConcurrentLimboResolutions = i;
        this.currentUser = user;
        this.pendingWritesCallbacks = new HashMap();
    }

    public void setCallback(SyncEngineCallback syncEngineCallback) {
        this.syncEngineListener = syncEngineCallback;
    }

    private void assertCallback(String str) {
        Assert.hardAssert(this.syncEngineListener != null, "Trying to call %s before setting callback", str);
    }

    public int listen(Query query) {
        assertCallback("listen");
        Assert.hardAssert(!this.queryViewsByQuery.containsKey(query), "We already listen to query: %s", query);
        TargetData allocateTarget = this.localStore.allocateTarget(query.toTarget());
        this.syncEngineListener.onViewSnapshots(Collections.singletonList(initializeViewAndComputeSnapshot(query, allocateTarget.getTargetId())));
        this.remoteStore.listen(allocateTarget);
        return allocateTarget.getTargetId();
    }

    private ViewSnapshot initializeViewAndComputeSnapshot(Query query, int i) {
        TargetChange targetChange;
        QueryResult executeQuery = this.localStore.executeQuery(query, true);
        ViewSnapshot.SyncState syncState = ViewSnapshot.SyncState.NONE;
        if (this.queriesByTarget.get(Integer.valueOf(i)) != null) {
            boolean z = false;
            if (this.queryViewsByQuery.get((Query) this.queriesByTarget.get(Integer.valueOf(i)).get(0)).getView().getSyncState() == ViewSnapshot.SyncState.SYNCED) {
                z = true;
            }
            targetChange = TargetChange.createSynthesizedTargetChangeForCurrentChange(z);
        } else {
            targetChange = null;
        }
        View view = new View(query, executeQuery.getRemoteKeys());
        ViewChange applyChanges = view.applyChanges(view.computeDocChanges(executeQuery.getDocuments()), targetChange);
        updateTrackedLimboDocuments(applyChanges.getLimboChanges(), i);
        this.queryViewsByQuery.put(query, new QueryView(query, i, view));
        if (!this.queriesByTarget.containsKey(Integer.valueOf(i))) {
            this.queriesByTarget.put(Integer.valueOf(i), new ArrayList(1));
        }
        this.queriesByTarget.get(Integer.valueOf(i)).add(query);
        return applyChanges.getSnapshot();
    }

    /* access modifiers changed from: package-private */
    public void stopListening(Query query) {
        assertCallback("stopListening");
        QueryView queryView = this.queryViewsByQuery.get(query);
        Assert.hardAssert(queryView != null, "Trying to stop listening to a query not found", new Object[0]);
        this.queryViewsByQuery.remove(query);
        int targetId = queryView.getTargetId();
        List list = this.queriesByTarget.get(Integer.valueOf(targetId));
        list.remove(query);
        if (list.isEmpty()) {
            this.localStore.releaseTarget(targetId);
            this.remoteStore.stopListening(targetId);
            removeAndCleanupTarget(targetId, Status.f489OK);
        }
    }

    public void writeMutations(List<Mutation> list, TaskCompletionSource<Void> taskCompletionSource) {
        assertCallback("writeMutations");
        LocalWriteResult writeLocally = this.localStore.writeLocally(list);
        addUserCallback(writeLocally.getBatchId(), taskCompletionSource);
        emitNewSnapsAndNotifyLocalStore(writeLocally.getChanges(), (RemoteEvent) null);
        this.remoteStore.fillWritePipeline();
    }

    private void addUserCallback(int i, TaskCompletionSource<Void> taskCompletionSource) {
        Map map = this.mutationUserCallbacks.get(this.currentUser);
        if (map == null) {
            map = new HashMap();
            this.mutationUserCallbacks.put(this.currentUser, map);
        }
        map.put(Integer.valueOf(i), taskCompletionSource);
    }

    public <TResult> Task<TResult> transaction(AsyncQueue asyncQueue, Function<Transaction, Task<TResult>> function) {
        return new TransactionRunner(asyncQueue, this.remoteStore, function).run();
    }

    public void handleRemoteEvent(RemoteEvent remoteEvent) {
        assertCallback("handleRemoteEvent");
        for (Map.Entry next : remoteEvent.getTargetChanges().entrySet()) {
            TargetChange targetChange = (TargetChange) next.getValue();
            LimboResolution limboResolution = this.activeLimboResolutionsByTarget.get((Integer) next.getKey());
            if (limboResolution != null) {
                Assert.hardAssert((targetChange.getAddedDocuments().size() + targetChange.getModifiedDocuments().size()) + targetChange.getRemovedDocuments().size() <= 1, "Limbo resolution for single document contains multiple changes.", new Object[0]);
                if (targetChange.getAddedDocuments().size() > 0) {
                    boolean unused = limboResolution.receivedDocument = true;
                } else if (targetChange.getModifiedDocuments().size() > 0) {
                    Assert.hardAssert(limboResolution.receivedDocument, "Received change for limbo target document without add.", new Object[0]);
                } else if (targetChange.getRemovedDocuments().size() > 0) {
                    Assert.hardAssert(limboResolution.receivedDocument, "Received remove for limbo target document without add.", new Object[0]);
                    boolean unused2 = limboResolution.receivedDocument = false;
                }
            }
        }
        emitNewSnapsAndNotifyLocalStore(this.localStore.applyRemoteEvent(remoteEvent), remoteEvent);
    }

    public void handleOnlineStateChange(OnlineState onlineState) {
        assertCallback("handleOnlineStateChange");
        ArrayList arrayList = new ArrayList();
        for (Map.Entry<Query, QueryView> value : this.queryViewsByQuery.entrySet()) {
            ViewChange applyOnlineStateChange = ((QueryView) value.getValue()).getView().applyOnlineStateChange(onlineState);
            Assert.hardAssert(applyOnlineStateChange.getLimboChanges().isEmpty(), "OnlineState should not affect limbo documents.", new Object[0]);
            if (applyOnlineStateChange.getSnapshot() != null) {
                arrayList.add(applyOnlineStateChange.getSnapshot());
            }
        }
        this.syncEngineListener.onViewSnapshots(arrayList);
        this.syncEngineListener.handleOnlineStateChange(onlineState);
    }

    public ImmutableSortedSet<DocumentKey> getRemoteKeysForTarget(int i) {
        LimboResolution limboResolution = this.activeLimboResolutionsByTarget.get(Integer.valueOf(i));
        if (limboResolution != null && limboResolution.receivedDocument) {
            return DocumentKey.emptyKeySet().insert(limboResolution.key);
        }
        ImmutableSortedSet<DocumentKey> emptyKeySet = DocumentKey.emptyKeySet();
        if (this.queriesByTarget.containsKey(Integer.valueOf(i))) {
            for (Query query : this.queriesByTarget.get(Integer.valueOf(i))) {
                if (this.queryViewsByQuery.containsKey(query)) {
                    emptyKeySet = emptyKeySet.unionWith(this.queryViewsByQuery.get(query).getView().getSyncedDocuments());
                }
            }
        }
        return emptyKeySet;
    }

    public void handleRejectedListen(int i, Status status) {
        assertCallback("handleRejectedListen");
        LimboResolution limboResolution = this.activeLimboResolutionsByTarget.get(Integer.valueOf(i));
        DocumentKey access$100 = limboResolution != null ? limboResolution.key : null;
        if (access$100 != null) {
            this.activeLimboTargetsByKey.remove(access$100);
            this.activeLimboResolutionsByTarget.remove(Integer.valueOf(i));
            pumpEnqueuedLimboResolutions();
            handleRemoteEvent(new RemoteEvent(SnapshotVersion.NONE, Collections.emptyMap(), Collections.emptySet(), Collections.singletonMap(access$100, new NoDocument(access$100, SnapshotVersion.NONE, false)), Collections.singleton(access$100)));
            return;
        }
        this.localStore.releaseTarget(i);
        removeAndCleanupTarget(i, status);
    }

    public void handleSuccessfulWrite(MutationBatchResult mutationBatchResult) {
        assertCallback("handleSuccessfulWrite");
        notifyUser(mutationBatchResult.getBatch().getBatchId(), (Status) null);
        resolvePendingWriteTasks(mutationBatchResult.getBatch().getBatchId());
        emitNewSnapsAndNotifyLocalStore(this.localStore.acknowledgeBatch(mutationBatchResult), (RemoteEvent) null);
    }

    public void handleRejectedWrite(int i, Status status) {
        assertCallback("handleRejectedWrite");
        ImmutableSortedMap<DocumentKey, MaybeDocument> rejectBatch = this.localStore.rejectBatch(i);
        if (!rejectBatch.isEmpty()) {
            logErrorIfInteresting(status, "Write failed at %s", rejectBatch.getMinKey().getPath());
        }
        notifyUser(i, status);
        resolvePendingWriteTasks(i);
        emitNewSnapsAndNotifyLocalStore(rejectBatch, (RemoteEvent) null);
    }

    public void registerPendingWritesTask(TaskCompletionSource<Void> taskCompletionSource) {
        if (!this.remoteStore.canUseNetwork()) {
            Logger.debug(TAG, "The network is disabled. The task returned by 'awaitPendingWrites()' will not complete until the network is enabled.", new Object[0]);
        }
        int highestUnacknowledgedBatchId = this.localStore.getHighestUnacknowledgedBatchId();
        if (highestUnacknowledgedBatchId == -1) {
            taskCompletionSource.setResult(null);
            return;
        }
        if (!this.pendingWritesCallbacks.containsKey(Integer.valueOf(highestUnacknowledgedBatchId))) {
            this.pendingWritesCallbacks.put(Integer.valueOf(highestUnacknowledgedBatchId), new ArrayList());
        }
        this.pendingWritesCallbacks.get(Integer.valueOf(highestUnacknowledgedBatchId)).add(taskCompletionSource);
    }

    private void resolvePendingWriteTasks(int i) {
        if (this.pendingWritesCallbacks.containsKey(Integer.valueOf(i))) {
            for (TaskCompletionSource result : this.pendingWritesCallbacks.get(Integer.valueOf(i))) {
                result.setResult(null);
            }
            this.pendingWritesCallbacks.remove(Integer.valueOf(i));
        }
    }

    private void failOutstandingPendingWritesAwaitingTasks() {
        for (Map.Entry<Integer, List<TaskCompletionSource<Void>>> value : this.pendingWritesCallbacks.entrySet()) {
            for (TaskCompletionSource exception : (List) value.getValue()) {
                exception.setException(new FirebaseFirestoreException("'waitForPendingWrites' task is cancelled due to User change.", FirebaseFirestoreException.Code.CANCELLED));
            }
        }
        this.pendingWritesCallbacks.clear();
    }

    private void notifyUser(int i, Status status) {
        Map map = this.mutationUserCallbacks.get(this.currentUser);
        if (map != null) {
            Integer valueOf = Integer.valueOf(i);
            TaskCompletionSource taskCompletionSource = (TaskCompletionSource) map.get(valueOf);
            if (taskCompletionSource != null) {
                if (status != null) {
                    taskCompletionSource.setException(Util.exceptionFromStatus(status));
                } else {
                    taskCompletionSource.setResult(null);
                }
                map.remove(valueOf);
            }
        }
    }

    private void removeAndCleanupTarget(int i, Status status) {
        for (Query query : this.queriesByTarget.get(Integer.valueOf(i))) {
            this.queryViewsByQuery.remove(query);
            if (!status.isOk()) {
                this.syncEngineListener.onError(query, status);
                logErrorIfInteresting(status, "Listen for %s failed", query);
            }
        }
        this.queriesByTarget.remove(Integer.valueOf(i));
        ImmutableSortedSet<DocumentKey> referencesForId = this.limboDocumentRefs.referencesForId(i);
        this.limboDocumentRefs.removeReferencesForId(i);
        Iterator<DocumentKey> it = referencesForId.iterator();
        while (it.hasNext()) {
            DocumentKey next = it.next();
            if (!this.limboDocumentRefs.containsKey(next)) {
                removeLimboTarget(next);
            }
        }
    }

    private void removeLimboTarget(DocumentKey documentKey) {
        Integer num = this.activeLimboTargetsByKey.get(documentKey);
        if (num != null) {
            this.remoteStore.stopListening(num.intValue());
            this.activeLimboTargetsByKey.remove(documentKey);
            this.activeLimboResolutionsByTarget.remove(num);
            pumpEnqueuedLimboResolutions();
        }
    }

    private void emitNewSnapsAndNotifyLocalStore(ImmutableSortedMap<DocumentKey, MaybeDocument> immutableSortedMap, RemoteEvent remoteEvent) {
        TargetChange targetChange;
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        for (Map.Entry<Query, QueryView> value : this.queryViewsByQuery.entrySet()) {
            QueryView queryView = (QueryView) value.getValue();
            View view = queryView.getView();
            View.DocumentChanges computeDocChanges = view.computeDocChanges(immutableSortedMap);
            if (computeDocChanges.needsRefill()) {
                computeDocChanges = view.computeDocChanges(this.localStore.executeQuery(queryView.getQuery(), false).getDocuments(), computeDocChanges);
            }
            if (remoteEvent == null) {
                targetChange = null;
            } else {
                targetChange = remoteEvent.getTargetChanges().get(Integer.valueOf(queryView.getTargetId()));
            }
            ViewChange applyChanges = queryView.getView().applyChanges(computeDocChanges, targetChange);
            updateTrackedLimboDocuments(applyChanges.getLimboChanges(), queryView.getTargetId());
            if (applyChanges.getSnapshot() != null) {
                arrayList.add(applyChanges.getSnapshot());
                arrayList2.add(LocalViewChanges.fromViewSnapshot(queryView.getTargetId(), applyChanges.getSnapshot()));
            }
        }
        this.syncEngineListener.onViewSnapshots(arrayList);
        this.localStore.notifyLocalViewChanges(arrayList2);
    }

    /* renamed from: com.google.firebase.firestore.core.SyncEngine$1 */
    static /* synthetic */ class C18861 {

        /* renamed from: $SwitchMap$com$google$firebase$firestore$core$LimboDocumentChange$Type */
        static final /* synthetic */ int[] f328x84ba890d;

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|6) */
        /* JADX WARNING: Code restructure failed: missing block: B:7:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        static {
            /*
                com.google.firebase.firestore.core.LimboDocumentChange$Type[] r0 = com.google.firebase.firestore.core.LimboDocumentChange.Type.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                f328x84ba890d = r0
                com.google.firebase.firestore.core.LimboDocumentChange$Type r1 = com.google.firebase.firestore.core.LimboDocumentChange.Type.ADDED     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = f328x84ba890d     // Catch:{ NoSuchFieldError -> 0x001d }
                com.google.firebase.firestore.core.LimboDocumentChange$Type r1 = com.google.firebase.firestore.core.LimboDocumentChange.Type.REMOVED     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.firestore.core.SyncEngine.C18861.<clinit>():void");
        }
    }

    private void updateTrackedLimboDocuments(List<LimboDocumentChange> list, int i) {
        for (LimboDocumentChange next : list) {
            int i2 = C18861.f328x84ba890d[next.getType().ordinal()];
            if (i2 == 1) {
                this.limboDocumentRefs.addReference(next.getKey(), i);
                trackLimboChange(next);
            } else if (i2 == 2) {
                Logger.debug(TAG, "Document no longer in limbo: %s", next.getKey());
                DocumentKey key = next.getKey();
                this.limboDocumentRefs.removeReference(key, i);
                if (!this.limboDocumentRefs.containsKey(key)) {
                    removeLimboTarget(key);
                }
            } else {
                throw Assert.fail("Unknown limbo change type: %s", next.getType());
            }
        }
    }

    private void trackLimboChange(LimboDocumentChange limboDocumentChange) {
        DocumentKey key = limboDocumentChange.getKey();
        if (!this.activeLimboTargetsByKey.containsKey(key)) {
            Logger.debug(TAG, "New document in limbo: %s", key);
            this.enqueuedLimboResolutions.add(key);
            pumpEnqueuedLimboResolutions();
        }
    }

    private void pumpEnqueuedLimboResolutions() {
        while (!this.enqueuedLimboResolutions.isEmpty() && this.activeLimboTargetsByKey.size() < this.maxConcurrentLimboResolutions) {
            DocumentKey remove = this.enqueuedLimboResolutions.remove();
            int nextId = this.targetIdGenerator.nextId();
            this.activeLimboResolutionsByTarget.put(Integer.valueOf(nextId), new LimboResolution(remove));
            this.activeLimboTargetsByKey.put(remove, Integer.valueOf(nextId));
            this.remoteStore.listen(new TargetData(Query.atPath(remove.getPath()).toTarget(), nextId, -1, QueryPurpose.LIMBO_RESOLUTION));
        }
    }

    public Map<DocumentKey, Integer> getActiveLimboDocumentResolutions() {
        return new HashMap(this.activeLimboTargetsByKey);
    }

    public Queue<DocumentKey> getEnqueuedLimboDocumentResolutions() {
        return new ArrayDeque(this.enqueuedLimboResolutions);
    }

    public void handleCredentialChange(User user) {
        boolean z = !this.currentUser.equals(user);
        this.currentUser = user;
        if (z) {
            failOutstandingPendingWritesAwaitingTasks();
            emitNewSnapsAndNotifyLocalStore(this.localStore.handleUserChange(user), (RemoteEvent) null);
        }
        this.remoteStore.handleCredentialChange();
    }

    private void logErrorIfInteresting(Status status, String str, Object... objArr) {
        if (errorIsInteresting(status)) {
            Logger.warn("Firestore", "%s: %s", String.format(str, objArr), status);
        }
    }

    private boolean errorIsInteresting(Status status) {
        Status.Code code = status.getCode();
        String description = status.getDescription() != null ? status.getDescription() : "";
        if ((code != Status.Code.FAILED_PRECONDITION || !description.contains("requires an index")) && code != Status.Code.PERMISSION_DENIED) {
            return false;
        }
        return true;
    }
}

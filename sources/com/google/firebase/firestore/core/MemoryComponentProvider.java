package com.google.firebase.firestore.core;

import com.google.firebase.database.collection.ImmutableSortedSet;
import com.google.firebase.firestore.core.ComponentProvider;
import com.google.firebase.firestore.local.DefaultQueryEngine;
import com.google.firebase.firestore.local.GarbageCollectionScheduler;
import com.google.firebase.firestore.local.LocalStore;
import com.google.firebase.firestore.local.MemoryPersistence;
import com.google.firebase.firestore.local.Persistence;
import com.google.firebase.firestore.model.DocumentKey;
import com.google.firebase.firestore.model.mutation.MutationBatchResult;
import com.google.firebase.firestore.remote.AndroidConnectivityMonitor;
import com.google.firebase.firestore.remote.RemoteEvent;
import com.google.firebase.firestore.remote.RemoteStore;
import p012io.grpc.Status;

public class MemoryComponentProvider extends ComponentProvider {
    /* access modifiers changed from: protected */
    public GarbageCollectionScheduler createGarbageCollectionScheduler(ComponentProvider.Configuration configuration) {
        return null;
    }

    /* access modifiers changed from: protected */
    public EventManager createEventManager(ComponentProvider.Configuration configuration) {
        return new EventManager(getSyncEngine());
    }

    /* access modifiers changed from: protected */
    public LocalStore createLocalStore(ComponentProvider.Configuration configuration) {
        return new LocalStore(getPersistence(), new DefaultQueryEngine(), configuration.getInitialUser());
    }

    /* access modifiers changed from: protected */
    public AndroidConnectivityMonitor createConnectivityMonitor(ComponentProvider.Configuration configuration) {
        return new AndroidConnectivityMonitor(configuration.getContext());
    }

    /* access modifiers changed from: protected */
    public Persistence createPersistence(ComponentProvider.Configuration configuration) {
        return MemoryPersistence.createEagerGcMemoryPersistence();
    }

    /* access modifiers changed from: protected */
    public RemoteStore createRemoteStore(ComponentProvider.Configuration configuration) {
        return new RemoteStore(new RemoteStoreCallback(), getLocalStore(), configuration.getDatastore(), configuration.getAsyncQueue(), getConnectivityMonitor());
    }

    /* access modifiers changed from: protected */
    public SyncEngine createSyncEngine(ComponentProvider.Configuration configuration) {
        return new SyncEngine(getLocalStore(), getRemoteStore(), configuration.getInitialUser(), configuration.getMaxConcurrentLimboResolutions());
    }

    private class RemoteStoreCallback implements RemoteStore.RemoteStoreCallback {
        private RemoteStoreCallback() {
        }

        public void handleRemoteEvent(RemoteEvent remoteEvent) {
            MemoryComponentProvider.this.getSyncEngine().handleRemoteEvent(remoteEvent);
        }

        public void handleRejectedListen(int i, Status status) {
            MemoryComponentProvider.this.getSyncEngine().handleRejectedListen(i, status);
        }

        public void handleSuccessfulWrite(MutationBatchResult mutationBatchResult) {
            MemoryComponentProvider.this.getSyncEngine().handleSuccessfulWrite(mutationBatchResult);
        }

        public void handleRejectedWrite(int i, Status status) {
            MemoryComponentProvider.this.getSyncEngine().handleRejectedWrite(i, status);
        }

        public void handleOnlineStateChange(OnlineState onlineState) {
            MemoryComponentProvider.this.getSyncEngine().handleOnlineStateChange(onlineState);
        }

        public ImmutableSortedSet<DocumentKey> getRemoteKeysForTarget(int i) {
            return MemoryComponentProvider.this.getSyncEngine().getRemoteKeysForTarget(i);
        }
    }
}

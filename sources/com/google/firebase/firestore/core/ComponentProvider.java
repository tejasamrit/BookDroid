package com.google.firebase.firestore.core;

import android.content.Context;
import com.google.firebase.firestore.FirebaseFirestoreSettings;
import com.google.firebase.firestore.auth.User;
import com.google.firebase.firestore.local.GarbageCollectionScheduler;
import com.google.firebase.firestore.local.LocalStore;
import com.google.firebase.firestore.local.Persistence;
import com.google.firebase.firestore.remote.ConnectivityMonitor;
import com.google.firebase.firestore.remote.Datastore;
import com.google.firebase.firestore.remote.RemoteStore;
import com.google.firebase.firestore.util.AsyncQueue;

public abstract class ComponentProvider {
    private ConnectivityMonitor connectityMonitor;
    private EventManager eventManager;
    private GarbageCollectionScheduler gargabeCollectionScheduler;
    private LocalStore localStore;
    private Persistence persistence;
    private RemoteStore remoteStore;
    private SyncEngine syncEngine;

    /* access modifiers changed from: protected */
    public abstract ConnectivityMonitor createConnectivityMonitor(Configuration configuration);

    /* access modifiers changed from: protected */
    public abstract EventManager createEventManager(Configuration configuration);

    /* access modifiers changed from: protected */
    public abstract GarbageCollectionScheduler createGarbageCollectionScheduler(Configuration configuration);

    /* access modifiers changed from: protected */
    public abstract LocalStore createLocalStore(Configuration configuration);

    /* access modifiers changed from: protected */
    public abstract Persistence createPersistence(Configuration configuration);

    /* access modifiers changed from: protected */
    public abstract RemoteStore createRemoteStore(Configuration configuration);

    /* access modifiers changed from: protected */
    public abstract SyncEngine createSyncEngine(Configuration configuration);

    public static class Configuration {
        private final AsyncQueue asyncQueue;
        private final Context context;
        private final DatabaseInfo databaseInfo;
        private final Datastore datastore;
        private final User initialUser;
        private final int maxConcurrentLimboResolutions;
        private final FirebaseFirestoreSettings settings;

        public Configuration(Context context2, AsyncQueue asyncQueue2, DatabaseInfo databaseInfo2, Datastore datastore2, User user, int i, FirebaseFirestoreSettings firebaseFirestoreSettings) {
            this.context = context2;
            this.asyncQueue = asyncQueue2;
            this.databaseInfo = databaseInfo2;
            this.datastore = datastore2;
            this.initialUser = user;
            this.maxConcurrentLimboResolutions = i;
            this.settings = firebaseFirestoreSettings;
        }

        /* access modifiers changed from: package-private */
        public FirebaseFirestoreSettings getSettings() {
            return this.settings;
        }

        /* access modifiers changed from: package-private */
        public AsyncQueue getAsyncQueue() {
            return this.asyncQueue;
        }

        /* access modifiers changed from: package-private */
        public DatabaseInfo getDatabaseInfo() {
            return this.databaseInfo;
        }

        /* access modifiers changed from: package-private */
        public Datastore getDatastore() {
            return this.datastore;
        }

        /* access modifiers changed from: package-private */
        public User getInitialUser() {
            return this.initialUser;
        }

        /* access modifiers changed from: package-private */
        public int getMaxConcurrentLimboResolutions() {
            return this.maxConcurrentLimboResolutions;
        }

        /* access modifiers changed from: package-private */
        public Context getContext() {
            return this.context;
        }
    }

    public Persistence getPersistence() {
        return this.persistence;
    }

    public GarbageCollectionScheduler getGargabeCollectionScheduler() {
        return this.gargabeCollectionScheduler;
    }

    public LocalStore getLocalStore() {
        return this.localStore;
    }

    public SyncEngine getSyncEngine() {
        return this.syncEngine;
    }

    public RemoteStore getRemoteStore() {
        return this.remoteStore;
    }

    public EventManager getEventManager() {
        return this.eventManager;
    }

    /* access modifiers changed from: protected */
    public ConnectivityMonitor getConnectivityMonitor() {
        return this.connectityMonitor;
    }

    public void initialize(Configuration configuration) {
        Persistence createPersistence = createPersistence(configuration);
        this.persistence = createPersistence;
        createPersistence.start();
        this.localStore = createLocalStore(configuration);
        this.connectityMonitor = createConnectivityMonitor(configuration);
        this.remoteStore = createRemoteStore(configuration);
        this.syncEngine = createSyncEngine(configuration);
        this.eventManager = createEventManager(configuration);
        this.localStore.start();
        this.remoteStore.start();
        this.gargabeCollectionScheduler = createGarbageCollectionScheduler(configuration);
    }
}

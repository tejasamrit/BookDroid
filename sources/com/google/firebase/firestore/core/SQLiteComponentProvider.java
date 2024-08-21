package com.google.firebase.firestore.core;

import com.google.firebase.firestore.core.ComponentProvider;
import com.google.firebase.firestore.local.GarbageCollectionScheduler;
import com.google.firebase.firestore.local.LocalSerializer;
import com.google.firebase.firestore.local.LruGarbageCollector;
import com.google.firebase.firestore.local.Persistence;
import com.google.firebase.firestore.local.SQLitePersistence;
import com.google.firebase.firestore.remote.RemoteSerializer;

public class SQLiteComponentProvider extends MemoryComponentProvider {
    /* access modifiers changed from: protected */
    public GarbageCollectionScheduler createGarbageCollectionScheduler(ComponentProvider.Configuration configuration) {
        return ((SQLitePersistence) getPersistence()).getReferenceDelegate().getGarbageCollector().newScheduler(configuration.getAsyncQueue(), getLocalStore());
    }

    /* access modifiers changed from: protected */
    public Persistence createPersistence(ComponentProvider.Configuration configuration) {
        return new SQLitePersistence(configuration.getContext(), configuration.getDatabaseInfo().getPersistenceKey(), configuration.getDatabaseInfo().getDatabaseId(), new LocalSerializer(new RemoteSerializer(configuration.getDatabaseInfo().getDatabaseId())), LruGarbageCollector.Params.WithCacheSizeBytes(configuration.getSettings().getCacheSizeBytes()));
    }
}

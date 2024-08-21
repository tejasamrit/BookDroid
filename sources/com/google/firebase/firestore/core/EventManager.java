package com.google.firebase.firestore.core;

import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.core.SyncEngine;
import com.google.firebase.firestore.util.Assert;
import com.google.firebase.firestore.util.Util;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import p012io.grpc.Status;

public final class EventManager implements SyncEngine.SyncEngineCallback {
    private OnlineState onlineState = OnlineState.UNKNOWN;
    private final Map<Query, QueryListenersInfo> queries;
    private final Set<EventListener<Void>> snapshotsInSyncListeners = new HashSet();
    private final SyncEngine syncEngine;

    public static class ListenOptions {
        public boolean includeDocumentMetadataChanges;
        public boolean includeQueryMetadataChanges;
        public boolean waitForSyncWhenOnline;
    }

    private static class QueryListenersInfo {
        /* access modifiers changed from: private */
        public final List<QueryListener> listeners = new ArrayList();
        /* access modifiers changed from: private */
        public int targetId;
        /* access modifiers changed from: private */
        public ViewSnapshot viewSnapshot;

        QueryListenersInfo() {
        }
    }

    public EventManager(SyncEngine syncEngine2) {
        this.syncEngine = syncEngine2;
        this.queries = new HashMap();
        syncEngine2.setCallback(this);
    }

    public int addQueryListener(QueryListener queryListener) {
        Query query = queryListener.getQuery();
        QueryListenersInfo queryListenersInfo = this.queries.get(query);
        boolean z = queryListenersInfo == null;
        if (z) {
            queryListenersInfo = new QueryListenersInfo();
            this.queries.put(query, queryListenersInfo);
        }
        queryListenersInfo.listeners.add(queryListener);
        Assert.hardAssert(true ^ queryListener.onOnlineStateChanged(this.onlineState), "onOnlineStateChanged() shouldn't raise an event for brand-new listeners.", new Object[0]);
        if (queryListenersInfo.viewSnapshot != null && queryListener.onViewSnapshot(queryListenersInfo.viewSnapshot)) {
            raiseSnapshotsInSyncEvent();
        }
        if (z) {
            int unused = queryListenersInfo.targetId = this.syncEngine.listen(query);
        }
        return queryListenersInfo.targetId;
    }

    public void removeQueryListener(QueryListener queryListener) {
        boolean z;
        Query query = queryListener.getQuery();
        QueryListenersInfo queryListenersInfo = this.queries.get(query);
        if (queryListenersInfo != null) {
            queryListenersInfo.listeners.remove(queryListener);
            z = queryListenersInfo.listeners.isEmpty();
        } else {
            z = false;
        }
        if (z) {
            this.queries.remove(query);
            this.syncEngine.stopListening(query);
        }
    }

    public void addSnapshotsInSyncListener(EventListener<Void> eventListener) {
        this.snapshotsInSyncListeners.add(eventListener);
        eventListener.onEvent(null, (FirebaseFirestoreException) null);
    }

    public void removeSnapshotsInSyncListener(EventListener<Void> eventListener) {
        this.snapshotsInSyncListeners.remove(eventListener);
    }

    private void raiseSnapshotsInSyncEvent() {
        for (EventListener<Void> onEvent : this.snapshotsInSyncListeners) {
            onEvent.onEvent(null, (FirebaseFirestoreException) null);
        }
    }

    public void onViewSnapshots(List<ViewSnapshot> list) {
        boolean z = false;
        for (ViewSnapshot next : list) {
            QueryListenersInfo queryListenersInfo = this.queries.get(next.getQuery());
            if (queryListenersInfo != null) {
                for (QueryListener onViewSnapshot : queryListenersInfo.listeners) {
                    if (onViewSnapshot.onViewSnapshot(next)) {
                        z = true;
                    }
                }
                ViewSnapshot unused = queryListenersInfo.viewSnapshot = next;
            }
        }
        if (z) {
            raiseSnapshotsInSyncEvent();
        }
    }

    public void onError(Query query, Status status) {
        QueryListenersInfo queryListenersInfo = this.queries.get(query);
        if (queryListenersInfo != null) {
            for (QueryListener onError : queryListenersInfo.listeners) {
                onError.onError(Util.exceptionFromStatus(status));
            }
        }
        this.queries.remove(query);
    }

    public void handleOnlineStateChange(OnlineState onlineState2) {
        this.onlineState = onlineState2;
        boolean z = false;
        for (QueryListenersInfo access$000 : this.queries.values()) {
            for (QueryListener onOnlineStateChanged : access$000.listeners) {
                if (onOnlineStateChanged.onOnlineStateChanged(onlineState2)) {
                    z = true;
                }
            }
        }
        if (z) {
            raiseSnapshotsInSyncEvent();
        }
    }
}

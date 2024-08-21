package com.google.firebase.database.core;

import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.collection.LLRBNode;
import com.google.firebase.database.connection.CompoundHash;
import com.google.firebase.database.connection.ListenHashProvider;
import com.google.firebase.database.core.operation.AckUserWrite;
import com.google.firebase.database.core.operation.ListenComplete;
import com.google.firebase.database.core.operation.Merge;
import com.google.firebase.database.core.operation.Operation;
import com.google.firebase.database.core.operation.OperationSource;
import com.google.firebase.database.core.operation.Overwrite;
import com.google.firebase.database.core.persistence.PersistenceManager;
import com.google.firebase.database.core.utilities.Clock;
import com.google.firebase.database.core.utilities.ImmutableTree;
import com.google.firebase.database.core.utilities.NodeSizeEstimator;
import com.google.firebase.database.core.utilities.Pair;
import com.google.firebase.database.core.utilities.Utilities;
import com.google.firebase.database.core.view.CacheNode;
import com.google.firebase.database.core.view.Change;
import com.google.firebase.database.core.view.DataEvent;
import com.google.firebase.database.core.view.Event;
import com.google.firebase.database.core.view.QuerySpec;
import com.google.firebase.database.core.view.View;
import com.google.firebase.database.logging.LogWrapper;
import com.google.firebase.database.snapshot.ChildKey;
import com.google.firebase.database.snapshot.EmptyNode;
import com.google.firebase.database.snapshot.IndexedNode;
import com.google.firebase.database.snapshot.NamedNode;
import com.google.firebase.database.snapshot.Node;
import com.google.firebase.database.snapshot.RangeMerge;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Callable;

public class SyncTree {
    private static final long SIZE_THRESHOLD_FOR_COMPOUND_HASH = 1024;
    private final Set<QuerySpec> keepSyncedQueries = new HashSet();
    /* access modifiers changed from: private */
    public final ListenProvider listenProvider;
    /* access modifiers changed from: private */
    public final LogWrapper logger;
    private long nextQueryTag = 1;
    /* access modifiers changed from: private */
    public final WriteTree pendingWriteTree = new WriteTree();
    /* access modifiers changed from: private */
    public final PersistenceManager persistenceManager;
    /* access modifiers changed from: private */
    public final Map<QuerySpec, Tag> queryToTagMap = new HashMap();
    /* access modifiers changed from: private */
    public ImmutableTree<SyncPoint> syncPointTree = ImmutableTree.emptyInstance();
    /* access modifiers changed from: private */
    public final Map<Tag, QuerySpec> tagToQueryMap = new HashMap();

    public interface CompletionListener {
        List<? extends Event> onListenComplete(DatabaseError databaseError);
    }

    public interface ListenProvider {
        void startListening(QuerySpec querySpec, Tag tag, ListenHashProvider listenHashProvider, CompletionListener completionListener);

        void stopListening(QuerySpec querySpec, Tag tag);
    }

    private class ListenContainer implements ListenHashProvider, CompletionListener {
        /* access modifiers changed from: private */
        public final Tag tag;
        private final View view;

        public ListenContainer(View view2) {
            this.view = view2;
            this.tag = SyncTree.this.tagForQuery(view2.getQuery());
        }

        public CompoundHash getCompoundHash() {
            com.google.firebase.database.snapshot.CompoundHash fromNode = com.google.firebase.database.snapshot.CompoundHash.fromNode(this.view.getServerCache());
            List<Path> posts = fromNode.getPosts();
            ArrayList arrayList = new ArrayList(posts.size());
            for (Path asList : posts) {
                arrayList.add(asList.asList());
            }
            return new CompoundHash(arrayList, fromNode.getHashes());
        }

        public String getSimpleHash() {
            return this.view.getServerCache().getHash();
        }

        public boolean shouldIncludeCompoundHash() {
            return NodeSizeEstimator.estimateSerializedNodeSize(this.view.getServerCache()) > 1024;
        }

        public List<? extends Event> onListenComplete(DatabaseError databaseError) {
            if (databaseError == null) {
                QuerySpec query = this.view.getQuery();
                Tag tag2 = this.tag;
                if (tag2 != null) {
                    return SyncTree.this.applyTaggedListenComplete(tag2);
                }
                return SyncTree.this.applyListenComplete(query.getPath());
            }
            LogWrapper access$100 = SyncTree.this.logger;
            access$100.warn("Listen at " + this.view.getQuery().getPath() + " failed: " + databaseError.toString());
            return SyncTree.this.removeAllEventRegistrations(this.view.getQuery(), databaseError);
        }
    }

    public SyncTree(Context context, PersistenceManager persistenceManager2, ListenProvider listenProvider2) {
        this.listenProvider = listenProvider2;
        this.persistenceManager = persistenceManager2;
        this.logger = context.getLogger("SyncTree");
    }

    public boolean isEmpty() {
        return this.syncPointTree.isEmpty();
    }

    public List<? extends Event> applyUserOverwrite(Path path, Node node, Node node2, long j, boolean z, boolean z2) {
        Utilities.hardAssert(z || !z2, "We shouldn't be persisting non-visible writes.");
        final boolean z3 = z2;
        final Path path2 = path;
        final Node node3 = node;
        final long j2 = j;
        final Node node4 = node2;
        final boolean z4 = z;
        return (List) this.persistenceManager.runInTransaction(new Callable<List<? extends Event>>() {
            public List<? extends Event> call() {
                if (z3) {
                    SyncTree.this.persistenceManager.saveUserOverwrite(path2, node3, j2);
                }
                SyncTree.this.pendingWriteTree.addOverwrite(path2, node4, Long.valueOf(j2), z4);
                if (!z4) {
                    return Collections.emptyList();
                }
                return SyncTree.this.applyOperationToSyncPoints(new Overwrite(OperationSource.USER, path2, node4));
            }
        });
    }

    public List<? extends Event> applyUserMerge(Path path, CompoundWrite compoundWrite, CompoundWrite compoundWrite2, long j, boolean z) {
        final boolean z2 = z;
        final Path path2 = path;
        final CompoundWrite compoundWrite3 = compoundWrite;
        final long j2 = j;
        final CompoundWrite compoundWrite4 = compoundWrite2;
        return (List) this.persistenceManager.runInTransaction(new Callable<List<? extends Event>>() {
            public List<? extends Event> call() throws Exception {
                if (z2) {
                    SyncTree.this.persistenceManager.saveUserMerge(path2, compoundWrite3, j2);
                }
                SyncTree.this.pendingWriteTree.addMerge(path2, compoundWrite4, Long.valueOf(j2));
                return SyncTree.this.applyOperationToSyncPoints(new Merge(OperationSource.USER, path2, compoundWrite4));
            }
        });
    }

    public List<? extends Event> ackUserWrite(long j, boolean z, boolean z2, Clock clock) {
        final boolean z3 = z2;
        final long j2 = j;
        final boolean z4 = z;
        final Clock clock2 = clock;
        return (List) this.persistenceManager.runInTransaction(new Callable<List<? extends Event>>() {
            public List<? extends Event> call() {
                if (z3) {
                    SyncTree.this.persistenceManager.removeUserWrite(j2);
                }
                UserWriteRecord write = SyncTree.this.pendingWriteTree.getWrite(j2);
                boolean removeWrite = SyncTree.this.pendingWriteTree.removeWrite(j2);
                if (write.isVisible() && !z4) {
                    Map<String, Object> generateServerValues = ServerValues.generateServerValues(clock2);
                    if (write.isOverwrite()) {
                        SyncTree.this.persistenceManager.applyUserWriteToServerCache(write.getPath(), ServerValues.resolveDeferredValueSnapshot(write.getOverwrite(), SyncTree.this, write.getPath(), generateServerValues));
                    } else {
                        SyncTree.this.persistenceManager.applyUserWriteToServerCache(write.getPath(), ServerValues.resolveDeferredValueMerge(write.getMerge(), SyncTree.this, write.getPath(), generateServerValues));
                    }
                }
                if (!removeWrite) {
                    return Collections.emptyList();
                }
                ImmutableTree emptyInstance = ImmutableTree.emptyInstance();
                if (write.isOverwrite()) {
                    emptyInstance = emptyInstance.set(Path.getEmptyPath(), true);
                } else {
                    Iterator<Map.Entry<Path, Node>> it = write.getMerge().iterator();
                    while (it.hasNext()) {
                        emptyInstance = emptyInstance.set((Path) it.next().getKey(), true);
                    }
                }
                return SyncTree.this.applyOperationToSyncPoints(new AckUserWrite(write.getPath(), emptyInstance, z4));
            }
        });
    }

    public List<? extends Event> removeAllWrites() {
        return (List) this.persistenceManager.runInTransaction(new Callable<List<? extends Event>>() {
            public List<? extends Event> call() throws Exception {
                SyncTree.this.persistenceManager.removeAllUserWrites();
                if (SyncTree.this.pendingWriteTree.purgeAllWrites().isEmpty()) {
                    return Collections.emptyList();
                }
                return SyncTree.this.applyOperationToSyncPoints(new AckUserWrite(Path.getEmptyPath(), new ImmutableTree(true), true));
            }
        });
    }

    public List<? extends Event> applyServerOverwrite(final Path path, final Node node) {
        return (List) this.persistenceManager.runInTransaction(new Callable<List<? extends Event>>() {
            public List<? extends Event> call() {
                SyncTree.this.persistenceManager.updateServerCache(QuerySpec.defaultQueryAtPath(path), node);
                return SyncTree.this.applyOperationToSyncPoints(new Overwrite(OperationSource.SERVER, path, node));
            }
        });
    }

    public List<? extends Event> applyServerMerge(final Path path, final Map<Path, Node> map) {
        return (List) this.persistenceManager.runInTransaction(new Callable<List<? extends Event>>() {
            public List<? extends Event> call() {
                CompoundWrite fromPathMerge = CompoundWrite.fromPathMerge(map);
                SyncTree.this.persistenceManager.updateServerCache(path, fromPathMerge);
                return SyncTree.this.applyOperationToSyncPoints(new Merge(OperationSource.SERVER, path, fromPathMerge));
            }
        });
    }

    public List<? extends Event> applyServerRangeMerges(Path path, List<RangeMerge> list) {
        SyncPoint syncPoint = this.syncPointTree.get(path);
        if (syncPoint == null) {
            return Collections.emptyList();
        }
        View completeView = syncPoint.getCompleteView();
        if (completeView == null) {
            return Collections.emptyList();
        }
        Node serverCache = completeView.getServerCache();
        for (RangeMerge applyTo : list) {
            serverCache = applyTo.applyTo(serverCache);
        }
        return applyServerOverwrite(path, serverCache);
    }

    public List<? extends Event> applyTaggedRangeMerges(Path path, List<RangeMerge> list, Tag tag) {
        QuerySpec queryForTag = queryForTag(tag);
        if (queryForTag == null) {
            return Collections.emptyList();
        }
        Utilities.hardAssert(path.equals(queryForTag.getPath()));
        SyncPoint syncPoint = this.syncPointTree.get(queryForTag.getPath());
        boolean z = true;
        Utilities.hardAssert(syncPoint != null, "Missing sync point for query tag that we're tracking");
        View viewForQuery = syncPoint.viewForQuery(queryForTag);
        if (viewForQuery == null) {
            z = false;
        }
        Utilities.hardAssert(z, "Missing view for query tag that we're tracking");
        Node serverCache = viewForQuery.getServerCache();
        for (RangeMerge applyTo : list) {
            serverCache = applyTo.applyTo(serverCache);
        }
        return applyTaggedQueryOverwrite(path, serverCache, tag);
    }

    public List<? extends Event> applyListenComplete(final Path path) {
        return (List) this.persistenceManager.runInTransaction(new Callable<List<? extends Event>>() {
            public List<? extends Event> call() {
                SyncTree.this.persistenceManager.setQueryComplete(QuerySpec.defaultQueryAtPath(path));
                return SyncTree.this.applyOperationToSyncPoints(new ListenComplete(OperationSource.SERVER, path));
            }
        });
    }

    public List<? extends Event> applyTaggedListenComplete(final Tag tag) {
        return (List) this.persistenceManager.runInTransaction(new Callable<List<? extends Event>>() {
            public List<? extends Event> call() {
                QuerySpec access$500 = SyncTree.this.queryForTag(tag);
                if (access$500 == null) {
                    return Collections.emptyList();
                }
                SyncTree.this.persistenceManager.setQueryComplete(access$500);
                return SyncTree.this.applyTaggedOperation(access$500, new ListenComplete(OperationSource.forServerTaggedQuery(access$500.getParams()), Path.getEmptyPath()));
            }
        });
    }

    /* access modifiers changed from: private */
    public List<? extends Event> applyTaggedOperation(QuerySpec querySpec, Operation operation) {
        Path path = querySpec.getPath();
        SyncPoint syncPoint = this.syncPointTree.get(path);
        Utilities.hardAssert(syncPoint != null, "Missing sync point for query tag that we're tracking");
        return syncPoint.applyOperation(operation, this.pendingWriteTree.childWrites(path), (Node) null);
    }

    public List<? extends Event> applyTaggedQueryOverwrite(final Path path, final Node node, final Tag tag) {
        return (List) this.persistenceManager.runInTransaction(new Callable<List<? extends Event>>() {
            public List<? extends Event> call() {
                QuerySpec access$500 = SyncTree.this.queryForTag(tag);
                if (access$500 == null) {
                    return Collections.emptyList();
                }
                Path relative = Path.getRelative(access$500.getPath(), path);
                SyncTree.this.persistenceManager.updateServerCache(relative.isEmpty() ? access$500 : QuerySpec.defaultQueryAtPath(path), node);
                return SyncTree.this.applyTaggedOperation(access$500, new Overwrite(OperationSource.forServerTaggedQuery(access$500.getParams()), relative, node));
            }
        });
    }

    public List<? extends Event> applyTaggedQueryMerge(final Path path, final Map<Path, Node> map, final Tag tag) {
        return (List) this.persistenceManager.runInTransaction(new Callable<List<? extends Event>>() {
            public List<? extends Event> call() {
                QuerySpec access$500 = SyncTree.this.queryForTag(tag);
                if (access$500 == null) {
                    return Collections.emptyList();
                }
                Path relative = Path.getRelative(access$500.getPath(), path);
                CompoundWrite fromPathMerge = CompoundWrite.fromPathMerge(map);
                SyncTree.this.persistenceManager.updateServerCache(path, fromPathMerge);
                return SyncTree.this.applyTaggedOperation(access$500, new Merge(OperationSource.forServerTaggedQuery(access$500.getParams()), relative, fromPathMerge));
            }
        });
    }

    public void setQueryActive(final QuerySpec querySpec) {
        this.persistenceManager.runInTransaction(new Callable<Void>() {
            public Void call() {
                SyncTree.this.persistenceManager.setQueryActive(querySpec);
                return null;
            }
        });
    }

    public void setQueryInactive(final QuerySpec querySpec) {
        this.persistenceManager.runInTransaction(new Callable<Void>() {
            public Void call() {
                SyncTree.this.persistenceManager.setQueryInactive(querySpec);
                return null;
            }
        });
    }

    public List<? extends Event> addEventRegistration(final EventRegistration eventRegistration) {
        return (List) this.persistenceManager.runInTransaction(new Callable<List<? extends Event>>() {
            public List<? extends Event> call() {
                CacheNode cacheNode;
                Node completeServerCache;
                QuerySpec querySpec = eventRegistration.getQuerySpec();
                Path path = querySpec.getPath();
                ImmutableTree access$700 = SyncTree.this.syncPointTree;
                Node node = null;
                Path path2 = path;
                boolean z = false;
                while (!access$700.isEmpty()) {
                    SyncPoint syncPoint = (SyncPoint) access$700.getValue();
                    if (syncPoint != null) {
                        if (node == null) {
                            node = syncPoint.getCompleteServerCache(path2);
                        }
                        z = z || syncPoint.hasCompleteView();
                    }
                    access$700 = access$700.getChild(path2.isEmpty() ? ChildKey.fromString("") : path2.getFront());
                    path2 = path2.popFront();
                }
                SyncPoint syncPoint2 = (SyncPoint) SyncTree.this.syncPointTree.get(path);
                if (syncPoint2 == null) {
                    syncPoint2 = new SyncPoint(SyncTree.this.persistenceManager);
                    SyncTree syncTree = SyncTree.this;
                    ImmutableTree unused = syncTree.syncPointTree = syncTree.syncPointTree.set(path, syncPoint2);
                } else {
                    z = z || syncPoint2.hasCompleteView();
                    if (node == null) {
                        node = syncPoint2.getCompleteServerCache(Path.getEmptyPath());
                    }
                }
                SyncTree.this.persistenceManager.setQueryActive(querySpec);
                if (node != null) {
                    cacheNode = new CacheNode(IndexedNode.from(node, querySpec.getIndex()), true, false);
                } else {
                    cacheNode = SyncTree.this.persistenceManager.serverCache(querySpec);
                    if (!cacheNode.isFullyInitialized()) {
                        Node Empty = EmptyNode.Empty();
                        Iterator it = SyncTree.this.syncPointTree.subtree(path).getChildren().iterator();
                        while (it.hasNext()) {
                            Map.Entry entry = (Map.Entry) it.next();
                            SyncPoint syncPoint3 = (SyncPoint) ((ImmutableTree) entry.getValue()).getValue();
                            if (!(syncPoint3 == null || (completeServerCache = syncPoint3.getCompleteServerCache(Path.getEmptyPath())) == null)) {
                                Empty = Empty.updateImmediateChild((ChildKey) entry.getKey(), completeServerCache);
                            }
                        }
                        for (NamedNode namedNode : cacheNode.getNode()) {
                            if (!Empty.hasChild(namedNode.getName())) {
                                Empty = Empty.updateImmediateChild(namedNode.getName(), namedNode.getNode());
                            }
                        }
                        cacheNode = new CacheNode(IndexedNode.from(Empty, querySpec.getIndex()), false, false);
                    }
                }
                boolean viewExistsForQuery = syncPoint2.viewExistsForQuery(querySpec);
                if (!viewExistsForQuery && !querySpec.loadsAllData()) {
                    Utilities.hardAssert(!SyncTree.this.queryToTagMap.containsKey(querySpec), "View does not exist but we have a tag");
                    Tag access$900 = SyncTree.this.getNextQueryTag();
                    SyncTree.this.queryToTagMap.put(querySpec, access$900);
                    SyncTree.this.tagToQueryMap.put(access$900, querySpec);
                }
                List<DataEvent> addEventRegistration = syncPoint2.addEventRegistration(eventRegistration, SyncTree.this.pendingWriteTree.childWrites(path), cacheNode);
                if (!viewExistsForQuery && !z) {
                    SyncTree.this.setupListener(querySpec, syncPoint2.viewForQuery(querySpec));
                }
                return addEventRegistration;
            }
        });
    }

    public List<Event> removeEventRegistration(EventRegistration eventRegistration) {
        return removeEventRegistration(eventRegistration.getQuerySpec(), eventRegistration, (DatabaseError) null);
    }

    public List<Event> removeAllEventRegistrations(QuerySpec querySpec, DatabaseError databaseError) {
        return removeEventRegistration(querySpec, (EventRegistration) null, databaseError);
    }

    private List<Event> removeEventRegistration(final QuerySpec querySpec, final EventRegistration eventRegistration, final DatabaseError databaseError) {
        return (List) this.persistenceManager.runInTransaction(new Callable<List<Event>>() {
            public List<Event> call() {
                boolean z;
                Path path = querySpec.getPath();
                SyncPoint syncPoint = (SyncPoint) SyncTree.this.syncPointTree.get(path);
                List<Event> arrayList = new ArrayList<>();
                if (syncPoint != null && (querySpec.isDefault() || syncPoint.viewExistsForQuery(querySpec))) {
                    Pair<List<QuerySpec>, List<Event>> removeEventRegistration = syncPoint.removeEventRegistration(querySpec, eventRegistration, databaseError);
                    if (syncPoint.isEmpty()) {
                        SyncTree syncTree = SyncTree.this;
                        ImmutableTree unused = syncTree.syncPointTree = syncTree.syncPointTree.remove(path);
                    }
                    List<QuerySpec> first = removeEventRegistration.getFirst();
                    arrayList = removeEventRegistration.getSecond();
                    Iterator it = first.iterator();
                    loop0:
                    while (true) {
                        z = false;
                        while (true) {
                            if (!it.hasNext()) {
                                break loop0;
                            }
                            QuerySpec querySpec = (QuerySpec) it.next();
                            SyncTree.this.persistenceManager.setQueryInactive(querySpec);
                            if (z || querySpec.loadsAllData()) {
                                z = true;
                            }
                        }
                    }
                    ImmutableTree access$700 = SyncTree.this.syncPointTree;
                    boolean z2 = access$700.getValue() != null && ((SyncPoint) access$700.getValue()).hasCompleteView();
                    Iterator<ChildKey> it2 = path.iterator();
                    while (it2.hasNext()) {
                        access$700 = access$700.getChild(it2.next());
                        z2 = z2 || (access$700.getValue() != null && ((SyncPoint) access$700.getValue()).hasCompleteView());
                        if (!z2) {
                            if (access$700.isEmpty()) {
                                break;
                            }
                        } else {
                            break;
                        }
                    }
                    if (z && !z2) {
                        ImmutableTree subtree = SyncTree.this.syncPointTree.subtree(path);
                        if (!subtree.isEmpty()) {
                            for (View view : SyncTree.this.collectDistinctViewsForSubTree(subtree)) {
                                ListenContainer listenContainer = new ListenContainer(view);
                                SyncTree.this.listenProvider.startListening(SyncTree.this.queryForListening(view.getQuery()), listenContainer.tag, listenContainer, listenContainer);
                            }
                        }
                    }
                    if (!z2 && !first.isEmpty() && databaseError == null) {
                        if (z) {
                            SyncTree.this.listenProvider.stopListening(SyncTree.this.queryForListening(querySpec), (Tag) null);
                        } else {
                            for (QuerySpec querySpec2 : first) {
                                Tag access$000 = SyncTree.this.tagForQuery(querySpec2);
                                Utilities.hardAssert(access$000 != null);
                                SyncTree.this.listenProvider.stopListening(SyncTree.this.queryForListening(querySpec2), access$000);
                            }
                        }
                    }
                    SyncTree.this.removeTags(first);
                }
                return arrayList;
            }
        });
    }

    private static class KeepSyncedEventRegistration extends EventRegistration {
        private QuerySpec spec;

        public DataEvent createEvent(Change change, QuerySpec querySpec) {
            return null;
        }

        public void fireCancelEvent(DatabaseError databaseError) {
        }

        public void fireEvent(DataEvent dataEvent) {
        }

        public boolean respondsTo(Event.EventType eventType) {
            return false;
        }

        public KeepSyncedEventRegistration(QuerySpec querySpec) {
            this.spec = querySpec;
        }

        public EventRegistration clone(QuerySpec querySpec) {
            return new KeepSyncedEventRegistration(querySpec);
        }

        public boolean isSameListener(EventRegistration eventRegistration) {
            return eventRegistration instanceof KeepSyncedEventRegistration;
        }

        public QuerySpec getQuerySpec() {
            return this.spec;
        }

        public boolean equals(Object obj) {
            return (obj instanceof KeepSyncedEventRegistration) && ((KeepSyncedEventRegistration) obj).spec.equals(this.spec);
        }

        public int hashCode() {
            return this.spec.hashCode();
        }
    }

    public void keepSynced(QuerySpec querySpec, boolean z) {
        if (z && !this.keepSyncedQueries.contains(querySpec)) {
            addEventRegistration(new KeepSyncedEventRegistration(querySpec));
            this.keepSyncedQueries.add(querySpec);
        } else if (!z && this.keepSyncedQueries.contains(querySpec)) {
            removeEventRegistration(new KeepSyncedEventRegistration(querySpec));
            this.keepSyncedQueries.remove(querySpec);
        }
    }

    /* access modifiers changed from: private */
    public List<View> collectDistinctViewsForSubTree(ImmutableTree<SyncPoint> immutableTree) {
        ArrayList arrayList = new ArrayList();
        collectDistinctViewsForSubTree(immutableTree, arrayList);
        return arrayList;
    }

    private void collectDistinctViewsForSubTree(ImmutableTree<SyncPoint> immutableTree, List<View> list) {
        SyncPoint value = immutableTree.getValue();
        if (value == null || !value.hasCompleteView()) {
            if (value != null) {
                list.addAll(value.getQueryViews());
            }
            Iterator<Map.Entry<ChildKey, ImmutableTree<SyncPoint>>> it = immutableTree.getChildren().iterator();
            while (it.hasNext()) {
                collectDistinctViewsForSubTree((ImmutableTree) it.next().getValue(), list);
            }
            return;
        }
        list.add(value.getCompleteView());
    }

    /* access modifiers changed from: private */
    public void removeTags(List<QuerySpec> list) {
        for (QuerySpec next : list) {
            if (!next.loadsAllData()) {
                Tag tagForQuery = tagForQuery(next);
                Utilities.hardAssert(tagForQuery != null);
                this.queryToTagMap.remove(next);
                this.tagToQueryMap.remove(tagForQuery);
            }
        }
    }

    /* access modifiers changed from: private */
    public QuerySpec queryForListening(QuerySpec querySpec) {
        return (!querySpec.loadsAllData() || querySpec.isDefault()) ? querySpec : QuerySpec.defaultQueryAtPath(querySpec.getPath());
    }

    /* access modifiers changed from: private */
    public void setupListener(QuerySpec querySpec, View view) {
        Path path = querySpec.getPath();
        Tag tagForQuery = tagForQuery(querySpec);
        ListenContainer listenContainer = new ListenContainer(view);
        this.listenProvider.startListening(queryForListening(querySpec), tagForQuery, listenContainer, listenContainer);
        ImmutableTree<SyncPoint> subtree = this.syncPointTree.subtree(path);
        if (tagForQuery != null) {
            Utilities.hardAssert(!subtree.getValue().hasCompleteView(), "If we're adding a query, it shouldn't be shadowed");
        } else {
            subtree.foreach(new ImmutableTree.TreeVisitor<SyncPoint, Void>() {
                public Void onNodeValue(Path path, SyncPoint syncPoint, Void voidR) {
                    if (path.isEmpty() || !syncPoint.hasCompleteView()) {
                        for (View query : syncPoint.getQueryViews()) {
                            QuerySpec query2 = query.getQuery();
                            SyncTree.this.listenProvider.stopListening(SyncTree.this.queryForListening(query2), SyncTree.this.tagForQuery(query2));
                        }
                        return null;
                    }
                    QuerySpec query3 = syncPoint.getCompleteView().getQuery();
                    SyncTree.this.listenProvider.stopListening(SyncTree.this.queryForListening(query3), SyncTree.this.tagForQuery(query3));
                    return null;
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public QuerySpec queryForTag(Tag tag) {
        return this.tagToQueryMap.get(tag);
    }

    /* access modifiers changed from: private */
    public Tag tagForQuery(QuerySpec querySpec) {
        return this.queryToTagMap.get(querySpec);
    }

    public Node calcCompleteEventCache(Path path, List<Long> list) {
        ImmutableTree<SyncPoint> immutableTree = this.syncPointTree;
        SyncPoint value = immutableTree.getValue();
        Path emptyPath = Path.getEmptyPath();
        Node node = null;
        Path path2 = path;
        do {
            ChildKey front = path2.getFront();
            path2 = path2.popFront();
            emptyPath = emptyPath.child(front);
            Path relative = Path.getRelative(emptyPath, path);
            immutableTree = front != null ? immutableTree.getChild(front) : ImmutableTree.emptyInstance();
            SyncPoint value2 = immutableTree.getValue();
            if (value2 != null) {
                node = value2.getCompleteServerCache(relative);
            }
            if (path2.isEmpty()) {
                break;
            }
        } while (node == null);
        return this.pendingWriteTree.calcCompleteEventCache(path, node, list, true);
    }

    /* access modifiers changed from: private */
    public Tag getNextQueryTag() {
        long j = this.nextQueryTag;
        this.nextQueryTag = 1 + j;
        return new Tag(j);
    }

    /* access modifiers changed from: private */
    public List<Event> applyOperationToSyncPoints(Operation operation) {
        return applyOperationHelper(operation, this.syncPointTree, (Node) null, this.pendingWriteTree.childWrites(Path.getEmptyPath()));
    }

    private List<Event> applyOperationHelper(Operation operation, ImmutableTree<SyncPoint> immutableTree, Node node, WriteTreeRef writeTreeRef) {
        if (operation.getPath().isEmpty()) {
            return applyOperationDescendantsHelper(operation, immutableTree, node, writeTreeRef);
        }
        SyncPoint value = immutableTree.getValue();
        if (node == null && value != null) {
            node = value.getCompleteServerCache(Path.getEmptyPath());
        }
        ArrayList arrayList = new ArrayList();
        ChildKey front = operation.getPath().getFront();
        Operation operationForChild = operation.operationForChild(front);
        ImmutableTree immutableTree2 = immutableTree.getChildren().get(front);
        if (!(immutableTree2 == null || operationForChild == null)) {
            arrayList.addAll(applyOperationHelper(operationForChild, immutableTree2, node != null ? node.getImmediateChild(front) : null, writeTreeRef.child(front)));
        }
        if (value != null) {
            arrayList.addAll(value.applyOperation(operation, writeTreeRef, node));
        }
        return arrayList;
    }

    /* access modifiers changed from: private */
    public List<Event> applyOperationDescendantsHelper(Operation operation, ImmutableTree<SyncPoint> immutableTree, Node node, WriteTreeRef writeTreeRef) {
        SyncPoint value = immutableTree.getValue();
        if (node == null && value != null) {
            node = value.getCompleteServerCache(Path.getEmptyPath());
        }
        ArrayList arrayList = new ArrayList();
        final Node node2 = node;
        final WriteTreeRef writeTreeRef2 = writeTreeRef;
        final Operation operation2 = operation;
        final ArrayList arrayList2 = arrayList;
        immutableTree.getChildren().inOrderTraversal(new LLRBNode.NodeVisitor<ChildKey, ImmutableTree<SyncPoint>>() {
            public void visitEntry(ChildKey childKey, ImmutableTree<SyncPoint> immutableTree) {
                Node node = node2;
                Node immediateChild = node != null ? node.getImmediateChild(childKey) : null;
                WriteTreeRef child = writeTreeRef2.child(childKey);
                Operation operationForChild = operation2.operationForChild(childKey);
                if (operationForChild != null) {
                    arrayList2.addAll(SyncTree.this.applyOperationDescendantsHelper(operationForChild, immutableTree, immediateChild, child));
                }
            }
        });
        if (value != null) {
            arrayList.addAll(value.applyOperation(operation, writeTreeRef, node));
        }
        return arrayList;
    }

    /* access modifiers changed from: package-private */
    public ImmutableTree<SyncPoint> getSyncPointTree() {
        return this.syncPointTree;
    }
}

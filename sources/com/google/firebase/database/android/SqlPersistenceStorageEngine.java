package com.google.firebase.database.android;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabaseLockedException;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.p000v4.media.session.PlaybackStateCompat;
import com.bumptech.glide.load.Key;
import com.google.firebase.database.DatabaseException;
import com.google.firebase.database.core.CompoundWrite;
import com.google.firebase.database.core.Path;
import com.google.firebase.database.core.UserWriteRecord;
import com.google.firebase.database.core.persistence.PersistenceStorageEngine;
import com.google.firebase.database.core.persistence.PruneForest;
import com.google.firebase.database.core.persistence.TrackedQuery;
import com.google.firebase.database.core.utilities.ImmutableTree;
import com.google.firebase.database.core.utilities.NodeSizeEstimator;
import com.google.firebase.database.core.utilities.Pair;
import com.google.firebase.database.core.utilities.Utilities;
import com.google.firebase.database.core.view.QuerySpec;
import com.google.firebase.database.logging.LogWrapper;
import com.google.firebase.database.snapshot.ChildKey;
import com.google.firebase.database.snapshot.ChildrenNode;
import com.google.firebase.database.snapshot.EmptyNode;
import com.google.firebase.database.snapshot.NamedNode;
import com.google.firebase.database.snapshot.Node;
import com.google.firebase.database.snapshot.NodeUtilities;
import com.google.firebase.database.util.JsonMapper;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

public class SqlPersistenceStorageEngine implements PersistenceStorageEngine {
    private static final int CHILDREN_NODE_SPLIT_SIZE_THRESHOLD = 16384;
    private static final String CREATE_SERVER_CACHE = "CREATE TABLE serverCache (path TEXT PRIMARY KEY, value BLOB);";
    private static final String CREATE_TRACKED_KEYS = "CREATE TABLE trackedKeys (id INTEGER, key TEXT);";
    private static final String CREATE_TRACKED_QUERIES = "CREATE TABLE trackedQueries (id INTEGER PRIMARY KEY, path TEXT, queryParams TEXT, lastUse INTEGER, complete INTEGER, active INTEGER);";
    private static final String CREATE_WRITES = "CREATE TABLE writes (id INTEGER, path TEXT, type TEXT, part INTEGER, node BLOB, UNIQUE (id, part));";
    private static final String FIRST_PART_KEY = ".part-0000";
    private static final String LOGGER_COMPONENT = "Persistence";
    private static final String PART_KEY_FORMAT = ".part-%04d";
    private static final String PART_KEY_PREFIX = ".part-";
    private static final String PATH_COLUMN_NAME = "path";
    private static final String ROW_ID_COLUMN_NAME = "rowid";
    private static final int ROW_SPLIT_SIZE = 262144;
    private static final String SERVER_CACHE_TABLE = "serverCache";
    private static final String TRACKED_KEYS_ID_COLUMN_NAME = "id";
    private static final String TRACKED_KEYS_KEY_COLUMN_NAME = "key";
    private static final String TRACKED_KEYS_TABLE = "trackedKeys";
    private static final String TRACKED_QUERY_ACTIVE_COLUMN_NAME = "active";
    private static final String TRACKED_QUERY_COMPLETE_COLUMN_NAME = "complete";
    private static final String TRACKED_QUERY_ID_COLUMN_NAME = "id";
    private static final String TRACKED_QUERY_LAST_USE_COLUMN_NAME = "lastUse";
    private static final String TRACKED_QUERY_PARAMS_COLUMN_NAME = "queryParams";
    private static final String TRACKED_QUERY_PATH_COLUMN_NAME = "path";
    private static final String TRACKED_QUERY_TABLE = "trackedQueries";
    private static final Charset UTF8_CHARSET = Charset.forName(Key.STRING_CHARSET_NAME);
    private static final String VALUE_COLUMN_NAME = "value";
    private static final String WRITES_TABLE = "writes";
    private static final String WRITE_ID_COLUMN_NAME = "id";
    private static final String WRITE_NODE_COLUMN_NAME = "node";
    private static final String WRITE_PART_COLUMN_NAME = "part";
    private static final String WRITE_TYPE_COLUMN_NAME = "type";
    private static final String WRITE_TYPE_MERGE = "m";
    private static final String WRITE_TYPE_OVERWRITE = "o";
    private final SQLiteDatabase database;
    private boolean insideTransaction;
    private final LogWrapper logger;
    private long transactionStart = 0;

    private static class PersistentCacheOpenHelper extends SQLiteOpenHelper {
        private static final int DATABASE_VERSION = 2;

        public PersistentCacheOpenHelper(Context context, String str) {
            super(context, str, (SQLiteDatabase.CursorFactory) null, 2);
        }

        public void onCreate(SQLiteDatabase sQLiteDatabase) {
            sQLiteDatabase.execSQL(SqlPersistenceStorageEngine.CREATE_SERVER_CACHE);
            sQLiteDatabase.execSQL(SqlPersistenceStorageEngine.CREATE_WRITES);
            sQLiteDatabase.execSQL(SqlPersistenceStorageEngine.CREATE_TRACKED_QUERIES);
            sQLiteDatabase.execSQL(SqlPersistenceStorageEngine.CREATE_TRACKED_KEYS);
        }

        public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
            Utilities.hardAssert(i2 == 2, "Why is onUpgrade() called with a different version?");
            if (i <= 1) {
                dropTable(sQLiteDatabase, SqlPersistenceStorageEngine.SERVER_CACHE_TABLE);
                sQLiteDatabase.execSQL(SqlPersistenceStorageEngine.CREATE_SERVER_CACHE);
                dropTable(sQLiteDatabase, SqlPersistenceStorageEngine.TRACKED_QUERY_COMPLETE_COLUMN_NAME);
                sQLiteDatabase.execSQL(SqlPersistenceStorageEngine.CREATE_TRACKED_KEYS);
                sQLiteDatabase.execSQL(SqlPersistenceStorageEngine.CREATE_TRACKED_QUERIES);
                return;
            }
            throw new AssertionError("We don't handle upgrading to " + i2);
        }

        private void dropTable(SQLiteDatabase sQLiteDatabase, String str) {
            sQLiteDatabase.execSQL("DROP TABLE IF EXISTS " + str);
        }
    }

    public SqlPersistenceStorageEngine(Context context, com.google.firebase.database.core.Context context2, String str) {
        try {
            String encode = URLEncoder.encode(str, "utf-8");
            this.logger = context2.getLogger(LOGGER_COMPONENT);
            this.database = openDatabase(context, encode);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveUserOverwrite(Path path, Node node, long j) {
        verifyInsideTransaction();
        long currentTimeMillis = System.currentTimeMillis();
        saveWrite(path, j, WRITE_TYPE_OVERWRITE, serializeObject(node.getValue(true)));
        long currentTimeMillis2 = System.currentTimeMillis() - currentTimeMillis;
        if (this.logger.logsDebug()) {
            this.logger.debug(String.format(Locale.US, "Persisted user overwrite in %dms", new Object[]{Long.valueOf(currentTimeMillis2)}), new Object[0]);
        }
    }

    public void saveUserMerge(Path path, CompoundWrite compoundWrite, long j) {
        verifyInsideTransaction();
        long currentTimeMillis = System.currentTimeMillis();
        saveWrite(path, j, WRITE_TYPE_MERGE, serializeObject(compoundWrite.getValue(true)));
        long currentTimeMillis2 = System.currentTimeMillis() - currentTimeMillis;
        if (this.logger.logsDebug()) {
            this.logger.debug(String.format(Locale.US, "Persisted user merge in %dms", new Object[]{Long.valueOf(currentTimeMillis2)}), new Object[0]);
        }
    }

    public void removeUserWrite(long j) {
        verifyInsideTransaction();
        long currentTimeMillis = System.currentTimeMillis();
        int delete = this.database.delete(WRITES_TABLE, "id = ?", new String[]{String.valueOf(j)});
        long currentTimeMillis2 = System.currentTimeMillis() - currentTimeMillis;
        if (this.logger.logsDebug()) {
            this.logger.debug(String.format(Locale.US, "Deleted %d write(s) with writeId %d in %dms", new Object[]{Integer.valueOf(delete), Long.valueOf(j), Long.valueOf(currentTimeMillis2)}), new Object[0]);
        }
    }

    public List<UserWriteRecord> loadUserWrites() {
        byte[] bArr;
        UserWriteRecord userWriteRecord;
        String[] strArr = {"id", "path", WRITE_TYPE_COLUMN_NAME, WRITE_PART_COLUMN_NAME, WRITE_NODE_COLUMN_NAME};
        long currentTimeMillis = System.currentTimeMillis();
        Cursor query = this.database.query(WRITES_TABLE, strArr, (String) null, (String[]) null, (String) null, (String) null, "id, part");
        ArrayList arrayList = new ArrayList();
        while (query.moveToNext()) {
            try {
                long j = query.getLong(0);
                Path path = new Path(query.getString(1));
                String string = query.getString(2);
                if (query.isNull(3)) {
                    bArr = query.getBlob(4);
                } else {
                    ArrayList arrayList2 = new ArrayList();
                    do {
                        arrayList2.add(query.getBlob(4));
                        if (!query.moveToNext() || query.getLong(0) != j) {
                            query.moveToPrevious();
                            bArr = joinBytes(arrayList2);
                        }
                        arrayList2.add(query.getBlob(4));
                        break;
                    } while (query.getLong(0) != j);
                    query.moveToPrevious();
                    bArr = joinBytes(arrayList2);
                }
                Object parseJsonValue = JsonMapper.parseJsonValue(new String(bArr, UTF8_CHARSET));
                if (WRITE_TYPE_OVERWRITE.equals(string)) {
                    userWriteRecord = new UserWriteRecord(j, path, NodeUtilities.NodeFromJSON(parseJsonValue), true);
                } else if (WRITE_TYPE_MERGE.equals(string)) {
                    userWriteRecord = new UserWriteRecord(j, path, CompoundWrite.fromValue((Map) parseJsonValue));
                } else {
                    throw new IllegalStateException("Got invalid write type: " + string);
                }
                arrayList.add(userWriteRecord);
            } catch (IOException e) {
                throw new RuntimeException("Failed to load writes", e);
            } catch (Throwable th) {
                query.close();
                throw th;
            }
        }
        long currentTimeMillis2 = System.currentTimeMillis() - currentTimeMillis;
        if (this.logger.logsDebug()) {
            this.logger.debug(String.format(Locale.US, "Loaded %d writes in %dms", new Object[]{Integer.valueOf(arrayList.size()), Long.valueOf(currentTimeMillis2)}), new Object[0]);
        }
        query.close();
        return arrayList;
    }

    private void saveWrite(Path path, long j, String str, byte[] bArr) {
        String str2 = str;
        byte[] bArr2 = bArr;
        verifyInsideTransaction();
        this.database.delete(WRITES_TABLE, "id = ?", new String[]{String.valueOf(j)});
        if (bArr2.length >= 262144) {
            List<byte[]> splitBytes = splitBytes(bArr2, 262144);
            for (int i = 0; i < splitBytes.size(); i++) {
                ContentValues contentValues = new ContentValues();
                contentValues.put("id", Long.valueOf(j));
                contentValues.put("path", pathToKey(path));
                contentValues.put(WRITE_TYPE_COLUMN_NAME, str2);
                contentValues.put(WRITE_PART_COLUMN_NAME, Integer.valueOf(i));
                contentValues.put(WRITE_NODE_COLUMN_NAME, splitBytes.get(i));
                this.database.insertWithOnConflict(WRITES_TABLE, (String) null, contentValues, 5);
            }
            return;
        }
        ContentValues contentValues2 = new ContentValues();
        contentValues2.put("id", Long.valueOf(j));
        contentValues2.put("path", pathToKey(path));
        contentValues2.put(WRITE_TYPE_COLUMN_NAME, str2);
        contentValues2.put(WRITE_PART_COLUMN_NAME, (Integer) null);
        contentValues2.put(WRITE_NODE_COLUMN_NAME, bArr2);
        this.database.insertWithOnConflict(WRITES_TABLE, (String) null, contentValues2, 5);
    }

    public Node serverCache(Path path) {
        return loadNested(path);
    }

    public void overwriteServerCache(Path path, Node node) {
        verifyInsideTransaction();
        updateServerCache(path, node, false);
    }

    public void mergeIntoServerCache(Path path, Node node) {
        verifyInsideTransaction();
        updateServerCache(path, node, true);
    }

    private void updateServerCache(Path path, Node node, boolean z) {
        int i;
        int i2;
        long currentTimeMillis = System.currentTimeMillis();
        if (!z) {
            i = removeNested(SERVER_CACHE_TABLE, path);
            i2 = saveNested(path, node);
        } else {
            Iterator it = node.iterator();
            int i3 = 0;
            int i4 = 0;
            while (it.hasNext()) {
                NamedNode namedNode = (NamedNode) it.next();
                i4 += removeNested(SERVER_CACHE_TABLE, path.child(namedNode.getName()));
                i3 += saveNested(path.child(namedNode.getName()), namedNode.getNode());
            }
            i2 = i3;
            i = i4;
        }
        long currentTimeMillis2 = System.currentTimeMillis() - currentTimeMillis;
        if (this.logger.logsDebug()) {
            this.logger.debug(String.format(Locale.US, "Persisted a total of %d rows and deleted %d rows for a set at %s in %dms", new Object[]{Integer.valueOf(i2), Integer.valueOf(i), path.toString(), Long.valueOf(currentTimeMillis2)}), new Object[0]);
        }
    }

    public void mergeIntoServerCache(Path path, CompoundWrite compoundWrite) {
        verifyInsideTransaction();
        long currentTimeMillis = System.currentTimeMillis();
        Iterator<Map.Entry<Path, Node>> it = compoundWrite.iterator();
        int i = 0;
        int i2 = 0;
        while (it.hasNext()) {
            Map.Entry next = it.next();
            i += removeNested(SERVER_CACHE_TABLE, path.child((Path) next.getKey()));
            i2 += saveNested(path.child((Path) next.getKey()), (Node) next.getValue());
        }
        long currentTimeMillis2 = System.currentTimeMillis() - currentTimeMillis;
        if (this.logger.logsDebug()) {
            this.logger.debug(String.format(Locale.US, "Persisted a total of %d rows and deleted %d rows for a merge at %s in %dms", new Object[]{Integer.valueOf(i2), Integer.valueOf(i), path.toString(), Long.valueOf(currentTimeMillis2)}), new Object[0]);
        }
    }

    public long serverCacheEstimatedSizeInBytes() {
        Cursor rawQuery = this.database.rawQuery(String.format("SELECT sum(length(%s) + length(%s)) FROM %s", new Object[]{VALUE_COLUMN_NAME, "path", SERVER_CACHE_TABLE}), (String[]) null);
        try {
            if (rawQuery.moveToFirst()) {
                return rawQuery.getLong(0);
            }
            throw new IllegalStateException("Couldn't read database result!");
        } finally {
            rawQuery.close();
        }
    }

    public void saveTrackedQuery(TrackedQuery trackedQuery) {
        verifyInsideTransaction();
        long currentTimeMillis = System.currentTimeMillis();
        ContentValues contentValues = new ContentValues();
        contentValues.put("id", Long.valueOf(trackedQuery.f315id));
        contentValues.put("path", pathToKey(trackedQuery.querySpec.getPath()));
        contentValues.put(TRACKED_QUERY_PARAMS_COLUMN_NAME, trackedQuery.querySpec.getParams().toJSON());
        contentValues.put(TRACKED_QUERY_LAST_USE_COLUMN_NAME, Long.valueOf(trackedQuery.lastUse));
        contentValues.put(TRACKED_QUERY_COMPLETE_COLUMN_NAME, Boolean.valueOf(trackedQuery.complete));
        contentValues.put(TRACKED_QUERY_ACTIVE_COLUMN_NAME, Boolean.valueOf(trackedQuery.active));
        this.database.insertWithOnConflict(TRACKED_QUERY_TABLE, (String) null, contentValues, 5);
        long currentTimeMillis2 = System.currentTimeMillis() - currentTimeMillis;
        if (this.logger.logsDebug()) {
            this.logger.debug(String.format(Locale.US, "Saved new tracked query in %dms", new Object[]{Long.valueOf(currentTimeMillis2)}), new Object[0]);
        }
    }

    public void deleteTrackedQuery(long j) {
        verifyInsideTransaction();
        String valueOf = String.valueOf(j);
        this.database.delete(TRACKED_QUERY_TABLE, "id = ?", new String[]{valueOf});
        this.database.delete(TRACKED_KEYS_TABLE, "id = ?", new String[]{valueOf});
    }

    public List<TrackedQuery> loadTrackedQueries() {
        String[] strArr = {"id", "path", TRACKED_QUERY_PARAMS_COLUMN_NAME, TRACKED_QUERY_LAST_USE_COLUMN_NAME, TRACKED_QUERY_COMPLETE_COLUMN_NAME, TRACKED_QUERY_ACTIVE_COLUMN_NAME};
        long currentTimeMillis = System.currentTimeMillis();
        Cursor query = this.database.query(TRACKED_QUERY_TABLE, strArr, (String) null, (String[]) null, (String) null, (String) null, "id");
        ArrayList arrayList = new ArrayList();
        while (query.moveToNext()) {
            try {
                arrayList.add(new TrackedQuery(query.getLong(0), QuerySpec.fromPathAndQueryObject(new Path(query.getString(1)), JsonMapper.parseJson(query.getString(2))), query.getLong(3), query.getInt(4) != 0, query.getInt(5) != 0));
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (Throwable th) {
                query.close();
                throw th;
            }
        }
        long currentTimeMillis2 = System.currentTimeMillis() - currentTimeMillis;
        if (this.logger.logsDebug()) {
            this.logger.debug(String.format(Locale.US, "Loaded %d tracked queries in %dms", new Object[]{Integer.valueOf(arrayList.size()), Long.valueOf(currentTimeMillis2)}), new Object[0]);
        }
        query.close();
        return arrayList;
    }

    public void resetPreviouslyActiveTrackedQueries(long j) {
        verifyInsideTransaction();
        long currentTimeMillis = System.currentTimeMillis();
        ContentValues contentValues = new ContentValues();
        contentValues.put(TRACKED_QUERY_ACTIVE_COLUMN_NAME, false);
        contentValues.put(TRACKED_QUERY_LAST_USE_COLUMN_NAME, Long.valueOf(j));
        this.database.updateWithOnConflict(TRACKED_QUERY_TABLE, contentValues, "active = 1", new String[0], 5);
        long currentTimeMillis2 = System.currentTimeMillis() - currentTimeMillis;
        if (this.logger.logsDebug()) {
            this.logger.debug(String.format(Locale.US, "Reset active tracked queries in %dms", new Object[]{Long.valueOf(currentTimeMillis2)}), new Object[0]);
        }
    }

    public void saveTrackedQueryKeys(long j, Set<ChildKey> set) {
        verifyInsideTransaction();
        long currentTimeMillis = System.currentTimeMillis();
        String valueOf = String.valueOf(j);
        this.database.delete(TRACKED_KEYS_TABLE, "id = ?", new String[]{valueOf});
        for (ChildKey asString : set) {
            ContentValues contentValues = new ContentValues();
            contentValues.put("id", Long.valueOf(j));
            contentValues.put(TRACKED_KEYS_KEY_COLUMN_NAME, asString.asString());
            this.database.insertWithOnConflict(TRACKED_KEYS_TABLE, (String) null, contentValues, 5);
        }
        long currentTimeMillis2 = System.currentTimeMillis() - currentTimeMillis;
        if (this.logger.logsDebug()) {
            this.logger.debug(String.format(Locale.US, "Set %d tracked query keys for tracked query %d in %dms", new Object[]{Integer.valueOf(set.size()), Long.valueOf(j), Long.valueOf(currentTimeMillis2)}), new Object[0]);
        }
    }

    public void updateTrackedQueryKeys(long j, Set<ChildKey> set, Set<ChildKey> set2) {
        verifyInsideTransaction();
        long currentTimeMillis = System.currentTimeMillis();
        String valueOf = String.valueOf(j);
        for (ChildKey asString : set2) {
            this.database.delete(TRACKED_KEYS_TABLE, "id = ? AND key = ?", new String[]{valueOf, asString.asString()});
        }
        for (ChildKey asString2 : set) {
            ContentValues contentValues = new ContentValues();
            contentValues.put("id", Long.valueOf(j));
            contentValues.put(TRACKED_KEYS_KEY_COLUMN_NAME, asString2.asString());
            this.database.insertWithOnConflict(TRACKED_KEYS_TABLE, (String) null, contentValues, 5);
        }
        long currentTimeMillis2 = System.currentTimeMillis() - currentTimeMillis;
        if (this.logger.logsDebug()) {
            this.logger.debug(String.format(Locale.US, "Updated tracked query keys (%d added, %d removed) for tracked query id %d in %dms", new Object[]{Integer.valueOf(set.size()), Integer.valueOf(set2.size()), Long.valueOf(j), Long.valueOf(currentTimeMillis2)}), new Object[0]);
        }
    }

    public Set<ChildKey> loadTrackedQueryKeys(long j) {
        return loadTrackedQueryKeys((Set<Long>) Collections.singleton(Long.valueOf(j)));
    }

    public Set<ChildKey> loadTrackedQueryKeys(Set<Long> set) {
        String[] strArr = {TRACKED_KEYS_KEY_COLUMN_NAME};
        long currentTimeMillis = System.currentTimeMillis();
        Cursor query = this.database.query(true, TRACKED_KEYS_TABLE, strArr, "id IN (" + commaSeparatedList(set) + ")", (String[]) null, (String) null, (String) null, (String) null, (String) null);
        HashSet hashSet = new HashSet();
        while (query.moveToNext()) {
            try {
                hashSet.add(ChildKey.fromString(query.getString(0)));
            } finally {
                query.close();
            }
        }
        long currentTimeMillis2 = System.currentTimeMillis() - currentTimeMillis;
        if (this.logger.logsDebug()) {
            this.logger.debug(String.format(Locale.US, "Loaded %d tracked queries keys for tracked queries %s in %dms", new Object[]{Integer.valueOf(hashSet.size()), set.toString(), Long.valueOf(currentTimeMillis2)}), new Object[0]);
        }
        return hashSet;
    }

    public void pruneCache(Path path, PruneForest pruneForest) {
        int i;
        int i2;
        Path path2 = path;
        PruneForest pruneForest2 = pruneForest;
        if (pruneForest.prunesAnything()) {
            verifyInsideTransaction();
            long currentTimeMillis = System.currentTimeMillis();
            Cursor loadNestedQuery = loadNestedQuery(path2, new String[]{ROW_ID_COLUMN_NAME, "path"});
            ImmutableTree immutableTree = new ImmutableTree(null);
            ImmutableTree immutableTree2 = new ImmutableTree(null);
            while (loadNestedQuery.moveToNext()) {
                long j = loadNestedQuery.getLong(0);
                Path path3 = new Path(loadNestedQuery.getString(1));
                if (!path2.contains(path3)) {
                    LogWrapper logWrapper = this.logger;
                    logWrapper.warn("We are pruning at " + path2 + " but we have data stored higher up at " + path3 + ". Ignoring.");
                } else {
                    Path relative = Path.getRelative(path2, path3);
                    if (pruneForest2.shouldPruneUnkeptDescendants(relative)) {
                        immutableTree = immutableTree.set(relative, Long.valueOf(j));
                    } else if (pruneForest2.shouldKeep(relative)) {
                        immutableTree2 = immutableTree2.set(relative, Long.valueOf(j));
                    } else {
                        LogWrapper logWrapper2 = this.logger;
                        logWrapper2.warn("We are pruning at " + path2 + " and have data at " + path3 + " that isn't marked for pruning or keeping. Ignoring.");
                    }
                }
            }
            if (!immutableTree.isEmpty()) {
                ArrayList<Pair> arrayList = new ArrayList<>();
                pruneTreeRecursive(path, Path.getEmptyPath(), immutableTree, immutableTree2, pruneForest, arrayList);
                Collection values = immutableTree.values();
                this.database.delete(SERVER_CACHE_TABLE, "rowid IN (" + commaSeparatedList(values) + ")", (String[]) null);
                for (Pair pair : arrayList) {
                    saveNested(path2.child((Path) pair.getFirst()), (Node) pair.getSecond());
                }
                i2 = values.size();
                i = arrayList.size();
            } else {
                i2 = 0;
                i = 0;
            }
            long currentTimeMillis2 = System.currentTimeMillis() - currentTimeMillis;
            if (this.logger.logsDebug()) {
                this.logger.debug(String.format(Locale.US, "Pruned %d rows with %d nodes resaved in %dms", new Object[]{Integer.valueOf(i2), Integer.valueOf(i), Long.valueOf(currentTimeMillis2)}), new Object[0]);
            }
        }
    }

    private void pruneTreeRecursive(Path path, Path path2, ImmutableTree<Long> immutableTree, ImmutableTree<Long> immutableTree2, PruneForest pruneForest, List<Pair<Path, Node>> list) {
        final ImmutableTree<Long> immutableTree3 = immutableTree2;
        PruneForest pruneForest2 = pruneForest;
        if (immutableTree.getValue() != null) {
            int intValue = ((Integer) pruneForest2.foldKeptNodes(0, new ImmutableTree.TreeVisitor<Void, Integer>() {
                public Integer onNodeValue(Path path, Void voidR, Integer num) {
                    return Integer.valueOf(immutableTree3.get(path) == null ? num.intValue() + 1 : num.intValue());
                }
            })).intValue();
            if (intValue > 0) {
                Path child = path.child(path2);
                if (this.logger.logsDebug()) {
                    this.logger.debug(String.format(Locale.US, "Need to rewrite %d nodes below path %s", new Object[]{Integer.valueOf(intValue), child}), new Object[0]);
                }
                final Node loadNested = loadNested(child);
                final ImmutableTree<Long> immutableTree4 = immutableTree2;
                final List<Pair<Path, Node>> list2 = list;
                final Path path3 = path2;
                pruneForest2.foldKeptNodes(null, new ImmutableTree.TreeVisitor<Void, Void>() {
                    public Void onNodeValue(Path path, Void voidR, Void voidR2) {
                        if (immutableTree4.get(path) != null) {
                            return null;
                        }
                        list2.add(new Pair(path3.child(path), loadNested.getChild(path)));
                        return null;
                    }
                });
                return;
            }
            return;
        }
        Iterator<Map.Entry<ChildKey, ImmutableTree<Long>>> it = immutableTree.getChildren().iterator();
        while (it.hasNext()) {
            Map.Entry next = it.next();
            ChildKey childKey = (ChildKey) next.getKey();
            PruneForest child2 = pruneForest2.child((ChildKey) next.getKey());
            Path path4 = path2;
            ImmutableTree<Long> child3 = immutableTree3.getChild(childKey);
            pruneTreeRecursive(path, path2.child(childKey), (ImmutableTree) next.getValue(), child3, child2, list);
        }
    }

    public void removeAllUserWrites() {
        verifyInsideTransaction();
        long currentTimeMillis = System.currentTimeMillis();
        int delete = this.database.delete(WRITES_TABLE, (String) null, (String[]) null);
        long currentTimeMillis2 = System.currentTimeMillis() - currentTimeMillis;
        if (this.logger.logsDebug()) {
            this.logger.debug(String.format(Locale.US, "Deleted %d (all) write(s) in %dms", new Object[]{Integer.valueOf(delete), Long.valueOf(currentTimeMillis2)}), new Object[0]);
        }
    }

    public void purgeCache() {
        verifyInsideTransaction();
        this.database.delete(SERVER_CACHE_TABLE, (String) null, (String[]) null);
        this.database.delete(WRITES_TABLE, (String) null, (String[]) null);
        this.database.delete(TRACKED_QUERY_TABLE, (String) null, (String[]) null);
        this.database.delete(TRACKED_KEYS_TABLE, (String) null, (String[]) null);
    }

    public void beginTransaction() {
        Utilities.hardAssert(!this.insideTransaction, "runInTransaction called when an existing transaction is already in progress.");
        if (this.logger.logsDebug()) {
            this.logger.debug("Starting transaction.", new Object[0]);
        }
        this.database.beginTransaction();
        this.insideTransaction = true;
        this.transactionStart = System.currentTimeMillis();
    }

    public void endTransaction() {
        this.database.endTransaction();
        this.insideTransaction = false;
        long currentTimeMillis = System.currentTimeMillis() - this.transactionStart;
        if (this.logger.logsDebug()) {
            this.logger.debug(String.format(Locale.US, "Transaction completed. Elapsed: %dms", new Object[]{Long.valueOf(currentTimeMillis)}), new Object[0]);
        }
    }

    public void setTransactionSuccessful() {
        this.database.setTransactionSuccessful();
    }

    public void close() {
        this.database.close();
    }

    private SQLiteDatabase openDatabase(Context context, String str) {
        try {
            SQLiteDatabase writableDatabase = new PersistentCacheOpenHelper(context, str).getWritableDatabase();
            writableDatabase.rawQuery("PRAGMA locking_mode = EXCLUSIVE", (String[]) null).close();
            writableDatabase.beginTransaction();
            writableDatabase.endTransaction();
            return writableDatabase;
        } catch (SQLiteException e) {
            if (e instanceof SQLiteDatabaseLockedException) {
                throw new DatabaseException("Failed to gain exclusive lock to Firebase Database's offline persistence. This generally means you are using Firebase Database from multiple processes in your app. Keep in mind that multi-process Android apps execute the code in your Application class in all processes, so you may need to avoid initializing FirebaseDatabase in your Application class. If you are intentionally using Firebase Database from multiple processes, you can only enable offline persistence (i.e. call setPersistenceEnabled(true)) in one of them.", e);
            }
            throw e;
        }
    }

    private void verifyInsideTransaction() {
        Utilities.hardAssert(this.insideTransaction, "Transaction expected to already be in progress.");
    }

    private int saveNested(Path path, Node node) {
        long estimateSerializedNodeSize = NodeSizeEstimator.estimateSerializedNodeSize(node);
        if (!(node instanceof ChildrenNode) || estimateSerializedNodeSize <= PlaybackStateCompat.ACTION_PREPARE) {
            saveNode(path, node);
            return 1;
        }
        int i = 0;
        if (this.logger.logsDebug()) {
            this.logger.debug(String.format(Locale.US, "Node estimated serialized size at path %s of %d bytes exceeds limit of %d bytes. Splitting up.", new Object[]{path, Long.valueOf(estimateSerializedNodeSize), 16384}), new Object[0]);
        }
        Iterator it = node.iterator();
        while (it.hasNext()) {
            NamedNode namedNode = (NamedNode) it.next();
            i += saveNested(path.child(namedNode.getName()), namedNode.getNode());
        }
        if (!node.getPriority().isEmpty()) {
            saveNode(path.child(ChildKey.getPriorityKey()), node.getPriority());
            i++;
        }
        saveNode(path, EmptyNode.Empty());
        return i + 1;
    }

    private String partKey(Path path, int i) {
        return pathToKey(path) + String.format(Locale.US, PART_KEY_FORMAT, new Object[]{Integer.valueOf(i)});
    }

    private void saveNode(Path path, Node node) {
        byte[] serializeObject = serializeObject(node.getValue(true));
        if (serializeObject.length >= 262144) {
            List<byte[]> splitBytes = splitBytes(serializeObject, 262144);
            if (this.logger.logsDebug()) {
                LogWrapper logWrapper = this.logger;
                logWrapper.debug("Saving huge leaf node with " + splitBytes.size() + " parts.", new Object[0]);
            }
            for (int i = 0; i < splitBytes.size(); i++) {
                ContentValues contentValues = new ContentValues();
                contentValues.put("path", partKey(path, i));
                contentValues.put(VALUE_COLUMN_NAME, splitBytes.get(i));
                this.database.insertWithOnConflict(SERVER_CACHE_TABLE, (String) null, contentValues, 5);
            }
            return;
        }
        ContentValues contentValues2 = new ContentValues();
        contentValues2.put("path", pathToKey(path));
        contentValues2.put(VALUE_COLUMN_NAME, serializeObject);
        this.database.insertWithOnConflict(SERVER_CACHE_TABLE, (String) null, contentValues2, 5);
    }

    /* JADX INFO: finally extract failed */
    private Node loadNested(Path path) {
        long j;
        Path path2;
        Node node;
        int i;
        Path path3;
        Path path4 = path;
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        long currentTimeMillis = System.currentTimeMillis();
        Cursor loadNestedQuery = loadNestedQuery(path4, new String[]{"path", VALUE_COLUMN_NAME});
        long currentTimeMillis2 = System.currentTimeMillis() - currentTimeMillis;
        long currentTimeMillis3 = System.currentTimeMillis();
        while (loadNestedQuery.moveToNext()) {
            try {
                arrayList.add(loadNestedQuery.getString(0));
                arrayList2.add(loadNestedQuery.getBlob(1));
            } catch (Throwable th) {
                loadNestedQuery.close();
                throw th;
            }
        }
        loadNestedQuery.close();
        long currentTimeMillis4 = System.currentTimeMillis() - currentTimeMillis3;
        long currentTimeMillis5 = System.currentTimeMillis();
        Node Empty = EmptyNode.Empty();
        HashMap hashMap = new HashMap();
        int i2 = 0;
        boolean z = false;
        while (true) {
            long j2 = currentTimeMillis4;
            if (i2 < arrayList2.size()) {
                if (((String) arrayList.get(i2)).endsWith(FIRST_PART_KEY)) {
                    String str = (String) arrayList.get(i2);
                    j = currentTimeMillis2;
                    Path path5 = new Path(str.substring(0, str.length() - 10));
                    int splitNodeRunLength = splitNodeRunLength(path5, arrayList, i2);
                    if (this.logger.logsDebug()) {
                        LogWrapper logWrapper = this.logger;
                        path3 = path5;
                        logWrapper.debug("Loading split node with " + splitNodeRunLength + " parts.", new Object[0]);
                    } else {
                        path3 = path5;
                    }
                    int i3 = splitNodeRunLength + i2;
                    node = deserializeNode(joinBytes(arrayList2.subList(i2, i3)));
                    i2 = i3 - 1;
                    path2 = path3;
                } else {
                    j = currentTimeMillis2;
                    node = deserializeNode((byte[]) arrayList2.get(i2));
                    path2 = new Path((String) arrayList.get(i2));
                }
                if (path2.getBack() != null && path2.getBack().isPriorityChildName()) {
                    hashMap.put(path2, node);
                } else if (path2.contains(path4)) {
                    Utilities.hardAssert(!z, "Descendants of path must come after ancestors.");
                    Empty = node.getChild(Path.getRelative(path2, path4));
                } else if (path4.contains(path2)) {
                    Empty = Empty.updateChild(Path.getRelative(path4, path2), node);
                    i = 1;
                    z = true;
                    i2 += i;
                    currentTimeMillis4 = j2;
                    currentTimeMillis2 = j;
                } else {
                    throw new IllegalStateException(String.format("Loading an unrelated row with path %s for %s", new Object[]{path2, path4}));
                }
                i = 1;
                i2 += i;
                currentTimeMillis4 = j2;
                currentTimeMillis2 = j;
            } else {
                long j3 = currentTimeMillis2;
                for (Map.Entry entry : hashMap.entrySet()) {
                    Empty = Empty.updateChild(Path.getRelative(path4, (Path) entry.getKey()), (Node) entry.getValue());
                }
                long currentTimeMillis6 = System.currentTimeMillis() - currentTimeMillis5;
                long currentTimeMillis7 = System.currentTimeMillis() - currentTimeMillis;
                if (this.logger.logsDebug()) {
                    this.logger.debug(String.format(Locale.US, "Loaded a total of %d rows for a total of %d nodes at %s in %dms (Query: %dms, Loading: %dms, Serializing: %dms)", new Object[]{Integer.valueOf(arrayList2.size()), Integer.valueOf(NodeSizeEstimator.nodeCount(Empty)), path4, Long.valueOf(currentTimeMillis7), Long.valueOf(j3), Long.valueOf(j2), Long.valueOf(currentTimeMillis6)}), new Object[0]);
                }
                return Empty;
            }
        }
    }

    private int splitNodeRunLength(Path path, List<String> list, int i) {
        int i2 = i + 1;
        String pathToKey = pathToKey(path);
        if (list.get(i).startsWith(pathToKey)) {
            while (i2 < list.size() && list.get(i2).equals(partKey(path, i2 - i))) {
                i2++;
            }
            if (i2 < list.size()) {
                if (list.get(i2).startsWith(pathToKey + PART_KEY_PREFIX)) {
                    throw new IllegalStateException("Run did not finish with all parts");
                }
            }
            return i2 - i;
        }
        throw new IllegalStateException("Extracting split nodes needs to start with path prefix");
    }

    private Cursor loadNestedQuery(Path path, String[] strArr) {
        String pathToKey = pathToKey(path);
        String pathPrefixStartToPrefixEnd = pathPrefixStartToPrefixEnd(pathToKey);
        String[] strArr2 = new String[(path.size() + 3)];
        String str = buildAncestorWhereClause(path, strArr2) + " OR (path > ? AND path < ?)";
        strArr2[path.size() + 1] = pathToKey;
        strArr2[path.size() + 2] = pathPrefixStartToPrefixEnd;
        return this.database.query(SERVER_CACHE_TABLE, strArr, str, strArr2, (String) null, (String) null, "path");
    }

    private static String pathToKey(Path path) {
        if (path.isEmpty()) {
            return "/";
        }
        return path.toString() + "/";
    }

    private static String pathPrefixStartToPrefixEnd(String str) {
        Utilities.hardAssert(str.endsWith("/"), "Path keys must end with a '/'");
        return str.substring(0, str.length() - 1) + '0';
    }

    private static String buildAncestorWhereClause(Path path, String[] strArr) {
        boolean z = true;
        int i = 0;
        if (strArr.length < path.size() + 1) {
            z = false;
        }
        Utilities.hardAssert(z);
        StringBuilder sb = new StringBuilder("(");
        while (!path.isEmpty()) {
            sb.append("path");
            sb.append(" = ? OR ");
            strArr[i] = pathToKey(path);
            path = path.getParent();
            i++;
        }
        sb.append("path");
        sb.append(" = ?)");
        strArr[i] = pathToKey(Path.getEmptyPath());
        return sb.toString();
    }

    private int removeNested(String str, Path path) {
        String pathToKey = pathToKey(path);
        String pathPrefixStartToPrefixEnd = pathPrefixStartToPrefixEnd(pathToKey);
        return this.database.delete(str, "path >= ? AND path < ?", new String[]{pathToKey, pathPrefixStartToPrefixEnd});
    }

    private static List<byte[]> splitBytes(byte[] bArr, int i) {
        int length = ((bArr.length - 1) / i) + 1;
        ArrayList arrayList = new ArrayList(length);
        for (int i2 = 0; i2 < length; i2++) {
            int i3 = i2 * i;
            int min = Math.min(i, bArr.length - i3);
            byte[] bArr2 = new byte[min];
            System.arraycopy(bArr, i3, bArr2, 0, min);
            arrayList.add(bArr2);
        }
        return arrayList;
    }

    private byte[] joinBytes(List<byte[]> list) {
        int i = 0;
        for (byte[] length : list) {
            i += length.length;
        }
        byte[] bArr = new byte[i];
        int i2 = 0;
        for (byte[] next : list) {
            System.arraycopy(next, 0, bArr, i2, next.length);
            i2 += next.length;
        }
        return bArr;
    }

    private byte[] serializeObject(Object obj) {
        try {
            return JsonMapper.serializeJsonValue(obj).getBytes(UTF8_CHARSET);
        } catch (IOException e) {
            throw new RuntimeException("Could not serialize leaf node", e);
        }
    }

    private Node deserializeNode(byte[] bArr) {
        try {
            return NodeUtilities.NodeFromJSON(JsonMapper.parseJsonValue(new String(bArr, UTF8_CHARSET)));
        } catch (IOException e) {
            String str = new String(bArr, UTF8_CHARSET);
            throw new RuntimeException("Could not deserialize node: " + str, e);
        }
    }

    private String commaSeparatedList(Collection<Long> collection) {
        StringBuilder sb = new StringBuilder();
        boolean z = true;
        for (Long longValue : collection) {
            long longValue2 = longValue.longValue();
            if (!z) {
                sb.append(",");
            }
            z = false;
            sb.append(longValue2);
        }
        return sb.toString();
    }
}

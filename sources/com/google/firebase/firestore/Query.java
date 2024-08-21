package com.google.firebase.firestore;

import android.app.Activity;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.core.ActivityScope;
import com.google.firebase.firestore.core.AsyncEventListener;
import com.google.firebase.firestore.core.Bound;
import com.google.firebase.firestore.core.EventManager;
import com.google.firebase.firestore.core.FieldFilter;
import com.google.firebase.firestore.core.Filter;
import com.google.firebase.firestore.core.ListenerRegistrationImpl;
import com.google.firebase.firestore.core.OrderBy;
import com.google.firebase.firestore.core.ViewSnapshot;
import com.google.firebase.firestore.model.Document;
import com.google.firebase.firestore.model.DocumentKey;
import com.google.firebase.firestore.model.FieldPath;
import com.google.firebase.firestore.model.ResourcePath;
import com.google.firebase.firestore.model.ServerTimestamps;
import com.google.firebase.firestore.model.Values;
import com.google.firebase.firestore.util.Assert;
import com.google.firebase.firestore.util.Executors;
import com.google.firebase.firestore.util.Preconditions;
import com.google.firebase.firestore.util.Util;
import com.google.firestore.p009v1.ArrayValue;
import com.google.firestore.p009v1.Value;
import com.iceteck.silicompressorr.FileUtils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;

public class Query {
    final FirebaseFirestore firestore;
    final com.google.firebase.firestore.core.Query query;

    public enum Direction {
        ASCENDING,
        DESCENDING
    }

    Query(com.google.firebase.firestore.core.Query query2, FirebaseFirestore firebaseFirestore) {
        this.query = (com.google.firebase.firestore.core.Query) Preconditions.checkNotNull(query2);
        this.firestore = (FirebaseFirestore) Preconditions.checkNotNull(firebaseFirestore);
    }

    public FirebaseFirestore getFirestore() {
        return this.firestore;
    }

    public Query whereEqualTo(String str, Object obj) {
        return whereHelper(FieldPath.fromDotSeparatedPath(str), Filter.Operator.EQUAL, obj);
    }

    public Query whereEqualTo(FieldPath fieldPath, Object obj) {
        return whereHelper(fieldPath, Filter.Operator.EQUAL, obj);
    }

    public Query whereNotEqualTo(String str, Object obj) {
        return whereHelper(FieldPath.fromDotSeparatedPath(str), Filter.Operator.NOT_EQUAL, obj);
    }

    public Query whereNotEqualTo(FieldPath fieldPath, Object obj) {
        return whereHelper(fieldPath, Filter.Operator.NOT_EQUAL, obj);
    }

    public Query whereLessThan(String str, Object obj) {
        return whereHelper(FieldPath.fromDotSeparatedPath(str), Filter.Operator.LESS_THAN, obj);
    }

    public Query whereLessThan(FieldPath fieldPath, Object obj) {
        return whereHelper(fieldPath, Filter.Operator.LESS_THAN, obj);
    }

    public Query whereLessThanOrEqualTo(String str, Object obj) {
        return whereHelper(FieldPath.fromDotSeparatedPath(str), Filter.Operator.LESS_THAN_OR_EQUAL, obj);
    }

    public Query whereLessThanOrEqualTo(FieldPath fieldPath, Object obj) {
        return whereHelper(fieldPath, Filter.Operator.LESS_THAN_OR_EQUAL, obj);
    }

    public Query whereGreaterThan(String str, Object obj) {
        return whereHelper(FieldPath.fromDotSeparatedPath(str), Filter.Operator.GREATER_THAN, obj);
    }

    public Query whereGreaterThan(FieldPath fieldPath, Object obj) {
        return whereHelper(fieldPath, Filter.Operator.GREATER_THAN, obj);
    }

    public Query whereGreaterThanOrEqualTo(String str, Object obj) {
        return whereHelper(FieldPath.fromDotSeparatedPath(str), Filter.Operator.GREATER_THAN_OR_EQUAL, obj);
    }

    public Query whereGreaterThanOrEqualTo(FieldPath fieldPath, Object obj) {
        return whereHelper(fieldPath, Filter.Operator.GREATER_THAN_OR_EQUAL, obj);
    }

    public Query whereArrayContains(String str, Object obj) {
        return whereHelper(FieldPath.fromDotSeparatedPath(str), Filter.Operator.ARRAY_CONTAINS, obj);
    }

    public Query whereArrayContains(FieldPath fieldPath, Object obj) {
        return whereHelper(fieldPath, Filter.Operator.ARRAY_CONTAINS, obj);
    }

    public Query whereArrayContainsAny(String str, List<? extends Object> list) {
        return whereHelper(FieldPath.fromDotSeparatedPath(str), Filter.Operator.ARRAY_CONTAINS_ANY, list);
    }

    public Query whereArrayContainsAny(FieldPath fieldPath, List<? extends Object> list) {
        return whereHelper(fieldPath, Filter.Operator.ARRAY_CONTAINS_ANY, list);
    }

    public Query whereIn(String str, List<? extends Object> list) {
        return whereHelper(FieldPath.fromDotSeparatedPath(str), Filter.Operator.IN, list);
    }

    public Query whereIn(FieldPath fieldPath, List<? extends Object> list) {
        return whereHelper(fieldPath, Filter.Operator.IN, list);
    }

    public Query whereNotIn(String str, List<? extends Object> list) {
        return whereHelper(FieldPath.fromDotSeparatedPath(str), Filter.Operator.NOT_IN, list);
    }

    public Query whereNotIn(FieldPath fieldPath, List<? extends Object> list) {
        return whereHelper(fieldPath, Filter.Operator.NOT_IN, list);
    }

    private Query whereHelper(FieldPath fieldPath, Filter.Operator operator, Object obj) {
        Value value;
        Preconditions.checkNotNull(fieldPath, "Provided field path must not be null.");
        Preconditions.checkNotNull(operator, "Provided op must not be null.");
        if (!fieldPath.getInternalPath().isKeyField()) {
            if (operator == Filter.Operator.IN || operator == Filter.Operator.NOT_IN || operator == Filter.Operator.ARRAY_CONTAINS_ANY) {
                validateDisjunctiveFilterElements(obj, operator);
            }
            value = this.firestore.getUserDataReader().parseQueryValue(obj, operator == Filter.Operator.IN || operator == Filter.Operator.NOT_IN);
        } else if (operator == Filter.Operator.ARRAY_CONTAINS || operator == Filter.Operator.ARRAY_CONTAINS_ANY) {
            throw new IllegalArgumentException("Invalid query. You can't perform '" + operator.toString() + "' queries on FieldPath.documentId().");
        } else if (operator == Filter.Operator.IN || operator == Filter.Operator.NOT_IN) {
            validateDisjunctiveFilterElements(obj, operator);
            ArrayValue.Builder newBuilder = ArrayValue.newBuilder();
            for (Object parseDocumentIdValue : (List) obj) {
                newBuilder.addValues(parseDocumentIdValue(parseDocumentIdValue));
            }
            value = (Value) Value.newBuilder().setArrayValue(newBuilder).build();
        } else {
            value = parseDocumentIdValue(obj);
        }
        FieldFilter create = FieldFilter.create(fieldPath.getInternalPath(), operator, value);
        validateNewFilter(create);
        return new Query(this.query.filter(create), this.firestore);
    }

    private void validateOrderByField(FieldPath fieldPath) {
        FieldPath inequalityField = this.query.inequalityField();
        if (this.query.getFirstOrderByField() == null && inequalityField != null) {
            validateOrderByFieldMatchesInequality(fieldPath, inequalityField);
        }
    }

    private Value parseDocumentIdValue(Object obj) {
        if (obj instanceof String) {
            String str = (String) obj;
            if (str.isEmpty()) {
                throw new IllegalArgumentException("Invalid query. When querying with FieldPath.documentId() you must provide a valid document ID, but it was an empty string.");
            } else if (this.query.isCollectionGroupQuery() || !str.contains("/")) {
                ResourcePath resourcePath = (ResourcePath) this.query.getPath().append(ResourcePath.fromString(str));
                if (DocumentKey.isDocumentKey(resourcePath)) {
                    return Values.refValue(getFirestore().getDatabaseId(), DocumentKey.fromPath(resourcePath));
                }
                throw new IllegalArgumentException("Invalid query. When querying a collection group by FieldPath.documentId(), the value provided must result in a valid document path, but '" + resourcePath + "' is not because it has an odd number of segments (" + resourcePath.length() + ").");
            } else {
                throw new IllegalArgumentException("Invalid query. When querying a collection by FieldPath.documentId() you must provide a plain document ID, but '" + str + "' contains a '/' character.");
            }
        } else if (obj instanceof DocumentReference) {
            return Values.refValue(getFirestore().getDatabaseId(), ((DocumentReference) obj).getKey());
        } else {
            throw new IllegalArgumentException("Invalid query. When querying with FieldPath.documentId() you must provide a valid String or DocumentReference, but it was of type: " + Util.typeName(obj));
        }
    }

    private void validateDisjunctiveFilterElements(Object obj, Filter.Operator operator) {
        if (obj instanceof List) {
            List list = (List) obj;
            if (list.size() != 0) {
                if (list.size() > 10) {
                    throw new IllegalArgumentException("Invalid Query. '" + operator.toString() + "' filters support a maximum of 10 elements in the value array.");
                }
                return;
            }
        }
        throw new IllegalArgumentException("Invalid Query. A non-empty array is required for '" + operator.toString() + "' filters.");
    }

    private void validateOrderByFieldMatchesInequality(FieldPath fieldPath, FieldPath fieldPath2) {
        if (!fieldPath.equals(fieldPath2)) {
            String canonicalString = fieldPath2.canonicalString();
            throw new IllegalArgumentException(String.format("Invalid query. You have an inequality where filter (whereLessThan(), whereGreaterThan(), etc.) on field '%s' and so you must also have '%s' as your first orderBy() field, but your first orderBy() is currently on field '%s' instead.", new Object[]{canonicalString, canonicalString, fieldPath.canonicalString()}));
        }
    }

    /* renamed from: com.google.firebase.firestore.Query$1 */
    static /* synthetic */ class C18761 {
        static final /* synthetic */ int[] $SwitchMap$com$google$firebase$firestore$core$Filter$Operator;

        /* JADX WARNING: Can't wrap try/catch for region: R(12:0|1|2|3|4|5|6|7|8|9|10|12) */
        /* JADX WARNING: Code restructure failed: missing block: B:13:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0033 */
        static {
            /*
                com.google.firebase.firestore.core.Filter$Operator[] r0 = com.google.firebase.firestore.core.Filter.Operator.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$google$firebase$firestore$core$Filter$Operator = r0
                com.google.firebase.firestore.core.Filter$Operator r1 = com.google.firebase.firestore.core.Filter.Operator.NOT_EQUAL     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$com$google$firebase$firestore$core$Filter$Operator     // Catch:{ NoSuchFieldError -> 0x001d }
                com.google.firebase.firestore.core.Filter$Operator r1 = com.google.firebase.firestore.core.Filter.Operator.ARRAY_CONTAINS     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$com$google$firebase$firestore$core$Filter$Operator     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.google.firebase.firestore.core.Filter$Operator r1 = com.google.firebase.firestore.core.Filter.Operator.IN     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = $SwitchMap$com$google$firebase$firestore$core$Filter$Operator     // Catch:{ NoSuchFieldError -> 0x0033 }
                com.google.firebase.firestore.core.Filter$Operator r1 = com.google.firebase.firestore.core.Filter.Operator.ARRAY_CONTAINS_ANY     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                int[] r0 = $SwitchMap$com$google$firebase$firestore$core$Filter$Operator     // Catch:{ NoSuchFieldError -> 0x003e }
                com.google.firebase.firestore.core.Filter$Operator r1 = com.google.firebase.firestore.core.Filter.Operator.NOT_IN     // Catch:{ NoSuchFieldError -> 0x003e }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x003e }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x003e }
            L_0x003e:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.firestore.Query.C18761.<clinit>():void");
        }
    }

    private List<Filter.Operator> conflictingOps(Filter.Operator operator) {
        int i = C18761.$SwitchMap$com$google$firebase$firestore$core$Filter$Operator[operator.ordinal()];
        if (i == 1) {
            return Arrays.asList(new Filter.Operator[]{Filter.Operator.NOT_EQUAL, Filter.Operator.NOT_IN});
        } else if (i == 2) {
            return Arrays.asList(new Filter.Operator[]{Filter.Operator.ARRAY_CONTAINS, Filter.Operator.ARRAY_CONTAINS_ANY, Filter.Operator.NOT_IN});
        } else if (i == 3) {
            return Arrays.asList(new Filter.Operator[]{Filter.Operator.ARRAY_CONTAINS_ANY, Filter.Operator.IN, Filter.Operator.NOT_IN});
        } else if (i == 4) {
            return Arrays.asList(new Filter.Operator[]{Filter.Operator.ARRAY_CONTAINS, Filter.Operator.ARRAY_CONTAINS_ANY, Filter.Operator.IN, Filter.Operator.NOT_IN});
        } else if (i != 5) {
            return new ArrayList();
        } else {
            return Arrays.asList(new Filter.Operator[]{Filter.Operator.ARRAY_CONTAINS, Filter.Operator.ARRAY_CONTAINS_ANY, Filter.Operator.IN, Filter.Operator.NOT_IN, Filter.Operator.NOT_EQUAL});
        }
    }

    private void validateNewFilter(Filter filter) {
        if (filter instanceof FieldFilter) {
            FieldFilter fieldFilter = (FieldFilter) filter;
            Filter.Operator operator = fieldFilter.getOperator();
            if (fieldFilter.isInequality()) {
                FieldPath inequalityField = this.query.inequalityField();
                FieldPath field = filter.getField();
                if (inequalityField == null || inequalityField.equals(field)) {
                    FieldPath firstOrderByField = this.query.getFirstOrderByField();
                    if (firstOrderByField != null) {
                        validateOrderByFieldMatchesInequality(firstOrderByField, field);
                    }
                } else {
                    throw new IllegalArgumentException(String.format("All where filters with an inequality (notEqualTo, notIn, lessThan, lessThanOrEqualTo, greaterThan, or greaterThanOrEqualTo) must be on the same field. But you have filters on '%s' and '%s'", new Object[]{inequalityField.canonicalString(), field.canonicalString()}));
                }
            }
            Filter.Operator findFilterOperator = this.query.findFilterOperator(conflictingOps(operator));
            if (findFilterOperator == null) {
                return;
            }
            if (findFilterOperator == operator) {
                throw new IllegalArgumentException("Invalid Query. You cannot use more than one '" + operator.toString() + "' filter.");
            }
            throw new IllegalArgumentException("Invalid Query. You cannot use '" + operator.toString() + "' filters with '" + findFilterOperator.toString() + "' filters.");
        }
    }

    public Query orderBy(String str) {
        return orderBy(FieldPath.fromDotSeparatedPath(str), Direction.ASCENDING);
    }

    public Query orderBy(FieldPath fieldPath) {
        Preconditions.checkNotNull(fieldPath, "Provided field path must not be null.");
        return orderBy(fieldPath.getInternalPath(), Direction.ASCENDING);
    }

    public Query orderBy(String str, Direction direction) {
        return orderBy(FieldPath.fromDotSeparatedPath(str), direction);
    }

    public Query orderBy(FieldPath fieldPath, Direction direction) {
        Preconditions.checkNotNull(fieldPath, "Provided field path must not be null.");
        return orderBy(fieldPath.getInternalPath(), direction);
    }

    private Query orderBy(FieldPath fieldPath, Direction direction) {
        OrderBy.Direction direction2;
        Preconditions.checkNotNull(direction, "Provided direction must not be null.");
        if (this.query.getStartAt() != null) {
            throw new IllegalArgumentException("Invalid query. You must not call Query.startAt() or Query.startAfter() before calling Query.orderBy().");
        } else if (this.query.getEndAt() == null) {
            validateOrderByField(fieldPath);
            if (direction == Direction.ASCENDING) {
                direction2 = OrderBy.Direction.ASCENDING;
            } else {
                direction2 = OrderBy.Direction.DESCENDING;
            }
            return new Query(this.query.orderBy(OrderBy.getInstance(direction2, fieldPath)), this.firestore);
        } else {
            throw new IllegalArgumentException("Invalid query. You must not call Query.endAt() or Query.endBefore() before calling Query.orderBy().");
        }
    }

    public Query limit(long j) {
        if (j > 0) {
            return new Query(this.query.limitToFirst(j), this.firestore);
        }
        throw new IllegalArgumentException("Invalid Query. Query limit (" + j + ") is invalid. Limit must be positive.");
    }

    public Query limitToLast(long j) {
        if (j > 0) {
            return new Query(this.query.limitToLast(j), this.firestore);
        }
        throw new IllegalArgumentException("Invalid Query. Query limitToLast (" + j + ") is invalid. Limit must be positive.");
    }

    public Query startAt(DocumentSnapshot documentSnapshot) {
        return new Query(this.query.startAt(boundFromDocumentSnapshot("startAt", documentSnapshot, true)), this.firestore);
    }

    public Query startAt(Object... objArr) {
        return new Query(this.query.startAt(boundFromFields("startAt", objArr, true)), this.firestore);
    }

    public Query startAfter(DocumentSnapshot documentSnapshot) {
        return new Query(this.query.startAt(boundFromDocumentSnapshot("startAfter", documentSnapshot, false)), this.firestore);
    }

    public Query startAfter(Object... objArr) {
        return new Query(this.query.startAt(boundFromFields("startAfter", objArr, false)), this.firestore);
    }

    public Query endBefore(DocumentSnapshot documentSnapshot) {
        return new Query(this.query.endAt(boundFromDocumentSnapshot("endBefore", documentSnapshot, true)), this.firestore);
    }

    public Query endBefore(Object... objArr) {
        return new Query(this.query.endAt(boundFromFields("endBefore", objArr, true)), this.firestore);
    }

    public Query endAt(DocumentSnapshot documentSnapshot) {
        return new Query(this.query.endAt(boundFromDocumentSnapshot("endAt", documentSnapshot, false)), this.firestore);
    }

    public Query endAt(Object... objArr) {
        return new Query(this.query.endAt(boundFromFields("endAt", objArr, false)), this.firestore);
    }

    private Bound boundFromDocumentSnapshot(String str, DocumentSnapshot documentSnapshot, boolean z) {
        Preconditions.checkNotNull(documentSnapshot, "Provided snapshot must not be null.");
        if (documentSnapshot.exists()) {
            Document document = documentSnapshot.getDocument();
            ArrayList arrayList = new ArrayList();
            for (OrderBy next : this.query.getOrderBy()) {
                if (next.getField().equals(FieldPath.KEY_PATH)) {
                    arrayList.add(Values.refValue(this.firestore.getDatabaseId(), document.getKey()));
                } else {
                    Value field = document.getField(next.getField());
                    if (ServerTimestamps.isServerTimestamp(field)) {
                        throw new IllegalArgumentException("Invalid query. You are trying to start or end a query using a document for which the field '" + next.getField() + "' is an uncommitted server timestamp. (Since the value of this field is unknown, you cannot start/end a query with it.)");
                    } else if (field != null) {
                        arrayList.add(field);
                    } else {
                        throw new IllegalArgumentException("Invalid query. You are trying to start or end a query using a document for which the field '" + next.getField() + "' (used as the orderBy) does not exist.");
                    }
                }
            }
            return new Bound(arrayList, z);
        }
        throw new IllegalArgumentException("Can't use a DocumentSnapshot for a document that doesn't exist for " + str + "().");
    }

    private Bound boundFromFields(String str, Object[] objArr, boolean z) {
        List<OrderBy> explicitOrderBy = this.query.getExplicitOrderBy();
        if (objArr.length <= explicitOrderBy.size()) {
            ArrayList arrayList = new ArrayList();
            for (int i = 0; i < objArr.length; i++) {
                String str2 = objArr[i];
                if (!explicitOrderBy.get(i).getField().equals(FieldPath.KEY_PATH)) {
                    arrayList.add(this.firestore.getUserDataReader().parseQueryValue(str2));
                } else if (str2 instanceof String) {
                    String str3 = str2;
                    if (this.query.isCollectionGroupQuery() || !str3.contains("/")) {
                        ResourcePath resourcePath = (ResourcePath) this.query.getPath().append(ResourcePath.fromString(str3));
                        if (DocumentKey.isDocumentKey(resourcePath)) {
                            arrayList.add(Values.refValue(this.firestore.getDatabaseId(), DocumentKey.fromPath(resourcePath)));
                        } else {
                            throw new IllegalArgumentException("Invalid query. When querying a collection group and ordering by FieldPath.documentId(), the value passed to " + str + "() must result in a valid document path, but '" + resourcePath + "' is not because it contains an odd number of segments.");
                        }
                    } else {
                        throw new IllegalArgumentException("Invalid query. When querying a collection and ordering by FieldPath.documentId(), the value passed to " + str + "() must be a plain document ID, but '" + str3 + "' contains a slash.");
                    }
                } else {
                    throw new IllegalArgumentException("Invalid query. Expected a string for document ID in " + str + "(), but got " + str2 + FileUtils.HIDDEN_PREFIX);
                }
            }
            return new Bound(arrayList, z);
        }
        throw new IllegalArgumentException("Too many arguments provided to " + str + "(). The number of arguments must be less than or equal to the number of orderBy() clauses.");
    }

    public Task<QuerySnapshot> get() {
        return get(Source.DEFAULT);
    }

    public Task<QuerySnapshot> get(Source source) {
        validateHasExplicitOrderByForLimitToLast();
        if (source == Source.CACHE) {
            return this.firestore.getClient().getDocumentsFromLocalCache(this.query).continueWith(Executors.DIRECT_EXECUTOR, Query$$Lambda$1.lambdaFactory$(this));
        }
        return getViaSnapshotListener(source);
    }

    static /* synthetic */ QuerySnapshot lambda$get$0(Query query2, Task task) throws Exception {
        return new QuerySnapshot(new Query(query2.query, query2.firestore), (ViewSnapshot) task.getResult(), query2.firestore);
    }

    private Task<QuerySnapshot> getViaSnapshotListener(Source source) {
        TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        TaskCompletionSource taskCompletionSource2 = new TaskCompletionSource();
        EventManager.ListenOptions listenOptions = new EventManager.ListenOptions();
        listenOptions.includeDocumentMetadataChanges = true;
        listenOptions.includeQueryMetadataChanges = true;
        listenOptions.waitForSyncWhenOnline = true;
        taskCompletionSource2.setResult(addSnapshotListenerInternal(Executors.DIRECT_EXECUTOR, listenOptions, (Activity) null, Query$$Lambda$2.lambdaFactory$(taskCompletionSource, taskCompletionSource2, source)));
        return taskCompletionSource.getTask();
    }

    static /* synthetic */ void lambda$getViaSnapshotListener$1(TaskCompletionSource taskCompletionSource, TaskCompletionSource taskCompletionSource2, Source source, QuerySnapshot querySnapshot, FirebaseFirestoreException firebaseFirestoreException) {
        if (firebaseFirestoreException != null) {
            taskCompletionSource.setException(firebaseFirestoreException);
            return;
        }
        try {
            ((ListenerRegistration) Tasks.await(taskCompletionSource2.getTask())).remove();
            if (!querySnapshot.getMetadata().isFromCache() || source != Source.SERVER) {
                taskCompletionSource.setResult(querySnapshot);
            } else {
                taskCompletionSource.setException(new FirebaseFirestoreException("Failed to get documents from server. (However, these documents may exist in the local cache. Run again without setting source to SERVER to retrieve the cached documents.)", FirebaseFirestoreException.Code.UNAVAILABLE));
            }
        } catch (ExecutionException e) {
            throw Assert.fail(e, "Failed to register a listener for a query result", new Object[0]);
        } catch (InterruptedException e2) {
            Thread.currentThread().interrupt();
            throw Assert.fail(e2, "Failed to register a listener for a query result", new Object[0]);
        }
    }

    public ListenerRegistration addSnapshotListener(EventListener<QuerySnapshot> eventListener) {
        return addSnapshotListener(MetadataChanges.EXCLUDE, eventListener);
    }

    public ListenerRegistration addSnapshotListener(Executor executor, EventListener<QuerySnapshot> eventListener) {
        return addSnapshotListener(executor, MetadataChanges.EXCLUDE, eventListener);
    }

    public ListenerRegistration addSnapshotListener(Activity activity, EventListener<QuerySnapshot> eventListener) {
        return addSnapshotListener(activity, MetadataChanges.EXCLUDE, eventListener);
    }

    public ListenerRegistration addSnapshotListener(MetadataChanges metadataChanges, EventListener<QuerySnapshot> eventListener) {
        return addSnapshotListener(Executors.DEFAULT_CALLBACK_EXECUTOR, metadataChanges, eventListener);
    }

    public ListenerRegistration addSnapshotListener(Executor executor, MetadataChanges metadataChanges, EventListener<QuerySnapshot> eventListener) {
        Preconditions.checkNotNull(executor, "Provided executor must not be null.");
        Preconditions.checkNotNull(metadataChanges, "Provided MetadataChanges value must not be null.");
        Preconditions.checkNotNull(eventListener, "Provided EventListener must not be null.");
        return addSnapshotListenerInternal(executor, internalOptions(metadataChanges), (Activity) null, eventListener);
    }

    public ListenerRegistration addSnapshotListener(Activity activity, MetadataChanges metadataChanges, EventListener<QuerySnapshot> eventListener) {
        Preconditions.checkNotNull(activity, "Provided activity must not be null.");
        Preconditions.checkNotNull(metadataChanges, "Provided MetadataChanges value must not be null.");
        Preconditions.checkNotNull(eventListener, "Provided EventListener must not be null.");
        return addSnapshotListenerInternal(Executors.DEFAULT_CALLBACK_EXECUTOR, internalOptions(metadataChanges), activity, eventListener);
    }

    private ListenerRegistration addSnapshotListenerInternal(Executor executor, EventManager.ListenOptions listenOptions, Activity activity, EventListener<QuerySnapshot> eventListener) {
        validateHasExplicitOrderByForLimitToLast();
        AsyncEventListener asyncEventListener = new AsyncEventListener(executor, Query$$Lambda$3.lambdaFactory$(this, eventListener));
        return ActivityScope.bind(activity, new ListenerRegistrationImpl(this.firestore.getClient(), this.firestore.getClient().listen(this.query, listenOptions, asyncEventListener), asyncEventListener));
    }

    static /* synthetic */ void lambda$addSnapshotListenerInternal$2(Query query2, EventListener eventListener, ViewSnapshot viewSnapshot, FirebaseFirestoreException firebaseFirestoreException) {
        if (firebaseFirestoreException != null) {
            eventListener.onEvent(null, firebaseFirestoreException);
            return;
        }
        Assert.hardAssert(viewSnapshot != null, "Got event without value or error set", new Object[0]);
        eventListener.onEvent(new QuerySnapshot(query2, viewSnapshot, query2.firestore), (FirebaseFirestoreException) null);
    }

    private void validateHasExplicitOrderByForLimitToLast() {
        if (this.query.hasLimitToLast() && this.query.getExplicitOrderBy().isEmpty()) {
            throw new IllegalStateException("limitToLast() queries require specifying at least one orderBy() clause");
        }
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Query)) {
            return false;
        }
        Query query2 = (Query) obj;
        if (!this.query.equals(query2.query) || !this.firestore.equals(query2.firestore)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return (this.query.hashCode() * 31) + this.firestore.hashCode();
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

package com.google.firebase.firestore.local;

import com.google.firebase.database.collection.ImmutableSortedMap;
import com.google.firebase.database.collection.ImmutableSortedSet;
import com.google.firebase.firestore.core.FieldFilter;
import com.google.firebase.firestore.core.Filter;
import com.google.firebase.firestore.core.IndexRange;
import com.google.firebase.firestore.core.Query;
import com.google.firebase.firestore.model.Document;
import com.google.firebase.firestore.model.DocumentCollections;
import com.google.firebase.firestore.model.DocumentKey;
import com.google.firebase.firestore.model.FieldPath;
import com.google.firebase.firestore.model.MaybeDocument;
import com.google.firebase.firestore.model.SnapshotVersion;
import com.google.firebase.firestore.model.Values;
import com.google.firebase.firestore.util.Assert;
import com.google.firestore.p009v1.Value;
import java.util.Arrays;
import java.util.List;

public class IndexedQueryEngine implements QueryEngine {
    private static final double HIGH_SELECTIVITY = 1.0d;
    private static final double LOW_SELECTIVITY = 0.5d;
    private static final List<Value.ValueTypeCase> lowCardinalityTypes = Arrays.asList(new Value.ValueTypeCase[]{Value.ValueTypeCase.BOOLEAN_VALUE, Value.ValueTypeCase.ARRAY_VALUE, Value.ValueTypeCase.MAP_VALUE});
    private final SQLiteCollectionIndex collectionIndex;
    private LocalDocumentsView localDocuments;

    public IndexedQueryEngine(SQLiteCollectionIndex sQLiteCollectionIndex) {
        this.collectionIndex = sQLiteCollectionIndex;
    }

    public void setLocalDocumentsView(LocalDocumentsView localDocumentsView) {
        this.localDocuments = localDocumentsView;
    }

    public ImmutableSortedMap<DocumentKey, Document> getDocumentsMatchingQuery(Query query, SnapshotVersion snapshotVersion, ImmutableSortedSet<DocumentKey> immutableSortedSet) {
        Assert.hardAssert(this.localDocuments != null, "setLocalDocumentsView() not called", new Object[0]);
        if (query.isDocumentQuery()) {
            return this.localDocuments.getDocumentsMatchingQuery(query, SnapshotVersion.NONE);
        }
        return performCollectionQuery(query);
    }

    private ImmutableSortedMap<DocumentKey, Document> performCollectionQuery(Query query) {
        Assert.hardAssert(!query.isDocumentQuery(), "matchesCollectionQuery() called with document query.", new Object[0]);
        IndexRange extractBestIndexRange = extractBestIndexRange(query);
        if (extractBestIndexRange != null) {
            return performQueryUsingIndex(query, extractBestIndexRange);
        }
        Assert.hardAssert(query.getFilters().isEmpty(), "If there are any filters, we should be able to use an index.", new Object[0]);
        return this.localDocuments.getDocumentsMatchingQuery(query, SnapshotVersion.NONE);
    }

    private ImmutableSortedMap<DocumentKey, Document> performQueryUsingIndex(Query query, IndexRange indexRange) {
        ImmutableSortedMap<DocumentKey, Document> emptyDocumentMap = DocumentCollections.emptyDocumentMap();
        IndexCursor cursor = this.collectionIndex.getCursor(query.getPath(), indexRange);
        while (cursor.next()) {
            try {
                Document document = (Document) this.localDocuments.getDocument(cursor.getDocumentKey());
                if (query.matches(document)) {
                    emptyDocumentMap = emptyDocumentMap.insert(cursor.getDocumentKey(), document);
                }
            } finally {
                cursor.close();
            }
        }
        return emptyDocumentMap;
    }

    private static double estimateFilterSelectivity(Filter filter) {
        Assert.hardAssert(filter instanceof FieldFilter, "Filter type expected to be FieldFilter", new Object[0]);
        FieldFilter fieldFilter = (FieldFilter) filter;
        Value value = fieldFilter.getValue();
        boolean isNullValue = Values.isNullValue(value);
        double d = HIGH_SELECTIVITY;
        if (isNullValue || Values.isNanValue(value)) {
            return HIGH_SELECTIVITY;
        }
        double d2 = fieldFilter.getOperator().equals(Filter.Operator.EQUAL) ? 1.0d : 0.5d;
        if (lowCardinalityTypes.contains(fieldFilter.getValue().getValueTypeCase())) {
            d = 0.5d;
        }
        return d * d2;
    }

    static IndexRange extractBestIndexRange(Query query) {
        Filter filter = null;
        if (!query.getFilters().isEmpty()) {
            double d = -1.0d;
            for (Filter next : query.getFilters()) {
                double estimateFilterSelectivity = estimateFilterSelectivity(next);
                if (estimateFilterSelectivity > d) {
                    filter = next;
                    d = estimateFilterSelectivity;
                }
            }
            Assert.hardAssert(filter != null, "Filter should be defined", new Object[0]);
            return convertFilterToIndexRange(filter);
        } else if (!query.getOrderBy().get(0).getField().equals(FieldPath.KEY_PATH)) {
            return IndexRange.builder().setFieldPath(query.getOrderBy().get(0).getField()).build();
        } else {
            return null;
        }
    }

    private static IndexRange convertFilterToIndexRange(Filter filter) {
        IndexRange.Builder fieldPath = IndexRange.builder().setFieldPath(filter.getField());
        if (filter instanceof FieldFilter) {
            FieldFilter fieldFilter = (FieldFilter) filter;
            Value value = fieldFilter.getValue();
            int i = C18891.$SwitchMap$com$google$firebase$firestore$core$Filter$Operator[fieldFilter.getOperator().ordinal()];
            if (i == 1) {
                fieldPath.setStart(value).setEnd(value);
            } else if (i == 2 || i == 3) {
                fieldPath.setEnd(value);
            } else if (i == 4 || i == 5) {
                fieldPath.setStart(value);
            } else {
                throw Assert.fail("Unexpected operator in query filter", new Object[0]);
            }
        }
        return fieldPath.build();
    }

    /* renamed from: com.google.firebase.firestore.local.IndexedQueryEngine$1 */
    static /* synthetic */ class C18891 {
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
                com.google.firebase.firestore.core.Filter$Operator r1 = com.google.firebase.firestore.core.Filter.Operator.EQUAL     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$com$google$firebase$firestore$core$Filter$Operator     // Catch:{ NoSuchFieldError -> 0x001d }
                com.google.firebase.firestore.core.Filter$Operator r1 = com.google.firebase.firestore.core.Filter.Operator.LESS_THAN_OR_EQUAL     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$com$google$firebase$firestore$core$Filter$Operator     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.google.firebase.firestore.core.Filter$Operator r1 = com.google.firebase.firestore.core.Filter.Operator.LESS_THAN     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = $SwitchMap$com$google$firebase$firestore$core$Filter$Operator     // Catch:{ NoSuchFieldError -> 0x0033 }
                com.google.firebase.firestore.core.Filter$Operator r1 = com.google.firebase.firestore.core.Filter.Operator.GREATER_THAN     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                int[] r0 = $SwitchMap$com$google$firebase$firestore$core$Filter$Operator     // Catch:{ NoSuchFieldError -> 0x003e }
                com.google.firebase.firestore.core.Filter$Operator r1 = com.google.firebase.firestore.core.Filter.Operator.GREATER_THAN_OR_EQUAL     // Catch:{ NoSuchFieldError -> 0x003e }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x003e }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x003e }
            L_0x003e:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.firestore.local.IndexedQueryEngine.C18891.<clinit>():void");
        }
    }

    public void handleDocumentChange(MaybeDocument maybeDocument, MaybeDocument maybeDocument2) {
        throw new RuntimeException("Not yet implemented.");
    }
}

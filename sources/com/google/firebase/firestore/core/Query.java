package com.google.firebase.firestore.core;

import com.google.firebase.firestore.core.Filter;
import com.google.firebase.firestore.core.OrderBy;
import com.google.firebase.firestore.model.Document;
import com.google.firebase.firestore.model.DocumentKey;
import com.google.firebase.firestore.model.FieldPath;
import com.google.firebase.firestore.model.ResourcePath;
import com.google.firebase.firestore.util.Assert;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

public final class Query {
    private static final OrderBy KEY_ORDERING_ASC = OrderBy.getInstance(OrderBy.Direction.ASCENDING, FieldPath.KEY_PATH);
    private static final OrderBy KEY_ORDERING_DESC = OrderBy.getInstance(OrderBy.Direction.DESCENDING, FieldPath.KEY_PATH);
    private final String collectionGroup;
    private final Bound endAt;
    private final List<OrderBy> explicitSortOrder;
    private final List<Filter> filters;
    private final long limit;
    private final LimitType limitType;
    private List<OrderBy> memoizedOrderBy;
    private Target memoizedTarget;
    private final ResourcePath path;
    private final Bound startAt;

    public enum LimitType {
        LIMIT_TO_FIRST,
        LIMIT_TO_LAST
    }

    public static Query atPath(ResourcePath resourcePath) {
        return new Query(resourcePath, (String) null);
    }

    public Query(ResourcePath resourcePath, String str, List<Filter> list, List<OrderBy> list2, long j, LimitType limitType2, Bound bound, Bound bound2) {
        this.path = resourcePath;
        this.collectionGroup = str;
        this.explicitSortOrder = list2;
        this.filters = list;
        this.limit = j;
        this.limitType = limitType2;
        this.startAt = bound;
        this.endAt = bound2;
    }

    public Query(ResourcePath resourcePath, String str) {
        this(resourcePath, str, Collections.emptyList(), Collections.emptyList(), -1, LimitType.LIMIT_TO_FIRST, (Bound) null, (Bound) null);
    }

    public ResourcePath getPath() {
        return this.path;
    }

    public String getCollectionGroup() {
        return this.collectionGroup;
    }

    public boolean isDocumentQuery() {
        return DocumentKey.isDocumentKey(this.path) && this.collectionGroup == null && this.filters.isEmpty();
    }

    public boolean isCollectionGroupQuery() {
        return this.collectionGroup != null;
    }

    public boolean matchesAllDocuments() {
        if (this.filters.isEmpty() && this.limit == -1 && this.startAt == null && this.endAt == null) {
            if (getExplicitOrderBy().isEmpty()) {
                return true;
            }
            if (getExplicitOrderBy().size() != 1 || !getFirstOrderByField().isKeyField()) {
                return false;
            }
            return true;
        }
        return false;
    }

    public List<Filter> getFilters() {
        return this.filters;
    }

    public long getLimitToFirst() {
        Assert.hardAssert(hasLimitToFirst(), "Called getLimitToFirst when no limit was set", new Object[0]);
        return this.limit;
    }

    public boolean hasLimitToFirst() {
        return this.limitType == LimitType.LIMIT_TO_FIRST && this.limit != -1;
    }

    public long getLimitToLast() {
        Assert.hardAssert(hasLimitToLast(), "Called getLimitToLast when no limit was set", new Object[0]);
        return this.limit;
    }

    public boolean hasLimitToLast() {
        return this.limitType == LimitType.LIMIT_TO_LAST && this.limit != -1;
    }

    public LimitType getLimitType() {
        Assert.hardAssert(hasLimitToLast() || hasLimitToFirst(), "Called getLimitType when no limit was set", new Object[0]);
        return this.limitType;
    }

    public Bound getStartAt() {
        return this.startAt;
    }

    public Bound getEndAt() {
        return this.endAt;
    }

    public FieldPath getFirstOrderByField() {
        if (this.explicitSortOrder.isEmpty()) {
            return null;
        }
        return this.explicitSortOrder.get(0).getField();
    }

    public FieldPath inequalityField() {
        for (Filter next : this.filters) {
            if (next instanceof FieldFilter) {
                FieldFilter fieldFilter = (FieldFilter) next;
                if (fieldFilter.isInequality()) {
                    return fieldFilter.getField();
                }
            }
        }
        return null;
    }

    public Filter.Operator findFilterOperator(List<Filter.Operator> list) {
        for (Filter next : this.filters) {
            if (next instanceof FieldFilter) {
                Filter.Operator operator = ((FieldFilter) next).getOperator();
                if (list.contains(operator)) {
                    return operator;
                }
            }
        }
        return null;
    }

    public Query filter(Filter filter) {
        boolean z = true;
        Assert.hardAssert(!isDocumentQuery(), "No filter is allowed for document query", new Object[0]);
        FieldPath fieldPath = null;
        if ((filter instanceof FieldFilter) && ((FieldFilter) filter).isInequality()) {
            fieldPath = filter.getField();
        }
        FieldPath inequalityField = inequalityField();
        Assert.hardAssert(inequalityField == null || fieldPath == null || inequalityField.equals(fieldPath), "Query must only have one inequality field", new Object[0]);
        if (!this.explicitSortOrder.isEmpty() && fieldPath != null && !this.explicitSortOrder.get(0).field.equals(fieldPath)) {
            z = false;
        }
        Assert.hardAssert(z, "First orderBy must match inequality field", new Object[0]);
        ArrayList arrayList = new ArrayList(this.filters);
        arrayList.add(filter);
        return new Query(this.path, this.collectionGroup, arrayList, this.explicitSortOrder, this.limit, this.limitType, this.startAt, this.endAt);
    }

    public Query orderBy(OrderBy orderBy) {
        FieldPath inequalityField;
        Assert.hardAssert(!isDocumentQuery(), "No ordering is allowed for document query", new Object[0]);
        if (!this.explicitSortOrder.isEmpty() || (inequalityField = inequalityField()) == null || inequalityField.equals(orderBy.field)) {
            ArrayList arrayList = new ArrayList(this.explicitSortOrder);
            arrayList.add(orderBy);
            return new Query(this.path, this.collectionGroup, this.filters, arrayList, this.limit, this.limitType, this.startAt, this.endAt);
        }
        throw Assert.fail("First orderBy must match inequality field", new Object[0]);
    }

    public Query limitToFirst(long j) {
        return new Query(this.path, this.collectionGroup, this.filters, this.explicitSortOrder, j, LimitType.LIMIT_TO_FIRST, this.startAt, this.endAt);
    }

    public Query limitToLast(long j) {
        return new Query(this.path, this.collectionGroup, this.filters, this.explicitSortOrder, j, LimitType.LIMIT_TO_LAST, this.startAt, this.endAt);
    }

    public Query startAt(Bound bound) {
        return new Query(this.path, this.collectionGroup, this.filters, this.explicitSortOrder, this.limit, this.limitType, bound, this.endAt);
    }

    public Query endAt(Bound bound) {
        return new Query(this.path, this.collectionGroup, this.filters, this.explicitSortOrder, this.limit, this.limitType, this.startAt, bound);
    }

    public Query asCollectionQueryAtPath(ResourcePath resourcePath) {
        return new Query(resourcePath, (String) null, this.filters, this.explicitSortOrder, this.limit, this.limitType, this.startAt, this.endAt);
    }

    public List<OrderBy> getExplicitOrderBy() {
        return this.explicitSortOrder;
    }

    public List<OrderBy> getOrderBy() {
        OrderBy.Direction direction;
        if (this.memoizedOrderBy == null) {
            FieldPath inequalityField = inequalityField();
            FieldPath firstOrderByField = getFirstOrderByField();
            boolean z = false;
            if (inequalityField == null || firstOrderByField != null) {
                ArrayList arrayList = new ArrayList();
                for (OrderBy next : this.explicitSortOrder) {
                    arrayList.add(next);
                    if (next.getField().equals(FieldPath.KEY_PATH)) {
                        z = true;
                    }
                }
                if (!z) {
                    if (this.explicitSortOrder.size() > 0) {
                        List<OrderBy> list = this.explicitSortOrder;
                        direction = list.get(list.size() - 1).getDirection();
                    } else {
                        direction = OrderBy.Direction.ASCENDING;
                    }
                    arrayList.add(direction.equals(OrderBy.Direction.ASCENDING) ? KEY_ORDERING_ASC : KEY_ORDERING_DESC);
                }
                this.memoizedOrderBy = arrayList;
            } else if (inequalityField.isKeyField()) {
                this.memoizedOrderBy = Collections.singletonList(KEY_ORDERING_ASC);
            } else {
                this.memoizedOrderBy = Arrays.asList(new OrderBy[]{OrderBy.getInstance(OrderBy.Direction.ASCENDING, inequalityField), KEY_ORDERING_ASC});
            }
        }
        return this.memoizedOrderBy;
    }

    private boolean matchesPathAndCollectionGroup(Document document) {
        ResourcePath path2 = document.getKey().getPath();
        if (this.collectionGroup != null) {
            if (!document.getKey().hasCollectionId(this.collectionGroup) || !this.path.isPrefixOf(path2)) {
                return false;
            }
            return true;
        } else if (DocumentKey.isDocumentKey(this.path)) {
            return this.path.equals(path2);
        } else {
            if (!this.path.isPrefixOf(path2) || this.path.length() != path2.length() - 1) {
                return false;
            }
            return true;
        }
    }

    private boolean matchesFilters(Document document) {
        for (Filter matches : this.filters) {
            if (!matches.matches(document)) {
                return false;
            }
        }
        return true;
    }

    private boolean matchesOrderBy(Document document) {
        for (OrderBy next : this.explicitSortOrder) {
            if (!next.getField().equals(FieldPath.KEY_PATH) && document.getField(next.field) == null) {
                return false;
            }
        }
        return true;
    }

    private boolean matchesBounds(Document document) {
        Bound bound = this.startAt;
        if (bound != null && !bound.sortsBeforeDocument(getOrderBy(), document)) {
            return false;
        }
        Bound bound2 = this.endAt;
        if (bound2 == null || !bound2.sortsBeforeDocument(getOrderBy(), document)) {
            return true;
        }
        return false;
    }

    public boolean matches(Document document) {
        return matchesPathAndCollectionGroup(document) && matchesOrderBy(document) && matchesFilters(document) && matchesBounds(document);
    }

    public Comparator<Document> comparator() {
        return new QueryComparator(getOrderBy());
    }

    private static class QueryComparator implements Comparator<Document> {
        private final List<OrderBy> sortOrder;

        QueryComparator(List<OrderBy> list) {
            boolean z;
            Iterator<OrderBy> it = list.iterator();
            loop0:
            while (true) {
                z = false;
                while (true) {
                    if (!it.hasNext()) {
                        break loop0;
                    }
                    OrderBy next = it.next();
                    if (z || next.getField().equals(FieldPath.KEY_PATH)) {
                        z = true;
                    }
                }
            }
            if (z) {
                this.sortOrder = list;
                return;
            }
            throw new IllegalArgumentException("QueryComparator needs to have a key ordering");
        }

        public int compare(Document document, Document document2) {
            for (OrderBy compare : this.sortOrder) {
                int compare2 = compare.compare(document, document2);
                if (compare2 != 0) {
                    return compare2;
                }
            }
            return 0;
        }
    }

    public Target toTarget() {
        OrderBy.Direction direction;
        if (this.memoizedTarget == null) {
            if (this.limitType == LimitType.LIMIT_TO_FIRST) {
                this.memoizedTarget = new Target(getPath(), getCollectionGroup(), getFilters(), getOrderBy(), this.limit, getStartAt(), getEndAt());
            } else {
                ArrayList arrayList = new ArrayList();
                for (OrderBy next : getOrderBy()) {
                    if (next.getDirection() == OrderBy.Direction.DESCENDING) {
                        direction = OrderBy.Direction.ASCENDING;
                    } else {
                        direction = OrderBy.Direction.DESCENDING;
                    }
                    arrayList.add(OrderBy.getInstance(direction, next.getField()));
                }
                Bound bound = this.endAt;
                Bound bound2 = null;
                Bound bound3 = bound != null ? new Bound(bound.getPosition(), !this.endAt.isBefore()) : null;
                Bound bound4 = this.startAt;
                if (bound4 != null) {
                    bound2 = new Bound(bound4.getPosition(), !this.startAt.isBefore());
                }
                this.memoizedTarget = new Target(getPath(), getCollectionGroup(), getFilters(), arrayList, this.limit, bound3, bound2);
            }
        }
        return this.memoizedTarget;
    }

    public String getCanonicalId() {
        return toTarget().getCanonicalId() + "|lt:" + this.limitType;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Query query = (Query) obj;
        if (this.limitType != query.limitType) {
            return false;
        }
        return toTarget().equals(query.toTarget());
    }

    public int hashCode() {
        return (toTarget().hashCode() * 31) + this.limitType.hashCode();
    }

    public String toString() {
        return "Query(target=" + toTarget().toString() + ";limitType=" + this.limitType.toString() + ")";
    }
}

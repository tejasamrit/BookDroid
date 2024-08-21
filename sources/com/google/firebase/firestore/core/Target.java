package com.google.firebase.firestore.core;

import com.google.firebase.firestore.core.OrderBy;
import com.google.firebase.firestore.model.DocumentKey;
import com.google.firebase.firestore.model.ResourcePath;
import com.google.firebase.firestore.util.Assert;
import java.util.List;

public final class Target {
    public static final long NO_LIMIT = -1;
    private final String collectionGroup;
    private final Bound endAt;
    private final List<Filter> filters;
    private final long limit;
    private String memoizedCannonicalId;
    private final List<OrderBy> orderBy;
    private final ResourcePath path;
    private final Bound startAt;

    Target(ResourcePath resourcePath, String str, List<Filter> list, List<OrderBy> list2, long j, Bound bound, Bound bound2) {
        this.path = resourcePath;
        this.collectionGroup = str;
        this.orderBy = list2;
        this.filters = list;
        this.limit = j;
        this.startAt = bound;
        this.endAt = bound2;
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

    public List<Filter> getFilters() {
        return this.filters;
    }

    public long getLimit() {
        Assert.hardAssert(hasLimit(), "Called getLimit when no limit was set", new Object[0]);
        return this.limit;
    }

    public boolean hasLimit() {
        return this.limit != -1;
    }

    public Bound getStartAt() {
        return this.startAt;
    }

    public Bound getEndAt() {
        return this.endAt;
    }

    public List<OrderBy> getOrderBy() {
        return this.orderBy;
    }

    public String getCanonicalId() {
        String str = this.memoizedCannonicalId;
        if (str != null) {
            return str;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(getPath().canonicalString());
        if (this.collectionGroup != null) {
            sb.append("|cg:");
            sb.append(this.collectionGroup);
        }
        sb.append("|f:");
        for (Filter canonicalId : getFilters()) {
            sb.append(canonicalId.getCanonicalId());
        }
        sb.append("|ob:");
        for (OrderBy next : getOrderBy()) {
            sb.append(next.getField().canonicalString());
            sb.append(next.getDirection().equals(OrderBy.Direction.ASCENDING) ? "asc" : "desc");
        }
        if (hasLimit()) {
            sb.append("|l:");
            sb.append(getLimit());
        }
        if (this.startAt != null) {
            sb.append("|lb:");
            sb.append(this.startAt.canonicalString());
        }
        if (this.endAt != null) {
            sb.append("|ub:");
            sb.append(this.endAt.canonicalString());
        }
        String sb2 = sb.toString();
        this.memoizedCannonicalId = sb2;
        return sb2;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Target target = (Target) obj;
        String str = this.collectionGroup;
        if (str == null ? target.collectionGroup != null : !str.equals(target.collectionGroup)) {
            return false;
        }
        if (this.limit != target.limit || !this.orderBy.equals(target.orderBy) || !this.filters.equals(target.filters) || !this.path.equals(target.path)) {
            return false;
        }
        Bound bound = this.startAt;
        if (bound == null ? target.startAt != null : !bound.equals(target.startAt)) {
            return false;
        }
        Bound bound2 = this.endAt;
        Bound bound3 = target.endAt;
        if (bound2 != null) {
            return bound2.equals(bound3);
        }
        if (bound3 == null) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        int hashCode = this.orderBy.hashCode() * 31;
        String str = this.collectionGroup;
        int i = 0;
        int hashCode2 = str != null ? str.hashCode() : 0;
        long j = this.limit;
        int hashCode3 = (((((((hashCode + hashCode2) * 31) + this.filters.hashCode()) * 31) + this.path.hashCode()) * 31) + ((int) (j ^ (j >>> 32)))) * 31;
        Bound bound = this.startAt;
        int hashCode4 = (hashCode3 + (bound != null ? bound.hashCode() : 0)) * 31;
        Bound bound2 = this.endAt;
        if (bound2 != null) {
            i = bound2.hashCode();
        }
        return hashCode4 + i;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Query(");
        sb.append(this.path.canonicalString());
        if (this.collectionGroup != null) {
            sb.append(" collectionGroup=");
            sb.append(this.collectionGroup);
        }
        if (!this.filters.isEmpty()) {
            sb.append(" where ");
            for (int i = 0; i < this.filters.size(); i++) {
                if (i > 0) {
                    sb.append(" and ");
                }
                sb.append(this.filters.get(i).toString());
            }
        }
        if (!this.orderBy.isEmpty()) {
            sb.append(" order by ");
            for (int i2 = 0; i2 < this.orderBy.size(); i2++) {
                if (i2 > 0) {
                    sb.append(", ");
                }
                sb.append(this.orderBy.get(i2));
            }
        }
        sb.append(")");
        return sb.toString();
    }
}

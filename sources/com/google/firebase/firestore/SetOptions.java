package com.google.firebase.firestore;

import com.google.firebase.firestore.model.mutation.FieldMask;
import com.google.firebase.firestore.util.Preconditions;
import java.util.HashSet;
import java.util.List;

public final class SetOptions {
    private static final SetOptions MERGE_ALL_FIELDS = new SetOptions(true, (FieldMask) null);
    static final SetOptions OVERWRITE = new SetOptions(false, (FieldMask) null);
    private final FieldMask fieldMask;
    private final boolean merge;

    private SetOptions(boolean z, FieldMask fieldMask2) {
        Preconditions.checkArgument(fieldMask2 == null || z, "Cannot specify a fieldMask for non-merge sets()", new Object[0]);
        this.merge = z;
        this.fieldMask = fieldMask2;
    }

    /* access modifiers changed from: package-private */
    public boolean isMerge() {
        return this.merge;
    }

    public FieldMask getFieldMask() {
        return this.fieldMask;
    }

    public static SetOptions merge() {
        return MERGE_ALL_FIELDS;
    }

    public static SetOptions mergeFields(List<String> list) {
        HashSet hashSet = new HashSet();
        for (String fromDotSeparatedPath : list) {
            hashSet.add(FieldPath.fromDotSeparatedPath(fromDotSeparatedPath).getInternalPath());
        }
        return new SetOptions(true, FieldMask.fromSet(hashSet));
    }

    public static SetOptions mergeFields(String... strArr) {
        HashSet hashSet = new HashSet();
        for (String fromDotSeparatedPath : strArr) {
            hashSet.add(FieldPath.fromDotSeparatedPath(fromDotSeparatedPath).getInternalPath());
        }
        return new SetOptions(true, FieldMask.fromSet(hashSet));
    }

    public static SetOptions mergeFieldPaths(List<FieldPath> list) {
        HashSet hashSet = new HashSet();
        for (FieldPath internalPath : list) {
            hashSet.add(internalPath.getInternalPath());
        }
        return new SetOptions(true, FieldMask.fromSet(hashSet));
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        SetOptions setOptions = (SetOptions) obj;
        if (this.merge != setOptions.merge) {
            return false;
        }
        FieldMask fieldMask2 = this.fieldMask;
        FieldMask fieldMask3 = setOptions.fieldMask;
        if (fieldMask2 != null) {
            return fieldMask2.equals(fieldMask3);
        }
        if (fieldMask3 == null) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        int i = (this.merge ? 1 : 0) * true;
        FieldMask fieldMask2 = this.fieldMask;
        return i + (fieldMask2 != null ? fieldMask2.hashCode() : 0);
    }
}

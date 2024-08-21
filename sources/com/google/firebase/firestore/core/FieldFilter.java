package com.google.firebase.firestore.core;

import com.google.firebase.firestore.core.Filter;
import com.google.firebase.firestore.model.Document;
import com.google.firebase.firestore.model.FieldPath;
import com.google.firebase.firestore.model.Values;
import com.google.firebase.firestore.util.Assert;
import com.google.firestore.p009v1.Value;
import java.util.Arrays;

public class FieldFilter extends Filter {
    private final FieldPath field;
    private final Filter.Operator operator;
    private final Value value;

    protected FieldFilter(FieldPath fieldPath, Filter.Operator operator2, Value value2) {
        this.field = fieldPath;
        this.operator = operator2;
        this.value = value2;
    }

    public Filter.Operator getOperator() {
        return this.operator;
    }

    public FieldPath getField() {
        return this.field;
    }

    public Value getValue() {
        return this.value;
    }

    public static FieldFilter create(FieldPath fieldPath, Filter.Operator operator2, Value value2) {
        if (fieldPath.isKeyField()) {
            if (operator2 == Filter.Operator.IN) {
                return new KeyFieldInFilter(fieldPath, value2);
            }
            if (operator2 == Filter.Operator.NOT_IN) {
                return new KeyFieldNotInFilter(fieldPath, value2);
            }
            boolean z = (operator2 == Filter.Operator.ARRAY_CONTAINS || operator2 == Filter.Operator.ARRAY_CONTAINS_ANY) ? false : true;
            Assert.hardAssert(z, operator2.toString() + "queries don't make sense on document keys", new Object[0]);
            return new KeyFieldFilter(fieldPath, operator2, value2);
        } else if (operator2 == Filter.Operator.ARRAY_CONTAINS) {
            return new ArrayContainsFilter(fieldPath, value2);
        } else {
            if (operator2 == Filter.Operator.IN) {
                return new InFilter(fieldPath, value2);
            }
            if (operator2 == Filter.Operator.ARRAY_CONTAINS_ANY) {
                return new ArrayContainsAnyFilter(fieldPath, value2);
            }
            if (operator2 == Filter.Operator.NOT_IN) {
                return new NotInFilter(fieldPath, value2);
            }
            return new FieldFilter(fieldPath, operator2, value2);
        }
    }

    public boolean matches(Document document) {
        Value field2 = document.getField(this.field);
        if (this.operator == Filter.Operator.NOT_EQUAL) {
            if (field2 == null || !matchesComparison(Values.compare(field2, this.value))) {
                return false;
            }
            return true;
        } else if (field2 == null || Values.typeOrder(field2) != Values.typeOrder(this.value) || !matchesComparison(Values.compare(field2, this.value))) {
            return false;
        } else {
            return true;
        }
    }

    /* renamed from: com.google.firebase.firestore.core.FieldFilter$1 */
    static /* synthetic */ class C18831 {
        static final /* synthetic */ int[] $SwitchMap$com$google$firebase$firestore$core$Filter$Operator;

        /* JADX WARNING: Can't wrap try/catch for region: R(14:0|1|2|3|4|5|6|7|8|9|10|11|12|14) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x003e */
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
                com.google.firebase.firestore.core.Filter$Operator r1 = com.google.firebase.firestore.core.Filter.Operator.LESS_THAN     // Catch:{ NoSuchFieldError -> 0x0012 }
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
                com.google.firebase.firestore.core.Filter$Operator r1 = com.google.firebase.firestore.core.Filter.Operator.EQUAL     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = $SwitchMap$com$google$firebase$firestore$core$Filter$Operator     // Catch:{ NoSuchFieldError -> 0x0033 }
                com.google.firebase.firestore.core.Filter$Operator r1 = com.google.firebase.firestore.core.Filter.Operator.NOT_EQUAL     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                int[] r0 = $SwitchMap$com$google$firebase$firestore$core$Filter$Operator     // Catch:{ NoSuchFieldError -> 0x003e }
                com.google.firebase.firestore.core.Filter$Operator r1 = com.google.firebase.firestore.core.Filter.Operator.GREATER_THAN     // Catch:{ NoSuchFieldError -> 0x003e }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x003e }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x003e }
            L_0x003e:
                int[] r0 = $SwitchMap$com$google$firebase$firestore$core$Filter$Operator     // Catch:{ NoSuchFieldError -> 0x0049 }
                com.google.firebase.firestore.core.Filter$Operator r1 = com.google.firebase.firestore.core.Filter.Operator.GREATER_THAN_OR_EQUAL     // Catch:{ NoSuchFieldError -> 0x0049 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0049 }
                r2 = 6
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0049 }
            L_0x0049:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.firestore.core.FieldFilter.C18831.<clinit>():void");
        }
    }

    /* access modifiers changed from: protected */
    public boolean matchesComparison(int i) {
        switch (C18831.$SwitchMap$com$google$firebase$firestore$core$Filter$Operator[this.operator.ordinal()]) {
            case 1:
                return i < 0;
            case 2:
                return i <= 0;
            case 3:
                return i == 0;
            case 4:
                return i != 0;
            case 5:
                return i > 0;
            case 6:
                return i >= 0;
            default:
                throw Assert.fail("Unknown FieldFilter operator: %s", this.operator);
        }
    }

    public boolean isInequality() {
        return Arrays.asList(new Filter.Operator[]{Filter.Operator.LESS_THAN, Filter.Operator.LESS_THAN_OR_EQUAL, Filter.Operator.GREATER_THAN, Filter.Operator.GREATER_THAN_OR_EQUAL, Filter.Operator.NOT_EQUAL, Filter.Operator.NOT_IN}).contains(this.operator);
    }

    public String getCanonicalId() {
        return getField().canonicalString() + getOperator().toString() + Values.canonicalId(getValue());
    }

    public String toString() {
        return this.field.canonicalString() + " " + this.operator + " " + this.value;
    }

    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof FieldFilter)) {
            return false;
        }
        FieldFilter fieldFilter = (FieldFilter) obj;
        if (this.operator != fieldFilter.operator || !this.field.equals(fieldFilter.field) || !this.value.equals(fieldFilter.value)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return ((((1147 + this.operator.hashCode()) * 31) + this.field.hashCode()) * 31) + this.value.hashCode();
    }
}

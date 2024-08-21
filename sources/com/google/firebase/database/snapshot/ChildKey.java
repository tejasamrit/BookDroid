package com.google.firebase.database.snapshot;

import com.google.firebase.database.core.utilities.Utilities;

public class ChildKey implements Comparable<ChildKey> {
    private static final ChildKey INFO_CHILD_KEY = new ChildKey(".info");
    private static final ChildKey MAX_KEY = new ChildKey("[MAX_KEY]");
    private static final ChildKey MIN_KEY = new ChildKey("[MIN_KEY]");
    private static final ChildKey PRIORITY_CHILD_KEY = new ChildKey(".priority");
    /* access modifiers changed from: private */
    public final String key;

    /* access modifiers changed from: protected */
    public int intValue() {
        return 0;
    }

    /* access modifiers changed from: protected */
    public boolean isInt() {
        return false;
    }

    public static ChildKey getMinName() {
        return MIN_KEY;
    }

    public static ChildKey getMaxName() {
        return MAX_KEY;
    }

    public static ChildKey getPriorityKey() {
        return PRIORITY_CHILD_KEY;
    }

    public static ChildKey getInfoKey() {
        return INFO_CHILD_KEY;
    }

    private ChildKey(String str) {
        this.key = str;
    }

    public String asString() {
        return this.key;
    }

    public boolean isPriorityChildName() {
        return equals(PRIORITY_CHILD_KEY);
    }

    public int compareTo(ChildKey childKey) {
        ChildKey childKey2;
        if (this == childKey) {
            return 0;
        }
        ChildKey childKey3 = MIN_KEY;
        if (this == childKey3 || childKey == (childKey2 = MAX_KEY)) {
            return -1;
        }
        if (childKey == childKey3 || this == childKey2) {
            return 1;
        }
        if (isInt()) {
            if (!childKey.isInt()) {
                return -1;
            }
            int compareInts = Utilities.compareInts(intValue(), childKey.intValue());
            return compareInts == 0 ? Utilities.compareInts(this.key.length(), childKey.key.length()) : compareInts;
        } else if (childKey.isInt()) {
            return 1;
        } else {
            return this.key.compareTo(childKey.key);
        }
    }

    public String toString() {
        return "ChildKey(\"" + this.key + "\")";
    }

    public int hashCode() {
        return this.key.hashCode();
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof ChildKey)) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        return this.key.equals(((ChildKey) obj).key);
    }

    public static ChildKey fromString(String str) {
        Integer tryParseInt = Utilities.tryParseInt(str);
        if (tryParseInt != null) {
            return new IntegerChildKey(str, tryParseInt.intValue());
        }
        if (str.equals(".priority")) {
            return PRIORITY_CHILD_KEY;
        }
        Utilities.hardAssert(!str.contains("/"));
        return new ChildKey(str);
    }

    private static class IntegerChildKey extends ChildKey {
        private final int intValue;

        /* access modifiers changed from: protected */
        public boolean isInt() {
            return true;
        }

        public /* bridge */ /* synthetic */ int compareTo(Object obj) {
            return ChildKey.super.compareTo((ChildKey) obj);
        }

        IntegerChildKey(String str, int i) {
            super(str);
            this.intValue = i;
        }

        /* access modifiers changed from: protected */
        public int intValue() {
            return this.intValue;
        }

        public String toString() {
            return "IntegerChildName(\"" + this.key + "\")";
        }
    }
}

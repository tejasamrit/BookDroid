package com.google.firebase.database.snapshot;

import com.google.firebase.database.core.utilities.Utilities;
import com.google.firebase.database.snapshot.LeafNode;
import com.google.firebase.database.snapshot.Node;

public class StringNode extends LeafNode<StringNode> {
    private final String value;

    public StringNode(String str, Node node) {
        super(node);
        this.value = str;
    }

    public Object getValue() {
        return this.value;
    }

    /* renamed from: com.google.firebase.database.snapshot.StringNode$1 */
    static /* synthetic */ class C18601 {

        /* renamed from: $SwitchMap$com$google$firebase$database$snapshot$Node$HashVersion */
        static final /* synthetic */ int[] f322x2aed15f4;

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|6) */
        /* JADX WARNING: Code restructure failed: missing block: B:7:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        static {
            /*
                com.google.firebase.database.snapshot.Node$HashVersion[] r0 = com.google.firebase.database.snapshot.Node.HashVersion.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                f322x2aed15f4 = r0
                com.google.firebase.database.snapshot.Node$HashVersion r1 = com.google.firebase.database.snapshot.Node.HashVersion.V1     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = f322x2aed15f4     // Catch:{ NoSuchFieldError -> 0x001d }
                com.google.firebase.database.snapshot.Node$HashVersion r1 = com.google.firebase.database.snapshot.Node.HashVersion.V2     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.database.snapshot.StringNode.C18601.<clinit>():void");
        }
    }

    public String getHashRepresentation(Node.HashVersion hashVersion) {
        int i = C18601.f322x2aed15f4[hashVersion.ordinal()];
        if (i == 1) {
            return getPriorityHash(hashVersion) + "string:" + this.value;
        } else if (i == 2) {
            return getPriorityHash(hashVersion) + "string:" + Utilities.stringHashV2Representation(this.value);
        } else {
            throw new IllegalArgumentException("Invalid hash version for string node: " + hashVersion);
        }
    }

    public StringNode updatePriority(Node node) {
        return new StringNode(this.value, node);
    }

    /* access modifiers changed from: protected */
    public LeafNode.LeafType getLeafType() {
        return LeafNode.LeafType.String;
    }

    /* access modifiers changed from: protected */
    public int compareLeafValues(StringNode stringNode) {
        return this.value.compareTo(stringNode.value);
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof StringNode)) {
            return false;
        }
        StringNode stringNode = (StringNode) obj;
        if (!this.value.equals(stringNode.value) || !this.priority.equals(stringNode.priority)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return this.value.hashCode() + this.priority.hashCode();
    }
}

package com.google.firebase.database.snapshot;

import com.google.firebase.database.core.utilities.Utilities;
import com.google.firebase.database.snapshot.LeafNode;
import com.google.firebase.database.snapshot.Node;
import java.util.Map;

public class DeferredValueNode extends LeafNode<DeferredValueNode> {
    private Map<Object, Object> value;

    /* access modifiers changed from: protected */
    public int compareLeafValues(DeferredValueNode deferredValueNode) {
        return 0;
    }

    public DeferredValueNode(Map<Object, Object> map, Node node) {
        super(node);
        this.value = map;
    }

    public Object getValue() {
        return this.value;
    }

    public String getHashRepresentation(Node.HashVersion hashVersion) {
        return getPriorityHash(hashVersion) + "deferredValue:" + this.value;
    }

    public DeferredValueNode updatePriority(Node node) {
        Utilities.hardAssert(PriorityUtilities.isValidPriority(node));
        return new DeferredValueNode(this.value, node);
    }

    /* access modifiers changed from: protected */
    public LeafNode.LeafType getLeafType() {
        return LeafNode.LeafType.DeferredValue;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof DeferredValueNode)) {
            return false;
        }
        DeferredValueNode deferredValueNode = (DeferredValueNode) obj;
        if (!this.value.equals(deferredValueNode.value) || !this.priority.equals(deferredValueNode.priority)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return this.value.hashCode() + this.priority.hashCode();
    }
}

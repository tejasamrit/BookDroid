package com.google.firebase.database.snapshot;

import com.google.firebase.database.core.utilities.Utilities;
import com.google.firebase.database.snapshot.LeafNode;
import com.google.firebase.database.snapshot.Node;

public class DoubleNode extends LeafNode<DoubleNode> {
    private final Double value;

    public DoubleNode(Double d, Node node) {
        super(node);
        this.value = d;
    }

    public Object getValue() {
        return this.value;
    }

    public String getHashRepresentation(Node.HashVersion hashVersion) {
        return (getPriorityHash(hashVersion) + "number:") + Utilities.doubleToHashString(this.value.doubleValue());
    }

    public DoubleNode updatePriority(Node node) {
        Utilities.hardAssert(PriorityUtilities.isValidPriority(node));
        return new DoubleNode(this.value, node);
    }

    /* access modifiers changed from: protected */
    public LeafNode.LeafType getLeafType() {
        return LeafNode.LeafType.Number;
    }

    /* access modifiers changed from: protected */
    public int compareLeafValues(DoubleNode doubleNode) {
        return this.value.compareTo(doubleNode.value);
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof DoubleNode)) {
            return false;
        }
        DoubleNode doubleNode = (DoubleNode) obj;
        if (!this.value.equals(doubleNode.value) || !this.priority.equals(doubleNode.priority)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return this.value.hashCode() + this.priority.hashCode();
    }
}

package com.google.firebase.database.core.utilities;

import com.google.firebase.database.snapshot.BooleanNode;
import com.google.firebase.database.snapshot.ChildrenNode;
import com.google.firebase.database.snapshot.DoubleNode;
import com.google.firebase.database.snapshot.LeafNode;
import com.google.firebase.database.snapshot.LongNode;
import com.google.firebase.database.snapshot.NamedNode;
import com.google.firebase.database.snapshot.Node;
import com.google.firebase.database.snapshot.StringNode;
import java.util.Iterator;

public class NodeSizeEstimator {
    private static final int LEAF_PRIORITY_OVERHEAD = 24;

    private static long estimateLeafNodeSize(LeafNode<?> leafNode) {
        long j = 8;
        if (!(leafNode instanceof DoubleNode) && !(leafNode instanceof LongNode)) {
            if (leafNode instanceof BooleanNode) {
                j = 4;
            } else if (leafNode instanceof StringNode) {
                j = ((long) ((String) leafNode.getValue()).length()) + 2;
            } else {
                throw new IllegalArgumentException("Unknown leaf node type: " + leafNode.getClass());
            }
        }
        if (leafNode.getPriority().isEmpty()) {
            return j;
        }
        return j + 24 + estimateLeafNodeSize((LeafNode) leafNode.getPriority());
    }

    public static long estimateSerializedNodeSize(Node node) {
        if (node.isEmpty()) {
            return 4;
        }
        if (node.isLeafNode()) {
            return estimateLeafNodeSize((LeafNode) node);
        }
        Utilities.hardAssert(node instanceof ChildrenNode, "Unexpected node type: " + node.getClass());
        long j = 1;
        Iterator it = node.iterator();
        while (it.hasNext()) {
            NamedNode namedNode = (NamedNode) it.next();
            j = j + ((long) namedNode.getName().asString().length()) + 4 + estimateSerializedNodeSize(namedNode.getNode());
        }
        return !node.getPriority().isEmpty() ? j + 12 + estimateLeafNodeSize((LeafNode) node.getPriority()) : j;
    }

    public static int nodeCount(Node node) {
        int i = 0;
        if (node.isEmpty()) {
            return 0;
        }
        if (node.isLeafNode()) {
            return 1;
        }
        Utilities.hardAssert(node instanceof ChildrenNode, "Unexpected node type: " + node.getClass());
        Iterator it = node.iterator();
        while (it.hasNext()) {
            i += nodeCount(((NamedNode) it.next()).getNode());
        }
        return i;
    }
}

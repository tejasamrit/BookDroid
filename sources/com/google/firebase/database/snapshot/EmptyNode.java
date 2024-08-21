package com.google.firebase.database.snapshot;

import com.google.firebase.database.core.Path;
import com.google.firebase.database.snapshot.Node;
import java.util.Collections;
import java.util.Iterator;

public class EmptyNode extends ChildrenNode implements Node {
    private static final EmptyNode empty = new EmptyNode();

    public Node getChild(Path path) {
        return this;
    }

    public int getChildCount() {
        return 0;
    }

    public String getHash() {
        return "";
    }

    public String getHashRepresentation(Node.HashVersion hashVersion) {
        return "";
    }

    public Node getImmediateChild(ChildKey childKey) {
        return this;
    }

    public ChildKey getPredecessorChildKey(ChildKey childKey) {
        return null;
    }

    public Node getPriority() {
        return this;
    }

    public ChildKey getSuccessorChildKey(ChildKey childKey) {
        return null;
    }

    public Object getValue() {
        return null;
    }

    public Object getValue(boolean z) {
        return null;
    }

    public boolean hasChild(ChildKey childKey) {
        return false;
    }

    public int hashCode() {
        return 0;
    }

    public boolean isEmpty() {
        return true;
    }

    public boolean isLeafNode() {
        return false;
    }

    public String toString() {
        return "<Empty Node>";
    }

    public EmptyNode updatePriority(Node node) {
        return this;
    }

    private EmptyNode() {
    }

    public static EmptyNode Empty() {
        return empty;
    }

    public Node updateImmediateChild(ChildKey childKey, Node node) {
        if (!node.isEmpty() && !childKey.isPriorityChildName()) {
            return new ChildrenNode().updateImmediateChild(childKey, node);
        }
        return this;
    }

    public Node updateChild(Path path, Node node) {
        if (path.isEmpty()) {
            return node;
        }
        ChildKey front = path.getFront();
        return updateImmediateChild(front, getImmediateChild(front).updateChild(path.popFront(), node));
    }

    public Iterator<NamedNode> iterator() {
        return Collections.emptyList().iterator();
    }

    public Iterator<NamedNode> reverseIterator() {
        return Collections.emptyList().iterator();
    }

    public int compareTo(Node node) {
        return node.isEmpty() ? 0 : -1;
    }

    public boolean equals(Object obj) {
        if (obj instanceof EmptyNode) {
            return true;
        }
        if (obj instanceof Node) {
            Node node = (Node) obj;
            if (!node.isEmpty() || !getPriority().equals(node.getPriority())) {
                return false;
            }
            return true;
        }
        return false;
    }
}

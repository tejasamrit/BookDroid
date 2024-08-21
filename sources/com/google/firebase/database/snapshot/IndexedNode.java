package com.google.firebase.database.snapshot;

import com.google.android.gms.common.internal.Objects;
import com.google.firebase.database.collection.ImmutableSortedSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;

public class IndexedNode implements Iterable<NamedNode> {
    private static final ImmutableSortedSet<NamedNode> FALLBACK_INDEX = new ImmutableSortedSet<>(Collections.emptyList(), (Comparator) null);
    private final Index index;
    private ImmutableSortedSet<NamedNode> indexed;
    private final Node node;

    private IndexedNode(Node node2, Index index2) {
        this.index = index2;
        this.node = node2;
        this.indexed = null;
    }

    private IndexedNode(Node node2, Index index2, ImmutableSortedSet<NamedNode> immutableSortedSet) {
        this.index = index2;
        this.node = node2;
        this.indexed = immutableSortedSet;
    }

    private void ensureIndexed() {
        if (this.indexed != null) {
            return;
        }
        if (this.index.equals(KeyIndex.getInstance())) {
            this.indexed = FALLBACK_INDEX;
            return;
        }
        ArrayList arrayList = new ArrayList();
        boolean z = false;
        for (NamedNode namedNode : this.node) {
            z = z || this.index.isDefinedOn(namedNode.getNode());
            arrayList.add(new NamedNode(namedNode.getName(), namedNode.getNode()));
        }
        if (z) {
            this.indexed = new ImmutableSortedSet<>(arrayList, this.index);
        } else {
            this.indexed = FALLBACK_INDEX;
        }
    }

    public static IndexedNode from(Node node2) {
        return new IndexedNode(node2, PriorityIndex.getInstance());
    }

    public static IndexedNode from(Node node2, Index index2) {
        return new IndexedNode(node2, index2);
    }

    public boolean hasIndex(Index index2) {
        return this.index == index2;
    }

    public Node getNode() {
        return this.node;
    }

    public Iterator<NamedNode> iterator() {
        ensureIndexed();
        if (Objects.equal(this.indexed, FALLBACK_INDEX)) {
            return this.node.iterator();
        }
        return this.indexed.iterator();
    }

    public Iterator<NamedNode> reverseIterator() {
        ensureIndexed();
        if (Objects.equal(this.indexed, FALLBACK_INDEX)) {
            return this.node.reverseIterator();
        }
        return this.indexed.reverseIterator();
    }

    public IndexedNode updateChild(ChildKey childKey, Node node2) {
        Node updateImmediateChild = this.node.updateImmediateChild(childKey, node2);
        ImmutableSortedSet<NamedNode> immutableSortedSet = this.indexed;
        ImmutableSortedSet<NamedNode> immutableSortedSet2 = FALLBACK_INDEX;
        if (Objects.equal(immutableSortedSet, immutableSortedSet2) && !this.index.isDefinedOn(node2)) {
            return new IndexedNode(updateImmediateChild, this.index, immutableSortedSet2);
        }
        ImmutableSortedSet<NamedNode> immutableSortedSet3 = this.indexed;
        if (immutableSortedSet3 == null || Objects.equal(immutableSortedSet3, immutableSortedSet2)) {
            return new IndexedNode(updateImmediateChild, this.index, (ImmutableSortedSet<NamedNode>) null);
        }
        ImmutableSortedSet<NamedNode> remove = this.indexed.remove(new NamedNode(childKey, this.node.getImmediateChild(childKey)));
        if (!node2.isEmpty()) {
            remove = remove.insert(new NamedNode(childKey, node2));
        }
        return new IndexedNode(updateImmediateChild, this.index, remove);
    }

    public IndexedNode updatePriority(Node node2) {
        return new IndexedNode(this.node.updatePriority(node2), this.index, this.indexed);
    }

    public NamedNode getFirstChild() {
        if (!(this.node instanceof ChildrenNode)) {
            return null;
        }
        ensureIndexed();
        if (!Objects.equal(this.indexed, FALLBACK_INDEX)) {
            return this.indexed.getMinEntry();
        }
        ChildKey firstChildKey = ((ChildrenNode) this.node).getFirstChildKey();
        return new NamedNode(firstChildKey, this.node.getImmediateChild(firstChildKey));
    }

    public NamedNode getLastChild() {
        if (!(this.node instanceof ChildrenNode)) {
            return null;
        }
        ensureIndexed();
        if (!Objects.equal(this.indexed, FALLBACK_INDEX)) {
            return this.indexed.getMaxEntry();
        }
        ChildKey lastChildKey = ((ChildrenNode) this.node).getLastChildKey();
        return new NamedNode(lastChildKey, this.node.getImmediateChild(lastChildKey));
    }

    public ChildKey getPredecessorChildName(ChildKey childKey, Node node2, Index index2) {
        if (this.index.equals(KeyIndex.getInstance()) || this.index.equals(index2)) {
            ensureIndexed();
            if (Objects.equal(this.indexed, FALLBACK_INDEX)) {
                return this.node.getPredecessorChildKey(childKey);
            }
            NamedNode predecessorEntry = this.indexed.getPredecessorEntry(new NamedNode(childKey, node2));
            if (predecessorEntry != null) {
                return predecessorEntry.getName();
            }
            return null;
        }
        throw new IllegalArgumentException("Index not available in IndexedNode!");
    }
}

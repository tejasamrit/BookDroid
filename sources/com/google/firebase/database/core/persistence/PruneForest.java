package com.google.firebase.database.core.persistence;

import com.google.firebase.database.collection.ImmutableSortedMap;
import com.google.firebase.database.core.Path;
import com.google.firebase.database.core.utilities.ImmutableTree;
import com.google.firebase.database.core.utilities.Predicate;
import com.google.firebase.database.snapshot.ChildKey;
import java.util.Set;

public class PruneForest {
    private static final Predicate<Boolean> KEEP_PREDICATE = new Predicate<Boolean>() {
        public boolean evaluate(Boolean bool) {
            return !bool.booleanValue();
        }
    };
    private static final ImmutableTree<Boolean> KEEP_TREE = new ImmutableTree<>(false);
    private static final Predicate<Boolean> PRUNE_PREDICATE = new Predicate<Boolean>() {
        public boolean evaluate(Boolean bool) {
            return bool.booleanValue();
        }
    };
    private static final ImmutableTree<Boolean> PRUNE_TREE = new ImmutableTree<>(true);
    private final ImmutableTree<Boolean> pruneForest;

    public PruneForest() {
        this.pruneForest = ImmutableTree.emptyInstance();
    }

    private PruneForest(ImmutableTree<Boolean> immutableTree) {
        this.pruneForest = immutableTree;
    }

    public boolean prunesAnything() {
        return this.pruneForest.containsMatchingValue(PRUNE_PREDICATE);
    }

    public boolean shouldPruneUnkeptDescendants(Path path) {
        Boolean leafMostValue = this.pruneForest.leafMostValue(path);
        return leafMostValue != null && leafMostValue.booleanValue();
    }

    public boolean shouldKeep(Path path) {
        Boolean leafMostValue = this.pruneForest.leafMostValue(path);
        return leafMostValue != null && !leafMostValue.booleanValue();
    }

    public boolean affectsPath(Path path) {
        return this.pruneForest.rootMostValue(path) != null || !this.pruneForest.subtree(path).isEmpty();
    }

    public PruneForest child(ChildKey childKey) {
        ImmutableTree<Boolean> child = this.pruneForest.getChild(childKey);
        if (child == null) {
            child = new ImmutableTree<>(this.pruneForest.getValue());
        } else if (child.getValue() == null && this.pruneForest.getValue() != null) {
            child = child.set(Path.getEmptyPath(), this.pruneForest.getValue());
        }
        return new PruneForest(child);
    }

    public PruneForest child(Path path) {
        if (path.isEmpty()) {
            return this;
        }
        return child(path.getFront()).child(path.popFront());
    }

    public <T> T foldKeptNodes(T t, final ImmutableTree.TreeVisitor<Void, T> treeVisitor) {
        return this.pruneForest.fold(t, new ImmutableTree.TreeVisitor<Boolean, T>() {
            public T onNodeValue(Path path, Boolean bool, T t) {
                return !bool.booleanValue() ? treeVisitor.onNodeValue(path, null, t) : t;
            }
        });
    }

    public PruneForest prune(Path path) {
        if (this.pruneForest.rootMostValueMatching(path, KEEP_PREDICATE) != null) {
            throw new IllegalArgumentException("Can't prune path that was kept previously!");
        } else if (this.pruneForest.rootMostValueMatching(path, PRUNE_PREDICATE) != null) {
            return this;
        } else {
            return new PruneForest(this.pruneForest.setTree(path, PRUNE_TREE));
        }
    }

    public PruneForest keep(Path path) {
        if (this.pruneForest.rootMostValueMatching(path, KEEP_PREDICATE) != null) {
            return this;
        }
        return new PruneForest(this.pruneForest.setTree(path, KEEP_TREE));
    }

    public PruneForest keepAll(Path path, Set<ChildKey> set) {
        if (this.pruneForest.rootMostValueMatching(path, KEEP_PREDICATE) != null) {
            return this;
        }
        return doAll(path, set, KEEP_TREE);
    }

    public PruneForest pruneAll(Path path, Set<ChildKey> set) {
        if (this.pruneForest.rootMostValueMatching(path, KEEP_PREDICATE) != null) {
            throw new IllegalArgumentException("Can't prune path that was kept previously!");
        } else if (this.pruneForest.rootMostValueMatching(path, PRUNE_PREDICATE) != null) {
            return this;
        } else {
            return doAll(path, set, PRUNE_TREE);
        }
    }

    private PruneForest doAll(Path path, Set<ChildKey> set, ImmutableTree<Boolean> immutableTree) {
        ImmutableTree subtree = this.pruneForest.subtree(path);
        ImmutableSortedMap<K, V> children = subtree.getChildren();
        for (ChildKey insert : set) {
            children = children.insert(insert, immutableTree);
        }
        return new PruneForest(this.pruneForest.setTree(path, new ImmutableTree((Boolean) subtree.getValue(), children)));
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof PruneForest) && this.pruneForest.equals(((PruneForest) obj).pruneForest);
    }

    public int hashCode() {
        return this.pruneForest.hashCode();
    }

    public String toString() {
        return "{PruneForest:" + this.pruneForest.toString() + "}";
    }
}

package com.google.firebase.database.core.utilities;

import com.google.firebase.database.collection.ImmutableSortedMap;
import com.google.firebase.database.collection.StandardComparator;
import com.google.firebase.database.core.Path;
import com.google.firebase.database.snapshot.ChildKey;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

public final class ImmutableTree<T> implements Iterable<Map.Entry<Path, T>> {
    private static final ImmutableTree EMPTY;
    private static final ImmutableSortedMap EMPTY_CHILDREN;
    private final ImmutableSortedMap<ChildKey, ImmutableTree<T>> children;
    private final T value;

    public interface TreeVisitor<T, R> {
        R onNodeValue(Path path, T t, R r);
    }

    static {
        ImmutableSortedMap<K, V> emptyMap = ImmutableSortedMap.Builder.emptyMap(StandardComparator.getComparator(ChildKey.class));
        EMPTY_CHILDREN = emptyMap;
        EMPTY = new ImmutableTree((Object) null, emptyMap);
    }

    public static <V> ImmutableTree<V> emptyInstance() {
        return EMPTY;
    }

    public ImmutableTree(T t, ImmutableSortedMap<ChildKey, ImmutableTree<T>> immutableSortedMap) {
        this.value = t;
        this.children = immutableSortedMap;
    }

    public ImmutableTree(T t) {
        this(t, EMPTY_CHILDREN);
    }

    public T getValue() {
        return this.value;
    }

    public ImmutableSortedMap<ChildKey, ImmutableTree<T>> getChildren() {
        return this.children;
    }

    public boolean isEmpty() {
        return this.value == null && this.children.isEmpty();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0017, code lost:
        r0 = r4.getFront();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.google.firebase.database.core.Path findRootMostMatchingPath(com.google.firebase.database.core.Path r4, com.google.firebase.database.core.utilities.Predicate<? super T> r5) {
        /*
            r3 = this;
            T r0 = r3.value
            if (r0 == 0) goto L_0x000f
            boolean r0 = r5.evaluate(r0)
            if (r0 == 0) goto L_0x000f
            com.google.firebase.database.core.Path r4 = com.google.firebase.database.core.Path.getEmptyPath()
            return r4
        L_0x000f:
            boolean r0 = r4.isEmpty()
            r1 = 0
            if (r0 == 0) goto L_0x0017
            return r1
        L_0x0017:
            com.google.firebase.database.snapshot.ChildKey r0 = r4.getFront()
            com.google.firebase.database.collection.ImmutableSortedMap<com.google.firebase.database.snapshot.ChildKey, com.google.firebase.database.core.utilities.ImmutableTree<T>> r2 = r3.children
            java.lang.Object r2 = r2.get(r0)
            com.google.firebase.database.core.utilities.ImmutableTree r2 = (com.google.firebase.database.core.utilities.ImmutableTree) r2
            if (r2 == 0) goto L_0x003f
            com.google.firebase.database.core.Path r4 = r4.popFront()
            com.google.firebase.database.core.Path r4 = r2.findRootMostMatchingPath(r4, r5)
            if (r4 == 0) goto L_0x003f
            com.google.firebase.database.core.Path r5 = new com.google.firebase.database.core.Path
            r1 = 1
            com.google.firebase.database.snapshot.ChildKey[] r1 = new com.google.firebase.database.snapshot.ChildKey[r1]
            r2 = 0
            r1[r2] = r0
            r5.<init>((com.google.firebase.database.snapshot.ChildKey[]) r1)
            com.google.firebase.database.core.Path r4 = r5.child((com.google.firebase.database.core.Path) r4)
            return r4
        L_0x003f:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.database.core.utilities.ImmutableTree.findRootMostMatchingPath(com.google.firebase.database.core.Path, com.google.firebase.database.core.utilities.Predicate):com.google.firebase.database.core.Path");
    }

    public Path findRootMostPathWithValue(Path path) {
        return findRootMostMatchingPath(path, Predicate.TRUE);
    }

    public T rootMostValue(Path path) {
        return rootMostValueMatching(path, Predicate.TRUE);
    }

    public T rootMostValueMatching(Path path, Predicate<? super T> predicate) {
        T t = this.value;
        if (t != null && predicate.evaluate(t)) {
            return this.value;
        }
        Iterator<ChildKey> it = path.iterator();
        ImmutableTree immutableTree = this;
        while (it.hasNext() && (immutableTree = immutableTree.children.get(it.next())) != null) {
            T t2 = immutableTree.value;
            if (t2 != null && predicate.evaluate(t2)) {
                return immutableTree.value;
            }
        }
        return null;
    }

    public T leafMostValue(Path path) {
        return leafMostValueMatching(path, Predicate.TRUE);
    }

    public T leafMostValueMatching(Path path, Predicate<? super T> predicate) {
        T t = this.value;
        T t2 = (t == null || !predicate.evaluate(t)) ? null : this.value;
        Iterator<ChildKey> it = path.iterator();
        ImmutableTree immutableTree = this;
        while (it.hasNext() && (immutableTree = immutableTree.children.get(it.next())) != null) {
            T t3 = immutableTree.value;
            if (t3 != null && predicate.evaluate(t3)) {
                t2 = immutableTree.value;
            }
        }
        return t2;
    }

    public boolean containsMatchingValue(Predicate<? super T> predicate) {
        T t = this.value;
        if (t != null && predicate.evaluate(t)) {
            return true;
        }
        Iterator<Map.Entry<ChildKey, ImmutableTree<T>>> it = this.children.iterator();
        while (it.hasNext()) {
            if (((ImmutableTree) it.next().getValue()).containsMatchingValue(predicate)) {
                return true;
            }
        }
        return false;
    }

    public ImmutableTree<T> getChild(ChildKey childKey) {
        ImmutableTree<T> immutableTree = this.children.get(childKey);
        if (immutableTree != null) {
            return immutableTree;
        }
        return emptyInstance();
    }

    public ImmutableTree<T> subtree(Path path) {
        if (path.isEmpty()) {
            return this;
        }
        ImmutableTree immutableTree = this.children.get(path.getFront());
        if (immutableTree != null) {
            return immutableTree.subtree(path.popFront());
        }
        return emptyInstance();
    }

    public ImmutableTree<T> set(Path path, T t) {
        if (path.isEmpty()) {
            return new ImmutableTree<>(t, this.children);
        }
        ChildKey front = path.getFront();
        ImmutableTree immutableTree = this.children.get(front);
        if (immutableTree == null) {
            immutableTree = emptyInstance();
        }
        return new ImmutableTree<>(this.value, this.children.insert(front, immutableTree.set(path.popFront(), t)));
    }

    public ImmutableTree<T> remove(Path path) {
        ImmutableSortedMap<ChildKey, ImmutableTree<T>> immutableSortedMap;
        if (!path.isEmpty()) {
            ChildKey front = path.getFront();
            ImmutableTree immutableTree = this.children.get(front);
            if (immutableTree == null) {
                return this;
            }
            ImmutableTree remove = immutableTree.remove(path.popFront());
            if (remove.isEmpty()) {
                immutableSortedMap = this.children.remove(front);
            } else {
                immutableSortedMap = this.children.insert(front, remove);
            }
            if (this.value != null || !immutableSortedMap.isEmpty()) {
                return new ImmutableTree<>(this.value, immutableSortedMap);
            }
            return emptyInstance();
        } else if (this.children.isEmpty()) {
            return emptyInstance();
        } else {
            return new ImmutableTree<>((Object) null, this.children);
        }
    }

    public T get(Path path) {
        if (path.isEmpty()) {
            return this.value;
        }
        ImmutableTree immutableTree = this.children.get(path.getFront());
        if (immutableTree != null) {
            return immutableTree.get(path.popFront());
        }
        return null;
    }

    public ImmutableTree<T> setTree(Path path, ImmutableTree<T> immutableTree) {
        ImmutableSortedMap<ChildKey, ImmutableTree<T>> immutableSortedMap;
        if (path.isEmpty()) {
            return immutableTree;
        }
        ChildKey front = path.getFront();
        ImmutableTree immutableTree2 = this.children.get(front);
        if (immutableTree2 == null) {
            immutableTree2 = emptyInstance();
        }
        ImmutableTree<T> tree = immutableTree2.setTree(path.popFront(), immutableTree);
        if (tree.isEmpty()) {
            immutableSortedMap = this.children.remove(front);
        } else {
            immutableSortedMap = this.children.insert(front, tree);
        }
        return new ImmutableTree<>(this.value, immutableSortedMap);
    }

    public void foreach(TreeVisitor<T, Void> treeVisitor) {
        fold(Path.getEmptyPath(), treeVisitor, (Object) null);
    }

    public <R> R fold(R r, TreeVisitor<? super T, R> treeVisitor) {
        return fold(Path.getEmptyPath(), treeVisitor, r);
    }

    private <R> R fold(Path path, TreeVisitor<? super T, R> treeVisitor, R r) {
        Iterator<Map.Entry<ChildKey, ImmutableTree<T>>> it = this.children.iterator();
        while (it.hasNext()) {
            Map.Entry next = it.next();
            r = ((ImmutableTree) next.getValue()).fold(path.child((ChildKey) next.getKey()), treeVisitor, r);
        }
        T t = this.value;
        return t != null ? treeVisitor.onNodeValue(path, t, r) : r;
    }

    public Collection<T> values() {
        final ArrayList arrayList = new ArrayList();
        foreach(new TreeVisitor<T, Void>() {
            public Void onNodeValue(Path path, T t, Void voidR) {
                arrayList.add(t);
                return null;
            }
        });
        return arrayList;
    }

    public Iterator<Map.Entry<Path, T>> iterator() {
        final ArrayList arrayList = new ArrayList();
        foreach(new TreeVisitor<T, Void>() {
            public Void onNodeValue(Path path, T t, Void voidR) {
                arrayList.add(new AbstractMap.SimpleImmutableEntry(path, t));
                return null;
            }
        });
        return arrayList.iterator();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ImmutableTree { value=");
        sb.append(getValue());
        sb.append(", children={");
        Iterator<Map.Entry<ChildKey, ImmutableTree<T>>> it = this.children.iterator();
        while (it.hasNext()) {
            Map.Entry next = it.next();
            sb.append(((ChildKey) next.getKey()).asString());
            sb.append("=");
            sb.append(next.getValue());
        }
        sb.append("} }");
        return sb.toString();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        ImmutableTree immutableTree = (ImmutableTree) obj;
        ImmutableSortedMap<ChildKey, ImmutableTree<T>> immutableSortedMap = this.children;
        if (immutableSortedMap == null ? immutableTree.children != null : !immutableSortedMap.equals(immutableTree.children)) {
            return false;
        }
        T t = this.value;
        T t2 = immutableTree.value;
        return t == null ? t2 == null : t.equals(t2);
    }

    public int hashCode() {
        T t = this.value;
        int i = 0;
        int hashCode = (t != null ? t.hashCode() : 0) * 31;
        ImmutableSortedMap<ChildKey, ImmutableTree<T>> immutableSortedMap = this.children;
        if (immutableSortedMap != null) {
            i = immutableSortedMap.hashCode();
        }
        return hashCode + i;
    }
}

package com.google.firebase.database.snapshot;

import com.google.firebase.database.collection.ImmutableSortedMap;
import com.google.firebase.database.collection.LLRBNode;
import com.google.firebase.database.core.Path;
import com.google.firebase.database.core.utilities.Utilities;
import com.google.firebase.database.snapshot.Node;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class ChildrenNode implements Node {
    public static Comparator<ChildKey> NAME_ONLY_COMPARATOR = new Comparator<ChildKey>() {
        public int compare(ChildKey childKey, ChildKey childKey2) {
            return childKey.compareTo(childKey2);
        }
    };
    private final ImmutableSortedMap<ChildKey, Node> children;
    private String lazyHash;
    private final Node priority;

    public boolean isLeafNode() {
        return false;
    }

    private static class NamedNodeIterator implements Iterator<NamedNode> {
        private final Iterator<Map.Entry<ChildKey, Node>> iterator;

        public NamedNodeIterator(Iterator<Map.Entry<ChildKey, Node>> it) {
            this.iterator = it;
        }

        public boolean hasNext() {
            return this.iterator.hasNext();
        }

        public NamedNode next() {
            Map.Entry next = this.iterator.next();
            return new NamedNode((ChildKey) next.getKey(), (Node) next.getValue());
        }

        public void remove() {
            this.iterator.remove();
        }
    }

    public static abstract class ChildVisitor extends LLRBNode.NodeVisitor<ChildKey, Node> {
        public abstract void visitChild(ChildKey childKey, Node node);

        public void visitEntry(ChildKey childKey, Node node) {
            visitChild(childKey, node);
        }
    }

    protected ChildrenNode() {
        this.lazyHash = null;
        this.children = ImmutableSortedMap.Builder.emptyMap(NAME_ONLY_COMPARATOR);
        this.priority = PriorityUtilities.NullPriority();
    }

    protected ChildrenNode(ImmutableSortedMap<ChildKey, Node> immutableSortedMap, Node node) {
        this.lazyHash = null;
        if (!immutableSortedMap.isEmpty() || node.isEmpty()) {
            this.priority = node;
            this.children = immutableSortedMap;
            return;
        }
        throw new IllegalArgumentException("Can't create empty ChildrenNode with priority!");
    }

    public boolean hasChild(ChildKey childKey) {
        return !getImmediateChild(childKey).isEmpty();
    }

    public boolean isEmpty() {
        return this.children.isEmpty();
    }

    public int getChildCount() {
        return this.children.size();
    }

    public Object getValue() {
        return getValue(false);
    }

    public Object getValue(boolean z) {
        Integer tryParseInt;
        if (isEmpty()) {
            return null;
        }
        HashMap hashMap = new HashMap();
        Iterator<Map.Entry<ChildKey, Node>> it = this.children.iterator();
        int i = 0;
        boolean z2 = true;
        int i2 = 0;
        while (it.hasNext()) {
            Map.Entry next = it.next();
            String asString = ((ChildKey) next.getKey()).asString();
            hashMap.put(asString, ((Node) next.getValue()).getValue(z));
            i++;
            if (z2) {
                if ((asString.length() > 1 && asString.charAt(0) == '0') || (tryParseInt = Utilities.tryParseInt(asString)) == null || tryParseInt.intValue() < 0) {
                    z2 = false;
                } else if (tryParseInt.intValue() > i2) {
                    i2 = tryParseInt.intValue();
                }
            }
        }
        if (z || !z2 || i2 >= i * 2) {
            if (z && !this.priority.isEmpty()) {
                hashMap.put(".priority", this.priority.getValue());
            }
            return hashMap;
        }
        ArrayList arrayList = new ArrayList(i2 + 1);
        for (int i3 = 0; i3 <= i2; i3++) {
            arrayList.add(hashMap.get("" + i3));
        }
        return arrayList;
    }

    public ChildKey getPredecessorChildKey(ChildKey childKey) {
        return this.children.getPredecessorKey(childKey);
    }

    public ChildKey getSuccessorChildKey(ChildKey childKey) {
        return this.children.getSuccessorKey(childKey);
    }

    public String getHashRepresentation(Node.HashVersion hashVersion) {
        boolean z;
        if (hashVersion == Node.HashVersion.V1) {
            StringBuilder sb = new StringBuilder();
            if (!this.priority.isEmpty()) {
                sb.append("priority:");
                sb.append(this.priority.getHashRepresentation(Node.HashVersion.V1));
                sb.append(":");
            }
            ArrayList<NamedNode> arrayList = new ArrayList<>();
            Iterator<NamedNode> it = iterator();
            loop0:
            while (true) {
                z = false;
                while (true) {
                    if (!it.hasNext()) {
                        break loop0;
                    }
                    NamedNode next = it.next();
                    arrayList.add(next);
                    if (z || !next.getNode().getPriority().isEmpty()) {
                        z = true;
                    }
                }
            }
            if (z) {
                Collections.sort(arrayList, PriorityIndex.getInstance());
            }
            for (NamedNode namedNode : arrayList) {
                String hash = namedNode.getNode().getHash();
                if (!hash.equals("")) {
                    sb.append(":");
                    sb.append(namedNode.getName().asString());
                    sb.append(":");
                    sb.append(hash);
                }
            }
            return sb.toString();
        }
        throw new IllegalArgumentException("Hashes on children nodes only supported for V1");
    }

    public String getHash() {
        if (this.lazyHash == null) {
            String hashRepresentation = getHashRepresentation(Node.HashVersion.V1);
            this.lazyHash = hashRepresentation.isEmpty() ? "" : Utilities.sha1HexDigest(hashRepresentation);
        }
        return this.lazyHash;
    }

    public Node getPriority() {
        return this.priority;
    }

    public Node updatePriority(Node node) {
        if (this.children.isEmpty()) {
            return EmptyNode.Empty();
        }
        return new ChildrenNode(this.children, node);
    }

    public Node getImmediateChild(ChildKey childKey) {
        if (childKey.isPriorityChildName() && !this.priority.isEmpty()) {
            return this.priority;
        }
        if (this.children.containsKey(childKey)) {
            return this.children.get(childKey);
        }
        return EmptyNode.Empty();
    }

    public Node getChild(Path path) {
        ChildKey front = path.getFront();
        if (front == null) {
            return this;
        }
        return getImmediateChild(front).getChild(path.popFront());
    }

    public void forEachChild(ChildVisitor childVisitor) {
        forEachChild(childVisitor, false);
    }

    public void forEachChild(final ChildVisitor childVisitor, boolean z) {
        if (!z || getPriority().isEmpty()) {
            this.children.inOrderTraversal(childVisitor);
        } else {
            this.children.inOrderTraversal(new LLRBNode.NodeVisitor<ChildKey, Node>() {
                boolean passedPriorityKey = false;

                public void visitEntry(ChildKey childKey, Node node) {
                    if (!this.passedPriorityKey && childKey.compareTo(ChildKey.getPriorityKey()) > 0) {
                        this.passedPriorityKey = true;
                        childVisitor.visitChild(ChildKey.getPriorityKey(), ChildrenNode.this.getPriority());
                    }
                    childVisitor.visitChild(childKey, node);
                }
            });
        }
    }

    public ChildKey getFirstChildKey() {
        return this.children.getMinKey();
    }

    public ChildKey getLastChildKey() {
        return this.children.getMaxKey();
    }

    public Node updateChild(Path path, Node node) {
        ChildKey front = path.getFront();
        if (front == null) {
            return node;
        }
        if (!front.isPriorityChildName()) {
            return updateImmediateChild(front, getImmediateChild(front).updateChild(path.popFront(), node));
        }
        Utilities.hardAssert(PriorityUtilities.isValidPriority(node));
        return updatePriority(node);
    }

    public Iterator<NamedNode> iterator() {
        return new NamedNodeIterator(this.children.iterator());
    }

    public Iterator<NamedNode> reverseIterator() {
        return new NamedNodeIterator(this.children.reverseIterator());
    }

    public Node updateImmediateChild(ChildKey childKey, Node node) {
        if (childKey.isPriorityChildName()) {
            return updatePriority(node);
        }
        ImmutableSortedMap<ChildKey, Node> immutableSortedMap = this.children;
        if (immutableSortedMap.containsKey(childKey)) {
            immutableSortedMap = immutableSortedMap.remove(childKey);
        }
        if (!node.isEmpty()) {
            immutableSortedMap = immutableSortedMap.insert(childKey, node);
        }
        if (immutableSortedMap.isEmpty()) {
            return EmptyNode.Empty();
        }
        return new ChildrenNode(immutableSortedMap, this.priority);
    }

    public int compareTo(Node node) {
        if (isEmpty()) {
            return node.isEmpty() ? 0 : -1;
        }
        if (!node.isLeafNode() && !node.isEmpty()) {
            return node == Node.MAX_NODE ? -1 : 0;
        }
        return 1;
    }

    /* JADX WARNING: Removed duplicated region for block: B:20:0x0045  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean equals(java.lang.Object r8) {
        /*
            r7 = this;
            r0 = 0
            if (r8 != 0) goto L_0x0004
            return r0
        L_0x0004:
            r1 = 1
            if (r8 != r7) goto L_0x0008
            return r1
        L_0x0008:
            boolean r2 = r8 instanceof com.google.firebase.database.snapshot.ChildrenNode
            if (r2 != 0) goto L_0x000d
            return r0
        L_0x000d:
            com.google.firebase.database.snapshot.ChildrenNode r8 = (com.google.firebase.database.snapshot.ChildrenNode) r8
            com.google.firebase.database.snapshot.Node r2 = r7.getPriority()
            com.google.firebase.database.snapshot.Node r3 = r8.getPriority()
            boolean r2 = r2.equals(r3)
            if (r2 != 0) goto L_0x001e
            return r0
        L_0x001e:
            com.google.firebase.database.collection.ImmutableSortedMap<com.google.firebase.database.snapshot.ChildKey, com.google.firebase.database.snapshot.Node> r2 = r7.children
            int r2 = r2.size()
            com.google.firebase.database.collection.ImmutableSortedMap<com.google.firebase.database.snapshot.ChildKey, com.google.firebase.database.snapshot.Node> r3 = r8.children
            int r3 = r3.size()
            if (r2 == r3) goto L_0x002d
            return r0
        L_0x002d:
            com.google.firebase.database.collection.ImmutableSortedMap<com.google.firebase.database.snapshot.ChildKey, com.google.firebase.database.snapshot.Node> r2 = r7.children
            java.util.Iterator r2 = r2.iterator()
            com.google.firebase.database.collection.ImmutableSortedMap<com.google.firebase.database.snapshot.ChildKey, com.google.firebase.database.snapshot.Node> r8 = r8.children
            java.util.Iterator r8 = r8.iterator()
        L_0x0039:
            boolean r3 = r2.hasNext()
            if (r3 == 0) goto L_0x0072
            boolean r3 = r8.hasNext()
            if (r3 == 0) goto L_0x0072
            java.lang.Object r3 = r2.next()
            java.util.Map$Entry r3 = (java.util.Map.Entry) r3
            java.lang.Object r4 = r8.next()
            java.util.Map$Entry r4 = (java.util.Map.Entry) r4
            java.lang.Object r5 = r3.getKey()
            com.google.firebase.database.snapshot.ChildKey r5 = (com.google.firebase.database.snapshot.ChildKey) r5
            java.lang.Object r6 = r4.getKey()
            boolean r5 = r5.equals(r6)
            if (r5 == 0) goto L_0x0071
            java.lang.Object r3 = r3.getValue()
            com.google.firebase.database.snapshot.Node r3 = (com.google.firebase.database.snapshot.Node) r3
            java.lang.Object r4 = r4.getValue()
            boolean r3 = r3.equals(r4)
            if (r3 != 0) goto L_0x0039
        L_0x0071:
            return r0
        L_0x0072:
            boolean r0 = r2.hasNext()
            if (r0 != 0) goto L_0x007f
            boolean r8 = r8.hasNext()
            if (r8 != 0) goto L_0x007f
            return r1
        L_0x007f:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r0 = "Something went wrong internally."
            r8.<init>(r0)
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.database.snapshot.ChildrenNode.equals(java.lang.Object):boolean");
    }

    public int hashCode() {
        Iterator<NamedNode> it = iterator();
        int i = 0;
        while (it.hasNext()) {
            NamedNode next = it.next();
            i = (((i * 31) + next.getName().hashCode()) * 17) + next.getNode().hashCode();
        }
        return i;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        toString(sb, 0);
        return sb.toString();
    }

    private static void addIndentation(StringBuilder sb, int i) {
        for (int i2 = 0; i2 < i; i2++) {
            sb.append(" ");
        }
    }

    private void toString(StringBuilder sb, int i) {
        if (!this.children.isEmpty() || !this.priority.isEmpty()) {
            sb.append("{\n");
            Iterator<Map.Entry<ChildKey, Node>> it = this.children.iterator();
            while (it.hasNext()) {
                Map.Entry next = it.next();
                int i2 = i + 2;
                addIndentation(sb, i2);
                sb.append(((ChildKey) next.getKey()).asString());
                sb.append("=");
                if (next.getValue() instanceof ChildrenNode) {
                    ((ChildrenNode) next.getValue()).toString(sb, i2);
                } else {
                    sb.append(((Node) next.getValue()).toString());
                }
                sb.append("\n");
            }
            if (!this.priority.isEmpty()) {
                addIndentation(sb, i + 2);
                sb.append(".priority=");
                sb.append(this.priority.toString());
                sb.append("\n");
            }
            addIndentation(sb, i);
            sb.append("}");
            return;
        }
        sb.append("{ }");
    }
}

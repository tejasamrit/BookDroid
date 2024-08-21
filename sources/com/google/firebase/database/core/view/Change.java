package com.google.firebase.database.core.view;

import com.google.firebase.database.core.view.Event;
import com.google.firebase.database.snapshot.ChildKey;
import com.google.firebase.database.snapshot.IndexedNode;
import com.google.firebase.database.snapshot.Node;

public class Change {
    private final ChildKey childKey;
    private final Event.EventType eventType;
    private final IndexedNode indexedNode;
    private final IndexedNode oldIndexedNode;
    private final ChildKey prevName;

    private Change(Event.EventType eventType2, IndexedNode indexedNode2, ChildKey childKey2, ChildKey childKey3, IndexedNode indexedNode3) {
        this.eventType = eventType2;
        this.indexedNode = indexedNode2;
        this.childKey = childKey2;
        this.prevName = childKey3;
        this.oldIndexedNode = indexedNode3;
    }

    public static Change valueChange(IndexedNode indexedNode2) {
        return new Change(Event.EventType.VALUE, indexedNode2, (ChildKey) null, (ChildKey) null, (IndexedNode) null);
    }

    public static Change childAddedChange(ChildKey childKey2, Node node) {
        return childAddedChange(childKey2, IndexedNode.from(node));
    }

    public static Change childAddedChange(ChildKey childKey2, IndexedNode indexedNode2) {
        return new Change(Event.EventType.CHILD_ADDED, indexedNode2, childKey2, (ChildKey) null, (IndexedNode) null);
    }

    public static Change childRemovedChange(ChildKey childKey2, Node node) {
        return childRemovedChange(childKey2, IndexedNode.from(node));
    }

    public static Change childRemovedChange(ChildKey childKey2, IndexedNode indexedNode2) {
        return new Change(Event.EventType.CHILD_REMOVED, indexedNode2, childKey2, (ChildKey) null, (IndexedNode) null);
    }

    public static Change childChangedChange(ChildKey childKey2, Node node, Node node2) {
        return childChangedChange(childKey2, IndexedNode.from(node), IndexedNode.from(node2));
    }

    public static Change childChangedChange(ChildKey childKey2, IndexedNode indexedNode2, IndexedNode indexedNode3) {
        return new Change(Event.EventType.CHILD_CHANGED, indexedNode2, childKey2, (ChildKey) null, indexedNode3);
    }

    public static Change childMovedChange(ChildKey childKey2, Node node) {
        return childMovedChange(childKey2, IndexedNode.from(node));
    }

    public static Change childMovedChange(ChildKey childKey2, IndexedNode indexedNode2) {
        return new Change(Event.EventType.CHILD_MOVED, indexedNode2, childKey2, (ChildKey) null, (IndexedNode) null);
    }

    public Change changeWithPrevName(ChildKey childKey2) {
        return new Change(this.eventType, this.indexedNode, this.childKey, childKey2, this.oldIndexedNode);
    }

    public ChildKey getChildKey() {
        return this.childKey;
    }

    public Event.EventType getEventType() {
        return this.eventType;
    }

    public IndexedNode getIndexedNode() {
        return this.indexedNode;
    }

    public ChildKey getPrevName() {
        return this.prevName;
    }

    public IndexedNode getOldIndexedNode() {
        return this.oldIndexedNode;
    }

    public String toString() {
        return "Change: " + this.eventType + " " + this.childKey;
    }
}

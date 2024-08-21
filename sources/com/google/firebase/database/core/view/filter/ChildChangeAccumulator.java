package com.google.firebase.database.core.view.filter;

import com.google.firebase.database.core.utilities.Utilities;
import com.google.firebase.database.core.view.Change;
import com.google.firebase.database.core.view.Event;
import com.google.firebase.database.snapshot.ChildKey;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChildChangeAccumulator {
    private final Map<ChildKey, Change> changeMap = new HashMap();

    public void trackChildChange(Change change) {
        Event.EventType eventType = change.getEventType();
        ChildKey childKey = change.getChildKey();
        Utilities.hardAssert(eventType == Event.EventType.CHILD_ADDED || eventType == Event.EventType.CHILD_CHANGED || eventType == Event.EventType.CHILD_REMOVED, "Only child changes supported for tracking");
        Utilities.hardAssert(!change.getChildKey().isPriorityChildName());
        if (this.changeMap.containsKey(childKey)) {
            Change change2 = this.changeMap.get(childKey);
            Event.EventType eventType2 = change2.getEventType();
            if (eventType == Event.EventType.CHILD_ADDED && eventType2 == Event.EventType.CHILD_REMOVED) {
                this.changeMap.put(change.getChildKey(), Change.childChangedChange(childKey, change.getIndexedNode(), change2.getIndexedNode()));
            } else if (eventType == Event.EventType.CHILD_REMOVED && eventType2 == Event.EventType.CHILD_ADDED) {
                this.changeMap.remove(childKey);
            } else if (eventType == Event.EventType.CHILD_REMOVED && eventType2 == Event.EventType.CHILD_CHANGED) {
                this.changeMap.put(childKey, Change.childRemovedChange(childKey, change2.getOldIndexedNode()));
            } else if (eventType == Event.EventType.CHILD_CHANGED && eventType2 == Event.EventType.CHILD_ADDED) {
                this.changeMap.put(childKey, Change.childAddedChange(childKey, change.getIndexedNode()));
            } else if (eventType == Event.EventType.CHILD_CHANGED && eventType2 == Event.EventType.CHILD_CHANGED) {
                this.changeMap.put(childKey, Change.childChangedChange(childKey, change.getIndexedNode(), change2.getOldIndexedNode()));
            } else {
                throw new IllegalStateException("Illegal combination of changes: " + change + " occurred after " + change2);
            }
        } else {
            this.changeMap.put(change.getChildKey(), change);
        }
    }

    public List<Change> getChanges() {
        return new ArrayList(this.changeMap.values());
    }
}

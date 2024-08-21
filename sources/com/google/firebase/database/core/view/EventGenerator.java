package com.google.firebase.database.core.view;

import com.google.firebase.database.core.EventRegistration;
import com.google.firebase.database.core.utilities.Utilities;
import com.google.firebase.database.core.view.Event;
import com.google.firebase.database.snapshot.Index;
import com.google.firebase.database.snapshot.IndexedNode;
import com.google.firebase.database.snapshot.NamedNode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class EventGenerator {
    /* access modifiers changed from: private */
    public final Index index;
    private final QuerySpec query;

    public EventGenerator(QuerySpec querySpec) {
        this.query = querySpec;
        this.index = querySpec.getIndex();
    }

    private void generateEventsForType(List<DataEvent> list, Event.EventType eventType, List<Change> list2, List<EventRegistration> list3, IndexedNode indexedNode) {
        ArrayList<Change> arrayList = new ArrayList<>();
        for (Change next : list2) {
            if (next.getEventType().equals(eventType)) {
                arrayList.add(next);
            }
        }
        Collections.sort(arrayList, changeComparator());
        for (Change change : arrayList) {
            for (EventRegistration next2 : list3) {
                if (next2.respondsTo(eventType)) {
                    list.add(generateEvent(change, next2, indexedNode));
                }
            }
        }
    }

    private DataEvent generateEvent(Change change, EventRegistration eventRegistration, IndexedNode indexedNode) {
        if (!change.getEventType().equals(Event.EventType.VALUE) && !change.getEventType().equals(Event.EventType.CHILD_REMOVED)) {
            change = change.changeWithPrevName(indexedNode.getPredecessorChildName(change.getChildKey(), change.getIndexedNode().getNode(), this.index));
        }
        return eventRegistration.createEvent(change, this.query);
    }

    public List<DataEvent> generateEventsForChanges(List<Change> list, IndexedNode indexedNode, List<EventRegistration> list2) {
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        for (Change next : list) {
            if (next.getEventType().equals(Event.EventType.CHILD_CHANGED) && this.index.indexedValueChanged(next.getOldIndexedNode().getNode(), next.getIndexedNode().getNode())) {
                arrayList2.add(Change.childMovedChange(next.getChildKey(), next.getIndexedNode()));
            }
        }
        ArrayList arrayList3 = arrayList;
        List<Change> list3 = list;
        List<EventRegistration> list4 = list2;
        IndexedNode indexedNode2 = indexedNode;
        generateEventsForType(arrayList3, Event.EventType.CHILD_REMOVED, list3, list4, indexedNode2);
        generateEventsForType(arrayList3, Event.EventType.CHILD_ADDED, list3, list4, indexedNode2);
        generateEventsForType(arrayList3, Event.EventType.CHILD_MOVED, arrayList2, list4, indexedNode2);
        List<Change> list5 = list;
        generateEventsForType(arrayList3, Event.EventType.CHILD_CHANGED, list5, list4, indexedNode2);
        generateEventsForType(arrayList3, Event.EventType.VALUE, list5, list4, indexedNode2);
        return arrayList;
    }

    private Comparator<Change> changeComparator() {
        return new Comparator<Change>() {
            public int compare(Change change, Change change2) {
                Utilities.hardAssert((change.getChildKey() == null || change2.getChildKey() == null) ? false : true);
                return EventGenerator.this.index.compare(new NamedNode(change.getChildKey(), change.getIndexedNode().getNode()), new NamedNode(change2.getChildKey(), change2.getIndexedNode().getNode()));
            }
        };
    }
}

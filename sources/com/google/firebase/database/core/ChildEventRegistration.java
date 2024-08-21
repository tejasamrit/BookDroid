package com.google.firebase.database.core;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.InternalHelpers;
import com.google.firebase.database.core.view.Change;
import com.google.firebase.database.core.view.DataEvent;
import com.google.firebase.database.core.view.Event;
import com.google.firebase.database.core.view.QuerySpec;

public class ChildEventRegistration extends EventRegistration {
    private final ChildEventListener eventListener;
    private final Repo repo;
    private final QuerySpec spec;

    public String toString() {
        return "ChildEventRegistration";
    }

    public ChildEventRegistration(Repo repo2, ChildEventListener childEventListener, QuerySpec querySpec) {
        this.repo = repo2;
        this.eventListener = childEventListener;
        this.spec = querySpec;
    }

    public boolean respondsTo(Event.EventType eventType) {
        return eventType != Event.EventType.VALUE;
    }

    public boolean equals(Object obj) {
        if (obj instanceof ChildEventRegistration) {
            ChildEventRegistration childEventRegistration = (ChildEventRegistration) obj;
            return childEventRegistration.eventListener.equals(this.eventListener) && childEventRegistration.repo.equals(this.repo) && childEventRegistration.spec.equals(this.spec);
        }
    }

    public int hashCode() {
        return (((this.eventListener.hashCode() * 31) + this.repo.hashCode()) * 31) + this.spec.hashCode();
    }

    public DataEvent createEvent(Change change, QuerySpec querySpec) {
        return new DataEvent(change.getEventType(), this, InternalHelpers.createDataSnapshot(InternalHelpers.createReference(this.repo, querySpec.getPath().child(change.getChildKey())), change.getIndexedNode()), change.getPrevName() != null ? change.getPrevName().asString() : null);
    }

    public void fireEvent(DataEvent dataEvent) {
        if (!isZombied()) {
            int i = C17671.f314x916339dc[dataEvent.getEventType().ordinal()];
            if (i == 1) {
                this.eventListener.onChildAdded(dataEvent.getSnapshot(), dataEvent.getPreviousName());
            } else if (i == 2) {
                this.eventListener.onChildChanged(dataEvent.getSnapshot(), dataEvent.getPreviousName());
            } else if (i == 3) {
                this.eventListener.onChildMoved(dataEvent.getSnapshot(), dataEvent.getPreviousName());
            } else if (i == 4) {
                this.eventListener.onChildRemoved(dataEvent.getSnapshot());
            }
        }
    }

    /* renamed from: com.google.firebase.database.core.ChildEventRegistration$1 */
    static /* synthetic */ class C17671 {

        /* renamed from: $SwitchMap$com$google$firebase$database$core$view$Event$EventType */
        static final /* synthetic */ int[] f314x916339dc;

        /* JADX WARNING: Can't wrap try/catch for region: R(8:0|1|2|3|4|5|6|(3:7|8|10)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        static {
            /*
                com.google.firebase.database.core.view.Event$EventType[] r0 = com.google.firebase.database.core.view.Event.EventType.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                f314x916339dc = r0
                com.google.firebase.database.core.view.Event$EventType r1 = com.google.firebase.database.core.view.Event.EventType.CHILD_ADDED     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = f314x916339dc     // Catch:{ NoSuchFieldError -> 0x001d }
                com.google.firebase.database.core.view.Event$EventType r1 = com.google.firebase.database.core.view.Event.EventType.CHILD_CHANGED     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = f314x916339dc     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.google.firebase.database.core.view.Event$EventType r1 = com.google.firebase.database.core.view.Event.EventType.CHILD_MOVED     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = f314x916339dc     // Catch:{ NoSuchFieldError -> 0x0033 }
                com.google.firebase.database.core.view.Event$EventType r1 = com.google.firebase.database.core.view.Event.EventType.CHILD_REMOVED     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.database.core.ChildEventRegistration.C17671.<clinit>():void");
        }
    }

    public void fireCancelEvent(DatabaseError databaseError) {
        this.eventListener.onCancelled(databaseError);
    }

    public EventRegistration clone(QuerySpec querySpec) {
        return new ChildEventRegistration(this.repo, this.eventListener, querySpec);
    }

    public boolean isSameListener(EventRegistration eventRegistration) {
        return (eventRegistration instanceof ChildEventRegistration) && ((ChildEventRegistration) eventRegistration).eventListener.equals(this.eventListener);
    }

    public QuerySpec getQuerySpec() {
        return this.spec;
    }

    /* access modifiers changed from: package-private */
    public Repo getRepo() {
        return this.repo;
    }
}

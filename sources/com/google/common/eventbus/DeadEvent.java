package com.google.common.eventbus;

import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import com.google.firebase.messaging.Constants;

public class DeadEvent {
    private final Object event;
    private final Object source;

    public DeadEvent(Object obj, Object obj2) {
        this.source = Preconditions.checkNotNull(obj);
        this.event = Preconditions.checkNotNull(obj2);
    }

    public Object getSource() {
        return this.source;
    }

    public Object getEvent() {
        return this.event;
    }

    public String toString() {
        return MoreObjects.toStringHelper((Object) this).add(Constants.ScionAnalytics.PARAM_SOURCE, this.source).add("event", this.event).toString();
    }
}

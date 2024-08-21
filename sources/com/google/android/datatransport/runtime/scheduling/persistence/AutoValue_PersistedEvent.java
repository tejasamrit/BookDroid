package com.google.android.datatransport.runtime.scheduling.persistence;

import com.google.android.datatransport.runtime.EventInternal;
import com.google.android.datatransport.runtime.TransportContext;
import java.util.Objects;

final class AutoValue_PersistedEvent extends PersistedEvent {
    private final EventInternal event;

    /* renamed from: id */
    private final long f138id;
    private final TransportContext transportContext;

    AutoValue_PersistedEvent(long j, TransportContext transportContext2, EventInternal eventInternal) {
        this.f138id = j;
        Objects.requireNonNull(transportContext2, "Null transportContext");
        this.transportContext = transportContext2;
        Objects.requireNonNull(eventInternal, "Null event");
        this.event = eventInternal;
    }

    public long getId() {
        return this.f138id;
    }

    public TransportContext getTransportContext() {
        return this.transportContext;
    }

    public EventInternal getEvent() {
        return this.event;
    }

    public String toString() {
        return "PersistedEvent{id=" + this.f138id + ", transportContext=" + this.transportContext + ", event=" + this.event + "}";
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof PersistedEvent)) {
            return false;
        }
        PersistedEvent persistedEvent = (PersistedEvent) obj;
        if (this.f138id != persistedEvent.getId() || !this.transportContext.equals(persistedEvent.getTransportContext()) || !this.event.equals(persistedEvent.getEvent())) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        long j = this.f138id;
        return this.event.hashCode() ^ ((((((int) (j ^ (j >>> 32))) ^ 1000003) * 1000003) ^ this.transportContext.hashCode()) * 1000003);
    }
}

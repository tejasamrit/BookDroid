package com.google.firebase.database.core.utilities;

public class OffsetClock implements Clock {
    private final Clock baseClock;
    private long offset = 0;

    public OffsetClock(Clock clock, long j) {
        this.baseClock = clock;
        this.offset = j;
    }

    public void setOffset(long j) {
        this.offset = j;
    }

    public long millis() {
        return this.baseClock.millis() + this.offset;
    }
}

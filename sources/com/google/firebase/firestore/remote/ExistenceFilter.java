package com.google.firebase.firestore.remote;

public final class ExistenceFilter {
    private final int count;

    public ExistenceFilter(int i) {
        this.count = i;
    }

    public int getCount() {
        return this.count;
    }

    public String toString() {
        return "ExistenceFilter{count=" + this.count + '}';
    }
}

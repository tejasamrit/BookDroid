package com.firebase.geofire;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;

final class EventListenerBridge implements GeoQueryDataEventListener {
    private final GeoQueryEventListener listener;

    public void onDataChanged(DataSnapshot dataSnapshot, GeoLocation geoLocation) {
    }

    public EventListenerBridge(GeoQueryEventListener geoQueryEventListener) {
        this.listener = geoQueryEventListener;
    }

    public void onDataEntered(DataSnapshot dataSnapshot, GeoLocation geoLocation) {
        this.listener.onKeyEntered(dataSnapshot.getKey(), geoLocation);
    }

    public void onDataExited(DataSnapshot dataSnapshot) {
        this.listener.onKeyExited(dataSnapshot.getKey());
    }

    public void onDataMoved(DataSnapshot dataSnapshot, GeoLocation geoLocation) {
        this.listener.onKeyMoved(dataSnapshot.getKey(), geoLocation);
    }

    public void onGeoQueryReady() {
        this.listener.onGeoQueryReady();
    }

    public void onGeoQueryError(DatabaseError databaseError) {
        this.listener.onGeoQueryError(databaseError);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return this.listener.equals(((EventListenerBridge) obj).listener);
    }

    public int hashCode() {
        return this.listener.hashCode();
    }
}

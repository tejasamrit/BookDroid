package com.firebase.geofire;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;

public interface GeoQueryDataEventListener {
    void onDataChanged(DataSnapshot dataSnapshot, GeoLocation geoLocation);

    void onDataEntered(DataSnapshot dataSnapshot, GeoLocation geoLocation);

    void onDataExited(DataSnapshot dataSnapshot);

    void onDataMoved(DataSnapshot dataSnapshot, GeoLocation geoLocation);

    void onGeoQueryError(DatabaseError databaseError);

    void onGeoQueryReady();
}

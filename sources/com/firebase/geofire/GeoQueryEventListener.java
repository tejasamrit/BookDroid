package com.firebase.geofire;

import com.google.firebase.database.DatabaseError;

public interface GeoQueryEventListener {
    void onGeoQueryError(DatabaseError databaseError);

    void onGeoQueryReady();

    void onKeyEntered(String str, GeoLocation geoLocation);

    void onKeyExited(String str);

    void onKeyMoved(String str, GeoLocation geoLocation);
}

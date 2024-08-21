package com.firebase.geofire;

import com.google.firebase.database.DatabaseError;

public interface LocationCallback {
    void onCancelled(DatabaseError databaseError);

    void onLocationResult(String str, GeoLocation geoLocation);
}

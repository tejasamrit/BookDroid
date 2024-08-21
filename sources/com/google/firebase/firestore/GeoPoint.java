package com.google.firebase.firestore;

import com.google.firebase.firestore.util.Util;

public class GeoPoint implements Comparable<GeoPoint> {
    private final double latitude;
    private final double longitude;

    public GeoPoint(double d, double d2) {
        if (Double.isNaN(d) || d < -90.0d || d > 90.0d) {
            throw new IllegalArgumentException("Latitude must be in the range of [-90, 90]");
        } else if (Double.isNaN(d2) || d2 < -180.0d || d2 > 180.0d) {
            throw new IllegalArgumentException("Longitude must be in the range of [-180, 180]");
        } else {
            this.latitude = d;
            this.longitude = d2;
        }
    }

    public double getLatitude() {
        return this.latitude;
    }

    public double getLongitude() {
        return this.longitude;
    }

    public int compareTo(GeoPoint geoPoint) {
        int compareDoubles = Util.compareDoubles(this.latitude, geoPoint.latitude);
        return compareDoubles == 0 ? Util.compareDoubles(this.longitude, geoPoint.longitude) : compareDoubles;
    }

    public String toString() {
        return "GeoPoint { latitude=" + this.latitude + ", longitude=" + this.longitude + " }";
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof GeoPoint)) {
            return false;
        }
        GeoPoint geoPoint = (GeoPoint) obj;
        if (this.latitude == geoPoint.latitude && this.longitude == geoPoint.longitude) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        long doubleToLongBits = Double.doubleToLongBits(this.latitude);
        long doubleToLongBits2 = Double.doubleToLongBits(this.longitude);
        return (((int) (doubleToLongBits ^ (doubleToLongBits >>> 32))) * 31) + ((int) (doubleToLongBits2 ^ (doubleToLongBits2 >>> 32)));
    }
}

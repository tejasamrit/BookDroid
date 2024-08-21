package com.firebase.geofire;

public final class GeoLocation {
    public final double latitude;
    public final double longitude;

    public static boolean coordinatesValid(double d, double d2) {
        return d >= -90.0d && d <= 90.0d && d2 >= -180.0d && d2 <= 180.0d;
    }

    public GeoLocation(double d, double d2) {
        if (coordinatesValid(d, d2)) {
            this.latitude = d;
            this.longitude = d2;
            return;
        }
        throw new IllegalArgumentException("Not a valid geo location: " + d + ", " + d2);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        GeoLocation geoLocation = (GeoLocation) obj;
        return Double.compare(geoLocation.latitude, this.latitude) == 0 && Double.compare(geoLocation.longitude, this.longitude) == 0;
    }

    public int hashCode() {
        long doubleToLongBits = Double.doubleToLongBits(this.latitude);
        long doubleToLongBits2 = Double.doubleToLongBits(this.longitude);
        return (((int) (doubleToLongBits ^ (doubleToLongBits >>> 32))) * 31) + ((int) (doubleToLongBits2 ^ (doubleToLongBits2 >>> 32)));
    }

    public String toString() {
        return "GeoLocation(" + this.latitude + ", " + this.longitude + ")";
    }
}

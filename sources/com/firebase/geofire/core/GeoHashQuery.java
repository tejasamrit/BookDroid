package com.firebase.geofire.core;

import com.firebase.geofire.GeoLocation;
import com.firebase.geofire.util.Base32Utils;
import com.firebase.geofire.util.GeoUtils;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class GeoHashQuery {
    private final String endValue;
    private final String startValue;

    public static final class Utils {
        private Utils() {
            throw new AssertionError("No instances.");
        }

        public static double bitsLatitude(double d) {
            return Math.min(Math.log(2.000393E7d / d) / Math.log(2.0d), 110.0d);
        }

        public static double bitsLongitude(double d, double d2) {
            double distanceToLongitudeDegrees = GeoUtils.distanceToLongitudeDegrees(d, d2);
            if (Math.abs(distanceToLongitudeDegrees) > 0.0d) {
                return Math.max(1.0d, Math.log(360.0d / distanceToLongitudeDegrees) / Math.log(2.0d));
            }
            return 1.0d;
        }

        public static int bitsForBoundingBox(GeoLocation geoLocation, double d) {
            double distanceToLatitudeDegrees = GeoUtils.distanceToLatitudeDegrees(d);
            return Math.min(((int) Math.floor(bitsLatitude(d))) * 2, Math.min((((int) Math.floor(bitsLongitude(d, Math.min(90.0d, geoLocation.latitude + distanceToLatitudeDegrees)))) * 2) - 1, (((int) Math.floor(bitsLongitude(d, Math.max(-90.0d, geoLocation.latitude - distanceToLatitudeDegrees)))) * 2) - 1));
        }
    }

    public GeoHashQuery(String str, String str2) {
        this.startValue = str;
        this.endValue = str2;
    }

    public static GeoHashQuery queryForGeoHash(GeoHash geoHash, int i) {
        String str;
        String geoHashString = geoHash.getGeoHashString();
        int ceil = (int) Math.ceil(((double) i) / 5.0d);
        if (geoHashString.length() < ceil) {
            return new GeoHashQuery(geoHashString, geoHashString + "~");
        }
        String substring = geoHashString.substring(0, ceil);
        String substring2 = substring.substring(0, substring.length() - 1);
        int base32CharToValue = Base32Utils.base32CharToValue(substring.charAt(substring.length() - 1));
        int length = 5 - (i - (substring2.length() * 5));
        int i2 = (base32CharToValue >> length) << length;
        int i3 = (1 << length) + i2;
        String str2 = substring2 + Base32Utils.valueToBase32Char(i2);
        if (i3 > 31) {
            str = substring2 + "~";
        } else {
            str = substring2 + Base32Utils.valueToBase32Char(i3);
        }
        return new GeoHashQuery(str2, str);
    }

    public static Set<GeoHashQuery> queriesAtLocation(GeoLocation geoLocation, double d) {
        boolean z;
        GeoLocation geoLocation2 = geoLocation;
        double d2 = d;
        int max = Math.max(1, Utils.bitsForBoundingBox(geoLocation, d));
        double d3 = geoLocation2.latitude;
        double d4 = geoLocation2.longitude;
        double d5 = d2 / 110574.0d;
        int ceil = (int) Math.ceil((double) (((float) max) / 5.0f));
        double min = Math.min(90.0d, d3 + d5);
        double max2 = Math.max(-90.0d, d3 - d5);
        double max3 = Math.max(GeoUtils.distanceToLongitudeDegrees(d2, min), GeoUtils.distanceToLongitudeDegrees(d2, max2));
        HashSet<GeoHashQuery> hashSet = new HashSet<>();
        double d6 = max2;
        double d7 = d3;
        double d8 = d4;
        GeoHash geoHash = r7;
        GeoHash geoHash2 = new GeoHash(d7, d4, ceil);
        double d9 = d8 - max3;
        GeoHash geoHash3 = r7;
        GeoHash geoHash4 = new GeoHash(d7, GeoUtils.wrapLongitude(d9), ceil);
        double d10 = d8 + max3;
        GeoHash geoHash5 = r7;
        int i = ceil;
        GeoHash geoHash6 = new GeoHash(d7, GeoUtils.wrapLongitude(d10), i);
        double d11 = min;
        GeoHash geoHash7 = new GeoHash(d11, d8, i);
        GeoHash geoHash8 = r7;
        GeoHash geoHash9 = new GeoHash(d11, GeoUtils.wrapLongitude(d9), ceil);
        GeoHash geoHash10 = r7;
        int i2 = ceil;
        GeoHash geoHash11 = new GeoHash(d11, GeoUtils.wrapLongitude(d10), i2);
        double d12 = d6;
        GeoHash geoHash12 = new GeoHash(d12, d8, i2);
        GeoHash geoHash13 = r7;
        GeoHash geoHash14 = new GeoHash(d12, GeoUtils.wrapLongitude(d9), ceil);
        double wrapLongitude = GeoUtils.wrapLongitude(d10);
        GeoHash geoHash15 = r7;
        GeoHash geoHash16 = new GeoHash(d12, wrapLongitude, ceil);
        hashSet.add(queryForGeoHash(geoHash, max));
        hashSet.add(queryForGeoHash(geoHash5, max));
        hashSet.add(queryForGeoHash(geoHash3, max));
        hashSet.add(queryForGeoHash(geoHash7, max));
        hashSet.add(queryForGeoHash(geoHash10, max));
        hashSet.add(queryForGeoHash(geoHash8, max));
        hashSet.add(queryForGeoHash(geoHash12, max));
        hashSet.add(queryForGeoHash(geoHash15, max));
        hashSet.add(queryForGeoHash(geoHash13, max));
        do {
            GeoHashQuery geoHashQuery = null;
            GeoHashQuery geoHashQuery2 = null;
            for (GeoHashQuery geoHashQuery3 : hashSet) {
                Iterator it = hashSet.iterator();
                while (true) {
                    if (it.hasNext()) {
                        GeoHashQuery geoHashQuery4 = (GeoHashQuery) it.next();
                        if (geoHashQuery3 != geoHashQuery4 && geoHashQuery3.canJoinWith(geoHashQuery4)) {
                            geoHashQuery = geoHashQuery3;
                            geoHashQuery2 = geoHashQuery4;
                            break;
                        }
                    } else {
                        break;
                    }
                }
            }
            if (geoHashQuery == null || geoHashQuery2 == null) {
                z = false;
                continue;
            } else {
                hashSet.remove(geoHashQuery);
                hashSet.remove(geoHashQuery2);
                hashSet.add(geoHashQuery.joinWith(geoHashQuery2));
                z = true;
                continue;
            }
        } while (z);
        return hashSet;
    }

    private boolean isPrefix(GeoHashQuery geoHashQuery) {
        return geoHashQuery.endValue.compareTo(this.startValue) >= 0 && geoHashQuery.startValue.compareTo(this.startValue) < 0 && geoHashQuery.endValue.compareTo(this.endValue) < 0;
    }

    private boolean isSuperQuery(GeoHashQuery geoHashQuery) {
        return geoHashQuery.startValue.compareTo(this.startValue) <= 0 && geoHashQuery.endValue.compareTo(this.endValue) >= 0;
    }

    public boolean canJoinWith(GeoHashQuery geoHashQuery) {
        return isPrefix(geoHashQuery) || geoHashQuery.isPrefix(this) || isSuperQuery(geoHashQuery) || geoHashQuery.isSuperQuery(this);
    }

    public GeoHashQuery joinWith(GeoHashQuery geoHashQuery) {
        if (geoHashQuery.isPrefix(this)) {
            return new GeoHashQuery(this.startValue, geoHashQuery.endValue);
        }
        if (isPrefix(geoHashQuery)) {
            return new GeoHashQuery(geoHashQuery.startValue, this.endValue);
        }
        if (isSuperQuery(geoHashQuery)) {
            return geoHashQuery;
        }
        if (geoHashQuery.isSuperQuery(this)) {
            return this;
        }
        throw new IllegalArgumentException("Can't join these 2 queries: " + this + ", " + geoHashQuery);
    }

    public boolean containsGeoHash(GeoHash geoHash) {
        String geoHashString = geoHash.getGeoHashString();
        return this.startValue.compareTo(geoHashString) <= 0 && this.endValue.compareTo(geoHashString) > 0;
    }

    public String getStartValue() {
        return this.startValue;
    }

    public String getEndValue() {
        return this.endValue;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        GeoHashQuery geoHashQuery = (GeoHashQuery) obj;
        return this.endValue.equals(geoHashQuery.endValue) && this.startValue.equals(geoHashQuery.startValue);
    }

    public int hashCode() {
        return (this.startValue.hashCode() * 31) + this.endValue.hashCode();
    }

    public String toString() {
        return "GeoHashQuery{startValue='" + this.startValue + '\'' + ", endValue='" + this.endValue + '\'' + '}';
    }
}

package com.firebase.geofire.core;

import com.firebase.geofire.GeoLocation;
import com.firebase.geofire.util.Base32Utils;
import java.util.Locale;

public class GeoHash {
    private static final int DEFAULT_PRECISION = 10;
    public static final int MAX_PRECISION = 22;
    public static final int MAX_PRECISION_BITS = 110;
    private final String geoHash;

    public GeoHash(double d, double d2) {
        this(d, d2, 10);
    }

    public GeoHash(GeoLocation geoLocation) {
        this(geoLocation.latitude, geoLocation.longitude, 10);
    }

    public GeoHash(double d, double d2, int i) {
        int i2 = i;
        if (i2 < 1) {
            throw new IllegalArgumentException("Precision of GeoHash must be larger than zero!");
        } else if (i2 > 22) {
            throw new IllegalArgumentException("Precision of a GeoHash must be less than 23!");
        } else if (GeoLocation.coordinatesValid(d, d2)) {
            double[] dArr = {-180.0d, 180.0d};
            double[] dArr2 = {-90.0d, 90.0d};
            char[] cArr = new char[i2];
            for (int i3 = 0; i3 < i2; i3++) {
                int i4 = 0;
                for (int i5 = 0; i5 < 5; i5++) {
                    boolean z = ((i3 * 5) + i5) % 2 == 0;
                    double d3 = z ? d2 : d;
                    double[] dArr3 = z ? dArr : dArr2;
                    double d4 = (dArr3[0] + dArr3[1]) / 2.0d;
                    if (d3 > d4) {
                        i4 = (i4 << 1) + 1;
                        dArr3[0] = d4;
                    } else {
                        i4 <<= 1;
                        dArr3[1] = d4;
                    }
                }
                cArr[i3] = Base32Utils.valueToBase32Char(i4);
            }
            this.geoHash = new String(cArr);
        } else {
            throw new IllegalArgumentException(String.format(Locale.US, "Not valid location coordinates: [%f, %f]", new Object[]{Double.valueOf(d), Double.valueOf(d2)}));
        }
    }

    public GeoHash(String str) {
        if (str.length() == 0 || !Base32Utils.isValidBase32String(str)) {
            throw new IllegalArgumentException("Not a valid geoHash: " + str);
        }
        this.geoHash = str;
    }

    public String getGeoHashString() {
        return this.geoHash;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return this.geoHash.equals(((GeoHash) obj).geoHash);
    }

    public String toString() {
        return "GeoHash{geoHash='" + this.geoHash + '\'' + '}';
    }

    public int hashCode() {
        return this.geoHash.hashCode();
    }
}

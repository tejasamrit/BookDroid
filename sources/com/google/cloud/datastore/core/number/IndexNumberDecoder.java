package com.google.cloud.datastore.core.number;

public class IndexNumberDecoder {
    private String doubleResultRepProblemMessage;
    private String longResultRepProblemMessage;
    private double resultAsDouble;
    private long resultAsLong;
    private int resultExponent;
    private boolean resultNegative;
    private long resultSignificand;

    private static long decodeTrailingSignificandByte(int i, int i2) {
        return ((long) (i & 254)) << (i2 - 1);
    }

    public IndexNumberDecoder() {
        reset();
    }

    public void reset() {
        this.longResultRepProblemMessage = "No bytes decoded.";
        this.doubleResultRepProblemMessage = "No bytes decoded.";
    }

    public boolean isResultLong() {
        updateResultLongState();
        return this.longResultRepProblemMessage.isEmpty();
    }

    public long resultAsLong() {
        updateResultLongState();
        if (this.longResultRepProblemMessage.isEmpty()) {
            return this.resultAsLong;
        }
        throw new IllegalArgumentException(this.longResultRepProblemMessage);
    }

    public boolean isResultDouble() {
        updateResultDoubleState();
        return this.doubleResultRepProblemMessage.isEmpty();
    }

    private void updateResultLongState() {
        if (this.longResultRepProblemMessage == null) {
            this.longResultRepProblemMessage = "";
            int i = this.resultExponent;
            if (i == Integer.MAX_VALUE) {
                if (this.resultSignificand != 0) {
                    this.longResultRepProblemMessage = "NaN is not an integer.";
                } else if (this.resultNegative) {
                    this.longResultRepProblemMessage = "+Infinity is not an integer.";
                } else {
                    this.longResultRepProblemMessage = "-Infinity is not an integer.";
                }
            } else if (i == Integer.MIN_VALUE && this.resultSignificand == 0) {
                this.resultAsLong = 0;
            } else if (i < 0) {
                this.longResultRepProblemMessage = "Number is not an integer.";
            } else if (i >= 64) {
                this.longResultRepProblemMessage = "Number is outside the long range.";
            } else if (i != 63) {
                int numberOfTrailingZeros = 64 - Long.numberOfTrailingZeros(this.resultSignificand);
                int i2 = this.resultExponent;
                if (i2 < numberOfTrailingZeros) {
                    this.longResultRepProblemMessage = "Number is not an integer.";
                    return;
                }
                long j = (1 << i2) ^ (this.resultSignificand >>> ((63 - i2) + 1));
                if (this.resultNegative) {
                    j = -j;
                }
                this.resultAsLong = j;
            } else if (this.resultSignificand != 0 || !this.resultNegative) {
                this.longResultRepProblemMessage = "Number is outside the long range.";
            } else {
                this.resultAsLong = Long.MIN_VALUE;
            }
        }
    }

    public double resultAsDouble() {
        updateResultDoubleState();
        if (this.doubleResultRepProblemMessage.isEmpty()) {
            return this.resultAsDouble;
        }
        throw new IllegalArgumentException(this.doubleResultRepProblemMessage);
    }

    private void updateResultDoubleState() {
        if (this.doubleResultRepProblemMessage == null) {
            this.doubleResultRepProblemMessage = "";
            int i = this.resultExponent;
            long j = 0;
            if (i == Integer.MAX_VALUE) {
                if (this.resultSignificand != 0) {
                    this.resultAsDouble = Double.NaN;
                } else if (this.resultNegative) {
                    this.resultAsDouble = Double.NEGATIVE_INFINITY;
                } else {
                    this.resultAsDouble = Double.POSITIVE_INFINITY;
                }
            } else if (i == Integer.MIN_VALUE && this.resultSignificand == 0) {
                this.resultAsDouble = 0.0d;
            } else if (64 - Long.numberOfTrailingZeros(this.resultSignificand) > 52) {
                this.doubleResultRepProblemMessage = "Number has too many significant bits for a double.";
            } else {
                long j2 = this.resultSignificand >>> 12;
                this.resultSignificand = j2;
                int i2 = this.resultExponent;
                if (i2 >= -1022) {
                    this.resultExponent = i2 + 1023;
                } else {
                    int i3 = -1022 - i2;
                    long j3 = j2 >>> i3;
                    this.resultSignificand = j3;
                    if ((j3 << i3) != j2) {
                        this.doubleResultRepProblemMessage = "Number has too many significant bits for a subnormal double.";
                    }
                    this.resultSignificand = (1 << (52 - i3)) | j3;
                    this.resultExponent = 0;
                }
                long j4 = (((long) this.resultExponent) << 52) | this.resultSignificand;
                if (this.resultNegative) {
                    j = Long.MIN_VALUE;
                }
                this.resultAsDouble = Double.longBitsToDouble(j4 | j);
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:58:0x010e  */
    /* JADX WARNING: Removed duplicated region for block: B:66:0x0134  */
    /* JADX WARNING: Removed duplicated region for block: B:67:0x0136  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int decode(boolean r20, byte[] r21, int r22) {
        /*
            r19 = this;
            r0 = r19
            int r1 = r22 + 1
            byte r2 = r21[r22]
            r3 = 255(0xff, float:3.57E-43)
            r2 = r2 & r3
            r4 = r2 & 128(0x80, float:1.794E-43)
            r6 = 1
            if (r4 != 0) goto L_0x0010
            r4 = 1
            goto L_0x0011
        L_0x0010:
            r4 = 0
        L_0x0011:
            if (r4 == 0) goto L_0x0016
            r7 = 255(0xff, float:3.57E-43)
            goto L_0x0017
        L_0x0016:
            r7 = 0
        L_0x0017:
            r2 = r2 ^ r7
            r8 = r4 ^ r20
            r9 = r2 & 64
            if (r9 != 0) goto L_0x0020
            r9 = 1
            goto L_0x0021
        L_0x0020:
            r9 = 0
        L_0x0021:
            if (r9 == 0) goto L_0x0026
            r10 = 255(0xff, float:3.57E-43)
            goto L_0x0027
        L_0x0026:
            r10 = 0
        L_0x0027:
            r11 = r2 ^ r10
            int r12 = decodeMarker(r11)
            r13 = -4
            r14 = 0
            if (r12 == r13) goto L_0x0102
            r13 = -3
            r5 = -1
            if (r12 == r13) goto L_0x00e7
            r13 = -2
            if (r12 == r13) goto L_0x00e7
            if (r12 == r5) goto L_0x00e7
            if (r12 == r6) goto L_0x00cd
            r2 = 2
            if (r12 == r2) goto L_0x00a6
            r2 = 3
            if (r12 == r2) goto L_0x0085
            r2 = 6
            java.lang.String r5 = "Invalid encoded byte array"
            if (r12 != r2) goto L_0x007f
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r6 = 2147483647(0x7fffffff, float:NaN)
            if (r4 == 0) goto L_0x0073
            if (r9 == 0) goto L_0x0055
            r0.recordNumber(r8, r2, r14)
            goto L_0x007c
        L_0x0055:
            int r2 = r1 + 1
            byte r1 = r21[r1]
            r1 = r1 & r3
            r3 = 128(0x80, float:1.794E-43)
            if (r1 != r3) goto L_0x0062
            r0.recordNumber(r8, r6, r14)
            goto L_0x006b
        L_0x0062:
            r3 = 96
            if (r1 != r3) goto L_0x006d
            r3 = 1
            r0.recordNumber(r8, r6, r3)
        L_0x006b:
            r1 = r2
            goto L_0x007c
        L_0x006d:
            java.lang.IllegalArgumentException r1 = new java.lang.IllegalArgumentException
            r1.<init>(r5)
            throw r1
        L_0x0073:
            if (r9 == 0) goto L_0x0079
            r0.recordNumber(r8, r2, r14)
            goto L_0x007c
        L_0x0079:
            r0.recordNumber(r8, r6, r14)
        L_0x007c:
            int r1 = r1 - r22
            return r1
        L_0x007f:
            java.lang.IllegalArgumentException r1 = new java.lang.IllegalArgumentException
            r1.<init>(r5)
            throw r1
        L_0x0085:
            r2 = r2 & r11
            int r2 = r2 << 8
            int r4 = r1 + 1
            byte r1 = r21[r1]
            r1 = r1 & r3
            r1 = r1 ^ r7
            r1 = r1 ^ r10
            r1 = r1 | r2
            int r1 = r1 + 148
            int r2 = r4 + 1
            byte r4 = r21[r4]
            r4 = r4 & r3
            r4 = r4 ^ r7
            r5 = 57
            long r10 = decodeTrailingSignificandByte(r4, r5)
            long r14 = r14 | r10
            r10 = r14
            r14 = 57
            r16 = r2
            r2 = r1
            goto L_0x00e4
        L_0x00a6:
            r2 = r11 & 7
            int r2 = r2 << 4
            int r4 = r1 + 1
            byte r1 = r21[r1]
            r1 = r1 & r3
            r1 = r1 ^ r7
            r5 = r1 ^ r10
            int r5 = r5 >>> 4
            r2 = r2 | r5
            int r2 = r2 + 20
            r5 = 60
            long r10 = (long) r1
            r12 = 15
            long r10 = r10 & r12
            long r10 = r10 << r5
            long r10 = r10 | r14
            int r1 = r4 + 1
            byte r4 = r21[r4]
            r4 = r4 & r3
            r4 = r4 ^ r7
            r14 = 53
            long r12 = decodeTrailingSignificandByte(r4, r14)
            long r10 = r10 | r12
            goto L_0x010b
        L_0x00cd:
            r2 = r11 & 15
            int r2 = r2 + 4
            int r4 = r1 + 1
            byte r1 = r21[r1]
            r1 = r1 & r3
            r1 = r1 ^ r7
            r5 = 57
            long r10 = decodeTrailingSignificandByte(r1, r5)
            long r14 = r14 | r10
            r10 = r14
            r14 = 57
        L_0x00e1:
            r16 = r4
            r4 = r1
        L_0x00e4:
            r1 = r16
            goto L_0x010b
        L_0x00e7:
            int r4 = r12 + 4
            int r10 = 64 - r4
            int r11 = r4 + 1
            int r5 = r5 << r11
            int r5 = ~r5
            r5 = r5 & 126(0x7e, float:1.77E-43)
            r5 = r5 & r2
            long r11 = (long) r5
            int r5 = r10 + -1
            long r11 = r11 << r5
            long r14 = r14 | r11
            r16 = r4
            r4 = r2
            r2 = r16
            r17 = r14
            r14 = r10
            r10 = r17
            goto L_0x010b
        L_0x0102:
            if (r9 != 0) goto L_0x013d
            r4 = 64
            r4 = r2
            r10 = r14
            r2 = 0
            r14 = 64
        L_0x010b:
            r4 = r4 & r6
            if (r4 == 0) goto L_0x0134
            int r4 = r1 + 1
            byte r1 = r21[r1]
            r1 = r1 & r3
            r1 = r1 ^ r7
            int r14 = r14 + -7
            if (r14 < 0) goto L_0x011e
            long r12 = decodeTrailingSignificandByte(r1, r14)
            long r10 = r10 | r12
            goto L_0x00e1
        L_0x011e:
            r5 = r1 & 254(0xfe, float:3.56E-43)
            long r12 = (long) r5
            int r14 = r14 + -1
            int r5 = -r14
            long r12 = r12 >>> r5
            long r10 = r10 | r12
            r5 = r1 & 1
            if (r5 != 0) goto L_0x012c
            r14 = 0
            goto L_0x00e1
        L_0x012c:
            java.lang.IllegalArgumentException r1 = new java.lang.IllegalArgumentException
            java.lang.String r2 = "Invalid encoded byte array: overlong sequence"
            r1.<init>(r2)
            throw r1
        L_0x0134:
            if (r9 == 0) goto L_0x0137
            int r2 = -r2
        L_0x0137:
            r0.recordNumber(r8, r2, r10)
            int r1 = r1 - r22
            return r1
        L_0x013d:
            java.lang.IllegalArgumentException r1 = new java.lang.IllegalArgumentException
            java.lang.String r2 = "Invalid encoded number: exponent negative zero is invalid"
            r1.<init>(r2)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.cloud.datastore.core.number.IndexNumberDecoder.decode(boolean, byte[], int):int");
    }

    private void recordNumber(boolean z, int i, long j) {
        this.longResultRepProblemMessage = null;
        this.doubleResultRepProblemMessage = null;
        this.resultNegative = z;
        this.resultExponent = i;
        this.resultSignificand = j;
    }

    static int decodeMarker(int i) {
        boolean z = (i & 32) != 0;
        if (z) {
            i ^= 255;
        }
        int numberOfLeadingZeros = 5 - (31 - Integer.numberOfLeadingZeros(i & 63));
        return z ? numberOfLeadingZeros : -numberOfLeadingZeros;
    }
}

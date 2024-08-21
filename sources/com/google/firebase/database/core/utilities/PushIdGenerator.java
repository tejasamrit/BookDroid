package com.google.firebase.database.core.utilities;

import java.util.Random;

public class PushIdGenerator {
    private static final String PUSH_CHARS = "-0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ_abcdefghijklmnopqrstuvwxyz";
    private static long lastPushTime = 0;
    private static final int[] lastRandChars = new int[12];
    private static final Random randGen = new Random();

    public static synchronized String generatePushChildName(long j) {
        String sb;
        synchronized (PushIdGenerator.class) {
            boolean z = true;
            boolean z2 = j == lastPushTime;
            lastPushTime = j;
            char[] cArr = new char[8];
            StringBuilder sb2 = new StringBuilder(20);
            for (int i = 7; i >= 0; i--) {
                cArr[i] = PUSH_CHARS.charAt((int) (j % 64));
                j /= 64;
            }
            Utilities.hardAssert(j == 0);
            sb2.append(cArr);
            if (!z2) {
                for (int i2 = 0; i2 < 12; i2++) {
                    lastRandChars[i2] = randGen.nextInt(64);
                }
            } else {
                incrementArray();
            }
            for (int i3 = 0; i3 < 12; i3++) {
                sb2.append(PUSH_CHARS.charAt(lastRandChars[i3]));
            }
            if (sb2.length() != 20) {
                z = false;
            }
            Utilities.hardAssert(z);
            sb = sb2.toString();
        }
        return sb;
    }

    private static void incrementArray() {
        int i = 11;
        while (i >= 0) {
            int[] iArr = lastRandChars;
            if (iArr[i] != 63) {
                iArr[i] = iArr[i] + 1;
                return;
            } else {
                iArr[i] = 0;
                i--;
            }
        }
    }
}

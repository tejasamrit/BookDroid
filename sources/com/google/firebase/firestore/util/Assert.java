package com.google.firebase.firestore.util;

public class Assert {
    public static void hardAssert(boolean z, String str, Object... objArr) {
        if (!z) {
            throw fail(str, objArr);
        }
    }

    public static AssertionError fail(String str, Object... objArr) {
        throw new AssertionError(format(str, objArr));
    }

    public static AssertionError fail(Throwable th, String str, Object... objArr) {
        throw ApiUtil.newAssertionError(format(str, objArr), th);
    }

    private static String format(String str, Object... objArr) {
        return "INTERNAL ASSERTION FAILED: " + String.format(str, objArr);
    }
}

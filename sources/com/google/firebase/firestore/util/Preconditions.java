package com.google.firebase.firestore.util;

import java.util.Objects;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class Preconditions {
    public static void checkArgument(boolean z, @Nullable String str, @Nullable Object... objArr) {
        if (!z) {
            throw new IllegalArgumentException(String.format(str, objArr));
        }
    }

    public static <T> T checkNotNull(@Nonnull T t) {
        Objects.requireNonNull(t);
        return t;
    }

    public static <T> T checkNotNull(@Nonnull T t, @Nullable Object obj) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException(String.valueOf(obj));
    }

    public static void checkState(boolean z) {
        if (!z) {
            throw new IllegalStateException();
        }
    }
}

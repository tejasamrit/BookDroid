package com.google.android.gms.internal.auth;

import android.util.Log;
import com.bumptech.glide.load.Key;
import com.google.android.gms.common.server.response.FastSafeParcelableJsonResponse;
import java.io.UnsupportedEncodingException;

public abstract class zzaz extends FastSafeParcelableJsonResponse {
    private static String zzem = "AUTH";

    public byte[] toByteArray() {
        try {
            return toString().getBytes(Key.STRING_CHARSET_NAME);
        } catch (UnsupportedEncodingException e) {
            Log.e(zzem, "Error serializing object.", e);
            return null;
        }
    }
}

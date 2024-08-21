package com.google.firebase.storage.internal;

import android.net.Uri;
import android.text.TextUtils;
import com.google.android.gms.common.internal.Preconditions;

public class Slashes {
    public static String preserveSlashEncode(String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        return slashize(Uri.encode(str));
    }

    public static String slashize(String str) {
        Preconditions.checkNotNull(str);
        return str.replace("%2F", "/");
    }

    public static String normalizeSlashes(String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        if (!str.startsWith("/") && !str.endsWith("/") && !str.contains("//")) {
            return str;
        }
        StringBuilder sb = new StringBuilder();
        for (String str2 : str.split("/", -1)) {
            if (!TextUtils.isEmpty(str2)) {
                if (sb.length() > 0) {
                    sb.append("/");
                    sb.append(str2);
                } else {
                    sb.append(str2);
                }
            }
        }
        return sb.toString();
    }
}

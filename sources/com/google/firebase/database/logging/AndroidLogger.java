package com.google.firebase.database.logging;

import android.util.Log;
import com.google.firebase.database.logging.Logger;
import java.util.List;

public class AndroidLogger extends DefaultLogger {
    /* access modifiers changed from: protected */
    public String buildLogMessage(Logger.Level level, String str, String str2, long j) {
        return str2;
    }

    public AndroidLogger(Logger.Level level, List<String> list) {
        super(level, list);
    }

    /* access modifiers changed from: protected */
    public void error(String str, String str2) {
        Log.e(str, str2);
    }

    /* access modifiers changed from: protected */
    public void warn(String str, String str2) {
        Log.w(str, str2);
    }

    /* access modifiers changed from: protected */
    public void info(String str, String str2) {
        Log.i(str, str2);
    }

    /* access modifiers changed from: protected */
    public void debug(String str, String str2) {
        Log.d(str, str2);
    }
}

package com.google.firebase.database.logging;

import com.google.firebase.database.logging.Logger;
import java.io.PrintWriter;
import java.io.StringWriter;

public class LogWrapper {
    private final String component;
    private final Logger logger;
    private final String prefix;

    private static String exceptionStacktrace(Throwable th) {
        StringWriter stringWriter = new StringWriter();
        th.printStackTrace(new PrintWriter(stringWriter));
        return stringWriter.toString();
    }

    public LogWrapper(Logger logger2, String str) {
        this(logger2, str, (String) null);
    }

    public LogWrapper(Logger logger2, String str, String str2) {
        this.logger = logger2;
        this.component = str;
        this.prefix = str2;
    }

    public void error(String str, Throwable th) {
        this.logger.onLogMessage(Logger.Level.ERROR, this.component, toLog(str, new Object[0]) + "\n" + exceptionStacktrace(th), now());
    }

    public void warn(String str) {
        warn(str, (Throwable) null);
    }

    public void warn(String str, Throwable th) {
        String log = toLog(str, new Object[0]);
        if (th != null) {
            log = log + "\n" + exceptionStacktrace(th);
        }
        this.logger.onLogMessage(Logger.Level.WARN, this.component, log, now());
    }

    public void info(String str) {
        this.logger.onLogMessage(Logger.Level.INFO, this.component, toLog(str, new Object[0]), now());
    }

    public void debug(String str, Object... objArr) {
        debug(str, (Throwable) null, objArr);
    }

    public boolean logsDebug() {
        return this.logger.getLogLevel().ordinal() <= Logger.Level.DEBUG.ordinal();
    }

    public void debug(String str, Throwable th, Object... objArr) {
        if (logsDebug()) {
            String log = toLog(str, objArr);
            if (th != null) {
                log = log + "\n" + exceptionStacktrace(th);
            }
            this.logger.onLogMessage(Logger.Level.DEBUG, this.component, log, now());
        }
    }

    private long now() {
        return System.currentTimeMillis();
    }

    private String toLog(String str, Object... objArr) {
        if (objArr.length > 0) {
            str = String.format(str, objArr);
        }
        if (this.prefix == null) {
            return str;
        }
        return this.prefix + " - " + str;
    }
}

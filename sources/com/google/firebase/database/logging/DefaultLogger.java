package com.google.firebase.database.logging;

import com.google.firebase.database.logging.Logger;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DefaultLogger implements Logger {
    private final Set<String> enabledComponents;
    private final Logger.Level minLevel;

    public DefaultLogger(Logger.Level level, List<String> list) {
        if (list != null) {
            this.enabledComponents = new HashSet(list);
        } else {
            this.enabledComponents = null;
        }
        this.minLevel = level;
    }

    public Logger.Level getLogLevel() {
        return this.minLevel;
    }

    public void onLogMessage(Logger.Level level, String str, String str2, long j) {
        if (shouldLog(level, str)) {
            String buildLogMessage = buildLogMessage(level, str, str2, j);
            int i = C18531.$SwitchMap$com$google$firebase$database$logging$Logger$Level[level.ordinal()];
            if (i == 1) {
                error(str, buildLogMessage);
            } else if (i == 2) {
                warn(str, buildLogMessage);
            } else if (i == 3) {
                info(str, buildLogMessage);
            } else if (i == 4) {
                debug(str, buildLogMessage);
            } else {
                throw new RuntimeException("Should not reach here!");
            }
        }
    }

    /* renamed from: com.google.firebase.database.logging.DefaultLogger$1 */
    static /* synthetic */ class C18531 {
        static final /* synthetic */ int[] $SwitchMap$com$google$firebase$database$logging$Logger$Level;

        /* JADX WARNING: Can't wrap try/catch for region: R(8:0|1|2|3|4|5|6|(3:7|8|10)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        static {
            /*
                com.google.firebase.database.logging.Logger$Level[] r0 = com.google.firebase.database.logging.Logger.Level.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$google$firebase$database$logging$Logger$Level = r0
                com.google.firebase.database.logging.Logger$Level r1 = com.google.firebase.database.logging.Logger.Level.ERROR     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$com$google$firebase$database$logging$Logger$Level     // Catch:{ NoSuchFieldError -> 0x001d }
                com.google.firebase.database.logging.Logger$Level r1 = com.google.firebase.database.logging.Logger.Level.WARN     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$com$google$firebase$database$logging$Logger$Level     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.google.firebase.database.logging.Logger$Level r1 = com.google.firebase.database.logging.Logger.Level.INFO     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = $SwitchMap$com$google$firebase$database$logging$Logger$Level     // Catch:{ NoSuchFieldError -> 0x0033 }
                com.google.firebase.database.logging.Logger$Level r1 = com.google.firebase.database.logging.Logger.Level.DEBUG     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.database.logging.DefaultLogger.C18531.<clinit>():void");
        }
    }

    /* access modifiers changed from: protected */
    public String buildLogMessage(Logger.Level level, String str, String str2, long j) {
        Date date = new Date(j);
        return date.toString() + " [" + level + "] " + str + ": " + str2;
    }

    /* access modifiers changed from: protected */
    public void error(String str, String str2) {
        System.err.println(str2);
    }

    /* access modifiers changed from: protected */
    public void warn(String str, String str2) {
        System.out.println(str2);
    }

    /* access modifiers changed from: protected */
    public void info(String str, String str2) {
        System.out.println(str2);
    }

    /* access modifiers changed from: protected */
    public void debug(String str, String str2) {
        System.out.println(str2);
    }

    /* access modifiers changed from: protected */
    public boolean shouldLog(Logger.Level level, String str) {
        return level.ordinal() >= this.minLevel.ordinal() && (this.enabledComponents == null || level.ordinal() > Logger.Level.DEBUG.ordinal() || this.enabledComponents.contains(str));
    }
}

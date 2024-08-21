package com.google.firebase.database.core;

import android.support.p000v4.media.session.PlaybackStateCompat;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseException;
import com.google.firebase.database.Logger;
import com.google.firebase.database.logging.Logger;
import java.util.List;

public class DatabaseConfig extends Context {
    public synchronized void setLogger(Logger logger) {
        assertUnfrozen();
        this.logger = logger;
    }

    public synchronized void setEventTarget(EventTarget eventTarget) {
        assertUnfrozen();
        this.eventTarget = eventTarget;
    }

    /* renamed from: com.google.firebase.database.core.DatabaseConfig$1 */
    static /* synthetic */ class C17711 {
        static final /* synthetic */ int[] $SwitchMap$com$google$firebase$database$Logger$Level;

        /* JADX WARNING: Can't wrap try/catch for region: R(12:0|1|2|3|4|5|6|7|8|9|10|12) */
        /* JADX WARNING: Code restructure failed: missing block: B:13:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0033 */
        static {
            /*
                com.google.firebase.database.Logger$Level[] r0 = com.google.firebase.database.Logger.Level.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$google$firebase$database$Logger$Level = r0
                com.google.firebase.database.Logger$Level r1 = com.google.firebase.database.Logger.Level.DEBUG     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$com$google$firebase$database$Logger$Level     // Catch:{ NoSuchFieldError -> 0x001d }
                com.google.firebase.database.Logger$Level r1 = com.google.firebase.database.Logger.Level.INFO     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$com$google$firebase$database$Logger$Level     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.google.firebase.database.Logger$Level r1 = com.google.firebase.database.Logger.Level.WARN     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = $SwitchMap$com$google$firebase$database$Logger$Level     // Catch:{ NoSuchFieldError -> 0x0033 }
                com.google.firebase.database.Logger$Level r1 = com.google.firebase.database.Logger.Level.ERROR     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                int[] r0 = $SwitchMap$com$google$firebase$database$Logger$Level     // Catch:{ NoSuchFieldError -> 0x003e }
                com.google.firebase.database.Logger$Level r1 = com.google.firebase.database.Logger.Level.NONE     // Catch:{ NoSuchFieldError -> 0x003e }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x003e }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x003e }
            L_0x003e:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.database.core.DatabaseConfig.C17711.<clinit>():void");
        }
    }

    public synchronized void setLogLevel(Logger.Level level) {
        assertUnfrozen();
        int i = C17711.$SwitchMap$com$google$firebase$database$Logger$Level[level.ordinal()];
        if (i == 1) {
            this.logLevel = Logger.Level.DEBUG;
        } else if (i == 2) {
            this.logLevel = Logger.Level.INFO;
        } else if (i == 3) {
            this.logLevel = Logger.Level.WARN;
        } else if (i == 4) {
            this.logLevel = Logger.Level.ERROR;
        } else if (i == 5) {
            this.logLevel = Logger.Level.NONE;
        } else {
            throw new IllegalArgumentException("Unknown log level: " + level);
        }
    }

    public synchronized void setDebugLogComponents(List<String> list) {
        assertUnfrozen();
        setLogLevel(Logger.Level.DEBUG);
        this.loggedComponents = list;
    }

    public void setRunLoop(RunLoop runLoop) {
        this.runLoop = runLoop;
    }

    public void setAuthTokenProvider(AuthTokenProvider authTokenProvider) {
        this.authTokenProvider = authTokenProvider;
    }

    public synchronized void setSessionPersistenceKey(String str) {
        assertUnfrozen();
        if (str == null || str.isEmpty()) {
            throw new IllegalArgumentException("Session identifier is not allowed to be empty or null!");
        }
        this.persistenceKey = str;
    }

    public synchronized void setPersistenceEnabled(boolean z) {
        assertUnfrozen();
        this.persistenceEnabled = z;
    }

    public synchronized void setPersistenceCacheSizeBytes(long j) {
        assertUnfrozen();
        if (j < PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED) {
            throw new DatabaseException("The minimum cache size must be at least 1MB");
        } else if (j <= 104857600) {
            this.cacheSize = j;
        } else {
            throw new DatabaseException("Firebase Database currently doesn't support a cache size larger than 100MB");
        }
    }

    public synchronized void setFirebaseApp(FirebaseApp firebaseApp) {
        this.firebaseApp = firebaseApp;
    }
}

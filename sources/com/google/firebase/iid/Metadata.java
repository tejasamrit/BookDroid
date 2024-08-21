package com.google.firebase.iid;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.util.Log;
import com.google.firebase.FirebaseApp;

/* compiled from: com.google.firebase:firebase-iid@@21.0.1 */
public class Metadata {
    private String appVersionCode;
    private String appVersionName;
    private final Context context;
    private int gmsVersionCode;
    private int iidImplementation = 0;

    public Metadata(Context context2) {
        this.context = context2;
    }

    public static String getDefaultSenderId(FirebaseApp firebaseApp) {
        String gcmSenderId = firebaseApp.getOptions().getGcmSenderId();
        if (gcmSenderId != null) {
            return gcmSenderId;
        }
        String applicationId = firebaseApp.getOptions().getApplicationId();
        if (!applicationId.startsWith("1:")) {
            return applicationId;
        }
        String[] split = applicationId.split(":");
        if (split.length < 2) {
            return null;
        }
        String str = split[1];
        if (str.isEmpty()) {
            return null;
        }
        return str;
    }

    private PackageInfo getPackageInfo(String str) {
        try {
            return this.context.getPackageManager().getPackageInfo(str, 0);
        } catch (PackageManager.NameNotFoundException e) {
            String valueOf = String.valueOf(e);
            StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 23);
            sb.append("Failed to find package ");
            sb.append(valueOf);
            Log.w("FirebaseInstanceId", sb.toString());
            return null;
        }
    }

    private synchronized void populateAppVersionInfo() {
        PackageInfo packageInfo = getPackageInfo(this.context.getPackageName());
        if (packageInfo != null) {
            this.appVersionCode = Integer.toString(packageInfo.versionCode);
            this.appVersionName = packageInfo.versionName;
        }
    }

    public synchronized String getAppVersionCode() {
        if (this.appVersionCode == null) {
            populateAppVersionInfo();
        }
        return this.appVersionCode;
    }

    public synchronized String getAppVersionName() {
        if (this.appVersionName == null) {
            populateAppVersionInfo();
        }
        return this.appVersionName;
    }

    public synchronized int getGmsVersionCode() {
        PackageInfo packageInfo;
        if (this.gmsVersionCode == 0 && (packageInfo = getPackageInfo("com.google.android.gms")) != null) {
            this.gmsVersionCode = packageInfo.versionCode;
        }
        return this.gmsVersionCode;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:39:0x0078, code lost:
        return r2;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized int getIidImplementation() {
        /*
            r5 = this;
            monitor-enter(r5)
            int r0 = r5.iidImplementation     // Catch:{ all -> 0x0079 }
            if (r0 == 0) goto L_0x0007
            monitor-exit(r5)
            return r0
        L_0x0007:
            android.content.Context r0 = r5.context     // Catch:{ all -> 0x0079 }
            android.content.pm.PackageManager r0 = r0.getPackageManager()     // Catch:{ all -> 0x0079 }
            java.lang.String r1 = "com.google.android.c2dm.permission.SEND"
            java.lang.String r2 = "com.google.android.gms"
            int r1 = r0.checkPermission(r1, r2)     // Catch:{ all -> 0x0079 }
            r2 = -1
            r3 = 0
            if (r1 != r2) goto L_0x0022
            java.lang.String r0 = "FirebaseInstanceId"
            java.lang.String r1 = "Google Play services missing or without correct permission."
            android.util.Log.e(r0, r1)     // Catch:{ all -> 0x0079 }
            monitor-exit(r5)
            return r3
        L_0x0022:
            boolean r1 = com.google.android.gms.common.util.PlatformVersion.isAtLeastO()     // Catch:{ all -> 0x0079 }
            r2 = 1
            if (r1 != 0) goto L_0x0046
            android.content.Intent r1 = new android.content.Intent     // Catch:{ all -> 0x0079 }
            java.lang.String r4 = "com.google.android.c2dm.intent.REGISTER"
            r1.<init>(r4)     // Catch:{ all -> 0x0079 }
            java.lang.String r4 = "com.google.android.gms"
            r1.setPackage(r4)     // Catch:{ all -> 0x0079 }
            java.util.List r1 = r0.queryIntentServices(r1, r3)     // Catch:{ all -> 0x0079 }
            if (r1 == 0) goto L_0x0046
            int r1 = r1.size()     // Catch:{ all -> 0x0079 }
            if (r1 > 0) goto L_0x0042
            goto L_0x0046
        L_0x0042:
            r5.iidImplementation = r2     // Catch:{ all -> 0x0079 }
            monitor-exit(r5)
            return r2
        L_0x0046:
            android.content.Intent r1 = new android.content.Intent     // Catch:{ all -> 0x0079 }
            java.lang.String r4 = "com.google.iid.TOKEN_REQUEST"
            r1.<init>(r4)     // Catch:{ all -> 0x0079 }
            java.lang.String r4 = "com.google.android.gms"
            r1.setPackage(r4)     // Catch:{ all -> 0x0079 }
            java.util.List r0 = r0.queryBroadcastReceivers(r1, r3)     // Catch:{ all -> 0x0079 }
            r1 = 2
            if (r0 == 0) goto L_0x0064
            int r0 = r0.size()     // Catch:{ all -> 0x0079 }
            if (r0 > 0) goto L_0x0060
            goto L_0x0064
        L_0x0060:
            r5.iidImplementation = r1     // Catch:{ all -> 0x0079 }
            monitor-exit(r5)
            return r1
        L_0x0064:
            java.lang.String r0 = "FirebaseInstanceId"
            java.lang.String r3 = "Failed to resolve IID implementation package, falling back"
            android.util.Log.w(r0, r3)     // Catch:{ all -> 0x0079 }
            boolean r0 = com.google.android.gms.common.util.PlatformVersion.isAtLeastO()     // Catch:{ all -> 0x0079 }
            if (r0 == 0) goto L_0x0075
            r5.iidImplementation = r1     // Catch:{ all -> 0x0079 }
            r2 = 2
            goto L_0x0077
        L_0x0075:
            r5.iidImplementation = r2     // Catch:{ all -> 0x0079 }
        L_0x0077:
            monitor-exit(r5)
            return r2
        L_0x0079:
            r0 = move-exception
            monitor-exit(r5)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.iid.Metadata.getIidImplementation():int");
    }

    public boolean isGmscorePresent() {
        return getIidImplementation() != 0;
    }
}

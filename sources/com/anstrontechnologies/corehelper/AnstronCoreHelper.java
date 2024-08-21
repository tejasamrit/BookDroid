package com.anstrontechnologies.corehelper;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.format.DateFormat;
import android.widget.Toast;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

public class AnstronCoreHelper {
    private Context context;

    public AnstronCoreHelper(Context context2) {
        this.context = context2;
    }

    public int getRandomNumber(int i, int i2) {
        return new Random().nextInt((i2 - i) + 1) + i;
    }

    public void createAlert(String str, String str2, String str3, String str4, DialogInterface.OnClickListener onClickListener, DialogInterface.OnClickListener onClickListener2, DialogInterface.OnDismissListener onDismissListener) {
        new AlertDialog.Builder(this.context).setTitle(str).setMessage(str2).setPositiveButton(str3, onClickListener).setNegativeButton(str4, onClickListener2).setOnDismissListener(onDismissListener).create().show();
    }

    public void createAlert(String str, String str2, String str3, String str4, DialogInterface.OnClickListener onClickListener, DialogInterface.OnClickListener onClickListener2) {
        new AlertDialog.Builder(this.context).setTitle(str).setMessage(str2).setPositiveButton(str3, onClickListener).setNegativeButton(str4, onClickListener2).create().show();
    }

    public void createAlert(String str, String str2, String str3, DialogInterface.OnClickListener onClickListener) {
        new AlertDialog.Builder(this.context).setTitle(str).setMessage(str2).setPositiveButton(str3, onClickListener).create().show();
    }

    public void createToast(String str) {
        Toast.makeText(this.context, str, 0).show();
    }

    public void createToast(String str, boolean z) {
        if (z) {
            Toast.makeText(this.context, str, 1).show();
        } else {
            Toast.makeText(this.context, str, 0).show();
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0037, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0038, code lost:
        if (r0 != null) goto L_0x003a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:?, code lost:
        r0.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x003e, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x003f, code lost:
        r9.addSuppressed(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0042, code lost:
        throw r1;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.String getFileNameFromUri(android.net.Uri r9) {
        /*
            r8 = this;
            java.lang.String r0 = r9.getScheme()
            r1 = 0
            if (r0 == 0) goto L_0x005d
            java.lang.String r0 = r9.getScheme()
            java.lang.String r2 = "content"
            boolean r0 = r0.equals(r2)
            if (r0 == 0) goto L_0x0048
            android.content.Context r0 = r8.context
            android.content.ContentResolver r2 = r0.getContentResolver()
            r4 = 0
            r5 = 0
            r6 = 0
            r7 = 0
            r3 = r9
            android.database.Cursor r0 = r2.query(r3, r4, r5, r6, r7)
            if (r0 == 0) goto L_0x0043
            boolean r2 = r0.moveToFirst()     // Catch:{ all -> 0x0035 }
            if (r2 == 0) goto L_0x0043
            java.lang.String r1 = "_display_name"
            int r1 = r0.getColumnIndex(r1)     // Catch:{ all -> 0x0035 }
            java.lang.String r1 = r0.getString(r1)     // Catch:{ all -> 0x0035 }
            goto L_0x0043
        L_0x0035:
            r9 = move-exception
            throw r9     // Catch:{ all -> 0x0037 }
        L_0x0037:
            r1 = move-exception
            if (r0 == 0) goto L_0x0042
            r0.close()     // Catch:{ all -> 0x003e }
            goto L_0x0042
        L_0x003e:
            r0 = move-exception
            r9.addSuppressed(r0)
        L_0x0042:
            throw r1
        L_0x0043:
            if (r0 == 0) goto L_0x0048
            r0.close()
        L_0x0048:
            if (r1 != 0) goto L_0x005d
            java.lang.String r1 = r9.getPath()
            r9 = 47
            int r9 = r1.lastIndexOf(r9)
            r0 = -1
            if (r9 == r0) goto L_0x005d
            int r9 = r9 + 1
            java.lang.String r1 = r1.substring(r9)
        L_0x005d:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.anstrontechnologies.corehelper.AnstronCoreHelper.getFileNameFromUri(android.net.Uri):java.lang.String");
    }

    public static String getReadableFileSize(int i) {
        float f;
        DecimalFormat decimalFormat = new DecimalFormat("###.#");
        String str = " KB";
        if (i > 1024) {
            f = (float) (i / 1024);
            if (f > 1024.0f) {
                f /= 1024.0f;
                if (f > 1024.0f) {
                    f /= 1024.0f;
                    str = " GB";
                } else {
                    str = " MB";
                }
            }
        } else {
            f = 0.0f;
        }
        return decimalFormat.format((double) f) + str;
    }

    public String getTimeStamp() {
        return Long.toString(System.currentTimeMillis() / 1000);
    }

    public String getDateTime() {
        return new SimpleDateFormat("yyyy-MM-dd hh:mm a", Locale.getDefault()).format(new Date());
    }

    public String getDateTime(int i) {
        if (i == 24) {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault()).format(new Date());
        }
        return new SimpleDateFormat("yyyy-MM-dd hh:mm a", Locale.getDefault()).format(new Date());
    }

    public String firstUpperCase(String str) {
        String upperCase = String.valueOf(str.charAt(0)).toUpperCase();
        String substring = str.substring(1);
        return upperCase + substring;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0037, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0038, code lost:
        if (r0 != null) goto L_0x003a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:?, code lost:
        r0.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x003e, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x003f, code lost:
        r9.addSuppressed(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0042, code lost:
        throw r1;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.String getFileSizeFromUri(android.net.Uri r9) {
        /*
            r8 = this;
            java.lang.String r0 = r9.getScheme()
            r1 = 0
            if (r0 == 0) goto L_0x005d
            java.lang.String r0 = r9.getScheme()
            java.lang.String r2 = "content"
            boolean r0 = r0.equals(r2)
            if (r0 == 0) goto L_0x0048
            android.content.Context r0 = r8.context
            android.content.ContentResolver r2 = r0.getContentResolver()
            r4 = 0
            r5 = 0
            r6 = 0
            r7 = 0
            r3 = r9
            android.database.Cursor r0 = r2.query(r3, r4, r5, r6, r7)
            if (r0 == 0) goto L_0x0043
            boolean r2 = r0.moveToFirst()     // Catch:{ all -> 0x0035 }
            if (r2 == 0) goto L_0x0043
            java.lang.String r1 = "_size"
            int r1 = r0.getColumnIndex(r1)     // Catch:{ all -> 0x0035 }
            java.lang.String r1 = r0.getString(r1)     // Catch:{ all -> 0x0035 }
            goto L_0x0043
        L_0x0035:
            r9 = move-exception
            throw r9     // Catch:{ all -> 0x0037 }
        L_0x0037:
            r1 = move-exception
            if (r0 == 0) goto L_0x0042
            r0.close()     // Catch:{ all -> 0x003e }
            goto L_0x0042
        L_0x003e:
            r0 = move-exception
            r9.addSuppressed(r0)
        L_0x0042:
            throw r1
        L_0x0043:
            if (r0 == 0) goto L_0x0048
            r0.close()
        L_0x0048:
            if (r1 != 0) goto L_0x005d
            java.lang.String r1 = r9.getPath()
            r9 = 47
            int r9 = r1.lastIndexOf(r9)
            r0 = -1
            if (r9 == r0) goto L_0x005d
            int r9 = r9 + 1
            java.lang.String r1 = r1.substring(r9)
        L_0x005d:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.anstrontechnologies.corehelper.AnstronCoreHelper.getFileSizeFromUri(android.net.Uri):java.lang.String");
    }

    public String timestampToDateTime(long j) {
        Calendar instance = Calendar.getInstance(Locale.ENGLISH);
        instance.setTimeInMillis(j * 1000);
        return DateFormat.format("yyyy-MM-dd HH:mm a", instance).toString();
    }
}

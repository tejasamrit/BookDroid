package com.iceteck.silicompressorr;

import android.net.Uri;

public class Util {
    /* JADX WARNING: Removed duplicated region for block: B:26:0x00a6  */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x00c9  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String getFilePath(android.content.Context r11, android.net.Uri r12) throws java.net.URISyntaxException {
        /*
            int r0 = android.os.Build.VERSION.SDK_INT
            r1 = 0
            r2 = 19
            if (r0 < r2) goto L_0x0097
            android.content.Context r0 = r11.getApplicationContext()
            boolean r0 = android.provider.DocumentsContract.isDocumentUri(r0, r12)
            if (r0 == 0) goto L_0x0097
            boolean r0 = isExternalStorageDocument(r12)
            java.lang.String r2 = ":"
            r3 = 1
            if (r0 == 0) goto L_0x003d
            java.lang.String r11 = android.provider.DocumentsContract.getDocumentId(r12)
            java.lang.String[] r11 = r11.split(r2)
            java.lang.StringBuilder r12 = new java.lang.StringBuilder
            r12.<init>()
            java.io.File r0 = android.os.Environment.getExternalStorageDirectory()
            r12.append(r0)
            java.lang.String r0 = "/"
            r12.append(r0)
            r11 = r11[r3]
            r12.append(r11)
            java.lang.String r11 = r12.toString()
            return r11
        L_0x003d:
            boolean r0 = isDownloadsDocument(r12)
            if (r0 == 0) goto L_0x005a
            java.lang.String r12 = android.provider.DocumentsContract.getDocumentId(r12)
            java.lang.String r0 = "content://downloads/public_downloads"
            android.net.Uri r0 = android.net.Uri.parse(r0)
            java.lang.Long r12 = java.lang.Long.valueOf(r12)
            long r2 = r12.longValue()
            android.net.Uri r12 = android.content.ContentUris.withAppendedId(r0, r2)
            goto L_0x0097
        L_0x005a:
            boolean r0 = isMediaDocument(r12)
            if (r0 == 0) goto L_0x0097
            java.lang.String r0 = android.provider.DocumentsContract.getDocumentId(r12)
            java.lang.String[] r0 = r0.split(r2)
            r2 = 0
            r4 = r0[r2]
            java.lang.String r5 = "image"
            boolean r5 = r5.equals(r4)
            if (r5 == 0) goto L_0x0076
            android.net.Uri r12 = android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI
            goto L_0x008b
        L_0x0076:
            java.lang.String r5 = "video"
            boolean r5 = r5.equals(r4)
            if (r5 == 0) goto L_0x0081
            android.net.Uri r12 = android.provider.MediaStore.Video.Media.EXTERNAL_CONTENT_URI
            goto L_0x008b
        L_0x0081:
            java.lang.String r5 = "audio"
            boolean r4 = r5.equals(r4)
            if (r4 == 0) goto L_0x008b
            android.net.Uri r12 = android.provider.MediaStore.Audio.Media.EXTERNAL_CONTENT_URI
        L_0x008b:
            java.lang.String[] r4 = new java.lang.String[r3]
            r0 = r0[r3]
            r4[r2] = r0
            java.lang.String r0 = "_id=?"
            r6 = r12
            r8 = r0
            r9 = r4
            goto L_0x009a
        L_0x0097:
            r6 = r12
            r8 = r1
            r9 = r8
        L_0x009a:
            java.lang.String r12 = r6.getScheme()
            java.lang.String r0 = "content"
            boolean r12 = r0.equalsIgnoreCase(r12)
            if (r12 == 0) goto L_0x00c9
            java.lang.String r12 = "_data"
            java.lang.String[] r7 = new java.lang.String[]{r12}
            android.content.ContentResolver r5 = r11.getContentResolver()     // Catch:{ Exception -> 0x00c4 }
            r10 = 0
            android.database.Cursor r11 = r5.query(r6, r7, r8, r9, r10)     // Catch:{ Exception -> 0x00c4 }
            int r12 = r11.getColumnIndexOrThrow(r12)     // Catch:{ Exception -> 0x00c4 }
            boolean r0 = r11.moveToFirst()     // Catch:{ Exception -> 0x00c4 }
            if (r0 == 0) goto L_0x00da
            java.lang.String r11 = r11.getString(r12)     // Catch:{ Exception -> 0x00c4 }
            return r11
        L_0x00c4:
            r11 = move-exception
            r11.printStackTrace()
            goto L_0x00da
        L_0x00c9:
            java.lang.String r11 = r6.getScheme()
            java.lang.String r12 = "file"
            boolean r11 = r12.equalsIgnoreCase(r11)
            if (r11 == 0) goto L_0x00da
            java.lang.String r11 = r6.getPath()
            return r11
        L_0x00da:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.iceteck.silicompressorr.Util.getFilePath(android.content.Context, android.net.Uri):java.lang.String");
    }

    public static boolean isExternalStorageDocument(Uri uri) {
        return "com.android.externalstorage.documents".equals(uri.getAuthority());
    }

    public static boolean isDownloadsDocument(Uri uri) {
        return "com.android.providers.downloads.documents".equals(uri.getAuthority());
    }

    public static boolean isMediaDocument(Uri uri) {
        return "com.android.providers.media.documents".equals(uri.getAuthority());
    }
}

package com.iceteck.silicompressorr;

import android.net.Uri;

public class PathUtil {
    /* JADX WARNING: Removed duplicated region for block: B:30:0x00ab  */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x00c9  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String getPath(android.content.Context r11, android.net.Uri r12) throws java.net.URISyntaxException {
        /*
            int r0 = android.os.Build.VERSION.SDK_INT
            r1 = 0
            r2 = 1
            r3 = 19
            if (r0 < r3) goto L_0x000a
            r0 = 1
            goto L_0x000b
        L_0x000a:
            r0 = 0
        L_0x000b:
            r3 = 0
            if (r0 == 0) goto L_0x009c
            android.content.Context r0 = r11.getApplicationContext()
            boolean r0 = android.provider.DocumentsContract.isDocumentUri(r0, r12)
            if (r0 == 0) goto L_0x009c
            boolean r0 = isExternalStorageDocument(r12)
            java.lang.String r4 = ":"
            if (r0 == 0) goto L_0x0043
            java.lang.String r11 = android.provider.DocumentsContract.getDocumentId(r12)
            java.lang.String[] r11 = r11.split(r4)
            java.lang.StringBuilder r12 = new java.lang.StringBuilder
            r12.<init>()
            java.io.File r0 = android.os.Environment.getExternalStorageDirectory()
            r12.append(r0)
            java.lang.String r0 = "/"
            r12.append(r0)
            r11 = r11[r2]
            r12.append(r11)
            java.lang.String r11 = r12.toString()
            return r11
        L_0x0043:
            boolean r0 = isDownloadsDocument(r12)
            if (r0 == 0) goto L_0x0060
            java.lang.String r12 = android.provider.DocumentsContract.getDocumentId(r12)
            java.lang.String r0 = "content://downloads/public_downloads"
            android.net.Uri r0 = android.net.Uri.parse(r0)
            java.lang.Long r12 = java.lang.Long.valueOf(r12)
            long r1 = r12.longValue()
            android.net.Uri r12 = android.content.ContentUris.withAppendedId(r0, r1)
            goto L_0x009c
        L_0x0060:
            boolean r0 = isMediaDocument(r12)
            if (r0 == 0) goto L_0x009c
            java.lang.String r0 = android.provider.DocumentsContract.getDocumentId(r12)
            java.lang.String[] r0 = r0.split(r4)
            r4 = r0[r1]
            java.lang.String r5 = "image"
            boolean r5 = r5.equals(r4)
            if (r5 == 0) goto L_0x007b
            android.net.Uri r12 = android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI
            goto L_0x0090
        L_0x007b:
            java.lang.String r5 = "video"
            boolean r5 = r5.equals(r4)
            if (r5 == 0) goto L_0x0086
            android.net.Uri r12 = android.provider.MediaStore.Video.Media.EXTERNAL_CONTENT_URI
            goto L_0x0090
        L_0x0086:
            java.lang.String r5 = "audio"
            boolean r4 = r5.equals(r4)
            if (r4 == 0) goto L_0x0090
            android.net.Uri r12 = android.provider.MediaStore.Audio.Media.EXTERNAL_CONTENT_URI
        L_0x0090:
            java.lang.String[] r4 = new java.lang.String[r2]
            r0 = r0[r2]
            r4[r1] = r0
            java.lang.String r0 = "_id=?"
            r6 = r12
            r8 = r0
            r9 = r4
            goto L_0x009f
        L_0x009c:
            r6 = r12
            r8 = r3
            r9 = r8
        L_0x009f:
            java.lang.String r12 = r6.getScheme()
            java.lang.String r0 = "content"
            boolean r12 = r0.equalsIgnoreCase(r12)
            if (r12 == 0) goto L_0x00c9
            java.lang.String r12 = "_data"
            java.lang.String[] r7 = new java.lang.String[]{r12}
            android.content.ContentResolver r5 = r11.getContentResolver()     // Catch:{ Exception -> 0x00da }
            r10 = 0
            android.database.Cursor r11 = r5.query(r6, r7, r8, r9, r10)     // Catch:{ Exception -> 0x00da }
            int r12 = r11.getColumnIndexOrThrow(r12)     // Catch:{ Exception -> 0x00da }
            boolean r0 = r11.moveToFirst()     // Catch:{ Exception -> 0x00da }
            if (r0 == 0) goto L_0x00da
            java.lang.String r11 = r11.getString(r12)     // Catch:{ Exception -> 0x00da }
            return r11
        L_0x00c9:
            java.lang.String r11 = r6.getScheme()
            java.lang.String r12 = "file"
            boolean r11 = r12.equalsIgnoreCase(r11)
            if (r11 == 0) goto L_0x00da
            java.lang.String r11 = r6.getPath()
            return r11
        L_0x00da:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.iceteck.silicompressorr.PathUtil.getPath(android.content.Context, android.net.Uri):java.lang.String");
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

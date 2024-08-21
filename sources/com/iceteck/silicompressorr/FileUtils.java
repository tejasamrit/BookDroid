package com.iceteck.silicompressorr;

import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.webkit.MimeTypeMap;
import androidx.core.content.FileProvider;
import java.io.File;
import java.io.FileFilter;
import java.text.DecimalFormat;
import java.util.Comparator;

public class FileUtils {
    private static final boolean DEBUG = false;
    public static final String HIDDEN_PREFIX = ".";
    public static final String MIME_TYPE_APP = "application/*";
    public static final String MIME_TYPE_AUDIO = "audio/*";
    public static final String MIME_TYPE_IMAGE = "image/*";
    public static final String MIME_TYPE_TEXT = "text/*";
    public static final String MIME_TYPE_VIDEO = "video/*";
    static final String TAG = "FileUtils";
    public static Comparator<File> sComparator = new Comparator<File>() {
        public int compare(File file, File file2) {
            return file.getName().toLowerCase().compareTo(file2.getName().toLowerCase());
        }
    };
    public static FileFilter sDirFilter = new FileFilter() {
        public boolean accept(File file) {
            return file.isDirectory() && !file.getName().startsWith(FileUtils.HIDDEN_PREFIX);
        }
    };
    public static FileFilter sFileFilter = new FileFilter() {
        public boolean accept(File file) {
            return file.isFile() && !file.getName().startsWith(FileUtils.HIDDEN_PREFIX);
        }
    };

    private FileUtils() {
    }

    public static String getExtension(String str) {
        if (str == null) {
            return null;
        }
        int lastIndexOf = str.lastIndexOf(HIDDEN_PREFIX);
        return lastIndexOf >= 0 ? str.substring(lastIndexOf) : "";
    }

    public static boolean isLocal(String str) {
        return str != null && !str.startsWith("http://") && !str.startsWith("https://");
    }

    public static boolean isMediaUri(Uri uri) {
        return "media".equalsIgnoreCase(uri.getAuthority());
    }

    public static Uri getUri(Context context, File file) {
        if (file != null) {
            return FileProvider.getUriForFile(context, SiliCompressor.getAuthorities(context), file);
        }
        return null;
    }

    public static File getPathWithoutFilename(File file) {
        if (file == null) {
            return null;
        }
        if (file.isDirectory()) {
            return file;
        }
        String name = file.getName();
        String absolutePath = file.getAbsolutePath();
        String substring = absolutePath.substring(0, absolutePath.length() - name.length());
        if (substring.endsWith("/")) {
            substring = substring.substring(0, substring.length() - 1);
        }
        return new File(substring);
    }

    public static String getMimeType(File file) {
        String extension = getExtension(file.getName());
        return extension.length() > 0 ? MimeTypeMap.getSingleton().getMimeTypeFromExtension(extension.substring(1)) : "application/octet-stream";
    }

    public static String getMimeType(Context context, Uri uri) {
        return getMimeType(new File(getPath(context, uri)));
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

    public static boolean isGooglePhotosUri(Uri uri) {
        return "com.google.android.apps.photos.content".equals(uri.getAuthority());
    }

    /* JADX WARNING: Removed duplicated region for block: B:18:0x0035  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String getDataColumn(android.content.Context r8, android.net.Uri r9, java.lang.String r10, java.lang.String[] r11) {
        /*
            java.lang.String r0 = "_data"
            java.lang.String[] r3 = new java.lang.String[]{r0}
            r7 = 0
            android.content.ContentResolver r1 = r8.getContentResolver()     // Catch:{ all -> 0x0032 }
            r6 = 0
            r2 = r9
            r4 = r10
            r5 = r11
            android.database.Cursor r8 = r1.query(r2, r3, r4, r5, r6)     // Catch:{ all -> 0x0032 }
            if (r8 == 0) goto L_0x002c
            boolean r9 = r8.moveToFirst()     // Catch:{ all -> 0x0029 }
            if (r9 == 0) goto L_0x002c
            int r9 = r8.getColumnIndexOrThrow(r0)     // Catch:{ all -> 0x0029 }
            java.lang.String r9 = r8.getString(r9)     // Catch:{ all -> 0x0029 }
            if (r8 == 0) goto L_0x0028
            r8.close()
        L_0x0028:
            return r9
        L_0x0029:
            r9 = move-exception
            r7 = r8
            goto L_0x0033
        L_0x002c:
            if (r8 == 0) goto L_0x0031
            r8.close()
        L_0x0031:
            return r7
        L_0x0032:
            r9 = move-exception
        L_0x0033:
            if (r7 == 0) goto L_0x0038
            r7.close()
        L_0x0038:
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.iceteck.silicompressorr.FileUtils.getDataColumn(android.content.Context, android.net.Uri, java.lang.String, java.lang.String[]):java.lang.String");
    }

    public static String getPath(Context context, Uri uri) {
        int i = Build.VERSION.SDK_INT;
        Uri uri2 = null;
        if (Build.VERSION.SDK_INT < 19 || !DocumentsContract.isDocumentUri(context, uri)) {
            if ("content".equalsIgnoreCase(uri.getScheme())) {
                if (isGooglePhotosUri(uri)) {
                    return uri.getLastPathSegment();
                }
                return getDataColumn(context, uri, (String) null, (String[]) null);
            } else if ("file".equalsIgnoreCase(uri.getScheme())) {
                return uri.getPath();
            }
        } else if (isExternalStorageDocument(uri)) {
            String[] split = DocumentsContract.getDocumentId(uri).split(":");
            if ("primary".equalsIgnoreCase(split[0])) {
                return Environment.getExternalStorageDirectory() + "/" + split[1];
            }
        } else if (isDownloadsDocument(uri)) {
            return getDataColumn(context, ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), Long.valueOf(DocumentsContract.getDocumentId(uri)).longValue()), (String) null, (String[]) null);
        } else if (isMediaDocument(uri)) {
            String[] split2 = DocumentsContract.getDocumentId(uri).split(":");
            String str = split2[0];
            if ("image".equals(str)) {
                uri2 = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
            } else if ("video".equals(str)) {
                uri2 = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
            } else if ("audio".equals(str)) {
                uri2 = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
            }
            return getDataColumn(context, uri2, "_id=?", new String[]{split2[1]});
        }
        return null;
    }

    public static File getFile(Context context, Uri uri) {
        String path;
        if (uri == null || (path = getPath(context, uri)) == null || !isLocal(path)) {
            return null;
        }
        return new File(path);
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
        return String.valueOf(decimalFormat.format((double) f) + str);
    }

    public static Bitmap getThumbnail(Context context, File file) {
        return getThumbnail(context, getUri(context, file), getMimeType(file));
    }

    public static Bitmap getThumbnail(Context context, Uri uri) {
        return getThumbnail(context, uri, getMimeType(context, uri));
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v1, resolved type: android.graphics.Bitmap} */
    /* JADX WARNING: type inference failed for: r1v0 */
    /* JADX WARNING: type inference failed for: r1v2, types: [android.database.Cursor] */
    /* JADX WARNING: type inference failed for: r1v3 */
    /* JADX WARNING: type inference failed for: r1v4 */
    /* JADX WARNING: type inference failed for: r1v5 */
    /* JADX WARNING: type inference failed for: r1v6 */
    /* JADX WARNING: type inference failed for: r1v7 */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0048, code lost:
        if (r9 != null) goto L_0x004a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x004a, code lost:
        r9.close();
        r1 = r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x005b, code lost:
        if (r9 != null) goto L_0x004a;
     */
    /* JADX WARNING: Failed to insert additional move for type inference */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x0056  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static android.graphics.Bitmap getThumbnail(android.content.Context r8, android.net.Uri r9, java.lang.String r10) {
        /*
            boolean r0 = isMediaUri(r9)
            r1 = 0
            if (r0 != 0) goto L_0x000f
            java.lang.String r8 = "FileUtils"
            java.lang.String r9 = "You can only retrieve thumbnails for images and videos."
            android.util.Log.e(r8, r9)
            return r1
        L_0x000f:
            if (r9 == 0) goto L_0x005e
            android.content.ContentResolver r8 = r8.getContentResolver()
            r4 = 0
            r5 = 0
            r6 = 0
            r7 = 0
            r2 = r8
            r3 = r9
            android.database.Cursor r9 = r2.query(r3, r4, r5, r6, r7)     // Catch:{ Exception -> 0x005a, all -> 0x0053 }
            boolean r0 = r9.moveToFirst()     // Catch:{ Exception -> 0x0051, all -> 0x004e }
            if (r0 == 0) goto L_0x0048
            r0 = 0
            int r0 = r9.getInt(r0)     // Catch:{ Exception -> 0x0051, all -> 0x004e }
            java.lang.String r2 = "video"
            boolean r2 = r10.contains(r2)     // Catch:{ Exception -> 0x0051, all -> 0x004e }
            r3 = 1
            if (r2 == 0) goto L_0x003a
            long r4 = (long) r0     // Catch:{ Exception -> 0x0051, all -> 0x004e }
            android.graphics.Bitmap r8 = android.provider.MediaStore.Video.Thumbnails.getThumbnail(r8, r4, r3, r1)     // Catch:{ Exception -> 0x0051, all -> 0x004e }
        L_0x0038:
            r1 = r8
            goto L_0x0048
        L_0x003a:
            java.lang.String r2 = "image/*"
            boolean r10 = r10.contains(r2)     // Catch:{ Exception -> 0x0051, all -> 0x004e }
            if (r10 == 0) goto L_0x0048
            long r4 = (long) r0     // Catch:{ Exception -> 0x0051, all -> 0x004e }
            android.graphics.Bitmap r8 = android.provider.MediaStore.Images.Thumbnails.getThumbnail(r8, r4, r3, r1)     // Catch:{ Exception -> 0x0051, all -> 0x004e }
            goto L_0x0038
        L_0x0048:
            if (r9 == 0) goto L_0x005e
        L_0x004a:
            r9.close()
            goto L_0x005e
        L_0x004e:
            r8 = move-exception
            r1 = r9
            goto L_0x0054
        L_0x0051:
            goto L_0x005b
        L_0x0053:
            r8 = move-exception
        L_0x0054:
            if (r1 == 0) goto L_0x0059
            r1.close()
        L_0x0059:
            throw r8
        L_0x005a:
            r9 = r1
        L_0x005b:
            if (r9 == 0) goto L_0x005e
            goto L_0x004a
        L_0x005e:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.iceteck.silicompressorr.FileUtils.getThumbnail(android.content.Context, android.net.Uri, java.lang.String):android.graphics.Bitmap");
    }

    public static Intent createGetContentIntent() {
        Intent intent = new Intent("android.intent.action.GET_CONTENT");
        intent.setType("*/*");
        intent.addCategory("android.intent.category.OPENABLE");
        return intent;
    }
}

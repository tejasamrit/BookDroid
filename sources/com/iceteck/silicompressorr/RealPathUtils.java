package com.iceteck.silicompressorr;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import androidx.loader.content.CursorLoader;

public class RealPathUtils {
    public static String getRealPathFromURI_API19(Context context, Uri uri) {
        String[] strArr = {"_data"};
        Cursor query = context.getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, strArr, "_id=?", new String[]{DocumentsContract.getDocumentId(uri).split(":")[1]}, (String) null);
        String string = query.moveToFirst() ? query.getString(query.getColumnIndex(strArr[0])) : "";
        query.close();
        return string;
    }

    public static String getRealPathFromURI_API11to18(Context context, Uri uri) {
        Cursor loadInBackground = new CursorLoader(context, uri, new String[]{"_data"}, (String) null, (String[]) null, (String) null).loadInBackground();
        if (loadInBackground == null) {
            return null;
        }
        int columnIndexOrThrow = loadInBackground.getColumnIndexOrThrow("_data");
        loadInBackground.moveToFirst();
        return loadInBackground.getString(columnIndexOrThrow);
    }

    public static String getRealPathFromURI_BelowAPI11(Context context, Uri uri) {
        Cursor query = context.getContentResolver().query(uri, new String[]{"_data"}, (String) null, (String[]) null, (String) null);
        int columnIndexOrThrow = query.getColumnIndexOrThrow("_data");
        query.moveToFirst();
        return query.getString(columnIndexOrThrow);
    }
}

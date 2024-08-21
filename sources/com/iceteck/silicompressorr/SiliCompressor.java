package com.iceteck.silicompressorr;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import androidx.core.content.FileProvider;
import com.iceteck.silicompressorr.videocompression.MediaController;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class SiliCompressor {
    private static final String FILE_PROVIDER_AUTHORITY = ".iceteck.silicompressor.provider";
    private static final String LOG_TAG = "SiliCompressor";
    private static Context mContext;
    static volatile SiliCompressor singleton;
    public static String videoCompressionPath;

    public SiliCompressor(Context context) {
        mContext = context;
    }

    public static SiliCompressor with(Context context) {
        if (singleton == null) {
            synchronized (SiliCompressor.class) {
                if (singleton == null) {
                    singleton = new Builder(context).build();
                }
            }
        }
        return singleton;
    }

    public String compress(String str, File file) {
        return compressImage(str, file);
    }

    public String compress(String str, File file, boolean z) {
        String compressImage = compressImage(str, file);
        if (z) {
            Log.d(LOG_TAG, deleteImageFile(str) ? "Source image file deleted" : "Error: Source image file not deleted.");
        }
        return compressImage;
    }

    static String getAuthorities(Context context) {
        return context.getPackageName() + FILE_PROVIDER_AUTHORITY;
    }

    public Bitmap getCompressBitmap(String str) throws IOException {
        return getCompressBitmap(str, false);
    }

    public Bitmap getCompressBitmap(String str, boolean z) throws IOException {
        File file = new File(compressImage(str, new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "Silicompressor/images")));
        Context context = mContext;
        Bitmap bitmap = MediaStore.Images.Media.getBitmap(mContext.getContentResolver(), FileProvider.getUriForFile(context, getAuthorities(context), file));
        if (z) {
            Log.d(LOG_TAG, deleteImageFile(str) ? "Source image file deleted" : "Error: Source image file not deleted.");
        }
        deleteImageFile(file.getAbsolutePath());
        return bitmap;
    }

    private static boolean deleteImageFile(String str) {
        File file = new File(str);
        if (file.exists()) {
            return file.delete();
        }
        return false;
    }

    public String compressVideo(String str, String str2) throws URISyntaxException {
        return compressVideo(str, str2, 0, 0, 0);
    }

    public String compressVideo(Uri uri, String str) throws URISyntaxException {
        return compressVideo(uri, str, 0, 0, 0);
    }

    public String compressVideo(String str, String str2, int i, int i2, int i3) throws URISyntaxException {
        if (MediaController.getInstance().convertVideo(str, new File(str2), i, i2, i3)) {
            Log.v(LOG_TAG, "Video Conversion Complete");
        } else {
            Log.v(LOG_TAG, "Video conversion in progress");
        }
        return MediaController.cachedFile.getPath();
    }

    public String compressVideo(Uri uri, String str, int i, int i2, int i3) throws URISyntaxException {
        if (MediaController.getInstance().convertVideo(mContext, uri, new File(str), i, i2, i3)) {
            Log.v(LOG_TAG, "Video Conversion Complete");
        } else {
            Log.v(LOG_TAG, "Video conversion in progress");
        }
        return MediaController.cachedFile.getPath();
    }

    private int calculateInSampleSize(BitmapFactory.Options options, int i, int i2) {
        int i3;
        int i4 = options.outHeight;
        int i5 = options.outWidth;
        if (i4 > i2 || i5 > i) {
            i3 = Math.round(((float) i4) / ((float) i2));
            int round = Math.round(((float) i5) / ((float) i));
            if (i3 >= round) {
                i3 = round;
            }
        } else {
            i3 = 1;
        }
        while (((float) (i5 * i4)) / ((float) (i3 * i3)) > ((float) (i * i2 * 2))) {
            i3++;
        }
        return i3;
    }

    private String getFilename(String str, File file) {
        if (!file.exists()) {
            file.mkdirs();
        }
        return file.getAbsolutePath() + "/IMG_" + new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date()) + ".jpg";
    }

    private String getRealPathFromURI(String str) {
        Uri parse = Uri.parse(str);
        Cursor query = mContext.getContentResolver().query(parse, (String[]) null, (String) null, (String[]) null, (String) null);
        if (query == null) {
            return parse.getPath();
        }
        query.moveToFirst();
        int columnIndex = query.getColumnIndex("_data");
        Log.e(LOG_TAG, String.format("%d", new Object[]{Integer.valueOf(columnIndex)}));
        String string = query.getString(columnIndex);
        query.close();
        return string;
    }

    private String compressImage(String str, File file) {
        Bitmap bitmap;
        Bitmap bitmap2;
        Bitmap bitmap3;
        String str2 = str;
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        Bitmap decodeFile = BitmapFactory.decodeFile(str2, options);
        int i = options.outHeight;
        int i2 = options.outWidth;
        float f = (float) (i2 / i);
        float f2 = (float) i;
        if (f2 > 816.0f || ((float) i2) > 612.0f) {
            if (f < 0.75f) {
                i2 = (int) ((816.0f / f2) * ((float) i2));
                i = (int) 816.0f;
            } else {
                i = f > 0.75f ? (int) ((612.0f / ((float) i2)) * f2) : (int) 816.0f;
                i2 = (int) 612.0f;
            }
        }
        options.inSampleSize = calculateInSampleSize(options, i2, i);
        options.inJustDecodeBounds = false;
        options.inPurgeable = true;
        options.inInputShareable = true;
        options.inTempStorage = new byte[16384];
        try {
            decodeFile = BitmapFactory.decodeFile(str2, options);
        } catch (OutOfMemoryError e) {
            e.printStackTrace();
        }
        try {
            bitmap = Bitmap.createBitmap(i2, i, Bitmap.Config.ARGB_8888);
        } catch (OutOfMemoryError e2) {
            e2.printStackTrace();
            bitmap = null;
        }
        Bitmap bitmap4 = bitmap;
        float f3 = (float) i2;
        float f4 = f3 / ((float) options.outWidth);
        float f5 = (float) i;
        float f6 = f5 / ((float) options.outHeight);
        float f7 = f3 / 2.0f;
        float f8 = f5 / 2.0f;
        Matrix matrix = new Matrix();
        matrix.setScale(f4, f6, f7, f8);
        Canvas canvas = new Canvas(bitmap4);
        canvas.setMatrix(matrix);
        canvas.drawBitmap(decodeFile, f7 - ((float) (decodeFile.getWidth() / 2)), f8 - ((float) (decodeFile.getHeight() / 2)), new Paint(2));
        try {
            int attributeInt = new ExifInterface(str2).getAttributeInt(androidx.exifinterface.media.ExifInterface.TAG_ORIENTATION, 0);
            Log.d("EXIF", "Exif: " + attributeInt);
            Matrix matrix2 = new Matrix();
            if (attributeInt == 6) {
                matrix2.postRotate(90.0f);
                Log.d("EXIF", "Exif: " + attributeInt);
            } else if (attributeInt == 3) {
                matrix2.postRotate(180.0f);
                Log.d("EXIF", "Exif: " + attributeInt);
            } else if (attributeInt == 8) {
                matrix2.postRotate(270.0f);
                Log.d("EXIF", "Exif: " + attributeInt);
            }
            bitmap3 = bitmap4;
            try {
                bitmap2 = Bitmap.createBitmap(bitmap4, 0, 0, bitmap4.getWidth(), bitmap4.getHeight(), matrix2, true);
            } catch (IOException e3) {
                e = e3;
                e.printStackTrace();
                bitmap2 = bitmap3;
                String filename = getFilename(str, file);
                bitmap2.compress(Bitmap.CompressFormat.JPEG, 80, new FileOutputStream(filename));
                return filename;
            }
        } catch (IOException e4) {
            e = e4;
            bitmap3 = bitmap4;
            e.printStackTrace();
            bitmap2 = bitmap3;
            String filename2 = getFilename(str, file);
            bitmap2.compress(Bitmap.CompressFormat.JPEG, 80, new FileOutputStream(filename2));
            return filename2;
        }
        String filename22 = getFilename(str, file);
        try {
            bitmap2.compress(Bitmap.CompressFormat.JPEG, 80, new FileOutputStream(filename22));
        } catch (FileNotFoundException e5) {
            e5.printStackTrace();
        }
        return filename22;
    }

    public String compress(int i) throws IOException {
        Bitmap decodeResource = BitmapFactory.decodeResource(mContext.getApplicationContext().getResources(), i);
        if (decodeResource == null) {
            return null;
        }
        String format = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.ENGLISH).format(new Date());
        File createTempFile = File.createTempFile("JPEG_" + format + "_", ".jpg", Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES));
        decodeResource.compress(Bitmap.CompressFormat.JPEG, 100, new FileOutputStream(createTempFile));
        Context context = mContext;
        FileProvider.getUriForFile(context, getAuthorities(context), createTempFile);
        String compressImage = compressImage(createTempFile.getAbsolutePath(), new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "Silicompressor/images"));
        if (createTempFile.exists()) {
            deleteImageFile(createTempFile.getAbsolutePath());
        }
        return compressImage;
    }

    public static class Builder {
        private final Context context;

        Builder(Context context2) {
            if (context2 != null) {
                this.context = context2.getApplicationContext();
                return;
            }
            throw new IllegalArgumentException("Context must not be null.");
        }

        public SiliCompressor build() {
            return new SiliCompressor(this.context);
        }
    }
}

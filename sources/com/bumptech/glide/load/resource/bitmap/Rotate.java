package com.bumptech.glide.load.resource.bitmap;

import android.graphics.Bitmap;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.util.Util;
import java.nio.ByteBuffer;
import java.security.MessageDigest;

public class Rotate extends BitmapTransformation {

    /* renamed from: ID */
    private static final String f133ID = "com.bumptech.glide.load.resource.bitmap.Rotate";
    private static final byte[] ID_BYTES = f133ID.getBytes(CHARSET);
    private final int degreesToRotate;

    public Rotate(int i) {
        this.degreesToRotate = i;
    }

    /* access modifiers changed from: protected */
    public Bitmap transform(BitmapPool bitmapPool, Bitmap bitmap, int i, int i2) {
        return TransformationUtils.rotateImage(bitmap, this.degreesToRotate);
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof Rotate) || this.degreesToRotate != ((Rotate) obj).degreesToRotate) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return Util.hashCode(-950519196, Util.hashCode(this.degreesToRotate));
    }

    public void updateDiskCacheKey(MessageDigest messageDigest) {
        messageDigest.update(ID_BYTES);
        messageDigest.update(ByteBuffer.allocate(4).putInt(this.degreesToRotate).array());
    }
}

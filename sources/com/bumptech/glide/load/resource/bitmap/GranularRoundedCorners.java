package com.bumptech.glide.load.resource.bitmap;

import android.graphics.Bitmap;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.util.Util;
import java.nio.ByteBuffer;
import java.security.MessageDigest;

public final class GranularRoundedCorners extends BitmapTransformation {

    /* renamed from: ID */
    private static final String f132ID = "com.bumptech.glide.load.resource.bitmap.GranularRoundedCorners";
    private static final byte[] ID_BYTES = f132ID.getBytes(CHARSET);
    private final float bottomLeft;
    private final float bottomRight;
    private final float topLeft;
    private final float topRight;

    public GranularRoundedCorners(float f, float f2, float f3, float f4) {
        this.topLeft = f;
        this.topRight = f2;
        this.bottomRight = f3;
        this.bottomLeft = f4;
    }

    /* access modifiers changed from: protected */
    public Bitmap transform(BitmapPool bitmapPool, Bitmap bitmap, int i, int i2) {
        return TransformationUtils.roundedCorners(bitmapPool, bitmap, this.topLeft, this.topRight, this.bottomRight, this.bottomLeft);
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof GranularRoundedCorners)) {
            return false;
        }
        GranularRoundedCorners granularRoundedCorners = (GranularRoundedCorners) obj;
        if (this.topLeft == granularRoundedCorners.topLeft && this.topRight == granularRoundedCorners.topRight && this.bottomRight == granularRoundedCorners.bottomRight && this.bottomLeft == granularRoundedCorners.bottomLeft) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return Util.hashCode(this.bottomLeft, Util.hashCode(this.bottomRight, Util.hashCode(this.topRight, Util.hashCode(-2013597734, Util.hashCode(this.topLeft)))));
    }

    public void updateDiskCacheKey(MessageDigest messageDigest) {
        messageDigest.update(ID_BYTES);
        messageDigest.update(ByteBuffer.allocate(16).putFloat(this.topLeft).putFloat(this.topRight).putFloat(this.bottomRight).putFloat(this.bottomLeft).array());
    }
}

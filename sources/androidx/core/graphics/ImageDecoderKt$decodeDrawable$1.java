package androidx.core.graphics;

import android.graphics.ImageDecoder;
import com.google.firebase.messaging.Constants;
import kotlin.Metadata;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo37158bv = {1, 0, 3}, mo37159d1 = {"\u0000\u001a\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\n¢\u0006\u0002\b\b"}, mo37160d2 = {"<anonymous>", "", "decoder", "Landroid/graphics/ImageDecoder;", "info", "Landroid/graphics/ImageDecoder$ImageInfo;", "source", "Landroid/graphics/ImageDecoder$Source;", "onHeaderDecoded"}, mo37161k = 3, mo37162mv = {1, 1, 16})
/* compiled from: ImageDecoder.kt */
public final class ImageDecoderKt$decodeDrawable$1 implements ImageDecoder.OnHeaderDecodedListener {
    final /* synthetic */ Function3 $action;

    public ImageDecoderKt$decodeDrawable$1(Function3 function3) {
        this.$action = function3;
    }

    public final void onHeaderDecoded(ImageDecoder imageDecoder, ImageDecoder.ImageInfo imageInfo, ImageDecoder.Source source) {
        Intrinsics.checkParameterIsNotNull(imageDecoder, "decoder");
        Intrinsics.checkParameterIsNotNull(imageInfo, "info");
        Intrinsics.checkParameterIsNotNull(source, Constants.ScionAnalytics.PARAM_SOURCE);
        this.$action.invoke(imageDecoder, imageInfo, source);
    }
}

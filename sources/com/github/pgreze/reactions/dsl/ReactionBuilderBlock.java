package com.github.pgreze.reactions.dsl;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;
import androidx.core.content.ContextCompat;
import com.github.pgreze.reactions.Reaction;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo37158bv = {1, 0, 3}, mo37159d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0015\u0010\u0005\u001a\u00020\u0006*\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0004J\u0015\u0010\u0005\u001a\u00020\u0006*\u00020\n2\u0006\u0010\b\u001a\u00020\tH\u0004R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000¨\u0006\u000b"}, mo37160d2 = {"Lcom/github/pgreze/reactions/dsl/ReactionBuilderBlock;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "scale", "Lcom/github/pgreze/reactions/Reaction;", "Landroid/graphics/drawable/Drawable;", "scaleType", "Landroid/widget/ImageView$ScaleType;", "", "library_release"}, mo37161k = 1, mo37162mv = {1, 4, 0})
/* compiled from: dsl.kt */
public final class ReactionBuilderBlock {
    private final Context context;

    public ReactionBuilderBlock(Context context2) {
        Intrinsics.checkNotNullParameter(context2, "context");
        this.context = context2;
    }

    public final Reaction scale(int i, ImageView.ScaleType scaleType) {
        Intrinsics.checkNotNullParameter(scaleType, "scaleType");
        Drawable drawable = ContextCompat.getDrawable(this.context, i);
        Intrinsics.checkNotNull(drawable);
        Intrinsics.checkNotNullExpressionValue(drawable, "getDrawableCompat(context, this)!!");
        return new Reaction(drawable, scaleType);
    }

    public final Reaction scale(Drawable drawable, ImageView.ScaleType scaleType) {
        Intrinsics.checkNotNullParameter(drawable, "$this$scale");
        Intrinsics.checkNotNullParameter(scaleType, "scaleType");
        return new Reaction(drawable, scaleType);
    }
}

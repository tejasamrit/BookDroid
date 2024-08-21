package com.github.pgreze.reactions.dsl;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;
import androidx.core.content.ContextCompat;
import com.github.pgreze.reactions.Reaction;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo37158bv = {1, 0, 3}, mo37159d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\u0018\u00002\u00020\u0001B#\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007¢\u0006\u0002\u0010\tJ\u0014\u0010\n\u001a\u00020\u000b2\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\rJ\u001f\u0010\u000f\u001a\u00020\u000b2\u0017\u0010\f\u001a\u0013\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\b0\u0010¢\u0006\u0002\b\u0012J\u0014\u0010\u0013\u001a\u00020\u000b2\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00140\rR\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0015"}, mo37160d2 = {"Lcom/github/pgreze/reactions/dsl/ReactionsConfiguration;", "", "context", "Landroid/content/Context;", "scaleType", "Landroid/widget/ImageView$ScaleType;", "reactions", "", "Lcom/github/pgreze/reactions/Reaction;", "(Landroid/content/Context;Landroid/widget/ImageView$ScaleType;Ljava/util/List;)V", "drawable", "", "block", "Lkotlin/Function0;", "Landroid/graphics/drawable/Drawable;", "reaction", "Lkotlin/Function1;", "Lcom/github/pgreze/reactions/dsl/ReactionBuilderBlock;", "Lkotlin/ExtensionFunctionType;", "resId", "", "library_release"}, mo37161k = 1, mo37162mv = {1, 4, 0})
/* compiled from: dsl.kt */
public final class ReactionsConfiguration {
    private final Context context;
    private final List<Reaction> reactions;
    private final ImageView.ScaleType scaleType;

    public ReactionsConfiguration(Context context2, ImageView.ScaleType scaleType2, List<Reaction> list) {
        Intrinsics.checkNotNullParameter(context2, "context");
        Intrinsics.checkNotNullParameter(scaleType2, "scaleType");
        Intrinsics.checkNotNullParameter(list, "reactions");
        this.context = context2;
        this.scaleType = scaleType2;
        this.reactions = list;
    }

    public final void resId(Function0<Integer> function0) {
        Intrinsics.checkNotNullParameter(function0, "block");
        Drawable drawable = ContextCompat.getDrawable(this.context, function0.invoke().intValue());
        Intrinsics.checkNotNull(drawable);
        Intrinsics.checkNotNullExpressionValue(drawable, "getDrawableCompat(context, block())!!");
        this.reactions.add(new Reaction(drawable, this.scaleType));
    }

    public final void drawable(Function0<? extends Drawable> function0) {
        Intrinsics.checkNotNullParameter(function0, "block");
        this.reactions.add(new Reaction((Drawable) function0.invoke(), this.scaleType));
    }

    public final void reaction(Function1<? super ReactionBuilderBlock, Reaction> function1) {
        Intrinsics.checkNotNullParameter(function1, "block");
        this.reactions.add(function1.invoke(new ReactionBuilderBlock(this.context)));
    }
}

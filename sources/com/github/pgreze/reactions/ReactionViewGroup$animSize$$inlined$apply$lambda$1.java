package com.github.pgreze.reactions;

import android.animation.ValueAnimator;
import android.view.ViewGroup;
import com.github.pgreze.reactions.ReactionViewState;
import java.util.List;
import java.util.Objects;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo37158bv = {1, 0, 3}, mo37159d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005¨\u0006\u0006"}, mo37160d2 = {"<anonymous>", "", "it", "Landroid/animation/ValueAnimator;", "kotlin.jvm.PlatformType", "onAnimationUpdate", "com/github/pgreze/reactions/ReactionViewGroup$animSize$1$1"}, mo37161k = 3, mo37162mv = {1, 4, 0})
/* compiled from: ReactionViewGroup.kt */
final class ReactionViewGroup$animSize$$inlined$apply$lambda$1 implements ValueAnimator.AnimatorUpdateListener {
    final /* synthetic */ List $paths$inlined;
    final /* synthetic */ ReactionViewState.Selected $state$inlined;
    final /* synthetic */ ReactionViewGroup this$0;

    ReactionViewGroup$animSize$$inlined$apply$lambda$1(ReactionViewGroup reactionViewGroup, List list, ReactionViewState.Selected selected) {
        this.this$0 = reactionViewGroup;
        this.$paths$inlined = list;
        this.$state$inlined = selected;
    }

    public final void onAnimationUpdate(ValueAnimator valueAnimator) {
        Intrinsics.checkNotNullExpressionValue(valueAnimator, "it");
        Object animatedValue = valueAnimator.getAnimatedValue();
        Objects.requireNonNull(animatedValue, "null cannot be cast to non-null type kotlin.Float");
        float floatValue = ((Float) animatedValue).floatValue();
        int i = 0;
        for (Object next : this.this$0.reactions) {
            int i2 = i + 1;
            if (i < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            int access$progressMove = ReactionViewGroupKt.progressMove((Pair) this.$paths$inlined.get(i), floatValue);
            ViewGroup.LayoutParams layoutParams = ((ReactionView) next).getLayoutParams();
            Intrinsics.checkNotNullExpressionValue(layoutParams, "view.layoutParams");
            ReactionViewGroupKt.setSize(layoutParams, access$progressMove);
            i = i2;
        }
        this.this$0.requestLayout();
    }
}

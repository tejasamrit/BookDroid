package com.github.pgreze.reactions;

import android.animation.ValueAnimator;
import android.view.View;
import com.github.pgreze.reactions.ReactionViewState;
import java.util.Objects;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo37158bv = {1, 0, 3}, mo37159d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005¨\u0006\u0006"}, mo37160d2 = {"<anonymous>", "", "it", "Landroid/animation/ValueAnimator;", "kotlin.jvm.PlatformType", "onAnimationUpdate", "com/github/pgreze/reactions/ReactionViewGroup$animTranslationY$2$1"}, mo37161k = 3, mo37162mv = {1, 4, 0})
/* compiled from: ReactionViewGroup.kt */
final class ReactionViewGroup$animTranslationY$$inlined$apply$lambda$1 implements ValueAnimator.AnimatorUpdateListener {
    final /* synthetic */ ReactionViewState.Boundary $boundary$inlined;
    final /* synthetic */ ReactionViewGroup this$0;

    ReactionViewGroup$animTranslationY$$inlined$apply$lambda$1(ReactionViewGroup reactionViewGroup, ReactionViewState.Boundary boundary) {
        this.this$0 = reactionViewGroup;
        this.$boundary$inlined = boundary;
    }

    public final void onAnimationUpdate(ValueAnimator valueAnimator) {
        Intrinsics.checkNotNullExpressionValue(valueAnimator, "it");
        Object animatedValue = valueAnimator.getAnimatedValue();
        Objects.requireNonNull(animatedValue, "null cannot be cast to non-null type kotlin.Float");
        float floatValue = ((Float) animatedValue).floatValue();
        float access$progressMove = (float) ReactionViewGroupKt.progressMove(this.$boundary$inlined.getPath(), floatValue);
        ReactionViewGroup reactionViewGroup = this.this$0;
        int childCount = reactionViewGroup.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = reactionViewGroup.getChildAt(i);
            Intrinsics.checkNotNullExpressionValue(childAt, "getChildAt(child)");
            childAt.setTranslationY(access$progressMove);
            childAt.setAlpha(this.$boundary$inlined instanceof ReactionViewState.Boundary.Appear ? floatValue : ((float) 1) - floatValue);
        }
        this.this$0.requestLayout();
    }
}

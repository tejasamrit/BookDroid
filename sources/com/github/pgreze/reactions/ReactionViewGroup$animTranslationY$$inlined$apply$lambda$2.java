package com.github.pgreze.reactions;

import android.animation.Animator;
import com.github.pgreze.reactions.ReactionViewState;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;

@Metadata(mo37158bv = {1, 0, 3}, mo37159d1 = {"\u0000\u0019\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0012\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H\u0016J\u0012\u0010\u0006\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H\u0016J\u0012\u0010\u0007\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H\u0016J\u0012\u0010\b\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H\u0016¨\u0006\t¸\u0006\u0000"}, mo37160d2 = {"com/github/pgreze/reactions/ReactionViewGroup$animTranslationY$2$2", "Landroid/animation/Animator$AnimatorListener;", "onAnimationCancel", "", "animation", "Landroid/animation/Animator;", "onAnimationEnd", "onAnimationRepeat", "onAnimationStart", "library_release"}, mo37161k = 1, mo37162mv = {1, 4, 0})
/* compiled from: ReactionViewGroup.kt */
public final class ReactionViewGroup$animTranslationY$$inlined$apply$lambda$2 implements Animator.AnimatorListener {
    final /* synthetic */ ReactionViewState.Boundary $boundary$inlined;
    final /* synthetic */ ReactionViewGroup this$0;

    public void onAnimationCancel(Animator animator) {
    }

    public void onAnimationRepeat(Animator animator) {
    }

    public void onAnimationStart(Animator animator) {
    }

    ReactionViewGroup$animTranslationY$$inlined$apply$lambda$2(ReactionViewGroup reactionViewGroup, ReactionViewState.Boundary boundary) {
        this.this$0 = reactionViewGroup;
        this.$boundary$inlined = boundary;
    }

    public void onAnimationEnd(Animator animator) {
        ReactionViewState.Boundary boundary = this.$boundary$inlined;
        if (boundary instanceof ReactionViewState.Boundary.Appear) {
            this.this$0.setCurrentState(ReactionViewState.WaitingSelection.INSTANCE);
        } else if (boundary instanceof ReactionViewState.Boundary.Disappear) {
            this.this$0.setVisibility(8);
            this.this$0.setCurrentState((ReactionViewState) null);
            Function0<Unit> dismissListener = this.this$0.getDismissListener();
            if (dismissListener != null) {
                Unit invoke = dismissListener.invoke();
            }
        }
    }
}

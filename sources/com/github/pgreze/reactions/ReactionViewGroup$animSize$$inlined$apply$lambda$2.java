package com.github.pgreze.reactions;

import android.animation.Animator;
import android.widget.TextView;
import com.github.pgreze.reactions.ReactionViewState;
import java.util.List;
import kotlin.Metadata;

@Metadata(mo37158bv = {1, 0, 3}, mo37159d1 = {"\u0000\u0019\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0012\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H\u0016J\u0012\u0010\u0006\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H\u0016J\u0012\u0010\u0007\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H\u0016J\u0012\u0010\b\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H\u0016¨\u0006\t¸\u0006\u0000"}, mo37160d2 = {"com/github/pgreze/reactions/ReactionViewGroup$animSize$1$2", "Landroid/animation/Animator$AnimatorListener;", "onAnimationCancel", "", "animation", "Landroid/animation/Animator;", "onAnimationEnd", "onAnimationRepeat", "onAnimationStart", "library_release"}, mo37161k = 1, mo37162mv = {1, 4, 0})
/* compiled from: ReactionViewGroup.kt */
public final class ReactionViewGroup$animSize$$inlined$apply$lambda$2 implements Animator.AnimatorListener {
    final /* synthetic */ List $paths$inlined;
    final /* synthetic */ ReactionViewState.Selected $state$inlined;
    final /* synthetic */ ReactionViewGroup this$0;

    public void onAnimationCancel(Animator animator) {
    }

    public void onAnimationRepeat(Animator animator) {
    }

    public void onAnimationStart(Animator animator) {
    }

    ReactionViewGroup$animSize$$inlined$apply$lambda$2(ReactionViewGroup reactionViewGroup, List list, ReactionViewState.Selected selected) {
        this.this$0 = reactionViewGroup;
        this.$paths$inlined = list;
        this.$state$inlined = selected;
    }

    public void onAnimationEnd(Animator animator) {
        ReactionView view;
        ReactionViewState.Selected selected = this.$state$inlined;
        if (selected != null && (view = selected.getView()) != null) {
            TextView access$getReactionText$p = this.this$0.reactionText;
            CharSequence invoke = this.this$0.config.getReactionTextProvider().invoke(Integer.valueOf(this.this$0.reactions.indexOf(view)));
            if (invoke != null) {
                access$getReactionText$p.setText(invoke);
                this.this$0.reactionText.setVisibility(0);
                this.this$0.requestLayout();
            }
        }
    }
}

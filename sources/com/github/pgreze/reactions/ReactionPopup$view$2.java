package com.github.pgreze.reactions;

import android.content.Context;
import android.widget.FrameLayout;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

@Metadata(mo37158bv = {1, 0, 3}, mo37159d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, mo37160d2 = {"<anonymous>", "Lcom/github/pgreze/reactions/ReactionViewGroup;", "invoke"}, mo37161k = 3, mo37162mv = {1, 4, 0})
/* compiled from: ReactionPopup.kt */
final class ReactionPopup$view$2 extends Lambda implements Function0<ReactionViewGroup> {
    final /* synthetic */ Context $context;
    final /* synthetic */ ReactionsConfig $reactionsConfig;
    final /* synthetic */ ReactionPopup this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    ReactionPopup$view$2(ReactionPopup reactionPopup, Context context, ReactionsConfig reactionsConfig) {
        super(0);
        this.this$0 = reactionPopup;
        this.$context = context;
        this.$reactionsConfig = reactionsConfig;
    }

    public final ReactionViewGroup invoke() {
        ReactionViewGroup reactionViewGroup = new ReactionViewGroup(this.$context, this.$reactionsConfig);
        reactionViewGroup.setLayoutParams(new FrameLayout.LayoutParams(-2, -2, 17));
        reactionViewGroup.setReactionSelectedListener(this.this$0.getReactionSelectedListener());
        this.this$0.rootView.addView(reactionViewGroup);
        reactionViewGroup.setDismissListener(new ReactionPopup$view$2$1$1(this.this$0));
        return reactionViewGroup;
    }
}

package com.github.pgreze.reactions;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.FunctionReferenceImpl;

@Metadata(mo37158bv = {1, 0, 3}, mo37159d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001¢\u0006\u0002\b\u0002"}, mo37160d2 = {"<anonymous>", "", "invoke"}, mo37161k = 3, mo37162mv = {1, 4, 0})
/* compiled from: ReactionPopup.kt */
final /* synthetic */ class ReactionPopup$view$2$1$1 extends FunctionReferenceImpl implements Function0<Unit> {
    ReactionPopup$view$2$1$1(ReactionPopup reactionPopup) {
        super(0, reactionPopup, ReactionPopup.class, "dismiss", "dismiss()V", 0);
    }

    public final void invoke() {
        ((ReactionPopup) this.receiver).dismiss();
    }
}

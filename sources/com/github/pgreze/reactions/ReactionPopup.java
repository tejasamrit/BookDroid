package com.github.pgreze.reactions;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.PopupWindow;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo37158bv = {1, 0, 3}, mo37159d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u00012\u00020\u0002BD\b\u0007\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012+\b\u0002\u0010\u0007\u001a%\u0012\u0013\u0012\u00110\t¢\u0006\f\b\n\u0012\b\b\u000b\u0012\u0004\b\b(\f\u0012\u0004\u0012\u00020\r\u0018\u00010\bj\u0004\u0018\u0001`\u000e¢\u0006\u0002\u0010\u000fJ\b\u0010\u001c\u001a\u00020\u001dH\u0016J\u0018\u0010\u001e\u001a\u00020\r2\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020\"H\u0017R=\u0010\u0007\u001a%\u0012\u0013\u0012\u00110\t¢\u0006\f\b\n\u0012\b\b\u000b\u0012\u0004\b\b(\f\u0012\u0004\u0012\u00020\r\u0018\u00010\bj\u0004\u0018\u0001`\u000eX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R\u000e\u0010\u0014\u001a\u00020\u0015X\u0004¢\u0006\u0002\n\u0000R\u001b\u0010\u0016\u001a\u00020\u00178BX\u0002¢\u0006\f\n\u0004\b\u001a\u0010\u001b\u001a\u0004\b\u0018\u0010\u0019¨\u0006#"}, mo37160d2 = {"Lcom/github/pgreze/reactions/ReactionPopup;", "Landroid/widget/PopupWindow;", "Landroid/view/View$OnTouchListener;", "context", "Landroid/content/Context;", "reactionsConfig", "Lcom/github/pgreze/reactions/ReactionsConfig;", "reactionSelectedListener", "Lkotlin/Function1;", "", "Lkotlin/ParameterName;", "name", "position", "", "Lcom/github/pgreze/reactions/ReactionSelectedListener;", "(Landroid/content/Context;Lcom/github/pgreze/reactions/ReactionsConfig;Lkotlin/jvm/functions/Function1;)V", "getReactionSelectedListener", "()Lkotlin/jvm/functions/Function1;", "setReactionSelectedListener", "(Lkotlin/jvm/functions/Function1;)V", "rootView", "Landroid/widget/FrameLayout;", "view", "Lcom/github/pgreze/reactions/ReactionViewGroup;", "getView", "()Lcom/github/pgreze/reactions/ReactionViewGroup;", "view$delegate", "Lkotlin/Lazy;", "dismiss", "", "onTouch", "v", "Landroid/view/View;", "event", "Landroid/view/MotionEvent;", "library_release"}, mo37161k = 1, mo37162mv = {1, 4, 0})
/* compiled from: ReactionPopup.kt */
public final class ReactionPopup extends PopupWindow implements View.OnTouchListener {
    private Function1<? super Integer, Boolean> reactionSelectedListener;
    /* access modifiers changed from: private */
    public final FrameLayout rootView;
    private final Lazy view$delegate;

    public ReactionPopup(Context context, ReactionsConfig reactionsConfig) {
        this(context, reactionsConfig, (Function1) null, 4, (DefaultConstructorMarker) null);
    }

    private final ReactionViewGroup getView() {
        return (ReactionViewGroup) this.view$delegate.getValue();
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ ReactionPopup(Context context, ReactionsConfig reactionsConfig, Function1 function1, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, reactionsConfig, (i & 4) != 0 ? null : function1);
    }

    public final Function1<Integer, Boolean> getReactionSelectedListener() {
        return this.reactionSelectedListener;
    }

    public final void setReactionSelectedListener(Function1<? super Integer, Boolean> function1) {
        this.reactionSelectedListener = function1;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public ReactionPopup(Context context, ReactionsConfig reactionsConfig, Function1<? super Integer, Boolean> function1) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(reactionsConfig, "reactionsConfig");
        this.reactionSelectedListener = function1;
        FrameLayout frameLayout = new FrameLayout(context);
        frameLayout.setLayoutParams(new ViewGroup.LayoutParams(-1, -1));
        Unit unit = Unit.INSTANCE;
        this.rootView = frameLayout;
        this.view$delegate = LazyKt.lazy(LazyThreadSafetyMode.NONE, new ReactionPopup$view$2(this, context, reactionsConfig));
        setContentView(frameLayout);
        setWidth(-1);
        setHeight(-1);
        setFocusable(true);
        setBackgroundDrawable(new ColorDrawable(0));
    }

    public boolean onTouch(View view, MotionEvent motionEvent) {
        Intrinsics.checkNotNullParameter(view, "v");
        Intrinsics.checkNotNullParameter(motionEvent, "event");
        if (!isShowing()) {
            showAtLocation(view, 0, 0, 0);
            getView().show(motionEvent, view);
        }
        return getView().onTouchEvent(motionEvent);
    }

    public void dismiss() {
        getView().dismiss();
        super.dismiss();
    }
}

package com.github.pgreze.reactions;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Point;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.github.pgreze.reactions.ReactionViewState;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.math.MathKt;

@Metadata(mo37158bv = {1, 0, 3}, mo37159d1 = {"\u0000¬\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0012\u0010>\u001a\u00020\u00192\b\u0010?\u001a\u0004\u0018\u00010@H\u0002J\u0010\u0010A\u001a\u00020\u00192\u0006\u0010B\u001a\u00020CH\u0002J\u0006\u0010D\u001a\u00020\u0019J\u001a\u0010E\u001a\u0004\u0018\u0001082\u0006\u0010F\u001a\u00020G2\u0006\u0010H\u001a\u00020GH\u0002J\u0010\u0010I\u001a\u00020#2\u0006\u0010J\u001a\u00020KH\u0002J0\u0010L\u001a\u00020\u00192\u0006\u0010M\u001a\u00020#2\u0006\u0010N\u001a\u00020\u00132\u0006\u0010O\u001a\u00020\u00132\u0006\u0010P\u001a\u00020\u00132\u0006\u0010Q\u001a\u00020\u0013H\u0014J(\u0010R\u001a\u00020\u00192\u0006\u0010S\u001a\u00020\u00132\u0006\u0010T\u001a\u00020\u00132\u0006\u0010U\u001a\u00020\u00132\u0006\u0010V\u001a\u00020\u0013H\u0014J\u0010\u0010W\u001a\u00020#2\u0006\u0010J\u001a\u00020KH\u0017J\u0006\u0010X\u001a\u00020\u0019J\u0016\u0010Y\u001a\u00020\u00192\u0006\u0010J\u001a\u00020K2\u0006\u0010Z\u001a\u00020[R\u000e\u0010\u0007\u001a\u00020\bX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000R\"\u0010\u000b\u001a\u0004\u0018\u00010\n2\b\u0010\t\u001a\u0004\u0018\u00010\n@BX\u000e¢\u0006\b\n\u0000\"\u0004\b\f\u0010\rR\"\u0010\u000f\u001a\u0004\u0018\u00010\u000e2\b\u0010\t\u001a\u0004\u0018\u00010\u000e@BX\u000e¢\u0006\b\n\u0000\"\u0004\b\u0010\u0010\u0011R\u000e\u0010\u0012\u001a\u00020\u0013X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0013X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0013X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0013X\u000e¢\u0006\u0002\n\u0000R\"\u0010\u0017\u001a\n\u0012\u0004\u0012\u00020\u0019\u0018\u00010\u0018X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u001b\"\u0004\b\u001c\u0010\u001dR\u000e\u0010\u001e\u001a\u00020\u001fX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010 \u001a\u00020\u0013X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010!\u001a\u00020\u0013X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\"\u001a\u00020#X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010$\u001a\u00020#X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010%\u001a\u00020\u0013X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010&\u001a\u00020\u0013X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010'\u001a\u00020\u001fX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010(\u001a\u00020)X\u000e¢\u0006\u0002\n\u0000R=\u0010*\u001a%\u0012\u0013\u0012\u00110\u0013¢\u0006\f\b,\u0012\b\b-\u0012\u0004\b\b(.\u0012\u0004\u0012\u00020#\u0018\u00010+j\u0004\u0018\u0001`/X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b0\u00101\"\u0004\b2\u00103R\u000e\u00104\u001a\u000205X\u0004¢\u0006\u0002\n\u0000R\u0014\u00106\u001a\b\u0012\u0004\u0012\u00020807X\u0004¢\u0006\u0002\n\u0000R\u000e\u00109\u001a\u00020\u0013X\u000e¢\u0006\u0002\n\u0000R\u0016\u0010:\u001a\n <*\u0004\u0018\u00010;0;X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010=\u001a\u00020\u0013X\u0004¢\u0006\u0002\n\u0000¨\u0006\\"}, mo37160d2 = {"Lcom/github/pgreze/reactions/ReactionViewGroup;", "Landroid/view/ViewGroup;", "context", "Landroid/content/Context;", "config", "Lcom/github/pgreze/reactions/ReactionsConfig;", "(Landroid/content/Context;Lcom/github/pgreze/reactions/ReactionsConfig;)V", "background", "Lcom/github/pgreze/reactions/RoundedView;", "value", "Landroid/animation/ValueAnimator;", "currentAnimator", "setCurrentAnimator", "(Landroid/animation/ValueAnimator;)V", "Lcom/github/pgreze/reactions/ReactionViewState;", "currentState", "setCurrentState", "(Lcom/github/pgreze/reactions/ReactionViewState;)V", "dialogHeight", "", "dialogWidth", "dialogX", "dialogY", "dismissListener", "Lkotlin/Function0;", "", "getDismissListener", "()Lkotlin/jvm/functions/Function0;", "setDismissListener", "(Lkotlin/jvm/functions/Function0;)V", "firstClick", "Landroid/graphics/Point;", "horizontalPadding", "iconDivider", "isFirstTouchAlwaysInsideButton", "", "isIgnoringFirstReaction", "largeIconSize", "mediumIconSize", "parentLocation", "parentSize", "Lcom/github/pgreze/reactions/Size;", "reactionSelectedListener", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "position", "Lcom/github/pgreze/reactions/ReactionSelectedListener;", "getReactionSelectedListener", "()Lkotlin/jvm/functions/Function1;", "setReactionSelectedListener", "(Lkotlin/jvm/functions/Function1;)V", "reactionText", "Landroid/widget/TextView;", "reactions", "", "Lcom/github/pgreze/reactions/ReactionView;", "smallIconSize", "tag", "", "kotlin.jvm.PlatformType", "verticalPadding", "animSize", "state", "Lcom/github/pgreze/reactions/ReactionViewState$Selected;", "animTranslationY", "boundary", "Lcom/github/pgreze/reactions/ReactionViewState$Boundary;", "dismiss", "getIntersectedIcon", "x", "", "y", "inInsideParentView", "event", "Landroid/view/MotionEvent;", "onLayout", "changed", "l", "t", "r", "b", "onSizeChanged", "width", "height", "oldW", "oldH", "onTouchEvent", "resetChildrenToNormalSize", "show", "parent", "Landroid/view/View;", "library_release"}, mo37161k = 1, mo37162mv = {1, 4, 0})
/* compiled from: ReactionViewGroup.kt */
public final class ReactionViewGroup extends ViewGroup {
    private final RoundedView background;
    /* access modifiers changed from: private */
    public final ReactionsConfig config;
    private ValueAnimator currentAnimator;
    /* access modifiers changed from: private */
    public ReactionViewState currentState;
    private int dialogHeight;
    private int dialogWidth;
    private int dialogX;
    private int dialogY;
    private Function0<Unit> dismissListener;
    private Point firstClick;
    private final int horizontalPadding;
    private int iconDivider;
    private boolean isFirstTouchAlwaysInsideButton;
    private boolean isIgnoringFirstReaction;
    private int largeIconSize;
    private int mediumIconSize;
    private Point parentLocation;
    private Size parentSize;
    private Function1<? super Integer, Boolean> reactionSelectedListener;
    /* access modifiers changed from: private */
    public final TextView reactionText;
    /* access modifiers changed from: private */
    public final List<ReactionView> reactions;
    private int smallIconSize;
    private final String tag = ReactionViewGroup.class.getSimpleName();
    private final int verticalPadding;

    @Metadata(mo37158bv = {1, 0, 3}, mo37161k = 3, mo37162mv = {1, 4, 0})
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[PopupGravity.values().length];
            $EnumSwitchMapping$0 = iArr;
            iArr[PopupGravity.DEFAULT.ordinal()] = 1;
            iArr[PopupGravity.PARENT_LEFT.ordinal()] = 2;
            iArr[PopupGravity.PARENT_RIGHT.ordinal()] = 3;
            iArr[PopupGravity.SCREEN_LEFT.ordinal()] = 4;
            iArr[PopupGravity.SCREEN_RIGHT.ordinal()] = 5;
            iArr[PopupGravity.CENTER.ordinal()] = 6;
        }
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public ReactionViewGroup(Context context, ReactionsConfig reactionsConfig) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(reactionsConfig, "config");
        this.config = reactionsConfig;
        int horizontalMargin = reactionsConfig.getHorizontalMargin();
        this.horizontalPadding = horizontalMargin;
        int verticalMargin = reactionsConfig.getVerticalMargin();
        this.verticalPadding = verticalMargin;
        this.iconDivider = horizontalMargin / 2;
        int reactionSize = reactionsConfig.getReactionSize();
        this.mediumIconSize = reactionSize;
        this.largeIconSize = reactionSize * 2;
        this.firstClick = new Point();
        this.parentLocation = new Point();
        this.parentSize = new Size(0, 0);
        this.dialogHeight = this.mediumIconSize + (verticalMargin * 2);
        int size = reactionsConfig.getReactions().size();
        int max = Math.max(1, size - 1);
        int i = this.iconDivider;
        int i2 = (horizontalMargin * 2) + (this.mediumIconSize * size) + (i * max);
        this.dialogWidth = i2;
        this.smallIconSize = (((i2 - (horizontalMargin * 2)) - this.largeIconSize) - (i * max)) / max;
        RoundedView roundedView = new RoundedView(context, reactionsConfig);
        roundedView.setLayoutParams(new ViewGroup.LayoutParams(this.dialogWidth, this.dialogHeight));
        addView(roundedView);
        Unit unit = Unit.INSTANCE;
        this.background = roundedView;
        Iterable<Reaction> reactions2 = reactionsConfig.getReactions();
        Collection arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(reactions2, 10));
        for (Reaction reactionView : reactions2) {
            ReactionView reactionView2 = new ReactionView(context, reactionView);
            int i3 = this.mediumIconSize;
            reactionView2.setLayoutParams(new ViewGroup.LayoutParams(i3, i3));
            addView(reactionView2);
            arrayList.add(reactionView2);
        }
        this.reactions = CollectionsKt.toList((List) arrayList);
        TextView textView = new TextView(context);
        textView.setTextSize(this.config.getTextSize());
        textView.setTextColor(this.config.getTextColor());
        textView.setPadding(this.config.getTextHorizontalPadding(), this.config.getTextVerticalPadding(), this.config.getTextHorizontalPadding(), this.config.getTextVerticalPadding());
        textView.setBackground(this.config.getTextBackground());
        textView.setVisibility(8);
        addView(textView);
        Unit unit2 = Unit.INSTANCE;
        this.reactionText = textView;
        this.isFirstTouchAlwaysInsideButton = true;
    }

    /* access modifiers changed from: private */
    public final void setCurrentState(ReactionViewState reactionViewState) {
        if (!Intrinsics.areEqual((Object) this.currentState, (Object) reactionViewState)) {
            ReactionViewState reactionViewState2 = this.currentState;
            this.currentState = reactionViewState;
            String str = this.tag;
            Log.i(str, "State: " + reactionViewState2 + " -> " + reactionViewState);
            if (reactionViewState instanceof ReactionViewState.Boundary) {
                animTranslationY((ReactionViewState.Boundary) reactionViewState);
            } else if (reactionViewState instanceof ReactionViewState.WaitingSelection) {
                animSize((ReactionViewState.Selected) null);
            } else if (reactionViewState instanceof ReactionViewState.Selected) {
                animSize((ReactionViewState.Selected) reactionViewState);
            }
        }
    }

    private final void setCurrentAnimator(ValueAnimator valueAnimator) {
        ValueAnimator valueAnimator2 = this.currentAnimator;
        if (valueAnimator2 != null) {
            valueAnimator2.cancel();
        }
        this.currentAnimator = valueAnimator;
        this.reactionText.setVisibility(8);
        ValueAnimator valueAnimator3 = this.currentAnimator;
        if (valueAnimator3 != null) {
            valueAnimator3.setDuration(100);
        }
        ValueAnimator valueAnimator4 = this.currentAnimator;
        if (valueAnimator4 != null) {
            valueAnimator4.start();
        }
    }

    public final Function1<Integer, Boolean> getReactionSelectedListener() {
        return this.reactionSelectedListener;
    }

    public final void setReactionSelectedListener(Function1<? super Integer, Boolean> function1) {
        this.reactionSelectedListener = function1;
    }

    public final Function0<Unit> getDismissListener() {
        return this.dismissListener;
    }

    public final void setDismissListener(Function0<Unit> function0) {
        this.dismissListener = function0;
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onSizeChanged(int r4, int r5, int r6, int r7) {
        /*
            r3 = this;
            super.onSizeChanged(r4, r5, r6, r7)
            com.github.pgreze.reactions.ReactionsConfig r5 = r3.config
            com.github.pgreze.reactions.PopupGravity r5 = r5.getPopupGravity()
            int[] r6 = com.github.pgreze.reactions.ReactionViewGroup.WhenMappings.$EnumSwitchMapping$0
            int r5 = r5.ordinal()
            r5 = r6[r5]
            r6 = 0
            r7 = 1
            r0 = 0
            switch(r5) {
                case 1: goto L_0x008f;
                case 2: goto L_0x0066;
                case 3: goto L_0x0038;
                case 4: goto L_0x0031;
                case 5: goto L_0x0025;
                case 6: goto L_0x001d;
                default: goto L_0x0017;
            }
        L_0x0017:
            kotlin.NoWhenBranchMatchedException r4 = new kotlin.NoWhenBranchMatchedException
            r4.<init>()
            throw r4
        L_0x001d:
            int r5 = r3.dialogWidth
            int r5 = r4 - r5
            int r5 = r5 / 2
            goto L_0x009b
        L_0x0025:
            int r5 = r3.dialogWidth
            int r5 = r4 - r5
            com.github.pgreze.reactions.ReactionsConfig r6 = r3.config
            int r6 = r6.getPopupMargin()
            goto L_0x009a
        L_0x0031:
            com.github.pgreze.reactions.ReactionsConfig r5 = r3.config
            int r5 = r5.getPopupMargin()
            goto L_0x009b
        L_0x0038:
            android.graphics.Point r5 = r3.parentLocation
            int r5 = r5.x
            com.github.pgreze.reactions.Size r1 = r3.parentSize
            int r1 = r1.getWidth()
            int r5 = r5 + r1
            int r1 = r3.dialogWidth
            int r5 = r5 - r1
            java.lang.Integer r5 = java.lang.Integer.valueOf(r5)
            r1 = r5
            java.lang.Number r1 = (java.lang.Number) r1
            int r1 = r1.intValue()
            if (r1 >= 0) goto L_0x0054
            goto L_0x0055
        L_0x0054:
            r7 = 0
        L_0x0055:
            if (r7 != 0) goto L_0x0058
            r6 = r5
        L_0x0058:
            if (r6 == 0) goto L_0x005f
            int r5 = r6.intValue()
            goto L_0x009b
        L_0x005f:
            com.github.pgreze.reactions.ReactionsConfig r5 = r3.config
            int r5 = r5.getPopupMargin()
            goto L_0x009b
        L_0x0066:
            android.graphics.Point r5 = r3.parentLocation
            int r5 = r5.x
            java.lang.Integer r5 = java.lang.Integer.valueOf(r5)
            r1 = r5
            java.lang.Number r1 = (java.lang.Number) r1
            int r1 = r1.intValue()
            int r2 = r3.dialogWidth
            int r1 = r1 + r2
            if (r1 <= r4) goto L_0x007b
            goto L_0x007c
        L_0x007b:
            r7 = 0
        L_0x007c:
            if (r7 != 0) goto L_0x007f
            r6 = r5
        L_0x007f:
            if (r6 == 0) goto L_0x0086
            int r5 = r6.intValue()
            goto L_0x009b
        L_0x0086:
            int r5 = r4 - r2
            com.github.pgreze.reactions.ReactionsConfig r6 = r3.config
            int r6 = r6.getPopupMargin()
            goto L_0x009a
        L_0x008f:
            android.graphics.Point r5 = r3.firstClick
            int r5 = r5.x
            int r6 = r3.horizontalPadding
            int r5 = r5 - r6
            int r6 = r3.mediumIconSize
            int r6 = r6 / 2
        L_0x009a:
            int r5 = r5 - r6
        L_0x009b:
            r3.dialogX = r5
            if (r5 < 0) goto L_0x00a4
            int r6 = r3.dialogWidth
            int r5 = r5 + r6
            if (r5 < r4) goto L_0x00af
        L_0x00a4:
            int r5 = r3.dialogWidth
            int r4 = r4 - r5
            int r4 = r4 / 2
            int r4 = java.lang.Math.max(r0, r4)
            r3.dialogX = r4
        L_0x00af:
            android.graphics.Point r4 = r3.parentLocation
            int r4 = r4.y
            int r5 = r3.dialogHeight
            int r5 = r5 * 2
            int r4 = r4 - r5
            r3.dialogY = r4
            if (r4 >= 0) goto L_0x00cc
            android.graphics.Point r4 = r3.parentLocation
            int r4 = r4.y
            com.github.pgreze.reactions.Size r5 = r3.parentSize
            int r5 = r5.getHeight()
            int r4 = r4 + r5
            int r5 = r3.dialogHeight
            int r4 = r4 + r5
            r3.dialogY = r4
        L_0x00cc:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.github.pgreze.reactions.ReactionViewGroup.onSizeChanged(int, int, int, int):void");
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        ReactionView view;
        RoundedView roundedView = this.background;
        int translationX = (int) roundedView.getTranslationX();
        int translationY = (int) roundedView.getTranslationY();
        roundedView.layout(this.dialogX + translationX, ((this.dialogY + this.mediumIconSize) - roundedView.getLayoutParams().height) + translationY, this.dialogX + this.dialogWidth + translationX, this.dialogY + this.dialogHeight + translationY);
        int i5 = 0;
        for (ReactionView reactionView : this.reactions) {
            int translationX2 = (int) reactionView.getTranslationX();
            int translationY2 = (int) reactionView.getTranslationY();
            int i6 = ((this.dialogY + this.dialogHeight) - this.verticalPadding) + translationY2;
            int i7 = (i6 - reactionView.getLayoutParams().height) + translationY2;
            int i8 = this.dialogX + this.horizontalPadding + i5 + translationX2;
            reactionView.layout(i8, i7, reactionView.getLayoutParams().width + i8 + translationX2, i6);
            i5 += reactionView.getWidth() + this.iconDivider;
        }
        if (this.reactionText.getVisibility() == 0) {
            this.reactionText.measure(0, 0);
            ReactionViewState reactionViewState = this.currentState;
            if (!(reactionViewState instanceof ReactionViewState.Selected)) {
                reactionViewState = null;
            }
            ReactionViewState.Selected selected = (ReactionViewState.Selected) reactionViewState;
            if (selected != null && (view = selected.getView()) != null) {
                int top = view.getTop();
                ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
                Intrinsics.checkNotNullExpressionValue(layoutParams, "selectedView.layoutParams");
                int min = top - Math.min(ReactionViewGroupKt.getSize(layoutParams), this.reactionText.getMeasuredHeight() * 2);
                float left = (((float) view.getLeft()) + (((float) (view.getRight() - view.getLeft())) / 2.0f)) - (((float) this.reactionText.getMeasuredWidth()) / 2.0f);
                float measuredWidth = ((float) this.reactionText.getMeasuredWidth()) + left;
                this.reactionText.layout((int) left, min, (int) measuredWidth, this.reactionText.getMeasuredHeight() + min);
            }
        }
    }

    public final void show(MotionEvent motionEvent, View view) {
        Intrinsics.checkNotNullParameter(motionEvent, "event");
        Intrinsics.checkNotNullParameter(view, "parent");
        this.firstClick = new Point(MathKt.roundToInt(motionEvent.getRawX()), MathKt.roundToInt(motionEvent.getRawY()));
        int[] iArr = new int[2];
        view.getLocationOnScreen(iArr);
        Unit unit = Unit.INSTANCE;
        this.parentLocation = new Point(iArr[0], iArr[1]);
        this.parentSize = new Size(view.getWidth(), view.getHeight());
        this.isFirstTouchAlwaysInsideButton = true;
        this.isIgnoringFirstReaction = true;
        onSizeChanged(getWidth(), getHeight(), getWidth(), getHeight());
        setVisibility(0);
        setCurrentState(new ReactionViewState.Boundary.Appear(TuplesKt.m278to(Integer.valueOf(this.dialogHeight), 0)));
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        Boolean invoke;
        Intrinsics.checkNotNullParameter(motionEvent, "event");
        boolean z = false;
        this.isFirstTouchAlwaysInsideButton = this.isFirstTouchAlwaysInsideButton && inInsideParentView(motionEvent);
        int action = motionEvent.getAction();
        Object obj = null;
        if (action != 0) {
            if (action != 1) {
                if (action != 2) {
                    if (action == 3) {
                        setCurrentState(ReactionViewState.WaitingSelection.INSTANCE);
                    }
                }
            } else if (this.isFirstTouchAlwaysInsideButton) {
                this.isFirstTouchAlwaysInsideButton = false;
                return true;
            } else {
                ReactionView intersectedIcon = getIntersectedIcon(motionEvent.getRawX(), motionEvent.getRawY());
                if (intersectedIcon != null) {
                    obj = intersectedIcon.getReaction();
                }
                int indexOf = obj != null ? CollectionsKt.indexOf(this.config.getReactions(), obj) : -1;
                Function1<? super Integer, Boolean> function1 = this.reactionSelectedListener;
                if (function1 == null || (invoke = function1.invoke(Integer.valueOf(indexOf))) == null || !(!invoke.booleanValue())) {
                    dismiss();
                } else {
                    setCurrentState(ReactionViewState.WaitingSelection.INSTANCE);
                }
            }
            return true;
        }
        if (this.isIgnoringFirstReaction) {
            ReactionView reactionView = (ReactionView) CollectionsKt.first(this.reactions);
            boolean z2 = motionEvent.getRawX() >= reactionView.getX() && motionEvent.getRawX() <= ((float) reactionView.getRight()) && motionEvent.getRawY() >= reactionView.getY() + ((float) reactionView.getHeight()) && motionEvent.getRawY() <= (reactionView.getY() + ((float) reactionView.getHeight())) + ((float) this.dialogHeight);
            if (this.isIgnoringFirstReaction && (z2 || this.isFirstTouchAlwaysInsideButton)) {
                z = true;
            }
            this.isIgnoringFirstReaction = z;
            if (z) {
                return true;
            }
        }
        if (this.currentState instanceof ReactionViewState.Boundary.Appear) {
            return true;
        }
        ReactionView intersectedIcon2 = getIntersectedIcon(motionEvent.getRawX(), motionEvent.getRawY());
        if (intersectedIcon2 == null) {
            setCurrentState(ReactionViewState.WaitingSelection.INSTANCE);
        } else {
            ReactionViewState reactionViewState = this.currentState;
            if (!(reactionViewState instanceof ReactionViewState.Selected)) {
                reactionViewState = null;
            }
            ReactionViewState.Selected selected = (ReactionViewState.Selected) reactionViewState;
            if (selected != null) {
                obj = selected.getView();
            }
            if (!Intrinsics.areEqual(obj, (Object) intersectedIcon2)) {
                setCurrentState(new ReactionViewState.Selected(intersectedIcon2));
            }
        }
        return true;
    }

    public final void resetChildrenToNormalSize() {
        setCurrentState(ReactionViewState.WaitingSelection.INSTANCE);
    }

    public final void dismiss() {
        ReactionViewState reactionViewState = this.currentState;
        if (reactionViewState != null) {
            ReactionView reactionView = null;
            if (!(reactionViewState instanceof ReactionViewState.Selected)) {
                reactionViewState = null;
            }
            ReactionViewState.Selected selected = (ReactionViewState.Selected) reactionViewState;
            if (selected != null) {
                reactionView = selected.getView();
            }
            setCurrentState(new ReactionViewState.Boundary.Disappear(reactionView, TuplesKt.m278to(0, Integer.valueOf(this.dialogHeight))));
        }
    }

    private final boolean inInsideParentView(MotionEvent motionEvent) {
        return motionEvent.getRawX() >= ((float) this.parentLocation.x) && motionEvent.getRawX() <= ((float) (this.parentLocation.x + this.parentSize.getWidth())) && motionEvent.getRawY() >= ((float) this.parentLocation.y) && motionEvent.getRawY() <= ((float) (this.parentLocation.y + this.parentSize.getHeight()));
    }

    private final ReactionView getIntersectedIcon(float f, float f2) {
        Object obj;
        boolean z;
        Iterator it = this.reactions.iterator();
        while (true) {
            if (!it.hasNext()) {
                obj = null;
                break;
            }
            obj = it.next();
            ReactionView reactionView = (ReactionView) obj;
            if (f < ((float) (reactionView.getLocation().x - this.horizontalPadding)) || f >= ((float) (reactionView.getLocation().x + reactionView.getWidth() + this.iconDivider)) || f2 < ((float) (reactionView.getLocation().y - this.horizontalPadding)) || f2 >= ((float) (reactionView.getLocation().y + reactionView.getHeight() + this.dialogHeight + this.iconDivider))) {
                z = false;
                continue;
            } else {
                z = true;
                continue;
            }
            if (z) {
                break;
            }
        }
        return (ReactionView) obj;
    }

    private final void animTranslationY(ReactionViewState.Boundary boundary) {
        boolean z = boundary instanceof ReactionViewState.Boundary.Appear;
        float f = z ? 0.0f : 1.0f;
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = getChildAt(i);
            Intrinsics.checkNotNullExpressionValue(childAt, "getChildAt(child)");
            childAt.setAlpha(f);
            childAt.setTranslationY((float) boundary.getPath().getFirst().intValue());
            if (z) {
                ViewGroup.LayoutParams layoutParams = childAt.getLayoutParams();
                Intrinsics.checkNotNullExpressionValue(layoutParams, "it.layoutParams");
                ReactionViewGroupKt.setSize(layoutParams, this.mediumIconSize);
            }
        }
        requestLayout();
        ValueAnimator ofFloat = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f});
        ofFloat.addUpdateListener(new ReactionViewGroup$animTranslationY$$inlined$apply$lambda$1(this, boundary));
        ofFloat.addListener(new ReactionViewGroup$animTranslationY$$inlined$apply$lambda$2(this, boundary));
        Unit unit = Unit.INSTANCE;
        setCurrentAnimator(ofFloat);
    }

    private final void animSize(ReactionViewState.Selected selected) {
        int i;
        Iterable<ReactionView> iterable = this.reactions;
        Collection arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable, 10));
        for (ReactionView reactionView : iterable) {
            ViewGroup.LayoutParams layoutParams = reactionView.getLayoutParams();
            Intrinsics.checkNotNullExpressionValue(layoutParams, "it.layoutParams");
            Integer valueOf = Integer.valueOf(ReactionViewGroupKt.getSize(layoutParams));
            if (selected == null) {
                i = this.mediumIconSize;
            } else if (Intrinsics.areEqual((Object) selected.getView(), (Object) reactionView)) {
                i = this.largeIconSize;
            } else {
                i = this.smallIconSize;
            }
            arrayList.add(TuplesKt.m278to(valueOf, Integer.valueOf(i)));
        }
        List list = (List) arrayList;
        ValueAnimator ofFloat = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f});
        ofFloat.addUpdateListener(new ReactionViewGroup$animSize$$inlined$apply$lambda$1(this, list, selected));
        ofFloat.addListener(new ReactionViewGroup$animSize$$inlined$apply$lambda$2(this, list, selected));
        Unit unit = Unit.INSTANCE;
        setCurrentAnimator(ofFloat);
    }
}

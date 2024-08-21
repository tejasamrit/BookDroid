package com.github.pgreze.reactions;

import android.view.View;
import android.view.ViewGroup;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo37158bv = {1, 0, 3}, mo37159d1 = {"\u0000.\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001a \u0010\b\u001a\u00020\u00012\u0006\u0010\t\u001a\u00020\u00012\u0006\u0010\n\u001a\u00020\u00012\u0006\u0010\u000b\u001a\u00020\fH\u0002\u001a!\u0010\r\u001a\u00020\u000e*\u00020\u000f2\u0012\u0010\u0010\u001a\u000e\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020\u000e0\u0011H\b\u001a \u0010\b\u001a\u00020\u0001*\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00010\u00132\u0006\u0010\u000b\u001a\u00020\fH\u0002\"(\u0010\u0002\u001a\u00020\u0001*\u00020\u00032\u0006\u0010\u0000\u001a\u00020\u00018B@BX\u000e¢\u0006\f\u001a\u0004\b\u0004\u0010\u0005\"\u0004\b\u0006\u0010\u0007¨\u0006\u0014"}, mo37160d2 = {"value", "", "size", "Landroid/view/ViewGroup$LayoutParams;", "getSize", "(Landroid/view/ViewGroup$LayoutParams;)I", "setSize", "(Landroid/view/ViewGroup$LayoutParams;I)V", "progressMove", "from", "to", "progress", "", "forEach", "", "Landroid/view/ViewGroup;", "action", "Lkotlin/Function1;", "Landroid/view/View;", "Lkotlin/Pair;", "library_release"}, mo37161k = 2, mo37162mv = {1, 4, 0})
/* compiled from: ReactionViewGroup.kt */
public final class ReactionViewGroupKt {
    private static final int progressMove(int i, int i2, float f) {
        return i + ((int) (((float) (i2 - i)) * f));
    }

    /* access modifiers changed from: private */
    public static final int getSize(ViewGroup.LayoutParams layoutParams) {
        return layoutParams.width;
    }

    /* access modifiers changed from: private */
    public static final void setSize(ViewGroup.LayoutParams layoutParams, int i) {
        layoutParams.width = i;
        layoutParams.height = i;
    }

    /* access modifiers changed from: private */
    public static final void forEach(ViewGroup viewGroup, Function1<? super View, Unit> function1) {
        int childCount = viewGroup.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = viewGroup.getChildAt(i);
            Intrinsics.checkNotNullExpressionValue(childAt, "getChildAt(child)");
            function1.invoke(childAt);
        }
    }

    /* access modifiers changed from: private */
    public static final int progressMove(Pair<Integer, Integer> pair, float f) {
        return progressMove(pair.getFirst().intValue(), pair.getSecond().intValue(), f);
    }
}

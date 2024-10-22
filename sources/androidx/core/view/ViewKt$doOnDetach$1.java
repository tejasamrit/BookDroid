package androidx.core.view;

import android.view.View;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo37158bv = {1, 0, 3}, mo37159d1 = {"\u0000\u0019\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016J\u0010\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016¨\u0006\u0007"}, mo37160d2 = {"androidx/core/view/ViewKt$doOnDetach$1", "Landroid/view/View$OnAttachStateChangeListener;", "onViewAttachedToWindow", "", "view", "Landroid/view/View;", "onViewDetachedFromWindow", "core-ktx_release"}, mo37161k = 1, mo37162mv = {1, 1, 16})
/* compiled from: View.kt */
public final class ViewKt$doOnDetach$1 implements View.OnAttachStateChangeListener {
    final /* synthetic */ Function1 $action;
    final /* synthetic */ View $this_doOnDetach;

    public void onViewAttachedToWindow(View view) {
        Intrinsics.checkParameterIsNotNull(view, "view");
    }

    public ViewKt$doOnDetach$1(View view, Function1 function1) {
        this.$this_doOnDetach = view;
        this.$action = function1;
    }

    public void onViewDetachedFromWindow(View view) {
        Intrinsics.checkParameterIsNotNull(view, "view");
        this.$this_doOnDetach.removeOnAttachStateChangeListener(this);
        this.$action.invoke(view);
    }
}

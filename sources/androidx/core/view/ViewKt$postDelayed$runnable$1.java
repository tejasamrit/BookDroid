package androidx.core.view;

import kotlin.Metadata;
import kotlin.jvm.functions.Function0;

@Metadata(mo37158bv = {1, 0, 3}, mo37159d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, mo37160d2 = {"<anonymous>", "", "run"}, mo37161k = 3, mo37162mv = {1, 1, 16})
/* compiled from: View.kt */
public final class ViewKt$postDelayed$runnable$1 implements Runnable {
    final /* synthetic */ Function0 $action;

    public ViewKt$postDelayed$runnable$1(Function0 function0) {
        this.$action = function0;
    }

    public final void run() {
        this.$action.invoke();
    }
}

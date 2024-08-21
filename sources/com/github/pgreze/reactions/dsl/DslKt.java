package com.github.pgreze.reactions.dsl;

import android.content.Context;
import android.widget.ImageView;
import com.github.pgreze.reactions.Reaction;
import com.github.pgreze.reactions.ReactionPopup;
import com.github.pgreze.reactions.ReactionsConfig;
import com.github.pgreze.reactions.ReactionsConfigBuilder;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo37158bv = {1, 0, 3}, mo37159d1 = {"\u0000J\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a'\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0017\u0010\u0004\u001a\u0013\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005¢\u0006\u0002\b\b\u001aT\u0010\t\u001a\u00020\n2\u0006\u0010\u0002\u001a\u00020\u00032+\b\u0002\u0010\u000b\u001a%\u0012\u0013\u0012\u00110\f¢\u0006\f\b\r\u0012\b\b\u000e\u0012\u0004\b\b(\u000f\u0012\u0004\u0012\u00020\u0010\u0018\u00010\u0005j\u0004\u0018\u0001`\u00112\u0017\u0010\u0004\u001a\u0013\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005¢\u0006\u0002\b\b\u001a-\u0010\u0012\u001a\u00020\u0007*\u00020\u00062\b\b\u0002\u0010\u0013\u001a\u00020\u00142\u0017\u0010\u0015\u001a\u0013\u0012\u0004\u0012\u00020\u0016\u0012\u0004\u0012\u00020\u00070\u0005¢\u0006\u0002\b\b¨\u0006\u0017"}, mo37160d2 = {"reactionConfig", "Lcom/github/pgreze/reactions/ReactionsConfig;", "context", "Landroid/content/Context;", "init", "Lkotlin/Function1;", "Lcom/github/pgreze/reactions/ReactionsConfigBuilder;", "", "Lkotlin/ExtensionFunctionType;", "reactionPopup", "Lcom/github/pgreze/reactions/ReactionPopup;", "reactionSelectedListener", "", "Lkotlin/ParameterName;", "name", "position", "", "Lcom/github/pgreze/reactions/ReactionSelectedListener;", "reactions", "scaleType", "Landroid/widget/ImageView$ScaleType;", "config", "Lcom/github/pgreze/reactions/dsl/ReactionsConfiguration;", "library_release"}, mo37161k = 2, mo37162mv = {1, 4, 0})
/* compiled from: dsl.kt */
public final class DslKt {
    public static /* synthetic */ ReactionPopup reactionPopup$default(Context context, Function1 function1, Function1 function12, int i, Object obj) {
        if ((i & 2) != 0) {
            function1 = null;
        }
        return reactionPopup(context, function1, function12);
    }

    public static final ReactionPopup reactionPopup(Context context, Function1<? super Integer, Boolean> function1, Function1<? super ReactionsConfigBuilder, Unit> function12) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(function12, "init");
        return new ReactionPopup(context, reactionConfig(context, function12), function1);
    }

    public static final ReactionsConfig reactionConfig(Context context, Function1<? super ReactionsConfigBuilder, Unit> function1) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(function1, "init");
        ReactionsConfigBuilder reactionsConfigBuilder = new ReactionsConfigBuilder(context);
        function1.invoke(reactionsConfigBuilder);
        return reactionsConfigBuilder.build();
    }

    public static /* synthetic */ void reactions$default(ReactionsConfigBuilder reactionsConfigBuilder, ImageView.ScaleType scaleType, Function1 function1, int i, Object obj) {
        if ((i & 1) != 0) {
            scaleType = ImageView.ScaleType.FIT_CENTER;
        }
        reactions(reactionsConfigBuilder, scaleType, function1);
    }

    public static final void reactions(ReactionsConfigBuilder reactionsConfigBuilder, ImageView.ScaleType scaleType, Function1<? super ReactionsConfiguration, Unit> function1) {
        Intrinsics.checkNotNullParameter(reactionsConfigBuilder, "$this$reactions");
        Intrinsics.checkNotNullParameter(scaleType, "scaleType");
        Intrinsics.checkNotNullParameter(function1, "config");
        List arrayList = new ArrayList();
        function1.invoke(new ReactionsConfiguration(reactionsConfigBuilder.getContext(), scaleType, arrayList));
        Unit unit = Unit.INSTANCE;
        reactionsConfigBuilder.withReactions((Collection<Reaction>) arrayList);
    }
}

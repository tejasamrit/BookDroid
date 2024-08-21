package com.github.pgreze.reactions;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;
import androidx.core.content.ContextCompat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.NotImplementedError;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.math.MathKt;

@Metadata(mo37158bv = {1, 0, 3}, mo37159d1 = {"\u0000n\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\r\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u001e\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0015\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u0007\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0006\u0010V\u001a\u00020WJ\u000e\u0010X\u001a\u00020\u00002\u0006\u0010\u0007\u001a\u00020\bJ\u0010\u0010Y\u001a\u00020\u00002\b\b\u0001\u0010\r\u001a\u00020\bJ\u0010\u0010Z\u001a\u00020\u00002\b\b\u0001\u0010\u0010\u001a\u00020\bJ\u000e\u0010[\u001a\u00020\u00002\u0006\u0010\u0013\u001a\u00020\bJ\u000e\u0010\\\u001a\u00020\u00002\u0006\u0010\u0016\u001a\u00020\u0017J\u000e\u0010]\u001a\u00020\u00002\u0006\u0010\u001c\u001a\u00020\bJ\u000e\u0010^\u001a\u00020\u00002\u0006\u0010\u001f\u001a\u00020\bJ/\u0010_\u001a\u00020\u00002'\u0010\"\u001a#\u0012\u0013\u0012\u00110\b¢\u0006\f\b$\u0012\b\b%\u0012\u0004\b\b(&\u0012\u0006\u0012\u0004\u0018\u00010'0#j\u0002`(J\u0010\u0010_\u001a\u00020\u00002\b\b\u0001\u0010`\u001a\u00020\bJ\u001a\u0010a\u001a\u00020\u00002\u0006\u0010`\u001a\u0002082\b\b\u0002\u0010b\u001a\u00020cH\u0007J\u0014\u0010a\u001a\u00020\u00002\f\u00101\u001a\b\u0012\u0004\u0012\u00020302J\u000e\u0010d\u001a\u00020\u00002\u0006\u0010>\u001a\u00020?J\u0010\u0010e\u001a\u00020\u00002\b\b\u0001\u0010D\u001a\u00020\bJ\u000e\u0010f\u001a\u00020\u00002\u0006\u0010G\u001a\u00020\bJ\u000e\u0010g\u001a\u00020\u00002\u0006\u0010J\u001a\u00020KJ\u000e\u0010h\u001a\u00020\u00002\u0006\u0010P\u001a\u00020\bJ\u000e\u0010i\u001a\u00020\u00002\u0006\u0010S\u001a\u00020\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u001e\u0010\u0007\u001a\u00020\b8\u0006@\u0006X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\u001a\u0010\r\u001a\u00020\bX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\n\"\u0004\b\u000f\u0010\fR\u001e\u0010\u0010\u001a\u00020\b8\u0006@\u0006X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\n\"\u0004\b\u0012\u0010\fR\u001a\u0010\u0013\u001a\u00020\bX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\n\"\u0004\b\u0015\u0010\fR\u001a\u0010\u0016\u001a\u00020\u0017X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0019\"\u0004\b\u001a\u0010\u001bR\u001a\u0010\u001c\u001a\u00020\bX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\n\"\u0004\b\u001e\u0010\fR\u001e\u0010\u001f\u001a\u00020\b8\u0006@\u0006X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b \u0010\n\"\u0004\b!\u0010\fR;\u0010\"\u001a#\u0012\u0013\u0012\u00110\b¢\u0006\f\b$\u0012\b\b%\u0012\u0004\b\b(&\u0012\u0006\u0012\u0004\u0018\u00010'0#j\u0002`(X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b)\u0010*\"\u0004\b+\u0010,R&\u0010.\u001a\u00020\b2\b\b\u0001\u0010-\u001a\u00020\b8F@FX\u000e¢\u0006\f\u001a\u0004\b/\u0010\n\"\u0004\b0\u0010\fR \u00101\u001a\b\u0012\u0004\u0012\u00020302X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b4\u00105\"\u0004\b6\u00107R$\u00109\u001a\u0002082\u0006\u0010-\u001a\u0002088F@FX\u000e¢\u0006\f\u001a\u0004\b:\u0010;\"\u0004\b<\u0010=R\u001c\u0010>\u001a\u0004\u0018\u00010?X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b@\u0010A\"\u0004\bB\u0010CR\u001e\u0010D\u001a\u00020\b8\u0006@\u0006X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bE\u0010\n\"\u0004\bF\u0010\fR\u001a\u0010G\u001a\u00020\bX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bH\u0010\n\"\u0004\bI\u0010\fR\u001a\u0010J\u001a\u00020KX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bL\u0010M\"\u0004\bN\u0010OR\u001a\u0010P\u001a\u00020\bX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bQ\u0010\n\"\u0004\bR\u0010\fR\u001e\u0010S\u001a\u00020\b8\u0006@\u0006X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bT\u0010\n\"\u0004\bU\u0010\f¨\u0006j"}, mo37160d2 = {"Lcom/github/pgreze/reactions/ReactionsConfigBuilder;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "getContext", "()Landroid/content/Context;", "horizontalMargin", "", "getHorizontalMargin", "()I", "setHorizontalMargin", "(I)V", "popupAlpha", "getPopupAlpha", "setPopupAlpha", "popupColor", "getPopupColor", "setPopupColor", "popupCornerRadius", "getPopupCornerRadius", "setPopupCornerRadius", "popupGravity", "Lcom/github/pgreze/reactions/PopupGravity;", "getPopupGravity", "()Lcom/github/pgreze/reactions/PopupGravity;", "setPopupGravity", "(Lcom/github/pgreze/reactions/PopupGravity;)V", "popupMargin", "getPopupMargin", "setPopupMargin", "reactionSize", "getReactionSize", "setReactionSize", "reactionTextProvider", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "position", "", "Lcom/github/pgreze/reactions/ReactionTextProvider;", "getReactionTextProvider", "()Lkotlin/jvm/functions/Function1;", "setReactionTextProvider", "(Lkotlin/jvm/functions/Function1;)V", "value", "reactionTexts", "getReactionTexts", "setReactionTexts", "reactions", "", "Lcom/github/pgreze/reactions/Reaction;", "getReactions", "()Ljava/util/Collection;", "setReactions", "(Ljava/util/Collection;)V", "", "reactionsIds", "getReactionsIds", "()[I", "setReactionsIds", "([I)V", "textBackground", "Landroid/graphics/drawable/Drawable;", "getTextBackground", "()Landroid/graphics/drawable/Drawable;", "setTextBackground", "(Landroid/graphics/drawable/Drawable;)V", "textColor", "getTextColor", "setTextColor", "textHorizontalPadding", "getTextHorizontalPadding", "setTextHorizontalPadding", "textSize", "", "getTextSize", "()F", "setTextSize", "(F)V", "textVerticalPadding", "getTextVerticalPadding", "setTextVerticalPadding", "verticalMargin", "getVerticalMargin", "setVerticalMargin", "build", "Lcom/github/pgreze/reactions/ReactionsConfig;", "withHorizontalMargin", "withPopupAlpha", "withPopupColor", "withPopupCornerRadius", "withPopupGravity", "withPopupMargin", "withReactionSize", "withReactionTexts", "res", "withReactions", "scaleType", "Landroid/widget/ImageView$ScaleType;", "withTextBackground", "withTextColor", "withTextHorizontalPadding", "withTextSize", "withTextVerticalPadding", "withVerticalMargin", "library_release"}, mo37161k = 1, mo37162mv = {1, 4, 0})
/* compiled from: model.kt */
public final class ReactionsConfigBuilder {
    private final Context context;
    private int horizontalMargin;
    private int popupAlpha;
    private int popupColor;
    private int popupCornerRadius;
    private PopupGravity popupGravity;
    private int popupMargin;
    private int reactionSize;
    private Function1<? super Integer, ? extends CharSequence> reactionTextProvider;
    private Collection<Reaction> reactions = CollectionsKt.emptyList();
    private Drawable textBackground;
    private int textColor;
    private int textHorizontalPadding;
    private float textSize;
    private int textVerticalPadding;
    private int verticalMargin;

    public final ReactionsConfigBuilder withReactions(int[] iArr) {
        return withReactions$default(this, iArr, (ImageView.ScaleType) null, 2, (Object) null);
    }

    public ReactionsConfigBuilder(Context context2) {
        Intrinsics.checkNotNullParameter(context2, "context");
        this.context = context2;
        this.reactionSize = context2.getResources().getDimensionPixelSize(C0865R.dimen.reactions_item_size);
        int dimensionPixelSize = context2.getResources().getDimensionPixelSize(C0865R.dimen.reactions_item_margin);
        this.horizontalMargin = dimensionPixelSize;
        this.verticalMargin = dimensionPixelSize;
        this.popupGravity = PopupGravity.DEFAULT;
        this.popupMargin = this.horizontalMargin;
        this.popupCornerRadius = 90;
        this.popupColor = -1;
        this.popupAlpha = 230;
        this.reactionTextProvider = ModelKt.NO_TEXT_PROVIDER;
        this.textColor = -1;
    }

    public final Context getContext() {
        return this.context;
    }

    public final Collection<Reaction> getReactions() {
        return this.reactions;
    }

    public final void setReactions(Collection<Reaction> collection) {
        Intrinsics.checkNotNullParameter(collection, "<set-?>");
        this.reactions = collection;
    }

    public final int[] getReactionsIds() {
        throw new NotImplementedError((String) null, 1, (DefaultConstructorMarker) null);
    }

    public final void setReactionsIds(int[] iArr) {
        Intrinsics.checkNotNullParameter(iArr, "value");
        withReactions$default(this, iArr, (ImageView.ScaleType) null, 2, (Object) null);
    }

    public final int getReactionSize() {
        return this.reactionSize;
    }

    public final void setReactionSize(int i) {
        this.reactionSize = i;
    }

    public final int getHorizontalMargin() {
        return this.horizontalMargin;
    }

    public final void setHorizontalMargin(int i) {
        this.horizontalMargin = i;
    }

    public final int getVerticalMargin() {
        return this.verticalMargin;
    }

    public final void setVerticalMargin(int i) {
        this.verticalMargin = i;
    }

    public final PopupGravity getPopupGravity() {
        return this.popupGravity;
    }

    public final void setPopupGravity(PopupGravity popupGravity2) {
        Intrinsics.checkNotNullParameter(popupGravity2, "<set-?>");
        this.popupGravity = popupGravity2;
    }

    public final int getPopupMargin() {
        return this.popupMargin;
    }

    public final void setPopupMargin(int i) {
        this.popupMargin = i;
    }

    public final int getPopupCornerRadius() {
        return this.popupCornerRadius;
    }

    public final void setPopupCornerRadius(int i) {
        this.popupCornerRadius = i;
    }

    public final int getPopupColor() {
        return this.popupColor;
    }

    public final void setPopupColor(int i) {
        this.popupColor = i;
    }

    public final int getPopupAlpha() {
        return this.popupAlpha;
    }

    public final void setPopupAlpha(int i) {
        this.popupAlpha = i;
    }

    public final Function1<Integer, CharSequence> getReactionTextProvider() {
        return this.reactionTextProvider;
    }

    public final void setReactionTextProvider(Function1<? super Integer, ? extends CharSequence> function1) {
        Intrinsics.checkNotNullParameter(function1, "<set-?>");
        this.reactionTextProvider = function1;
    }

    public final int getReactionTexts() {
        throw new NotImplementedError((String) null, 1, (DefaultConstructorMarker) null);
    }

    public final void setReactionTexts(int i) {
        withReactionTexts(i);
    }

    public final Drawable getTextBackground() {
        return this.textBackground;
    }

    public final void setTextBackground(Drawable drawable) {
        this.textBackground = drawable;
    }

    public final int getTextColor() {
        return this.textColor;
    }

    public final void setTextColor(int i) {
        this.textColor = i;
    }

    public final int getTextHorizontalPadding() {
        return this.textHorizontalPadding;
    }

    public final void setTextHorizontalPadding(int i) {
        this.textHorizontalPadding = i;
    }

    public final int getTextVerticalPadding() {
        return this.textVerticalPadding;
    }

    public final void setTextVerticalPadding(int i) {
        this.textVerticalPadding = i;
    }

    public final float getTextSize() {
        return this.textSize;
    }

    public final void setTextSize(float f) {
        this.textSize = f;
    }

    public final ReactionsConfigBuilder withReactions(Collection<Reaction> collection) {
        Intrinsics.checkNotNullParameter(collection, "reactions");
        ReactionsConfigBuilder reactionsConfigBuilder = this;
        this.reactions = collection;
        return reactionsConfigBuilder;
    }

    public static /* synthetic */ ReactionsConfigBuilder withReactions$default(ReactionsConfigBuilder reactionsConfigBuilder, int[] iArr, ImageView.ScaleType scaleType, int i, Object obj) {
        if ((i & 2) != 0) {
            scaleType = ImageView.ScaleType.FIT_CENTER;
        }
        return reactionsConfigBuilder.withReactions(iArr, scaleType);
    }

    public final ReactionsConfigBuilder withReactionTexts(Function1<? super Integer, ? extends CharSequence> function1) {
        Intrinsics.checkNotNullParameter(function1, "reactionTextProvider");
        ReactionsConfigBuilder reactionsConfigBuilder = this;
        this.reactionTextProvider = function1;
        return reactionsConfigBuilder;
    }

    public final ReactionsConfigBuilder withReactionTexts(int i) {
        ReactionsConfigBuilder reactionsConfigBuilder = this;
        this.reactionTextProvider = new ReactionsConfigBuilder$withReactionTexts$2$1(this.context.getResources().getStringArray(i));
        return reactionsConfigBuilder;
    }

    public final ReactionsConfigBuilder withReactionSize(int i) {
        ReactionsConfigBuilder reactionsConfigBuilder = this;
        this.reactionSize = i;
        return reactionsConfigBuilder;
    }

    public final ReactionsConfigBuilder withHorizontalMargin(int i) {
        ReactionsConfigBuilder reactionsConfigBuilder = this;
        this.horizontalMargin = i;
        return reactionsConfigBuilder;
    }

    public final ReactionsConfigBuilder withVerticalMargin(int i) {
        ReactionsConfigBuilder reactionsConfigBuilder = this;
        this.verticalMargin = i;
        return reactionsConfigBuilder;
    }

    public final ReactionsConfigBuilder withPopupGravity(PopupGravity popupGravity2) {
        Intrinsics.checkNotNullParameter(popupGravity2, "popupGravity");
        ReactionsConfigBuilder reactionsConfigBuilder = this;
        this.popupGravity = popupGravity2;
        return reactionsConfigBuilder;
    }

    public final ReactionsConfigBuilder withPopupMargin(int i) {
        ReactionsConfigBuilder reactionsConfigBuilder = this;
        this.popupMargin = i;
        return reactionsConfigBuilder;
    }

    public final ReactionsConfigBuilder withPopupCornerRadius(int i) {
        ReactionsConfigBuilder reactionsConfigBuilder = this;
        this.popupCornerRadius = i;
        return reactionsConfigBuilder;
    }

    public final ReactionsConfigBuilder withPopupColor(int i) {
        ReactionsConfigBuilder reactionsConfigBuilder = this;
        this.popupColor = i;
        return reactionsConfigBuilder;
    }

    public final ReactionsConfigBuilder withPopupAlpha(int i) {
        ReactionsConfigBuilder reactionsConfigBuilder = this;
        this.popupAlpha = i;
        return reactionsConfigBuilder;
    }

    public final ReactionsConfigBuilder withTextBackground(Drawable drawable) {
        Intrinsics.checkNotNullParameter(drawable, "textBackground");
        ReactionsConfigBuilder reactionsConfigBuilder = this;
        this.textBackground = drawable;
        return reactionsConfigBuilder;
    }

    public final ReactionsConfigBuilder withTextColor(int i) {
        ReactionsConfigBuilder reactionsConfigBuilder = this;
        this.textColor = i;
        return reactionsConfigBuilder;
    }

    public final ReactionsConfigBuilder withTextHorizontalPadding(int i) {
        ReactionsConfigBuilder reactionsConfigBuilder = this;
        this.textHorizontalPadding = i;
        return reactionsConfigBuilder;
    }

    public final ReactionsConfigBuilder withTextVerticalPadding(int i) {
        ReactionsConfigBuilder reactionsConfigBuilder = this;
        this.textVerticalPadding = i;
        return reactionsConfigBuilder;
    }

    public final ReactionsConfigBuilder withTextSize(float f) {
        ReactionsConfigBuilder reactionsConfigBuilder = this;
        this.textSize = f;
        return reactionsConfigBuilder;
    }

    public final ReactionsConfig build() {
        int i;
        int i2;
        float f;
        Collection<Reaction> collection = this.reactions;
        boolean z = true;
        Float f2 = null;
        Collection<Reaction> collection2 = collection.isEmpty() ^ true ? collection : null;
        if (collection2 != null) {
            PopupGravity popupGravity2 = this.popupGravity;
            int i3 = this.popupMargin;
            int i4 = this.popupCornerRadius;
            int i5 = this.popupColor;
            int i6 = this.popupAlpha;
            int i7 = this.reactionSize;
            int i8 = this.horizontalMargin;
            int i9 = this.verticalMargin;
            Function1<? super Integer, ? extends CharSequence> function1 = this.reactionTextProvider;
            Drawable drawable = this.textBackground;
            if (drawable == null) {
                drawable = ContextCompat.getDrawable(this.context, C0865R.C0867drawable.reactions_text_background);
                Intrinsics.checkNotNull(drawable);
                Intrinsics.checkNotNullExpressionValue(drawable, "ContextCompat.getDrawabl…ctions_text_background)!!");
            }
            Drawable drawable2 = drawable;
            int i10 = this.textColor;
            Integer valueOf = Integer.valueOf(this.textHorizontalPadding);
            if (!(valueOf.intValue() != 0)) {
                valueOf = null;
            }
            if (valueOf != null) {
                i = valueOf.intValue();
            } else {
                i = MathKt.roundToInt(this.context.getResources().getDimension(C0865R.dimen.reactions_text_horizontal_padding));
            }
            int i11 = i;
            Integer valueOf2 = Integer.valueOf(this.textVerticalPadding);
            if (!(valueOf2.intValue() != 0)) {
                valueOf2 = null;
            }
            if (valueOf2 != null) {
                i2 = valueOf2.intValue();
            } else {
                i2 = MathKt.roundToInt(this.context.getResources().getDimension(C0865R.dimen.reactions_text_vertical_padding));
            }
            int i12 = i2;
            Float valueOf3 = Float.valueOf(this.textSize);
            if (valueOf3.floatValue() == 0.0f) {
                z = false;
            }
            if (z) {
                f2 = valueOf3;
            }
            if (f2 != null) {
                f = f2.floatValue();
            } else {
                f = this.context.getResources().getDimension(C0865R.dimen.reactions_text_size);
            }
            return new ReactionsConfig(collection2, i7, i8, i9, popupGravity2, i3, i4, i5, i6, function1, drawable2, i10, i11, i12, f);
        }
        throw new IllegalArgumentException("Empty reactions");
    }

    public final ReactionsConfigBuilder withReactions(int[] iArr, ImageView.ScaleType scaleType) {
        Intrinsics.checkNotNullParameter(iArr, "res");
        Intrinsics.checkNotNullParameter(scaleType, "scaleType");
        Collection arrayList = new ArrayList(iArr.length);
        for (int drawable : iArr) {
            Drawable drawable2 = ContextCompat.getDrawable(this.context, drawable);
            Intrinsics.checkNotNull(drawable2);
            Intrinsics.checkNotNullExpressionValue(drawable2, "ContextCompat.getDrawable(context, it)!!");
            arrayList.add(new Reaction(drawable2, scaleType));
        }
        return withReactions((Collection<Reaction>) (List) arrayList);
    }
}

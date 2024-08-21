package com.github.pgreze.reactions;

import android.graphics.drawable.Drawable;
import java.util.Collection;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo37158bv = {1, 0, 3}, mo37159d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u001e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\r\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0007\n\u0002\b'\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\b\u0018\u00002\u00020\u0001B°\u0001\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\b\b\u0001\u0010\u0005\u001a\u00020\u0006\u0012\b\b\u0001\u0010\u0007\u001a\u00020\u0006\u0012\b\b\u0001\u0010\b\u001a\u00020\u0006\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\u0006\u0012\u0006\u0010\f\u001a\u00020\u0006\u0012\b\b\u0001\u0010\r\u001a\u00020\u0006\u0012\b\b\u0001\u0010\u000e\u001a\u00020\u0006\u0012'\u0010\u000f\u001a#\u0012\u0013\u0012\u00110\u0006¢\u0006\f\b\u0011\u0012\b\b\u0012\u0012\u0004\b\b(\u0013\u0012\u0006\u0012\u0004\u0018\u00010\u00140\u0010j\u0002`\u0015\u0012\u0006\u0010\u0016\u001a\u00020\u0017\u0012\b\b\u0001\u0010\u0018\u001a\u00020\u0006\u0012\u0006\u0010\u0019\u001a\u00020\u0006\u0012\u0006\u0010\u001a\u001a\u00020\u0006\u0012\u0006\u0010\u001b\u001a\u00020\u001c¢\u0006\u0002\u0010\u001dJ\u000f\u00103\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0003J*\u00104\u001a#\u0012\u0013\u0012\u00110\u0006¢\u0006\f\b\u0011\u0012\b\b\u0012\u0012\u0004\b\b(\u0013\u0012\u0006\u0012\u0004\u0018\u00010\u00140\u0010j\u0002`\u0015HÆ\u0003J\t\u00105\u001a\u00020\u0017HÆ\u0003J\t\u00106\u001a\u00020\u0006HÆ\u0003J\t\u00107\u001a\u00020\u0006HÆ\u0003J\t\u00108\u001a\u00020\u0006HÆ\u0003J\t\u00109\u001a\u00020\u001cHÆ\u0003J\t\u0010:\u001a\u00020\u0006HÆ\u0003J\t\u0010;\u001a\u00020\u0006HÆ\u0003J\t\u0010<\u001a\u00020\u0006HÆ\u0003J\t\u0010=\u001a\u00020\nHÆ\u0003J\t\u0010>\u001a\u00020\u0006HÆ\u0003J\t\u0010?\u001a\u00020\u0006HÆ\u0003J\t\u0010@\u001a\u00020\u0006HÆ\u0003J\t\u0010A\u001a\u00020\u0006HÆ\u0003JÆ\u0001\u0010B\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0003\u0010\u0005\u001a\u00020\u00062\b\b\u0003\u0010\u0007\u001a\u00020\u00062\b\b\u0003\u0010\b\u001a\u00020\u00062\b\b\u0002\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\u00062\b\b\u0002\u0010\f\u001a\u00020\u00062\b\b\u0003\u0010\r\u001a\u00020\u00062\b\b\u0003\u0010\u000e\u001a\u00020\u00062)\b\u0002\u0010\u000f\u001a#\u0012\u0013\u0012\u00110\u0006¢\u0006\f\b\u0011\u0012\b\b\u0012\u0012\u0004\b\b(\u0013\u0012\u0006\u0012\u0004\u0018\u00010\u00140\u0010j\u0002`\u00152\b\b\u0002\u0010\u0016\u001a\u00020\u00172\b\b\u0003\u0010\u0018\u001a\u00020\u00062\b\b\u0002\u0010\u0019\u001a\u00020\u00062\b\b\u0002\u0010\u001a\u001a\u00020\u00062\b\b\u0002\u0010\u001b\u001a\u00020\u001cHÆ\u0001J\u0013\u0010C\u001a\u00020D2\b\u0010E\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010F\u001a\u00020\u0006HÖ\u0001J\t\u0010G\u001a\u00020HHÖ\u0001R\u0011\u0010\u0007\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001fR\u0011\u0010\u000e\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b \u0010\u001fR\u0011\u0010\r\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\u001fR\u0011\u0010\f\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010\u001fR\u0011\u0010\t\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b#\u0010$R\u0011\u0010\u000b\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b%\u0010\u001fR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b&\u0010\u001fR2\u0010\u000f\u001a#\u0012\u0013\u0012\u00110\u0006¢\u0006\f\b\u0011\u0012\b\b\u0012\u0012\u0004\b\b(\u0013\u0012\u0006\u0012\u0004\u0018\u00010\u00140\u0010j\u0002`\u0015¢\u0006\b\n\u0000\u001a\u0004\b'\u0010(R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b)\u0010*R\u0011\u0010\u0016\u001a\u00020\u0017¢\u0006\b\n\u0000\u001a\u0004\b+\u0010,R\u0011\u0010\u0018\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b-\u0010\u001fR\u0011\u0010\u0019\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b.\u0010\u001fR\u0011\u0010\u001b\u001a\u00020\u001c¢\u0006\b\n\u0000\u001a\u0004\b/\u00100R\u0011\u0010\u001a\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b1\u0010\u001fR\u0011\u0010\b\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b2\u0010\u001f¨\u0006I"}, mo37160d2 = {"Lcom/github/pgreze/reactions/ReactionsConfig;", "", "reactions", "", "Lcom/github/pgreze/reactions/Reaction;", "reactionSize", "", "horizontalMargin", "verticalMargin", "popupGravity", "Lcom/github/pgreze/reactions/PopupGravity;", "popupMargin", "popupCornerRadius", "popupColor", "popupAlphaValue", "reactionTextProvider", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "position", "", "Lcom/github/pgreze/reactions/ReactionTextProvider;", "textBackground", "Landroid/graphics/drawable/Drawable;", "textColor", "textHorizontalPadding", "textVerticalPadding", "textSize", "", "(Ljava/util/Collection;IIILcom/github/pgreze/reactions/PopupGravity;IIIILkotlin/jvm/functions/Function1;Landroid/graphics/drawable/Drawable;IIIF)V", "getHorizontalMargin", "()I", "getPopupAlphaValue", "getPopupColor", "getPopupCornerRadius", "getPopupGravity", "()Lcom/github/pgreze/reactions/PopupGravity;", "getPopupMargin", "getReactionSize", "getReactionTextProvider", "()Lkotlin/jvm/functions/Function1;", "getReactions", "()Ljava/util/Collection;", "getTextBackground", "()Landroid/graphics/drawable/Drawable;", "getTextColor", "getTextHorizontalPadding", "getTextSize", "()F", "getTextVerticalPadding", "getVerticalMargin", "component1", "component10", "component11", "component12", "component13", "component14", "component15", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "", "other", "hashCode", "toString", "", "library_release"}, mo37161k = 1, mo37162mv = {1, 4, 0})
/* compiled from: model.kt */
public final class ReactionsConfig {
    private final int horizontalMargin;
    private final int popupAlphaValue;
    private final int popupColor;
    private final int popupCornerRadius;
    private final PopupGravity popupGravity;
    private final int popupMargin;
    private final int reactionSize;
    private final Function1<Integer, CharSequence> reactionTextProvider;
    private final Collection<Reaction> reactions;
    private final Drawable textBackground;
    private final int textColor;
    private final int textHorizontalPadding;
    private final float textSize;
    private final int textVerticalPadding;
    private final int verticalMargin;

    public static /* synthetic */ ReactionsConfig copy$default(ReactionsConfig reactionsConfig, Collection collection, int i, int i2, int i3, PopupGravity popupGravity2, int i4, int i5, int i6, int i7, Function1 function1, Drawable drawable, int i8, int i9, int i10, float f, int i11, Object obj) {
        ReactionsConfig reactionsConfig2 = reactionsConfig;
        int i12 = i11;
        return reactionsConfig.copy((i12 & 1) != 0 ? reactionsConfig2.reactions : collection, (i12 & 2) != 0 ? reactionsConfig2.reactionSize : i, (i12 & 4) != 0 ? reactionsConfig2.horizontalMargin : i2, (i12 & 8) != 0 ? reactionsConfig2.verticalMargin : i3, (i12 & 16) != 0 ? reactionsConfig2.popupGravity : popupGravity2, (i12 & 32) != 0 ? reactionsConfig2.popupMargin : i4, (i12 & 64) != 0 ? reactionsConfig2.popupCornerRadius : i5, (i12 & 128) != 0 ? reactionsConfig2.popupColor : i6, (i12 & 256) != 0 ? reactionsConfig2.popupAlphaValue : i7, (i12 & 512) != 0 ? reactionsConfig2.reactionTextProvider : function1, (i12 & 1024) != 0 ? reactionsConfig2.textBackground : drawable, (i12 & 2048) != 0 ? reactionsConfig2.textColor : i8, (i12 & 4096) != 0 ? reactionsConfig2.textHorizontalPadding : i9, (i12 & 8192) != 0 ? reactionsConfig2.textVerticalPadding : i10, (i12 & 16384) != 0 ? reactionsConfig2.textSize : f);
    }

    public final Collection<Reaction> component1() {
        return this.reactions;
    }

    public final Function1<Integer, CharSequence> component10() {
        return this.reactionTextProvider;
    }

    public final Drawable component11() {
        return this.textBackground;
    }

    public final int component12() {
        return this.textColor;
    }

    public final int component13() {
        return this.textHorizontalPadding;
    }

    public final int component14() {
        return this.textVerticalPadding;
    }

    public final float component15() {
        return this.textSize;
    }

    public final int component2() {
        return this.reactionSize;
    }

    public final int component3() {
        return this.horizontalMargin;
    }

    public final int component4() {
        return this.verticalMargin;
    }

    public final PopupGravity component5() {
        return this.popupGravity;
    }

    public final int component6() {
        return this.popupMargin;
    }

    public final int component7() {
        return this.popupCornerRadius;
    }

    public final int component8() {
        return this.popupColor;
    }

    public final int component9() {
        return this.popupAlphaValue;
    }

    public final ReactionsConfig copy(Collection<Reaction> collection, int i, int i2, int i3, PopupGravity popupGravity2, int i4, int i5, int i6, int i7, Function1<? super Integer, ? extends CharSequence> function1, Drawable drawable, int i8, int i9, int i10, float f) {
        Collection<Reaction> collection2 = collection;
        Intrinsics.checkNotNullParameter(collection2, "reactions");
        PopupGravity popupGravity3 = popupGravity2;
        Intrinsics.checkNotNullParameter(popupGravity3, "popupGravity");
        Function1<? super Integer, ? extends CharSequence> function12 = function1;
        Intrinsics.checkNotNullParameter(function12, "reactionTextProvider");
        Drawable drawable2 = drawable;
        Intrinsics.checkNotNullParameter(drawable2, "textBackground");
        return new ReactionsConfig(collection2, i, i2, i3, popupGravity3, i4, i5, i6, i7, function12, drawable2, i8, i9, i10, f);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ReactionsConfig)) {
            return false;
        }
        ReactionsConfig reactionsConfig = (ReactionsConfig) obj;
        return Intrinsics.areEqual((Object) this.reactions, (Object) reactionsConfig.reactions) && this.reactionSize == reactionsConfig.reactionSize && this.horizontalMargin == reactionsConfig.horizontalMargin && this.verticalMargin == reactionsConfig.verticalMargin && Intrinsics.areEqual((Object) this.popupGravity, (Object) reactionsConfig.popupGravity) && this.popupMargin == reactionsConfig.popupMargin && this.popupCornerRadius == reactionsConfig.popupCornerRadius && this.popupColor == reactionsConfig.popupColor && this.popupAlphaValue == reactionsConfig.popupAlphaValue && Intrinsics.areEqual((Object) this.reactionTextProvider, (Object) reactionsConfig.reactionTextProvider) && Intrinsics.areEqual((Object) this.textBackground, (Object) reactionsConfig.textBackground) && this.textColor == reactionsConfig.textColor && this.textHorizontalPadding == reactionsConfig.textHorizontalPadding && this.textVerticalPadding == reactionsConfig.textVerticalPadding && Float.compare(this.textSize, reactionsConfig.textSize) == 0;
    }

    public int hashCode() {
        Collection<Reaction> collection = this.reactions;
        int i = 0;
        int hashCode = (((((((collection != null ? collection.hashCode() : 0) * 31) + this.reactionSize) * 31) + this.horizontalMargin) * 31) + this.verticalMargin) * 31;
        PopupGravity popupGravity2 = this.popupGravity;
        int hashCode2 = (((((((((hashCode + (popupGravity2 != null ? popupGravity2.hashCode() : 0)) * 31) + this.popupMargin) * 31) + this.popupCornerRadius) * 31) + this.popupColor) * 31) + this.popupAlphaValue) * 31;
        Function1<Integer, CharSequence> function1 = this.reactionTextProvider;
        int hashCode3 = (hashCode2 + (function1 != null ? function1.hashCode() : 0)) * 31;
        Drawable drawable = this.textBackground;
        if (drawable != null) {
            i = drawable.hashCode();
        }
        return ((((((((hashCode3 + i) * 31) + this.textColor) * 31) + this.textHorizontalPadding) * 31) + this.textVerticalPadding) * 31) + Float.floatToIntBits(this.textSize);
    }

    public String toString() {
        return "ReactionsConfig(reactions=" + this.reactions + ", reactionSize=" + this.reactionSize + ", horizontalMargin=" + this.horizontalMargin + ", verticalMargin=" + this.verticalMargin + ", popupGravity=" + this.popupGravity + ", popupMargin=" + this.popupMargin + ", popupCornerRadius=" + this.popupCornerRadius + ", popupColor=" + this.popupColor + ", popupAlphaValue=" + this.popupAlphaValue + ", reactionTextProvider=" + this.reactionTextProvider + ", textBackground=" + this.textBackground + ", textColor=" + this.textColor + ", textHorizontalPadding=" + this.textHorizontalPadding + ", textVerticalPadding=" + this.textVerticalPadding + ", textSize=" + this.textSize + ")";
    }

    public ReactionsConfig(Collection<Reaction> collection, int i, int i2, int i3, PopupGravity popupGravity2, int i4, int i5, int i6, int i7, Function1<? super Integer, ? extends CharSequence> function1, Drawable drawable, int i8, int i9, int i10, float f) {
        Function1<? super Integer, ? extends CharSequence> function12 = function1;
        Drawable drawable2 = drawable;
        Intrinsics.checkNotNullParameter(collection, "reactions");
        Intrinsics.checkNotNullParameter(popupGravity2, "popupGravity");
        Intrinsics.checkNotNullParameter(function12, "reactionTextProvider");
        Intrinsics.checkNotNullParameter(drawable2, "textBackground");
        this.reactions = collection;
        this.reactionSize = i;
        this.horizontalMargin = i2;
        this.verticalMargin = i3;
        this.popupGravity = popupGravity2;
        this.popupMargin = i4;
        this.popupCornerRadius = i5;
        this.popupColor = i6;
        this.popupAlphaValue = i7;
        this.reactionTextProvider = function12;
        this.textBackground = drawable2;
        this.textColor = i8;
        this.textHorizontalPadding = i9;
        this.textVerticalPadding = i10;
        this.textSize = f;
    }

    public final Collection<Reaction> getReactions() {
        return this.reactions;
    }

    public final int getReactionSize() {
        return this.reactionSize;
    }

    public final int getHorizontalMargin() {
        return this.horizontalMargin;
    }

    public final int getVerticalMargin() {
        return this.verticalMargin;
    }

    public final PopupGravity getPopupGravity() {
        return this.popupGravity;
    }

    public final int getPopupMargin() {
        return this.popupMargin;
    }

    public final int getPopupCornerRadius() {
        return this.popupCornerRadius;
    }

    public final int getPopupColor() {
        return this.popupColor;
    }

    public final int getPopupAlphaValue() {
        return this.popupAlphaValue;
    }

    public final Function1<Integer, CharSequence> getReactionTextProvider() {
        return this.reactionTextProvider;
    }

    public final Drawable getTextBackground() {
        return this.textBackground;
    }

    public final int getTextColor() {
        return this.textColor;
    }

    public final int getTextHorizontalPadding() {
        return this.textHorizontalPadding;
    }

    public final int getTextVerticalPadding() {
        return this.textVerticalPadding;
    }

    public final float getTextSize() {
        return this.textSize;
    }
}

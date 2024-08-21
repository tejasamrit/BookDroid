package com.github.pgreze.reactions;

import android.graphics.drawable.Drawable;
import android.widget.ImageView;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo37158bv = {1, 0, 3}, mo37159d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\t\u0010\f\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0011\u001a\u00020\u0012HÖ\u0001J\t\u0010\u0013\u001a\u00020\u0014HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0015"}, mo37160d2 = {"Lcom/github/pgreze/reactions/Reaction;", "", "image", "Landroid/graphics/drawable/Drawable;", "scaleType", "Landroid/widget/ImageView$ScaleType;", "(Landroid/graphics/drawable/Drawable;Landroid/widget/ImageView$ScaleType;)V", "getImage", "()Landroid/graphics/drawable/Drawable;", "getScaleType", "()Landroid/widget/ImageView$ScaleType;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "", "library_release"}, mo37161k = 1, mo37162mv = {1, 4, 0})
/* compiled from: model.kt */
public final class Reaction {
    private final Drawable image;
    private final ImageView.ScaleType scaleType;

    public static /* synthetic */ Reaction copy$default(Reaction reaction, Drawable drawable, ImageView.ScaleType scaleType2, int i, Object obj) {
        if ((i & 1) != 0) {
            drawable = reaction.image;
        }
        if ((i & 2) != 0) {
            scaleType2 = reaction.scaleType;
        }
        return reaction.copy(drawable, scaleType2);
    }

    public final Drawable component1() {
        return this.image;
    }

    public final ImageView.ScaleType component2() {
        return this.scaleType;
    }

    public final Reaction copy(Drawable drawable, ImageView.ScaleType scaleType2) {
        Intrinsics.checkNotNullParameter(drawable, "image");
        Intrinsics.checkNotNullParameter(scaleType2, "scaleType");
        return new Reaction(drawable, scaleType2);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Reaction)) {
            return false;
        }
        Reaction reaction = (Reaction) obj;
        return Intrinsics.areEqual((Object) this.image, (Object) reaction.image) && Intrinsics.areEqual((Object) this.scaleType, (Object) reaction.scaleType);
    }

    public int hashCode() {
        Drawable drawable = this.image;
        int i = 0;
        int hashCode = (drawable != null ? drawable.hashCode() : 0) * 31;
        ImageView.ScaleType scaleType2 = this.scaleType;
        if (scaleType2 != null) {
            i = scaleType2.hashCode();
        }
        return hashCode + i;
    }

    public String toString() {
        return "Reaction(image=" + this.image + ", scaleType=" + this.scaleType + ")";
    }

    public Reaction(Drawable drawable, ImageView.ScaleType scaleType2) {
        Intrinsics.checkNotNullParameter(drawable, "image");
        Intrinsics.checkNotNullParameter(scaleType2, "scaleType");
        this.image = drawable;
        this.scaleType = scaleType2;
    }

    public final Drawable getImage() {
        return this.image;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ Reaction(Drawable drawable, ImageView.ScaleType scaleType2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(drawable, (i & 2) != 0 ? ImageView.ScaleType.FIT_CENTER : scaleType2);
    }

    public final ImageView.ScaleType getScaleType() {
        return this.scaleType;
    }
}

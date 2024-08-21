package com.github.pgreze.reactions;

import android.content.Context;
import android.graphics.Point;
import android.widget.ImageView;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo37158bv = {1, 0, 3}, mo37159d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\b\u0007\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J0\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u0014\u001a\u00020\u00122\u0006\u0010\u0015\u001a\u00020\u0012H\u0015R\u0013\u0010\u0007\u001a\u00020\b8F¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\u0016"}, mo37160d2 = {"Lcom/github/pgreze/reactions/ReactionView;", "Landroid/widget/ImageView;", "context", "Landroid/content/Context;", "reaction", "Lcom/github/pgreze/reactions/Reaction;", "(Landroid/content/Context;Lcom/github/pgreze/reactions/Reaction;)V", "location", "Landroid/graphics/Point;", "getLocation", "()Landroid/graphics/Point;", "getReaction", "()Lcom/github/pgreze/reactions/Reaction;", "onLayout", "", "changed", "", "left", "", "top", "right", "bottom", "library_release"}, mo37161k = 1, mo37162mv = {1, 4, 0})
/* compiled from: ReactionView.kt */
public final class ReactionView extends ImageView {
    private final Point location = new Point();
    private final Reaction reaction;

    public final Reaction getReaction() {
        return this.reaction;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public ReactionView(Context context, Reaction reaction2) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(reaction2, "reaction");
        this.reaction = reaction2;
        setScaleType(reaction2.getScaleType());
        setImageDrawable(reaction2.getImage());
    }

    public final Point getLocation() {
        if (this.location.x == 0 || this.location.y == 0) {
            int[] iArr = new int[2];
            getLocationOnScreen(iArr);
            this.location.set(iArr[0], iArr[1]);
        }
        return this.location;
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        getLocation().set(0, 0);
    }
}

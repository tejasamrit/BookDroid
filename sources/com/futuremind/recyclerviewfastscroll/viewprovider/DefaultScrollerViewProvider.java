package com.futuremind.recyclerviewfastscroll.viewprovider;

import android.graphics.drawable.InsetDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import com.futuremind.recyclerviewfastscroll.C0860R;
import com.futuremind.recyclerviewfastscroll.Utils;
import com.futuremind.recyclerviewfastscroll.viewprovider.VisibilityAnimationManager;

public class DefaultScrollerViewProvider extends ScrollerViewProvider {
    private View bubble;
    private View handle;

    /* access modifiers changed from: protected */
    public ViewBehavior provideHandleBehavior() {
        return null;
    }

    public View provideHandleView(ViewGroup viewGroup) {
        this.handle = new View(getContext());
        int dimensionPixelSize = getScroller().isVertical() ? 0 : getContext().getResources().getDimensionPixelSize(C0860R.dimen.fastscroll__handle_inset);
        int dimensionPixelSize2 = !getScroller().isVertical() ? 0 : getContext().getResources().getDimensionPixelSize(C0860R.dimen.fastscroll__handle_inset);
        Utils.setBackground(this.handle, new InsetDrawable(ContextCompat.getDrawable(getContext(), C0860R.C0862drawable.fastscroll__default_handle), dimensionPixelSize2, dimensionPixelSize, dimensionPixelSize2, dimensionPixelSize));
        this.handle.setLayoutParams(new ViewGroup.LayoutParams(getContext().getResources().getDimensionPixelSize(getScroller().isVertical() ? C0860R.dimen.fastscroll__handle_clickable_width : C0860R.dimen.fastscroll__handle_height), getContext().getResources().getDimensionPixelSize(getScroller().isVertical() ? C0860R.dimen.fastscroll__handle_height : C0860R.dimen.fastscroll__handle_clickable_width)));
        return this.handle;
    }

    public View provideBubbleView(ViewGroup viewGroup) {
        View inflate = LayoutInflater.from(getContext()).inflate(C0860R.layout.fastscroll__default_bubble, viewGroup, false);
        this.bubble = inflate;
        return inflate;
    }

    public TextView provideBubbleTextView() {
        return (TextView) this.bubble;
    }

    public int getBubbleOffset() {
        int i;
        float f;
        if (getScroller().isVertical()) {
            f = ((float) this.handle.getHeight()) / 2.0f;
            i = this.bubble.getHeight();
        } else {
            f = ((float) this.handle.getWidth()) / 2.0f;
            i = this.bubble.getWidth();
        }
        return (int) (f - ((float) i));
    }

    /* access modifiers changed from: protected */
    public ViewBehavior provideBubbleBehavior() {
        return new DefaultBubbleBehavior(new VisibilityAnimationManager.Builder(this.bubble).withPivotX(1.0f).withPivotY(1.0f).build());
    }
}

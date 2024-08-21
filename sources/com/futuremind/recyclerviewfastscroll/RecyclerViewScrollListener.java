package com.futuremind.recyclerviewfastscroll;

import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class RecyclerViewScrollListener extends RecyclerView.OnScrollListener {
    List<ScrollerListener> listeners = new ArrayList();
    int oldScrollState = 0;
    private final FastScroller scroller;

    public interface ScrollerListener {
        void onScroll(float f);
    }

    public RecyclerViewScrollListener(FastScroller fastScroller) {
        this.scroller = fastScroller;
    }

    public void addScrollerListener(ScrollerListener scrollerListener) {
        this.listeners.add(scrollerListener);
    }

    public void onScrollStateChanged(RecyclerView recyclerView, int i) {
        super.onScrollStateChanged(recyclerView, i);
        if (i == 0 && this.oldScrollState != 0) {
            this.scroller.getViewProvider().onScrollFinished();
        } else if (i != 0 && this.oldScrollState == 0) {
            this.scroller.getViewProvider().onScrollStarted();
        }
        this.oldScrollState = i;
    }

    public void onScrolled(RecyclerView recyclerView, int i, int i2) {
        if (this.scroller.shouldUpdateHandlePosition()) {
            updateHandlePosition(recyclerView);
        }
    }

    /* access modifiers changed from: package-private */
    public void updateHandlePosition(RecyclerView recyclerView) {
        int i;
        int i2;
        int i3;
        if (this.scroller.isVertical()) {
            i3 = recyclerView.computeVerticalScrollOffset();
            i2 = recyclerView.computeVerticalScrollExtent();
            i = recyclerView.computeVerticalScrollRange();
        } else {
            i3 = recyclerView.computeHorizontalScrollOffset();
            i2 = recyclerView.computeHorizontalScrollExtent();
            i = recyclerView.computeHorizontalScrollRange();
        }
        float f = ((float) i3) / ((float) (i - i2));
        this.scroller.setScrollerPosition(f);
        notifyListeners(f);
    }

    public void notifyListeners(float f) {
        for (ScrollerListener onScroll : this.listeners) {
            onScroll.onScroll(f);
        }
    }
}

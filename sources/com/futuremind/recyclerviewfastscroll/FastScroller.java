package com.futuremind.recyclerviewfastscroll;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.core.widget.TextViewCompat;
import androidx.recyclerview.widget.RecyclerView;
import com.futuremind.recyclerviewfastscroll.RecyclerViewScrollListener;
import com.futuremind.recyclerviewfastscroll.viewprovider.DefaultScrollerViewProvider;
import com.futuremind.recyclerviewfastscroll.viewprovider.ScrollerViewProvider;

public class FastScroller extends LinearLayout {
    private static final int STYLE_NONE = -1;
    private View bubble;
    private int bubbleColor;
    private int bubbleOffset;
    private int bubbleTextAppearance;
    private TextView bubbleTextView;
    private View handle;
    private int handleColor;
    /* access modifiers changed from: private */
    public boolean manuallyChangingPosition;
    private int maxVisibility;
    private RecyclerView recyclerView;
    private final RecyclerViewScrollListener scrollListener;
    private int scrollerOrientation;
    /* access modifiers changed from: private */
    public SectionTitleProvider titleProvider;
    /* access modifiers changed from: private */
    public ScrollerViewProvider viewProvider;

    public FastScroller(Context context) {
        this(context, (AttributeSet) null);
    }

    public FastScroller(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    /* JADX INFO: finally extract failed */
    public FastScroller(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.scrollListener = new RecyclerViewScrollListener(this);
        setClipChildren(false);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, C0860R.styleable.fastscroll__fastScroller, C0860R.attr.fastscroll__style, 0);
        try {
            this.bubbleColor = obtainStyledAttributes.getColor(C0860R.styleable.fastscroll__fastScroller_fastscroll__bubbleColor, -1);
            this.handleColor = obtainStyledAttributes.getColor(C0860R.styleable.fastscroll__fastScroller_fastscroll__handleColor, -1);
            this.bubbleTextAppearance = obtainStyledAttributes.getResourceId(C0860R.styleable.fastscroll__fastScroller_fastscroll__bubbleTextAppearance, -1);
            obtainStyledAttributes.recycle();
            this.maxVisibility = getVisibility();
            setViewProvider(new DefaultScrollerViewProvider());
        } catch (Throwable th) {
            obtainStyledAttributes.recycle();
            throw th;
        }
    }

    public void setViewProvider(ScrollerViewProvider scrollerViewProvider) {
        removeAllViews();
        this.viewProvider = scrollerViewProvider;
        scrollerViewProvider.setFastScroller(this);
        this.bubble = scrollerViewProvider.provideBubbleView(this);
        this.handle = scrollerViewProvider.provideHandleView(this);
        this.bubbleTextView = scrollerViewProvider.provideBubbleTextView();
        addView(this.bubble);
        addView(this.handle);
    }

    public void setRecyclerView(RecyclerView recyclerView2) {
        this.recyclerView = recyclerView2;
        if (recyclerView2.getAdapter() instanceof SectionTitleProvider) {
            this.titleProvider = (SectionTitleProvider) recyclerView2.getAdapter();
        }
        recyclerView2.addOnScrollListener(this.scrollListener);
        invalidateVisibility();
        recyclerView2.setOnHierarchyChangeListener(new ViewGroup.OnHierarchyChangeListener() {
            public void onChildViewAdded(View view, View view2) {
                FastScroller.this.invalidateVisibility();
            }

            public void onChildViewRemoved(View view, View view2) {
                FastScroller.this.invalidateVisibility();
            }
        });
    }

    public void setOrientation(int i) {
        this.scrollerOrientation = i;
        super.setOrientation(i == 0 ? 1 : 0);
    }

    public void setBubbleColor(int i) {
        this.bubbleColor = i;
        invalidate();
    }

    public void setHandleColor(int i) {
        this.handleColor = i;
        invalidate();
    }

    public void setBubbleTextAppearance(int i) {
        this.bubbleTextAppearance = i;
        invalidate();
    }

    public void addScrollerListener(RecyclerViewScrollListener.ScrollerListener scrollerListener) {
        this.scrollListener.addScrollerListener(scrollerListener);
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        initHandleMovement();
        this.bubbleOffset = this.viewProvider.getBubbleOffset();
        applyStyling();
        this.scrollListener.updateHandlePosition(this.recyclerView);
    }

    private void applyStyling() {
        int i = this.bubbleColor;
        if (i != -1) {
            setBackgroundTint(this.bubbleTextView, i);
        }
        int i2 = this.handleColor;
        if (i2 != -1) {
            setBackgroundTint(this.handle, i2);
        }
        int i3 = this.bubbleTextAppearance;
        if (i3 != -1) {
            TextViewCompat.setTextAppearance(this.bubbleTextView, i3);
        }
    }

    private void setBackgroundTint(View view, int i) {
        Drawable wrap = DrawableCompat.wrap(view.getBackground());
        if (wrap != null) {
            DrawableCompat.setTint(wrap.mutate(), i);
            Utils.setBackground(view, wrap);
        }
    }

    private void initHandleMovement() {
        this.handle.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View view, MotionEvent motionEvent) {
                FastScroller.this.requestDisallowInterceptTouchEvent(true);
                if (motionEvent.getAction() == 0 || motionEvent.getAction() == 2) {
                    if (FastScroller.this.titleProvider != null && motionEvent.getAction() == 0) {
                        FastScroller.this.viewProvider.onHandleGrabbed();
                    }
                    boolean unused = FastScroller.this.manuallyChangingPosition = true;
                    float access$400 = FastScroller.this.getRelativeTouchPosition(motionEvent);
                    FastScroller.this.setScrollerPosition(access$400);
                    FastScroller.this.setRecyclerViewPosition(access$400);
                    return true;
                } else if (motionEvent.getAction() != 1) {
                    return false;
                } else {
                    boolean unused2 = FastScroller.this.manuallyChangingPosition = false;
                    if (FastScroller.this.titleProvider != null) {
                        FastScroller.this.viewProvider.onHandleReleased();
                    }
                    return true;
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public float getRelativeTouchPosition(MotionEvent motionEvent) {
        float rawX;
        int width;
        int width2;
        if (isVertical()) {
            rawX = motionEvent.getRawY() - Utils.getViewRawY(this.handle);
            width = getHeight();
            width2 = this.handle.getHeight();
        } else {
            rawX = motionEvent.getRawX() - Utils.getViewRawX(this.handle);
            width = getWidth();
            width2 = this.handle.getWidth();
        }
        return rawX / ((float) (width - width2));
    }

    public void setVisibility(int i) {
        this.maxVisibility = i;
        invalidateVisibility();
    }

    /* access modifiers changed from: private */
    public void invalidateVisibility() {
        if (this.recyclerView.getAdapter() == null || this.recyclerView.getAdapter().getItemCount() == 0 || this.recyclerView.getChildAt(0) == null || isRecyclerViewNotScrollable() || this.maxVisibility != 0) {
            super.setVisibility(4);
        } else {
            super.setVisibility(0);
        }
    }

    private boolean isRecyclerViewNotScrollable() {
        if (isVertical()) {
            if (this.recyclerView.getChildAt(0).getHeight() * this.recyclerView.getAdapter().getItemCount() <= this.recyclerView.getHeight()) {
                return true;
            }
            return false;
        } else if (this.recyclerView.getChildAt(0).getWidth() * this.recyclerView.getAdapter().getItemCount() <= this.recyclerView.getWidth()) {
            return true;
        } else {
            return false;
        }
    }

    /* access modifiers changed from: private */
    public void setRecyclerViewPosition(float f) {
        TextView textView;
        RecyclerView recyclerView2 = this.recyclerView;
        if (recyclerView2 != null) {
            int itemCount = recyclerView2.getAdapter().getItemCount();
            int valueInRange = (int) Utils.getValueInRange(0.0f, (float) (itemCount - 1), (float) ((int) (f * ((float) itemCount))));
            this.recyclerView.scrollToPosition(valueInRange);
            SectionTitleProvider sectionTitleProvider = this.titleProvider;
            if (sectionTitleProvider != null && (textView = this.bubbleTextView) != null) {
                textView.setText(sectionTitleProvider.getSectionTitle(valueInRange));
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void setScrollerPosition(float f) {
        if (isVertical()) {
            this.bubble.setY(Utils.getValueInRange(0.0f, (float) (getHeight() - this.bubble.getHeight()), (((float) (getHeight() - this.handle.getHeight())) * f) + ((float) this.bubbleOffset)));
            this.handle.setY(Utils.getValueInRange(0.0f, (float) (getHeight() - this.handle.getHeight()), f * ((float) (getHeight() - this.handle.getHeight()))));
            return;
        }
        this.bubble.setX(Utils.getValueInRange(0.0f, (float) (getWidth() - this.bubble.getWidth()), (((float) (getWidth() - this.handle.getWidth())) * f) + ((float) this.bubbleOffset)));
        this.handle.setX(Utils.getValueInRange(0.0f, (float) (getWidth() - this.handle.getWidth()), f * ((float) (getWidth() - this.handle.getWidth()))));
    }

    public boolean isVertical() {
        return this.scrollerOrientation == 1;
    }

    /* access modifiers changed from: package-private */
    public boolean shouldUpdateHandlePosition() {
        return this.handle != null && !this.manuallyChangingPosition && this.recyclerView.getChildCount() > 0;
    }

    /* access modifiers changed from: package-private */
    public ScrollerViewProvider getViewProvider() {
        return this.viewProvider;
    }
}

package com.smarteist.autoimageslider.IndicatorView;

import android.content.Context;
import android.database.DataSetObserver;
import android.graphics.Canvas;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.Pair;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import androidx.core.text.TextUtilsCompat;
import androidx.viewpager.widget.PagerAdapter;
import com.smarteist.autoimageslider.IndicatorView.IndicatorManager;
import com.smarteist.autoimageslider.IndicatorView.animation.data.Value;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.IndicatorView.draw.controller.DrawController;
import com.smarteist.autoimageslider.IndicatorView.draw.data.Indicator;
import com.smarteist.autoimageslider.IndicatorView.draw.data.Orientation;
import com.smarteist.autoimageslider.IndicatorView.draw.data.PositionSavedState;
import com.smarteist.autoimageslider.IndicatorView.draw.data.RtlMode;
import com.smarteist.autoimageslider.IndicatorView.utils.CoordinatesUtils;
import com.smarteist.autoimageslider.IndicatorView.utils.DensityUtils;
import com.smarteist.autoimageslider.IndicatorView.utils.IdUtils;
import com.smarteist.autoimageslider.InfiniteAdapter.InfinitePagerAdapter;
import com.smarteist.autoimageslider.SliderPager;

public class PageIndicatorView extends View implements SliderPager.OnPageChangeListener, IndicatorManager.C2253Listener, SliderPager.OnAdapterChangeListener {
    private boolean isInteractionEnabled;
    private IndicatorManager manager;
    private DataSetObserver setObserver;
    private SliderPager viewPager;

    public PageIndicatorView(Context context) {
        super(context);
        init((AttributeSet) null);
    }

    public PageIndicatorView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init(attributeSet);
    }

    public PageIndicatorView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init(attributeSet);
    }

    public PageIndicatorView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        init(attributeSet);
    }

    /* access modifiers changed from: protected */
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        findViewPager(getParent());
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        unRegisterSetObserver();
        super.onDetachedFromWindow();
    }

    public Parcelable onSaveInstanceState() {
        Indicator indicator = this.manager.indicator();
        PositionSavedState positionSavedState = new PositionSavedState(super.onSaveInstanceState());
        positionSavedState.setSelectedPosition(indicator.getSelectedPosition());
        positionSavedState.setSelectingPosition(indicator.getSelectingPosition());
        positionSavedState.setLastSelectedPosition(indicator.getLastSelectedPosition());
        return positionSavedState;
    }

    public void onRestoreInstanceState(Parcelable parcelable) {
        if (parcelable instanceof PositionSavedState) {
            Indicator indicator = this.manager.indicator();
            PositionSavedState positionSavedState = (PositionSavedState) parcelable;
            indicator.setSelectedPosition(positionSavedState.getSelectedPosition());
            indicator.setSelectingPosition(positionSavedState.getSelectingPosition());
            indicator.setLastSelectedPosition(positionSavedState.getLastSelectedPosition());
            super.onRestoreInstanceState(positionSavedState.getSuperState());
            return;
        }
        super.onRestoreInstanceState(parcelable);
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        Pair<Integer, Integer> measureViewSize = this.manager.drawer().measureViewSize(i, i2);
        setMeasuredDimension(((Integer) measureViewSize.first).intValue(), ((Integer) measureViewSize.second).intValue());
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        this.manager.drawer().draw(canvas);
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        this.manager.drawer().touch(motionEvent);
        return true;
    }

    public void onIndicatorUpdated() {
        invalidate();
    }

    public void onPageScrolled(int i, float f, int i2) {
        onPageScroll(i, f);
    }

    public void onPageSelected(int i) {
        onPageSelect(i);
    }

    public void onPageScrollStateChanged(int i) {
        if (i == 0) {
            this.manager.indicator().setInteractiveAnimation(this.isInteractionEnabled);
        }
    }

    public void onAdapterChanged(SliderPager sliderPager, PagerAdapter pagerAdapter, PagerAdapter pagerAdapter2) {
        updateState();
    }

    public void setCount(int i) {
        if (i >= 0 && this.manager.indicator().getCount() != i) {
            this.manager.indicator().setCount(i);
            updateVisibility();
            requestLayout();
        }
    }

    public int getCount() {
        return this.manager.indicator().getCount();
    }

    public void setDynamicCount(boolean z) {
        this.manager.indicator().setDynamicCount(z);
        if (z) {
            registerSetObserver();
        } else {
            unRegisterSetObserver();
        }
    }

    public void setRadius(int i) {
        if (i < 0) {
            i = 0;
        }
        this.manager.indicator().setRadius(DensityUtils.dpToPx(i));
        invalidate();
    }

    public void setRadius(float f) {
        if (f < 0.0f) {
            f = 0.0f;
        }
        this.manager.indicator().setRadius((int) f);
        invalidate();
    }

    public int getRadius() {
        return this.manager.indicator().getRadius();
    }

    public void setPadding(int i) {
        if (i < 0) {
            i = 0;
        }
        this.manager.indicator().setPadding(DensityUtils.dpToPx(i));
        invalidate();
    }

    public void setPadding(float f) {
        if (f < 0.0f) {
            f = 0.0f;
        }
        this.manager.indicator().setPadding((int) f);
        invalidate();
    }

    public int getPadding() {
        return this.manager.indicator().getPadding();
    }

    public void setScaleFactor(float f) {
        if (f > 1.0f) {
            f = 1.0f;
        } else if (f < 0.3f) {
            f = 0.3f;
        }
        this.manager.indicator().setScaleFactor(f);
    }

    public float getScaleFactor() {
        return this.manager.indicator().getScaleFactor();
    }

    public void setStrokeWidth(float f) {
        int radius = this.manager.indicator().getRadius();
        if (f < 0.0f) {
            f = 0.0f;
        } else {
            float f2 = (float) radius;
            if (f > f2) {
                f = f2;
            }
        }
        this.manager.indicator().setStroke((int) f);
        invalidate();
    }

    public void setStrokeWidth(int i) {
        int dpToPx = DensityUtils.dpToPx(i);
        int radius = this.manager.indicator().getRadius();
        if (dpToPx < 0) {
            dpToPx = 0;
        } else if (dpToPx > radius) {
            dpToPx = radius;
        }
        this.manager.indicator().setStroke(dpToPx);
        invalidate();
    }

    public int getStrokeWidth() {
        return this.manager.indicator().getStroke();
    }

    public void setSelectedColor(int i) {
        this.manager.indicator().setSelectedColor(i);
        invalidate();
    }

    public int getSelectedColor() {
        return this.manager.indicator().getSelectedColor();
    }

    public void setUnselectedColor(int i) {
        this.manager.indicator().setUnselectedColor(i);
        invalidate();
    }

    public int getUnselectedColor() {
        return this.manager.indicator().getUnselectedColor();
    }

    public void setAutoVisibility(boolean z) {
        if (!z) {
            setVisibility(0);
        }
        this.manager.indicator().setAutoVisibility(z);
        updateVisibility();
    }

    public void setOrientation(Orientation orientation) {
        if (orientation != null) {
            this.manager.indicator().setOrientation(orientation);
            requestLayout();
        }
    }

    public void setAnimationDuration(long j) {
        this.manager.indicator().setAnimationDuration(j);
    }

    public long getAnimationDuration() {
        return this.manager.indicator().getAnimationDuration();
    }

    public void setAnimationType(IndicatorAnimationType indicatorAnimationType) {
        this.manager.onValueUpdated((Value) null);
        if (indicatorAnimationType != null) {
            this.manager.indicator().setAnimationType(indicatorAnimationType);
        } else {
            this.manager.indicator().setAnimationType(IndicatorAnimationType.NONE);
        }
        invalidate();
    }

    public void setInteractiveAnimation(boolean z) {
        this.manager.indicator().setInteractiveAnimation(z);
        this.isInteractionEnabled = z;
    }

    public void setViewPager(SliderPager sliderPager) {
        releaseViewPager();
        if (sliderPager != null) {
            this.viewPager = sliderPager;
            sliderPager.addOnPageChangeListener(this);
            this.viewPager.addOnAdapterChangeListener(this);
            this.manager.indicator().setViewPagerId(this.viewPager.getId());
            setDynamicCount(this.manager.indicator().isDynamicCount());
            updateState();
        }
    }

    public void releaseViewPager() {
        SliderPager sliderPager = this.viewPager;
        if (sliderPager != null) {
            sliderPager.removeOnPageChangeListener(this);
            this.viewPager = null;
        }
    }

    public void setRtlMode(RtlMode rtlMode) {
        Indicator indicator = this.manager.indicator();
        if (rtlMode == null) {
            indicator.setRtlMode(RtlMode.Off);
        } else {
            indicator.setRtlMode(rtlMode);
        }
        if (this.viewPager != null) {
            int selectedPosition = indicator.getSelectedPosition();
            if (isRtl()) {
                selectedPosition = (indicator.getCount() - 1) - selectedPosition;
            } else {
                SliderPager sliderPager = this.viewPager;
                if (sliderPager != null) {
                    selectedPosition = sliderPager.getCurrentItem();
                }
            }
            indicator.setLastSelectedPosition(selectedPosition);
            indicator.setSelectingPosition(selectedPosition);
            indicator.setSelectedPosition(selectedPosition);
            invalidate();
        }
    }

    public int getSelection() {
        return this.manager.indicator().getSelectedPosition();
    }

    public void setSelection(int i) {
        Indicator indicator = this.manager.indicator();
        int adjustPosition = adjustPosition(i);
        if (adjustPosition != indicator.getSelectedPosition() && adjustPosition != indicator.getSelectingPosition()) {
            indicator.setInteractiveAnimation(false);
            indicator.setLastSelectedPosition(indicator.getSelectedPosition());
            indicator.setSelectingPosition(adjustPosition);
            indicator.setSelectedPosition(adjustPosition);
            this.manager.animate().basic();
        }
    }

    public void setSelected(int i) {
        Indicator indicator = this.manager.indicator();
        IndicatorAnimationType animationType = indicator.getAnimationType();
        indicator.setAnimationType(IndicatorAnimationType.NONE);
        setSelection(i);
        indicator.setAnimationType(animationType);
    }

    public void clearSelection() {
        Indicator indicator = this.manager.indicator();
        indicator.setInteractiveAnimation(false);
        indicator.setLastSelectedPosition(-1);
        indicator.setSelectingPosition(-1);
        indicator.setSelectedPosition(-1);
        this.manager.animate().basic();
    }

    public void setProgress(int i, float f) {
        Indicator indicator = this.manager.indicator();
        if (indicator.isInteractiveAnimation()) {
            int count = indicator.getCount();
            if (count <= 0 || i < 0) {
                i = 0;
            } else {
                int i2 = count - 1;
                if (i > i2) {
                    i = i2;
                }
            }
            if (f < 0.0f) {
                f = 0.0f;
            } else if (f > 1.0f) {
                f = 1.0f;
            }
            if (f == 1.0f) {
                indicator.setLastSelectedPosition(indicator.getSelectedPosition());
                indicator.setSelectedPosition(i);
            }
            indicator.setSelectingPosition(i);
            this.manager.animate().interactive(f);
        }
    }

    public void setClickListener(DrawController.ClickListener clickListener) {
        this.manager.drawer().setClickListener(clickListener);
    }

    private void init(AttributeSet attributeSet) {
        setupId();
        initIndicatorManager(attributeSet);
    }

    private void setupId() {
        if (getId() == -1) {
            setId(IdUtils.generateViewId());
        }
    }

    private void initIndicatorManager(AttributeSet attributeSet) {
        IndicatorManager indicatorManager = new IndicatorManager(this);
        this.manager = indicatorManager;
        indicatorManager.drawer().initAttributes(getContext(), attributeSet);
        Indicator indicator = this.manager.indicator();
        indicator.setPaddingLeft(getPaddingLeft());
        indicator.setPaddingTop(getPaddingTop());
        indicator.setPaddingRight(getPaddingRight());
        indicator.setPaddingBottom(getPaddingBottom());
        this.isInteractionEnabled = indicator.isInteractiveAnimation();
    }

    private void registerSetObserver() {
        SliderPager sliderPager;
        if (this.setObserver == null && (sliderPager = this.viewPager) != null && sliderPager.getAdapter() != null) {
            this.setObserver = new DataSetObserver() {
                public void onChanged() {
                    PageIndicatorView.this.updateState();
                }
            };
            try {
                this.viewPager.getAdapter().registerDataSetObserver(this.setObserver);
            } catch (IllegalStateException e) {
                e.printStackTrace();
            }
        }
    }

    private void unRegisterSetObserver() {
        SliderPager sliderPager;
        if (this.setObserver != null && (sliderPager = this.viewPager) != null && sliderPager.getAdapter() != null) {
            try {
                this.viewPager.getAdapter().unregisterDataSetObserver(this.setObserver);
                this.setObserver = null;
            } catch (IllegalStateException e) {
                e.printStackTrace();
            }
        }
    }

    /* access modifiers changed from: private */
    public void updateState() {
        int i;
        int i2;
        SliderPager sliderPager = this.viewPager;
        if (sliderPager != null && sliderPager.getAdapter() != null) {
            if (this.viewPager.getAdapter() instanceof InfinitePagerAdapter) {
                i2 = ((InfinitePagerAdapter) this.viewPager.getAdapter()).getRealCount();
                i = i2 > 0 ? this.viewPager.getCurrentItem() % i2 : 0;
            } else {
                i2 = this.viewPager.getAdapter().getCount();
                i = this.viewPager.getCurrentItem();
            }
            if (isRtl()) {
                i = (i2 - 1) - i;
            }
            this.manager.indicator().setSelectedPosition(i);
            this.manager.indicator().setSelectingPosition(i);
            this.manager.indicator().setLastSelectedPosition(i);
            this.manager.indicator().setCount(i2);
            this.manager.animate().end();
            updateVisibility();
            requestLayout();
        }
    }

    private void updateVisibility() {
        if (this.manager.indicator().isAutoVisibility()) {
            int count = this.manager.indicator().getCount();
            int visibility = getVisibility();
            if (visibility != 0 && count > 1) {
                setVisibility(0);
            } else if (visibility != 4 && count <= 1) {
                setVisibility(4);
            }
        }
    }

    private void onPageSelect(int i) {
        Indicator indicator = this.manager.indicator();
        boolean isViewMeasured = isViewMeasured();
        int count = indicator.getCount();
        if (isViewMeasured) {
            if (isRtl()) {
                i = (count - 1) - i;
            }
            setSelection(i);
        }
    }

    private void onPageScroll(int i, float f) {
        Indicator indicator = this.manager.indicator();
        if (isViewMeasured() && indicator.isInteractiveAnimation() && indicator.getAnimationType() != IndicatorAnimationType.NONE) {
            Pair<Integer, Float> progress = CoordinatesUtils.getProgress(indicator, i, f, isRtl());
            setProgress(((Integer) progress.first).intValue(), ((Float) progress.second).floatValue());
        }
    }

    /* renamed from: com.smarteist.autoimageslider.IndicatorView.PageIndicatorView$2 */
    static /* synthetic */ class C22552 {

        /* renamed from: $SwitchMap$com$smarteist$autoimageslider$IndicatorView$draw$data$RtlMode */
        static final /* synthetic */ int[] f478xeb2b66b3;

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|(3:5|6|8)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        static {
            /*
                com.smarteist.autoimageslider.IndicatorView.draw.data.RtlMode[] r0 = com.smarteist.autoimageslider.IndicatorView.draw.data.RtlMode.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                f478xeb2b66b3 = r0
                com.smarteist.autoimageslider.IndicatorView.draw.data.RtlMode r1 = com.smarteist.autoimageslider.IndicatorView.draw.data.RtlMode.On     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = f478xeb2b66b3     // Catch:{ NoSuchFieldError -> 0x001d }
                com.smarteist.autoimageslider.IndicatorView.draw.data.RtlMode r1 = com.smarteist.autoimageslider.IndicatorView.draw.data.RtlMode.Off     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = f478xeb2b66b3     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.smarteist.autoimageslider.IndicatorView.draw.data.RtlMode r1 = com.smarteist.autoimageslider.IndicatorView.draw.data.RtlMode.Auto     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.smarteist.autoimageslider.IndicatorView.PageIndicatorView.C22552.<clinit>():void");
        }
    }

    private boolean isRtl() {
        int i = C22552.f478xeb2b66b3[this.manager.indicator().getRtlMode().ordinal()];
        if (i == 1) {
            return true;
        }
        if (i == 3 && TextUtilsCompat.getLayoutDirectionFromLocale(getContext().getResources().getConfiguration().locale) == 1) {
            return true;
        }
        return false;
    }

    private boolean isViewMeasured() {
        return (getMeasuredHeight() == 0 && getMeasuredWidth() == 0) ? false : true;
    }

    private void findViewPager(ViewParent viewParent) {
        if (viewParent != null && (viewParent instanceof ViewGroup) && ((ViewGroup) viewParent).getChildCount() > 0) {
            SliderPager findViewPager = findViewPager((ViewGroup) viewParent, this.manager.indicator().getViewPagerId());
            if (findViewPager != null) {
                setViewPager(findViewPager);
            } else {
                findViewPager(viewParent.getParent());
            }
        }
    }

    private SliderPager findViewPager(ViewGroup viewGroup, int i) {
        View findViewById;
        if (viewGroup.getChildCount() > 0 && (findViewById = viewGroup.findViewById(i)) != null && (findViewById instanceof SliderPager)) {
            return (SliderPager) findViewById;
        }
        return null;
    }

    private int adjustPosition(int i) {
        int count = this.manager.indicator().getCount() - 1;
        if (i <= 0) {
            return 0;
        }
        return i > count ? count : i;
    }
}

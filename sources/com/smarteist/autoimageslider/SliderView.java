package com.smarteist.autoimageslider;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Interpolator;
import android.widget.FrameLayout;
import androidx.core.view.ViewCompat;
import androidx.viewpager.widget.PagerAdapter;
import com.smarteist.autoimageslider.IndicatorView.PageIndicatorView;
import com.smarteist.autoimageslider.IndicatorView.animation.type.BaseAnimation;
import com.smarteist.autoimageslider.IndicatorView.animation.type.ColorAnimation;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.IndicatorView.draw.controller.AttributeController;
import com.smarteist.autoimageslider.IndicatorView.draw.controller.DrawController;
import com.smarteist.autoimageslider.IndicatorView.draw.data.Orientation;
import com.smarteist.autoimageslider.IndicatorView.draw.data.RtlMode;
import com.smarteist.autoimageslider.IndicatorView.utils.DensityUtils;
import com.smarteist.autoimageslider.InfiniteAdapter.InfinitePagerAdapter;
import com.smarteist.autoimageslider.SliderPager;
import com.smarteist.autoimageslider.SliderViewAdapter;
import com.smarteist.autoimageslider.Transformations.AntiClockSpinTransformation;
import com.smarteist.autoimageslider.Transformations.Clock_SpinTransformation;
import com.smarteist.autoimageslider.Transformations.CubeInDepthTransformation;
import com.smarteist.autoimageslider.Transformations.CubeInRotationTransformation;
import com.smarteist.autoimageslider.Transformations.CubeInScalingTransformation;
import com.smarteist.autoimageslider.Transformations.CubeOutDepthTransformation;
import com.smarteist.autoimageslider.Transformations.CubeOutRotationTransformation;
import com.smarteist.autoimageslider.Transformations.CubeOutScalingTransformation;
import com.smarteist.autoimageslider.Transformations.DepthTransformation;
import com.smarteist.autoimageslider.Transformations.FadeTransformation;
import com.smarteist.autoimageslider.Transformations.FanTransformation;
import com.smarteist.autoimageslider.Transformations.FidgetSpinTransformation;
import com.smarteist.autoimageslider.Transformations.GateTransformation;
import com.smarteist.autoimageslider.Transformations.HingeTransformation;
import com.smarteist.autoimageslider.Transformations.HorizontalFlipTransformation;
import com.smarteist.autoimageslider.Transformations.PopTransformation;
import com.smarteist.autoimageslider.Transformations.SimpleTransformation;
import com.smarteist.autoimageslider.Transformations.SpinnerTransformation;
import com.smarteist.autoimageslider.Transformations.TossTransformation;
import com.smarteist.autoimageslider.Transformations.VerticalFlipTransformation;
import com.smarteist.autoimageslider.Transformations.VerticalShutTransformation;
import com.smarteist.autoimageslider.Transformations.ZoomOutTransformation;
import java.util.Objects;

public class SliderView extends FrameLayout implements Runnable, View.OnTouchListener, SliderViewAdapter.DataSetListener, SliderPager.OnPageChangeListener {
    public static final int AUTO_CYCLE_DIRECTION_BACK_AND_FORTH = 2;
    public static final int AUTO_CYCLE_DIRECTION_LEFT = 1;
    public static final int AUTO_CYCLE_DIRECTION_RIGHT = 0;
    public static final String TAG = "Slider View : ";
    private int mAutoCycleDirection;
    private boolean mFlagBackAndForth;
    private final Handler mHandler = new Handler();
    private InfinitePagerAdapter mInfinitePagerAdapter;
    private boolean mIsAutoCycle;
    private boolean mIsIndicatorEnabled = true;
    private boolean mIsInfiniteAdapter = true;
    private OnSliderPageListener mPageListener;
    private SliderViewAdapter mPagerAdapter;
    private PageIndicatorView mPagerIndicator;
    private int mPreviousPosition = -1;
    private int mScrollTimeInMillis;
    private SliderPager mSliderPager;

    public interface OnSliderPageListener {
        void onSliderPageChanged(int i);
    }

    public void onPageScrollStateChanged(int i) {
    }

    public void onPageScrolled(int i, float f, int i2) {
    }

    public SliderView(Context context) {
        super(context);
        setupSlideView(context);
    }

    public SliderView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        setupSlideView(context);
        setUpAttributes(context, attributeSet);
    }

    public SliderView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        setupSlideView(context);
        setUpAttributes(context, attributeSet);
    }

    private void setUpAttributes(Context context, AttributeSet attributeSet) {
        Orientation orientation;
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, C2268R.styleable.SliderView, 0, 0);
        boolean z = obtainStyledAttributes.getBoolean(C2268R.styleable.SliderView_sliderIndicatorEnabled, true);
        int i = obtainStyledAttributes.getInt(C2268R.styleable.SliderView_sliderAnimationDuration, 250);
        int i2 = obtainStyledAttributes.getInt(C2268R.styleable.SliderView_sliderScrollTimeInSec, 2);
        boolean z2 = obtainStyledAttributes.getBoolean(C2268R.styleable.SliderView_sliderAutoCycleEnabled, true);
        boolean z3 = obtainStyledAttributes.getBoolean(C2268R.styleable.SliderView_sliderStartAutoCycle, false);
        int i3 = obtainStyledAttributes.getInt(C2268R.styleable.SliderView_sliderAutoCycleDirection, 0);
        setSliderAnimationDuration(i);
        setScrollTimeInSec(i2);
        setAutoCycle(z2);
        setAutoCycleDirection(i3);
        setAutoCycle(z3);
        setIndicatorEnabled(z);
        if (this.mIsIndicatorEnabled) {
            initIndicator();
            if (obtainStyledAttributes.getInt(C2268R.styleable.SliderView_sliderIndicatorOrientation, Orientation.HORIZONTAL.ordinal()) == 0) {
                orientation = Orientation.HORIZONTAL;
            } else {
                orientation = Orientation.VERTICAL;
            }
            int dimension = (int) obtainStyledAttributes.getDimension(C2268R.styleable.SliderView_sliderIndicatorMarginLeft, (float) DensityUtils.dpToPx(12));
            int dimension2 = (int) obtainStyledAttributes.getDimension(C2268R.styleable.SliderView_sliderIndicatorMarginTop, (float) DensityUtils.dpToPx(12));
            int dimension3 = (int) obtainStyledAttributes.getDimension(C2268R.styleable.SliderView_sliderIndicatorMarginRight, (float) DensityUtils.dpToPx(12));
            int dimension4 = (int) obtainStyledAttributes.getDimension(C2268R.styleable.SliderView_sliderIndicatorMarginBottom, (float) DensityUtils.dpToPx(12));
            int i4 = obtainStyledAttributes.getInt(C2268R.styleable.SliderView_sliderIndicatorGravity, 81);
            int color = obtainStyledAttributes.getColor(C2268R.styleable.SliderView_sliderIndicatorUnselectedColor, Color.parseColor(ColorAnimation.DEFAULT_UNSELECTED_COLOR));
            int color2 = obtainStyledAttributes.getColor(C2268R.styleable.SliderView_sliderIndicatorSelectedColor, Color.parseColor(ColorAnimation.DEFAULT_SELECTED_COLOR));
            int i5 = obtainStyledAttributes.getInt(C2268R.styleable.SliderView_sliderIndicatorAnimationDuration, BaseAnimation.DEFAULT_ANIMATION_TIME);
            RtlMode rtlMode = AttributeController.getRtlMode(obtainStyledAttributes.getInt(C2268R.styleable.SliderView_sliderIndicatorRtlMode, RtlMode.Off.ordinal()));
            setIndicatorOrientation(orientation);
            setIndicatorRadius((int) obtainStyledAttributes.getDimension(C2268R.styleable.SliderView_sliderIndicatorRadius, (float) DensityUtils.dpToPx(2)));
            setIndicatorPadding((int) obtainStyledAttributes.getDimension(C2268R.styleable.SliderView_sliderIndicatorPadding, (float) DensityUtils.dpToPx(3)));
            setIndicatorMargin((int) obtainStyledAttributes.getDimension(C2268R.styleable.SliderView_sliderIndicatorMargin, (float) DensityUtils.dpToPx(12)));
            setIndicatorMarginCustom(dimension, dimension2, dimension3, dimension4);
            setIndicatorGravity(i4);
            setIndicatorMargins(dimension, dimension2, dimension3, dimension4);
            setIndicatorUnselectedColor(color);
            setIndicatorSelectedColor(color2);
            setIndicatorAnimationDuration((long) i5);
            setIndicatorRtlMode(rtlMode);
        }
        obtainStyledAttributes.recycle();
    }

    private void initIndicator() {
        if (this.mPagerIndicator == null) {
            this.mPagerIndicator = new PageIndicatorView(getContext());
            FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-2, -2);
            layoutParams.gravity = 81;
            layoutParams.setMargins(20, 20, 20, 20);
            addView(this.mPagerIndicator, 1, layoutParams);
        }
        this.mPagerIndicator.setViewPager(this.mSliderPager);
        this.mPagerIndicator.setDynamicCount(true);
    }

    private void setupSlideView(Context context) {
        SliderPager sliderPager = new SliderPager(context);
        this.mSliderPager = sliderPager;
        sliderPager.setOverScrollMode(1);
        this.mSliderPager.setId(ViewCompat.generateViewId());
        addView(this.mSliderPager, 0, new FrameLayout.LayoutParams(-1, -1));
        this.mSliderPager.setOnTouchListener(this);
        this.mSliderPager.addOnPageChangeListener(this);
    }

    public void setOnIndicatorClickListener(DrawController.ClickListener clickListener) {
        this.mPagerIndicator.setClickListener(clickListener);
    }

    public void setCurrentPageListener(OnSliderPageListener onSliderPageListener) {
        this.mPageListener = onSliderPageListener;
    }

    public void setSliderAdapter(SliderViewAdapter sliderViewAdapter) {
        this.mPagerAdapter = sliderViewAdapter;
        InfinitePagerAdapter infinitePagerAdapter = new InfinitePagerAdapter(sliderViewAdapter);
        this.mInfinitePagerAdapter = infinitePagerAdapter;
        this.mSliderPager.setAdapter(infinitePagerAdapter);
        this.mPagerAdapter.dataSetChangedListener(this);
        setCurrentPagePosition(0);
    }

    public void setSliderAdapter(SliderViewAdapter sliderViewAdapter, boolean z) {
        this.mIsInfiniteAdapter = z;
        if (!z) {
            this.mPagerAdapter = sliderViewAdapter;
            this.mSliderPager.setAdapter(sliderViewAdapter);
            return;
        }
        setSliderAdapter(sliderViewAdapter);
    }

    public void setInfiniteAdapterEnabled(boolean z) {
        SliderViewAdapter sliderViewAdapter = this.mPagerAdapter;
        if (sliderViewAdapter != null) {
            setSliderAdapter(sliderViewAdapter, z);
        }
    }

    public SliderPager getSliderPager() {
        return this.mSliderPager;
    }

    public PagerAdapter getSliderAdapter() {
        return this.mPagerAdapter;
    }

    public boolean isAutoCycle() {
        return this.mIsAutoCycle;
    }

    public void setAutoCycle(boolean z) {
        this.mIsAutoCycle = z;
    }

    public void setOffscreenPageLimit(int i) {
        this.mSliderPager.setOffscreenPageLimit(i);
    }

    public int getScrollTimeInSec() {
        return this.mScrollTimeInMillis / 1000;
    }

    public void setScrollTimeInSec(int i) {
        this.mScrollTimeInMillis = i * 1000;
    }

    public int getScrollTimeInMillis() {
        return this.mScrollTimeInMillis;
    }

    public void setScrollTimeInMillis(int i) {
        this.mScrollTimeInMillis = i;
    }

    /* renamed from: com.smarteist.autoimageslider.SliderView$2 */
    static /* synthetic */ class C22792 {
        static final /* synthetic */ int[] $SwitchMap$com$smarteist$autoimageslider$SliderAnimations;

        /* JADX WARNING: Can't wrap try/catch for region: R(42:0|1|2|3|4|5|6|7|8|9|10|11|12|13|14|15|16|17|18|19|20|21|22|23|24|25|26|27|28|29|30|31|32|33|34|35|36|37|38|39|40|(3:41|42|44)) */
        /* JADX WARNING: Can't wrap try/catch for region: R(44:0|1|2|3|4|5|6|7|8|9|10|11|12|13|14|15|16|17|18|19|20|21|22|23|24|25|26|27|28|29|30|31|32|33|34|35|36|37|38|39|40|41|42|44) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x003e */
        /* JADX WARNING: Missing exception handler attribute for start block: B:13:0x0049 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:15:0x0054 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:17:0x0060 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:19:0x006c */
        /* JADX WARNING: Missing exception handler attribute for start block: B:21:0x0078 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:23:0x0084 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:25:0x0090 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:27:0x009c */
        /* JADX WARNING: Missing exception handler attribute for start block: B:29:0x00a8 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:31:0x00b4 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:33:0x00c0 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:35:0x00cc */
        /* JADX WARNING: Missing exception handler attribute for start block: B:37:0x00d8 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:39:0x00e4 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:41:0x00f0 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0033 */
        static {
            /*
                com.smarteist.autoimageslider.SliderAnimations[] r0 = com.smarteist.autoimageslider.SliderAnimations.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$smarteist$autoimageslider$SliderAnimations = r0
                com.smarteist.autoimageslider.SliderAnimations r1 = com.smarteist.autoimageslider.SliderAnimations.ANTICLOCKSPINTRANSFORMATION     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$com$smarteist$autoimageslider$SliderAnimations     // Catch:{ NoSuchFieldError -> 0x001d }
                com.smarteist.autoimageslider.SliderAnimations r1 = com.smarteist.autoimageslider.SliderAnimations.CLOCK_SPINTRANSFORMATION     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$com$smarteist$autoimageslider$SliderAnimations     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.smarteist.autoimageslider.SliderAnimations r1 = com.smarteist.autoimageslider.SliderAnimations.CUBEINDEPTHTRANSFORMATION     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = $SwitchMap$com$smarteist$autoimageslider$SliderAnimations     // Catch:{ NoSuchFieldError -> 0x0033 }
                com.smarteist.autoimageslider.SliderAnimations r1 = com.smarteist.autoimageslider.SliderAnimations.CUBEINROTATIONTRANSFORMATION     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                int[] r0 = $SwitchMap$com$smarteist$autoimageslider$SliderAnimations     // Catch:{ NoSuchFieldError -> 0x003e }
                com.smarteist.autoimageslider.SliderAnimations r1 = com.smarteist.autoimageslider.SliderAnimations.CUBEINSCALINGTRANSFORMATION     // Catch:{ NoSuchFieldError -> 0x003e }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x003e }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x003e }
            L_0x003e:
                int[] r0 = $SwitchMap$com$smarteist$autoimageslider$SliderAnimations     // Catch:{ NoSuchFieldError -> 0x0049 }
                com.smarteist.autoimageslider.SliderAnimations r1 = com.smarteist.autoimageslider.SliderAnimations.CUBEOUTDEPTHTRANSFORMATION     // Catch:{ NoSuchFieldError -> 0x0049 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0049 }
                r2 = 6
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0049 }
            L_0x0049:
                int[] r0 = $SwitchMap$com$smarteist$autoimageslider$SliderAnimations     // Catch:{ NoSuchFieldError -> 0x0054 }
                com.smarteist.autoimageslider.SliderAnimations r1 = com.smarteist.autoimageslider.SliderAnimations.CUBEOUTROTATIONTRANSFORMATION     // Catch:{ NoSuchFieldError -> 0x0054 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0054 }
                r2 = 7
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0054 }
            L_0x0054:
                int[] r0 = $SwitchMap$com$smarteist$autoimageslider$SliderAnimations     // Catch:{ NoSuchFieldError -> 0x0060 }
                com.smarteist.autoimageslider.SliderAnimations r1 = com.smarteist.autoimageslider.SliderAnimations.CUBEOUTSCALINGTRANSFORMATION     // Catch:{ NoSuchFieldError -> 0x0060 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0060 }
                r2 = 8
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0060 }
            L_0x0060:
                int[] r0 = $SwitchMap$com$smarteist$autoimageslider$SliderAnimations     // Catch:{ NoSuchFieldError -> 0x006c }
                com.smarteist.autoimageslider.SliderAnimations r1 = com.smarteist.autoimageslider.SliderAnimations.DEPTHTRANSFORMATION     // Catch:{ NoSuchFieldError -> 0x006c }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x006c }
                r2 = 9
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x006c }
            L_0x006c:
                int[] r0 = $SwitchMap$com$smarteist$autoimageslider$SliderAnimations     // Catch:{ NoSuchFieldError -> 0x0078 }
                com.smarteist.autoimageslider.SliderAnimations r1 = com.smarteist.autoimageslider.SliderAnimations.FADETRANSFORMATION     // Catch:{ NoSuchFieldError -> 0x0078 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0078 }
                r2 = 10
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0078 }
            L_0x0078:
                int[] r0 = $SwitchMap$com$smarteist$autoimageslider$SliderAnimations     // Catch:{ NoSuchFieldError -> 0x0084 }
                com.smarteist.autoimageslider.SliderAnimations r1 = com.smarteist.autoimageslider.SliderAnimations.FANTRANSFORMATION     // Catch:{ NoSuchFieldError -> 0x0084 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0084 }
                r2 = 11
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0084 }
            L_0x0084:
                int[] r0 = $SwitchMap$com$smarteist$autoimageslider$SliderAnimations     // Catch:{ NoSuchFieldError -> 0x0090 }
                com.smarteist.autoimageslider.SliderAnimations r1 = com.smarteist.autoimageslider.SliderAnimations.FIDGETSPINTRANSFORMATION     // Catch:{ NoSuchFieldError -> 0x0090 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0090 }
                r2 = 12
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0090 }
            L_0x0090:
                int[] r0 = $SwitchMap$com$smarteist$autoimageslider$SliderAnimations     // Catch:{ NoSuchFieldError -> 0x009c }
                com.smarteist.autoimageslider.SliderAnimations r1 = com.smarteist.autoimageslider.SliderAnimations.GATETRANSFORMATION     // Catch:{ NoSuchFieldError -> 0x009c }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x009c }
                r2 = 13
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x009c }
            L_0x009c:
                int[] r0 = $SwitchMap$com$smarteist$autoimageslider$SliderAnimations     // Catch:{ NoSuchFieldError -> 0x00a8 }
                com.smarteist.autoimageslider.SliderAnimations r1 = com.smarteist.autoimageslider.SliderAnimations.HINGETRANSFORMATION     // Catch:{ NoSuchFieldError -> 0x00a8 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x00a8 }
                r2 = 14
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x00a8 }
            L_0x00a8:
                int[] r0 = $SwitchMap$com$smarteist$autoimageslider$SliderAnimations     // Catch:{ NoSuchFieldError -> 0x00b4 }
                com.smarteist.autoimageslider.SliderAnimations r1 = com.smarteist.autoimageslider.SliderAnimations.HORIZONTALFLIPTRANSFORMATION     // Catch:{ NoSuchFieldError -> 0x00b4 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x00b4 }
                r2 = 15
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x00b4 }
            L_0x00b4:
                int[] r0 = $SwitchMap$com$smarteist$autoimageslider$SliderAnimations     // Catch:{ NoSuchFieldError -> 0x00c0 }
                com.smarteist.autoimageslider.SliderAnimations r1 = com.smarteist.autoimageslider.SliderAnimations.POPTRANSFORMATION     // Catch:{ NoSuchFieldError -> 0x00c0 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x00c0 }
                r2 = 16
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x00c0 }
            L_0x00c0:
                int[] r0 = $SwitchMap$com$smarteist$autoimageslider$SliderAnimations     // Catch:{ NoSuchFieldError -> 0x00cc }
                com.smarteist.autoimageslider.SliderAnimations r1 = com.smarteist.autoimageslider.SliderAnimations.SPINNERTRANSFORMATION     // Catch:{ NoSuchFieldError -> 0x00cc }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x00cc }
                r2 = 17
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x00cc }
            L_0x00cc:
                int[] r0 = $SwitchMap$com$smarteist$autoimageslider$SliderAnimations     // Catch:{ NoSuchFieldError -> 0x00d8 }
                com.smarteist.autoimageslider.SliderAnimations r1 = com.smarteist.autoimageslider.SliderAnimations.TOSSTRANSFORMATION     // Catch:{ NoSuchFieldError -> 0x00d8 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x00d8 }
                r2 = 18
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x00d8 }
            L_0x00d8:
                int[] r0 = $SwitchMap$com$smarteist$autoimageslider$SliderAnimations     // Catch:{ NoSuchFieldError -> 0x00e4 }
                com.smarteist.autoimageslider.SliderAnimations r1 = com.smarteist.autoimageslider.SliderAnimations.VERTICALFLIPTRANSFORMATION     // Catch:{ NoSuchFieldError -> 0x00e4 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x00e4 }
                r2 = 19
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x00e4 }
            L_0x00e4:
                int[] r0 = $SwitchMap$com$smarteist$autoimageslider$SliderAnimations     // Catch:{ NoSuchFieldError -> 0x00f0 }
                com.smarteist.autoimageslider.SliderAnimations r1 = com.smarteist.autoimageslider.SliderAnimations.VERTICALSHUTTRANSFORMATION     // Catch:{ NoSuchFieldError -> 0x00f0 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x00f0 }
                r2 = 20
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x00f0 }
            L_0x00f0:
                int[] r0 = $SwitchMap$com$smarteist$autoimageslider$SliderAnimations     // Catch:{ NoSuchFieldError -> 0x00fc }
                com.smarteist.autoimageslider.SliderAnimations r1 = com.smarteist.autoimageslider.SliderAnimations.ZOOMOUTTRANSFORMATION     // Catch:{ NoSuchFieldError -> 0x00fc }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x00fc }
                r2 = 21
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x00fc }
            L_0x00fc:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.smarteist.autoimageslider.SliderView.C22792.<clinit>():void");
        }
    }

    public void setSliderTransformAnimation(SliderAnimations sliderAnimations) {
        switch (C22792.$SwitchMap$com$smarteist$autoimageslider$SliderAnimations[sliderAnimations.ordinal()]) {
            case 1:
                this.mSliderPager.setPageTransformer(false, new AntiClockSpinTransformation());
                return;
            case 2:
                this.mSliderPager.setPageTransformer(false, new Clock_SpinTransformation());
                return;
            case 3:
                this.mSliderPager.setPageTransformer(false, new CubeInDepthTransformation());
                return;
            case 4:
                this.mSliderPager.setPageTransformer(false, new CubeInRotationTransformation());
                return;
            case 5:
                this.mSliderPager.setPageTransformer(false, new CubeInScalingTransformation());
                return;
            case 6:
                this.mSliderPager.setPageTransformer(false, new CubeOutDepthTransformation());
                return;
            case 7:
                this.mSliderPager.setPageTransformer(false, new CubeOutRotationTransformation());
                return;
            case 8:
                this.mSliderPager.setPageTransformer(false, new CubeOutScalingTransformation());
                return;
            case 9:
                this.mSliderPager.setPageTransformer(false, new DepthTransformation());
                return;
            case 10:
                this.mSliderPager.setPageTransformer(false, new FadeTransformation());
                return;
            case 11:
                this.mSliderPager.setPageTransformer(false, new FanTransformation());
                return;
            case 12:
                this.mSliderPager.setPageTransformer(false, new FidgetSpinTransformation());
                return;
            case 13:
                this.mSliderPager.setPageTransformer(false, new GateTransformation());
                return;
            case 14:
                this.mSliderPager.setPageTransformer(false, new HingeTransformation());
                return;
            case 15:
                this.mSliderPager.setPageTransformer(false, new HorizontalFlipTransformation());
                return;
            case 16:
                this.mSliderPager.setPageTransformer(false, new PopTransformation());
                return;
            case 17:
                this.mSliderPager.setPageTransformer(false, new SpinnerTransformation());
                return;
            case 18:
                this.mSliderPager.setPageTransformer(false, new TossTransformation());
                return;
            case 19:
                this.mSliderPager.setPageTransformer(false, new VerticalFlipTransformation());
                return;
            case 20:
                this.mSliderPager.setPageTransformer(false, new VerticalShutTransformation());
                return;
            case 21:
                this.mSliderPager.setPageTransformer(false, new ZoomOutTransformation());
                return;
            default:
                this.mSliderPager.setPageTransformer(false, new SimpleTransformation());
                return;
        }
    }

    public boolean onTouch(View view, MotionEvent motionEvent) {
        if (!isAutoCycle()) {
            return false;
        }
        if (motionEvent.getAction() == 2) {
            stopAutoCycle();
            return false;
        } else if (motionEvent.getAction() != 1) {
            return false;
        } else {
            this.mHandler.postDelayed(new Runnable() {
                public void run() {
                    SliderView.this.startAutoCycle();
                }
            }, 2000);
            return false;
        }
    }

    public void setCustomSliderTransformAnimation(SliderPager.PageTransformer pageTransformer) {
        this.mSliderPager.setPageTransformer(false, pageTransformer);
    }

    public void setSliderAnimationDuration(int i) {
        this.mSliderPager.setScrollDuration(i);
    }

    public void setSliderAnimationDuration(int i, Interpolator interpolator) {
        this.mSliderPager.setScrollDuration(i, interpolator);
    }

    public void setCurrentPagePosition(int i) {
        this.mSliderPager.setCurrentItem(i, true);
    }

    public int getCurrentPagePosition() {
        Objects.requireNonNull(getSliderAdapter(), "Adapter not set");
        return getSliderPager().getCurrentItem();
    }

    public PageIndicatorView getPagerIndicator() {
        return this.mPagerIndicator;
    }

    public void setPageIndicatorView(PageIndicatorView pageIndicatorView) {
        this.mPagerIndicator = pageIndicatorView;
        initIndicator();
    }

    public void setIndicatorEnabled(boolean z) {
        this.mIsIndicatorEnabled = z;
        if (this.mPagerIndicator == null && z) {
            initIndicator();
        }
    }

    public void setIndicatorAnimationDuration(long j) {
        this.mPagerIndicator.setAnimationDuration(j);
    }

    public void setIndicatorGravity(int i) {
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) this.mPagerIndicator.getLayoutParams();
        layoutParams.gravity = i;
        this.mPagerIndicator.setLayoutParams(layoutParams);
    }

    public void setIndicatorPadding(int i) {
        this.mPagerIndicator.setPadding(i);
    }

    public void setIndicatorMargins(int i, int i2, int i3, int i4) {
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) this.mPagerIndicator.getLayoutParams();
        layoutParams.setMargins(i, i2, i3, i4);
        this.mPagerIndicator.setLayoutParams(layoutParams);
    }

    public void setIndicatorOrientation(Orientation orientation) {
        this.mPagerIndicator.setOrientation(orientation);
    }

    public void setIndicatorAnimation(IndicatorAnimationType indicatorAnimationType) {
        this.mPagerIndicator.setAnimationType(indicatorAnimationType);
    }

    public void setIndicatorVisibility(boolean z) {
        if (z) {
            this.mPagerIndicator.setVisibility(0);
        } else {
            this.mPagerIndicator.setVisibility(8);
        }
    }

    private int getAdapterItemsCount() {
        try {
            return getSliderAdapter().getCount();
        } catch (NullPointerException unused) {
            Log.e(TAG, "getAdapterItemsCount: Slider Adapter is null so, it can't get count of items");
            return 0;
        }
    }

    public void startAutoCycle() {
        this.mHandler.removeCallbacks(this);
        this.mHandler.postDelayed(this, (long) this.mScrollTimeInMillis);
    }

    public void stopAutoCycle() {
        this.mHandler.removeCallbacks(this);
    }

    public void setAutoCycleDirection(int i) {
        this.mAutoCycleDirection = i;
    }

    public int getAutoCycleDirection() {
        return this.mAutoCycleDirection;
    }

    public int getIndicatorRadius() {
        return this.mPagerIndicator.getRadius();
    }

    public void setIndicatorRtlMode(RtlMode rtlMode) {
        this.mPagerIndicator.setRtlMode(rtlMode);
    }

    public void setIndicatorRadius(int i) {
        this.mPagerIndicator.setRadius(i);
    }

    public void setIndicatorMargin(int i) {
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) this.mPagerIndicator.getLayoutParams();
        layoutParams.setMargins(i, i, i, i);
        this.mPagerIndicator.setLayoutParams(layoutParams);
    }

    public void setIndicatorMarginCustom(int i, int i2, int i3, int i4) {
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) this.mPagerIndicator.getLayoutParams();
        layoutParams.setMargins(i, i2, i3, i4);
        this.mPagerIndicator.setLayoutParams(layoutParams);
    }

    public void setIndicatorSelectedColor(int i) {
        this.mPagerIndicator.setSelectedColor(i);
    }

    public int getIndicatorSelectedColor() {
        return this.mPagerIndicator.getSelectedColor();
    }

    public void setIndicatorUnselectedColor(int i) {
        this.mPagerIndicator.setUnselectedColor(i);
    }

    public int getIndicatorUnselectedColor() {
        return this.mPagerIndicator.getUnselectedColor();
    }

    public void run() {
        try {
            slideToNextPosition();
        } finally {
            if (this.mIsAutoCycle) {
                this.mHandler.postDelayed(this, (long) this.mScrollTimeInMillis);
            }
        }
    }

    public void slideToNextPosition() {
        int currentItem = this.mSliderPager.getCurrentItem();
        int adapterItemsCount = getAdapterItemsCount();
        if (adapterItemsCount > 1) {
            if (this.mAutoCycleDirection == 2) {
                if (!(currentItem % (adapterItemsCount - 1) != 0 || this.mPreviousPosition == getAdapterItemsCount() - 1 || this.mPreviousPosition == 0)) {
                    this.mFlagBackAndForth = !this.mFlagBackAndForth;
                }
                if (this.mFlagBackAndForth) {
                    this.mSliderPager.setCurrentItem(currentItem + 1, true);
                } else {
                    this.mSliderPager.setCurrentItem(currentItem - 1, true);
                }
            }
            if (this.mAutoCycleDirection == 1) {
                this.mSliderPager.setCurrentItem(currentItem - 1, true);
            }
            if (this.mAutoCycleDirection == 0) {
                this.mSliderPager.setCurrentItem(currentItem + 1, true);
            }
        }
        this.mPreviousPosition = currentItem;
    }

    public void slideToPreviousPosition() {
        int currentItem = this.mSliderPager.getCurrentItem();
        int adapterItemsCount = getAdapterItemsCount();
        if (adapterItemsCount > 1) {
            if (this.mAutoCycleDirection == 2) {
                if (!(currentItem % (adapterItemsCount - 1) != 0 || this.mPreviousPosition == getAdapterItemsCount() - 1 || this.mPreviousPosition == 0)) {
                    this.mFlagBackAndForth = !this.mFlagBackAndForth;
                }
                if (!this.mFlagBackAndForth || currentItem >= this.mPreviousPosition) {
                    this.mSliderPager.setCurrentItem(currentItem + 1, true);
                } else {
                    this.mSliderPager.setCurrentItem(currentItem - 1, true);
                }
            }
            if (this.mAutoCycleDirection == 1) {
                this.mSliderPager.setCurrentItem(currentItem + 1, true);
            }
            if (this.mAutoCycleDirection == 0) {
                this.mSliderPager.setCurrentItem(currentItem - 1, true);
            }
        }
        this.mPreviousPosition = currentItem;
    }

    public void dataSetChanged() {
        if (this.mIsInfiniteAdapter) {
            this.mInfinitePagerAdapter.notifyDataSetChanged();
            this.mSliderPager.setCurrentItem(0, false);
        }
    }

    public void onPageSelected(int i) {
        OnSliderPageListener onSliderPageListener = this.mPageListener;
        if (onSliderPageListener != null) {
            onSliderPageListener.onSliderPageChanged(i);
        }
    }
}

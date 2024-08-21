package com.google.android.material.navigation;

import android.app.Activity;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.InsetDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewTreeObserver;
import androidx.appcompat.C0041R;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.appcompat.view.SupportMenuInflater;
import androidx.appcompat.view.menu.MenuItemImpl;
import androidx.appcompat.widget.TintTypedArray;
import androidx.core.content.ContextCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.customview.view.AbsSavedState;
import com.google.android.material.C0907R;
import com.google.android.material.internal.ContextUtils;
import com.google.android.material.internal.NavigationMenu;
import com.google.android.material.internal.NavigationMenuPresenter;
import com.google.android.material.internal.ScrimInsetsFrameLayout;
import com.google.android.material.resources.MaterialResources;
import com.google.android.material.shape.MaterialShapeDrawable;
import com.google.android.material.shape.MaterialShapeUtils;
import com.google.android.material.shape.ShapeAppearanceModel;

public class NavigationView extends ScrimInsetsFrameLayout {
    private static final int[] CHECKED_STATE_SET = {16842912};
    private static final int DEF_STYLE_RES = C0907R.style.Widget_Design_NavigationView;
    private static final int[] DISABLED_STATE_SET = {-16842910};
    private static final int PRESENTER_NAVIGATION_VIEW_ID = 1;
    OnNavigationItemSelectedListener listener;
    private final int maxWidth;
    private final NavigationMenu menu;
    private MenuInflater menuInflater;
    private ViewTreeObserver.OnGlobalLayoutListener onGlobalLayoutListener;
    /* access modifiers changed from: private */
    public final NavigationMenuPresenter presenter;
    /* access modifiers changed from: private */
    public final int[] tmpLocation;

    public interface OnNavigationItemSelectedListener {
        boolean onNavigationItemSelected(MenuItem menuItem);
    }

    public NavigationView(Context context) {
        this(context, (AttributeSet) null);
    }

    public NavigationView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, C0907R.attr.navigationViewStyle);
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public NavigationView(android.content.Context r11, android.util.AttributeSet r12, int r13) {
        /*
            r10 = this;
            int r4 = DEF_STYLE_RES
            android.content.Context r11 = com.google.android.material.theme.overlay.MaterialThemeOverlay.wrap(r11, r12, r13, r4)
            r10.<init>(r11, r12, r13)
            com.google.android.material.internal.NavigationMenuPresenter r11 = new com.google.android.material.internal.NavigationMenuPresenter
            r11.<init>()
            r10.presenter = r11
            r0 = 2
            int[] r0 = new int[r0]
            r10.tmpLocation = r0
            android.content.Context r6 = r10.getContext()
            com.google.android.material.internal.NavigationMenu r7 = new com.google.android.material.internal.NavigationMenu
            r7.<init>(r6)
            r10.menu = r7
            int[] r2 = com.google.android.material.C0907R.styleable.NavigationView
            r8 = 0
            int[] r5 = new int[r8]
            r0 = r6
            r1 = r12
            r3 = r13
            androidx.appcompat.widget.TintTypedArray r12 = com.google.android.material.internal.ThemeEnforcement.obtainTintedStyledAttributes(r0, r1, r2, r3, r4, r5)
            int r13 = com.google.android.material.C0907R.styleable.NavigationView_android_background
            boolean r13 = r12.hasValue(r13)
            if (r13 == 0) goto L_0x003d
            int r13 = com.google.android.material.C0907R.styleable.NavigationView_android_background
            android.graphics.drawable.Drawable r13 = r12.getDrawable(r13)
            androidx.core.view.ViewCompat.setBackground(r10, r13)
        L_0x003d:
            android.graphics.drawable.Drawable r13 = r10.getBackground()
            if (r13 == 0) goto L_0x004b
            android.graphics.drawable.Drawable r13 = r10.getBackground()
            boolean r13 = r13 instanceof android.graphics.drawable.ColorDrawable
            if (r13 == 0) goto L_0x006b
        L_0x004b:
            android.graphics.drawable.Drawable r13 = r10.getBackground()
            com.google.android.material.shape.MaterialShapeDrawable r0 = new com.google.android.material.shape.MaterialShapeDrawable
            r0.<init>()
            boolean r1 = r13 instanceof android.graphics.drawable.ColorDrawable
            if (r1 == 0) goto L_0x0065
            android.graphics.drawable.ColorDrawable r13 = (android.graphics.drawable.ColorDrawable) r13
            int r13 = r13.getColor()
            android.content.res.ColorStateList r13 = android.content.res.ColorStateList.valueOf(r13)
            r0.setFillColor(r13)
        L_0x0065:
            r0.initializeElevationOverlay(r6)
            androidx.core.view.ViewCompat.setBackground(r10, r0)
        L_0x006b:
            int r13 = com.google.android.material.C0907R.styleable.NavigationView_elevation
            boolean r13 = r12.hasValue(r13)
            if (r13 == 0) goto L_0x007d
            int r13 = com.google.android.material.C0907R.styleable.NavigationView_elevation
            int r13 = r12.getDimensionPixelSize(r13, r8)
            float r13 = (float) r13
            r10.setElevation(r13)
        L_0x007d:
            int r13 = com.google.android.material.C0907R.styleable.NavigationView_android_fitsSystemWindows
            boolean r13 = r12.getBoolean(r13, r8)
            r10.setFitsSystemWindows(r13)
            int r13 = com.google.android.material.C0907R.styleable.NavigationView_android_maxWidth
            int r13 = r12.getDimensionPixelSize(r13, r8)
            r10.maxWidth = r13
            int r13 = com.google.android.material.C0907R.styleable.NavigationView_itemIconTint
            boolean r13 = r12.hasValue(r13)
            if (r13 == 0) goto L_0x009d
            int r13 = com.google.android.material.C0907R.styleable.NavigationView_itemIconTint
            android.content.res.ColorStateList r13 = r12.getColorStateList(r13)
            goto L_0x00a4
        L_0x009d:
            r13 = 16842808(0x1010038, float:2.3693715E-38)
            android.content.res.ColorStateList r13 = r10.createDefaultColorStateList(r13)
        L_0x00a4:
            int r0 = com.google.android.material.C0907R.styleable.NavigationView_itemTextAppearance
            boolean r0 = r12.hasValue(r0)
            r1 = 1
            if (r0 == 0) goto L_0x00b5
            int r0 = com.google.android.material.C0907R.styleable.NavigationView_itemTextAppearance
            int r0 = r12.getResourceId(r0, r8)
            r2 = 1
            goto L_0x00b7
        L_0x00b5:
            r0 = 0
            r2 = 0
        L_0x00b7:
            int r3 = com.google.android.material.C0907R.styleable.NavigationView_itemIconSize
            boolean r3 = r12.hasValue(r3)
            if (r3 == 0) goto L_0x00c8
            int r3 = com.google.android.material.C0907R.styleable.NavigationView_itemIconSize
            int r3 = r12.getDimensionPixelSize(r3, r8)
            r10.setItemIconSize(r3)
        L_0x00c8:
            r3 = 0
            int r4 = com.google.android.material.C0907R.styleable.NavigationView_itemTextColor
            boolean r4 = r12.hasValue(r4)
            if (r4 == 0) goto L_0x00d7
            int r3 = com.google.android.material.C0907R.styleable.NavigationView_itemTextColor
            android.content.res.ColorStateList r3 = r12.getColorStateList(r3)
        L_0x00d7:
            if (r2 != 0) goto L_0x00e2
            if (r3 != 0) goto L_0x00e2
            r3 = 16842806(0x1010036, float:2.369371E-38)
            android.content.res.ColorStateList r3 = r10.createDefaultColorStateList(r3)
        L_0x00e2:
            int r4 = com.google.android.material.C0907R.styleable.NavigationView_itemBackground
            android.graphics.drawable.Drawable r4 = r12.getDrawable(r4)
            if (r4 != 0) goto L_0x00f4
            boolean r5 = r10.hasShapeAppearance(r12)
            if (r5 == 0) goto L_0x00f4
            android.graphics.drawable.Drawable r4 = r10.createDefaultItemBackground(r12)
        L_0x00f4:
            int r5 = com.google.android.material.C0907R.styleable.NavigationView_itemHorizontalPadding
            boolean r5 = r12.hasValue(r5)
            if (r5 == 0) goto L_0x0105
            int r5 = com.google.android.material.C0907R.styleable.NavigationView_itemHorizontalPadding
            int r5 = r12.getDimensionPixelSize(r5, r8)
            r11.setItemHorizontalPadding(r5)
        L_0x0105:
            int r5 = com.google.android.material.C0907R.styleable.NavigationView_itemIconPadding
            int r5 = r12.getDimensionPixelSize(r5, r8)
            int r9 = com.google.android.material.C0907R.styleable.NavigationView_itemMaxLines
            int r9 = r12.getInt(r9, r1)
            r10.setItemMaxLines(r9)
            com.google.android.material.navigation.NavigationView$1 r9 = new com.google.android.material.navigation.NavigationView$1
            r9.<init>()
            r7.setCallback(r9)
            r11.setId(r1)
            r11.initForMenu(r6, r7)
            r11.setItemIconTintList(r13)
            int r13 = r10.getOverScrollMode()
            r11.setOverScrollMode(r13)
            if (r2 == 0) goto L_0x0131
            r11.setItemTextAppearance(r0)
        L_0x0131:
            r11.setItemTextColor(r3)
            r11.setItemBackground(r4)
            r11.setItemIconPadding(r5)
            r7.addMenuPresenter(r11)
            androidx.appcompat.view.menu.MenuView r11 = r11.getMenuView(r10)
            android.view.View r11 = (android.view.View) r11
            r10.addView(r11)
            int r11 = com.google.android.material.C0907R.styleable.NavigationView_menu
            boolean r11 = r12.hasValue(r11)
            if (r11 == 0) goto L_0x0157
            int r11 = com.google.android.material.C0907R.styleable.NavigationView_menu
            int r11 = r12.getResourceId(r11, r8)
            r10.inflateMenu(r11)
        L_0x0157:
            int r11 = com.google.android.material.C0907R.styleable.NavigationView_headerLayout
            boolean r11 = r12.hasValue(r11)
            if (r11 == 0) goto L_0x0168
            int r11 = com.google.android.material.C0907R.styleable.NavigationView_headerLayout
            int r11 = r12.getResourceId(r11, r8)
            r10.inflateHeaderView(r11)
        L_0x0168:
            r12.recycle()
            r10.setupInsetScrimsListener()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.navigation.NavigationView.<init>(android.content.Context, android.util.AttributeSet, int):void");
    }

    public void setOverScrollMode(int i) {
        super.setOverScrollMode(i);
        NavigationMenuPresenter navigationMenuPresenter = this.presenter;
        if (navigationMenuPresenter != null) {
            navigationMenuPresenter.setOverScrollMode(i);
        }
    }

    private boolean hasShapeAppearance(TintTypedArray tintTypedArray) {
        return tintTypedArray.hasValue(C0907R.styleable.NavigationView_itemShapeAppearance) || tintTypedArray.hasValue(C0907R.styleable.NavigationView_itemShapeAppearanceOverlay);
    }

    /* access modifiers changed from: protected */
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        MaterialShapeUtils.setParentAbsoluteElevation(this);
    }

    public void setElevation(float f) {
        if (Build.VERSION.SDK_INT >= 21) {
            super.setElevation(f);
        }
        MaterialShapeUtils.setElevation(this, f);
    }

    private final Drawable createDefaultItemBackground(TintTypedArray tintTypedArray) {
        MaterialShapeDrawable materialShapeDrawable = new MaterialShapeDrawable(ShapeAppearanceModel.builder(getContext(), tintTypedArray.getResourceId(C0907R.styleable.NavigationView_itemShapeAppearance, 0), tintTypedArray.getResourceId(C0907R.styleable.NavigationView_itemShapeAppearanceOverlay, 0)).build());
        materialShapeDrawable.setFillColor(MaterialResources.getColorStateList(getContext(), tintTypedArray, C0907R.styleable.NavigationView_itemShapeFillColor));
        return new InsetDrawable(materialShapeDrawable, tintTypedArray.getDimensionPixelSize(C0907R.styleable.NavigationView_itemShapeInsetStart, 0), tintTypedArray.getDimensionPixelSize(C0907R.styleable.NavigationView_itemShapeInsetTop, 0), tintTypedArray.getDimensionPixelSize(C0907R.styleable.NavigationView_itemShapeInsetEnd, 0), tintTypedArray.getDimensionPixelSize(C0907R.styleable.NavigationView_itemShapeInsetBottom, 0));
    }

    /* access modifiers changed from: protected */
    public Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        savedState.menuState = new Bundle();
        this.menu.savePresenterStates(savedState.menuState);
        return savedState;
    }

    /* access modifiers changed from: protected */
    public void onRestoreInstanceState(Parcelable parcelable) {
        if (!(parcelable instanceof SavedState)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.getSuperState());
        this.menu.restorePresenterStates(savedState.menuState);
    }

    public void setNavigationItemSelectedListener(OnNavigationItemSelectedListener onNavigationItemSelectedListener) {
        this.listener = onNavigationItemSelectedListener;
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        int mode = View.MeasureSpec.getMode(i);
        if (mode == Integer.MIN_VALUE) {
            i = View.MeasureSpec.makeMeasureSpec(Math.min(View.MeasureSpec.getSize(i), this.maxWidth), 1073741824);
        } else if (mode == 0) {
            i = View.MeasureSpec.makeMeasureSpec(this.maxWidth, 1073741824);
        }
        super.onMeasure(i, i2);
    }

    /* access modifiers changed from: protected */
    public void onInsetsChanged(WindowInsetsCompat windowInsetsCompat) {
        this.presenter.dispatchApplyWindowInsets(windowInsetsCompat);
    }

    public void inflateMenu(int i) {
        this.presenter.setUpdateSuspended(true);
        getMenuInflater().inflate(i, this.menu);
        this.presenter.setUpdateSuspended(false);
        this.presenter.updateMenuView(false);
    }

    public Menu getMenu() {
        return this.menu;
    }

    public View inflateHeaderView(int i) {
        return this.presenter.inflateHeaderView(i);
    }

    public void addHeaderView(View view) {
        this.presenter.addHeaderView(view);
    }

    public void removeHeaderView(View view) {
        this.presenter.removeHeaderView(view);
    }

    public int getHeaderCount() {
        return this.presenter.getHeaderCount();
    }

    public View getHeaderView(int i) {
        return this.presenter.getHeaderView(i);
    }

    public ColorStateList getItemIconTintList() {
        return this.presenter.getItemTintList();
    }

    public void setItemIconTintList(ColorStateList colorStateList) {
        this.presenter.setItemIconTintList(colorStateList);
    }

    public ColorStateList getItemTextColor() {
        return this.presenter.getItemTextColor();
    }

    public void setItemTextColor(ColorStateList colorStateList) {
        this.presenter.setItemTextColor(colorStateList);
    }

    public Drawable getItemBackground() {
        return this.presenter.getItemBackground();
    }

    public void setItemBackgroundResource(int i) {
        setItemBackground(ContextCompat.getDrawable(getContext(), i));
    }

    public void setItemBackground(Drawable drawable) {
        this.presenter.setItemBackground(drawable);
    }

    public int getItemHorizontalPadding() {
        return this.presenter.getItemHorizontalPadding();
    }

    public void setItemHorizontalPadding(int i) {
        this.presenter.setItemHorizontalPadding(i);
    }

    public void setItemHorizontalPaddingResource(int i) {
        this.presenter.setItemHorizontalPadding(getResources().getDimensionPixelSize(i));
    }

    public int getItemIconPadding() {
        return this.presenter.getItemIconPadding();
    }

    public void setItemIconPadding(int i) {
        this.presenter.setItemIconPadding(i);
    }

    public void setItemIconPaddingResource(int i) {
        this.presenter.setItemIconPadding(getResources().getDimensionPixelSize(i));
    }

    public void setCheckedItem(int i) {
        MenuItem findItem = this.menu.findItem(i);
        if (findItem != null) {
            this.presenter.setCheckedItem((MenuItemImpl) findItem);
        }
    }

    public void setCheckedItem(MenuItem menuItem) {
        MenuItem findItem = this.menu.findItem(menuItem.getItemId());
        if (findItem != null) {
            this.presenter.setCheckedItem((MenuItemImpl) findItem);
            return;
        }
        throw new IllegalArgumentException("Called setCheckedItem(MenuItem) with an item that is not in the current menu.");
    }

    public MenuItem getCheckedItem() {
        return this.presenter.getCheckedItem();
    }

    public void setItemTextAppearance(int i) {
        this.presenter.setItemTextAppearance(i);
    }

    public void setItemIconSize(int i) {
        this.presenter.setItemIconSize(i);
    }

    public void setItemMaxLines(int i) {
        this.presenter.setItemMaxLines(i);
    }

    public int getItemMaxLines() {
        return this.presenter.getItemMaxLines();
    }

    private MenuInflater getMenuInflater() {
        if (this.menuInflater == null) {
            this.menuInflater = new SupportMenuInflater(getContext());
        }
        return this.menuInflater;
    }

    private ColorStateList createDefaultColorStateList(int i) {
        TypedValue typedValue = new TypedValue();
        if (!getContext().getTheme().resolveAttribute(i, typedValue, true)) {
            return null;
        }
        ColorStateList colorStateList = AppCompatResources.getColorStateList(getContext(), typedValue.resourceId);
        if (!getContext().getTheme().resolveAttribute(C0041R.attr.colorPrimary, typedValue, true)) {
            return null;
        }
        int i2 = typedValue.data;
        int defaultColor = colorStateList.getDefaultColor();
        int[] iArr = DISABLED_STATE_SET;
        return new ColorStateList(new int[][]{iArr, CHECKED_STATE_SET, EMPTY_STATE_SET}, new int[]{colorStateList.getColorForState(iArr, defaultColor), i2, defaultColor});
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (Build.VERSION.SDK_INT < 16) {
            getViewTreeObserver().removeGlobalOnLayoutListener(this.onGlobalLayoutListener);
        } else {
            getViewTreeObserver().removeOnGlobalLayoutListener(this.onGlobalLayoutListener);
        }
    }

    private void setupInsetScrimsListener() {
        this.onGlobalLayoutListener = new ViewTreeObserver.OnGlobalLayoutListener() {
            public void onGlobalLayout() {
                NavigationView navigationView = NavigationView.this;
                navigationView.getLocationOnScreen(navigationView.tmpLocation);
                boolean z = true;
                boolean z2 = NavigationView.this.tmpLocation[1] == 0;
                NavigationView.this.presenter.setBehindStatusBar(z2);
                NavigationView.this.setDrawTopInsetForeground(z2);
                Activity activity = ContextUtils.getActivity(NavigationView.this.getContext());
                if (activity != null && Build.VERSION.SDK_INT >= 21) {
                    boolean z3 = activity.findViewById(16908290).getHeight() == NavigationView.this.getHeight();
                    boolean z4 = Color.alpha(activity.getWindow().getNavigationBarColor()) != 0;
                    NavigationView navigationView2 = NavigationView.this;
                    if (!z3 || !z4) {
                        z = false;
                    }
                    navigationView2.setDrawBottomInsetForeground(z);
                }
            }
        };
        getViewTreeObserver().addOnGlobalLayoutListener(this.onGlobalLayoutListener);
    }

    public static class SavedState extends AbsSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new Parcelable.ClassLoaderCreator<SavedState>() {
            public SavedState createFromParcel(Parcel parcel, ClassLoader classLoader) {
                return new SavedState(parcel, classLoader);
            }

            public SavedState createFromParcel(Parcel parcel) {
                return new SavedState(parcel, (ClassLoader) null);
            }

            public SavedState[] newArray(int i) {
                return new SavedState[i];
            }
        };
        public Bundle menuState;

        public SavedState(Parcel parcel, ClassLoader classLoader) {
            super(parcel, classLoader);
            this.menuState = parcel.readBundle(classLoader);
        }

        public SavedState(Parcelable parcelable) {
            super(parcelable);
        }

        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeBundle(this.menuState);
        }
    }
}

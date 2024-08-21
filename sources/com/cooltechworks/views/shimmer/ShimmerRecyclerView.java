package com.cooltechworks.views.shimmer;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.gms.common.ConnectionResult;

public class ShimmerRecyclerView extends RecyclerView {
    private RecyclerView.Adapter mActualAdapter;
    private RecyclerView.LayoutManager mActualLayoutManager;
    /* access modifiers changed from: private */
    public boolean mCanScroll;
    private int mGridCount;
    private LayoutMangerType mLayoutMangerType;
    private int mLayoutReference;
    private ShimmerAdapter mShimmerAdapter;
    private RecyclerView.LayoutManager mShimmerLayoutManager;

    public enum LayoutMangerType {
        LINEAR_VERTICAL,
        LINEAR_HORIZONTAL,
        GRID
    }

    public ShimmerRecyclerView(Context context) {
        super(context);
        init(context, (AttributeSet) null);
    }

    public ShimmerRecyclerView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init(context, attributeSet);
    }

    public ShimmerRecyclerView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init(context, attributeSet);
    }

    /* JADX INFO: finally extract failed */
    private void init(Context context, AttributeSet attributeSet) {
        this.mShimmerAdapter = new ShimmerAdapter();
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, C0832R.styleable.ShimmerRecyclerView, 0, 0);
        try {
            setDemoLayoutReference(obtainStyledAttributes.getResourceId(C0832R.styleable.ShimmerRecyclerView_shimmer_demo_layout, C0832R.layout.layout_sample_view));
            setDemoChildCount(obtainStyledAttributes.getInteger(C0832R.styleable.ShimmerRecyclerView_shimmer_demo_child_count, 10));
            setGridChildCount(obtainStyledAttributes.getInteger(C0832R.styleable.ShimmerRecyclerView_shimmer_demo_grid_child_count, 2));
            int integer = obtainStyledAttributes.getInteger(C0832R.styleable.ShimmerRecyclerView_shimmer_demo_layout_manager_type, 0);
            if (integer == 0) {
                setDemoLayoutManager(LayoutMangerType.LINEAR_VERTICAL);
            } else if (integer == 1) {
                setDemoLayoutManager(LayoutMangerType.LINEAR_HORIZONTAL);
            } else if (integer == 2) {
                setDemoLayoutManager(LayoutMangerType.GRID);
            } else {
                throw new IllegalArgumentException("This value for layout manager is not valid!");
            }
            int integer2 = obtainStyledAttributes.getInteger(C0832R.styleable.ShimmerRecyclerView_shimmer_demo_angle, 0);
            int color = obtainStyledAttributes.getColor(C0832R.styleable.ShimmerRecyclerView_shimmer_demo_shimmer_color, getColor(C0832R.C0833color.default_shimmer_color));
            Drawable drawable = obtainStyledAttributes.getDrawable(C0832R.styleable.ShimmerRecyclerView_shimmer_demo_view_holder_item_background);
            int integer3 = obtainStyledAttributes.getInteger(C0832R.styleable.ShimmerRecyclerView_shimmer_demo_duration, ConnectionResult.DRIVE_EXTERNAL_STORAGE_REQUIRED);
            boolean z = obtainStyledAttributes.getBoolean(C0832R.styleable.ShimmerRecyclerView_shimmer_demo_reverse_animation, false);
            obtainStyledAttributes.recycle();
            this.mShimmerAdapter.setShimmerAngle(integer2);
            this.mShimmerAdapter.setShimmerColor(color);
            this.mShimmerAdapter.setShimmerItemBackground(drawable);
            this.mShimmerAdapter.setShimmerDuration(integer3);
            this.mShimmerAdapter.setAnimationReversed(z);
            showShimmerAdapter();
        } catch (Throwable th) {
            obtainStyledAttributes.recycle();
            throw th;
        }
    }

    public void setGridChildCount(int i) {
        this.mGridCount = i;
    }

    public void setDemoLayoutManager(LayoutMangerType layoutMangerType) {
        this.mLayoutMangerType = layoutMangerType;
    }

    public void setDemoChildCount(int i) {
        this.mShimmerAdapter.setMinItemCount(i);
    }

    public void setDemoShimmerDuration(int i) {
        this.mShimmerAdapter.setShimmerDuration(i);
    }

    public void showShimmerAdapter() {
        this.mCanScroll = false;
        if (this.mShimmerLayoutManager == null) {
            initShimmerManager();
        }
        setLayoutManager(this.mShimmerLayoutManager);
        setAdapter(this.mShimmerAdapter);
    }

    public void hideShimmerAdapter() {
        this.mCanScroll = true;
        setLayoutManager(this.mActualLayoutManager);
        setAdapter(this.mActualAdapter);
    }

    public void setLayoutManager(RecyclerView.LayoutManager layoutManager) {
        if (layoutManager == null) {
            this.mActualLayoutManager = null;
        } else if (layoutManager != this.mShimmerLayoutManager) {
            this.mActualLayoutManager = layoutManager;
        }
        super.setLayoutManager(layoutManager);
    }

    public void setAdapter(RecyclerView.Adapter adapter) {
        if (adapter == null) {
            this.mActualAdapter = null;
        } else if (adapter != this.mShimmerAdapter) {
            this.mActualAdapter = adapter;
        }
        super.setAdapter(adapter);
    }

    public RecyclerView.Adapter getActualAdapter() {
        return this.mActualAdapter;
    }

    public int getLayoutReference() {
        return this.mLayoutReference;
    }

    public void setDemoLayoutReference(int i) {
        this.mLayoutReference = i;
        this.mShimmerAdapter.setLayoutReference(getLayoutReference());
    }

    /* renamed from: com.cooltechworks.views.shimmer.ShimmerRecyclerView$4 */
    static /* synthetic */ class C08394 {

        /* renamed from: $SwitchMap$com$cooltechworks$views$shimmer$ShimmerRecyclerView$LayoutMangerType */
        static final /* synthetic */ int[] f135xb74a9f5a;

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|(3:5|6|8)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        static {
            /*
                com.cooltechworks.views.shimmer.ShimmerRecyclerView$LayoutMangerType[] r0 = com.cooltechworks.views.shimmer.ShimmerRecyclerView.LayoutMangerType.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                f135xb74a9f5a = r0
                com.cooltechworks.views.shimmer.ShimmerRecyclerView$LayoutMangerType r1 = com.cooltechworks.views.shimmer.ShimmerRecyclerView.LayoutMangerType.LINEAR_VERTICAL     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = f135xb74a9f5a     // Catch:{ NoSuchFieldError -> 0x001d }
                com.cooltechworks.views.shimmer.ShimmerRecyclerView$LayoutMangerType r1 = com.cooltechworks.views.shimmer.ShimmerRecyclerView.LayoutMangerType.LINEAR_HORIZONTAL     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = f135xb74a9f5a     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.cooltechworks.views.shimmer.ShimmerRecyclerView$LayoutMangerType r1 = com.cooltechworks.views.shimmer.ShimmerRecyclerView.LayoutMangerType.GRID     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.cooltechworks.views.shimmer.ShimmerRecyclerView.C08394.<clinit>():void");
        }
    }

    private void initShimmerManager() {
        int i = C08394.f135xb74a9f5a[this.mLayoutMangerType.ordinal()];
        if (i == 1) {
            this.mShimmerLayoutManager = new LinearLayoutManager(getContext()) {
                public boolean canScrollVertically() {
                    return ShimmerRecyclerView.this.mCanScroll;
                }
            };
        } else if (i == 2) {
            this.mShimmerLayoutManager = new LinearLayoutManager(getContext(), 0, false) {
                public boolean canScrollHorizontally() {
                    return ShimmerRecyclerView.this.mCanScroll;
                }
            };
        } else if (i == 3) {
            this.mShimmerLayoutManager = new GridLayoutManager(getContext(), this.mGridCount) {
                public boolean canScrollVertically() {
                    return ShimmerRecyclerView.this.mCanScroll;
                }
            };
        }
    }

    private int getColor(int i) {
        if (Build.VERSION.SDK_INT >= 23) {
            return getContext().getColor(i);
        }
        return getResources().getColor(i);
    }
}
